package com.nl.portal.dt;

import java.io.Serializable;

public class RptProcessControl implements Serializable{
	private String process_name;
	private long execute_time;
	private long partition_id;
	private int step_id;
	private int status;
	private String first_time;
	private double run_seconds;
	private String end_time;
	private String error_msg;
	private String start_time;
	private String oper_id;
	private String oper_name;
	private int max_status;
	private int total_count;

	public String getProcess_name() {
		return process_name;
	}

	public void setProcess_name(String process_name) {
		this.process_name = process_name;
	}

	public long getExecute_time() {
		return execute_time;
	}

	public void setExecute_time(long execute_time) {
		this.execute_time = execute_time;
	}

	public long getPartition_id() {
		return partition_id;
	}

	public void setPartition_id(long partition_id) {
		this.partition_id = partition_id;
	}

	public int getStep_id() {
		return step_id;
	}

	public void setStep_id(int step_id) {
		this.step_id = step_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFirst_time() {
		return first_time;
	}

	public void setFirst_time(String first_time) {
		this.first_time = first_time;
	}

	public double getRun_seconds() {
		return run_seconds;
	}

	public void setRun_seconds(double run_seconds) {
		this.run_seconds = run_seconds;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getError_msg() {
		return error_msg;
	}

	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getOper_id() {
		return oper_id;
	}

	public void setOper_id(String oper_id) {
		this.oper_id = oper_id;
	}

	public String getOper_name() {
		return oper_name;
	}

	public void setOper_name(String oper_name) {
		this.oper_name = oper_name;
	}

	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}

	public int getMax_status() {
		return max_status;
	}

	public void setMax_status(int max_status) {
		this.max_status = max_status;
	}
	
	
}
