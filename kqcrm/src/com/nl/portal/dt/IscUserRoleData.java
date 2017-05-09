package com.nl.portal.dt;

import java.io.Serializable;

/**
 *
 * @author xiaqt
 * @creatdate May 17, 2012
 */
public class IscUserRoleData implements Serializable {

	private long sno;
	private String user_seq;
	private long role_id;
	private int data_type;
	private int data_level;
	private String code;
	private String codeName;
	private String str;
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public long getSno() {
		return sno;
	}
	public void setSno(long sno) {
		this.sno = sno;
	}
	public long getRole_id() {
		return role_id;
	}
	public void setRole_id(long role_id) {
		this.role_id = role_id;
	}
	public int getData_type() {
		return data_type;
	}
	public void setData_type(int data_type) {
		this.data_type = data_type;
	}
	public int getData_level() {
		return data_level;
	}
	public void setData_level(int data_level) {
		this.data_level = data_level;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
}
