package com.nl.portal.dt;

import java.io.Serializable;

/**
 *
 * @author xiaqt
 * @creatdate May 18, 2012
 */
public class IscRolePrivilege implements Serializable {

	private long privilege_id;
	private long role_id;
	public long getPrivilege_id() {
		return privilege_id;
	}
	public void setPrivilege_id(long privilege_id) {
		this.privilege_id = privilege_id;
	}
	public long getRole_id() {
		return role_id;
	}
	public void setRole_id(long role_id) {
		this.role_id = role_id;
	}
}
