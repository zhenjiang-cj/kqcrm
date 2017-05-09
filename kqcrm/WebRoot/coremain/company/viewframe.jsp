<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@ page import="com.nl.company.actionForm.*"%>
<%@ page import="com.nl.company.dt.*"%>
<%@ page import="com.nl.util.GlobalConst"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
CompanyForm comForm = (CompanyForm) request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);

String company_info_id = request.getParameter("company_info_id");
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="../global/backhead.jsp" />
<html>
  <head>
    <base href="<%=path%>">
    
    <title>慧通住建</title>
	
	<link href="<%=path%>/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="<%=path%>/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="<%=path%>/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
	<link href="<%=path%>/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
	<!--[if IE]>
	<link href="<%=path%>/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
	<![endif]-->
	<!--[if lte IE 9]>
	<script src="<%=path%>/dwz/js/speedup.js" type="text/javascript"></script>
	<![endif]-->
	<link href="<%=path %>/css/htzjbackcore.css" rel="stylesheet" type="text/css"  />
	
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
	
	
	
	<!-- 可以用dwz.min.js替换前面全部dwz.*.js (注意：替换是下面dwz.regional.zh.js还需要引入)-->
	<script src="<%=path%>/dwz/bin/dwz.min.js" type="text/javascript"></script>
	
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
						<h2><span>Folder</span>企业基本信息</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">							
							<li><a href="<%=path%>/companyAction.do?method=toViewBase&company_info_id=<%=company_info_id %>" target="navTab" rel="baseinfo">基本信息</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>企业人员信息</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">			
							<li><a href="<%=path%>/companyAction.do?method=viewUserinfo&company_info_id=<%=company_info_id %>" target="navTab" rel="userinfo">人员汇总</a></li>										
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>企业其他信息</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">			
							<li><a href="<%=path%>/companyAction.do?method=viewPro&company_info_id=<%=company_info_id %>" target="navTab" rel="projectinfo">物业项目信息</a></li>
							<li><a href="<%=path%>/companyAction.do?method=viewWuyePerf&company_info_id=<%=company_info_id %>" target="navTab" rel="projectinfo">物业经营业绩</a></li>
							<li><a href="<%=path%>/companyAction.do?method=viewCompanyinfo&company_info_id=<%=company_info_id %>" target="navTab" rel="projectinfo">企业资本</a></li>
							<!--<li><a href="<%=path%>/companyAction.do?method=viewVerifiinfo&company_info_id=<%=company_info_id %>" target="navTab" rel="projectinfo">验资报告</a></li>	-->
							<!-- <li><a href="<%=path%>/companyAction.do?method=toVerifiinfo" target="navTab" rel="projectinfo">验资报告</a></li>	 -->
							<li><a href="<%=path%>/companyAction.do?method=viewCxinfo&company_info_id=<%=company_info_id %>" target="navTab" rel="projectinfo">诚信证明</a></li>											
						</ul>
					</div>
					
				</div>
				
				
			</div>
		</div>
		<jsp:include page="../global/backwelcom.jsp" />

	</div>

	<jsp:include page="../global/backfoot.jsp" />



</body>
</html>

