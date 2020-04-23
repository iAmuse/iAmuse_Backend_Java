package com.iamuse.admin.entity;

import javax.persistence.Column;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import com.iamuse.admin.VO.BaseRequestVO;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateSubscriptionReq extends  BaseRequestVO {
	
	@Column(unique = true, nullable = false)
	private int userid;
	private String subId;
	
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
	@Override
	public String toString() {
		return "UpdateSubscriptionReq [userid=" + userid + ", subId=" + subId + "]";
	}	
}