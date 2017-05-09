<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'serchuser.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <form id="pagerForm" action="<%=path%>/companyAction.do?method=doUserSearch">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${model.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="<%=path%>/companyAction.do?method=doUserSearch" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>员工姓名:</label>
				<input class="textInput" name="company_name" value="" type="text">
			</li>	  
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="118" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th orderfield="company_name">公司名称</th>
				<th orderfield="employee_id">员工编号</th>
				<th orderfield="employee_name">员工姓名</th>
				<th width="80">查找带回</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>技术部</td>
				<td>1001</td>
				<td>administrator</td>
				<td>administrator</td>
				<td>
					<a class="btnSelect" href="javascript:$.bringBack({id:'1', orgName:'技术部', orgNum:'1001'})" title="查找带回">选择</a>
				</td>
			</tr>
			<tr>
				<td>人事部</td>
				<td>1002</td>
				<td>test</td>
				<td>administrator</td>
				<td>
					<a class="btnSelect" href="javascript:$.bringBack({id:'2', orgName:'人事部', orgNum:'1002'})" title="查找带回">选择</a>
				</td>
			</tr>
		</tbody>
	</table>

	<div class="panelBar">
		<div class="pages">
			<span>每页</span>

			<select name="numPerPage" onchange="dwzPageBreak({targetType:dialog, numPerPage:'10'})">
				<option value="10" selected="selected">10</option>
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
			</select>
			<span>条，共2条</span>
		</div>
		<div class="pagination" targetType="dialog" totalCount="2" numPerPage="10" pageNumShown="1" currentPage="1"></div>
	</div>
</div>
  </body>
</html>
