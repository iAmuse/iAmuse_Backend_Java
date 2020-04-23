package com.iamuse.admin.service;

import java.util.List;

import com.iamuse.admin.VO.CrashLogsFormVO;
import com.iamuse.admin.VO.DeviceIPVO;
import com.iamuse.admin.VO.ImageEmailFormVO;
import com.iamuse.admin.VO.ImageFormVO;
import com.iamuse.admin.entity.UploadImage;
import com.iamuse.admin.entity.UploadImageEmail;
import com.iamuse.admin.entity.Usermaster;

public interface IamuseDashboardService {

	 	public List<ImageFormVO> getImagesList(String userId);
		public ImageFormVO downloadImage(int imageId,int userId);
		public boolean updateRGBValue(ImageFormVO imageFormVO,String userId);
		public UploadImage loadImageDetail(int imageId);
		public boolean updateRGBValueMax(ImageFormVO imageFormVO,String userId);
		public boolean resetRGBValueDefault(ImageFormVO imageFormVO, Integer integer);
		public boolean resetSystemDefaultRGBValue(String userId);
		public Usermaster getUsermasterDetails(String userId);
		public boolean deleteImage(int imageId);
		public boolean updateDefaultRGB(ImageFormVO imageFormVO, String userId);
		public List<ImageEmailFormVO> getEmailImagesList(String userId,Integer eventId);
		public boolean deleteEmailImage(int imageId);
		public UploadImageEmail emailImage(int id);
		public List<CrashLogsFormVO> getCrashLogsList(String userId);
		public Boolean updateEmailId(int parseInt, String emailId);
		public CrashLogsFormVO downloadCrashFile(String id);
		public boolean isRead(int parseInt, String string);
		public Integer deleteSelected(int parseInt, String page);
		public List<ImageEmailFormVO> getUserEmailListForExport(String dateFrom,String dateTo, int userId);
		public boolean clearList(String userId);
		public List<DeviceIPVO> getDeviceIPList(String userId);
		public List<ImageEmailFormVO> getEmailImagesLists(String string, Integer eventId, int pageid, int total);
		public List<ImageEmailFormVO> getEmailImagesZipList(String string, String[] total);
		public String deleteMailedImage(String string, String[] total);
		public List<ImageEmailFormVO> getEmailImagesShareList(String string, String[] total);
		public List<ImageEmailFormVO> getEmailImagesListBasedOnEventID(Integer userId, Integer eventId,String selectedEventName);
		public List<ImageEmailFormVO> getEventImagesSummaryLists(String userId, Integer eventId);
		public List<ImageEmailFormVO> getEmailImagesListCSV(String string,
				Integer eventId);

	
	
}
