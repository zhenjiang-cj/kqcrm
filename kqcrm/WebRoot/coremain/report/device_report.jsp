<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.portal.dt.*"%>
<%@ page import="com.nl.portal.actionForm.*"%>
<%@page import="java.util.List"%>
<%@ page import="com.nl.util.GlobalConst"%>
<%@page import="com.nl.util.SessionData"%>
<%@page import="com.nl.util.SessionConst"%>
<%@page import="com.nl.util.GlobalUtil"%>
<%
	String path = request.getContextPath();
	
	List<CrmInfo> datalist = (List<CrmInfo>) request.getAttribute("datalist");
	CrmForm userform = (CrmForm) request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
	SessionData sessdata = (SessionData) request.getSession().getAttribute(SessionConst.LOGIN_SESSION);

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>设备统计</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page"> 
	<link href="<%=path%>/css/content.css" rel="stylesheet" type="text/css"  />
  </head>
  
  <body>
  
<form id="detailForm" onsubmit="return validateCallback(this,dialogAjaxDone)" method="post" action="<%=path%>/crmAction.do?method=queryDeviceReportSum">
	<input type="hidden" id="org_id" name="org_id" />	
</form>

<div class="pageContent">
    <%if(userform.getData_level()==2){ %>
	<div class="panelBar">
		<ul class="toolBar">

			<li ><a class="icon" href="<%=path%>/crmAction.do?method=queryDeviceReportSum" target="navTab" ><span float="right">返回</span></a></li>
		</ul>
	</div>
	<%} %>
	<table class="table" width="1400" layoutH="138"  style="table-layout:fixed">
		<thead>
			<tr>
				<th width="100">区域</th>
				<th width="100">设备安装总数</th>				 
				<th width="100">滤芯1</th>
				<th width="100">滤芯2</th>
				<th width="100">滤芯3</th>
				<th width="100">滤芯4</th>
			</tr>
		</thead>
		<tbody>
		
			<% if(datalist!=null&&datalist.size()>0){
				for(int i=0;i<datalist.size();i++){
					CrmInfo user = datalist.get(i);
					
					%>
					<tr>
						<td>
						<%if(userform.getData_level()==1){ %>
						<a href="<%=path%>/crmAction.do?method=queryDeviceReportDetail&org_id=<%=user.getOrg_id() %>" target="navTab" rel="deviceReport" ><%=user.getOrg_name() %></a>
						</td>
						<%}else{ %>
						<%=user.getOrg_name() %>
						<%} %>
						<td><%=user.getCnt_device() %></td>						
						<td><%=user.getMaterial1() %></td>
						<td><%=user.getMaterial2() %></td>
						<td><%=user.getMaterial3() %></td>
						<td><%=user.getMaterial4() %></td>
					</tr>
					
					<%
				}
			}
			%>
			
			
		</tbody>
	</table>
  
</div>

  </body>
 
</html>
<script type="text/javascript">

	function doBack()	
	{
		window.open('<%=path%>/crmAction.do?method=queryDeviceReportSum','navTab');
        //window.location.href='<%=path%>/crmAction.do?method=queryDeviceReportSum';
	    //document.getElementById("detailForm").submit();
	}
	
</script>