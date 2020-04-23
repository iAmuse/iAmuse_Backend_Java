package com.iamuse.admin.dao;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
import com.iamuse.admin.entity.AdminBoothEventPicture;
import com.iamuse.admin.entity.Adminboothevent;
import com.iamuse.admin.entity.BackgroundImages;
import com.iamuse.admin.entity.BoothAdminLogin;
import com.iamuse.admin.entity.BoothUploadImageEmail;
import com.iamuse.admin.entity.DeviceRegistration;
import com.iamuse.admin.entity.Fovbyuser;
import com.iamuse.admin.entity.SubscriptionMaster;
import com.iamuse.admin.entity.TokenClsss;
import com.iamuse.admin.entity.UploadImage;

public interface BoothAdminDao {

	SignInVO createBoothAdmin(SignInVO signInVO);

	List<SubscriptionMaster> getSubscriptionList();

	List<DeviceRegistration> getRegisteredDevice(Integer userId);

	List<EventVO> getEventList(Integer userId, int pageid, int total, Integer subId);

	EventVO saveCreateEvent(EventVO eventVO, Integer userId);

	Adminboothevent getCurrentEvent(Integer userId, EventVO eventVO);

	public AdminPictureVO uploadBackgroundImage(AdminPictureVO adminPictureVO, MultipartFile[] files, String rootPath,MultipartFile thankyoufiles,MultipartFile lookAtTouchScreen,MultipartFile cameraTVScreenSaver,MultipartFile waterMarkImage,String default4Images);

	List<AdminPictureVO> getPicList(Integer eId, Integer userId);

	AdminPictureVO getImageConfigure(Integer picId, Integer userId, Integer picId2);

	String saveCoordinatesOfImg(AdminPictureVO adminPictureVO, MultipartFile files, String rootPath);

	BoothAdminLogin getProfileDetails(Integer userId);

	String updateProfileDetails(Integer userId, SignInVO signInVO);

	SubscriptionMaster getSubscriptionListById(Integer id);

	TransactionReceiptVO setTransactionHistoryOfSubscription(Integer userId, HttpServletRequest request, String res, String cmd);

	String updatePreviousSubscription(Integer userId);

	String advanceBoothSetUp(SignInVO signInVO);

	String deleteEvent(Integer eid, Integer userId);

	TransactionReceiptVO getTransactionDetails(String trsId, String subId);

	int getCount(Integer userId);

	int getEventCount(Integer userId);

	OptionsReports getEventReportDetails(Integer userId, Integer eventId,String emailId);
	
	OptionsReports getEventReportDetails(Integer userId, Integer eventId);

	List<BoothUploadImageEmail> dbToCsv(Integer userId, int p, int total);

	String resendEmailImages(String userId, String[] total,HttpServletRequest request);

	String syncDevice(Integer userId, Integer deviceId);

	EventVO getEventDetails(Integer userId, Integer eventId);

	
	//start added by abhishek 13-12-2016
	
	public List<SubscriptionMaster> getSubscriptionListSA();

	public SubscriptionMaster getSubscriptionListByIdForDeactive(Integer id);

	public SubscriptionMaster getSubscriptionListByIdForActive(Integer id);

	public List<SubscriptionMaster> getSubscriptionListSAWithPagination(int pageid, int total);
	//end added by abhishek 13-12-2016

	List<EventVO> getEventListDefault();

	OptionsReports getEventReportDetailsDefault(Integer userId, Integer eventId, Integer defaultId);

	boolean checkDefaultAlreadyExits(EventVO eventVO);

	SubscriptionMaster getSubscription(Integer subId);

	List<EventVO> getEvents(Integer userId);

	boolean setAdminDetails(EventVO eventVO);

	public Adminboothevent getFOVValueBasedOnEvent(int eventId);

	List<TransactionHistoryVO> getTransactionHistory(Integer userId);

	public List<EventVO> getEventsWithDelete(Integer userId);

	Adminboothevent getEventDetails(Integer eId);

	UploadImage getCurrentImages(Integer userId);

	boolean updateMaskingImageStatus(Integer pictureId);

	boolean updateWaterMarkStatus(Integer pictureId);

	UploadImage getCurrentImagesClicked(Integer userId, int imageId);

	String sendIndividualMailImage(String emailId, Integer imgId, HttpServletRequest request);

	int getCountByEvent(Integer userId, Integer eventId);

	String deletEventSinglePicture(String picId, Integer eventId, HttpServletRequest request);

	String advanceBoothSetUpConfig(SignInVO signInVO);

	List<SubscriptionMaster> getSubscriptionDetails();

	String setShareValue(int userId, String[] imagesIdList);

	String setTwitterShareValue(int userId, String[] imagesIdList);

	public	AdminPictureVO editUploadBackgroundImage(AdminPictureVO adminPictureVO, MultipartFile[] files, String rootPath);

	public List<ImageEmailFormVO> getPreSetBackGrounds(Integer userId);

	PaginationVO getFirstLast(Integer eId, Integer picId);

	Fovbyuser getFovTableData(Integer userId);

	SignInVO getImageData(Integer userId);

	String saveZoomScale(Integer userId, SignInVO signInVO);

	AdminPictureVO updateWaterMarkLookAtTouchThankYouCameraScreen(AdminPictureVO adminPictureVO, MultipartFile[] files,
			String rootPath, MultipartFile thankyoufiles, MultipartFile lookAtTouchScreen,
			MultipartFile cameraTVScreenSaver, MultipartFile waterMarkImage);

	List<ImageEmailFormVO> getPreSetThankYouScreenBasedOnEventId(Integer userId, Integer eid, Integer subId);

	List<ImageEmailFormVO> getPreSetWaterMarkImageBasedOnEventId(Integer userId, Integer eid, Integer subId);

	List<ImageEmailFormVO> getPreSetLookAtTouchScreenBasedOnEventId(Integer userId, Integer eid, Integer subId);

	List<ImageEmailFormVO> getPreSetCameraTVScreenSaverBasedOnEventId(Integer userId, Integer eid, Integer subId);

	List<ImageEmailFormVO> getPreSetThankYouScreen(Integer userId);

	List<ImageEmailFormVO> getPreSetWaterMarkImageScreen(Integer userId);

	List<ImageEmailFormVO> getPreSetLookAtTouchScreen(Integer userId);

	List<ImageEmailFormVO> getPreSetCameraTVScreen(Integer userId);

	List<ImageEmailFormVO> getPreSetWaterMarkImageTouchScreenCameraTVThankYouScreen(Integer userId);

	List<DeviceRegistration> getRegisteredDevicePush(Integer userId);

	List<AdminBoothEventPicture> notConfiguredImage(Integer eid, Integer userId);

	DeviceVO grtDeviceTockenAndIP(Integer userId);

	String updateEventDate(Integer eventId);
	
	Boolean deleteUserProfile(Integer userId, String rootPath);
	
	AccessToken GetAccessToken(Integer userId);
	
	public BoothAdminLogin updateSubsctiptionForAdmin(Integer userId,Integer subId);
	
	public boolean addDataAccessToken(TokenClsss token);
	
	public void removeAccessToken(TokenClsss token);
	
	
}
