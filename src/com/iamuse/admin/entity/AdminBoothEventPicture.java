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
 * AdminBoothEventPicture entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "admin_booth_event_picture", catalog = "iamuse_internal")
public class AdminBoothEventPicture implements java.io.Serializable {

	// Fields

	private Integer picId;
	private String picName;
	private String picTitle;
	private String scaleXOffset;
	private String scaleYOffset;
	private String scaleZOffset;
	private String imageMask;
	private Date createdDate;
	private String rgbValues;
	private Integer createdBy;
	private Date updatedDate;
	private Integer updatedBy;
	private Integer EId;
	private String waterMarkImage;
	private String imageWidth;
	private String imageHeight;
	private String scalingWidth;
	private String scalingHeight;
	private Boolean isDeleted;
	private Boolean status;
	private Boolean preSetBackground;

	// Constructors

	/** default constructor */
	public AdminBoothEventPicture() {
	}

	/** full constructor */
	public AdminBoothEventPicture(String picName, String picTitle,
			String scaleXOffset, String scaleYOffset, String scaleZOffset,
			String imageMask, Date createdDate, String rgbValues,
			Integer createdBy, Date updatedDate, Integer updatedBy,
			Integer EId, String waterMarkImage, String imageWidth,
			String imageHeight, String scalingWidth, String scalingHeight,
			Boolean isDeleted, Boolean status,Boolean preSetBackground) {
		this.picName = picName;
		this.picTitle = picTitle;
		this.scaleXOffset = scaleXOffset;
		this.scaleYOffset = scaleYOffset;
		this.scaleZOffset = scaleZOffset;
		this.imageMask = imageMask;
		this.createdDate = createdDate;
		this.rgbValues = rgbValues;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.EId = EId;
		this.waterMarkImage = waterMarkImage;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		this.scalingWidth = scalingWidth;
		this.scalingHeight = scalingHeight;
		this.isDeleted = isDeleted;
		this.status = status;
		this.setPreSetBackground(preSetBackground);
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "pic_id", unique = true, nullable = false)
	public Integer getPicId() {
		return this.picId;
	}

	public void setPicId(Integer picId) {
		this.picId = picId;
	}

	@Column(name = "pic_name", length = 450)
	public String getPicName() {
		return this.picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	@Column(name = "pic_title", length = 1000)
	public String getPicTitle() {
		return this.picTitle;
	}

	public void setPicTitle(String picTitle) {
		this.picTitle = picTitle;
	}

	@Column(name = "scale_x_offset", length = 45)
	public String getScaleXOffset() {
		return this.scaleXOffset;
	}

	public void setScaleXOffset(String scaleXOffset) {
		this.scaleXOffset = scaleXOffset;
	}

	@Column(name = "scale_y_offset", length = 45)
	public String getScaleYOffset() {
		return this.scaleYOffset;
	}

	public void setScaleYOffset(String scaleYOffset) {
		this.scaleYOffset = scaleYOffset;
	}

	@Column(name = "scale_z_offset", length = 45)
	public String getScaleZOffset() {
		return this.scaleZOffset;
	}

	public void setScaleZOffset(String scaleZOffset) {
		this.scaleZOffset = scaleZOffset;
	}

	@Column(name = "image_mask", length = 2000)
	public String getImageMask() {
		return this.imageMask;
	}

	public void setImageMask(String imageMask) {
		this.imageMask = imageMask;
	}

	@Column(name = "created_date", length = 19)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "rgb_values", length = 45)
	public String getRgbValues() {
		return this.rgbValues;
	}

	public void setRgbValues(String rgbValues) {
		this.rgbValues = rgbValues;
	}

	@Column(name = "created_by")
	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "updated_date", length = 19)
	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name = "updated_by")
	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "e_id")
	public Integer getEId() {
		return this.EId;
	}

	public void setEId(Integer EId) {
		this.EId = EId;
	}

	@Column(name = "waterMarkImage", length = 2000)
	public String getWaterMarkImage() {
		return this.waterMarkImage;
	}

	public void setWaterMarkImage(String waterMarkImage) {
		this.waterMarkImage = waterMarkImage;
	}

	@Column(name = "imageWidth", length = 45)
	public String getImageWidth() {
		return this.imageWidth;
	}

	public void setImageWidth(String imageWidth) {
		this.imageWidth = imageWidth;
	}

	@Column(name = "imageHeight", length = 45)
	public String getImageHeight() {
		return this.imageHeight;
	}

	public void setImageHeight(String imageHeight) {
		this.imageHeight = imageHeight;
	}

	@Column(name = "scalingWidth", length = 45)
	public String getScalingWidth() {
		return this.scalingWidth;
	}

	public void setScalingWidth(String scalingWidth) {
		this.scalingWidth = scalingWidth;
	}

	@Column(name = "scalingHeight", length = 45)
	public String getScalingHeight() {
		return this.scalingHeight;
	}

	public void setScalingHeight(String scalingHeight) {
		this.scalingHeight = scalingHeight;
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

	@Column(name = "preSetBackground")
	public Boolean getPreSetBackground() {
		return preSetBackground;
	}

	public void setPreSetBackground(Boolean preSetBackground) {
		this.preSetBackground = preSetBackground;
	}

}