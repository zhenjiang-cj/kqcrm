package com.nl.portal.actionForm;

import java.util.List;
import java.util.Map;

import org.apache.struts.upload.FormFile;

import com.nl.base.BaseAppActionForm;
import com.nl.portal.dt.SysOperator;

public class OperatorForm extends BaseAppActionForm
{
	private String userNo;
	private String userSeq;
	private String userId;
	private String userName;
	private String deptId;
	private String sysId;
	private String msisdn;
	private String email;
	private String oldPassword;
	private String newPassword1;
	private String newPassword2;
	private String[] chooseRole;
	private SysOperator operator;
	private Map operRoleMap;
	private String[] orgtreedata;
	
	private String hr_sno;
	private String user_id;
	private String user_name;
	private String crm_id;
	private String is_role;
	private String dept_name;
	private String sno_dept;
	private String sno_dept_old;
	private String sno_county;
	private String role_level;
	private String is_check;
	
	private String beginDate;
	private String endDate;
	
	
	/** 数据范围级别**/
	private int dataOrgLevel;
	/** 数据范围类型**/
	private int dataType;
	/**是否客户经理级别，0表示不是，1表示是**/
	private int manageFlag;
	/**数据范围的机构编号**/
	private String dataOrgId;
	/**操作员对应的BOSS工号**/
	private String bossCode;
	
	/**角色类型**/
	private String roleType;
	/**需赋权限的目标操作员**/
	private String targetOpers;
	
	private Map retInfo;
	
	private String errorMsg;
	private FormFile batchFile;
	private List dataList;
	private String operatorPswd;
	private String batchNo;
	
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getOperatorPswd() {
		return operatorPswd;
	}
	public void setOperatorPswd(String operatorPswd) {
		this.operatorPswd = operatorPswd;
	}
	public FormFile getBatchFile() {
		return batchFile;
	}
	public void setBatchFile(FormFile batchFile) {
		this.batchFile = batchFile;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getUserNo()
	{
		return userNo;
	}
	public void setUserNo(String userNo)
	{
		this.userNo = userNo;
	}
	public String getUserId()
	{
		return userId;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getDeptId()
	{
		return deptId;
	}
	public void setDeptId(String deptId)
	{
		this.deptId = deptId;
	}
	
	public String getSysId()
	{
		return sysId;
	}
	public void setSysId(String sysId)
	{
		this.sysId = sysId;
	}
	
	public String getMsisdn()
	{
		return msisdn;
	}
	public void setMsisdn(String msisdn)
	{
		this.msisdn = msisdn;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public SysOperator getOperator()
	{
		return operator;
	}
	public void setOperator(SysOperator operator)
	{
		this.operator = operator;
	}
	public Map getOperRoleMap()
	{
		return operRoleMap;
	}
	public void setOperRoleMap(Map operRoleMap)
	{
		this.operRoleMap = operRoleMap;
	}
	public String[] getChooseRole()
	{
		return chooseRole;
	}
	public void setChooseRole(String[] chooseRole)
	{
		this.chooseRole = chooseRole;
	}
	public String getOldPassword()
	{
		return oldPassword;
	}
	public void setOldPassword(String oldPassword)
	{
		this.oldPassword = oldPassword;
	}
	public String getNewPassword1()
	{
		return newPassword1;
	}
	public void setNewPassword1(String newPassword1)
	{
		this.newPassword1 = newPassword1;
	}
	public String getNewPassword2()
	{
		return newPassword2;
	}
	public void setNewPassword2(String newPassword2)
	{
		this.newPassword2 = newPassword2;
	}
	public int getDataOrgLevel()
	{
		return dataOrgLevel;
	}
	public void setDataOrgLevel(int dataOrgLevel)
	{
		this.dataOrgLevel = dataOrgLevel;
	}
	public int getDataType()
	{
		return dataType;
	}
	public void setDataType(int dataType)
	{
		this.dataType = dataType;
	}
	public int getManageFlag()
	{
		return manageFlag;
	}
	public void setManageFlag(int manageFlag)
	{
		this.manageFlag = manageFlag;
	}
	public String getDataOrgId()
	{
		return dataOrgId;
	}
	public void setDataOrgId(String dataOrgId)
	{
		this.dataOrgId = dataOrgId;
	}
	public String getBossCode()
	{
		return bossCode;
	}
	public void setBossCode(String bossCode)
	{
		this.bossCode = bossCode;
	}
	public String[] getOrgtreedata() {
		return orgtreedata;
	}
	public void setOrgtreedata(String[] orgtreedata) {
		this.orgtreedata = orgtreedata;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public String getTargetOpers() {
		return targetOpers;
	}
	public void setTargetOpers(String targetOpers) {
		this.targetOpers = targetOpers;
	}
	public Map getRetInfo() {
		return retInfo;
	}
	public void setRetInfo(Map retInfo) {
		this.retInfo = retInfo;
	}
	public String getHr_sno() {
		return hr_sno;
	}
	public void setHr_sno(String hr_sno) {
		this.hr_sno = hr_sno;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getCrm_id() {
		return crm_id;
	}
	public void setCrm_id(String crm_id) {
		this.crm_id = crm_id;
	}
	public String getIs_role() {
		return is_role;
	}
	public void setIs_role(String is_role) {
		this.is_role = is_role;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getSno_dept() {
		return sno_dept;
	}
	public void setSno_dept(String sno_dept) {
		this.sno_dept = sno_dept;
	}
	public String getSno_county() {
		return sno_county;
	}
	public void setSno_county(String sno_county) {
		this.sno_county = sno_county;
	}
	public String getRole_level() {
		return role_level;
	}
	public void setRole_level(String role_level) {
		this.role_level = role_level;
	}
	@Override
	public String getIs_check() {
		return is_check;
	}
	public void setIs_check(String is_check) {
		this.is_check = is_check;
	}
	public List getDataList() {
		return dataList;
	}
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
	/**
	 * @return the beginDate
	 */
	public String getBeginDate() {
		return beginDate;
	}
	/**
	 * @param beginDate the beginDate to set
	 */
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the userSeq
	 */
	public String getUserSeq() {
		return userSeq;
	}
	/**
	 * @param userSeq the userSeq to set
	 */
	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}
	/**
	 * @return the sno_dept_old
	 */
	public String getSno_dept_old() {
		return sno_dept_old;
	}
	/**
	 * @param sno_dept_old the sno_dept_old to set
	 */
	public void setSno_dept_old(String sno_dept_old) {
		this.sno_dept_old = sno_dept_old;
	}
	
}
