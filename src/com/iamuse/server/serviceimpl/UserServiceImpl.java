package com.iamuse.server.serviceimpl;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.runners.model.TestClass;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.amuse.server.dao.TokenClsss;
import com.amuse.server.dao.UserDao;
import com.iamuse.server.daoimpl.UserDaoImpl;
import com.iamuse.server.entity.AccessToken;
import com.iamuse.server.entity.Adminboothevent;
import com.iamuse.server.entity.BoothAdminLogin;
import com.iamuse.server.entity.BoothUploadImageEmail;
import com.iamuse.server.entity.DeviceIp;
import com.iamuse.server.entity.DeviceRegistration;
import com.iamuse.server.entity.Fovbyuser;
import com.iamuse.server.entity.SubscriptionMaster;
import com.iamuse.server.entity.TransactionMaster;
import com.iamuse.server.entity.UpdateSubscriptionReq;
import com.iamuse.server.entity.UploadImage;
import com.iamuse.server.requestVO.BaseRequestVO;
import com.iamuse.server.requestVO.BoothAdminRegistrationRequestVO;
import com.iamuse.server.requestVO.CrashLogsRequestVO;
import com.iamuse.server.requestVO.DeviceIPRequestVO;
import com.iamuse.server.requestVO.DeviceRegistrationRequestVO;
import com.iamuse.server.requestVO.DeviceTokenRequestVO;
import com.iamuse.server.requestVO.FetchingEventListRequestVO;
import com.iamuse.server.requestVO.FileVO;
import com.iamuse.server.requestVO.IOSTranscationsDetailsRequestVO;
import com.iamuse.server.requestVO.ImageVO;
import com.iamuse.server.requestVO.LoginBoothAdminRegistrationRequestVO;
import com.iamuse.server.requestVO.RGBValueRequestVO;
import com.iamuse.server.requestVO.RestartVO;
import com.iamuse.server.requestVO.SubscriptionRequestVO;
import com.iamuse.server.requestVO.UploadImageRequestVO;
import com.iamuse.server.requestVO.UploadImageWithEmailRequestVO;
import com.iamuse.server.responseVO.AppleReceiptVerifyResponse;
import com.iamuse.server.responseVO.BaseResponseVO;
import com.iamuse.server.responseVO.EventFetchingBaseResponseVO;
import com.iamuse.server.responseVO.LoginBaseResponseVO;
import com.iamuse.server.responseVO.SubscriptionMasterResponseVO;
import com.iamuse.server.service.UserService;
import com.iamuse.server.util.DateUtils;
import com.iamuse.server.util.IAmuseUtil;
import com.iamuse.server.util.MailUtil;
import com.iamuse.server.util.PushNotificationTaskRestart;
import com.iamuse.server.util.ZohoMarketingAddLeadToList;


@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private MessageSource messageSource;
	
	private Locale locale = LocaleContextHolder.getLocale();
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MailUtil mailUtil;
	
	
	@Autowired
	SessionFactory sessionFactory;
	
	
public int uploadImage(UploadImageRequestVO uploadImageRequestVO,String path,HttpServletRequest request) {
		
		boolean result= false;
		String url =null;
		
		String image = uploadImageRequestVO.getImages();
			String userId=uploadImageRequestVO.getUserId();
			
			Integer imageId=userDao.uploadImage(path);
			
//			String rootPath = System.getProperty("catalina.home");
			//String rootPath = request.getRealPath("../");
			
			String rootPath = null;
			try {
			 rootPath = new java.io.File(request.getSession().getServletContext().getRealPath("")+"/..").getCanonicalPath();
			} catch (java.io.IOException e) {
			 e.printStackTrace();
			}
			if(imageId!=0)
			{
				File file=new File(rootPath+path+"/"+uploadImageRequestVO.getUserId());
			if (!file.exists()) {
				file.mkdir();
			}
			System.out.println("file.getAbsolutePath() \t"+file.getAbsolutePath());
			url= IAmuseUtil.writeFile(image, file.getAbsolutePath(), imageId,"jpg");
			userDao.updateImageName(imageId,path,userId);
			return imageId;
			}
			
		
return imageId;
}

