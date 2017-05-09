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
	public static String ALIAS_SNO = "组织序号";
	public static String ALIAS_DEPT_NAME = "组织名称";
	public static String ALIAS_DEPT_COUNTY = "归属单元";
	public static String ALIAS_CREATE_TIME = "创建时间";
	public static String ALIAS_CREATE_OPER_ID = "创建人员";
	public static String ALIAS_CHANGE_TIME = "修改时间";
	public static String ALIAS_CHANGE_OPER_ID = "修改人员";
	public static String ALIAS_IS_VALID = "是否有效";
	
	//columns START
    /**
     * 组织序号       db_column: SNO 
     */
	private Integer sno;
    /**
     * 组织名称       db_column: DEPT_NAME 
     */
	private String deptName;
    /**
     * 归属单元       db_column: DEPT_COUNTY 
     */
	private Integer deptCounty;
    /**
     * 创建时间       db_column: CREATE_TIME 
     */
	private Date createTime;
    /**
     * 创建人员       db_column: CREATE_OPER_ID 
     */
	private Integer createOperId;
    /**
     * 修改时间       db_column: CHANGE_TIME 
     */
	private Date changeTime;
    /**
     * 修改人员       db_column: CHANGE_OPER_ID 
     */
	private Integer changeOperId;
    /**
     * 是否有效       db_column: IS_VALID 
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

