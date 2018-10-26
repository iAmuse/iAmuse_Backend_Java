package com.iamuse.server.requestVO;

import org.springframework.stereotype.Component;

@Component
public class BoothAdminRegistrationRequestVO {

	private String userName;
	private String emailId;
	private String password;
	private String location;
	private String userType="";
	
	private String googleId;

	
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "BoothAdminRegistrationRequestVO [userName=" + userName
				+ ", emailId=" + emailId + ", password=" + password
				+ ", location=" + location + "]";
	}

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}
}
