package com.nl.portal.dt;

import java.io.Serializable;

/**
 *
 * @author xiaqt
 * @creatdate May 17, 2012
 */
public class IscPriDataRel implements Serializable {

	private long privilege_id;
	private int data_type;
	private String privilege_name;
	private long role_id;
	private String role_name;
	
	private String totalCount;
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getPrivilege_name() {
		return privilege_name;
	}
	public void setPrivilege_name(String privilege_name) {
		this.privilege_name = privilege_name;
	}
	public long getRole_id() {
		return role_id;
	}
	public void setRole_id(long role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public long getPrivilege_id() {
		return privilege_id;
	}
	public void setPrivilege_id(long privilege_id) {
		this.privilege_id = privilege_id;
	}
	public int getData_type() {
		return data_type;
	}
	public void setData_type(int data_type) {
		this.data_type = data_type;
	}
}
