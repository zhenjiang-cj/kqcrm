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
<script type="text/javascript">
    //页面增加键盘监听事件
    document.onkeydown = keyDown;
    document.all.operatorId.focus();
    function keyDown(e)
    {
        if (event.keyCode == 13)
        {
            doSubmit();
        }
    }
    
    function doSubmit()
    {
            var userForm = this.document.forms[0];
	        if (userForm.user_id.value == "")
	        {
	            alert("请输入用户号！"); 
	            userForm.user_id.focus();
	            return false;
	        }
	
	        if (userForm.user_pswd.value == "")
	        {
	            alert("请输入密码！");
	            userForm.user_pswd.focus();
	            return false;
	        }
	
	        showLoadding();
	        userForm.submit();
    }
    
    function reset()
    {
        document.all.user_id.value="";
        document.all.user_pswd.value="";
        this.document.forms[0].user_id.focus();
    }
    
    function showLoadding()
    {
	    var targetDiv=document.getElementById("loaddingDiv");
		if (targetDiv!=null){
		    var x=event.clientX //获取鼠标指针位置相对于触发事件的对象的X坐标 
	        var y=event.clientY //获取鼠标指针位置相对于触发事件的对象的Y坐标 
	        targetDiv.style.top=document.body.clientHeight/2;
	        targetDiv.style.left=document.body.clientWidth/2 + 230	;
	        
			targetDiv.style.display="block";
		}
	}
	
	function doReset()
    {
        showD("./coremain/portal/find_password.jsp",400,300,"重置密码",200);
    }
    
    IMGFOLDERPATH = './plugins/jquery/plugins/dialog/images/';
    function showD(url1,w,h,tit,top)
	{
	    var diag = new Dialog("Diag1");
		diag.Width = w;
		diag.Height = h;
		diag.Title = tit;
		diag.URL = url1;
		diag.Top = top;
		diag.show();
    }
    
    
</script>
  </head>
  
  <body class="loginbody">
  <form   action="<%=path%>/login.do?method=login" method="post" name="userForm">
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
					<a class="login-btn" id="submit" onclick="doSubmit();">登录</a>
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