public BoothAdminLogin getDefaultRGBValue(RGBValueRequestVO rbRgbValueRequestVO){
	
	BoothAdminLogin boothAdminLogin=userDao.getDefaultRGBValue(rbRgbValueRequestVO);

return boothAdminLogin;
}
public boolean saveDeviceToken(DeviceTokenRequestVO deviceTokenRequestVO) {
	boolean result= false;
    result= userDao.saveDeviceToken(deviceTokenRequestVO);
	return result;
}

	@Override
	public void removeAccessToken(TokenClsss token)
	{
		userDao.removeAccessToken(token);
	}

	
		/*
		 * sessionFactory.getCurrentSession().beginTransaction(); Criteria criteria =
		 * sessionFactory.getCurrentSession().createCriteria(AccessToken.class); token
		 * =(TokenClsss) criteria.uniqueResult(); token.setAccess_token("access_token");
		 * token.setRefresh_token("refresh_token"); userDao.addDataAccessToken(token);
		 */
			
		/*
		 * sessionFactory.getCurrentSession().beginTransaction(); Criteria criteria =
		 * sessionFactory.getCurrentSession().createCriteria(TokenClsss.class);
		 * TokenClsss access =(TokenClsss) criteria.uniqueResult();
		 */
			//access.setAccess_token("Access_token");
		//	access.setAccess_token("access_token");
			//access.getAccess_token();
		//	access.setAccess_token(token.getAccess_token());
		//	access.setRefresh_token(token.getRefresh_token());
	  
	 
public UploadImage getImageDetails(int imageId){
	UploadImage uploadImage=userDao.getImageDetails(imageId);
	return uploadImage;
}

//public int uploadImageWithEmailId(UploadImageWithEmailRequestVO uploadImageWithEmailRequestVO,String path) {
//	boolean result= false;
//	String url =null;
//	int i=0;
//	String name=null;
//	String rootPath = System.getProperty("catalina.home");
//	String userId=uploadImageWithEmailRequestVO.getUserId();
//	Integer imageId=userDao.uploadImageWithEmailId("/IAmuseimages/EmailImages",uploadImageWithEmailRequestVO);
//    List<String> imagesNames = new ArrayList<String>();
//    if (imageId != 0) {
//    	List<ImageVO> images = uploadImageWithEmailRequestVO.getImages();
//		for (ImageVO image : images)
//		{
//			i++;
//			if (null != image){
//				 url= IAmuseUtil.writeFile(image.getImage(), path+"/"+imageId, i,"jpg");
//				// File file = new File(url+"/"+i+".jpg");
//				   byte[] bytes;
//						//log.info(bytes.length);
//					//	if (bytes.length != 0) {
//							try {
//								bytes = scale(100,100,url+"/"+i+".jpg");
//								IAmuseUtil.writeFile(bytes, rootPath+"/IAmuseimages/EmailImagesThumbnail/"+imageId, ""+i,"jpg");
//							} catch (ApplicationException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//					//}
//				if (i == 1) {
//					 name= i + ".jpg";
//				} else {
//					name=name+ ","+  i + ".jpg";
//			}
//		}
//		}	
//		result=userDao.updateImageNameForEmailId(imageId,name,userId);
//		if(result==true)
//		{
//		try{
//				mailUtil.sendEmail("IAMUSE <harsh@star-knowledge.org>",uploadImageWithEmailRequestVO.getEmailId().trim(),path,"Your Picture Is Ready","IAMUSE"+imageId+".jpg",url,i,true);
//				userDao.updateEmailSendTime(imageId,userId);
//				imageId=5;
//			}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//		}
//		}
//		return imageId;
	
	

