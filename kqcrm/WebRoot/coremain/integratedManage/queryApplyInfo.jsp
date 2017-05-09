<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="../../WEB-INF/tlds/c-rt.tld" prefix="c" %>
<%@ page import="com.nl.util.GlobalConst"%>
<%@ page import="com.nl.integratedManage.actionForm.ApprovalFlowForm"%>
<%
String path = request.getContextPath();
ApprovalFlowForm afForm = (ApprovalFlowForm)request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=path%>">
    
    <title>申报情况查询</title>

  </head>
  
  <body>
    <form id="pagerForm" method="post" action="<%=path%>/approvalFlowAction.do?method=queryApplyInfo">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="20" />
  </form>

<div class="pageContent">
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="100" align="center">公司名称</th>
				<th width="120" align="center">申报日期</th>
				<th width="100" align="center">申请事项</th>
				<th width="150" align="center">原资质</th>
				<th width="80" align="center">申请资质</th>
				<th width="80" align="center">审批状态</th>
				<th width="80" align="center">联系人</th>
			</tr>
		</thead>
		<tbody>
			<%if(afForm.getTotalCount()>0){ %>
			<c:forEach items="${orderList}" var="list" varStatus="index">
			<tr target="sid" rel="${list.apply_order_id}">			    
			    <td><a href="<%=path %>/companyAction.do?method=toBaseinfo1&companyId=${list.company_info_id}" target="navTab" rel="baseinfo" title="企业详细信息" >${list.company_name}</a></td>
			    <td>${list.create_date}</td>
				<td>${list.apply_type}</td>
				<td>${list.apply_grade_old}</td>
				<td>${list.apply_grade_new}</td>
				<td>${list.task_name}</td>
				<td>${list.contacts_name}</td>
			</tr>
			</c:forEach>
			<%}else{ %>
			  <tr>
		          <td align="center" colspan="7">无数据！</td>
		      </tr>
			<%} %>
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
