package com.iamuse.admin.VO;

import java.util.List;

import org.springframework.stereotype.Component;

import com.iamuse.admin.entity.BoothAdminLogin;

@Component
public class LoginBaseResponseVO {

	private String responseCode;
	private String responseDescription;
	
	private BoothAdminLoginResponseVO boothAdminLoginResponse;
	private List<DeviceRegistrationResponseVO> deviceRegistrationResponse;
	private List<SubscriptionMasterResponseVO> subscriptionMasterList;
//	private AdminEventPictureMappingResponse adminEventPictureMappingResponse;
		
	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	public BoothAdminLoginResponseVO getBoothAdminLoginResponse() {
		return boothAdminLoginResponse;
	}

	public void setBoothAdminLoginResponse(
			BoothAdminLoginResponseVO boothAdminLoginResponse) {
		this.boothAdminLoginResponse = boothAdminLoginResponse;
	}

	

	public List<DeviceRegistrationResponseVO> getDeviceRegistrationResponse() {
		return deviceRegistrationResponse;
	}

	public void setDeviceRegistrationResponse(
			List<DeviceRegistrationResponseVO> deviceRegistrationResponse) {
		this.deviceRegistrationResponse = deviceRegistrationResponse;
	}

	

	public List<SubscriptionMasterResponseVO> getSubscriptionMasterList() {
		return subscriptionMasterList;
	}

	public void setSubscriptionMasterList(
			List<SubscriptionMasterResponseVO> subscriptionMasterList) {
		this.subscriptionMasterList = subscriptionMasterList;
	}

	@Override
	public String toString() {
		return "LoginBaseResponseVO [responseCode=" + responseCode + ", responseDescription=" + responseDescription
				+ ", boothAdminLoginResponse=" + boothAdminLoginResponse + ", deviceRegistrationResponse="
				+ deviceRegistrationResponse + ", subscriptionMasterList=" + subscriptionMasterList + "]";
	}
	

//	public AdminEventPictureMappingResponse getAdminEventPictureMappingResponse() {
//		return adminEventPictureMappingResponse;
//	}
//
//	public void setAdminEventPictureMappingResponse(
//			AdminEventPictureMappingResponse adminEventPictureMappingResponse) {
//		this.adminEventPictureMappingResponse = adminEventPictureMappingResponse;
//	}

	

}
