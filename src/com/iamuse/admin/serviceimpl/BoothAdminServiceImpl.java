package com.iamuse.admin.serviceimpl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.iamuse.admin.VO.AdminPictureVO;
import com.iamuse.admin.VO.DeviceVO;
import com.iamuse.admin.VO.EventVO;
import com.iamuse.admin.VO.ImageEmailFormVO;
import com.iamuse.admin.VO.OptionsReports;
import com.iamuse.admin.VO.PaginationVO;
import com.iamuse.admin.VO.SignInVO;
import com.iamuse.admin.VO.TransactionHistoryVO;
import com.iamuse.admin.VO.TransactionReceiptVO;
import com.iamuse.admin.entity.AccessToken;
import com.iamuse.admin.dao.BoothAdminDao;
import com.iamuse.admin.entity.AdminBoothEventPicture;
import com.iamuse.admin.entity.Adminboothevent;
import com.iamuse.admin.entity.BoothAdminLogin;
import com.iamuse.admin.entity.BoothUploadImageEmail;
import com.iamuse.admin.entity.DeviceRegistration;
import com.iamuse.admin.entity.Fovbyuser;
import com.iamuse.admin.entity.SubscriptionMaster;
import com.iamuse.admin.entity.TokenClsss;
import com.iamuse.admin.entity.UpdateSubscriptionReq;
import com.iamuse.admin.entity.UploadImage;
import com.iamuse.admin.service.BoothAdminService;

@Service
@Transactional
public class BoothAdminServiceImpl implements BoothAdminService {
	@Autowired BoothAdminDao boothAdminDao;
	@Autowired SessionFactory sessionFactory;

	@Override
	public SignInVO createBoothAdmin(SignInVO signInVO) {
		return boothAdminDao.createBoothAdmin(signInVO);
	}

	@Override
	public List<SubscriptionMaster> getSubscriptionList() {
		return	boothAdminDao.getSubscriptionList();
	}

	@Override
	public List<DeviceRegistration> getRegisteredDevice(Integer userId) {
			return boothAdminDao.getRegisteredDevice(userId);
	}

	@Override
	public List<EventVO> getEventList(Integer userId, int pageid, int total, Integer subId) {
		return boothAdminDao.getEventList(userId,pageid,total,subId);
	}

	@Override
	public EventVO saveCreateEvent(EventVO eventVO, Integer userId) {
		return boothAdminDao.saveCreateEvent(eventVO,userId);
	}

	@Override
	public Adminboothevent getCurrentEvent(Integer userId,EventVO eventVO) {
		return boothAdminDao.getCurrentEvent(userId,eventVO);
	}

	@Override
	public AdminPictureVO uploadBackgroundImage(AdminPictureVO adminPictureVO,MultipartFile[] files, String rootPath,MultipartFile thankyoufiles,MultipartFile lookAtTouchScreen,MultipartFile cameraTVScreenSaver,MultipartFile waterMarkImage,String default4Images) {
		return boothAdminDao.uploadBackgroundImage(adminPictureVO,files,rootPath,thankyoufiles, lookAtTouchScreen, cameraTVScreenSaver, waterMarkImage,default4Images);
	}

	@Override
	public List<AdminPictureVO> getPicList(Integer eId, Integer userId) {
		return boothAdminDao.getPicList(eId,userId);
	}

	@Override
	public AdminPictureVO getImageConfigure(Integer picId, Integer userId, Integer subId) {
		return boothAdminDao.getImageConfigure(picId,userId,subId);
	}

	@Override
	public String saveCoordinatesOfImg(AdminPictureVO adminPictureVO,MultipartFile files,String rootPath) {
		return boothAdminDao.saveCoordinatesOfImg(adminPictureVO,files,rootPath);
	}

	@Override
	public BoothAdminLogin getProfileDetails(Integer userId) {
		return boothAdminDao.getProfileDetails(userId);
	}

