package com.iamuse.server.responseVO;

public class FOVVO extends BaseResponseVO{
 private String top;
 private String left;
 private String bottom;
 private String right;
 private String otherCountdownDelay;
 private String otherIntractionTimout;
 
	public String getOtherCountdownDelay() {
		return otherCountdownDelay;
	}
	public void setOtherCountdownDelay(String otherCountdownDelay) {
		this.otherCountdownDelay = otherCountdownDelay;
	}
	public String getOtherIntractionTimout() {
		return otherIntractionTimout;
	}
	public void setOtherIntractionTimout(String otherIntractionTimout) {
		this.otherIntractionTimout = otherIntractionTimout;
	}
	public String getTop() {
		return top;
	}
	public void setTop(String top) {
		this.top = top;
	}
	public String getLeft() {
		return left;
	}
	public void setLeft(String left) {
		this.left = left;
	}
	public String getBottom() {
		return bottom;
	}
	public void setBottom(String bottom) {
		this.bottom = bottom;
	}
	public String getRight() {
		return right;
	}
	public void setRight(String right) {
		this.right = right;
	}
	@Override
	public String toString() {
		return "FOVVO [top=" + top + ", left=" + left + ", bottom=" + bottom + ", right=" + right
				+ ", otherCountdownDelay=" + otherCountdownDelay + ", otherIntractionTimout=" + otherIntractionTimout
				+ ", getOtherCountdownDelay()=" + getOtherCountdownDelay() + ", getOtherIntractionTimout()="
				+ getOtherIntractionTimout() + ", getTop()=" + getTop() + ", getLeft()=" + getLeft() + ", getBottom()="
				+ getBottom() + ", getRight()=" + getRight() + ", getResponseCode()=" + getResponseCode()
				+ ", getResponseDescription()=" + getResponseDescription() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
	
 
}
