<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=path%>">
    <link type="text/css" rel= "stylesheet" href="<%=path%>/css/content.css" >
    <title></title>
  </head>
  <style>
	.div_container{width:0;height:0;position:fixed;left:50%;rigth:50%;top:50%;bottom:50%;}
	.div_position{width:600px;height:220px;margin-left:-300px;margin-top:-110px}
  </style>
<body>

<div class="pageFormContent" layoutH="56">
	
	<div class="div_container">
	<div class="div_position">
	<table class="TabList"> 
		<tr style="margin-top:20px;">
            <td colspan=4 style="text-align:center;font-size:30px;font-weight:bold;">缺少企业信息，请先填写企业基本信息！</td>
        </tr>                        
    </table>
    </div>
    </div>
    
</div>
  </body>
</html>

