package com.iamuse.server.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.iamuse.server.responseVO.BaseResponseVO;
import com.iamuse.server.responseVO.CrashLogsResponseVO;
import com.iamuse.server.responseVO.UploadImageResponseVO;
import com.iamuse.server.util.IAmuseUtil;



@Lazy(true)
@Component
public class ResponseHelper {
	
	@Autowired
	private UploadImageResponseVO uploadImageResponseVO;
	@Autowired
	private BaseResponseVO baseResponseVO;
	@Autowired
	private CrashLogsResponseVO crashLogsResponseVO;
	
	public UploadImageResponseVO generateImageUploadResponse(String responseCode)
	{
		
		uploadImageResponseVO.setResponseCode(IAmuseUtil.responseSplitter(responseCode).get(0));
		uploadImageResponseVO.setResponseDescription(IAmuseUtil.responseSplitter(responseCode).get(1));
		uploadImageResponseVO.setImageId(0);
		
		return uploadImageResponseVO;
	}
	
	public BaseResponseVO generateResponse(String responseCode)
	{
		
		baseResponseVO.setResponseCode(IAmuseUtil.responseSplitter(responseCode).get(0));
		baseResponseVO.setResponseDescription(IAmuseUtil.responseSplitter(responseCode).get(1));
		//uploadImageResponseVO.setImageId(0);
		
		return baseResponseVO;
	}

	public CrashLogsResponseVO generateCrashLogsUploadResponse(String responseCode) 
	 {
	  
	  crashLogsResponseVO.setResponseCode(IAmuseUtil.responseSplitter(responseCode).get(0));
	  crashLogsResponseVO.setResponseDescription(IAmuseUtil.responseSplitter(responseCode).get(1));
	  
	  return crashLogsResponseVO;
	 }
	
}
