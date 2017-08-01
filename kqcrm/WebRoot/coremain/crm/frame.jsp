<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.nl.portal.actionForm.*"%>
<%@ page import="com.nl.util.GlobalConst"%>
<%@page import="com.nl.util.SessionData"%>
<%@page import="com.nl.util.SessionConst"%>
<%@page import="com.nl.util.GlobalUtil"%>
<%
	String path = request.getContextPath();
	CrmForm comForm = (CrmForm) request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
	
	SessionData sessdata = (SessionData) request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
	List privlist = null;
	if(sessdata!=null){
		privlist = sessdata.getPrivMap();
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=path%>">
    
    <title>客户管理</title> 
	
	<script type="text/javascript">
	
	
	</script>
	
  </head>
<body scroll="no">
<div class="accordion" fillSpace="sideBar">
	<div class="accordionHeader">
		<h2><span>Folder</span>客户管理</h2>
	</div>
	<div class="accordionContent">
		<ul class="tree treeFolder">
			<%
			if(GlobalUtil.functionCheck(privlist,GlobalConst.FUNCTION_CRM_YXUSER_ADD)){
				%>
			<li><a href="<%=path%>/crmAction.do?method=toYxkhAdd" target="navTab" rel="toYxkhAdd">意向客户录入</a></li>	
			<%} %>
			<%
			if(GlobalUtil.functionCheck(privlist,GlobalConst.FUNCTION_CRM_YXUSER_EDIT)||
					GlobalUtil.functionCheck(privlist,GlobalConst.FUNCTION_CRM_YXUSER_DEL)||
					GlobalUtil.functionCheck(privlist,GlobalConst.FUNCTION_CRM_YXUSER_EXP)){
				%>
			<li><a href="<%=path%>/crmAction.do?method=toYxkhManage" target="navTab" rel="queryYxkh">意向客户维护</a></li>	
			<%} %>
			<li><a href="<%=path%>/crmAction.do?method=toKhManage" target="navTab" rel="user">客户管理</a></li>	
			
		</ul>
	</div>
</div>
</body>
</html>

