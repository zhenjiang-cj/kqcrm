<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.base.utils.GlobalFunc"%>
<%@page import="com.nl.util.GlobalUtil"%>
<%@page import="com.nl.util.SessionConst"%>
<%@page import="com.nl.util.GlobalConst"%>
<%@page import="com.nl.util.SessionData"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

SessionData sessdata = (SessionData) request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
List privlist = null;
if(sessdata!=null){
	privlist = sessdata.getPrivMap();
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>开渠CRM</title>

<link href="<%=path%>/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=path%>/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=path%>/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="<%=path%>/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<!--[if IE]>
<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->
<style type="text/css">
	#header{height:85px}
	#leftside, #container, #splitBar, #splitBarProxy{top:90px}
</style>

<!--[if lte IE 9]>
<script src="js/speedup.js" type="text/javascript"></script>
<![endif]-->
	<!--<script src="<%=path%>/dwz/js/jquery-1.7.2.min.js" type="text/javascript"></script>-->
	<!-- <script src="<%=path%>/dwz/js/jquery-1.7.2.js" type="text/javascript"></script> -->
	 <script src="<%=path%>/plugins/jquery/jquery-1.4.4.js" type="text/javascript"></script> 
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
		
	<script src="<%=path%>/dwz/bin/dwz.min.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("/kqcrm/dwz/dwz.frag.xml", {
		//loginUrl:"login.jsp", loginTitle:"登录",	// 弹出登录对话框
  		loginUrl:"/kqcrm/login.jsp",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"themes"});
			//DWZ.loadLogin();
			//setTimeout(function() {$("#sidebar .toggleCollapse div").trigger("click");}, 10);
			checksession();
			setInterval(checksession, 60*10*1000);
			$("#navMenu>:first>:first").addClass("selected");
			setTimeout(function() {$("#navMenu .selected a").trigger("click");}, 10);
		}
	});
});
//判断session是否失效
function checksession()
{
	try{
		$.ajax({
		    type:"post",
			dataType:"json",
			contentType:"application/json;charset=UTF-8",
			url:'<%=path%>/sessionFile.jsp',
		    success:function(data){
	            if('1'!=data){
                   DWZ.loadLogin();
	            }
		    },
		    error:function (data){
		        alert("验证session失败！");
                   DWZ.loadLogin();
		    }
		});
	}catch(e){
		alert(e);
	}

}
function fillSpace(key){
	var obj = jmenus.get(key);
	if (!obj) return;
	
	var parent = $(obj).parent();
	var height = parent.height() - (($(".accordionHeader", obj).size()) * ($(".accordionHeader:first-child", obj).outerHeight())) -2;

	//var os = parent.children().not(obj);
	//$.each(os, function(i){
	//	height -= $(os[i]).outerHeight();
	//});
	$(".accordionContent",obj).height(height);
}
</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="http://j-ui.com">标志</a>
				<ul class="nav">
				<%
				if(sessdata!=null){
					%>
					<li><a>用户名称：<%=sessdata.getUser_name() %></a></li>
					<li><a>用户工号：<%=sessdata.getUser_id() %></a></li>
					<%
				}
				%>
					<li><a href="<%=path%>/login.do?method=loginout">退出</a></li>
					
				</ul>
			</div>
		
			<div id="navMenu">
				<ul>
				<%
				if(GlobalUtil.functionCheck(privlist,GlobalConst.FUNCTION_CRM_MANAGE)){
					%>
					<li><a href="<%=path%>/coremain/crm/frame.jsp"><span>客户管理</span></a></li>
					<%
				}
				%>
				<%
				if(GlobalUtil.functionCheck(privlist,GlobalConst.FUNCTION_HT_MANAGE)){
					%>
					<li  ><a href="<%=path%>/coremain/crm/htframe.jsp"><span>合同管理</span></a></li>
					<%
				}
				%>
				<%
				if(GlobalUtil.functionCheck(privlist,GlobalConst.FUNCTION_HF_MANAGE)){
					%>
					<li  ><a href="<%=path%>/coremain/crm/hfframe.jsp"><span>客户回访</span></a></li>
					<%
				}
				%>
				<%
				if(GlobalUtil.functionCheck(privlist,GlobalConst.FUNCTION_SYS_MANAGE)){
					%>
					<li ><a href="<%=path%>/coremain/portal/user/frame.jsp"><span>系统管理</span></a></li>
					<%
				}
				%>
				</ul>
			</div>
		</div>

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
						<h2><span>Folder</span>界面组件</h2>
					</div>
					<div class="accordionContent">
						<!-- <ul class="tree treeFolder">
							<li><a href="tabsPage.html" target="navTab">主框架面板</a>
								<ul>
									<li><a href="http://www.baidu.com" target="navTab" rel="page1">页面一(外部页面)</a></li>
									<li><a href="main.html" target="navTab" rel="main">我的主页</a></li>
									<li><a href="demo_page2.html" target="navTab" rel="external" external="true">iframe navTab页面</a></li>
									<li><a href="demo_page1.html" target="navTab" rel="page1" fresh="false">替换页面一</a></li>
									<li><a href="demo_page2.html" target="navTab" rel="page2">页面二</a></li>
									<li><a href="demo_page4.html" target="navTab" rel="page3" title="页面三（自定义标签名）">页面三</a></li>
									<li><a href="demo_page4.html" target="navTab" rel="page4" fresh="false">测试页面（fresh="false"）</a></li>
									<li><a href="w_editor.html" target="navTab">表单提交会话超时</a></li>
									<li><a href="demo/common/ajaxTimeout.html" target="navTab">navTab会话超时</a></li>
									<li><a href="demo/common/ajaxTimeout.html" target="dialog">dialog会话超时</a></li>
								</ul>
							</li>
							
						</ul> -->
					</div>
					 
				</div>

			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderMargin"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">首页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li class="selected"><a href="javascript:;">首页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox mainContent" style="height: 538px;">
					<div class=" myBody page unitBox">
					<h2 class="contentTitle">&nbsp;</h2>
				<div>
				 <p>&nbsp;</p>
				<p>&nbsp;</p>
				<p>&nbsp;</p>
				 <p>&nbsp;</p>
				<p>&nbsp;</p>
				<p>&nbsp;</p>
				<table width="500" border="0" align="center" cellpadding="0" cellspacing="0" class="myLogin">
				  <tbody><tr>
				    <td height="80" align="center">&nbsp;</td>
				  </tr>
				   <tr>
				    <td height="40" align="center"><h2 class="contentTitle">
				    <%
				    out.println("欢迎登录开渠CRM系统");
				    %>
				    
				    
				    </h2></td>
				  </tr> 
				    <tr>
				    <td height="89" align="center">
				      如您在使用过程中存在无法解决的问题, 请与本系统的技术支持单位联系。<br>
				        <br>
				    联系电话:13814173130</td>
				  </tr>
				</tbody></table>
				
				</div>
				</div>
								</div>
				
			</div>
		</div>

	</div>

	<div id="footer">Copyright &copy; 2017 开渠科技</div>

</body>
</html>