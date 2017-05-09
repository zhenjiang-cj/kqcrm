package com.nl.portal.dt;

import java.io.Serializable;

public class SysOperatingLog implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long operating_srl = 0;//������ˮ
	
	private String operating_oper_id = "";//��������
	
	private long operating_date = 0;//��������
	
	private String operating_time = "";//����ʱ��
	
	private int operating_type = 0;//��������
	
	private long function_id = 0;//���ܱ��
	
	private String operating_object = "";//��������
	
	private int sys_id = 0;//ϵͳ���
	
	private int operating_result = 0;//�������
	
	private String remark = "";//��ע
	private String reserved1;//Ԥ���ֶ�1
//	private String reserved2;//Ԥ���ֶ�2
//	private String reserved3;//Ԥ���ֶ�3
	
	public String getReserved1() {
		return reserved1;
	}
	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public long getOperating_srl() {
		return operating_srl;
	}
	public void setOperating_srl(long operating_srl) {
		this.operating_srl = operating_srl;
	}
	public String getOperating_oper_id() {
		return operating_oper_id;
	}
	public void setOperating_oper_id(String operating_oper_id) {
		this.operating_oper_id = operating_oper_id;
	}
	public long getOperating_date() {
		return operating_date;
	}
	public void setOperating_date(long operating_date) {
		this.operating_date = operating_date;
	}
	public String getOperating_time() {
		return operating_time;
	}
	public void setOperating_time(String operating_time) {
		this.operating_time = operating_time;
	}
	public int getOperating_type() {
		return operating_type;
	}
	public void setOperating_type(int operating_type) {
		this.operating_type = operating_type;
	}
	public long getFunction_id() {
		return function_id;
	}
	public void setFunction_id(long function_id) {
		this.function_id = function_id;
	}
	public String getOperating_object() {
		return operating_object;
	}
	public void setOperating_object(String operating_object) {
		this.operating_object = operating_object;
	}
	public int getSys_id() {
		return sys_id;
	}
	public void setSys_id(int sys_id) {
		this.sys_id = sys_id;
	}
	public int getOperating_result() {
		return operating_result;
	}
	public void setOperating_result(int operating_result) {
		this.operating_result = operating_result;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
