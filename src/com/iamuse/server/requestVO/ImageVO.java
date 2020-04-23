package com.iamuse.server.requestVO;

import org.springframework.stereotype.Component;

@Component
public class ImageVO {
	
	
	private Integer eventId;
	private Integer defaultId;
	private Integer picId;
	public String 	image;
	
	
	//image=image.replaceAll(" ","_").toLoweCase();
	
	public Integer getPicId() {
		return picId;
	}
	public void setPicId(Integer picId) {
		this.picId = picId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public Integer getDefaultId() {
		return defaultId;
	}
	public void setDefaultId(Integer defaultId) {
		this.defaultId = defaultId;
	}
}
