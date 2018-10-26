package com.iamuse.admin.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.iamuse.admin.VO.AdminPictureVO;
import com.iamuse.admin.VO.EventVO;
import com.iamuse.admin.VO.ImageEmailFormVO;
import com.iamuse.admin.VO.ImageFormVO;
import com.iamuse.admin.entity.AdminBoothEventPicture;
import com.iamuse.admin.entity.Adminboothevent;
import com.iamuse.admin.entity.BoothAdminLogin;
import com.iamuse.admin.entity.DeviceRegistration;
import com.iamuse.admin.entity.Fovbyuser;
import com.iamuse.admin.entity.TransactionMaster;
import com.iamuse.admin.service.BoothAdminService;
import com.iamuse.admin.service.IamuseDashboardService;
import com.iamuse.admin.service.LoginService;
import com.iamuse.admin.service.SuperadminService;
import com.iamuse.admin.util.ImagePushNotificationTask;
import com.iamuse.admin.util.PushNotificationTask;
import com.iamuse.admin.util.PushNotificationTaskImagesUpdate;
import com.iamuse.admin.util.ServiceRequestTemplateZoho;
import com.iamuse.admin.util.ThreadPool;
import com.paypal.constants.ServerConstants;

@SuppressWarnings({"unused","unchecked","rawtypes"})
@Controller
public class EventController {
	
	@Autowired BoothAdminService boothAdminService;
	@Autowired IamuseDashboardService iamuseDashboardService;
	@Autowired SuperadminService superadminService;
	@Autowired ThreadPool pool;
	@Autowired PushNotificationTask task;
	@Autowired ImagePushNotificationTask imagetask;
	@Autowired PushNotificationTaskImagesUpdate taskImageUpdate;
	@Autowired MessageSource messageSource;
	@Autowired LoginService loginService;
	
	List<DeviceRegistration> deviceRegistration;
	BoothAdminLogin boothAdminLogin;
	BoothAdminLogin boothAdminLogin1;
	String rootPaths = System.getProperty("catalina.home");
	private Locale locale = LocaleContextHolder.getLocale();
	@Autowired ServiceRequestTemplateZoho serviceRequestTemplateZoho;
	
	@RequestMapping(value="getEventList")
	public String getEventList(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes) throws ParseException
	{
		return "redirect:getSubscribedEventList";
	}
	
