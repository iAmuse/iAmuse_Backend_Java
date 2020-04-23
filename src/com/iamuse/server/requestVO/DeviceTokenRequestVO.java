package com.iamuse.server.requestVO;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
public class DeviceTokenRequestVO extends BaseRequestVO {
	private String deviceToken="";

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
}
