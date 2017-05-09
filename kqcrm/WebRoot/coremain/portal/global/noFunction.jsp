<%@ page language="java" pageEncoding="GBK"%>
<%String contextpath = request.getContextPath(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>无权限提示界面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link  href="<%=contextpath %>/css/systemCont.css" rel="stylesheet" type="text/css">
</head>
<body class="Contbody">
	<div class="contAllDiv borNO">
		<!--显示工作区 START-->
		    <div class="divF"></div>
		  	<div class="workCont">
		  	   <div class="failD">
		         <div class="Divs">
		           <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                 <tr>
	                   <td><div class="picS">&nbsp;</div></td>
	                   <td><div class="fonts">您没有该功能的权限 ！</div></td>
	                 </tr>
	               </table>	
	            </div>
			 </div>
		</div>
		<!--显示工作区 END-->
	</div>
</body>
</html>
