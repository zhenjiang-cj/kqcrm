<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="../../WEB-INF/tlds/c-rt.tld" prefix="c" %>
<%@ taglib uri="../../WEB-INF/tlds/fn.tld" prefix="fn" %>
<%@ page import="com.nl.util.GlobalConst"%>
<%@ page import="com.nl.base.utils.GlobalFunc"%>
<%@ page import="com.nl.integratedManage.actionForm.ApprovalFlowForm"%>
<%@page import="com.nl.util.SessionData"%>
<%@page import="com.nl.util.SessionConst"%> 
<%
	String path = request.getContextPath();
	ApprovalFlowForm afForm = (ApprovalFlowForm)request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
	SessionData sessdata = (SessionData) request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
	String auId = sessdata.getAdmUser().getAuID();

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=path%>">
    
    <title>֤���ѯ</title>

  </head>
  
  <body>
    <form id="pagerForm" method="post" action="<%=path%>/approvalFlowAction.do?method=queryAptitudeInfo">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="20" />
  </form>


<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="<%=path%>/approvalFlowAction.do?method=queryAptitudeInfo" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					��ҵ���ƣ�<input type="text" name="companyName" size="30" />
				</td>
				<td>
					֤����룺<input type="text" name="aptitudeCertNo" size="30" />
				</td>
			</tr>
			<tr>
				<td>
					�������
					<select id="aptitudeType" name="aptitudeType">
						<option value="-99">--��ѡ��--</option>
						<option value="1">ע���ʲ�����ʦ</option>
						<option value="2">ע��˰��ʦ</option>
						<option value="3">ע����۹���ʦ</option>
						<option value="4">ע�������ʦ</option>
						<option value="5">ע����й滮ʦ</option>
						<option value="6">ע�᷿�ز�����ʦ</option>
						<option value="7">ע�Ὠ��ʦ</option>
						<option value="8">ע��ṹ����ʦ</option>
						<option value="9">ע����ʦ</option>
						<option value="10">��ҵע�����ʦ</option>
					</select>
				</td>
				<td>
					���ʵȼ���
					<select id="aptitudeGrade" name="aptitudeGrade">
						<option value="-99">--��ѡ��--</option>
						<option value="1">�ݶ�����</option>
						<option value="2">����</option>
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
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="20" align="center">���</th>
				<th width="100" align="center">��ҵ����</th>
				<th width="50" align="center">��ҵ����</th>
				<th width="50" align="center">����</th>
				<th width="50" align="center">֤����Ч����</th>
				<th width="50" align="center">֤����Ч��ֹ</th>			
				<th width="150" align="center">����</th>			
			</tr>
		</thead>
		<tbody>
			<%if(afForm.getTotalCount()>0){ %>
			<c:forEach items="${appList}" var="list" varStatus="index">
			<tr target="sid" rel="${list.apply_order_id}">
			    <td>${index.index+1}</td>
			    <td>${list.company_name}</td>
				<td>${list.company_type}</td>
				<td>${list.apply_type}</td>
				<td>${list.aptitude_start_date}</td>
				<td>${list.aptitude_end_date}</td>
				<td>
				<c:if test="${list.aptitude_cert_no == null}">  
					<a href="<%=path %>/approvalFlowAction.do?method=toUpdateCert&applyOrderId=${list.apply_order_id}&companyName=${list.company_name}&fileType=15" target="dialog" rel="toUpdateCert" width="650" height="230">¼��֤����Ϣ</a>
				</c:if>
				<c:if test="${list.aptitude_cert_no != null}">  
					<a href="<%=path %>/approvalFlowAction.do?method=downloadCertFile&applyOrderId=${list.apply_order_id}&fileType=15&fileOrder=1&builtWay=2"><span>֤����(ϵͳ)</span></a>
					<a href="<%=path %>/approvalFlowAction.do?method=downloadCertFile&applyOrderId=${list.apply_order_id}&fileType=15&fileOrder=2&builtWay=2"><span>֤�鸱��(ϵͳ)</span></a>
					<a href="<%=path %>/approvalFlowAction.do?method=downloadCertFile&applyOrderId=${list.apply_order_id}&fileType=15&fileOrder=3&builtWay=2"><span>֤������(ϵͳ)</span></a>
					
					<c:if test="${list.cnt_files > 0}"> 
					<c:forEach  varStatus="i" begin="1" end="${list.cnt_files}" step="1"> 					
						<a href="<%=path %>/approvalFlowAction.do?method=downloadCertFile&applyOrderId=${list.apply_order_id}&fileType=15&builtWay=1&fileOrder=${i.count}"><span>֤���ļ�${i.count}(�ֹ�)</span></a>				
					</c:forEach>
					</c:if>
					
					<a href="<%=path%>/coremain/integratedManage/toUploadFile.jsp?companyInfoId=${list.company_info_id}&applyOrderId=${list.apply_order_id}&auId=<%=auId %>&type=15" target="dialog" rel="uploadFile" width="645" height="370">
                      	<span>�ϴ�����֤��</span>
                    </a>
				</c:if>
					
				</td>
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
		jQuery("#aptitudeType").val('<%=GlobalFunc.initStr(afForm.getAptitudeType(),"-99")%>');
		jQuery("#aptitudeGrade").val('<%=GlobalFunc.initStr(afForm.getAptitudeGrade(),"-99")%>');

    })


</script>
