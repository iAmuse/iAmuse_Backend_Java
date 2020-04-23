package com.iamuse.admin.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.iamuse.admin.VO.AdminPictureVO;
import com.iamuse.admin.VO.BarChartResponseVO;
import com.iamuse.admin.VO.BoothAdminLoginResponseVO;
import com.iamuse.admin.VO.EventVO;
import com.iamuse.admin.VO.ImageEmailFormVO;
import com.iamuse.admin.VO.OptionsReports;
import com.iamuse.admin.VO.PaginationVO;
import com.iamuse.admin.entity.AdminBoothEventPicture;
import com.iamuse.admin.entity.Adminboothevent;
import com.iamuse.admin.entity.BoothAdminLogin;
import com.iamuse.admin.entity.DeviceRegistration;
import com.iamuse.admin.entity.Fovbyuser;
import com.iamuse.admin.entity.SubscriptionMaster;
import com.iamuse.admin.service.BoothAdminService;
import com.iamuse.admin.service.IamuseDashboardService;
import com.iamuse.admin.service.LoginService;
import com.iamuse.admin.service.SuperadminService;
import com.iamuse.admin.util.HighChartDataUtils;
import com.iamuse.admin.util.MailUtil;
import com.paypal.constants.ServerConstants;

@Controller
public class SuperadminController {
	
	@Autowired BoothAdminService boothAdminService;
	@Autowired private MailUtil mailUtil;	
	@Autowired SuperadminService superadminServices;
	@Autowired IamuseDashboardService iamuseDashboardService;
	@Autowired LoginService loginService;
	BoothAdminLogin boothAdminLogin;
	//1st Step of Create Event
	
	@RequestMapping(value="superAdminCreateEvent")
	public String createEventSuperAdminHtml(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
		if(boothAdminLogin!=null){
			BoothAdminLogin boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
			modelMap.addAttribute("boothAdminLogin2",boothAdminLogin1);
	     	return "superadminCreateEvent";
		}else{
			return "redirect:/";
		}	
	}
	
	//2nd Step Create Event
	@RequestMapping(value="saveSuperAdminEvent", method=RequestMethod.POST)
	public String saveCreateEventHtmlSuperAdmin(@ModelAttribute("EventVO") EventVO eventVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes)
	{	
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
		
		if(boothAdminLogin!=null){
		 boolean flag=boothAdminService.checkDefaultAlreadyExits(eventVO);
		if(flag){
		redirectAttributes.addFlashAttribute("errorMessage", "You have already created default Event");
		return "redirect:superAdminCreateEvent";	
		}else{
			BoothAdminLogin boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
			modelMap.addAttribute("boothAdminLogin2",boothAdminLogin1);
			modelMap.addAttribute("event",eventVO);
			return "eventBackgroundSA";
		}
		}
		else{
			return "redirect:/";
		}
	}
	
	//3rd Step of Create Event
	
