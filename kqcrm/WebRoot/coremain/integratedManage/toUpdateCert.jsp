<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ page import="com.nl.util.GlobalConst"%>
<%@ page import="com.nl.integratedManage.actionForm.ApprovalFlowForm"%>
<%@ page import="com.nl.base.utils.GlobalFunc"%>
<%
String path = request.getContextPath();

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=path%>">
    <link type="text/css" rel= "stylesheet" href="<%=path%>/css/content.css" >
    <title>������ҵ֤��������Ϣ</title>
  </head>
  
<body>

<div class="pageContent">
	<form method="post" action="<%=path%>/approvalFlowAction.do?method=updateCertInfo" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
	<input type="hidden" name="applyOrderId"  value="${form.applyOrderId}">
	<input type="hidden" name="fileType"  value="${form.fileType}">
	<input type="hidden" name="companyName"  value="${form.companyName}">
	
	<input type="hidden" name="regisAddr"  value="${appInfo.regis_addr}">
	<input type="hidden" name="taxRegistNo"  value="${appInfo.tax_regist_no}">
	<input type="hidden" name="zipCode"  value="${appInfo.zip_code}">
	<input type="hidden" name="establishmentDate"  value="${appInfo.establishment_date}">
	<input type="hidden" name="registCapital"  value="${appInfo.regist_capital}">
	<input type="hidden" name="employeeName"  value="${appInfo.employee_name}">
	<input type="hidden" name="mobilePhone"  value="${appInfo.mobile_phone}">
	<input type="hidden" name="companyType"  value="${appInfo.company_type}">
	
	<div class="panel" layoutH="56">		
			<h1>��ҵ֤����Ϣ��д</h1>
			<div>
			<table class="TabList">                   
                  <tr style="margin-top:20px;">
                      <td class="tit">��˾���ƣ�</td>
                      <td style="text-align:left;">
                          ${form.companyName}
                      </td>
                      <td class="tit">����֤��ţ�</td>
                      <td style="text-align:left;">
                          <input name="aptitudeCertNo" type="text" class="required" />
                      </td>
                  </tr>
                  <tr style="margin-top:20px;">
                      <td class="tit">֤����Ч��ʼ��</td>
                      <td style="text-align:left;">
                          <input type="text" name="beginDate" class="date" size="14" class="required"/>
                      </td>
                      <td class="tit">֤����Ч��ֹ��</td>
                      <td style="text-align:left;">
                          <input type="text" name="endDate" class="date" size="14" class="required"/>
                      </td>
                  </tr>
				  <tr style="margin-top:20px;">
                      <td class="tit">�������</td>
                      <td colspan=3 style="text-align:left;">
                          <select id="aptitudeType" name="aptitudeType" >
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

    })


</script>
