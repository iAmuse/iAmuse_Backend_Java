package com.iamuse.admin.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CrashLogs entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "crash_logs", catalog = "iamuse_internal")
public class CrashLogs implements java.io.Serializable {

	// Fields

	private Integer id;
	private String fileUrl;
	private String fileName;
	private Timestamp uploadTime;
	private Integer userId;
	private Boolean status;
	private Boolean readStatus;

	// Constructors

	/** default constructor */
	public CrashLogs() {
	}

	/** full constructor */
	public CrashLogs(String fileUrl, String fileName, Timestamp uploadTime,
			Integer userId, Boolean status, Boolean readStatus) {
		this.fileUrl = fileUrl;
		this.fileName = fileName;
		this.uploadTime = uploadTime;
		this.userId = userId;
		this.status = status;
		this.readStatus = readStatus;
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

	@Column(name = "file_url", length = 1000)
	public String getFileUrl() {
		return this.fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	@Column(name = "file_name", length = 1000)
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "upload_time", length = 19)
	public Timestamp getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}

	@Column(name = "user_id")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "status")
	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Column(name = "read_status")
	public Boolean getReadStatus() {
		return this.readStatus;
	}

	public void setReadStatus(Boolean readStatus) {
		this.readStatus = readStatus;
	}

}