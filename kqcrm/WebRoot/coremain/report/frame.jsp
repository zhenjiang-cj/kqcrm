<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.portal.dt.*"%>
<%@ page import="com.nl.portal.actionForm.*"%>
<%@ page import="com.nl.portal.sc.*"%>
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
    
    <title>数据统计</title> 
	
	<script type="text/javascript">
	
	
	</script>
	
  </head>
<body scroll="no">
<div class="accordion" fillSpace="sideBar">
	<div class="accordionHeader">
		<h2><span>Folder</span>数据统计</h2>
	</div>
	<div class="accordionContent">
		<ul class="tree treeFolder">
			<li><a href="<%=path%>/crmAction.do?method=queryDeviceReportSum" target="navTab" rel="deviceReport">设备统计</a></li>	
		</ul>
	</div>
</div>
</body>
</html>

