package com.iamuse.server.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BoothUploadImageEmail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "booth_upload_image_email", catalog = "iamuse_internal")
public class BoothUploadImageEmail implements java.io.Serializable {

	// Fields

	private Integer id;
	private String mailImageUrl;
	private Timestamp uploadTime;
	private String mailImageName;
	private String emailId;
	private Integer userId;
	private Timestamp mailSentTime;
	private Boolean status;
	private String photoSessionId;
	private String publicUseAck;
	private String fileName;
	private String renderVersion;
	private String share;
	private Integer downloadStatus;
	private Boolean isDeleted;
	private Integer eventId;
	private String newsletterOptIn;
	private Integer defaultId;
	private String guestUserName;
	private String guestMobileNo;
	private String imageTimestamp;
	private String guestSessions;

	@Column(name="picId")
	private Integer picId;
	@Column(name="sessionTime")
	private String sessionTime;
	
	// Constructors

	/** default constructor */
	public BoothUploadImageEmail() {
	}

	/** full constructor */
	public BoothUploadImageEmail(String mailImageUrl, Timestamp uploadTime,
			String mailImageName, String emailId, Integer userId,
			Timestamp mailSentTime, Boolean status, String photoSessionId,
			String publicUseAck, String fileName, String renderVersion,
			String share, Integer downloadStatus, Boolean isDeleted,
			Integer eventId, String newsletterOptIn, Integer defaultId,
			String guestUserName, String guestMobileNo,Integer picId,String sessionTime,String guestSessions) {
		this.sessionTime=sessionTime;
		this.picId=picId;
		this.mailImageUrl = mailImageUrl;
		this.uploadTime = uploadTime;
		this.mailImageName = mailImageName;
		this.emailId = emailId;
		this.userId = userId;
		this.mailSentTime = mailSentTime;
		this.status = status;
		this.photoSessionId = photoSessionId;
		this.publicUseAck = publicUseAck;
		this.fileName = fileName;
		this.renderVersion = renderVersion;
		this.share = share;
		this.downloadStatus = downloadStatus;
		this.isDeleted = isDeleted;
		this.eventId = eventId;
		this.newsletterOptIn = newsletterOptIn;
		this.defaultId = defaultId;
		this.guestUserName = guestUserName;
		this.guestMobileNo = guestMobileNo;
		this.guestSessions=guestSessions;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "mail_image_url", length = 1000)
	public String getMailImageUrl() {
		return this.mailImageUrl;
	}

	public void setMailImageUrl(String mailImageUrl) {
		this.mailImageUrl = mailImageUrl;
	}

	@Column(name = "upload_time", length = 45)
	public Timestamp getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}

	@Column(name = "mail_image_name", length = 45)
	public String getMailImageName() {
		return this.mailImageName;
	}

	public void setMailImageName(String mailImageName) {
		this.mailImageName = mailImageName;
	}

	@Column(name = "email_id", length = 45)
	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Column(name = "user_id", length = 45)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "mail_sent_time", length = 19)
	public Timestamp getMailSentTime() {
		return this.mailSentTime;
	}

	public void setMailSentTime(Timestamp mailSentTime) {
		this.mailSentTime = mailSentTime;
	}

	@Column(name = "status")
	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Column(name = "photoSessionId", length = 45)
	public String getPhotoSessionId() {
		return this.photoSessionId;
	}

	public void setPhotoSessionId(String photoSessionId) {
		this.photoSessionId = photoSessionId;
	}

	@Column(name = "publicUseAck", length = 45)
	public String getPublicUseAck() {
		return this.publicUseAck;
	}

	public void setPublicUseAck(String publicUseAck) {
		this.publicUseAck = publicUseAck;
	}

	@Column(name = "fileName", length = 45)
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "renderVersion", length = 45)
	public String getRenderVersion() {
		return this.renderVersion;
	}

	public void setRenderVersion(String renderVersion) {
		this.renderVersion = renderVersion;
	}

	@Column(name = "share", length = 45)
	public String getShare() {
		return this.share;
	}

	public void setShare(String share) {
		this.share = share;
	}

	@Column(name = "downloadStatus")
	public Integer getDownloadStatus() {
		return this.downloadStatus;
	}

	public void setDownloadStatus(Integer downloadStatus) {
		this.downloadStatus = downloadStatus;
	}

	@Column(name = "isDeleted")
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Column(name = "eventId")
	public Integer getEventId() {
		return this.eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	@Column(name = "newsletterOptIn", length = 45)
	public String getNewsletterOptIn() {
		return this.newsletterOptIn;
	}

	public void setNewsletterOptIn(String newsletterOptIn) {
		this.newsletterOptIn = newsletterOptIn;
	}

	@Column(name = "defaultId")
	public Integer getDefaultId() {
		return this.defaultId;
	}

	public void setDefaultId(Integer defaultId) {
		this.defaultId = defaultId;
	}

	@Column(name = "guestUserName", length = 45)
	public String getGuestUserName() {
		return this.guestUserName;
	}

	public void setGuestUserName(String guestUserName) {
		this.guestUserName = guestUserName;
	}

	@Column(name = "guestMobileNo", length = 45)
	public String getGuestMobileNo() {
		return this.guestMobileNo;
	}

	public void setGuestMobileNo(String guestMobileNo) {
		this.guestMobileNo = guestMobileNo;
	}

	public Integer getPicId() {
		return picId;
	}

	public void setPicId(Integer picId) {
		this.picId = picId;
	}

	public String getSessionTime() {
		return sessionTime;
	}

	public void setSessionTime(String sessionTime) {
		this.sessionTime = sessionTime;
	}

	@Column(name="imageTimestamp")
	public String getImageTimestamp() {
		return imageTimestamp;
	}

	public void setImageTimestamp(String imageTimestamp) {
		this.imageTimestamp = imageTimestamp;
	}
	
	@Column(name = "guest_sessions")
	public String getGuestSessions() {
		return guestSessions;
	}

	public void setGuestSessions(String guestSessions) {
		this.guestSessions = guestSessions;
	}

	@Override
	public String toString() {
		return "BoothUploadImageEmail [id=" + id + ", mailImageUrl=" + mailImageUrl + ", uploadTime=" + uploadTime
				+ ", mailImageName=" + mailImageName + ", emailId=" + emailId + ", userId=" + userId + ", mailSentTime="
				+ mailSentTime + ", status=" + status + ", photoSessionId=" + photoSessionId + ", publicUseAck="
				+ publicUseAck + ", fileName=" + fileName + ", renderVersion=" + renderVersion + ", share=" + share
				+ ", downloadStatus=" + downloadStatus + ", isDeleted=" + isDeleted + ", eventId=" + eventId
				+ ", newsletterOptIn=" + newsletterOptIn + ", defaultId=" + defaultId + ", guestUserName="
				+ guestUserName + ", guestMobileNo=" + guestMobileNo + ", imageTimestamp=" + imageTimestamp + ", picId="
				+ picId + ", sessionTime=" + sessionTime + "]";
	}

}