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
    
    <title>�����</title>

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
					��ҵ���ƣ�<input type="text" name="companyName" size="30" />
				</td>
				<td>
					��ҵ���ͣ�
					<select name="companyType">
						<option value="-99">--��ѡ��--</option>
						<option value="1">����ȫ��</option>
						<option value="2">�ɷݺ���</option>
						<option value="3">��Ӫ</option>
						<option value="4">�������ι�˾</option>
						<option value="5">�ɷ����޹�˾</option>
						<option value="6">˽Ӫ��ҵ</option>
						<option value="7">������ҵ</option>
						<option value="8">���ʾ�Ӫ��ҵ���ۻ�ġ�̨�壩</option>
						<option value="9">������Ӫ��ҵ���ۻ�ġ�̨�壩</option>
						<option value="10">�۰�̨�̶��ʾ�Ӫ��ҵ</option>
						<option value="11">�۰�̨��Ͷ�ʹɷ����޹�˾</option>
						<option value="12">������ʾ�Ӫ��ҵ</option>
						<option value="13">���������Ӫ��ҵ</option>
						<option value="14">���ʣ����ʣ���ҵ</option>
						<option value="15">����Ͷ�ʹɷ����ޣ���˾��</option>
						<option value="16">���幤�̻�</option>
						<option value="17">���˺ϻ�</option>
						<option value="18">�������ι�˾����Ȼ�˿عɣ�</option>
						<option value="19">���޹�˾�����˶��ʣ�����</option>
						<option value="20">���޹�˾�����˶��ʣ�˽Ӫ</option>
						<option value="21">���޹�˾����Ȼ�˶��ʣ�</option>
						<option value="22">�������ι�˾��̨�۰ĺ��ʣ�</option>
						<option value="23">�������ι�˾��̨�۰ķ��˶��ʣ�</option>
						<option value="24">���޹�˾���������ι�˾��̨�۰��뾳�ں��ʣ�</option>
						<option value="25">���޹�˾����Ȼ�˿عɣ�</option>
						<option value="26">�������ι�˾��������ʣ�</option>
						<option value="27">�������ι�˾����Ȼ�˶��ʣ�</option>
						<option value="28">�������ι�˾�����˶��ʣ�</option>
						<option value="29">�������ι�˾�ֹ�˾</option>
						<option value="30">���޹�˾�����˶��ʣ�</option>
						<option value="31">�������ι�˾��̨�۰������Ͷ���ߺ��ʣ�</option>
					</select>
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">����</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="edit" href="<%=path %>/approvalFlowAction.do?method=doDealOrder&applyOrderId={sid}" target="navTab" rel="doDeal"><span>��������</span></a></li>			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="50" align="center">���</th>
				<th width="150" align="center">��ҵ����</th>
				<th width="50" align="center">��ҵ����</th>
				<th width="80" align="center">��������</th>
				<th width="80" align="center">����ȼ�</th>	
				<th width="80" align="center">�걨����</th>
				<th width="80" align="center">��ϵ��</th>		
				<th width="80" align="center">���ʵ���ʱ��</th>
				<th width="80" align="center">����״̬</th>					
			</tr>
		</thead>
		<tbody>
			<%if(afForm.getTotalCount()>0){ %>
			<c:forEach items="${orderList}" var="list" varStatus="index">
			<tr target="sid" rel="${list.apply_order_id}">
			    <td>${index.index+1}</td>
			    <td><a href="<%=path %>/companyAction.do?method=toViewFrame&companyId=${list.company_info_id}" target="dialog" rel="baseinfo" title="��ҵ��ϸ��Ϣ" max="true">${list.company_name}</a></td>
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
		          <td align="center" colspan="7">�����ݣ�</td>
		      </tr>
			<%} %>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>��ʾ</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>
			<span>������${pager.totalCount}��</span>
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
