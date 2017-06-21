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
	
	List<CrmInfo> repairlist = (List<CrmInfo>) request.getAttribute("repairlist");//
	CrmForm userform = (CrmForm) request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
	SessionData sessdata = (SessionData) request.getSession().getAttribute(SessionConst.LOGIN_SESSION);

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>维修记录查询</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page"> 
	<link href="<%=path%>/css/content.css" rel="stylesheet" type="text/css"  />
  </head>
  
  <body>
  
  <form id="pagerForm" method="post" action="<%=path%>/crmAction.do?method=queryRepairRecord">
	<input type="hidden" name="pageNum" value="${pager.pageNum}" />
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
</form>


<div class="pageHeader">
	<form rel="pagerForm"  onsubmit="return navTabSearch(this);" action="<%=path%>/crmAction.do?method=queryRepairRecord" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					客户姓名：<input type="text" name="kh_name" id="kh_name"  value="<%=userform.getKh_name()==null?"":userform.getKh_name() %>" />
				</td>

			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" width="1400" layoutH="138"  style="table-layout:fixed">
		<thead>
			<tr>
				<th width="100">客户编码</th>
				<th width="100">客户姓名</th>				 
				<th width="100">电话号码</th>
				<th width="140">地址</th>  
				<th width="100">报修日期</th>
				<th width="140">报修内容</th>
				<th width="100">维修人员</th>
				<th width="140">维修时间</th>
				<th width="140">故障原因</th>
			</tr>
		</thead>
		<tbody>
		
			<% if(repairlist!=null&&repairlist.size()>0){
				for(int i=0;i<repairlist.size();i++){
					CrmInfo user = repairlist.get(i);
					
					%>
					<tr target="sid_user" rel="1">
						<td><%=user.getKh_id() %></td>
						<td><%=user.getKh_name() %></td>						
						<td><%=user.getKh_phone1() %></td>
						<td  title="<%=user.getKh_addr() %>" style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;"><%=user.getKh_addr() %></td>
						<td><%=user.getWarranty_date()==null?"":user.getWarranty_date() %></td>
						<td  title="<%=user.getWarranty_content() %>" style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;"><%=user.getWarranty_content() %></td>
						<td><%=user.getRepair_person()==null?"":user.getRepair_person() %></td>
						<td><%=user.getRepair_date()==null?"":user.getRepair_date() %></td>
						<td  title="<%=user.getRepair_reason() %>" style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;"><%=user.getRepair_reason() %></td>
					</tr>
					
					<%
				}
			}
			%>
			
			
		</tbody>
	</table>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span>
				<span>20条，共${pager.totalCount}条</span>
			</div>
			
			<div class="pagination" targetType="navTab" totalCount="${pager.totalCount}" numPerPage="${pager.numPerPage}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
		
		</div>
  
</div>
  </body>
 
</html>
