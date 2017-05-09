<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=path%>">
    <link type="text/css" rel= "stylesheet" href="<%=path%>/css/content.css" >
    <title>图片展示</title>
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
            <td colspan=4 style="text-align:center;font-size:15px;font-weight:bold;">企业资质申请扫描件</td>
        </tr>  
        
        <c:forEach items="${picList}" var="list" varStatus="index">
		<tr >
			<td><img height='70' width='70' src='<%=path%>/specialSupport/showPhoto.do?file_id=${list.file_id}' onclick='openWindow(${fn:length(picList)-index.index})' style='cursor: pointer;'>
			<div id='light${fn:length(picList)-index.index}' class='white_content'><img height='100%' width='100%' src='<%=path%>/specialSupport/showPhoto.do?file_id=${list.file_id}' onclick='closeWindow(${fn:length(picList)-index.index})' style='cursor: pointer;'></div>
			<div id='fade${fn:length(picList)-index.index}' class='black_overlay'></div></td>
		</tr>
		</c:forEach>                      
               
    </table>
    </div>
    </div>
</div>
  </body>
</html>
<script type="text/javascript">
    
    function openWindow(num){
            document.getElementById('light'+num).style.display='block';
            document.getElementById('fade'+num).style.display='block';
        }
        
    function closeWindow(num){
        document.getElementById('light'+num).style.display='none';
        document.getElementById('fade'+num).style.display='none';
    }
    
     
</script>
