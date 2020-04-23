package com.iamuse.server.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
	private Date createdDate;
	@Column(name="createdUserId")
	private Integer createdUserId;
	@Column(name="updatedByUserId")
	private Integer updatedByUserId;
	private Timestamp updatedDate;

	@Column(name = "updatedDate", length = 19)
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}
	@Column(name="status")
	private Boolean status;
	
	@Column(name="isDeleted")
	private Boolean isDeleted;
	
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}
	// Constructors

	/** default constructor */
	public SubscriptionMaster() {
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
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}