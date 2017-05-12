package com.nl.portal.actionForm;

import com.nl.base.BaseAppActionForm;

public class LoginForm extends BaseAppActionForm
{ 
	private String sno;
	private String user_id;
	private String user_name;
	private String user_pswd;
	private String msisdn;
	private String email;
	private String message = "";
	
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_pswd() {
		return user_pswd;
	}
	public void setUser_pswd(String user_pswd) {
		this.user_pswd = user_pswd;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
