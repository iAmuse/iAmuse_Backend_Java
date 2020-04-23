package com.iamuse.server.requestVO;

import org.springframework.stereotype.Component;

@Component
public class ForgotPasswordRequestVO extends BaseRequestVO{
	
	private String emailId;
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getEmailId() {
		return emailId;
	}
	

}