	@Override
	public String updateProfileDetails(Integer userId, SignInVO signInVO) {
		return boothAdminDao.updateProfileDetails(userId,signInVO);
	}

	@Override
	public SubscriptionMaster getSubscriptionListById(Integer id){
		return boothAdminDao.getSubscriptionListById(id);
	}

	@Override
	public TransactionReceiptVO setTransactionHistoryOfSubscription(Integer userId, HttpServletRequest request, String res, String cmd) {
		return boothAdminDao.setTransactionHistoryOfSubscription(userId,request,res,cmd);
	}

	@Override
	public String updatePreviousSubscription(Integer userId) {
		return boothAdminDao.updatePreviousSubscription(userId);
	}

	@Override
	public String advanceBoothSetUp(SignInVO signInVO) {
		return boothAdminDao.advanceBoothSetUp(signInVO);
	}

	@Override
	public String deleteEvent(Integer eid, Integer userId) {
		return boothAdminDao.deleteEvent(eid,userId);
	}

	@Override
	public TransactionReceiptVO getTransactionDetails(String trsId, String subId) {
		return boothAdminDao.getTransactionDetails(trsId,subId);
	}

	@Override
	public int getCount(Integer userId) {
		return boothAdminDao.getCount(userId);
	}

	@Override
	public int getEventCount(Integer userId) {
		return boothAdminDao.getEventCount(userId);
	}

	@Override
	public OptionsReports getEventReportDetails(Integer userId, Integer eventId,String emailId) {
		return boothAdminDao.getEventReportDetails(userId,eventId,emailId);
	}
	
	@Override
	public OptionsReports getEventReportDetails(Integer userId, Integer eventId) {
		return boothAdminDao.getEventReportDetails(userId,eventId);
	}

	@Override
	public List<BoothUploadImageEmail> dbToCsv(Integer userId, int p, int total) {
		return boothAdminDao.dbToCsv(userId,p,total);
	}

	@Override
	public String resendEmailImages(String userId, String[] total,HttpServletRequest request) {
		return boothAdminDao.resendEmailImages(userId,total,request);
	}

	@Override
	public String syncDevice(Integer userId, Integer deviceId) {
		return boothAdminDao.syncDevice(userId,deviceId);
	}

	@Override
	public EventVO getEventDetails(Integer userId, Integer eventId) {
		return boothAdminDao.getEventDetails(userId,eventId);
	}

	@Override
	public List<SubscriptionMaster> getSubscriptionListSA() {
		return	boothAdminDao.getSubscriptionListSA();
	}

	@Override
	public SubscriptionMaster getSubscriptionListByIdForDeactive(Integer id) {
		return boothAdminDao.getSubscriptionListByIdForDeactive(id);
	}

	@Override
	public SubscriptionMaster getSubscriptionListByIdForActive(Integer id) {
		return boothAdminDao.getSubscriptionListByIdForActive(id);
	}

	@Override
	public List<SubscriptionMaster> getSubscriptionListSAWithPagination(int pageid, int total) {
		return	boothAdminDao.getSubscriptionListSAWithPagination(pageid,total);
	}

	@Override
	public List<EventVO> getEventListDefault() {
		return boothAdminDao.getEventListDefault();
	}

	@Override
	public OptionsReports getEventReportDetailsDefault(Integer userId, Integer eventId, Integer defaultId) {
		return boothAdminDao.getEventReportDetailsDefault(userId,eventId,defaultId);
	}

	@Override
	public boolean checkDefaultAlreadyExits(EventVO eventVO) {
		return boothAdminDao.checkDefaultAlreadyExits(eventVO);
	}

	@Override
	public SubscriptionMaster getSubscription(Integer subId) {
		return boothAdminDao.getSubscription(subId);
	}

	@Override
	public List<EventVO> getEvents(Integer userId) {
		return boothAdminDao.getEvents(userId);
	}

	@Override
	public boolean setAdminDetails(EventVO eventVO) {
		return boothAdminDao.setAdminDetails(eventVO);
	}

