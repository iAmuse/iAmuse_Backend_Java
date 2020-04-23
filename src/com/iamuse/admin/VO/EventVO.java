package com.iamuse.admin.VO;

import java.util.Date;

public class EventVO
{
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
  private String eventType = "";
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
  private String isName;
  private String isPhone;
  private String eventTimezone;
  
  public String getEventHostEmail()
  {
    return this.eventHostEmail;
  }
  
  public void setEventHostEmail(String eventHostEmail)
  {
    this.eventHostEmail = eventHostEmail;
  }
  
  public Integer getTotalGuestSession()
  {
    return this.totalGuestSession;
  }
  
  public void setTotalGuestSession(Integer totalGuestSession)
  {
    this.totalGuestSession = totalGuestSession;
  }
  
  public String getZoomScale()
  {
    return this.zoomScale;
  }
  
  public void setZoomScale(String zoomScale)
  {
    this.zoomScale = zoomScale;
  }
  
  public String getFacebook()
  {
    return this.facebook;
  }
  
  public void setFacebook(String facebook)
  {
    this.facebook = facebook;
  }
  
  public String getTwitter()
  {
    return this.twitter;
  }
  
  public void setTwitter(String twitter)
  {
    this.twitter = twitter;
  }
  
  public String getEmailBody()
  {
    return this.emailBody;
  }
  
  public void setEmailBody(String emailBody)
  {
    this.emailBody = emailBody;
  }
  
  public String getPaymentAction()
  {
    return this.paymentAction;
  }
  
  public void setPaymentAction(String paymentAction)
  {
    this.paymentAction = paymentAction;
  }
  
  public String getAmount()
  {
    return this.amount;
  }
  
  public void setAmount(String amount)
  {
    this.amount = amount;
  }
  
  public String getCardType()
  {
    return this.cardType;
  }
  
  public void setCardType(String cardType)
  {
    this.cardType = cardType;
  }
  
  public String getAcct()
  {
    return this.acct;
  }
  
  public void setAcct(String acct)
  {
    this.acct = acct;
  }
  
  public String getMonth()
  {
    return this.month;
  }
  
  public void setMonth(String month)
  {
    this.month = month;
  }
  
  public String getYear()
  {
    return this.year;
  }
  
  public void setYear(String year)
  {
    this.year = year;
  }
  
  public String getBuyerEmailId()
  {
    return this.buyerEmailId;
  }
  
  public void setBuyerEmailId(String buyerEmailId)
  {
    this.buyerEmailId = buyerEmailId;
  }
  
  public String getCvv2()
  {
    return this.cvv2;
  }
  
  public void setCvv2(String cvv2)
  {
    this.cvv2 = cvv2;
  }
  
