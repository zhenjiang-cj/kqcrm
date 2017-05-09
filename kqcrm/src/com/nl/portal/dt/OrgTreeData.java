package com.nl.portal.dt;

import java.io.Serializable;

/**
 *
 * @author sanjing
 * @creatdate Aug 13, 2012
 */
public class OrgTreeData implements Serializable{
	private int data_level;
	private String org_id;
	private String org_name;
	private String parent_org_id;
	private String parent_org_name;
	private String is_parent;
	public int getData_level() {
		return data_level;
	}
	public void setData_level(int data_level) {
		this.data_level = data_level;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	public String getParent_org_id() {
		return parent_org_id;
	}
	public void setParent_org_id(String parent_org_id) {
		this.parent_org_id = parent_org_id;
	}
	public String getParent_org_name() {
		return parent_org_name;
	}
	public void setParent_org_name(String parent_org_name) {
		this.parent_org_name = parent_org_name;
	}
	public String getIs_parent() {
		return is_parent;
	}
	public void setIs_parent(String is_parent) {
		this.is_parent = is_parent;
	}
}
