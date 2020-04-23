package com.iamuse.admin.controller;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.iamuse.admin.VO.CrashLogsFormVO;
import com.iamuse.admin.VO.ImageEmailFormVO;
import com.iamuse.admin.VO.ImageFormVO;
import com.iamuse.admin.entity.BoothAdminLogin;
import com.iamuse.admin.entity.DeviceRegistration;
import com.iamuse.admin.service.BoothAdminService;
import com.iamuse.admin.service.IamuseDashboardService;
import com.iamuse.admin.util.ClearDevicePicturesPushNotificationTask;
import com.iamuse.admin.util.ImagePushNotificationTask;
import com.iamuse.admin.util.MailUtil;
import com.iamuse.admin.util.PushNotificationTask;
import com.iamuse.admin.util.ThreadPool;
import com.paypal.constants.ServerConstants;

@Controller
public class IamuseDashBoardController {
	
	
	@Autowired MessageSource messageSource;
	@Autowired IamuseDashboardService iamuseDashboardService;
	@Autowired ThreadPool pool;
	@Autowired PushNotificationTask task;
	@Autowired ImagePushNotificationTask imagetask;
	@Autowired ClearDevicePicturesPushNotificationTask cleartask;
	@Autowired MailUtil mailUtil;
	@Autowired BoothAdminService boothAdminService;
	
	private Locale locale = LocaleContextHolder.getLocale();
	private static final Logger log = Logger.getLogger(IamuseDashBoardController.class);
	String rootPath = System.getProperty("catalina.home");
	List<DeviceRegistration> deviceRegistration;
	BoothAdminLogin boothAdminLogin;
	BoothAdminLogin boothAdminLogin1;
	
	@RequestMapping(value="/imagedownload/{id}")
	public void imageDownload(@PathVariable("id") int imageId,HttpServletRequest request,HttpServletResponse response)
	{
	   try {
		   boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		   ImageFormVO downloadImageResponseVO = iamuseDashboardService.downloadImage(imageId,boothAdminLogin.getUserId());
		 if(downloadImageResponseVO!=null)
		 {
			
			File file = new File(rootPath+downloadImageResponseVO.getImages()+"/"+boothAdminLogin.getUserId()+"/"+downloadImageResponseVO.getImagename());
		 if(file.exists())
		 {
		 ServletContext context = request.getSession().getServletContext();
		 InputStream in = new BufferedInputStream(new FileInputStream(file));
		 String mimetype = context.getMimeType(downloadImageResponseVO.getImages());
		 response.setContentType((mimetype != null) ? mimetype : "application/octet-stream");
		 response.setHeader("Content-Disposition","attachment; filename=\""  +downloadImageResponseVO.getImagename()+"");
		 response.setCharacterEncoding("utf-8");
	     response.setContentLength(in.available());
	     ServletOutputStream out = response.getOutputStream();
	     IOUtils.copy(in, out);
		 response.flushBuffer();
		 in.close();
		 out.flush();
		 out.close();
		 }
		 }
		
	   } catch (Exception e) {
		   log.error("Error imagedownload",e);
		   log.info("IamuseDashBoardController Method:imageDownload");
	   }

	}
	
	

