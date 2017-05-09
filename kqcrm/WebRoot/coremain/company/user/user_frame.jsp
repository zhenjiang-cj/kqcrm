<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.company.dt.*"%>
<%@ page import="com.nl.company.actionForm.*"%>
<%@page import="java.util.List"%>
<%@ page import="com.nl.util.GlobalConst"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<CompanyInfo> comlist = (List<CompanyInfo>) request.getAttribute("comlist");//
CompanyForm comForm = (CompanyForm) request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>人员管理</title>
    
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
  
  <form id="pagerForm" method="post" action="<%=path%>/companyAction.do?method=toCompanyUserManage">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="20" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=path%>/companyAction.do?method=toCompanyUserManage" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					用户编号：<input type="text" name="company_user_id" />
				</td>
				<td>
					用户名称：<input type="text" name="company_user_name" />
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
	<div class="panelBar">
		<ul class="toolBar">
			<li>
					<a class="add" href="<%=path%>/companyAction.do?method=toAddCompanyUser"
												 target="dialog" rel="dlg_page1"   title="新增用户" width="645" height="400">
												<span >新增用户</span></a>
			</li>
			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="20%">用户编号</th>
				<th width="20%">用户名称</th>
				<th width="10%">用户状态</th>
				<th width="50%">操作</th>
			</tr>
		</thead>
		<tbody>
		<% if(comlist!=null&&comlist.size()>0){
			for(int i =0; i<comlist.size();i++){
				CompanyInfo  com =  comlist.get(i);
				String status_name ="";
				String status = com.getStatus();
				if("1".equals(status)){
					status_name="正常";
				}else{
					status_name="失效";
				}
			%>
			<tr target="sid_user" rel="1">
				<td>
					<%=com.getCompany_user_id() %>
				</td>
				<td>
					<%=com.getCompany_user_name() %> 
				</td>
				<td>
					<%=status_name %> 
				</td>
				<td  class="panelBar">
				   <ul class="toolBar">
				<li><a class="edit" href="<%=path%>/companyAction.do?method=toEditCompanyUser&company_user_sno=<%=com.getCompany_user_sno() %>" target="dialog" width="645" height="470"><span>修改</span></a></li>
				<%
				if("1".equals(com.getStatus())){
					%>
					<li><a class="delete" href="<%=path%>/companyAction.do?method=delCompanyUser&company_user_sno=<%=com.getCompany_user_sno() %>" target="ajaxTodo" title="确定要失效吗?"><span>失效</span></a></li>
					<%
				}else{
					%>
					<li><a class="edit" href="<%=path%>/companyAction.do?method=regCompanyUser&company_user_sno=<%=com.getCompany_user_sno() %>" target="ajaxTodo" title="确定要激活吗?"><span>激活</span></a></li>
					<%
				}
				%>
				<li><a class="edit" href="<%=path%>/companyAction.do?method=resetCompanyUser&company_user_sno=<%=com.getCompany_user_sno() %>" target="ajaxTodo" title="重置密码"><span>重置密码</span></a></li>
						<li class="line">line</li>
						
					</ul>
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
