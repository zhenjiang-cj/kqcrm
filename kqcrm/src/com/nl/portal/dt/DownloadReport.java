package com.nl.portal.dt;

import java.io.Serializable;

public class DownloadReport implements Serializable{
	private String month;
	private String function_id;
	private String table_name;
	private int cnt_download;
	private int total_count;
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getFunction_id() {
		return function_id;
	}
	public void setFunction_id(String function_id) {
		this.function_id = function_id;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public int getCnt_download() {
		return cnt_download;
	}
	public void setCnt_download(int cnt_download) {
		this.cnt_download = cnt_download;
	}
	public int getTotal_count() {
		return total_count;
	}
	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}
	
}
