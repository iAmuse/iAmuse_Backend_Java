package com.iamuse.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Usermaster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "usermaster", catalog = "iamuse")
public class Usermaster implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String username;
	private String password;
	private Boolean status;
	private String deviceId;
	private String deviceToken;
	private String hexValueDefault;
	private String rgbValueDefault;
	private String rgbaValueDefault;
	private Integer currentImageId;
	private Boolean isDefaultRgb;
	private String hexValueManual;
	private String rgbValueManual;
	private String rgbaValueManual;

	// Constructors

	/** default constructor */
	public Usermaster() {
	}

	/** full constructor */
	public Usermaster(String username, String password, Boolean status,
			String deviceId, String deviceToken, String hexValueDefault,
			String rgbValueDefault, String rgbaValueDefault,
			Integer currentImageId, Boolean isDefaultRgb,
			String hexValueManual, String rgbValueManual, String rgbaValueManual) {
		this.username = username;
		this.password = password;
		this.status = status;
		this.deviceId = deviceId;
		this.deviceToken = deviceToken;
		this.hexValueDefault = hexValueDefault;
		this.rgbValueDefault = rgbValueDefault;
		this.rgbaValueDefault = rgbaValueDefault;
		this.currentImageId = currentImageId;
		this.isDefaultRgb = isDefaultRgb;
		this.hexValueManual = hexValueManual;
		this.rgbValueManual = rgbValueManual;
		this.rgbaValueManual = rgbaValueManual;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "username", length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", length = 45)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "status")
	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Column(name = "device_id", length = 1000)
	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Column(name = "device_token", length = 1000)
	public String getDeviceToken() {
		return this.deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	@Column(name = "hex_value_default", length = 45)
	public String getHexValueDefault() {
		return this.hexValueDefault;
	}

	public void setHexValueDefault(String hexValueDefault) {
		this.hexValueDefault = hexValueDefault;
	}

	@Column(name = "rgb_value_default", length = 45)
	public String getRgbValueDefault() {
		return this.rgbValueDefault;
	}

	public void setRgbValueDefault(String rgbValueDefault) {
		this.rgbValueDefault = rgbValueDefault;
	}

	@Column(name = "rgba_value_default", length = 45)
	public String getRgbaValueDefault() {
		return this.rgbaValueDefault;
	}

	public void setRgbaValueDefault(String rgbaValueDefault) {
		this.rgbaValueDefault = rgbaValueDefault;
	}

	@Column(name = "current_image_id")
	public Integer getCurrentImageId() {
		return this.currentImageId;
	}

	public void setCurrentImageId(Integer currentImageId) {
		this.currentImageId = currentImageId;
	}

	@Column(name = "is_default_rgb")
	public Boolean getIsDefaultRgb() {
		return this.isDefaultRgb;
	}

	public void setIsDefaultRgb(Boolean isDefaultRgb) {
		this.isDefaultRgb = isDefaultRgb;
	}

	@Column(name = "hex_value_manual", length = 45)
	public String getHexValueManual() {
		return this.hexValueManual;
	}

	public void setHexValueManual(String hexValueManual) {
		this.hexValueManual = hexValueManual;
	}

	@Column(name = "rgb_value_manual", length = 45)
	public String getRgbValueManual() {
		return this.rgbValueManual;
	}

	public void setRgbValueManual(String rgbValueManual) {
		this.rgbValueManual = rgbValueManual;
	}

	@Column(name = "rgba_value_manual", length = 45)
	public String getRgbaValueManual() {
		return this.rgbaValueManual;
	}

	public void setRgbaValueManual(String rgbaValueManual) {
		this.rgbaValueManual = rgbaValueManual;
	}

}