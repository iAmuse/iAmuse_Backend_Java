package com.iamuse.admin.VO;

import org.springframework.stereotype.Component;

@Component
public class BoothAdminRegistrationRequestVO {

	private String userName;
	private String emailId;
	private String password;
	private String location;
	private String userType="";
	private String token;
	private String contactNumber;
	private String pin;
	private String lastname;
	
    /*private LoginBoothAdminRegistrationRequestVO myBar;
	
	
	public BoothAdminRegistrationRequestVO shallowCopy() {
		BoothAdminRegistrationRequestVO newFoo = new BoothAdminRegistrationRequestVO();
	    newFoo.myBar = myBar;
	    return newFoo;
		
	}*/

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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "BoothAdminRegistrationRequestVO [userName=" + userName + ", emailId=" + emailId + ", password="
				+ password + ", location=" + location + ", userType=" + userType + ", token=" + token
				+ ", contactNumber=" + contactNumber + ", pin=" + pin + ", lastname=" + lastname + "]";
	}

	

		
}