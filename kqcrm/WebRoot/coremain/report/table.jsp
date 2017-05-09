<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.report.dt.*"%>
<%@ page import="com.nl.report.actionForm.*"%>
<%@page import="java.util.List"%>
<%@ page import="com.nl.util.GlobalConst"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ReportForm repForm = (ReportForm) request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
List<ReportInfo> org_list = (List<ReportInfo>) request.getAttribute("org_list");//
List<ReportInfo> rep_list = (List<ReportInfo>) request.getAttribute("rep_list");//
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'user_resume.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="<%=path%>/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="<%=path%>/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="<%=path%>/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
	<link href="<%=path%>/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>

  </head>
  
  <body>
  
  <form id="pagerForm" method="post" action="<%=path%>/reportAction.do?method=toGetReport">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="20" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=path%>/reportAction.do?method=toGetReport" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					信息类型：
					<select  name="message_type" id="message_type" >
						<option value="" >全部</option>
						<option value="1" <%if("1".equals(repForm.getMessage_type())){%>selected<%} %>>住建动态</option>
						<option value="2" <%if("2".equals(repForm.getMessage_type())){%>selected<%} %>>通知公告</option>
						<option value="3" <%if("3".equals(repForm.getMessage_type())){%>selected<%} %>>政策法规</option>
						<option value="4" <%if("4".equals(repForm.getMessage_type())){%>selected<%} %>>办事指南</option>
						<option value="5" <%if("5".equals(repForm.getMessage_type())){%>selected<%} %>>资料档案</option>
						<option value="6" <%if("6".equals(repForm.getMessage_type())){%>selected<%} %>>权力事项</option>
					</select>
					发布处室：
					<select  name="org_id" id="org_id" >
								<option value="">全部</option>
						<%
						if(org_list!=null&&org_list.size()>0){
							for(int i=0;i<org_list.size();i++){
								ReportInfo org = org_list.get(i);
								%>
								<option value="<%=org.getOrg_id() %>" <%if(org.getOrg_id().equals(repForm.getOrg_id())){%>selected<%} %>><%=org.getOrg_name() %></option>
								<%
							}
						}
						%>
					</select>
					选择时间：<input type="text" name="begin_date" class="date" readonly="true" value="<%=repForm.getBegin_date() %>"/> ~ <input type="text" name="end_date" class="date" readonly="true"  value="<%=repForm.getEnd_date() %>"/>
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
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="15%">信息类型</th>
				<th width="20%">信息标题</th>
				<th width="20%">发布处室</th>
				<th width="20%">发布人员</th>
				<th width="20%">上传时间</th>
				<th width="10%">流量量</th>
			</tr>
		</thead>
		<tbody>
		<% if(rep_list!=null&&rep_list.size()>0){
			for(int i =0; i<rep_list.size();i++){
				ReportInfo  rep =  rep_list.get(i);
			%>
			<tr target="sid_user" rel="1">
				<td>
					<%=rep.getMessage_name() %>
				</td>
				<td>
					<%=rep.getMessage_title() %> 
				</td>
				<td>
					<%=rep.getOrg_name() %> 
				</td>
				<td>
					<%=rep.getOperater_fb() %>
				</td>
				<td>
					<%=rep.getCreate_date() %>
				</td>
				<td>
					<%=rep.getCnt_ll() %>
				</td>
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
				<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
					<option value="20">20</option>
					<option value="50">50</option>
					<option value="100">100</option>
					<option value="200">200</option>
				</select>
				<span>条，共${pager.totalCount}条</span>
			</div>
			
			<div class="pagination" targetType="navTab" totalCount="${pager.totalCount}" numPerPage="${pager.numPerPage}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
		
		</div>
</div>
  
  
  </body>
</html>
