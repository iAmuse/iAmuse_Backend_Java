package com.iamuse.server.responseVO;

public class SubscriptionMasterResponseVO {

	// Fields

	private String subId;
	private String subName;
	private String subPrice;
	private String subValidaityDayPeriod;
	private String createdDate;
	private Integer createdUserId;
	private Integer updatedByUserId;
	private String updatedDate;
	private Boolean status;
	private Boolean isDeleted;

	
	private String responseCode;
	private String responseDescription;
	
	
	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getSubPrice() {
		return subPrice;
	}

	public void setSubPrice(String subPrice) {
		this.subPrice = subPrice;
	}

	public String getSubValidaityDayPeriod() {
		return subValidaityDayPeriod;
	}

	public void setSubValidaityDayPeriod(String subValidaityDayPeriod) {
		this.subValidaityDayPeriod = subValidaityDayPeriod;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	
	public Integer getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(Integer createdUserId) {
		this.createdUserId = createdUserId;
	}

	public Integer getUpdatedByUserId() {
		return updatedByUserId;
	}

	public void setUpdatedByUserId(Integer updatedByUserId) {
		this.updatedByUserId = updatedByUserId;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "SubscriptionMasterResponseVO [subId=" + subId + ", subName="
				+ subName + ", subPrice=" + subPrice
				+ ", subValidaityDayPeriod=" + subValidaityDayPeriod
				+ ", createdDate=" + createdDate + ", createdUserId="
				+ createdUserId + ", updatedByUserId=" + updatedByUserId
				+ ", updatedDate=" + updatedDate + ", status=" + status
				+ ", isDeleted=" + isDeleted + ", responseCode=" + responseCode
				+ ", responseDescription=" + responseDescription + "]";
	}
}