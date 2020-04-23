package com.iamuse.server.responseVO;


public class BoothAdminLoginResponseVO {
	private Integer userId;
	private String username;
	//private String password;
	private String emailId;
	private Integer subId;
	private String createdDate;
	private String updatedDate;
	private String contactNumber;
	private String subUpdatedDate;
	private String userRole;
	private String location;
	private String userType;
	private String token;
//	private String fovTop;
//	private String fovBottom;
//	private String fovLeft;
//	private String fovRight;
//	private String greenScreenWidth;
//	private String greenScreenDistance;
//	private String greenScreenHeight;
//	private String greenScreenCountDownDelay;
//	private String otherIntractionTimout;
//	private String otherCountdownDelay;

	
	
	private String pin;
	private Boolean status;
	private Boolean isDeleted;
	
	private String hexValueDefault;
	private String rgbValueDefault;
	private String rgbaValueDefault;
	private Integer currentImageId;
	private Boolean isDefaultRgb;
	private String hexValueManual;
	private String rgbValueManual;
	private String rgbaValueManual;
	private String lastname;
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
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Integer getSubId() {
		return subId;
	}
	public void setSubId(Integer subId) {
		this.subId = subId;
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
	public String getHexValueDefault() {
		return hexValueDefault;
	}
	public void setHexValueDefault(String hexValueDefault) {
		this.hexValueDefault = hexValueDefault;
	}
	public String getRgbValueDefault() {
		return rgbValueDefault;
	}
	public void setRgbValueDefault(String rgbValueDefault) {
		this.rgbValueDefault = rgbValueDefault;
	}
	public String getRgbaValueDefault() {
		return rgbaValueDefault;
	}
	public void setRgbaValueDefault(String rgbaValueDefault) {
		this.rgbaValueDefault = rgbaValueDefault;
	}
	public Integer getCurrentImageId() {
		return currentImageId;
	}
	public void setCurrentImageId(Integer currentImageId) {
		this.currentImageId = currentImageId;
	}
	public Boolean getIsDefaultRgb() {
		return isDefaultRgb;
	}
	public void setIsDefaultRgb(Boolean isDefaultRgb) {
		this.isDefaultRgb = isDefaultRgb;
	}
	public String getHexValueManual() {
		return hexValueManual;
	}
	public void setHexValueManual(String hexValueManual) {
		this.hexValueManual = hexValueManual;
	}
	public String getRgbValueManual() {
		return rgbValueManual;
	}
	public void setRgbValueManual(String rgbValueManual) {
		this.rgbValueManual = rgbValueManual;
	}
	public String getRgbaValueManual() {
		return rgbaValueManual;
	}
	public void setRgbaValueManual(String rgbaValueManual) {
		this.rgbaValueManual = rgbaValueManual;
	}
	
	
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "BoothAdminLoginResponseVO [userId=" + userId + ", username=" + username + ", emailId=" + emailId
				+ ", subId=" + subId + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate
				+ ", contactNumber=" + contactNumber + ", subUpdatedDate=" + subUpdatedDate + ", userRole=" + userRole
				+ ", location=" + location + ", pin=" + pin + ", status=" + status + ", isDeleted=" + isDeleted
				+ ", hexValueDefault=" + hexValueDefault + ", rgbValueDefault=" + rgbValueDefault
				+ ", rgbaValueDefault=" + rgbaValueDefault + ", currentImageId=" + currentImageId + ", isDefaultRgb="
				+ isDefaultRgb + ", hexValueManual=" + hexValueManual + ", rgbValueManual=" + rgbValueManual
				+ ", rgbaValueManual=" + rgbaValueManual + ", lastname=" + lastname + "]";
	}
	
}