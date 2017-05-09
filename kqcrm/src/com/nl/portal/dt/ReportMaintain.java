package com.nl.portal.dt;

import java.io.Serializable;

public class ReportMaintain implements Serializable{
	private String report_id;
	private String report_name;
	private String report_path;
	private String report_developer;
	private String report_owner;
	private String report_desc;
	private String type_code;
	private String type_value;
	private String range_code;
	private String range_value;
	private String online_date;
	private int total_count;
	
	private int cnt_times;
	private int cnt_frequency;
	private int cnt_download;
	
	public int getCnt_times() {
		return cnt_times;
	}
	public void setCnt_times(int cnt_times) {
		this.cnt_times = cnt_times;
	}
	public int getCnt_frequency() {
		return cnt_frequency;
	}
	public void setCnt_frequency(int cnt_frequency) {
		this.cnt_frequency = cnt_frequency;
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
	public String getReport_id() {
		return report_id;
	}
	public void setReport_id(String report_id) {
		this.report_id = report_id;
	}
	public String getReport_name() {
		return report_name;
	}
	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}
	public String getReport_path() {
		return report_path;
	}
	public void setReport_path(String report_path) {
		this.report_path = report_path;
	}
	public String getReport_developer() {
		return report_developer;
	}
	public void setReport_developer(String report_developer) {
		this.report_developer = report_developer;
	}
	public String getReport_owner() {
		return report_owner;
	}
	public void setReport_owner(String report_owner) {
		this.report_owner = report_owner;
	}
	public String getReport_desc() {
		return report_desc;
	}
	public void setReport_desc(String report_desc) {
		this.report_desc = report_desc;
	}
	public String getType_value() {
		return type_value;
	}
	public void setType_value(String type_value) {
		this.type_value = type_value;
	}
	public String getRange_value() {
		return range_value;
	}
	public void setRange_value(String range_value) {
		this.range_value = range_value;
	}
	public String getType_code() {
		return type_code;
	}
	public void setType_code(String type_code) {
		this.type_code = type_code;
	}
	public String getRange_code() {
		return range_code;
	}
	public void setRange_code(String range_code) {
		this.range_code = range_code;
	}
	public String getOnline_date() {
		return online_date;
	}
	public void setOnline_date(String online_date) {
		this.online_date = online_date;
	}
	
	
}
