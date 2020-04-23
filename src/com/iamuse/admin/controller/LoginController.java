package com.iamuse.admin.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iamuse.admin.VO.BoothAdminRegistrationRequestVO;
import com.iamuse.admin.VO.ImageFormVO;
import com.iamuse.admin.VO.LoginBaseResponseVO;
import com.iamuse.admin.VO.LoginBoothAdminRegistrationRequestVO;
import com.iamuse.admin.VO.LoginVO;
import com.iamuse.admin.VO.SignInVO;
import com.iamuse.admin.entity.BoothAdminLogin;
import com.iamuse.admin.entity.DeviceRegistration;
import com.iamuse.admin.entity.UploadImage;
import com.iamuse.admin.service.BoothAdminService;
import com.iamuse.admin.service.IamuseDashboardService;
import com.iamuse.admin.service.LoginService;
import com.iamuse.admin.util.MailUtil;
import com.iamuse.admin.util.ZohoContactsSynUtil;
import com.iamuse.admin.validator.LoginFormValidator;
import com.paypal.constants.ServerConstants;

@Controller
public class LoginController {
	private Locale locale = LocaleContextHolder.getLocale();

	@Autowired MessageSource messageSource;
	@Autowired LoginService loginService;
	@Autowired IamuseDashboardService iamuseDashboardService;
	@Autowired LoginFormValidator validator;
	@Autowired BoothAdminService boothAdminService;
	@Autowired MailUtil mailUtil;
	@Autowired LoginBaseResponseVO responseVo;
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
		modelMap.addAttribute("boothAdminLogin2",boothAdminLogin2);
		