 	@RequestMapping(value="/saveRGBValue",params={"id"})
	public String updateRGBValue(@ModelAttribute("ImageFormVO") ImageFormVO imageFormVO,@RequestParam(value="id") String imageId,HttpServletResponse response,HttpServletRequest request,ModelMap model,RedirectAttributes redirectAttributes)
	{
 		
 		boolean result;
 		String checkMinOrMax;
 		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
 		if(boothAdminLogin == null)
		{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
 		boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
		try {
			String rgbval=request.getParameter("rgbValue");
			String hexval=request.getParameter("hexValue");
			String rgbaval=request.getParameter("rgbaValue");
			checkMinOrMax=request.getParameter("rgb");
			if(checkMinOrMax==null){
				checkMinOrMax=ServerConstants.DEFAULTS;
	
			}
			if(("min").equals(checkMinOrMax) && (ServerConstants.DEFAULTS).equals(checkMinOrMax))
			{
			imageFormVO.setImageId(Integer.parseInt(imageId));
			imageFormVO.setRgbValue(rgbval);
			imageFormVO.setHexValue(hexval);
			imageFormVO.setRgbaValue(rgbaval);
			if(imageFormVO.getRgbValue()==null || ("").equals(imageFormVO.getRgbValue())){
				 imageFormVO.setRgbValue(0+","+0+","+0);
				   }
		    result=iamuseDashboardService.updateRGBValue(imageFormVO,""+boothAdminLogin.getUserId());
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
			else if(("max").equals(checkMinOrMax)){
				imageFormVO.setImageId(Integer.parseInt(imageId));
				imageFormVO.setRgbValue(rgbval);
				imageFormVO.setHexValue(hexval);
				imageFormVO.setRgbaValue(rgbaval);
				 if(imageFormVO.getRgbValue()==null || ("").equals(imageFormVO.getRgbValue())){
					 imageFormVO.setRgbValue(0+","+0+","+0);
					   }
			    result=iamuseDashboardService.updateRGBValueMax(imageFormVO,""+boothAdminLogin.getUserId());
			    if(result){
			    	boothAdminLogin1.setIsDefaultRgb(ServerConstants.MAKE_FALSE);
			    	boothAdminLogin1.setHexValueManual(hexval);
			    	boothAdminLogin1.setRgbaValueManual(rgbaval);
					if(rgbval==null || ("").equals(rgbval)){
						boothAdminLogin1.setRgbValueManual(0+","+0+","+0);
						}
					else{
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
				     result=iamuseDashboardService.updateRGBValue(imageFormVO,""+boothAdminLogin.getUserId());
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
				if(result==ServerConstants.MAKE_TRUE){
					ThreadPoolTaskExecutor taskExecutor=pool.taskExecutor();
					deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
					task.setDetails(deviceRegistration, messageSource,rootPath);
					taskExecutor.execute(task);
		    		redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,  messageSource.getMessage("rgb.update.success",null,locale));
					return ServerConstants.OPEN_IMAGE_PAGE+imageId;
				}
				
			}
			
	 catch (Exception e) {
		 log.error("Error updateRGBValue",e);
		   log.info("IamuseDashBoardController Method:updateRGBValue");
			
		}
		return ServerConstants.OPEN_IMAGE_PAGE+imageId;
	}
 	
 	@RequestMapping(value="/resetRGBValue",params={"id"})
	public String resetRGBValue(@ModelAttribute("ImageFormVO") ImageFormVO imageFormVO,@RequestParam(value="id") String imageId,HttpServletRequest request,ModelMap model,RedirectAttributes redirectAttributes)
	{ 
 		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin == null)
		{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
 		imageFormVO.setImageId(Integer.parseInt(imageId));
 		iamuseDashboardService.resetRGBValueDefault(imageFormVO,boothAdminLogin.getUserId());
 		return ServerConstants.OPEN_IMAGE_PAGE+imageId;
}
 	
 	@RequestMapping("/resetSystemDefaultRGBValue")
	public String resetSystemDefaultRGBValue(@ModelAttribute("ImageFormVO") ImageFormVO imageFormVO,HttpServletRequest request,ModelMap model,RedirectAttributes redirectAttributes)
	{ 
 		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
 		if(boothAdminLogin==null)
		{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
 		boolean result;
 		List<ImageFormVO> imageFormVO1 = new ArrayList<>();
 		 result=iamuseDashboardService.resetSystemDefaultRGBValue(""+boothAdminLogin.getUserId());
 		if(result==ServerConstants.MAKE_TRUE){
			boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
			boothAdminLogin1.setCurrentImageId(0);
			boothAdminLogin1.setIsDefaultRgb(ServerConstants.MAKE_TRUE);
 			request.getSession().setAttribute(ServerConstants.USER_MASTER,boothAdminLogin1);
 			imageFormVO.setHexValue(boothAdminLogin1.getHexValueDefault());
			imageFormVO.setRgbValue(boothAdminLogin1.getRgbValueDefault());
			imageFormVO.setRgbaValue(boothAdminLogin1.getRgbaValueDefault());
		    imageFormVO1.add(imageFormVO);
		    model.addAttribute("imageDetails",imageFormVO1);
		    ThreadPoolTaskExecutor taskExecutor=pool.taskExecutor();
			
			deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
			task.setDetails(deviceRegistration,messageSource, rootPath);
			taskExecutor.execute(task);
    		return ServerConstants.BOOTH_SETUP;	
 		}
 		return ServerConstants.BOOTH_SETUP;
	}
 	
 	@RequestMapping(value="/removeImage",params={"id"})
  	public String deleteImage(@ModelAttribute("ImageFormVO") ImageFormVO imageFormVO,@RequestParam(value="id") String imageId,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes)
 	{  
 		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
 		if(boothAdminLogin==null)
		{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
 		boolean result;
 		result=iamuseDashboardService.deleteImage(Integer.parseInt(imageId));
 		if(result==ServerConstants.MAKE_TRUE){
 			redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,  messageSource.getMessage("img.delete.success",null,locale));
 			log.info("Image deleted successfully");
 			return ServerConstants.RGB_SETUP;
 		}
 	
 		return ServerConstants.RGB_SETUP;
 	}
 	 
 	@RequestMapping(value="/updateDefaultRGB")
	public String updateDefaultRGB(@ModelAttribute("imageFormVO") ImageFormVO imageFormVO,HttpServletRequest request,ModelMap model,RedirectAttributes redirectAttributes)
	{
		try {
			boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
			boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
 			int imageId=boothAdminLogin1.getCurrentImageId();
			String userId=""+boothAdminLogin.getUserId();
			boolean result;
			imageFormVO.setImageId(imageId);
			Color color = new Color(Integer.parseInt(imageFormVO.getR()), Integer.parseInt(imageFormVO.getG()), Integer.parseInt(imageFormVO.getB()));
			  String hex = Integer.toHexString(color.getRGB() & 0xffffff);
			  if (hex.length() < 6) {
			      hex = "0" + hex;
			  }
	String hexValue=hex.toUpperCase();
	String rgbValue=imageFormVO.getR()+","+imageFormVO.getG()+","+imageFormVO.getB();
	String rgbaValue=rgbValue+","+"255";
	imageFormVO.setHexValue(hexValue);
	imageFormVO.setRgbValue(rgbValue);
	imageFormVO.setRgbaValue(rgbaValue);
			 result=iamuseDashboardService.updateRGBValue(imageFormVO,userId);
				if(result==ServerConstants.MAKE_TRUE)
				{	
					boothAdminLogin1.setHexValueManual(hexValue);
					boothAdminLogin1.setRgbValueManual(rgbValue);
					boothAdminLogin1.setRgbaValueManual(rgbaValue);
					boothAdminLogin1.setIsDefaultRgb(ServerConstants.MAKE_FALSE);
					request.getSession().setAttribute(ServerConstants.USER_MASTER, boothAdminLogin1);
					ThreadPoolTaskExecutor taskExecutor=pool.taskExecutor();
					deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
					task.setDetails(deviceRegistration, messageSource,rootPath);
					taskExecutor.execute(task);
					redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE,  "Transparent Values Updated Successfully");
					log.info("RGB Value updated successfully");
					return ServerConstants.RGB_SETUP;
				}
				else
					return ServerConstants.RGB_SETUP;
			}
			
	 catch (Exception e) {
		 log.info("IanuseDashBoardController Method:updateDefaultRGB");
		 log.error("Error updateDefaultRGB",e);
		}
		return ServerConstants.RGB_SETUP;
	}

 	@RequestMapping("/iamuseCrashLogsList")
	public String iamuseCrashLogsList(@ModelAttribute("CrashLogsFormVO") CrashLogsFormVO crashLogsFormVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)
	{
 		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
		if(boothAdminLogin ==null)
		{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}else{
			List<CrashLogsFormVO> crashLogsList = iamuseDashboardService.getCrashLogsList(""+boothAdminLogin.getUserId());
			modelMap.addAttribute("crashLogsList", crashLogsList);
			return "crashlogsPage";	
		}
		
	}
 	
 	@RequestMapping(value="/autoRefresh")
	public String autoRefresh(HttpServletRequest request)
	{
 		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
 		if(boothAdminLogin ==null){
 			return ServerConstants.REDIRECT_LOGIN_PAGE;
 		}
 		int oldListSize=(Integer)request.getSession().getAttribute("oldListSize");
 		List<ImageFormVO> oldimagesList=(List)request.getSession().getAttribute("oldList");
 		List<ImageFormVO> imagesList = iamuseDashboardService.getImagesList(""+boothAdminLogin.getUserId());
 		if(imagesList.size()>oldListSize)
 		{
	 		String status="1";
	 		request.getSession().setAttribute(ServerConstants.STATUS, status);	
			request.getSession().setAttribute("oldListSize", imagesList.size());
			request.getSession().setAttribute("oldList", imagesList);
			request.getSession().setAttribute(ServerConstants.EMAIL_IMAGE_LIST,oldimagesList );
 		}
 		else{
 		 oldimagesList=(List)request.getSession().getAttribute("oldList");
 		 request.getSession().setAttribute("imagesList",oldimagesList );
 		}
		return "boothSetUpByEventConfigFirst";
	}
	
	@RequestMapping(value="/afterRefresh")
	public String afterRefresh(HttpServletRequest request,ModelMap model,RedirectAttributes redirectAttributes)
	{
		String status="0";
		request.getSession().setAttribute(ServerConstants.STATUS, status);	
		List<ImageEmailFormVO> oldimagesList=(List)request.getSession().getAttribute("oldList");
		request.getSession().setAttribute(ServerConstants.EMAIL_IMAGE_LIST,oldimagesList );
		return ServerConstants.BOOTH_SETUP;
	}
	
	@RequestMapping(value="/afterRefreshConfig")
	public String afterRefreshConfig(HttpServletRequest request,ModelMap model,RedirectAttributes redirectAttributes)
	{
		String status="0";
		request.getSession().setAttribute(ServerConstants.STATUS, status);	
		List<ImageEmailFormVO> oldimagesList=(List)request.getSession().getAttribute("oldList");
		request.getSession().setAttribute(ServerConstants.EMAIL_IMAGE_LIST,oldimagesList );
		return ServerConstants.BOOTH_SETUP_BY_EVENT_CONFIG_FIRST;
	}

	@RequestMapping(value="/crashLogsDownloads/{id}")
	public void crashLogsDownloads(@PathVariable("id")String id,HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes)
	{
	   try {
		   iamuseDashboardService.isRead(Integer.parseInt(id),"");;
		   CrashLogsFormVO crashLogsFormVO = iamuseDashboardService.downloadCrashFile(id);
		   ArrayList<File> files = new ArrayList<>();
			
		 if(crashLogsFormVO!=null)
		 {
			 String[] arr=crashLogsFormVO.getFileName().split(",");
			 int z=0;
			 int k=0;
			 for(;z<arr.length;z++)
			 {
			 files.add(new File(rootPath+crashLogsFormVO.getFileUrl()+"/"+id+"/"+arr[z]));
			 }
		 response.setContentType("application/zip");
		 response.setHeader("Content-Disposition","attachment; filename=crashlogs.zip");
		 response.setCharacterEncoding("utf-8");
	     ServletOutputStream out = response.getOutputStream();
	     ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(out));
	     for (File file : files) {
				zos.putNextEntry(new ZipEntry(arr[k]));
				 InputStream in = new BufferedInputStream(new FileInputStream(file));
	     IOUtils.copy(in, zos);
		 response.flushBuffer();
		 in.close();
		 k++;
	     }
			 
		 zos.flush();
		 zos.close();
		 }
		 } catch (Exception e) {
	    	log.info("IamuseDashBoardController Method : crashLogsDownloads");
	    	log.error("Error crashLogsDownloads",e);
	   }

	}
	
	@ResponseBody 
	@RequestMapping(value="/clearDevicePictures")
  	public void clearList(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes)
 	{  
			boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute(ServerConstants.BOOTH_ADMIN_LOGIN);
	 		deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
	 		ThreadPoolTaskExecutor taskExecutor=pool.taskExecutor();
			cleartask.setDetails(deviceRegistration, messageSource,rootPath);
			taskExecutor.execute(cleartask);
 	}
	
	
	@RequestMapping(value="/saveRGBValueBooth",params={"id"})
	public String updateRGBValueBooth(@ModelAttribute("ImageFormVO") ImageFormVO imageFormVO,@RequestParam(value="id") String imageId,HttpServletResponse response,HttpServletRequest request,ModelMap model,RedirectAttributes redirectAttributes)
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
					log.info("dwhjdqwhjdqjdg"+rootPath);
					task.setDetails(deviceRegistration, messageSource,rootPath);
					taskExecutor.execute(task);
		    		redirectAttributes.addFlashAttribute(ServerConstants.SUCCESS_MESSAGE, "Transparent color saved");
		    		return "redirect:boothSetUpByEventConfigSecond";
				}
			}
	 catch (Exception e) {
		 log.error("Error saveRGBValueBoothSetup",e);
		   log.info("BoothAdminController Method:saveRGBValueBoothSetup");
		}
		return ServerConstants.BOOTH_SETUP_BY_EVENT_CONFIG_FIRST;
	}
	@RequestMapping("/resetSystemDefaultRGBValueConfig")
	public String resetSystemDefaultRGBValueConfig(@ModelAttribute("ImageFormVO") ImageFormVO imageFormVO,HttpServletRequest request,ModelMap model,RedirectAttributes redirectAttributes)
	{ 
 		boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
 		if(boothAdminLogin==null)
		{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
 		boolean result;
 		List<ImageFormVO> imageFormVO1 = new ArrayList<>();
 		 result=iamuseDashboardService.resetSystemDefaultRGBValue(""+boothAdminLogin.getUserId());
 		if(result==ServerConstants.MAKE_TRUE){
			boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
			boothAdminLogin1.setCurrentImageId(0);
			boothAdminLogin1.setIsDefaultRgb(ServerConstants.MAKE_TRUE);
 			request.getSession().setAttribute(ServerConstants.USER_MASTER,boothAdminLogin1);
 			imageFormVO.setHexValue(boothAdminLogin1.getHexValueDefault());
			imageFormVO.setRgbValue(boothAdminLogin1.getRgbValueDefault());
			imageFormVO.setRgbaValue(boothAdminLogin1.getRgbaValueDefault());
		    imageFormVO1.add(imageFormVO);
		    model.addAttribute("imageDetails",imageFormVO1);
		    ThreadPoolTaskExecutor taskExecutor=pool.taskExecutor();
			deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin.getUserId());
			task.setDetails(deviceRegistration,messageSource, rootPath);
			taskExecutor.execute(task);
    		return ServerConstants.BOOTH_SETUP_BY_EVENT_CONFIG_FIRST;	
 		}
 		return ServerConstants.BOOTH_SETUP_BY_EVENT_CONFIG_FIRST;
}
	
}