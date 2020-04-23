package com.iamuse.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Fovbyuser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fovbyuser", catalog = "iamuse_internal")
//@Table(name = "fovbyuser", catalog = "iamus_internal")
public class Fovbyuser implements java.io.Serializable {

	// Fields

	private Integer fovId;
	private String fovTop;
	private String fovBottom;
	private String fovLeft;
	private String fovRight;
	private String greenScreenWidth;
	private String greenScreenDistance;
	private String greenScreenHeight;
	private String greenScreenCountDownDelay;
	private String otherInstructionTimeout;
	private String othrtCountDelay;
	private Integer userId;
	private String zoomScale;
	private String imageX;
	private String imageY;
	private String imageHeight;
	private String imageWidth;
	// Constructors

	@Column(name = "imageX", length = 45)
	public String getImageX() {
		return imageX;
	}

	public void setImageX(String imageX) {
		this.imageX = imageX;
	}

	@Column(name = "imageY", length = 45)
	public String getImageY() {
		return imageY;
	}

	public void setImageY(String imageY) {
		this.imageY = imageY;
	}

	@Column(name = "imageHeight", length = 45)
	public String getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(String imageHeight) {
		this.imageHeight = imageHeight;
	}

	@Column(name = "imageWidth", length = 45)
	public String getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(String imageWidth) {
		this.imageWidth = imageWidth;
	}

	/** default constructor */
	public Fovbyuser() {
	}

	/** full constructor */
	public Fovbyuser(String fovTop, String fovBottom, String fovLeft,
			String fovRight, String greenScreenWidth,
			String greenScreenDistance, String greenScreenHeight,
			String greenScreenCountDownDelay, String otherInstructionTimeout,
			String othrtCountDelay, Integer userId,String zoomScale,
			String imageX,String imageY,String imageHeight,String imageWidth) {
		this.fovTop = fovTop;
		this.fovBottom = fovBottom;
		this.fovLeft = fovLeft;
		this.fovRight = fovRight;
		this.greenScreenWidth = greenScreenWidth;
		this.greenScreenDistance = greenScreenDistance;
		this.greenScreenHeight = greenScreenHeight;
		this.greenScreenCountDownDelay = greenScreenCountDownDelay;
		this.otherInstructionTimeout = otherInstructionTimeout;
		this.othrtCountDelay = othrtCountDelay;
		this.userId = userId;
		this.imageX = imageX;
		this.imageY = imageY;
		this.imageHeight = imageHeight;
		this.imageWidth = imageWidth;
		this.setZoomScale(zoomScale);
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "fovId", unique = true, nullable = false)
	public Integer getFovId() {
		return this.fovId;
	}

	public void setFovId(Integer fovId) {
		this.fovId = fovId;
	}

	@Column(name = "fovTop", length = 45)
	public String getFovTop() {
		return this.fovTop;
	}

	public void setFovTop(String fovTop) {
		this.fovTop = fovTop;
	}

	@Column(name = "fovBottom", length = 45)
	public String getFovBottom() {
		return this.fovBottom;
	}

	public void setFovBottom(String fovBottom) {
		this.fovBottom = fovBottom;
	}

	@Column(name = "fovLeft", length = 45)
	public String getFovLeft() {
		return this.fovLeft;
	}

	public void setFovLeft(String fovLeft) {
		this.fovLeft = fovLeft;
	}

	@Column(name = "fovRight", length = 45)
	public String getFovRight() {
		return this.fovRight;
	}

	public void setFovRight(String fovRight) {
		this.fovRight = fovRight;
	}

	@Column(name = "greenScreenWidth", length = 45)
	public String getGreenScreenWidth() {
		return this.greenScreenWidth;
	}

	public void setGreenScreenWidth(String greenScreenWidth) {
		this.greenScreenWidth = greenScreenWidth;
	}

	@Column(name = "greenScreenDistance", length = 45)
	public String getGreenScreenDistance() {
		return this.greenScreenDistance;
	}

	public void setGreenScreenDistance(String greenScreenDistance) {
		this.greenScreenDistance = greenScreenDistance;
	}

	@Column(name = "greenScreenHeight", length = 45)
	public String getGreenScreenHeight() {
		return this.greenScreenHeight;
	}

	public void setGreenScreenHeight(String greenScreenHeight) {
		this.greenScreenHeight = greenScreenHeight;
	}

	@Column(name = "greenScreenCountDownDelay", length = 45)
	public String getGreenScreenCountDownDelay() {
		return this.greenScreenCountDownDelay;
	}

	public void setGreenScreenCountDownDelay(String greenScreenCountDownDelay) {
		this.greenScreenCountDownDelay = greenScreenCountDownDelay;
	}

	@Column(name = "otherInstructionTimeout", length = 45)
	public String getOtherInstructionTimeout() {
		return this.otherInstructionTimeout;
	}

	public void setOtherInstructionTimeout(String otherInstructionTimeout) {
		this.otherInstructionTimeout = otherInstructionTimeout;
	}

	@Column(name = "othrtCountDelay", length = 45)
	public String getOthrtCountDelay() {
		return this.othrtCountDelay;
	}

	public void setOthrtCountDelay(String othrtCountDelay) {
		this.othrtCountDelay = othrtCountDelay;
	}

	@Column(name = "userId")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "zoomScale")
	public String getZoomScale() {
		return zoomScale;
	}

	public void setZoomScale(String zoomScale) {
		this.zoomScale = zoomScale;
	}

	@Override
	public String toString() {
		return "Fovbyuser [fovId=" + fovId + ", fovTop=" + fovTop + ", fovBottom=" + fovBottom + ", fovLeft=" + fovLeft
				+ ", fovRight=" + fovRight + ", greenScreenWidth=" + greenScreenWidth + ", greenScreenDistance="
				+ greenScreenDistance + ", greenScreenHeight=" + greenScreenHeight + ", greenScreenCountDownDelay="
				+ greenScreenCountDownDelay + ", otherInstructionTimeout=" + otherInstructionTimeout
				+ ", othrtCountDelay=" + othrtCountDelay + ", userId=" + userId + ", zoomScale=" + zoomScale
				+ ", imageX=" + imageX + ", imageY=" + imageY + ", imageHeight=" + imageHeight + ", imageWidth="
				+ imageWidth + "]";
	}
	
	

}