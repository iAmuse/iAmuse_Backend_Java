package com.iamuse.server.requestVO;

import org.springframework.stereotype.Component;

@Component
public class FetchingEventListRequestVO {

	private Integer userId=0;
	private String subId="";

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}


}