public int uploadImageWithEmailId(UploadImageWithEmailRequestVO uploadImageWithEmailRequestVO,String path,HttpServletRequest request) {	
	Integer result= 0;
	String url =null;
	int i=0;
	String name=null;
	String imageName = null;
	String emailBody="";
	String rootPath = null;
	RestTemplate restTemplate = new RestTemplate();
	BoothUploadImageEmail uploadImageEmail = new BoothUploadImageEmail();
	int str= uploadImageWithEmailRequestVO.getNewsletterOptIn();
	//uploadImageEmail.setNewsletterOptIn("" + uploadImageWithEmailRequestVO.getNewsletterOptIn());
	if(str==1)
	{
	 try {
			ObjectMapper oMapper = new ObjectMapper();
			HttpClient client = new DefaultHttpClient();
			//String productImaUse="https://campaigns.zoho.com/api/addlistsubscribersinbulk?scope=CampaignsAPI&resfmt=JSON&authtoken=0bc02027f7248fe3f8d3bebe37c04dac&listkey=d1ec5d9ab01c9c0785d2cdd03b868aa68060bef678df20a0&emailids="+uploadImageWithEmailRequestVO.getEmailId();
			String productImaUse="https://campaigns.zoho.com/api/addlistsubscribersinbulk?scope=CampaignsAPI&resfmt=JSON&authtoken=0bc02027f7248fe3f8d3bebe37c04dac&listkey=d1ec5d9ab01c9c07011e644c02d82e148060bef678df20a0&emailids="+uploadImageWithEmailRequestVO.getEmailId();
			//String productImaUse="https://subscriptions.zoho.com/api/v1/products";
	        HttpGet getProductRequest=createHttpGet(productImaUse);
	        HttpResponse getIamUseProductResponse =client.execute(getProductRequest);
	        ZohoMarketingAddLeadToList.zohoMarketingHumAddLeadUploadImage(uploadImageWithEmailRequestVO.getEmailId());
	        BufferedReader rd = new BufferedReader(new InputStreamReader(getIamUseProductResponse.getEntity().getContent()));
	        String line = "";
	        while ((line = rd.readLine()) != null) {
	       	  JSONParser j = new JSONParser();
	             JSONObject o = (JSONObject)j.parse(line);
	             System.out.println("Call api Json:::"+o);
	             if(getIamUseProductResponse.getStatusLine().getStatusCode()==200) {           
	             }
	       }
	        
	 }
		catch(Exception e) {
			e.printStackTrace();
		}
	
	}
 
	 
	try {
	 rootPath = new java.io.File(request.getSession().getServletContext().getRealPath("")+"/..").getCanonicalPath();
	} catch (java.io.IOException e) {
	 e.printStackTrace();
	}
	String userId=uploadImageWithEmailRequestVO.getUserId();
	
    List<String> imagesNames = new ArrayList<String>();
    	List<ImageVO> images = uploadImageWithEmailRequestVO.getImages();
		Integer eventId=images.get(0).getEventId();
		int l=0;
		for (ImageVO image : images)
		{
			String k= IAmuseUtil.generateImageName();
			i++;
			int j=0;
			if (null != image){
				 url= IAmuseUtil.writeFileImageUpload(image.getImage(), path+"/"+uploadImageWithEmailRequestVO.getImages().get(j).getEventId(), k,"jpg");
				   byte[] bytes;
							try {
								bytes = scale(319,196,url+"/"+k+".jpg");
								IAmuseUtil.writeFile(bytes, rootPath+"/IAmuseimages/EmailImagesThumbnail/"+uploadImageWithEmailRequestVO.getImages().get(j).getEventId(), ""+k,"jpg");
							} catch (ApplicationException e) {
								e.printStackTrace();
							}
							name= k + ".jpg";
							result=userDao.updateImageNameForEmailId(name,userId,image.getEventId(),"/IAmuseimages/EmailImages",uploadImageWithEmailRequestVO,image.getDefaultId(),image.getPicId());
							System.out.println("Result="+result);
		}					
		if(result!=0)
		{
			if(l==0){
					imageName=k+".jpg";
			}else{
				imageName=imageName+","+k+".jpg";
			}
		}
j=j+1;
l++;
		}
try{
		Adminboothevent adminboothevent=userDao.getAdminBoothEvent(Integer.parseInt(uploadImageWithEmailRequestVO.getUserId()),eventId);
		if(("null").equals(adminboothevent.getEmailBody()) || ("").equals(adminboothevent.getEmailBody())){
			emailBody="Thank you for Picture(s) with us! Here are the picture(s) to keep as a memory of the event. We hope you had fun!";
		}else{
			emailBody=adminboothevent.getEmailBody();
		}
		String testText="<html><body>"+
				"<table id=\"m_-5368927744985068358backgroundTable\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"+
				"<tbody><tr><td><table id=\"m_-5368927744985068358innerTable\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"+
				"<tbody><tr><td class=\"m_-5368927744985068358payloadCell\" valign=\"top\"><table style=\"border:1px solid rgb(207,207,207);border-radius:8px;background:rgb(255,255,255)\" border=\"0\" cellspacing=\"0\" width=\"100%\">"+
				"<tbody><tr><td style=\"color:rgb(85,85,85);font-size:14px;font-family:'helvetica neue',arial,serif;padding:30px 10px\" align=\"center\">"+
				"<h3 style=\"color:rgb(68,68,68);text-align:center;margin:0px;padding:0px\">Your photo booth picture from</h3>"+
				"<h1 style=\"font-weight:600;text-align:center;\">"+adminboothevent.getEventName()+"</h1>"+
				//"<p style=\"color:rgb(68,68,68);text-align:center;margin:0px;padding:0px\"><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT100_com_zimbra_url\"><a href=\"http://www.iamuse.com\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://www.iamuse.com&amp;source=gmail&amp;ust=1493380684676000&amp;usg=AFQjCNExSsY9fpbaIXKUYmJaDURNeFlELw\"><img alt=\"iAmuse\" longdesc=\"https://ci3.googleusercontent.com/proxy/tSnkDkFiofgBYd5c5rsqAFQE_sTYbRIdlGOTJCekl9GkbR2Yz4vb0tMUMQ=s0-d-e1-ft#http://www.iamuse.com\" height=\"50\" width=\"150\" src=\"http://star-k.eastus.cloudapp.azure.com:8000/IAmuse/resources/images/images/iamuse-email-logo.png\" class=\"CToWUd\"></a></span></p>"+
			    "<p style=\"font-weight:600;font-size:16px;text-align:center;\">"+emailBody+"</p>"+
			    "<p style=\"font-size:15px;text-align:left;\"><span style=\"font-weight:bold;margin-right:3px;\">Facebook :</span><a href=\""+adminboothevent.getFacebook()+"\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://adminboothevent.getFacebook();&amp;source=gmail&amp;ust=1493380684676000&amp;usg=AFQjCNGumqOxdBAoecShS9vqcuckJavPCA\">"+adminboothevent.getFacebook()+"</a>"+
			   "</p><p style=\"font-size:15px;text-align:left;\"><span style=\"font-weight:bold;margin-right:3px\">Twitter :</span><a href=\""+adminboothevent.getTwitter()+"\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://adminboothevent.getTwitter();&amp;source=gmail&amp;ust=1493380684676000&amp;usg=AFQjCNFDLaMsnpw0j20w_N4xRU_loYQW4Q\">"+adminboothevent.getTwitter()+"</a></p>"+
			    //"<p style=\"font-size:15px\"><span style=\"font-weight:bold;margin-right:3px\">Event Date :</span> "+adminboothevent.getEventStart()+"</p><p style=\"font-size:15px\"><span style=\"font-weight:bold;margin-right:3px\">Event Host's email address :</span> "+adminboothevent.getEventHostMailerId()+"</p>"+
			   // "<p style=\"font-size:15px\"><span style=\"font-weight:bold;margin-right:3px\">Facebook :</span><a href=\""+adminboothevent.getFacebook()+"\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://adminboothevent.getFacebook();&amp;source=gmail&amp;ust=1493380684676000&amp;usg=AFQjCNGumqOxdBAoecShS9vqcuckJavPCA\">"+adminboothevent.getFacebook()+"</a>"+
			    //"</p><p style=\"font-size:15px;margin-bottom:35px\"><span style=\"font-weight:bold;margin-right:3px\">Twitter :</span><a href=\""+adminboothevent.getTwitter()+"\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://adminboothevent.getTwitter();&amp;source=gmail&amp;ust=1493380684676000&amp;usg=AFQjCNFDLaMsnpw0j20w_N4xRU_loYQW4Q\">"+adminboothevent.getTwitter()+"</a></p>"+
			    "<p style=\"font-size:15px;margin-top:0px;text-align:left;\"><span style=\"font-weight:bold;margin-right:3px\">Event Host:</span> "+adminboothevent.getSponsorName()+" |&nbsp;"+adminboothevent.getEventHostMailerId()+"</p>"+
			    "<hr>"+
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
               //"<p style=\"text-align:center;\">List Description comes here.</p>"+
               //"<p>Our&nbsp<a href=\"\" target=\"_blank\" rel=\"nofollow\">Privacy Policy</a>&nbspand&nbsp<a href=\"#\" target=\"_blank\" rel=\"nofollow\">Terms of use.</a></p>"+
               "<p style=\"font-size:11px;font-family:Arial;\">Our <a style='color:#0000FF;font-size:11px;font-family:Arial;'target=\"_blank\" href=\"https://www.iamuse.com/privacy-policy\"><span style='color:#0000FF;font-size:11px;font-family:Arial;'>Privacy Policy</span></a> and <a style='color:#0000FF;font-size:11px;font-family:Arial;' target=\"_blank\" href=\"https://www.iamuse.com/terms-conditions\"><span style='color:#0000FF;font-size:11px;font-family:Arial;'>Terms of Use</span></a></p>"+
               //"<p>Visit our website <span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT104_com_zimbra_url\"><a href=\"http://www.iamuse.com\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://www.iamuse.com&amp;source=gmail&amp;ust=1493380684677000&amp;usg=AFQjCNE_v-p9Y1LQV-DpIv5GqwYEJDT-rQ\">www.iamuse.com</a></span></p>"+
                "</td></tr></tbody></table></td></tr><tr>"+
               // "<td class=\"m_-5368927744985068358payloadCell\" style=\"height:40px;font-size:9px;font-family:'helvetica neue',arial,serif;color:rgb(136,136,136)\" align=\"right\" valign=\"top\"><span class=\"m_-5368927744985068358Object\" role=\"link\" id=\"m_-5368927744985068358OBJ_PREFIX_DWT105_com_zimbra_url\"><a style=\"color:rgb(136,136,136)\" href=\"http://iamuse.com\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://iamuse.com&amp;source=gmail&amp;ust=1493380684677000&amp;usg=AFQjCNHuUfOsnIEfdwOnnQQ9sl7Ljgn9ZA\">powered by iAmuse.com</a></span></td>"+
                "</tr></tbody></table></td></tr></tbody></table></body></html>";
		
		 /*String testText="\n\n\t"+emailBody+
					        "\n\n\t Event Host Name : " +adminboothevent.getEventName()+
					        "\n\n\t Event Date : " +  adminboothevent.getEventStart()+
					        "\n\n\t Event Host's email address : "+adminboothevent.getEventHostMailerId()+
					        "\n\t Others" +"\n"+
					        "\n\n\t Facebook : " +adminboothevent.getFacebook() +
					        "\n\n\t Twitter : " +adminboothevent.getTwitter()  ;*/
		   	String mailerHostAddress=adminboothevent.getEventHostMailerId();
		    mailUtil.sendEmailUploadMail(adminboothevent.getSponsorName()+"("+mailerHostAddress+")"+" <dev@iamuse.com>",uploadImageWithEmailRequestVO.getEmailId().trim(),path,"Your Picture Is Ready","IAMUSE.jpg",url,imageName,true,testText);
		    userDao.updateEmailSendTime(imageName,userId);
		    userDao.updateStatusCount(uploadImageWithEmailRequestVO.getEmailId().trim(),eventId,Integer.parseInt(uploadImageWithEmailRequestVO.getUserId()));
		    
		   
			result=5;
		}catch(Exception e){
			e.printStackTrace();
		}
		//}
	return result;
}
public int crashlogsupload(CrashLogsRequestVO crashLogsRequestVO, String path,HttpServletRequest request) {
	String url=null;
	String name=null;
	Integer id = userDao.crashlogsupload(path);
	String rootPath = System.getProperty("catalina.home");
	//String rootPath = request.getRealPath("../");
	if (id != 0) {
		List<FileVO> files = crashLogsRequestVO.getFiles();
		int i = 0;
		for (FileVO file : files)
		{
			i++;
			if (null != file){
				
				 url= IAmuseUtil.writeFile(file.getFile(), rootPath+path+"/"+id, i,"crash");
				if (i == 1) {
					 name= i + ".crash";
				} else {
					name=name+ ","+  i + ".crash";
			}
				
		}
		}	
		String userId = crashLogsRequestVO.getUserId();
		userDao.crashlogsuploadName(id, name, userId);
		return id;
		
	}
	return id;
}

