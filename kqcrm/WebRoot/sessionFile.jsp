<%@ page language="java" pageEncoding="GBK"%>
<%@page import="com.nl.portal.sc.*"%>
<%@page import="com.nl.util.SessionConst"%>
<%@page import="com.nl.util.SessionData"%>


<%
	String bosscodestr = null; 
	HttpSession s = request.getSession();
	SessionData sessionData = (SessionData)s.getAttribute(SessionConst.LOGIN_SESSION);
	if(sessionData==null){
		out.print("0");
	}else{
		if(sessionData.getUser_id()==null||sessionData.getUser_id().equals("")){
			out.print("0");
		}else{
			out.print("1");
		}
	}
	
%>    