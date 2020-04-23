package com.amuse.server.dao;



import java.util.List;

import com.iamuse.server.entity.AccessToken;
import com.iamuse.server.entity.Adminboothevent;
import com.iamuse.server.entity.BoothAdminLogin;
import com.iamuse.server.entity.DeviceIp;
import com.iamuse.server.entity.DeviceRegistration;
import com.iamuse.server.entity.Fovbyuser;
import com.iamuse.server.entity.SubscriptionMaster;
import com.iamuse.server.entity.TransactionMaster;
import com.iamuse.server.entity.UploadImage;
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
import com.iamuse.server.responseVO.BaseResponseVO;
import com.iamuse.server.responseVO.EventFetchingBaseResponseVO;
import com.iamuse.server.responseVO.LoginBaseResponseVO;
import com.iamuse.server.util.PushNotificationTaskRestart;



public interface UserDao {

	public void removeAccessToken(TokenClsss token);
	public boolean addDataAccessToken(TokenClsss token);
	public BoothAdminLogin updateSubsctiptionForAdmin(Integer userId,Integer subId);
	public BoothAdminLogin updateSubscriptionPlan(Integer userId,Integer subId,String paymentAmount,Integer transactionId);
	/* public List<GetAllUser> getUserList(int userid); */
	
	public Integer uploadImage(String image);
	public boolean updateImageName(Integer imageName,String url,String userId);
	public BoothAdminLogin getDefaultRGBValue(RGBValueRequestVO rgbValueRequestVO);
	public boolean saveDeviceToken(DeviceTokenRequestVO deviceTokenRequestVO);
	public UploadImage getImageDetails(int imageId);
	public Integer uploadImageWithEmailId(String image,UploadImageWithEmailRequestVO uploadImageWithEmailRequestVO);
	public Integer updateImageNameForEmailId(String name, String userId,Integer eventId, String string,
	UploadImageWithEmailRequestVO uploadImageWithEmailRequestVO, Integer defaultId, Integer picId);
	public boolean updateEmailSendTime(String imageName,String userId);
	public Integer crashlogsupload(String url);
	public boolean crashlogsuploadName(Integer id, String fileName, String userId);
	public boolean saveDeviceIP(DeviceIPRequestVO deviceIPRequestVO);
	public DeviceIp getDeviceIP(DeviceIPRequestVO deviceIPRequestVO);
	public LoginBaseResponseVO saveAdminBoothRegistration(BoothAdminRegistrationRequestVO adminBoothRegistrationRequestVO);
	public LoginBaseResponseVO saveGmailBoothRegistration(BoothAdminRegistrationRequestVO adminBoothRegistrationRequestVO);
	public LoginBaseResponseVO fetchLoginBaseResponseVO(LoginBoothAdminRegistrationRequestVO loginRegistrationRequestVO);
	public LoginBaseResponseVO fetchGmailLoginBaseResponseVO(LoginBoothAdminRegistrationRequestVO loginRegistrationRequestVO);
	public EventFetchingBaseResponseVO fetchEventFetchingAdminBooth(FetchingEventListRequestVO fetchinfEventAdminBoothRequestVo);
	public String deviceRegisterSevice(DeviceRegistrationRequestVO deviceRegistrationRequestVO);
	public SubscriptionMaster fetchSubscriptionsMasterList(SubscriptionRequestVO subscription);
	public BaseResponseVO saveTranscationIOSDetails(IOSTranscationsDetailsRequestVO iosTrxDetailsBasedUserId, TransactionMaster trxMaster);
	public Adminboothevent getAdminBoothEvent(int userId, Integer eventId);
	public RestartVO restertServer(BaseRequestVO restartVO, PushNotificationTaskRestart taskRestartUpdate);
	public Fovbyuser getFobByUser(BaseRequestVO baseRequestVO);
	public List<DeviceRegistration> getRegisteredDevice(Integer userId);
	public String logOutService(DeviceRegistrationRequestVO deviceRegistrationRequestVO);
	public void updateStatusCount(String emailId, Integer eventId,Integer userId);
	public BoothAdminLogin getBoothAdminLoginById(Integer userId);
//	public boolean insetTokenData(TokenClsss token);
	public TransactionMaster subscriptionUpdateInAppPurchase(TransactionMaster transactionMaster);

}