	@Override
	public Adminboothevent getFOVValueBasedOnEvent(int eventId) {
		return boothAdminDao.getFOVValueBasedOnEvent(eventId);
	}

	@Override
	public List<TransactionHistoryVO> getTransactionHistory(Integer userId) {
		return boothAdminDao.getTransactionHistory(userId);
	}

	@Override
	public List<EventVO> getEventsWithDelete(Integer userId) {
		return boothAdminDao.getEventsWithDelete(userId);
	}

	@Override
	public Adminboothevent getEventDetails(Integer eId) {
		return boothAdminDao.getEventDetails(eId);
	}

	@Override
	public UploadImage getCurrentImages(Integer userId) {
		return boothAdminDao.getCurrentImages(userId);
	}

	@Override
	public boolean updateMaskingImageStatus(Integer pictureId) {
		return boothAdminDao.updateMaskingImageStatus(pictureId);
		
	}

	@Override
	public boolean updateWaterMarkStatus(Integer pictureId) {
		return boothAdminDao.updateWaterMarkStatus(pictureId);
	}

	@Override
	public UploadImage getCurrentImagesClicked(Integer userId, int imageId) {
		return boothAdminDao.getCurrentImagesClicked(userId,imageId);
	}

	@Override
	public String sendIndividualMailImage(String emailId, Integer imgId, HttpServletRequest request) {
		return boothAdminDao.sendIndividualMailImage(emailId,imgId,request);
	}

	@Override
	public int getCountByEvent(Integer userId, Integer eventId) {
		return boothAdminDao.getCountByEvent(userId,eventId);
	}

	@Override
	public String deletEventSinglePicture(String picId, Integer eventId, HttpServletRequest request) {
		return boothAdminDao.deletEventSinglePicture(picId,eventId,request);
	}

	@Override
	public String advanceBoothSetUpConfig(SignInVO signInVO) {
		return boothAdminDao.advanceBoothSetUpConfig(signInVO);
	}

	@Override
	public List<SubscriptionMaster> getSubscriptionDetails() {
		return boothAdminDao.getSubscriptionDetails();
	}

	@Override
	public String setShareValue(int userId, String[] imagesIdList) {
		return  boothAdminDao.setShareValue(userId,imagesIdList);
	}

	@Override
	public String setTwitterShareValue(int userId, String[] imagesIdList) {
		return boothAdminDao.setTwitterShareValue(userId,imagesIdList);
	}

	@Override
	public AdminPictureVO editUploadBackgroundImage(AdminPictureVO adminPictureVO, MultipartFile[] files, String rootPath) {
		return boothAdminDao.editUploadBackgroundImage(adminPictureVO,files,rootPath);
	}

	@Override
	public List<ImageEmailFormVO> getPreSetBackGrounds(Integer userId) {
		return boothAdminDao.getPreSetBackGrounds(userId);
	}

	@Override
	public PaginationVO getFirstLast(Integer eId, Integer picId) {
		return boothAdminDao.getFirstLast(eId,picId);
	}

	@Override
	public Fovbyuser getFovTableData(Integer userId) {
		return boothAdminDao.getFovTableData(userId);
	}

	@Override
	public SignInVO getImageData(Integer userId) {
		return boothAdminDao.getImageData(userId);
	}

	@Override
	public String saveZoomScale(Integer userId, SignInVO signInVO) {
		return boothAdminDao.saveZoomScale(userId,signInVO);
	}

	@Override
	public AdminPictureVO updateWaterMarkLookAtTouchThankYouCameraScreen(AdminPictureVO adminPictureVO,MultipartFile[] files, String rootPath, MultipartFile thankyoufiles, MultipartFile lookAtTouchScreen,MultipartFile cameraTVScreenSaver, MultipartFile waterMarkImage) {
		return boothAdminDao.updateWaterMarkLookAtTouchThankYouCameraScreen( adminPictureVO, files,  rootPath,  thankyoufiles,  lookAtTouchScreen, cameraTVScreenSaver,  waterMarkImage);
	}

