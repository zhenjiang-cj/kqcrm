/*
 * Powered By [rapid-framework]
 * Since 2014 create by dq
 */

package com.nl.portal.vo;

import com.nl.util.PageRequest;

import java.util.*;

/**
 * @author dq
 * @version 1.0
 * @since 1.0
 */


public class PortalDeptInfoQueryVo extends PageRequest{

	public static String SNO = "SNO";
	public static String DEPT_NAME = "DEPT_NAME";
	public static String DEPT_COUNTY = "DEPT_COUNTY";
	public static String CREATE_TIME = "CREATE_TIME";
	public static String CREATE_OPER_ID = "CREATE_OPER_ID";
	public static String CHANGE_TIME = "CHANGE_TIME";
	public static String CHANGE_OPER_ID = "CHANGE_OPER_ID";
	public static String IS_VALID = "IS_VALID";
	

	/** ��֯��� */
	private Integer sno;
	/** ��֯���� */
	private String deptName;
	/** ������Ԫ */
	private Integer deptCounty;
	/** ����ʱ�� */
	private Date createTimeBegin;
	private Date createTimeEnd;
	/** ������Ա */
	private Integer createOperId;
	/** �޸�ʱ�� */
	private Date changeTimeBegin;
	private Date changeTimeEnd;
	/** �޸���Ա */
	private Integer changeOperId;
	/** �Ƿ���Ч */
	private Integer isValid;

	public Integer getSno() {
		return this.sno;
	}
	
	public void setSno(Integer value) {
		this.sno = value;
	}
	
	public String getDeptName() {
		return this.deptName;
	}
	
	public void setDeptName(String value) {
		this.deptName = value;
	}
	
	public Integer getDeptCounty() {
		return this.deptCounty;
	}
	
	public void setDeptCounty(Integer value) {
		this.deptCounty = value;
	}
	
	public Date getCreateTimeBegin() {
		return this.createTimeBegin;
	}
	
	public void setCreateTimeBegin(Date value) {
		this.createTimeBegin = value;
	}	
	
	public Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}
	
	public void setCreateTimeEnd(Date value) {
		this.createTimeEnd = value;
	}
	
	public Integer getCreateOperId() {
		return this.createOperId;
	}
	
	public void setCreateOperId(Integer value) {
		this.createOperId = value;
	}
	
	public Date getChangeTimeBegin() {
		return this.changeTimeBegin;
	}
	
	public void setChangeTimeBegin(Date value) {
		this.changeTimeBegin = value;
	}	
	
	public Date getChangeTimeEnd() {
		return this.changeTimeEnd;
	}
	
	public void setChangeTimeEnd(Date value) {
		this.changeTimeEnd = value;
	}
	
	public Integer getChangeOperId() {
		return this.changeOperId;
	}
	
	public void setChangeOperId(Integer value) {
		this.changeOperId = value;
	}
	
	public Integer getIsValid() {
		return this.isValid;
	}
	
	public void isNormal() {
		this.isValid = 1;
	}
}

