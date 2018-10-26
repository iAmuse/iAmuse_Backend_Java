package com.iamuse.server.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import com.iamuse.server.entity.BoothAdminLogin;
import com.iamuse.server.entity.DeviceIp;
import com.iamuse.server.entity.DeviceRegistration;
import com.iamuse.server.entity.Fovbyuser;
import com.iamuse.server.entity.UploadImage;
import com.iamuse.server.requestVO.BaseRequestVO;
import com.iamuse.server.requestVO.BoothAdminRegistrationRequestVO;
import com.iamuse.server.requestVO.CrashLogsRequestVO;
import com.iamuse.server.requestVO.DeviceIPRequestVO;
import com.iamuse.server.requestVO.DeviceRegistrationRequestVO;
import com.iamuse.server.requestVO.DeviceTokenRequestVO;
import com.iamuse.server.requestVO.FetchingEventListRequestVO;
import com.iamuse.server.requestVO.IOSTranscationsDetailsRequestVO;
import com.iamuse.server.requestVO.LoginBoothAdminRegistrationRequestVO;
import com.iamuse.server.requestVO.RGBValueRequestVO;
import com.iamuse.server.requestVO.RestartVO;
import com.iamuse.server.requestVO.SubscriptionRequestVO;
import com.iamuse.server.requestVO.UploadImageRequestVO;
import com.iamuse.server.requestVO.UploadImageWithEmailRequestVO;
import com.iamuse.server.responseVO.AppleReceiptVerifyResponse;
import com.iamuse.server.responseVO.BaseResponseVO;
import com.iamuse.server.responseVO.EventFetchingBaseResponseVO;
import com.iamuse.server.responseVO.LoginBaseResponseVO;
import com.iamuse.server.responseVO.SubscriptionMasterResponseVO;
import com.iamuse.server.util.PushNotificationTaskRestart;



public interface UserService {
	
	
	public int uploadImage(UploadImageRequestVO uploadImageRequestVO,String path,HttpServletRequest request);
	public BoothAdminLogin getDefaultRGBValue(RGBValueRequestVO rbRgbValueRequestVO);
	public boolean saveDeviceToken(DeviceTokenRequestVO deviceTokenRequestVO);
	public UploadImage getImageDetails(int imageId);
	public int uploadImageWithEmailId(UploadImageWithEmailRequestVO uploadImageWithEmailRequestVO,String path,HttpServletRequest request);
	public int crashlogsupload(CrashLogsRequestVO crashLogsRequestVO,String path,HttpServletRequest request);
	public boolean saveDeviceIP(DeviceIPRequestVO deviceIPRequestVO);
	public DeviceIp getDeviceIP(DeviceIPRequestVO deviceIPRequestVO);
	public LoginBaseResponseVO saveAdminBoothRegistration(BoothAdminRegistrationRequestVO adminBoothRegistrationRequestVO);
	public LoginBaseResponseVO fetchLoginBaseResponseVO(LoginBoothAdminRegistrationRequestVO loginRegistrationRequestVO);
	public EventFetchingBaseResponseVO fetchEventFetchingAdminBooth(FetchingEventListRequestVO fetchinfEventAdminBoothRequestVo);
	public String deviceRegisterSevice(DeviceRegistrationRequestVO deviceRegistrationRequestVO);
	public SubscriptionMasterResponseVO fetchSubscriptionsMasterList(SubscriptionRequestVO subscription) throws IOException;
	public BaseResponseVO saveTranscationIOSDetails(IOSTranscationsDetailsRequestVO iosTrxDetailsBasedUserId,AppleReceiptVerifyResponse responseVO);
	public RestartVO restertServer(BaseRequestVO restartVO, PushNotificationTaskRestart taskRestartUpdate);
	public Fovbyuser getFobByUser(BaseRequestVO baseRequestVO);
	public List<DeviceRegistration> getRegisteredDevice(Integer userId);
	public String logOutService(DeviceRegistrationRequestVO deviceRegistrationRequestVO);
	public LoginBaseResponseVO registrationLoginGoogle(BoothAdminRegistrationRequestVO adminBoothRegistrationRequestVO);
	
}
