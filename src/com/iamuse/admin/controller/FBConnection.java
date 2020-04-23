package com.iamuse.admin.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iamuse.admin.VO.ImageFormVO;
import com.iamuse.admin.VO.SignInVO;
import com.iamuse.admin.entity.BoothAdminLogin;
import com.iamuse.admin.service.IamuseDashboardService;
import com.iamuse.admin.service.LoginService;
import com.paypal.constants.ServerConstants;



@Controller
public class FBConnection {
	private String code="";
	String superAdmin="superadmin";
	String boothAdmin="boothadmin";
	

	public static final String FB_APP_ID = "278102269337484";
	public static final String FB_APP_SECRET = "13a1f7b7b2b3d0b8bdfde81aaed967f5";
	public static final String REDIRECT_URI = "http://localhost:8080/iamuse/fbhome";
	@Autowired LoginService loginService;
	@Autowired IamuseDashboardService iamuseDashboardService;
	//@Autowired BhtrAdminService bhtrAdminService;
	
	
	@RequestMapping(value="/fbhome",method=RequestMethod.GET)
	public String display(HttpServletRequest request, HttpServletResponse response,ModelMap model,RedirectAttributes redirectAttributes) throws ServletException, IOException
	{
		BoothAdminLogin boothAdminLogin=null;
		SignInVO signInVO=new SignInVO();
	
		code = request.getParameter("code");
		if (code == null || code.equals("")) {
			throw new RuntimeException("ERROR: Didn't get code parameter in callback.");
		}
		BoothAdminLogin userMaster=loginService.setFbDetails(code);
		 if(userMaster !=null){
			 	signInVO.setFacebookUrl(userMaster.getFacebookUrl());
			 	signInVO.setTwitterUrl(userMaster.getTwitterUrl());
				boothAdminLogin=loginService.isValidUserSocial(signInVO);
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
						}else if((boothAdmin).equals(boothAdminLogin.getUserRole()) && boothAdminLogin.getLoginTour() == 0 && boothAdminLogin.getSubId()!=1 || boothAdminLogin.getSubId()==1){
							return "redirect:getSubscription";
						}else if((boothAdmin).equals(boothAdminLogin.getUserRole()) && boothAdminLogin.getLoginTour() == 1 && boothAdminLogin.getSubId()!=1 || boothAdminLogin.getSubId()==1){
							return "redirect:getSubscribedEventList";
						}
					}
					
		 }
		 model.addAttribute("errorMessage", "Invalid Email Address Or Password");
		return "signInPage";
		
	}

	static String accessToken = "";

	public String getFBAuthUrl() {
		String fbLoginUrl = "";
		try {
			fbLoginUrl = "http://www.facebook.com/dialog/oauth?" + "client_id="
					+ FBConnection.FB_APP_ID + "&redirect_uri="
					+ URLEncoder.encode(FBConnection.REDIRECT_URI, "UTF-8")
					+ "&scope=email,public_profile";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fbLoginUrl;
	}

	public String getFBGraphUrl(String code) {
		String fbGraphUrl = "";
		try {
			fbGraphUrl = "https://graph.facebook.com/oauth/access_token?"
					+ "client_id=" + FBConnection.FB_APP_ID + "&redirect_uri="
					+ URLEncoder.encode(FBConnection.REDIRECT_URI, "UTF-8")
					+ "&client_secret=" + FB_APP_SECRET + "&code=" + code;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fbGraphUrl;
	}

	public String getAccessToken(String code) throws JSONException {
		if ("".equals(accessToken)) {
			URL fbGraphURL;
			try {
				fbGraphURL = new URL(getFBGraphUrl(code));

			} catch (MalformedURLException e) {
				e.printStackTrace();
				throw new RuntimeException("Invalid code received " + e);
			}
			URLConnection fbConnection;
			StringBuffer b = null;
			try {
				fbConnection = fbGraphURL.openConnection();
				BufferedReader in = new BufferedReader(new InputStreamReader(fbConnection.getInputStream()));
				String inputLine;
				b = new StringBuffer();
				while ((inputLine = in.readLine()) != null)
					b.append(inputLine + "\n");
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Unable to connect with Facebook "
						+ e);
			}
			JSONObject jsonObject = new JSONObject(b.toString());
			String access_token = jsonObject.getString("access_token");
			accessToken = access_token;
			if (accessToken.startsWith("{")) {
				throw new RuntimeException("ERROR: Access Token Invalid: "
						+ accessToken);
			}
		}
		return accessToken;
	}
}