public boolean saveDeviceIP(DeviceIPRequestVO deviceIPRequestVO) {
	boolean result= false;
    result= userDao.saveDeviceIP(deviceIPRequestVO);
	return result;
}

public DeviceIp getDeviceIP(DeviceIPRequestVO deviceIPRequestVO) {
	DeviceIp deviceIp=userDao.getDeviceIP(deviceIPRequestVO);
	return deviceIp;
}

public byte[] scale(int width, int height, String imageUrl) throws ApplicationException {
	   //  ByteArrayInputStream in = new ByteArrayInputStream(bytes);
	     try {
	    	 File file = new File(imageUrl);
	    	 FileInputStream fis = new FileInputStream(file); 
	      BufferedImage img = ImageIO.read(fis);
	     System.out.println("fwfew");
	      BufferedImage imageBuff = Scalr.resize(img, Method.QUALITY,Mode.AUTOMATIC, width,height, Scalr.OP_ANTIALIAS);
	      ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	   //   log.info("scallling........."+imageBuff);
	      ImageIO.write(imageBuff, "png", buffer);

	      return buffer.toByteArray();
	     } catch (IOException e) {
	      throw new ApplicationException("IOException in scale", null);
	     }
	    }
@Override
public LoginBaseResponseVO saveAdminBoothRegistration(BoothAdminRegistrationRequestVO adminBoothRegistrationRequestVO) {
	return userDao.saveAdminBoothRegistration(adminBoothRegistrationRequestVO);
}

