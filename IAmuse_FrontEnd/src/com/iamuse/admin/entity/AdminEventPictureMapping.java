package com.iamuse.admin.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AdminEventPictureMapping entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "admin_event_picture_mapping", catalog = "iamuse_internal")
public class AdminEventPictureMapping implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer picId;
	private Integer EId;
	private Integer userId;
	private Date createdDate;
	private Boolean status;
	private Boolean isDeleted;
	private String eventType;

	// Constructors

	/** default constructor */
	public AdminEventPictureMapping() {
	}

	/** full constructor */
	public AdminEventPictureMapping(Integer picId, Integer EId, Integer userId,
			Timestamp createdDate,Boolean status,Boolean isDeleted,String eventType) {
		this.picId = picId;
		this.EId = EId;
		this.userId = userId;
		this.createdDate = createdDate;
		this.status = status;
		this.isDeleted = isDeleted;
		this.setEventType(eventType);
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "pic_id")
	public Integer getPicId() {
		return this.picId;
	}

	public void setPicId(Integer picId) {
		this.picId = picId;
	}

	@Column(name = "e_id")
	public Integer getEId() {
		return this.EId;
	}

	public void setEId(Integer EId) {
		this.EId = EId;
	}

	@Column(name = "user_id")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "created_date", length = 19)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
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
	
	@Column(name="eventType")
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}


}