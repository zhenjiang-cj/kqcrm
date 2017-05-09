<%@ page language="java"  pageEncoding="GBK"%>
<%@page import="com.nl.util.GlobalConst"%>
<%@page import="com.nl.portal.actionForm.OperatorForm"%>

<%
	String path = request.getContextPath();
	
	OperatorForm operatorForm = (OperatorForm)request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
	
	String param = "&userId=" + operatorForm.getUserId()
                 + "&userName=" + operatorForm.getUserName();
	
	String name="";	

	if(operatorForm.getFunctionId().equals(GlobalConst.FUNCTION_OPERATOR_UPDATE))//修改操作员
	{    
		name="修改操作员资料";
	}
	else if(operatorForm.getFunctionId().equals(GlobalConst.FUNCTION_OPERATOR_ROLE_UPDATE))//修改操作员角色
	{    
		name="修改操作员角色";
	}
	else if(operatorForm.getFunctionId().equals(GlobalConst.FUNCTION_OPERATOR_PASSWORD_RESET))//重置作员角色
	{    
		name="重置操作员密码";
	}

	else if(operatorForm.getFunctionId().equals(GlobalConst.FUNCTION_OPERATOR_ADD))//新增工号
	{    
		name="新增工号";
	}

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
  </head>
  <body>
 
  </body>
<script type="text/javascript" src="<%=path%>/plugins/jquery/jquery-1.4.4.js"></script>
  <script type="text/javascript">
   	//加载后执行
	jQuery(document).ready(function(){
		<%
		if(operatorForm.getFlag()==0){
		%>
			parent.parent.showM("<%=name%>成功!",'1');
  		<%
  		}else{
  			param = "";
		%>
			parent.parent.showM("<%=name%>失败!",'2');
		<%
		}
		%>

  		<%
  		if(operatorForm.getFunctionId().equals(GlobalConst.FUNCTION_OPERATOR_UPDATE)){   //操作员资料修改
  		%>
  			 window.location.href="./operator.do?method=queryList&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_ROLE_MANAGE%>" + "<%=param%>";
  		<%
  		}else if(operatorForm.getFunctionId().equals(GlobalConst.FUNCTION_OPERATOR_ROLE_UPDATE))//修改
  		{
  		%>
	  		window.location.href="./operator.do?method=queryList&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_ROLE_MANAGE%>" + "<%=param%>";
  		<%
  		}else if(operatorForm.getFunctionId().equals(GlobalConst.FUNCTION_OPERATOR_PASSWORD_RESET))//重置密码
  		{
  		%>
	  		window.location.href="./operator.do?method=queryList&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_ROLE_MANAGE%>" + "<%=param%>";
  		
  		<%
		}else if(operatorForm.getFunctionId().equals(GlobalConst.FUNCTION_OPERATOR_ADD)){//新增工号
  		%>
	  		window.location.href="./operator.do?method=queryList&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_ROLE_MANAGE%>" ;
  		<%
		}
  		%>

	  		
  			
	});
  </script>
</html>
