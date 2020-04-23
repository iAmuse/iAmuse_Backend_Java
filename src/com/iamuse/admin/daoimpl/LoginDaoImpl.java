package com.iamuse.admin.daoimpl;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iamuse.admin.VO.AdminEventPictureMappingResponse;
import com.iamuse.admin.VO.BoothAdminLoginResponseVO;
import com.iamuse.admin.VO.BoothAdminRegistrationRequestVO;
import com.iamuse.admin.VO.DeviceRegistrationResponseVO;
import com.iamuse.admin.VO.LoginBaseResponseVO;
import com.iamuse.admin.VO.LoginBoothAdminRegistrationRequestVO;
import com.iamuse.admin.VO.LoginVO;
import com.iamuse.admin.VO.SignInVO;
import com.iamuse.admin.VO.SubscriptionMasterResponseVO;
import com.iamuse.admin.controller.FBConnection;
import com.iamuse.admin.dao.LoginDao;
import com.iamuse.admin.entity.BoothAdminLogin;
import com.iamuse.admin.entity.DeviceRegistration;
import com.iamuse.admin.entity.Fovbyuser;
import com.iamuse.admin.entity.SubscriptionMaster;
import com.iamuse.admin.entity.UploadImage;
import com.iamuse.admin.service.FBGraph;
import com.iamuse.admin.util.Crypto;
import com.paypal.constants.ServerConstants;


@Repository
public class LoginDaoImpl implements LoginDao{
	@Autowired SessionFactory sessionFactory;
	
	private static final Logger log = Logger.getLogger(LoginDao.class);
	/*****************************************************|| Start Previous IAmuse Code ||************************************/	
	
