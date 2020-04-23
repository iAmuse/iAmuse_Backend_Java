package com.iamuse.server.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.iamuse.server.entity.DeviceRegistration;
import com.iamuse.server.service.UserService;


@Component
@Scope("prototype")
public class PushNotificationTaskRestart implements Runnable{


	@Autowired
	UserService iamuseDashboardService;
	int imageId;
	String deviceToken;
	MessageSource messageSource;
	String deviceId;
	String path="";
	private List<DeviceRegistration> deviceRegistration;
	private static final Logger log = Logger.getLogger(PushNotificationTaskRestart.class);
	public void setDetails(List<DeviceRegistration> deviceRegistration,MessageSource messageSource, String path){

		this.deviceRegistration=deviceRegistration;
		this.messageSource=messageSource;
		//this.deviceId=deviceId;
		this.path=path;
	}

	public void run() {

		try {
			System.out.println("try block");
			
			if(deviceRegistration.size()>0)
		{

				System.out.println("Inside Push Notification Task");
				log.info("Inside Push Notification Task");
			boolean pushStatus=	PushUtil.sendPushToApple("updateIP",deviceRegistration,path);
			System.out.println(pushStatus);	
			if(pushStatus){
				
				   log.info("Method:ImagePushNotificationTask");
				   log.info("SuccessFully Send push");
			System.out.println("After Push Notification Task");
			}

			}
			
		
		
		}catch (Exception e) {
			
		}

	}

}
