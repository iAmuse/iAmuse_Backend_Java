package com.iamuse.admin.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DeviceRegistration entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "device_registration", catalog = "iamuse_internal")
public class DeviceRegistration implements java.io.Serializable {

	// Fields

	private Integer deviceId;
	private String deviceName;
	private String deviceType;
	private String operationgSystemVersion;
	private String deteactedResolution;
	private String guidedAccessEnabled;
	private String deviceStorage;
	private String wirelessNetwork;
	private String ipAddress;
	private Timestamp lastSyncTime;
	private Integer userId;
	private String deviceToken;
	private Timestamp createdDate;
	private Boolean status;
	private Boolean isDeleted;
	private String subNetMask;
	
	private String deviceTimestamp;
	
	@Column(name = "deviceUUID", length = 100)
	private String deviceUUID;
	
	
	public String getDeviceUUID() {
		return deviceUUID;
	}



	public void setDeviceUUID(String deviceUUID) {
		this.deviceUUID = deviceUUID;
	}
	// Constructors

	/** default constructor */
	public DeviceRegistration() {
	}

	/** full constructor */
	public DeviceRegistration(String deviceName, String deviceType,
			String operationgSystemVersion, String deteactedResolution,
			String guidedAccessEnabled, String deviceStorage,
			String wirelessNetwork, String ipAddress, Timestamp lastSyncTime,
			Integer userId, String deviceToken, Timestamp createdDate,Boolean status,Boolean isDeleted,String deviceUUID,String subNetMask) {
		this.deviceName = deviceName;
		this.deviceType = deviceType;
		this.operationgSystemVersion = operationgSystemVersion;
		this.deteactedResolution = deteactedResolution;
		this.guidedAccessEnabled = guidedAccessEnabled;
		this.deviceStorage = deviceStorage;
		this.wirelessNetwork = wirelessNetwork;
		this.ipAddress = ipAddress;
		this.lastSyncTime = lastSyncTime;
		this.userId = userId;
		this.deviceToken = deviceToken;
		this.createdDate = createdDate;
		this.status = status;
		this.isDeleted = isDeleted;
		this.deviceUUID=deviceUUID;
		this.subNetMask=subNetMask;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "device_id", unique = true, nullable = false)
	public Integer getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	@Column(name = "device_name", length = 45)
	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	@Column(name = "device_type", length = 45)
	public String getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	@Column(name = "operationg_system_version", length = 45)
	public String getOperationgSystemVersion() {
		return this.operationgSystemVersion;
	}

	public void setOperationgSystemVersion(String operationgSystemVersion) {
		this.operationgSystemVersion = operationgSystemVersion;
	}

	@Column(name = "deteacted_resolution", length = 100)
	public String getDeteactedResolution() {
		return this.deteactedResolution;
	}

	public void setDeteactedResolution(String deteactedResolution) {
		this.deteactedResolution = deteactedResolution;
	}

	@Column(name = "guided_access_enabled", length = 45)
	public String getGuidedAccessEnabled() {
		return this.guidedAccessEnabled;
	}

	public void setGuidedAccessEnabled(String guidedAccessEnabled) {
		this.guidedAccessEnabled = guidedAccessEnabled;
	}

	@Column(name = "device_storage", length = 100)
	public String getDeviceStorage() {
		return this.deviceStorage;
	}

	public void setDeviceStorage(String deviceStorage) {
		this.deviceStorage = deviceStorage;
	}

	@Column(name = "wireless_network", length = 45)
	public String getWirelessNetwork() {
		return this.wirelessNetwork;
	}

	public void setWirelessNetwork(String wirelessNetwork) {
		this.wirelessNetwork = wirelessNetwork;
	}

	@Column(name = "ip_address", length = 45)
	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Column(name = "last_sync_time", length = 19)
	public Timestamp getLastSyncTime() {
		return this.lastSyncTime;
	}

	public void setLastSyncTime(Timestamp lastSyncTime) {
		this.lastSyncTime = lastSyncTime;
	}

	@Column(name = "user_id")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "device_token", length = 100)
	public String getDeviceToken() {
		return this.deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	@Column(name = "createdDate", length = 19)
	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name = "status")
	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}


	@Column(name = "isDeleted")
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


	@Column(name = "subNetMask")
	public String getSubNetMask() {
		return subNetMask;
	}



	public void setSubNetMask(String subNetMask) {
		this.subNetMask = subNetMask;
	}


	@Column(name = "deviceTimestamp")
	public String getDeviceTimestamp() {
		return deviceTimestamp;
	}



	public void setDeviceTimestamp(String deviceTimestamp) {
		this.deviceTimestamp = deviceTimestamp;
	}

}