	@Override
	public BoothAdminLogin isValidUser(SignInVO signInVO)
	{
		BoothAdminLogin boothAdminLogin=null;
		try{
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteria.add(Restrictions.eq("emailId",signInVO.getEmailId()));
			//criteria.add(Restrictions.eq("password",signInVO.getPassword()));
			Criterion enc = Restrictions.eq("password",Crypto.encrypt(signInVO.getPassword()));
			Criterion nor = Restrictions.eq("password",signInVO.getPassword());
			LogicalExpression orExp = Restrictions.or(enc,nor);
			criteria.add(orExp);
			boothAdminLogin=(BoothAdminLogin)criteria.uniqueResult();
			sessionFactory.getCurrentSession().getTransaction().commit();
		}
		catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			log.info("LoginDaoImpl Method : isValidUser");
			log.error("Error isValidUser",e);
		}
		return boothAdminLogin;
	}
	
	@Override
	public BoothAdminLogin gMailIsValidUser(SignInVO signInVO)
	{
		BoothAdminLogin boothAdminLogin=null;
		try{
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteria.add(Restrictions.eq("emailId",signInVO.getEmailId()));
			//criteria.add(Restrictions.eq("isDeleted",false));
			boothAdminLogin=(BoothAdminLogin)criteria.uniqueResult();
			/*if(boothAdminLogin.getIsDeleted() == true)
			{
				boothAdminLogin.setIsDeleted(false);
				sessionFactory.getCurrentSession().update(boothAdminLogin);
			}*/
			log.info("BoothAdminLogin***="+boothAdminLogin);
			sessionFactory.getCurrentSession().getTransaction().commit();
		}
		catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			log.info("LoginDaoImpl Method : isValidUser");
			log.error("Error isValidUser",e);
		}
		return boothAdminLogin;
	}
	
	/*****************************************************|| End Previous IAmuse Code ||************************************/

	
	@Override
	public BoothAdminLogin isValidUserSocial(SignInVO signInVO)
	{
		BoothAdminLogin boothAdminLogin=null;
		try{
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			//criteria.add(Restrictions.eq("emailId",signInVO.getEmailId()));
			Criterion enc = Restrictions.eq("facebookUrl",signInVO.getFacebookUrl());
			Criterion nor = Restrictions.eq("twitterUrl",signInVO.getTwitterUrl());
			LogicalExpression orExp = Restrictions.or(enc,nor);
			criteria.add(orExp);
			boothAdminLogin=(BoothAdminLogin)criteria.uniqueResult();
			sessionFactory.getCurrentSession().getTransaction().commit();
		}
		catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			log.info("LoginDaoImpl Method : isValidUser");
			log.error("Error isValidUser",e);
		}
		return boothAdminLogin;
	}
	
	@Override
	public void updateTour(Integer userId) {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteria.add(Restrictions.eq("userId",userId));
			BoothAdminLogin boothAdminLogin=(BoothAdminLogin)criteria.uniqueResult();
			if(boothAdminLogin !=null){
				boothAdminLogin.setLoginTour(1);
				sessionFactory.getCurrentSession().update(boothAdminLogin);
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("LoginDaoImpl Method : updateTour");
			log.error("Error updateTour",e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
	}
	@Override
	public void setLastUpdateImages(Integer userId) {
		try {
			   sessionFactory.getCurrentSession().beginTransaction();
			   Criteria criteria= sessionFactory.getCurrentSession().createCriteria(UploadImage.class);
			   criteria.add(Restrictions.eq("userId", userId));
			   criteria.add(Restrictions.eq("isValidate", false));
			   criteria.addOrder(Order.desc("imageId"));
			   criteria.setFirstResult(0);
			   criteria.setMaxResults(1);
			   UploadImage eventList = (UploadImage)criteria.uniqueResult(); 
			   if(eventList !=null){
				   eventList.setIsValidate(true);
				   sessionFactory.getCurrentSession().update(eventList);
			   }
			   sessionFactory.getCurrentSession().getTransaction().commit();
		  } catch (Exception e) {
			  log.info("LoginDaoImpl Method : setLastUpdateImages");
				log.error("Error setLastUpdateImages",e);
		   sessionFactory.getCurrentSession().getTransaction().rollback();
	}
	}
	@Override
	public String forgotPassword(String username,String token) {
		String result="";
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			   Criteria criteria= sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			   criteria.add(Restrictions.eq("emailId", username));
			   criteria.add(Restrictions.eq("status", true));
			   BoothAdminLogin boothAdminLogin = (BoothAdminLogin)criteria.uniqueResult(); 
			   if(boothAdminLogin !=null){
				   boothAdminLogin.setToken(token);
				   sessionFactory.getCurrentSession().update(boothAdminLogin);
				  // result.setUsername(boothAdminLogin.getEmailId());
				   result="success";
			   }else{
				   result="invalidEmail";
			   }
			   sessionFactory.getCurrentSession().getTransaction().commit();
		  } catch (Exception e) {
			  log.info("LoginDaoImpl Method : forgotPassword");
				log.error("Error forgotPassword",e);
		   sessionFactory.getCurrentSession().getTransaction().rollback();
	}
		return result;
	}
	@Override
	public String resetPassword(SignInVO signInVO) {
		String result="";
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria= sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteria.add(Restrictions.eq("token", signInVO.getToken()));
			criteria.add(Restrictions.eq("status", true));
			BoothAdminLogin boothAdminLogin = (BoothAdminLogin)criteria.uniqueResult();
			if(boothAdminLogin !=null){
				boothAdminLogin.setPassword(Crypto.encrypt(signInVO.getPassword()));
				boothAdminLogin.setToken(UUID.randomUUID().toString());
				sessionFactory.getCurrentSession().update(boothAdminLogin);
				result="success";
			}else if(boothAdminLogin ==null){
				result="fail";
			}
			  sessionFactory.getCurrentSession().getTransaction().commit();
		  } catch (Exception e) {
			  log.info("LoginDaoImpl Method : resetPassword");
				log.error("Error resetPassword",e);
		   sessionFactory.getCurrentSession().getTransaction().rollback();
	}
		return result;
	}
	
	@Override
	public String changePassword(SignInVO signInVO) {
		
		String result="";
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria= sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteria.add(Restrictions.eq("userId", signInVO.getUserId()));
			criteria.add(Restrictions.eq("password", Crypto.encrypt(signInVO.getPassword())));
			criteria.add(Restrictions.eq("status", true));
			BoothAdminLogin boothAdminLogin = (BoothAdminLogin)criteria.uniqueResult();
			if(boothAdminLogin !=null){
				boothAdminLogin.setPassword(Crypto.encrypt(signInVO.getNewPassword()));
				//boothAdminLogin.setToken(UUID.randomUUID().toString());
				sessionFactory.getCurrentSession().update(boothAdminLogin);
				result="success";
			}else if(boothAdminLogin ==null){
				result="fail";
			}
			  sessionFactory.getCurrentSession().getTransaction().commit();
		  } catch (Exception e) {
			  log.info("LoginDaoImpl Method : changePassword");
				log.error("Error changePassword",e);
		   sessionFactory.getCurrentSession().getTransaction().rollback();
	}
		return result;
	}
	@Override
	public BoothAdminLogin setFbDetails(String code) {
		BoothAdminLogin boothAdminLogin=null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			FBConnection fbConnection = new FBConnection();
			String accessToken = fbConnection.getAccessToken(code);
			FBGraph fbGraph = new FBGraph(accessToken);
			String graph = fbGraph.getFBGraph();

			Map<String, String> fbProfileData = fbGraph.getGraphData(graph);
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			Criterion c1 = Restrictions.eq("facebookUrl", fbProfileData.get("id"));
			Criterion c2 = Restrictions.eq("emailId", fbProfileData.get("email"));
			LogicalExpression orExp = Restrictions.or(c1, c2);
			criteria.add(orExp);
			boothAdminLogin=(BoothAdminLogin) criteria.uniqueResult();
			if(boothAdminLogin !=null){
				boothAdminLogin.setImageFileName(fbProfileData.get("picture"));
				boothAdminLogin.setFacebookUrl(fbProfileData.get("id"));
				sessionFactory.getCurrentSession().update(boothAdminLogin);
			}else{
				BoothAdminLogin userMaster2=new BoothAdminLogin();
				
				userMaster2.setCreatedDate(new java.sql.Timestamp(new Date().getTime()));
				userMaster2.setStatus(ServerConstants.MAKE_TRUE);
				userMaster2.setSubId(ServerConstants.SUBSCRIPTION_NORMAL);
				userMaster2.setIsDeleted(ServerConstants.MAKE_FALSE);
				userMaster2.setSubUpdatedDate(new java.sql.Timestamp(new Date().getTime()));
				userMaster2.setHexValueDefault("#341561");
				userMaster2.setRgbValueDefault("0,255,0");
				userMaster2.setRgbaValueDefault("0,255,0,255");
				userMaster2.setHexValueManual("#4EDB84");
				userMaster2.setRgbValueManual("0,255,0");
				userMaster2.setRgbaValueManual("255,255,255,255");
				userMaster2.setIsDefaultRgb(ServerConstants.MAKE_TRUE);
				userMaster2.setCurrentImageId(0);
				userMaster2.setUserType("Professional");
				userMaster2.setLoginTour(1);
				userMaster2.setSubId(1);
				userMaster2.setUsername(fbProfileData.get("name"));
				userMaster2.setFacebookUrl(fbProfileData.get("id"));
				userMaster2.setEmailId(fbProfileData.get("email"));
				userMaster2.setImageFileName(fbProfileData.get("picture"));
				userMaster2.setUserRole("boothadmin");
				Integer userId=(Integer) sessionFactory.getCurrentSession().save(userMaster2);
			if(userId !=0){
				Fovbyuser fovbyuser=new Fovbyuser();
				fovbyuser.setUserId(userId);
				fovbyuser.setZoomScale("1.00");
				fovbyuser.setFovTop("0");
				fovbyuser.setFovLeft("0");
				fovbyuser.setFovRight("0");
				fovbyuser.setFovBottom("0");
				sessionFactory.getCurrentSession().save(fovbyuser);
			}
			Criteria criteria1=sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteria1.add(Restrictions.eq("userId",userId));
			boothAdminLogin=(BoothAdminLogin) criteria1.uniqueResult();
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return boothAdminLogin;
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
				login.setCreatedDate(com.iamuse.admin.util.DateUtils.timeStampConvertIntoStringDateFormat(adminBoothLoginEntity.getCreatedDate()));
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
							com.iamuse.admin.util.DateUtils.timeStampConvertIntoStringDateFormat(adminBoothLoginEntity.getSubUpdatedDate()));
				}
				if (adminBoothLoginEntity.getUpdatedDate() != null) {
					login.setUpdatedDate(
							com.iamuse.admin.util.DateUtils.timeStampConvertIntoStringDateFormat(adminBoothLoginEntity.getUpdatedDate()));
				}
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
					vo.setCreatedDate(com.iamuse.admin.util.DateUtils.timeStampConvertIntoStringDateFormat(s.getCreatedDate()));
					
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
						vo.setUpdatedDate(com.iamuse.admin.util.DateUtils.timeStampConvertIntoStringDateFormat(s.getUpdatedDate()));
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
						deviceVO.setCreatedDate(com.iamuse.admin.util.DateUtils.timeStampConvertIntoStringDateFormat(d.getCreatedDate()));
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
						deviceVO.setLastSyncTime(com.iamuse.admin.util.DateUtils.timeStampConvertIntoStringDateFormat(d.getLastSyncTime()));
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
				//boothAdminRegistrationEntity.setCreatedDate(new Timestamp(new Date().getTime()));
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
			login.setCreatedDate(com.iamuse.admin.util.DateUtils.timeStampConvertIntoStringDateFormat(boothAdminRegistrationEntity.getCreatedDate()));
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
			login.setSubUpdatedDate(com.iamuse.admin.util.DateUtils.timeStampConvertIntoStringDateFormat(boothAdminRegistrationEntity.getSubUpdatedDate()));
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
						vo.setCreatedDate(com.iamuse.admin.util.DateUtils.timeStampConvertIntoStringDateFormat(s.getCreatedDate()));
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
							vo.setUpdatedDate(com.iamuse.admin.util.DateUtils.timeStampConvertIntoStringDateFormat(s.getUpdatedDate()));
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
							deviceVO.setCreatedDate(com.iamuse.admin.util.DateUtils.timeStampConvertIntoStringDateFormat(d.getCreatedDate()));
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
							deviceVO.setLastSyncTime(com.iamuse.admin.util.DateUtils.timeStampConvertIntoStringDateFormat(d.getLastSyncTime()));
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
}