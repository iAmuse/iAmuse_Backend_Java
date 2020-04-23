package com.iamuse.admin.serviceimpl;


import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iamuse.admin.VO.CrashLogsFormVO;
import com.iamuse.admin.VO.DeviceIPVO;
import com.iamuse.admin.VO.ImageEmailFormVO;
import com.iamuse.admin.VO.ImageFormVO;
import com.iamuse.admin.dao.IamuseDashboardDao;
import com.iamuse.admin.entity.UploadImage;
import com.iamuse.admin.entity.UploadImageEmail;
import com.iamuse.admin.entity.Usermaster;
import com.iamuse.admin.service.IamuseDashboardService;

@Service
public class IamuseDashboardServiceImpl implements IamuseDashboardService{

	@Autowired
	IamuseDashboardDao iamuseDashboardDao;
	
	@Autowired SessionFactory sessionFactory;
	

	private static final Logger log = Logger.getLogger(IamuseDashboardService.class);
	
	@Override
	public List<ImageFormVO> getImagesList(String userId)
	{
		return iamuseDashboardDao.getImagesList(userId);
	}
	
	@Override
	public ImageFormVO downloadImage(int imageId,int userId)
	{
		ImageFormVO imageResponseVO=null;
			String imageUrl=iamuseDashboardDao.downloadImage(imageId,userId);
			String imageName=iamuseDashboardDao.downloadImageName(imageId,userId);
			if(!imageUrl.isEmpty())
			{
				imageResponseVO= new ImageFormVO();
				imageResponseVO.setImages(imageUrl);
				String fileName = imageUrl.substring(imageUrl.lastIndexOf("\\")+1);
				imageResponseVO.setImagename(imageName);
			}
		return imageResponseVO;
	}
	
