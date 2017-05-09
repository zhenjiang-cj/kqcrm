package com.nl.portal.dt;

import java.io.Serializable;

/**
 * 
 * Copyright (c) 2011,�´�½����������޹�˾ All rights reserved�� �ļ����ƣ�SysOperator.java
 * ������ϵͳ����������Ϣʵ����
 * 
 * @author xupeng
 * @date 2011-8-02
 * @version �޸ļ�¼
 */
public class SysOperator implements Serializable
{
	private static final long serialVersionUID = 5454155825314635342L;

	/* Ա����� */
	private String SNo;
	/* ��¼�˻� */
	private String user_id;
	/* ��¼���� */
	private String userPassword;
	/* ���� */
	private String user_name;
	/* �ֻ����� */
	private String msisdn;
	/* �������� */
	private String email;
	/* Ա����� */
	private String cop_code;
	/* ���ñ�־ */
	private int en_flag;
	/* �������ű�� */
	private String sno_dept;
	/* ���������� */
	private String sno_dept_name;
	/* ���������� */
	private String gno;
	/* ���������� */
	private String group_name;
	/*�ܼ�¼��*/
	private String totalCount;
	/*����Ա��¼ʱ��������� */
	private String userPassword1;
	/*CRMԱ�����*/
	private String boss_code;
	
	private String hr_sno;
	
	private String account_4A;
	
	private String short_code;
	
	private String user_seq;
	
	private String begin_date;
	private String end_date;

	/**
	 * @return the begin_date
	 */
	public String getBegin_date() {
		return begin_date;
	}

	/**
	 * @param begin_date the begin_date to set
	 */
	public void setBegin_date(String begin_date) {
		this.begin_date = begin_date;
	}

	/**
	 * @return the end_date
	 */
	public String getEnd_date() {
		return end_date;
	}

	/**
	 * @param end_date the end_date to set
	 */
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
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

	/**
	 * @return the short_code
	 */
	public String getShort_code() {
		return short_code;
	}

	/**
	 * @param short_code the short_code to set
	 */
	public void setShort_code(String short_code) {
		this.short_code = short_code;
	}

	public String getHr_sno() {
		return hr_sno;
	}

	public void setHr_sno(String hr_sno) {
		this.hr_sno = hr_sno;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getSNo()
	{
		return SNo;
	}

	public void setSNo(String no)
	{
		SNo = no;
	}

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}

	public String getUserPassword()
	{
		return userPassword;
	}

	public void setUserPassword(String userPassword)
	{
		this.userPassword = userPassword;
	}

	public String getUser_name()
	{
		return user_name;
	}

	public void setUser_name(String user_name)
	{
		this.user_name = user_name;
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

	public String getCop_code()
	{
		return cop_code;
	}

	public void setCop_code(String cop_code)
	{
		this.cop_code = cop_code;
	}

	public int getEn_flag()
	{
		return en_flag;
	}

	public void setEn_flag(int en_flag)
	{
		this.en_flag = en_flag;
	}

	public String getSno_dept()
	{
		return sno_dept;
	}

	public void setSno_dept(String sno_dept)
	{
		this.sno_dept = sno_dept;
	}

	public String getSno_dept_name()
	{
		return sno_dept_name;
	}

	public void setSno_dept_name(String sno_dept_name)
	{
		this.sno_dept_name = sno_dept_name;
	}

	public String getGno()
	{
		return gno;
	}

	public void setGno(String gno)
	{
		this.gno = gno;
	}

	public String getGroup_name()
	{
		return group_name;
	}

	public void setGroup_name(String group_name)
	{
		this.group_name = group_name;
	}

	public String getTotalCount()
	{
		return totalCount;
	}

	public void setTotalCount(String totalCount)
	{
		this.totalCount = totalCount;
	}

	public String getUserPassword1()
	{
		return userPassword1;
	}

	public void setUserPassword1(String userPassword1)
	{
		this.userPassword1 = userPassword1;
	}

	public String getBoss_code() {
		return boss_code;
	}

	public void setBoss_code(String boss_code) {
		this.boss_code = boss_code;
	}

	public String getAccount_4A() {
		return account_4A;
	}

	public void setAccount_4A(String account_4A) {
		this.account_4A = account_4A;
	}
	
	
}