	@RequestMapping(value="getSubscribedEventList")
	public String getSubscribedEventList(@ModelAttribute("EventVO") EventVO eventVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes) throws ParseException, IOException
	{
		String result1 = ServerConstants.EVENT_LIST;
		String pageids = request.getParameter(ServerConstants.PAGE_ID);
		String totals = request.getParameter(ServerConstants.TOTAL);
		int pageid = 0;
		int total = 0;
		if (pageids == null && totals == null) {
			pageid = 1;
			total = 10;
		}
		if (total == 0) {
			total = Integer.parseInt(totals);
			pageid = Integer.parseInt(pageids);
		}
		boothAdminLogin = (BoothAdminLogin) request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if (boothAdminLogin == null) {
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		} else {
			boothAdminLogin1 = boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
			Fovbyuser fovbyuser = boothAdminService.getFovTableData(boothAdminLogin1.getUserId());
			deviceRegistration = boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION, deviceRegistration);
			if (("boothadmin").equalsIgnoreCase(boothAdminLogin.getUserRole())) {

				modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN, boothAdminLogin1);
				if (boothAdminLogin1.getSubUpdatedDate() != null) {
					if(boothAdminLogin1.getAllowGuestEmailToSelf() !=null) {
						TransactionMaster transactionReceiptVOs = new TransactionMaster();
						try {
							String urls="https://subscriptions.zoho.com/api/v1/hostedpages/"+boothAdminLogin1.getAllowGuestEmailToSelf();
							Object response1=serviceRequestTemplateZoho.zohoRequestToServer(urls);
							JSONArray responseResult = new JSONArray("[" + response1.toString() + "]");
							for (int i = 0; i < responseResult.length(); i++) {
								JSONObject responseData = (JSONObject) responseResult.get(i);
								transactionReceiptVOs.setProtectionEligibility(responseData.getString("hostedpage_id"));
								transactionReceiptVOs.setPayerStatus(responseData.getString("url"));
								JSONArray data = new JSONArray("[" + responseData.getString("data") + "]");
								for (int j = 0; j < data.length(); j++) {
									JSONObject subscriptionData = (JSONObject) data.get(j);
									JSONArray subscription = new JSONArray("[" + subscriptionData.getString("subscription") + "]");
									for (int k = 0; k < subscription.length(); k++) {
										JSONObject subscriptionResponse = (JSONObject) subscription.get(k);
										transactionReceiptVOs.setProductId(subscriptionResponse.getString("product_id"));// =product_id;
										transactionReceiptVOs.setTxnId(subscriptionResponse.getString("subscription_id"));// =subscription_id;
										transactionReceiptVOs.setPaymentAmount(subscriptionResponse.getString("amount"));// =amount;
										transactionReceiptVOs.setPaymentStatus(subscriptionResponse.getString("payment_terms_label"));// =paymentStatus;
										transactionReceiptVOs.setPaymentDate(subscriptionResponse.getString("created_at"));// =created_at;
										transactionReceiptVOs.setOriginalpurchasedate(subscriptionResponse.getString("current_term_ends_at"));
										JSONArray customerData = new JSONArray("[" + subscriptionResponse.getString("customer") + "]");
										for (int l = 0; l < customerData.length(); l++) {
											JSONObject custResponse = (JSONObject) customerData.get(l);
											transactionReceiptVOs.setPayerId(custResponse.getString("customer_id"));// =customer_id;
											transactionReceiptVOs.setPayerEmail(custResponse.getString("email"));// =resemail;
											transactionReceiptVOs.setFirstName(custResponse.getString("first_name")+ " " + custResponse.getString("last_name"));// =display_name;
										}

										JSONArray planeData = new JSONArray("[" + subscriptionResponse.getString("plan") + "]");
										for (int l = 0; l < planeData.length(); l++) {
											JSONObject planResponse = (JSONObject) planeData.get(l);
											transactionReceiptVOs.setItemid(planResponse.getString("plan_code"));// =plan_code;
											transactionReceiptVOs.setTax(planResponse.getString("tax_percentage"));// =tax_percentage;
											transactionReceiptVOs.setItemNumber(planResponse.getString("plan_code"));// =plan_code;
											transactionReceiptVOs.setItemName(planResponse.getString("name"));// =name;
											transactionReceiptVOs.setPaymentFee(planResponse.getString("setup_fee"));// =setup_fee;
										}
									}
								}
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}

					
					boothAdminService.setTransactionHistoryOfSubscriptionAfterLogin(boothAdminLogin.getUserId(),transactionReceiptVOs);

					boothAdminLogin1 = boothAdminService.getProfileDetails(boothAdminLogin1.getUserId());
					}
				} else if (boothAdminLogin1.getPlanCode().equals("1")) {
					return "redirect:getSubscription";
				}

				List<EventVO> eventList = boothAdminService.getEventList(boothAdminLogin.getUserId(), pageid, total, boothAdminLogin1.getPlanCode());

				int rCount = boothAdminService.getEventCount(boothAdminLogin.getUserId());
				int pageCount;
				if (rCount % total == 0) {
					pageCount = rCount / total;
				} else {
					pageCount = rCount / total + 1;
				}

				modelMap.addAttribute(ServerConstants.EVENT_LIST, eventList);
				modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN, boothAdminLogin1);
				modelMap.addAttribute(ServerConstants.FOVBYUSER, fovbyuser.getZoomScale());
				modelMap.addAttribute(ServerConstants.PAGE_ID, pageid);
				modelMap.addAttribute("pageCount", pageCount);
				modelMap.addAttribute(ServerConstants.TOTAL, total);

				// }
				return ServerConstants.EVENT_LIST;

			} else if (("superadmin").equalsIgnoreCase(boothAdminLogin.getUserRole())) {
				List<EventVO> eventList = superadminService.getEventListForSuperAdmin();
				List<EventVO> eventListPagination = superadminService.getEventListForSuperAdminWithPagination(pageid,
						total);
				int rCount = boothAdminService.getEventCount(boothAdminLogin.getUserId());
				;
				if (!eventList.isEmpty()) {
					rCount = eventList.size();
				}

				int pageCount;
				if (rCount % total == 0) {
					pageCount = rCount / total;
				} else {
					pageCount = rCount / total + 1;
				}

				modelMap.addAttribute(ServerConstants.EVENT_LIST, eventListPagination);
				modelMap.addAttribute("userId", boothAdminLogin.getUserId());

				modelMap.addAttribute(ServerConstants.PAGE_ID, pageid);
				modelMap.addAttribute("pageCount", pageCount);
				modelMap.addAttribute(ServerConstants.TOTAL, total);
				modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN, boothAdminLogin1);
				result1 = "SuperAdminEventList";
			}
		}
		return result1;
	}
	
	@RequestMapping(value="create-event.html")
	public String createEventHtml(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin==null){
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}else{
			deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
			boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN,boothAdminLogin1);
			return "createEvent";	
		}
	     	
	}
	
	@RequestMapping(value="save-create-event.html")
	public String saveCreateEventHtml(@ModelAttribute("EventVO") EventVO eventVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes)
	{	
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin !=null){
			boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
			deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
			modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN,boothAdminLogin1);
			
			modelMap.addAttribute("event",eventVO);
			
			List<ImageEmailFormVO> emailImagesList=boothAdminService.getPreSetBackGrounds(boothAdminLogin.getUserId());
			modelMap.addAttribute("emailImagesList",emailImagesList);
			
			List<ImageEmailFormVO> thankYou=boothAdminService.getPreSetThankYouScreen(boothAdminLogin.getUserId());
			modelMap.addAttribute("thankYou",thankYou);
				
				
			List<ImageEmailFormVO> waterMarkImage=boothAdminService.getPreSetWaterMarkImageScreen(boothAdminLogin.getUserId());
			modelMap.addAttribute("waterMarkImage",waterMarkImage);
				
				
			List<ImageEmailFormVO> lookAtTouch=boothAdminService.getPreSetLookAtTouchScreen(boothAdminLogin.getUserId());
			modelMap.addAttribute("lookAtTouch",lookAtTouch);
				
				
			List<ImageEmailFormVO> cameraTvScreenSaver=boothAdminService.getPreSetCameraTVScreen(boothAdminLogin.getUserId());
			modelMap.addAttribute("cameraTvScreenSaver",cameraTvScreenSaver);
			
			Fovbyuser fovbyuser=boothAdminService.getFovTableData(boothAdminLogin1.getUserId());
			modelMap.addAttribute(ServerConstants.FOVBYUSER,fovbyuser);
		
			return "eventBackground";
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
	}
	
	@RequestMapping(value="uploadBackgroundImage",method = RequestMethod.POST)
	public String uploadBackgroundImage(
			@RequestParam(value="files",required=false) MultipartFile[] files,
			@RequestParam(value="thankyoufiles",required=false) MultipartFile thankyoufiles,
			@RequestParam(value="lookAtTouchScreen",required=false) MultipartFile lookAtTouchScreen,
			@RequestParam(value="cameraTVScreenSaver",required=false) MultipartFile cameraTVScreenSaver,
			@RequestParam(value="waterMarkImage",required=false) MultipartFile waterMarkImage,
			@ModelAttribute("AdminPictureVO") AdminPictureVO adminPictureVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) throws IOException{
		 	
			String rootPath = new java.io.File(request.getSession().getServletContext().getRealPath("")+"/.."+ServerConstants.IMAGES).getCanonicalPath();
		
		HttpSession session = request.getSession();
        ServletContext sc = session.getServletContext();
        String default4Images = sc.getRealPath("/");
        AdminPictureVO adminPictureVOs;
        
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin !=null){
			deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
		adminPictureVO.setCreatedBy(boothAdminLogin.getUserId());
		boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
		if(boothAdminLogin1.getPlanCode().equals("1")){
			return "eventBackground";
		}else{
		adminPictureVOs=boothAdminService.uploadBackgroundImage(adminPictureVO,files,rootPath,thankyoufiles,lookAtTouchScreen,cameraTVScreenSaver,waterMarkImage,default4Images);
		}
		if((ServerConstants.SUCCESS).equalsIgnoreCase(adminPictureVOs.getResult())){
			loginService.updateTour(boothAdminLogin.getUserId());
			return "redirect:addImagesOfEvent?eventId="+adminPictureVOs.getEId();
		}else{
			return "eventBackground";
		}
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
	}
	
	
	@RequestMapping(value="getUploadedImages")
	public String getUploadedImages(@RequestParam("eventId")Integer eid,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin !=null){
			
			deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
			
			List<AdminPictureVO> adminPictureVOs2=boothAdminService.getPicList(eid,boothAdminLogin.getUserId());
			boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN,boothAdminLogin1);
			
			modelMap.addAttribute("adminPictureVOs2",adminPictureVOs2);
			
			modelMap.addAttribute("eid",eid);
			
			List<AdminBoothEventPicture> notConfiguredImage=boothAdminService.notConfiguredImage(eid,boothAdminLogin.getUserId());
			modelMap.addAttribute("notConfiguredImage",notConfiguredImage.size());
			
			Adminboothevent adminboothevent=boothAdminService.getEventDetails(eid);
			modelMap.addAttribute("eventName",adminboothevent.getEventName());
			modelMap.addAttribute("eventZoomScale",adminboothevent.getZoomScale());
			
			List<ImageEmailFormVO> emailImagesList=boothAdminService.getPreSetBackGrounds(boothAdminLogin.getUserId());
			modelMap.addAttribute("emailImagesList",emailImagesList);
			
			List<ImageEmailFormVO> setThankYouByEventId=boothAdminService.getPreSetThankYouScreenBasedOnEventId(boothAdminLogin.getUserId(),eid,boothAdminLogin1.getPlanCode());
			modelMap.addAttribute("setThankYouByEventId",setThankYouByEventId);
			
			List<ImageEmailFormVO> setWaterMarkImageByEventId=boothAdminService.getPreSetWaterMarkImageBasedOnEventId(boothAdminLogin.getUserId(),eid,boothAdminLogin1.getPlanCode());
			modelMap.addAttribute("setWaterMarkImageByEventId",setWaterMarkImageByEventId);
			
			List<ImageEmailFormVO> setLookAtTouchByEventId=boothAdminService.getPreSetLookAtTouchScreenBasedOnEventId(boothAdminLogin.getUserId(),eid,boothAdminLogin1.getPlanCode());
			modelMap.addAttribute("setLookAtTouchByEventId",setLookAtTouchByEventId);
			
			List<ImageEmailFormVO> setCameraTVScreenByEventId=boothAdminService.getPreSetCameraTVScreenSaverBasedOnEventId(boothAdminLogin.getUserId(),eid,boothAdminLogin1.getPlanCode());
			modelMap.addAttribute("setCameraTVScreenByEventId",setCameraTVScreenByEventId);
			
			List<ImageEmailFormVO> thankYou=boothAdminService.getPreSetThankYouScreen(boothAdminLogin.getUserId());
			modelMap.addAttribute("thankYou",thankYou);
				
			List<ImageEmailFormVO> waterMarkImage=boothAdminService.getPreSetWaterMarkImageScreen(boothAdminLogin.getUserId());
			modelMap.addAttribute("waterMarkImage",waterMarkImage);
				
			List<ImageEmailFormVO> lookAtTouch=boothAdminService.getPreSetLookAtTouchScreen(boothAdminLogin.getUserId());
			modelMap.addAttribute("lookAtTouch",lookAtTouch);
				
			List<ImageEmailFormVO> cameraTvScreenSaver=boothAdminService.getPreSetCameraTVScreen(boothAdminLogin.getUserId());
			modelMap.addAttribute("cameraTvScreenSaver",cameraTvScreenSaver);
			
			Fovbyuser fovbyuser=boothAdminService.getFovTableData(boothAdminLogin1.getUserId());
			modelMap.addAttribute(ServerConstants.FOVBYUSER,fovbyuser);
		
	     	return "uploadedImages";	
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
	}
	
	@RequestMapping(value="/getImagePushAdmin")
	 public String getImagePushAdmin(HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes)
	 {
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin ==null)
		{
			return "redirect:signUpPage";
		}
		List<DeviceRegistration> deviceRegistrations=boothAdminService.getRegisteredDevicePush(boothAdminLogin.getUserId());
		ThreadPoolTaskExecutor taskExecutor=pool.taskExecutor();
		imagetask.setDetails(deviceRegistrations, messageSource,rootPaths);
    	taskExecutor.execute(imagetask);
	
		redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,  "System Is Capturing Camera Device Screenshot, Make Sure You Are Logged In On Camera Device.");
	     return "redirect:boothSetUp";
	 }
	
	
	@RequestMapping(value="getFOVValueBasedOnEvent",method = RequestMethod.GET)
	@ResponseBody
	public Adminboothevent getFOVValueBasedOnEvent(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,@RequestParam(value="selectedEventId",required=false)String selectedEventId,@RequestParam(value="selectedEventName",required=false)String selectedEventName)
	{
		return boothAdminService.getFOVValueBasedOnEvent(Integer.parseInt(selectedEventId));
	}
	
	@ResponseBody
	@RequestMapping(value="/autoRefreshFov")
	public String autoRefresh(@RequestParam()Integer eventId,HttpServletRequest request)
	{
 		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
 		if(boothAdminLogin ==null){
 			return ServerConstants.REDIRECT_LOGIN_PAGE;
 		}
 		int oldListSize=(Integer)request.getSession().getAttribute("oldListSize");
 		
 		List<ImageFormVO> imagesList = iamuseDashboardService.getImagesList(""+boothAdminLogin.getUserId());
 		if(imagesList.size()>oldListSize)
 		{
 			String status="1";
			List<ImageFormVO> oldimagesList=(List)request.getSession().getAttribute("oldList");
	 		request.getSession().setAttribute("status", status);	
			request.getSession().setAttribute("oldListSize", imagesList.size());
			request.getSession().setAttribute("oldList", imagesList);
			return "redirect:boothSetUpByEvent?eventId"+eventId;
 		}
		return "redirect:boothSetUpByEvent?eventId"+eventId;
		
	}
	
	@RequestMapping(value="/getImagePushAdminCurrent")
	 public String getImagePushAdminCurrent(@RequestParam("eventId")Integer eventId,HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes)
	 {
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin ==null)
		{
			return "redirect:signUpPage";
		}
		deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
		ThreadPoolTaskExecutor taskExecutor=pool.taskExecutor();
		imagetask.setDetails(deviceRegistration, messageSource,rootPaths);
   		taskExecutor.execute(imagetask);
	
		redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,  "System Is Capturing Camera Device Screenshot, Make Sure You Are Logged In On Camera Device.");
	     return "redirect:boothSetUpByEvent?eventId="+eventId;
	 }
	
	
	@RequestMapping(value="sendIndividualMailImage")
	public String sendIndividualMailImage(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes,
			@RequestParam(value="emailId",required=false)String emailId,
			@RequestParam(value="imgId",required=false)Integer imgId,
			@RequestParam(value="eventId",required=false)Integer eventId)
	{
		String result=boothAdminService.sendIndividualMailImage(emailId,imgId,request);
		if((ServerConstants.SUCCESS).equals(result)){
			redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,"Email Send Successfully");
			return "redirect:eventGallery?eventId="+eventId;
		}else{
			redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,"Email Send Faild");
		return "redirect:eventGallery?eventId="+eventId;
		}
	}
	
	@RequestMapping(value="deletEventPicture")
	public String deletEventSinglePicture(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes,
			@RequestParam(value="picId",required=false)String picId,
			@RequestParam(value="eventId",required=false)Integer eventId)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		String result=boothAdminService.deletEventSinglePicture(picId,eventId,request);
		if((ServerConstants.SUCCESS).equals(result)){
			
			deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
			
			ThreadPoolTaskExecutor taskExecutor=pool.taskExecutor();
			taskImageUpdate.setDetails(deviceRegistration, messageSource,rootPaths);
	    	taskExecutor.execute(taskImageUpdate);
			redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,"Delete  image successfully");
			return ServerConstants.GET_UPLOAD_IMAGES+eventId;
		}else{
			redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,"Failed for deleting selecting image");
			return ServerConstants.GET_UPLOAD_IMAGES+eventId;
		}
	}
	
	
	
	@RequestMapping(value="editUploadBackgroundImage",method = RequestMethod.POST)
	public String editUploadBackgroundImage(@RequestParam(value="files",required=false) MultipartFile[] files,@ModelAttribute("AdminPictureVO") AdminPictureVO adminPictureVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes) throws IOException{
		
		String rootPath =  new java.io.File(request.getSession().getServletContext().getRealPath("")+"/.."+ServerConstants.IMAGES).getCanonicalPath();
		
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin !=null){
			adminPictureVO.setCreatedBy(boothAdminLogin.getUserId());
			AdminPictureVO adminPictureVOs=boothAdminService.editUploadBackgroundImage(adminPictureVO,files,rootPath);
		if((ServerConstants.SUCCESS).equalsIgnoreCase(adminPictureVOs.getResult())){
			redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,"Successfully uploaded background image");
			return ServerConstants.GET_UPLOAD_IMAGES+adminPictureVOs.getEId();
		}else{
			redirectAttributes.addFlashAttribute(ServerConstants.ERROR_MESSAGE,"failed for upload background image");
			return ServerConstants.GET_UPLOAD_IMAGES+adminPictureVO.getEId();
		}
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
	}
	
	
	
	@RequestMapping(value="updateWaterMarkLookAtTouchThankYouCameraScreen",method = RequestMethod.POST)
	public String updateWaterMarkLookAtTouchThankYouCameraScreen(@RequestParam(value="files",required=false) MultipartFile[] files,
			@RequestParam(value="thankyoufiles",required=false) MultipartFile thankyoufiles,
			@RequestParam(value="lookAtTouchScreen",required=false) MultipartFile lookAtTouchScreen,
			@RequestParam(value="cameraTVScreenSaver",required=false) MultipartFile cameraTVScreenSaver,
			@RequestParam(value="waterMarkImage",required=false) MultipartFile waterMarkImage,
			@ModelAttribute("AdminPictureVO") AdminPictureVO adminPictureVO,
			HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes) throws IOException{
		
		String rootPath =  new java.io.File(request.getSession().getServletContext().getRealPath("")+"/.."+ServerConstants.IMAGES).getCanonicalPath();
		
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin !=null){
			deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
			adminPictureVO.setCreatedBy(boothAdminLogin.getUserId());
			AdminPictureVO adminPictureVOs=boothAdminService.updateWaterMarkLookAtTouchThankYouCameraScreen(adminPictureVO,files,rootPath,thankyoufiles,lookAtTouchScreen,cameraTVScreenSaver,waterMarkImage);
		
		if((ServerConstants.SUCCESS).equalsIgnoreCase(adminPictureVOs.getResult())){
			if(boothAdminLogin.getUserRole().equals("superadmin")){
				redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,"Successfully uploaded background image");
				return "redirect:getUploadedImagesSA?eventId="+adminPictureVOs.getEId();
			}else{
			redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,"Successfully uploaded background image");
			return ServerConstants.GET_UPLOAD_IMAGES+adminPictureVOs.getEId();
			}
		}else{
			redirectAttributes.addFlashAttribute(ServerConstants.ERROR_MESSAGE,"failed for upload background image");
			return ServerConstants.GET_UPLOAD_IMAGES+adminPictureVOs.getEId();
		}
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
	}
	
}