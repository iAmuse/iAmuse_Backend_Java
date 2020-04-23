package com.iamuse.server.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.iamuse.server.util.ThreadPool;
import com.iamuse.server.util.ZohoContactsSynUtil;
import com.iamuse.server.util.ZohoMarketingAddLeadToList;
import com.amuse.server.dao.TokenClsss;

import com.iamuse.kafka.Producer;
import com.iamuse.server.entity.BoothAdminLogin;
import com.iamuse.server.entity.DeviceRegistration;
import com.iamuse.server.entity.Fovbyuser;
import com.iamuse.server.entity.TransactionMaster;
import com.iamuse.server.entity.UpdateSubscriptionReq;
import com.iamuse.server.entity.ZohoMarketingHub;
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
import com.iamuse.server.validator.IAmuseValidator;

@Controller
public class IAmuseController {

	
	final static Logger logger = Logger.getLogger(IAmuseController.class);
	
	private Locale locale = LocaleContextHolder.getLocale();

	@Autowired
	private IAmuseValidator iamuseValidator;
	@Autowired
	PushNotificationTaskRestart taskRestartUpdate;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private ResponseHelper responseHelper;
	@Autowired
	private UploadImageResponseVO uploadImageResponseVO;
	@Autowired
	private RGBValueResponseVO rgbValueResponseVO;
	@Autowired
	private UserService userService;
	@Autowired
	private BaseResponseVO baseResponseVO;
	@Autowired
	LoginBaseResponseVO responseVo;
	@Autowired
	private CrashLogsResponseVO crashLogsResponseVO;
	@Autowired
	MailUtil mailUtil;
	@Autowired
	DeviceIPResponseVO deviceIPResponseVO;
	@Autowired
	ThreadPool pool;

	@RequestMapping(value = "v1/iamuse/req-resp", method = RequestMethod.GET)
	public String getServiceAPI() {
		return "service";
	}

