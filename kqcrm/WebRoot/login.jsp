<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>CRM系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="shortcut icon" href="<%=path%>/resources/images/favicon.ico" />
	<link href="<%=path%>/resources/style/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=path%>/resources/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/jquery.i18n.properties-1.0.9.js" ></script>
	<script type="text/javascript" src="<%=path%>/resources/js/jquery-ui-1.10.3.custom.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/md5.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/page_login.js?lang=zh"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body class="loginbody">
  <form class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)" action="<%=path%>/login.do?method=login" method="post" name="userForm">
	<div class="dataEye">
		<div class="loginbox">
			<div class="logo-a">
				<!-- <a href="http://www.js-css.cn" title="">
					<img src="resources/images/logo-a.png">
				</a> -->
			</div>
			<div class="login-content">
				<div class="loginbox-title">
					<h3>登录</h3>
				</div>
				<form id="signupForm">
				<div class="login-error"></div>
				<div class="row">
					<!-- <label class="field">用户名</label>-->
					<input type="text" class="input-text-user input-click" name="user_id" id="user_id">
				</div>
				<div class="row">
					<!--<label class="field">密码</label>-->
					<input type="password" class="input-text-password input-click" name="user_pswd" id="user_pswd">
				</div>
				<div class="row btnArea">
					<a class="login-btn" id="submit">登录</a>
				</div>
				</form>
			</div>
		</div>
		
	<div id="footer">
		<div class="dblock">
			<div class="inline-block"><img src="resources/images/logo.png"></div>
			<div class="inline-block copyright">
				<p><a href="#">关于我们</a>  | <a href="#">隐私政策</a> </p>
				<p> Copyright © 2017 镇江开渠科技发展有限公司</p>
			</div>
		</div>
	</div>
	</div>
	<div class="loading">
		<div class="mask">
			<div class="loading-img">
			<img src="resources/images/loading.gif" width="31" height="31">
			</div>
		</div>
	</div>
	</form>
	</body>
</html>
