package com.nl.portal.dt;

import java.io.Serializable;

public class RemindUserOrgCfg implements Serializable{
	
	private String SNo;			//��Ա���
	private int is_remind;  	//�Ƿ�����   1��   2��
	private int remind_cycle;	//��������   1����  2����
	private int remind_method;  //���ѷ�ʽ   1����  2����	
	private int remind_cnt;		//���Ѵ���
	public String getSNo() {
		return SNo;
	}
	public void setSNo(String no) {
		SNo = no;
	}
	public int getIs_remind() {
		return is_remind;
	}
	public void setIs_remind(int is_remind) {
		this.is_remind = is_remind;
	}
	public int getRemind_cycle() {
		return remind_cycle;
	}
	public void setRemind_cycle(int remind_cycle) {
		this.remind_cycle = remind_cycle;
	}
	public int getRemind_method() {
		return remind_method;
	}
	public void setRemind_method(int remind_method) {
		this.remind_method = remind_method;
	}
	public int getRemind_cnt() {
		return remind_cnt;
	}
	public void setRemind_cnt(int remind_cnt) {
		this.remind_cnt = remind_cnt;
	}
	
	
}
