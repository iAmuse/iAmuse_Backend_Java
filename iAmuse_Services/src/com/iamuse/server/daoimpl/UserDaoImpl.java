package com.iamuse.server.daoimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amuse.server.dao.UserDao;
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
import com.iamuse.server.util.ServiceRequestTemplateZoho;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired private SessionFactory sessionFactory;
	@Autowired private ServiceRequestTemplateZoho serviceRequestTemplateZoho;
	
	@Override 
	public Integer uploadImage(String image) {
		Integer imageId=0;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			UploadImage uploadImage=new UploadImage();
			uploadImage.setImageUrl(image);
			uploadImage.setStatus(true);
			uploadImage.setIsDeleted(false);
			uploadImage.setIsValidate(false);
			imageId=(Integer) sessionFactory.getCurrentSession().save(uploadImage);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.getMessage();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return imageId;
	}
	
	@Override
	public boolean updateImageName(Integer imageName,String url,String userId) {
		boolean result=false;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			UploadImage uploadImage = (UploadImage) sessionFactory.getCurrentSession().load(UploadImage.class,imageName);
			String imageNamewithextension=imageName+".jpg";
			uploadImage.setImageName(imageNamewithextension);
			uploadImage.setImageUrl(url);
			uploadImage.setUserId(Integer.parseInt(userId));
			uploadImage.setUploadTime(IAmuseUtil.getTimeStamp());
			sessionFactory.getCurrentSession().saveOrUpdate(uploadImage);
			sessionFactory.getCurrentSession().getTransaction().commit();
		 result=true;
		} catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return result;
	}
	
	@Override
	public BoothAdminLogin getDefaultRGBValue(RGBValueRequestVO rbRgbValueRequestVO){
		BoothAdminLogin boothAdminLogin = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteria.add(Restrictions.eq("userId",Integer.parseInt(rbRgbValueRequestVO.getUserId())));
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
		boolean result=false;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Usermaster.class);
			criteria.add(Restrictions.eq("userId",Integer.parseInt(deviceTokenRequestVO.getUserId())));
			Usermaster usermaster = (Usermaster) criteria.uniqueResult();
			if(deviceTokenRequestVO.getDeviceToken()!=null){
			usermaster.setDeviceToken(deviceTokenRequestVO.getDeviceToken());
			sessionFactory.getCurrentSession().update(usermaster);
			result=true;
			}else{
				result=false;
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return result;
	}
	
	@Override
	public UploadImage getImageDetails(int imageId) {
		UploadImage imageDetails = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UploadImage.class);
			criteria.add(Restrictions.eq("imageId", imageId));
			criteria.add(Restrictions.eq("status", true));
			imageDetails=(UploadImage) criteria.uniqueResult();
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return imageDetails;
	}
	
	@Override
	public Integer uploadImageWithEmailId(String image,UploadImageWithEmailRequestVO uploadImageWithEmailRequestVO) {
		Integer imageId=0;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			BoothUploadImageEmail uploadImageEmail=new BoothUploadImageEmail();
			uploadImageEmail.setMailImageUrl(image);
			uploadImageEmail.setEmailId(uploadImageWithEmailRequestVO.getEmailId());
			uploadImageEmail.setPhotoSessionId(uploadImageWithEmailRequestVO.getPhotoSessionId());
			uploadImageEmail.setPublicUseAck(Integer.toString(uploadImageWithEmailRequestVO.getPublicUseAck()));
			uploadImageEmail.setNewsletterOptIn(Integer.toString(uploadImageWithEmailRequestVO .getNewsletterOptIn()));
			uploadImageEmail.setFileName(uploadImageWithEmailRequestVO.getFileName());
			uploadImageEmail.setRenderVersion(uploadImageWithEmailRequestVO.getRenderVersion());
			uploadImageEmail.setShare(Integer.toString(uploadImageWithEmailRequestVO.getShare()));
			uploadImageEmail.setIsDeleted(false);
			uploadImageEmail.setStatus(true);
			uploadImageEmail.setDownloadStatus(0);
			imageId=(Integer) sessionFactory.getCurrentSession().save(uploadImageEmail);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return imageId;
	}
	
	@Override
	public Integer updateImageNameForEmailId(String imageName,String userId,Integer eventId,String image,
			UploadImageWithEmailRequestVO uploadImageWithEmailRequestVO,Integer defaultId, Integer picId) {
		Integer imageId=0;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			BoothUploadImageEmail uploadImageEmail=new BoothUploadImageEmail();
			uploadImageEmail.setMailImageName(imageName);
			uploadImageEmail.setUserId(Integer.parseInt(userId));
			uploadImageEmail.setUploadTime(IAmuseUtil.getTimeStamp());
			uploadImageEmail.setEventId(eventId);
			uploadImageEmail.setMailImageUrl(image);
			uploadImageEmail.setEmailId(uploadImageWithEmailRequestVO.getEmailId());
			uploadImageEmail.setPhotoSessionId(uploadImageWithEmailRequestVO.getPhotoSessionId());
			uploadImageEmail.setPublicUseAck(""+uploadImageWithEmailRequestVO.getPublicUseAck());
			uploadImageEmail.setNewsletterOptIn(""+uploadImageWithEmailRequestVO .getNewsletterOptIn());
			uploadImageEmail.setFileName(uploadImageWithEmailRequestVO.getFileName());
			uploadImageEmail.setRenderVersion(uploadImageWithEmailRequestVO.getRenderVersion());
			uploadImageEmail.setShare(""+uploadImageWithEmailRequestVO.getShare());
			uploadImageEmail.setIsDeleted(false);
			uploadImageEmail.setStatus(true);
			uploadImageEmail.setDownloadStatus(0);
			uploadImageEmail.setGuestMobileNo(uploadImageWithEmailRequestVO.getGuestMobileNumber());
			uploadImageEmail.setGuestUserName(uploadImageWithEmailRequestVO.getGuestName());
			uploadImageEmail.setPicId(picId);
			uploadImageEmail.setImageTimestamp(uploadImageWithEmailRequestVO.getImageTimestamp());
			if(uploadImageWithEmailRequestVO.getSessionTime()==null && uploadImageWithEmailRequestVO.getSessionTime()==""){
			uploadImageEmail.setSessionTime("0");
		    }else if(uploadImageWithEmailRequestVO.getSessionTime().equalsIgnoreCase("nan")){
		    	uploadImageEmail.setSessionTime("0");	
		    }else{
			uploadImageEmail.setSessionTime(uploadImageWithEmailRequestVO.getSessionTime());
		    }
			
			if(defaultId!=null){
				uploadImageEmail.setDefaultId(defaultId);	
			}
			imageId=(Integer) sessionFactory.getCurrentSession().save(uploadImageEmail);
			
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return imageId;
	}
	
	@Override
	public boolean updateEmailSendTime(String imageName,String userId) {
		boolean result=false;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			String[] img=imageName.split(",");//splits the string based on whitespace  
			for(String w:img){
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
			criteria.add(Restrictions.eq("mailImageName", w));
			criteria.add(Restrictions.eq("userId", Integer.parseInt(userId)));
			BoothUploadImageEmail imageDetails=(BoothUploadImageEmail) criteria.uniqueResult();
			if(imageDetails !=null){
			imageDetails.setMailSentTime(IAmuseUtil.getTimeStamp());
			sessionFactory.getCurrentSession().update(imageDetails);
			}
			}
			result=true;
			
		 sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		
		return result;
	}
	
	@Override
	public Integer crashlogsupload(String url) {
		Integer id=0;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			CrashLogs crashLogs=new CrashLogs();
			crashLogs.setFileUrl(url);
			crashLogs.setStatus(true);
			id=(Integer) sessionFactory.getCurrentSession().save(crashLogs);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		
		return id;
	}
	
