package com.nl.portal.actionForm;

import com.nl.base.BaseAppActionForm;


public class CompanyUserForm extends BaseAppActionForm{
	private String company_user_id;
	private String user_pwd;
	private String valid_pic;
	public String getCompany_user_id() {
		return company_user_id;
	}
	public void setCompany_user_id(String company_user_id) {
		this.company_user_id = company_user_id;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getValid_pic() {
		return valid_pic;
	}
	public void setValid_pic(String valid_pic) {
		this.valid_pic = valid_pic;
	}

}
