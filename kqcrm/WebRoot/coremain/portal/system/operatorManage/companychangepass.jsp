<%@ page language="java" pageEncoding="GBK"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>

<%
SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss"); 
String ctime = formatter.format(new Date());
 

 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>��ͨס���칫ҵ��Эͬƽ̨</TITLE>
<META http-equiv=Content-Type content="text/html; charset=GBK">
<META content="text/html; charset=GBK" https-equiv="Content-Type">
<STYLE type=text/css>
BODY {
	MARGIN: 0px;
	background-color: #D4DFF3;
}
.12 {
	FONT-SIZE: 12px; COLOR: #e1eef6;
}
body,td,th {
	font-family: ����;
	font-size: 14px;
	color: #e1eef6;
}
a:link {
	text-decoration: none;
	color: #0072B5;
}
a:visited {
	text-decoration: none;
	color: #0072B5;
}
a:hover {
	text-decoration: none;
	color: #0072B5;
}
a:active {
	text-decoration: none;
	color: #0072B5;
}
.STYLE1 {
	color: #333333;
	line-height: 20px;
}
.STYLE2 {
	color: #000000;
	line-height: 20px;
	font-size: 12px;
}
</STYLE>

<script language="javascript">
/**
* JavaScript д��, ��ȡ, �h�� Cookie ֵ
**/
function SetCookie(name,value){
	var Days = 30; //�� cookie �������� 30 ��
	var exp  = new Date();
	exp.setTime(exp.getTime() + Days*24*60*60*1000);
	document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}
function getCookie(name){
	var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
	if(arr != null) return unescape(arr[2]); return null;
}

	
function check(){
	/*
	if (loginForm.oldPwd.value=="")
		{
		alert("�����벻��Ϊ��!");
		loginForm.userId.focus();
		return false;
		}
		*/
	if (loginForm.newPwd.value=="")
		{
		alert("�����벻��Ϊ��!");
		loginForm.newPwd.focus();
		return false;
		}
	
	if (loginForm.renewPwd.value=="")
		{
		alert("ȷ�������벻��Ϊ��!");
		loginForm.renewPwd.focus();
		return false;
		}
	if(loginForm.newPwd.value!=loginForm.renewPwd.value){
		alert("�������������벻һ��!");
		loginForm.renewPwd.focus();
		return false;
	
	}
	
	return true;

}
function toNext(event){
 var event = event || window.event;
 if(event.keyCode==13){
    event.keyCode=9;
 }
}
<%
String logincode = "";

if(request.getAttribute("logincode")!=null)
	logincode =  request.getAttribute("logincode").toString();
	out.println("logincode="+logincode);
if(!"".equals(logincode)){
	if("1".equals(logincode)){}
	else if("7".equals(logincode)){
		out.println("alert('��ʼ�����޸������������벻һ��')");
	}else if("8".equals(logincode)){
		out.println("alert('��ʼ�����޸Ĵ���')");
	}else
		out.println("alert('��ʼ�����޸��쳣')");
}

%>
</script>

</HEAD>
<BODY onLoad="loginForm.newPwd.focus();">
<table width="100%" border="0" cellpadding="0" cellspacing="0"
	background="<%=request.getContextPath() %>/images/htzj/login_bg.gif">
	<tr>
		<td height="50" class="12">&nbsp;&nbsp;&nbsp;&nbsp;��ӭ��¼����ͨס����ҵ�칫ҵ��Эͬƽ̨����  ��ǰ������ʱ��Ϊ��<%=ctime %></td>
	</tr>
</table>
<p>&nbsp;</p>
<table width="528" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td width="100%" height="256" background="<%=request.getContextPath() %>/images/htzj/login.gif"><br>
		<br>
		<br>
		<br>
		<form id="userLogin" name="loginForm" action="/htzj/login.do?method=login&go=1&checkflag=1" method="post" onSubmit="return check();">
		<table width="100%">
			<tr>
				<td width="80">&nbsp;</td>
				<td width="326">
				<span class="STYLE1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ʼ�����޸�</span>
				<br><br>
				<span class="STYLE1"> <!-- ԭ&nbsp;��&nbsp;�룺<input name="oldPwd" onkeydown="toNext(event);" type="text" size="22" maxlength="20"> 
				<br>-->
				��&nbsp;��&nbsp;�룺<input  name="newPwd" type="password" size="22" maxlength="20"> 
            	<br>
            	ȷ�������룺<input type="password" name="renewPwd" size="22" maxlength="20" value=""/>
            	<br><br><br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value=" ȷ �� " /> &nbsp;&nbsp;
				
				</span>
				</td>
			</tr>
		</table>
		<input type="hidden" name="changePassFlag" value="1"/>
		</form>




		</td>
	</tr>
	<tr>
		<td align="center" class="STYLE2">
			
		</td>
	</tr>
</table>
<p></p>


</BODY>
</HTML>



