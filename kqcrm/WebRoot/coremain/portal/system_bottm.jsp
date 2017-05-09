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
    <title>中国人寿镇江分公司一平台框架</title>
    <link href="../../css/systemBtm.css" rel="stylesheet" type="text/css">

  </head>
<style>*{margin:0;padding:0}
</style>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="btmTab" >
  <tr>
    <td class="Ltit">系统消息：</td>
    <td class="Rcont">
      <MARQUEE direction=left onmouseout=start(); onmouseover=stop();
		scrollAmount=2 scrollDelay=1>
		欢迎您 “<%=operator.getUser_name()%>” 进入中国人寿镇江分公司统一登录平台，欢迎提出宝贵建议！
	  </MARQUEE>
	</td>
  </tr>
</table>

<div id="operationDIV1" style="display:none; position:absolute; left:0; top:50; width:420; z-index:1000" onMouseOver="show(1)" onMouseOut="hide(1)">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="winOp">
  <tr>
    <td class="td01"> </td>
    <td class="td02"><div  class="tit">VPMN业务</div></td>
    <td class="td03"> </td>
    <td class="td04"> </td>
    <td class="td05"> </td>
  </tr>
</table>
<!--标题栏 END-->
<!--内容 START-->
<DIV class="winOpM">
<div class="contAllDiv borNO">
		<!--浮动信息START-->
		<div class="workCont">
						  <table  border="0" cellspacing="1" cellpadding="0" class="TabList">
							  <tr>
								<td class="tit">成员短号</td>
								<td>9040</td>
								<td class="tit">VPMN业务号</td>
								<td>2500000264</td>
							  </tr>
							  <tr>
								<td class="tit">开始时间</td>
								<td>2005-11-03</td>
								<td class="tit">结束时间</td>
								<td> </td>
							  </tr>
							  <tr>
								<td class="tit">网内去话</td>
								<td>153030</td>
								<td class="tit">网内来话</td>
								<td>本地</td>
							  </tr>
						  </table>

		</div>
		<!--浮动信息 END-->
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
</div>

</body>
</html>
<script type="text/javascript">
function F_showOperation(id){
    var targetDiv=document.getElementById("operationDIV"+id);
	if (targetDiv!=null){
	    var x=event.clientX;//获取鼠标指针位置相对于触发事件的对象的X坐标 
        var y=event.clientY; //获取鼠标指针位置相对于触发事件的对象的Y坐标 
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
