package com.iamuse.admin.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DeviceRegistrationAll entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "device_registration_all", catalog = "iamuse_internal")
public class DeviceRegistrationAll implements java.io.Serializable {

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
	private Date updateDatetime;
	private Integer userId;
	private String deviceToken;
	private Date createdDate;
	private Boolean isDeleted;
	private Boolean status;
	private String subNetMask;
	private String deviceUUID;

	// Constructors

	/** default constructor */
	public DeviceRegistrationAll() {
	}

	/** full constructor */
	public DeviceRegistrationAll(String deviceName, String deviceType,
			String operationgSystemVersion, String deteactedResolution,
			String guidedAccessEnabled, String deviceStorage,
			String wirelessNetwork, String ipAddress, Timestamp updateDatetime,
			Integer userId, String deviceToken, Timestamp createdDate,
			Boolean isDeleted, Boolean status, String subNetMask,
			String deviceUUID) {
		this.deviceName = deviceName;
		this.deviceType = deviceType;
		this.operationgSystemVersion = operationgSystemVersion;
		this.deteactedResolution = deteactedResolution;
		this.guidedAccessEnabled = guidedAccessEnabled;
		this.deviceStorage = deviceStorage;
		this.wirelessNetwork = wirelessNetwork;
		this.ipAddress = ipAddress;
		this.updateDatetime = updateDatetime;
		this.userId = userId;
		this.deviceToken = deviceToken;
		this.createdDate = createdDate;
		this.isDeleted = isDeleted;
		this.status = status;
		this.subNetMask = subNetMask;
		this.deviceUUID = deviceUUID;
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

	@Column(name = "update_datetime", length = 19)
	public Date getUpdateDatetime() {
		return this.updateDatetime;
	}

	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
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
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "isDeleted")
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Column(name = "status")
	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Column(name = "subNetMask", length = 450)
	public String getSubNetMask() {
		return this.subNetMask;
	}

	public void setSubNetMask(String subNetMask) {
		this.subNetMask = subNetMask;
	}

	@Column(name = "deviceUUID", length = 100)
	public String getDeviceUUID() {
		return this.deviceUUID;
	}

	public void setDeviceUUID(String deviceUUID) {
		this.deviceUUID = deviceUUID;
	}

}