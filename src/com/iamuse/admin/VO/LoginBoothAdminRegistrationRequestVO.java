package com.iamuse.admin.VO;

import org.springframework.stereotype.Component;

@Component
public class LoginBoothAdminRegistrationRequestVO {

	private String emailId;
	private String password;
	private String username;
	private String accesstoken;
	
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
	
	public String getAccesstoken() {
		return accesstoken;
	}

	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "LoginBoothAdminRegistrationRequestVO [emailId=" + emailId + ", password=" + password + ", username="
				+ username + ", accesstoken=" + accesstoken + "]";
	}	
}