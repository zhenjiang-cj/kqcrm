<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.company.dt.*"%>
<%@ page import="com.nl.company.actionForm.*"%>
<%@page import="java.util.List"%>
<%@ page import="com.nl.util.GlobalConst"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<ProPerfInfo> prolist = (List<ProPerfInfo>) request.getAttribute("prolist");//
CompanyForm comForm = (CompanyForm) request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
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
  
  <form id="pagerForm" method="post" action="<%=path%>/companyAction.do?method=toWuyePerf">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="20" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=path%>/companyAction.do?method=toWuyePerf" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					年度： 
					<input type="text" name="year" class="date" dateFmt="yyyy" readonly="true"  />
										<a class="inputDateButton" href="javascript:;">选择</a>
										<span class="info">yyyy</span>
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
			<%
				if("".equals(comForm.getApply_order_id())){
					%>
								
					<li><a class="add" href="<%=path%>/companyAction.do?method=toWuyePerfAdd" target="dialog" width="645" height="470"><span>添加</span></a></li>
			<li class="line">line</li>
					<%
				}
				%>
			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="10%">年度</th>
				<th width="10%">万米以上小区(个)</th>
				<th width="20%">整改小区(个)</th>
				<th width="20%">总建筑面积(㎡)</th>
				<th width="20%">年度企业利润总额(元)</th>
				<th width="20%">操作</th>
			</tr>
		</thead>
		<tbody>
		<% if(prolist!=null&&prolist.size()>0){
			for(int i =0; i<prolist.size();i++){
				ProPerfInfo  pro =  prolist.get(i);
			%>
			<tr target="sid_user" rel="1">
				<td>
					<%=pro.getYear() %>
				</td>
				<td>
					<%=pro.getWpm_district() %>
				</td>
				<td>
					<%=pro.getRectification_district() %>
				</td>
				<td>
					<%=pro.getFull_built_area() %>
				</td>
				<td>
					<%=pro.getFull_year_profit() %>
				</td>
				<td  class="panelBar">
				   <ul class="toolBar">
				      	<%
				if("".equals(comForm.getApply_order_id())){
					%>
								
					<li><a class="edit" href="<%=path%>/companyAction.do?method=toEditWuyePerf&operating_performance_srl=<%=pro.getOperating_performance_srl() %>" target="dialog" width="645" height="470"><span>修改</span></a></li>
						<li><a class="delete" href="<%=path%>/companyAction.do?method=delWuyePerf&operating_performance_srl=<%=pro.getOperating_performance_srl() %>" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
						<li class="line">line</li>
					<%
				}
				%>
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
