package com.iamuse.admin.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.iamuse.admin.VO.AdminPictureVO;
import com.iamuse.admin.VO.DeviceVO;
import com.iamuse.admin.VO.EventVO;
import com.iamuse.admin.VO.ImageEmailFormVO;
import com.iamuse.admin.VO.ImageFormVO;
import com.iamuse.admin.VO.PaginationVO;
import com.iamuse.admin.VO.SignInVO;
import com.iamuse.admin.entity.AdminBoothEventPicture;
import com.iamuse.admin.entity.Adminboothevent;
import com.iamuse.admin.entity.BoothAdminLogin;
import com.iamuse.admin.entity.DeviceRegistration;
import com.iamuse.admin.entity.Fovbyuser;
import com.iamuse.admin.entity.UploadImage;
import com.iamuse.admin.service.BoothAdminService;
import com.iamuse.admin.service.IamuseDashboardService;
import com.iamuse.admin.service.LoginService;
import com.iamuse.admin.service.SuperadminService;
import com.iamuse.admin.util.ImagePushNotificationTask;
import com.iamuse.admin.util.MailUtil;
import com.iamuse.admin.util.PushNotificationTask;
import com.iamuse.admin.util.PushNotificationTaskImagesUpdate;
import com.iamuse.admin.util.ThreadPool;
import com.paypal.constants.ServerConstants;

@Controller
public class AutoConfigController {
	
	@Autowired BoothAdminService boothAdminService;
	@Autowired IamuseDashboardService iamuseDashboardService;
	@Autowired SuperadminService superadminService;
	@Autowired ThreadPool pool;
	@Autowired PushNotificationTask task;
	@Autowired PushNotificationTaskImagesUpdate taskImageUpdate;
	@Autowired ImagePushNotificationTask imagetask;
	@Autowired MessageSource messageSource;
	@Autowired MailUtil mailUtil;
	@Autowired LoginService loginService;
	
	
	List<DeviceRegistration> deviceRegistration;
	BoothAdminLogin boothAdminLogin;
	BoothAdminLogin boothAdminLogin1;
	String rootPaths = System.getProperty("catalina.home");
	
	
	
	@RequestMapping(value="getRegisteredDeviceConfig")
	public String getDevices(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes) throws ParseException
	{
		return "redirect:getRegisteredDeviceConfigs";
	}
	
