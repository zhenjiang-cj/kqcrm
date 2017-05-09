package com.nl.util;

import com.nl.base.utils.SystemTool;

public class PortalzjException extends Exception {

	private static final long serialVersionUID = 1L;
	private String cause;
	
	public PortalzjException(){
		super();
	}
	
	public PortalzjException(String msg){
		super(msg);
		SystemTool.getLoggerForWebApp().error(getMessage());
	}

	public PortalzjException(String msg,Throwable t){
		super(msg,t);
		SystemTool.getLoggerForWebApp().error(getMessage());
	}
	
	public PortalzjException(String bossCode, String msg, String cause){
		super(msg);
		this.cause = cause;
		SystemTool.getLoggerForWebApp(bossCode, GlobalConst.ERROR).error(getMessage());
	}

	public PortalzjException(String bossCode, String msg,Throwable t){
		super(msg,t);
		this.cause = t.toString();
		SystemTool.getLoggerForWebApp(bossCode, GlobalConst.ERROR).error(getMessage());
	}

	public PortalzjException(Throwable t){
		super(t);
	}
	
	@Override
	public String getMessage(){
		String msg = super.getMessage();
		msg = msg == null ? "" : msg;
		this.cause = cause == null ? "" : cause;
		return "msg:::" + msg + "  " +super.getStackTrace()[0] + "  Cause:::" + this.cause;
	}
	
	@Override
	public String toString(){
		return getMessage();
	}
}