  public String getFirstName()
  {
    return this.firstName;
  }
  
  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }
  
  public String getLastName()
  {
    return this.lastName;
  }
  
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }
  
  public String getStreet()
  {
    return this.street;
  }
  
  public void setStreet(String street)
  {
    this.street = street;
  }
  
  public String getCity()
  {
    return this.city;
  }
  
  public void setCity(String city)
  {
    this.city = city;
  }
  
  public String getState()
  {
    return this.state;
  }
  
  public void setState(String state)
  {
    this.state = state;
  }
  
  public String getZip()
  {
    return this.zip;
  }
  
  public void setZip(String zip)
  {
    this.zip = zip;
  }
  
  public String getCountryCode()
  {
    return this.countryCode;
  }
  
  public void setCountryCode(String countryCode)
  {
    this.countryCode = countryCode;
  }
  
  public Integer getEId()
  {
    return this.EId;
  }
  
  public void setEId(Integer eId)
  {
    this.EId = eId;
  }
  
  public String getEventName()
  {
    return this.eventName;
  }
  
  public void setEventName(String eventName)
  {
    this.eventName = eventName;
  }
  
  public String getEventStart()
  {
    return this.eventStart;
  }
  
  public void setEventStart(String eventStart)
  {
    this.eventStart = eventStart;
  }
  
  public Date getEventEnd()
  {
    return this.eventEnd;
  }
  
  public void setEventEnd(Date eventEnd)
  {
    this.eventEnd = eventEnd;
  }
  
  public String getEventLocation()
  {
    return this.eventLocation;
  }
  
  public void setEventLocation(String eventLocation)
  {
    this.eventLocation = eventLocation;
  }
  
  public String getEventHostMailerId()
  {
    return this.eventHostMailerId;
  }
  
  public void setEventHostMailerId(String eventHostMailerId)
  {
    this.eventHostMailerId = eventHostMailerId;
  }
  
  public Date getCreatedDate()
  {
    return this.createdDate;
  }
  
  public void setCreatedDate(Date createdDate)
  {
    this.createdDate = createdDate;
  }
  
  public Integer getCreatedBy()
  {
    return this.createdBy;
  }
  
  public void setCreatedBy(Integer createdBy)
  {
    this.createdBy = createdBy;
  }
  
  public Date getUpdatedDate()
  {
    return this.updatedDate;
  }
  
  public void setUpdatedDate(Date updatedDate)
  {
    this.updatedDate = updatedDate;
  }
  
  public Integer getUpdatedBy()
  {
    return this.updatedBy;
  }
  
  public void setUpdatedBy(Integer updatedBy)
  {
    this.updatedBy = updatedBy;
  }
  
  public String getSponsorName()
  {
    return this.sponsorName;
  }
  
  public void setSponsorName(String sponsorName)
  {
    this.sponsorName = sponsorName;
  }
  
  public void setResult(String result)
  {
    this.result = result;
  }
  
  public String getResult()
  {
    return this.result;
  }
  
  public String getCreaterName()
  {
    return this.createrName;
  }
  
  public void setCreaterName(String createrName)
  {
    this.createrName = createrName;
  }
  
  public Integer getPicId()
  {
    return Integer.valueOf(this.picId);
  }
  
  public void setPicId(int picId)
  {
    this.picId = picId;
  }
  
  public String getEventType()
  {
    return this.eventType;
  }
  
  public void setEventType(String eventType)
  {
    this.eventType = eventType;
  }
  
  public Integer getDefaultId()
  {
    return this.defaultId;
  }
  
  public void setDefaultId(Integer defaultId)
  {
    this.defaultId = defaultId;
  }
  
  public String getIsSubscribed()
  {
    return this.isSubscribed;
  }
  
  public void setIsSubscribed(String isSubscribed)
  {
    this.isSubscribed = isSubscribed;
  }
  
  public String getEventTimezone()
  {
    return this.eventTimezone;
  }
  
  public void setEventTimezone(String eventTimezone)
  {
    this.eventTimezone = eventTimezone;
  }
  
  public String getIsName()
  {
    return this.isName;
  }
  
  public void setIsName(String isName)
  {
    this.isName = isName;
  }
  
  public String getIsPhone()
  {
    return this.isPhone;
  }
  
  public void setIsPhone(String isPhone)
  {
    this.isPhone = isPhone;
  }

@Override
public String toString() {
	return "EventVO [EId=" + EId + ", eventName=" + eventName + ", eventStart=" + eventStart + ", eventEnd=" + eventEnd
			+ ", eventLocation=" + eventLocation + ", eventHostMailerId=" + eventHostMailerId + ", createdDate="
			+ createdDate + ", createdBy=" + createdBy + ", updatedDate=" + updatedDate + ", updatedBy=" + updatedBy
			+ ", sponsorName=" + sponsorName + ", createrName=" + createrName + ", picId=" + picId + ", eventType="
			+ eventType + ", defaultId=" + defaultId + ", paymentAction=" + paymentAction + ", amount=" + amount
			+ ", cardType=" + cardType + ", acct=" + acct + ", month=" + month + ", year=" + year + ", buyerEmailId="
			+ buyerEmailId + ", cvv2=" + cvv2 + ", firstName=" + firstName + ", lastName=" + lastName + ", street="
			+ street + ", city=" + city + ", state=" + state + ", zip=" + zip + ", countryCode=" + countryCode
			+ ", result=" + result + ", facebook=" + facebook + ", twitter=" + twitter + ", emailBody=" + emailBody
			+ ", zoomScale=" + zoomScale + ", eventHostEmail=" + eventHostEmail + ", totalGuestSession="
			+ totalGuestSession + ", isSubscribed=" + isSubscribed + ", isName=" + isName + ", isPhone=" + isPhone
			+ ", eventTimezone=" + eventTimezone + "]";
}
  
  
}