@Override
public LoginBaseResponseVO saveGmailBoothRegistration(BoothAdminRegistrationRequestVO adminBoothRegistrationRequestVO) {
	return userDao.saveGmailBoothRegistration(adminBoothRegistrationRequestVO);
}

@Override
public LoginBaseResponseVO fetchLoginBaseResponseVO(
		LoginBoothAdminRegistrationRequestVO loginRegistrationRequestVO) {
	return userDao.fetchLoginBaseResponseVO(loginRegistrationRequestVO);
}

@Override
public LoginBaseResponseVO fetchGmailLoginBaseResponseVO(
		LoginBoothAdminRegistrationRequestVO loginRegistrationRequestVO) {
	return userDao.fetchGmailLoginBaseResponseVO(loginRegistrationRequestVO);
}


@Override
public EventFetchingBaseResponseVO fetchEventFetchingAdminBooth(FetchingEventListRequestVO fetchinfEventAdminBoothRequestVo) {
	return userDao.fetchEventFetchingAdminBooth(fetchinfEventAdminBoothRequestVo);
}

@Override
public String deviceRegisterSevice(DeviceRegistrationRequestVO deviceRegistrationRequestVO) {
	return userDao.deviceRegisterSevice(deviceRegistrationRequestVO);
}

@Override 
public boolean addDataAccessToken(TokenClsss token) {
	
	 boolean result= false;	
	 result =userDao.addDataAccessToken(token);
	 return true;
}