		UploadImage uploadImage=boothAdminService.getCurrentImagesClicked(boothAdminLogin.getUserId(),Integer.parseInt(id));
		modelMap.addAttribute("uploadImage",uploadImage);
			return "openimagepage";	
	}

	@RequestMapping(value="/")
	public String signInPage(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)
	{
		//String url=request.getRequestURL().toString() + "?" + request.getQueryString();
		if(request.getQueryString()!=null) {
			 modelMap.addAttribute("errorMessage", "Login to complete the purchase");
			 return "signInPage";
		}
		
		int a=10;
		try {
			a=a/0;
		} catch (Exception e) {
			// TODO: handle exception
			
			System.out.println("Arithmetic exception caught.."+e.getMessage());
		}
		System.out.println("Normal flow execution..");
		return "signInPage";	
	}

	
		
	@RequestMapping(value="/signInAction")
	public String signInAction(@ModelAttribute("SignInVO") SignInVO signInVO,HttpServletRequest request,ModelMap model,RedirectAttributes redirectAttributes)
	{
		  BoothAdminLogin boothAdminLogin=null;
		  try {
			String result = validator.validateBoothLoginForm(signInVO);
			if(("success").equals(result)){
			boothAdminLogin=loginService.isValidUser(signInVO);
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
					}
					else if(boothAdminLogin.getIsDeleted()==true){						
						redirectAttributes.addFlashAttribute("errorMessage", "User Doesn't Exist, Sign Up for a New Account to continue");
						return ServerConstants.REDIRECT_LOGIN_PAGE;
					}
					else if((boothAdmin).equals(boothAdminLogin.getUserRole()) && boothAdminLogin.getLoginTour() == 0 && boothAdminLogin.getSubId()!=1 || boothAdminLogin.getSubId()==1){
						return "redirect:getSubscription";
						
					}else if((boothAdmin).equals(boothAdminLogin.getUserRole()) && boothAdminLogin.getLoginTour() == 1 && boothAdminLogin.getSubId()!=1 || boothAdminLogin.getSubId()==1){
						return "redirect:getSubscribedEventList";
					}
				}else{
					model.addAttribute("errorMessage", "Invalid Email Address Or Password");
					return "signInPage";
				}
				}else if(result.equals("Password id is required.")){
					redirectAttributes.addFlashAttribute("errorMessage", "Password id is required");
					return ServerConstants.REDIRECT_LOGIN_PAGE;
				}else{
					redirectAttributes.addFlashAttribute("errorMessage", "Invalid Email Address Or Password");
				return ServerConstants.REDIRECT_LOGIN_PAGE;
				}
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		redirectAttributes.addFlashAttribute("errorMessage", "User has been deleted");
		return ServerConstants.REDIRECT_LOGIN_PAGE;
	}
	
	@RequestMapping(value="/signInGmail")
	public String signInGmail(@RequestParam("emailId")String emailId,HttpServletRequest request,ModelMap model,RedirectAttributes redirectAttributes)
	{
		
		 BoothAdminLogin boothAdminLogin=null;
		try {
			
			SignInVO signInVO = new SignInVO();
			signInVO.setEmailId(emailId);
			signInVO.setPin("0000");
			signInVO.setUserType("personal");
			String result = validator.validateGmailBoothLoginForm(signInVO);
			if(("success").equals(result)){
			boothAdminLogin=loginService.gMailIsValidUser(signInVO);
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
					}
					else if(boothAdminLogin.getIsDeleted()==true){						
						redirectAttributes.addFlashAttribute("errorMessage", "User Doesn't Exist, Sign Up for a New Account to continue");
						return ServerConstants.REDIRECT_LOGIN_PAGE;
					}
					else if((boothAdmin).equals(boothAdminLogin.getUserRole()) && boothAdminLogin.getLoginTour() == 0 && boothAdminLogin.getSubId()!=1 || boothAdminLogin.getSubId()==1){
						return "redirect:getProfileDetails";
						
					}else if((boothAdmin).equals(boothAdminLogin.getUserRole()) && boothAdminLogin.getLoginTour() == 1 && boothAdminLogin.getSubId()!=1 || boothAdminLogin.getSubId()==1){
						return "redirect:getSubscribedEventList";
					}
				}else{
					model.addAttribute("errorMessage", "Invalid Email Address Or Password");
					return "signInPage";
				}
				}else if(result.equals("Password id is required.")){
					redirectAttributes.addFlashAttribute("errorMessage", "Password id is required");
					return ServerConstants.REDIRECT_LOGIN_PAGE;
				}else{
					redirectAttributes.addFlashAttribute("errorMessage", "Invalid Email Address Or Password");
				return ServerConstants.REDIRECT_LOGIN_PAGE;
				}
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		redirectAttributes.addFlashAttribute("errorMessage", "User has been deleted");
		return ServerConstants.REDIRECT_LOGIN_PAGE;
	}
	
	@RequestMapping(value="/SignOut")
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
			String forgotURL=baseURL+confirmationUrl;
			String result=loginService.forgotPassword(loginVO.getUsername(),token);
			if(result.equals("success")){
				redirectAttributes.addFlashAttribute("successMessage", "Password reset has been emailed to : "+loginVO.getUsername());
				String testText="<html><body>"+
						"<table id=\"m_-5368927744985068358backgroundTable\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"+
						"<tbody><tr><td><table id=\"m_-5368927744985068358innerTable\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"+
						"<tbody><tr><td class=\"m_-5368927744985068358payloadCell\" valign=\"top\"><table style=\"border:1px solid rgb(207,207,207);border-radius:8px;background:rgb(255,255,255)\" border=\"0\" cellspacing=\"0\" width=\"100%\">"+
						"<tbody><tr><td style=\"color:rgb(85,85,85);font-size:14px;font-family:'helvetica neue',arial,serif;padding:30px 10px\" align=\"center\">"+
						"<p style=\"color:rgb(68,68,68);text-align:center;margin:0px;padding:0px\"><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT100_com_zimbra_url\"><a href=\"http://www.iamuse.com\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://www.iamuse.com&amp;source=gmail&amp;ust=1493380684676000&amp;usg=AFQjCNExSsY9fpbaIXKUYmJaDURNeFlELw\"><img alt=\"iAmuse\" longdesc=\"https://ci3.googleusercontent.com/proxy/tSnkDkFiofgBYd5c5rsqAFQE_sTYbRIdlGOTJCekl9GkbR2Yz4vb0tMUMQ=s0-d-e1-ft#http://www.iamuse.com\" height=\"50\" width=\"150\" src=\"http://star-k.eastus.cloudapp.azure.com:8080/iamuse/resources/images/images/iamuse-email-logo.png\" class=\"CToWUd\"></a></span></p>"+
					    "<p style=\"font-weight:600;font-size:16px;text-align:left\">Hi, "+loginVO.getUsername()+ " please use the below link to change your password.</p>"+
					    "<p style=\"font-weight:600;font-size:16px;text-align:left\">URL: <a href="+forgotURL+" target=\"_blank\">"+forgotURL+"</a></p>"+
					    "<p style=\"font-weight:600;font-size:16px;text-align:left\">Sincerely <br>Team iAmuse</p>"+
						"<table border=\"0\" cellpadding=\"5\" cellspacing=\"0\" width=\"100%\">"+
		                "<tbody><tr><td align=\"center\"> <p><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT101_com_zimbra_url\"><a href=\"https://www.facebook.com/iamusebooth\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=https://www.facebook.com/iamusebooth&amp;source=gmail&amp;ust=1493380684677000&amp;usg=AFQjCNFLfToZNO2UisyTB9FWtiPfUFEhcA\"><img alt=\"\" height=\"77\" width=\"78\" src=\"http://star-k.eastus.cloudapp.azure.com:8080/iamuse/resources/images/images/facebookIcon.jpg\" class=\"CToWUd\"></a></span></p><p>Like us on Facebook</p></td>"+
		                "<td align=\"center\"><p><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT102_com_zimbra_url\"><a href=\"http://instagram.com/iamusepics\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://instagram.com/iamusepics&amp;source=gmail&amp;ust=1493380684677000&amp;usg=AFQjCNF5OifpP40I6GUbWMZA7Wq8I0Y4mw\"><img alt=\"\" height=\"76\" width=\"77\" src=\"http://star-k.eastus.cloudapp.azure.com:8080/iamuse/resources/images/images/instagram.png\" class=\"CToWUd\"></a></span></p><p>Follow us on Instagram</p></td>"+
		                "<td align=\"center\"><p><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT103_com_zimbra_url\"><a href=\"http://instagram.com/iamusepics\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://instagram.com/iamusepics&amp;source=gmail&amp;ust=1493380684677000&amp;usg=AFQjCNF5OifpP40I6GUbWMZA7Wq8I0Y4mw\"><img alt=\"\" height=\"77\" width=\"78\" src=\"http://star-k.eastus.cloudapp.azure.com:8080/iamuse/resources/images/images/twitterIcon.jpg\" class=\"CToWUd\"></a></span></p><p>Follow us on Twitter</p></td>"+
		                "</tr></tbody></table>"+
		                "<p>Visit our website <span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT104_com_zimbra_url\"><a href=\"http://www.iamuse.com\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://www.iamuse.com&amp;source=gmail&amp;ust=1493380684677000&amp;usg=AFQjCNE_v-p9Y1LQV-DpIv5GqwYEJDT-rQ\">www.iamuse.com</a></span></p>"+
		                "</td></tr></tbody></table></td></tr><tr>"+
		                "<td class=\"m_-5368927744985068358payloadCell\" style=\"height:40px;font-size:9px;font-family:'helvetica neue',arial,serif;color:rgb(136,136,136)\" align=\"right\" valign=\"top\"><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT105_com_zimbra_url\"><a style=\"color:rgb(136,136,136)\" href=\"http://iamuse.com\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://iamuse.com&amp;source=gmail&amp;ust=1493380684677000&amp;usg=AFQjCNHuUfOsnIEfdwOnnQQ9sl7Ljgn9ZA\">powered by iAmuse.com</a></span></td>"+
		                "</tr></tbody></table></td></tr></tbody></table></body></html>";
				mailUtil.sendEmailByInfo("IAmuse <info@iamuse.com>", loginVO.getUsername(),testText ,"Forgot Password URL");
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
				String emailbody = boothAdminLogin.getUsername();
								
				String testText="<html><body>"+
						"<table id=\"m_-5368927744985068358backgroundTable\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"+
						"<tbody><tr><td><table id=\"m_-5368927744985068358innerTable\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"+
						"<tbody><tr><td class=\"m_-5368927744985068358payloadCell\" valign=\"top\"><table style=\"border:1px solid rgb(207,207,207);border-radius:8px;background:rgb(255,255,255)\" border=\"0\" cellspacing=\"0\" width=\"100%\">"+
						"<tbody><tr><td style=\"color:rgb(85,85,85);font-size:14px;font-family:'helvetica neue',arial,serif;padding:30px 10px\" align=\"center\">"+
		                "<h1 style=\"color:rgb(68,68,68);text-align:center;margin:0px;padding:0px\">Welcome to IAmuse</h1>"+
						"<p style=\"color:rgb(68,68,68);text-align:center;margin:0px;padding:0px\"><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT100_com_zimbra_url\"><a href=\"http://www.iamuse.com\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://www.iamuse.com&amp;source=gmail&amp;ust=1493380684676000&amp;usg=AFQjCNExSsY9fpbaIXKUYmJaDURNeFlELw\"><img alt=\"iAmuse\" longdesc=\"https://ci3.googleusercontent.com/proxy/tSnkDkFiofgBYd5c5rsqAFQE_sTYbRIdlGOTJCekl9GkbR2Yz4vb0tMUMQ=s0-d-e1-ft#http://www.iamuse.com\" height=\"50\" width=\"150\" src=\"http://star-k.eastus.cloudapp.azure.com:8080/iamuse/resources/images/images/iamuse-email-logo.png\" class=\"CToWUd\"></a></span></p>"+
					    "<p style=\"font-weight:600;font-size:16px;text-align:left\">Hi.. "+emailbody+",</p>"+
						"<p style=\"font-weight:400;font-size:16px;text-align:left\">Your password has been reset successfully.</p>"+
					    "<p style=\"font-weight:400;font-size:16px;text-align:left\">We hope you enjoy iAmuse PhotoBooth<br>Happy iAmusing!</p>"+
					    "<p style=\"font-weight:600;font-size:16px;text-align:left\">Sincerely <br>Team iAmuse</p>"+
						"<table border=\"0\" cellpadding=\"5\" cellspacing=\"0\" width=\"100%\">"+
		                "<tbody><tr><td align=\"center\"> <p><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT101_com_zimbra_url\"><a href=\"https://www.facebook.com/iamusebooth\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=https://www.facebook.com/iamusebooth&amp;source=gmail&amp;ust=1493380684677000&amp;usg=AFQjCNFLfToZNO2UisyTB9FWtiPfUFEhcA\"><img alt=\"\" height=\"77\" width=\"78\" src=\"http://star-k.eastus.cloudapp.azure.com:8080/iamuse/resources/images/images/facebookIcon.jpg\" class=\"CToWUd\"></a></span></p><p>Like us on Facebook</p></td>"+
		                "<td align=\"center\"><p><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT102_com_zimbra_url\"><a href=\"http://instagram.com/iamusepics\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://instagram.com/iamusepics&amp;source=gmail&amp;ust=1493380684677000&amp;usg=AFQjCNF5OifpP40I6GUbWMZA7Wq8I0Y4mw\"><img alt=\"\" height=\"76\" width=\"77\" src=\"http://star-k.eastus.cloudapp.azure.com:8080/iamuse/resources/images/images/instagram.png\" class=\"CToWUd\"></a></span></p><p>Follow us on Instagram</p></td>"+
		                "<td align=\"center\"><p><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT103_com_zimbra_url\"><a href=\"http://instagram.com/iamusepics\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://instagram.com/iamusepics&amp;source=gmail&amp;ust=1493380684677000&amp;usg=AFQjCNF5OifpP40I6GUbWMZA7Wq8I0Y4mw\"><img alt=\"\" height=\"77\" width=\"78\" src=\"http://star-k.eastus.cloudapp.azure.com:8080/iamuse/resources/images/images/twitterIcon.jpg\" class=\"CToWUd\"></a></span></p><p>Follow us on Twitter</p></td>"+
		                "</tr></tbody></table>"+
		                "<p>Visit our website <span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT104_com_zimbra_url\"><a href=\"http://www.iamuse.com\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://www.iamuse.com&amp;source=gmail&amp;ust=1493380684677000&amp;usg=AFQjCNE_v-p9Y1LQV-DpIv5GqwYEJDT-rQ\">www.iamuse.com</a></span></p>"+
		                "</td></tr></tbody></table></td></tr><tr>"+
		                "<td class=\"m_-5368927744985068358payloadCell\" style=\"height:40px;font-size:9px;font-family:'helvetica neue',arial,serif;color:rgb(136,136,136)\" align=\"right\" valign=\"top\"><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT105_com_zimbra_url\"><a style=\"color:rgb(136,136,136)\" href=\"http://iamuse.com\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://iamuse.com&amp;source=gmail&amp;ust=1493380684677000&amp;usg=AFQjCNHuUfOsnIEfdwOnnQQ9sl7Ljgn9ZA\">powered by iAmuse.com</a></span></td>"+
		                "</tr></tbody></table></td></tr></tbody></table></body></html>";
				mailUtil.sendEmailByInfo("IAMUSE <dev@iamuse.com>",boothAdminLogin.getEmailId(),testText,"Change of Password");
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
			modelMap.addAttribute("boothAdminLogin2",boothAdminLogin2);
			return "changeOldPassword";
		}
	}
	
	@RequestMapping(value = "/signInGmail", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody LoginBaseResponseVO signInGmail(
			@RequestBody LoginBoothAdminRegistrationRequestVO loginRegistrationRequestVO,HttpServletRequest request) throws ClientProtocolException, IOException {
				
		LoginBaseResponseVO loginBaseResponseVO = new LoginBaseResponseVO();
		BoothAdminRegistrationRequestVO adminBoothRegistrationRequestVO = new BoothAdminRegistrationRequestVO();
		adminBoothRegistrationRequestVO.setUserName(loginRegistrationRequestVO.getUsername());
		adminBoothRegistrationRequestVO.setEmailId(loginRegistrationRequestVO.getEmailId());
		adminBoothRegistrationRequestVO.setToken(loginRegistrationRequestVO.getAccesstoken());
		if(loginRegistrationRequestVO.getEmailId() != null && loginRegistrationRequestVO.getEmailId() != "")
		{
		loginBaseResponseVO = loginService.fetchGmailLoginBaseResponseVO(loginRegistrationRequestVO);
		System.out.println("loginBaseResponseVO="+loginBaseResponseVO);
		} 
		if(Integer.parseInt(loginBaseResponseVO.getResponseCode()) == 0)
		{
			responseVo = loginService.saveGmailBoothRegistration(adminBoothRegistrationRequestVO);
			responseVo.setResponseCode("1");
			responseVo.setResponseDescription("Registered Successfully");
			ZohoContactsSynUtil.zohoContactSync(adminBoothRegistrationRequestVO.getEmailId());
		} else {
			responseVo = loginService.saveGmailBoothRegistration(adminBoothRegistrationRequestVO);
			responseVo.setResponseCode("1");
			responseVo.setResponseDescription("This EmailId is already Registered");
		}
		return responseVo;
	}
}
