<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.nl.company.sc.CompanySc"%>
<%@ page import="com.nl.company.dt.PicFileInfo"%>
<%@ page import="com.nl.company.actionForm.*"%>
<%@ page import="com.nl.util.GlobalConst"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String type = request.getParameter("type");
String companyid = request.getParameter("companyid"); 
String file_num = request.getParameter("file_num"); 
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'show_img.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <div width="100%"  >
   	<img  src="<%=path%>/companyAction.do?method=showPhoto&type=<%=type %>&companyid=<%=companyid %>&file_num=<%=file_num %>&r='+Math.random()"   /><br>
   </div>
    
  </body>
  
</html>
