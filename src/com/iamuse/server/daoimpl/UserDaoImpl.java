package com.iamuse.server.daoimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.mortbay.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.amuse.server.dao.TokenClsss;
import com.amuse.server.dao.UserDao;
import com.iamuse.admin.VO.TransactionHistoryVO;
import com.iamuse.server.entity.AccessToken;
import com.iamuse.server.entity.AdminEventPictureMapping;
import com.iamuse.server.entity.AdminPicture;
import com.iamuse.server.entity.Adminboothevent;
import com.iamuse.server.entity.BoothAdminLogin;
import com.iamuse.server.entity.BoothUploadImageEmail;
import com.iamuse.server.entity.CrashLogs;
import com.iamuse.server.entity.DeviceIp;
import com.iamuse.server.entity.DeviceRegistration;
import com.iamuse.server.entity.DeviceRegistrationAll;
import com.iamuse.server.entity.Fovbyuser;
import com.iamuse.server.entity.StatusCount;
import com.iamuse.server.entity.SubscriptionMaster;
import com.iamuse.server.entity.TransactionMappingAdmin;
import com.iamuse.server.entity.TransactionMaster;
import com.iamuse.server.entity.UploadImage;
import com.iamuse.server.entity.Usermaster;
import com.iamuse.server.requestVO.BaseRequestVO;
import com.iamuse.server.requestVO.BoothAdminRegistrationRequestVO;
import com.iamuse.server.requestVO.DeviceIPRequestVO;
import com.iamuse.server.requestVO.DeviceRegistrationRequestVO;
import com.iamuse.server.requestVO.DeviceTokenRequestVO;
import com.iamuse.server.requestVO.FetchingEventListRequestVO;
import com.iamuse.server.requestVO.IOSTranscationsDetailsRequestVO;
import com.iamuse.server.requestVO.LoginBoothAdminRegistrationRequestVO;
import com.iamuse.server.requestVO.RGBValueRequestVO;
import com.iamuse.server.requestVO.RestartVO;
import com.iamuse.server.requestVO.SubscriptionRequestVO;
import com.iamuse.server.requestVO.UploadImageWithEmailRequestVO;
import com.iamuse.server.responseVO.AdminBoothEventResponseVO;
import com.iamuse.server.responseVO.AdminEventPictureMappingResponse;
import com.iamuse.server.responseVO.AdminPictureResponseVO;
import com.iamuse.server.responseVO.BaseResponseVO;
import com.iamuse.server.responseVO.BoothAdminLoginResponseVO;
import com.iamuse.server.responseVO.DeviceRegistrationResponseVO;
import com.iamuse.server.responseVO.EventFetchingBaseResponseVO;
import com.iamuse.server.responseVO.LoginBaseResponseVO;
import com.iamuse.server.responseVO.SubscriptionMasterResponseVO;
import com.iamuse.server.util.Crypto;
import com.iamuse.server.util.DateUtils;
import com.iamuse.server.util.IAmuseUtil;
import com.iamuse.server.util.PushNotificationTaskRestart;
import com.iamuse.server.util.ServerConstants;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Integer uploadImage(String image) {
		Integer imageId = 0;
	//	image = image.replaceAll(" ", "_").toLowerCase();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			UploadImage uploadImage = new UploadImage();
			uploadImage.setImageUrl(image);
			uploadImage.setStatus(true);
			uploadImage.setIsDeleted(false);
			uploadImage.setIsValidate(false);
			imageId = (Integer) sessionFactory.getCurrentSession().save(uploadImage);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.getMessage();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return imageId;
	}

	@Override
	public boolean updateImageName(Integer imageName, String url, String userId) {
		boolean result = false;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			UploadImage uploadImage = (UploadImage) sessionFactory.getCurrentSession().load(UploadImage.class,
					imageName);
			String imageNamewithextension = imageName + ".jpg";
			uploadImage.setImageName(imageNamewithextension);
			uploadImage.setImageUrl(url);
			uploadImage.setUserId(Integer.parseInt(userId));
			uploadImage.setUploadTime(IAmuseUtil.getTimeStamp());
			sessionFactory.getCurrentSession().saveOrUpdate(uploadImage);
			sessionFactory.getCurrentSession().getTransaction().commit();
			result = true;
		} catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return result;
	}

	@Override
	public BoothAdminLogin getDefaultRGBValue(RGBValueRequestVO rbRgbValueRequestVO) {
		BoothAdminLogin boothAdminLogin = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteria.add(Restrictions.eq("userId", Integer.parseInt(rbRgbValueRequestVO.getUserId())));
			boothAdminLogin = (BoothAdminLogin) criteria.uniqueResult();
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return boothAdminLogin;
	}
	
	@Override
	public boolean saveDeviceToken(DeviceTokenRequestVO deviceTokenRequestVO) {
		boolean result = false;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Usermaster.class);
			criteria.add(Restrictions.eq("userId", Integer.parseInt(deviceTokenRequestVO.getUserId())));
			Usermaster usermaster = (Usermaster) criteria.uniqueResult();
			if (deviceTokenRequestVO.getDeviceToken() != null) {
				usermaster.setDeviceToken(deviceTokenRequestVO.getDeviceToken());
				sessionFactory.getCurrentSession().update(usermaster);
				result = true;
			} else {
				result = false;
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return result;
	}
	
	
	@Override
	public void removeAccessToken(TokenClsss token)
	{
		try {
			
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = this.sessionFactory.getCurrentSession();
			Query q = session.createQuery("delete AccessToken where userid = "+ token.getUserId());
			q.executeUpdate();
			sessionFactory.getCurrentSession().getTransaction().commit();
			
			/*
			 * sessionFactory.getCurrentSession().beginTransaction(); Criteria criteria=
			 * sessionFactory.getCurrentSession().createCriteria(AccessToken.class);
			 * criteria.add(Restrictions.eq("userid", token.getUserid())); AccessToken
			 * accessToken = (AccessToken) criteria.uniqueResult() ; if(accessToken!=null){
			 * sessionFactory.getCurrentSession().delete(accessToken);
			 * sessionFactory.getCurrentSession().getTransaction().commit(); } else {
			 * sessionFactory.getCurrentSession().getTransaction().commit(); }
			 */
			
			/*
			 * sessionFactory.getCurrentSession().beginTransaction(); Session session =
			 * sessionFactory.getCurrentSession();
			 * 
			 * Criteria criteriaTransactionMappingAdmin=
			 * session.createCriteria(AccessToken.class);
			 * criteriaTransactionMappingAdmin.add(Restrictions.eq("userid",
			 * token.getUserid())); List<AccessToken> transactionMappingAdmins =
			 * criteriaTransactionMappingAdmin.list(); if(transactionMappingAdmins != null){
			 * for (AccessToken transactionMappingAdmin : transactionMappingAdmins) {
			 * session.delete(transactionMappingAdmin); } }
			 */
		}
		catch(Exception e)
		{
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
	}
	
	
	
	@Override
	public UploadImage getImageDetails(int imageId) {
		UploadImage imageDetails = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UploadImage.class);
			criteria.add(Restrictions.eq("imageId", imageId));
			criteria.add(Restrictions.eq("status", true));
			imageDetails = (UploadImage) criteria.uniqueResult();
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return imageDetails;
	}	
	@Override
	public Integer uploadImageWithEmailId(String image, UploadImageWithEmailRequestVO uploadImageWithEmailRequestVO) {
		Integer imageId = 0;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			BoothUploadImageEmail uploadImageEmail = new BoothUploadImageEmail();
			uploadImageEmail.setMailImageUrl(image);
			uploadImageEmail.setEmailId(uploadImageWithEmailRequestVO.getEmailId());
			uploadImageEmail.setPhotoSessionId(uploadImageWithEmailRequestVO.getPhotoSessionId());
			uploadImageEmail.setPublicUseAck(Integer.toString(uploadImageWithEmailRequestVO.getPublicUseAck()));
			uploadImageEmail.setNewsletterOptIn(Integer.toString(uploadImageWithEmailRequestVO.getNewsletterOptIn()));
			uploadImageEmail.setFileName(uploadImageWithEmailRequestVO.getFileName());
			uploadImageEmail.setRenderVersion(uploadImageWithEmailRequestVO.getRenderVersion());
			uploadImageEmail.setShare(Integer.toString(uploadImageWithEmailRequestVO.getShare()));
			uploadImageEmail.setIsDeleted(false);
			uploadImageEmail.setStatus(true);
			uploadImageEmail.setDownloadStatus(0);
			imageId = (Integer) sessionFactory.getCurrentSession().save(uploadImageEmail);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return imageId;
	}

	@Override
	public Integer updateImageNameForEmailId(String imageName, String userId, Integer eventId, String image,
			UploadImageWithEmailRequestVO uploadImageWithEmailRequestVO, Integer defaultId, Integer picId) {
		Integer imageId = 0;
		Integer rowCount = 0;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			BoothUploadImageEmail uploadImageEmail = new BoothUploadImageEmail();
			uploadImageEmail.setMailImageName(imageName);
			uploadImageEmail.setUserId(Integer.parseInt(userId));
			uploadImageEmail.setUploadTime(IAmuseUtil.getTimeStamp());
			uploadImageEmail.setEventId(eventId);
			uploadImageEmail.setMailImageUrl(image);
			uploadImageEmail.setEmailId(uploadImageWithEmailRequestVO.getEmailId());
			uploadImageEmail.setPhotoSessionId(uploadImageWithEmailRequestVO.getPhotoSessionId());
			uploadImageEmail.setPublicUseAck("" + uploadImageWithEmailRequestVO.getPublicUseAck());
			uploadImageEmail.setNewsletterOptIn("" + uploadImageWithEmailRequestVO.getNewsletterOptIn());
			uploadImageEmail.setFileName(uploadImageWithEmailRequestVO.getFileName());
			uploadImageEmail.setRenderVersion(uploadImageWithEmailRequestVO.getRenderVersion());
			uploadImageEmail.setShare("" + uploadImageWithEmailRequestVO.getShare());
			uploadImageEmail.setIsDeleted(false);
			uploadImageEmail.setStatus(true);
			uploadImageEmail.setDownloadStatus(0);
			uploadImageEmail.setGuestMobileNo(uploadImageWithEmailRequestVO.getGuestMobileNumber());
			uploadImageEmail.setGuestUserName(uploadImageWithEmailRequestVO.getGuestName());
			uploadImageEmail.setPicId(picId);
			uploadImageEmail.setImageTimestamp(uploadImageWithEmailRequestVO.getImageTimestamp());
			if (uploadImageWithEmailRequestVO.getSessionTime() == null
					&& uploadImageWithEmailRequestVO.getSessionTime() == "") {
				uploadImageEmail.setSessionTime("0");
			} else if (uploadImageWithEmailRequestVO.getSessionTime().equalsIgnoreCase("nan")) {
				uploadImageEmail.setSessionTime("0");
			} else {
				uploadImageEmail.setSessionTime(uploadImageWithEmailRequestVO.getSessionTime());
			}

			if (defaultId != null) {
				uploadImageEmail.setDefaultId(defaultId);
			}
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
            criteria.add(Restrictions.eq("eventId", eventId));
            //criteria.add(Restrictions.eq("emailId", uploadImageWithEmailRequestVO.getEmailId()));
            criteria.setProjection(Projections.rowCount());
            List listOfEvents = criteria.list();
            if (listOfEvents!=null) {
                rowCount = (Integer) listOfEvents.get(0)+1;
                System.out.println("Total Results:" + rowCount);
            }
          	Integer guestSessionCount = rowCount;
			uploadImageEmail.setGuestSessions( Integer.toString(guestSessionCount));
			imageId = (Integer) sessionFactory.getCurrentSession().save(uploadImageEmail);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return imageId;
	}
	
	@Override
	public boolean updateEmailSendTime(String imageName, String userId) {
		boolean result = false;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			String[] img = imageName.split(",");// splits the string based on whitespace
			for (String w : img) {
				Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
				criteria.add(Restrictions.eq("mailImageName", w));
				criteria.add(Restrictions.eq("userId", Integer.parseInt(userId)));
				BoothUploadImageEmail imageDetails = (BoothUploadImageEmail) criteria.uniqueResult();
				if (imageDetails != null) {
					imageDetails.setMailSentTime(IAmuseUtil.getTimeStamp());
					sessionFactory.getCurrentSession().update(imageDetails);
				}
			}
			result = true;

			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}

		return result;
	}

	@Override
	public Integer crashlogsupload(String url) {
		Integer id = 0;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			CrashLogs crashLogs = new CrashLogs();
			crashLogs.setFileUrl(url);
			crashLogs.setStatus(true);
			id = (Integer) sessionFactory.getCurrentSession().save(crashLogs);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}

		return id;
	}

	@Override
	public boolean crashlogsuploadName(Integer id, String fileName, String userId) {
		boolean result = false;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			CrashLogs crashLogs = (CrashLogs) sessionFactory.getCurrentSession().load(CrashLogs.class, id);
			crashLogs.setFileName(fileName);
			crashLogs.setUploadTime(IAmuseUtil.getTimeStamp());
			crashLogs.setUserId(Integer.parseInt(userId));
			crashLogs.setReadStatus(false);
			sessionFactory.getCurrentSession().saveOrUpdate(crashLogs);
			result = true;
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean saveDeviceIP(DeviceIPRequestVO deviceIPRequestVO) {
		boolean result = false;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			DeviceIp deviceIp = new DeviceIp();
			deviceIp.setDeviceType(deviceIPRequestVO.getDeviceType());
			deviceIp.setDeviceIp(deviceIPRequestVO.getDeviceIP());
			deviceIp.setUploadTime(IAmuseUtil.getTimeStamp());
			deviceIp.setStatus(true);
			sessionFactory.getCurrentSession().save(deviceIp);
			result = true;
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return result;
	}

	@Override
	public DeviceIp getDeviceIP(DeviceIPRequestVO deviceIPRequestVO) {
		DeviceIp deviceIp = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DeviceIp.class);
			criteria.add(Restrictions.eq("deviceType", deviceIPRequestVO.getDeviceType()));
			deviceIp = (DeviceIp) criteria.uniqueResult();
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return deviceIp;
	}
	@Override
	public boolean addDataAccessToken(TokenClsss token) {
		
		boolean result = false;
		try {
			 	sessionFactory.getCurrentSession().beginTransaction();
				Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AccessToken.class);
				criteria.add(Restrictions.eq("userid", Integer.parseInt(token.getUserId())));
				AccessToken accessToken = (AccessToken) criteria.uniqueResult();
				AccessToken access = new AccessToken();
				if(accessToken == null) {
					access.setUserid((Integer.parseInt(token.getUserId())));
					access.setRefresh_token(token.getRefresh_token());
					access.setAccess_token(token.getAccess_token());
					access.setFlag(token.getFlag());
					access.setCreate_by(IAmuseUtil.getTimeStamp());
					access.setUpdated_by(IAmuseUtil.getTimeStamp());
					sessionFactory.getCurrentSession().save(access);					
				}				
				else if(accessToken != null) {
					accessToken.setUserid((Integer.parseInt(token.getUserId())));
					accessToken.setRefresh_token(token.getRefresh_token());
					accessToken.setAccess_token(token.getAccess_token());
					accessToken.setFlag(token.getFlag());
					accessToken.setCreate_by(IAmuseUtil.getTimeStamp());
					accessToken.setUpdated_by(IAmuseUtil.getTimeStamp());
					sessionFactory.getCurrentSession().update(accessToken);			
					}	        
				result = true;
				sessionFactory.getCurrentSession().getTransaction().commit();
			}catch(Exception e)
				{
					e.printStackTrace();
					sessionFactory.getCurrentSession().getTransaction().rollback();
					result = false;
				}
				return result;
	}

	public BoothAdminLogin updateSubsctiptionForAdmin(Integer userId, Integer subId) {
		BoothAdminLogin boothAdminLogin = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteria.add(Restrictions.eq("userId", userId));
			boothAdminLogin = (BoothAdminLogin) criteria.uniqueResult();
			boothAdminLogin.setSubId(subId);
			Date today = Calendar.getInstance().getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String folderName = formatter.format(today);
			Date startDate = formatter.parse(folderName);

			if (subId == 2) {
				Date newDate = DateUtils.addDays(startDate, 1);
				boothAdminLogin.setSubEndDate(newDate);
			} else if (subId == 3) {
				Date newDate = DateUtils.addDays(startDate, 30);
				boothAdminLogin.setSubEndDate(newDate);
			} 
			sessionFactory.getCurrentSession().saveOrUpdate(boothAdminLogin);
			sessionFactory.getCurrentSession().getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return boothAdminLogin;
	}
	
	public BoothAdminLogin updateSubscriptionPlan(Integer userId, Integer subId,String paymentAmount,Integer transactionId) {
		BoothAdminLogin boothAdminLogin = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteria.add(Restrictions.eq("userId", userId));
			boothAdminLogin = (BoothAdminLogin) criteria.uniqueResult();
			boothAdminLogin.setSubId(subId);
			/*Date today = Calendar.getInstance().getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String folderName = formatter.format(today);
			Date startDate = formatter.parse(folderName);*/

			if (subId == 2) {
				Date newDate = DateUtils.addDays(new Date(), 1);
				boothAdminLogin.setSubEndDate(newDate);
			} else if (subId == 3 && paymentAmount.equals("150.00")) {
				//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Calendar c = Calendar.getInstance();
		        c.setTime(boothAdminLogin.getSubEndDate());
		        c.add(Calendar.MONTH, 1);
				//Date today = Calendar.getInstance().getTime();
				//Date newDate = DateUtils.addDays(new Date(), 30);
				boothAdminLogin.setSubEndDate(c.getTime());
			} else if (subId == 3 && paymentAmount.equals("1200.00")) {
				Calendar c = Calendar.getInstance();
				c.setTime(boothAdminLogin.getSubEndDate());
		        c.add(Calendar.YEAR, 1);
				//Date newDate = DateUtils.addDays(new Date(), 365);
				boothAdminLogin.setSubEndDate(c.getTime());
				boothAdminLogin.setIs_annual(true);
			}
			sessionFactory.getCurrentSession().saveOrUpdate(boothAdminLogin);
			
			TransactionMappingAdmin transactionMappingAdmin = new TransactionMappingAdmin();
			transactionMappingAdmin.setUserId(userId);
			transactionMappingAdmin.setTransactionMasterId(transactionId);
			transactionMappingAdmin.setDate(new Date());
			sessionFactory.getCurrentSession().save(transactionMappingAdmin);
			sessionFactory.getCurrentSession().getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return boothAdminLogin;
	}

	
	@Override
	public LoginBaseResponseVO saveAdminBoothRegistration(
			BoothAdminRegistrationRequestVO adminBoothRegistrationRequestVO) {

		new ArrayList<>();
		new AdminEventPictureMappingResponse();
		LoginBaseResponseVO responseVo = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			responseVo = new LoginBaseResponseVO();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteria.add(Restrictions.eq("emailId", adminBoothRegistrationRequestVO.getEmailId().trim()));
			criteria.add(Restrictions.eq("status", true));
			criteria.add(Restrictions.eq("isDeleted", false));
			BoothAdminLogin adminBoothEntity = (BoothAdminLogin) criteria.uniqueResult();
			if (adminBoothEntity == null) {
				responseVo = new LoginBaseResponseVO();

				BoothAdminLogin boothAdminRegistrationEntity = new BoothAdminLogin();
				boothAdminRegistrationEntity.setUserType(adminBoothRegistrationRequestVO.getUserType());
				boothAdminRegistrationEntity.setEmailId(adminBoothRegistrationRequestVO.getEmailId().trim());
				boothAdminRegistrationEntity.setPassword(Crypto.encrypt(adminBoothRegistrationRequestVO.getPassword()));
				boothAdminRegistrationEntity.setUsername(adminBoothRegistrationRequestVO.getUserName());
				boothAdminRegistrationEntity.setLastname(adminBoothRegistrationRequestVO.getLastname());
				boothAdminRegistrationEntity.setStatus(true);
				boothAdminRegistrationEntity.setFacebookUrl("Facebook");
				boothAdminRegistrationEntity.setTwitterUrl("Twitter");
				boothAdminRegistrationEntity.setLoginTour(0);
				boothAdminRegistrationEntity.setSubId(ServerConstants.SUBSCRIPTION_NORMAL);
				boothAdminRegistrationEntity.setUserRole("boothadmin");
				boothAdminRegistrationEntity.setCreatedDate(Date.from(java.time.ZonedDateTime.now().toInstant()));
				boothAdminRegistrationEntity.setLocation(adminBoothRegistrationRequestVO.getLocation());
				boothAdminRegistrationEntity.setIsDeleted(false);
				boothAdminRegistrationEntity.setHexValueDefault("#341561");
				boothAdminRegistrationEntity.setRgbValueDefault("0,255,0");
				boothAdminRegistrationEntity.setRgbaValueDefault("0,255,0,255");
				boothAdminRegistrationEntity.setHexValueManual("#4EDB84");
				boothAdminRegistrationEntity.setRgbaValueManual("0,255,0");
				boothAdminRegistrationEntity.setRgbaValueManual("255,255,255,255");
				boothAdminRegistrationEntity.setUserType("Personal");
				boothAdminRegistrationEntity.setCurrentImageId(0);
				boothAdminRegistrationEntity.setLoginTour(0);
				boothAdminRegistrationEntity.setIsDefaultRgb(true);
				boothAdminRegistrationEntity.setSubUpdatedDate(Date.from(java.time.ZonedDateTime.now().toInstant()));
				if(adminBoothRegistrationRequestVO.getPin().equals(""))
				{
				boothAdminRegistrationEntity.setPin("0000");
				}
				else
				{
					boothAdminRegistrationEntity.setPin(adminBoothRegistrationRequestVO.getPin());
				}
				boothAdminRegistrationEntity.setContactNumber(adminBoothRegistrationRequestVO.getContactNumber());
				/*if (boothAdminRegistrationEntity.getSubId() == 4) {
					Date newDate = DateUtils.addDays(new Date(), 30);
					boothAdminRegistrationEntity.setSubEndDate(newDate);
				}*/
				Integer userId = (Integer) sessionFactory.getCurrentSession().save(boothAdminRegistrationEntity);
				if (userId != 0) {
					Fovbyuser fovbyuser = new Fovbyuser();
					fovbyuser.setUserId(userId);
					fovbyuser.setZoomScale("0.75");
					fovbyuser.setFovTop("0");
					fovbyuser.setFovLeft("0");
					fovbyuser.setFovRight("0");
					fovbyuser.setFovBottom("0");
					sessionFactory.getCurrentSession().save(fovbyuser);
				}

				Criteria criteriaAdminBoothLogin = sessionFactory.getCurrentSession()
						.createCriteria(BoothAdminLogin.class);
				criteriaAdminBoothLogin.add(Restrictions.eq("userId", userId));
				criteriaAdminBoothLogin.add(Restrictions.eq("status", true));
				criteriaAdminBoothLogin.add(Restrictions.eq("isDeleted", false));
				BoothAdminLogin adminBoothLoginEntity = (BoothAdminLogin) criteriaAdminBoothLogin.uniqueResult();
				BoothAdminLoginResponseVO login = new BoothAdminLoginResponseVO();
				if (adminBoothLoginEntity != null) {
					login.setContactNumber(adminBoothLoginEntity.getContactNumber());
					login.setCreatedDate(
							DateUtils.timeStampConvertIntoStringDateFormat(adminBoothLoginEntity.getCreatedDate()));
					login.setEmailId(adminBoothLoginEntity.getEmailId());
					login.setStatus(adminBoothLoginEntity.getStatus());
					login.setSubId(adminBoothLoginEntity.getSubId());
					login.setLocation(adminBoothLoginEntity.getLocation());
					login.setStatus(adminBoothLoginEntity.getStatus());
					if (adminBoothLoginEntity.getIsDeleted() != null) {
						login.setIsDeleted(adminBoothLoginEntity.getIsDeleted());
					}
					if (adminBoothLoginEntity.getSubUpdatedDate() != null) {
						login.setSubUpdatedDate(DateUtils
								.timeStampConvertIntoStringDateFormat(adminBoothLoginEntity.getSubUpdatedDate()));
					}
					if (adminBoothLoginEntity.getUpdatedDate() != null) {
						login.setUpdatedDate(
								DateUtils.timeStampConvertIntoStringDateFormat(adminBoothLoginEntity.getUpdatedDate()));
					}
					login.setUserId(adminBoothLoginEntity.getUserId());
					login.setUsername(adminBoothLoginEntity.getUsername());
					login.setLastname(adminBoothLoginEntity.getLastname());
					login.setUserRole(adminBoothLoginEntity.getUserRole());
					login.setHexValueDefault(adminBoothLoginEntity.getHexValueDefault());
					login.setRgbValueDefault(adminBoothLoginEntity.getRgbValueDefault());
					login.setRgbaValueDefault(adminBoothLoginEntity.getRgbaValueDefault());
					if (adminBoothLoginEntity.getCurrentImageId() != null) {
						login.setCurrentImageId(adminBoothLoginEntity.getCurrentImageId());
					}
					login.setIsDefaultRgb(adminBoothLoginEntity.getIsDefaultRgb());
					login.setHexValueManual(adminBoothLoginEntity.getHexValueManual());
					login.setRgbValueManual(adminBoothLoginEntity.getRgbValueManual());
					login.setRgbaValueManual(adminBoothLoginEntity.getRgbaValueManual());
				}

				if (adminBoothLoginEntity != null) {

					Criteria crt = sessionFactory.getCurrentSession().createCriteria(SubscriptionMaster.class)
							.add(Restrictions.eq("subId", adminBoothLoginEntity.getSubId()));
					crt.add(Restrictions.eq("status", true));
					crt.add(Restrictions.eq("isDeleted", false));
					List<SubscriptionMaster> subscriptionMasterList = crt.list();
					List<SubscriptionMasterResponseVO> subscriptionMasterResponseVoList = new ArrayList<>();
					SubscriptionMasterResponseVO vo = null;
					for (SubscriptionMaster s : subscriptionMasterList) {
						vo = new SubscriptionMasterResponseVO();
						if (s.getStatus() != null) {
							vo.setStatus(s.getStatus());
						}
						if (s.getIsDeleted() != null) {
							vo.setIsDeleted(s.getIsDeleted());
						}
						vo.setCreatedDate(DateUtils.timeStampConvertIntoStringDateFormat(s.getCreatedDate()));
						vo.setSubId(s.getSubId());
						vo.setSubName(s.getSubName());
						vo.setSubPrice(s.getSubPrice());
						vo.setSubValidaityDayPeriod(s.getSubValidaityDayPeriod());
						if (s.getCreatedUserId() != null) {
							vo.setCreatedUserId(s.getCreatedUserId());
						}

						if (s.getUpdatedByUserId() != null) {
							vo.setUpdatedByUserId(s.getUpdatedByUserId());
						}

						if (s.getUpdatedDate() != null) {
							vo.setUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormat(s.getUpdatedDate()));
						}
						subscriptionMasterResponseVoList.add(vo);
					}
					// end 11-11-2016
					Criteria criteriaDeviceRegistration = sessionFactory.getCurrentSession()
							.createCriteria(DeviceRegistration.class);
					criteriaDeviceRegistration.add(Restrictions.eq("status", true));
					criteriaDeviceRegistration.add(Restrictions.eq("isDeleted", false));
					criteriaDeviceRegistration.add(Restrictions.eq("userId", adminBoothLoginEntity.getUserId()));
					List<DeviceRegistration> deviceRegistration = criteriaDeviceRegistration.list();
					// start 11-11-2016
					List<DeviceRegistrationResponseVO> deviceResponseVOList = new ArrayList<>();
					DeviceRegistrationResponseVO deviceVO = null;
					for (DeviceRegistration d : deviceRegistration) {
						deviceVO = new DeviceRegistrationResponseVO();
						if (d.getStatus() != null) {
							deviceVO.setStatus(d.getStatus());
						}

						if (d.getIsDeleted() != null) {
							deviceVO.setIsDeleted(d.getIsDeleted());
						}
						if (d.getCreatedDate() != null) {
							deviceVO.setCreatedDate(DateUtils.timeStampConvertIntoStringDateFormat(d.getCreatedDate()));
						}
						deviceVO.setDeteactedResolution(d.getDeteactedResolution());
						deviceVO.setDeviceId(d.getDeviceId());
						deviceVO.setDeviceName(d.getDeviceName());
						deviceVO.setDeviceStorage(d.getDeviceStorage());
						deviceVO.setDeviceToken(d.getDeviceToken());
						deviceVO.setDeviceType(d.getDeviceType());
						deviceVO.setGuidedAccessEnabled(d.getGuidedAccessEnabled());
						deviceVO.setIpAddress(d.getIpAddress());
						if (d.getLastSyncTime() != null) {
							deviceVO.setLastSyncTime(
									DateUtils.timeStampConvertIntoStringDateFormat(d.getLastSyncTime()));
						}
						deviceVO.setOperationgSystemVersion(d.getOperationgSystemVersion());
						deviceVO.setUserId(d.getUserId());
						deviceVO.setWirelessNetwork(d.getWirelessNetwork());
						deviceVO.setDeviceUUID(d.getDeviceUUID());
						deviceVO.setSubNetMask(d.getSubNetMask());
						deviceResponseVOList.add(deviceVO);
					}

					// end 11-11-2016
					responseVo.setResponseCode("1");
					responseVo.setResponseDescription("Success");
					responseVo.setBoothAdminLoginResponse(login);
					responseVo.setSubscriptionMasterList(subscriptionMasterResponseVoList);
					responseVo.setDeviceRegistrationResponse(deviceResponseVOList);
				}

				responseVo.setResponseCode("1");
				responseVo.setResponseDescription("Success");
			} else {
				responseVo = new LoginBaseResponseVO();
				responseVo.setResponseCode("0");
				responseVo.setResponseDescription("Failure,Email id already registred!");
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return responseVo;
	}

	public LoginBaseResponseVO saveGmailBoothRegistration(
			BoothAdminRegistrationRequestVO adminBoothRegistrationRequestVO) {

		new ArrayList<>();
		new AdminEventPictureMappingResponse();
		LoginBaseResponseVO responseVo = null;
		BoothAdminLogin boothAdminRegistrationEntity = new BoothAdminLogin();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			responseVo = new LoginBaseResponseVO();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteria.add(Restrictions.eq("emailId", adminBoothRegistrationRequestVO.getEmailId().trim()));
			criteria.add(Restrictions.eq("status", true));
			//criteria.add(Restrictions.eq("isDeleted", false));
			BoothAdminLogin adminBoothEntity = (BoothAdminLogin) criteria.uniqueResult();
			if (adminBoothEntity == null) {
			  			
				responseVo = new LoginBaseResponseVO();
				
				boothAdminRegistrationEntity.setUserType(adminBoothRegistrationRequestVO.getUserType());
				boothAdminRegistrationEntity.setEmailId(adminBoothRegistrationRequestVO.getEmailId().trim());
				//boothAdminRegistrationEntity.setPassword(Crypto.encrypt(adminBoothRegistrationRequestVO.getPassword()));
				boothAdminRegistrationEntity.setUsername(adminBoothRegistrationRequestVO.getUserName());
				boothAdminRegistrationEntity.setToken(adminBoothRegistrationRequestVO.getToken());
				boothAdminRegistrationEntity.setFacebookUrl("Facebook");
				boothAdminRegistrationEntity.setTwitterUrl("Twitter");
				boothAdminRegistrationEntity.setLoginTour(0);
				boothAdminRegistrationEntity.setStatus(true);
				boothAdminRegistrationEntity.setSubId(ServerConstants.SUBSCRIPTION_NORMAL);
				boothAdminRegistrationEntity.setUserRole("boothadmin");
				boothAdminRegistrationEntity.setCreatedDate(Date.from(java.time.ZonedDateTime.now().toInstant()));
				boothAdminRegistrationEntity.setLocation(adminBoothRegistrationRequestVO.getLocation());
				boothAdminRegistrationEntity.setIsDeleted(false);
				boothAdminRegistrationEntity.setHexValueDefault("#341561");
				boothAdminRegistrationEntity.setRgbValueDefault("0,255,0");
				boothAdminRegistrationEntity.setRgbaValueDefault("0,255,0,255");
				boothAdminRegistrationEntity.setHexValueManual("#4EDB84");
				boothAdminRegistrationEntity.setRgbValueManual("0,255,0");
				boothAdminRegistrationEntity.setRgbaValueManual("255,255,255,255");
				boothAdminRegistrationEntity.setUserType("Personal");
				boothAdminRegistrationEntity.setCurrentImageId(0);
				boothAdminRegistrationEntity.setLoginTour(0);
				boothAdminRegistrationEntity.setIsDefaultRgb(true);
				boothAdminRegistrationEntity.setSubUpdatedDate(Date.from(java.time.ZonedDateTime.now().toInstant()));
				boothAdminRegistrationEntity.setPin("0000");
				boothAdminRegistrationEntity.setIs_annual(false);
				if (boothAdminRegistrationEntity.getSubId() == 3) {
					Date newDate = DateUtils.addDays(new Date(), 30);
					boothAdminRegistrationEntity.setSubEndDate(newDate);
				}
				System.out.println("BoothAdminRegistrationEntity Inside saveGmailBoothRegistration ****="+boothAdminRegistrationEntity);
				Integer userId = (Integer) sessionFactory.getCurrentSession().save(boothAdminRegistrationEntity);
				if (userId != 0) {
					Fovbyuser fovbyuser = new Fovbyuser();
					fovbyuser.setUserId(userId);
					fovbyuser.setZoomScale("0.75");
					fovbyuser.setFovTop("0");
					fovbyuser.setFovLeft("0");
					fovbyuser.setFovRight("0");
					fovbyuser.setFovBottom("0");
					sessionFactory.getCurrentSession().save(fovbyuser);
					
				}
			}
					if (adminBoothEntity != null) {
					Query query = sessionFactory.getCurrentSession().createQuery("update BoothAdminLogin set isDeleted = false where userId ="+adminBoothEntity.getUserId());
					int result = query.executeUpdate();
					System.out.println("Result="+result);
				}
			
			if (adminBoothEntity != null) {
			Criteria criteriaAdminBoothLogin = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteriaAdminBoothLogin.add(Restrictions.eq("emailId", adminBoothEntity.getEmailId()));
			criteriaAdminBoothLogin.add(Restrictions.eq("status", true));
			//criteriaAdminBoothLogin.add(Restrictions.eq("isDeleted", false));
			BoothAdminLogin adminBoothLoginEntity = (BoothAdminLogin) criteriaAdminBoothLogin.uniqueResult();
			//BoothAdminLogin login = new BoothAdminLogin();
			if (adminBoothEntity != null) {
				boothAdminRegistrationEntity.setContactNumber(adminBoothEntity.getContactNumber());
				boothAdminRegistrationEntity.setCreatedDate(new Date());
				boothAdminRegistrationEntity.setEmailId(adminBoothLoginEntity.getEmailId());
				boothAdminRegistrationEntity.setStatus(adminBoothLoginEntity.getStatus());
				boothAdminRegistrationEntity.setSubId(adminBoothLoginEntity.getSubId());
				boothAdminRegistrationEntity.setLocation(adminBoothLoginEntity.getLocation());
				boothAdminRegistrationEntity.setStatus(adminBoothLoginEntity.getStatus());
				//if (adminBoothLoginEntity.getIsDeleted() != null) {
				boothAdminRegistrationEntity.setIsDeleted(false);
				//}
				if (adminBoothLoginEntity.getSubUpdatedDate() != null) {
					boothAdminRegistrationEntity.setSubUpdatedDate(new Date());
				}
				if (adminBoothLoginEntity.getUpdatedDate() != null) {
					boothAdminRegistrationEntity.setUpdatedDate(new Date());
				}
				boothAdminRegistrationEntity.setUserId(adminBoothLoginEntity.getUserId());
				boothAdminRegistrationEntity.setUsername(adminBoothLoginEntity.getUsername());
				boothAdminRegistrationEntity.setUserRole(adminBoothLoginEntity.getUserRole());
				boothAdminRegistrationEntity.setHexValueDefault(adminBoothLoginEntity.getHexValueDefault());
				boothAdminRegistrationEntity.setRgbValueDefault(adminBoothLoginEntity.getRgbValueDefault());
				boothAdminRegistrationEntity.setRgbaValueDefault(adminBoothLoginEntity.getRgbaValueDefault());
				if (adminBoothEntity.getCurrentImageId() != null) {
					boothAdminRegistrationEntity.setCurrentImageId(adminBoothLoginEntity.getCurrentImageId());
				}
				boothAdminRegistrationEntity.setIsDefaultRgb(adminBoothLoginEntity.getIsDefaultRgb());
				boothAdminRegistrationEntity.setHexValueManual(adminBoothLoginEntity.getHexValueManual());
				boothAdminRegistrationEntity.setRgbValueManual(adminBoothLoginEntity.getRgbValueManual());
				boothAdminRegistrationEntity.setRgbaValueManual(adminBoothLoginEntity.getRgbaValueManual());
				boothAdminRegistrationEntity.setFacebookUrl("Facebook");
				boothAdminRegistrationEntity.setTwitterUrl("Twitter");
				boothAdminRegistrationEntity.setLoginTour(0);
				
			}		    
			}
			
			BoothAdminLoginResponseVO login = new BoothAdminLoginResponseVO();
			login.setUserId(boothAdminRegistrationEntity.getUserId());
			login.setUserType(boothAdminRegistrationEntity.getUserType());
			login.setEmailId(boothAdminRegistrationEntity.getEmailId().trim());
			//boothAdminRegistrationEntity.setPassword(Crypto.encrypt(adminBoothRegistrationRequestVO.getPassword()));
			login.setUsername(boothAdminRegistrationEntity.getUsername());
			login.setToken(boothAdminRegistrationEntity.getToken());
			login.setStatus(true);
			login.setSubId(ServerConstants.SUBSCRIPTION_NORMAL);
			login.setUserRole("boothadmin");
			login.setCreatedDate(DateUtils.timeStampConvertIntoStringDateFormat(boothAdminRegistrationEntity.getCreatedDate()));
			login.setLocation(boothAdminRegistrationEntity.getLocation());
			login.setIsDeleted(false);
			login.setHexValueDefault("#341561");
			login.setRgbValueDefault("0,255,0");
			login.setRgbaValueDefault("0,255,0,255");
			login.setHexValueManual("#4EDB84");
			login.setRgbValueManual("0,255,0");
			login.setRgbaValueManual("255,255,255,255");
			login.setUserType("Personal");
			login.setCurrentImageId(0);
			login.setIsDefaultRgb(true);
			login.setSubUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormat(boothAdminRegistrationEntity.getSubUpdatedDate()));
			login.setPin("0000");
			
				
					Criteria crt = sessionFactory.getCurrentSession().createCriteria(SubscriptionMaster.class);
					if(boothAdminRegistrationEntity.getSubId()==4)
					{
					crt.add(Restrictions.eq("subId",3));
					}
					else
					{
						crt.add(Restrictions.eq("subId",boothAdminRegistrationEntity.getSubId()));
					}
					crt.add(Restrictions.eq("status", true));
					crt.add(Restrictions.eq("isDeleted", false));
					List<SubscriptionMaster> subscriptionMasterList = crt.list();
					List<SubscriptionMasterResponseVO> subscriptionMasterResponseVoList = new ArrayList<>();
					SubscriptionMasterResponseVO vo = null;
					for (SubscriptionMaster s : subscriptionMasterList) {
						vo = new SubscriptionMasterResponseVO();
						if (s.getStatus() != null) {
							vo.setStatus(s.getStatus());
						}
						if (s.getIsDeleted() != null) {
							vo.setIsDeleted(s.getIsDeleted());
						}
						vo.setCreatedDate(DateUtils.timeStampConvertIntoStringDateFormat(s.getCreatedDate()));
						vo.setSubId(s.getSubId());
						vo.setSubName(s.getSubName());
						vo.setSubPrice(s.getSubPrice());
						vo.setSubValidaityDayPeriod(s.getSubValidaityDayPeriod());
						if (s.getCreatedUserId() != null) {
							vo.setCreatedUserId(s.getCreatedUserId());
						}

						if (s.getUpdatedByUserId() != null) {
							vo.setUpdatedByUserId(s.getUpdatedByUserId());
						}

						if (s.getUpdatedDate() != null) {
							vo.setUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormat(s.getUpdatedDate()));
						}
						subscriptionMasterResponseVoList.add(vo);
					}
					// end 11-11-2016
					Criteria criteriaDeviceRegistration = sessionFactory.getCurrentSession()
							.createCriteria(DeviceRegistration.class);
					criteriaDeviceRegistration.add(Restrictions.eq("status", true));
					criteriaDeviceRegistration.add(Restrictions.eq("isDeleted", false));
					criteriaDeviceRegistration.add(Restrictions.eq("userId", boothAdminRegistrationEntity.getUserId()));
					List<DeviceRegistration> deviceRegistration = criteriaDeviceRegistration.list();
					// start 11-11-2016
					List<DeviceRegistrationResponseVO> deviceResponseVOList = new ArrayList<>();
					DeviceRegistrationResponseVO deviceVO = null;
					for (DeviceRegistration d : deviceRegistration) {
						deviceVO = new DeviceRegistrationResponseVO();
						if (d.getStatus() != null) {
							deviceVO.setStatus(d.getStatus());
						}

						if (d.getIsDeleted() != null) {
							deviceVO.setIsDeleted(d.getIsDeleted());
						}
						if (d.getCreatedDate() != null) {
							deviceVO.setCreatedDate(DateUtils.timeStampConvertIntoStringDateFormat(d.getCreatedDate()));
						}
						deviceVO.setDeteactedResolution(d.getDeteactedResolution());
						deviceVO.setDeviceId(d.getDeviceId());
						deviceVO.setDeviceName(d.getDeviceName());
						deviceVO.setDeviceStorage(d.getDeviceStorage());
						deviceVO.setDeviceToken(d.getDeviceToken());
						deviceVO.setDeviceType(d.getDeviceType());
						deviceVO.setGuidedAccessEnabled(d.getGuidedAccessEnabled());
						deviceVO.setIpAddress(d.getIpAddress());
						if (d.getLastSyncTime() != null) {
							deviceVO.setLastSyncTime(DateUtils.timeStampConvertIntoStringDateFormat(d.getLastSyncTime()));
						}
						deviceVO.setOperationgSystemVersion(d.getOperationgSystemVersion());
						deviceVO.setUserId(d.getUserId());
						deviceVO.setWirelessNetwork(d.getWirelessNetwork());
						deviceVO.setDeviceUUID(d.getDeviceUUID());
						deviceVO.setSubNetMask(d.getSubNetMask());
						deviceResponseVOList.add(deviceVO);
					}

					// end 11-11-2016
					responseVo.setResponseCode("1");
					responseVo.setResponseDescription("Success");
					responseVo.setBoothAdminLoginResponse(login);
					responseVo.setSubscriptionMasterList(subscriptionMasterResponseVoList);
					responseVo.setDeviceRegistrationResponse(deviceResponseVOList);			
				
		sessionFactory.getCurrentSession().getTransaction().commit();	
		
		}catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return responseVo;
	}



	
		
	@Override
	public LoginBaseResponseVO fetchLoginBaseResponseVO(
			LoginBoothAdminRegistrationRequestVO loginRegistrationRequestVO) {
		new ArrayList<>();
		new AdminEventPictureMappingResponse();
		LoginBaseResponseVO responseVo = new LoginBaseResponseVO();

		try {
			sessionFactory.getCurrentSession().beginTransaction();

			Criteria criteriaAdminBoothLogin = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteriaAdminBoothLogin.add(Restrictions.eq("emailId", loginRegistrationRequestVO.getEmailId()));
			criteriaAdminBoothLogin.add(Restrictions.eq("password", Crypto.encrypt(loginRegistrationRequestVO.getPassword())));
			criteriaAdminBoothLogin.add(Restrictions.eq("status", true));
			criteriaAdminBoothLogin.add(Restrictions.eq("isDeleted", false));
			BoothAdminLogin adminBoothLoginEntity = (BoothAdminLogin) criteriaAdminBoothLogin.uniqueResult();
			if (adminBoothLoginEntity != null) {
			//Transaction-details
			Criteria criteriaTransactionMaster = sessionFactory.getCurrentSession().createCriteria(TransactionMaster.class);
			criteriaTransactionMaster.add(Restrictions.eq("userId", adminBoothLoginEntity.getUserId()));
			criteriaTransactionMaster.addOrder(Order.desc("paymentDate"));
			List<TransactionMaster> transactionMasterEntity = criteriaTransactionMaster.list();
			String amount="0";
			while(transactionMasterEntity.iterator().hasNext())
			{
				amount=transactionMasterEntity.iterator().next().getPaymentAmount();
				break;
			}
			Log.info("TransactionMaster1"+amount);
			BoothAdminLoginResponseVO login = new BoothAdminLoginResponseVO();
			
				login.setContactNumber(adminBoothLoginEntity.getContactNumber());
				login.setCreatedDate(
						DateUtils.timeStampConvertIntoStringDateFormat(adminBoothLoginEntity.getCreatedDate()));
				login.setEmailId(adminBoothLoginEntity.getEmailId());
				login.setStatus(adminBoothLoginEntity.getStatus());
				login.setSubId(adminBoothLoginEntity.getSubId());
				login.setLocation(adminBoothLoginEntity.getLocation());
				login.setStatus(adminBoothLoginEntity.getStatus());
				
				if (adminBoothLoginEntity.getIsDeleted() != null) {
					login.setIsDeleted(adminBoothLoginEntity.getIsDeleted());
				}
				if (adminBoothLoginEntity.getSubUpdatedDate() != null) {
					login.setSubUpdatedDate(
							DateUtils.timeStampConvertIntoStringDateFormat(adminBoothLoginEntity.getSubUpdatedDate()));
				}
				if (adminBoothLoginEntity.getUpdatedDate() != null) {
					login.setUpdatedDate(
							DateUtils.timeStampConvertIntoStringDateFormat(adminBoothLoginEntity.getUpdatedDate()));
				}
				login.setUserId(adminBoothLoginEntity.getUserId());
				login.setUsername(adminBoothLoginEntity.getUsername());
				login.setLastname(adminBoothLoginEntity.getLastname());
				login.setUserRole(adminBoothLoginEntity.getUserRole());
				// 123456789
				login.setHexValueDefault(adminBoothLoginEntity.getHexValueDefault());
				login.setRgbValueDefault(adminBoothLoginEntity.getRgbValueDefault());
				login.setRgbaValueDefault(adminBoothLoginEntity.getRgbaValueDefault());
				if (adminBoothLoginEntity.getCurrentImageId() != null) {
					login.setCurrentImageId(adminBoothLoginEntity.getCurrentImageId());
				}
				login.setIsDefaultRgb(adminBoothLoginEntity.getIsDefaultRgb());
				login.setHexValueManual(adminBoothLoginEntity.getHexValueManual());
				login.setRgbValueManual(adminBoothLoginEntity.getRgbValueManual());
				login.setRgbaValueManual(adminBoothLoginEntity.getRgbaValueManual());
			

			
				Criteria crt = sessionFactory.getCurrentSession().createCriteria(SubscriptionMaster.class);
				if(adminBoothLoginEntity.getSubId()==4)
				{
				crt.add(Restrictions.eq("subId",3));
				}
				else
				{
					crt.add(Restrictions.eq("subId",adminBoothLoginEntity.getSubId()));
				}
				crt.add(Restrictions.eq("status", true));
				crt.add(Restrictions.eq("isDeleted", false));
				List<SubscriptionMaster> subscriptionMasterList = crt.list();

				// start 11-11-2016
				List<SubscriptionMasterResponseVO> subscriptionMasterResponseVoList = new ArrayList<>();
				SubscriptionMasterResponseVO vo = null;
				for (SubscriptionMaster s : subscriptionMasterList) {
					vo = new SubscriptionMasterResponseVO();
					if (s.getStatus() != null) {
						vo.setStatus(s.getStatus());
					}
					if (s.getIsDeleted() != null) {
						vo.setIsDeleted(s.getIsDeleted());
					}
					if(adminBoothLoginEntity.getIs_annual()!=null)
					{
						vo.setIs_annual(adminBoothLoginEntity.getIs_annual());
					}
					vo.setCreatedDate(DateUtils.timeStampConvertIntoStringDateFormat(s.getCreatedDate()));
					vo.setSubId(s.getSubId());
					vo.setSubName(s.getSubName());
					vo.setSubPrice(s.getSubPrice());
					vo.setSubValidaityDayPeriod(s.getSubValidaityDayPeriod());
					if (s.getCreatedUserId() != null) {
						vo.setCreatedUserId(s.getCreatedUserId());
					}
					if (s.getUpdatedByUserId() != null) {
						vo.setUpdatedByUserId(s.getUpdatedByUserId());
					}
					if (s.getUpdatedDate() != null) {
						vo.setUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormat(s.getUpdatedDate()));
					}
					subscriptionMasterResponseVoList.add(vo);
				}
				// end 11-11-2016
				Criteria criteriaDeviceRegistration = sessionFactory.getCurrentSession()
						.createCriteria(DeviceRegistration.class);
				criteriaDeviceRegistration.add(Restrictions.eq("status", true));
				criteriaDeviceRegistration.add(Restrictions.eq("isDeleted", false));
				criteriaDeviceRegistration.add(Restrictions.eq("userId", adminBoothLoginEntity.getUserId()));
				List<DeviceRegistration> deviceRegistration = criteriaDeviceRegistration.list();
				// start 11-11-2016
				List<DeviceRegistrationResponseVO> deviceResponseVOList = new ArrayList<>();
				DeviceRegistrationResponseVO deviceVO = null;
				for (DeviceRegistration d : deviceRegistration) {
					deviceVO = new DeviceRegistrationResponseVO();
					if (d.getStatus() != null) {
						deviceVO.setStatus(d.getStatus());
					}
					if (d.getIsDeleted() != null) {
						deviceVO.setIsDeleted(d.getIsDeleted());
					}
					if (d.getCreatedDate() != null) {
						deviceVO.setCreatedDate(DateUtils.timeStampConvertIntoStringDateFormat(d.getCreatedDate()));
					}
					deviceVO.setDeteactedResolution(d.getDeteactedResolution());
					deviceVO.setDeviceId(d.getDeviceId());
					deviceVO.setDeviceName(d.getDeviceName());
					deviceVO.setDeviceStorage(d.getDeviceStorage());
					deviceVO.setDeviceToken(d.getDeviceToken());
					deviceVO.setDeviceType(d.getDeviceType());
					deviceVO.setGuidedAccessEnabled(d.getGuidedAccessEnabled());
					deviceVO.setIpAddress(d.getIpAddress());
					if (d.getLastSyncTime() != null) {
						deviceVO.setLastSyncTime(DateUtils.timeStampConvertIntoStringDateFormat(d.getLastSyncTime()));
					}
					deviceVO.setOperationgSystemVersion(d.getOperationgSystemVersion());
					deviceVO.setUserId(d.getUserId());
					deviceVO.setWirelessNetwork(d.getWirelessNetwork());
					deviceVO.setDeviceUUID(d.getDeviceUUID());
					deviceVO.setSubNetMask(d.getSubNetMask());
					deviceResponseVOList.add(deviceVO);
				}
				// end 11-11-2016
				responseVo.setResponseCode("1");
				responseVo.setResponseDescription("Success");
				responseVo.setBoothAdminLoginResponse(login);
				responseVo.setSubscriptionMasterList(subscriptionMasterResponseVoList);
				responseVo.setDeviceRegistrationResponse(deviceResponseVOList);
			} else {
				responseVo = new LoginBaseResponseVO();
				responseVo.setResponseCode("0");
				responseVo.setResponseDescription("Please enter the correct email id and password");
				responseVo.setBoothAdminLoginResponse(new BoothAdminLoginResponseVO());
				responseVo.setSubscriptionMasterList(new ArrayList<SubscriptionMasterResponseVO>());
				responseVo.setDeviceRegistrationResponse(new ArrayList<DeviceRegistrationResponseVO>());
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return responseVo;

	}


	@Override
	public LoginBaseResponseVO fetchGmailLoginBaseResponseVO(
			LoginBoothAdminRegistrationRequestVO loginRegistrationRequestVO) {
		new ArrayList<>();
		new AdminEventPictureMappingResponse();
		LoginBaseResponseVO responseVo = new LoginBaseResponseVO();

		try {
			sessionFactory.getCurrentSession().beginTransaction();

			Criteria criteriaAdminBoothLogin = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteriaAdminBoothLogin.add(Restrictions.eq("emailId", loginRegistrationRequestVO.getEmailId()));
			criteriaAdminBoothLogin.add(Restrictions.eq("status", true));
			criteriaAdminBoothLogin.add(Restrictions.eq("isDeleted", false));

			BoothAdminLogin adminBoothLoginEntity = (BoothAdminLogin) criteriaAdminBoothLogin.uniqueResult();
			BoothAdminLoginResponseVO login = new BoothAdminLoginResponseVO();
			
			 //Transaction-details 
			  /*Criteria criteriaTransactionMaster = sessionFactory.getCurrentSession().createCriteria(TransactionMaster.class);
			  criteriaTransactionMaster.add(Restrictions.eq("userId",adminBoothLoginEntity.getUserId()));
			  criteriaTransactionMaster.addOrder(Order.desc("paymentDate"));*/
			
			
			if (adminBoothLoginEntity != null) {
				login.setContactNumber(adminBoothLoginEntity.getContactNumber());
				//login.setCreatedDate(DateUtils.timeStampConvertIntoStringDateFormat(adminBoothLoginEntity.getCreatedDate()));
				login.setEmailId(adminBoothLoginEntity.getEmailId());
				login.setStatus(adminBoothLoginEntity.getStatus());
				
				login.setSubId(adminBoothLoginEntity.getSubId());
				login.setLocation(adminBoothLoginEntity.getLocation());
				login.setStatus(adminBoothLoginEntity.getStatus());
				if (adminBoothLoginEntity.getIsDeleted() != null) {
					login.setIsDeleted(adminBoothLoginEntity.getIsDeleted());
				}
				/*if (adminBoothLoginEntity.getSubUpdatedDate() != null) {
					login.setSubUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormat(adminBoothLoginEntity.getSubUpdatedDate()));
				}
				if (adminBoothLoginEntity.getUpdatedDate() != null) {
					login.setUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormat(adminBoothLoginEntity.getUpdatedDate()));
				}*/
				login.setUserId(adminBoothLoginEntity.getUserId());
				login.setUsername(adminBoothLoginEntity.getUsername());
				login.setUserRole(adminBoothLoginEntity.getUserRole());
				// 123456789
				login.setHexValueDefault(adminBoothLoginEntity.getHexValueDefault());
				login.setRgbValueDefault(adminBoothLoginEntity.getRgbValueDefault());
				login.setRgbaValueDefault(adminBoothLoginEntity.getRgbaValueDefault());
				if (adminBoothLoginEntity.getCurrentImageId() != null) {
					login.setCurrentImageId(adminBoothLoginEntity.getCurrentImageId());
				}
				login.setIsDefaultRgb(adminBoothLoginEntity.getIsDefaultRgb());
				login.setHexValueManual(adminBoothLoginEntity.getHexValueManual());
				login.setRgbValueManual(adminBoothLoginEntity.getRgbValueManual());
				login.setRgbaValueManual(adminBoothLoginEntity.getRgbaValueManual());
			}

			
			if (adminBoothLoginEntity != null) {
				Criteria crt = sessionFactory.getCurrentSession().createCriteria(SubscriptionMaster.class)
						.add(Restrictions.eq("subId", adminBoothLoginEntity.getSubId()));
				crt.add(Restrictions.eq("status", true));
				crt.add(Restrictions.eq("isDeleted", false));
				List<SubscriptionMaster> subscriptionMasterList = crt.list();

				// start 11-11-2016
				List<SubscriptionMasterResponseVO> subscriptionMasterResponseVoList = new ArrayList<>();
				SubscriptionMasterResponseVO vo = null;
				for (SubscriptionMaster s : subscriptionMasterList) {
					vo = new SubscriptionMasterResponseVO();
					if (s.getStatus() != null) {
						vo.setStatus(s.getStatus());
					}
					if (s.getIsDeleted() != null) {
						vo.setIsDeleted(s.getIsDeleted());
					}
					vo.setCreatedDate(DateUtils.timeStampConvertIntoStringDateFormat(s.getCreatedDate()));
					
					vo.setSubId(s.getSubId());
					vo.setSubName(s.getSubName());
					vo.setSubPrice(s.getSubPrice());
					vo.setSubValidaityDayPeriod(s.getSubValidaityDayPeriod());
					if (s.getCreatedUserId() != null) {
						vo.setCreatedUserId(s.getCreatedUserId());
					}
					if (s.getUpdatedByUserId() != null) {
						vo.setUpdatedByUserId(s.getUpdatedByUserId());
					}
					if (s.getUpdatedDate() != null) {
						vo.setUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormat(s.getUpdatedDate()));
					}
					subscriptionMasterResponseVoList.add(vo);
				}
				// end 11-11-2016
				Criteria criteriaDeviceRegistration = sessionFactory.getCurrentSession()
						.createCriteria(DeviceRegistration.class);
				criteriaDeviceRegistration.add(Restrictions.eq("status", true));
				criteriaDeviceRegistration.add(Restrictions.eq("isDeleted", false));
				criteriaDeviceRegistration.add(Restrictions.eq("userId", adminBoothLoginEntity.getUserId()));
				List<DeviceRegistration> deviceRegistration = criteriaDeviceRegistration.list();
				// start 11-11-2016
				List<DeviceRegistrationResponseVO> deviceResponseVOList = new ArrayList<>();
				DeviceRegistrationResponseVO deviceVO = null;
				for (DeviceRegistration d : deviceRegistration) {
					deviceVO = new DeviceRegistrationResponseVO();
					if (d.getStatus() != null) {
						deviceVO.setStatus(d.getStatus());
					}
					if (d.getIsDeleted() != null) {
						deviceVO.setIsDeleted(d.getIsDeleted());
					}
					if (d.getCreatedDate() != null) {
						deviceVO.setCreatedDate(DateUtils.timeStampConvertIntoStringDateFormat(d.getCreatedDate()));
					}
					deviceVO.setDeteactedResolution(d.getDeteactedResolution());
					deviceVO.setDeviceId(d.getDeviceId());
					deviceVO.setDeviceName(d.getDeviceName());
					deviceVO.setDeviceStorage(d.getDeviceStorage());
					deviceVO.setDeviceToken(d.getDeviceToken());
					deviceVO.setDeviceType(d.getDeviceType());
					deviceVO.setGuidedAccessEnabled(d.getGuidedAccessEnabled());
					deviceVO.setIpAddress(d.getIpAddress());
					if (d.getLastSyncTime() != null) {
						deviceVO.setLastSyncTime(DateUtils.timeStampConvertIntoStringDateFormat(d.getLastSyncTime()));
					}
					deviceVO.setOperationgSystemVersion(d.getOperationgSystemVersion());
					deviceVO.setUserId(d.getUserId());
					deviceVO.setWirelessNetwork(d.getWirelessNetwork());
					deviceVO.setDeviceUUID(d.getDeviceUUID());
					deviceVO.setSubNetMask(d.getSubNetMask());
					deviceResponseVOList.add(deviceVO);
				}
				// end 11-11-2016
				responseVo.setResponseCode("1");
				responseVo.setResponseDescription("Success");
				responseVo.setBoothAdminLoginResponse(login);
				responseVo.setSubscriptionMasterList(subscriptionMasterResponseVoList);
				responseVo.setDeviceRegistrationResponse(deviceResponseVOList);
			} 
			
		else {
				responseVo = new LoginBaseResponseVO();
				responseVo.setResponseCode("0");
				responseVo.setResponseDescription("Please enter the correct email id and password");
				responseVo.setBoothAdminLoginResponse(new BoothAdminLoginResponseVO());
				responseVo.setSubscriptionMasterList(new ArrayList<SubscriptionMasterResponseVO>());
				responseVo.setDeviceRegistrationResponse(new ArrayList<DeviceRegistrationResponseVO>());
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return responseVo;

	}

	
	
	@Override
	public EventFetchingBaseResponseVO fetchEventFetchingAdminBooth(
			FetchingEventListRequestVO fetchinfEventAdminBoothRequestVo) {
		AdminEventPictureMappingResponse adminEventPictureMappingObject = new AdminEventPictureMappingResponse();
		EventFetchingBaseResponseVO responseVo = new EventFetchingBaseResponseVO();
		List<AdminBoothEventResponseVO> responseVOList = new ArrayList<>();
		try {

			sessionFactory.getCurrentSession().beginTransaction();

			Integer userId = new Integer(fetchinfEventAdminBoothRequestVo.getUserId());

			if (fetchinfEventAdminBoothRequestVo.getSubId() != 0) {
				// if(fetchinfEventAdminBoothRequestVo.getSubId()!=1 ){
				Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
				//if (fetchinfEventAdminBoothRequestVo.getSubId() != 1) {
				Criterion c1 = Restrictions.eq("createdBy", userId);
				Criterion c2 = Restrictions.eq("createdBy", 47);
				LogicalExpression orExp = Restrictions.or(c1,c2);
				criteria.add(orExp);
				
				//}
				//criteria.add(Restrictions.eq("status", true));
				//criteria.add(Restrictions.eq("isDeleted", false));
				/*if (fetchinfEventAdminBoothRequestVo.getSubId() == 1) {
					criteria.add(Restrictions.eq("eventType", "default"));
				}*/
				criteria.addOrder(Order.desc("EId"));
				List<Adminboothevent> adminEventPictureMapping = criteria.list();
				// Criteria criteria
				// =sessionFactory.getCurrentSession().createCriteria(AdminEventPictureMapping.class);
				// ProjectionList projList = Projections.projectionList();
				// projList.add(Projections.property("EId"));
				// criteria.setProjection(Projections.distinct(projList));
				// criteria.add(Restrictions.eq("status", true));
				// criteria.add(Restrictions.eq("isDeleted", false));
				// criteria.addOrder(Order.desc("EId"));
				// List<Integer> adminEventPictureMapping
				// =criteria.add(Restrictions.eq("userId",userId)).setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE).list();

				if (adminEventPictureMapping.size() > 0) {
					for (Adminboothevent object : adminEventPictureMapping) {

						if (object != null) {
							Criteria criteriaFovbyuser = sessionFactory.getCurrentSession().createCriteria(Fovbyuser.class);
							criteriaFovbyuser.add(Restrictions.eq("userId", userId));
							Fovbyuser fovbyuser = (Fovbyuser) criteriaFovbyuser.uniqueResult();

							
							Criteria criteriaFovPin = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
							criteriaFovPin.add(Restrictions.eq("userId", fetchinfEventAdminBoothRequestVo.getUserId()));
							BoothAdminLogin boothAdminLogin = (BoothAdminLogin) criteriaFovPin.uniqueResult();
							
												
							Criteria criteriaAdminBoothEvent = sessionFactory.getCurrentSession()
									.createCriteria(Adminboothevent.class);
							criteriaAdminBoothEvent.add(Restrictions.eq("status", true));
							criteriaAdminBoothEvent.add(Restrictions.eq("isDeleted", false));
							if (fovbyuser != null) {
								criteria.add(Restrictions.eq("zoomScale", fovbyuser.getZoomScale()));
							} else {
								criteria.add(Restrictions.eq("zoomScale", "0.75"));
							}
							List<Adminboothevent> eventList = criteriaAdminBoothEvent
									.add(Restrictions.eq("EId", object.getEId())).list();

							AdminBoothEventResponseVO responseVO = null;
							for (Adminboothevent adminboothevent : eventList) {

								responseVO = new AdminBoothEventResponseVO();
								Criteria criteriaAdminPicture = sessionFactory.getCurrentSession()
										.createCriteria(AdminPicture.class);
								criteriaAdminPicture.add(Restrictions.eq("status", true));
								criteriaAdminPicture.add(Restrictions.eq("isDeleted", false));
								List<AdminPicture> adminPictureList = criteriaAdminPicture
										.add(Restrictions.eq("eId", adminboothevent.getEId())).list();
								// Start 11-11-2016
								List<AdminPictureResponseVO> pictureResponseList = new ArrayList<>();
								AdminPictureResponseVO picture = null;
								for (AdminPicture p : adminPictureList) {
									picture = new AdminPictureResponseVO();
									picture.setCreatedBy(p.getCreatedBy());
									picture.setCreatedDate(
											DateUtils.timeStampConvertIntoStringDateFormat(p.getCreatedDate()));
									picture.seteId(p.geteId());
									picture.setImageMask(p.getImageMask());
									picture.setPicId(p.getPicId());
									picture.setPicName(p.getPicName());
									picture.setPicTitle(p.getPicTitle());
									picture.setRgbValues(p.getRgbValues());
									picture.setScaleXOffset(p.getScaleXOffset());
									picture.setScaleYOffset(p.getScaleYOffset());
									picture.setScaleZOffset(p.getScaleZOffset());
									picture.setUpdatedBy(p.getUpdatedBy());
									picture.setWaterMarkImage(p.getWaterMarkImage());
									if (p.getUpdatedDate() != null) {
										picture.setUpdatedDate(
												DateUtils.timeStampConvertIntoStringDateFormat(p.getUpdatedDate()));
									}
									if(p.getImageHeight()!=null) {
										picture.setImageHeight(p.getImageHeight());
									}
									if (p.getImageWidth() != null) {
										picture.setImageWidth(p.getImageWidth());
									}
									if (p.getScalingHeight() != null) {
										picture.setScalingHeight(p.getScalingHeight());
									}
									if (p.getScalingWidth() != null) {
										picture.setScalingWidth(p.getScalingWidth());
									}
									if (p.getStatus() != null) {
										picture.setStatus(p.getStatus());
									}
									if (p.getIsDeleted() != null) {
										picture.setIsDeleted(p.getIsDeleted());
									}
									pictureResponseList.add(picture);
								}
								responseVO.setAdminBoothEventPicture(pictureResponseList);
								// end 11-11-2016
								responseVO.setCreatedBy(adminboothevent.getCreatedBy());
								if (adminboothevent.getStatus() != null) {
									responseVO.setStatus(adminboothevent.getStatus());
								}

								if (adminboothevent.getIsDeleted() != null) {
									responseVO.setIsDeleted(adminboothevent.getIsDeleted());
								}
								responseVO.setCreatedDate(DateUtils
										.timeStampConvertIntoStringDateFormat(adminboothevent.getCreatedDate()));
								responseVO.setEId(adminboothevent.getEId());
								responseVO.setEventEnd(adminboothevent.getEventEnd());
								responseVO.setEventHostMailerId(adminboothevent.getEventHostMailerId());
								responseVO.setEventLocation(adminboothevent.getEventLocation());
								responseVO.setEventName(adminboothevent.getEventName());
								responseVO.setEventStart(adminboothevent.getEventStart());
								responseVO.setUpdatedBy(adminboothevent.getUpdatedBy());
								responseVO.setIsPhone(adminboothevent.getIsPhone());
								responseVO.setIsName(adminboothevent.getIsName());
								if (adminboothevent.getUpdatedDate() != null) {
									responseVO.setUpdatedDate(DateUtils
											.timeStampConvertIntoStringDateFormat(adminboothevent.getUpdatedDate()));
								}
								responseVO.setSponsorName(adminboothevent.getSponsorName());
								responseVO.setIsSubscribed(adminboothevent.getIsSubscribed());
								responseVO.setFovTop(adminboothevent.getFovTop());
								responseVO.setFovBottom(adminboothevent.getFovBottom());
								responseVO.setFovLeft(adminboothevent.getFovLeft());
								responseVO.setFovRight(adminboothevent.getFovRight());
								responseVO.setGreenScreenWidth(adminboothevent.getGreenScreenWidth());
								responseVO.setGreenScreenDistance(adminboothevent.getGreenScreenDistance());
								responseVO.setGreenScreenHeight(adminboothevent.getGreenScreenHeight());
								responseVO.setGreenScreenCountdownDelay(adminboothevent.getGreenScreenCountdownDelay());
								responseVO.setOtherIntractionTimout(adminboothevent.getOtherIntractionTimout());
								responseVO.setOtherCountdownDelay(adminboothevent.getOtherCountdownDelay());
								// Added By Abhishek Dated 4-01-2017
								responseVO.setThankYouScreen(adminboothevent.getThankYouScreen());
								responseVO.setCameraTVScreenSaver(adminboothevent.getCameraTVScreenSaver());
								responseVO.setLookAtTouchScreen(adminboothevent.getLookAtTouchScreen());
								responseVO.setWaterMarkImage(adminboothevent.getWaterMarkImage());
								responseVO.setPin(boothAdminLogin.getPin());
								responseVOList.add(responseVO);
							}
						}
						adminEventPictureMappingObject.setModifiedResult(responseVOList);
					}
				}
				/*
				 * }else{ System.out.println(" hi abhishek fetch the default event ");
				 * 
				 * Criteria criteriaAdminBoothEvent
				 * =sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
				 * criteriaAdminBoothEvent.add(Restrictions.eq("status", true));
				 * criteriaAdminBoothEvent.add(Restrictions.eq("isDeleted", false));
				 * List<Adminboothevent>
				 * eventList=criteriaAdminBoothEvent.add(Restrictions.eq("eventType",
				 * "default")).list();
				 * 
				 * AdminBoothEventResponseVO responseVO=null; for (Adminboothevent
				 * adminboothevent : eventList) { Criteria criteria
				 * =sessionFactory.getCurrentSession().createCriteria(AdminEventPictureMapping.
				 * class); criteria.add(Restrictions.eq("status", true));
				 * criteria.add(Restrictions.eq("isDeleted", false));
				 * criteria.add(Restrictions.eq("EId", adminboothevent.getEId()));
				 * List<AdminEventPictureMapping> adminEventPictureMapping =criteria.list();
				 * responseVO=new AdminBoothEventResponseVO(); for (AdminEventPictureMapping
				 * adminEventPictureMapping2 : adminEventPictureMapping) { Criteria
				 * criteriaAdminPicture
				 * =sessionFactory.getCurrentSession().createCriteria(AdminPicture.class);
				 * criteriaAdminPicture.add(Restrictions.eq("status", true));
				 * criteriaAdminPicture.add(Restrictions.eq("isDeleted", false));
				 * List<AdminPicture>
				 * adminPictureList=criteriaAdminPicture.add(Restrictions.eq("picId",
				 * adminEventPictureMapping2.getPicId())).list(); //Start 11-11-2016
				 * List<AdminPictureResponseVO> pictureResponseList=new ArrayList<>();
				 * AdminPictureResponseVO picture=null; for (AdminPicture p : adminPictureList)
				 * { picture=new AdminPictureResponseVO();
				 * picture.setCreatedBy(p.getCreatedBy());
				 * picture.setCreatedDate(DateUtils.timeStampConvertIntoStringDateFormat(p.
				 * getCreatedDate())); picture.setImageMask(p.getImageMask());
				 * picture.setPicId(p.getPicId()); picture.setPicName(p.getPicName());
				 * picture.setPicTitle(p.getPicTitle()); picture.setRgbValues(p.getRgbValues());
				 * picture.setScaleXOffset(p.getScaleXOffset());
				 * picture.setScaleYOffset(p.getScaleYOffset());
				 * picture.setScaleZOffset(p.getScaleZOffset());
				 * picture.setUpdatedBy(p.getUpdatedBy());
				 * picture.setWaterMarkImage(p.getWaterMarkImage());
				 * if(p.getUpdatedDate()!=null){
				 * picture.setUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormat(p.
				 * getUpdatedDate())); } if(p.getImageHeight()!=null){
				 * picture.setImageHeight(p.getImageHeight()); } if(p.getImageWidth()!=null){
				 * picture.setImageWidth(p.getImageWidth()); } if(p.getScalingHeight()!=null){
				 * picture.setScalingHeight(p.getScalingHeight()); }
				 * if(p.getScalingWidth()!=null){ picture.setScalingWidth(p.getScalingWidth());
				 * } if(p.getStatus()!=null){ picture.setStatus(p.getStatus()); }
				 * if(p.getIsDeleted()!=null){ picture.setIsDeleted(p.getIsDeleted()); }
				 * pictureResponseList.add(picture); }
				 * responseVO.setAdminBoothEventPicture(pictureResponseList); // end 11-11-2016
				 * responseVO.setCreatedBy(adminboothevent.getCreatedBy());
				 * if(adminboothevent.getStatus()!=null){
				 * responseVO.setStatus(adminboothevent.getStatus()); }
				 * 
				 * if(adminboothevent.getIsDeleted()!=null){
				 * responseVO.setIsDeleted(adminboothevent.getIsDeleted()); }
				 * responseVO.setCreatedDate(DateUtils.timeStampConvertIntoStringDateFormat(
				 * adminboothevent.getCreatedDate()));
				 * responseVO.setEId(adminboothevent.getEId());
				 * responseVO.setEventEnd(adminboothevent.getEventEnd());
				 * responseVO.setEventHostMailerId(adminboothevent.getEventHostMailerId());
				 * responseVO.setEventLocation(adminboothevent.getEventLocation());
				 * responseVO.setEventName(adminboothevent.getEventName());
				 * responseVO.setEventStart(adminboothevent.getEventStart());
				 * responseVO.setUpdatedBy(adminboothevent.getUpdatedBy());
				 * if(adminboothevent.getUpdatedDate()!=null){
				 * responseVO.setUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormat(
				 * adminboothevent.getUpdatedDate())); }
				 * responseVO.setSponsorName(adminboothevent.getSponsorName());
				 * responseVOList.add(responseVO); } }
				 * adminEventPictureMappingObject.setModifiedResult(responseVOList); }
				 */
				responseVo.setResponseCode("1");
				responseVo.setResponseDescription("Success");
				responseVo.setAdminEventPictureMappingResponse(adminEventPictureMappingObject);
			} else {
				responseVo.setResponseCode("0");
				responseVo.setResponseDescription("failer,No record's found because sub id is not valid enterend");
				responseVo.setAdminEventPictureMappingResponse(adminEventPictureMappingObject);
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return responseVo;
	}

	@Override
	public String deviceRegisterSevice(DeviceRegistrationRequestVO deviceRegistrationRequestVO) {
		String result = "";
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DeviceRegistration.class);
			criteria.add(Restrictions.eq("userId", deviceRegistrationRequestVO.getUserId()));
			criteria.add(Restrictions.eq("deviceType", deviceRegistrationRequestVO.getDeviceType()));
			DeviceRegistration deviceRegistrations = (DeviceRegistration) criteria.uniqueResult();
			if (deviceRegistrations != null) {
				deviceRegistrations.setDeviceToken(deviceRegistrationRequestVO.getDeviceToken());
				deviceRegistrations.setDeteactedResolution(deviceRegistrationRequestVO.getDeteactedResolution());
				deviceRegistrations.setDeviceName(deviceRegistrationRequestVO.getDeviceName());
				deviceRegistrations.setDeviceStorage(deviceRegistrationRequestVO.getDeviceStorage());
				deviceRegistrations.setDeviceType(deviceRegistrationRequestVO.getDeviceType());
				deviceRegistrations.setGuidedAccessEnabled(deviceRegistrationRequestVO.getGuidedAccessEnabled());
				deviceRegistrations.setIpAddress(deviceRegistrationRequestVO.getIpAddress());
				deviceRegistrations.setLastSyncTime(new Date());
				deviceRegistrations
						.setOperationgSystemVersion(deviceRegistrationRequestVO.getOperationgSystemVersion());
				deviceRegistrations.setWirelessNetwork(deviceRegistrationRequestVO.getWirelessNetwork());
				deviceRegistrations.setSubNetMask(deviceRegistrationRequestVO.getSubNetMask());
				deviceRegistrations.setDeviceUUID(deviceRegistrationRequestVO.getDeviceUUID());
				deviceRegistrations.setDeviceTimestamp(deviceRegistrationRequestVO.getDeviceTimestamp());
				sessionFactory.getCurrentSession().update(deviceRegistrations);
				result = "update";
			} else {
				DeviceRegistration deviceRegistration1 = new DeviceRegistration();
				deviceRegistration1.setDeteactedResolution(deviceRegistrationRequestVO.getDeteactedResolution());
				deviceRegistration1.setDeviceName(deviceRegistrationRequestVO.getDeviceName());
				deviceRegistration1.setDeviceStorage(deviceRegistrationRequestVO.getDeviceStorage());
				deviceRegistration1.setDeviceToken(deviceRegistrationRequestVO.getDeviceToken());
				deviceRegistration1.setDeviceType(deviceRegistrationRequestVO.getDeviceType());
				deviceRegistration1.setGuidedAccessEnabled(deviceRegistrationRequestVO.getGuidedAccessEnabled());
				deviceRegistration1.setIpAddress(deviceRegistrationRequestVO.getIpAddress());
				deviceRegistration1.setLastSyncTime(new Date());
				deviceRegistration1
						.setOperationgSystemVersion(deviceRegistrationRequestVO.getOperationgSystemVersion());
				deviceRegistration1.setUserId(deviceRegistrationRequestVO.getUserId());
				deviceRegistration1.setWirelessNetwork(deviceRegistrationRequestVO.getWirelessNetwork());
				deviceRegistration1.setCreatedDate(new Date());
				deviceRegistration1.setStatus(true);
				deviceRegistration1.setIsDeleted(false);
				deviceRegistration1.setDeviceTimestamp(deviceRegistrationRequestVO.getDeviceTimestamp());
				deviceRegistration1.setSubNetMask(deviceRegistrationRequestVO.getSubNetMask());
				deviceRegistration1.setDeviceUUID(deviceRegistrationRequestVO.getDeviceUUID());
				sessionFactory.getCurrentSession().save(deviceRegistration1);
				result = "success";
			}
			DeviceRegistrationAll deviceRegistrationsAlls = new DeviceRegistrationAll();
			deviceRegistrationsAlls.setDeviceToken(deviceRegistrationRequestVO.getDeviceToken());
			deviceRegistrationsAlls.setDeteactedResolution(deviceRegistrationRequestVO.getDeteactedResolution());
			deviceRegistrationsAlls.setDeviceName(deviceRegistrationRequestVO.getDeviceName());
			deviceRegistrationsAlls.setDeviceStorage(deviceRegistrationRequestVO.getDeviceStorage());
			deviceRegistrationsAlls.setDeviceType(deviceRegistrationRequestVO.getDeviceType());
			deviceRegistrationsAlls.setGuidedAccessEnabled(deviceRegistrationRequestVO.getGuidedAccessEnabled());
			deviceRegistrationsAlls.setIpAddress(deviceRegistrationRequestVO.getIpAddress());
			deviceRegistrationsAlls
					.setOperationgSystemVersion(deviceRegistrationRequestVO.getOperationgSystemVersion());
			deviceRegistrationsAlls.setUserId(deviceRegistrationRequestVO.getUserId());
			deviceRegistrationsAlls.setWirelessNetwork(deviceRegistrationRequestVO.getWirelessNetwork());
			deviceRegistrationsAlls.setSubNetMask(deviceRegistrationRequestVO.getSubNetMask());
			deviceRegistrationsAlls.setDeviceUUID(deviceRegistrationRequestVO.getDeviceUUID());
			deviceRegistrationsAlls.setCreatedDate(new Date());
			deviceRegistrationsAlls.setStatus(true);
			deviceRegistrationsAlls.setIsDeleted(false);
			sessionFactory.getCurrentSession().save(deviceRegistrationsAlls);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return result;
	}

	@Override
	public SubscriptionMaster fetchSubscriptionsMasterList(SubscriptionRequestVO subscription) {
		SubscriptionMaster subscriptionMaster = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();

			Criteria criteriaAdminBoothLogin = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteriaAdminBoothLogin.add(Restrictions.eq("userId", subscription.getUserId()));
			criteriaAdminBoothLogin.add(Restrictions.eq("status", true));
			criteriaAdminBoothLogin.add(Restrictions.eq("isDeleted", false));
			BoothAdminLogin adminBoothLoginEntity = (BoothAdminLogin) criteriaAdminBoothLogin.uniqueResult();

			Criteria crt = sessionFactory.getCurrentSession().createCriteria(SubscriptionMaster.class);
			crt.add(Restrictions.eq("status", true));
			crt.add(Restrictions.eq("isDeleted", false));
			crt.add(Restrictions.eq("subId",adminBoothLoginEntity.getSubId()));
			//Log.info("LOgin"+adminBoothLoginEntity.getSubId());
			subscriptionMaster = (SubscriptionMaster) crt.uniqueResult();
			Criteria criteriaTransactionMaster = sessionFactory.getCurrentSession().createCriteria(TransactionMaster.class);
			criteriaTransactionMaster.add(Restrictions.eq("userId", adminBoothLoginEntity.getUserId()));
			criteriaTransactionMaster.addOrder(Order.desc("paymentDate"));
			
			/*
			 * if(amount.equals("150.00") && adminBoothLoginEntity.getSubId()==3) {
			 * subscriptionMaster.setSubId(adminBoothLoginEntity.getSubId()); } else
			 * if(amount.equals("1200.00") && adminBoothLoginEntity.getSubId()==4) {
			 * subscriptionMaster.setSubId(adminBoothLoginEntity.getSubId()); } else {
			 * subscriptionMaster.setSubId(adminBoothLoginEntity.getSubId());
			 * 
			 * }
			 */
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subscriptionMaster;
	}

	@Override
	public BaseResponseVO saveTranscationIOSDetails(IOSTranscationsDetailsRequestVO iosTrxDetailsBasedUserId,
			TransactionMaster trxMaster) {
		BaseResponseVO result = new BaseResponseVO();
		try {
			sessionFactory.getCurrentSession().beginTransaction();

			sessionFactory.getCurrentSession().save(trxMaster);

			Integer trx_id = (Integer) sessionFactory.getCurrentSession().getIdentifier(trxMaster);

			TransactionMappingAdmin mappingAdmin = new TransactionMappingAdmin();
			mappingAdmin.setTransactionMasterId(trx_id);
			mappingAdmin.setUserId(iosTrxDetailsBasedUserId.getUserId());

			Date today = Calendar.getInstance().getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String stringCurrentDate = formatter.format(today);
			Date currentDate = formatter.parse(stringCurrentDate);
			mappingAdmin.setDate(currentDate);
			sessionFactory.getCurrentSession().save(mappingAdmin);

			BoothAdminLogin adminLogin = (BoothAdminLogin) sessionFactory.getCurrentSession().get(BoothAdminLogin.class,
					iosTrxDetailsBasedUserId.getUserId());
			if (adminLogin != null) {
				Criteria crt = sessionFactory.getCurrentSession().createCriteria(SubscriptionMaster.class)
						.add(Restrictions.eq("subPrice", iosTrxDetailsBasedUserId.getAmount()));
				crt.add(Restrictions.eq("status", true));
				crt.add(Restrictions.eq("isDeleted", false));
				SubscriptionMaster master = (SubscriptionMaster) crt.uniqueResult();
				if (master != null) {
					adminLogin.setSubId(master.getSubId());
					adminLogin.setSubUpdatedDate(new Date());
				}
				sessionFactory.getCurrentSession().update(adminLogin);
			}
			result.setResponseDescription("success");
			result.setResponseCode("1");
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.getStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
			result.setResponseDescription("something went wrong");
			result.setResponseCode("0");
			return result;
		}
		return result;
	}

	@Override
	public Adminboothevent getAdminBoothEvent(int userId, Integer eventId) {
		Adminboothevent adminboothevent = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria cUser = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			cUser.add(Restrictions.eq("EId", eventId));
			Adminboothevent adminboothevents = (Adminboothevent) cUser.uniqueResult();

			if (adminboothevents != null) {
				Criteria crt = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
				if (!("default").equals(adminboothevents.getEventType())) {
					crt.add(Restrictions.eq("createdBy", userId));
				}
				crt.add(Restrictions.eq("EId", eventId));
				adminboothevent = (Adminboothevent) crt.uniqueResult();
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return adminboothevent;
	}

	@Override
	public RestartVO restertServer(BaseRequestVO restartVO, PushNotificationTaskRestart taskRestartUpdate) {
		RestartVO restartVOs = new RestartVO();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DeviceRegistration.class);
			criteria.add(Restrictions.eq("userId", Integer.parseInt(restartVO.getUserId())));
			criteria.add(Restrictions.eq("status", true));
			criteria.add(Restrictions.eq("isDeleted", false));
			criteria.add(Restrictions.eq("deviceType", "Camera device"));
			DeviceRegistration deviceRegistration = (DeviceRegistration) criteria.uniqueResult();
			if (deviceRegistration != null) {
				Criteria criteriaTouch = sessionFactory.getCurrentSession().createCriteria(DeviceRegistration.class);
				criteriaTouch.add(Restrictions.eq("userId", Integer.parseInt(restartVO.getUserId())));
				criteriaTouch.add(Restrictions.eq("status", true));
				criteriaTouch.add(Restrictions.eq("isDeleted", false));
				criteriaTouch.add(Restrictions.eq("deviceType", "Guest Touchscreen"));
				DeviceRegistration deviceRegistrationTouch = (DeviceRegistration) criteriaTouch.uniqueResult();
				if (deviceRegistrationTouch != null) {
					restartVOs.setCameraIP(deviceRegistration.getIpAddress());
					restartVOs.setTouchIP(deviceRegistrationTouch.getIpAddress());
				}
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return restartVOs;
	}

	@Override
	public Fovbyuser getFobByUser(BaseRequestVO baseRequestVO) {
		Fovbyuser fovbyuser = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Fovbyuser.class);
			criteria.add(Restrictions.eq("userId", Integer.parseInt(baseRequestVO.getUserId())));
			fovbyuser = (Fovbyuser) criteria.uniqueResult();
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return fovbyuser;
	}

	@Override
	public List<DeviceRegistration> getRegisteredDevice(Integer userId) {
		List<DeviceRegistration> deviceRegistration = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DeviceRegistration.class);
			criteria.add(Restrictions.eq("userId", userId));
			criteria.add(Restrictions.eq("status", true));
			criteria.add(Restrictions.eq("isDeleted", false));
			deviceRegistration = criteria.list();
			sessionFactory.getCurrentSession().getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return deviceRegistration;
	}
	/*
	 * @Override public List<AllUserList> getUserList(int userid) {
	 * List<AllUserList> userid1=null; try{
	 * sessionFactory.getCurrentSession().beginTransaction(); Criteria criteria =
	 * sessionFactory.getCurrentSession().createCriteria(AllUserList.class);
	 * criteria.add(Restrictions.eq("userid", userid)); userid1=criteria.list();
	 * sessionFactory.getCurrentSession().getTransaction().commit(); } catch
	 * (Exception e) { e.printStackTrace();
	 * sessionFactory.getCurrentSession().getTransaction().rollback(); } return
	 * userid1; }
	 */
	
	@Override
	public String logOutService(DeviceRegistrationRequestVO deviceRegistrationRequestVO) {
		String result = "";
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DeviceRegistration.class);
			criteria.add(Restrictions.eq("userId", deviceRegistrationRequestVO.getUserId()));
			criteria.add(Restrictions.eq("deviceType", deviceRegistrationRequestVO.getDeviceType()));
			criteria.add(Restrictions.eq("status", true));
			criteria.add(Restrictions.eq("isDeleted", false));
			DeviceRegistration deviceRegistration = (DeviceRegistration) criteria.uniqueResult();
			if (deviceRegistration != null) {
				deviceRegistration.setDeviceToken("");
				sessionFactory.getCurrentSession().update(deviceRegistration);
			}
			result = "success";
			sessionFactory.getCurrentSession().getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return result;
	}

	@Override
	public void updateStatusCount(String emailId, Integer eventId,Integer userId) {
		try {
									
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(StatusCount.class);
			criteria.add(Restrictions.eq("emailId", emailId));
			criteria.add(Restrictions.eq("eventId", eventId));
			criteria.add(Restrictions.eq("userId", userId));
			//Integer distCounbt = (Integer) criteria.uniqueResult();
			StatusCount statusCount = (StatusCount) criteria.uniqueResult();
			if (statusCount != null) {
				//Integer repetedGuest = statusCount.getRepetedGuestCount();
				Integer mailSent = statusCount.getMailSentCount();
				statusCount.setMailSentCount(mailSent + 1);
				statusCount.setRepetedGuestCount(1);
				sessionFactory.getCurrentSession().update(statusCount);
			} else {
				StatusCount statusCount2 = new StatusCount();
				statusCount2.setEmailId(emailId);
				statusCount2.setEventId(eventId);
				statusCount2.setRepetedGuestCount(0);
				statusCount2.setMailSentCount(1);
				statusCount2.setUserId(userId);
				sessionFactory.getCurrentSession().save(statusCount2);
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}

	}
	@Override
	public BoothAdminLogin getBoothAdminLoginById(Integer userId) {
		BoothAdminLogin boothAdminLogin = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteria.add(Restrictions.eq("userId",userId));
		    boothAdminLogin = (BoothAdminLogin) criteria.uniqueResult();
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return boothAdminLogin;
}
	
	@Override
	public TransactionMaster subscriptionUpdateInAppPurchase(TransactionMaster transactionMaster) {
		TransactionMaster transactionDetails = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Integer transactionId = (Integer) sessionFactory.getCurrentSession().save(transactionMaster);			
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TransactionMaster.class);
			criteria.add(Restrictions.eq("transactionMasterId",transactionId));
			transactionDetails =  (TransactionMaster) criteria.uniqueResult();
			sessionFactory.getCurrentSession().getTransaction().commit();
            return transactionDetails;
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return null;
	}
	
	
}
