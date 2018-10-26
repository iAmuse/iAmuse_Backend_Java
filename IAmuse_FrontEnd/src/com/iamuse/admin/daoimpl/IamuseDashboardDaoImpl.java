package com.iamuse.admin.daoimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.iamuse.admin.VO.CrashLogsFormVO;
import com.iamuse.admin.VO.DeviceIPVO;
import com.iamuse.admin.VO.ImageEmailFormVO;
import com.iamuse.admin.VO.ImageFormVO;
import com.iamuse.admin.dao.IamuseDashboardDao;
import com.iamuse.admin.entity.AdminBoothEventPicture;
import com.iamuse.admin.entity.Adminboothevent;
import com.iamuse.admin.entity.BoothAdminLogin;
import com.iamuse.admin.entity.BoothUploadImageEmail;
import com.iamuse.admin.entity.CrashLogs;
import com.iamuse.admin.entity.DeviceIp;
import com.iamuse.admin.entity.UploadImage;
import com.iamuse.admin.entity.UploadImageEmail;
import com.iamuse.admin.entity.Usermaster;
import com.iamuse.admin.util.DateUtil;
import com.iamuse.admin.util.IAmuseadminUtil;
import com.paypal.constants.ServerConstants;



@SuppressWarnings("unchecked")
@Repository
public class IamuseDashboardDaoImpl implements IamuseDashboardDao{
	
	@Autowired SessionFactory sessionFactory;
	
	private static final Logger log = Logger.getLogger(IamuseDashboardDao.class);
	
