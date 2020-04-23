package com.iamuse.admin.serviceimpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.iamuse.admin.VO.AdminPictureVO;
import com.iamuse.admin.VO.BoothAdminLoginResponseVO;
import com.iamuse.admin.VO.EventVO;
import com.iamuse.admin.VO.SignInVO;
import com.iamuse.admin.dao.BoothAdminDao;
import com.iamuse.admin.dao.SuperadminDao;
import com.iamuse.admin.entity.Adminboothevent;
import com.iamuse.admin.entity.BoothAdminLogin;
import com.iamuse.admin.entity.DeviceRegistration;
import com.iamuse.admin.entity.SubscriptionMaster;
import com.iamuse.admin.service.BoothAdminService;
import com.iamuse.admin.service.LoginService;
import com.iamuse.admin.service.SuperadminService;
import com.iamuse.admin.util.IAmuseadminUtil;
import com.iamuse.admin.util.DateUtils;

@Service
@Transactional
public class SuperadminServiceImpl implements SuperadminService {
	
		@Autowired SuperadminDao superadminDao;
		@Autowired BoothAdminDao boothAdminDao;
		@Autowired SessionFactory sessionFactory;

		private static final Logger log = Logger.getLogger(SuperadminService.class);
		
		@Override
		public Adminboothevent getCurrentEvent(Integer eid) {
			return superadminDao.getCurrentEvent(eid);
		}
		
		@Override
		public AdminPictureVO uploadBackgroundImage(AdminPictureVO adminPictureVO,MultipartFile file, String rootPath) {
			return superadminDao.uploadBackgroundImage(adminPictureVO,file,rootPath);
		}
		
