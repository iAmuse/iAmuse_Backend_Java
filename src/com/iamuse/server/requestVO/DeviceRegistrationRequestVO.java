package com.iamuse.server.requestVO;

import java.sql.Timestamp;

public class DeviceRegistrationRequestVO{
	private Integer deviceId;
	private String deviceName;
	private String deviceType;
	private String operationgSystemVersion;
	private String deteactedResolution;
	private String guidedAccessEnabled;
	private String deviceStorage;
	private String wirelessNetwork;
	private String ipAddress;
	private Integer userId;
	private String deviceToken="";
	private String oldDeviceToken="";
	private String subNetMask;
	private String deviceUUID;
	private String deviceTimestamp;
	
	
	
	public String getDeviceUUID() {
		return deviceUUID;
	}
	public void setDeviceUUID(String deviceUUID) {
		this.deviceUUID = deviceUUID;
	}
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getOperationgSystemVersion() {
		return operationgSystemVersion;
	}
	public void setOperationgSystemVersion(String operationgSystemVersion) {
		this.operationgSystemVersion = operationgSystemVersion;
	}
	public String getDeteactedResolution() {
		return deteactedResolution;
	}
	public void setDeteactedResolution(String deteactedResolution) {
		this.deteactedResolution = deteactedResolution;
	}
	public String getGuidedAccessEnabled() {
		return guidedAccessEnabled;
	}
	public void setGuidedAccessEnabled(String guidedAccessEnabled) {
		this.guidedAccessEnabled = guidedAccessEnabled;
	}
	public String getDeviceStorage() {
		return deviceStorage;
	}
	public void setDeviceStorage(String deviceStorage) {
		this.deviceStorage = deviceStorage;
	}
	public String getWirelessNetwork() {
		return wirelessNetwork;
	}
	public void setWirelessNetwork(String wirelessNetwork) {
		this.wirelessNetwork = wirelessNetwork;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	public String getOldDeviceToken() {
		return oldDeviceToken;
	}
	public void setOldDeviceToken(String oldDeviceToken) {
		this.oldDeviceToken = oldDeviceToken;
	}

	public String getSubNetMask() {
		return subNetMask;
	}
	public void setSubNetMask(String subNetMask) {
		this.subNetMask = subNetMask;
	}
	public String getDeviceTimestamp() {
		return deviceTimestamp;
	}
	public void setDeviceTimestamp(String deviceTimestamp) {
		this.deviceTimestamp = deviceTimestamp;
	}
	
}
