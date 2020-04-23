package com.iamuse.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * StatusCount entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "status_count", catalog = "iamuse_internal")
public class StatusCount implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private String emailId;
	private Integer eventId;
	private Integer repetedGuestCount;
	private Integer mailSentCount;
	private Integer facebookShareCount;
	private Integer twitterShareCount;

	// Constructors

	/** default constructor */
	public StatusCount() {
	}

	/** full constructor */
	public StatusCount(String emailId, Integer eventId,
			Integer repetedGuestCount, Integer mailSentCount,
			Integer facebookShareCount, Integer twitterShareCount) {
		this.emailId = emailId;
		this.eventId = eventId;
		this.repetedGuestCount = repetedGuestCount;
		this.mailSentCount = mailSentCount;
		this.facebookShareCount = facebookShareCount;
		this.twitterShareCount = twitterShareCount;
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

	@Column(name = "emailId", length = 45)
	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Column(name = "eventId")
	public Integer getEventId() {
		return this.eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	@Column(name = "repetedGuestCount")
	public Integer getRepetedGuestCount() {
		return this.repetedGuestCount;
	}

	public void setRepetedGuestCount(Integer repetedGuestCount) {
		this.repetedGuestCount = repetedGuestCount;
	}

	@Column(name = "mailSentCount")
	public Integer getMailSentCount() {
		return this.mailSentCount;
	}

	public void setMailSentCount(Integer mailSentCount) {
		this.mailSentCount = mailSentCount;
	}

	@Column(name = "facebookShareCount")
	public Integer getFacebookShareCount() {
		return this.facebookShareCount;
	}

	public void setFacebookShareCount(Integer facebookShareCount) {
		this.facebookShareCount = facebookShareCount;
	}

	@Column(name = "twitterShareCount")
	public Integer getTwitterShareCount() {
		return this.twitterShareCount;
	}

	public void setTwitterShareCount(Integer twitterShareCount) {
		this.twitterShareCount = twitterShareCount;
	}
	
	@Column(name = "user_id")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "StatusCount [id=" + id + ", userId=" + userId + ", emailId=" + emailId + ", eventId=" + eventId
				+ ", repetedGuestCount=" + repetedGuestCount + ", mailSentCount=" + mailSentCount
				+ ", facebookShareCount=" + facebookShareCount + ", twitterShareCount=" + twitterShareCount + "]";
	}
}