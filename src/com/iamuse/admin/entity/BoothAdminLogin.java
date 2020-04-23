package com.iamuse.admin.entity;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BoothAdminLogin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "booth_admin_login", catalog = "iamuse_internal")
public class BoothAdminLogin implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String username;
	private String password;
	private Boolean status;
	private String emailId;
	private Integer subId;
	private Date createdDate;
	private Date updatedDate;
	private String contactNumber;
	private Date subUpdatedDate;
	private String userRole;
	private String location;
	private Boolean isDeleted;
	private String hexValueDefault;
	private String rgbValueDefault;
	private String rgbaValueDefault;
	private Integer currentImageId;
	private Boolean isDefaultRgb;
	private String hexValueManual;
	private String rgbValueManual;
	private String rgbaValueManual;
	private Blob image;
	private String imageFileName;
	private String facebookUrl;
	private String twitterUrl;
	private String allowGuestEmailToSelf;
	private String allowGuestGetPicBySms;
	private String sendGuestLinkToEvent;
	private String printEnable;
	private String askGuestToSignUp;
	private String signUpUrl;
	private String requireAcknowledgement;
	private String userType;
	private Integer loginTour;
	private Date subEndDate;
	private String token;
	private String pin;
	private String lastname;
	private Boolean is_annual;
	// Constructors

	/** default constructor */
	public BoothAdminLogin() {
	}

	/** full constructor */
	public BoothAdminLogin(String username, String password, boolean status,
			String emailId, Integer subId, Date createdDate,
			Date updatedDate, String contactNumber,
			Date subUpdatedDate, String userRole, String location,
			Boolean isDeleted, String hexValueDefault, String rgbValueDefault,
			String rgbaValueDefault, Integer currentImageId,
			Boolean isDefaultRgb, String hexValueManual, String rgbValueManual,
			String rgbaValueManual, Blob image, String imageFileName,
			String facebookUrl, String twitterUrl,
			String allowGuestEmailToSelf, String allowGuestGetPicBySms,
			String sendGuestLinkToEvent, String printEnable,
			String askGuestToSignUp, String signUpUrl,
			String requireAcknowledgement,String userType,Integer loginTour, Date subEndDate,String token) {
		this.username = username;
		this.password = password;
		this.status = status;
		this.emailId = emailId;
		this.subId = subId;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.contactNumber = contactNumber;
		this.subUpdatedDate = subUpdatedDate;
		this.userRole = userRole;
		this.location = location;
		this.isDeleted = isDeleted;
		this.hexValueDefault = hexValueDefault;
		this.rgbValueDefault = rgbValueDefault;
		this.rgbaValueDefault = rgbaValueDefault;
		this.currentImageId = currentImageId;
		this.isDefaultRgb = isDefaultRgb;
		this.hexValueManual = hexValueManual;
		this.rgbValueManual = rgbValueManual;
		this.rgbaValueManual = rgbaValueManual;
		this.image = image;
		this.imageFileName = imageFileName;
		this.facebookUrl = facebookUrl;
		this.twitterUrl = twitterUrl;
		this.allowGuestEmailToSelf = allowGuestEmailToSelf;
		this.allowGuestGetPicBySms = allowGuestGetPicBySms;
		this.sendGuestLinkToEvent = sendGuestLinkToEvent;
		this.printEnable = printEnable;
		this.askGuestToSignUp = askGuestToSignUp;
		this.signUpUrl = signUpUrl;
		this.requireAcknowledgement = requireAcknowledgement;
		this.userType=userType;
		this.loginTour=loginTour;
		this.subEndDate=subEndDate;
		this.token=token;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "username", length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "status")
	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Column(name = "email_id")
	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Column(name = "sub_id")
	public Integer getSubId() {
		return this.subId;
	}

	public void setSubId(Integer subId) {
		this.subId = subId;
	}

	@Column(name = "created_date", length = 19)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "updated_date", length = 19)
	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name = "contactNumber", length = 45)
	public String getContactNumber() {
		return this.contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Column(name = "sub_updated_date", length = 19)
	public Date getSubUpdatedDate() {
		return this.subUpdatedDate;
	}

	public void setSubUpdatedDate(Date subUpdatedDate) {
		this.subUpdatedDate = subUpdatedDate;
	}

	@Column(name = "userRole", length = 45)
	public String getUserRole() {
		return this.userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Column(name = "location", length = 200)
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "isDeleted")
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Column(name = "hex_value_default", length = 45)
	public String getHexValueDefault() {
		return this.hexValueDefault;
	}

	public void setHexValueDefault(String hexValueDefault) {
		this.hexValueDefault = hexValueDefault;
	}

	@Column(name = "rgb_value_default", length = 45)
	public String getRgbValueDefault() {
		return this.rgbValueDefault;
	}

	public void setRgbValueDefault(String rgbValueDefault) {
		this.rgbValueDefault = rgbValueDefault;
	}

	@Column(name = "rgba_value_default", length = 45)
	public String getRgbaValueDefault() {
		return this.rgbaValueDefault;
	}

	public void setRgbaValueDefault(String rgbaValueDefault) {
		this.rgbaValueDefault = rgbaValueDefault;
	}

	@Column(name = "current_image_id")
	public Integer getCurrentImageId() {
		return this.currentImageId;
	}

	public void setCurrentImageId(Integer currentImageId) {
		this.currentImageId = currentImageId;
	}

	@Column(name = "is_default_rgb")
	public Boolean getIsDefaultRgb() {
		return this.isDefaultRgb;
	}

	public void setIsDefaultRgb(Boolean isDefaultRgb) {
		this.isDefaultRgb = isDefaultRgb;
	}

	@Column(name = "hex_value_manual", length = 45)
	public String getHexValueManual() {
		return this.hexValueManual;
	}

	public void setHexValueManual(String hexValueManual) {
		this.hexValueManual = hexValueManual;
	}

	@Column(name = "rgb_value_manual", length = 45)
	public String getRgbValueManual() {
		return this.rgbValueManual;
	}

	public void setRgbValueManual(String rgbValueManual) {
		this.rgbValueManual = rgbValueManual;
	}

	@Column(name = "rgba_value_manual", length = 45)
	public String getRgbaValueManual() {
		return this.rgbaValueManual;
	}

	public void setRgbaValueManual(String rgbaValueManual) {
		this.rgbaValueManual = rgbaValueManual;
	}

	@Column(name = "image")
	public Blob getImage() {
		return this.image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	@Column(name = "imageFileName", length = 500)
	public String getImageFileName() {
		return this.imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	@Column(name = "facebookUrl", length = 200)
	public String getFacebookUrl() {
		return this.facebookUrl;
	}

	public void setFacebookUrl(String facebookUrl) {
		this.facebookUrl = facebookUrl;
	}

	@Column(name = "twitterUrl", length = 200)
	public String getTwitterUrl() {
		return this.twitterUrl;
	}

	public void setTwitterUrl(String twitterUrl) {
		this.twitterUrl = twitterUrl;
	}

	@Column(name = "allowGuestEmailToSelf", length = 45)
	public String getAllowGuestEmailToSelf() {
		return this.allowGuestEmailToSelf;
	}

	public void setAllowGuestEmailToSelf(String allowGuestEmailToSelf) {
		this.allowGuestEmailToSelf = allowGuestEmailToSelf;
	}

	@Column(name = "allowGuestGetPicBySMS", length = 45)
	public String getAllowGuestGetPicBySms() {
		return this.allowGuestGetPicBySms;
	}

	public void setAllowGuestGetPicBySms(String allowGuestGetPicBySms) {
		this.allowGuestGetPicBySms = allowGuestGetPicBySms;
	}

	@Column(name = "SendGuestLinkToEvent", length = 200)
	public String getSendGuestLinkToEvent() {
		return this.sendGuestLinkToEvent;
	}

	public void setSendGuestLinkToEvent(String sendGuestLinkToEvent) {
		this.sendGuestLinkToEvent = sendGuestLinkToEvent;
	}

	@Column(name = "printEnable", length = 45)
	public String getPrintEnable() {
		return this.printEnable;
	}

	public void setPrintEnable(String printEnable) {
		this.printEnable = printEnable;
	}

	@Column(name = "askGuestToSignUp", length = 200)
	public String getAskGuestToSignUp() {
		return this.askGuestToSignUp;
	}

	public void setAskGuestToSignUp(String askGuestToSignUp) {
		this.askGuestToSignUp = askGuestToSignUp;
	}

	@Column(name = "signUpUrl", length = 45)
	public String getSignUpUrl() {
		return this.signUpUrl;
	}

	public void setSignUpUrl(String signUpUrl) {
		this.signUpUrl = signUpUrl;
	}

	@Column(name = "requireAcknowledgement", length = 45)
	public String getRequireAcknowledgement() {
		return this.requireAcknowledgement;
	}

	public void setRequireAcknowledgement(String requireAcknowledgement) {
		this.requireAcknowledgement = requireAcknowledgement;
	}

	@Column(name = "userType", length = 45)
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Column(name = "loginTour")
	public Integer getLoginTour() {
		return loginTour;
	}

	public void setLoginTour(Integer loginTour) {
		this.loginTour = loginTour;
	}
	
	@Column(name = "subEndDate")
	public Date getSubEndDate() {
		return subEndDate;
	}

	public void setSubEndDate(Date subEndDate) {
		this.subEndDate = subEndDate;
	}

	@Column(name = "token")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	

	
	public Boolean getIs_annual() {
		return is_annual;
	}

	public void setIs_annual(Boolean is_annual) {
		this.is_annual = is_annual;
	}

	@Override
	public String toString() {
		return "BoothAdminLogin [userId=" + userId + ", username=" + username + ", password=" + password + ", status="
				+ status + ", emailId=" + emailId + ", subId=" + subId + ", createdDate=" + createdDate
				+ ", updatedDate=" + updatedDate + ", contactNumber=" + contactNumber + ", subUpdatedDate="
				+ subUpdatedDate + ", userRole=" + userRole + ", location=" + location + ", isDeleted=" + isDeleted
				+ ", hexValueDefault=" + hexValueDefault + ", rgbValueDefault=" + rgbValueDefault
				+ ", rgbaValueDefault=" + rgbaValueDefault + ", currentImageId=" + currentImageId + ", isDefaultRgb="
				+ isDefaultRgb + ", hexValueManual=" + hexValueManual + ", rgbValueManual=" + rgbValueManual
				+ ", rgbaValueManual=" + rgbaValueManual + ", image=" + image + ", imageFileName=" + imageFileName
				+ ", facebookUrl=" + facebookUrl + ", twitterUrl=" + twitterUrl + ", allowGuestEmailToSelf="
				+ allowGuestEmailToSelf + ", allowGuestGetPicBySms=" + allowGuestGetPicBySms + ", sendGuestLinkToEvent="
				+ sendGuestLinkToEvent + ", printEnable=" + printEnable + ", askGuestToSignUp=" + askGuestToSignUp
				+ ", signUpUrl=" + signUpUrl + ", requireAcknowledgement=" + requireAcknowledgement + ", userType="
				+ userType + ", loginTour=" + loginTour + ", subEndDate=" + subEndDate + ", token=" + token + ", pin="
				+ pin + ", lastname=" + lastname + ", is_annual=" + is_annual + "]";
	}

	

	
	
	
	

}