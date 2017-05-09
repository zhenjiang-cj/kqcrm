package com.nl.portal.dt;

import java.io.Serializable;

public class SyncData implements Serializable{
	private long batch_no;
	private String user_id;
	private String account_4A;
	private String process_result;
	private int process_success;
	private int process_failure;
	private int process_num;
	private String modify_mode;
	private String operator_id;
	private String operator_name;
	private String operate_date;
	private int total_count;
	
	public int getTotal_count() {
		return total_count;
	}
	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}
	public long getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(long batch_no) {
		this.batch_no = batch_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getAccount_4A() {
		return account_4A;
	}
	public void setAccount_4A(String account_4A) {
		this.account_4A = account_4A;
	}
	public String getProcess_result() {
		return process_result;
	}
	public void setProcess_result(String process_result) {
		this.process_result = process_result;
	}
	public int getProcess_success() {
		return process_success;
	}
	public void setProcess_success(int process_success) {
		this.process_success = process_success;
	}
	public int getProcess_failure() {
		return process_failure;
	}
	public void setProcess_failure(int process_failure) {
		this.process_failure = process_failure;
	}
	public int getProcess_num() {
		return process_num;
	}
	public void setProcess_num(int process_num) {
		this.process_num = process_num;
	}
	public String getOperator_id() {
		return operator_id;
	}
	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}
	public String getOperate_date() {
		return operate_date;
	}
	public void setOperate_date(String operate_date) {
		this.operate_date = operate_date;
	}

	public String getModify_mode() {
		return modify_mode;
	}
	public void setModify_mode(String modify_mode) {
		this.modify_mode = modify_mode;
	}
	public String getOperator_name() {
		return operator_name;
	}
	public void setOperator_name(String operator_name) {
		this.operator_name = operator_name;
	}
	
	
}
