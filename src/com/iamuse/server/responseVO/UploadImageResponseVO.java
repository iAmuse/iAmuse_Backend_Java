package com.iamuse.server.responseVO;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
@Lazy(true)
@Component
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class UploadImageResponseVO extends BaseResponseVO {
	
	private int imageId;

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public int getImageId() {
		return imageId;
	}

	

}
