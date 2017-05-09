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
  
  <form id="pagerForm" method="post" action="<%=path%>/companyAction.do?method=toCompanyinfo">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="20" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=path%>/companyAction.do?method=toCompanyinfo" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					股东名称：<input type="text" name="investor_name" />
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
			<!-- <a class="add" href="<%=path%>/companyAction.do?method=toCompanyInfoAdd" target="navTab"><span>添加</span></a> -->
			<%
				if("".equals(comForm.getApply_order_id())){
					%>
					<a class="add" href="<%=path%>/companyAction.do?method=toCompanyInfoAdd&company_info_id=<%=comForm.getCompany_info_id() %>"
												 target="dialog" rel="dlg_page1"   title="新增企业投资者信息" width="645" height="400">
												<span  >新增企业投资者信息</span></a>
					<%
				}
				%>
			</li>
			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="20%">名称</th>
				<th width="20%">投资者类型</th>
				<th width="20%">出资类型（元）</th>
				<th width="20%">注册资本（元）</th>
				<th width="20%">操作</th>
			</tr>
		</thead>
		<tbody>
		<% if(comlist!=null&&comlist.size()>0){
			for(int i =0; i<comlist.size();i++){
				CompanyInfo  com =  comlist.get(i);
			%>
			<tr target="sid_user" rel="1">
				<td>
					<%=com.getInvestor_name() %>
				</td>
				<td>
					<%=DictMgmt.getValueDescs(DictMgmt.DICT_COMPANY_INVESTOR_TYPE,com.getInvestor_type(),"" )%> 
				</td>
				<td>
					<%=DictMgmt.getValueDescs(DictMgmt.DICT_COMPANY_INVESTMENT_WAY,com.getInvestor_type(),"" )%> 
				</td>
				<td>
					<%=com.getRegist_capital() %>
				</td>
				<td  class="panelBar">
				   <ul class="toolBar">
				   <%
				if("".equals(comForm.getApply_order_id())){
					%>
				<li><a class="edit" href="<%=path%>/companyAction.do?method=toEditCompanyInfo&investment_info_srl=<%=com.getInvestment_info_srl() %>" target="dialog" width="645" height="470"><span>修改</span></a></li>
						<li><a class="delete" href="<%=path%>/companyAction.do?method=delCompanyInfo&investment_info_srl=<%=com.getInvestment_info_srl() %>" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
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
