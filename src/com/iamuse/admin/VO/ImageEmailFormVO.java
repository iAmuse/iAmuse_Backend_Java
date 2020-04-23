package com.iamuse.admin.VO;

import java.util.Arrays;

public class ImageEmailFormVO  {
	
	private Integer id;
	private String mailImageUrl;
	private String uploadTime;
	private String mailImageName;
	private String emailId;
	private Integer userId;
	private String mailSentTime;
	private Integer serialNumber;
	private Integer downloadStatus;
	private String userName;
	private String contactNo;
	private Integer eventId;
	private String[] imageIds;
	private String eventAction;
	
	private String eventName;
	
	private String thankYouScreen;
	private String cameraTVScreenSaver;
	private String lookAtTouchScreen;
	private String waterMarkImage;
	private String mailImageUrlThumb;
	private String newsletterOptln;
	private String subscribed;
	private String totalSession;
	private String eventDate;
	private String imageTimestamp;
	
	public String getThankYouScreen() {
		return thankYouScreen;
	}
	public void setThankYouScreen(String thankYouScreen) {
		this.thankYouScreen = thankYouScreen;
	}
	public String getCameraTVScreenSaver() {
		return cameraTVScreenSaver;
	}
	public void setCameraTVScreenSaver(String cameraTVScreenSaver) {
		this.cameraTVScreenSaver = cameraTVScreenSaver;
	}
	public String getLookAtTouchScreen() {
		return lookAtTouchScreen;
	}
	public void setLookAtTouchScreen(String lookAtTouchScreen) {
		this.lookAtTouchScreen = lookAtTouchScreen;
	}
	public String getWaterMarkImage() {
		return waterMarkImage;
	}
	public void setWaterMarkImage(String waterMarkImage) {
		this.waterMarkImage = waterMarkImage;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public Integer getDownloadStatus() {
		return downloadStatus;
	}
	public void setDownloadStatus(Integer downloadStatus) {
		this.downloadStatus = downloadStatus;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public Integer getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMailImageUrl() {
		return mailImageUrl;
	}
	public void setMailImageUrl(String mailImageUrl) {
		this.mailImageUrl = mailImageUrl;
	}
	
	public String getMailImageName() {
		return mailImageName;
	}
	public void setMailImageName(String mailImageName) {
		this.mailImageName = mailImageName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getMailSentTime() {
		return mailSentTime;
	}
	public void setMailSentTime(String mailSentTime) {
		this.mailSentTime = mailSentTime;
	}
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public String[] getImageIds() {
		return imageIds;
	}
	public void setImageIds(String[] imageIds) {
		this.imageIds = imageIds;
	}
	public String getEventAction() {
		return eventAction;
	}
	public void setEventAction(String eventAction) {
		this.eventAction = eventAction;
	}
	public String getMailImageUrlThumb() {
		return mailImageUrlThumb;
	}
	public void setMailImageUrlThumb(String mailImageUrlThumb) {
		this.mailImageUrlThumb = mailImageUrlThumb;
	}
	public String getNewsletterOptln() {
		return newsletterOptln;
	}
	public void setNewsletterOptln(String newsletterOptln) {
		this.newsletterOptln = newsletterOptln;
	}
	public String getSubscribed() {
		return subscribed;
	}
	public void setSubscribed(String subscribed) {
		this.subscribed = subscribed;
	}
	public String getTotalSession() {
		return totalSession;
	}
	public void setTotalSession(String totalSession) {
		this.totalSession = totalSession;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public String getImageTimestamp() {
		return imageTimestamp;
	}
	public void setImageTimestamp(String imageTimestamp) {
		this.imageTimestamp = imageTimestamp;
	}
	@Override
	public String toString() {
		return "ImageEmailFormVO [id=" + id + ", mailImageUrl=" + mailImageUrl + ", uploadTime=" + uploadTime
				+ ", mailImageName=" + mailImageName + ", emailId=" + emailId + ", userId=" + userId + ", mailSentTime="
				+ mailSentTime + ", serialNumber=" + serialNumber + ", downloadStatus=" + downloadStatus + ", userName="
				+ userName + ", contactNo=" + contactNo + ", eventId=" + eventId + ", imageIds="
				+ Arrays.toString(imageIds) + ", eventAction=" + eventAction + ", eventName=" + eventName
				+ ", thankYouScreen=" + thankYouScreen + ", cameraTVScreenSaver=" + cameraTVScreenSaver
				+ ", lookAtTouchScreen=" + lookAtTouchScreen + ", waterMarkImage=" + waterMarkImage
				+ ", mailImageUrlThumb=" + mailImageUrlThumb + ", newsletterOptln=" + newsletterOptln + ", subscribed="
				+ subscribed + ", totalSession=" + totalSession + ", eventDate=" + eventDate + ", imageTimestamp="
				+ imageTimestamp + "]";
	}	
}