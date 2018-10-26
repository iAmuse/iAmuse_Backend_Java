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
 * TransactionMappingAdmin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "transaction_mapping_admin", catalog = "iamuse_internal")
public class TransactionMappingAdmin implements java.io.Serializable {

	// Fields

	private Integer mapId;
	private Integer userId;
	private Integer transactionMasterId;
	private Date date;
	

	// Constructors

	/** default constructor */
	public TransactionMappingAdmin() {
	}

	/** full constructor */
	public TransactionMappingAdmin(Integer userId, Integer transactionMasterId,
			Timestamp date) {
		this.userId = userId;
		this.transactionMasterId = transactionMasterId;
		this.date = date;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "map_id", unique = true, nullable = false)
	public Integer getMapId() {
		return this.mapId;
	}

	public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}

	@Column(name = "user_id")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "transaction_master_id")
	public Integer getTransactionMasterId() {
		return this.transactionMasterId;
	}

	public void setTransactionMasterId(Integer transactionMasterId) {
		this.transactionMasterId = transactionMasterId;
	}

	@Column(name = "date", length = 19)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	

}