package com.iamuse.admin.daoimpl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import com.iamuse.admin.VO.AdminPictureVO;
import com.iamuse.admin.VO.BoothAdminLoginResponseVO;
import com.iamuse.admin.VO.EventVO;
import com.iamuse.admin.dao.SuperadminDao;
import com.iamuse.admin.entity.AdminBoothEventPicture;
import com.iamuse.admin.entity.AdminEventPictureMapping;
import com.iamuse.admin.entity.Adminboothevent;
import com.iamuse.admin.entity.BoothAdminLogin;
import com.iamuse.admin.entity.BoothPictureCropper;
import com.iamuse.admin.entity.SubscriptionMaster;
import com.iamuse.admin.util.IAmuseadminUtil;
import com.paypal.constants.ServerConstants;

@Repository
public class SuperadminDaoImpl implements SuperadminDao {
	@Autowired SessionFactory sessionFactory;

	

	public List<EventVO> getEventList(Integer userId) {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd MMM,yyyy");

		List<EventVO> eventVOs=new ArrayList<EventVO>();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria= sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			criteria.add(Restrictions.eq("createdBy", userId));
			List<Adminboothevent> eventList = (List<Adminboothevent>)criteria.list(); 
			if(eventList !=null){
				for(Adminboothevent event:eventList){
					EventVO eventVO=new EventVO();
					eventVO.setEId(event.getEId());
					eventVO.setEventStart(myFormat.format(event.getEventStart()));
					eventVO.setEventName(event.getEventName());
					eventVO.setSponsorName(event.getSponsorName());
					eventVO.setCreaterName(event.getCreaterName());
					eventVO.setCreatedBy(event.getCreatedBy());
					eventVOs.add(eventVO);
				}
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return eventVOs;
	}

	

	public Adminboothevent getCurrentEvent(Integer eid) {
		Adminboothevent event=null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria= sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			criteria.add(Restrictions.eq("EId", eid));
			event = (Adminboothevent)criteria.uniqueResult(); 
			if(event != null){
				return event;
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return event;
	}

	public AdminPictureVO uploadBackgroundImage(AdminPictureVO adminPictureVO, MultipartFile file, String rootPath) {
		AdminPictureVO adminPictureVO2=new AdminPictureVO();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			AdminBoothEventPicture adminPicture = new AdminBoothEventPicture();
			adminPicture.setCreatedBy(adminPictureVO.getCreatedBy());
			adminPicture.setCreatedDate(new Date());
			
			 byte[] bytes  = file.getBytes();
	           String fileName = file.getOriginalFilename();
	           if(fileName!=""){
	           String fileUrl = IAmuseadminUtil.writeFile2(bytes, rootPath, adminPictureVO.getPicId(), fileName);
	           adminPicture.setPicName(fileUrl);
	           }
	           Integer adminPicId=(Integer)sessionFactory.getCurrentSession().save(adminPicture);
		
			adminPictureVO2.setResult("success");
			adminPictureVO2.setPicId(adminPicId);
			if(adminPicId!=null){
				sessionFactory.getCurrentSession().beginTransaction();
				Criteria criteria= sessionFactory.getCurrentSession().createCriteria(AdminBoothEventPicture.class);
				criteria.add(Restrictions.eq("picId", adminPicId));
				AdminBoothEventPicture adminPictures = (AdminBoothEventPicture)criteria.uniqueResult(); 
					if(adminPictures !=null){
						adminPictureVO2.setPicName(adminPictures.getPicName());
					}
				
				AdminEventPictureMapping adminEventPictureMapping=new AdminEventPictureMapping();
				adminEventPictureMapping.setPicId(adminPicId);
				adminEventPictureMapping.setEId(adminPictureVO.getEId());
				adminEventPictureMapping.setUserId(adminPictureVO.getCreatedBy());
				adminEventPictureMapping.setCreatedDate(new Date());
				sessionFactory.getCurrentSession().save(adminEventPictureMapping);
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return adminPictureVO2;
	}

	public SubscriptionMaster getSubscriptionListById(Integer subId) {
		SubscriptionMaster subscriptionPlan=null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria= sessionFactory.getCurrentSession().createCriteria(SubscriptionMaster.class);
			criteria.add(Restrictions.eq("subId", subId));
			subscriptionPlan = (SubscriptionMaster)criteria.uniqueResult(); 
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return subscriptionPlan;
	}

	public List<BoothAdminLogin> getBoothAdminLoginList() {
		List<BoothAdminLogin> list=new ArrayList<>();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria crt=sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			crt.add(Restrictions.eq("userRole", "boothadmin"));
			crt.add(Restrictions.eq("isDeleted", false));
			crt.add(Restrictions.eq("status", true));
			list=crt.list();
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			list.add(new BoothAdminLogin());
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return list;
	}

	public List<EventVO> getEventListForSuperAdmin() {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd MMM,yyyy");

		List<EventVO> eventVOs=new ArrayList<EventVO>();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria= sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			criteria.add(Restrictions.eq("status", true));
			criteria.add(Restrictions.eq("isDeleted", false));
			List<Adminboothevent> eventList = (List<Adminboothevent>)criteria.list(); 
			if(eventList !=null){
				for(Adminboothevent event:eventList){
					EventVO eventVO=new EventVO();
					eventVO.setEId(event.getEId());
					eventVO.setEventStart(myFormat.format(event.getEventStart()));
					eventVO.setEventName(event.getEventName());
					eventVO.setSponsorName(event.getSponsorName());
					eventVO.setCreaterName(event.getCreaterName());
					eventVO.setCreatedBy(event.getCreatedBy());
					eventVO.setEventType(event.getEventType());
					eventVOs.add(eventVO);
				}
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return eventVOs;
	}

	public boolean saveSubscriptionMaster(SubscriptionMaster subscriptionMaster, Integer userId) {
		boolean flag = false;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			subscriptionMaster.setCreatedUserId(userId);
			subscriptionMaster.setCreatedDate(new Timestamp(new Date().getTime()));
			subscriptionMaster.setStatus(true);
			subscriptionMaster.setIsDeleted(false);
			sessionFactory.getCurrentSession().save(subscriptionMaster);
			sessionFactory.getCurrentSession().getTransaction().commit();
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
			flag=false;
		}
		return flag;
	}

	public boolean updateSubscriptionMaster(
			SubscriptionMaster subscriptionMaster, Integer userId) {
		boolean flag = false;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			subscriptionMaster.setUpdatedByUserId(userId);
			subscriptionMaster.setUpdatedDate(new Timestamp(new Date().getTime()));
			sessionFactory.getCurrentSession().update(subscriptionMaster);
			sessionFactory.getCurrentSession().getTransaction().commit();
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
			flag=false;
		}
		return flag;
	}

	public boolean deleteSubscriptionMasterById(SubscriptionMaster subscriptionMaster,Integer userId) {
		boolean flag = false;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			subscriptionMaster.setIsDeleted(true);
			subscriptionMaster.setUpdatedByUserId(userId);
			subscriptionMaster.setUpdatedDate(new Timestamp(new Date().getTime()));
			sessionFactory.getCurrentSession().update(subscriptionMaster);
			sessionFactory.getCurrentSession().getTransaction().commit();
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
			flag=false;
		}
		return flag;
	}

	public boolean activeSubscriptionMaster(
			SubscriptionMaster subscriptionMaster, Integer userId) {
		boolean flag = false;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			subscriptionMaster.setUpdatedByUserId(userId);
			subscriptionMaster.setUpdatedDate(new Timestamp(new Date().getTime()));
			subscriptionMaster.setStatus(true);
			sessionFactory.getCurrentSession().update(subscriptionMaster);
			sessionFactory.getCurrentSession().getTransaction().commit();
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
			flag=false;
		}
		return flag;
	}

