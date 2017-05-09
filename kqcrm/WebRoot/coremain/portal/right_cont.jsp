<%@ page language="java" pageEncoding="GBK"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>镇江人寿统一登录平台</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<link  href="./css/systemCont.css" rel="stylesheet" type="text/css">
</head>
<style>*{margin:0;padding:0}
</style>
<body class="Contbody" scrolling="auto" >
	<div class="contAllDiv borNO">
		<!--显示工作区 START-->
		    
<div class="workCont" style=" display:block">
<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
	<tr id="tr1" style="display:block">
		<td>
			<iframe id="iframe1" frameborder="0" width="100%" height="100%" src="./coremain/portal/welcome.html" scrolling="auto"></iframe>
		</td>
	</tr>
	
	<tr id="tr2" style="display:none">
		<td>
			<iframe id="iframe2" frameborder="0" width="100%" height="100%" src="" scrolling="auto"></iframe>
		</td>
	</tr>
	
	<tr id="tr3" style="display:none">
		<td>
			<iframe id="iframe3" frameborder="0" width="100%" height="100%" src="" scrolling="auto"></iframe>
		</td>
	</tr>
	
	<tr id="tr4" style="display:none">
		<td>
			<iframe id="iframe4" frameborder="0" width="100%" height="100%" src=""  scrolling="auto"></iframe>
		</td>
	</tr>
	
	<tr id="tr5" style="display:none">
		<td>
			<iframe id="iframe5" frameborder="0" width="100%" height="100%" src=""  scrolling="auto"></iframe>
		</td>
	</tr>
	
</table>
		</div>

		<!--显示工作区 END-->
</div>	  

<script language="javascript" type="text/javascript">
function showFrame(id,taskurl)
{
	for(i=1;i<=5;i++)
	{
		this.document.getElementById("tr"+i).style.display = "none";
	}
	this.document.getElementById("tr"+id).style.display = "block";
	this.document.getElementById("iframe"+id).src = taskurl;
	
}

function moveAndShowFrame(id,taskurl)
{
	this.document.getElementById("iframe1").src = this.document.getElementById("iframe2").src;
	this.document.getElementById("iframe2").src = this.document.getElementById("iframe3").src;
	this.document.getElementById("iframe3").src = this.document.getElementById("iframe4").src;
	this.document.getElementById("iframe4").src = this.document.getElementById("iframe5").src;
	this.document.getElementById("iframe5").src = taskurl;
	showFrameById(5);
}


//
function showFrameById(id)
{
	for(i=1;i<=5;i++)
	{
		this.document.getElementById("tr"+i).style.display = "none";
	}
	this.document.getElementById("tr"+id).style.display = "block";
}

//
function deleteFrameByIdAndShowNextById(id)
{
	this.document.getElementById("tr"+id).style.display="none";
	this.document.getElementById("iframe"+id).src = "";
	showFrameById(id-1);
	 //this.document.getElementById("tr"+(id-1)).style.display="block";
}

function deleteFrame(id)
{
	//alert(id);
	this.document.getElementById("tr"+id).style.display="none";
	this.document.getElementById("iframe"+id).src="";
}
		
function moveFrame(id)
{
	//alert(id);
	this.document.getElementById("iframe"+id).src = this.document.getElementById("iframe"+(id+1)).src;
	//alert("iframe" + id + ".src = "+this.document.getElementById("iframe"+id).src);
	
}


function showFrame1(id)
{	
	//alert("iframe" + id + ".src = "+this.document.getElementById("iframe"+id).src);
	//alert(id);
	this.document.getElementById("tr"+id).style.display="block";
}
function hideFrame(id)
{
	this.document.getElementById("tr"+id).style.display="none";
}

function change(url)
{
	parent.document.getElementById("leftContent").src=url;
}

</script>
</body>
</html>
