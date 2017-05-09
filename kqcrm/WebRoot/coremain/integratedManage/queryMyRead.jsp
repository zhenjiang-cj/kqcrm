<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="../../WEB-INF/tlds/c-rt.tld" prefix="c" %>
<%@ taglib uri="../../WEB-INF/tlds/fn.tld" prefix="fn" %>
<%@ page import="com.nl.integratedManage.actionForm.ApprovalFlowForm"%>
<%@ page import="com.nl.base.utils.GlobalFunc"%>
<%@ page import="com.nl.util.GlobalConst"%>
<%
String path = request.getContextPath();
ApprovalFlowForm afForm = (ApprovalFlowForm)request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=path%>">
    
    <title>已办件</title>

  </head>
  
  <body>
    <form id="pagerForm" method="post" action="<%=path%>/approvalFlowAction.do?method=queryMyRead">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="20" />
  </form>


<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="<%=path%>/approvalFlowAction.do?method=queryMyRead" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					企业名称：<input type="text" name="companyName" size="30" />
				</td>
				<td>
					申请事项：
					<select id="applyType" name="applyType">
						<option value="-99">--请选择--</option>
						<option value="1">初次申请</option>
						<option value="2">核定等级</option>
						<option value="3">资质延续</option>
						<option value="4">事项变更</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan=2>
					审批时间：
					<input type="text" name="beginDate" class="date" size="14" />
					-
					<input type="text" name="endDate" class="date" size="14" />
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
				<th width="50" align="center">序号</th>
				<th width="100" align="center">企业名称</th>
				<th width="80" align="center">企业类型</th>	
				<th width="80" align="center">申请事项</th>
				<th width="80" align="center">申请等级</th>	
				<th width="80" align="center">申报日期</th>
				<th width="80" align="center">联系人</th>				
				<th width="80" align="center">审核时间</th>
				<th width="80" align="center">审核意见</th>			
				<th width="80" align="center">审批状态</th>	
			</tr>
		</thead>
		<tbody>
			<%if(afForm.getTotalCount()>0){ %>
			<c:forEach items="${orderList}" var="list" varStatus="index">
			<tr target="sid" rel="${list.apply_order_id}">
				<td>${index.index+1}</td>
			    <td><a href="<%=path %>/companyAction.do?method=toViewFrame&companyId=${list.company_info_id}" target="dialog" rel="baseinfo" title="企业详细信息" max="true">${list.company_name}</a></td>
			    <td>${list.company_type}</td>
				<td>${list.apply_type}</td>
				<td>${list.apply_grade}</td>
				<td>${list.create_date}</td>
				<td>${list.contacts_name}</td>				
				<td>${list.check_date_now}</td>
				<td>${list.check_desc_now}</td>
				<td>${list.task_instance_name}</td>
			</tr>
			</c:forEach>
			<%}else{ %>
			  <tr>
		          <td align="center" colspan="10">无数据！</td>
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
<script type="text/javascript">
    jQuery(document).ready(function(){
		jQuery("input[name='companyName']").val('<%=GlobalFunc.initStr(afForm.getCompanyName(),"")%>');
		jQuery("#applyType").val('<%=GlobalFunc.initStr(afForm.getApplyType(),"-99")%>');
		jQuery("input[name='beginDate']").val('<%=GlobalFunc.initStr(afForm.getBeginDate(),"")%>');
		jQuery("input[name='endDate']").val('<%=GlobalFunc.initStr(afForm.getEndDate(),"")%>');
    })


</script>
