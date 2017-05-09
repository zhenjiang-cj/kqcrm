package com.nl.portal.dt;

import java.io.Serializable;

/**
 * 
 * Copyright (c) 2011,新大陆软件工程有限公司 All rights reserved。
 * 文件名称：SysOperatorRole.java
 * 描述：工号角色关系 实体
 * @author xupeng
 * @date 2011-8-2
 * @version 
 * 修改记录
 */

public class SysOperatorRole implements Serializable 
{
	private static final long serialVersionUID = 5454155825314635342L;
		
	/*员工序号*/ 	
	private String SNo;
	private String user_seq;
	/*登录账户*/ 	
	private String uesr_id;
	/*姓名*/ 	
	private String user_name;
	/*角色ID*/ 	
	private String role_id;
	/*角色名称*/
	private String role_name;
	/*角色级别*/
	private int role_level;
	/*角色描述*/
	private String remark;
	/*角色启用标志*/
	private int enflag;
	/*角色归属系统编号*/
	private int sysid;
	/*数据级别类型*/
	private int data_type;
	/*数据级别*/
	private int data_level;
	/*编号*/
	private String code;
	/*创建时间*/
	private String createtime;
	/*创建人工号*/
	private String createid;
	/*创建人姓名*/
	private String oper_name;
	/*总记录数*/
	private String totalCount;
	/*角色类型*/
	private int role_type;
	private String role_type_name;
	private String sys_name;
	
	public String getRole_type_name() {
		return role_type_name;
	}
	public void setRole_type_name(String role_type_name) {
		this.role_type_name = role_type_name;
	}
	public String getSys_name() {
		return sys_name;
	}
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}
	public String getSNo() {
		return SNo;
	}
	public void setSNo(String no) {
		SNo = no;
	}
	public String getUesr_id() {
		return uesr_id;
	}
	public void setUesr_id(String uesr_id) {
		this.uesr_id = uesr_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public int getRole_level() {
		return role_level;
	}
	public void setRole_level(int role_level) {
		this.role_level = role_level;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getEnflag() {
		return enflag;
	}
	public void setEnflag(int enflag) {
		this.enflag = enflag;
	}
	public int getSysid() {
		return sysid;
	}
	public void setSysid(int sysid) {
		this.sysid = sysid;
	}

	/**
	 * @return the data_type
	 */
	public int getData_type() {
		return data_type;
	}
	/**
	 * @param data_type the data_type to set
	 */
	public void setData_type(int data_type) {
		this.data_type = data_type;
	}
	/**
	 * @return the data_level
	 */
	public int getData_level() {
		return data_level;
	}
	/**
	 * @param data_level the data_level to set
	 */
	public void setData_level(int data_level) {
		this.data_level = data_level;
	}
	public String getCreatetime()
	{
		return createtime;
	}
	public void setCreatetime(String createtime)
	{
		this.createtime = createtime;
	}
	
	public String getCreateid()
	{
		return createid;
	}
	public void setCreateid(String createid)
	{
		this.createid = createid;
	}
	public String getOper_name()
	{
		return oper_name;
	}
	public void setOper_name(String oper_name)
	{
		this.oper_name = oper_name;
	}
	public String getTotalCount()
	{
		return totalCount;
	}
	public void setTotalCount(String totalCount)
	{
		this.totalCount = totalCount;
	}
	public int getRole_type() {
		return role_type;
	}
	public void setRole_type(int role_type) {
		this.role_type = role_type;
	}
	/**
	 * @return the user_seq
	 */
	public String getUser_seq() {
		return user_seq;
	}
	/**
	 * @param user_seq the user_seq to set
	 */
	public void setUser_seq(String user_seq) {
		this.user_seq = user_seq;
	}
	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}

