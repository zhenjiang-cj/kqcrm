<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.nl.util.GlobalConst"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String contextpath = request.getContextPath();   
%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>操作员密码修改</title>
    <link  href="<%=contextpath%>/css/systemCont.css" rel="stylesheet" type="text/css">
</head>
<body class="Contbody" scroll="no">
	<div class="contAllDiv borNO">
		<!--显示工作区 START-->
		    <div class="divF"></div>
		  	<div class="workCont">
		  	    <input type="hidden" name="functionId" value="<%=GlobalConst.FUNCTION_OPERATOR_DETAIL %>">
		  	    <input type="hidden" name="systemId" value="<%=GlobalConst.SYSTEM_ID_SYSTEM%>">
				<!--标题栏 START-->
				 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="Title01">
				  <tr>
					<td class="td01"></td>
					<td class="td02"><div class="F01">操作员密码修改</div><div class="S01"><img src="<%=contextpath%>/images/portal/contTit12.gif"></div></td>
					<td class="td03"></td>
				  </tr>
				</table>
				<!--标题栏 END-->
				<!--表格1 START-->
				  <table width="100%"  border="0" cellspacing="1" cellpadding="0"  class="TabList">
					  <tr>			 
					    <td class="tit" width="25%">输入旧密码
						</td>
						<td width="75%">
						    <input id="oldPassword" name="oldPassword" type="password" class="inputS" value=""><font class="Red">*</font>
						</td>
					  </tr>
					  <tr>			 
					    <td class="tit">输入新密码
						</td>
						<td width="35%">
						    <input id="newPassword1" name="newPassword1" type="password" class="inputS" value=""><font class="Red">*</font>
						</td>
					  </tr>
					  <tr>			 
					    <td class="tit">确认新密码
						</td>
						<td>
						   <input id="newPassword2" name="roleName2" type="password" class="inputS" value=""><font class="Red">*</font>
						</td>
					  </tr>  
				   </table>
				       <br>
				       <font class="Red">提示：密码长度为3-15位的数字或字母组合！</font>
				   
				   

				<div class="OpLine">
				 <input name=""  type="button" value="修 改"  class="bntSty" onClick="modifyPassword()"/>
		         <input name=""  type="button" value="返 回"  class="bntSty" onClick="goBack()"/>
		       </div>		      
			</div>
		<!--显示工作区 END-->
	</div>
</body>
</html>
<script type="text/javascript" src="<%=contextpath%>/plugins/jquery/plugins/dialog/Dialog.js"></script>
<script type="text/javascript">
    function modifyPassword()
    {
        
    }
    function goBack()
    {
        Dialog.close();
    }
</script>
