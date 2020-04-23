package com.iamuse.admin.VO;


public class ImageFormVO  {
	
	private Integer imageId;
	private String images="";
	private Boolean status;
	private Boolean isThumbnail;
	private String uploadTime="";
	private String deviceId="";
	private Integer listSize;
	private String imagename="";
	private String rgbValue="";
	private String rgbaValue="";
	private String  hexValue="";
	private String updateTime="";
	private Integer serialNumber;
	private String deviceToken="";
	private String rgbValueMax="";
	private String hexValueMax="";
	private String rgbaValueMax="";
	private String isUpdatedRGB="";
	private String r="";
	private String g="";
	private String b="";
	private String hexValueManual="";
	private String rgbValueManual="";
	private String rgbaValueManual="";
	
	
	@Override
	public String toString() {
		return "ImageFormVO [imageId=" + imageId + ", images=" + images + ", status=" + status + ", isThumbnail="
				+ isThumbnail + ", uploadTime=" + uploadTime + ", deviceId=" + deviceId + ", listSize=" + listSize
				+ ", imagename=" + imagename + ", rgbValue=" + rgbValue + ", rgbaValue=" + rgbaValue + ", hexValue="
				+ hexValue + ", updateTime=" + updateTime + ", serialNumber=" + serialNumber + ", deviceToken="
				+ deviceToken + ", rgbValueMax=" + rgbValueMax + ", hexValueMax=" + hexValueMax + ", rgbaValueMax="
				+ rgbaValueMax + ", isUpdatedRGB=" + isUpdatedRGB + ", r=" + r + ", g=" + g + ", b=" + b
				+ ", hexValueManual=" + hexValueManual + ", rgbValueManual=" + rgbValueManual + ", rgbaValueManual="
				+ rgbaValueManual + "]";
	}
	
	public String getHexValueManual() {
		return hexValueManual;
	}
	public void setHexValueManual(String hexValueManual) {
		this.hexValueManual = hexValueManual;
	}
	public String getRgbValueManual() {
		return rgbValueManual;
	}
	public void setRgbValueManual(String rgbValueManual) {
		this.rgbValueManual = rgbValueManual;
	}
	public String getRgbaValueManual() {
		return rgbaValueManual;
	}
	public void setRgbaValueManual(String rgbaValueManual) {
		this.rgbaValueManual = rgbaValueManual;
	}
	public String getR() {
		return r;
	}
	public void setR(String r) {
		this.r = r;
	}
	public String getG() {
		return g;
	}
	public void setG(String g) {
		this.g = g;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public String getIsUpdatedRGB() {
		return isUpdatedRGB;
	}
	public void setIsUpdatedRGB(String isUpdatedRGB) {
		this.isUpdatedRGB = isUpdatedRGB;
	}
	public String getRgbValueMax() {
		return rgbValueMax;
	}
	public void setRgbValueMax(String rgbValueMax) {
		this.rgbValueMax = rgbValueMax;
	}
	public String getHexValueMax() {
		return hexValueMax;
	}
	public void setHexValueMax(String hexValueMax) {
		this.hexValueMax = hexValueMax;
	}
	public String getRgbaValueMax() {
		return rgbaValueMax;
	}
	public void setRgbaValueMax(String rgbaValueMax) {
		this.rgbaValueMax = rgbaValueMax;
	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	public Integer getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getRgbValue() {
		return rgbValue;
	}
	public void setRgbValue(String rgbValue) {
		this.rgbValue = rgbValue;
	}

	public String getRgbaValue() {
		return rgbaValue;
	}
	public void setRgbaValue(String rgbaValue) {
		this.rgbaValue = rgbaValue;
	}
	public String getHexValue() {
		return hexValue;
	}
	public void setHexValue(String hexValue) {
		this.hexValue = hexValue;
	}
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
	public Integer getListSize() {
		return listSize;
	}
	public void setListSize(Integer listSize) {
		this.listSize = listSize;
	}
	public Integer getImageId() {
		return imageId;
	}
	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Boolean getIsThumbnail() {
		return isThumbnail;
	}
	public void setIsThumbnail(Boolean isThumbnail) {
		this.isThumbnail = isThumbnail;
	}
	
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	

}
