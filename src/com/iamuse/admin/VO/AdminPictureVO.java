package com.iamuse.admin.VO;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class AdminPictureVO extends EventVO {

	private Integer picId;
	private Integer defaultId;
	private String picName;
	private String picTitle;
	private String scaleXOffset;
	private String scaleYOffset;
	private String scaleZOffset;
	private String imageMask;
	private String imageWaterMark;
	private Timestamp createdDate;
	private String rgbValues;
	private Integer createdBy;
	private Timestamp updatedDate;
	private Integer updatedBy;
	private String result;
	private Integer subId;
	private String imageWidth;
	private String imageHeight;
	private String scalingWidth;
	private String scalingHeight;

	private String cropImgX = "114";
	private String cropImgY = "100";
	private String cropImgWidth = "480";
	private String cropImgHeight = "270";
	private List<MultipartFile> files;
	private String eventType = "";

	private boolean isDeleted;
	private String selectedPreImage = null;

	private String selectedPreWaterMarkImage = null;
	private String selectedPreCameraTVScreenSaver = null;
	private String selectedPreLookAtTouchScreen = null;
	private String selectedPreThankYouScreen = null;
	private String finish = null;
	private Integer position;
	private Integer positionPrev;
	private Integer positionNext;

	private String isSubscribed;
	private String eventTimezone;
	private String isName;
	private String isPhone;
	private String gridShift;

	public String getEventTimezone() {
		return eventTimezone;
	}

	public void setEventTimezone(String eventTimezone) {
		this.eventTimezone = eventTimezone;
	}

	public Integer getPositionPrev() {
		return positionPrev;
	}

	public void setPositionPrev(Integer positionPrev) {
		this.positionPrev = positionPrev;
	}

	public Integer getPositionNext() {
		return positionNext;
	}

	public void setPositionNext(Integer positionNext) {
		this.positionNext = positionNext;
	}

	public String getSelectedPreWaterMarkImage() {
		return selectedPreWaterMarkImage;
	}

	public void setSelectedPreWaterMarkImage(String selectedPreWaterMarkImage) {
		this.selectedPreWaterMarkImage = selectedPreWaterMarkImage;
	}

	public String getSelectedPreCameraTVScreenSaver() {
		return selectedPreCameraTVScreenSaver;
	}

	public void setSelectedPreCameraTVScreenSaver(String selectedPreCameraTVScreenSaver) {
		this.selectedPreCameraTVScreenSaver = selectedPreCameraTVScreenSaver;
	}

	public String getSelectedPreLookAtTouchScreen() {
		return selectedPreLookAtTouchScreen;
	}

	public void setSelectedPreLookAtTouchScreen(String selectedPreLookAtTouchScreen) {
		this.selectedPreLookAtTouchScreen = selectedPreLookAtTouchScreen;
	}

	public String getSelectedPreThankYouScreen() {
		return selectedPreThankYouScreen;
	}

	public void setSelectedPreThankYouScreen(String selectedPreThankYouScreen) {
		this.selectedPreThankYouScreen = selectedPreThankYouScreen;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getCropImgX() {
		return cropImgX;
	}

	public void setCropImgX(String cropImgX) {
		this.cropImgX = cropImgX;
	}

	public String getCropImgY() {
		return cropImgY;
	}

	public void setCropImgY(String cropImgY) {
		this.cropImgY = cropImgY;
	}

	public String getCropImgWidth() {
		return cropImgWidth;
	}

	public void setCropImgWidth(String cropImgWidth) {
		this.cropImgWidth = cropImgWidth;
	}

	public String getCropImgHeight() {
		return cropImgHeight;
	}

	public void setCropImgHeight(String cropImgHeight) {
		this.cropImgHeight = cropImgHeight;
	}

	public String getScalingWidth() {
		return scalingWidth;
	}

	public void setScalingWidth(String scalingWidth) {
		this.scalingWidth = scalingWidth;
	}

	public String getScalingHeight() {
		return scalingHeight;
	}

	public void setScalingHeight(String scalingHeight) {
		this.scalingHeight = scalingHeight;
	}

	public String getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(String imageWidth) {
		this.imageWidth = imageWidth;
	}

	public String getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(String imageHeight) {
		this.imageHeight = imageHeight;
	}

	@Override
	public Integer getCreatedBy() {
		return createdBy;
	}

	@Override
	public Integer getPicId() {
		return picId;
	}

	public void setPicId(Integer picId) {
		this.picId = picId;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getPicTitle() {
		return picTitle;
	}

	public void setPicTitle(String picTitle) {
		this.picTitle = picTitle;
	}

	public String getScaleXOffset() {
		return scaleXOffset;
	}

	public void setScaleXOffset(String scaleXOffset) {
		this.scaleXOffset = scaleXOffset;
	}

	public String getScaleYOffset() {
		return scaleYOffset;
	}

	public void setScaleYOffset(String scaleYOffset) {
		this.scaleYOffset = scaleYOffset;
	}

	public String getScaleZOffset() {
		return scaleZOffset;
	}

	public void setScaleZOffset(String scaleZOffset) {
		this.scaleZOffset = scaleZOffset;
	}

	public String getImageMask() {
		return imageMask;
	}

	public void setImageMask(String imageMask) {
		this.imageMask = imageMask;
	}

	@Override
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getRgbValues() {
		return rgbValues;
	}

	public void setRgbValues(String rgbValues) {
		this.rgbValues = rgbValues;
	}

	@Override
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public Integer getUpdatedBy() {
		return updatedBy;
	}

	@Override
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String getResult() {
		return result;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public Integer getSubId() {
		return subId;
	}

	public void setSubId(Integer subId) {
		this.subId = subId;
	}

	@Override
	public Integer getDefaultId() {
		return defaultId;
	}

	@Override
	public void setDefaultId(Integer defaultId) {
		this.defaultId = defaultId;
	}

	@Override
	public String getEventType() {
		return eventType;
	}

	@Override
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getImageWaterMark() {
		return imageWaterMark;
	}

	public void setImageWaterMark(String imageWaterMark) {
		this.imageWaterMark = imageWaterMark;
	}

	public String getSelectedPreImage() {
		return selectedPreImage;
	}

	public void setSelectedPreImage(String selectedPreImage) {
		this.selectedPreImage = selectedPreImage;
	}

	public String getFinish() {
		return finish;
	}

	public void setFinish(String finish) {
		this.finish = finish;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getIsSubscribed() {
		return isSubscribed;
	}

	public void setIsSubscribed(String isSubscribed) {
		this.isSubscribed = isSubscribed;
	}

	public String getIsName() {
		return isName;
	}

	public void setIsName(String isName) {
		this.isName = isName;
	}

	public String getIsPhone() {
		return isPhone;
	}

	public void setIsPhone(String isPhone) {
		this.isPhone = isPhone;
	}

	public String getGridShift() {
		return gridShift;
	}

	public void setGridShift(String gridShift) {
		this.gridShift = gridShift;
	}

	@Override
	public String toString() {
		return "AdminPictureVO [picId=" + picId + ", defaultId=" + defaultId + ", picName=" + picName + ", picTitle="
				+ picTitle + ", scaleXOffset=" + scaleXOffset + ", scaleYOffset=" + scaleYOffset + ", scaleZOffset="
				+ scaleZOffset + ", imageMask=" + imageMask + ", imageWaterMark=" + imageWaterMark + ", createdDate="
				+ createdDate + ", rgbValues=" + rgbValues + ", createdBy=" + createdBy + ", updatedDate=" + updatedDate
				+ ", updatedBy=" + updatedBy + ", result=" + result + ", subId=" + subId + ", imageWidth=" + imageWidth
				+ ", imageHeight=" + imageHeight + ", scalingWidth=" + scalingWidth + ", scalingHeight=" + scalingHeight
				+ ", cropImgX=" + cropImgX + ", cropImgY=" + cropImgY + ", cropImgWidth=" + cropImgWidth
				+ ", cropImgHeight=" + cropImgHeight + ", files=" + files + ", eventType=" + eventType + ", isDeleted="
				+ isDeleted + ", selectedPreImage=" + selectedPreImage + ", selectedPreWaterMarkImage="
				+ selectedPreWaterMarkImage + ", selectedPreCameraTVScreenSaver=" + selectedPreCameraTVScreenSaver
				+ ", selectedPreLookAtTouchScreen=" + selectedPreLookAtTouchScreen + ", selectedPreThankYouScreen="
				+ selectedPreThankYouScreen + ", finish=" + finish + ", position=" + position + ", positionPrev="
				+ positionPrev + ", positionNext=" + positionNext + ", isSubscribed=" + isSubscribed
				+ ", eventTimezone=" + eventTimezone + ", isName=" + isName + ", isPhone=" + isPhone + ", gridShift="
				+ gridShift + "]";
	}

}
