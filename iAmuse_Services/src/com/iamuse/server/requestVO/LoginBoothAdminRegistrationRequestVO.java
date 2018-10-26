package com.iamuse.server.requestVO;

import org.springframework.stereotype.Component;

@Component
public class LoginBoothAdminRegistrationRequestVO {

	private String emailId;
	private String password;

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

	@Override
	public String toString() {
		return "LoginBoothAdminRegistrationRequestVO [emailId=" + emailId
				+ ", password=" + password + "]";
	}

}
