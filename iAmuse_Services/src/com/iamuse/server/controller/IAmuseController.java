package com.iamuse.server.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iamuse.server.entity.BoothAdminLogin;
import com.iamuse.server.entity.DeviceRegistration;
import com.iamuse.server.entity.Fovbyuser;
import com.iamuse.server.helper.ResponseHelper;
import com.iamuse.server.requestVO.BaseRequestVO;
import com.iamuse.server.requestVO.BoothAdminRegistrationRequestVO;
import com.iamuse.server.requestVO.CrashLogsRequestVO;
import com.iamuse.server.requestVO.DeviceIPRequestVO;
import com.iamuse.server.requestVO.DeviceRegistrationRequestVO;
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
import com.iamuse.server.responseVO.CrashLogsResponseVO;
import com.iamuse.server.responseVO.DeviceIPResponseVO;
import com.iamuse.server.responseVO.EventFetchingBaseResponseVO;
import com.iamuse.server.responseVO.FOVVO;
import com.iamuse.server.responseVO.LoginBaseResponseVO;
import com.iamuse.server.responseVO.RGBValueResponseVO;
import com.iamuse.server.responseVO.SubscriptionMasterResponseVO;
import com.iamuse.server.responseVO.UploadImageResponseVO;
import com.iamuse.server.service.UserService;
import com.iamuse.server.util.IOSPaymentVerifyRecepitUtil;
import com.iamuse.server.util.MailUtil;
import com.iamuse.server.util.PushNotificationTaskRestart;
import com.iamuse.server.util.ThreadPool;
import com.iamuse.server.validator.IAmuseValidator;

@Controller
public class IAmuseController {

	private Locale locale = LocaleContextHolder.getLocale();
	
	@Autowired private IAmuseValidator iamuseValidator;
	@Autowired PushNotificationTaskRestart taskRestartUpdate;
	@Autowired private MessageSource messageSource;
	@Autowired private ResponseHelper responseHelper;
	@Autowired private UploadImageResponseVO uploadImageResponseVO;
	@Autowired private RGBValueResponseVO rgbValueResponseVO;
	@Autowired private UserService userService;
	@Autowired private BaseResponseVO baseResponseVO;
	@Autowired LoginBaseResponseVO responseVo;
	@Autowired private CrashLogsResponseVO crashLogsResponseVO;
	@Autowired MailUtil mailUtil;
	@Autowired DeviceIPResponseVO deviceIPResponseVO;
	@Autowired ThreadPool pool;
	
	@RequestMapping(value = "v1/iamuse/req-resp", method = RequestMethod.GET)
	public String getServiceAPI()
	{
		return "service";
	}
	