		@Override
		@Transactional
		public List<BoothAdminLoginResponseVO> getBoothAdminLoginList() {
			List<BoothAdminLoginResponseVO>  responseList=new ArrayList<>();
			
			List<BoothAdminLogin> login=superadminDao.getBoothAdminLoginList();
			BoothAdminLoginResponseVO response;
			for (BoothAdminLogin boothAdminLogin : login) {
				response=new BoothAdminLoginResponseVO();
				response.setContactNumber(DateUtils.checkNull(boothAdminLogin.getContactNumber()));
				response.setCreatedDate(DateUtils.timeStampConvertIntoStringDateFormatYYYYMMDD(boothAdminLogin.getCreatedDate()));
				response.setEmailId(DateUtils.checkNull(boothAdminLogin.getEmailId()));
				response.setStatus(boothAdminLogin.getStatus());
				response.setSubId(boothAdminLogin.getSubId());
				if(boothAdminLogin.getSubUpdatedDate()!=null){
				response.setSubUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormatYYYYMMDD(boothAdminLogin.getSubUpdatedDate()));
				}
				if(boothAdminLogin.getUpdatedDate()!=null){
				response.setUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormatYYYYMMDD(boothAdminLogin.getUpdatedDate()));
				}
				response.setUserId(boothAdminLogin.getUserId());
				response.setUsername(boothAdminLogin.getUsername());
				response.setUserRole(boothAdminLogin.getUserRole());
				responseList.add(response);
			}
			return responseList;
		}
		
		@Override
		@Transactional
		public List<EventVO> getEventListForSuperAdmin() {
			return superadminDao.getEventListForSuperAdmin();
		}
		
		@Override
		public boolean saveSubscriptionMaster(SubscriptionMaster subscriptionMaster, Integer userId) {
			return superadminDao.saveSubscriptionMaster(subscriptionMaster,userId);
		}
		
		@Override
		public boolean updateSubscriptionMaster(SubscriptionMaster subscriptionMaster, Integer userId) {
			return superadminDao.updateSubscriptionMaster(subscriptionMaster,userId);
		}
		
		@Override
		public boolean updateAdminSubscription(BoothAdminLogin boothAdminLogin, Integer userId) {
			return superadminDao.updateAdminSubscription(boothAdminLogin,userId);
		}
		
		@Override
		public boolean deleteSubscriptionMasterById(SubscriptionMaster masterValue,Integer userId) {
			return superadminDao.deleteSubscriptionMasterById(masterValue,userId);
		}

		@Override
		public boolean activeSubscriptionMaster(SubscriptionMaster subscriptionMaster, Integer userId) {
			return superadminDao.activeSubscriptionMaster(subscriptionMaster,userId);
		}
		
		@Override
		public boolean deactiveSubscriptionMaster(SubscriptionMaster subscriptionMaster, Integer userId) {
			return superadminDao.deactiveSubscriptionMaster(subscriptionMaster,userId);
		}
		
		@Override
		public EventVO getEventValueByEventId(Integer eventId) {
			 Adminboothevent  event=superadminDao.getEventValueByEventId(eventId);
			 EventVO vo =new EventVO();
			 if(event.getCreatedBy()!=null){	 
			 vo.setCreatedBy(event.getCreatedBy());
			 }
			 if(event.getCreatedDate()!=null){
			 vo.setCreatedDate(event.getCreatedDate());
			 }
			 vo.setEId(event.getEId());
			 if(event.getEventEnd()!=null){
			 vo.setEventEnd(event.getEventEnd());
			 }
			 vo.setEventHostMailerId(IAmuseadminUtil.checkNull(event.getEventHostMailerId()));
			 vo.setEventLocation(IAmuseadminUtil.checkNull(event.getEventLocation()));
			 vo.setEventName(IAmuseadminUtil.checkNull(event.getEventName()));
			 if(event.getEventStart()!=null){
			 vo.setEventStart(DateUtils.timeStampConvertIntoStringDateFormatYYYYMMDD(event.getEventStart()));
			 }
			 vo.setSponsorName(IAmuseadminUtil.checkNull(event.getSponsorName()));
			 if(event.getUpdatedBy()!=null){
			 vo.setUpdatedBy(event.getUpdatedBy());
			 }
			 if(event.getUpdatedDate()!=null){
			 vo.setUpdatedDate(event.getUpdatedDate());
			 }
			return vo;
		}
		
		
		@Override
		public boolean updateAdminBoothEvent(EventVO eventVO, Integer userId) {
			Adminboothevent adminBoothEvent=superadminDao.getCurrentEvent(eventVO.getEId());
			adminBoothEvent.setUpdatedBy(userId);
			adminBoothEvent.setUpdatedDate(new Date());
			if(eventVO.getEventName()!=null){
			adminBoothEvent.setEventName(eventVO.getEventName());
			}
			
			if(eventVO.getSponsorName()!=null){
				adminBoothEvent.setSponsorName(eventVO.getSponsorName());
				}
			if(eventVO.getEventStart()!=null){
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		        String dateInString = eventVO.getEventStart();
		        Date eventStartDate=null;
		        try {

		            eventStartDate = formatter.parse(dateInString);
		        } catch (ParseException e) {
		        	log.info("SuperadminServiceImpl Method : updateAdminBoothEvent ");
					log.error("Error updateAdminBoothEvent",e);
		        }
				adminBoothEvent.setEventStart(eventStartDate);
				}
			return superadminDao.updateAdminBoothEvent(adminBoothEvent);
		}
		
		@Override
		public boolean deleteAdminBoothEventByEventId(Integer id) {
			return superadminDao.deleteAdminBoothEventByEventId(id);
		}
		
		@Override
		public EventVO saveCreateEventBySuperAdmin(EventVO eventVO, Integer userId) {
			return superadminDao.saveCreateEventBySuperAdmin(eventVO, userId);
		}
		
		@Override
		public List<SubscriptionMaster> getSubscriptionListSuperAdminPage() {
			List<SubscriptionMaster> subscriptionMasters=null;
			try {
				sessionFactory.getCurrentSession().beginTransaction();
				subscriptionMasters=superadminDao.getSubscriptionListSuperAdminPage();
				sessionFactory.getCurrentSession().getTransaction().commit();
			} catch (Exception e) {
				log.info("SuperadminServiceImpl Method : getSubscriptionListSuperAdminPage ");
				log.error("Error getSubscriptionListSuperAdminPage",e);
				sessionFactory.getCurrentSession().getTransaction().rollback();
			}
			return subscriptionMasters;
		}
		
		@Override
		public Integer getSubscriptionCountForBarChart(String startDate,String endDate) {
			return superadminDao.getSubscriptionCountForBarChart(startDate,endDate);
		}
		
		@Override
		public Integer getSubscriptionCountForBarChartDefaultSubscription(
				String janStartDate, String janEndDate,Integer subId) {
			return superadminDao.getSubscriptionCountForBarChartDefaultSubscription(janStartDate,janEndDate,subId);
		}
		
		@Override
		public List<BoothAdminLoginResponseVO> getBoothAdminLoginListSuperAdmin() {
			List<BoothAdminLoginResponseVO>  responseList=new ArrayList<>();
			List<BoothAdminLogin> login=superadminDao.getBoothAdminLoginList();
			BoothAdminLoginResponseVO response;
			for (BoothAdminLogin boothAdminLogin : login) {
				response=new BoothAdminLoginResponseVO();
				//SubscriptionMaster subscriptionMaster=superadminDao.getSubscriptionListById(boothAdminLogin.getSubId());
				response.setContactNumber(DateUtils.checkNull(boothAdminLogin.getContactNumber()));
				response.setCreatedDate(DateUtils.timeStampConvertIntoStringDateFormatYYYYMMDD(new Date()));
				response.setEmailId(DateUtils.checkNull(boothAdminLogin.getEmailId()));
				response.setIsDeleted(boothAdminLogin.getIsDeleted());
				response.setLocation(DateUtils.checkNull(boothAdminLogin.getLocation()));
				response.setStatus(boothAdminLogin.getStatus());
				response.setSubId(boothAdminLogin.getSubId());
				if(boothAdminLogin.getSubEndDate()!=null){
					response.setSubEndDateFormat(DateUtils.timeStampConvertIntoStringDateFormatYYYYMMDD(boothAdminLogin.getSubEndDate()));
				}
				
				if(boothAdminLogin.getUpdatedDate()!=null){
				response.setUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormatYYYYMMDD(boothAdminLogin.getUpdatedDate()));
				}
				response.setUserId(boothAdminLogin.getUserId());
				response.setUsername(boothAdminLogin.getUsername());
				response.setUserRole(boothAdminLogin.getUserRole());
				responseList.add(response);
			}
			return responseList;
		}
		
		@Override
		public AdminPictureVO uploadBackgroundImage(AdminPictureVO adminPictureVO,MultipartFile[] files, String rootPath) {
				return superadminDao.uploadBackgroundImage(adminPictureVO,files,rootPath);
		}
		
		@Override
		public List<AdminPictureVO> getPicList(Integer eid, Integer userId) {
			return superadminDao.getPicList(eid,userId);
		}
		
		@Override
		public AdminPictureVO getImageConfigure(Integer picId) {
			return superadminDao.getImageConfigure(picId);
		}
		
		@Override
		public String saveCoordinatesOfImg(AdminPictureVO adminPictureVO,MultipartFile files, MultipartFile files1, String rootPath) {
			return superadminDao.saveCoordinatesOfImg(adminPictureVO,files,files1,rootPath);
		}

		@Override
		public List<BoothAdminLoginResponseVO> getBoothAdminLoginListSuperAdminWithPagination(
				int pageid, int total) {
			

			List<BoothAdminLoginResponseVO>  responseList=new ArrayList<>();
			
			List<BoothAdminLogin> login=superadminDao.getBoothAdminLoginListWithPagination(pageid,total);
			BoothAdminLoginResponseVO response;
			for (BoothAdminLogin boothAdminLogin : login) {
				response=new BoothAdminLoginResponseVO();
				//SubscriptionMaster subscriptionMaster=superadminDao.getSubscriptionListById(boothAdminLogin.getSubId());
				response.setContactNumber(DateUtils.checkNull(boothAdminLogin.getContactNumber()));
				response.setCreatedDate(DateUtils.timeStampConvertIntoStringDateFormatYYYYMMDD(new Date()));
				response.setEmailId(DateUtils.checkNull(boothAdminLogin.getEmailId()));
				response.setIsDeleted(boothAdminLogin.getIsDeleted());
				response.setLocation(DateUtils.checkNull(boothAdminLogin.getLocation()));
				response.setStatus(boothAdminLogin.getStatus());
				response.setSubId(boothAdminLogin.getSubId());
				
				if(boothAdminLogin.getSubEndDate()!=null){
					//int i=Integer.parseInt(subscriptionMaster.getSubValidaityDayPeriod());				
					//Date incrementDate=new Date(boothAdminLogin.getSubUpdatedDate().getTime()+(1000*60*60*24*i));
				response.setSubEndDateFormat(DateUtils.timeStampConvertIntoStringDateFormatYYYYMMDD(boothAdminLogin.getSubEndDate()));
				}
				if(boothAdminLogin.getUpdatedDate()!=null){
				response.setUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormatYYYYMMDD(boothAdminLogin.getUpdatedDate()));
				}
				response.setUserId(boothAdminLogin.getUserId());
				response.setUsername(boothAdminLogin.getUsername());
				response.setUserRole(boothAdminLogin.getUserRole());
				responseList.add(response);
			}
			return responseList;
		}

		@Override
		public List<EventVO> getEventListForSuperAdminWithPagination(int pageid, int total) {
			return superadminDao.getEventListForSuperAdminWithPagination(pageid,total);
		}
		

}
