package com.iamuse.server.responseVO;

import org.springframework.stereotype.Component;

@Component
public class BaseResponseVO {
	
	
	private String responseCode;
	private String responseDescription;

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode){
		this.responseCode = responseCode;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	@Override
	public String toString() {
		return "BaseResponseVO [responseCode=" + responseCode
				+ ", responseDescription=" + responseDescription + "]";
	}

	
	
}
