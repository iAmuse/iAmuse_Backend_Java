package com.iamuse.server.responseVO;

import org.springframework.stereotype.Component;

@Component
public class RGBValueResponseVO extends BaseResponseVO {
	
	private String rgbValue;

	public String getRgbValue() {
		return rgbValue;
	}

	public void setRgbValue(String rgbValue) {
		this.rgbValue = rgbValue;
	}

	

}
