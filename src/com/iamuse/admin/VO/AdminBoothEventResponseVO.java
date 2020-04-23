package com.iamuse.admin.VO;


import java.util.Date;
import java.util.List;

public class AdminBoothEventResponseVO {

	private Integer EId;
	private String eventName;
	private Date eventStart;
	private Date eventEnd;
	private String eventLocation;
	private String eventHostMailerId;
	private String createdDate;
	private Integer createdBy;
	private String updatedDate;
	private Integer updatedBy;
	private String sponsorName;
	private List<AdminPictureResponseVO> adminBoothEventPicture;
	
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
	
	//Added Dated 4-01-2017 by Abhishek Bhardwaj 
	private String thankYouScreen;
	private String cameraTVScreenSaver;
	private String lookAtTouchScreen;
	private String waterMarkImage;
	private Boolean isSubscribed;
	private Integer isPhone;
	private Integer isName;
	private String pin;
	
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

private Boolean status;
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

	public Integer getEId() {
		return EId;
	}

	public void setEId(Integer eId) {
		EId = eId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getEventStart() {
		return eventStart;
	}

	public void setEventStart(Date eventStart) {
		this.eventStart = eventStart;
	}

	public Date getEventEnd() {
		return eventEnd;
	}

	public void setEventEnd(Date eventEnd) {
		this.eventEnd = eventEnd;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getEventHostMailerId() {
		return eventHostMailerId;
	}

	public void setEventHostMailerId(String eventHostMailerId) {
		this.eventHostMailerId = eventHostMailerId;
	}

	
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	

	public List<AdminPictureResponseVO> getAdminBoothEventPicture() {
		return adminBoothEventPicture;
	}

	public void setAdminBoothEventPicture(
			List<AdminPictureResponseVO> adminBoothEventPicture) {
		this.adminBoothEventPicture = adminBoothEventPicture;
	}

	public String getSponsorName() {
		return sponsorName;
	}

	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
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

	public String getWaterMarkImage() {
		return waterMarkImage;
	}

	public void setWaterMarkImage(String waterMarkImage) {
		this.waterMarkImage = waterMarkImage;
	}

	public Boolean getIsSubscribed() {
		return isSubscribed;
	}

	public void setIsSubscribed(Boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
	}

	public Integer getIsPhone() {
		return isPhone;
	}

	public void setIsPhone(Integer isPhone) {
		this.isPhone = isPhone;
	}

	public Integer getIsName() {
		return isName;
	}

	public void setIsName(Integer isName) {
		this.isName = isName;
	}
		
	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	@Override
	public String toString() {
		return "AdminBoothEventResponseVO [EId=" + EId + ", eventName=" + eventName + ", eventStart=" + eventStart
				+ ", eventEnd=" + eventEnd + ", eventLocation=" + eventLocation + ", eventHostMailerId="
				+ eventHostMailerId + ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", updatedDate="
				+ updatedDate + ", updatedBy=" + updatedBy + ", sponsorName=" + sponsorName
				+ ", adminBoothEventPicture=" + adminBoothEventPicture + ", fovTop=" + fovTop + ", fovBottom="
				+ fovBottom + ", fovLeft=" + fovLeft + ", fovRight=" + fovRight + ", greenScreenWidth="
				+ greenScreenWidth + ", greenScreenDistance=" + greenScreenDistance + ", greenScreenHeight="
				+ greenScreenHeight + ", greenScreenCountdownDelay=" + greenScreenCountdownDelay
				+ ", otherIntractionTimout=" + otherIntractionTimout + ", otherCountdownDelay=" + otherCountdownDelay
				+ ", thankYouScreen=" + thankYouScreen + ", cameraTVScreenSaver=" + cameraTVScreenSaver
				+ ", lookAtTouchScreen=" + lookAtTouchScreen + ", waterMarkImage=" + waterMarkImage + ", isSubscribed="
				+ isSubscribed + ", isPhone=" + isPhone + ", isName=" + isName + ", pin=" + pin + ", status=" + status
				+ ", isDeleted=" + isDeleted + "]";
	}		
}