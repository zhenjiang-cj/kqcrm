<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.nl.util.GlobalConst"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String contextpath = request.getContextPath();   
%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>����Ա�����޸�</title>
    <link  href="<%=contextpath%>/css/systemCont.css" rel="stylesheet" type="text/css">
</head>
<body class="Contbody" scroll="no">
	<div class="contAllDiv borNO">
		<!--��ʾ������ START-->
		    <div class="divF"></div>
		  	<div class="workCont">
		  	    <input type="hidden" name="functionId" value="<%=GlobalConst.FUNCTION_OPERATOR_DETAIL %>">
		  	    <input type="hidden" name="systemId" value="<%=GlobalConst.SYSTEM_ID_SYSTEM%>">
				<!--������ START-->
				 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="Title01">
				  <tr>
					<td class="td01"></td>
					<td class="td02"><div class="F01">����Ա�����޸�</div><div class="S01"><img src="<%=contextpath%>/images/portal/contTit12.gif"></div></td>
					<td class="td03"></td>
				  </tr>
				</table>
				<!--������ END-->
				<!--���1 START-->
				  <table width="100%"  border="0" cellspacing="1" cellpadding="0"  class="TabList">
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
						   <input id="newPassword2" name="roleName2" type="password" class="inputS" value=""><font class="Red">*</font>
						</td>
					  </tr>  
				   </table>
				       <br>
				       <font class="Red">��ʾ�����볤��Ϊ3-15λ�����ֻ���ĸ��ϣ�</font>
				   
				   

				<div class="OpLine">
				 <input name=""  type="button" value="�� ��"  class="bntSty" onClick="modifyPassword()"/>
		         <input name=""  type="button" value="�� ��"  class="bntSty" onClick="goBack()"/>
		       </div>		      
			</div>
		<!--��ʾ������ END-->
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
