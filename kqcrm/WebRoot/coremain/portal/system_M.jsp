<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>NGBOSS_子系统_缩进列</title>
<link href="./css/systemM.css"  type="text/css" rel="stylesheet" />

<script language="javascript" type="text/javascript">
function change(){
    if (document.all.changeFlag.value == 1)
    {
    	/*
        if(parent.document.getElementById("subformset").cols == "179,9,*")
		{
			alert('222222222');
			parent.document.getElementById("subformset").cols = "0,9,*"
			alert('333333333');
			this.document.getElementById("bnt").className="bntM02"
			
		}
		else
		{
			parent.document.getElementById("subformset").cols = "179,9,*"
			this.document.getElementById("bnt").className="bntM"
		}
		*/
		if(parent.document.getElementById("leftContent").style.display == 'none')
		{
				parent.document.getElementById("contents").style.width = '' ;
				parent.document.getElementById("leftContent").style.width = '' ;
				parent.document.getElementById("leftContent").style.display = 'block' ;
				var maxWidth = Math.max(
					parent.document.documentElement["clientWidth"],
					parent.document.body["scrollWidth"], parent.document.documentElement["scrollWidth"],
					parent.document.body["offsetWidth"], parent.document.documentElement["offsetWidth"]
				);
				parent.document.getElementById("contents").style.width = ( maxWidth - 185 ) +'px';
				parent.document.getElementById("leftContent").width = '176px' ;
				//parent.document.getElementById("contents").style.width = '75%' ;
				this.document.getElementById("bnt").className="bntM02";
		}
		else
		{
			parent.document.getElementById("contents").style.width = '' ;
			parent.document.getElementById("leftContent").style.width = '' ;
			parent.document.getElementById("leftContent").style.display = 'none' ;
			parent.document.getElementById("contents").style.width = '99%' ;
			this.document.getElementById("bnt").className="bntM";
		}
    }	
}   

function change1(){
    if (document.all.changeFlag.value == 0)
    {
        //if(parent.document.getElementById("subformset").cols == "179,9,*")
		//{
			//parent.document.getElementById("subformset").cols = "0,9,*"
			if(parent.document.getElementById("leftContent").style.display == 'none')
			{
				parent.document.getElementById("contents").style.width = '99%' ;
			    this.document.getElementById("bnt").className="bntM02";//控制按钮
			}
			
		//}
		else
		{
			//parent.document.getElementById("subformset").cols = "179,9,*"
			parent.document.getElementById("contents").style.width = '' ;
			parent.document.getElementById("leftContent").style.display = 'block' ;
			var maxWidth = Math.max(
				parent.document.documentElement["clientWidth"],
				parent.document.body["scrollWidth"], parent.document.documentElement["scrollWidth"],
				parent.document.body["offsetWidth"], parent.document.documentElement["offsetWidth"]
			);
			parent.document.getElementById("contents").style.width = ( maxWidth - 185 ) +'px';
			parent.document.getElementById("leftContent").width = '176px' ;
			//parent.document.getElementById("leftContent").style.width = '15%' ;
			//parent.document.getElementById("contents").style.width = '75%' ;
			this.document.getElementById("bnt").className="bntM02";
		}
    }	
} 
</script>
</head>
<body onload="change1()">
<input type="hidden" name="changeFlag" value="0"/>
<table border="0" cellspacing="0" cellpadding="0" class="Mtab" >
  <tr>
    <td><input name="" id="bnt" type="button" class="bntM" onClick="change()"></td>
  </tr>
</table>
</body>
</html>
