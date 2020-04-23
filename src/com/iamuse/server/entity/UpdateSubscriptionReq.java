package com.iamuse.server.entity;

import javax.persistence.Column;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import com.iamuse.server.requestVO.BaseRequestVO;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateSubscriptionReq extends  BaseRequestVO {
	
	@Column(unique = true, nullable = false)
	private int userid;
		
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	 
	public String getSubId() {
		return subId;
	}
	public void setSubId(String subId) {
		this.subId = subId;
	}
	private String subId;
}
