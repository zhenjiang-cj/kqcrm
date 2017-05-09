<%@ page language="java"  pageEncoding="GBK"%>
<%@page import="com.nl.portal.actionForm.RoleForm"%>
<%@page import="com.nl.util.GlobalConst"%>

<%
	String path = request.getContextPath();
	
	RoleForm roleForm = (RoleForm)request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM); 
	
	String param = "&sysId=" + roleForm.getSysId()
                 + "&roleName=" + roleForm.getRoleName()
                 + "&roleType=" + roleForm.getRoleType();

	
	String name="";	

	if(roleForm.getOperationType().equals(GlobalConst.OPERATION_INSERT))//增加
	{ 
		name="增加操作员角色";
	}
	else if(roleForm.getOperationType().equals(GlobalConst.OPERATION_UPDATE))//修改
	{    
		name="修改操作员角色";
	}
	else if(roleForm.getOperationType().equals(GlobalConst.OPERATION_DELETE))//删除
	{    
		name="删除操作员角色";
	}
	else if(roleForm.getOperationType().equals(GlobalConst.FUNCTION_ROLE_BATCH))//删除
	{    
		name="批量赋角色";
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
		if(roleForm.getFlag()==0){
		%>
			parent.parent.showM("<%=name%>成功!",'1');
  		<%
  		}else{
		%>
			parent.parent.showM("<%=name%>失败!",'2');
		<%
		}
		%>

  		<%
  		if(roleForm.getOperationType().equals(GlobalConst.OPERATION_INSERT)){   //增加
  		%>
  			 window.location.href="./role.do?method=queryList&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_ROLE_MANAGE%>" + "<%=param%>";
  		<%
  		}else if(roleForm.getOperationType().equals(GlobalConst.OPERATION_UPDATE))//修改
  		{
  		%>
	  		 window.location.href="./role.do?method=queryList&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_ROLE_MANAGE%>" + "<%=param%>";
  		<%
  		}
        else if(roleForm.getOperationType().equals(GlobalConst.OPERATION_DELETE))//删除
  		{
  		%>
	  		 window.location.href="./role.do?method=queryList&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_ROLE_MANAGE%>" + "<%=param%>";
  		<%
  		}else if(roleForm.getOperationType().equals(GlobalConst.FUNCTION_ROLE_BATCH)){
  		%>
  			window.location.href="./role.do?method=preBatchRole&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_ROLE_BATCH%>" + "<%=param%>";
  		<%
  		}
  		%>
	});
  </script>
</html>
