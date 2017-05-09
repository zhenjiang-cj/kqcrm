<%@ page language="java" pageEncoding="GBK"%>
<%@ page import="com.nl.util.GlobalUtil"%>
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
<body class="">
  
   <script>
   	   alert('您的登录已超时，请重新登录！');
       window.location.href = "<%=GlobalUtil.getPropertiesText("struts-config_res","htzj_uri") %>/htzj/publish/index.jsp";
   </script>
</body>
</html>
