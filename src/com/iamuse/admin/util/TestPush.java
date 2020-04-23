package com.iamuse.admin.util;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.json.JSONException;
import javapns.Push;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import javapns.notification.ResponsePacket;

public class TestPush {
	private TestPush(){}
	
	static final String type = "type";
	//public final static String AUTH_KEY_FCM = "Your api key";
	//public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";
	
	static File file =new File("E:\\sc_close.p12");
	static final String  certPass="12345678";
	static final boolean isProduction=true;
	static final String deviceToken="d851c060b52fd2fa629ceee4cfea7b63d3b0921a26e16f7872569e9f2c373818";
	
	
	
	
	public static void main(String[] args) throws CommunicationException, KeystoreException, JSONException, IOException {
		TestPush.sendPushToApple(file, certPass,isProduction,deviceToken);
		//TestPush.sendPushToAndroid();
			
		}

	private static void sendPushToApple(File file, String certPass, boolean isProduction, String deviceToken) throws CommunicationException, KeystoreException, JSONException {
		boolean result=false;
		// This is for ssl please check and verify
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
					payload.addCustomDictionary(type,"FOVPush");
					payload.addAlert("Hello");
					payload.addSound("default");
					List<PushedNotification> notifications =	Push.payload(payload,file.getAbsolutePath(),certPass, isProduction,   deviceToken);
					for (PushedNotification notification : notifications) {
						if (notification.isSuccessful()) {
									result=true;
						} else {
			                  Exception theProblem = notification.getException();
			                  theProblem.printStackTrace();
			                  ResponsePacket theErrorResponse = notification.getResponse();
			                  if (theErrorResponse != null) {
			                  }
						}
						System.out.println(result);
			           }
		} catch (CommunicationException e) {
			e.printStackTrace();
		} catch (KeystoreException e) {
			e.printStackTrace();
		}catch (JSONException e) {
			e.printStackTrace();
		}
		}
	
	
	/*private static void sendPushToAndroid() throws JSONException, IOException {
		 String result = "";
		    URL url = new URL(API_URL_FCM);
		    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		    conn.setUseCaches(false);
		    conn.setDoInput(true);
		    conn.setDoOutput(true);

		    conn.setRequestMethod("POST");
		    conn.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);
		    conn.setRequestProperty("Content-Type", "application/json");

		    JSONObject json = new JSONObject();

		    json.put("to", "9170f9c360518428df0083b1cfcb42260777fd516acfcb31beef255163d0d88c");
		    JSONObject info = new JSONObject();
		    info.put("title", "notification title"); // Notification title
		    info.put("body", "message body"); // Notification body
		    json.put("notification", info);
		    try {
		        OutputStreamWriter wr = new OutputStreamWriter(
		                conn.getOutputStream());
		        wr.write(json.toString());
		        wr.flush();

		        BufferedReader br = new BufferedReader(new InputStreamReader(
		                (conn.getInputStream())));

		        String output;
		        System.out.println("Output from Server .... \n");
		        while ((output = br.readLine()) != null) {
		            System.out.println(output);
		        }
		        result = "success";
		    } catch (Exception e) {
		        e.printStackTrace();
		        result = "failure";
		    }
		    //System.out.println("FCM Notification is sent successfully");
		    System.out.println(result); 
	}*/
}
