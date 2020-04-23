package com.iamuse.admin.VO;

public class DeviceRegistrationResponseVO {
	private Integer deviceId;
	private String deviceName;
	private String deviceType;
	private String operationgSystemVersion;
	private String deteactedResolution;
	private String guidedAccessEnabled;
	private String deviceStorage;
	private String wirelessNetwork;
	private String ipAddress;
	private String lastSyncTime;
	private Integer userId;
	private String deviceToken;
	private String createdDate;
	
	
	private String oldDeviceToken="";
	private String subNetMask;
	private String deviceUUID;
	
	

public String getDeviceUUID() {
		return deviceUUID;
	}

	public void setDeviceUUID(String deviceUUID) {
		this.deviceUUID = deviceUUID;
	}

private Boolean status;
private Boolean isDeleted;

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

	public String getLastSyncTime() {
		return lastSyncTime;
	}

	public void setLastSyncTime(String lastSyncTime) {
		this.lastSyncTime = lastSyncTime;
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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "DeviceRegistrationResponseVO [deviceId=" + deviceId
				+ ", deviceName=" + deviceName + ", deviceType=" + deviceType
				+ ", operationgSystemVersion=" + operationgSystemVersion
				+ ", deteactedResolution=" + deteactedResolution
				+ ", guidedAccessEnabled=" + guidedAccessEnabled
				+ ", deviceStorage=" + deviceStorage + ", wirelessNetwork="
				+ wirelessNetwork + ", ipAddress=" + ipAddress
				+ ", lastSyncTime=" + lastSyncTime + ", userId=" + userId
				+ ", deviceToken=" + deviceToken + ", createdDate="
				+ createdDate + ", status=" + status + ", isDeleted="
				+ isDeleted + "]";
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

	

}