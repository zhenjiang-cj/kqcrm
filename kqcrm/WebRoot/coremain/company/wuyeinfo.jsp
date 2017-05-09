<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.company.dt.*"%>
<%@ page import="com.nl.company.actionForm.*"%>
<%@page import="java.util.List"%>
<%@ page import="com.nl.util.GlobalConst"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<ProInfo> prolist = (List<ProInfo>) request.getAttribute("prolist");//
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
  
  <form id="pagerForm" method="post" action="<%=path%>/companyAction.do?method=toWuyeinfo">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="20" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=path%>/companyAction.do?method=toWuyeinfo" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					项目名称：<input type="text" name="pro_name" />
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
								
				<li><a class="add" href="<%=path%>/companyAction.do?method=toProAdd" target="navTab"><span>添加</span></a></li>
			<li class="line">line</li>
					<%
				}
				%>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="1" style="table-layout:fixed">
		<thead>
			<tr>
				<th width="10%">项目名称</th>
				<th width="10%">物业类型</th>
				<th width="20%">项目所在地</th>
				<th width="20%">建筑面积</th>
				<th width="20%">服务合同期</th>
				<th width="20%">操作</th>
			</tr>
		</thead>
		<tbody>
		<% if(prolist!=null&&prolist.size()>0){
			for(int i =0; i<prolist.size();i++){
				ProInfo  pro =  prolist.get(i);
			%>
			<tr target="sid_user" rel="1">
				<td title="<%=pro.getPro_name() %>" style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">
					<%=pro.getPro_name() %>
				</td>
				<td title="物业类型" style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">
					<%=DictMgmt.getValueDescs(DictMgmt.DICT_COMPANY_WUYE_TYPE,pro.getPro_type(),"" )%> 
				</td>
				<td title="<%=pro.getPro_addr() %>" style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">
					<%=pro.getPro_addr() %>
				</td>
				<td title="<%=pro.getHigh_flow_area() %>" style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">
					<%=pro.getHigh_flow_area() %>
				</td>
				<td title="服务合同期" style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">
					<%=pro.getContract_start_date() %>~<%=pro.getContract_end_date() %>
				</td>
				<td  class="panelBar">
				   <ul class="toolBar">
				   	<%
				if("".equals(comForm.getApply_order_id())){
					%>
								
					<li><a class="edit" href="<%=path%>/companyAction.do?method=toProEdit&pro_code=<%=pro.getPro_code() %>" target="navTab"><span>修改</span></a></li>
						<li><a class="delete" href="<%=path%>/companyAction.do?method=delPro&pro_code=<%=pro.getPro_code() %>" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
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
