package com.iamuse.admin.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DeviceIp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "device_ip", catalog = "iamuse")
public class DeviceIp implements java.io.Serializable {

	// Fields

	private Integer id;
	private String deviceType;
	private String deviceIp;
	private Boolean status;
	private Integer userId;
	private Timestamp uploadTime;

	// Constructors

	/** default constructor */
	public DeviceIp() {
	}

	/** full constructor */
	public DeviceIp(String deviceType, String deviceIp, Boolean status,
			Integer userId, Timestamp uploadTime) {
		this.deviceType = deviceType;
		this.deviceIp = deviceIp;
		this.status = status;
		this.userId = userId;
		this.uploadTime = uploadTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "device_type", length = 1000)
	public String getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	@Column(name = "device_ip", length = 1000)
	public String getDeviceIp() {
		return this.deviceIp;
	}

	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	@Column(name = "status")
	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Column(name = "user_id")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "upload_time", length = 19)
	public Timestamp getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}

}