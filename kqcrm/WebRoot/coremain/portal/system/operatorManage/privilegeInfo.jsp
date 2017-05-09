<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.nl.base.utils.GlobalFunc"%>
<%@page import="com.nl.util.GlobalConst"%>
<%@page import="com.nl.portal.actionForm.RoleForm"%>
<%@page import="com.nl.portal.actionForm.OperatorForm"%>
<%@page import="com.nl.portal.dt.*"%>
<%@page import="com.nl.util.GlobalConst"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.util.SessionData"%>
<%@page import="com.nl.util.SessionConst"%>
<%@ taglib uri="/pageListTag.tld" prefix="pageTags"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String contextpath = request.getContextPath();
    OperatorForm operatorForm = (OperatorForm)request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
    SessionData sessdata = (SessionData) request.getSession().getAttribute(SessionConst.LOGIN_SESSION);    
    
    List<IscPriDataRel> list = (List<IscPriDataRel>)request.getAttribute("list");
    IscPriDataRel operator;
    int resultSum = 0;
    String param = "&dataType=" + operatorForm.getDataType()
                 + "&sysId=" + operatorForm.getSysId();
    String url = "./operator.do?method=queryPrivilegeInfo&systemId="+GlobalConst.SYSTEM_ID_SYSTEM+"&functionId="+GlobalConst.FUNCTION_OPERATOR_ROLE_UPDATE
                 + param;
%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>操作员管理</title>
    <link  href="<%=contextpath%>/css/systemCont.css" rel="stylesheet" type="text/css">
</head>
<body class="Contbody">
	<div class="contAllDiv borNO">
		<!--显示工作区 START-->
		    <div class="divF"></div>
		  	<div class="workCont">
		  	  <form action="<%=contextpath%>/operator.do?method=queryPrivilegeInfo" method="post" name="operatorForm">
		  	    <input type="hidden" name="functionId" value="<%=GlobalConst.FUNCTION_OPERATOR_ROLE_UPDATE%>">
		  	    <input type="hidden" name="systemId" value="<%=GlobalConst.SYSTEM_ID_SYSTEM%>">
				<input type="hidden" id="dataType" name="dataType" value="<%=operatorForm.getDataType() %>">
				<input type="hidden" id="sysId" name="sysId" value="<%=operatorForm.getSysId() %>">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="Title01">
				  <tr>
					<td class="td01"></td>
					<td class="td02"><div class="F01">权限角色信息列表</div><div class="S01"><img src="<%=contextpath%>/images/portal/contTit12.gif"></div></td>
					<td class="td03"></td>
				  </tr>
				</table>
				<!--标题栏 END-->
				<!--表格1 START-->
				  <table  width="100%" border="0" cellspacing="1" cellpadding="0"  class="TabList">
					  <tr>
						<th width="10%">数据类型</th>
						<th width="40%">角色名称</th>
						<th width="40%">权限名称</th>
					  </tr>
					  <%
					      if (list != null && list.size() > 0)
					      {
					          for (int i = 0; i< list.size(); i++)
					          {	
					              	operator = list.get(i);	      
					  %>
					       <tr>
					          <td align="center"><%=operator.getData_type() %></td>
					          <td align="left"><%=operator.getRole_name() %></td>
					          <td align="left"><%=operator.getPrivilege_name() %></td>
					       </tr>
					  <%
					          }					  
					      }
					      else
					      {
					  %>
					      <tr>
					          <td align="center" colspan="6">没有符合条件的结果，请根据条件后重新查询！</td>
					      </tr>
					  <%
					      }
					  %>
				     
		      </table>
		      
		       <div class="OpLine">
		         <input name=""  type="button" value="关 闭"  class="bntSty" onClick="goBack()"/>
		       </div>
		      </form>  
			</div>
			
		<!--显示工作区 END-->
	</div>
</body>
</html>
<script type="text/javascript" src="<%=contextpath%>/plugins/jquery/jquery-1.4.4.js"></script>
<script type="text/javascript" src="<%=contextpath%>/plugins/jquery/plugins/dialog/Dialog.js"></script>
<script type="text/javascript">
    jQuery(document).ready(function(){
    })
    function goBack()
    {
    	Dialog.close();
    }
</script>
