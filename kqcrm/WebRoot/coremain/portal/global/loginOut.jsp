<%@ page language="java" pageEncoding="GBK"%>
<%@ page import="com.nl.util.GlobalUtil"%>
  <%@page import="com.nl.util.SessionConst"%>
<%String contextpath = request.getContextPath(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>会话超时提示界面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
</head>

<%
request.getSession().removeAttribute(SessionConst.LOGIN_SESSION);
request.getSession().removeAttribute(SessionConst.COMPANY_LOGIN_SESSION);
%>
<body class="">
  
   <script>
       window.location.href = "<%=GlobalUtil.getPropertiesText("struts-config_res","htzj_uri") %>/htzj/publish/index.jsp";
   </script>
</body>
</html>
