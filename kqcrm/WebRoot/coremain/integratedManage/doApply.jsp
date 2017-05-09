<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.nl.util.SessionData"%>
<%@page import="com.nl.util.SessionConst"%>  
<%
String path = request.getContextPath();
SessionData sessdata = (SessionData) request.getSession().getAttribute(SessionConst.COMPANY_LOGIN_SESSION);
String auId = sessdata.getCompanyUser().getCompany_user_id();

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=path%>">
    <link type="text/css" rel= "stylesheet" href="<%=path%>/css/content.css" >
    <title>�����걨</title>
  </head>
  
<body>

<div class="pageFormContent">
	<form method="post" action="<%=path%>/approvalFlowAction.do?method=applyOrder" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	<input type="hidden" name="companyInfoId"  value="${companyApply.company_info_id}">
	<input type="hidden" name="companyName"  value="${companyApply.company_name}">
	<input type="hidden" name="companyType"  value="${companyApply.company_type}">
	<input type="hidden" name="companyCounty"  value="${companyApply.company_county}">
	<input type="hidden" name="companyRegion"  value="${companyApply.company_region}">
	<input type="hidden" name="organizationNo"  value="${companyApply.organization_no}">
	<input type="hidden" name="legalPersonId"  value="${companyApply.employee_id}">
	<input type="hidden" name="legalPersonName"  value="${companyApply.employee_name}">
	<input type="hidden" name="mobilePhone"  value="${companyApply.mobile_phone}">
	<input type="hidden" name="infoStatus"  value="${companyApply.info_status}">
	<input type="hidden" name="flowInstanceId"  value="${companyApply.flow_instance_id}">
	<input type="hidden" name="taskInstanceId"  value="${companyApply.task_instance_id}">
	<input type="hidden" name="taskId"  value="${companyApply.task_id}">
	<input type="hidden" name="applyOrderId"  value="${companyApply.apply_order_id}">
	
	<div class="panel" layoutH="56">		
			<h1>�����걨��д</h1>
			<div>
			<table class="TabList">                   
                  <tr style="margin-top:20px;">
                      <td class="tit">��������:</td>
                      <td colspan=3 style="text-align:left;">
                           <select id="applyType" name="applyType">
								<option value="1">��������</option>
								<option value="2">�˶��ȼ�</option>
								<option value="3">��������</option>
								<option value="4">���ʱ��</option>
							</select>
                      </td>
                  </tr>
                  <tr style="margin-top:20px;">
                      <td class="tit">�걨��</td>
                      <td colspan=3 style="text-align:left;">
                          	<a class="button" href="<%=path%>/coremain/integratedManage/toUploadFile.jsp?companyInfoId=${companyApply.company_info_id}&applyOrderId=${companyApply.apply_order_id}&auId=<%=auId %>&type=12" target="dialog" rel="uploadFile" width="645" height="370">
                          	<span id="span_upload">�ϴ��걨��ɨ���</span>
                          	</a>  
                          	
                      </td>
                  </tr>
                  <tr style="margin-top:20px;">
                      <td class="tit">��˾���ƣ�</td>
                      <td style="text-align:left;">
                          ${companyApply.company_name}
                      </td>
                      <td class="tit">��˾���ͣ�</td>
                      <td style="text-align:left;">
                          ${companyApply.company_type_name}
                      </td>
                  </tr>
                  <tr style="margin-top:20px;">
                      <td class="tit">���������ˣ�</td>
                      <td style="text-align:left;">
                          ${companyApply.employee_name}
                      </td>
                      <td class="tit">�ƶ��绰��</td>
                      <td style="text-align:left;">
                          ${companyApply.mobile_phone}
                      </td>
                  </tr>
				  <tr style="margin-top:20px;">
                      <td class="tit">��ҵ��ϵ�ˣ�</td>
                      <td style="text-align:left;">
                          <input name="contactsName" type="text" value="${companyApply.contacts_name}" class="required"/>
                      </td>
                      <td class="tit">��ϵ�绰��</td>
                      <td style="text-align:left;">
                          <input name="contactsMobilePhone" type="text" value="${companyApply.contacts_mobile_phone}" maxlength="11" class="required"/>
                      </td>
                  </tr>
                  <tr style="margin-top:20px;">
                      <td class="tit">��ҵ�걨������</td>
                      <td colspan=3 style="text-align:left;">
                          <textarea name="companyDeclare" cols="80" rows="2" class="required textInput" style="margin: 0px; height: 400px; width: 620px;">${companyApply.company_declare}</textarea>
                      </td>
                  </tr>
            </table>
			</div>
		</div>

		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">�ύ</button></div></div></li>

			</ul>
		</div>
	</form>
</div>
  </body>
</html>
<script type="text/javascript">
    jQuery(document).ready(function(){
		jQuery("#applyType").val('${companyApply.apply_type}');
    })


</script>
