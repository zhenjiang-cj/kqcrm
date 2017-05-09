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
    
    <title>企业资质过期查询</title>

  </head>
  
  <body>
    <form id="pagerForm" method="post" action="<%=path%>/approvalFlowAction.do?method=queryExpireCert">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="20" />
  </form>


<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="<%=path%>/approvalFlowAction.do?method=queryExpireCert" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td colspan=2>
					企业名称：<input type="text" name="companyName" size="30" />
				</td>
			</tr>
			<tr>
				<td>
					资质类型：
					<select id="aptitudeType" name="aptitudeType">
						<option value="-99">--请选择--</option>
						<option value="1">注册资产评估师</option>
						<option value="2">注册税务师</option>
						<option value="3">注册造价工程师</option>
						<option value="4">注册监理工程师</option>
						<option value="5">注册城市规划师</option>
						<option value="6">注册房地产估计师</option>
						<option value="7">注册建筑师</option>
						<option value="8">注册结构工程师</option>
						<option value="9">注册会计师</option>
						<option value="10">物业注册管理师</option>
					</select>
				</td>
				<td>
					资质等级：
					<select id="aptitudeGrade" name="aptitudeGrade">
						<option value="-99">--请选择--</option>
						<option value="1">暂定三级</option>
						<option value="2">三级</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan=2>
					过期日期在：
					<input type="text" name="beginDate" class="date" size="14" />
					~
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
				<th width="20" align="center">序号</th>
				<th width="100" align="center">企业名称</th>
				<th width="80" align="center">资质类型</th>
				<th width="80" align="center">资质等级</th>	
				<th width="50" align="center">联系电话</th>		
				<th width="80" align="center">过期日期</th>
				<th width="40" align="center">剩余天数</th>			
			</tr>
		</thead>
		<tbody>
			<%if(afForm.getTotalCount()>0){ %>
			<c:forEach items="${certList}" var="list" varStatus="index">
			<tr>
				<td>${index.index+1}</td>
			    <td><a href="<%=path %>/companyAction.do?method=toViewFrame&companyId=${list.company_info_id}" target="navTab" rel="baseinfo" title="企业详细信息" max="true" >${list.company_name}</a></td>
				<td>${list.aptitude_type_name}</td>
				<td>${list.aptitude_grade_name}</td>
				<td>${list.contacts_no}</td>
				<td>${list.aptitude_end_date}</td>
				<td>${list.leave_days}</td>
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
		jQuery("#aptitudeGrade").val('<%=GlobalFunc.initStr(afForm.getApplyGrade(),"-99")%>');
		jQuery("#aptitudeType").val('<%=GlobalFunc.initStr(afForm.getActionType(),"-99")%>');
		jQuery("input[name='beginDate']").val('<%=GlobalFunc.initStr(afForm.getBeginDate(),"")%>');
		jQuery("input[name='endDate']").val('<%=GlobalFunc.initStr(afForm.getEndDate(),"")%>');
    })


</script>
