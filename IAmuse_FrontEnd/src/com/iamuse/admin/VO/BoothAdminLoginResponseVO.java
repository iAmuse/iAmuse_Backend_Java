package com.iamuse.admin.VO;

import java.util.Date;


public class BoothAdminLoginResponseVO {
	private Integer userId;
	private String username;
	private String emailId;
	private String planCode;
	private String createdDate;
	private String updatedDate;
	private String contactNumber;
	private String subUpdatedDate;
	private String userRole;
	private String location;

	private String fovTop;
	private String fovBottom;
	private String fovLeft;
	private String fovRight;
	private String greenScreenWidth;
	private String greenScreenDistance;
	private String greenScreenHeight;
	private String greenScreenCountDownDelay;
	private String otherIntractionTimout;
	private String otherCountdownDelay;

	
	
	
	private Boolean status;
	private Boolean isDeleted;
	
	private Date subUpdatedDateFormat;
	private String subEndDateFormat;
	
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPlanCode() {
		return planCode;
	}

	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getSubUpdatedDate() {
		return subUpdatedDate;
	}

	public void setSubUpdatedDate(String subUpdatedDate) {
		this.subUpdatedDate = subUpdatedDate;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getFovTop() {
		return fovTop;
	}

	public void setFovTop(String fovTop) {
		this.fovTop = fovTop;
	}

	public String getFovBottom() {
		return fovBottom;
	}

	public void setFovBottom(String fovBottom) {
		this.fovBottom = fovBottom;
	}

	public String getFovLeft() {
		return fovLeft;
	}

	public void setFovLeft(String fovLeft) {
		this.fovLeft = fovLeft;
	}

	public String getFovRight() {
		return fovRight;
	}

	public void setFovRight(String fovRight) {
		this.fovRight = fovRight;
	}

	public String getGreenScreenWidth() {
		return greenScreenWidth;
	}

	public void setGreenScreenWidth(String greenScreenWidth) {
		this.greenScreenWidth = greenScreenWidth;
	}

	public String getGreenScreenDistance() {
		return greenScreenDistance;
	}

	public void setGreenScreenDistance(String greenScreenDistance) {
		this.greenScreenDistance = greenScreenDistance;
	}

	public String getGreenScreenHeight() {
		return greenScreenHeight;
	}

	public void setGreenScreenHeight(String greenScreenHeight) {
		this.greenScreenHeight = greenScreenHeight;
	}

	public String getGreenScreenCountDownDelay() {
		return greenScreenCountDownDelay;
	}

	public void setGreenScreenCountDownDelay(String greenScreenCountDownDelay) {
		this.greenScreenCountDownDelay = greenScreenCountDownDelay;
	}

	public String getOtherIntractionTimout() {
		return otherIntractionTimout;
	}

	public void setOtherIntractionTimout(String otherIntractionTimout) {
		this.otherIntractionTimout = otherIntractionTimout;
	}

	public String getOtherCountdownDelay() {
		return otherCountdownDelay;
	}

	public void setOtherCountdownDelay(String otherCountdownDelay) {
		this.otherCountdownDelay = otherCountdownDelay;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getSubUpdatedDateFormat() {
		return subUpdatedDateFormat;
	}

	public void setSubUpdatedDateFormat(Date subUpdatedDateFormat) {
		this.subUpdatedDateFormat = subUpdatedDateFormat;
	}

	public String getSubEndDateFormat() {
		return subEndDateFormat;
	}

	public void setSubEndDateFormat(String subEndDateFormat) {
		this.subEndDateFormat = subEndDateFormat;
	}

	@Override
	public String toString() {
		return "BoothAdminLoginResponseVO [userId=" + userId + ", username=" + username + ", emailId=" + emailId
				+ ", subId=" + planCode + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate
				+ ", contactNumber=" + contactNumber + ", subUpdatedDate=" + subUpdatedDate + ", userRole=" + userRole
				+ ", location=" + location + ", fovTop=" + fovTop + ", fovBottom=" + fovBottom + ", fovLeft=" + fovLeft
				+ ", fovRight=" + fovRight + ", greenScreenWidth=" + greenScreenWidth + ", greenScreenDistance="
				+ greenScreenDistance + ", greenScreenHeight=" + greenScreenHeight + ", greenScreenCountDownDelay="
				+ greenScreenCountDownDelay + ", otherIntractionTimout=" + otherIntractionTimout
				+ ", otherCountdownDelay=" + otherCountdownDelay + ", status=" + status + ", isDeleted=" + isDeleted
				+ ", subUpdatedDateFormat=" + subUpdatedDateFormat + ", subEndDateFormat=" + subEndDateFormat + "]";
	}

	
	
}