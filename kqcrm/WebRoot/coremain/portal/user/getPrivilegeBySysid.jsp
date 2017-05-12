<%@ page language="java" pageEncoding="GBK"%>
<%@page import="com.nl.portal.sc.*"%>
<%@page import="com.nl.portal.dt.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.nl.util.SessionConst"%>
<%@page import="com.nl.util.SessionData"%>
<%@page import="com.nl.util.GlobalConst"%>
<%@page import="com.nl.base.utils.GlobalFunc"%>

<%@page import="com.nl.portal.actionForm.*"%>
<%@page import="net.sf.json.JSONArray"%>

<%
HashMap<String,String> resultMap = new HashMap<String,String>();

try
{
	String sysid = request.getParameter("sysid");
	UserForm formBean = new UserForm();
	
	UserSc sc = new UserSc();
	formBean.setSysid(sysid);
	List<UserInfo> privilegeList = sc.getPrivilegeBySysid(formBean);
	
	//return  GlobalFunc.getJosnStrForList(privilegeList);
	
	 
	out.print(GlobalFunc.getJosnStrForList(privilegeList));
}catch(Exception e){
//	getLogger(bosscodestr,GlobalConst.ERROR).error("进入欢迎页面出错:"+e.getMessage());
	out.print("[]");
	throw new Exception();
	
}
	
%>    