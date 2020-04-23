package com.iamuse.admin.VO;


public class CrashLogsFormVO  {
	private Integer serialNumber;
	
	private Integer logId;
	private String fileUrl;
	private String uploadTime;
	private String fileName;
	private Integer userId;
	private Boolean readStatus;
	
	
	
	public Integer getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public void setReadStatus(Boolean readStatus) {
		this.readStatus = readStatus;
	}
	public Boolean getReadStatus() {
		return readStatus;
	}
	
	
	
	
	
	
	
}
