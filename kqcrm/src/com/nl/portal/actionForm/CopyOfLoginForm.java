package com.nl.portal.actionForm;

import com.nl.base.BaseAppActionForm;

public class CopyOfLoginForm extends BaseAppActionForm
{

	private String operatorId = "";
	private String operPassword = "";
	private String message = "";
	private String encry = "";
	private String newPassword1;
	private String newPassword2;	
	private int updPassFlag;//�Ƿ�ִ���޸�����ı�־
	
	private String userNo;
	private String msisdn;//�û��ֻ�����
	private String verifyCode; //��֤��
	private int verifyFlag;//��֤����Ч��ʾ   1��Ч  0ʧЧ
	private String ip;
	
	private String lgUserID;
	
	//���ƴ󴫹������û�ID
	private String userid = "";
	
	//��ҵ�û���¼��FORM
	private String companyUserId;
	private String userPwd;
	private String validPic;
	private String md5UserPwd;
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public int getVerifyFlag() {
		return verifyFlag;
	}

	public void setVerifyFlag(int verifyFlag) {
		this.verifyFlag = verifyFlag;
	}

	@Override
	public String getOperatorId()
	{
		return operatorId;
	}

	@Override
	public void setOperatorId(String operatorId)
	{
		this.operatorId = operatorId;
	}

	public String getOperPassword()
	{
		return operPassword;
	}

	public void setOperPassword(String operPassword)
	{
		this.operPassword = operPassword;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getEncry()
	{
		return encry;
	}

	public void setEncry(String encry)
	{
		this.encry = encry;
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

	public int getUpdPassFlag()
	{
		return updPassFlag;
	}

	public void setUpdPassFlag(int updPassFlag)
	{
		this.updPassFlag = updPassFlag;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getLgUserID() {
		return lgUserID;
	}

	public void setLgUserID(String lgUserID) {
		this.lgUserID = lgUserID;
	}

	public String getCompanyUserId() {
		return companyUserId;
	}

	public void setCompanyUserId(String companyUserId) {
		this.companyUserId = companyUserId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getValidPic() {
		return validPic;
	}

	public void setValidPic(String validPic) {
		this.validPic = validPic;
	}

	public String getMd5UserPwd() {
		return md5UserPwd;
	}

	public void setMd5UserPwd(String md5UserPwd) {
		this.md5UserPwd = md5UserPwd;
	}

	
	

}
