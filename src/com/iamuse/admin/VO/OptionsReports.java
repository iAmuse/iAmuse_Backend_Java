package com.iamuse.admin.VO;

public class OptionsReports {
	private int totalGuestSessions;
	private int	totalGuests;
	private int	repeatGuests;
	private int	photosSent;
	private int	emailsSent;
	private String	avgVisitorSession;
	private int	SignUps;
	private int	emailBounces;
	private int	facebook;
	private int	twitter;
	private int	userId;
	private int picId;
	private int eventId;
	
	
	public int getTotalGuestSessions() {
		return totalGuestSessions;
	}
	public void setTotalGuestSessions(int totalGuestSessions) {
		this.totalGuestSessions = totalGuestSessions;
	}
	public int getTotalGuests() {
		return totalGuests;
	}
	public void setTotalGuests(int totalGuests) {
		this.totalGuests = totalGuests;
	}
	public int getRepeatGuests() {
		return repeatGuests;
	}
	public void setRepeatGuests(int repeatGuests) {
		this.repeatGuests = repeatGuests;
	}
	public int getPhotosSent() {
		return photosSent;
	}
	public void setPhotosSent(int photosSent) {
		this.photosSent = photosSent;
	}
	public int getEmailsSent() {
		return emailsSent;
	}
	public void setEmailsSent(int emailsSent) {
		this.emailsSent = emailsSent;
	}
	public String getAvgVisitorSession() {
		return avgVisitorSession;
	}
	public void setAvgVisitorSession(String avgVisitorSession) {
		this.avgVisitorSession = avgVisitorSession;
	}
	public int getSignUps() {
		return SignUps;
	}
	public void setSignUps(int signUps) {
		SignUps = signUps;
	}
	public int getEmailBounces() {
		return emailBounces;
	}
	public void setEmailBounces(int emailBounces) {
		this.emailBounces = emailBounces;
	}
	public int getFacebook() {
		return facebook;
	}
	public void setFacebook(int facebook) {
		this.facebook = facebook;
	}
	public int getTwitter() {
		return twitter;
	}
	public void setTwitter(int twitter) {
		this.twitter = twitter;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPicId() {
		return picId;
	}
	public void setPicId(int picId) {
		this.picId = picId;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

}
