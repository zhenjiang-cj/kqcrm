<%@ page language="java" pageEncoding="GBK"%>
<%@ page import="com.nl.util.GlobalUtil"%>
<%String contextpath = request.getContextPath(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>�Ự��ʱ��ʾ����</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
</head>
<body class="">
  
   <script>
   	   alert('���ĵ�¼�ѳ�ʱ�������µ�¼��');
       window.location.href = "<%=GlobalUtil.getPropertiesText("struts-config_res","htzj_uri") %>/htzj/publish/index.jsp";
   </script>
</body>
</html>
