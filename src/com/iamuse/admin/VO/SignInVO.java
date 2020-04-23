package com.iamuse.admin.VO;

import java.sql.Blob;
import java.util.Date;

public class SignInVO {
	
	private Integer userId;
	private String username;
	private String lastname;
	private String password;
	private Boolean status;
	private String emailId;
	private Integer subId;
	private Date createdDate;
	private Date updatedDate;
	private String contactNumber;
	private Date subUpdatedDate;
	private String pageType;
	private String fovTop;
	private String fovBottom;
	private String fovLeft;
	private String fovRight;
	private String greenScreenWidth;
	private String greenScreenDistance;
	private String greenScreenHeight;
	private String greenScreenCountdownDelay;
	private String otherIntractionTimout;
	private String otherCountdownDelay;
	private Integer EId;
	private Boolean isDeleted;
	
	private String facebookUrl;
	private String twitterUrl;
	
	private Blob image;
	
	
	private String imageFileName;
	private String userType;
	
	
	private String facebook;
	private String twitter;
	private String emailBody;
	
	private String imageX;
	private String imageY;
	private String imageHeight;
	private String imageWidth;
	private String zoomScale;
	private String save;
	private String result;
	private String token;
	private String newPassword;
	private String pin;
	
	public String getImageX() {
		return imageX;
	}
	public void setImageX(String imageX) {
		this.imageX = imageX;
	}
	public String getImageY() {
		return imageY;
	}
	public void setImageY(String imageY) {
		this.imageY = imageY;
	}
	public String getImageWidth() {
		return imageWidth;
	}
	public void setImageWidth(String imageWidth) {
		this.imageWidth = imageWidth;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	public String getEmailBody() {
		return emailBody;
	}
	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
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
	public String getGreenScreenCountdownDelay() {
		return greenScreenCountdownDelay;
	}
	public void setGreenScreenCountdownDelay(String greenScreenCountdownDelay) {
		this.greenScreenCountdownDelay = greenScreenCountdownDelay;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public Integer getSubId() {
		return subId;
	}
	public void setSubId(Integer subId) {
		this.subId = subId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public void setPageType(String pageType) {
		this.pageType = pageType;
	}
	public String getPageType() {
		return pageType;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Date getSubUpdatedDate() {
		return subUpdatedDate;
	}
	public void setSubUpdatedDate(Date subUpdatedDate) {
		this.subUpdatedDate = subUpdatedDate;
	}
	public Blob getImage() {
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public Integer getEId() {
		return EId;
	}
	public void setEId(Integer eId) {
		EId = eId;
	}
	public String getFacebookUrl() {
		return facebookUrl;
	}
	public void setFacebookUrl(String facebookUrl) {
		this.facebookUrl = facebookUrl;
	}
	public String getTwitterUrl() {
		return twitterUrl;
	}
	public void setTwitterUrl(String twitterUrl) {
		this.twitterUrl = twitterUrl;
	}
	
	public String getImageHeight() {
		return imageHeight;
	}
	public void setImageHeight(String imageHeight) {
		this.imageHeight = imageHeight;
	}
	public String getZoomScale() {
		return zoomScale;
	}
	public void setZoomScale(String zoomScale) {
		this.zoomScale = zoomScale;
	}
	public String getSave() {
		return save;
	}
	public void setSave(String save) {
		this.save = save;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	@Override
	public String toString() {
		return "SignInVO [userId=" + userId + ", username=" + username + ", lastname=" + lastname + ", password="
				+ password + ", status=" + status + ", emailId=" + emailId + ", subId=" + subId + ", createdDate="
				+ createdDate + ", updatedDate=" + updatedDate + ", contactNumber=" + contactNumber
				+ ", subUpdatedDate=" + subUpdatedDate + ", pageType=" + pageType + ", fovTop=" + fovTop
				+ ", fovBottom=" + fovBottom + ", fovLeft=" + fovLeft + ", fovRight=" + fovRight + ", greenScreenWidth="
				+ greenScreenWidth + ", greenScreenDistance=" + greenScreenDistance + ", greenScreenHeight="
				+ greenScreenHeight + ", greenScreenCountdownDelay=" + greenScreenCountdownDelay
				+ ", otherIntractionTimout=" + otherIntractionTimout + ", otherCountdownDelay=" + otherCountdownDelay
				+ ", EId=" + EId + ", isDeleted=" + isDeleted + ", facebookUrl=" + facebookUrl + ", twitterUrl="
				+ twitterUrl + ", image=" + image + ", imageFileName=" + imageFileName + ", userType=" + userType
				+ ", facebook=" + facebook + ", twitter=" + twitter + ", emailBody=" + emailBody + ", imageX=" + imageX
				+ ", imageY=" + imageY + ", imageHeight=" + imageHeight + ", imageWidth=" + imageWidth + ", zoomScale="
				+ zoomScale + ", save=" + save + ", result=" + result + ", token=" + token + ", newPassword="
				+ newPassword + ", pin=" + pin + "]";
	}		
}