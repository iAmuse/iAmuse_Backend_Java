package com.iamuse.server.requestVO;

import org.springframework.stereotype.Component;

@Component
public class FetchingEventListRequestVO {

	private Integer userId=0;
	private Integer subId=0;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSubId() {
		return subId;
	}

	public void setSubId(Integer subId) {
		this.subId = subId;
	}

	@Override
	public String toString() {
		return "FetchingEventListRequestVO [userId=" + userId + ", subId="
				+ subId + "]";
	}

}
