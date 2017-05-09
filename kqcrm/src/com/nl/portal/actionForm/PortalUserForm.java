package com.nl.portal.actionForm;

import com.nl.base.BaseAppActionForm;

/**
 *
 * @author wangtt
 * @creatdate Mar 25, 2014
 */
public class PortalUserForm extends BaseAppActionForm {
	private String hr_sno;
	private String user_id;
	private String user_name;
	private String sex;
	private String work_type;
	private String short_code;
	private String msisdn;
	private String borth_date;
	private String email;
	private String sno_dept;
	private String dept_name;
	private String dept_begin_date;
	private String dept_end_date;
	private String postCode;
	private String post_name;
	private String post_begin_date;
	private String post_end_date;
	private String town_id;
	private String town_name;
	private String town_begin_date;
	private String town_end_date;
	
	private String model_id;
	private String edit_flag ;
	
	private String post_flag;
	private String town_flag;
	
	private String rel_sno_dept;
	private String rel_sno_post;
	private String rel_sno_town;
	
	
	private String dept_county;
	private String son_dept_name;
	private String son_dept_sno;
	
	private String user_type;//人员类型
	private String user_status;//人员状态
	
	
	
	
	
	
	
	
	
	
	/**
	 * @return the user_type
	 */
	public String getUser_type() {
		return user_type;
	}
	/**
	 * @param user_type the user_type to set
	 */
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	/**
	 * @return the user_status
	 */
	public String getUser_status() {
		return user_status;
	}
	/**
	 * @param user_status the user_status to set
	 */
	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}
	public String getSon_dept_sno() {
		return son_dept_sno;
	}
	public void setSon_dept_sno(String son_dept_sno) {
		this.son_dept_sno = son_dept_sno;
	}
	public String getDept_county() {
		return dept_county;
	}
	public void setDept_county(String dept_county) {
		this.dept_county = dept_county;
	}
	public String getRel_sno_dept() {
		return rel_sno_dept;
	}
	public void setRel_sno_dept(String rel_sno_dept) {
		this.rel_sno_dept = rel_sno_dept;
	}
	public String getRel_sno_post() {
		return rel_sno_post;
	}
	public void setRel_sno_post(String rel_sno_post) {
		this.rel_sno_post = rel_sno_post;
	}
	public String getRel_sno_town() {
		return rel_sno_town;
	}
	public void setRel_sno_town(String rel_sno_town) {
		this.rel_sno_town = rel_sno_town;
	}
	public String getPost_flag() {
		return post_flag;
	}
	public void setPost_flag(String post_flag) {
		this.post_flag = post_flag;
	}
	public String getTown_flag() {
		return town_flag;
	}
	public void setTown_flag(String town_flag) {
		this.town_flag = town_flag;
	}
	public String getModel_id() {
		return model_id;
	}
	public void setModel_id(String model_id) {
		this.model_id = model_id;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getWork_type() {
		return work_type;
	}
	public void setWork_type(String work_type) {
		this.work_type = work_type;
	}
	public String getShort_code() {
		return short_code;
	}
	public void setShort_code(String short_code) {
		this.short_code = short_code;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getBorth_date() {
		return borth_date;
	}
	public void setBorth_date(String borth_date) {
		this.borth_date = borth_date;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSno_dept() {
		return sno_dept;
	}
	public void setSno_dept(String sno_dept) {
		this.sno_dept = sno_dept;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getDept_begin_date() {
		return dept_begin_date;
	}
	public void setDept_begin_date(String dept_begin_date) {
		this.dept_begin_date = dept_begin_date;
	}
	public String getDept_end_date() {
		return dept_end_date;
	}
	public void setDept_end_date(String dept_end_date) {
		this.dept_end_date = dept_end_date;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getPost_name() {
		return post_name;
	}
	public void setPost_name(String post_name) {
		this.post_name = post_name;
	}
	public String getPost_begin_date() {
		return post_begin_date;
	}
	public void setPost_begin_date(String post_begin_date) {
		this.post_begin_date = post_begin_date;
	}
	public String getPost_end_date() {
		return post_end_date;
	}
	public void setPost_end_date(String post_end_date) {
		this.post_end_date = post_end_date;
	}
	public String getTown_id() {
		return town_id;
	}
	public void setTown_id(String town_id) {
		this.town_id = town_id;
	}
	public String getTown_name() {
		return town_name;
	}
	public void setTown_name(String town_name) {
		this.town_name = town_name;
	}
	public String getTown_begin_date() {
		return town_begin_date;
	}
	public void setTown_begin_date(String town_begin_date) {
		this.town_begin_date = town_begin_date;
	}
	public String getTown_end_date() {
		return town_end_date;
	}
	public void setTown_end_date(String town_end_date) {
		this.town_end_date = town_end_date;
	}
	public String getEdit_flag() {
		return edit_flag;
	}
	public void setEdit_flag(String edit_flag) {
		this.edit_flag = edit_flag;
	}
	public String getHr_sno() {
		return hr_sno;
	}
	public void setHr_sno(String hr_sno) {
		this.hr_sno = hr_sno;
	}
	public String getSon_dept_name() {
		return son_dept_name;
	}
	public void setSon_dept_name(String son_dept_name) {
		this.son_dept_name = son_dept_name;
	}
	

}
