<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.nl.util.GlobalConst"%>
<%@page import="com.nl.portal.actionForm.LoginForm"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String contextpath = request.getContextPath(); 
    LoginForm loginForm = (LoginForm)request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM); 
%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>操作员密码修改</title>
    <link  href="<%=contextpath%>/css/systemCont.css" rel="stylesheet" type="text/css">
    <link  href="<%=contextpath%>/css/winOp.css" rel="stylesheet" type="text/css">
</head>
<body>
<form action="<%=contextpath%>/login.do?method=login" method="post" name="loginForm">
    <!--标题栏 START-->
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="winOp">
	  <tr>
	    <td class="td01"> </td>
	    <td class="td02"><div  class="tit">密码修改</div></td>
	    <td class="td03"> </td>
	    <td class="td04"> </td>
	    <td class="td05"> </td>
	  </tr>
	</table>
	<!--标题栏 END-->
	<!--内容 START-->
	<DIV class="winOpM">
	<div class="contAllDiv borNO">
	        <div style="line-height:25px;">
	            友情提醒：<br>
			       &nbsp;&nbsp;&nbsp;&nbsp;1、您的密码为初始密码，您必须修改密码后才能登录；<br>
			       &nbsp;&nbsp;&nbsp;&nbsp;2、密码长度为6-15位的数字或字母组合。
	        </div>
	        <input type="hidden" name="operatorId" id="operatorId" value="<%=loginForm.getOperatorId()%>">
	        <input type="hidden" name="operPassword" id="operPassword" value="<%=loginForm.getOperPassword()%>">
	        <input type="hidden" name="updPassFlag" id="updPassFlag" value="1">
			<!--浮动信息START-->
			<div class="workCont">
			  <table border="0" cellspacing="1" cellpadding="0" class="TabList" style="width:100%">
			  <tr>			 
			    <td class="tit" width="20%">请输入新密码
				</td>
				<td width="80%">
				    <input id="newPassword1" name="newPassword1" type="password" class="inputS" value=""><font class="Red">*</font>
				</td>
			  </tr>
			  <tr>			 
			    <td class="tit">请确认新密码
				</td>
				<td>
				   <input id="newPassword2" name="newPassword2" type="password" class="inputS" value=""><font class="Red">*</font>
				</td>
			  </tr>  
		   </table>		       
		</div>
		<!--浮动信息 END-->
		
	</div>
	<div class="fg8"></div>
	<div class="altC">
	     <input id="button1" name="button1"  type="button" value="修 改"  class="bntSty" onClick="modifyPassword()"/>
		 <input name=""  type="button" value="返 回"  class="bntSty" onClick="goBack()"/>
	</div>
	</DIV>
	<!--内容 END-->
	<!--尾 START-->
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="winOpB">
	  <tr>
	    <td class="td01"></td>
	    <td class="td02">&nbsp;</td>
	    <td class="td03"></td>
	  </tr>
	</table>
	<!--尾 START-->
	</form>
</body>
</html>
<script type="text/javascript" src="<%=contextpath%>/plugins/jquery/jquery-1.4.4.js"></script>
<script type="text/javascript" src="<%=contextpath%>/plugins/jquery/plugins/dialog/Dialog.js"></script>
<script type="text/javascript">
    jQuery(document).ready(function(){
        jQuery("#newPassword1").focus();
    })

    function modifyPassword()
    {
        var newPassword1 = jQuery("#newPassword1").val();
        var newPassword2 = jQuery("#newPassword2").val();

        if (newPassword1.length == 0)
        {
            alert("请输入新密码！");
            jQuery("#newPassword1").focus();
            return false;
        }
        if (newPassword1.length < 6 || newPassword1.length > 15)
        {
            alert("新密码长度不符合要求！");
            jQuery("#newPassword1").focus();
            return false;
        }
        if (newPassword2.length == 0)
        {
            alert("请确认新密码！");
            jQuery("#newPassword2").focus();
            return false;
        } 
        if (newPassword1 != newPassword2)
        {
            alert("您的确认密码和新密码不一致，请重新输入！！");
            jQuery("#newPassword2").focus();
            return false;
        }
        jQuery("#button1").attr("disabled","disabled");
        loginForm.submit();
    }
    
    function goBack()
    {
        window.location.href="<%=contextpath%>/index.jsp";
    }
</script>
