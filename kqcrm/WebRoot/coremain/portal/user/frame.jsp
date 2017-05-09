<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.portal.dt.*"%>
<%@ page import="com.nl.portal.actionForm.*"%>
<%@ page import="com.nl.portal.sc.*"%>
<%@ page import="com.nl.util.GlobalConst"%>
<%
	String path = request.getContextPath();
	UserForm comForm = (UserForm) request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
	//CompanySc sc = new CompanySc();

	//根据当前登录的企业用户查询申请工单信息表并关联企业资质信息表，取企业资质信息表最新的一条有效记录，
	//获取“证书有效期止”，比对当前日期如果小于<=30（该天数为资源文件可配置）（如果超期则为负数）  如果成立，则进行提醒。
	//正数，则提醒，你的企业资质资质将在xx天后到期，请尽快申请新资质。
	//负数，则提醒，你的企业资质资质已经过期xx天，请尽快申请新资质。
	//大于30天，不提醒
	
	//int day = sc.checkAptitude(comForm);
	//System.out.println("%%%"+GlobalConst.BUFF_DAY);
	//int buff_day = Integer.parseInt(GlobalConst.BUFF_DAY);
	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=path%>">
    
    <title>系统管理</title> 
	
	<script type="text/javascript">
	
	
	</script>
	
  </head>
<body scroll="no">
<div class="accordion" fillSpace="sideBar">
	<div class="accordionHeader">
		<h2><span>Folder</span>用户管理</h2>
	</div>
	<div class="accordionContent">
		<ul class="tree treeFolder">
			<li><a href="<%=path%>/userAction.do?method=toUserManage" target="navTab" rel="user">用户管理</a></li>	
		</ul>
	</div>
	<div class="accordionHeader">
		<h2><span>Folder</span>角色管理</h2>
	</div>
	<div class="accordionContent">
		<ul class="tree treeFolder ">
			<li><a href="<%=path%>/userAction.do?method=toRoleManage" target="navTab" rel="role">角色管理</a></li>	
		</ul>
	</div> 
</div>
</body>
</html>

