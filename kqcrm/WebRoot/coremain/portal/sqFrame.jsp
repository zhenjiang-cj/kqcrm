<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.portal.actionForm.LoginForm"%>
<%@page import="com.nl.util.GlobalConst"%>
<%
	String path = request.getContextPath();
	LoginForm loginForm = (LoginForm)request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
	if (loginForm == null)
	{
	    loginForm = new LoginForm();
	}
	
 

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=path%>">
    
    <title>慧通住建</title>
	
	<link href="<%=path%>/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="<%=path%>/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="<%=path%>/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
	<link href="<%=path%>/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="<%=path %>/css/htzjbackcore.css" rel="stylesheet" type="text/css"  />
	<!--[if IE]>
	<link href="<%=path%>/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
	<![endif]-->
	
	<!--[if lte IE 9]>
	<script src="<%=path%>/dwz/js/speedup.js" type="text/javascript"></script>
	<![endif]-->
	
	<script src="<%=path%>/dwz/js/jquery-1.7.2.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/jquery.cookie.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/jquery.validate.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>
	
	<!-- svg图表  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
	<script type="text/javascript" src="<%=path%>/dwz/chart/raphael.js"></script>
	<script type="text/javascript" src="<%=path%>/dwz/chart/g.raphael.js"></script>
	<script type="text/javascript" src="<%=path%>/dwz/chart/g.bar.js"></script>
	<script type="text/javascript" src="<%=path%>/dwz/chart/g.line.js"></script>
	<script type="text/javascript" src="<%=path%>/dwz/chart/g.pie.js"></script>
	<script type="text/javascript" src="<%=path%>/dwz/chart/g.dot.js"></script>
	
	<script src="<%=path%>/dwz/js/dwz.core.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.util.date.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.validate.method.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.barDrag.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.drag.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.tree.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.accordion.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.ui.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.theme.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.switchEnv.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.alertMsg.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.contextmenu.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.navTab.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.tab.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.resize.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.dialog.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.dialogDrag.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.sortDrag.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.cssTable.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.stable.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.taskBar.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.ajax.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.pagination.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.database.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.datepicker.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.effects.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.panel.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.checkbox.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.history.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.combox.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.print.js" type="text/javascript"></script>
	
	<!-- 可以用dwz.min.js替换前面全部dwz.*.js (注意：替换是下面dwz.regional.zh.js还需要引入)
	<script src="<%=path%>/dwz/bin/dwz.min.js" type="text/javascript"></script>
	-->
	<script src="<%=path%>/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
	
	<script type="text/javascript">
	$(function(){
		DWZ.init("/htzj/dwz/dwz.frag.xml", {
			loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
	//		loginUrl:"login.html",	// 跳到登录页面
			statusCode:{ok:200, error:300, timeout:301}, //【可选】
			pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
			debug:false,	// 调试模式 【true|false】
			callback:function(){
				initEnv();
				$("#themeList").theme({themeBase:"themes"}); // themeBase 相对于index页面的主题base路径
			}
		});
	});
	
	</script>
	
  </head>
<body scroll="no">
	<div id="layout">
		<jsp:include page="../global/backhead.jsp" />

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2><span>Folder</span>资质申报</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">							
							<li><a href="<%=path%>/approvalFlowAction.do?method=doApply" target="navTab" rel="doApply">申报事项</a></li>
							<li><a href="<%=path%>/approvalFlowAction.do?method=queryApplyInfo" target="navTab" rel="queryApply">申报情况查询</a></li>
						</ul>
					</div>					
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							<div class="alertInfo">
								
							</div>
							<div class="right">

							</div>

						</div>
						
						<div style="width:230px;position: absolute;top:60px;right:0" layoutH="80">
							
						</div>
					</div>
					
				</div>
			</div>
		</div>

	</div>

	<div id="footer"></div>



</body>
</html>

