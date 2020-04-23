package com.iamuse.admin.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SubscriptionMaster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "subscription_master", catalog = "iamuse_internal")
public class SubscriptionMaster implements java.io.Serializable {

	// Fields

	private Integer subId;
	private String subName;
	private String subPrice;
	private String subValidaityDayPeriod;
	private Timestamp createdDate;
	private Integer createdUserId;
	private Integer updatedByUserId;
	private Timestamp updatedDate;
	private Boolean status;
	private Boolean isDeleted;
	private String description;
	private String deviceDescription;

	// Constructors

	/** default constructor */
	public SubscriptionMaster() {
	}

	/** full constructor */
	public SubscriptionMaster(String subName, String subPrice,
			String subValidaityDayPeriod, Timestamp createdDate,
			Integer createdUserId,Integer updatedByUserId, Timestamp updatedDate,Boolean status,Boolean isDeleted,String description,String deviceDescription) {
		this.subName = subName;
		this.subPrice = subPrice;
		this.subValidaityDayPeriod = subValidaityDayPeriod;
		this.createdDate = createdDate;
		this.createdDate =createdDate;
		this.updatedByUserId=updatedByUserId;
		this.updatedDate=updatedDate;
		this.status = status;
		this.isDeleted = isDeleted;
		this.description=description;
		this.setDeviceDescription(deviceDescription);
	}

	@Column(name="createdUserId")
	public Integer getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(Integer createdUserId) {
		this.createdUserId = createdUserId;
	}

	@Column(name="updatedByUserId")
	public Integer getUpdatedByUserId() {
		return updatedByUserId;
	}

	public void setUpdatedByUserId(Integer updatedByUserId) {
		this.updatedByUserId = updatedByUserId;
	}
	
	 @Column(name = "updatedDate", length = 19)
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "sub_id", unique = true, nullable = false)
	public Integer getSubId() {
		return this.subId;
	}

	public void setSubId(Integer subId) {
		this.subId = subId;
	}

	@Column(name = "sub_name", length = 45)
	public String getSubName() {
		return this.subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	@Column(name = "sub_price", length = 45)
	public String getSubPrice() {
		return this.subPrice;
	}

	public void setSubPrice(String subPrice) {
		this.subPrice = subPrice;
	}

	@Column(name = "sub_validaity_day_period", length = 45)
	public String getSubValidaityDayPeriod() {
		return this.subValidaityDayPeriod;
	}

	public void setSubValidaityDayPeriod(String subValidaityDayPeriod) {
		this.subValidaityDayPeriod = subValidaityDayPeriod;
	}

	@Column(name = "created_date", length = 19)
	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name = "status")
	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}


	@Column(name = "isDeleted")
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "deviceDescription")
	public String getDeviceDescription() {
		return deviceDescription;
	}

	public void setDeviceDescription(String deviceDescription) {
		this.deviceDescription = deviceDescription;
	}

	@Override
	public String toString() {
		return "SubscriptionMaster [subId=" + subId + ", subName=" + subName + ", subPrice=" + subPrice
				+ ", subValidaityDayPeriod=" + subValidaityDayPeriod + ", createdDate=" + createdDate
				+ ", createdUserId=" + createdUserId + ", updatedByUserId=" + updatedByUserId + ", updatedDate="
				+ updatedDate + ", status=" + status + ", isDeleted=" + isDeleted + ", description=" + description
				+ ", deviceDescription=" + deviceDescription + "]";
	}
	
	
}