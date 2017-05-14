<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.portal.dt.*"%>
<%@ page import="com.nl.portal.actionForm.*"%>
<%@ page import="com.nl.portal.sc.*"%>
<%@ page import="com.nl.util.GlobalConst"%>
<%
	String path = request.getContextPath();
	CrmForm comForm = (CrmForm) request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
	
	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=path%>">
    
    <title>回访工作</title> 
	
	<script type="text/javascript">
	
	
	</script>
	
  </head>
<body scroll="no">
<div class="accordion" fillSpace="sideBar">
	<div class="accordionHeader">
		<h2><span>Folder</span>回访工作</h2>
	</div>
	<div class="accordionContent">
		<ul class="tree treeFolder">
			<li><a href="<%=path%>/crmAction.do?method=toHfManage" target="navTab" rel="ht">回访工作</a></li>	
		</ul>
	</div>
</div>
</body>
</html>

