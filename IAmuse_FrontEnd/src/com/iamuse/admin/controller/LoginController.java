package com.iamuse.admin.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.iamuse.admin.VO.ImageFormVO;
import com.iamuse.admin.VO.LoginVO;
import com.iamuse.admin.VO.SignInVO;
import com.iamuse.admin.entity.BoothAdminLogin;
import com.iamuse.admin.entity.DeviceRegistration;
import com.iamuse.admin.entity.UploadImage;
import com.iamuse.admin.service.BoothAdminService;
import com.iamuse.admin.service.IamuseDashboardService;
import com.iamuse.admin.service.LoginService;
import com.iamuse.admin.util.MailUtil;
import com.iamuse.admin.validator.LoginFormValidator;
import com.paypal.constants.ServerConstants;

@SuppressWarnings({"unused"})
@Controller
public class LoginController {
	private Locale locale = LocaleContextHolder.getLocale();

	@Autowired MessageSource messageSource;
	@Autowired LoginService loginService;
	@Autowired IamuseDashboardService iamuseDashboardService;
	@Autowired LoginFormValidator validator;
	@Autowired BoothAdminService boothAdminService;
	@Autowired MailUtil mailUtil;
	
	String superAdmin="superadmin";
	String boothAdmin="boothadmin";
	
	
	@RequestMapping("/openimagepage")
	public String openimagepage(@RequestParam(value="id") String id,@ModelAttribute("ImageFormVO") ImageFormVO imageFormVO,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)
	{	
		BoothAdminLogin boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
		if(boothAdminLogin ==null)
		{
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}
		BoothAdminLogin boothAdminLogin2=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
		
		List<ImageFormVO> imagesList = iamuseDashboardService.getImagesList(""+boothAdminLogin.getUserId());
		System.out.println("sgasdghhjasd");
		modelMap.addAttribute("imagesList", imagesList);
		
		
		List<ImageFormVO> imageFormVO1 = new ArrayList<>();
		if(boothAdminLogin2.getIsDefaultRgb()==true){
			imageFormVO.setHexValue(boothAdminLogin2.getHexValueDefault());
			imageFormVO.setRgbValue(boothAdminLogin2.getRgbValueDefault());
			imageFormVO.setRgbaValue(boothAdminLogin2.getRgbaValueDefault());
		}else{
		imageFormVO.setHexValue(boothAdminLogin2.getHexValueManual());
		imageFormVO.setRgbValue(boothAdminLogin2.getRgbValueManual());
		imageFormVO.setRgbaValue(boothAdminLogin2.getRgbaValueManual());
		}
		String rgb[]=imageFormVO.getRgbValue().split(",");
		imageFormVO.setR(rgb[0]);
		imageFormVO.setG(rgb[1]);
		imageFormVO.setB(rgb[2]);
	    imageFormVO1.add(imageFormVO);
	    modelMap.addAttribute("imageDetails",imageFormVO1);
	
		modelMap.addAttribute("id", id);
		modelMap.addAttribute("boothAdminLogin",boothAdminLogin2);
		
		UploadImage uploadImage=boothAdminService.getCurrentImagesClicked(boothAdminLogin.getUserId(),Integer.parseInt(id));
		modelMap.addAttribute("uploadImage",uploadImage);
			return "openimagepage";	
	}

	@RequestMapping(value="/")
	public String signInPage(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)
	{
		return "signInPage";	
	}
	
