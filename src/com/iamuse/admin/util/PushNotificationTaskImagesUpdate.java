package com.iamuse.admin.util;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.iamuse.admin.entity.DeviceRegistration;
import com.iamuse.admin.service.IamuseDashboardService;

@Component
@Scope("prototype")
public class PushNotificationTaskImagesUpdate implements Runnable{


	@Autowired
	IamuseDashboardService iamuseDashboardService;
	int imageId;
	String deviceToken;
	MessageSource messageSource;
	String deviceId;
	String path="";
	private List<DeviceRegistration> deviceRegistration;
		
	public void setDetails(List<DeviceRegistration> deviceRegistration,MessageSource messageSource, String path){

		this.deviceRegistration = deviceRegistration;
		this.messageSource=messageSource;
		this.path=path;
	}

	@Override
	public void run() {

		try {
			if(!deviceRegistration.isEmpty())
			{
				PushUtil.sendPushToApple("EventUpdate",deviceRegistration,path);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
