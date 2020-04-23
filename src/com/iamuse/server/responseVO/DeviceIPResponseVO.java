package com.iamuse.server.responseVO;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class DeviceIPResponseVO extends BaseResponseVO {
	
	private String deviceIP;

	public String getDeviceIP() {
		return deviceIP;
	}

	public void setDeviceIP(String deviceIP) {
		this.deviceIP = deviceIP;
	}

	

	

}
