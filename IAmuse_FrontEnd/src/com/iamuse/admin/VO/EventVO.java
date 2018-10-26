package com.iamuse.admin.VO;

import java.util.Date;

/**
 * @author JAVA-04
 *
 */
public class EventVO {
	
	private Integer EId;
	private String eventName;
	private String eventStart;
	private Date eventEnd;
	private String eventLocation;
	private String eventHostMailerId;
	private Date createdDate;
	private Integer createdBy;
	private Date updatedDate;
	private Integer updatedBy;
	private String sponsorName;
	private String createrName;
	private int picId;
	private String eventType="";
	private Integer defaultId;
	
	
	 private String paymentAction;
	  private String amount;
	  private String cardType;
	  private String acct;
	  private String month;
	  private String year;
	  private String buyerEmailId;
	  private String cvv2;
	  private String firstName;
	  private String lastName;
	  private String street;
	  private String city; 
	  private String state; 
	  private String zip;
	  private String countryCode;
	  private String result;
	
	  
	  private String facebook;
		private String twitter;
		private String emailBody;
		
		private String zoomScale;
		private String eventHostEmail;
		private Integer totalGuestSession;
		private String isSubscribed;
		private String eventTimezone;
		
	public String getEventHostEmail() {
			return eventHostEmail;
		}
		public void setEventHostEmail(String eventHostEmail) {
			this.eventHostEmail = eventHostEmail;
		}
		public Integer getTotalGuestSession() {
			return totalGuestSession;
		}
		public void setTotalGuestSession(Integer totalGuestSession) {
			this.totalGuestSession = totalGuestSession;
		}
	public String getZoomScale() {
			return zoomScale;
		}
		public void setZoomScale(String zoomScale) {
			this.zoomScale = zoomScale;
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
	public String getPaymentAction() {
		return paymentAction;
	}
	public void setPaymentAction(String paymentAction) {
		this.paymentAction = paymentAction;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getAcct() {
		return acct;
	}
	public void setAcct(String acct) {
		this.acct = acct;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getBuyerEmailId() {
		return buyerEmailId;
	}
	public void setBuyerEmailId(String buyerEmailId) {
		this.buyerEmailId = buyerEmailId;
	}
	public String getCvv2() {
		return cvv2;
	}
	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
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
	public String getEventStart() {
		return eventStart;
	}
	public void setEventStart(String eventStart) {
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
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getSponsorName() {
		return sponsorName;
	}
	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getResult() {
		return result;
	}
	public String getCreaterName() {
		return createrName;
	}
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
	public Integer getPicId() {
		return picId;
	}
	public void setPicId(int picId) {
		this.picId = picId;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public Integer getDefaultId() {
		return defaultId;
	}
	public void setDefaultId(Integer defaultId) {
		this.defaultId = defaultId;
	}
	public String getIsSubscribed() {
		return isSubscribed;
	}
	public void setIsSubscribed(String isSubscribed) {
		this.isSubscribed = isSubscribed;
	}
	public String getEventTimezone() {
		return eventTimezone;
	}
	public void setEventTimezone(String eventTimezone) {
		this.eventTimezone = eventTimezone;
	}
	
	

}
