package com.iamuse.server.responseVO;

import java.util.List;

public class SubscriptionMasterListResponseVO {
	
	private List<SubscriptionMasterResponseVO> subscriptionMasterResponse;

	public List<SubscriptionMasterResponseVO> getSubscriptionMasterResponse() {
		return subscriptionMasterResponse;
	}

	public void setSubscriptionMasterResponse(
			List<SubscriptionMasterResponseVO> subscriptionMasterResponse) {
		this.subscriptionMasterResponse = subscriptionMasterResponse;
	}
	
}