	@RequestMapping(value="uploadBackGroundImageSA",method = RequestMethod.POST)
	/*public String uploadBackgroundImage(@RequestParam(value="files",required=false) MultipartFile[] files,
			@ModelAttribute("AdminPictureVO") AdminPictureVO adminPictureVO,
			HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,
			@RequestParam(value="thankyoufiles",required=false) MultipartFile thankyoufiles,
			@RequestParam(value="lookAtTouchScreen",required=false) MultipartFile lookAtTouchScreen,
			@RequestParam(value="cameraTVScreenSaver",required=false) MultipartFile cameraTVScreenSaver,
			@RequestParam(value="waterMarkImage",required=false) MultipartFile waterMarkImage) throws IOException{
		
		String rootPath = new java.io.File(request.getSession().getServletContext().getRealPath("")+"/.."+ServerConstants.IMAGES).getCanonicalPath();
		
		HttpSession session = request.getSession();
        ServletContext sc = session.getServletContext();
        String default4Images = sc.getRealPath("/");
        AdminPictureVO adminPictureVOs;
        
        boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin !=null){
			List<DeviceRegistration> deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
			adminPictureVO.setCreatedBy(boothAdminLogin.getUserId());
		
		adminPictureVOs=boothAdminService.uploadBackgroundImage(adminPictureVO,files,rootPath,thankyoufiles,lookAtTouchScreen,cameraTVScreenSaver,waterMarkImage,default4Images);
		if((ServerConstants.SUCCESS).equalsIgnoreCase(adminPictureVOs.getResult())){
			loginService.updateTour(boothAdminLogin.getUserId());
			return "redirect:getUploadedImagesSA?eventId="+adminPictureVOs.getEId();
		}else{
			return "eventBackground";
		}
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}*/
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
        AdminPictureVO adminPictureVOs = null;
        
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin !=null){
			adminPictureVO.setCreatedBy(boothAdminLogin.getUserId());
		adminPictureVOs=boothAdminService.uploadBackgroundImage(adminPictureVO,files,rootPath,thankyoufiles,lookAtTouchScreen,cameraTVScreenSaver,waterMarkImage,default4Images);
		}
		if((ServerConstants.SUCCESS).equalsIgnoreCase(adminPictureVOs.getResult())){
			loginService.updateTour(boothAdminLogin.getUserId());
			return "redirect:getUploadedImagesSA?eventId="+adminPictureVOs.getEId();
		}else{
			return "eventBackgroundSA";
		}
		
	}
	
	@RequestMapping(value="addUploadBackgroundImageSA",method = RequestMethod.POST)
	public String addUploadBackgroundImageSA(@RequestParam(value="files",required=ServerConstants.MAKE_FALSE) MultipartFile[] files,@ModelAttribute("AdminPictureVO") AdminPictureVO adminPictureVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes) throws IOException{
		 	boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		 	String rootPath= new java.io.File(request.getSession().getServletContext().getRealPath("")+"/..").getCanonicalPath();
			if(boothAdminLogin !=null && rootPath !=null){
			adminPictureVO.setCreatedBy(boothAdminLogin.getUserId());
			AdminPictureVO adminPictureVOs=boothAdminService.editUploadBackgroundImage(adminPictureVO,files,rootPath+ServerConstants.IMAGES);
			if(adminPictureVOs !=null){
			if(adminPictureVOs.getResult().equalsIgnoreCase(ServerConstants.SUCCESS)){
				redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,ServerConstants.UPLOAD_SUCCESS);
				return "redirect:getUploadedImagesSA?eventId="+adminPictureVOs.getEId();
			}else{
				redirectAttributes.addFlashAttribute(ServerConstants.ERROR_MESSAGE, ServerConstants.UPLOAD_FAILED);
				return "redirect:getUploadedImagesSA?eventId="+adminPictureVOs.getEId();
			}}
			}else{
				return ServerConstants.REDIRECT_LOGIN_PAGE;
			}
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
	
	//This is for testing purpose
	@RequestMapping(value="test",method = RequestMethod.GET)
	public String m1() {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		return"test";
	}
	
	@RequestMapping(value="getUploadedImagesSA")
	public String getUploadedImages(@RequestParam("eventId")Integer eid,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)
	{
			boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
			if(boothAdminLogin !=null){
			List<AdminPictureVO> adminPictureVOs2=boothAdminService.getPicList(eid,boothAdminLogin.getUserId());
			BoothAdminLogin boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN2,boothAdminLogin1);
			
			modelMap.addAttribute("adminPictureVOs2",adminPictureVOs2);
			
			modelMap.addAttribute("eid",eid);
			
			List<AdminBoothEventPicture> notConfiguredImage=boothAdminService.notConfiguredImage(eid,boothAdminLogin.getUserId());
			modelMap.addAttribute("notConfiguredImage",notConfiguredImage.size());
			
			Adminboothevent adminboothevent=boothAdminService.getEventDetails(eid);
			modelMap.addAttribute("eventName",adminboothevent.getEventName());
			modelMap.addAttribute("eventZoomScale",adminboothevent.getZoomScale());
			
			List<ImageEmailFormVO> emailImagesList=boothAdminService.getPreSetBackGrounds(boothAdminLogin.getUserId());
			modelMap.addAttribute("emailImagesList",emailImagesList);
			
			
			List<ImageEmailFormVO> setThankYouByEventId=boothAdminService.getPreSetThankYouScreenBasedOnEventId(boothAdminLogin.getUserId(),eid,4);
			modelMap.addAttribute("setThankYouByEventId",setThankYouByEventId);
			
			List<ImageEmailFormVO> setWaterMarkImageByEventId=boothAdminService.getPreSetWaterMarkImageBasedOnEventId(boothAdminLogin.getUserId(),eid,4);
			modelMap.addAttribute("setWaterMarkImageByEventId",setWaterMarkImageByEventId);
			
			List<ImageEmailFormVO> setLookAtTouchByEventId=boothAdminService.getPreSetLookAtTouchScreenBasedOnEventId(boothAdminLogin.getUserId(),eid,4);
			modelMap.addAttribute("setLookAtTouchByEventId",setLookAtTouchByEventId);
			
			List<ImageEmailFormVO> setCameraTVScreenByEventId=boothAdminService.getPreSetCameraTVScreenSaverBasedOnEventId(boothAdminLogin.getUserId(),eid,4);
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
			
			List<DeviceRegistration> deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
			
			
		     	return "addEventIamgesSA";	
			}else{
				return ServerConstants.REDIRECT_LOGIN_PAGE;
			}
		}
		
	
	@RequestMapping(value="uploadedImagesSA")
	public String uploadedImages(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
		if(boothAdminLogin!=null){
		Integer eid=(Integer)request.getSession().getAttribute("eid");
		List<AdminPictureVO> adminPictureVOs2=boothAdminService.getPicList(eid,boothAdminLogin.getUserId());
		
		modelMap.addAttribute("adminPictureVOs2",adminPictureVOs2);
		
		BoothAdminLogin boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
		modelMap.addAttribute("boothAdminLogin2",boothAdminLogin1);
	     	return "uploadedImagesSA";
		}else{
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="setUpBackgroundImageSA")
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
			BoothAdminLogin boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
				PaginationVO paginationVO=boothAdminService.getFirstLast(Integer.parseInt(eventIds),adminPictureVO.getPicId());
				modelMap.addAttribute("first", paginationVO.getFirst());
				modelMap.addAttribute("current", adminPictureVO.getPicId());
				modelMap.addAttribute("last",  paginationVO.getLast());
				modelMap.addAttribute("next", paginationVO.getNext());
				modelMap.addAttribute("previous",  paginationVO.getPrevious());
				modelMap.addAttribute("position" ,position);
				AdminPictureVO adminPictureVO1=boothAdminService.getImageConfigure(Integer.parseInt(pageids),boothAdminLogin1.getUserId(),boothAdminLogin1.getSubId());
				modelMap.addAttribute("adminPictureVO1",adminPictureVO1);
				modelMap.addAttribute("eid",Integer.parseInt(eventIds));
				List<AdminPictureVO> adminPictureVOs2=boothAdminService.getPicList(Integer.parseInt(eventIds),boothAdminLogin.getUserId());
				modelMap.addAttribute("adminPictureVOs2",adminPictureVOs2);
				modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN2,boothAdminLogin1);
				List<DeviceRegistration> deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
				modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
				return "setUpBackgroundSA";
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
	}
	
	@RequestMapping(value="saveCoordinatesOfImgSA")
	public String saveCoordinatesOfImg(@RequestParam(value="files",required=ServerConstants.MAKE_FALSE) MultipartFile files,@ModelAttribute("AdminPictureVO") AdminPictureVO adminPictureVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes) throws IOException{
		String  result;
		String rootPath;
		String pid="&position=";
		String eid="&eId=";
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin !=null){
			BoothAdminLogin boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
				PaginationVO paginationVO=boothAdminService.getFirstLast(adminPictureVO.getEId(),adminPictureVO.getPicId());
				adminPictureVO.setUpdatedBy(boothAdminLogin.getUserId());
				adminPictureVO.setSubId(boothAdminLogin1.getSubId());
				List<DeviceRegistration> deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
				modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
				rootPath = new java.io.File(request.getSession().getServletContext().getRealPath("")+"/.."+"/iAmuse_images/Admin_Picture/Image_mask").getCanonicalPath();
				result=boothAdminService.saveCoordinatesOfImg(adminPictureVO,files,rootPath);
				if((ServerConstants.SUCCESS).equals(result)){
							Integer posupdate=adminPictureVO.getPosition();
				    		if(("Save & Exit").equalsIgnoreCase(adminPictureVO.getFinish())){
				    			redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,"Thank You !! \n Go to your device login and downloads the events");
				    			return "redirect:getUploadedImagesSA?eventId="+adminPictureVO.getEId();
				    		}else if(("Upload Now").equalsIgnoreCase(adminPictureVO.getFinish())){
				    			return "redirect:setUpBackgroundImageSA?picId="+adminPictureVO.getPicId()+eid+adminPictureVO.getEId()+pid+(posupdate-1);
				    		}else if(("Previous").equalsIgnoreCase(adminPictureVO.getFinish())){
				    			return "redirect:setUpBackgroundImageSA?picId="+paginationVO.getPrevious()+eid+adminPictureVO.getEId()+pid+(adminPictureVO.getPosition()-2);
				    		}else{
				    			return "redirect:setUpBackgroundImageSA?picId="+paginationVO.getNext()+eid+adminPictureVO.getEId()+pid+adminPictureVO.getPosition();
				    		}
				}
				return ServerConstants.SETUP_BACKGROUND_IMAGE+paginationVO.getNext()+eid+adminPictureVO.getEId()+pid+adminPictureVO.getPosition();
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
	}
	
	
	
	//Super Admin Concept Start 
	@RequestMapping(value="getBoothAdminList")
	public String getBoothAdminList(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
		if(boothAdminLogin!=null){
		String pageids=request.getParameter("pageid");
		String totals=request.getParameter("total");
		int pageid=0;
		int total=0;
		if(pageids==null  && totals==null){
			pageid=1;
			total=10;
		}
		if(total==0){
			total=Integer.parseInt(totals);
			pageid=Integer.parseInt(pageids);
		}
		
		List<BoothAdminLoginResponseVO> boothAdminLoginList=superadminServices.getBoothAdminLoginListSuperAdmin();
		
		List<BoothAdminLoginResponseVO> boothAdminLoginListPagination=superadminServices.getBoothAdminLoginListSuperAdminWithPagination(pageid,total);
		BoothAdminLogin boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
		modelMap.addAttribute("boothAdminLogin2",boothAdminLogin1);
		int rCount =0;
		if(boothAdminLoginList.size()>0){
			rCount =	boothAdminLoginList.size();
		}
		int pageCount;
		if(rCount%total==0)
		{
			 pageCount=rCount/total;
		}
		else
		{
			 pageCount=rCount/total+1;
		}
		
		if(boothAdminLoginListPagination.size()>0){
			System.out.println("**************************************************8");
		modelMap.addAttribute("boothAdminLoginList",boothAdminLoginListPagination);
		modelMap.addAttribute("subList", superadminServices.getSubscriptionListSuperAdminPage());
		modelMap.addAttribute("currentDate",new Date());
		modelMap.addAttribute("pageid",pageid);
		modelMap.addAttribute("pageCount",pageCount);
		modelMap.addAttribute("total",total);
		}else{
			boothAdminLoginList=new ArrayList<>();
			boothAdminLoginList.add(new BoothAdminLoginResponseVO());
			modelMap.addAttribute("boothAdminLoginList",boothAdminLoginList);
		}
		return "BoothAdminList";
		}else{
	    	return "redirect:../";
		}
	}
	
	@RequestMapping(value="updateAdminSubscription")
	public String updateAdminSubscription(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes) throws IOException
	{	
		String result="redirect:/";
		String userId = request.getParameter("dataString");
		String sub_Id = request.getParameter("selectedPlan");
		System.out.println("userId**************"+userId);
		System.out.println("sub_Id**************"+sub_Id);
		boothAdminLogin.setSubId(10);
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
		if(boothAdminLogin!=null){
		if(boothAdminLogin.getUserRole().equalsIgnoreCase("superadmin")){
		if(superadminServices.updateAdminSubscription(boothAdminLogin,Integer.parseInt(userId))){
		//	redirectAttributes.addFlashAttribute("successMessage","update subscription succesfully");
			System.out.println("<--------------------------------------------------------------------------->");
			response.sendRedirect("getBoothAdminList");
		// result= "redirect:getBoothAdminList";
		}else{
		//	redirectAttributes.addFlashAttribute("errorMessage","fail for update the subscription");
			response.sendRedirect("getBoothAdminList");
			return "redirect:getBoothAdminList";
		}
		}else{
			response.sendRedirect("getBoothAdminList");
			return "redirect:/";
		}
	     	return result;	
		}else{
			response.sendRedirect("getBoothAdminList");
			return "redirect:/";
		}
	}
	
	
	@RequestMapping(value="getSuperAdminSubscription")
	public String getSuperAdminSubscription(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
		if(boothAdminLogin!=null){
		try {
			
			String pageids=request.getParameter("pageid");
			String totals=request.getParameter("total");
			int pageid=0;
			int total=0;
			if(pageids==null  && totals==null){
				pageid=1;
				total=10;
			}
			if(total==0){
				total=Integer.parseInt(totals);
				pageid=Integer.parseInt(pageids);
			}
			List<SubscriptionMaster> subscriptionPlan=boothAdminService.getSubscriptionListSA();
			
			List<SubscriptionMaster> subscriptionPlanList=boothAdminService.getSubscriptionListSAWithPagination(pageid,total);
			
			int rCount =0;
			if(subscriptionPlan.size()>0){
				rCount =	subscriptionPlan.size();
			}
			int pageCount;
			if(rCount%total==0)
			{
				 pageCount=rCount/total;
			}
			else
			{
				 pageCount=rCount/total+1;
			}
			
			modelMap.addAttribute("subscriptionPlan",subscriptionPlanList);
			modelMap.addAttribute("pageid",pageid);
			modelMap.addAttribute("pageCount",pageCount);
			modelMap.addAttribute("total",total);
			
	} catch (Exception e) {
				e.printStackTrace();
	}
		BoothAdminLogin boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
		modelMap.addAttribute("boothAdminLogin2",boothAdminLogin1);
		return "superadminSubscriptionList";
		}else{
			return "redirect:";
		}
	}
	
	@RequestMapping(value="uploadBackgroundSA")
	public String uploadBackgroundSuperAdmin(@ModelAttribute("EventVO")EventVO eventVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)
	{
		
				boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
		if(boothAdminLogin!=null){
				modelMap.addAttribute("event" , eventVO);
				
				BoothAdminLogin boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
				modelMap.addAttribute("boothAdminLogin2",boothAdminLogin1);
			    modelMap.addAttribute("boothAdminLogin",boothAdminLogin1);
	     	
		return "eventBackgroundSA";
		
	}else{
		return "redirect:/";
	}
	}
	
	
	
	
	
	@RequestMapping(value="create-superadminSubscription.html")
	public String createSuperAdminSubscriptionHtml(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)
	{
		
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
		if(boothAdminLogin!=null){
	     	return "createSuperadminSubscription";
		}else{
	     	return "redirect:";
	}
	}
	
	@RequestMapping(value="saveNewSubscriptions.html")
	public String saveNewSubscriptions(@ModelAttribute("subscriptionMaster") SubscriptionMaster subscriptionMaster,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes)
	{	
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
		String result="redirect:/"; 
		if(boothAdminLogin!=null){
		if(boothAdminLogin.getUserRole().equalsIgnoreCase("superadmin")){
		if(superadminServices.saveSubscriptionMaster(subscriptionMaster,boothAdminLogin.getUserId())){
			redirectAttributes.addFlashAttribute("successMessage", "Susbcriptions Created Successfully ");
		 result= "redirect:getSuperAdminSubscription";
		}else{
			redirectAttributes.addFlashAttribute("errorMessage","fail for creating the Susbcriptions");
			return "redirect:getSuperAdminSubscription";
		}
		}else{
			
			return "redirect:/";
		}
	     	return result;
		}else{
			return "redirect:/";
		}
	}
	
	
	@RequestMapping(value="editSubscription", method=RequestMethod.GET)
	public String editSubscriptionValues(@RequestParam(value="id", required=false)Integer id,HttpServletRequest request,HttpServletResponse response,Model model)
	{

		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
		if(boothAdminLogin!=null){
		if(boothAdminLogin.getUserRole().equalsIgnoreCase("superadmin")){
			SubscriptionMaster masterValue=boothAdminService.getSubscriptionListByIdForActive(id);
			if(masterValue==null){
				masterValue=boothAdminService.getSubscriptionListByIdForDeactive(id);
			}
		if(masterValue!=null){
			model.addAttribute("masterValue",masterValue);
			return "editSuperadminSubscription";
		}else{
			return "redirect:getSuperAdminSubscription";
		}
		}else{
			return "redirect:/";
		}	
		}else{
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="updateSubscriptions.html")
	public String updateSubscriptions(@ModelAttribute("subscriptionMaster") SubscriptionMaster subscriptionMaster,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes)
	{	
		String result="redirect:/"; 
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
		if(boothAdminLogin!=null){
		if(boothAdminLogin.getUserRole().equalsIgnoreCase("superadmin")){
		if(superadminServices.updateSubscriptionMaster(subscriptionMaster,boothAdminLogin.getUserId())){
			redirectAttributes.addFlashAttribute("successMessage","update subscription succesfully");
		 result= "redirect:getSuperAdminSubscription";
		}else{
			redirectAttributes.addFlashAttribute("errorMessage","fail for update the subscription");
			return "redirect:getSuperAdminSubscription";
		}
		}else{
			return "redirect:/";
		}
	     	return result;	
		}else{
			return "redirect:/";
		}
	}
	
	
	@RequestMapping(value="deleteSubscription", method=RequestMethod.GET)
	public String deleteSubscriptionValues(@RequestParam(value="id", required=false)Integer id,HttpServletRequest request,HttpServletResponse response,Model model,RedirectAttributes redirectAttributes)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
		if(boothAdminLogin!=null){
		if(boothAdminLogin.getUserRole().equalsIgnoreCase("superadmin")){
			SubscriptionMaster masterValue=boothAdminService.getSubscriptionListByIdForActive(id);
			if(masterValue==null){
				masterValue=boothAdminService.getSubscriptionListByIdForDeactive(id);
			}
		if(superadminServices.deleteSubscriptionMasterById(masterValue,boothAdminLogin.getUserId())){
			redirectAttributes.addFlashAttribute("successMessage","Deleted Subscription Succesfully");
			return "redirect:getSuperAdminSubscription";
		}else{
			redirectAttributes.addFlashAttribute("errorMessage","fail for deleting the subscription");
			return "redirect:getSuperAdminSubscription";
		}
		}else{
			return "redirect:/";
		}	
		}else{
			return "redirect:/";
		}
	}
	
	
	@RequestMapping(value="viewSubscription", method=RequestMethod.GET)
	public String viewSubscriptionValues(@RequestParam(value="id", required=false)Integer id,HttpServletRequest request,HttpServletResponse response,Model model)
	{

		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
		if(boothAdminLogin!=null){
		if(boothAdminLogin.getUserRole().equalsIgnoreCase("superadmin")){
			SubscriptionMaster masterValue=boothAdminService.getSubscriptionListById(id);
		if(masterValue!=null){
			model.addAttribute("masterValue",masterValue);
			return "viewSuperadminSubscription";
		}else{
			return "redirect:getSuperAdminSubscription";
		}
		}else{
			return "redirect:/";
		}	
		}else{
			return "redirect:/";
		}
	}
	
	
	@RequestMapping(value="activeSubscription")
	public String activeSubscriptions(@RequestParam(value="id", required=false)Integer id,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes)
	{	
		String result="redirect:/"; 
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
		
		if(boothAdminLogin!=null){
		if(boothAdminLogin.getUserRole().equalsIgnoreCase("superadmin")){
			SubscriptionMaster subscriptionMaster=boothAdminService.getSubscriptionListByIdForDeactive(id);
			if(subscriptionMaster==null){
				subscriptionMaster=boothAdminService.getSubscriptionListByIdForActive(id);	
			}
		if(superadminServices.activeSubscriptionMaster(subscriptionMaster,boothAdminLogin.getUserId())){
			redirectAttributes.addFlashAttribute("successMessage","Active Subscription Succesfully");
		 result= "redirect:getSuperAdminSubscription";
		}else{
			redirectAttributes.addFlashAttribute("errorMessage","fail for active the subscription");
			return "redirect:getSuperAdminSubscription";
		}
		}else{
			return "redirect:/";
		}
	     	return result;	
		}else{
			return "redirect:/";
		}
	}
	
	
	@RequestMapping(value="deactiveSubscription")
	public String deactiveSubscriptions(@RequestParam(value="id", required=false)Integer id,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes)
	{	
		String result="redirect:/"; 
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
	if(boothAdminLogin!=null){
		if(boothAdminLogin.getUserRole().equalsIgnoreCase("superadmin")){
			SubscriptionMaster subscriptionMaster=boothAdminService.getSubscriptionListByIdForActive(id);
			if(subscriptionMaster==null){
				subscriptionMaster=boothAdminService.getSubscriptionListByIdForDeactive(id);
			}
		if(superadminServices.deactiveSubscriptionMaster(subscriptionMaster,boothAdminLogin.getUserId())){
			redirectAttributes.addFlashAttribute("successMessage","Deactive subscription succesfully");
		 result= "redirect:getSuperAdminSubscription";
		}else{
			redirectAttributes.addFlashAttribute("errorMessage","fail for deactive subscription");
			return "redirect:getSuperAdminSubscription";
		}
		}else{
			return "redirect:/";
		}
	     	return result;	
	}else{
		return "redirect:/";
	}
	}
	
	
	
	@RequestMapping(value="viewSuperAdminEvent", method=RequestMethod.GET)
	public String viewSuperAdminEvent(@RequestParam(value="id", required=false)Integer id,HttpServletRequest request,HttpServletResponse response,Model model)
	{
		
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
		if(boothAdminLogin!=null){
		
		if(boothAdminLogin.getUserRole().equalsIgnoreCase("superadmin")){
			EventVO eventList=superadminServices.getEventValueByEventId(id);
			
		if(eventList!=null){
			System.out.println(eventList.getEventStart());
			model.addAttribute("eventList",eventList);
			return "viewsuperadminEvent";
		}else{
			return "redirect:getEventList";
		}
		}else{
			return "redirect:/";
		}	
		}else{
			return "redirect:/";
		}
		}
	
	
	@RequestMapping(value="editSuperAdminEvent", method=RequestMethod.GET)
	public String editSuperAdminEvent(@RequestParam(value="id", required=false)Integer id,HttpServletRequest request,HttpServletResponse response,Model model)
	{
		
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
		
		if(boothAdminLogin!=null){
		if(boothAdminLogin.getUserRole().equalsIgnoreCase("superadmin")){
			EventVO eventList=superadminServices.getEventValueByEventId(id);
			
		if(eventList!=null){
			model.addAttribute("eventList",eventList);
			return "editsuperadminEvent";
		}else{
			return "redirect:getEventList";
		}
		}else{
			return "redirect:/";
		}	
		}else{
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="updatesuperadminEvent")
	public String updateSubscriptions(@ModelAttribute("eventVO") EventVO eventVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes)
	{	
		String result="redirect:/"; 
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
		if(boothAdminLogin!=null){
		
		if(boothAdminLogin.getUserRole().equalsIgnoreCase("superadmin")){
		if(superadminServices.updateAdminBoothEvent(eventVO,boothAdminLogin.getUserId())){
			 result= "redirect:getEventList";
		}else{
			return "redirect:getEventList";
		}
		}else{
			return "redirect:/";
		}
	     	return result;	
		}else{
			return "redirect:/";
		}
	}
	@RequestMapping(value="deleteSuperAdminEvent", method=RequestMethod.GET)
	public String deleteSuperAdminEvent(@RequestParam(value="id", required=false)Integer id,HttpServletRequest request,HttpServletResponse response,Model model,RedirectAttributes redirectAttributes)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
		if(boothAdminLogin!=null){
		if(boothAdminLogin.getUserRole().equalsIgnoreCase("superadmin")){
		if(superadminServices.deleteAdminBoothEventByEventId(id)){
			redirectAttributes.addFlashAttribute("successMessage","Deleted Event Succesfully");
			return "redirect:getEventList";
		}else{
			redirectAttributes.addFlashAttribute("errorMessage","fail for deleting the Event");
			return "redirect:getEventList";
		}
		}else{
			return "redirect:/";
		}	
		}else{
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="getReports")
	public String getReports(HttpServletRequest request,HttpServletResponse response, Model model,@RequestParam (value="Value", required=false)String Value,ModelMap modelMap)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
		if(boothAdminLogin!=null){
		
		List<String> yearList = new ArrayList<>();
		for (int i = 2000; i <= 2500; i++) {
			yearList.add(String.valueOf(i));
		}
		BoothAdminLogin boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
		modelMap.addAttribute("boothAdminLogin2",boothAdminLogin1);
		SimpleDateFormat myFormat=new SimpleDateFormat("yyyy");
		model.addAttribute("year", myFormat.format(new Date()));
		model.addAttribute("yearList", yearList);
		if(Value!=null && Value.equalsIgnoreCase("monthly")){
		return "pieChart";	
		}
	     	return "barChart";	
	     	
	}else{
		return "redirect:";
	}
	}
	
	@RequestMapping(value="barChart",method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<BarChartResponseVO> getBarChartReports(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,@RequestParam(value="dateText",required=false)String dateText,@RequestParam(value="Value",required=false)String Value,@RequestParam(value="selectedMonth", required=false)String selectedMonth)
	{
		
		ArrayList<BarChartResponseVO> list = new ArrayList<>();
		List<BoothAdminLoginResponseVO> adminLoginList = superadminServices.getBoothAdminLoginList();
		if(dateText!=null){
			if(Value!=null && Value.equalsIgnoreCase("monthly")){

				int year =Integer.parseInt(dateText);
				int month =Integer.parseInt(selectedMonth);
				String endDate=HighChartDataUtils.dateFormat(HighChartDataUtils.makingEndDate(month,  year));
				
				String createdStartDate=HighChartDataUtils.dateFormat(HighChartDataUtils.makingStartDate(month,  year));
				
				List<SubscriptionMaster> subsrciptionList=boothAdminService.getSubscriptionList();
				
				for (SubscriptionMaster subscriptionMaster : subsrciptionList) {
					
				
				Integer count=superadminServices.getSubscriptionCountForBarChartDefaultSubscription(createdStartDate,endDate,subscriptionMaster.getSubId());
				
				
				int percentage=(count*100)/adminLoginList.size();
				
				BarChartResponseVO monthlyBasedResponse=new BarChartResponseVO();
				monthlyBasedResponse.setName(subscriptionMaster.getSubName());
				monthlyBasedResponse.setY(percentage);
				list.add(monthlyBasedResponse);
				monthlyBasedResponse=null;
				}
				
			}else{
				
				int year =Integer.parseInt(dateText);
				
				
				int month =1;
				
				for (int i = month; i <= 12; i++) {
				
				String endDate=HighChartDataUtils.dateFormat(HighChartDataUtils.makingEndDate(i,  year));
				
				String createdStartDate=HighChartDataUtils.dateFormat(HighChartDataUtils.makingStartDate(i,  year));
				
				Integer count=superadminServices.getSubscriptionCountForBarChart(createdStartDate,endDate);
				
				BarChartResponseVO monthlyBasedResponse=new BarChartResponseVO();
				monthlyBasedResponse.setName(HighChartDataUtils.dispalyMonthName(i));
				monthlyBasedResponse.setY(count);
				list.add(monthlyBasedResponse);
				monthlyBasedResponse=null;
				
				}
			}
		}else{
			if(Value!=null && Value.equalsIgnoreCase("monthly")){
				
				list=defaultPieChartDataFirstTimeLoadTheJSPPage(adminLoginList);
			}else{
			    list=defaultBarChartDataFirstTimeLoadTheJSPPage(adminLoginList);
			}
		}
		
	     	return list;
	}
	
	private ArrayList<BarChartResponseVO> defaultPieChartDataFirstTimeLoadTheJSPPage(List<BoothAdminLoginResponseVO> adminLoginList) {
		ArrayList<BarChartResponseVO> list = new ArrayList<>();
		String janStartDate="2016-12-01";
		String janEndDate="2016-12-31";
		
		List<SubscriptionMaster> subsrciptionList=boothAdminService.getSubscriptionList();
		
		for (SubscriptionMaster subscriptionMaster : subsrciptionList) {
			
		
		Integer janDefaultCount=superadminServices.getSubscriptionCountForBarChartDefaultSubscription(janStartDate,janEndDate,subscriptionMaster.getSubId());
		
		int janPercentage=(janDefaultCount*100)/adminLoginList.size();
		
		BarChartResponseVO pieChartDefaultResponse=new BarChartResponseVO();
		pieChartDefaultResponse.setName(subscriptionMaster.getSubName());
		if(janDefaultCount!=0 && janDefaultCount!=null){
			pieChartDefaultResponse.setY(janPercentage);
			}else{
				pieChartDefaultResponse.setY(janPercentage);
			}
		
		
		list.add(pieChartDefaultResponse);
		pieChartDefaultResponse=null;
		}
		return list;
	}

	
private ArrayList<BarChartResponseVO> defaultBarChartDataFirstTimeLoadTheJSPPage(List<BoothAdminLoginResponseVO> adminLoginList) {
		
		ArrayList<BarChartResponseVO> list = new ArrayList<>();
		String janStartDate="2016-01-01";
		String janEndDate="2016-01-31";
		
		Integer janCount=superadminServices.getSubscriptionCountForBarChart(janStartDate,janEndDate);
		
		BarChartResponseVO JanuareyResponse=new BarChartResponseVO();
		JanuareyResponse.setName("Jan");
		JanuareyResponse.setY(janCount);
		
		String febStartDate="2016-02-01";
		String febEndDate="2016-02-29";
		
		Integer febCount=superadminServices.getSubscriptionCountForBarChart(febStartDate,febEndDate);
		
		BarChartResponseVO febResponse=new BarChartResponseVO();
		febResponse.setName("Feb");
		febResponse.setY(febCount);
		
		String marchStartDate="2016-03-01";
		String marchEndDate="2016-03-31";
		
		Integer marchCount=superadminServices.getSubscriptionCountForBarChart(marchStartDate,marchEndDate);
		
		BarChartResponseVO marchResponse=new BarChartResponseVO();
		marchResponse.setName("Mar");
		marchResponse.setY(marchCount);
		
		String aprilStartDate="2016-04-01";
		String aprilEndDate="2016-04-30";
		
		Integer aprilCount=superadminServices.getSubscriptionCountForBarChart(aprilStartDate,aprilEndDate);
		
		BarChartResponseVO aprilResponse=new BarChartResponseVO();
		aprilResponse.setName("Apr");
		aprilResponse.setY(aprilCount);
		
		String mayStartDate="2016-05-01";
		String mayEndDate="2016-05-31";
		
		Integer mayCount=superadminServices.getSubscriptionCountForBarChart(mayStartDate,mayEndDate);
		
		BarChartResponseVO mayResponse=new BarChartResponseVO();
		mayResponse.setName("May");
		mayResponse.setY(mayCount);
		
		String juneStartDate="2016-06-01";
		String juneEndDate="2016-06-30";
		
		Integer juneCount=superadminServices.getSubscriptionCountForBarChart(juneStartDate,juneEndDate);
	
		BarChartResponseVO juneResponse=new BarChartResponseVO();
		juneResponse.setName("Jun");
		juneResponse.setY(juneCount);
		
		String julyStartDate="2016-07-01";
		String julyEndDate="2016-07-31";
		
		Integer julyCount=superadminServices.getSubscriptionCountForBarChart(julyStartDate,julyEndDate);
		
		BarChartResponseVO julyResponse=new BarChartResponseVO();
		julyResponse.setName("July");
		julyResponse.setY(julyCount);
		
		String augustStartDate="2016-08-01";
		String augustEndDate="2016-08-31";
		
		Integer augustCount=superadminServices.getSubscriptionCountForBarChart(augustStartDate,augustEndDate);
		
		BarChartResponseVO augustResponse=new BarChartResponseVO();
		augustResponse.setName("Aug");
		augustResponse.setY(augustCount);
		
		String septemberStartDate="2016-09-01";
		String septemberEndDate="2016-09-30";
		
		Integer septemberCount=superadminServices.getSubscriptionCountForBarChart(septemberStartDate,septemberEndDate);
		
		BarChartResponseVO septemberResponse=new BarChartResponseVO();
		septemberResponse.setName("Sep");
		septemberResponse.setY(septemberCount);
		
		String octoberStartDate="2016-10-01";
		String octoberEndDate="2016-10-31";
		
		Integer octoberCount=superadminServices.getSubscriptionCountForBarChart(octoberStartDate,octoberEndDate);
		
		BarChartResponseVO octoberResponse=new BarChartResponseVO();
		octoberResponse.setName("Oct");
		octoberResponse.setY(octoberCount);
		
		String novemberStartDate="2016-11-01";
		String novemberEndDate="2016-11-30";
		
		Integer novemberCount=superadminServices.getSubscriptionCountForBarChart(novemberStartDate,novemberEndDate);
		
		BarChartResponseVO novemberResponse=new BarChartResponseVO();
		novemberResponse.setName("Nov");
		novemberResponse.setY(novemberCount);
		
		String decemberStartDate="2016-12-01";
		String decemberEndDate="2016-12-31";
		
		Integer decemberCount=superadminServices.getSubscriptionCountForBarChart(decemberStartDate,decemberEndDate);
		
		BarChartResponseVO decemberResponse=new BarChartResponseVO();
		decemberResponse.setName("Dec");
		decemberResponse.setY(decemberCount);
			
		list.add(JanuareyResponse);
		list.add(febResponse);
		list.add(marchResponse);
		list.add(aprilResponse);
		list.add(mayResponse);
		list.add(juneResponse);
		list.add(julyResponse);
		list.add(augustResponse);
		list.add(septemberResponse);
		list.add(octoberResponse);
		list.add(novemberResponse);
		list.add(decemberResponse);
		return list;
	}
	public static BarChartResponseVO fromJson(String json) throws JsonParseException,JsonMappingException, IOException{
		BarChartResponseVO responseVO = new ObjectMapper().readValue(json, BarChartResponseVO.class);
		System.out.println("Java Object created from JSON String ");
		System.out.println("JSON String : " + json);
		System.out.println("Java Object : " + responseVO);
		return responseVO;
		}
	
	@RequestMapping(value="upgradeSubscriptionMail",method=RequestMethod.GET)
	public String upgradeSubscriptionMail(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="emailId",required=false)String emailId,RedirectAttributes redirectAttributes){

		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
		if(boothAdminLogin!=null){
		try{
			
			String testText="<html><body>"+
					"<table id=\"m_-5368927744985068358backgroundTable\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"+
					"<tbody><tr><td><table id=\"m_-5368927744985068358innerTable\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"+
					"<tbody><tr><td class=\"m_-5368927744985068358payloadCell\" valign=\"top\"><table style=\"border:1px solid rgb(207,207,207);border-radius:8px;background:rgb(255,255,255)\" border=\"0\" cellspacing=\"0\" width=\"100%\">"+
					"<tbody><tr><td style=\"color:rgb(85,85,85);font-size:14px;font-family:'helvetica neue',arial,serif;padding:30px 10px\" align=\"center\">"+
					"<p style=\"color:rgb(68,68,68);text-align:center;margin:0px;padding:0px\"><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT100_com_zimbra_url\"><a href=\"http://www.iamuse.com\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://www.iamuse.com&amp;source=gmail&amp;ust=1493380684676000&amp;usg=AFQjCNExSsY9fpbaIXKUYmJaDURNeFlELw\"><img alt=\"iAmuse\" longdesc=\"https://ci3.googleusercontent.com/proxy/tSnkDkFiofgBYd5c5rsqAFQE_sTYbRIdlGOTJCekl9GkbR2Yz4vb0tMUMQ=s0-d-e1-ft#http://www.iamuse.com\" height=\"100\" width=\"250\" src=\"http://star-k.eastus.cloudapp.azure.com:8080/iamuse/resources/images/images/iamuse-email-logo.png\" class=\"CToWUd\"></a></span></p>"+
				    "<h3 style=\"color:rgb(68,68,68);text-align:center;margin:0px;padding:0px\">Thank you for using the Free Trial of iAmuse, here is a sample email that is sent to your Guests.</h3><p>Buy iAmuse for a <u><a href=\"https://www.iamuse.com/pricing\" rel=\"noopener noreferrer\" style=\"text-decoration:underline;\" target=\"_blank\"><b>Single Event</b></a></u> or <u><a href=\"https://www.iamuse.com/pricing\" rel=\"noopener noreferrer\" style=\"text-decoration:underline;\" target=\"_blank\"><b>Unlimited Professional Use</b></a></u> so you can edit this text and unlock other exciting features!</p>"+
				    "<p style=\"color:rgb(68,68,68);text-align:center;margin:0px;padding:0px;background-color:#e2dfcc;\">Do you want this photo booth experience at your own event?<br>iAmuse DIY photo booth - <a alt=\"Download now!\" href=\"http://download.iAmuse.com\" rel=\"noopener noreferrer\" style=\"text-decoration:underline;\" target=\"_blank\" title=\"Download now!\">Download now!</a>&nbsp;| <a alt=\"Get a Booth\" href=\"https://www.iamuse.com/buy-green-screen-photo-booth\" rel=\"noopener noreferrer\" style=\"text-decoration:underline;\" target=\"_blank\" title=\"Get a Booth\"><font color=\"#000000\" style=\"color:#000000;\">Get a Booth</font></a></p>"+
				    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"50%\">"+
	                "<tbody><tr><td align=\"center\"> <p><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT101_com_zimbra_url\"><a href=\"https://www.facebook.com/iamusebooth/\" style=\"text-decoration:underline;display: block;font-size: 1px;\" target=\"_blank\"><img alt=\"Facebook\" src=\"https://campaign-image.com/zohocampaigns/335336000001084073_1_zcsclwgtfb4.png\" style=\"border: 0px;margin: 0px;outline: none; text-decoration: none;\" vspace=\"5\"></span></p></a></td>"+
	                "<td align=\"center\"><p><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT102_com_zimbra_url\"><a href=\"https://twitter.com/iamusebooth/\" style=\"text-decoration:underline;display: block;font-size: 1px;\" target=\"_blank\"><img alt=\"Twitter\" src=\"https://campaign-image.com/zohocampaigns/335336000001084073_2_zcsclwgttwt4.png\" style=\"border: 0px;margin: 0px;outline: none; text-decoration: none\" vspace=\"5\"></span></p></a></td>"+
	                "<td align=\"center\"><p><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT103_com_zimbra_url\"><a href=\"https://www.linkedin.com/company/18273958/\" style=\"text-decoration:underline;display: block;font-size: 1px;\" target=\"_blank\"><img alt=\"LinkedIn\" src=\"https://campaign-image.com/zohocampaigns/335336000001084073_3_zcsclwgtlin4.png\" style=\"border: 0px;margin: 0px;outline: none; text-decoration: none\" vspace=\"5\"></span></p></a></td>"+
	                "<td align=\"center\"><p><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT103_com_zimbra_url\"><a href=\"https://www.instagram.com/iamusepics/\" style=\"text-decoration:underline;display: block;font-size: 1px;\" target=\"_blank\"><img alt=\"Instagram\" src=\"https://campaign-image.com/zohocampaigns/335336000001084073_4_zcsclwgtinsta4.png\" style=\"border: 0px;margin: 0px;outline: none; text-decoration: none\" vspace=\"5\"></span></p></a>"+
	                "<td align=\"center\"><p><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT103_com_zimbra_url\"><a href=\"mailto:support@iAmuse.com\" style=\"text-decoration:underline;display: block;font-size: 1px;\" target=\"_blank\"><img alt=\"support@iAmuse.com\" src=\"https://campaign-image.com/zohocampaigns/335336000001084073_5_zcsclwgtmail4.png\" style=\"border: 0px;margin: 0px;outline: none; text-decoration: none\" vspace=\"5\"></span></p></a>"+
	                 "</tr></tbody></table>"+
	               "<p style=\"font-size:11px;font-family:Arial;\">"+
	               "This email was sent by <a style='color:#0000FF;font-size:11px;font-family:Arial;'target=\"_blank\" href=\"mailto:info@iamuse.com\"><span style='color:#0000FF;font-size:11px;font-family:Arial;'>info@iamuse.com</span></a> to <a style='color:#0000FF;font-size:11px;font-family:Arial;' target=3D\"_blank\" href=\"mailto:mike@tanyi.ca\"><span style=3D'color:#0000FF;font-size:11px;font-family:Arial;'>mike@tanyi.ca</span></a></p>"+
	               "<p style=\"font-size:11px;font-family:Arial;\">"+
	               "Not interested? <a style='color:#0000FF;font-size:11px;font-family:Arial;' target=\"_blank\" href=\"https://iamu.maillist-manage.com/ua/testemail?od=2d5a885a69b60a972b74097ddbe736c751185630859ca1fd0&cmpDgs=14a75a50b8770a86&tm=unsub\"rel=\"nofollow\"><span style='color:#0000FF;font-size:11px;font-family:Arial;'>Unsubscribe</span></a> | <a style='color:#0000FF;font-size:11px;font-family:Arial;'href=\"https://iamu.maillist-manage.com/ua/testemail?od=2d5a885a69b60a972b74097ddbe736c751185630859ca1fd0&cmpDgs=14a75a50b8770a86&tm=upd\" target=\"_blank\" rel=\"nofollow\"><span style='color:#0000FF;font-size:11px;font-family:Arial;'>Update profile</span></a></p>"+
	              // "<p style=\"text-align:center;\">List Description comes here.</p>"+
	               //"<p>Our&nbsp<a href=\"\" target=\"_blank\" rel=\"nofollow\">Privacy Policy</a>&nbspand&nbsp<a href=\"#\" target=\"_blank\" rel=\"nofollow\">Terms of use.</a></p>"+
	               "<p style=\"font-size:11px;font-family:Arial;\">Our <a style='color:#0000FF;font-size:11px;font-family:Arial;'target=\"_blank\" href=\"https://www.iamuse.com/privacy-policy\"><span style='color:#0000FF;font-size:11px;font-family:Arial;'>Privacy Policy</span></a> and <a style='color:#0000FF;font-size:11px;font-family:Arial;' target=\"_blank\" href=\"https://www.iamuse.com/terms-conditions\"><span style='color:#0000FF;font-size:11px;font-family:Arial;'>Terms of Use</span></a></p>"+
	               //"<p>Visit our website <span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT104_com_zimbra_url\"><a href=\"http://www.iamuse.com\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://www.iamuse.com&amp;source=gmail&amp;ust=1493380684677000&amp;usg=AFQjCNE_v-p9Y1LQV-DpIv5GqwYEJDT-rQ\">www.iamuse.com</a></span></p>"+
	                "</td></tr></tbody></table></td></tr><tr>"+	
				    /*
						 * "<tbody><tr><td align=\"center\"> <p><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT101_com_zimbra_url\"><a href=\"https://www.facebook.com/iamusebooth\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=https://www.facebook.com/iamusebooth&amp;source=gmail&amp;ust=1493380684677000&amp;usg=AFQjCNFLfToZNO2UisyTB9FWtiPfUFEhcA\"><img alt=\"\" height=\"77\" width=\"78\" src=\"https://ci5.googleusercontent.com/proxy/QgfU2y23FrIXterFADbYO6zHV-jy-Q77H7tdk1antUNK7QB_pJnh70wJsD5eRvf-SNxKFGxkGbyZ48O38acnes2gH8Z3JUoiRZAIhEg-gKU=s0-d-e1-ft#http://iamuse-kiosk.appspot.com/images/social/facebook.png\" class=\"CToWUd\"></a></span></p><p>Like us on Facebook</p></td>"
						 * +
						 * "<td align=\"center\"><p><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT102_com_zimbra_url\"><a href=\"http://instagram.com/iamusepics\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://instagram.com/iamusepics&amp;source=gmail&amp;ust=1493380684677000&amp;usg=AFQjCNF5OifpP40I6GUbWMZA7Wq8I0Y4mw\"><img alt=\"\" height=\"76\" width=\"77\" src=\"https://ci6.googleusercontent.com/proxy/nWsavTIlx5fyu7OeMazk3LCxbs5zE_ooib6Ge2H6zYXQd4Q7Nc5f661dn9_rGpnBXD7UX8VSTNciK2FMYtqmrlfqoz4qMycEGRFxo4vqZWbW=s0-d-e1-ft#http://iamuse-kiosk.appspot.com/images/social/instagram.png\" class=\"CToWUd\"></a></span></p><p>Follow us on Instagram</p></td>"
						 * +
						 * "<td align=\"center\"><p><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT103_com_zimbra_url\"><a href=\"http://instagram.com/iamusepics\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://instagram.com/iamusepics&amp;source=gmail&amp;ust=1493380684677000&amp;usg=AFQjCNF5OifpP40I6GUbWMZA7Wq8I0Y4mw\"><img alt=\"\" height=\"77\" width=\"78\" src=\"https://ci3.googleusercontent.com/proxy/C8UwfqvdWNrRWij9MwBeGbrD1MgAvx5E1fO-a1JpCaIHYFn7oWDXD7YaGQKCdFu-kOF4bqMhC6dRi7lAoznpM2fLiUdfUxCXlzSpwNyC9A=s0-d-e1-ft#http://iamuse-kiosk.appspot.com/images/social/twitter.png\" class=\"CToWUd\"></a></span></p><p>Follow us on Twitter</p></td>"
						 * + "</tr></tbody></table>"+
						 */
						/*
						 * "<p>Visit our website <span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT104_com_zimbra_url\"><a href=\"http://www.iamuse.com\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://www.iamuse.com&amp;source=gmail&amp;ust=1493380684677000&amp;usg=AFQjCNE_v-p9Y1LQV-DpIv5GqwYEJDT-rQ\">www.iamuse.com</a></span></p>"
						 * + "</td></tr></tbody></table></td></tr><tr>"+
						 * "<td class=\"m_-5368927744985068358payloadCell\" style=\"height:40px;font-size:9px;font-family:'helvetica neue',arial,serif;color:rgb(136,136,136)\" align=\"right\" valign=\"top\"><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT105_com_zimbra_url\"><a style=\"color:rgb(136,136,136)\" href=\"http://iamuse.com\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://iamuse.com&amp;source=gmail&amp;ust=1493380684677000&amp;usg=AFQjCNHuUfOsnIEfdwOnnQQ9sl7Ljgn9ZA\">powered by iAmuse.com</a></span></td>"
						 * +
						 */
                    "</tr></tbody></table></td></tr></tbody></table></body></html>";	
		
			
		mailUtil.sendUpgradeSubscriptionEmail("IAMUSE<dev@iamuse.com>", emailId, testText, "Upgrade Your Subscription");
		redirectAttributes.addFlashAttribute("successMessage","Mail Sent Successfully to '"+emailId+"'");
		return "redirect:getBoothAdminList";
		}catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage","fail for sending a mail to '"+emailId+"'");
			return "redirect:getBoothAdminList";
		}
		}else{
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="eventReportDetailsSA")
	public String eventReportDetails(@RequestParam ("eventId")Integer eventId,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
		BoothAdminLogin boothAdminLogin2=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
		
		if(boothAdminLogin2 !=null){
		modelMap.addAttribute("boothAdminLogin2",boothAdminLogin2);
		
		EventVO eventVO=boothAdminService.getEventDetails(boothAdminLogin.getUserId(),eventId);
		modelMap.addAttribute("events",eventVO);
		
		OptionsReports optionsReports=boothAdminService.getEventReportDetails(boothAdminLogin.getUserId(),eventId);
		modelMap.addAttribute("optionsReports",optionsReports);
		
		
		List<ImageEmailFormVO> emailImagesLists = iamuseDashboardService.getEventImagesSummaryLists(""+boothAdminLogin.getUserId(),eventId);
		modelMap.addAttribute("emailImagesLists", emailImagesLists);	
		
		List<ImageEmailFormVO> emailImagesList = iamuseDashboardService.getEmailImagesListBasedOnEventID(boothAdminLogin.getUserId(),eventId,eventVO.getEventName());
		modelMap.addAttribute(ServerConstants.EMAIL_IMAGE_LIST,emailImagesList);
		modelMap.addAttribute("eventIdsss",eventId);
		request.getSession().setAttribute(ServerConstants.EMAIL_IMAGE_LIST,emailImagesList);
		
		return "viewEventDatailSA";
		}
		else{
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="eventGallerySA")
	public String eventGallerySA(@RequestParam(value="eventId",required=ServerConstants.MAKE_TRUE)Integer eventId,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)
	{
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		String pageids=request.getParameter(ServerConstants.PAGE_ID);
		String totals=request.getParameter(ServerConstants.TOTAL);
		int pageid=0;
		int total=0;
		if(pageids==null  && totals==null){
			pageid=1;
			total=50;
		}
		if(total==0){
			total=Integer.parseInt(totals);
			pageid=Integer.parseInt(pageids);
		}	
		if(boothAdminLogin !=null){
			List<DeviceRegistration> deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
			BoothAdminLogin boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
				List<ImageEmailFormVO> emailImagesList = iamuseDashboardService.getEmailImagesLists(""+boothAdminLogin.getUserId(),eventId,pageid,total);
				modelMap.addAttribute(ServerConstants.EMAIL_IMAGE_LIST, emailImagesList);
				
				OptionsReports optionsReports=boothAdminService.getEventReportDetails(boothAdminLogin.getUserId(),eventId);
				modelMap.addAttribute(ServerConstants.OPTIONS_REPORTS,optionsReports);
				modelMap.addAttribute(ServerConstants.BOOTH_ADMIN_LOGIN2,boothAdminLogin1);
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
				modelMap.addAttribute(ServerConstants.PAGE_ID,pageid);
				modelMap.addAttribute("pageCount",pageCount);
				modelMap.addAttribute(ServerConstants.TOTAL,total);
				
				return "eventGallerySA";
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
	}
	
	@RequestMapping(value="dbToImagesZipSA")
	 public  String dbToImagesZip(@ModelAttribute("ImageEmailFormVO")ImageEmailFormVO imageEmailFormVO,@RequestParam("eventId")Integer eventId,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes) throws IOException
	 {
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
		if(boothAdminLogin!=null){
		String[] total=imageEmailFormVO.getImageIds();
		  if(imageEmailFormVO.getEventAction().equals("export")){
			  List<ImageEmailFormVO> emailImagesList = iamuseDashboardService.getEmailImagesZipList(""+boothAdminLogin.getUserId(),total);
			  String name2 = null;
			  try {
				  name2 = new java.io.File(request.getSession().getServletContext().getRealPath("")+"/..").getCanonicalPath();
			  } catch (java.io.IOException e) {
			   e.printStackTrace();
			  }
			  
			         try
		                {
		                        	String zipFile = "zipdemo.zip";
		  					  		response.setHeader("Content-Disposition","attachment; filename=\"" + zipFile + "\"");
		  					  		response.setContentType("application/zip");
		  					  		ServletOutputStream outputStream = response.getOutputStream();
		  					     
		                       
		                        byte[] buffer = new byte[1024];
		                       
		                         ZipOutputStream zout = new ZipOutputStream(outputStream);
		                         
		                         for(int i=0; i < total.length; i++)
		                         {
		                                FileInputStream fin = new FileInputStream(name2+emailImagesList.get(i).getMailImageUrl()+"/"+emailImagesList.get(i).getEventId()+"/"+emailImagesList.get(i).getMailImageName());
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
		                          redirectAttributes.addFlashAttribute("successMessage","Export the selected images succesfully");
		                }
		                catch(IOException ioe)
		                {
		                	 redirectAttributes.addFlashAttribute("errorMessage","fail for exporting the selected images succesfully ");
		                	ioe.printStackTrace();
		                        System.out.println("IOException :" + ioe);
		                }
		               
		}else if(imageEmailFormVO.getEventAction().equals("delete")){
				System.out.println(total);
				  String result = iamuseDashboardService.deleteMailedImage(""+boothAdminLogin.getUserId(),total);
				  if(result.equals("success")){
					  redirectAttributes.addFlashAttribute("successMessage","Selected Images Deleted");
					  return "redirect:eventGallerySA?eventId="+imageEmailFormVO.getEventId();
				  }else{
					  redirectAttributes.addFlashAttribute("errorMessage","Images Not Deleted");
					  return "redirect:eventGallerySA?eventId="+imageEmailFormVO.getEventId();
				  }
		  }else if(imageEmailFormVO.getEventAction().equals("resend")){
			  
			  String result=boothAdminService.resendEmailImages(""+boothAdminLogin.getUserId(),total,request);
			  if(result.equals("success")){
				  redirectAttributes.addFlashAttribute("successMessage","Sent Mail Images Successfully");
				  return "redirect:eventGallerySA?eventId="+imageEmailFormVO.getEventId();
			  }
		  }
		  return "redirect:eventGallerySA?eventId="+imageEmailFormVO.getEventId();
		}else{
				return "redirect:/";
			}
		   }
	
	@RequestMapping(value="socialShareSA")
	 public String shareFbOrTwiter(@RequestParam("userId")String u,@RequestParam("imageIds")String[] imageIds,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes)
	 {
		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
		if(boothAdminLogin!=null){
		String[] total=imageIds;
		System.out.println(total.toString());
		  List<ImageEmailFormVO> emailImagesList = iamuseDashboardService.getEmailImagesShareList(""+u,total);
		  if(emailImagesList.size()>0){
		  modelMap.addAttribute("emailImagesList",emailImagesList);
		  return "socialShare";
		  }
		  String referer = request.getHeader("Referer");
		    return "redirect:/"+ referer;
		}else{
			return "redirect:/";
		}
	 }
	
	@RequestMapping(value="updateWaterMarkLookAtTouchThankYouCameraScreenSA",method = RequestMethod.POST)
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
			List<DeviceRegistration> deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
			modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
			adminPictureVO.setCreatedBy(boothAdminLogin.getUserId());
			AdminPictureVO adminPictureVOs=boothAdminService.updateWaterMarkLookAtTouchThankYouCameraScreen(adminPictureVO,files,rootPath,thankyoufiles,lookAtTouchScreen,cameraTVScreenSaver,waterMarkImage);
		
		if((ServerConstants.SUCCESS).equalsIgnoreCase(adminPictureVOs.getResult())){
			redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,"Successfully uploaded background image");
			return "redirect:getUploadedImagesSA?eventId="+adminPictureVOs.getEId();
		}else{
			redirectAttributes.addFlashAttribute(ServerConstants.ERROR_MESSAGE,"failed for upload background image");
			return "redirect:getUploadedImagesSA?eventId="+adminPictureVOs.getEId();
		}
		}else{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
	}
}
