package com.nl.portal.dt;

import java.io.Serializable;
/**
 * 
 * Copyright (c) 2011,新大陆软件工程有限公司 All rights reserved。
 * 文件名称：SysGroup.java
 * 描述：系统操作工号信息实体类
 * @author xupeng
 * @date 2011-8-02
 * @version 
 * 修改记录
 */
public class SysGroup implements Serializable 
{
	private static final long serialVersionUID = 5454155825314635342L;
	
	/*员工序号*/ 	
	private String SNo;
	/*分组编号*/ 	
	private String gno;
	/*分组名*/ 	
	private String group_name;
	/*分组区域*/ 	
	private String group_county;
	/*上级部门*/ 	
	private String sno_upper;
	/*启用标志*/ 	
	private int enflag;
	/*组类型*/ 	
	private int group_type;
	/*备注*/ 	
	private String remark;
	/*工单流转等级*/ 	
	private int com_order;
	/*部门类型*/ 	
	private int dept_type;
	/*数据级别*/ 	
	private int date_level;
	/*编码*/ 	
	private int code;
	
	public String getSNo() {
		return SNo;
	}
	public void setSNo(String no) {
		SNo = no;
	}
	public String getGno() {
		return gno;
	}
	public void setGno(String gno) {
		this.gno = gno;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getGroup_county() {
		return group_county;
	}
	public void setGroup_county(String group_county) {
		this.group_county = group_county;
	}
	public String getSno_upper() {
		return sno_upper;
	}
	public void setSno_upper(String sno_upper) {
		this.sno_upper = sno_upper;
	}
	public int getEnflag() {
		return enflag;
	}
	public void setEnflag(int enflag) {
		this.enflag = enflag;
	}
	public int getGroup_type() {
		return group_type;
	}
	public void setGroup_type(int group_type) {
		this.group_type = group_type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getCom_order() {
		return com_order;
	}
	public void setCom_order(int com_order) {
		this.com_order = com_order;
	}
	public int getDept_type() {
		return dept_type;
	}
	public void setDept_type(int dept_type) {
		this.dept_type = dept_type;
	}
	public int getDate_level() {
		return date_level;
	}
	public void setDate_level(int date_level) {
		this.date_level = date_level;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
}