@Override 
public BoothAdminLogin updateSubscriptionFormAdmin(UpdateSubscriptionReq updateSubscriptionReq) 
{
	  return userDao.updateSubsctiptionForAdmin(updateSubscriptionReq.getUserid(),Integer.parseInt(updateSubscriptionReq.getSubId())); 
}

@Override 
public BoothAdminLogin updateSubscriptionPlan(UpdateSubscriptionReq updateSubscriptionReq,String paymentAmount,Integer transactionId) 
{
	  return userDao.updateSubscriptionPlan(updateSubscriptionReq.getUserid(),Integer.parseInt(updateSubscriptionReq.getSubId()),
			  paymentAmount,transactionId); 
}

@Override
public SubscriptionMasterResponseVO fetchSubscriptionsMasterList(SubscriptionRequestVO subscription) {
	SubscriptionMaster masterList=userDao.fetchSubscriptionsMasterList(subscription);
	SubscriptionMasterResponseVO responseVO=new SubscriptionMasterResponseVO();
	if (masterList!=null) {
	responseVO.setCreatedDate(DateUtils.timeStampConvertIntoStringDateFormat(masterList.getCreatedDate()));
	
	if(masterList.getCreatedUserId()!=null){
	responseVO.setCreatedUserId(masterList.getCreatedUserId());
	}
	
	responseVO.setStatus(masterList.getStatus());
	responseVO.setSubId(masterList.getSubId());
	responseVO.setSubName(masterList.getSubName());
	responseVO.setSubPrice(masterList.getSubPrice());
	responseVO.setSubValidaityDayPeriod(masterList.getSubValidaityDayPeriod());
	if(masterList.getUpdatedByUserId()!=null){
	responseVO.setUpdatedByUserId(masterList.getUpdatedByUserId());
	}
	if(masterList.getUpdatedDate()!=null){
	responseVO.setUpdatedDate(DateUtils.timeStampConvertIntoStringDateFormat(masterList.getUpdatedDate()));
	}
	
	if(masterList.getStatus()!=null){
		responseVO.setStatus(masterList.getStatus());
	}
	
	if(masterList.getIsDeleted()!=null){
		responseVO.setIsDeleted(masterList.getIsDeleted());
	}
	responseVO.setResponseCode("1");
	responseVO.setResponseDescription("Success");
	}else{
		responseVO.setResponseCode("0");
		responseVO.setResponseDescription("Fail");
	}
	
	return responseVO;
}