	public boolean deactiveSubscriptionMaster(
			SubscriptionMaster subscriptionMaster, Integer userId) {
		boolean flag = false;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			subscriptionMaster.setUpdatedByUserId(userId);
			subscriptionMaster.setUpdatedDate(new Timestamp(new Date().getTime()));
			subscriptionMaster.setStatus(false);
			sessionFactory.getCurrentSession().update(subscriptionMaster);
			sessionFactory.getCurrentSession().getTransaction().commit();
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
			flag=false;
		}
		return flag;
	}

	public Adminboothevent getEventValueByEventId(Integer eventId) {
		Adminboothevent event=new Adminboothevent();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria crt=sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			crt.add(Restrictions.eq("EId", eventId));
			crt.add(Restrictions.eq("status", true));
			crt.add(Restrictions.eq("isDeleted", false));
			event=(Adminboothevent)crt.uniqueResult();
			sessionFactory.getCurrentSession().getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
			
		}
		return event;
	}

	public boolean updateAdminBoothEvent(Adminboothevent adminBoothEvent) {
		boolean flag = false;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			sessionFactory.getCurrentSession().update(adminBoothEvent);
			sessionFactory.getCurrentSession().getTransaction().commit();
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
			flag=false;
		}
		return flag;
	}

	public boolean deleteAdminBoothEventByEventId(Integer id) {
		boolean flag = false;
		
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria= sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
		 	criteria.add(Restrictions.eq("EId", id));
		 	criteria.add(Restrictions.eq("status", true));
			criteria.add(Restrictions.eq("isDeleted", false));
			Adminboothevent adminboothevent = (Adminboothevent)criteria.uniqueResult();
		 	if(adminboothevent !=null){
		 		adminboothevent.setIsDeleted(true);
		 		sessionFactory.getCurrentSession().update(adminboothevent);
		 		
//		 		sessionFactory.getCurrentSession().beginTransaction();
				Criteria criteriaAdminBoothEventPicture= sessionFactory.getCurrentSession().createCriteria(AdminBoothEventPicture.class);
			 	criteriaAdminBoothEventPicture.add(Restrictions.eq("EId", id));
			 	criteriaAdminBoothEventPicture.add(Restrictions.eq("status", true));
				criteriaAdminBoothEventPicture.add(Restrictions.eq("isDeleted", false));
				List<AdminBoothEventPicture> adminBoothEventPicture =criteriaAdminBoothEventPicture.list();
				if(adminBoothEventPicture.size() >=0){
					for(AdminBoothEventPicture adminBoothEventPictures:adminBoothEventPicture){
						adminBoothEventPictures.setIsDeleted(true);
						sessionFactory.getCurrentSession().update(adminBoothEventPictures);
					}
				}
				
				
				Criteria mappingCriteria= sessionFactory.getCurrentSession().createCriteria(AdminEventPictureMapping.class);
			 	mappingCriteria.add(Restrictions.eq("EId", id));
			 	mappingCriteria.add(Restrictions.eq("status", true));
				mappingCriteria.add(Restrictions.eq("isDeleted", false));
				List<AdminEventPictureMapping> mapping =mappingCriteria.list();
				if(mapping.size()>0){
					for (AdminEventPictureMapping adminEventPictureMapping : mapping) {
						adminEventPictureMapping.setIsDeleted(true);
					sessionFactory.getCurrentSession().update(adminEventPictureMapping);
				}
				}
		 	}
		 	sessionFactory.getCurrentSession().getTransaction().commit();
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
			flag=false;
		}
		return flag;
	}

	public EventVO saveCreateEventBySuperAdmin(EventVO eventVO, Integer userId) {
		EventVO eventVO2=new EventVO();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
	        String dateInString = eventVO.getEventStart();
	        Date date=null;
	        try {
	            date = formatter.parse(dateInString);
	            System.out.println(date);
	            System.out.println(formatter.format(date));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        sessionFactory.getCurrentSession().beginTransaction();
		try {
			Adminboothevent event=new Adminboothevent();
				event.setCreatedBy(userId);
				event.setEventStart(date);
				event.setCreatedDate(new Date());
				event.setEventName(eventVO.getEventName());
				event.setSponsorName(eventVO.getSponsorName());
				event.setCreaterName(eventVO.getCreaterName());
				event.setStatus(true);
				event.setIsDeleted(false);
				Integer e_id=(Integer)sessionFactory.getCurrentSession().save(event);
				eventVO2.setResult("success");
				eventVO2.setEId(e_id);
			sessionFactory.getCurrentSession().getTransaction().commit();
			 
			
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return eventVO2;
	
	}
	
	public List<SubscriptionMaster> getSubscriptionListSuperAdminPage() {
		Criteria criteria= sessionFactory.getCurrentSession().createCriteria(SubscriptionMaster.class);
		//criteria.add(Restrictions.eq("status", true));
		criteria.add(Restrictions.eq("isDeleted", false));
		List<SubscriptionMaster> subscriptionMasters = criteria.list();
		return subscriptionMasters;
	}

	public Integer getSubscriptionCountForBarChart(String startDate,String endDate) {
		
		String hql="select count(*) as count from iamuse_new.booth_admin_login where created_date BETWEEN '"+startDate+"' AND '"+endDate+"'";
		//System.err.println(" date------------------->>>>>>>\t"+startDate);
		sessionFactory.getCurrentSession().beginTransaction();
		BigInteger count =(BigInteger)sessionFactory.getCurrentSession().createSQLQuery(hql).uniqueResult();
		
		//System.out.println(" count \t"+count.intValue());
		return count.intValue();
	}

	public Integer getSubscriptionCountForBarChartDefaultSubscription(
			String janStartDate, String janEndDate,Integer subId) {
		String hql="select count(*) as count from iamuse_new.booth_admin_login where created_date BETWEEN '"+janStartDate+"' AND '"+janEndDate+"' and sub_id="+subId;
		//System.err.println(" date------------------->>>>>>>\t"+startDate);
		sessionFactory.getCurrentSession().beginTransaction();
		BigInteger count =(BigInteger)sessionFactory.getCurrentSession().createSQLQuery(hql).uniqueResult();
		
		//System.out.println(" count \t"+count.intValue());
		return count.intValue();
	}

	public BoothAdminLogin getProfileDetails(Integer userId) {
		BoothAdminLogin boothAdminLogin=null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria= sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteria.add(Restrictions.eq("userId", userId));
			criteria.add(Restrictions.eq("status", true));
			criteria.add(Restrictions.eq("isDeleted", false));
			boothAdminLogin =(BoothAdminLogin) criteria.uniqueResult();
			if(boothAdminLogin !=null){
				return boothAdminLogin;
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return boothAdminLogin;
	}

	public AdminPictureVO uploadBackgroundImage(AdminPictureVO adminPictureVO,
			MultipartFile[] files, String rootPath) {
		AdminPictureVO adminPictureVO2=new AdminPictureVO();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			
			for (MultipartFile file : files) {
        		byte[] bytes  = file.getBytes();
        		String fileName = file.getOriginalFilename();
        		if(fileName!=""){
        			String fileUrl1=IAmuseadminUtil.writeFileUploadingImage(bytes, rootPath, adminPictureVO.getCreatedBy(), adminPictureVO.getEId(), fileName);
        			AdminBoothEventPicture adminPicture = new AdminBoothEventPicture();
     				adminPicture.setCreatedBy(adminPictureVO.getCreatedBy());
     				adminPicture.setCreatedDate(new Date());
     	           adminPicture.setPicName(fileUrl1);
     	          adminPicture.setEId(adminPictureVO.getEId());
     	         adminPicture.setIsDeleted(false);
     	         adminPicture.setStatus(true);
     	           Integer adminPicId=(Integer)sessionFactory.getCurrentSession().save(adminPicture);
     	   		
     				
     				if(adminPicId!=null){

     					AdminEventPictureMapping adminEventPictureMapping=new AdminEventPictureMapping();
     					adminEventPictureMapping.setPicId(adminPicId);
     					adminEventPictureMapping.setEId(adminPictureVO.getEId());
     					adminEventPictureMapping.setUserId(adminPictureVO.getCreatedBy());
     					adminEventPictureMapping.setCreatedDate(new Date());
     					adminEventPictureMapping.setIsDeleted(false);
     					adminEventPictureMapping.setStatus(true);
     					sessionFactory.getCurrentSession().save(adminEventPictureMapping);
     						}
     					
     				}
     	           }
			adminPictureVO2.setResult("success");
			adminPictureVO2.setEId(adminPictureVO.getEId());
			sessionFactory.getCurrentSession().getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return adminPictureVO2;
	}

	public List<AdminPictureVO> getPicList(Integer eid, Integer userId) {
		List<AdminPictureVO> adminPictureVOs=new ArrayList<AdminPictureVO>();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria= sessionFactory.getCurrentSession().createCriteria(AdminEventPictureMapping.class);
			criteria.add(Restrictions.eq("userId", userId));
			criteria.add(Restrictions.eq("EId", eid));
			criteria.add(Restrictions.eq("status", true));
			criteria.add(Restrictions.eq("isDeleted", false));
			List<AdminEventPictureMapping>	adminEventPictureMapping =(List<AdminEventPictureMapping>) criteria.list(); 
			if(adminEventPictureMapping.size()>0){
				for(AdminEventPictureMapping adminEventPictureMappings:adminEventPictureMapping){
					Criteria criteriaAdminPicture= sessionFactory.getCurrentSession().createCriteria(AdminBoothEventPicture.class);
					criteriaAdminPicture.add(Restrictions.eq("picId", adminEventPictureMappings.getPicId()));
					AdminBoothEventPicture	adminPicture =(AdminBoothEventPicture) criteriaAdminPicture.uniqueResult();
					if(adminPicture !=null){
						AdminPictureVO adminPictureVO=new AdminPictureVO();
						adminPictureVO.setPicId(adminPicture.getPicId());
						adminPictureVO.setPicName(adminPicture.getPicName());
						adminPictureVOs.add(adminPictureVO);
					}
					
				}
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return adminPictureVOs;
	}

	public AdminPictureVO getImageConfigure(Integer picId) {
		AdminPictureVO adminPictureVO=new AdminPictureVO();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria= sessionFactory.getCurrentSession().createCriteria(AdminBoothEventPicture.class);
			criteria.add(Restrictions.eq("picId", picId));
			criteria.add(Restrictions.eq("status", true));
			criteria.add(Restrictions.eq("isDeleted", false));
			AdminBoothEventPicture adminPicture =(AdminBoothEventPicture) criteria.uniqueResult();
			if(adminPicture !=null){
				adminPictureVO.setPicId(adminPicture.getPicId());
				adminPictureVO.setPicName(adminPicture.getPicName());
				adminPictureVO.setPicTitle(adminPicture.getPicTitle());
				adminPictureVO.setScaleXOffset(adminPicture.getScaleXOffset());
				adminPictureVO.setScaleYOffset(adminPicture.getScaleYOffset());
				adminPictureVO.setScaleZOffset(adminPicture.getScaleZOffset());
				adminPictureVO.setScalingWidth(adminPicture.getScalingWidth());
				adminPictureVO.setScalingHeight(adminPicture.getScalingHeight());
				adminPictureVO.setEId(adminPicture.getEId());
				
				Criteria criteriaBoothPictureCropper= sessionFactory.getCurrentSession().createCriteria(BoothPictureCropper.class);
				criteriaBoothPictureCropper.add(Restrictions.eq("adminBoothEventPictureId", adminPicture.getPicId()));
				//criteriaBoothPictureCropper.add(Restrictions.eq("status", true));
				//criteriaBoothPictureCropper.add(Restrictions.eq("isDeleted", false));
				BoothPictureCropper boothPictureCropper =(BoothPictureCropper) criteriaBoothPictureCropper.uniqueResult();
				if(boothPictureCropper !=null){
				adminPictureVO.setCropImgX(boothPictureCropper.getImgX());
				adminPictureVO.setCropImgY(boothPictureCropper.getImgY());
				adminPictureVO.setCropImgWidth(boothPictureCropper.getImgWidth());
				adminPictureVO.setCropImgHeight(boothPictureCropper.getImgHeight());
				}
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return adminPictureVO;
	}

	public String saveCoordinatesOfImg(AdminPictureVO adminPictureVO,MultipartFile files, MultipartFile files1, String rootPath) {
		String result="";
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria= sessionFactory.getCurrentSession().createCriteria(AdminBoothEventPicture.class);
			criteria.add(Restrictions.eq("picId", adminPictureVO.getPicId()));
			criteria.add(Restrictions.eq("status", true));
			criteria.add(Restrictions.eq("isDeleted", false));
			AdminBoothEventPicture adminPicture =(AdminBoothEventPicture) criteria.uniqueResult();
			if(adminPicture !=null){
				adminPicture.setScaleXOffset(adminPictureVO.getScaleXOffset());
				adminPicture.setScaleYOffset(adminPictureVO.getScaleYOffset());
				adminPicture.setScaleZOffset(adminPictureVO.getScaleZOffset());
				adminPicture.setPicTitle(adminPictureVO.getPicTitle());
				adminPicture.setImageWidth(adminPictureVO.getImageWidth());
				adminPicture.setImageHeight(adminPictureVO.getImageHeight());
				adminPicture.setScalingWidth(adminPictureVO.getScalingWidth());
				adminPicture.setScalingHeight(adminPictureVO.getScalingHeight());
				adminPicture.setUpdatedBy(adminPictureVO.getUpdatedBy());
				adminPicture.setUpdatedDate(new Date());
				
				Criteria criteriaAdminboothevent= sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
				criteriaAdminboothevent.add(Restrictions.eq("EId", adminPicture.getEId()));
				criteriaAdminboothevent.add(Restrictions.eq("status", true));
				criteriaAdminboothevent.add(Restrictions.eq("isDeleted", false));
				Adminboothevent adminboothevent =(Adminboothevent) criteriaAdminboothevent.uniqueResult();
				if(adminboothevent!=null){
					adminboothevent.setUpdatedDate(new Date());
					sessionFactory.getCurrentSession().update(adminboothevent);
					
					
					Criteria criteriaBoothPictureCropper= sessionFactory.getCurrentSession().createCriteria(BoothPictureCropper.class);
					criteriaBoothPictureCropper.add(Restrictions.eq("adminBoothEventPictureId", adminPictureVO.getPicId()));
					BoothPictureCropper boothPictureCroppers =(BoothPictureCropper) criteriaBoothPictureCropper.uniqueResult();
					if(boothPictureCroppers !=null){
						boothPictureCroppers.setImgX(adminPictureVO.getCropImgX());
						boothPictureCroppers.setImgY(adminPictureVO.getCropImgY());
						boothPictureCroppers.setImgWidth(adminPictureVO.getCropImgWidth());
						boothPictureCroppers.setImgHeight(adminPictureVO.getCropImgHeight());
						boothPictureCroppers.setAdminBoothEventPictureId(adminPictureVO.getPicId());
						sessionFactory.getCurrentSession().update(boothPictureCroppers);
					}else{
					BoothPictureCropper boothPictureCropper=new BoothPictureCropper();
					boothPictureCropper.setImgX(adminPictureVO.getCropImgX());
					boothPictureCropper.setImgY(adminPictureVO.getCropImgY());
					boothPictureCropper.setImgWidth(adminPictureVO.getCropImgWidth());
					boothPictureCropper.setImgHeight(adminPictureVO.getCropImgHeight());
					boothPictureCropper.setAdminBoothEventPictureId(adminPictureVO.getPicId());
					sessionFactory.getCurrentSession().save(boothPictureCropper);
					}
				}
				
				byte[] bytes  = files.getBytes();
        		String fileName = files.getOriginalFilename();
        		if(fileName!=""){
        			String fileUrl1=IAmuseadminUtil.writeFile1BYSuperAdmin(bytes, rootPath, adminPictureVO.getUpdatedBy(), adminPictureVO.getPicId(), fileName);
				adminPicture.setImageMask(fileUrl1);
        		}
        		byte[] bytes1  = files1.getBytes();
        		String fileName1 = files1.getOriginalFilename();
        		if(fileName1!=""){
        			String fileUrl1=IAmuseadminUtil.writeFile1BYSuperAdmin(bytes1, rootPath, adminPictureVO.getUpdatedBy(), adminPictureVO.getPicId(), fileName);
				adminPicture.setImageMask(fileUrl1);
        		}
				sessionFactory.getCurrentSession().update(adminPicture);
				result="success";
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return result;
	}



	@Override
	public List<BoothAdminLogin> getBoothAdminLoginListWithPagination(
			int pageid, int total) {
		List<BoothAdminLogin> list=new ArrayList<>();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria crt=sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			crt.add(Restrictions.eq("userRole", "boothadmin"));
			crt.add(Restrictions.eq("isDeleted", false));
			crt.add(Restrictions.eq("status", true));
			crt.setFirstResult((pageid-1)*total);
			crt.setMaxResults(total);
			crt.addOrder(Order.desc("userId"));
			list=crt.list();
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			list.add(new BoothAdminLogin());
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return list;
	}



	@Override
	public List<EventVO> getEventListForSuperAdminWithPagination(int pageid,
			int total) {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd MMM,yyyy");

		List<EventVO> eventVOs=new ArrayList<EventVO>();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria= sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
			criteria.add(Restrictions.eq("status", true));
			criteria.add(Restrictions.eq("isDeleted", false));
			criteria.setFirstResult((pageid-1)*total);
			criteria.setMaxResults(total);
			criteria.addOrder(Order.desc("EId"));
			List<Adminboothevent> eventList = (List<Adminboothevent>)criteria.list(); 
			if(eventList !=null){
				for(Adminboothevent event:eventList){
					EventVO eventVO=new EventVO();
					eventVO.setEId(event.getEId());
					eventVO.setEventStart(myFormat.format(event.getEventStart()));
					eventVO.setEventName(event.getEventName());
					eventVO.setSponsorName(event.getSponsorName());
					eventVO.setCreaterName(event.getCreaterName());
					eventVO.setCreatedBy(event.getCreatedBy());
					eventVO.setEventType(event.getEventType());
					eventVOs.add(eventVO);
				}
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return eventVOs;
	}



	@Override
	public void updateExpiredUsers(List<Integer> listUserIds) {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			for(Integer i:listUserIds) {
		 	Criteria criteria= sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
		 	criteria.add(Restrictions.eq(ServerConstants.USER_ID, i));
		 	criteria.add(Restrictions.eq(ServerConstants.STATUS, ServerConstants.MAKE_TRUE));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, ServerConstants.MAKE_FALSE));
		 	BoothAdminLogin subscriptionPlan = (BoothAdminLogin)criteria.uniqueResult();
		 	if(subscriptionPlan !=null){
			    subscriptionPlan.setPlanCode("1");
			    Date today = Calendar.getInstance().getTime();
			    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			    String folderName = formatter.format(today);
			    Date startDate = formatter.parse(folderName);
			    subscriptionPlan.setSubUpdatedDate(startDate);
			    sessionFactory.getCurrentSession().update(subscriptionPlan);
		   }
			}
		 	sessionFactory.getCurrentSession().getTransaction().commit();
	  } catch (Exception e) {
		  sessionFactory.getCurrentSession().getTransaction().rollback();
	  }
		
	}


	
	

}
