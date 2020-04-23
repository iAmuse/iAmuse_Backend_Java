package com.iamuse.server.requestVO;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.iamuse.server.entity.ZohoMarketingHub;

@Lazy(true)
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class UploadImageWithEmailRequestVO extends BaseRequestVO {

	
	//private String image;
	private List<ImageVO> images;
	private String sessionTime;
	private String emailId;
    private String userId;
    private String photoSessionId;
    private int publicUseAck;
    private int newsletterOptIn;
    private String fileName;
    private String renderVersion;
    private int share;
    private String guestName;
    private String guestMobileNumber;
    private String imageTimestamp;
          
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getGuestMobileNumber() {
		return guestMobileNumber;
	}
	public void setGuestMobileNumber(String guestMobileNumber) {
		this.guestMobileNumber = guestMobileNumber;
	}
	public List<ImageVO> getImages() {
		return images;
	}
	public void setImages(List<ImageVO> images) {
		this.images = images;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhotoSessionId() {
		return photoSessionId;
	}
	public void setPhotoSessionId(String photoSessionId) {
		this.photoSessionId = photoSessionId;
	}
	public int getPublicUseAck() {
		return publicUseAck;
	}
	public void setPublicUseAck(int publicUseAck) {
		this.publicUseAck = publicUseAck;
	}
	public int getNewsletterOptIn() {
		return newsletterOptIn;
	}
	public void setNewsletterOptIn(int newsletterOptIn) {
		this.newsletterOptIn = newsletterOptIn;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getRenderVersion() {
		return renderVersion;
	}
	public void setRenderVersion(String renderVersion) {
		this.renderVersion = renderVersion;
	}
	public int getShare() {
		return share;
	}
	public void setShare(int share) {
		this.share = share;
	}
	public String getSessionTime() {
		return sessionTime;
	}
	public void setSessionTime(String sessionTime) {
		this.sessionTime = sessionTime;
	}
	public String getImageTimestamp() {
		return imageTimestamp;
	}
	public void setImageTimestamp(String imageTimestamp) {
		this.imageTimestamp = imageTimestamp;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "UploadImageWithEmailRequestVO [images=" + images + ", sessionTime=" + sessionTime + ", emailId="
				+ emailId + ", userId=" + userId + ", photoSessionId=" + photoSessionId + ", publicUseAck="
				+ publicUseAck + ", newsletterOptIn=" + newsletterOptIn + ", fileName=" + fileName + ", renderVersion="
				+ renderVersion + ", share=" + share + ", guestName=" + guestName + ", guestMobileNumber="
				+ guestMobileNumber + ", imageTimestamp=" + imageTimestamp + "]";
	}	
}