@Override
public BaseResponseVO saveTranscationIOSDetails(IOSTranscationsDetailsRequestVO iosTrxDetailsBasedUserId,AppleReceiptVerifyResponse responseVO) {
	
	
	TransactionMaster  trxMaster=new TransactionMaster();
	trxMaster.setStatus(true);
	trxMaster.setIsDeleted(false);
	trxMaster.setTxnType("IOS");
	
	trxMaster.setProductId(responseVO.getReceipt().getProduct_id());
	trxMaster.setOriginalpurchasedatems(DateUtils.stringDateFormatYYYYMMDDHHMMSS(responseVO.getReceipt().getOriginal_purchase_date_ms().toString()));
	trxMaster.setPurchasedatepst(responseVO.getReceipt().getPurchase_date_pst());
	trxMaster.setOriginalpurchasedate(responseVO.getReceipt().getOriginal_purchase_date());
	trxMaster.setBvrs(responseVO.getReceipt().getBvrs());
	trxMaster.setTxnId(responseVO.getReceipt().getTransaction_id());
	trxMaster.setOriginalpurchasedatepst(responseVO.getReceipt().getOriginal_purchase_date_pst());
	trxMaster.setUniqueidentifier(responseVO.getReceipt().getUnique_identifier());
	trxMaster.setOriginaltransactionid(responseVO.getReceipt().getOriginal_transaction_id());
	trxMaster.setItemid(responseVO.getReceipt().getItem_id());
	trxMaster.setPurchasedatems(DateUtils.stringDateFormatYYYYMMDDHHMMSS(responseVO.getReceipt().getPurchase_date_ms()));
	trxMaster.setQuantity(responseVO.getReceipt().getQuantity());
	trxMaster.setPurchasedate(responseVO.getReceipt().getPurchase_date());
	trxMaster.setBid(responseVO.getReceipt().getBid());
	trxMaster.setUniquevendoridentifier(responseVO.getReceipt().getUnique_vendor_identifier());
	trxMaster.setUserId(iosTrxDetailsBasedUserId.getUserId());
	trxMaster.setPaymentAmount(iosTrxDetailsBasedUserId.getAmount());
	trxMaster.setPaymentGross(iosTrxDetailsBasedUserId.getAmount());
	return userDao.saveTranscationIOSDetails(iosTrxDetailsBasedUserId,trxMaster);
}

public RestartVO restertServer(BaseRequestVO restartVO, PushNotificationTaskRestart taskRestartUpdate) {
	return userDao.restertServer(restartVO,taskRestartUpdate);
}

public Fovbyuser getFobByUser(BaseRequestVO baseRequestVO) {
	return userDao.getFobByUser(baseRequestVO);
}

public List<DeviceRegistration> getRegisteredDevice(Integer userId) {
	return userDao.getRegisteredDevice(userId);
}


@Override
public String logOutService(DeviceRegistrationRequestVO deviceRegistrationRequestVO) {
	return userDao.logOutService(deviceRegistrationRequestVO);
}

private HttpGet createHttpGet(String url) {
	
	HttpGet request = new HttpGet(url);
	request.setHeader("Content-Type", "application/json");
//	request.setHeader("Authorization", "Zoho-authtoken 3241cfd56afbc162bed1af6b7f47902c");
//	request.setHeader("X-com-zoho-subscriptions-organizationid", "690292259");
	return request;
}

@Override
public TransactionMaster subscriptionUpdateInAppPurchase(TransactionMaster transactionMaster) {
		return userDao.subscriptionUpdateInAppPurchase(transactionMaster);
}

}

