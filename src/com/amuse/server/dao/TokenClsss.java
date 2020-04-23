package com.amuse.server.dao;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import com.iamuse.server.requestVO.BaseRequestVO;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenClsss extends  BaseRequestVO {
	
	@Column(unique = true, nullable = false)
	private int userid;
	private String access_token;
	private String refresh_token;
	@Temporal(TemporalType.DATE)
	@Column(name = "created_by", length = 10)
	private Timestamp create_by;
	
	public Timestamp getCreate_by() {
		return create_by;
	}
	public void setCreate_by(Timestamp create_by) {
		this.create_by = create_by;
	}
	private int flag;
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
		
}
