package com.nl.portal.dt;

import java.io.Serializable;
/**
 * 
 * Copyright (c) 2011,�´�½����������޹�˾ All rights reserved��
 * �ļ����ƣ�SysDept.java
 * ������ϵͳ����������Ϣʵ����
 * @author xupeng
 * @date 2011-8-02
 * @version 
 * �޸ļ�¼
 */
public class SysDept implements Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	/*Ա�����*/ 	
	private String SNo;
	/*���ű��*/ 	
	private String sno_dept;
	/*������*/ 	
	private String sno_dept_name;
	/*��������*/ 	
	private String dept_county;
	/*�ϼ�����*/ 	
	private String sno_upper;
	/*���ñ�־*/ 	
	private int enflag;
	
	public String getSNo() {
		return SNo;
	}
	public void setSNo(String no) {
		SNo = no;
	}
	public String getSno_dept() {
		return sno_dept;
	}
	public void setSno_dept(String sno_dept) {
		this.sno_dept = sno_dept;
	}
	public String getSno_dept_name() {
		return sno_dept_name;
	}
	public void setSno_dept_name(String sno_dept_name) {
		this.sno_dept_name = sno_dept_name;
	}
	public String getDept_county() {
		return dept_county;
	}
	public void setDept_county(String dept_county) {
		this.dept_county = dept_county;
	}
	public String getSno_upper() {
		return sno_upper;
	}
	public void setSno_upper(String sno_upper) {
		this.sno_upper = sno_upper;
	}
	public int getEnflag() {
		return enflag;
	}
	public void setEnflag(int enflag) {
		this.enflag = enflag;
	}
}

