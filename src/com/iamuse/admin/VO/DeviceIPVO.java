package com.iamuse.admin.VO;

public class DeviceIPVO  {
	private String deviceType="";
	private String deviceIP="";
	private String uploadTime="";
	private Integer serialNumber;
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
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
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}
	public Integer getSerialNumber() {
		return serialNumber;
	}
	
	
}
