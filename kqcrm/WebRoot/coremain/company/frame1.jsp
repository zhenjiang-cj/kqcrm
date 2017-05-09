<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="../global/backhead.jsp" />
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>企业管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<link href="<%=path%>/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="<%=path%>/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="<%=path%>/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
	<link href="<%=path%>/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
	<!--[if IE]>
	<link href="<%=path%>/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
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
	  <!-- 头部 --> 
		<div id="leftside">
			<div id="sidebar" style="height:648px">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2>企业基本信息</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">	
							<li><a href="<%=path%>/companyAction.do?method=toBaseinfo" target="navTab" rel="baseinfo">基本信息</a></li>
							<li><a href="<%=path%>/coremain/company/viewframe.jsp?company_info_id=1" target="_top" rel="main">基本信息2</a></li>						
						</ul>
					</div>					
				</div>
				
				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2>企业人员信息</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">	
							<li><a href="<%=path%>/companyAction.do?method=toUserinfo" target="navTab" rel="userinfo">人员汇总</a></li>
							<li><a href="<%=path%>/companyAction.do?method=toUserlegal" target="navTab" rel="userinfo">法定代表人</a></li>
							<li><a href="<%=path%>/companyAction.do?method=toUsermanage" target="navTab" rel="userinfo">总经理</a></li>
							<li><a href="<%=path%>/companyAction.do?method=toUserOn" target="navTab" rel="userinfo">企业在职人员</a></li>					
						</ul>
					</div>					
				</div>
				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2>企业其他信息</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">	
							<li><a href="<%=path%>/companyAction.do?method=toWuyeinfo" target="navTab" rel="projectinfo">物业项目信息</a></li>
							<li><a href="<%=path%>/companyAction.do?method=toWuyePerf" target="navTab" rel="projectinfo">物业经营业绩</a></li>
							<li><a href="<%=path%>/companyAction.do?method=toCompanyinfo" target="navTab" rel="projectinfo">企业资本</a></li>
							<li><a href="<%=path%>/companyAction.do?method=toVerifiinfo" target="navTab" rel="projectinfo">验资报告</a></li>				
						</ul>
					</div>					
				</div>
				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2>资质申报</h2>
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
		<jsp:include page="../global/backwelcom.jsp" />
	<!--右边内容  -->	
		
	</div>

   <!-- 底部 -->
	<jsp:include page="../global/backfoot.jsp" />

</body>
</html>