	@Override
	public List<ImageFormVO> getImagesList(String userId)
	{
		List<ImageFormVO> imageFormVO = new ArrayList<>(); 
		int i=0;
		try {
		sessionFactory.getCurrentSession().beginTransaction();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UploadImage.class);
		criteria.add(Restrictions.eq(ServerConstants.STATUS,true));
		criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, false));
		criteria.add(Restrictions.eq(ServerConstants.USER_ID, Integer.parseInt(userId)));
		criteria.addOrder(Order.desc(ServerConstants.IMAGE_ID));
		List<UploadImage> uploadImage = criteria.list();
		if(uploadImage.size()>0){
		int listSize=uploadImage.size();
		for (Iterator iterator = uploadImage.iterator(); iterator.hasNext();) {
			 i++;
			UploadImage uploadImages = (UploadImage) iterator.next();
			ImageFormVO imageFormVOs = new ImageFormVO();
			imageFormVOs.setSerialNumber(i);
			imageFormVOs.setImageId(uploadImages.getImageId());
			imageFormVOs.setImages(uploadImages.getImageUrl());
			imageFormVOs.setImagename(uploadImages.getImageName());
			imageFormVOs.setListSize(listSize);
			imageFormVOs.setHexValue(IAmuseadminUtil.checkNull(uploadImages.getHexValue()));
			imageFormVOs.setRgbValue(IAmuseadminUtil.checkNull(uploadImages.getRgbValue()));
			imageFormVOs.setRgbaValue(IAmuseadminUtil.checkNull(uploadImages.getRgbaValue()));
			imageFormVOs.setHexValueMax(IAmuseadminUtil.checkNull(uploadImages.getHexValueMax()));
			imageFormVOs.setRgbValueMax(IAmuseadminUtil.checkNull(uploadImages.getRgbValueMax()));
			imageFormVOs.setRgbaValueMax(IAmuseadminUtil.checkNull(uploadImages.getRgbaValueMax()));
			imageFormVOs.setUploadTime(IAmuseadminUtil.getDatetimeInWord(uploadImages.getUploadTime()));
			imageFormVOs.setUpdateTime(IAmuseadminUtil.getDatetimeInWord(uploadImages.getUpdateTime()));
			imageFormVO.add(imageFormVOs);
		}
		}
		sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("IamuseDashboardDaoImpl Method : getImagesList ");
			log.error("Error getImagesList",e);
			e.getMessage();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return imageFormVO;
	}
	
	@Override
	public String downloadImage(int imageId,int userId)
	{
		String imageUrl="";
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UploadImage.class);
			criteria.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteria.add(Restrictions.eq(ServerConstants.IMAGE_ID, imageId));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, true));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, false));
			UploadImage uploadImage = (UploadImage) criteria.uniqueResult();
			if(uploadImage!=null)
			{
				imageUrl=uploadImage.getImageUrl();
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("IamuseDashboardDaoImpl Method : downloadImage ");
			log.error("Error downloadImage",e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return imageUrl;
	}
	
	@Override
	public boolean updateRGBValue(ImageFormVO imageFormVO,String userId)
	{
		Boolean result=false;
		try {
		if(imageFormVO.getImageId()!=0){
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UploadImage.class);
			criteria.add(Restrictions.eq(ServerConstants.IMAGE_ID, imageFormVO.getImageId()));
			UploadImage uploadImage = (UploadImage) criteria.uniqueResult();
			uploadImage.setHexValue(imageFormVO.getHexValue());
			uploadImage.setRgbValue(imageFormVO.getRgbValue());
			uploadImage.setRgbaValue(imageFormVO.getRgbaValue());
			uploadImage.setUpdateTime(IAmuseadminUtil.getTimeStamp());
			uploadImage.setIsUpdatedRgb(true);
			sessionFactory.getCurrentSession().update(uploadImage);
		}
			Criteria criteria1 = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteria1.add(Restrictions.eq(ServerConstants.USER_ID,Integer.parseInt(userId)));
			BoothAdminLogin usermaster = (BoothAdminLogin) criteria1.uniqueResult();
			usermaster.setCurrentImageId(imageFormVO.getImageId());
			usermaster.setIsDefaultRgb(false);
			usermaster.setHexValueManual(imageFormVO.getHexValue());
			usermaster.setRgbValueManual(imageFormVO.getRgbValue());
			usermaster.setRgbaValueManual(imageFormVO.getRgbaValue());
			sessionFactory.getCurrentSession().update(usermaster);
			result = true;
		} catch (Exception e) {
			log.info("IamuseDashboardDaoImpl Method : updateRGBValue ");
			log.error("Error updateRGBValue",e);
		}
			return result;
	}

	@Override
	public UploadImage loadImageDetail(int imageId) {
		UploadImage imageDetails = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UploadImage.class);
			criteria.add(Restrictions.eq(ServerConstants.IMAGE_ID, imageId));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, true));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED, false));
			imageDetails=(UploadImage) criteria.uniqueResult();
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("IamuseDashboardDaoImpl Method : loadImageDetail ");
			log.error("Error loadImageDetail",e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return imageDetails;
	}
	
	@Override
	public boolean updateRGBValueMax(ImageFormVO imageFormVO,String userId)
	{
		Boolean result=false;
		try {
		if(imageFormVO.getImageId()!=0){
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UploadImage.class);
			criteria.add(Restrictions.eq(ServerConstants.IMAGE_ID, imageFormVO.getImageId()));
			UploadImage uploadImage = (UploadImage) criteria.uniqueResult();
			uploadImage.setHexValueMax(imageFormVO.getHexValue());
			uploadImage.setRgbValueMax(imageFormVO.getRgbValue());
			uploadImage.setRgbaValueMax(imageFormVO.getRgbaValue());
			uploadImage.setUpdateTime(IAmuseadminUtil.getTimeStamp());
			sessionFactory.getCurrentSession().update(uploadImage);
		}
			Criteria criteria1 = sessionFactory.getCurrentSession().createCriteria(Usermaster.class);
			criteria1.add(Restrictions.eq(ServerConstants.USER_ID,Integer.parseInt(userId)));
			Usermaster usermaster = (Usermaster) criteria1.uniqueResult();
			usermaster.setCurrentImageId(imageFormVO.getImageId());
			usermaster.setIsDefaultRgb(false);
			usermaster.setHexValueManual(imageFormVO.getHexValue());
			usermaster.setRgbValueManual(imageFormVO.getRgbValue());
			usermaster.setRgbaValueManual(imageFormVO.getRgbaValue());
			sessionFactory.getCurrentSession().update(usermaster);
			result = true;
		} catch (Exception e) {
			log.info("IamuseDashboardDaoImpl Method : updateRGBValueMax ");
			log.error("Error updateRGBValueMax",e);
		}
			return result;
	}
	
	@Override
	public boolean resetRGBValueDefault(ImageFormVO imageFormVO,Integer userId)
	{
		Boolean result=false;
		try {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UploadImage.class);
		criteria.add(Restrictions.eq(ServerConstants.USER_ID, userId));
		criteria.add(Restrictions.eq(ServerConstants.IMAGE_ID, imageFormVO.getImageId()));
		UploadImage uploadImage = (UploadImage) criteria.uniqueResult();
		uploadImage.setIsUpdatedRgb(false);
		sessionFactory.getCurrentSession().update(uploadImage);
		result = true;
		} catch (Exception e) {
			log.info("IamuseDashboardDaoImpl Method : resetRGBValueDefault ");
			log.error("Error resetRGBValueDefault",e);
		}
		return result;
	}
	
	@Override
	public boolean resetSystemDefaultRGBValue(String userId)
	{
		Boolean result=false;
		try {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UploadImage.class);
		criteria.add(Restrictions.eq(ServerConstants.STATUS, true));
		List<UploadImage> uploadImage = criteria.list();
		for (Iterator iterator = uploadImage.iterator(); iterator.hasNext();) {
			UploadImage uploadImages = (UploadImage) iterator.next();
		uploadImages.setIsUpdatedRgb(false);
		sessionFactory.getCurrentSession().update(uploadImages);
		}
		Criteria criteria1 = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
		criteria1.add(Restrictions.eq(ServerConstants.USER_ID,Integer.parseInt(userId)));
		BoothAdminLogin usermaster=(BoothAdminLogin)criteria1.uniqueResult();
		usermaster.setCurrentImageId(0);
		usermaster.setIsDefaultRgb(true);
		sessionFactory.getCurrentSession().update(usermaster);
		result = true;
		} catch (Exception e) {
			log.info("IamuseDashboardDaoImpl Method : resetSystemDefaultRGBValue ");
			log.error("Error resetSystemDefaultRGBValue",e);
		}
		return result;
	}
	
	@Override
	public Usermaster getUsermasterDetails(String userId)
	{
		Usermaster userMaster=null;
		try{
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Usermaster.class);
			criteria.add(Restrictions.eq(ServerConstants.USER_ID,Integer.parseInt(userId)));
			userMaster=(Usermaster)criteria.uniqueResult();
			sessionFactory.getCurrentSession().getTransaction().commit();
		}
		catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			log.info("IamuseDashboardDaoImpl Method : getUsermasterDetails ");
			log.error("Error getUsermasterDetails",e);
		}
		return userMaster;
	}
	
	@Override
	public boolean deleteImage(int imageId) {
		  boolean result=false;
		  try {
			    sessionFactory.getCurrentSession().beginTransaction();
			    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UploadImage.class);
				criteria.add(Restrictions.eq(ServerConstants.IMAGE_ID,imageId));
				UploadImage uploadImage = (UploadImage) criteria.uniqueResult();
				if(uploadImage!=null)
				{
					uploadImage.setIsDeleted(true);
				    sessionFactory.getCurrentSession().update(uploadImage);
				    result=true;
				}
				sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			log.info("IamuseDashboardDaoImpl Method : deleteImage ");
			log.error("Error deleteImage",e);
			return false;
		}
			return result;
		}
	
	@Override
	public boolean updateDefaultRGB(ImageFormVO imageFormVO, String userId) {
		Usermaster userMaster=null;
		boolean result=false;
		try{
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Usermaster.class);
			criteria.add(Restrictions.eq(ServerConstants.USER_ID,Integer.parseInt(userId)));
			userMaster=(Usermaster)criteria.uniqueResult();
			userMaster.setRgbValueDefault(imageFormVO.getR()+","+imageFormVO.getG()+","+imageFormVO.getB());
			userMaster.setRgbaValueDefault(imageFormVO.getR()+","+imageFormVO.getG()+","+imageFormVO.getB()+","+"255");
			userMaster.setCurrentImageId(0);
			userMaster.setIsDefaultRgb(true);
			sessionFactory.getCurrentSession().update(userMaster);
			result=true;
			sessionFactory.getCurrentSession().getTransaction().commit();
		}
		catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			log.info("IamuseDashboardDaoImpl Method : updateDefaultRGB ");
			log.error("Error updateDefaultRGB",e);
		}
		return result;
	}

	@Override
	public List<ImageEmailFormVO> getEmailImagesLists(String userId, Integer eventId, int pageid, int total)
	{
		List<ImageEmailFormVO> imageEmailFormVO = new ArrayList<>(); 
		int i=0;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			
			Criteria criteria1= sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteria1.add(Restrictions.eq(ServerConstants.USER_ID, Integer.parseInt(userId)));
			BoothAdminLogin boothAdminLogin1 = (BoothAdminLogin) criteria1.uniqueResult();
			
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
			if(!boothAdminLogin1.getUserRole().equals("superadmin")){
			criteria.add(Restrictions.eq(ServerConstants.USER_ID, Integer.parseInt(userId)));
			}
			criteria.add(Restrictions.eq(ServerConstants.STATUS,true));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED,false));
			criteria.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			criteria.addOrder(Order.desc("uploadTime"));
			criteria.setFirstResult((pageid-1)*total);
			criteria.setMaxResults(total);
			
			List<BoothUploadImageEmail> uploadImageEmail = criteria.list();
			for (Iterator iterator = uploadImageEmail.iterator(); iterator.hasNext();) {
				 i++;
				 BoothUploadImageEmail uploadImageEmail1 = (BoothUploadImageEmail) iterator.next();
				 
				 Criteria criteriaBoothAdminLogin = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
					criteriaBoothAdminLogin.add(Restrictions.eq(ServerConstants.USER_ID,uploadImageEmail1.getUserId()));
					BoothAdminLogin boothAdminLogin = (BoothAdminLogin) criteriaBoothAdminLogin.uniqueResult();
					if(boothAdminLogin !=null){
						ImageEmailFormVO imageEmailFormVOs = new ImageEmailFormVO();
						imageEmailFormVOs.setUserName(boothAdminLogin.getUsername());
						imageEmailFormVOs.setContactNo(boothAdminLogin.getContactNumber());
						imageEmailFormVOs.setId(uploadImageEmail1.getId());
						imageEmailFormVOs.setSerialNumber(i);
						if(uploadImageEmail1.getImageTimestamp().isEmpty())	{
							imageEmailFormVOs.setImageTimestamp(""+uploadImageEmail1.getUploadTime());
						}else{
							imageEmailFormVOs.setImageTimestamp(uploadImageEmail1.getImageTimestamp());
						}
						 imageEmailFormVOs.setMailImageUrl(uploadImageEmail1.getMailImageUrl()+"/"+uploadImageEmail1.getEventId()+"/"+uploadImageEmail1.getMailImageName());
						 imageEmailFormVOs.setMailImageUrlThumb( "/IAmuseimages/EmailImagesThumbnail"+"/"+uploadImageEmail1.getEventId()+"/"+uploadImageEmail1.getMailImageName());
						 imageEmailFormVOs.setMailImageName(uploadImageEmail1.getMailImageName());
						 imageEmailFormVOs.setEmailId(uploadImageEmail1.getEmailId());
						 imageEmailFormVOs.setUploadTime(IAmuseadminUtil.getDatetimeInWord(uploadImageEmail1.getUploadTime()));
						 imageEmailFormVOs.setMailSentTime(IAmuseadminUtil.getDatetimeInWord(uploadImageEmail1.getMailSentTime()));
						 imageEmailFormVOs.setDownloadStatus(uploadImageEmail1.getDownloadStatus());
						 Criteria criteriaAdminboothevent = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
						 criteriaAdminboothevent.add(Restrictions.eq("EId",eventId));
						 Adminboothevent eventIdss = (Adminboothevent) criteriaAdminboothevent.uniqueResult();
						 if(eventIdss !=null){
							 imageEmailFormVOs.setEventName(eventIdss.getEventName());
						 }
						 imageEmailFormVO.add(imageEmailFormVOs);
					}
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("IamuseDashboardDaoImpl Method : getEmailImagesLists ");
			log.error("Error getEmailImagesLists",e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return imageEmailFormVO;
	}
	
	@Override
	public boolean deleteEmailImage(int imageId) {
		  boolean result=false;
		  try {
			    sessionFactory.getCurrentSession().beginTransaction();
			    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UploadImageEmail.class);
				criteria.add(Restrictions.eq("id",imageId));
				UploadImageEmail uploadImageEmail = (UploadImageEmail) criteria.uniqueResult();
				if(uploadImageEmail!=null)
				{
					uploadImageEmail.setStatus(false);
				    sessionFactory.getCurrentSession().update(uploadImageEmail);
				    result=true;
				}
				sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			log.info("IamuseDashboardDaoImpl Method : deleteEmailImage ");
			log.error("Error deleteEmailImage",e);
			return false;
		}
			return result;
		}
	
	@Override
	public UploadImageEmail emailImage(int id) {
		UploadImageEmail uploadImageEmail=null;
		try{
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(UploadImageEmail.class);
			criteria.add(Restrictions.eq("id",id));
			uploadImageEmail=(UploadImageEmail)criteria.uniqueResult();
			sessionFactory.getCurrentSession().getTransaction().commit();
		}
		catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			log.info("IamuseDashboardDaoImpl Method : emailImage ");
			log.error("Error emailImage",e);
		}
		return uploadImageEmail;
	}
	
	@Override
	public List<CrashLogsFormVO> getCrashLogsList(String userId) {
		int i=0;
		List<CrashLogsFormVO> crashLogsFormVO = new ArrayList<>();
		try {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CrashLogs.class);
		criteria.add(Restrictions.eq(ServerConstants.STATUS,true));
		criteria.add(Restrictions.eq(ServerConstants.USER_ID, Integer.parseInt(userId)));
		criteria.addOrder(Order.desc("id"));
		List<CrashLogs> crashLogs = criteria.list();
		for (Iterator iterator = crashLogs.iterator(); iterator.hasNext();) {
			 i++;
			 CrashLogs crashLogs1 = (CrashLogs) iterator.next();
			 CrashLogsFormVO crashLogsFormVOs = new CrashLogsFormVO();
			 crashLogsFormVOs.setSerialNumber(i);
			 crashLogsFormVOs.setLogId(crashLogs1.getId());
			 crashLogsFormVOs.setFileUrl(crashLogs1.getFileUrl());
			 crashLogsFormVOs.setFileName(crashLogs1.getFileName());
			 crashLogsFormVOs.setReadStatus(crashLogs1.getReadStatus());
			 crashLogsFormVOs.setUploadTime(IAmuseadminUtil.getDatetimeInWord(crashLogs1.getUploadTime()));
			 crashLogsFormVO.add(crashLogsFormVOs);
		}
		} catch (Exception e) {
			log.info("IamuseDashboardDaoImpl Method : getCrashLogsList ");
			log.error("Error getCrashLogsList",e);
		}
		return crashLogsFormVO;
	}
	
	@Override
	public Boolean updateEmailId(int id, String emailId) {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UploadImageEmail.class);
			criteria.add(Restrictions.eq("id",id));
			UploadImageEmail uploadImageEmail = (UploadImageEmail) criteria.uniqueResult();
			uploadImageEmail.setEmailId(emailId);
			 sessionFactory.getCurrentSession().update(uploadImageEmail);
			 sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("IamuseDashboardDaoImpl Method : updateEmailId ");
			log.error("Error updateEmailId",e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return true;
	}
	
	@Override
	public String downloadCrashFile(String id)
	{
		String fileUrl="";
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CrashLogs.class);
			criteria.add(Restrictions.eq("id", Integer.parseInt(id)));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, true));
			CrashLogs crashLogs = (CrashLogs) criteria.uniqueResult();
			if(crashLogs!=null)
			{
				fileUrl=crashLogs.getFileUrl()+";"+crashLogs.getFileName();
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("IamuseDashboardDaoImpl Method : downloadCrashFile ");
			log.error("Error downloadCrashFile",e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return fileUrl;
	}

	@Override
	public boolean isRead(int id, String string) {
		boolean result=false;
		  try {
			    sessionFactory.getCurrentSession().beginTransaction();
			    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CrashLogs.class);
				criteria.add(Restrictions.eq("id",id));
				CrashLogs crashLogs = (CrashLogs) criteria.uniqueResult();
				if(crashLogs!=null)
				{
					crashLogs.setReadStatus(true);
				    sessionFactory.getCurrentSession().update(crashLogs);
				    result=true;
				}
				sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			log.info("IamuseDashboardDaoImpl Method : isRead ");
			log.error("Error isRead",e);
			return false;
		}
			return result;
	}

	@Override
	public Integer deleteSelected(int id,String page) {
		Integer status=0;
		try{
			sessionFactory.getCurrentSession().beginTransaction();
			if(("userImage").equals(page))
			{
		    Criteria criteriastatus = sessionFactory.getCurrentSession().createCriteria(UploadImageEmail.class);
			criteriastatus.add(Restrictions.eq("id",id));
			UploadImageEmail rs=(UploadImageEmail) criteriastatus.uniqueResult();
			if(rs!=null){
				rs.setStatus(false);
				sessionFactory.getCurrentSession().update(rs);
				status=1;
			}}
			if(("crashlogsFiles").equals(page))
			{
		    Criteria criteriastatus = sessionFactory.getCurrentSession().createCriteria(CrashLogs.class);
			criteriastatus.add(Restrictions.eq("id",id));
			CrashLogs rs=(CrashLogs) criteriastatus.uniqueResult();
			if(rs!=null){
				rs.setStatus(false);
				sessionFactory.getCurrentSession().update(rs);
				status=1;
			}}
			if(("dashboardImage").equals(page))
			{
		    Criteria criteriastatus = sessionFactory.getCurrentSession().createCriteria(UploadImage.class);
			criteriastatus.add(Restrictions.eq(ServerConstants.IMAGE_ID,id));
			UploadImage rs=(UploadImage) criteriastatus.uniqueResult();
			if(rs!=null){
				rs.setStatus(false);
				sessionFactory.getCurrentSession().update(rs);
				status=1;
			}}
			sessionFactory.getCurrentSession().getTransaction().commit();
		}catch(Exception e){
			sessionFactory.getCurrentSession().getTransaction().rollback();
			log.info("IamuseDashboardDaoImpl Method : deleteSelected ");
			log.error("Error deleteSelected",e);
		}
		return status;
	}
	
	@Override
	public List<ImageEmailFormVO> getUserEmailListForExport(String dateFrom,String dateTo, int userId) {
		Date fromDate=null;
		Date toDate=null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		  try {
			  fromDate= df.parse(dateFrom);
			toDate = df.parse(dateTo);
			toDate.setHours(23);
			toDate.setMinutes(59);
			
		} catch (ParseException e) {
			log.info("IamuseDashboardDaoImpl Method : getUserEmailListForExport ");
			log.error(e.getMessage());
		}
		
		int i=0;
		List<ImageEmailFormVO> imageEmailFormVO = new ArrayList<>(); 
		try {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UploadImageEmail.class);
		criteria.add(Restrictions.eq(ServerConstants.STATUS,true));
		criteria.add(Restrictions.eq(ServerConstants.USER_ID,userId));
		criteria.addOrder(Order.desc("id"));
		criteria.add(Restrictions.between("mailSentTime", fromDate, toDate));
		List<UploadImageEmail> uploadImageEmail = criteria.list();
		for (Iterator iterator = uploadImageEmail.iterator(); iterator.hasNext();) {
			 i++;
			 UploadImageEmail uploadImageEmail1 = (UploadImageEmail) iterator.next();
			 ImageEmailFormVO imageEmailFormVOs = new ImageEmailFormVO();
			 imageEmailFormVOs.setSerialNumber(i);
			imageEmailFormVOs.setEmailId(uploadImageEmail1.getEmailId());
			 imageEmailFormVOs.setUploadTime(IAmuseadminUtil.dateToString(uploadImageEmail1.getUploadTime()));
			 imageEmailFormVOs.setMailSentTime(IAmuseadminUtil.dateToString(uploadImageEmail1.getMailSentTime()));
			 imageEmailFormVO.add(imageEmailFormVOs);
		}
		Criteria criteria2 = sessionFactory.getCurrentSession().createCriteria(UploadImageEmail.class);
		criteria2.add(Restrictions.eq(ServerConstants.STATUS,true));
		criteria2.add(Restrictions.eq(ServerConstants.USER_ID,userId));
		criteria2.addOrder(Order.desc("id"));
		criteria2.add(Restrictions.between("mailSentTime", fromDate, toDate));
		List<UploadImageEmail> uploadImageEmail2 = criteria2.list();
		for (Iterator iterator = uploadImageEmail.iterator(); iterator.hasNext();) {
			 i++;
			 UploadImageEmail uploadImageEmail3 = (UploadImageEmail) iterator.next();
			 uploadImageEmail3.setDownloadStatus(1);
			 sessionFactory.getCurrentSession().update(uploadImageEmail3);
	}
		} catch (Exception e) {
			log.info("IamuseDashboardDaoImpl Method : getUserEmailListForExport ");
			log.error("Error getUserEmailListForExport",e);
		}
		return imageEmailFormVO;
	}
	
	@Override
	public boolean clearList(String userId) {
			boolean result=false;
			  try {
				  int downloadStat=1;
				    sessionFactory.getCurrentSession().beginTransaction();
				    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UploadImageEmail.class);
				    criteria.add(Restrictions.eq(ServerConstants.USER_ID,Integer.parseInt(userId)));
					criteria.add(Restrictions.eq("downloadStatus",downloadStat));
					List<UploadImageEmail> uploadImageEmail = criteria.list();
					if(!uploadImageEmail.isEmpty())
					{
					for (Iterator iterator = uploadImageEmail.iterator(); iterator.hasNext();) {
						UploadImageEmail uploadImageEmail2 = (UploadImageEmail) iterator.next();
						uploadImageEmail2.setStatus(false);
						uploadImageEmail2.setDownloadStatus(2);
					    sessionFactory.getCurrentSession().update(uploadImageEmail2);
					}}
					if(!uploadImageEmail.isEmpty())
					{
					result=true;
					}
					sessionFactory.getCurrentSession().getTransaction().commit();
			} catch (Exception e) {
				sessionFactory.getCurrentSession().getTransaction().rollback();
				log.info("IamuseDashboardDaoImpl Method : clearList ");
				log.error("Error clearList",e);
				return false;
			}
				return result;
		}
	
	@Override
	public List<DeviceIPVO> getDeviceIPList(String userId) {
		int i=0;
		List<DeviceIPVO> deviceIPVO = new ArrayList<>(); 
		try {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DeviceIp.class);
		criteria.add(Restrictions.eq(ServerConstants.STATUS,true));
		criteria.addOrder(Order.desc("id"));
		List<DeviceIp> deviceIp = criteria.list();
		for (Iterator iterator = deviceIp.iterator(); iterator.hasNext();) {
			 i++;
			 DeviceIp deviceIp1 = (DeviceIp) iterator.next();
			 DeviceIPVO deviceIPVOs = new DeviceIPVO();
			 deviceIPVOs.setSerialNumber(i);
			 deviceIPVOs.setDeviceType(deviceIp1.getDeviceType());
			 deviceIPVOs.setDeviceIP(deviceIp1.getDeviceIp());
			 deviceIPVOs.setUploadTime(IAmuseadminUtil.getDatetimeInWord(deviceIp1.getUploadTime()));
			 deviceIPVO.add(deviceIPVOs);
		}
		} catch (Exception e) {
			log.info("IamuseDashboardDaoImpl Method : getDeviceIPList ");
			log.error("Error getDeviceIPList",e);
		}
		return deviceIPVO;
	}
	
	@Override
	public String downloadImageName(int imageId,int userId) {
		String imageName="";
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UploadImage.class);
			criteria.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			criteria.add(Restrictions.eq(ServerConstants.IMAGE_ID, imageId));
			criteria.add(Restrictions.eq(ServerConstants.STATUS, true));
			UploadImage uploadImage = (UploadImage) criteria.uniqueResult();
			if(uploadImage!=null)
			{
				imageName=uploadImage.getImageName();
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("IamuseDashboardDaoImpl Method : downloadImageName ");
			log.error("Error downloadImageName",e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return imageName;	
		}
	
	@Override
	public List<ImageEmailFormVO> getEmailImagesZipList(String userId, String[] total) {
				List<ImageEmailFormVO> imageEmailFormVO = new ArrayList<>(); 
				try {
					sessionFactory.getCurrentSession().beginTransaction();
					String[] elements = total;   
					for (String s: elements) {           
					Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
					criteria.add(Restrictions.eq(ServerConstants.USER_ID, Integer.parseInt(userId)));
					criteria.add(Restrictions.eq(ServerConstants.STATUS,true));
					criteria.add(Restrictions.eq(ServerConstants.IS_DELETED,false));
					criteria.add(Restrictions.eq("id", Integer.parseInt(s)));
					BoothUploadImageEmail uploadImageEmail = (BoothUploadImageEmail) criteria.uniqueResult();
					if(uploadImageEmail !=null){
						ImageEmailFormVO imageEmailFormVO2=new ImageEmailFormVO();
						imageEmailFormVO2.setMailImageUrl(uploadImageEmail.getMailImageUrl());
						imageEmailFormVO2.setMailImageName(uploadImageEmail.getMailImageName());
						imageEmailFormVO2.setEventId(uploadImageEmail.getEventId());
						imageEmailFormVO.add(imageEmailFormVO2);
					}
					}
					sessionFactory.getCurrentSession().getTransaction().commit();
				}catch (Exception e) {
					log.info("IamuseDashboardDaoImpl Method : getEmailImagesZipList ");
					log.error("Error getEmailImagesZipList",e);
					sessionFactory.getCurrentSession().getTransaction().rollback();
			}
		return imageEmailFormVO;
	}
	
	@Override
	public String deleteMailedImage(String userId, String[] total) {
		String result="";
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			String[] elements = total;   
			for (String s: elements) {           
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
			criteria.add(Restrictions.eq(ServerConstants.USER_ID, Integer.parseInt(userId)));
			criteria.add(Restrictions.eq(ServerConstants.STATUS,true));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED,false));
			criteria.add(Restrictions.eq("id", Integer.parseInt(s)));
			BoothUploadImageEmail uploadImageEmail = (BoothUploadImageEmail) criteria.uniqueResult();
			if(uploadImageEmail !=null){
				uploadImageEmail.setIsDeleted(true);
				sessionFactory.getCurrentSession().update(uploadImageEmail);
			}}
			result="success";
			sessionFactory.getCurrentSession().getTransaction().commit();
		}catch (Exception e) {
			log.info("IamuseDashboardDaoImpl Method : deleteMailedImage ");
			log.error("Error deleteMailedImage",e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
	}
		return result;
	}
	
	@Override
	public List<ImageEmailFormVO> getEmailImagesShareList(String userId, String[] total) {
			List<ImageEmailFormVO> imageEmailFormVO = new ArrayList<>(); 
			try {
				sessionFactory.getCurrentSession().beginTransaction();
				String[] elements = total;   
				for (String s: elements) {           
					Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
					criteria.add(Restrictions.eq(ServerConstants.USER_ID, Integer.parseInt(userId)));
					criteria.add(Restrictions.eq(ServerConstants.STATUS,true));
					criteria.add(Restrictions.eq(ServerConstants.IS_DELETED,false));
					criteria.add(Restrictions.eq("id", Integer.parseInt(s)));
					BoothUploadImageEmail uploadImageEmail = (BoothUploadImageEmail) criteria.uniqueResult();
					if(uploadImageEmail !=null){
						ImageEmailFormVO imageEmailFormVO2=new ImageEmailFormVO();
						imageEmailFormVO2.setMailImageUrl(uploadImageEmail.getMailImageUrl()+"/"+uploadImageEmail.getEventId()+"/"+uploadImageEmail.getMailImageName());
						imageEmailFormVO.add(imageEmailFormVO2);
					}}
				sessionFactory.getCurrentSession().getTransaction().commit();
			}catch (Exception e) {
				log.info("IamuseDashboardDaoImpl Method : getEmailImagesShareList ");
				log.error("Error getEmailImagesShareList",e);
				sessionFactory.getCurrentSession().getTransaction().rollback();
			}
			return imageEmailFormVO;
		}
	
	
	@Override
	public List<ImageEmailFormVO> getEmailImagesList(String userId,Integer eventId)
	{
		int i=0;
		List<ImageEmailFormVO> imageEmailFormVO = new ArrayList<>();
		try {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
		criteria.add(Restrictions.eq(ServerConstants.STATUS,true));
		criteria.add(Restrictions.eq(ServerConstants.IS_DELETED,false));
		criteria.add(Restrictions.eq(ServerConstants.USER_ID, Integer.parseInt(userId)));
		criteria.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
		List<BoothUploadImageEmail> uploadImageEmail = criteria.list();
		for(BoothUploadImageEmail obj:uploadImageEmail){
			 	Criteria criteriaAdminBoothEvent = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
				criteriaAdminBoothEvent.add(Restrictions.eq("EId",obj.getEventId()));
				List<Adminboothevent> adminBoothEvent = criteriaAdminBoothEvent.list();
				if(adminBoothEvent !=null){
					ImageEmailFormVO imageEmailFormVOs;
					for (Adminboothevent adminboothevent2 : adminBoothEvent) {
						imageEmailFormVOs = new ImageEmailFormVO();
						imageEmailFormVOs.setUserName(obj.getGuestUserName());
						imageEmailFormVOs.setContactNo(obj.getGuestMobileNo());
						imageEmailFormVOs.setEventName(adminboothevent2.getEventName());
						imageEmailFormVOs.setEventDate(DateUtil.timeStampConvertIntoStringDateFormatYYYYMMDD(adminboothevent2.getCreatedDate()));
						imageEmailFormVOs.setSerialNumber(i);
						if(("1").equals(obj.getNewsletterOptIn())){
						imageEmailFormVOs.setSubscribed("Yes");
						}else{
							imageEmailFormVOs.setSubscribed("No");
						}
						imageEmailFormVOs.setNewsletterOptln(obj.getNewsletterOptIn());
						imageEmailFormVOs.setEmailId(obj.getEmailId());
						imageEmailFormVO.add(imageEmailFormVOs);
					} 
				}
		}
		} catch (Exception e) {
			log.info("IamuseDashboardDaoImpl Method : getEmailImagesList ");
			log.error("Error getEmailImagesList",e);
		}
		return imageEmailFormVO;
	}
	
	@Override
	public List<ImageEmailFormVO> getEmailImagesListBasedOnEventID(Integer userId, Integer eventId,String selectedEventName) {
 			List<ImageEmailFormVO> imageEmailFormVO = new ArrayList<>(); 
 			try {
 				Criteria criteriaBoothAdminLogin = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
 				criteriaBoothAdminLogin.add(Restrictions.eq(ServerConstants.USER_ID, userId));
 				BoothAdminLogin boothAdminLogin=(BoothAdminLogin) criteriaBoothAdminLogin.uniqueResult();
 				
			Criteria uploadList = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
			uploadList.add(Restrictions.eq(ServerConstants.EVENT_ID,eventId));
			if(!boothAdminLogin.getUserRole().equals("superadmin")){
				uploadList.add(Restrictions.eq(ServerConstants.USER_ID, userId));
			}
			List<BoothUploadImageEmail> list=uploadList.list();
			if(list !=null){
					ImageEmailFormVO imageEmailFormVOs;
					Criteria criteriaAdminBoothEvent = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
					criteriaAdminBoothEvent.add(Restrictions.eq("EId",eventId));
					Adminboothevent adminBoothEvent = (Adminboothevent)criteriaAdminBoothEvent.uniqueResult();
				
				for (BoothUploadImageEmail adminboothevent2 : list) {
						imageEmailFormVOs = new ImageEmailFormVO();
					 if(adminboothevent2.getGuestUserName()!=null){
						 imageEmailFormVOs.setUserName(adminboothevent2.getGuestUserName());
					 }else{
						 imageEmailFormVOs.setUserName("");
					 }
					 if(adminboothevent2.getGuestMobileNo()!=null){
						 imageEmailFormVOs.setContactNo(adminboothevent2.getGuestMobileNo());
					 }else{
						 imageEmailFormVOs.setContactNo("");
					 }	
					 imageEmailFormVOs.setEventName(selectedEventName +"  "+"("+DateUtil.timeStampConvertIntoStringDateFormatYYYYMMDD(adminBoothEvent.getCreatedDate())+")");
					 imageEmailFormVOs.setEmailId(adminboothevent2.getEmailId());
					 imageEmailFormVO.add(imageEmailFormVOs);
				} 
			}
			} catch (Exception e) {
				log.info("IamuseDashboardDaoImpl Method : getEmailImagesListBasedOnEventID ");
				log.error("Error getEmailImagesListBasedOnEventID",e);
			}
			return imageEmailFormVO;
	}
	
	
	@Override
	public List<ImageEmailFormVO> getEventImagesSummaryLists(String userId,Integer eventId) {
		List<ImageEmailFormVO> imageEmailFormVO = new ArrayList<>(); 
		int i=0;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Criteria criteriaBoothAdminLogin = sessionFactory.getCurrentSession().createCriteria(BoothAdminLogin.class);
			criteriaBoothAdminLogin.add(Restrictions.eq(ServerConstants.USER_ID, Integer.parseInt(userId)));
			BoothAdminLogin boothAdminLogin=(BoothAdminLogin) criteriaBoothAdminLogin.uniqueResult();
				
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
			if(!boothAdminLogin.getUserRole().equals("superadmin")){
			criteria.add(Restrictions.eq(ServerConstants.USER_ID, Integer.parseInt(userId)));
			}
			criteria.add(Restrictions.eq(ServerConstants.STATUS,true));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED,false));
			criteria.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			 ProjectionList projList = Projections.projectionList();
			 projList.add(Projections.property("picId"));
			 criteria.setProjection(Projections.distinct(projList));
			 List<Integer> mapping = criteria.list();
			for (Iterator iterator = mapping.iterator(); iterator.hasNext();) {
				 i++;
				 Integer eventMapping = (Integer) iterator.next();
				 
				 Criteria criteriaPicture = sessionFactory.getCurrentSession().createCriteria(AdminBoothEventPicture.class);
					criteriaPicture.add(Restrictions.eq("picId",eventMapping));
					AdminBoothEventPicture picture = (AdminBoothEventPicture) criteriaPicture.uniqueResult();
					if(picture !=null){
						ImageEmailFormVO imageEmailFormVOs = new ImageEmailFormVO();
						 imageEmailFormVOs.setSerialNumber(i);
						 imageEmailFormVOs.setMailImageUrl(picture.getPicName());
						 imageEmailFormVO.add(imageEmailFormVOs);
					}
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.info("IamuseDashboardDaoImpl Method : getEventImagesSummaryLists ");
			log.error("Error getEventImagesSummaryLists",e);
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		
		return imageEmailFormVO;
	}

	@Override
	public List<ImageEmailFormVO> getEmailImagesListCSV(String string, Integer eventId) {
		int i=0;
			List<ImageEmailFormVO> imageEmailFormVO = new ArrayList<>();
			List<ImageEmailFormVO> imageEmailFormVO1 = new ArrayList<>();
			try {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BoothUploadImageEmail.class);
			criteria.add(Restrictions.eq(ServerConstants.STATUS,true));
			criteria.add(Restrictions.eq(ServerConstants.IS_DELETED,false));
			criteria.add(Restrictions.eq(ServerConstants.USER_ID, Integer.parseInt(string)));
			criteria.add(Restrictions.eq(ServerConstants.EVENT_ID, eventId));
			List<BoothUploadImageEmail> uploadImageEmail = criteria.list();
			for(BoothUploadImageEmail obj:uploadImageEmail){
				 	Criteria criteriaAdminBoothEvent = sessionFactory.getCurrentSession().createCriteria(Adminboothevent.class);
					criteriaAdminBoothEvent.add(Restrictions.eq("EId",obj.getEventId()));
					List<Adminboothevent> adminBoothEvent = criteriaAdminBoothEvent.list();
					if(adminBoothEvent !=null){
						ImageEmailFormVO imageEmailFormVOs;
						for (Adminboothevent adminboothevent2 : adminBoothEvent) {
							imageEmailFormVOs = new ImageEmailFormVO();
							imageEmailFormVOs.setUserName(obj.getGuestUserName());
							imageEmailFormVOs.setContactNo(obj.getGuestMobileNo());
							//imageEmailFormVOs.setEventName(adminboothevent2.getEventName() +"  "+"("+DateUtils.timeStampConvertIntoStringDateFormatYYYYMMDD(adminboothevent2.getCreatedDate())+")");
							imageEmailFormVOs.setEventName(adminboothevent2.getEventName());
							imageEmailFormVOs.setEventDate(DateUtil.timeStampConvertIntoStringDateFormatYYYYMMDD(adminboothevent2.getCreatedDate()));
							imageEmailFormVOs.setSerialNumber(i);
							if(("1").equals(obj.getNewsletterOptIn())){
							imageEmailFormVOs.setSubscribed("Yes");
							}else{
								imageEmailFormVOs.setSubscribed("No");
							}
							imageEmailFormVOs.setNewsletterOptln(obj.getNewsletterOptIn());
							imageEmailFormVOs.setEmailId(obj.getEmailId());
							imageEmailFormVO.add(imageEmailFormVOs);
						} 
					}
			}
			} catch (Exception e) {
				log.info("IamuseDashboardDaoImpl Method : getEmailImagesList ");
				log.error("Error getEmailImagesList",e);
			}
			
			boolean flag=false;
			int count=0;
			for(ImageEmailFormVO obj:imageEmailFormVO)
			{
				flag=false;
				for(ImageEmailFormVO obj2:imageEmailFormVO1)
				{
					if(obj2.getEmailId().equals(obj.getEmailId()))
					{
						flag=true;
					}
				}
				
				if(flag==false)
				{
					imageEmailFormVO1.add(obj);
				}
			}
			for(ImageEmailFormVO obj:imageEmailFormVO1)
			{
				count=0;
				for(ImageEmailFormVO obj2:imageEmailFormVO)
				{

					if(obj2.getEmailId().equals(obj.getEmailId()))
					{
						count=count+1;
					}
				}
				obj.setTotalSession(Integer.toString(count));
			}
			
			return imageEmailFormVO1;
			}
}