	@RequestMapping(value = "v1/iamuse/imageupload", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody UploadImageResponseVO uploadPropertyImages(@RequestBody UploadImageRequestVO uploadImageRequestVO, HttpServletRequest request)
	{
		if(uploadImageRequestVO.getUserId()!=null && uploadImageRequestVO.getUserId()!=""){
		try {
			String result=iamuseValidator.validateImageUploadRequest(uploadImageRequestVO);
			
			if(result.equals(messageSource.getMessage("response.success",null,locale)))
			{ 
			
				int imageId=userService.uploadImage(uploadImageRequestVO,"/IAmuseimagesTakePicture",request);
			
				if(imageId!=0)
				{
					uploadImageResponseVO=responseHelper.generateImageUploadResponse(result);
					uploadImageResponseVO.setImageId(imageId);
				}else{
					uploadImageResponseVO=responseHelper.generateImageUploadResponse(messageSource.getMessage("response.internal.error",null,locale));
				}
			}
			else
			{
				uploadImageResponseVO=responseHelper.generateImageUploadResponse(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		}else{
			uploadImageResponseVO.setResponseCode("4");
			uploadImageResponseVO.setResponseDescription("Please Entered the  User Id");
		}
			
		return uploadImageResponseVO;
	}
	@RequestMapping(value = "v1/iamuse/firstTimeRGBConfiguration", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody RGBValueResponseVO firstTimeRGBConfiguration(@RequestBody RGBValueRequestVO rgbValueRequestVO, HttpServletRequest request)
	{   
		if(rgbValueRequestVO.getUserId()!=null & rgbValueRequestVO.getUserId()!=""){
		BoothAdminLogin boothAdminLogin= userService.getDefaultRGBValue(rgbValueRequestVO);
		if(boothAdminLogin.getIsDefaultRgb()==false ){
			rgbValueResponseVO.setRgbValue(boothAdminLogin.getRgbValueManual());
		}
		else{
     	rgbValueResponseVO.setRgbValue(boothAdminLogin.getRgbValueDefault());
		}
		rgbValueResponseVO.setResponseCode("0000");
		rgbValueResponseVO.setResponseDescription("Success");
		}else{
			rgbValueResponseVO.setResponseCode("4");
			rgbValueResponseVO.setResponseDescription("Please Entered the  user id ");
		}
		
		return rgbValueResponseVO;
}
	
	@RequestMapping(value = "v1/iamuse/imageuploadWithEmailId", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody BaseResponseVO imageuploadwithEmailId(@RequestBody UploadImageWithEmailRequestVO uploadImageWithEmailRequestVO, HttpServletRequest request)
	{
		if(uploadImageWithEmailRequestVO.getUserId()!="" && uploadImageWithEmailRequestVO.getUserId()!=null){
		
		try {
			String result=iamuseValidator.validateImageUploadWithEmail(uploadImageWithEmailRequestVO);
			
			if(result.equals(messageSource.getMessage("response.success",null,locale)))
			{
				String rootPath = null;
				try {
				 rootPath = new java.io.File(request.getSession().getServletContext().getRealPath("")+"/..").getCanonicalPath();
				} catch (java.io.IOException e) {
				 e.printStackTrace();
				}
				
				int imageId=userService.uploadImageWithEmailId(uploadImageWithEmailRequestVO,rootPath+"/IAmuseimages/EmailImages",request);
				if(imageId!=0)
				{
					baseResponseVO.setResponseCode("0000");
					baseResponseVO.setResponseDescription("Success");
					
				}if(imageId!=5){
					baseResponseVO=responseHelper.generateResponse(messageSource.getMessage("mail.sent.failure",null,locale));
				}
			}
			else
			{
				baseResponseVO=responseHelper.generateResponse(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		}else		{
			baseResponseVO.setResponseCode("4");
			baseResponseVO.setResponseDescription("Please entered the  user id");
		}
		return baseResponseVO;
	}
	
	@RequestMapping(value = "v1/iamuse/crashlogsupload", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody CrashLogsResponseVO crashLogsUpload(@RequestBody CrashLogsRequestVO crashLogsRequestVO,HttpServletRequest request){
		if(crashLogsRequestVO.getUserId()!=null && crashLogsRequestVO.getUserId()!=""){
		try {
			String result=iamuseValidator.validateCrashlogsupload(crashLogsRequestVO);
			
			if(result.equals(messageSource.getMessage("response.success",null,locale)))
			{
				int id=userService.crashlogsupload(crashLogsRequestVO,"/IAmuseimages/IAmuseCrashLogs",request);
			
				if(id!=0)
				{
					crashLogsResponseVO=responseHelper.generateCrashLogsUploadResponse(result);
					
				}else{
					crashLogsResponseVO=responseHelper.generateCrashLogsUploadResponse(messageSource.getMessage("response.internal.error",null,locale));
				}
			}
			else
			{
				crashLogsResponseVO=responseHelper.generateCrashLogsUploadResponse(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		}else{
			crashLogsResponseVO.setResponseCode("4");
			crashLogsResponseVO.setResponseDescription("Please entered the  user id ");
		}
		return crashLogsResponseVO;
		
	}
	
	@RequestMapping(value = "v1/iamuse/saveDeviceIP", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody BaseResponseVO saveDeviceIP(@RequestBody DeviceIPRequestVO deviceIPRequestVO, HttpServletRequest request)
	{ 
		if(deviceIPRequestVO.getUserId()!=null && deviceIPRequestVO.getUserId()!=""){
		boolean result;
		result=userService.saveDeviceIP(deviceIPRequestVO);
		if(result)
		{
		baseResponseVO.setResponseCode("0000");
		baseResponseVO.setResponseDescription("Success");
		}
		else{
			baseResponseVO.setResponseCode("0022");
			baseResponseVO.setResponseDescription("Failure");
		}
		}else{
			baseResponseVO.setResponseCode("4");
			baseResponseVO.setResponseDescription("Please entered the user id");
		}
		return baseResponseVO;
}

	
	@RequestMapping(value = "v1/iamuse/registrationAdminBooth", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody
	LoginBaseResponseVO saveAdminBoothRegistration(@RequestBody BoothAdminRegistrationRequestVO adminBoothRegistrationRequestVO,HttpServletRequest request) {
		
		if(adminBoothRegistrationRequestVO.getEmailId()!=null && adminBoothRegistrationRequestVO.getEmailId()!="" && adminBoothRegistrationRequestVO.getPassword()!=null && adminBoothRegistrationRequestVO.getPassword()!=""){
		
			responseVo=userService.saveAdminBoothRegistration(adminBoothRegistrationRequestVO);
		}else{
			responseVo.setResponseCode("4");
			responseVo.setResponseDescription("Please entered the email id or password");
		}
		return responseVo;
	}
	
	
	
	@RequestMapping(value = "v1/iamuse/loginAdminBooth", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody LoginBaseResponseVO loginAdminBooth(@RequestBody LoginBoothAdminRegistrationRequestVO loginRegistrationRequestVO,HttpServletRequest request) {
		LoginBaseResponseVO loginBaseResponseVO=new LoginBaseResponseVO();
		if(loginRegistrationRequestVO.getEmailId()!=null && loginRegistrationRequestVO.getEmailId()!="" && loginRegistrationRequestVO.getPassword()!=null && loginRegistrationRequestVO.getPassword()!=""){
			loginBaseResponseVO=userService.fetchLoginBaseResponseVO(loginRegistrationRequestVO);
		}else{
			loginBaseResponseVO.setResponseCode("4");
			loginBaseResponseVO.setResponseDescription("Please entered the emailId and password ");
		}
		return loginBaseResponseVO;
	}
	
	
	@RequestMapping(value = "v1/iamuse/eventFetchingAdminBooth", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody EventFetchingBaseResponseVO eventFetchingAdminBooth(@RequestBody FetchingEventListRequestVO fetchinfEventAdminBoothRequestVo,HttpServletRequest request) {
		EventFetchingBaseResponseVO eventResponseVo=new EventFetchingBaseResponseVO();
		if(fetchinfEventAdminBoothRequestVo.getUserId()!=null && fetchinfEventAdminBoothRequestVo.getUserId()!=0 && fetchinfEventAdminBoothRequestVo.getSubId()!=null && !fetchinfEventAdminBoothRequestVo.getSubId().equals("0")){
			eventResponseVo= userService.fetchEventFetchingAdminBooth(fetchinfEventAdminBoothRequestVo);
		}else{
			eventResponseVo.setResponseCode("4");
			eventResponseVo.setResponseDescription("Please entered the user-id and sub-id");
		}
		
		return  eventResponseVo;
	}
	
	@RequestMapping(value = "v1/iamuse/deviceRegisterService", method = RequestMethod.POST, headers="Accept=application/json")
	 public @ResponseBody BaseResponseVO deviceRegisterSevice(@RequestBody DeviceRegistrationRequestVO deviceRegistrationRequestVO, HttpServletRequest request)
	 { 
		
		if(deviceRegistrationRequestVO.getUserId()!=null && deviceRegistrationRequestVO.getUserId()!=0){
	  try {
	   String result=userService.deviceRegisterSevice(deviceRegistrationRequestVO);
	   if(("success").equals(result)){
			
		   ThreadPoolTaskExecutor taskExecutor=pool.taskExecutor();
			String rootPath = System.getProperty("catalina.home");
			List<DeviceRegistration> deviceRegistration=userService.getRegisteredDevice(deviceRegistrationRequestVO.getUserId());

			taskRestartUpdate.setDetails(deviceRegistration, messageSource,rootPath);
			taskExecutor.execute(taskRestartUpdate);
		   
		   
	    baseResponseVO.setResponseCode("1");
	    baseResponseVO.setResponseDescription("Success");
	   }else if(("update").equals(result)){
	    baseResponseVO.setResponseCode("3");
	    baseResponseVO.setResponseDescription("Update");
	   }else{
		   baseResponseVO.setResponseCode("0");
		    baseResponseVO.setResponseDescription("Failure");
	   }
	  } catch (Exception e) {
		  baseResponseVO.setResponseCode("2");
		    baseResponseVO.setResponseDescription("Something went wrong !!!!!!");
	  }
		}else{
			    baseResponseVO.setResponseCode("4");
			    baseResponseVO.setResponseDescription("Please entered the user-id");
		   }
	  return baseResponseVO;
	 }
	
	@RequestMapping(value = "v1/iamuse/subscriptionsList", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody SubscriptionMasterResponseVO subscriptionsList(@RequestBody SubscriptionRequestVO subscription,HttpServletRequest request) throws IOException {
		SubscriptionMasterResponseVO subResponseVo=new SubscriptionMasterResponseVO();
		if(subscription.getUserId()!=null && subscription.getUserId()!=0){
			subResponseVo=userService.fetchSubscriptionsMasterList(subscription);
		}else{
			subResponseVo.setResponseCode("4");
			subResponseVo.setResponseDescription("Please entered the user-id");
		}
		return subResponseVo;
	}
	
	
	@RequestMapping(value = "v1/iamuse/fetchingTranscationDetailsIOS", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody BaseResponseVO saveTranscationIOSDetails(@RequestBody IOSTranscationsDetailsRequestVO iosTrxDetailsBasedUserId,HttpServletRequest request) {
		
		AppleReceiptVerifyResponse responseVO=IOSPaymentVerifyRecepitUtil.verifyAppleInAppPurchaseRecepit(iosTrxDetailsBasedUserId.getReceiptData(), messageSource.getMessage("itune.verification.url", null, locale), messageSource.getMessage("itune.verification.password", null, locale));
		
		BaseResponseVO baseResponseVO=new BaseResponseVO();
		System.out.println("008908098098908908908989890   "+responseVO.getStatus());
		if(responseVO.getStatus().equalsIgnoreCase("0")){
			
			baseResponseVO=userService.saveTranscationIOSDetails(iosTrxDetailsBasedUserId,responseVO);
			
		}else if(("21002").equalsIgnoreCase(responseVO.getStatus())){
			baseResponseVO.setResponseCode("0");
			baseResponseVO.setResponseDescription("Invalid receipt");
		}
		else if(("21006").equalsIgnoreCase(responseVO.getStatus())){
			baseResponseVO.setResponseCode("0");
			baseResponseVO.setResponseDescription("receipt has expired");
		}
		else if(("21007").equalsIgnoreCase(responseVO.getStatus())){
			baseResponseVO.setResponseCode("0");
			baseResponseVO.setResponseDescription("we are verifying development receipt at production enviornment");
		}
		return baseResponseVO;
	}
	
	@RequestMapping(value = "v1/iamuse/getIP", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody RestartVO restertServer(@RequestBody BaseRequestVO restartVO,HttpServletRequest request) {
		RestartVO baseResponseVO=new RestartVO();
		
		RestartVO deviceRegistrations=userService.restertServer(restartVO,taskRestartUpdate);
		 if(deviceRegistrations !=null){
			 if(deviceRegistrations.getCameraIP()==null){
				 baseResponseVO.setCameraIP("");
			 }else{
			 	baseResponseVO.setCameraIP(deviceRegistrations.getCameraIP());
			 }if(deviceRegistrations.getTouchIP()==null){
				 baseResponseVO.setTouchIP("");
			 }else{
			 	baseResponseVO.setTouchIP(deviceRegistrations.getTouchIP());
			 }
			    baseResponseVO.setResponseCode("1");
			    baseResponseVO.setResponseDescription("Success");
			   }
		 return baseResponseVO;
	}
	
	@RequestMapping(value = "v1/iamuse/getFov", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody FOVVO getFobByUser(@RequestBody BaseRequestVO baseRequestVO,HttpServletRequest request) {
		FOVVO baseResponseVO=new FOVVO();
		
		Fovbyuser fovbyuser=userService.getFobByUser(baseRequestVO);
		 if(fovbyuser !=null){
			 		baseResponseVO.setBottom(fovbyuser.getFovBottom());
			 		baseResponseVO.setTop(fovbyuser.getFovTop());
			 		baseResponseVO.setLeft(fovbyuser.getFovLeft());
			 		baseResponseVO.setRight(fovbyuser.getFovRight());
			 		baseResponseVO.setOtherCountdownDelay(fovbyuser.getOthrtCountDelay());
			 		baseResponseVO.setOtherIntractionTimout(fovbyuser.getOtherInstructionTimeout());
			 		
			 		 ThreadPoolTaskExecutor taskExecutor=pool.taskExecutor();
						String rootPath = System.getProperty("catalina.home");
						List<DeviceRegistration> deviceRegistration=userService.getRegisteredDevice(Integer.parseInt(baseRequestVO.getUserId()));

						taskRestartUpdate.setDetails(deviceRegistration, messageSource,rootPath);
						taskExecutor.execute(taskRestartUpdate);
			 		
			 		baseResponseVO.setResponseCode("1");
			 		baseResponseVO.setResponseDescription("Success");
			   }else{
				   	baseResponseVO.setResponseCode("0022");
					baseResponseVO.setResponseDescription("Record Not Found");
			   }
		 return baseResponseVO;
	}
	
	@RequestMapping(value = "v1/iamuse/logOut", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody BaseResponseVO getFobByUser(@RequestBody DeviceRegistrationRequestVO deviceRegistrationRequestVO,HttpServletRequest request) {
		String boothAdminLogin=userService.logOutService(deviceRegistrationRequestVO);
		if(boothAdminLogin.equals("success")){
			baseResponseVO.setResponseCode("1");
			baseResponseVO.setResponseDescription("Success");
		}else{
			baseResponseVO.setResponseCode("0022");
			baseResponseVO.setResponseDescription("Record Not Found");
		}
		return baseResponseVO;
	}
	
	@RequestMapping(value = "v1/iamuse/registrationLoginGoogle", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody LoginBaseResponseVO registrationLoginGoogle(@RequestBody BoothAdminRegistrationRequestVO adminBoothRegistrationRequestVO,HttpServletRequest request) {
		
		if(adminBoothRegistrationRequestVO.getEmailId()!=null && adminBoothRegistrationRequestVO.getEmailId()!=""){
		
			responseVo=userService.registrationLoginGoogle(adminBoothRegistrationRequestVO);
		}else{
			responseVo.setResponseCode("4");
			responseVo.setResponseDescription("Please entered the email id or password");
		}
		return responseVo;
	}
}
