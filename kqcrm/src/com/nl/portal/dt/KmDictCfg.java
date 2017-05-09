package com.nl.portal.dt;

import java.io.Serializable;

public class KmDictCfg implements Serializable{
	private int dict_id;
	private String dict_name;
	private int value;
	private String value_name;
	private int parent_id;
	private int sort_id;
	private String remark;
	private int is_modify;
	private int is_recursion;
	public int getDict_id() {
		return dict_id;
	}
	public void setDict_id(int dict_id) {
		this.dict_id = dict_id;
	}
	public String getDict_name() {
		return dict_name;
	}
	public void setDict_name(String dict_name) {
		this.dict_name = dict_name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getValue_name() {
		return value_name;
	}
	public void setValue_name(String value_name) {
		this.value_name = value_name;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public int getSort_id() {
		return sort_id;
	}
	public void setSort_id(int sort_id) {
		this.sort_id = sort_id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getIs_modify() {
		return is_modify;
	}
	public void setIs_modify(int is_modify) {
		this.is_modify = is_modify;
	}
	public int getIs_recursion() {
		return is_recursion;
	}
	public void setIs_recursion(int is_recursion) {
		this.is_recursion = is_recursion;
	}
	
	
}
