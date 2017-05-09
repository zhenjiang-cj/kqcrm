package com.nl.portal.dt;

import java.io.Serializable;
/**
 * 
 * Copyright (c) 2011,�´�½����������޹�˾ All rights reserved��
 * �ļ����ƣ�SysOrganizition.java
 * ��������֯����ʵ��
 * @author xupeng
 * @date 2011-8-2
 * @version 
 * �޸ļ�¼
 */
public class SysOrganizition implements Serializable 
{
	private static final long serialVersionUID = 5454155825314635342L;
	
	/*�������*/ 	
	private String org_id;
	/*��������*/ 	
	private String name;
	/*�ϼ�����*/ 	
	private String super_id;
	/*������*/ 	
	private String region_code;
	/*��������*/ 	
	private int org_level;
	/*����չ��˳��*/ 	
	private int org_order; 
	/*С������*/ 	
	private String area_code;
	/*С������*/ 	
	private String area_desp;
	/*���б�ʶ*/ 	
	private int city_flag ;
	/*BOSS�г��б���*/ 	
	private String boss_city_code;
	/*BOSS�г�������*/ 	
	private String bas_area_code;
	/*С������*/ 	
	private String area_flag;
	/*С������*/ 	
	private String super_code;
	
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSuper_id() {
		return super_id;
	}
	public void setSuper_id(String super_id) {
		this.super_id = super_id;
	}
	public String getRegion_code() {
		return region_code;
	}
	public void setRegion_code(String region_code) {
		this.region_code = region_code;
	}
	public int getOrg_level() {
		return org_level;
	}
	public void setOrg_level(int org_level) {
		this.org_level = org_level;
	}
	public int getOrg_order() {
		return org_order;
	}
	public void setOrg_order(int org_order) {
		this.org_order = org_order;
	}
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	public String getArea_desp() {
		return area_desp;
	}
	public void setArea_desp(String area_desp) {
		this.area_desp = area_desp;
	}
	public int getCity_flag() {
		return city_flag;
	}
	public void setCity_flag(int city_flag) {
		this.city_flag = city_flag;
	}
	public String getBoss_city_code() {
		return boss_city_code;
	}
	public void setBoss_city_code(String boss_city_code) {
		this.boss_city_code = boss_city_code;
	}
	public String getBas_area_code() {
		return bas_area_code;
	}
	public void setBas_area_code(String bas_area_code) {
		this.bas_area_code = bas_area_code;
	}
	public String getArea_flag() {
		return area_flag;
	}
	public void setArea_flag(String area_flag) {
		this.area_flag = area_flag;
	}
	public String getSuper_code() {
		return super_code;
	}
	public void setSuper_code(String super_code) {
		this.super_code = super_code;
	}
	
}