	@RequestMapping(value="/signInAction")
	public String signInAction(@ModelAttribute("SignInVO") SignInVO signInVO,HttpServletRequest request,ModelMap model,RedirectAttributes redirectAttributes) throws ParseException
	{
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String folderName = formatter.format(today);
		Date startDate = formatter.parse(folderName);
		try {
			String result = validator.validateBoothLoginForm(signInVO);
			if(("success").equals(result)){
				BoothAdminLogin boothAdminLogin=loginService.isValidUser(signInVO);
				if(boothAdminLogin !=null)
				{	
					request.getSession().setAttribute("boothAdminLogin", boothAdminLogin);
					request.getSession().setAttribute("status","0");
					List<ImageFormVO> imagesList = iamuseDashboardService.getImagesList(""+boothAdminLogin.getUserId());
					if(imagesList!=null){
						request.getSession().setAttribute("oldListSize", imagesList.size());
						request.getSession().setAttribute("oldList", imagesList);
					}
					if(boothAdminLogin.getUserRole().equals("superadmin")){
						return "redirect:getRegisteredDevice";
					}else if((boothAdmin).equals(boothAdminLogin.getUserRole())){
						if (startDate.compareTo(boothAdminLogin.getSubEndDate()==null ? yesterday():boothAdminLogin.getSubEndDate()) > 0 ) {
							String resultUpdate = boothAdminService.updatePreviousSubscription(boothAdminLogin.getUserId());
							if ((ServerConstants.SUCCESS).equals(resultUpdate)) {
								redirectAttributes.addFlashAttribute(ServerConstants.ERROR_MESSAGE, "Your Subscription expires today!");
								return "redirect:getSubscription";
							}
						}else {
							return "redirect:getSubscribedEventList";
						}
					}
				}else{
					model.addAttribute("errorMessage", "Invalid Email Address Or Password");
					return "signInPage";
				}
				}else{
					redirectAttributes.addFlashAttribute("errorMessage", result);
					return ServerConstants.REDIRECT_LOGIN_PAGE;
				}
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		redirectAttributes.addFlashAttribute("errorMessage", "Invalid Email Address Or Password");
		return ServerConstants.REDIRECT_LOGIN_PAGE;
	}
	
	@RequestMapping(value="/googleAction",method=RequestMethod.GET)
	public String display(@RequestParam(value="emailId",required=false)String emailId,@RequestParam(value="username",required=false)String username,
			@RequestParam(value="googleId",required=false)String googleId,@RequestParam(value="imageUrl",required=false)String imageUrl,
			HttpServletRequest request, HttpServletResponse response,ModelMap model,RedirectAttributes redirectAttributes) throws ServletException, IOException
	{
		SignInVO signInVO=new SignInVO();
		signInVO.setEmailId(emailId);
		signInVO.setUsername(username);
		signInVO.setGoogleId(googleId);
		signInVO.setImageFileName(imageUrl);
		
				BoothAdminLogin boothAdminLogin=loginService.setGoogleDetails(signInVO);
				HttpSession session=request.getSession();  
				session.setAttribute("boothAdminLogin", boothAdminLogin);
					if(boothAdminLogin !=null)
					{	
						request.getSession().setAttribute("status","0");
						List<ImageFormVO> imagesList = iamuseDashboardService.getImagesList(""+boothAdminLogin.getUserId());
						if(imagesList!=null){
						request.getSession().setAttribute("oldListSize", imagesList.size());
						request.getSession().setAttribute("oldList", imagesList);
						System.out.println(boothAdminLogin.getUserRole());
						}
						if(boothAdminLogin.getUserRole().equals("superadmin")){
							return "redirect:getRegisteredDevice";
						}/*else if((boothAdmin).equals(boothAdminLogin.getUserRole()) && boothAdminLogin.getLoginTour() == 0 && boothAdminLogin.getSubId()!=1 || boothAdminLogin.getSubId()==1){
							return "redirect:getSubscription";
						}*/else if((boothAdmin).equals(boothAdminLogin.getUserRole())/* && boothAdminLogin.getLoginTour() == 1 && boothAdminLogin.getSubId()!=1 || boothAdminLogin.getSubId()==1*/){
							return "redirect:getSubscribedEventList";
						}
					}else {
						model.addAttribute("errorMessage", "Invalid Email Address Or Password");
						return "signInPage";
					}
					return "signInPage";
	}
	
	
	
	
	@RequestMapping("SignOut")
	public String SignOut(HttpServletRequest request)
	{
		BoothAdminLogin boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
		if(boothAdminLogin==null){
			return ServerConstants.REDIRECT_LOGIN_PAGE;
		}else{
			request.getSession().invalidate();
		}
		return ServerConstants.REDIRECT_LOGIN_PAGE;
	}
	
	@RequestMapping("forgotPassword")
	public String forgotPassword(@ModelAttribute("loginVO")LoginVO loginVO,HttpServletRequest request,RedirectAttributes redirectAttributes)
	{
		
			String token=UUID.randomUUID().toString();
			String confirmationUrl = "registrationConfirm?token=" + token;
			String baseURL=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+ "/";
			String result=loginService.forgotPassword(loginVO.getUsername(),token);
			if(result.equals("success")){
				redirectAttributes.addFlashAttribute("successMessage", "Reset Password Email Sent On You Email : "+loginVO.getUsername());
				mailUtil.sendEmailByInfo("IAmuse <info@iamuse.com>", loginVO.getUsername(), baseURL+confirmationUrl, "Forgot Password URL");
			}else if(result.equals("invalidEmail")){
				redirectAttributes.addFlashAttribute("errorMessage", "Invalid Email : "+loginVO.getUsername());
				return ServerConstants.REDIRECT_LOGIN_PAGE;
			}
		return ServerConstants.REDIRECT_LOGIN_PAGE;
	}
	
	@RequestMapping("registrationConfirm")
	public String registrationConfirm(@RequestParam(value="token",required=false)String token,HttpServletRequest request,ModelMap modelMap)
	{
		
		modelMap.addAttribute("token",token);
		return "forgotPassword";
	}
	
	@RequestMapping("resetPassword")
	public String resetPassword(@ModelAttribute("signInVO")SignInVO signInVO,HttpServletRequest request,ModelMap modelMap,RedirectAttributes redirectAttributes)
	{
		String result=loginService.resetPassword(signInVO);
		if(result.equals("success")){
			redirectAttributes.addFlashAttribute("successMessage","Password Reset Successfully");
			return "redirect:/";
		}else if(result.equals("fail")){
			redirectAttributes.addFlashAttribute("errorMessage","Your Reset Token Is Expired");
			return "redirect:/";
		}
		return "redirect:/";
	}
	
	@RequestMapping("changePassword")
	public String changePassword(@ModelAttribute("SignInVO")SignInVO signInVO,HttpServletRequest request,ModelMap modelMap,RedirectAttributes redirectAttributes)
	{
		BoothAdminLogin boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
		if(boothAdminLogin!=null){
			String result=loginService.changePassword(signInVO);
			if(result.equals("success")){
				redirectAttributes.addFlashAttribute("successMessage","Password Changed Successfully");
				return "redirect:changeOldPassword";
			}else if(result.equals("fail")){
				redirectAttributes.addFlashAttribute("errorMessage","Invalid password faild to changed password");
				return "redirect:changeOldPassword";
			}
			}else{
				return "redirect:/";
			}
		return "redirect:/";
	}
	@RequestMapping("changeOldPassword")
	public String changeOldPassword(HttpServletRequest request,ModelMap modelMap)
	{
		BoothAdminLogin boothAdminLogin=(BoothAdminLogin)request.getSession().getAttribute("boothAdminLogin");
		if(boothAdminLogin==null){
			return "redirect:/";
		}else{
			BoothAdminLogin boothAdminLogin1=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
			List<DeviceRegistration> deviceRegistration=boothAdminService.getRegisteredDevice(boothAdminLogin1.getUserId());
			modelMap.addAttribute(ServerConstants.DEVICE_REGISTRATION,deviceRegistration);
			BoothAdminLogin boothAdminLogin2=boothAdminService.getProfileDetails(boothAdminLogin.getUserId());
			modelMap.addAttribute("boothAdminLogin",boothAdminLogin2);
			return "changeOldPassword";
		}
	}
	
	private Date yesterday() {
	    final Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -1);
	    return cal.getTime();
	}
}
