package com.nl.util;

import java.util.List;
import java.util.Map;

import com.nl.base.utils.BASTree;
import com.nl.portal.dt.AdmUserFc;
import com.nl.portal.dt.HtzjCodeBm;
import com.nl.portal.dt.KmCompanyUser;



/**
 * @Description: 
 * @author CJ
 * @version 1.0
 * Oct 16, 2016
 * -------------------------------------------
 * @History:
 * 修订日期    修订人    版本    描述
 * 
 */
public class SessionData 
{
    //登录工号信息
	private  AdmUserFc admUser;
	
	//部门信息
	private  HtzjCodeBm htzjCodeBm;
	
	//企业工号信息
	private KmCompanyUser companyUser;
	
	
	
	
	private String sno;
	private String user_id;
	private String user_name;
	private String msisdn;
	private String user_pswd;
	
	private String org_id;
	private String org_name;
	private String provinces;
	private String provinces_name;
	private String city;
	private String city_name;
	private String region;
	private String region_name;
	private String org_level;
	

	//登录工号所有系统的系统菜单Tree
	private Map<String, BASTree> treeMap;
	private List<String> privMap;
	
	

 

	public Map<String, BASTree> getTreeMap() {
		return treeMap;
	}

	public void setTreeMap(Map<String, BASTree> treeMap) {
		this.treeMap = treeMap;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pswd() {
		return user_pswd;
	}

	public void setUser_pswd(String user_pswd) {
		this.user_pswd = user_pswd;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public AdmUserFc getAdmUser() {
		return admUser;
	}

	public void setAdmUser(AdmUserFc admUser) {
		this.admUser = admUser;
	}

	public HtzjCodeBm getHtzjCodeBm() {
		return htzjCodeBm;
	}

	public void setHtzjCodeBm(HtzjCodeBm htzjCodeBm) {
		this.htzjCodeBm = htzjCodeBm;
	}

	public KmCompanyUser getCompanyUser() {
		return companyUser;
	}

	public void setCompanyUser(KmCompanyUser companyUser) {
		this.companyUser = companyUser;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getProvinces() {
		return provinces;
	}

	public void setProvinces(String provinces) {
		this.provinces = provinces;
	}

	public String getProvinces_name() {
		return provinces_name;
	}

	public void setProvinces_name(String provinces_name) {
		this.provinces_name = provinces_name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRegion_name() {
		return region_name;
	}

	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}

	public String getOrg_level() {
		return org_level;
	}

	public void setOrg_level(String org_level) {
		this.org_level = org_level;
	}

	public List<String> getPrivMap() {
		return privMap;
	}

	public void setPrivMap(List<String> privMap) {
		this.privMap = privMap;
	}

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	
	
}