	@Override
	public boolean updateRGBValue(ImageFormVO imageFormVO,String userId) {
		boolean result = false;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			result = iamuseDashboardDao.updateRGBValue(imageFormVO,userId);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			log.info("IamuseDashboardServiceImpl Method : updateRGBValue ");
			log.error("Error updateRGBValue",e);
		}
		return result;
	}
	
	@Override
	public boolean updateRGBValueMax(ImageFormVO imageFormVO,String userId) {
		boolean result = false;   
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			result = iamuseDashboardDao.updateRGBValueMax(imageFormVO,userId);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			log.info("IamuseDashboardServiceImpl Method : updateRGBValueMax ");
			log.error("Error updateRGBValueMax",e);
		}
		return result;
	}
	
	@Override
	public boolean resetRGBValueDefault(ImageFormVO imageFormVO,Integer userId) {
		boolean result = false;   
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			result = iamuseDashboardDao.resetRGBValueDefault(imageFormVO,userId);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			log.info("IamuseDashboardServiceImpl Method : updateDefaultRGB ");
			log.error("Error updateDefaultRGB",e);
		}
		return result;
	}
	
	@Override
	public boolean resetSystemDefaultRGBValue(String userId) {
		boolean result = false;   
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			result = iamuseDashboardDao.resetSystemDefaultRGBValue(userId);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			log.info("IamuseDashboardServiceImpl Method : resetSystemDefaultRGBValue ");
			log.error("Error resetSystemDefaultRGBValue",e);
		}
		return result;
	}
	
	@Override
	public UploadImage loadImageDetail(int imageId) {
		return  iamuseDashboardDao.loadImageDetail(imageId);
	}
	
	@Override
	public Usermaster getUsermasterDetails(String userId){
		 return  iamuseDashboardDao.getUsermasterDetails(userId);
	}
	 
	 @Override
	 public boolean deleteImage(int imageId) {
			return iamuseDashboardDao.deleteImage(imageId);
		}

	 @Override
	 public boolean updateDefaultRGB(ImageFormVO imageFormVO, String userId) {
	 		boolean result=false;
	 		try {
	 			result=iamuseDashboardDao.updateDefaultRGB(imageFormVO,userId);
	 		} catch (Exception e) {
	 			log.info("IamuseDashboardServiceImpl Method : updateDefaultRGB ");
				log.error("Error updateDefaultRGB",e);
	 		}
	 		return result;
	 	}
	 
	 @Override
		public List<ImageEmailFormVO> getEmailImagesList(String userId,Integer eventId)
		{
			List<ImageEmailFormVO> imageEmailFormVO = null;
			try {
				sessionFactory.getCurrentSession().beginTransaction();
				imageEmailFormVO =iamuseDashboardDao.getEmailImagesList(userId,eventId);
				sessionFactory.getCurrentSession().getTransaction().commit();
			} catch (Exception e) {
				sessionFactory.getCurrentSession().getTransaction().rollback();
				log.info("IamuseDashboardServiceImpl Method : getEmailImagesList ");
				log.error("Error getEmailImagesList",e);
			}
			return imageEmailFormVO;
		}
		
		@Override
		 public boolean deleteEmailImage(int imageId) {
				return iamuseDashboardDao.deleteEmailImage(imageId);
			}
		 
		 @Override
		public UploadImageEmail emailImage(int id) {
			UploadImageEmail uploadImageEmail = null;
			try {
				uploadImageEmail = iamuseDashboardDao.emailImage(id);
			} catch (Exception e) {
				log.info("IamuseDashboardServiceImpl Method : emailImage ");
				log.error("Error emailImage",e);
			}
			return uploadImageEmail;
		}
		
		@Override
		public List<CrashLogsFormVO> getCrashLogsList(String userId) {
			List<CrashLogsFormVO> crashLogsFormVO = null;
			try {
				sessionFactory.getCurrentSession().beginTransaction();
				crashLogsFormVO =iamuseDashboardDao.getCrashLogsList(userId);
				sessionFactory.getCurrentSession().getTransaction().commit();
			} catch (Exception e) {
				sessionFactory.getCurrentSession().getTransaction().rollback();
				log.info("IamuseDashboardServiceImpl Method : getCrashLogsList ");
				log.error("Error getCrashLogsList",e);
			}
			return crashLogsFormVO;
		}

		@Override
		public Boolean updateEmailId(int id, String emailId) {
			return iamuseDashboardDao.updateEmailId(id,emailId);
		}
		
		@Override
		public CrashLogsFormVO downloadCrashFile(String id) {
			CrashLogsFormVO crashLogsFormVO=null;
			String fileUrl=iamuseDashboardDao.downloadCrashFile(id);
			if(!fileUrl.isEmpty())
			{
				String[] a=fileUrl.split(";");
				crashLogsFormVO= new CrashLogsFormVO();
				crashLogsFormVO.setFileUrl(a[0]);
				crashLogsFormVO.setFileName(a[1]);
			}
		return crashLogsFormVO;
		}
		
		@Override
		public boolean isRead(int id, String string) {
			return iamuseDashboardDao.isRead(id,"");
		}
		
		@Override
		public Integer deleteSelected(int id, String page) {
			return iamuseDashboardDao.deleteSelected(id,page);
		}
		
		@Override
		public List<ImageEmailFormVO> getUserEmailListForExport(String dateFrom, String dateTo, int userId) {
			List<ImageEmailFormVO> imageEmailFormVO = null;
			try {
				sessionFactory.getCurrentSession().beginTransaction();
				imageEmailFormVO =iamuseDashboardDao.getUserEmailListForExport(dateFrom,dateTo,userId);
				sessionFactory.getCurrentSession().getTransaction().commit();
			} catch (Exception e) {
				sessionFactory.getCurrentSession().getTransaction().rollback();
				log.info("IamuseDashboardServiceImpl Method : getUserEmailListForExport ");
				log.error("Error getUserEmailListForExport",e);
			}
			return imageEmailFormVO;
		}

		@Override
		public boolean clearList(String userId) {
				return iamuseDashboardDao.clearList(userId);
			}
		
		@Override
		public List<DeviceIPVO> getDeviceIPList(String userId) {
			List<DeviceIPVO> deviceIPVOs = null;
			try {
				sessionFactory.getCurrentSession().beginTransaction();
				deviceIPVOs =iamuseDashboardDao.getDeviceIPList(userId);
				sessionFactory.getCurrentSession().getTransaction().commit();
			} catch (Exception e) {
				sessionFactory.getCurrentSession().getTransaction().rollback();
				log.info("IamuseDashboardServiceImpl Method : getDeviceIPList ");
				log.error("Error getDeviceIPList",e);
			}
			return deviceIPVOs;
		}
		
		@Override
		public List<ImageEmailFormVO> getEmailImagesLists(String string, Integer eventId, int pageid, int total) {
			return iamuseDashboardDao.getEmailImagesLists(string,eventId,pageid,total);
		}
		
		@Override
		public List<ImageEmailFormVO> getEmailImagesZipList(String string, String[] total) {
			return iamuseDashboardDao.getEmailImagesZipList(string , total);
		}
		
		@Override
		public String deleteMailedImage(String string, String[] total) {
			return iamuseDashboardDao.deleteMailedImage(string , total);
		}
		
		@Override
		public List<ImageEmailFormVO> getEmailImagesShareList(String userId, String[] total) {
			return iamuseDashboardDao.getEmailImagesShareList(userId,total);
		}
		
		@Override
		public List<ImageEmailFormVO> getEmailImagesListBasedOnEventID(Integer userId, Integer eventId,
				String selectedEventName) {
			List<ImageEmailFormVO> imageEmailFormVO = null;
			try {
				sessionFactory.getCurrentSession().beginTransaction();
				imageEmailFormVO =iamuseDashboardDao.getEmailImagesListBasedOnEventID(userId,eventId,selectedEventName);
				sessionFactory.getCurrentSession().getTransaction().commit();
			} catch (Exception e) {
				sessionFactory.getCurrentSession().getTransaction().rollback();
				log.info("IamuseDashboardServiceImpl Method : getEmailImagesList ");
				log.error("Error getEmailImagesList",e);
			}
			return imageEmailFormVO;
		}
		
		@Override
		public List<ImageEmailFormVO> getEventImagesSummaryLists(String userId, Integer eventId) {
			return iamuseDashboardDao.getEventImagesSummaryLists(userId,eventId);
		}

		@Override
		public List<ImageEmailFormVO> getEmailImagesListCSV(String string,
				Integer eventId) {
			List<ImageEmailFormVO> imageEmailFormVO = null;
			try {
				sessionFactory.getCurrentSession().beginTransaction();
				imageEmailFormVO =iamuseDashboardDao.getEmailImagesListCSV(string,eventId);
				sessionFactory.getCurrentSession().getTransaction().commit();
			} catch (Exception e) {
				sessionFactory.getCurrentSession().getTransaction().rollback();
				log.info("IamuseDashboardServiceImpl Method : getEmailImagesListCSV ");
				log.error("Error getEmailImagesList",e);
			}
			return imageEmailFormVO;
		}


}



