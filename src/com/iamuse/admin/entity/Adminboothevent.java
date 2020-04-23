package com.iamuse.admin.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Adminboothevent entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "adminboothevent", catalog = "iamuse_internal")
public class Adminboothevent implements java.io.Serializable {

	// Fields

	private Integer EId;
	private String eventName;
	private Date eventStart;
	private Date eventEnd;
	private String eventLocation;
	private String eventHostMailerId;
	private Date createdDate;
	private Integer createdBy;
	private Date updatedDate;
	private Integer updatedBy;
	private String sponsorName;
	private String createrName;
	private Boolean isDeleted;
	private Boolean status;
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
	private String eventType;
	@Column(name = "thankYouScreen", length = 1000)
	private String thankYouScreen;
	@Column(name = "cameraTVScreenSaver", length = 1000)
	private String cameraTVScreenSaver;
	@Column(name = "lookAtTouchScreen", length = 1000)
	private String lookAtTouchScreen;
	@Column(name = "waterMarkImage", length = 2000)
	private String waterMarkImage;
	
	
	@Column(name = "facebook", length = 250)
	private String facebook;
	@Column(name = "twitter", length = 250)
	private String twitter;
	@Column(name = "emailBody", length = 1000)
	private String emailBody;
	
	
	@Column(name = "preSetThankYouScreen")
	private Boolean preSetThankYouScreen;

	
	@Column(name = "preCameraTVScreenSaver")
	private Boolean preCameraTVScreenSaver;
	
	@Column(name = "preLookAtTouchScreen")
	private Boolean preLookAtTouchScreen;
	
	@Column(name = "preWaterMarkImage")
	private Boolean preWaterMarkImage;

	
	@Column(name = "zoomScale")
	private String zoomScale;
	
	@Column(name = "isSubscribed")
	private Boolean isSubscribed;
	
	@Column(name = "eventTimezone")
	private String eventTimezone;
	
	@Column(name = "isName")
	private Boolean isName;
	
	@Column(name = "isPhone")
	private Boolean isPhone;
	
	
	public String getZoomScale() {
		return zoomScale;
	}

	public void setZoomScale(String zoomScale) {
		this.zoomScale = zoomScale;
	}

	/** default constructor */
	public Adminboothevent() {
	}

	/** full constructor */
	

	

	public Adminboothevent(Integer eId, String eventName, Date eventStart, Date eventEnd, String eventLocation,
			String eventHostMailerId, Date createdDate, Integer createdBy, Date updatedDate, Integer updatedBy,
			String sponsorName, String createrName, Boolean isDeleted, Boolean status, String fovTop, String fovBottom,
			String fovLeft, String fovRight, String greenScreenWidth, String greenScreenDistance,
			String greenScreenHeight, String greenScreenCountdownDelay, String otherIntractionTimout,
			String otherCountdownDelay, String eventType, String thankYouScreen, String cameraTVScreenSaver,
			String lookAtTouchScreen, String waterMarkImage, String facebook, String twitter, String emailBody,
			Boolean preSetThankYouScreen, Boolean preCameraTVScreenSaver, Boolean preLookAtTouchScreen,
			Boolean preWaterMarkImage,String zoomScale) {
		
		this.EId = eId;
		this.eventName = eventName;
		this.eventStart = eventStart;
		this.eventEnd = eventEnd;
		this.eventLocation = eventLocation;
		this.eventHostMailerId = eventHostMailerId;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.sponsorName = sponsorName;
		this.createrName = createrName;
		this.isDeleted = isDeleted;
		this.status = status;
		this.fovTop = fovTop;
		this.fovBottom = fovBottom;
		this.fovLeft = fovLeft;
		this.fovRight = fovRight;
		this.greenScreenWidth = greenScreenWidth;
		this.greenScreenDistance = greenScreenDistance;
		this.greenScreenHeight = greenScreenHeight;
		this.greenScreenCountdownDelay = greenScreenCountdownDelay;
		this.otherIntractionTimout = otherIntractionTimout;
		this.otherCountdownDelay = otherCountdownDelay;
		this.eventType = eventType;
		this.thankYouScreen = thankYouScreen;
		this.cameraTVScreenSaver = cameraTVScreenSaver;
		this.lookAtTouchScreen = lookAtTouchScreen;
		this.waterMarkImage = waterMarkImage;
		this.facebook = facebook;
		this.twitter = twitter;
		this.emailBody = emailBody;
		this.preSetThankYouScreen = preSetThankYouScreen;
		this.preCameraTVScreenSaver = preCameraTVScreenSaver;
		this.preLookAtTouchScreen = preLookAtTouchScreen;
		this.preWaterMarkImage = preWaterMarkImage;
		this.zoomScale=zoomScale;
	}

	public String getThankYouScreen() {
		return thankYouScreen;
	}
	
	public Boolean getPreSetThankYouScreen() {
		return preSetThankYouScreen;
	}

	public void setPreSetThankYouScreen(Boolean preSetThankYouScreen) {
		this.preSetThankYouScreen = preSetThankYouScreen;
	}

	public Boolean getPreCameraTVScreenSaver() {
		return preCameraTVScreenSaver;
	}

	public void setPreCameraTVScreenSaver(Boolean preCameraTVScreenSaver) {
		this.preCameraTVScreenSaver = preCameraTVScreenSaver;
	}

