package com.iamuse.admin.util;

import java.util.List;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.iamuse.admin.entity.DeviceRegistration;

@Component
@Scope("prototype")
public class PushNotificationTaskFOV implements Runnable{


	MessageSource messageSource;
	String path="";
	private List<DeviceRegistration> deviceRegistration;
		
	public void setDetailsForFOV(List<DeviceRegistration> deviceRegistration,MessageSource messageSource,String path){

		this.deviceRegistration=deviceRegistration;
		this.messageSource=messageSource;
		this.path=path;
	}

	@Override
	public void run() {
		try {
			if(!deviceRegistration.isEmpty())
			{
				PushUtil.sendPushToApple("FOVPush",deviceRegistration,path);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
