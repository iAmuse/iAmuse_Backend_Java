package com.iamuse.server.requestVO;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy(true)
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceIPRequestVO extends BaseRequestVO {
	private String deviceType="";
	private String deviceIP="";
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceIP() {
		return deviceIP;
	}
	public void setDeviceIP(String deviceIP) {
		this.deviceIP = deviceIP;
	}

	
	

}
