package com.iamuse.admin.util;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import javapns.Push;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import javapns.notification.ResponsePacket;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.log4j.Logger;
import org.json.JSONException;

import com.iamuse.admin.entity.DeviceRegistration;

public class PushUtil {
	 private PushUtil(){}
	//private static final String GOOGLE_SERVER_KEY = "AIzaSyDwX3o8yoxZo4kvp4YUmdEQQ-T4nZ4kyzc"; //For Debugging
    //private static final String GOOGLE_SERVER_KEY = "AIzaSyBu72OIhgwUNAa2QZ1rEczjZUEpvXNYzv4"; //For Production
	//static final String MESSAGE_KEY = "message";
	static final String type = "type";
	private static final Logger log = Logger.getLogger(PushUtil.class);
	
	public static boolean sendPushToApple(String message,List<DeviceRegistration> deviceRegistration, String path)
	{
		boolean result=false;
		try {
			SSLContext ctx;
			try {
				ctx = SSLContext.getInstance("TLS");
				 try {
					ctx.init(new KeyManager[0], new TrustManager[] {new DefaultTrustManager()}, new SecureRandom());
					  SSLContext.setDefault(ctx);
				} catch (KeyManagementException e) {
					e.printStackTrace(); 
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
	       
			PushNotificationPayload payload = PushNotificationPayload.complex();
			if(message.equals("ImagePush")){
				payload.addCustomDictionary(type,message);
				payload.addAlert(message);
				payload.addSound("default");
			}
			if(message.equals("RgbPush")){
				payload.addCustomDictionary(type,message);
				payload.addAlert(message);
				payload.addSound("default");
				}
			if(message.equals("ClearDevicePictures")){
				payload.addCustomDictionary(type,"ClearDevicePictures");
				payload.addAlert(message);
				payload.addSound("default");
				}
			if(message.equals("EventUpdate")){
				payload.addCustomDictionary(type,message);
				payload.addAlert(message);
				payload.addSound("default");
				}
			if(message.equals("FOVPush")){
				payload.addCustomDictionary(type,"FOVPush");
				payload.addAlert(message);
				payload.addSound("default");
			}
		for(DeviceRegistration deviceRegistrations:deviceRegistration){
			log.info("Device Token inside Push TO Apple:"+deviceRegistrations.getDeviceToken());
			List<PushedNotification> notifications=	Push.payload(payload,path+"/"+"iAmuseappProduction.p12", "star@123", true, deviceRegistrations.getDeviceToken());
			//List<PushedNotification> notifications=	Push.payload(payload,path+"/"+"Certificates_iAmuse_new_Prod.p12", "star@123", false, "1f016c41823db3060c76912605689339a6655d44a18390924856dc56497a378d");
			/*List<PushedNotification> notifications=	Push.payload(payload,path+"/"+"vibewire.p12", "12345678", false, deviceRegistrations.getDeviceToken());*/
			for (PushedNotification notification : notifications) {
	            if (notification.isSuccessful()) {
	            	log.info("successful:"+notification.getResponse());
	               result=true;
	             } else {
	            	    log.info("error: "+notification.getException());
	                    Exception theProblem = notification.getException();
	                    theProblem.printStackTrace();
	                    ResponsePacket theErrorResponse = notification.getResponse();
	                    if (theErrorResponse != null) {
	                    }
	            }
			}
		}
		} catch (CommunicationException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			   log.info("Method:sendPushToApple");
		} catch (KeystoreException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			   log.info("Method:sendPushToApple");
		}catch (JSONException e) {
			log.error(e.getMessage());
			   log.info("Method:sendPushToApple");
			e.printStackTrace();
		}
		
		return result; 
	}
	
	
	
	
	
	public static void main(String[] args) {
	//PushUtil.sendPushToAndroid("Testing Push", "APA91bHW_mhCgra7IuV5UFWnV5Cfo1NRIyjEBnUS30OANCTOh_iHXiBL58XUHstEJQZN78T_8GEM-aQzLGdn_n5CyLkigBAaJKVtkTWlLpdHmil355zJNcmpJNvybnv9I0oy5fVRCY54EFf4RKxxtQC5T7pQAXHMcQ","232734023408957");
	//PushUtil.sendPushToApple("Testing Push","7de90329a2b1ab6f7d65b0e5423718976cbca4515e07222ea9988fb94c09ce4a","231241234132");
		
	}
	
}
class DefaultTrustManager implements X509TrustManager {

    public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}

    public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}