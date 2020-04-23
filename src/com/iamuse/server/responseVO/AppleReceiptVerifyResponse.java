package com.iamuse.server.responseVO;

public class AppleReceiptVerifyResponse {
private String status;
private VerifyAppleInAppPurchaseRecepitResponseVO receipt;
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public VerifyAppleInAppPurchaseRecepitResponseVO getReceipt() {
	return receipt;
}
public void setReceipt(VerifyAppleInAppPurchaseRecepitResponseVO receipt) {
	this.receipt = receipt;
}
@Override
public String toString() {
	return "ApplieVerifyResponse [status=" + status + ", receipt=" + receipt
			+ "]";
}


}