	@RequestMapping(value = "v1/iamuse/accesstoken", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody TokenClsss token(@RequestBody TokenClsss token, HttpServletRequest request) {
		userService.addDataAccessToken(token);
		return token;
	}
	
	@RequestMapping(value = "v1/iamuse/removeaccesstoken", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody void RemoveToken(@RequestBody TokenClsss token, HttpServletRequest request) {
		userService.removeAccessToken(token);
		//return token;
	}
	
	@RequestMapping(value = "v1/iamuse/updateSubscriptionFormAdmin", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody BoothAdminLogin updateSubscriptionFormAdmin(@RequestBody UpdateSubscriptionReq updateSubscriptionReq, HttpServletRequest request) {
		return userService.updateSubscriptionFormAdmin(updateSubscriptionReq);
	}
	
	@RequestMapping(value = "v1/iamuse/imageupload", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody UploadImageResponseVO uploadPropertyImages(
			@RequestBody UploadImageRequestVO uploadImageRequestVO, HttpServletRequest request) {
		if (uploadImageRequestVO.getUserId() != null && uploadImageRequestVO.getUserId() != "") {
			try {
				String result = iamuseValidator.validateImageUploadRequest(uploadImageRequestVO);

				if (result.equals(messageSource.getMessage("response.success", null, locale))) {

					int imageId = userService.uploadImage(uploadImageRequestVO, "/IAmuseimagesTakePicture", request);

					if (imageId != 0) {
						uploadImageResponseVO = responseHelper.generateImageUploadResponse(result);
						uploadImageResponseVO.setImageId(imageId);
					} else {
						uploadImageResponseVO = responseHelper.generateImageUploadResponse(
								messageSource.getMessage("response.internal.error", null, locale));
					}
				} else {
					uploadImageResponseVO = responseHelper.generateImageUploadResponse(result);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			uploadImageResponseVO.setResponseCode("4");
			uploadImageResponseVO.setResponseDescription("Please Entered the  User Id");
		}
		logger.info(uploadImageRequestVO);
		return uploadImageResponseVO;
	}

	@RequestMapping(value = "v1/iamuse/firstTimeRGBConfiguration", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody RGBValueResponseVO firstTimeRGBConfiguration(@RequestBody RGBValueRequestVO rgbValueRequestVO,
			HttpServletRequest request) {
		if (rgbValueRequestVO.getUserId() != null & rgbValueRequestVO.getUserId() != "") {
			BoothAdminLogin boothAdminLogin = userService.getDefaultRGBValue(rgbValueRequestVO);
			if (boothAdminLogin.getIsDefaultRgb() == false) {
				rgbValueResponseVO.setRgbValue(boothAdminLogin.getRgbValueManual());
			} else {
				rgbValueResponseVO.setRgbValue(boothAdminLogin.getRgbValueDefault());
			}
			rgbValueResponseVO.setResponseCode("0000");
			rgbValueResponseVO.setResponseDescription("Success");
		} else {
			rgbValueResponseVO.setResponseCode("4");
			rgbValueResponseVO.setResponseDescription("Please Entered the  user id ");
		}
		logger.info(rgbValueResponseVO);
		return rgbValueResponseVO;
	}

	@RequestMapping(value = "v1/iamuse/imageuploadWithEmailId", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody BaseResponseVO imageuploadwithEmailId(
			@RequestBody UploadImageWithEmailRequestVO uploadImageWithEmailRequestVO, HttpServletRequest request) {
//		if(uploadImageWithEmailRequestVO.getUserId()!="" && uploadImageWithEmailRequestVO.getUserId()!=null){
		if (true) {
				
			try {	
				String result = iamuseValidator.validateImageUploadWithEmail(uploadImageWithEmailRequestVO);

				if (result.equals(messageSource.getMessage("response.success", null, locale))) {
					String rootPath = null;
					try {
						rootPath = new java.io.File(request.getSession().getServletContext().getRealPath("") + "/..")
								.getCanonicalPath();
					} catch (java.io.IOException e) {
						e.printStackTrace();
					}

					int imageId = userService.uploadImageWithEmailId(uploadImageWithEmailRequestVO,
							rootPath + "/IAmuseimages/EmailImages", request);
					if (imageId != 0) {
						baseResponseVO.setResponseCode("0000");
						baseResponseVO.setResponseDescription("Success");

					}
					if (imageId != 5) {
						baseResponseVO = responseHelper
								.generateResponse(messageSource.getMessage("mail.sent.failure", null, locale));
					}
				} else {
					baseResponseVO = responseHelper.generateResponse(result);
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
		} else {
			baseResponseVO.setResponseCode("4");
			baseResponseVO.setResponseDescription("Please entered the  user id");
		}
		logger.info(baseResponseVO);
		return baseResponseVO;
	}

	@RequestMapping(value = "v1/iamuse/crashlogsupload", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody CrashLogsResponseVO crashLogsUpload(@RequestBody CrashLogsRequestVO crashLogsRequestVO,
			HttpServletRequest request) {
		if (crashLogsRequestVO.getUserId() != null && crashLogsRequestVO.getUserId() != "") {
			try {
				String result = iamuseValidator.validateCrashlogsupload(crashLogsRequestVO);

				if (result.equals(messageSource.getMessage("response.success", null, locale))) {
					int id = userService.crashlogsupload(crashLogsRequestVO, "/IAmuseimages/IAmuseCrashLogs", request);

					if (id != 0) {
						crashLogsResponseVO = responseHelper.generateCrashLogsUploadResponse(result);

					} else {
						crashLogsResponseVO = responseHelper.generateCrashLogsUploadResponse(
								messageSource.getMessage("response.internal.error", null, locale));
					}
				} else {
					crashLogsResponseVO = responseHelper.generateCrashLogsUploadResponse(result);
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
		} else {
			crashLogsResponseVO.setResponseCode("4");
			crashLogsResponseVO.setResponseDescription("Please entered the  user id ");
		}
		logger.info(crashLogsResponseVO);
		return crashLogsResponseVO;

	}

	@RequestMapping(value = "v1/iamuse/saveDeviceIP", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody BaseResponseVO saveDeviceIP(@RequestBody DeviceIPRequestVO deviceIPRequestVO,
			HttpServletRequest request) {
		if (deviceIPRequestVO.getUserId() != null && deviceIPRequestVO.getUserId() != "") {
			boolean result;
			result = userService.saveDeviceIP(deviceIPRequestVO);
			if (result) {
				baseResponseVO.setResponseCode("0000");
				baseResponseVO.setResponseDescription("Success");
			} else {
				baseResponseVO.setResponseCode("0022");
				baseResponseVO.setResponseDescription("Failure");
			}
		} else {
			baseResponseVO.setResponseCode("4");
			baseResponseVO.setResponseDescription("Please entered the user id");
		}
		logger.info(baseResponseVO);
		return baseResponseVO;
	}

	@RequestMapping(value = "v1/iamuse/registrationAdminBooth", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody LoginBaseResponseVO saveAdminBoothRegistration(
			@RequestBody BoothAdminRegistrationRequestVO adminBoothRegistrationRequestVO, HttpServletRequest request) throws ClientProtocolException, IOException {
		
		
		if (adminBoothRegistrationRequestVO.getEmailId() != null && adminBoothRegistrationRequestVO.getEmailId() != ""
				&& adminBoothRegistrationRequestVO.getPassword() != null
				&& adminBoothRegistrationRequestVO.getPassword() != "") {

			responseVo = userService.saveAdminBoothRegistration(adminBoothRegistrationRequestVO);
			ZohoContactsSynUtil.zohoContactSync(adminBoothRegistrationRequestVO.getEmailId());
			ZohoMarketingAddLeadToList.zohoMarketingHumAddLeadSignUp(adminBoothRegistrationRequestVO.getEmailId());
		} else {
			responseVo.setResponseCode("4");
			responseVo.setResponseDescription("Please entered the email id or password");
		}
		logger.info(responseVo);
		return responseVo;
	}

	@RequestMapping(value = "v1/iamuse/signInGmail", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody LoginBaseResponseVO signInGmail(
			@RequestBody LoginBoothAdminRegistrationRequestVO loginRegistrationRequestVO,HttpServletRequest request) throws ClientProtocolException, IOException {
				
						
		LoginBaseResponseVO loginBaseResponseVO = new LoginBaseResponseVO();
		BoothAdminRegistrationRequestVO adminBoothRegistrationRequestVO = new BoothAdminRegistrationRequestVO();
		adminBoothRegistrationRequestVO.setUserName(loginRegistrationRequestVO.getUsername());
		adminBoothRegistrationRequestVO.setEmailId(loginRegistrationRequestVO.getEmailId());
		adminBoothRegistrationRequestVO.setToken(loginRegistrationRequestVO.getAccesstoken());
		adminBoothRegistrationRequestVO.setPin("0000");
		adminBoothRegistrationRequestVO.setUserType("personal");
		if(loginRegistrationRequestVO.getEmailId() != null && loginRegistrationRequestVO.getEmailId() != "")
		{
		loginBaseResponseVO = userService.fetchGmailLoginBaseResponseVO(loginRegistrationRequestVO);
		//PropertyConfigurator.configure("log4j.properties");
		logger.info("SignInWithGmail loginBaseResponseVO Logger Test="+loginBaseResponseVO);
		//Producer.kafkaProducer(loginBaseResponseVO);
		System.out.println("loginBaseResponseVO="+loginBaseResponseVO);
		} 
		if(Integer.parseInt(loginBaseResponseVO.getResponseCode()) == 0)
		{
			responseVo = userService.saveGmailBoothRegistration(adminBoothRegistrationRequestVO);
			responseVo.setResponseCode("1");
			responseVo.setResponseDescription("Registered Successfully");
			ZohoContactsSynUtil.zohoContactSync(adminBoothRegistrationRequestVO.getEmailId());
			ZohoMarketingAddLeadToList.zohoMarketingHumAddLeadSignUp(loginRegistrationRequestVO.getEmailId());
		} else {
			responseVo = userService.saveGmailBoothRegistration(adminBoothRegistrationRequestVO);
			responseVo.setResponseCode("1");
			responseVo.setResponseDescription("This EmailId is already Registered");
		}
		logger.info("SignInWithGmail Logger Test="+responseVo);
		Producer.kafkaProducer(responseVo);
		return responseVo;
	}
	
	@RequestMapping(value = "v1/iamuse/loginAdminBooth", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody LoginBaseResponseVO loginAdminBooth(
			@RequestBody LoginBoothAdminRegistrationRequestVO loginRegistrationRequestVO, HttpServletRequest request) {
		LoginBaseResponseVO loginBaseResponseVO = new LoginBaseResponseVO();
		if (loginRegistrationRequestVO.getEmailId() != null && loginRegistrationRequestVO.getEmailId() != ""
				&& loginRegistrationRequestVO.getPassword() != null && loginRegistrationRequestVO.getPassword() != "") {
			loginBaseResponseVO = userService.fetchLoginBaseResponseVO(loginRegistrationRequestVO);
		} else {
			loginBaseResponseVO.setResponseCode("4");
			loginBaseResponseVO.setResponseDescription("Please entered the emailId and password ");
		}
		logger.info(loginBaseResponseVO);
		return loginBaseResponseVO;
	}
	
	@RequestMapping(value = "v1/iamuse/eventFetchingAdminBooth", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody EventFetchingBaseResponseVO eventFetchingAdminBooth(
			@RequestBody FetchingEventListRequestVO fetchinfEventAdminBoothRequestVo, HttpServletRequest request) {
		EventFetchingBaseResponseVO eventResponseVo = new EventFetchingBaseResponseVO();
		if (fetchinfEventAdminBoothRequestVo.getUserId() != null && fetchinfEventAdminBoothRequestVo.getUserId() != 0
				&& fetchinfEventAdminBoothRequestVo.getSubId() != null
				&& fetchinfEventAdminBoothRequestVo.getSubId() != 0) {
			eventResponseVo = userService.fetchEventFetchingAdminBooth(fetchinfEventAdminBoothRequestVo);
		} else {
			eventResponseVo.setResponseCode("4");
			eventResponseVo.setResponseDescription("Please entered the user-id and sub-id");
		}
		logger.info(eventResponseVo);
		return eventResponseVo;
	}

	@RequestMapping(value = "v1/iamuse/deviceRegisterService", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody BaseResponseVO deviceRegisterSevice(
			@RequestBody DeviceRegistrationRequestVO deviceRegistrationRequestVO, HttpServletRequest request) {

		if (deviceRegistrationRequestVO.getUserId() != null && deviceRegistrationRequestVO.getUserId() != 0) {
			try {
				String result = userService.deviceRegisterSevice(deviceRegistrationRequestVO);
				if (("success").equals(result)) {

					ThreadPoolTaskExecutor taskExecutor = pool.taskExecutor();
					String rootPath = System.getProperty("catalina.home");
					List<DeviceRegistration> deviceRegistration = userService
							.getRegisteredDevice(deviceRegistrationRequestVO.getUserId());

					taskRestartUpdate.setDetails(deviceRegistration, messageSource, rootPath);
					taskExecutor.execute(taskRestartUpdate);

					baseResponseVO.setResponseCode("1");
					baseResponseVO.setResponseDescription("Success");
				} else if (("update").equals(result)) {
					baseResponseVO.setResponseCode("3");
					baseResponseVO.setResponseDescription("Update");
				} else {
					baseResponseVO.setResponseCode("0");
					baseResponseVO.setResponseDescription("Failure");
				}
			} catch (Exception e) {
				baseResponseVO.setResponseCode("2");
				baseResponseVO.setResponseDescription("Something went wrong !!!!!!");
				logger.error(e);
			}
		} else {
			baseResponseVO.setResponseCode("4");
			baseResponseVO.setResponseDescription("Please entered the user-id");
		}
		logger.info(baseResponseVO);
		return baseResponseVO;
	}

	@RequestMapping(value = "v1/iamuse/subscriptionsList", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody SubscriptionMasterResponseVO subscriptionsList(@RequestBody SubscriptionRequestVO subscription,
			HttpServletRequest request) {
		SubscriptionMasterResponseVO subResponseVo = new SubscriptionMasterResponseVO();
		if (subscription.getUserId() != null && subscription.getUserId() != 0) {
			subResponseVo = userService.fetchSubscriptionsMasterList(subscription);
		} else {
			subResponseVo.setResponseCode("4");
			subResponseVo.setResponseDescription("Please entered the user-id");
		}
		logger.info(subResponseVo);
		return subResponseVo;
	}
	@RequestMapping(value = "v1/iamuse/fetchingTranscationDetailsIOS", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody BaseResponseVO saveTranscationIOSDetails(
			@RequestBody IOSTranscationsDetailsRequestVO iosTrxDetailsBasedUserId, HttpServletRequest request) {

		String VERIFICATION_URL = "https://sandbox.itunes.apple.com/verifyReceipt";
		AppleReceiptVerifyResponse responseVO = IOSPaymentVerifyRecepitUtil
				.verifyAppleInAppPurchaseRecepit(iosTrxDetailsBasedUserId.getReceiptData(), VERIFICATION_URL);

		BaseResponseVO baseResponseVO = new BaseResponseVO();

		if (responseVO.getStatus().equalsIgnoreCase("0")) {

			baseResponseVO = userService.saveTranscationIOSDetails(iosTrxDetailsBasedUserId, responseVO);

		} else if (("21002").equalsIgnoreCase(responseVO.getStatus())) {
			baseResponseVO.setResponseCode("0");
			baseResponseVO.setResponseDescription("Invalid receipt");
		} else if (("21006").equalsIgnoreCase(responseVO.getStatus())) {
			baseResponseVO.setResponseCode("0");
			baseResponseVO.setResponseDescription("receipt has expired");
		} else if (("21007").equalsIgnoreCase(responseVO.getStatus())) {
			baseResponseVO.setResponseCode("0");
			baseResponseVO.setResponseDescription("we are verifying development receipt at production enviornment");
		}
		logger.info(baseResponseVO);
		return baseResponseVO;
	}

	@RequestMapping(value = "v1/iamuse/getIP", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody RestartVO restertServer(@RequestBody BaseRequestVO restartVO, HttpServletRequest request) {
		RestartVO baseResponseVO = new RestartVO();

		RestartVO deviceRegistrations = userService.restertServer(restartVO, taskRestartUpdate);
		if (deviceRegistrations != null) {
			if (deviceRegistrations.getCameraIP() == null) {
				baseResponseVO.setCameraIP("");
			} else {
				baseResponseVO.setCameraIP(deviceRegistrations.getCameraIP());
			}
			if (deviceRegistrations.getTouchIP() == null) {
				baseResponseVO.setTouchIP("");
			} else {
				baseResponseVO.setTouchIP(deviceRegistrations.getTouchIP());
			}
			baseResponseVO.setResponseCode("1");
			baseResponseVO.setResponseDescription("Success");
		}
		logger.info(baseResponseVO);
		return baseResponseVO;
	}

	/*
	 * @RequestMapping(value = "v1/iamuse/getUserList", method = RequestMethod.GET,
	 * headers = "Accept=application/json") public @ResponseBody GetAllUser
	 * allUser(@RequestBody HttpServletRequest request) { //
	 * userService.getUserList(userid); return userService.getUserList(userid); }
	 */

	@RequestMapping(value = "v1/iamuse/getFov", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody FOVVO getFobByUser(@RequestBody BaseRequestVO baseRequestVO, HttpServletRequest request) {
		FOVVO baseResponseVO = new FOVVO();

		Fovbyuser fovbyuser = userService.getFobByUser(baseRequestVO);
		if (fovbyuser != null) {
			baseResponseVO.setBottom(fovbyuser.getFovBottom());
			baseResponseVO.setTop(fovbyuser.getFovTop());
			baseResponseVO.setLeft(fovbyuser.getFovLeft());
			baseResponseVO.setRight(fovbyuser.getFovRight());
			baseResponseVO.setOtherCountdownDelay(fovbyuser.getOthrtCountDelay());
			baseResponseVO.setOtherIntractionTimout(fovbyuser.getOtherInstructionTimeout());

			ThreadPoolTaskExecutor taskExecutor = pool.taskExecutor();
			String rootPath = System.getProperty("catalina.home");
			List<DeviceRegistration> deviceRegistration = userService
					.getRegisteredDevice(Integer.parseInt(baseRequestVO.getUserId()));

			taskRestartUpdate.setDetails(deviceRegistration, messageSource, rootPath);
			taskExecutor.execute(taskRestartUpdate);

			baseResponseVO.setResponseCode("1");
			baseResponseVO.setResponseDescription("Success");
		} else {
			baseResponseVO.setResponseCode("0022");
			baseResponseVO.setResponseDescription("Record Not Found");
		}
		logger.info(baseResponseVO);
		return baseResponseVO;
	}

	@RequestMapping(value = "v1/iamuse/logOut", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody BaseResponseVO getFobByUser(
			@RequestBody DeviceRegistrationRequestVO deviceRegistrationRequestVO, HttpServletRequest request) {
		String boothAdminLogin = userService.logOutService(deviceRegistrationRequestVO);
		if (boothAdminLogin.equals("success")) {
			baseResponseVO.setResponseCode("1");
			baseResponseVO.setResponseDescription("Success");
		} else {
			baseResponseVO.setResponseCode("0022");
			baseResponseVO.setResponseDescription("Record Not Found");
		}
		logger.info(baseResponseVO);
		return baseResponseVO;
	}
	

	@ResponseBody
	@RequestMapping(value = "v1/iamuse/subscriptionUpdateInAppPurchase", method = RequestMethod.POST,headers = "Accept=application/json")
	public String subscriptionUpdateInAppPurchase(@RequestBody TransactionMaster transactionMaster,
			HttpServletRequest request,HttpServletResponse response) {

		try {
			UpdateSubscriptionReq updateSubscriptionReq = new UpdateSubscriptionReq();
			updateSubscriptionReq.setUserid(transactionMaster.getUserId());
			updateSubscriptionReq.setSubId(transactionMaster.getItemNumber());
			TransactionMaster transactionDetails = userService.subscriptionUpdateInAppPurchase(transactionMaster);
			userService.updateSubscriptionPlan(updateSubscriptionReq,transactionMaster.getPaymentAmount(),transactionDetails.getTransactionMasterId());
			if (transactionDetails != null) {
				ObjectMapper objectMapper = new ObjectMapper();
				String result = objectMapper.writeValueAsString(transactionDetails);
				return result;
			}
		} catch (Exception ex) {
			logger.error("Exception=" + ex.getStackTrace());
		}
		return "{\"success\": false,  \"payload\": \"Payment was failed\"  }";
	}
	
	@ResponseBody
	@RequestMapping(value = "v1/iamuse/getZohoMarketinghubAccessToken", method = RequestMethod.POST,headers = "Accept=application/json")
	public String getZohoMarketinghubAccessToken(@RequestBody ZohoMarketingHub zohoMarketingHub,HttpServletRequest request,HttpServletResponse response)
	{
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headerAccessToken = new HttpHeaders();
			headerAccessToken.add("Accept", "application/json");
			headerAccessToken.add("Content-Type", "application/json");
			HttpEntity<String> requests = new HttpEntity<String>(headerAccessToken);
            
			String access_token_url = "https://accounts.zoho.in/oauth/v2/token";
			access_token_url += "?code="+zohoMarketingHub.getCode();
			access_token_url += "&grant_type="+zohoMarketingHub.getGrant_type();
			access_token_url += "&redirect_uri="+zohoMarketingHub.getRedirect_uri();
			access_token_url += "&client_id="+zohoMarketingHub.getClient_id();
			access_token_url += "&client_secret="+zohoMarketingHub.getClient_secret();
			ResponseEntity<Object> responseForAccessToken = restTemplate.exchange(access_token_url, HttpMethod.POST, requests, Object.class);
					
			ObjectMapper mapper = new ObjectMapper();
			String result = mapper.writeValueAsString(responseForAccessToken.getBody());
		    final ObjectNode node = new ObjectMapper().readValue(result, ObjectNode.class);
		    String accessToken = null;
		    if (node.has("access_token")) {
		    	accessToken = node.get("access_token").getTextValue();
		    	zohoMarketingHub.setAccesstoken(accessToken);
		        System.out.println("access_token: " + accessToken);
		    }
		    
		    String oauthToken = "Zoho-oauthtoken "+zohoMarketingHub.getAccesstoken();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", oauthToken);
			headers.add("Content-Type", "application/json");

		    List<Object> li = new ArrayList<Object>();
		    String[] listOfEmails = zohoMarketingHub.getEmailIds();
			for (String list : listOfEmails) {
			HttpEntity<String> req = new HttpEntity<String>(headers);
            String Leadurl = "https://marketinghub.zoho.in/api/v1/addleadsinbulk";
            Leadurl += "?listkey="+zohoMarketingHub.getListkey();
            Leadurl += "&resfmt=JSON";
            Leadurl += "&emailids="+list;
			System.out.println("Inside restcall for lead="+Leadurl);
			ResponseEntity<Object> responseForLeads = restTemplate.exchange(Leadurl, HttpMethod.POST, req, Object.class);
			li.add(responseForLeads.getBody());
			}
			String res= mapper.writeValueAsString(li);
			return res;			
		} catch (Exception ex) {
			logger.error("Exception=" + ex.getMessage());
		}
		return "{\"success\": false,  \"payload\": \"Payment was failed\"  }";
	}
	
	@ResponseBody
	@RequestMapping(value = "v1/iamuse/getZohoMarketinghubLead", method = RequestMethod.POST,headers = "Accept=application/json")
	public String getZohoMarketinghubLead(@RequestBody ZohoMarketingHub zohoMarketingHub,HttpServletRequest request,HttpServletResponse response)
	{
		try {	
			           
			List<Object> li = new ArrayList<Object>();
			RestTemplate restTemplate = new RestTemplate();
			          
			String oauthToken = "Zoho-oauthtoken "+zohoMarketingHub.getAccesstoken();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", oauthToken);
			headers.add("Content-Type", "application/json");
			
			String[] listOfEmails = zohoMarketingHub.getEmailIds();
			for (String list : listOfEmails) {
			HttpEntity<String> requests = new HttpEntity<String>(headers);
            String access_token_url = "https://marketinghub.zoho.in/api/v1/addleadsinbulk";
			access_token_url += "?listkey="+zohoMarketingHub.getListkey();
			access_token_url += "&resfmt=JSON";
			access_token_url += "&emailids="+list;
			System.out.println("Inside restcall for lead="+access_token_url);
			
			ResponseEntity<Object> responseForLeads = restTemplate.exchange(access_token_url, HttpMethod.POST, requests, Object.class);
			li.add(responseForLeads.getBody());
			}
			ObjectMapper mapper = new ObjectMapper();
		    String result = mapper.writeValueAsString(li);
		    System.out.println(result);
		    return result;		
		} catch (Exception ex) {
			logger.error("Exception=" + ex.getStackTrace());
		}
		return "{\"success\": false,  \"payload\": \"Payment was failed\"  }";
	}
}