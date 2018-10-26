package com.iamuse.admin.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Blob;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import com.iamuse.admin.VO.AdminPictureVO;
import com.iamuse.admin.VO.DeviceVO;
import com.iamuse.admin.VO.EventVO;
import com.iamuse.admin.VO.ImageEmailFormVO;
import com.iamuse.admin.VO.ImageFormVO;
import com.iamuse.admin.VO.OptionsReports;
import com.iamuse.admin.VO.PaginationVO;
import com.iamuse.admin.VO.SignInVO;
import com.iamuse.admin.VO.SubscribedCustomer;
import com.iamuse.admin.VO.TransactionHistoryVO;
import com.iamuse.admin.VO.TransactionReceiptVO;
import com.iamuse.admin.entity.AdminBoothEventPicture;
import com.iamuse.admin.entity.Adminboothevent;
import com.iamuse.admin.entity.BoothAdminLogin;
import com.iamuse.admin.entity.DeviceRegistration;
import com.iamuse.admin.entity.Fovbyuser;
import com.iamuse.admin.entity.SubscriptionMaster;
import com.iamuse.admin.entity.TransactionMaster;
import com.iamuse.admin.entity.UploadImage;
import com.iamuse.admin.service.BoothAdminService;
import com.iamuse.admin.service.IamuseDashboardService;
import com.iamuse.admin.service.SuperadminService;
import com.iamuse.admin.util.DateUtil;
import com.iamuse.admin.util.ImagePushNotificationTask;
import com.iamuse.admin.util.MailUtil;
import com.iamuse.admin.util.PushNotificationTask;
import com.iamuse.admin.util.PushNotificationTaskFOV;
import com.iamuse.admin.util.PushNotificationTaskImagesUpdate;
import com.iamuse.admin.util.ServiceRequestTemplateZoho;
import com.iamuse.admin.util.ThreadPool;
import com.paypal.constants.ServerConstants;
import twitter4j.TwitterException;

@Controller
@SuppressWarnings("unused")
public class BoothAdminController {
	
	@Autowired BoothAdminService boothAdminService;
	@Autowired IamuseDashboardService iamuseDashboardService;
	@Autowired SuperadminService superadminService;
	@Autowired ThreadPool pool;
	@Autowired PushNotificationTask task;
	@Autowired PushNotificationTaskFOV fovTask;
	@Autowired PushNotificationTaskImagesUpdate taskImageUpdate;
	@Autowired ImagePushNotificationTask imagetask;
	@Autowired MessageSource messageSource;
	@Autowired MailUtil mailUtil;
	@Autowired ServiceRequestTemplateZoho serviceRequestTemplateZoho;
	
	private Locale locale = LocaleContextHolder.getLocale();
	private static final Logger log = Logger.getLogger(BoothAdminController.class);
	List<DeviceRegistration> deviceRegistration;
	BoothAdminLogin boothAdminLogin;
	BoothAdminLogin boothAdminLogin1;
	String rootPaths = System.getProperty("catalina.home");
	
	@RequestMapping(value="signUpPage")
	public String signUpPage(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)
	{
	     	return "signUpPage";	
	}
	
