<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.nl.util.SessionData"%>
<%@page import="com.nl.util.SessionConst"%>
<%@page import="com.nl.portal.dt.SysOperator"%>
<%
	SessionData sessionData = (SessionData)session.getAttribute(SessionConst.LOGIN_SESSION);
	SysOperator operator =  sessionData.getSysOperator();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>�й������򽭷ֹ�˾һƽ̨���</title>
    <link href="../../css/systemBtm.css" rel="stylesheet" type="text/css">

  </head>
<style>*{margin:0;padding:0}
</style>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="btmTab" >
  <tr>
    <td class="Ltit">ϵͳ��Ϣ��</td>
    <td class="Rcont">
      <MARQUEE direction=left onmouseout=start(); onmouseover=stop();
		scrollAmount=2 scrollDelay=1>
		��ӭ�� ��<%=operator.getUser_name()%>�� �����й������򽭷ֹ�˾ͳһ��¼ƽ̨����ӭ��������飡
	  </MARQUEE>
	</td>
  </tr>
</table>

<div id="operationDIV1" style="display:none; position:absolute; left:0; top:50; width:420; z-index:1000" onMouseOver="show(1)" onMouseOut="hide(1)">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="winOp">
  <tr>
    <td class="td01"> </td>
    <td class="td02"><div  class="tit">VPMNҵ��</div></td>
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
						  <table  border="0" cellspacing="1" cellpadding="0" class="TabList">
							  <tr>
								<td class="tit">��Ա�̺�</td>
								<td>9040</td>
								<td class="tit">VPMNҵ���</td>
								<td>2500000264</td>
							  </tr>
							  <tr>
								<td class="tit">��ʼʱ��</td>
								<td>2005-11-03</td>
								<td class="tit">����ʱ��</td>
								<td> </td>
							  </tr>
							  <tr>
								<td class="tit">����ȥ��</td>
								<td>153030</td>
								<td class="tit">��������</td>
								<td>����</td>
							  </tr>
						  </table>

		</div>
		<!--������Ϣ END-->
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
</div>

</body>
</html>
<script type="text/javascript">
function F_showOperation(id){
    var targetDiv=document.getElementById("operationDIV"+id);
	if (targetDiv!=null){
	    var x=event.clientX;//��ȡ���ָ��λ������ڴ����¼��Ķ����X���� 
        var y=event.clientY; //��ȡ���ָ��λ������ڴ����¼��Ķ����Y���� 
        targetDiv.style.top=y;
		targetDiv.style.display="block";
	}
}

function F_hidOperation(id){
    var targetDiv=document.getElementById("operationDIV"+id);
	if (targetDiv!=null){
	    
		targetDiv.style.display="none";
	}
}
</script>
