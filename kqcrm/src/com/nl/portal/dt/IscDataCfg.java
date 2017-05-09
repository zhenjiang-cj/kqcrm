package com.nl.portal.dt;

import java.io.Serializable;

/**
 *
 * @author xiaqt
 * @creatdate May 17, 2012
 */
public class IscDataCfg implements Serializable {

	private int data_type;
	private int data_level_no;
	private int data_level;
	private long code;
	public int getData_type() {
		return data_type;
	}
	public void setData_type(int data_type) {
		this.data_type = data_type;
	}
	public int getData_level_no() {
		return data_level_no;
	}
	public void setData_level_no(int data_level_no) {
		this.data_level_no = data_level_no;
	}
	public int getData_level() {
		return data_level;
	}
	public void setData_level(int data_level) {
		this.data_level = data_level;
	}
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
}
