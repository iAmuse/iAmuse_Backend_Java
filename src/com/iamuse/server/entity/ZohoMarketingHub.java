package com.iamuse.server.entity;

import java.util.Arrays;
import java.util.List;

public class ZohoMarketingHub {
	
	private String client_id;
	private String grant_type;
	private String client_secret;
	private String redirect_uri;
	private String code;
	private String accesstoken;
	private String listkey;
	private String emailIds[];
	
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getGrant_type() {
		return grant_type;
	}
	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}
	public String getClient_secret() {
		return client_secret;
	}
	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}
	public String getRedirect_uri() {
		return redirect_uri;
	}
	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAccesstoken() {
		return accesstoken;
	}
	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}
	public String getListkey() {
		return listkey;
	}
	public void setListkey(String listkey) {
		this.listkey = listkey;
	}
	
	public String[] getEmailIds() {
		return emailIds;
	}
	public void setEmailIds(String[] emailIds) {
		this.emailIds = emailIds;
	}
	@Override
	public String toString() {
		return "ZohoMarketingHub [client_id=" + client_id + ", grant_type=" + grant_type + ", client_secret="
				+ client_secret + ", redirect_uri=" + redirect_uri + ", code=" + code + ", accesstoken=" + accesstoken
				+ ", listkey=" + listkey + ", emailIds=" + Arrays.toString(emailIds) + "]";
	}	
}
