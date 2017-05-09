package com.nl.portal.dt;

import java.io.Serializable;

public class SysLoginLog implements Serializable 
{
	private static final long serialVersionUID = 5454155825314635342L;
	
	private long login_srl = 0;//µÇÂ¼Á÷Ë®
	
	private String login_oper_id = "";//µÇÂ¼¹¤ºÅ
	
	private long login_date = 0;//µÇÂ¼ÈÕÆÚ
	
	private String login_time = "";//µÇÂ¼Ê±¼ä
	
	private String login_ip = "";//µÇÂ¼IP
	
	private int login_result = 0;//µÇÂ¼½á¹û
	
	private int sys_id = 0;//ÏµÍ³±àºÅ
	
	private String remark = "";//±¸×¢
//	private String reserved1;//Ô¤Áô×Ö¶Î1
//	private String reserved2;//Ô¤Áô×Ö¶Î2
//	private String reserved3;//Ô¤Áô×Ö¶Î3
	
	public long getLogin_srl() {
		return login_srl;
	}
	public void setLogin_srl(long login_srl) {
		this.login_srl = login_srl;
	}
	public String getLogin_oper_id() {
		return login_oper_id;
	}
	public void setLogin_oper_id(String login_oper_id) {
		this.login_oper_id = login_oper_id;
	}
	public long getLogin_date() {
		return login_date;
	}
	public void setLogin_date(long login_date) {
		this.login_date = login_date;
	}
	public String getLogin_time() {
		return login_time;
	}
	public void setLogin_time(String login_time) {
		this.login_time = login_time;
	}
	public String getLogin_ip() {
		return login_ip;
	}
	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}
	public int getLogin_result() {
		return login_result;
	}
	public void setLogin_result(int login_result) {
		this.login_result = login_result;
	}
	public int getSys_id() {
		return sys_id;
	}
	public void setSys_id(int sys_id) {
		this.sys_id = sys_id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
