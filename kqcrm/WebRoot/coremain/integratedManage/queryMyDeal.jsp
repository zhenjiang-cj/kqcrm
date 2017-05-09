<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="../../WEB-INF/tlds/c-rt.tld" prefix="c" %>
<%@ taglib uri="../../WEB-INF/tlds/fn.tld" prefix="fn" %>
<%@ page import="com.nl.util.GlobalConst"%>
<%@ page import="com.nl.base.utils.GlobalFunc"%>
<%@ page import="com.nl.integratedManage.actionForm.ApprovalFlowForm"%>
<%
	String path = request.getContextPath();
	ApprovalFlowForm afForm = (ApprovalFlowForm)request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=path%>">
    
    <title>待办件</title>

  </head>
  
  <body>
    <form id="pagerForm" method="post" action="<%=path%>/approvalFlowAction.do?method=queryMyDeal">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="20" />
  </form>


<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="<%=path%>/approvalFlowAction.do?method=queryMyDeal" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					企业名称：<input type="text" name="companyName" size="30" />
				</td>
				<td>
					企业类型：
					<select name="companyType">
						<option value="-99">--请选择--</option>
						<option value="1">集体全资</option>
						<option value="2">股份合作</option>
						<option value="3">联营</option>
						<option value="4">有限责任公司</option>
						<option value="5">股份有限公司</option>
						<option value="6">私营企业</option>
						<option value="7">其他企业</option>
						<option value="8">合资经营企业（港或澳、台湾）</option>
						<option value="9">合作经营企业（港或澳、台湾）</option>
						<option value="10">港澳台商独资经营企业</option>
						<option value="11">港澳台商投资股份有限公司</option>
						<option value="12">中外合资经营企业</option>
						<option value="13">中外合作经营企业</option>
						<option value="14">外资（独资）企业</option>
						<option value="15">国外投资股份有限（公司）</option>
						<option value="16">个体工商户</option>
						<option value="17">个人合伙</option>
						<option value="18">有限责任公司（自然人控股）</option>
						<option value="19">有限公司（法人独资）内资</option>
						<option value="20">有限公司（法人独资）私营</option>
						<option value="21">有限公司（自然人独资）</option>
						<option value="22">有限责任公司（台港澳合资）</option>
						<option value="23">有限责任公司（台港澳法人独资）</option>
						<option value="24">有限公司，有限责任公司（台港澳与境内合资）</option>
						<option value="25">有限公司（自然人控股）</option>
						<option value="26">有限责任公司（中外合资）</option>
						<option value="27">有限责任公司（自然人独资）</option>
						<option value="28">有限责任公司（法人独资）</option>
						<option value="29">有限责任公司分公司</option>
						<option value="30">有限公司（法人独资）</option>
						<option value="31">有限责任公司（台港澳与外国投资者合资）</option>
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
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="edit" href="<%=path %>/approvalFlowAction.do?method=doDealOrder&applyOrderId={sid}" target="navTab" rel="doDeal"><span>工单处理</span></a></li>			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="50" align="center">序号</th>
				<th width="150" align="center">企业名称</th>
				<th width="50" align="center">企业类型</th>
				<th width="80" align="center">申请事项</th>
				<th width="80" align="center">申请等级</th>	
				<th width="80" align="center">申报日期</th>
				<th width="80" align="center">联系人</th>		
				<th width="80" align="center">资质到期时间</th>
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
				<td>${list.aptitude_end_date}</td>
				<td>${list.task_instance_name}</td>
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
<script type="text/javascript">
    jQuery(document).ready(function(){
		jQuery("input[name='companyName']").val('<%=GlobalFunc.initStr(afForm.getCompanyName(),"")%>');
		jQuery("#companyType").val('<%=GlobalFunc.initStr(afForm.getCompanyType(),"-99")%>');

    })


</script>
