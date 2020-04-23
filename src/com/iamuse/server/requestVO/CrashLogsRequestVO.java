package com.iamuse.server.requestVO;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy(true)
@Component
public class CrashLogsRequestVO extends BaseRequestVO{
	
	
	private List<FileVO> files;

	public void setFiles(List<FileVO> files) {
		this.files = files;
	}

	public List<FileVO> getFiles() {
		return files;
	}
	

	
	
	
	


}
