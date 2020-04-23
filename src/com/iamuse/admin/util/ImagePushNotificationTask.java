package com.iamuse.admin.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.iamuse.admin.entity.DeviceRegistration;
import com.iamuse.admin.service.IamuseDashboardService;

@Component
@Scope("prototype")
public class ImagePushNotificationTask implements Runnable{


	@Autowired
	IamuseDashboardService iamuseDashboardService;
	
	int imageId;
	String deviceToken;
	MessageSource messageSource;
	String deviceId;
	String path="";
	private List<DeviceRegistration> deviceRegistration;
	private static final Logger log = Logger.getLogger(ImagePushNotificationTask.class);
	public void setDetails(List<DeviceRegistration> deviceRegistration,MessageSource messageSource, String path){

		this.deviceRegistration=deviceRegistration;
		this.messageSource=messageSource;
		this.path=path;
	}

	@Override
	public void run() {

		try {
			if(!deviceRegistration.isEmpty())
			{
						log.info("Inside Push Notification Task");
					    log.info("deviceRegistrantion"+deviceRegistration.size());
						boolean pushStatus=	PushUtil.sendPushToApple("ImagePush",deviceRegistration,path);
						if(pushStatus){
						log.info("Method:ImagePushNotificationTask");
						log.info("SuccessFully Send push");
			}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
