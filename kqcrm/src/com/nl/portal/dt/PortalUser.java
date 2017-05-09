package com.nl.portal.dt;

import java.io.Serializable;

/**
 *
 * @author wangtt
 * @creatdate Mar 25, 2014
 */
public class PortalUser implements Serializable {

	private int total_count;
	
	/////portal_user_info/////
	private String 	hr_sno	;
	private String  sno;
	private String user_id;
	private String 	user_name	;
	private String 	msisdn	;
	private String 	short_code	;
	private String 	email	;
	private String 	create_time	;
	private String 	create_oper_id	;
	private String create_oper_name;
	private String 	change_time	;
	private String 	change_oper_id;
	private String change_oper_name;
	private String 	is_valid	;
	private String 	borth_date	;
	private String 	sex	;
	private String 	work_type	;
	private int user_status;
	private String user_type;
	
	//////portal_user_dept//////
//	private String 	hr_sno	;
	private String 	sno_dept	;
	private String 	dept_begin_date	;
	private String 	dept_end_date	;
//	private String 	create_time	;
//	private String 	create_oper_id	;
//	private String 	change_time	;
//	private String 	change_oper_id	;
	
	/////////////portal_dept_info///////////////
//	private String 	sno_dept	;
	private String 	dept_name	;
	private String 	dept_county	;
	private String sno_dept_upper;
	private String dept_name_upper;
//	private String 	create_time	;
//	private String 	create_oper_id	;
//	private String 	change_time	;
//	private String 	change_oper_id	;
//	private String 	is_valid	;
	
	
	/////////////PORTAL_POST_IN(PORTAL岗位信息表)//////////////////
	private String 	post_code	;
	private String 	post_name	;
	private String 	sys_id	;
	private String 	model_id	;
	private String  post_begin_date;
	private String post_end_date;
	
	//////////////PORTAL_USER_TOWN(PORTAL人员乡镇关系表)//////////////////////////
	private String town_id;
	private String town_name;
	private String town_begin_date;
	private String town_end_date;
	
	
	//生育信息
	private int is_bear;
	private int bear_type;
	
	
	
	
	
	
	
	
	
	
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
	public String getHr_sno() {
		return hr_sno;
	}
	public void setHr_sno(String hr_sno) {
		this.hr_sno = hr_sno;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getShort_code() {
		return short_code;
	}
	public void setShort_code(String short_code) {
		this.short_code = short_code;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getCreate_oper_id() {
		return create_oper_id;
	}
	public void setCreate_oper_id(String create_oper_id) {
		this.create_oper_id = create_oper_id;
	}
	public String getChange_time() {
		return change_time;
	}
	public void setChange_time(String change_time) {
		this.change_time = change_time;
	}
	public String getChange_oper_id() {
		return change_oper_id;
	}
	public void setChange_oper_id(String change_oper_id) {
		this.change_oper_id = change_oper_id;
	}
	public String getIs_valid() {
		return is_valid;
	}
	public void setIs_valid(String is_valid) {
		this.is_valid = is_valid;
	}
	public String getBorth_date() {
		return borth_date;
	}
	public void setBorth_date(String borth_date) {
		this.borth_date = borth_date;
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
	public String getSno_dept() {
		return sno_dept;
	}
	public void setSno_dept(String sno_dept) {
		this.sno_dept = sno_dept;
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
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getDept_county() {
		return dept_county;
	}
	public void setDept_county(String dept_county) {
		this.dept_county = dept_county;
	}
	public int getTotal_count() {
		return total_count;
	}
	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}
	public String getPost_code() {
		return post_code;
	}
	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}
	public String getPost_name() {
		return post_name;
	}
	public void setPost_name(String post_name) {
		this.post_name = post_name;
	}
	public String getSys_id() {
		return sys_id;
	}
	public void setSys_id(String sys_id) {
		this.sys_id = sys_id;
	}
	public String getModel_id() {
		return model_id;
	}
	public void setModel_id(String model_id) {
		this.model_id = model_id;
	}
	public String getTown_id() {
		return town_id;
	}
	public void setTown_id(String town_id) {
		this.town_id = town_id;
	}
	public String getCreate_oper_name() {
		return create_oper_name;
	}
	public void setCreate_oper_name(String create_oper_name) {
		this.create_oper_name = create_oper_name;
	}
	public String getSno_dept_upper() {
		return sno_dept_upper;
	}
	public void setSno_dept_upper(String sno_dept_upper) {
		this.sno_dept_upper = sno_dept_upper;
	}
	public String getDept_name_upper() {
		return dept_name_upper;
	}
	public void setDept_name_upper(String dept_name_upper) {
		this.dept_name_upper = dept_name_upper;
	}
	public String getChange_oper_name() {
		return change_oper_name;
	}
	public void setChange_oper_name(String change_oper_name) {
		this.change_oper_name = change_oper_name;
	}

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
	public int getUser_status() {
		return user_status;
	}
	/**
	 * @param user_status the user_status to set
	 */
	public void setUser_status(int user_status) {
		this.user_status = user_status;
	}
	/**
	 * @return the is_bear
	 */
	public int getIs_bear() {
		return is_bear;
	}
	/**
	 * @param is_bear the is_bear to set
	 */
	public void setIs_bear(int is_bear) {
		this.is_bear = is_bear;
	}
	/**
	 * @return the bear_type
	 */
	public int getBear_type() {
		return bear_type;
	}
	/**
	 * @param bear_type the bear_type to set
	 */
	public void setBear_type(int bear_type) {
		this.bear_type = bear_type;
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



}