	@RequestMapping(value="getRegisteredDeviceConfigs")
	public String getRegisteredDevice(@ModelAttribute("SignInVO") SignInVO signInVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes) throws ParseException
	{
		String result1="registeredDeviceConfig";
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin !=null){
			deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
			boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
					if(("boothadmin").equalsIgnoreCase(boothAdminLogin.getUserRole())){
							modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
							modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN2,boothAdminLogin1);
							DeviceVO deviceVO=boothAdminService.grtDeviceTockenAndIP(boothAdminLogin.getUserId());
							modelMap.addAttribute("deviceVO",deviceVO);
							return "registeredDeviceConfig";
					}
					return result1;
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
		}
	
	@RequestMapping(value="boothSetUpByEventConfigFirst")
	public String boothSetUpByEventConfigFirst(@ModelAttribute("ImageFormVO") ImageFormVO imageFormVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin ==null){
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
		List<ImageFormVO> imageFormVO1 = new ArrayList<>();
		List<ImageFormVO> imagesList = iamuseDashboardService.getImagesList(""+boothAdminLogin.getUserId());
		modelMap.addAttribute("imagesList", imagesList);
		    boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
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
		else{
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
		modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN2,boothAdminLogin1);
		UploadImage uploadImage=boothAdminService.getCurrentImages(boothAdminLogin.getUserId());
		
		if(uploadImage.getImageName() !=null){
		modelMap.addAttribute(ServerConstants.UPLOAD_IMAGE,uploadImage);
		}/*else{
			modelMap.addAttribute("hide","hide");
		}*/
		deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
		modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
		return "boothOpenimagepage";
		
	}
	
	@RequestMapping(value="boothSetUpByEventConfigSecond",method = RequestMethod.GET)
	public String boothSetUpByEventConfigs(@ModelAttribute("SignInVO") SignInVO signInVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin ==null){
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
		return "redirect:boothSetUpByEventConfigs";
	}
	
	@RequestMapping(value="boothSetUpByEventConfigs",method = RequestMethod.GET)
	public String boothSetUpByEventConfig(@ModelAttribute("SignInVO") SignInVO signInVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin ==null){
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
		deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
		boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
		modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN2,boothAdminLogin1);
		modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
		Fovbyuser fovbyuser=boothAdminService.getFovTableData(boothAdminLogin1.getUserId());
		modelMap.addAttribute(ServerConstants.FOVBYUSER,fovbyuser);
		UploadImage uploadImage=boothAdminService.getCurrentImages(boothAdminLogin.getUserId());
		if(uploadImage.getImageName() !=null){
			modelMap.addAttribute(ServerConstants.UPLOAD_IMAGE,uploadImage);
		}/*else{
			modelMap.addAttribute("hide","hide");
		}*/
		//return "redirect:create-event-config.html";
		return "boothSetUpByEventConfig";
	}
	
	@RequestMapping(value="boothSetUpByEventConfig",method = RequestMethod.GET)
	public String boothSetUpByEventConfig(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin ==null){
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
		deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
		boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
		modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN2,boothAdminLogin1);
		modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
		UploadImage uploadImage=boothAdminService.getCurrentImages(boothAdminLogin.getUserId());
		if(uploadImage.getImageName() !=null){
			modelMap.addAttribute(ServerConstants.UPLOAD_IMAGE,uploadImage);
		}/*else{
			modelMap.addAttribute("hide","hide");
		}*/
		return "boothSetUpByEventConfig";
		//return "redirect:create-event-config.html";
	}
	
	@RequestMapping(value="advanceBoothSetUpByEventConfig")
	public String advanceBoothSetUpByEventConfig(@ModelAttribute("SignInVO1")SignInVO signInVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin!=null){
			boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN,boothAdminLogin);
			modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN2,boothAdminLogin1);
			Fovbyuser fovbyuser=boothAdminService.getFovTableData(boothAdminLogin1.getUserId());
			modelMap.addAttribute(ServerConstants.FOVBYUSER,fovbyuser);
			UploadImage uploadImage=boothAdminService.getCurrentImages(boothAdminLogin1.getUserId());
			//if(uploadImage.getImageName() !=null){
				modelMap.addAttribute(ServerConstants.UPLOAD_IMAGE,uploadImage);
				/*}else{
					modelMap.addAttribute("hide","hide");
				}*/
			signInVO.setUserId(boothAdminLogin1.getUserId());
			String result=boothAdminService.advanceBoothSetUpConfig(signInVO);
			modelMap.addAttribute("signInVO",signInVO);
			if((ServerConstants.SUCCESS).equals(result)){
				deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
				modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
				 return "zoomScaleImagePage";
			}
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
		return "redirect:boothSetUp";
		
	}
	
	@RequestMapping(value="/getImagePushAdminCurrentConfig")
	public String getImagePushAdminCurrent(HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes)
	 {
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin ==null)
		{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
			deviceRegistration=boothAdminService.getRegisteredDevicePush(boothAdminLogin.getUserId());
			ThreadPoolTaskExecutor taskExecutor=pool.taskExecutor();
			imagetask.setDetails(deviceRegistration, messageSource,rootPaths);
	  		taskExecutor.execute(imagetask);
			redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,  "System Is Capturing Camera Device Screenshot, Make Sure You Are Logged In On Camera Device.");
		    return "redirect:boothSetUpByEventConfigFirst";
	 }
	
	@RequestMapping(value="create-event-config.html")
	public String createEventHtml(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin==null){
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}else{
			deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
			modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN2,boothAdminLogin1);
			return "createEventConfig";	
		}
	}
	
	@RequestMapping(value="save-create-event-Config.html")
	public String saveCreateEventHtml(@ModelAttribute("EventVO") EventVO eventVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes)
	{	
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin !=null){
				boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
				modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN2,boothAdminLogin1);
				modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN,boothAdminLogin1);
				modelMap.addAttribute("event",eventVO);
				List<ImageEmailFormVO> emailImagesList=boothAdminService.getPreSetBackGrounds(boothAdminLogin.getUserId());
				modelMap.addAttribute(ServerConstants.EMAIL_IMAGE_LIST,emailImagesList);
				List<ImageEmailFormVO> thankYou=boothAdminService.getPreSetThankYouScreen(boothAdminLogin.getUserId());
				modelMap.addAttribute("thankYou",thankYou);
				List<ImageEmailFormVO> waterMarkImage=boothAdminService.getPreSetWaterMarkImageScreen(boothAdminLogin.getUserId());
				modelMap.addAttribute("waterMarkImage",waterMarkImage);
				List<ImageEmailFormVO> lookAtTouch=boothAdminService.getPreSetLookAtTouchScreen(boothAdminLogin.getUserId());
				modelMap.addAttribute("lookAtTouch",lookAtTouch);
				List<ImageEmailFormVO> cameraTvScreenSaver=boothAdminService.getPreSetCameraTVScreen(boothAdminLogin.getUserId());
				modelMap.addAttribute("cameraTvScreenSaver",cameraTvScreenSaver);
				deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
				modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
				return "eventBackgroundConfig";
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
	}
	
	@RequestMapping(value="uploadBackgroundImageConfig",method = RequestMethod.POST)
	public String uploadBackgroundImage(@RequestParam(value="files",required=ServerConstants.MAKE_FALSE) MultipartFile[] files,
			@RequestParam(value="thankyoufiles",required=ServerConstants.MAKE_FALSE) MultipartFile thankyoufiles,
			@RequestParam(value="lookAtTouchScreen",required=ServerConstants.MAKE_FALSE) MultipartFile lookAtTouchScreen,
			@RequestParam(value="cameraTVScreenSaver",required=ServerConstants.MAKE_FALSE) MultipartFile cameraTVScreenSaver,
			@RequestParam(value="waterMarkImage",required=ServerConstants.MAKE_FALSE) MultipartFile waterMarkImage,
			@ModelAttribute("AdminPictureVO") AdminPictureVO adminPictureVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) throws IOException{
		
		String rootPath = new java.io.File(request.getSession().getServletContext().getRealPath("")+"/.."+ServerConstants.IMAGES).getCanonicalPath();
		
		HttpSession session = request.getSession();
        ServletContext sc = session.getServletContext();
        String default4Images = sc.getRealPath("/");
		
        boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin !=null){
			deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
			adminPictureVO.setCreatedBy(boothAdminLogin.getUserId());
			AdminPictureVO adminPictureVOs=boothAdminService.uploadBackgroundImage(adminPictureVO,files,rootPath,thankyoufiles,lookAtTouchScreen,cameraTVScreenSaver,waterMarkImage,default4Images);
			if((ServerConstants.SUCCESS).equalsIgnoreCase(adminPictureVOs.getResult())){
				loginService.updateTour(boothAdminLogin.getUserId());
				return ServerConstants.ADD_IMAGES_OF_EVENT_CONFIG+adminPictureVOs.getEId();
			}else{
				return "eventBackgroundConfig";
			}
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
	}
	
	@RequestMapping(value="getUploadedImagesConfig")
	public String getUploadedImages(@RequestParam("eventId")Integer eid,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin !=null){
			boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
			deviceRegistration=boothAdminService.getRegisteredDevicePush(boothAdminLogin1.getUserId());
			modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
			
			List<AdminPictureVO> adminPictureVOs2=boothAdminService.getPicList(eid,boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.ADMIN_PICTURE_VOS2,adminPictureVOs2);
			modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN2,boothAdminLogin1);
			modelMap.addAttribute("eid",eid);
			Adminboothevent adminboothevent=boothAdminService.getEventDetails(eid);
			modelMap.addAttribute("eventName",adminboothevent.getEventName());
			modelMap.addAttribute("eventZoomScale",adminboothevent.getZoomScale());
			List<AdminBoothEventPicture> notConfiguredImage=boothAdminService.notConfiguredImage(eid,boothAdminLogin.getUserId());
			modelMap.addAttribute("notConfiguredImage",notConfiguredImage.size());
			List<ImageEmailFormVO> emailImagesList=boothAdminService.getPreSetBackGrounds(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.EMAIL_IMAGE_LIST,emailImagesList);
			List<ImageEmailFormVO> setThankYouByEventId=boothAdminService.getPreSetThankYouScreenBasedOnEventId(boothAdminLogin.getUserId(),eid,boothAdminLogin1.getSubId());
			modelMap.addAttribute("setThankYouByEventId",setThankYouByEventId);
			List<ImageEmailFormVO> setWaterMarkImageByEventId=boothAdminService.getPreSetWaterMarkImageBasedOnEventId(boothAdminLogin.getUserId(),eid,boothAdminLogin1.getSubId());
			modelMap.addAttribute("setWaterMarkImageByEventId",setWaterMarkImageByEventId);
			List<ImageEmailFormVO> setLookAtTouchByEventId=boothAdminService.getPreSetLookAtTouchScreenBasedOnEventId(boothAdminLogin.getUserId(),eid,boothAdminLogin1.getSubId());
			modelMap.addAttribute("setLookAtTouchByEventId",setLookAtTouchByEventId);
			List<ImageEmailFormVO> setCameraTVScreenByEventId=boothAdminService.getPreSetCameraTVScreenSaverBasedOnEventId(boothAdminLogin.getUserId(),eid,boothAdminLogin1.getSubId());
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
			
	     	return "uploadedImagesConfig";	
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
	}
	
	@RequestMapping(value="setUpBackgroundImageConfig")
	public String setUpBackgroundImage(@ModelAttribute("AdminPictureVO") AdminPictureVO adminPictureVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap){
		
		String pageids="";
		if(pageids!=""){
			pageids=request.getParameter("pictureId");
		}else{
			pageids=request.getParameter("picId");
		}
		String eventIds=request.getParameter("eId");
		String position=request.getParameter("position");
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin !=null){
			deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
			PaginationVO paginationVO=boothAdminService.getFirstLast(Integer.parseInt(eventIds),adminPictureVO.getPicId());
			modelMap.addAttribute("first", paginationVO.getFirst());
			modelMap.addAttribute("current", adminPictureVO.getPicId());
			modelMap.addAttribute("last",  paginationVO.getLast());
			modelMap.addAttribute("next", paginationVO.getNext());
			modelMap.addAttribute("previous",  paginationVO.getPrevious());
			modelMap.addAttribute("position" ,position);
			boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
		 	AdminPictureVO adminPictureVO1=boothAdminService.getImageConfigure(Integer.parseInt(pageids),boothAdminLogin1.getUserId(),boothAdminLogin1.getSubId());
			modelMap.addAttribute("adminPictureVO1",adminPictureVO1);
			modelMap.addAttribute("eid",eventIds);
			modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN2,boothAdminLogin1);
			List<AdminPictureVO> adminPictureVOs2=boothAdminService.getPicList(Integer.parseInt(eventIds),boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.ADMIN_PICTURE_VOS2,adminPictureVOs2);
			return "setUpBackgroundConfig";
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
	}
	
	@RequestMapping(value="saveCoordinatesOfImgConfig")
	public String saveCoordinatesOfImg(@RequestParam(value="files",required=ServerConstants.MAKE_FALSE) MultipartFile files,@ModelAttribute("AdminPictureVO") AdminPictureVO adminPictureVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes) throws IOException{
		String  result;
		String pid="&position=";
		String eid="&eId=";
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin !=null){
			deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
			boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
			PaginationVO paginationVO=boothAdminService.getFirstLast(adminPictureVO.getEId(),adminPictureVO.getPicId());
				adminPictureVO.setUpdatedBy(boothAdminLogin.getUserId());
				adminPictureVO.setSubId(boothAdminLogin1.getSubId());
				String rootPath = new java.io.File(request.getSession().getServletContext().getRealPath("")+"/.."+"/iAmuse_images/Admin_Picture/Image_mask").getCanonicalPath();
				result=boothAdminService.saveCoordinatesOfImg(adminPictureVO,files,rootPath);
				if((ServerConstants.SUCCESS).equals(result)){
				    		if(("Save & Exit").equalsIgnoreCase(adminPictureVO.getFinish())){
				    			redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,"Thank You !! \n Go to your device login and downloads the events");
				    			return "redirect:getUploadedImages?eventId="+adminPictureVO.getEId();
				    		}else if(("Upload Now").equalsIgnoreCase(adminPictureVO.getFinish())){
				    			return ServerConstants.SETUP_BACKGROUND_IMAGE_CONFIG+adminPictureVO.getPicId()+eid+adminPictureVO.getEId()+pid+adminPictureVO.getPosition();
				    		}else if(("Previous").equalsIgnoreCase(adminPictureVO.getFinish())){
				    			return ServerConstants.SETUP_BACKGROUND_IMAGE_CONFIG+paginationVO.getPrevious()+eid+adminPictureVO.getEId()+pid+(adminPictureVO.getPosition()-2);
				    		}else{
				    			return ServerConstants.SETUP_BACKGROUND_IMAGE_CONFIG+paginationVO.getNext()+eid+adminPictureVO.getEId()+pid+adminPictureVO.getPosition();
				    		}
				}
				return "redirect:setUpBackgroundImageConfig";
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
	}
	
	@RequestMapping(value="editUploadBackgroundImageConfig",method = RequestMethod.POST)
	public String editUploadBackgroundImage(@RequestParam("files") MultipartFile[] files,@ModelAttribute("AdminPictureVO") AdminPictureVO adminPictureVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes) throws IOException{
		String rootPath =  new java.io.File(request.getSession().getServletContext().getRealPath("")+"/.."+ServerConstants.IMAGES).getCanonicalPath();
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin !=null){
			deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
			adminPictureVO.setCreatedBy(boothAdminLogin.getUserId());
			AdminPictureVO adminPictureVOs=boothAdminService.editUploadBackgroundImage(adminPictureVO,files,rootPath);
		if((ServerConstants.SUCCESS).equalsIgnoreCase(adminPictureVOs.getResult())){
			redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,ServerConstants.UPLOAD_SUCCESS);
			return ServerConstants.GET_UPLOAD_IMAGES_CINFIG+adminPictureVOs.getEId();
		}else{
			redirectAttributes.addFlashAttribute(ServerConstants.ERROR_MESSAGE,ServerConstants.UPLOAD_FAILED);
			return ServerConstants.GET_UPLOAD_IMAGES_CINFIG+adminPictureVOs.getEId();
		}
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
	}
	
	@RequestMapping(value="setZoomProfile",method = RequestMethod.POST)
	public String setZoomProfile(@ModelAttribute("SignInVO") SignInVO signInVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes){
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin !=null){
			deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
				String result=boothAdminService.saveZoomScale(boothAdminLogin.getUserId(),signInVO);
				if((ServerConstants.SUCCESS).equals(result) && ("Next").equals(signInVO.getSave())){
						return "redirect:create-event-config.html";
				}
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
		return "advanceBoothSetUpByEventConfig";
		
	}
	
	@RequestMapping(value="updateWaterMarkLookAtTouchThankYouCameraScreenConfig",method = RequestMethod.POST)
	public String updateWaterMarkLookAtTouchThankYouCameraScreen(@RequestParam(value="files",required=ServerConstants.MAKE_FALSE) MultipartFile[] files,
			@RequestParam(value="thankyoufiles",required=ServerConstants.MAKE_FALSE) MultipartFile thankyoufiles,
			@RequestParam(value="lookAtTouchScreen",required=ServerConstants.MAKE_FALSE) MultipartFile lookAtTouchScreen,
			@RequestParam(value="cameraTVScreenSaver",required=ServerConstants.MAKE_FALSE) MultipartFile cameraTVScreenSaver,
			@RequestParam(value="waterMarkImage",required=ServerConstants.MAKE_FALSE) MultipartFile waterMarkImage,
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
			redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,ServerConstants.UPLOAD_SUCCESS);
			return ServerConstants.GET_UPLOAD_IMAGES_CINFIG+adminPictureVOs.getEId();
		}else{
			redirectAttributes.addFlashAttribute(ServerConstants.ERROR_MESSAGE,ServerConstants.UPLOAD_FAILED);
			return ServerConstants.GET_UPLOAD_IMAGES_CINFIG+adminPictureVOs.getEId();
		}
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
	}
	
	@RequestMapping(value="finishTour")
	public String finishTour(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes){
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin !=null){
			loginService.updateTour(boothAdminLogin.getUserId());
		}
		redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,"Thank You !!\n Go To Your Device Login And Download Events");
		return "redirect:getSubscribedEventList";
	}
		
	@RequestMapping(value="addImagesOfEventConfig")
	public String testing(@RequestParam(value="eventId", required=ServerConstants.MAKE_TRUE)Integer eid,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes){
			
		 	boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
			if(boothAdminLogin !=null && eid != 0){
				List<AdminPictureVO> adminPictureVOs2=boothAdminService.getPicList(eid,boothAdminLogin.getUserId());
				boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
				modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN2,boothAdminLogin1);
				modelMap.addAttribute(ServerConstants.ADMIN_PICTURE_VOS2,adminPictureVOs2);
				modelMap.addAttribute("eid",eid);
				Adminboothevent adminboothevent=boothAdminService.getEventDetails(eid);
				modelMap.addAttribute("eventName",adminboothevent.getEventName());
				Fovbyuser fovbyuser=boothAdminService.getFovTableData(boothAdminLogin1.getUserId());
				modelMap.addAttribute(ServerConstants.FOVBYUSER,fovbyuser);
				List<ImageEmailFormVO> emailImagesList=boothAdminService.getPreSetBackGrounds(boothAdminLogin.getUserId());
				modelMap.addAttribute(ServerConstants.EMAIL_IMAGE_LIST,emailImagesList);
				List<AdminBoothEventPicture> notConfiguredImage=boothAdminService.notConfiguredImage(eid,boothAdminLogin.getUserId());
				modelMap.addAttribute("notConfiguredImage",notConfiguredImage.size());
				
				deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
				modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
				return "addEventImageConfig";	
			}else{
				return ServerConstants.REDIRECT_LOGIN_PAGE;
			}
	 }
	 
	@RequestMapping(value="addUploadBackgroundImageConfig",method = RequestMethod.POST)
	public String addUploadBackgroundImage(@RequestParam(value="files",required=ServerConstants.MAKE_FALSE) MultipartFile[] files,@ModelAttribute("AdminPictureVO") AdminPictureVO adminPictureVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes) throws IOException{
			
		 String rootPath =  new java.io.File(request.getSession().getServletContext().getRealPath("")+"/.."+ServerConstants.IMAGES).getCanonicalPath();
		 boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
			if(boothAdminLogin !=null){
				deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
				modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
				adminPictureVO.setCreatedBy(boothAdminLogin.getUserId());
				AdminPictureVO adminPictureVOs=boothAdminService.editUploadBackgroundImage(adminPictureVO,files,rootPath);
			if((ServerConstants.SUCCESS).equalsIgnoreCase(adminPictureVOs.getResult())){
				redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,ServerConstants.UPLOAD_SUCCESS);
				return ServerConstants.ADD_IMAGES_OF_EVENT_CONFIG+adminPictureVOs.getEId();
			}else{
				redirectAttributes.addFlashAttribute(ServerConstants.ERROR_MESSAGE,ServerConstants.UPLOAD_FAILED);
				return ServerConstants.ADD_IMAGES_OF_EVENT_CONFIG+adminPictureVOs.getEId();
			}
			}else{
				return ServerConstants.REDIRECT_LOGIN_PAGE;
			}
		}
	 
	@RequestMapping(value="delEventPictureConfig")
	public String deletEventSinglePicture(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes,@RequestParam(value="picId",required=ServerConstants.MAKE_FALSE)String picId,@RequestParam(value="eventId",required=ServerConstants.MAKE_FALSE)Integer eventId)
		{
			String result=boothAdminService.deletEventSinglePicture(picId,eventId,request);
			if((ServerConstants.SUCCESS).equals(result)){
				deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
				ThreadPoolTaskExecutor taskExecutor=pool.taskExecutor();
				taskImageUpdate.setDetails(deviceRegistration, messageSource,rootPaths);
		    	taskExecutor.execute(taskImageUpdate);
				redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,"Delete  image successfully");
				return ServerConstants.ADD_IMAGES_OF_EVENT_CONFIG+eventId;
			}else{
				redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,"Failed for deleting selecting image");
				return ServerConstants.ADD_IMAGES_OF_EVENT_CONFIG+eventId;
			}
		}
	
}
