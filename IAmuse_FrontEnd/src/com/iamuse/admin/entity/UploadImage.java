package com.iamuse.admin.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UploadImage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "upload_image", catalog = "iamuse_internal")
public class UploadImage implements java.io.Serializable {

	// Fields

	private Integer imageId;
	private String imageUrl;
	private Boolean status;
	private Boolean isThumbnail;
	private Timestamp uploadTime;
	private String imageName;
	private String hexValue;
	private String rgbValue;
	private String rgbaValue;
	private Timestamp updateTime;
	private String hexValueMax;
	private String rgbValueMax;
	private String rgbaValueMax;
	private Boolean isUpdatedRgb;
	private Integer userId;
	private Boolean isDeleted;
	private Boolean isValidate;

	// Constructors

	/** default constructor */
	public UploadImage() {
	}

	/** full constructor */
	public UploadImage(String imageUrl, Boolean status, Boolean isThumbnail,
			Timestamp uploadTime, String imageName, String hexValue,
			String rgbValue, String rgbaValue, Timestamp updateTime,
			String hexValueMax, String rgbValueMax, String rgbaValueMax,
			Boolean isUpdatedRgb, Integer userId,Boolean isDeleted,Boolean isValidate) {
		this.imageUrl = imageUrl;
		this.status = status;
		this.isThumbnail = isThumbnail;
		this.uploadTime = uploadTime;
		this.imageName = imageName;
		this.hexValue = hexValue;
		this.rgbValue = rgbValue;
		this.rgbaValue = rgbaValue;
		this.updateTime = updateTime;
		this.hexValueMax = hexValueMax;
		this.rgbValueMax = rgbValueMax;
		this.rgbaValueMax = rgbaValueMax;
		this.isUpdatedRgb = isUpdatedRgb;
		this.userId = userId;
		this.isDeleted = isDeleted;
		this.setIsValidate(isValidate);
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "image_id", unique = true, nullable = false)
	public Integer getImageId() {
		return this.imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	@Column(name = "image_url", length = 1000)
	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Column(name = "status")
	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Column(name = "isThumbnail")
	public Boolean getIsThumbnail() {
		return this.isThumbnail;
	}

	public void setIsThumbnail(Boolean isThumbnail) {
		this.isThumbnail = isThumbnail;
	}

	@Column(name = "upload_time", length = 19)
	public Timestamp getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}

	@Column(name = "image_name", length = 50)
	public String getImageName() {
		return this.imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Column(name = "hex_value", length = 45)
	public String getHexValue() {
		return this.hexValue;
	}

	public void setHexValue(String hexValue) {
		this.hexValue = hexValue;
	}

	@Column(name = "rgb_value", length = 45)
	public String getRgbValue() {
		return this.rgbValue;
	}

	public void setRgbValue(String rgbValue) {
		this.rgbValue = rgbValue;
	}

	@Column(name = "rgba_value", length = 45)
	public String getRgbaValue() {
		return this.rgbaValue;
	}

	public void setRgbaValue(String rgbaValue) {
		this.rgbaValue = rgbaValue;
	}

	@Column(name = "update_time", length = 19)
	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "hex_value_max", length = 45)
	public String getHexValueMax() {
		return this.hexValueMax;
	}

	public void setHexValueMax(String hexValueMax) {
		this.hexValueMax = hexValueMax;
	}

	@Column(name = "rgb_value_max", length = 45)
	public String getRgbValueMax() {
		return this.rgbValueMax;
	}

	public void setRgbValueMax(String rgbValueMax) {
		this.rgbValueMax = rgbValueMax;
	}

	@Column(name = "rgba_value_max", length = 45)
	public String getRgbaValueMax() {
		return this.rgbaValueMax;
	}

	public void setRgbaValueMax(String rgbaValueMax) {
		this.rgbaValueMax = rgbaValueMax;
	}

	@Column(name = "isUpdatedRGB")
	public Boolean getIsUpdatedRgb() {
		return this.isUpdatedRgb;
	}

	public void setIsUpdatedRgb(Boolean isUpdatedRgb) {
		this.isUpdatedRgb = isUpdatedRgb;
	}

	@Column(name = "user_id")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "isDeleted")
	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Column(name = "isValidate")
	public Boolean getIsValidate() {
		return isValidate;
	}

	public void setIsValidate(Boolean isValidate) {
		this.isValidate = isValidate;
	}

}