	public Boolean getPreLookAtTouchScreen() {
		return preLookAtTouchScreen;
	}

	public void setPreLookAtTouchScreen(Boolean preLookAtTouchScreen) {
		this.preLookAtTouchScreen = preLookAtTouchScreen;
	}

	public Boolean getPreWaterMarkImage() {
		return preWaterMarkImage;
	}

	public void setPreWaterMarkImage(Boolean preWaterMarkImage) {
		this.preWaterMarkImage = preWaterMarkImage;
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

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "e_id", unique = true, nullable = false)
	public Integer getEId() {
		return this.EId;
	}

	public void setEId(Integer EId) {
		this.EId = EId;
	}

	@Column(name = "event_name", length = 100)
	public String getEventName() {
		return this.eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "event_start", length = 10)
	public Date getEventStart() {
		return this.eventStart;
	}

	public void setEventStart(Date eventStart) {
		this.eventStart = eventStart;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "event_end", length = 10)
	public Date getEventEnd() {
		return this.eventEnd;
	}

	public void setEventEnd(Date eventEnd) {
		this.eventEnd = eventEnd;
	}

	@Column(name = "event_location", length = 45)
	public String getEventLocation() {
		return this.eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	@Column(name = "event_host_mailer_id")
	public String getEventHostMailerId() {
		return this.eventHostMailerId;
	}

	public void setEventHostMailerId(String eventHostMailerId) {
		this.eventHostMailerId = eventHostMailerId;
	}

	@Column(name = "created_date", length = 19)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	@Column(name = "sponsorName", length = 100)
	public String getSponsorName() {
		return this.sponsorName;
	}

	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}

	@Column(name = "createrName", length = 45)
	public String getCreaterName() {
		return this.createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
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

	@Column(name = "greenScreenCountdownDelay", length = 45)
	public String getGreenScreenCountdownDelay() {
		return this.greenScreenCountdownDelay;
	}

	public void setGreenScreenCountdownDelay(String greenScreenCountdownDelay) {
		this.greenScreenCountdownDelay = greenScreenCountdownDelay;
	}

	@Column(name = "otherIntractionTimout", length = 45)
	public String getOtherIntractionTimout() {
		return this.otherIntractionTimout;
	}

	public void setOtherIntractionTimout(String otherIntractionTimout) {
		this.otherIntractionTimout = otherIntractionTimout;
	}

	@Column(name = "otherCountdownDelay", length = 45)
	public String getOtherCountdownDelay() {
		return this.otherCountdownDelay;
	}

	public void setOtherCountdownDelay(String otherCountdownDelay) {
		this.otherCountdownDelay = otherCountdownDelay;
	}

	@Column(name = "eventType", length = 45)
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
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

	public Boolean getIsSubscribed() {
		return isSubscribed;
	}

	public void setIsSubscribed(Boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
	}

	public String getEventTimezone() {
		return eventTimezone;
	}

	public void setEventTimezone(String eventTimezone) {
		this.eventTimezone = eventTimezone;
	}

	public Boolean getIsName() {
		return isName;
	}

	public void setIsName(Boolean isName) {
		this.isName = isName;
	}
	

	public Boolean getIsPhone() {
		return isPhone;
	}

	public void setIsPhone(Boolean isPhone) {
		this.isPhone = isPhone;
	}

	@Override
	public String toString() {
		return "Adminboothevent [EId=" + EId + ", eventName=" + eventName + ", eventStart=" + eventStart + ", eventEnd="
				+ eventEnd + ", eventLocation=" + eventLocation + ", eventHostMailerId=" + eventHostMailerId
				+ ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", updatedDate=" + updatedDate
				+ ", updatedBy=" + updatedBy + ", sponsorName=" + sponsorName + ", createrName=" + createrName
				+ ", isDeleted=" + isDeleted + ", status=" + status + ", fovTop=" + fovTop + ", fovBottom=" + fovBottom
				+ ", fovLeft=" + fovLeft + ", fovRight=" + fovRight + ", greenScreenWidth=" + greenScreenWidth
				+ ", greenScreenDistance=" + greenScreenDistance + ", greenScreenHeight=" + greenScreenHeight
				+ ", greenScreenCountdownDelay=" + greenScreenCountdownDelay + ", otherIntractionTimout="
				+ otherIntractionTimout + ", otherCountdownDelay=" + otherCountdownDelay + ", eventType=" + eventType
				+ ", thankYouScreen=" + thankYouScreen + ", cameraTVScreenSaver=" + cameraTVScreenSaver
				+ ", lookAtTouchScreen=" + lookAtTouchScreen + ", waterMarkImage=" + waterMarkImage + ", facebook="
				+ facebook + ", twitter=" + twitter + ", emailBody=" + emailBody + ", preSetThankYouScreen="
				+ preSetThankYouScreen + ", preCameraTVScreenSaver=" + preCameraTVScreenSaver
				+ ", preLookAtTouchScreen=" + preLookAtTouchScreen + ", preWaterMarkImage=" + preWaterMarkImage
				+ ", zoomScale=" + zoomScale + ", isSubscribed=" + isSubscribed + ", eventTimezone=" + eventTimezone
				+ ", isName=" + isName + ", isPhone=" + isPhone + "]";
	}

	
	
	

}