@Override
public boolean crashlogsuploadName(Integer id, String fileName,String userId) {
		boolean result=false;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			CrashLogs crashLogs = (CrashLogs) sessionFactory.getCurrentSession().load(CrashLogs.class,id);
			crashLogs.setFileName(fileName);
			crashLogs.setUploadTime(IAmuseUtil.getTimeStamp());
			crashLogs.setUserId(Integer.parseInt(userId));
			crashLogs.setReadStatus(false);
		    sessionFactory.getCurrentSession().saveOrUpdate(crashLogs);
		    result=true;
		    sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

@Override
public boolean saveDeviceIP(DeviceIPRequestVO deviceIPRequestVO) {
	boolean result=false;
	try {
		sessionFactory.getCurrentSession().beginTransaction();
		DeviceIp deviceIp=new DeviceIp();
		deviceIp.setDeviceType(deviceIPRequestVO.getDeviceType());
		deviceIp.setDeviceIp(deviceIPRequestVO.getDeviceIP());
		deviceIp.setUploadTime(IAmuseUtil.getTimeStamp());
		deviceIp.setStatus(true);
		sessionFactory.getCurrentSession().save(deviceIp);
		result=true;
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
		criteria.add(Restrictions.eq("deviceType",deviceIPRequestVO.getDeviceType()));
	    deviceIp = (DeviceIp) criteria.uniqueResult();
		sessionFactory.getCurrentSession().getTransaction().commit();
	} catch (Exception e) {
		e.printStackTrace();
		sessionFactory.getCurrentSession().getTransaction().rollback();
	}
	return deviceIp;
}

@Override
public LoginBaseResponseVO saveAdminBoothRegistration(BoothAdminRegistrationRequestVO adminBoothRegistrationRequestVO) {
	
	 List<AdminBoothEventResponseVO> responseVOList=new ArrayList<>();
	 AdminEventPictureMappingResponse adminEventPictureMappingObject=new AdminEventPictureMappingResponse();
	LoginBaseResponseVO responseVo=null;
	try {
		sessionFactory.getCurrentSession().beginTransaction();
		responseVo =new  LoginBaseResponseVO();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
		criteria.add(Restrictions.eq("emailId",adminBoothRegistrationRequestVO.getEmailId().trim()));
		criteria.add(Restrictions.eq("status", true));
		criteria.add(Restrictions.eq("isDeleted", false));
		BoothAdminLogin adminBoothEntity = (BoothAdminLogin) criteria.uniqueResult();
		if(adminBoothEntity ==null){
			responseVo =new  LoginBaseResponseVO();
			
			BoothAdminLogin boothAdminRegistrationEntity=new BoothAdminLogin();
			boothAdminRegistrationEntity.setUserType(adminBoothRegistrationRequestVO.getUserType());
			boothAdminRegistrationEntity.setEmailId(adminBoothRegistrationRequestVO.getEmailId().trim());
			boothAdminRegistrationEntity.setPassword(Crypto.encrypt(adminBoothRegistrationRequestVO.getPassword()));
			boothAdminRegistrationEntity.setUsername(adminBoothRegistrationRequestVO.getUserName());
			boothAdminRegistrationEntity.setStatus(true);
			boothAdminRegistrationEntity.setSubId(ServerConstants.SUBSCRIPTION_NORMAL);
			boothAdminRegistrationEntity.setUserRole("boothadmin");
			boothAdminRegistrationEntity.setCreatedDate(new Date());
			boothAdminRegistrationEntity.setLocation(adminBoothRegistrationRequestVO.getLocation());
			boothAdminRegistrationEntity.setIsDeleted(false);
			boothAdminRegistrationEntity.setHexValueDefault("#341561");
			boothAdminRegistrationEntity.setRgbValueDefault("0,255,0");
			boothAdminRegistrationEntity.setRgbaValueDefault("0,255,0,255");
			boothAdminRegistrationEntity.setCurrentImageId(0);
			boothAdminRegistrationEntity.setIsSubscriptionByAdmin(false);
			boothAdminRegistrationEntity.setIsDefaultRgb(true);
			boothAdminRegistrationEntity.setSubUpdatedDate(new java.sql.Timestamp(new Date().getTime()));
			Integer userId=(Integer)sessionFactory.getCurrentSession().save(boothAdminRegistrationEntity);
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
			
			Criteria criteriaAdminBoothLogin = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteriaAdminBoothLogin.add(Restrictions.eq("userId",userId));
			criteriaAdminBoothLogin.add(Restrictions.eq("status",true));
			criteriaAdminBoothLogin.add(Restrictions.eq("isDeleted",false));
			BoothAdminLogin adminBoothLoginEntity = (BoothAdminLogin) criteriaAdminBoothLogin.uniqueResult();
			BoothAdminLoginResponseVO login=new BoothAdminLoginResponseVO();
			if(adminBoothLoginEntity!=null){
				login.setContactNumber(adminBoothLoginEntity.getContactNumber());
				login.setCreatedDate(DateUtils.timeStampConvertIntoStringDateFormat(adminBoothLoginEntity.getCreatedDate()));
				login.setEmailId(adminBoothLoginEntity.getEmailId());
				login.setStatus(adminBoothLoginEntity.getStatus());
				login.setSubId(adminBoothLoginEntity.getSubId());
				login.setLocation(adminBoothLoginEntity.getLocation());
				login.setStatus(adminBoothLoginEntity.getStatus());
				if(adminBoothLoginEntity.getIsDeleted()!=null){
					login.setIsDeleted(adminBoothLoginEntity.getIsDeleted());
				}
				if(adminBoothLoginEntity.getSubUpdatedDate()!=null){
					login.setSubUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormat(adminBoothLoginEntity.getSubUpdatedDate()));
				}
				if(adminBoothLoginEntity.getUpdatedDate()!=null){
					login.setUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormat(adminBoothLoginEntity.getUpdatedDate()));
				}
				login.setUserId(adminBoothLoginEntity.getUserId());
				login.setUsername(adminBoothLoginEntity.getUsername());
				login.setUserRole(adminBoothLoginEntity.getUserRole());
				login.setHexValueDefault(adminBoothLoginEntity.getHexValueDefault());
				login.setRgbValueDefault(adminBoothLoginEntity.getRgbValueDefault());
				login.setRgbaValueDefault(adminBoothLoginEntity.getRgbaValueDefault());
				if(adminBoothLoginEntity.getCurrentImageId()!=null){
					login.setCurrentImageId(adminBoothLoginEntity.getCurrentImageId());
				}
				login.setIsDefaultRgb(adminBoothLoginEntity.getIsDefaultRgb());
				login.setHexValueManual(adminBoothLoginEntity.getHexValueManual());
				login.setRgbValueManual(adminBoothLoginEntity.getRgbValueManual());
				login.setRgbaValueManual(adminBoothLoginEntity.getRgbaValueManual());
			}
			
			if(adminBoothLoginEntity!=null){
				Object response1=serviceRequestTemplateZoho.zohoRequestToServer("https://subscriptions.zoho.com/api/v1/plans?filter_by=PlanStatus.All");
				List<SubscriptionMasterResponseVO> subscriptionMasterResponseVoList=new ArrayList<>();
				SubscriptionMasterResponseVO responseVO=new SubscriptionMasterResponseVO();
					responseVO.setCreatedDate(null);
			     	responseVO.setSubId("1");
			     	responseVO.setSubName("FREE");
			     	responseVO.setSubPrice("0");
			     	responseVO.setSubValidaityDayPeriod("Life Time");
			     	responseVO.setResponseCode("1");
			    	responseVO.setResponseDescription("Success");
			    	subscriptionMasterResponseVoList.add(responseVO);
				 try {
					 SubscriptionMasterResponseVO subscriptionMasterResponseVO=null;
				     JSONArray responseResult = new JSONArray("["+response1.toString()+"]");
				     for (int i=0; i<responseResult.length();i++){
				         JSONObject responseData = (JSONObject) responseResult.get(i);
				         JSONArray data = new JSONArray(responseData.getString("plans"));
				         for(int j=0; j<data.length(); j++) {
				        	 JSONObject subscriptionData = (JSONObject) data.get(j);
					         System.out.println("plancode::"+responseData.getString("plan_code"));
					         String subId=subscriptionData.getString("plan_code");
					         String name=subscriptionData.getString("name");
					         String description=subscriptionData.getString("description");
					         String created_time=subscriptionData.getString("created_time");
					         String recurring_price=subscriptionData.getString("recurring_price");
					         String interval_unit=subscriptionData.getString("interval_unit");
				   
				         
				         
					         subscriptionMasterResponseVO=new SubscriptionMasterResponseVO();
				
					         subscriptionMasterResponseVO.setCreatedDate(created_time);
					         subscriptionMasterResponseVO.setSubId(subId);
					         subscriptionMasterResponseVO.setSubName(name);
					         subscriptionMasterResponseVO.setSubPrice(recurring_price);
					         subscriptionMasterResponseVO.setSubValidaityDayPeriod(interval_unit);
						
						subscriptionMasterResponseVoList.add(subscriptionMasterResponseVO);
				     }
				     }
				 }catch(Exception e) {
					 e.printStackTrace();
				 }
			/*Criteria crt=sessionFactory.getCurrentSession().createCriteria(SubscriptionMaster.class).add(Restrictions.eq("subId", adminBoothLoginEntity.getSubId()));
			crt.add(Restrictions.eq("status", true));
			crt.add(Restrictions.eq("isDeleted", false));
			List<SubscriptionMaster> subscriptionMasterList =crt.list();
			List<SubscriptionMasterResponseVO> subscriptionMasterResponseVoList=new ArrayList<>();
			SubscriptionMasterResponseVO vo=null;
			for (SubscriptionMaster s : subscriptionMasterList) {
				vo=new SubscriptionMasterResponseVO();
				if(s.getStatus()!=null){
				vo.setStatus(s.getStatus());
				}
				if(s.getIsDeleted()!=null){
				vo.setIsDeleted(s.getIsDeleted());
				}
				vo.setCreatedDate(DateUtils.timeStampConvertIntoStringDateFormat(s.getCreatedDate()));
				vo.setSubId(s.getSubId());
				vo.setSubName(s.getSubName());
				vo.setSubPrice(s.getSubPrice());
				vo.setSubValidaityDayPeriod(s.getSubValidaityDayPeriod());
				if(s.getCreatedUserId()!=null){
				vo.setCreatedUserId(s.getCreatedUserId());
				}
				
				if(s.getUpdatedByUserId()!=null){
					vo.setUpdatedByUserId(s.getUpdatedByUserId());
					}
				
				if(s.getUpdatedDate()!=null){
					vo.setUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormat(s.getUpdatedDate()));
					}
				subscriptionMasterResponseVoList.add(vo);
			}*/
			//end 11-11-2016
			Criteria criteriaDeviceRegistration =sessionFactory.getCurrentSession().createCriteria(DeviceRegistration.class);
			criteriaDeviceRegistration.add(Restrictions.eq("status", true));
			criteriaDeviceRegistration.add(Restrictions.eq("isDeleted", false));
			criteriaDeviceRegistration.add(Restrictions.eq("userId", adminBoothLoginEntity.getUserId()));
			List<DeviceRegistration> deviceRegistration=criteriaDeviceRegistration.list();
			//start 11-11-2016
			List<DeviceRegistrationResponseVO> deviceResponseVOList=new ArrayList<>();
			DeviceRegistrationResponseVO deviceVO=null;
			for (DeviceRegistration d : deviceRegistration) {
				deviceVO=new DeviceRegistrationResponseVO();
				if(d.getStatus()!=null){
					deviceVO.setStatus(d.getStatus());
				}
				
				if(d.getIsDeleted()!=null){
					deviceVO.setIsDeleted(d.getIsDeleted());
				}
				if(d.getCreatedDate()!=null){
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
				if(d.getLastSyncTime()!=null){
					deviceVO.setLastSyncTime(DateUtils.timeStampConvertIntoStringDateFormat(d.getLastSyncTime()));
				}
				deviceVO.setOperationgSystemVersion(d.getOperationgSystemVersion());
				deviceVO.setUserId(d.getUserId());
				deviceVO.setWirelessNetwork(d.getWirelessNetwork());
				deviceVO.setDeviceUUID(d.getDeviceUUID());
				deviceVO.setSubNetMask(d.getSubNetMask());
				deviceResponseVOList.add(deviceVO);
			}
			
			//end 11-11-2016
			responseVo.setResponseCode("1");
			responseVo.setResponseDescription("Success");
			responseVo.setBoothAdminLoginResponse(login);
			responseVo.setSubscriptionMasterList(subscriptionMasterResponseVoList);
			responseVo.setDeviceRegistrationResponse(deviceResponseVOList);
		}

			responseVo.setResponseCode("1");
			responseVo.setResponseDescription("Success");
		}else{
			responseVo =new  LoginBaseResponseVO();
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

@Override
public LoginBaseResponseVO fetchLoginBaseResponseVO(LoginBoothAdminRegistrationRequestVO loginRegistrationRequestVO) {
	 List<AdminBoothEventResponseVO> responseVOList=new ArrayList<>();
	 AdminEventPictureMappingResponse adminEventPictureMappingObject=new AdminEventPictureMappingResponse();
	LoginBaseResponseVO responseVo=new  LoginBaseResponseVO();

	try {
		sessionFactory.getCurrentSession().beginTransaction();
		
		
		Criteria criteriaAdminBoothLogin = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
		criteriaAdminBoothLogin.add(Restrictions.eq("emailId",loginRegistrationRequestVO.getEmailId()));
		criteriaAdminBoothLogin.add(Restrictions.eq("password",Crypto.encrypt(loginRegistrationRequestVO.getPassword())));
		criteriaAdminBoothLogin.add(Restrictions.eq("status",true));
		criteriaAdminBoothLogin.add(Restrictions.eq("isDeleted",false));
		
		BoothAdminLogin adminBoothLoginEntity = (BoothAdminLogin) criteriaAdminBoothLogin.uniqueResult();
		BoothAdminLoginResponseVO login=new BoothAdminLoginResponseVO();
		if(adminBoothLoginEntity!=null){
			login.setContactNumber(adminBoothLoginEntity.getContactNumber());
			login.setCreatedDate(DateUtils.timeStampConvertIntoStringDateFormat(adminBoothLoginEntity.getCreatedDate()));
			login.setEmailId(adminBoothLoginEntity.getEmailId());
			login.setStatus(adminBoothLoginEntity.getStatus());
			login.setSubId(adminBoothLoginEntity.getSubId());
			login.setLocation(adminBoothLoginEntity.getLocation());
			login.setStatus(adminBoothLoginEntity.getStatus());
			if(adminBoothLoginEntity.getIsDeleted()!=null){
				login.setIsDeleted(adminBoothLoginEntity.getIsDeleted());
			}
			if(adminBoothLoginEntity.getSubUpdatedDate()!=null){
				login.setSubUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormat(adminBoothLoginEntity.getSubUpdatedDate()));
			}
			if(adminBoothLoginEntity.getUpdatedDate()!=null){
				login.setUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormat(adminBoothLoginEntity.getUpdatedDate()));
			}
			login.setUserId(adminBoothLoginEntity.getUserId());
			login.setUsername(adminBoothLoginEntity.getUsername());
			login.setUserRole(adminBoothLoginEntity.getUserRole());
			//123456789
			login.setHexValueDefault(adminBoothLoginEntity.getHexValueDefault());
			login.setRgbValueDefault(adminBoothLoginEntity.getRgbValueDefault());
			login.setRgbaValueDefault(adminBoothLoginEntity.getRgbaValueDefault());
			if(adminBoothLoginEntity.getCurrentImageId()!=null){
			login.setCurrentImageId(adminBoothLoginEntity.getCurrentImageId());
			}
			login.setIsDefaultRgb(adminBoothLoginEntity.getIsDefaultRgb());
			login.setHexValueManual(adminBoothLoginEntity.getHexValueManual());
			login.setRgbValueManual(adminBoothLoginEntity.getRgbValueManual());
			login.setRgbaValueManual(adminBoothLoginEntity.getRgbaValueManual());
		}
		
		if(adminBoothLoginEntity!=null){
			
			Object response1=serviceRequestTemplateZoho.zohoRequestToServer("https://subscriptions.zoho.com/api/v1/plans?filter_by=PlanStatus.All");
			List<SubscriptionMasterResponseVO> subscriptionMasterResponseVoList=new ArrayList<>();
			SubscriptionMasterResponseVO responseVO=new SubscriptionMasterResponseVO();
			responseVO.setCreatedDate(null);
	     	responseVO.setSubId("1");
	     	responseVO.setSubName("FREE");
	     	responseVO.setSubPrice("0");
	     	responseVO.setSubValidaityDayPeriod("Life Time");
	     	responseVO.setResponseCode("1");
	    	responseVO.setResponseDescription("Success");
	    	subscriptionMasterResponseVoList.add(responseVO);
			 try {
				 SubscriptionMasterResponseVO subscriptionMasterResponseVO=null;
			     JSONArray responseResult = new JSONArray("["+response1.toString()+"]");
			     for (int i=0; i<responseResult.length();i++){
			         JSONObject responseData = (JSONObject) responseResult.get(i);
			         JSONArray data = new JSONArray(responseData.getString("plans"));
			         for(int j=0; j<data.length(); j++) {
			        	 JSONObject subscriptionData = (JSONObject) data.get(j);
			         System.out.println("plancode::"+subscriptionData.getString("plan_code"));
			         String subId=subscriptionData.getString("plan_code");
			         String name=subscriptionData.getString("name");
			         String description=subscriptionData.getString("description");
			         String created_time=subscriptionData.getString("created_time");
			         String recurring_price=subscriptionData.getString("recurring_price");
			         String interval_unit=subscriptionData.getString("interval_unit");
			   
			         
			         
			         subscriptionMasterResponseVO=new SubscriptionMasterResponseVO();
		
			         subscriptionMasterResponseVO.setCreatedDate(created_time);
			         subscriptionMasterResponseVO.setSubId(subId);
			         subscriptionMasterResponseVO.setSubName(name);
			         subscriptionMasterResponseVO.setSubPrice(recurring_price);
			         subscriptionMasterResponseVO.setSubValidaityDayPeriod(interval_unit);
					
					subscriptionMasterResponseVoList.add(subscriptionMasterResponseVO);
			         }
			        /* for(int j=0; j<data.length(); j++) {
			        	 JSONObject subscriptionData = (JSONObject) data.get(j);

			        	 JSONArray subscription = new JSONArray("["+subscriptionData.getString("subscription")+"]");
				         for(int k=0; k<subscription.length(); k++) {
				        	 JSONObject subscriptionResponse = (JSONObject) subscription.get(k);
				        	 subscriptionMasterResponseVO.setProductId(subscriptionResponse.getString("product_id"));//=product_id;
				        	 subscriptionMasterResponseVO.setTxnId(subscriptionResponse.getString("subscription_id"));//=subscription_id;
				        	 subscriptionMasterResponseVO.setPaymentAmount(subscriptionResponse.getString("amount"));//=amount;
				        	 subscriptionMasterResponseVO.setPaymentStatus(subscriptionResponse.getString("payment_terms_label"));//=paymentStatus;
				        	 subscriptionMasterResponseVO.setPaymentDate(subscriptionResponse.getString("created_at"));//=created_at;
				        	 subscriptionMasterResponseVO.setOriginalpurchasedate(subscriptionResponse.getString("current_term_ends_at"));
				        	 JSONArray customerData = new JSONArray("["+subscriptionResponse.getString("customer")+"]");
					        	 for(int l=0; l<customerData.length(); l++) {
						        	 JSONObject custResponse = (JSONObject) customerData.get(l);
						        	 subscriptionMasterResponseVO.setPayerId(custResponse.getString("customer_id"));//=customer_id; 
						        	 subscriptionMasterResponseVO.setPayerEmail(custResponse.getString("email"));//=resemail;
						        	 subscriptionMasterResponseVO.setFirstName(custResponse.getString("first_name")+" "+custResponse.getString("last_name"));//=display_name;
						         }
				        	 	
				        	  JSONArray planeData = new JSONArray("["+subscriptionResponse.getString("plan")+"]");
						         for(int l=0; l<planeData.length(); l++) {
						        	 JSONObject planResponse = (JSONObject) planeData.get(l);
						        	 subscriptionMasterResponseVO.setItemid(planResponse.getString("plan_code"));//=plan_code;
						        	 subscriptionMasterResponseVO.setTax(planResponse.getString("tax_percentage"));//=tax_percentage;
						        	 subscriptionMasterResponseVO.setItemNumber(planResponse.getString("plan_code"));//=plan_code;
						        	 subscriptionMasterResponseVO.setItemName(planResponse.getString("name"));//=name;
						        	 subscriptionMasterResponseVO.setPaymentFee(planResponse.getString("setup_fee"));//=setup_fee;
						         }
				         }
			         }*/
			     }
			 } catch (JSONException e){
			     e.printStackTrace();
			 }
			
			/*	
		Criteria crt=sessionFactory.getCurrentSession().createCriteria(SubscriptionMaster.class).add(Restrictions.eq("subId", adminBoothLoginEntity.getSubId()));
		crt.add(Restrictions.eq("status", true));
		crt.add(Restrictions.eq("isDeleted", false));
		List<SubscriptionMaster> subscriptionMasterList =crt.list();
		
		//start 11-11-2016
		List<SubscriptionMasterResponseVO> subscriptionMasterResponseVoList=new ArrayList<>();
		SubscriptionMasterResponseVO vo=null;
		for (SubscriptionMaster s : subscriptionMasterList) {
			vo=new SubscriptionMasterResponseVO();
			if(s.getStatus()!=null){
				vo.setStatus(s.getStatus());
			}
			if(s.getIsDeleted()!=null){
				vo.setIsDeleted(s.getIsDeleted());
			}
			vo.setCreatedDate(DateUtils.timeStampConvertIntoStringDateFormat(s.getCreatedDate()));
			vo.setSubId(s.getSubId());
			vo.setSubName(s.getSubName());
			vo.setSubPrice(s.getSubPrice());
			vo.setSubValidaityDayPeriod(s.getSubValidaityDayPeriod());
			if(s.getCreatedUserId()!=null){
				vo.setCreatedUserId(s.getCreatedUserId());
			}
			if(s.getUpdatedByUserId()!=null){
				vo.setUpdatedByUserId(s.getUpdatedByUserId());
			}
			if(s.getUpdatedDate()!=null){
			vo.setUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormat(s.getUpdatedDate()));
				}
			subscriptionMasterResponseVoList.add(vo);
		}*/
		//end 11-11-2016
		Criteria criteriaDeviceRegistration =sessionFactory.getCurrentSession().createCriteria(DeviceRegistration.class);
		criteriaDeviceRegistration.add(Restrictions.eq("status", true));
		criteriaDeviceRegistration.add(Restrictions.eq("isDeleted", false));
		criteriaDeviceRegistration.add(Restrictions.eq("userId", adminBoothLoginEntity.getUserId()));
		List<DeviceRegistration> deviceRegistration=criteriaDeviceRegistration.list();
		//start 11-11-2016
		List<DeviceRegistrationResponseVO> deviceResponseVOList=new ArrayList<>();
		DeviceRegistrationResponseVO deviceVO=null;
		for (DeviceRegistration d : deviceRegistration) {
			deviceVO=new DeviceRegistrationResponseVO();
			if(d.getStatus()!=null){
				deviceVO.setStatus(d.getStatus());
			}
			if(d.getIsDeleted()!=null){
				deviceVO.setIsDeleted(d.getIsDeleted());
			}
			if(d.getCreatedDate()!=null){
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
			if(d.getLastSyncTime()!=null){
				deviceVO.setLastSyncTime(DateUtils.timeStampConvertIntoStringDateFormat(d.getLastSyncTime()));
			}
			deviceVO.setOperationgSystemVersion(d.getOperationgSystemVersion());
			deviceVO.setUserId(d.getUserId());
			deviceVO.setWirelessNetwork(d.getWirelessNetwork());
			deviceVO.setDeviceUUID(d.getDeviceUUID());
			deviceVO.setSubNetMask(d.getSubNetMask());
			deviceResponseVOList.add(deviceVO);
		}
		//end 11-11-2016
		responseVo.setResponseCode("1");
		responseVo.setResponseDescription("Success");
		responseVo.setBoothAdminLoginResponse(login);
		responseVo.setSubscriptionMasterList(subscriptionMasterResponseVoList);
		responseVo.setDeviceRegistrationResponse(deviceResponseVOList);
		}else{
			responseVo =new  LoginBaseResponseVO();
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
public EventFetchingBaseResponseVO fetchEventFetchingAdminBooth(FetchingEventListRequestVO fetchinfEventAdminBoothRequestVo) {
	 AdminEventPictureMappingResponse adminEventPictureMappingObject=new AdminEventPictureMappingResponse();
	 EventFetchingBaseResponseVO responseVo =new  EventFetchingBaseResponseVO();
	 List<AdminBoothEventResponseVO> responseVOList=new ArrayList<>();
	try {
		
		sessionFactory.getCurrentSession().beginTransaction();
		 
		 Integer userId=new Integer(fetchinfEventAdminBoothRequestVo.getUserId());
		 
		 if(!fetchinfEventAdminBoothRequestVo.getSubId().equals("0")){
		// if(fetchinfEventAdminBoothRequestVo.getSubId()!=1 ){
			 Criteria criteria =sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			 if(!fetchinfEventAdminBoothRequestVo.getSubId().equals("1")){
			 criteria.add(Restrictions.eq("createdBy", userId));
			 }
			 criteria.add(Restrictions.eq("status", true));
			 criteria.add(Restrictions.eq("isDeleted", false));
			 if(fetchinfEventAdminBoothRequestVo.getSubId().equals("1")){
			criteria.add(Restrictions.eq("eventType","default"));
			 }
			 criteria.addOrder(Order.desc("EId"));
			 List<Adminboothevent> adminEventPictureMapping =criteria.list();
		 //Criteria criteria =sessionFactory.getCurrentSession().createCriteria(AdminEventPictureMapping.class);
		 //ProjectionList projList = Projections.projectionList();
		 //projList.add(Projections.property("EId"));
		 //criteria.setProjection(Projections.distinct(projList));
		 //criteria.add(Restrictions.eq("status", true));
		 //criteria.add(Restrictions.eq("isDeleted", false));
		 //criteria.addOrder(Order.desc("EId"));
		//List<Integer> adminEventPictureMapping =criteria.add(Restrictions.eq("userId",userId)).setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE).list();

		if(adminEventPictureMapping.size()>0){
		for (Adminboothevent object : adminEventPictureMapping) {
			
		if(object!=null){
			Criteria criteriaFovbyuser= sessionFactory.getCurrentSession().createCriteria(Fovbyuser.class);
			criteriaFovbyuser.add(Restrictions.eq("userId", userId));
			Fovbyuser fovbyuser=(Fovbyuser)criteriaFovbyuser.uniqueResult();
			
			Criteria criteriaAdminBoothEvent =sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			criteriaAdminBoothEvent.add(Restrictions.eq("status", true));
			criteriaAdminBoothEvent.add(Restrictions.eq("isDeleted", false));
		 if(fovbyuser!=null){
			 criteria.add(Restrictions.eq("zoomScale", fovbyuser.getZoomScale()));
		 }else{
			 criteria.add(Restrictions.eq("zoomScale", "1.00"));
		 }
		 List<Adminboothevent> eventList=criteriaAdminBoothEvent.add(Restrictions.eq("EId", object.getEId())).list();
		
		AdminBoothEventResponseVO responseVO=null;
		for (Adminboothevent adminboothevent : eventList) {
			
			responseVO=new  AdminBoothEventResponseVO();
			Criteria criteriaAdminPicture =sessionFactory.getCurrentSession().createCriteria(AdminPicture.class);
			criteriaAdminPicture.add(Restrictions.eq("status", true));
			criteriaAdminPicture.add(Restrictions.eq("isDeleted", false));
			List<AdminPicture> adminPictureList=criteriaAdminPicture.add(Restrictions.eq("eId", adminboothevent.getEId())).list();
		//Start 11-11-2016
			List<AdminPictureResponseVO> pictureResponseList=new ArrayList<>();
			AdminPictureResponseVO picture=null;
			for (AdminPicture p : adminPictureList) {
				picture=new AdminPictureResponseVO();
				picture.setCreatedBy(p.getCreatedBy());
				picture.setCreatedDate(DateUtils.timeStampConvertIntoStringDateFormat(p.getCreatedDate()));
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
				if(p.getUpdatedDate()!=null){
					picture.setUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormat(p.getUpdatedDate()));
				}
				if(p.getImageHeight()!=null){
					picture.setImageHeight(p.getImageHeight());
				}
				if(p.getImageWidth()!=null){
					picture.setImageWidth(p.getImageWidth());
				}
				if(p.getScalingHeight()!=null){
					picture.setScalingHeight(p.getScalingHeight());
				}
				if(p.getScalingWidth()!=null){
					picture.setScalingWidth(p.getScalingWidth());
				}
				if(p.getStatus()!=null){
					picture.setStatus(p.getStatus());
				}
				if(p.getIsDeleted()!=null){
					picture.setIsDeleted(p.getIsDeleted());
				}
				pictureResponseList.add(picture);
			}
			responseVO.setAdminBoothEventPicture(pictureResponseList);
			// end 11-11-2016
		 responseVO.setCreatedBy(adminboothevent.getCreatedBy());
		 if(adminboothevent.getStatus()!=null){
			 responseVO.setStatus(adminboothevent.getStatus());
		 }
		 
		 if(adminboothevent.getIsDeleted()!=null){
			 responseVO.setIsDeleted(adminboothevent.getIsDeleted());
		 }
		 responseVO.setCreatedDate(DateUtils.timeStampConvertIntoStringDateFormat(adminboothevent.getCreatedDate()));
		 responseVO.setEId(adminboothevent.getEId());
		 responseVO.setEventEnd(adminboothevent.getEventEnd());
		 responseVO.setEventHostMailerId(adminboothevent.getEventHostMailerId());
		 responseVO.setEventLocation(adminboothevent.getEventLocation());
		 responseVO.setEventName(adminboothevent.getEventName());
		 responseVO.setEventStart(adminboothevent.getEventStart());
		 responseVO.setUpdatedBy(adminboothevent.getUpdatedBy());
		 if(adminboothevent.getUpdatedDate()!=null){
			 responseVO.setUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormat(adminboothevent.getUpdatedDate()));
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
			//Added By Abhishek Dated 4-01-2017
			responseVO.setThankYouScreen(adminboothevent.getThankYouScreen());
			responseVO.setCameraTVScreenSaver(adminboothevent.getCameraTVScreenSaver());
			responseVO.setLookAtTouchScreen(adminboothevent.getLookAtTouchScreen());
			responseVO.setWaterMarkImage(adminboothevent.getWaterMarkImage());
			responseVOList.add(responseVO);
		} 
		}
		 adminEventPictureMappingObject.setModifiedResult(responseVOList);
		}
		 }
	/*}else{ System.out.println(" hi abhishek fetch the default event ");
	  
    Criteria criteriaAdminBoothEvent =sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
    criteriaAdminBoothEvent.add(Restrictions.eq("status", true));
    criteriaAdminBoothEvent.add(Restrictions.eq("isDeleted", false));
    List<Adminboothevent> eventList=criteriaAdminBoothEvent.add(Restrictions.eq("eventType", "default")).list();
   
   AdminBoothEventResponseVO responseVO=null;
   for (Adminboothevent adminboothevent : eventList) {
	   Criteria criteria =sessionFactory.getCurrentSession().createCriteria(AdminEventPictureMapping.class);
	   criteria.add(Restrictions.eq("status", true));
	   criteria.add(Restrictions.eq("isDeleted", false));
	   criteria.add(Restrictions.eq("EId", adminboothevent.getEId()));
	   List<AdminEventPictureMapping> adminEventPictureMapping =criteria.list(); 
	   responseVO=new  AdminBoothEventResponseVO();
    for (AdminEventPictureMapping adminEventPictureMapping2 : adminEventPictureMapping) {
    	Criteria criteriaAdminPicture =sessionFactory.getCurrentSession().createCriteria(AdminPicture.class);
    	criteriaAdminPicture.add(Restrictions.eq("status", true));
    	criteriaAdminPicture.add(Restrictions.eq("isDeleted", false));
    	List<AdminPicture> adminPictureList=criteriaAdminPicture.add(Restrictions.eq("picId", adminEventPictureMapping2.getPicId())).list();
	   //Start 11-11-2016
	    List<AdminPictureResponseVO> pictureResponseList=new ArrayList<>();
	    AdminPictureResponseVO picture=null;
	    for (AdminPicture p : adminPictureList) {
	     picture=new AdminPictureResponseVO();
	     picture.setCreatedBy(p.getCreatedBy());
	     picture.setCreatedDate(DateUtils.timeStampConvertIntoStringDateFormat(p.getCreatedDate()));
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
	     if(p.getUpdatedDate()!=null){
	    	 picture.setUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormat(p.getUpdatedDate()));
	     }
	     if(p.getImageHeight()!=null){
	    	 picture.setImageHeight(p.getImageHeight());
	     }
	     if(p.getImageWidth()!=null){
	    	 picture.setImageWidth(p.getImageWidth());
	     }
	     if(p.getScalingHeight()!=null){
	    	 picture.setScalingHeight(p.getScalingHeight());
	     }
	     if(p.getScalingWidth()!=null){
	    	 picture.setScalingWidth(p.getScalingWidth());
	     }
	     if(p.getStatus()!=null){
	    	 picture.setStatus(p.getStatus());
	     }
	     if(p.getIsDeleted()!=null){
	    	 picture.setIsDeleted(p.getIsDeleted());
	     }
	     	pictureResponseList.add(picture);
	    }
	    responseVO.setAdminBoothEventPicture(pictureResponseList);
	    // end 11-11-2016
	    responseVO.setCreatedBy(adminboothevent.getCreatedBy());
	    if(adminboothevent.getStatus()!=null){
	    	responseVO.setStatus(adminboothevent.getStatus());
	    }
	    
	    if(adminboothevent.getIsDeleted()!=null){
	    	responseVO.setIsDeleted(adminboothevent.getIsDeleted());
	    }
	    responseVO.setCreatedDate(DateUtils.timeStampConvertIntoStringDateFormat(adminboothevent.getCreatedDate()));
	    responseVO.setEId(adminboothevent.getEId());
	    responseVO.setEventEnd(adminboothevent.getEventEnd());
	    responseVO.setEventHostMailerId(adminboothevent.getEventHostMailerId());
	    responseVO.setEventLocation(adminboothevent.getEventLocation());
	    responseVO.setEventName(adminboothevent.getEventName());
	    responseVO.setEventStart(adminboothevent.getEventStart());
	    responseVO.setUpdatedBy(adminboothevent.getUpdatedBy());
	    if(adminboothevent.getUpdatedDate()!=null){
	    	responseVO.setUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormat(adminboothevent.getUpdatedDate()));
	    }
	    responseVO.setSponsorName(adminboothevent.getSponsorName());
	    responseVOList.add(responseVO);
	   }
	   } 
		adminEventPictureMappingObject.setModifiedResult(responseVOList);
	}*/
				responseVo.setResponseCode("1");
				responseVo.setResponseDescription("Success");
				responseVo.setAdminEventPictureMappingResponse(adminEventPictureMappingObject);
		}else{
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
	 String result="";
	 try {
		 if(deviceRegistrationRequestVO.getUserId() !=null && (deviceRegistrationRequestVO.getDeviceType() !=null || !deviceRegistrationRequestVO.getDeviceType().isEmpty() )) {
		 		sessionFactory.getCurrentSession().beginTransaction();
				Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DeviceRegistration.class);
				criteria.add(Restrictions.eq("userId",deviceRegistrationRequestVO.getUserId()));
				criteria.add(Restrictions.eq("deviceType", deviceRegistrationRequestVO.getDeviceType()));
				DeviceRegistration deviceRegistration = (DeviceRegistration) criteria.uniqueResult();
				if(deviceRegistration !=null){
						deviceRegistration.setDeviceToken(deviceRegistrationRequestVO.getDeviceToken());
						deviceRegistration.setDeteactedResolution(deviceRegistrationRequestVO.getDeteactedResolution());
						deviceRegistration.setDeviceName(deviceRegistrationRequestVO.getDeviceName());
						deviceRegistration.setDeviceStorage(deviceRegistrationRequestVO.getDeviceStorage());
						deviceRegistration.setDeviceType(deviceRegistrationRequestVO.getDeviceType());
						deviceRegistration.setGuidedAccessEnabled(deviceRegistrationRequestVO.getGuidedAccessEnabled());
						deviceRegistration.setIpAddress(deviceRegistrationRequestVO.getIpAddress());
						deviceRegistration.setLastSyncTime(new Date());
						deviceRegistration.setOperationgSystemVersion(deviceRegistrationRequestVO.getOperationgSystemVersion());
						deviceRegistration.setWirelessNetwork(deviceRegistrationRequestVO.getWirelessNetwork());
						deviceRegistration.setSubNetMask(deviceRegistrationRequestVO.getSubNetMask());
						deviceRegistration.setDeviceUUID(deviceRegistrationRequestVO.getDeviceUUID());
						deviceRegistration.setDeviceTimestamp(deviceRegistrationRequestVO.getDeviceTimestamp());
						sessionFactory.getCurrentSession().update(deviceRegistration);
						result="update";
				}else{
					  DeviceRegistration deviceRegistration1 = new DeviceRegistration();
					  deviceRegistration1.setDeteactedResolution(deviceRegistrationRequestVO.getDeteactedResolution());
					  deviceRegistration1.setDeviceName(deviceRegistrationRequestVO.getDeviceName());
					  deviceRegistration1.setDeviceStorage(deviceRegistrationRequestVO.getDeviceStorage());
					  deviceRegistration1.setDeviceToken(deviceRegistrationRequestVO.getDeviceToken());
					  deviceRegistration1.setDeviceType(deviceRegistrationRequestVO.getDeviceType());
					  deviceRegistration1.setGuidedAccessEnabled(deviceRegistrationRequestVO.getGuidedAccessEnabled());
					  deviceRegistration1.setIpAddress(deviceRegistrationRequestVO.getIpAddress());
					  deviceRegistration1.setLastSyncTime(new Date());
					  deviceRegistration1.setOperationgSystemVersion(deviceRegistrationRequestVO.getOperationgSystemVersion());
					  deviceRegistration1.setUserId(deviceRegistrationRequestVO.getUserId());
					  deviceRegistration1.setWirelessNetwork(deviceRegistrationRequestVO.getWirelessNetwork());
					  deviceRegistration1.setCreatedDate(new Date());
					  deviceRegistration1.setStatus(true);
					  deviceRegistration1.setIsDeleted(false);
					  deviceRegistration1.setDeviceTimestamp(deviceRegistrationRequestVO.getDeviceTimestamp());
					  deviceRegistration1.setSubNetMask(deviceRegistrationRequestVO.getSubNetMask());
					  deviceRegistration1.setDeviceUUID(deviceRegistrationRequestVO.getDeviceUUID());
					  sessionFactory.getCurrentSession().save(deviceRegistration1);
					  result="success";
				}
					DeviceRegistrationAll deviceRegistrationsAlls=new DeviceRegistrationAll();
					deviceRegistrationsAlls.setDeviceToken(deviceRegistrationRequestVO.getDeviceToken());
					deviceRegistrationsAlls.setDeteactedResolution(deviceRegistrationRequestVO.getDeteactedResolution());
					deviceRegistrationsAlls.setDeviceName(deviceRegistrationRequestVO.getDeviceName());
					deviceRegistrationsAlls.setDeviceStorage(deviceRegistrationRequestVO.getDeviceStorage());
					deviceRegistrationsAlls.setDeviceType(deviceRegistrationRequestVO.getDeviceType());
					deviceRegistrationsAlls.setGuidedAccessEnabled(deviceRegistrationRequestVO.getGuidedAccessEnabled());
					deviceRegistrationsAlls.setIpAddress(deviceRegistrationRequestVO.getIpAddress());
					deviceRegistrationsAlls.setOperationgSystemVersion(deviceRegistrationRequestVO.getOperationgSystemVersion());
					deviceRegistrationsAlls.setUserId(deviceRegistrationRequestVO.getUserId());
					deviceRegistrationsAlls.setWirelessNetwork(deviceRegistrationRequestVO.getWirelessNetwork());
					deviceRegistrationsAlls.setSubNetMask(deviceRegistrationRequestVO.getSubNetMask());
					deviceRegistrationsAlls.setDeviceUUID(deviceRegistrationRequestVO.getDeviceUUID());
					deviceRegistrationsAlls.setCreatedDate(new Date());
					deviceRegistrationsAlls.setStatus(true);
					deviceRegistrationsAlls.setIsDeleted(false);
					sessionFactory.getCurrentSession().save(deviceRegistrationsAlls);
					sessionFactory.getCurrentSession().getTransaction().commit();
		 }
	 } catch (Exception e) {
	  e.printStackTrace();
	  sessionFactory.getCurrentSession().getTransaction().rollback();
	 }
	 return result;
	}

@Override
public BoothAdminLogin fetchSubscriptionsMasterList(SubscriptionRequestVO subscription) {

			sessionFactory.getCurrentSession().beginTransaction();
			
			Criteria criteriaAdminBoothLogin = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteriaAdminBoothLogin.add(Restrictions.eq("userId",subscription.getUserId()));
			criteriaAdminBoothLogin.add(Restrictions.eq("status",true));
			criteriaAdminBoothLogin.add(Restrictions.eq("isDeleted",false));
			return  (BoothAdminLogin) criteriaAdminBoothLogin.uniqueResult();
			
}

@Override
public BaseResponseVO saveTranscationIOSDetails(IOSTranscationsDetailsRequestVO iosTrxDetailsBasedUserId,TransactionMaster trxMaster) {
	BaseResponseVO result=new BaseResponseVO();
	try {
		sessionFactory.getCurrentSession().beginTransaction();
		
		sessionFactory.getCurrentSession().save(trxMaster);
		
		Integer trx_id=(Integer) sessionFactory.getCurrentSession().getIdentifier(trxMaster);
		
		
		TransactionMappingAdmin mappingAdmin =new TransactionMappingAdmin();
		mappingAdmin.setTransactionMasterId(trx_id);
		mappingAdmin.setUserId(iosTrxDetailsBasedUserId.getUserId());
		
		Date today = Calendar.getInstance().getTime();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    String stringCurrentDate = formatter.format(today);
	    Date currentDate = formatter.parse(stringCurrentDate);
		mappingAdmin.setDate(currentDate);
		sessionFactory.getCurrentSession().save(mappingAdmin);
		
		BoothAdminLogin adminLogin=(BoothAdminLogin) sessionFactory.getCurrentSession().get(BoothAdminLogin.class, iosTrxDetailsBasedUserId.getUserId());
		if(adminLogin!=null){
		Criteria crt=sessionFactory.getCurrentSession().createCriteria(SubscriptionMaster.class).add(Restrictions.eq("subPrice",iosTrxDetailsBasedUserId.getAmount()));
		crt.add(Restrictions.eq("status", true));
		crt.add(Restrictions.eq("isDeleted", false));
		SubscriptionMaster master=(SubscriptionMaster) crt.uniqueResult();	
				if (master != null) {
					adminLogin.setSubId(master.getSubName());
					adminLogin.setSubUpdatedDate(new Date());
					adminLogin.setSubEndDate(DateUtils.addDays(new Date(), Integer.parseInt(master.getSubValidaityDayPeriod())));
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
	Adminboothevent adminboothevent=null;
	try {
		 sessionFactory.getCurrentSession().beginTransaction();
		 Criteria cUser = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
		 cUser.add(Restrictions.eq("EId", eventId));
		 Adminboothevent  adminboothevents = (Adminboothevent) cUser.uniqueResult();
		 
		 if(adminboothevents !=null){
		  Criteria crt = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
		  if(!("default").equals(adminboothevents.getEventType())){
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
	RestartVO restartVOs=new RestartVO();
	try {
		sessionFactory.getCurrentSession().beginTransaction();
		Criteria criteria= sessionFactory.getCurrentSession().createCriteria(DeviceRegistration.class);
		criteria.add(Restrictions.eq("userId", Integer.parseInt(restartVO.getUserId())));
		criteria.add(Restrictions.eq("status", true));
		criteria.add(Restrictions.eq("isDeleted", false));
		criteria.add(Restrictions.eq("deviceType", "Camera device"));
		DeviceRegistration deviceRegistration = (DeviceRegistration) criteria.uniqueResult() ;
		if(deviceRegistration!=null){
				Criteria criteriaTouch= sessionFactory.getCurrentSession().createCriteria(DeviceRegistration.class);
				criteriaTouch.add(Restrictions.eq("userId", Integer.parseInt(restartVO.getUserId())));
				criteriaTouch.add(Restrictions.eq("status", true));
				criteriaTouch.add(Restrictions.eq("isDeleted", false));
				criteriaTouch.add(Restrictions.eq("deviceType", "Guest Touchscreen"));
				DeviceRegistration deviceRegistrationTouch = (DeviceRegistration) criteriaTouch.uniqueResult() ;
				if(deviceRegistrationTouch !=null){
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
	Fovbyuser fovbyuser=null;
	try {
		sessionFactory.getCurrentSession().beginTransaction();
		Criteria criteria= sessionFactory.getCurrentSession().createCriteria(Fovbyuser.class);
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
	List<DeviceRegistration> deviceRegistration=null;
	try {
		sessionFactory.getCurrentSession().beginTransaction();
		Criteria criteria= sessionFactory.getCurrentSession().createCriteria(DeviceRegistration.class);
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

@Override
public String logOutService(DeviceRegistrationRequestVO deviceRegistrationRequestVO) {
	String result="";
	try {
		sessionFactory.getCurrentSession().beginTransaction();
		Criteria criteria= sessionFactory.getCurrentSession().createCriteria(DeviceRegistration.class);
		criteria.add(Restrictions.eq("userId", deviceRegistrationRequestVO.getUserId()));
		criteria.add(Restrictions.eq("deviceType", deviceRegistrationRequestVO.getDeviceType()));
		criteria.add(Restrictions.eq("status", true));
		criteria.add(Restrictions.eq("isDeleted", false));
		DeviceRegistration deviceRegistration = (DeviceRegistration) criteria.uniqueResult(); 
		if(deviceRegistration !=null){
			deviceRegistration.setDeviceToken("");
			sessionFactory.getCurrentSession().update(deviceRegistration);
		}
		result="success";
		sessionFactory.getCurrentSession().getTransaction().commit();
		
	} catch (Exception e) {
		e.printStackTrace();
		sessionFactory.getCurrentSession().getTransaction().rollback();
	}
	return result;
}

@Override
public void updateStatusCount(String emailId, Integer eventId) {
	try {
		sessionFactory.getCurrentSession().beginTransaction();
		Criteria criteria= sessionFactory.getCurrentSession().createCriteria(StatusCount.class);
		criteria.add(Restrictions.eq("emailId", emailId));
		criteria.add(Restrictions.eq("eventId", eventId));
		StatusCount statusCount = (StatusCount) criteria.uniqueResult();
		if(statusCount !=null){
			Integer repetedGuest=statusCount.getRepetedGuestCount();
			Integer mailSent=statusCount.getMailSentCount();
			statusCount.setMailSentCount(mailSent+1);
			statusCount.setRepetedGuestCount(repetedGuest==0?(repetedGuest+1):repetedGuest);
			sessionFactory.getCurrentSession().update(statusCount);
		}else{
			StatusCount statusCount2=new StatusCount();
			statusCount2.setEmailId(emailId);
			statusCount2.setEventId(eventId);
			statusCount2.setRepetedGuestCount(0);
			statusCount2.setMailSentCount(1);
			sessionFactory.getCurrentSession().save(statusCount2);
		}
		sessionFactory.getCurrentSession().getTransaction().commit();
	} catch (Exception e) {
		sessionFactory.getCurrentSession().getTransaction().rollback();
	}
	
}

@Override
public LoginBaseResponseVO registrationLoginGoogle(BoothAdminRegistrationRequestVO adminBoothRegistrationRequestVO) {

	BoothAdminLogin adminBoothLoginEntity=null;
	LoginBaseResponseVO responseVo =new  LoginBaseResponseVO();
	try {
		sessionFactory.getCurrentSession().beginTransaction();
		
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
		Criterion c1 = Restrictions.eq("emailId", adminBoothRegistrationRequestVO.getEmailId());
		Criterion c2 = Restrictions.eq("googleId", adminBoothRegistrationRequestVO.getGoogleId());
		LogicalExpression orExp = Restrictions.or(c1, c2);
		criteria.add(orExp);
		adminBoothLoginEntity=(BoothAdminLogin) criteria.uniqueResult();
		if(adminBoothLoginEntity !=null){
			adminBoothLoginEntity.setGoogleId(adminBoothRegistrationRequestVO.getGoogleId());
			sessionFactory.getCurrentSession().update(adminBoothLoginEntity);
			
			Criteria criteriaAdminBoothLogin = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteriaAdminBoothLogin.add(Restrictions.eq("userId",adminBoothLoginEntity.getUserId()));
			criteriaAdminBoothLogin.add(Restrictions.eq("status",true));
			criteriaAdminBoothLogin.add(Restrictions.eq("isDeleted",false));
			adminBoothLoginEntity = (BoothAdminLogin) criteriaAdminBoothLogin.uniqueResult();
			BoothAdminLoginResponseVO login=new BoothAdminLoginResponseVO();
			if(adminBoothLoginEntity!=null){
				login.setContactNumber(adminBoothLoginEntity.getContactNumber());
				login.setCreatedDate(DateUtils.timeStampConvertIntoStringDateFormat(adminBoothLoginEntity.getCreatedDate()));
				login.setEmailId(adminBoothLoginEntity.getEmailId());
				login.setStatus(adminBoothLoginEntity.getStatus());
				login.setSubId(adminBoothLoginEntity.getSubId());
				login.setLocation(adminBoothLoginEntity.getLocation());
				login.setStatus(adminBoothLoginEntity.getStatus());
				if(adminBoothLoginEntity.getIsDeleted()!=null){
					login.setIsDeleted(adminBoothLoginEntity.getIsDeleted());
				}
				if(adminBoothLoginEntity.getSubUpdatedDate()!=null){
					login.setSubUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormat(adminBoothLoginEntity.getSubUpdatedDate()));
				}
				if(adminBoothLoginEntity.getUpdatedDate()!=null){
					login.setUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormat(adminBoothLoginEntity.getUpdatedDate()));
				}
				login.setUserId(adminBoothLoginEntity.getUserId());
				login.setUsername(adminBoothLoginEntity.getUsername());
				login.setUserRole(adminBoothLoginEntity.getUserRole());
				login.setHexValueDefault(adminBoothLoginEntity.getHexValueDefault());
				login.setRgbValueDefault(adminBoothLoginEntity.getRgbValueDefault());
				login.setRgbaValueDefault(adminBoothLoginEntity.getRgbaValueDefault());
				if(adminBoothLoginEntity.getCurrentImageId()!=null){
					login.setCurrentImageId(adminBoothLoginEntity.getCurrentImageId());
				}
				login.setIsDefaultRgb(adminBoothLoginEntity.getIsDefaultRgb());
				login.setHexValueManual(adminBoothLoginEntity.getHexValueManual());
				login.setRgbValueManual(adminBoothLoginEntity.getRgbValueManual());
				login.setRgbaValueManual(adminBoothLoginEntity.getRgbaValueManual());
			}
			
			if(adminBoothLoginEntity!=null){
				Object response1=serviceRequestTemplateZoho.zohoRequestToServer("https://subscriptions.zoho.com/api/v1/plans?filter_by=PlanStatus.All");
				List<SubscriptionMasterResponseVO> subscriptionMasterResponseVoList=new ArrayList<>();
				SubscriptionMasterResponseVO responseVO=new SubscriptionMasterResponseVO();
				responseVO.setCreatedDate(null);
		     	responseVO.setSubId("1");
		     	responseVO.setSubName("FREE");
		     	responseVO.setSubPrice("0");
		     	responseVO.setSubValidaityDayPeriod("Life Time");
		     	responseVO.setResponseCode("1");
		    	responseVO.setResponseDescription("Success");
		    	subscriptionMasterResponseVoList.add(responseVO);
				 try {
					 SubscriptionMasterResponseVO subscriptionMasterResponseVO=null;
				     JSONArray responseResult = new JSONArray("["+response1.toString()+"]");
				     for (int i=0; i<responseResult.length();i++){
				         JSONObject responseData = (JSONObject) responseResult.get(i);
				         JSONArray data = new JSONArray(responseData.getString("plans"));
				         for(int j=0; j<data.length(); j++) {
				        	 JSONObject subscriptionData = (JSONObject) data.get(j);
				         System.out.println("plancode::"+subscriptionData.getString("plan_code"));
				         String subId=subscriptionData.getString("plan_code");
				         String name=subscriptionData.getString("name");
				         String description=subscriptionData.getString("description");
				         String created_time=subscriptionData.getString("created_time");
				         String recurring_price=subscriptionData.getString("recurring_price");
				         String interval_unit=subscriptionData.getString("interval_unit");
				   
				         
				         subscriptionMasterResponseVO=new SubscriptionMasterResponseVO();
			
				         subscriptionMasterResponseVO.setCreatedDate(created_time);
				         subscriptionMasterResponseVO.setSubId(subId);
				         subscriptionMasterResponseVO.setSubName(name);
				         subscriptionMasterResponseVO.setSubPrice(recurring_price);
				         subscriptionMasterResponseVO.setSubValidaityDayPeriod(interval_unit);
						
						subscriptionMasterResponseVoList.add(subscriptionMasterResponseVO);
				     }
				 }
				 }catch(Exception e) {
					 e.printStackTrace();
				 }
			/*Criteria crt=sessionFactory.getCurrentSession().createCriteria(SubscriptionMaster.class).add(Restrictions.eq("subId", adminBoothLoginEntity.getSubId()));
			crt.add(Restrictions.eq("status", true));
			crt.add(Restrictions.eq("isDeleted", false));
			List<SubscriptionMaster> subscriptionMasterList =crt.list();
			List<SubscriptionMasterResponseVO> subscriptionMasterResponseVoList=new ArrayList<>();
			SubscriptionMasterResponseVO vo=null;
			for (SubscriptionMaster s : subscriptionMasterList) {
				vo=new SubscriptionMasterResponseVO();
				if(s.getStatus()!=null){
				vo.setStatus(s.getStatus());
				}
				if(s.getIsDeleted()!=null){
				vo.setIsDeleted(s.getIsDeleted());
				}
				vo.setCreatedDate(DateUtils.timeStampConvertIntoStringDateFormat(s.getCreatedDate()));
				vo.setSubId(s.getSubId());
				vo.setSubName(s.getSubName());
				vo.setSubPrice(s.getSubPrice());
				vo.setSubValidaityDayPeriod(s.getSubValidaityDayPeriod());
				if(s.getCreatedUserId()!=null){
				vo.setCreatedUserId(s.getCreatedUserId());
				}
				
				if(s.getUpdatedByUserId()!=null){
					vo.setUpdatedByUserId(s.getUpdatedByUserId());
					}
				
				if(s.getUpdatedDate()!=null){
					vo.setUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormat(s.getUpdatedDate()));
					}
				subscriptionMasterResponseVoList.add(vo);
			}*/
			//end 11-11-2016
			Criteria criteriaDeviceRegistration =sessionFactory.getCurrentSession().createCriteria(DeviceRegistration.class);
			criteriaDeviceRegistration.add(Restrictions.eq("status", true));
			criteriaDeviceRegistration.add(Restrictions.eq("isDeleted", false));
			criteriaDeviceRegistration.add(Restrictions.eq("userId", adminBoothLoginEntity.getUserId()));
			List<DeviceRegistration> deviceRegistration=criteriaDeviceRegistration.list();
			//start 11-11-2016
			List<DeviceRegistrationResponseVO> deviceResponseVOList=new ArrayList<>();
			DeviceRegistrationResponseVO deviceVO=null;
			for (DeviceRegistration d : deviceRegistration) {
				deviceVO=new DeviceRegistrationResponseVO();
				if(d.getStatus()!=null){
					deviceVO.setStatus(d.getStatus());
				}
				
				if(d.getIsDeleted()!=null){
					deviceVO.setIsDeleted(d.getIsDeleted());
				}
				if(d.getCreatedDate()!=null){
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
				if(d.getLastSyncTime()!=null){
					deviceVO.setLastSyncTime(DateUtils.timeStampConvertIntoStringDateFormat(d.getLastSyncTime()));
				}
				deviceVO.setOperationgSystemVersion(d.getOperationgSystemVersion());
				deviceVO.setUserId(d.getUserId());
				deviceVO.setWirelessNetwork(d.getWirelessNetwork());
				deviceVO.setDeviceUUID(d.getDeviceUUID());
				deviceVO.setSubNetMask(d.getSubNetMask());
				deviceResponseVOList.add(deviceVO);
			}
			
			//end 11-11-2016
			responseVo.setResponseCode("1");
			responseVo.setResponseDescription("Success");
			responseVo.setBoothAdminLoginResponse(login);
			responseVo.setSubscriptionMasterList(subscriptionMasterResponseVoList);
			responseVo.setDeviceRegistrationResponse(deviceResponseVOList);
			}
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
			userMaster2.setIsSubscriptionByAdmin(false);
			userMaster2.setSubId(ServerConstants.SUBSCRIPTION_NORMAL);
			userMaster2.setUsername(adminBoothRegistrationRequestVO.getUserName());
			userMaster2.setGoogleId(adminBoothRegistrationRequestVO.getGoogleId());
			userMaster2.setEmailId(adminBoothRegistrationRequestVO.getEmailId());
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
		Criteria criteriaAdminBoothLogin = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
		criteriaAdminBoothLogin.add(Restrictions.eq("userId",userId));
		criteriaAdminBoothLogin.add(Restrictions.eq("status",true));
		criteriaAdminBoothLogin.add(Restrictions.eq("isDeleted",false));
		adminBoothLoginEntity = (BoothAdminLogin) criteriaAdminBoothLogin.uniqueResult();
		BoothAdminLoginResponseVO login=new BoothAdminLoginResponseVO();
		if(adminBoothLoginEntity!=null){
			login.setContactNumber(adminBoothLoginEntity.getContactNumber());
			login.setCreatedDate(DateUtils.timeStampConvertIntoStringDateFormat(adminBoothLoginEntity.getCreatedDate()));
			login.setEmailId(adminBoothLoginEntity.getEmailId());
			login.setStatus(adminBoothLoginEntity.getStatus());
			login.setSubId(adminBoothLoginEntity.getSubId());
			login.setLocation(adminBoothLoginEntity.getLocation());
			login.setStatus(adminBoothLoginEntity.getStatus());
			if(adminBoothLoginEntity.getIsDeleted()!=null){
				login.setIsDeleted(adminBoothLoginEntity.getIsDeleted());
			}
			if(adminBoothLoginEntity.getSubUpdatedDate()!=null){
				login.setSubUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormat(adminBoothLoginEntity.getSubUpdatedDate()));
			}
			if(adminBoothLoginEntity.getUpdatedDate()!=null){
				login.setUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormat(adminBoothLoginEntity.getUpdatedDate()));
			}
			login.setUserId(adminBoothLoginEntity.getUserId());
			login.setUsername(adminBoothLoginEntity.getUsername());
			login.setUserRole(adminBoothLoginEntity.getUserRole());
			login.setHexValueDefault(adminBoothLoginEntity.getHexValueDefault());
			login.setRgbValueDefault(adminBoothLoginEntity.getRgbValueDefault());
			login.setRgbaValueDefault(adminBoothLoginEntity.getRgbaValueDefault());
			if(adminBoothLoginEntity.getCurrentImageId()!=null){
				login.setCurrentImageId(adminBoothLoginEntity.getCurrentImageId());
			}
			login.setIsDefaultRgb(adminBoothLoginEntity.getIsDefaultRgb());
			login.setHexValueManual(adminBoothLoginEntity.getHexValueManual());
			login.setRgbValueManual(adminBoothLoginEntity.getRgbValueManual());
			login.setRgbaValueManual(adminBoothLoginEntity.getRgbaValueManual());
		}
		
		if(adminBoothLoginEntity!=null){
			Object response1=serviceRequestTemplateZoho.zohoRequestToServer("https://subscriptions.zoho.com/api/v1/plans?filter_by=PlanStatus.All");
			List<SubscriptionMasterResponseVO> subscriptionMasterResponseVoList=new ArrayList<>();
			SubscriptionMasterResponseVO responseVO=new SubscriptionMasterResponseVO();
			responseVO.setCreatedDate(null);
	     	responseVO.setSubId("1");
	     	responseVO.setSubName("FREE");
	     	responseVO.setSubPrice("0");
	     	responseVO.setSubValidaityDayPeriod("Life Time");
	     	responseVO.setResponseCode("1");
	    	responseVO.setResponseDescription("Success");
	    	subscriptionMasterResponseVoList.add(responseVO);
			 try {
				 SubscriptionMasterResponseVO subscriptionMasterResponseVO=null;
			     JSONArray responseResult = new JSONArray("["+response1.toString()+"]");
			     for (int i=0; i<responseResult.length();i++){
			         JSONObject responseData = (JSONObject) responseResult.get(i);
			         JSONArray data = new JSONArray(responseData.getString("plans"));
			         for(int j=0; j<data.length(); j++) {
			        	 JSONObject subscriptionData = (JSONObject) data.get(j);
			         System.out.println("plancode::"+responseData.getString("plan_code"));
			         String subId=subscriptionData.getString("plan_code");
			         String name=subscriptionData.getString("name");
			         String description=subscriptionData.getString("description");
			         String created_time=subscriptionData.getString("created_time");
			         String recurring_price=subscriptionData.getString("recurring_price");
			         String interval_unit=subscriptionData.getString("interval_unit");
			   
			         
			         
			         subscriptionMasterResponseVO=new SubscriptionMasterResponseVO();
		
			         subscriptionMasterResponseVO.setCreatedDate(created_time);
			         subscriptionMasterResponseVO.setSubId(subId);
			         subscriptionMasterResponseVO.setSubName(name);
			         subscriptionMasterResponseVO.setSubPrice(recurring_price);
			         subscriptionMasterResponseVO.setSubValidaityDayPeriod(interval_unit);
					
					subscriptionMasterResponseVoList.add(subscriptionMasterResponseVO);
			     }
			     }
			 }catch(Exception e) {
				 e.printStackTrace();
			 }
			
	/*	Criteria crt=sessionFactory.getCurrentSession().createCriteria(SubscriptionMaster.class).add(Restrictions.eq("subId", adminBoothLoginEntity.getSubId()));
		crt.add(Restrictions.eq("status", true));
		crt.add(Restrictions.eq("isDeleted", false));
		List<SubscriptionMaster> subscriptionMasterList =crt.list();
		List<SubscriptionMasterResponseVO> subscriptionMasterResponseVoList=new ArrayList<>();
		SubscriptionMasterResponseVO vo=null;
		for (SubscriptionMaster s : subscriptionMasterList) {
			vo=new SubscriptionMasterResponseVO();
			if(s.getStatus()!=null){
			vo.setStatus(s.getStatus());
			}
			if(s.getIsDeleted()!=null){
			vo.setIsDeleted(s.getIsDeleted());
			}
			vo.setCreatedDate(DateUtils.timeStampConvertIntoStringDateFormat(s.getCreatedDate()));
			vo.setSubId(s.getSubId());
			vo.setSubName(s.getSubName());
			vo.setSubPrice(s.getSubPrice());
			vo.setSubValidaityDayPeriod(s.getSubValidaityDayPeriod());
			if(s.getCreatedUserId()!=null){
			vo.setCreatedUserId(s.getCreatedUserId());
			}
			
			if(s.getUpdatedByUserId()!=null){
				vo.setUpdatedByUserId(s.getUpdatedByUserId());
				}
			
			if(s.getUpdatedDate()!=null){
				vo.setUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormat(s.getUpdatedDate()));
				}
			subscriptionMasterResponseVoList.add(vo);
		}*/
		//end 11-11-2016
		Criteria criteriaDeviceRegistration =sessionFactory.getCurrentSession().createCriteria(DeviceRegistration.class);
		criteriaDeviceRegistration.add(Restrictions.eq("status", true));
		criteriaDeviceRegistration.add(Restrictions.eq("isDeleted", false));
		criteriaDeviceRegistration.add(Restrictions.eq("userId", adminBoothLoginEntity.getUserId()));
		List<DeviceRegistration> deviceRegistration=criteriaDeviceRegistration.list();
		//start 11-11-2016
		List<DeviceRegistrationResponseVO> deviceResponseVOList=new ArrayList<>();
		DeviceRegistrationResponseVO deviceVO=null;
		for (DeviceRegistration d : deviceRegistration) {
			deviceVO=new DeviceRegistrationResponseVO();
			if(d.getStatus()!=null){
				deviceVO.setStatus(d.getStatus());
			}
			
			if(d.getIsDeleted()!=null){
				deviceVO.setIsDeleted(d.getIsDeleted());
			}
			if(d.getCreatedDate()!=null){
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
			if(d.getLastSyncTime()!=null){
				deviceVO.setLastSyncTime(DateUtils.timeStampConvertIntoStringDateFormat(d.getLastSyncTime()));
			}
			deviceVO.setOperationgSystemVersion(d.getOperationgSystemVersion());
			deviceVO.setUserId(d.getUserId());
			deviceVO.setWirelessNetwork(d.getWirelessNetwork());
			deviceVO.setDeviceUUID(d.getDeviceUUID());
			deviceVO.setSubNetMask(d.getSubNetMask());
			deviceResponseVOList.add(deviceVO);
		}
		
		//end 11-11-2016
		responseVo.setResponseCode("1");
		responseVo.setResponseDescription("Success");
		responseVo.setBoothAdminLoginResponse(login);
		responseVo.setSubscriptionMasterList(subscriptionMasterResponseVoList);
		responseVo.setDeviceRegistrationResponse(deviceResponseVOList);
		}
		responseVo.setResponseCode("1");
		responseVo.setResponseDescription("Success");
		}
		sessionFactory.getCurrentSession().getTransaction().commit();
	} catch (Exception e) {
		e.printStackTrace();
		sessionFactory.getCurrentSession().getTransaction().rollback();
	}
	return responseVo;

}

}

