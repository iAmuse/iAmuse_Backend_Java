package com.iamuse.server.requestVO;

import org.springframework.stereotype.Component;

@Component
public class IOSTranscationsDetailsRequestVO {

	private Integer userId;
	private String amount;
	private String receiptData;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getReceiptData() {
		return receiptData;
	}

	public void setReceiptData(String receiptData) {
		this.receiptData = receiptData;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "IOSTranscationsDetailsRequestVO [userId=" + userId
				+ ", amount=" + amount + ", receiptData=" + receiptData + "]";
	}

}
