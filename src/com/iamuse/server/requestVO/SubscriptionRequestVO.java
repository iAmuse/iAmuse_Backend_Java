package com.iamuse.server.requestVO;

import org.springframework.stereotype.Component;

@Component
public class SubscriptionRequestVO {

	private Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "FetchingEventListRequestVO [userId=" + userId + "]";
	}

}
