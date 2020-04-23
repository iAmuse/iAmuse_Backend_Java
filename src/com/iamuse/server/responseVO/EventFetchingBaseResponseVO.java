package com.iamuse.server.responseVO;

import org.springframework.stereotype.Component;

@Component
public class EventFetchingBaseResponseVO {

	private String responseCode;
	private String responseDescription;
	private AdminEventPictureMappingResponse adminEventPictureMappingResponse;

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

	public AdminEventPictureMappingResponse getAdminEventPictureMappingResponse() {
		return adminEventPictureMappingResponse;
	}

	public void setAdminEventPictureMappingResponse(
			AdminEventPictureMappingResponse adminEventPictureMappingResponse) {
		this.adminEventPictureMappingResponse = adminEventPictureMappingResponse;
	}

	@Override
	public String toString() {
		return "EventFetchingBaseResponseVO [responseCode=" + responseCode
				+ ", responseDescription=" + responseDescription
				+ ", adminEventPictureMappingResponse="
				+ adminEventPictureMappingResponse + "]";
	}

}
