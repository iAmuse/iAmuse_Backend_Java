package com.iamuse.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BoothPictureCropper entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "booth_picture_cropper", catalog = "iamuse_internal")
public class BoothPictureCropper implements java.io.Serializable {

	// Fields

	private Integer cropperId;
	private String imgX;
	private String imgY;
	private String imgWidth;
	private String imgHeight;
	private Integer adminBoothEventPictureId;
	private Integer defaultAdminBoothEventPictureId;
	private String gridShift;
	// Constructors

	/** default constructor */
	public BoothPictureCropper() {
	}

	/** full constructor */
	public BoothPictureCropper(String imgX, String imgY, String imgWidth,
			String imgHeight, Integer adminBoothEventPictureId, Integer defaultAdminBoothEventPictureId) {
		this.imgX = imgX;
		this.imgY = imgY;
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
		this.adminBoothEventPictureId = adminBoothEventPictureId;
		this.setDefaultAdminBoothEventPictureId(defaultAdminBoothEventPictureId);
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "cropper_id", unique = true, nullable = false)
	public Integer getCropperId() {
		return this.cropperId;
	}

	public void setCropperId(Integer cropperId) {
		this.cropperId = cropperId;
	}

	@Column(name = "imgX", length = 45)
	public String getImgX() {
		return this.imgX;
	}

	public void setImgX(String imgX) {
		this.imgX = imgX;
	}

	@Column(name = "imgY", length = 45)
	public String getImgY() {
		return this.imgY;
	}

	public void setImgY(String imgY) {
		this.imgY = imgY;
	}

	@Column(name = "imgWidth", length = 45)
	public String getImgWidth() {
		return this.imgWidth;
	}

	public void setImgWidth(String imgWidth) {
		this.imgWidth = imgWidth;
	}

	@Column(name = "imgHeight", length = 45)
	public String getImgHeight() {
		return this.imgHeight;
	}

	public void setImgHeight(String imgHeight) {
		this.imgHeight = imgHeight;
	}

	@Column(name = "adminBoothEventPictureId")
	public Integer getAdminBoothEventPictureId() {
		return this.adminBoothEventPictureId;
	}
	
	public String getGridShift() {
		return gridShift;
	}

	@Column(name = "gridShift")
	public void setGridShift(String gridShift) {
		this.gridShift = gridShift;
	}

	public void setAdminBoothEventPictureId(Integer adminBoothEventPictureId) {
		this.adminBoothEventPictureId = adminBoothEventPictureId;
	}

	public Integer getDefaultAdminBoothEventPictureId() {
		return defaultAdminBoothEventPictureId;
	}

	public void setDefaultAdminBoothEventPictureId(Integer defaultAdminBoothEventPictureId) {
		this.defaultAdminBoothEventPictureId = defaultAdminBoothEventPictureId;
	}

}