	@Override
	public List<ImageEmailFormVO> getPreSetThankYouScreenBasedOnEventId(Integer userId, Integer eid, Integer subId) {
		return boothAdminDao.getPreSetThankYouScreenBasedOnEventId( userId,  eid, subId);
	}

	@Override
	public List<ImageEmailFormVO> getPreSetWaterMarkImageBasedOnEventId(Integer userId, Integer eid, Integer subId) {
		return boothAdminDao.getPreSetWaterMarkImageBasedOnEventId( userId,  eid, subId);
	}

	@Override
	public List<ImageEmailFormVO> getPreSetLookAtTouchScreenBasedOnEventId(Integer userId, Integer eid, Integer subId) {
		return boothAdminDao.getPreSetLookAtTouchScreenBasedOnEventId( userId,  eid, subId);
	}

	@Override
	public List<ImageEmailFormVO> getPreSetCameraTVScreenSaverBasedOnEventId(Integer userId, Integer eid, Integer subId) {
		return boothAdminDao.getPreSetCameraTVScreenSaverBasedOnEventId( userId,  eid, subId);
	}

	@Override
	public List<ImageEmailFormVO> getPreSetThankYouScreen(Integer userId) {
		return boothAdminDao.getPreSetThankYouScreen( userId);
	}

	@Override
	public List<ImageEmailFormVO> getPreSetWaterMarkImageScreen(Integer userId) {
		return boothAdminDao.getPreSetWaterMarkImageScreen( userId);
	}

	@Override
	public List<ImageEmailFormVO> getPreSetLookAtTouchScreen(Integer userId) {
		return boothAdminDao.getPreSetLookAtTouchScreen( userId);
	}

	@Override
	public List<ImageEmailFormVO> getPreSetCameraTVScreen(Integer userId) {
		return boothAdminDao.getPreSetCameraTVScreen( userId);
	}

	@Override
	public List<ImageEmailFormVO> getPreSetWaterMarkImageTouchScreenCameraTVThankYouScreen(Integer userId) {
		return boothAdminDao.getPreSetWaterMarkImageTouchScreenCameraTVThankYouScreen( userId);
	}

	@Override
	public List<DeviceRegistration> getRegisteredDevicePush(Integer userId) {
		return boothAdminDao.getRegisteredDevicePush(userId);
	}

	@Override
	public List<AdminBoothEventPicture> notConfiguredImage(Integer eid, Integer userId) {
		return boothAdminDao.notConfiguredImage(eid,userId);
	}

	@Override
	public DeviceVO grtDeviceTockenAndIP(Integer userId) {
		return boothAdminDao.grtDeviceTockenAndIP(userId);
	}

	@Override
	public String updateEventDate(Integer eventId) {
		return boothAdminDao.updateEventDate(eventId);
	}

	@Override
	public Boolean deleteUserProfile(Integer userId, String rootPath) {
		return boothAdminDao.deleteUserProfile(userId, rootPath);
	}	
	
	
	@Override
	public AccessToken GetAccessToken(Integer userId) {
		return boothAdminDao.GetAccessToken(userId);
	}
	
	@Override 
	public BoothAdminLogin updateSubscriptionFormAdmin(UpdateSubscriptionReq updateSubscriptionReq) 
	{
		  return boothAdminDao.updateSubsctiptionForAdmin(updateSubscriptionReq.getUserid(),Integer.parseInt(updateSubscriptionReq.getSubId())); 
	}

	@Override 
	public boolean addDataAccessToken(TokenClsss token) {
		
		 boolean result= false;	
		 result =boothAdminDao.addDataAccessToken(token);
		 return true;
	}
	
	
	@Override
	public void removeAccessToken(TokenClsss token)
	{
		boothAdminDao.removeAccessToken(token);
	}
	
}
