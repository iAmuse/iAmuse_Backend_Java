package com.iamuse.admin.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Columns;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.AccessType;
import javax.persistence.Column;

@Entity
@Table(name ="booth_admin_drive_token", catalog = "iamuse_internal")
public class AccessToken implements java.io.Serializable  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int userid; 
	private String access_token;
	private String refresh_token;
	private int flag;
	
	@Column(name = "create_by", length = 20)
	private Timestamp create_by;
	@Column(name = "updated_by", length = 20)
	private Date updated_by;

	public Date getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(Date updated_by) {
		this.updated_by = updated_by;
	}
	public AccessToken() {}	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	
	public Timestamp getCreate_by() {
		return create_by;
	}
	public void setCreate_by(Timestamp create_by) {
		this.create_by = create_by;
	}
	@Override
	public String toString() {
		return "AccessToken [id=" + id + ", userid=" + userid + ", access_token=" + access_token + ", refresh_token="
				+ refresh_token + ", flag=" + flag + ", create_by=" + create_by + ", updated_by=" + updated_by + "]";
	}
	
	
	
}
