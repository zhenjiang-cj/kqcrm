package com.nl.portal.dt;

import java.io.Serializable;


public class KmCompanyUser implements Serializable{
	private String company_user_sno;
	private String company_user_id;
	private String company_user_name;
	private String user_pwd;
	private String user_type;
	private String start_date_cu;
	private String end_date_cu;
	private String create_id;
	private String create_date;
	private String status;
	public String getCompany_user_sno() {
		return company_user_sno;
	}
	public void setCompany_user_sno(String company_user_sno) {
		this.company_user_sno = company_user_sno;
	}
	public String getCompany_user_id() {
		return company_user_id;
	}
	public void setCompany_user_id(String company_user_id) {
		this.company_user_id = company_user_id;
	}
	public String getCompany_user_name() {
		return company_user_name;
	}
	public void setCompany_user_name(String company_user_name) {
		this.company_user_name = company_user_name;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public String getStart_date_cu() {
		return start_date_cu;
	}
	public void setStart_date_cu(String start_date_cu) {
		this.start_date_cu = start_date_cu;
	}
	public String getEnd_date_cu() {
		return end_date_cu;
	}
	public void setEnd_date_cu(String end_date_cu) {
		this.end_date_cu = end_date_cu;
	}
	public String getCreate_id() {
		return create_id;
	}
	public void setCreate_id(String create_id) {
		this.create_id = create_id;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
