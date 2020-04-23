package com.iamuse.server.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AdminPicture entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "admin_booth_event_picture", catalog = "iamuse_internal")
public class AdminPicture implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
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
	private Integer eId;
	
	@Column(name="waterMarkImage", length=2000)
	private String waterMarkImage;
	@Column(name="imageWidth", length=45)
	private String imageWidth;
	@Column(name="imageHeight", length=45)
	private String imageHeight;
	@Column(name="scalingWidth", length=45)
	private String scalingWidth;
	
	@Column(name="scalingHeight", length=45)
	private String scalingHeight;
	
	
	@Column(name="status")
	private Boolean status;


	@Column(name="isDeleted")
	private Boolean isDeleted;

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

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "pic_id", unique = true, nullable = false)
	public Integer getPicId() {
		return this.picId;
	}

	public void setPicId(Integer picId) {
		this.picId = picId;
	}

	@Column(name = "pic_name", length = 45)
	public String getPicName() {
		return this.picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	@Column(name = "pic_title", length = 45)
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", length = 19)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date", length = 19)
	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
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
	public Integer geteId() {
		return eId;
	}

	public void seteId(Integer eId) {
		this.eId = eId;
	}

	public String getWaterMarkImage() {
		return waterMarkImage;
	}

	public void setWaterMarkImage(String waterMarkImage) {
		this.waterMarkImage = waterMarkImage;
	}

	
	public String getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(String imageWidth) {
		this.imageWidth = imageWidth;
	}

	public String getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(String imageHeight) {
		this.imageHeight = imageHeight;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getScalingWidth() {
		return scalingWidth;
	}

	public void setScalingWidth(String scalingWidth) {
		this.scalingWidth = scalingWidth;
	}

	public String getScalingHeight() {
		return scalingHeight;
	}

	public void setScalingHeight(String scalingHeight) {
		this.scalingHeight = scalingHeight;
	}


}