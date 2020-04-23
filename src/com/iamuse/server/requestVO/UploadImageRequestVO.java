package com.iamuse.server.requestVO;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy(true)
@Component
public class UploadImageRequestVO extends BaseRequestVO {

	
	private String images;

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
	
	
	

}
