package com.nl.portal.dt;

import java.io.Serializable;

public class AdmUserLog implements Serializable{
	private String lgID;
	private String lgUserID;
	private String lgOptrType;
	private String lgObjType;
	private String lgNotes;
	private String lgDate;
	private String lgStatus;
	private String lgUserType;
	private String lgpcIP;
	private String lgpcName;
	
	private String totalCount;
	
	public String getLgID() {
		return lgID;
	}
	public void setLgID(String lgID) {
		this.lgID = lgID;
	}

	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getLgUserID() {
		return lgUserID;
	}
	public void setLgUserID(String lgUserID) {
		this.lgUserID = lgUserID;
	}
	public String getLgOptrType() {
		return lgOptrType;
	}
	public void setLgOptrType(String lgOptrType) {
		this.lgOptrType = lgOptrType;
	}
	public String getLgObjType() {
		return lgObjType;
	}
	public void setLgObjType(String lgObjType) {
		this.lgObjType = lgObjType;
	}
	public String getLgNotes() {
		return lgNotes;
	}
	public void setLgNotes(String lgNotes) {
		this.lgNotes = lgNotes;
	}
	public String getLgStatus() {
		return lgStatus;
	}
	public void setLgStatus(String lgStatus) {
		this.lgStatus = lgStatus;
	}
	public String getLgUserType() {
		return lgUserType;
	}
	public void setLgUserType(String lgUserType) {
		this.lgUserType = lgUserType;
	}
	public String getLgpcIP() {
		return lgpcIP;
	}
	public void setLgpcIP(String lgpcIP) {
		this.lgpcIP = lgpcIP;
	}
	public String getLgpcName() {
		return lgpcName;
	}
	public void setLgpcName(String lgpcName) {
		this.lgpcName = lgpcName;
	}
	public String getLgDate() {
		return lgDate;
	}
	public void setLgDate(String lgDate) {
		this.lgDate = lgDate;
	}
	
	
	
}
