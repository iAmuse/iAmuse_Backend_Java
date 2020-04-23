package com.iamuse.server.responseVO;

import java.util.List;

import com.iamuse.server.entity.AdminPicture;
import com.iamuse.server.entity.Adminboothevent;

public class AdminEventPictureMappingResponse {

//	private List<Adminboothevent> event;
//	private List<AdminPicture> adminBoothEventPicture;
private List<AdminBoothEventResponseVO> modifiedResult;
	
//	public List<AdminPicture> getAdminBoothEventPicture() {
//		return adminBoothEventPicture;
//	}
//
//	public void setAdminBoothEventPicture(List<AdminPicture> adminBoothEventPicture) {
//		this.adminBoothEventPicture = adminBoothEventPicture;
//	}
//
//	public List<Adminboothevent> getEvent() {
//		return event;
//	}
//
//	public void setEvent(List<Adminboothevent> event) {
//		this.event = event;
//	}

	public List<AdminBoothEventResponseVO> getModifiedResult() {
		return modifiedResult;
	}

	public void setModifiedResult(List<AdminBoothEventResponseVO> modifiedResult) {
		this.modifiedResult = modifiedResult;
	}

	

}
