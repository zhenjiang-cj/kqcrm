/*
 * Powered By [rapid-framework]
 * Since 2014 create by dq
 */

package com.nl.portal.dt;

import java.util.Date;

/**
 * @author dq
 * @version 1.0
 * @since 1.0
 */


public class PortalDeptInfo implements java.io.Serializable {
	
	//alias
	public static String TABLE_ALIAS = "PortalDeptInfo";
	public static String ALIAS_SNO = "��֯���";
	public static String ALIAS_DEPT_NAME = "��֯����";
	public static String ALIAS_DEPT_COUNTY = "������Ԫ";
	public static String ALIAS_CREATE_TIME = "����ʱ��";
	public static String ALIAS_CREATE_OPER_ID = "������Ա";
	public static String ALIAS_CHANGE_TIME = "�޸�ʱ��";
	public static String ALIAS_CHANGE_OPER_ID = "�޸���Ա";
	public static String ALIAS_IS_VALID = "�Ƿ���Ч";
	
	//columns START
    /**
     * ��֯���       db_column: SNO 
     */
	private Integer sno;
    /**
     * ��֯����       db_column: DEPT_NAME 
     */
	private String deptName;
    /**
     * ������Ԫ       db_column: DEPT_COUNTY 
     */
	private Integer deptCounty;
    /**
     * ����ʱ��       db_column: CREATE_TIME 
     */
	private Date createTime;
    /**
     * ������Ա       db_column: CREATE_OPER_ID 
     */
	private Integer createOperId;
    /**
     * �޸�ʱ��       db_column: CHANGE_TIME 
     */
	private Date changeTime;
    /**
     * �޸���Ա       db_column: CHANGE_OPER_ID 
     */
	private Integer changeOperId;
    /**
     * �Ƿ���Ч       db_column: IS_VALID 
     */
	private Integer isValid;
	//columns END

	public PortalDeptInfo(){
	}

	public PortalDeptInfo(
			Integer sno
	){
		this.sno = sno;
	}

	public void setSno(Integer value) {
		this.sno = value;
	}
	
	public Integer getSno() {
		return this.sno;
	}
	public void setDeptName(String value) {
		this.deptName = value;
	}
	
	public String getDeptName() {
		return this.deptName;
	}
	public void setDeptCounty(Integer value) {
		this.deptCounty = value;
	}
	
	public Integer getDeptCounty() {
		return this.deptCounty;
	}
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setCreateOperId(Integer value) {
		this.createOperId = value;
	}
	
	public Integer getCreateOperId() {
		return this.createOperId;
	}
	public void setChangeTime(Date value) {
		this.changeTime = value;
	}
	
	public Date getChangeTime() {
		return this.changeTime;
	}
	public void setChangeOperId(Integer value) {
		this.changeOperId = value;
	}
	
	public Integer getChangeOperId() {
		return this.changeOperId;
	}
	public void setIsValid(Integer value) {
		this.isValid = value;
	}
	
	public Integer getIsValid() {
		return this.isValid;
	}

}

