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
    <title>����Ա�����޸�</title>
    <link  href="<%=contextpath%>/css/systemCont.css" rel="stylesheet" type="text/css">
    <link  href="<%=contextpath%>/css/winOp.css" rel="stylesheet" type="text/css">
</head>
<body>
<form action="<%=contextpath%>/login.do?method=login" method="post" name="loginForm">
    <!--������ START-->
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="winOp">
	  <tr>
	    <td class="td01"> </td>
	    <td class="td02"><div  class="tit">�����޸�</div></td>
	    <td class="td03"> </td>
	    <td class="td04"> </td>
	    <td class="td05"> </td>
	  </tr>
	</table>
	<!--������ END-->
	<!--���� START-->
	<DIV class="winOpM">
	<div class="contAllDiv borNO">
	        <div style="line-height:25px;">
	            �������ѣ�<br>
			       &nbsp;&nbsp;&nbsp;&nbsp;1����������Ϊ��ʼ���룬�������޸��������ܵ�¼��<br>
			       &nbsp;&nbsp;&nbsp;&nbsp;2�����볤��Ϊ6-15λ�����ֻ���ĸ��ϡ�
	        </div>
	        <input type="hidden" name="operatorId" id="operatorId" value="<%=loginForm.getOperatorId()%>">
	        <input type="hidden" name="operPassword" id="operPassword" value="<%=loginForm.getOperPassword()%>">
	        <input type="hidden" name="updPassFlag" id="updPassFlag" value="1">
			<!--������ϢSTART-->
			<div class="workCont">
			  <table border="0" cellspacing="1" cellpadding="0" class="TabList" style="width:100%">
			  <tr>			 
			    <td class="tit" width="20%">������������
				</td>
				<td width="80%">
				    <input id="newPassword1" name="newPassword1" type="password" class="inputS" value=""><font class="Red">*</font>
				</td>
			  </tr>
			  <tr>			 
			    <td class="tit">��ȷ��������
				</td>
				<td>
				   <input id="newPassword2" name="newPassword2" type="password" class="inputS" value=""><font class="Red">*</font>
				</td>
			  </tr>  
		   </table>		       
		</div>
		<!--������Ϣ END-->
		
	</div>
	<div class="fg8"></div>
	<div class="altC">
	     <input id="button1" name="button1"  type="button" value="�� ��"  class="bntSty" onClick="modifyPassword()"/>
		 <input name=""  type="button" value="�� ��"  class="bntSty" onClick="goBack()"/>
	</div>
	</DIV>
	<!--���� END-->
	<!--β START-->
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="winOpB">
	  <tr>
	    <td class="td01"></td>
	    <td class="td02">&nbsp;</td>
	    <td class="td03"></td>
	  </tr>
	</table>
	<!--β START-->
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
            alert("�����������룡");
            jQuery("#newPassword1").focus();
            return false;
        }
        if (newPassword1.length < 6 || newPassword1.length > 15)
        {
            alert("�����볤�Ȳ�����Ҫ��");
            jQuery("#newPassword1").focus();
            return false;
        }
        if (newPassword2.length == 0)
        {
            alert("��ȷ�������룡");
            jQuery("#newPassword2").focus();
            return false;
        } 
        if (newPassword1 != newPassword2)
        {
            alert("����ȷ������������벻һ�£����������룡��");
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
