<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.nl.util.GlobalConst"%>
<%@page import="com.nl.portal.actionForm.OperatorForm"%>
<%@page import="com.nl.util.SessionConst"%>
<%@page import="com.nl.util.SessionData"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String contextpath = request.getContextPath(); 
    OperatorForm operatorForm = (OperatorForm)request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
    SessionData sessdata = (SessionData) request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>����Ա�����޸�</title>
    <link  href="<%=contextpath%>/css/systemCont.css" rel="stylesheet" type="text/css">
    <link  href="<%=contextpath%>/css/winOp.css" rel="stylesheet" type="text/css">
</head>
<body>
<form action="<%=contextpath%>/operator.do?method=updateOperPassword" method="post" name="operatorForm">
    <!--������ START-->
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="winOp">
	  <tr>
	    <td class="td01"> </td>
	    <td class="td02"><div  class="tit">����Ա�����޸�</div></td>
	    <td class="td03"> </td>
	    <td class="td04"> </td>
	    <td class="td05"> </td>
	  </tr>
	</table>
	<!--������ END-->
	<!--���� START-->
	<DIV class="winOpM">
	<div class="contAllDiv borNO">
	     
			<!--������ϢSTART-->
			<div class="workCont">
		  	  <input type="hidden" name="functionId" value="<%=GlobalConst.FUNCTION_OPERATOR_PASSWORD_UPDATE%>">
		  	  <input type="hidden" name="systemId" value="<%=GlobalConst.SYSTEM_ID_SYSTEM%>">
		  	  <input type="hidden" name="userSeq"  value="<%=sessdata.getSysOperator().getSNo()%>">
			  <table border="0" cellspacing="1" cellpadding="0" class="TabList" style="width:100%">
			  <tr>			 
			    <td class="tit" width="25%">���������
				</td>
				<td width="75%">
				    <input id="oldPassword" name="oldPassword" type="password" class="inputS" value=""><font class="Red">*</font>
				</td>
			  </tr>
			  <tr>			 
			    <td class="tit">����������
				</td>
				<td width="35%">
				    <input id="newPassword1" name="newPassword1" type="password" class="inputS" value=""><font class="Red">*</font>
				</td>
			  </tr>
			  <tr>			 
			    <td class="tit">ȷ��������
				</td>
				<td>
				   <input id="newPassword2" name="newPassword2" type="password" class="inputS" value=""><font class="Red">*</font>
				</td>
			  </tr>  
		   </table>		       
		</div>
		<!--������Ϣ END-->
		
	</div>
	<br>
	<span><font class="R">��ʾ�����볤��Ϊ6-15λ�����ֻ���ĸ��ϣ�</font></span>
	<div class="fg8"></div>
	<div class="altC">
	     <input name="button1"  type="button" value="�� ��"  class="bntSty" onClick="modifyPassword()"/>
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
          if (<%=operatorForm.getFlag()%> == 0)
          {
              parent.parent.parent.showM("�����޸ĳɹ�!",'1');
			  Dialog.close();
          }
          else if ("<%=operatorForm.getFlag()%>" == -1)
          {
              parent.parent.parent.showM("�����޸�ʧ��!",'2');
          }
          else if ("<%=operatorForm.getFlag()%>" == -2)
          {
              parent.parent.parent.showM("�����벻��ȷ�������޸�ʧ��!",'2');
          }
    })
    function modifyPassword()
    {
        var oldPassword = jQuery("#oldPassword").val();
        var newPassword1 = jQuery("#newPassword1").val();
        var newPassword2 = jQuery("#newPassword2").val();
        if (oldPassword.length == 0)
        {
            alert("����������룡");
            jQuery("#oldPassword").focus();
            return false;
        }
        if (oldPassword.length < 3 || oldPassword.length > 15)
        {
            alert("�����볤�Ȳ�����Ҫ��");
            jQuery("#oldPassword").focus();
            return false;
        }
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
        operatorForm.submit();
    }
    function goBack()
    {
        Dialog.close();
    }
</script>
