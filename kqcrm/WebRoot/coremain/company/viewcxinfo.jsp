<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.company.dt.*"%>
<%@ page import="com.nl.company.actionForm.*"%>
<%@page import="java.util.List"%>
<%@ page import="com.nl.util.GlobalConst"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.NumberFormat"%>
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
  
  <form id="pagerForm" method="post" action="<%=path%>/companyAction.do?method=viewCxinfo">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="20" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=path%>/companyAction.do?method=viewCxinfo" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					出具单位： 
					<select  name="prove_dept" id="prove_dept" >
							<option value="">全部</option>
							<option value="1">京口住建局</option>
							<option value="2">润州住建局</option>
							<option value="3">丹徒住建局</option>
							<option value="4">新区建设局</option>
							<option value="5">丹阳住建局</option>
							<option value="6">句容住建局</option>
							<option value="7">扬中住建局</option>
							<option value="8">其他</option>
					</select>
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
				<th width="50%">出具单位</th>
				<th width="50%">操作</th>
			</tr>
		</thead>
		<tbody>
		<% if(comlist!=null&&comlist.size()>0){
			for(int i =0; i<comlist.size();i++){
				CompanyInfo  com =  comlist.get(i);
				String prove_dept_id= com.getProve_dept();
				String prove_dept = "";
				if("1".equals(prove_dept_id)){
					prove_dept = "京口住建局";
				}else if("2".equals(prove_dept_id)){
					prove_dept = "润州住建局";
					
				}else if("3".equals(prove_dept_id)){
					prove_dept = "丹徒住建局";
					
				}else if("4".equals(prove_dept_id)){
					prove_dept = "新区建设局";
					
				}else if("5".equals(prove_dept_id)){
					prove_dept = "丹阳住建局";
					
				}else if("6".equals(prove_dept_id)){
					prove_dept = "句容住建局";
					
				}else if("7".equals(prove_dept_id)){
					prove_dept = "扬中住建局";
					
				}else if("8".equals(prove_dept_id)){
					prove_dept = "其他";
					
				}
			%>
			<tr target="sid_user" rel="1">
				<td>
					<%=prove_dept %>
				</td>
				<td  class="panelBar">
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
			<span>条，共${totalCount}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="200" numPerPage="20" pageNumShown="10" currentPage="1"></div>

	</div>
</div>
  
  
  </body>
</html>
