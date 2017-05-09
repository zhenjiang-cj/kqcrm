package com.nl.portal.dt;

import java.io.Serializable;

/**
 * 
 * Copyright (c) 2011,�´�½����������޹�˾ All rights reserved��
 * �ļ����ƣ�SysPrivilegeList.java ������Ȩ��ʵ��
 * @author xupeng
 * @date 2011-8-2
 * @version �޸ļ�¼
 */
public class SysPrivilege  implements Serializable
{
	private static final long serialVersionUID = 5454155825314635342L;

	/*Ȩ�ޱ���*/
	private String privilege_id;
	/*ϵͳ���*/
	private String sys_id;
	/*���ܱ��*/
	private String component_id;
	/*Ȩ������*/
	private String privilege_name;
	/*��һ�����*/
	private String parent_id;
	/*Ȩ�޼���*/
	private String priv_level;
	/**/
	private String plat_lable;
	/*״̬*/
	private int status;
	/*����*/
	private String description;
	/**/
	private String proviceall;
	/*�Ƿ���ڱ�־*/
	private int existFlag;
	/**/
	private String tool_type;
	/*����ʱ��*/
	private String create_date;
	/*����*/
	private String type;
	/*Ȩ�޵Ĺ�����ɫ���*/
	private String role_id;
	/*������ɫ���ݼ�������*/
	private int data_type;
	/*������ɫ���ݼ���*/
	private int data_level;
	/*������ɫ�����ݱ��*/
	private String code;
	
	private String create_id;
	private String change_id;
	private String change_date;
	
	
	/**
	 * @return the create_id
	 */
	public String getCreate_id() {
		return create_id;
	}
	/**
	 * @param create_id the create_id to set
	 */
	public void setCreate_id(String create_id) {
		this.create_id = create_id;
	}
	/**
	 * @return the change_id
	 */
	public String getChange_id() {
		return change_id;
	}
	/**
	 * @param change_id the change_id to set
	 */
	public void setChange_id(String change_id) {
		this.change_id = change_id;
	}
	/**
	 * @return the change_date
	 */
	public String getChange_date() {
		return change_date;
	}
	/**
	 * @param change_date the change_date to set
	 */
	public void setChange_date(String change_date) {
		this.change_date = change_date;
	}
	public String getPrivilege_id() {
		return privilege_id;
	}
	public void setPrivilege_id(String privilege_id) {
		this.privilege_id = privilege_id;
	}
	public String getSys_id() {
		return sys_id;
	}
	public void setSys_id(String sys_id) {
		this.sys_id = sys_id;
	}
	public String getComponent_id() {
		return component_id;
	}
	public void setComponent_id(String component_id) {
		this.component_id = component_id;
	}
	public String getPrivilege_name() {
		return privilege_name;
	}
	public void setPrivilege_name(String privilege_name) {
		this.privilege_name = privilege_name;
	}
	public String getPlat_lable() {
		return plat_lable;
	}
	public void setPlat_lable(String plat_lable) {
		this.plat_lable = plat_lable;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProviceall() {
		return proviceall;
	}
	public void setProviceall(String proviceall) {
		this.proviceall = proviceall;
	}
	public String getTool_type() {
		return tool_type;
	}
	public void setTool_type(String tool_type) {
		this.tool_type = tool_type;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getExistFlag()
	{
		return existFlag;
	}
	public void setExistFlag(int existFlag)
	{
		this.existFlag = existFlag;
	}
	public String getParent_id()
	{
		return parent_id;
	}
	public void setParent_id(String parent_id)
	{
		this.parent_id = parent_id;
	}
	public String getPriv_level()
	{
		return priv_level;
	}
	public void setPriv_level(String priv_level)
	{
		this.priv_level = priv_level;
	}	
	
}