	@RequestMapping(value="createBootAdmin")
	public String createBoothAdmin(@ModelAttribute("SignInVO") SignInVO signInVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin ==null){
			SignInVO signInVOs=boothAdminService.createBoothAdmin(signInVO);
		     	if((ServerConstants.SUCCESS).equals(signInVOs.getResult())){
		     		boothAdminLogin1=boothAdminService.getProfileDetails(signInVOs.getUserId());
					deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin1.getUserId());
					modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
		     		request.getSession().setAttribute(ServerConstants.BOOTH_ADMIN_LOGIN,boothAdminLogin1);
		     		request.getSession().setAttribute("status","0");
					request.getSession().setAttribute("oldListSize", 0);
		     		return ServerConstants.GET_SUBS;
		     	}else if(("exist").equals(signInVOs.getResult())){
		     		redirectAttributes.addFlashAttribute(ServerConstants.ERROR_MESSAGE,"Booth Admin already exists");
		     		return "redirect:signUpPage";
		     	}
		}else{
	     	return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
		return ServerConstants.REDIRECT_LOGIN_PAGE;
	}	
	
	@RequestMapping(value="getDevices")
	public String getDevices(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes) throws ParseException
	{
		return "redirect:getRegisteredDevice";
	}
	
	@RequestMapping(value="getRegisteredDevice")
	public String getRegisteredDevice(@ModelAttribute("SignInVO") SignInVO signInVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes) throws ParseException
	{
		String result1="registeredDevice";
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin !=null){
			boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
			deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
					if(("boothadmin").equalsIgnoreCase(boothAdminLogin.getUserRole())){
							modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION, deviceRegistration);
							modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN,boothAdminLogin1);
							DeviceVO deviceVO=boothAdminService.grtDeviceTockenAndIP(boothAdminLogin.getUserId());
							modelMap.addAttribute("deviceVO",deviceVO);
							return "registeredDevice";
					}else if(("superadmin").equalsIgnoreCase(boothAdminLogin.getUserRole())){
							modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
							modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN,boothAdminLogin1);
							result1="registeredDeviceSuperAdmin";
							}
					return result1;
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
		}
	
	@ResponseBody
	@RequestMapping(value="syncDevice")
	public String syncDevice(@RequestParam("deviceId")Integer deviceId,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes) throws ParseException
	{
		String result;
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin !=null){
				boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
				result=boothAdminService.syncDevice(boothAdminLogin1.getUserId(),deviceId);
				if((ServerConstants.SUCCESS).equals(result)){
					return "redirect:getRegisteredDeviceConfig";
				}
		}else{
		return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
		return result;
	}
	
	@RequestMapping(value="setUpBackgroundImage")
	public String setUpBackgroundImage(@ModelAttribute("AdminPictureVO") AdminPictureVO adminPictureVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap){
		
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		String pageids="";
			if(pageids!=""){
				pageids=request.getParameter("pictureId");
			}else{
				pageids=request.getParameter("picId");
			}
		String eventIds=request.getParameter("eId");
		String position=request.getParameter("position");
		
		if(boothAdminLogin !=null){
				boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
				PaginationVO paginationVO=boothAdminService.getFirstLast(Integer.parseInt(eventIds),adminPictureVO.getPicId());
				modelMap.addAttribute("first", paginationVO.getFirst());
				modelMap.addAttribute("current", adminPictureVO.getPicId());
				modelMap.addAttribute("last",  paginationVO.getLast());
				modelMap.addAttribute("next", paginationVO.getNext());
				modelMap.addAttribute("previous",  paginationVO.getPrevious());
				modelMap.addAttribute("position" ,position);
				AdminPictureVO adminPictureVO1=boothAdminService.getImageConfigure(Integer.parseInt(pageids),boothAdminLogin1.getUserId(),boothAdminLogin1.getPlanCode());
				modelMap.addAttribute("adminPictureVO1",adminPictureVO1);
				modelMap.addAttribute("eid",Integer.parseInt(eventIds));
				List<AdminPictureVO> adminPictureVOs2=boothAdminService.getPicList(Integer.parseInt(eventIds),boothAdminLogin.getUserId());
				modelMap.addAttribute("adminPictureVOs2",adminPictureVOs2);
				modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN,boothAdminLogin1);
				deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
				modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
				return "setUpBackground";
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
	}
	
	@RequestMapping(value="saveCoordinatesOfImg")
	public String saveCoordinatesOfImg(@RequestParam(value="files",required=ServerConstants.MAKE_FALSE) MultipartFile files,@ModelAttribute("AdminPictureVO") AdminPictureVO adminPictureVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes) throws IOException{
		String  result;
		String rootPath;
		String pid="&position=";
		String eid="&eId=";
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin !=null){
			boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
				PaginationVO paginationVO=boothAdminService.getFirstLast(adminPictureVO.getEId(),adminPictureVO.getPicId());
				adminPictureVO.setUpdatedBy(boothAdminLogin1.getUserId());
				adminPictureVO.setPlanCode(boothAdminLogin1.getPlanCode()==null?"1":boothAdminLogin1.getPlanCode());
				deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin1.getUserId());
				modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
				rootPath = new java.io.File(request.getSession().getServletContext().getRealPath("")+"/.."+"/iAmuse_images/Admin_Picture/Image_mask").getCanonicalPath();
				result=boothAdminService.saveCoordinatesOfImg(adminPictureVO,files,rootPath);
				if((ServerConstants.SUCCESS).equals(result)){
							Integer posupdate=adminPictureVO.getPosition();
				    		if(("Save & Exit").equalsIgnoreCase(adminPictureVO.getFinish())){
				    			redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,"Thank You !! \n Go to your device login and downloads the events");
				    			return "redirect:getUploadedImages?eventId="+adminPictureVO.getEId();
				    		}else if(("Upload Now").equalsIgnoreCase(adminPictureVO.getFinish())){
				    			return ServerConstants.SETUP_BACKGROUND_IMAGE+adminPictureVO.getPicId()+eid+adminPictureVO.getEId()+pid+(posupdate-1);
				    		}else if(("Previous").equalsIgnoreCase(adminPictureVO.getFinish())){
				    			return ServerConstants.SETUP_BACKGROUND_IMAGE+paginationVO.getPrevious()+eid+adminPictureVO.getEId()+pid+(adminPictureVO.getPosition()-2);
				    		}else{
				    			return ServerConstants.SETUP_BACKGROUND_IMAGE+paginationVO.getNext()+eid+adminPictureVO.getEId()+pid+adminPictureVO.getPosition();
				    		}
				}
				return ServerConstants.SETUP_BACKGROUND_IMAGE+paginationVO.getNext()+eid+adminPictureVO.getEId()+pid+adminPictureVO.getPosition();
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
	}
	
	@RequestMapping(value="deleteEvent")
	public String deleteEvent(@RequestParam("eventId")Integer eid,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin !=null){
			deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
				String result=boothAdminService.deleteEvent(eid,boothAdminLogin.getUserId());
				if(result.equals(ServerConstants.SUCCESS)){
					if(!deviceRegistration.isEmpty()){
						ThreadPoolTaskExecutor taskExecutor=pool.taskExecutor();
						taskImageUpdate.setDetails(deviceRegistration, messageSource,rootPaths);
						taskExecutor.execute(taskImageUpdate);
					return "redirect:getEventList";
				}
					return "redirect:getEventList";	
		}
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
		return ServerConstants.REDIRECT_LOGIN_PAGE;
	}
	
	@RequestMapping(value="eventReportDetails")
	public String eventReportDetails(@RequestParam ("eventId")Integer eventId,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin !=null){
				deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
				modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
				boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
				modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN,boothAdminLogin1);
		
					List<ImageEmailFormVO> emailImagesLists = iamuseDashboardService.getEventImagesSummaryLists(""+boothAdminLogin.getUserId(),eventId);
					modelMap.addAttribute("emailImagesLists", emailImagesLists);	
						
					EventVO eventVO=boothAdminService.getEventDetails(boothAdminLogin.getUserId(),eventId);
					modelMap.addAttribute("events",eventVO);
					
					OptionsReports optionsReports=boothAdminService.getEventReportDetails(boothAdminLogin.getUserId(),eventId);
					modelMap.addAttribute(ServerConstants.OPTIONS_REPORTS,optionsReports);
					
					List<ImageEmailFormVO> emailImagesList = iamuseDashboardService.getEmailImagesListBasedOnEventID(boothAdminLogin.getUserId(),eventId,eventVO.getEventName());
					modelMap.addAttribute(ServerConstants.EMAIL_IMAGE_LIST,emailImagesList);
					modelMap.addAttribute("eventIdsss",eventId);
					request.getSession().setAttribute(ServerConstants.EMAIL_IMAGE_LIST,emailImagesList);
				
				return "viewEventDatails";
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
	}
	
	@RequestMapping(value="eventGallery")
	public String eventGallery(@RequestParam(value="eventId",required=ServerConstants.MAKE_TRUE)Integer eventId,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		String pageids=request.getParameter(ServerConstants.PAGE_ID);
		String totals=request.getParameter(ServerConstants.TOTAL);
		int pageid=0;
		int total=0;
		if(pageids==null  && totals==null){
			pageid=1;
			total=51;
		}
		if(total==0){
			total=Integer.parseInt(totals);
			pageid=Integer.parseInt(pageids);
		}	
		if(boothAdminLogin !=null){
			deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
			boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
				List<ImageEmailFormVO> emailImagesList = iamuseDashboardService.getEmailImagesLists(""+boothAdminLogin.getUserId(),eventId,pageid,total);
				modelMap.addAttribute(ServerConstants.EMAIL_IMAGE_LIST, emailImagesList);
				
				OptionsReports optionsReports=boothAdminService.getEventReportDetails(boothAdminLogin.getUserId(),eventId);
				modelMap.addAttribute(ServerConstants.OPTIONS_REPORTS,optionsReports);
				modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN,boothAdminLogin1);
				int rCount=boothAdminService.getCountByEvent(boothAdminLogin.getUserId(),eventId);
				int pageCount;
				if(rCount%total==0)
				{
					 pageCount=rCount/total;
				}
				else
				{
					 pageCount=rCount/total+1;
				}
				if(emailImagesList.size()>0){
				modelMap.addAttribute("eventName",emailImagesList.get(0).getEventName());
				}
				modelMap.addAttribute(ServerConstants.PAGE_ID,pageid);
				modelMap.addAttribute("pageCount",pageCount);
				modelMap.addAttribute(ServerConstants.TOTAL,total);
				
				return "eventGallery";
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
	}
	
	@RequestMapping(value="getProfileDetails")
	public String getProfileDetails(@ModelAttribute("SignInVO")SignInVO signInVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin !=null){
			deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
			
			boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN,boothAdminLogin1);
			
			return "myProfile";
			}
		return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
	
	@ResponseBody
	@RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
	public void showImage(@RequestParam("id") Integer userId, HttpServletResponse response,HttpServletRequest request)
	 {
		
		try{
			if(userId !=0){
				boothAdminLogin1=boothAdminService.getProfileDetails(userId);
				Blob imageBytes = boothAdminLogin1.getImage();
				int blobLength = (int) imageBytes.length();  
				byte[] blobAsBytes = imageBytes.getBytes(1, blobLength);
			    response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			    response.getOutputStream().write(blobAsBytes);
			    response.getOutputStream().close();
			}
		}catch(Exception e){
			log.info("BoothAdminController Method : imageDisplay");
			log.error("Error imageDisplay",e);
		}
	}
	
	@RequestMapping(value="editProfileDetails")
	public String editProfileDetails(@ModelAttribute("SignInVO")SignInVO signInVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin !=null){
			deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
			boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN,boothAdminLogin1);
			return "editMyProfile";
		}
		return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
	
	@RequestMapping(value="updateProfileDetails")
	public String updateProfileDetails(@ModelAttribute("SignInVO")SignInVO signInVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes,@RequestParam("file") MultipartFile file)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		try {
			if(!file.isEmpty()){
				if(file.getSize()>0 && file.getSize() < 60000){
					Blob blob = Hibernate.createBlob(file.getInputStream());
					signInVO.setImage(blob);
					signInVO.setImageFileName(file.getOriginalFilename());
				}else{
					redirectAttributes.addFlashAttribute(ServerConstants.ERROR_MESSAGE,"Upload Profile Images should be less than 60 KB size");
					return "redirect:editProfileDetails";
				}
			}
			String result=boothAdminService.updateProfileDetails(boothAdminLogin.getUserId(),signInVO);
			if(result.equals(ServerConstants.SUCCESS)){
				redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,"Your Profile Updated Successfully");
			return "redirect:getProfileDetails";
			}
		} catch (IOException e) {
			log.info("BoothAdminController Method : updateProfileDetails");
			log.error("Error updateProfileDetails",e);
		}
		return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
	
	@RequestMapping(value="getContactEmail")
	public String loginpage(@RequestParam("eventId")Integer eventId,@ModelAttribute("ImageEmailFormVO") ImageEmailFormVO imageEmailFormVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)
	{   
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin !=null){
			deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
			boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
			List<ImageEmailFormVO> emailImagesList = iamuseDashboardService.getEmailImagesListCSV(""+boothAdminLogin.getUserId(),eventId);
			modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN,boothAdminLogin1);
			modelMap.addAttribute("eventId",eventId);
			modelMap.addAttribute(ServerConstants.EMAIL_IMAGE_LIST,emailImagesList);
			return "contactEmail";
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
	}
	
	@RequestMapping(value="getSubscription")
	public String getSubscription(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap , RedirectAttributes redirectAttributes) throws ParseException, JSONException, IOException
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin!=null){
			boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
			deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
		if(("boothadmin").equalsIgnoreCase(boothAdminLogin.getUserRole())){
			modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
			if(boothAdminLogin1.getSubUpdatedDate()!=null){
				modelMap.addAttribute(ServerConstants.VALID_FROM,boothAdminLogin1.getSubUpdatedDate());
				modelMap.addAttribute("validTill",DateUtil.addDays(boothAdminLogin1.getSubUpdatedDate(), 30));
			    }
			    modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN,boothAdminLogin1);
			    modelMap.addAttribute(ServerConstants.VALID_FROM,boothAdminLogin1.getSubUpdatedDate());
			    modelMap.addAttribute("boothAdminLogin1",boothAdminLogin1);
			    List<TransactionHistoryVO> transactionHistoryVOs=boothAdminService.getTransactionHistory(boothAdminLogin.getUserId());
			    modelMap.addAttribute("transactionHistoryVOs",transactionHistoryVOs);
			    List<SubscriptionMaster> subscriptionMaster=boothAdminService.getSubscriptionDetails();
				modelMap.addAttribute("subscriptionMaster",subscriptionMaster);
				Object response1=serviceRequestTemplateZoho.zohoRequestToServer("https://subscriptions.zoho.com/api/v1/plans?filter_by=PlanStatus.All");
				modelMap.addAttribute("plans",response1.toString());
				}
					return ServerConstants.SUBSCRIPTION;
				}else{
					 return ServerConstants.REDIRECT_LOGIN_PAGE;
				}

	    }
	
	@RequestMapping(value="createCustomer")
	public String createCustomer(@RequestParam("planCode")String planCode,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap , RedirectAttributes redirectAttributes) throws ParseException, IOException
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin!=null){
			boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
			deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
		if(("boothadmin").equalsIgnoreCase(boothAdminLogin.getUserRole())){
			modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
			if(boothAdminLogin1.getSubUpdatedDate()!=null){
				modelMap.addAttribute(ServerConstants.VALID_FROM,boothAdminLogin1.getSubUpdatedDate());
				modelMap.addAttribute("validTill",DateUtil.addDays(boothAdminLogin1.getSubUpdatedDate(), 30));
			    }
			    modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN,boothAdminLogin1);
			    modelMap.addAttribute(ServerConstants.VALID_FROM,boothAdminLogin1.getSubUpdatedDate());
			    List<TransactionHistoryVO> transactionHistoryVOs=boothAdminService.getTransactionHistory(boothAdminLogin.getUserId());
			    modelMap.addAttribute("transactionHistoryVOs",transactionHistoryVOs);
			    List<SubscriptionMaster> subscriptionMaster=boothAdminService.getSubscriptionDetails();
				modelMap.addAttribute("subscriptionMaster",subscriptionMaster);
			
				Object response1=serviceRequestTemplateZoho.zohoRequestToServer("https://subscriptions.zoho.com/api/v1/plans/"+planCode);
				modelMap.addAttribute("plan",response1.toString());
			}
			return "createCustomer";
		}
		return ServerConstants.REDIRECT_LOGIN_PAGE;
		
	}
	
	@ResponseBody
	@RequestMapping(value="subscribedPlan", method = RequestMethod.POST,produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?>  saveSubscribedPlan(@RequestBody SubscribedCustomer subscribedCustomer ,HttpServletRequest request) throws IOException
	{
		Object response1=serviceRequestTemplateZoho.zohoRequestToServer("https://subscriptions.zoho.com/api/v1/subscriptions");
		return new ResponseEntity<>( response1.toString(), HttpStatus.OK);
	}
	
	@RequestMapping(value="closeSubs")
	public String closeSubs(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)
	{
		return ServerConstants.GET_SUBS;
	}
	
	@RequestMapping(value="boothSetUp")
	public String boothSetUp(@ModelAttribute("ImageFormVO") ImageFormVO imageFormVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin ==null){
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
		deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
		modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
		boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
		List<ImageFormVO> imageFormVO1 = new ArrayList<>();
		List<ImageFormVO> imagesList = iamuseDashboardService.getImagesList(""+boothAdminLogin.getUserId());
		modelMap.addAttribute("imagesList", imagesList);
		if(boothAdminLogin1.getIsDefaultRgb()==ServerConstants.MAKE_TRUE && boothAdminLogin1.getCurrentImageId()==0){
			imageFormVO.setHexValue(boothAdminLogin1.getHexValueDefault());
			imageFormVO.setRgbValue(boothAdminLogin1.getRgbValueDefault());
			imageFormVO.setRgbaValue(boothAdminLogin1.getRgbaValueDefault());
			
			String[] rgb=boothAdminLogin1.getRgbValueDefault().split(",");
			imageFormVO.setR(rgb[0]);
			imageFormVO.setG(rgb[1]);
			imageFormVO.setB(rgb[2]);
		    imageFormVO1.add(imageFormVO);
		    modelMap.addAttribute(ServerConstants.IMAGE_DETAILS,imageFormVO1);
		modelMap.addAttribute("id",""+boothAdminLogin1.getCurrentImageId());
		}else if(boothAdminLogin1.getIsDefaultRgb()==ServerConstants.MAKE_FALSE && boothAdminLogin1.getCurrentImageId()==0){
			imageFormVO.setHexValue(boothAdminLogin1.getHexValueManual());
			imageFormVO.setRgbValue(boothAdminLogin1.getRgbValueManual());
			imageFormVO.setRgbaValue(boothAdminLogin1.getRgbaValueManual());
			
			String[] rgb=boothAdminLogin1.getRgbValueManual().split(",");
			imageFormVO.setR(rgb[0]);
			imageFormVO.setG(rgb[1]);
			imageFormVO.setB(rgb[2]);
		    imageFormVO1.add(imageFormVO);
		    modelMap.addAttribute(ServerConstants.IMAGE_DETAILS,imageFormVO1);
		modelMap.addAttribute("id",""+boothAdminLogin1.getCurrentImageId());
		}
		modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN,boothAdminLogin1);
		UploadImage uploadImage=boothAdminService.getCurrentImages(boothAdminLogin.getUserId());
		if(uploadImage.getImageName() !=null){
			modelMap.addAttribute(ServerConstants.UPLOAD_IMAGE,uploadImage);
		}else{
			modelMap.addAttribute("hide","hide");
		}
	     	return "boothSetUp";
		}
	
	@RequestMapping(value="advanceBoothSetUp")
	public String advanceBoothSetUp(@ModelAttribute("SignInVO")SignInVO signInVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin!=null){
			boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
			deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN,boothAdminLogin1);
			modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
			signInVO.setUserId(boothAdminLogin.getUserId());
			String result=boothAdminService.advanceBoothSetUp(signInVO);
			if(result.equals(ServerConstants.SUCCESS)){
				redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,"Your event fov booth setup successfully");
				 return ServerConstants.BOOTH_SETUP;
			}
			}else{
				return ServerConstants.REDIRECT_LOGIN_PAGE;
			}
	    return ServerConstants.BOOTH_SETUP;
	}
	
	@RequestMapping(value="/saveSubscriptionDetails" )
	public String  saveEventTicketDetails(@RequestParam("hostedpage_id")String obj, HttpServletRequest request) throws IOException
	{
		
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		TransactionMaster transactionReceiptVOs=new TransactionMaster();
		Object response1=serviceRequestTemplateZoho.zohoRequestToServer("https://subscriptions.zoho.com/api/v1/hostedpages/"+obj);
			 try {
			     JSONArray responseResult = new JSONArray("["+response1.toString()+"]");
			     for (int i=0; i<responseResult.length();i++){
			         JSONObject responseData = (JSONObject) responseResult.get(i);
			         System.out.println("hostedPageid::"+responseData.getString("hostedpage_id"));
			         transactionReceiptVOs.setProtectionEligibility(responseData.getString("hostedpage_id"));
			         transactionReceiptVOs.setPayerStatus(responseData.getString("url"));
			         System.out.println("url::"+responseData.getString("url"));
			         JSONArray data = new JSONArray("["+responseData.getString("data")+"]");
			         for(int j=0; j<data.length(); j++) {
			        	 JSONObject subscriptionData = (JSONObject) data.get(j);

			        	 JSONArray subscription = new JSONArray("["+subscriptionData.getString("subscription")+"]");
				         for(int k=0; k<subscription.length(); k++) {
				        	 JSONObject subscriptionResponse = (JSONObject) subscription.get(k);
				        	 transactionReceiptVOs.setProductId(subscriptionResponse.getString("product_id"));//=product_id;
				        	 transactionReceiptVOs.setTxnId(subscriptionResponse.getString("subscription_id"));//=subscription_id;
				        	 transactionReceiptVOs.setPaymentAmount(subscriptionResponse.getString("amount"));//=amount;
				        	 transactionReceiptVOs.setPaymentStatus(subscriptionResponse.getString("payment_terms_label"));//=paymentStatus;
				        	 transactionReceiptVOs.setPaymentDate(subscriptionResponse.getString("created_at"));//=created_at;
				        	 transactionReceiptVOs.setOriginalpurchasedate(subscriptionResponse.getString("current_term_ends_at"));
				        	 JSONArray customerData = new JSONArray("["+subscriptionResponse.getString("customer")+"]");
					        	 for(int l=0; l<customerData.length(); l++) {
						        	 JSONObject custResponse = (JSONObject) customerData.get(l);
						        	 transactionReceiptVOs.setPayerId(custResponse.getString("customer_id"));//=customer_id; 
						        	 transactionReceiptVOs.setPayerEmail(custResponse.getString("email"));//=resemail;
								     transactionReceiptVOs.setFirstName(custResponse.getString("first_name")+" "+custResponse.getString("last_name"));//=display_name;
						         }
				        	 	
				        	  JSONArray planeData = new JSONArray("["+subscriptionResponse.getString("plan")+"]");
						         for(int l=0; l<planeData.length(); l++) {
						        	 JSONObject planResponse = (JSONObject) planeData.get(l);
						        	 transactionReceiptVOs.setItemid(planResponse.getString("plan_code"));//=plan_code;
						        	 transactionReceiptVOs.setTax(planResponse.getString("tax_percentage"));//=tax_percentage;
						        	 transactionReceiptVOs.setItemNumber(planResponse.getString("plan_code"));//=plan_code;
						        	 transactionReceiptVOs.setItemName(planResponse.getString("name"));//=name;
						        	 transactionReceiptVOs.setPaymentFee(planResponse.getString("setup_fee"));//=setup_fee;
						         }
				         }
			         }
			     }
			 } catch (JSONException e){
			     e.printStackTrace();
			 }
		
		TransactionReceiptVO transactionReceiptVO=boothAdminService.setTransactionHistoryOfSubscription(boothAdminLogin.getUserId(),transactionReceiptVOs);
			
		return "redirect:paymentReciepts?trsId="+transactionReceiptVO.getTrsId()+"&subId="+transactionReceiptVO.getSubId();
		
	}
	
	
	@RequestMapping(value="/paymentReciepts")
	public String getTransactionDetails(@RequestParam("trsId") String trsId,@RequestParam("subId") String subId,HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,RedirectAttributes redirectAttributes)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin !=null){
		TransactionReceiptVO transactionReceiptVO=boothAdminService.getTransactionDetails(trsId,subId);
		if(transactionReceiptVO.getResult().equals(ServerConstants.SUCCESS)){
			modelMap.addAttribute("transactionReceiptVO",transactionReceiptVO);
			return "paymentReciept";
		}
			return "paymentReciept";
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;	
		}
	}
	
	@RequestMapping(value="dbToCsv")
	public String dbToCsv(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes) throws IOException
	 {
		 int p;
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
	  int pageid=Integer.parseInt(request.getParameter(ServerConstants.PAGE_ID));
	  int total=Integer.parseInt(request.getParameter(ServerConstants.TOTAL));
	 
	  if(pageid==1)
		  p=0;
	  else
	     p=((pageid-1)*total);
	  
	  if(boothAdminLogin!=null){
		  
	  try{
	   String csvFileName = "EmailRecord.csv";
	   response.setContentType("text/csv");
	   // creates mock data
	   String headerKey = ServerConstants.CONTENT_DISPOSITION;
	   String headerValue = String.format("attachment; filename=\"%s\"",csvFileName);
	   response.setHeader(headerKey, headerValue);
	   // uses the Super CSV API to generate CSV data from the model data 
	   ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
	   String[] header = { "userName",ServerConstants.EVENT_NAME, "contactNo", "emailId"};
	   csvWriter.writeHeader(header);
	   csvWriter.close();
	      
	  } catch (Exception e) {
	   log.info("BoothAdminController Method : dbToCsv");
		log.error("Error dbToCsv" ,e);
	  }
	  }else{
		  return ServerConstants.REDIRECT_LOGIN_PAGE;
	  }
	return null;
	 }
	
	@RequestMapping(value="dbToImagesZip")
	public  String dbToImagesZip(@ModelAttribute("ImageEmailFormVO")ImageEmailFormVO imageEmailFormVO,@RequestParam(value="eventId",required=false)Integer eventId,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes) throws IOException
	 {
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		String name2;
		FileInputStream fin;
		 if(boothAdminLogin!=null){
			 String[] total=imageEmailFormVO.getImageIds();
		  if(("export").equals(imageEmailFormVO.getEventAction())){
			  List<ImageEmailFormVO> emailImagesList = iamuseDashboardService.getEmailImagesZipList(""+boothAdminLogin.getUserId(),total);
				  name2 = new java.io.File(request.getSession().getServletContext().getRealPath("")+"/..").getCanonicalPath();
			         try{
			        	 String date = FastDateFormat.getInstance("MM-dd-yyyy").format(System.currentTimeMillis( ));
		                        	String zipFile = "iAmuse-"+date+"-"+imageEmailFormVO.getEventName()+".zip";
		  					  		response.setHeader(ServerConstants.CONTENT_DISPOSITION,"attachment; filename=\"" + zipFile + "\"");
		  					  		response.setContentType("application/zip");
		  					  		ServletOutputStream outputStream = response.getOutputStream();
		                        byte[] buffer = new byte[1024];
		                         ZipOutputStream zout = new ZipOutputStream(outputStream);
		                         for(int i=0; i < total.length; i++)
		                         {
		                                fin = new FileInputStream(name2+emailImagesList.get(i).getMailImageUrl()+"/"+emailImagesList.get(i).getEventId()+"/"+emailImagesList.get(i).getMailImageName());
		                                zout.putNextEntry(new ZipEntry(emailImagesList.get(i).getMailImageName()));
		                                int length;
		                                while((length = fin.read(buffer)) > 0)
		                                {
		                                   zout.write(buffer, 0, length);
		                                }
		                                 zout.closeEntry();
		                                 fin.close();
		                         }
		                          zout.close();
		                          modelMap.addAttribute(ServerConstants.SUCCESS_MESSAGE,"Export Images Successfully");
		                }
		                catch(IOException ioe)
		                {
		                	 redirectAttributes.addFlashAttribute(ServerConstants.ERROR_MESSAGE,"fail for exporting the selected images succesfully ");
		                        log.info("BoothAdminController Method : dbToImagesZip");
		        				log.error("Error dbToImagesZip",ioe);
		                }
		  }else if(("delete").equals(imageEmailFormVO.getEventAction())){
				  String result = iamuseDashboardService.deleteMailedImage(""+boothAdminLogin.getUserId(),total);
				  if(result.equals(ServerConstants.SUCCESS)){
					  redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,"Selected Images Deleted");
					  return ServerConstants.EVENT_GALLERY+imageEmailFormVO.getEventId();
				  }else{
					  redirectAttributes.addFlashAttribute(ServerConstants.ERROR_MESSAGE,"Images Not Deleted");
					  return ServerConstants.EVENT_GALLERY+imageEmailFormVO.getEventId();
				  }
		  }else if(("resend").equals(imageEmailFormVO.getEventAction())){
			  
			  String result=boothAdminService.resendEmailImages(""+boothAdminLogin.getUserId(),total,request);
			  if(result.equals(ServerConstants.SUCCESS)){
				  redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,"Email Sent Successfully!");
				  return ServerConstants.EVENT_GALLERY+imageEmailFormVO.getEventId();
			  }else{
				  redirectAttributes.addFlashAttribute(ServerConstants.ERROR_MESSAGE,result);
				  return ServerConstants.EVENT_GALLERY+imageEmailFormVO.getEventId();
			  }
		  }
		  	return ServerConstants.EVENT_GALLERY+imageEmailFormVO.getEventId();
		 }else{
			 return ServerConstants.REDIRECT_LOGIN_PAGE;
		 }
		   }
	
	@RequestMapping(value="socialShare")
	public String shareFbOrTwiter(@RequestParam("userId")String u,@RequestParam("imageIds")String[] imageIds,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes)
	 {
		 if(!("null").equals(u)){
		String[] total=imageIds;
		  List<ImageEmailFormVO> emailImagesList = iamuseDashboardService.getEmailImagesShareList(""+u,total);
		  if(!emailImagesList.isEmpty()){
		  modelMap.addAttribute(ServerConstants.EMAIL_IMAGE_LIST,emailImagesList);
		  return "socialShare";
		  }
		  String referer = request.getHeader("Referer");
		    return "redirect:"+ referer;
		 }else{
			 return ServerConstants.REDIRECT_LOGIN_PAGE;
		 }
	 }
	
	@RequestMapping(value="/NotifyPage")
	public String NotifyPage(@ModelAttribute("EventVO") EventVO eventVO,HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,RedirectAttributes redirectAttributes)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin !=null){
	try{
		
					redirectAttributes.addFlashAttribute(ServerConstants.ERROR_MESSAGE,"Invalid Payment Please Contact To Merchant");
					return "redirect:getSubscription";
	}
	catch (Exception e) {
		log.info("BoothAdminController Method : NotifyPage");
		log.error("Error NotifyPage",e);
	}
	return ServerConstants.GET_SUBS;
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;	
		}
	}
	
	@RequestMapping(value="saveAdminDetails")
	public String saveAdminDetails(@ModelAttribute("EventVO") EventVO eventVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes)
	 {
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		eventVO.setCreatedBy(boothAdminLogin.getUserId());
		  if(boothAdminService.setAdminDetails(eventVO)){
			  redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,"Update Event Summary Successfully");
			  return "redirect:eventReportDetails?eventId="+eventVO.getEId();
	 }
		  return "redirect:eventReportDetails?eventId="+eventVO.getEId();
	 }
	
	@ResponseBody
	@RequestMapping(value="sendTestMail",method = RequestMethod.GET)
	public boolean sendTestMail(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,@RequestParam(value="email",required=ServerConstants.MAKE_FALSE)String email,RedirectAttributes redirectAttributes)
	{
		boolean results=ServerConstants.MAKE_FALSE;
		try {
			StringBuffer url = request.getRequestURL();
			String uri = request.getRequestURI();
			String host = url.substring(0, url.indexOf(uri));
			String testText="<html><body>"+
					"<table id=\"m_-5368927744985068358backgroundTable\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"+
					"<tbody><tr><td><table id=\"m_-5368927744985068358innerTable\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"+
					"<tbody><tr><td class=\"m_-5368927744985068358payloadCell\" valign=\"top\"><table style=\"border:1px solid rgb(207,207,207);border-radius:8px;background:rgb(255,255,255)\" border=\"0\" cellspacing=\"0\" width=\"100%\">"+
					"<tbody><tr><td style=\"color:rgb(85,85,85);font-size:14px;font-family:'helvetica neue',arial,serif;padding:30px 10px\" align=\"center\">"+
					"<p style=\"color:rgb(68,68,68);text-align:center;margin:0px;padding:0px\"><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT100_com_zimbra_url\"><a href=\"http://www.iamuse.com\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://www.iamuse.com&amp;source=gmail&amp;ust=1493380684676000&amp;usg=AFQjCNExSsY9fpbaIXKUYmJaDURNeFlELw\"><img alt=\"iAmuse\" longdesc=\"https://ci3.googleusercontent.com/proxy/tSnkDkFiofgBYd5c5rsqAFQE_sTYbRIdlGOTJCekl9GkbR2Yz4vb0tMUMQ=s0-d-e1-ft#http://www.iamuse.com\" height=\"150\" width=\"250\" src=\""+host+request.getContextPath()+"/resources/images/images/logo_iamuse.png"+"\" class=\"CToWUd\"></a></span></p>"+
				    "<p style=\"font-weight:600;font-size:16px\">Test Email</p>"+
				    "<h3 style=\"color:rgb(68,68,68);text-align:center;margin:0px;padding:0px\">We are pleased to deliver the picture you took with us.</h3><p>If you like it, spread the word</p><table border=\"0\" cellpadding=\"5\" cellspacing=\"0\" width=\"100%\">"+
                    "<tbody><tr><td align=\"center\"> <p><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT101_com_zimbra_url\"><a href=\"https://www.facebook.com/iamusebooth\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=https://www.facebook.com/iamusebooth&amp;source=gmail&amp;ust=1493380684677000&amp;usg=AFQjCNFLfToZNO2UisyTB9FWtiPfUFEhcA\"><img alt=\"\" height=\"77\" width=\"78\" src=\""+host+request.getContextPath()+"/resources/images/images/facebookIcon.jpg"+"\" class=\"CToWUd\"></a></span></p><p>Like us on Facebook</p></td>"+
                    "<td align=\"center\"><p><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT102_com_zimbra_url\"><a href=\"http://instagram.com/iamusepics\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://instagram.com/iamusepics&amp;source=gmail&amp;ust=1493380684677000&amp;usg=AFQjCNF5OifpP40I6GUbWMZA7Wq8I0Y4mw\"><img alt=\"\" height=\"76\" width=\"77\" src=\""+host+request.getContextPath()+"/resources/images/images/instagram.png"+"\" class=\"CToWUd\"></a></span></p><p>Follow us on Instagram</p></td>"+
                    "<td align=\"center\"><p><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT103_com_zimbra_url\"><a href=\"http://instagram.com/iamusepics\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://instagram.com/iamusepics&amp;source=gmail&amp;ust=1493380684677000&amp;usg=AFQjCNF5OifpP40I6GUbWMZA7Wq8I0Y4mw\"><img alt=\"\" height=\"77\" width=\"78\" src=\""+host+request.getContextPath()+"/resources/images/images/twitterIcon.jpg"+"\" class=\"CToWUd\"></a></span></p><p>Follow us on Twitter</p></td>"+
                    "</tr></tbody></table>"+
                    "<p>Visit our website <span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT104_com_zimbra_url\"><a href=\"http://www.iamuse.com\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://www.iamuse.com&amp;source=gmail&amp;ust=1493380684677000&amp;usg=AFQjCNE_v-p9Y1LQV-DpIv5GqwYEJDT-rQ\">www.iamuse.com</a></span></p>"+
                    "</td></tr></tbody></table></td></tr><tr>"+
                    "<td class=\"m_-5368927744985068358payloadCell\" style=\"height:40px;font-size:9px;font-family:'helvetica neue',arial,serif;color:rgb(136,136,136)\" align=\"right\" valign=\"top\"><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT105_com_zimbra_url\"><a style=\"color:rgb(136,136,136)\" href=\"http://iamuse.com\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://iamuse.com&amp;source=gmail&amp;ust=1493380684677000&amp;usg=AFQjCNHuUfOsnIEfdwOnnQQ9sl7Ljgn9ZA\">powered by iAmuse.com</a></span></td>"+
                    "</tr></tbody></table></td></tr></tbody></table></body></html>";
			
			if(mailUtil.sendTestEmail("IAMUSE <apps@iamuse.com>", email, "Test Mail",testText)){
				results=ServerConstants.MAKE_TRUE;
			}else{
				results=ServerConstants.MAKE_FALSE;
			}
		} catch (Exception e) {
			log.info("BoothAdminController Method : sendTestMail");
			log.error("Error sendTestMail",e);
			e.getMessage();
		}
		return results;
	}
	
	@ResponseBody
	@RequestMapping(value="fetchContactList",method = RequestMethod.GET)
	public List<ImageEmailFormVO> emailImagesList(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,@RequestParam(value="selectedEventId",required=ServerConstants.MAKE_FALSE)String selectedEventId,@RequestParam(value="selectedEventName",required=ServerConstants.MAKE_FALSE)String selectedEventName)
	{
		return iamuseDashboardService.getEmailImagesListBasedOnEventID(boothAdminLogin.getUserId(),Integer.parseInt(selectedEventId),selectedEventName);
	}
	
	@RequestMapping(value="advanceBoothSetUpByEvent")
	public String advanceBoothSetUpByEvent(@ModelAttribute("SignInVO")SignInVO signInVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin1!=null){
			boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN,boothAdminLogin1);
			deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
			Adminboothevent adminboothevent=boothAdminService.getEventDetails(signInVO.getEId());
			modelMap.addAttribute("adminboothevent",adminboothevent);
			signInVO.setUserId(boothAdminLogin.getUserId());
			String result=boothAdminService.advanceBoothSetUp(signInVO);
			if(result.equals(ServerConstants.SUCCESS)){
				 return "redirect:rgbSetup";
			}
			}else{
				return ServerConstants.REDIRECT_LOGIN_PAGE;
			}
	    return ServerConstants.BOOTH_SETUP;
	}
	
	@RequestMapping(value="finishConfiguration")
	public String finishConfiguration(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes)
	{
		redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,"Thank You");
		return "redirect:getSubscribedEventList";
	}
	
	@ResponseBody
	@RequestMapping(value="updateMaskingImageStatus",method = RequestMethod.GET)
	public String updateMaskingImageStatus(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,@RequestParam(value="pictureId",required=ServerConstants.MAKE_FALSE)Integer pictureId)
	{
		String result = null;
		if(boothAdminService.updateMaskingImageStatus(pictureId))
		{
			result="Update Image Mask";
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value="updateWaterMarkStatus",method = RequestMethod.GET)
	public String updateWaterMarkStatus(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,@RequestParam(value="pictureId",required=ServerConstants.MAKE_FALSE)Integer pictureId)
	{
		String result = null;
		if(boothAdminService.updateWaterMarkStatus(pictureId)){
			result="Update Image Mask";
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="setFbShareValue",method = RequestMethod.GET)
	public String setShareValue(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap, @RequestParam(value="userId",required=ServerConstants.MAKE_FALSE)int userId, @RequestParam(value="imagesId",required=ServerConstants.MAKE_FALSE)String imagesId)
	 {
	  String[] imagesIdList = imagesId.split(","); 
	  return boothAdminService.setShareValue(userId,imagesIdList);
	 }
	 
	@ResponseBody
	@RequestMapping(value="setTwitterShareValue",method = RequestMethod.GET)
	public String setTwitterShareValue(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap, @RequestParam(value="userId",required=ServerConstants.MAKE_FALSE)int userId, @RequestParam(value="imagesId",required=ServerConstants.MAKE_FALSE)String imagesId) throws MalformedURLException, IOException, TwitterException
	 {
	  String[] imagesIdList = imagesId.split(","); 
	 return boothAdminService.setTwitterShareValue(userId,imagesIdList);
	 }
	
	@RequestMapping(value="exportsContact")
	public  String exportsContact(@RequestParam("eventId")Integer eventId,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes) throws IOException
	 {
		 boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
	  if(boothAdminLogin!=null){
		  List<ImageEmailFormVO> emailImagesList = iamuseDashboardService.getEmailImagesListCSV(""+boothAdminLogin.getUserId(),eventId);
		  OptionsReports optionsReports=boothAdminService.getEventReportDetails(boothAdminLogin.getUserId(),eventId);
	try{
	   String csvFileName = "ExportsContact.csv";
	   response.setContentType("text/csv");
	   String headerKey = ServerConstants.CONTENT_DISPOSITION;
	   String headerValue = String.format("attachment; filename=\"%s\"",csvFileName);
	   response.setHeader(headerKey, headerValue);
	   ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
	   String[] header = { "userName",ServerConstants.EVENT_NAME, "contactNo","eventDate", "emailId", "subscribed"};
	   csvWriter.writeHeader(header);
	   for (ImageEmailFormVO eventL : emailImagesList) {
	    csvWriter.write(eventL, header);
	   }
	   String[] header2 = { "totalGuestSessions","totalGuests", "repeatGuests","photosSent", "emailsSent", "avgVisitorSession","SignUps","emailBounces","facebook","twitter"};
	   csvWriter.writeHeader(header2);
	    csvWriter.write(optionsReports, header2);
	   csvWriter.close();
	      
	  } catch (Exception e) {
	   log.info("BoothAdminController Method : exportsContact");
		log.error("Error exportsContact",e);
	  }
	  }else{
		  return ServerConstants.REDIRECT_LOGIN_PAGE;
	  }
	return null;
	 }
	 
	@RequestMapping(value="saveRGBValueBoothSetup",params={"id"})
	public String saveRGBValueBoothSetup(@ModelAttribute("ImageFormVO") ImageFormVO imageFormVO,@RequestParam(value="id", required=false) String imageId,HttpServletRequest request,HttpServletResponse response,ModelMap model,RedirectAttributes redirectAttributes)
	{
		 	boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
	 		boolean result;
	 		String checkMinOrMax;
	 		if(boothAdminLogin == null)
			{
				return ServerConstants.REDIRECT_LOGIN_PAGE;
			}
			try {
				boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
				deviceRegistration =boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
				String rgbval=request.getParameter("rgbValue");
				String hexval=request.getParameter("hexValue");
				String rgbaval=request.getParameter("rgbaValue");
				checkMinOrMax=request.getParameter("rgb");
				if(checkMinOrMax==null){
					checkMinOrMax="default";
				}
				if(("min").equals(checkMinOrMax) && ("default").equals(checkMinOrMax))
				{
				imageFormVO.setImageId(Integer.parseInt(imageId));
				imageFormVO.setRgbValue(rgbval);
				imageFormVO.setHexValue(hexval);
				imageFormVO.setRgbaValue(rgbaval);
				if(imageFormVO.getRgbValue()==null || ("").equals(imageFormVO.getRgbValue())){
					 imageFormVO.setRgbValue(0+","+0+","+0);
					   }
			    result=iamuseDashboardService.updateRGBValue(imageFormVO,""+boothAdminLogin1.getUserId());
			    if(result){
			    	boothAdminLogin1.setHexValueManual(hexval);
			    	if(rgbval==null || ("").equals(rgbval)){
			    			boothAdminLogin1.setRgbValueManual(0+","+0+","+0);
						}else{
							boothAdminLogin1.setRgbValueManual(rgbval);
						}
			    	boothAdminLogin1.setRgbaValueManual(rgbaval);
			    	boothAdminLogin1.setIsDefaultRgb(ServerConstants.MAKE_FALSE);
					request.getSession().setAttribute(ServerConstants.USER_MASTER, boothAdminLogin1);
			    }
				}
				else if(("max").equals(checkMinOrMax)){
					imageFormVO.setImageId(Integer.parseInt(imageId));
					imageFormVO.setRgbValue(rgbval);
					imageFormVO.setHexValue(hexval);
					imageFormVO.setRgbaValue(rgbaval);
					 if(imageFormVO.getRgbValue()==null || ("").equals(imageFormVO.getRgbValue())){
						 imageFormVO.setRgbValue(0+","+0+","+0);
						   }
				    result=iamuseDashboardService.updateRGBValueMax(imageFormVO,""+boothAdminLogin1.getUserId());
				    if(result){
				    	boothAdminLogin1.setIsDefaultRgb(ServerConstants.MAKE_FALSE);
				    	boothAdminLogin1.setHexValueManual(hexval);
				    	boothAdminLogin1.setRgbaValueManual(rgbaval);
						if(rgbval==null || ("").equals(rgbval)){
								boothAdminLogin1.setRgbValueManual(0+","+0+","+0);
							}else{
								boothAdminLogin1.setRgbValueManual(rgbval);
						}
						request.getSession().setAttribute(ServerConstants.USER_MASTER, boothAdminLogin1);
				    }
				}
				else{
					imageFormVO.setImageId(Integer.parseInt(imageId));
					imageFormVO.setRgbValue(rgbval);
					imageFormVO.setHexValue(hexval);
					imageFormVO.setRgbaValue(rgbaval);
					 if(imageFormVO.getRgbValue()==null || ("").equals(imageFormVO.getRgbValue())){
						 imageFormVO.setRgbValue(0+","+0+","+0);
						   }
					     result=iamuseDashboardService.updateRGBValue(imageFormVO,""+boothAdminLogin1.getUserId());
					     if(result){
					    	 boothAdminLogin1.setHexValueManual(hexval);
						    	if(rgbval==null || ("").equals(rgbval)){
						    		boothAdminLogin1.setRgbValueManual(0+","+0+","+0);
									}
									else{
										boothAdminLogin1.setRgbValueManual(rgbval);
									}
						    	boothAdminLogin1.setRgbaValueManual(rgbaval);
						    	boothAdminLogin1.setIsDefaultRgb(ServerConstants.MAKE_FALSE);
								request.getSession().setAttribute(ServerConstants.USER_MASTER, boothAdminLogin1);
						    }
				    }
					if(result){
						ThreadPoolTaskExecutor taskExecutor=pool.taskExecutor();
						log.info("dwhjdqwhjdqjdg"+rootPaths);
						task.setDetails(deviceRegistration, messageSource,rootPaths);
						taskExecutor.execute(task);
			    		redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE, "Transparent color saved");
			    		return ServerConstants.BOOTH_SETUP;
					}
				}
		 catch (Exception e) {
			 log.error("Error saveRGBValueBoothSetup",e);
			   log.info("BoothAdminController Method:saveRGBValueBoothSetup");
			}
			return "redirect:cropEdges";
		}
	 
	@RequestMapping(value="cropEdges",method = RequestMethod.GET)
	public String boothSetUpByEventConfig(@ModelAttribute("SignInVO") SignInVO signInVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes)
	{
		 	boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
			if(boothAdminLogin ==null){
				return ServerConstants.REDIRECT_LOGIN_PAGE;
			}else{
				boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
				deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
				modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN,boothAdminLogin1);
				modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
				
				Fovbyuser fovbyuser=boothAdminService.getFovTableData(boothAdminLogin1.getUserId());
				modelMap.addAttribute(ServerConstants.FOVBYUSER,fovbyuser);
				
				UploadImage uploadImage=boothAdminService.getCurrentImages(boothAdminLogin.getUserId());
				
				if(uploadImage.getImageName() !=null){
					modelMap.addAttribute(ServerConstants.UPLOAD_IMAGE,uploadImage);
				}else{
					modelMap.addAttribute("hide","hide");
				}
				
				SignInVO signInVO1=boothAdminService.getImageData(boothAdminLogin.getUserId());
				modelMap.addAttribute("signInVO1",signInVO1);
				return "cropEdges";
			}
		}
	 
	@RequestMapping(value="zoomScalePage")
	public String advanceBoothSetUpByEventConfig(@ModelAttribute("SignInVO") SignInVO signInVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes)
		{
	 		String result=null;
	 		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
			if(boothAdminLogin!=null){
				boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
				deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
				modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN,boothAdminLogin);
				UploadImage uploadImage=boothAdminService.getCurrentImages(boothAdminLogin.getUserId());
				if(uploadImage.getImageName() !=null){
						modelMap.addAttribute(ServerConstants.UPLOAD_IMAGE,uploadImage);
					}else{
						modelMap.addAttribute("hide","hide");
					}
				if(signInVO !=null){
					signInVO.setUserId(boothAdminLogin.getUserId());
					result=boothAdminService.advanceBoothSetUpConfig(signInVO);
					modelMap.addAttribute("signInVO",signInVO);
				}
				if((ServerConstants.SUCCESS).equals(result)){
					Fovbyuser fovbyuser=boothAdminService.getFovTableData(boothAdminLogin1.getUserId());
					modelMap.addAttribute(ServerConstants.FOVBYUSER,fovbyuser);
					ThreadPoolTaskExecutor taskExecutor=pool.taskExecutor();
					log.info("dwhjdqwhjdqjdg"+rootPaths);
					fovTask.setDetailsForFOV(deviceRegistration, messageSource,rootPaths);
					taskExecutor.execute(fovTask);
					 return "redirect:cropEdges";
				}
				}else{
					return ServerConstants.REDIRECT_LOGIN_PAGE;
				}
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
	 
	@RequestMapping(value="zoomPage")
	public String zoomPage(@ModelAttribute("SignInVO") SignInVO signInVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes)
		{
	 		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
			if(boothAdminLogin!=null){
				boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
				modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN,boothAdminLogin1);
				deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
				modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
				UploadImage uploadImage=boothAdminService.getCurrentImages(boothAdminLogin.getUserId());
				if(uploadImage.getImageName() !=null){
						modelMap.addAttribute(ServerConstants.UPLOAD_IMAGE,uploadImage);
					}else{
						modelMap.addAttribute("hide","hide");
					}
					Fovbyuser fovbyuser=boothAdminService.getFovTableData(boothAdminLogin1.getUserId());
					modelMap.addAttribute(ServerConstants.FOVBYUSER,fovbyuser);
					 return "zoomScalePage";
				}else{
					return ServerConstants.REDIRECT_LOGIN_PAGE;
				}
		}
	 	
	@RequestMapping(value="setZoomProfilePage",method = RequestMethod.POST)
	public String setZoomProfilePage(@ModelAttribute("SignInVO") SignInVO signInVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes){
				boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
				String result;
					if(boothAdminLogin==null){
						return ServerConstants.REDIRECT_LOGIN_PAGE;
					}else{
					result=boothAdminService.saveZoomScale(boothAdminLogin.getUserId(),signInVO);
					if(result.equals(ServerConstants.SUCCESS) && ("Save").equals(signInVO.getSave())){
							return "redirect:zoomPage";
					}
					}
			return ServerConstants.BOOTH_SETUP;
		}
	 
	@RequestMapping(value="addImagesOfEvent")
	public String testing(@RequestParam(value="eventId", required=ServerConstants.MAKE_TRUE)Integer eid,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes){
		 boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		 if(boothAdminLogin !=null && eid != 0){
			List<AdminPictureVO> adminPictureVOs2=boothAdminService.getPicList(eid,boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN,boothAdminLogin1);
			modelMap.addAttribute("adminPictureVOs2",adminPictureVOs2);
			modelMap.addAttribute("eid",eid);
			Adminboothevent adminboothevent=boothAdminService.getEventDetails(eid);
			modelMap.addAttribute(ServerConstants.EVENT_NAME,adminboothevent.getEventName());
			List<ImageEmailFormVO> emailImagesList=boothAdminService.getPreSetBackGrounds(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.EMAIL_IMAGE_LIST,emailImagesList);
			Fovbyuser fovbyuser=boothAdminService.getFovTableData(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.FOVBYUSER,fovbyuser);
			
			List<AdminBoothEventPicture> notConfiguredImage=boothAdminService.notConfiguredImage(eid,boothAdminLogin.getUserId());
			modelMap.addAttribute("notConfiguredImage",notConfiguredImage.size());
			
			deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
			
					return "addEventImage";	
			}else{
				return ServerConstants.REDIRECT_LOGIN_PAGE;
			}
	 }
	 
	@RequestMapping(value="addUploadBackgroundImage",method = RequestMethod.POST)
	public String addUploadBackgroundImage(@RequestParam(value="files",required=ServerConstants.MAKE_FALSE) MultipartFile[] files,@ModelAttribute("AdminPictureVO") AdminPictureVO adminPictureVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes) throws IOException{
		 	boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		 	String rootPath= new java.io.File(request.getSession().getServletContext().getRealPath("")+"/..").getCanonicalPath();
			if(boothAdminLogin !=null && rootPath !=null){
			adminPictureVO.setCreatedBy(boothAdminLogin.getUserId());
			AdminPictureVO adminPictureVOs=boothAdminService.editUploadBackgroundImage(adminPictureVO,files,rootPath+ServerConstants.IMAGES);
			if(adminPictureVOs !=null){
			if(adminPictureVOs.getResult().equalsIgnoreCase(ServerConstants.SUCCESS)){
				redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,ServerConstants.UPLOAD_SUCCESS);
				return ServerConstants.ADD_IMAGES_OF_EVENT+adminPictureVOs.getEId();
			}else{
				redirectAttributes.addFlashAttribute(ServerConstants.ERROR_MESSAGE, ServerConstants.UPLOAD_FAILED);
				return ServerConstants.ADD_IMAGES_OF_EVENT+adminPictureVOs.getEId();
			}}
			}else{
				return ServerConstants.REDIRECT_LOGIN_PAGE;
			}
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
	 
	@RequestMapping(value="delEventPicture")
	public String deletEventSinglePicture(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes,@RequestParam(value="picId",required=ServerConstants.MAKE_FALSE)String picId,@RequestParam(value="eventId",required=ServerConstants.MAKE_FALSE)Integer eventId)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin !=null){
			deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
			String result=boothAdminService.deletEventSinglePicture(picId,eventId,request);
			if(result.equals(ServerConstants.SUCCESS)){
				if(boothAdminLogin.getUserRole().equals("superadmin")){
					ThreadPoolTaskExecutor taskExecutor=pool.taskExecutor();
					taskImageUpdate.setDetails(deviceRegistration, messageSource,rootPaths);
			    	taskExecutor.execute(taskImageUpdate);
					redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,"Delete  image successfully");
					return "redirect:getUploadedImagesSA?eventId="+eventId;
				}else{
					ThreadPoolTaskExecutor taskExecutor=pool.taskExecutor();
					taskImageUpdate.setDetails(deviceRegistration, messageSource,rootPaths);
			    	taskExecutor.execute(taskImageUpdate);
					redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,"Delete  image successfully");
					return ServerConstants.ADD_IMAGES_OF_EVENT+eventId;
				}
			}else{
				redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,"Failed for deleting selecting image");
				return ServerConstants.ADD_IMAGES_OF_EVENT+eventId;
			}
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
		}
	
	@RequestMapping(value="publishNow")
	public String publishNow(@RequestParam(value="eid", required=false )Integer eventId,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes) throws ParseException
		{
			boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
			if(boothAdminLogin !=null ){
				if(boothAdminLogin.getUserRole().equals("superadmin")){
					return "redirect:getSubscribedEventList";
				}else{
				String result=boothAdminService.updateEventDate(eventId);
				if(("success").equals(result)){
				deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
				if(!deviceRegistration.isEmpty()){
					ThreadPoolTaskExecutor taskExecutor=pool.taskExecutor();
					taskImageUpdate.setDetails(deviceRegistration, messageSource,rootPaths);
					taskExecutor.execute(taskImageUpdate);
					redirectAttributes.addFlashAttribute("successMessage","Publish Successfully");
					return "redirect:getUploadedImages?eventId="+eventId;
				}else{
					return "redirect:getSubscribedEventList";
				}
				}
			}
			}
			return ServerConstants.REDIRECT_LOGIN_PAGE;
	}
	
	@ResponseBody
	@RequestMapping(value="validateTakeTestPicture",method = RequestMethod.GET)
	public boolean validateTakeTestPicture(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,@RequestParam(value="userId",required=ServerConstants.MAKE_FALSE)Integer userId,RedirectAttributes redirectAttributes)
	{
		boolean results=ServerConstants.MAKE_FALSE;
		try {
			Fovbyuser fovbyuser=boothAdminService.getFovTableData(userId);
			
			if(fovbyuser.getImageWidth()!=null){
				results=ServerConstants.MAKE_TRUE;
			}else{
				results=ServerConstants.MAKE_FALSE;
			}
		} catch (Exception e) {
			log.info("BoothAdminController Method : sendTestMail");
			log.error("Error sendTestMail",e);
			e.getMessage();
		}
		return results;
	}
	
}
