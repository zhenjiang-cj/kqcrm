<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=path%>">
    <link type="text/css" rel= "stylesheet" href="<%=path%>/css/content.css" >
    <title>��������</title>
  </head>
  
<body>
    
<div class="pageContent">
	<form id="dealForm" name="dealForm" method="post" action="<%=path%>/approvalFlowAction.do?method=dealOrder" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	<input type="hidden" name="taskId"  value="${orderInfo.task_id}">
	<input type="hidden" name="taskInstanceId"  value="${orderInfo.task_instance_id}">
	<input type="hidden" name="flowInstanceId"  value="${orderInfo.flow_instance_id}">
	<input type="hidden" name="companyInfoId"  value="${baseInfo.company_info_id}">
	<input type="hidden" name="applyOrderId"  value="${baseInfo.apply_order_id}">
	<input type="hidden" name="companyCounty"  value="${baseInfo.company_county}">	
	<input type="hidden" name="companyCreateId"  value="${baseInfo.create_id}">	
	<input type="hidden" name="aptitudeGrade"  value="${orderInfo.apply_grade}">
	<input type="hidden" name="applyType"  value="${orderInfo.apply_type}">
	
	<input type="hidden" id="actionType" name="actionType" >
		<div class="panel" layoutH="86">		
			<h1>��ҵ�������</h1>
			<div>
			<table class="TabList">                   
                  <tr style="margin-top:20px;">
                      <td class="tit">��ҵ��Ϣ���:</td>
                      <td style="text-align:left;">
                          ${baseInfo.company_info_id }
                      </td>
                      <td class="tit02">��ҵ���ƣ�</td>
                      <td style="text-align:left;">
                          ${baseInfo.company_name }
                      </td>
                      <td class="tit">��˾���ͣ�</td>
                      <td style="text-align:left;">
                          ${baseInfo.company_type }
                      </td>
                  </tr>
                  <tr style="margin-top:20px;">
                      <td class="tit">��������</td>
                      <td style="text-align:left;">
                          ${baseInfo.company_region }
                      </td>
                      <td class="tit">������Ԫ��</td>
                      <td style="text-align:left;">
                          ${baseInfo.company_county }
                      </td>
                      <td class="tit">�������ͣ�</td>
                      <td style="text-align:left;">
                          ${baseInfo.legal_person_type }
                      </td>
                  </tr>
                  <tr style="margin-top:20px;">
                      <td class="tit">ע���ַ��</td>
                      <td style="text-align:left;">
                          ${baseInfo.regis_addr }
                      </td>
                      <td class="tit">�������ڣ�</td>
                      <td style="text-align:left;">
                          ${baseInfo.establishment_date }
                      </td>
                      <td class="tit">���ܲ��ţ�</td>
                      <td style="text-align:left;">
                          ${baseInfo.parent_department }
                      </td>
                  </tr>
                  <tr style="margin-top:20px;">
                      <td class="tit">��׼���ڣ�</td>
                      <td style="text-align:left;">
                          ${baseInfo.approva_date }
                      </td>
                      <td class="tit">��׼��λ��</td>
                      <td style="text-align:left;">
                          ${baseInfo.approva_department }
                      </td>
                      <td class="tit">��ҵ���ʣ�</td>
                      <td style="text-align:left;">
                          ${baseInfo.aptitude_file_no }
                      </td>
                  </tr>
                  <tr style="margin-top:20px;">
                      <td class="tit">Ӫҵִ��ע��ţ�</td>
                      <td style="text-align:left;">
                          ${baseInfo.busi_license_no }
                      </td>
                      <td class="tit">Ӫҵִ���ļ���ţ�</td>
                      <td style="text-align:left;">
                          ${baseInfo.busi_license_file_no }
                      </td>
                      <td class="tit">˰��ǼǺţ�</td>
                      <td style="text-align:left;">
                          ${baseInfo.tax_regist_no }
                      </td>
                  </tr>
                  <tr style="margin-top:20px;">
                      <td class="tit">��֯��������ţ�</td>
                      <td style="text-align:left;">
                          ${baseInfo.organization_no }
                      </td>
                      <td class="tit">ע���ʱ���</td>
                      <td style="text-align:left;">
                          ${baseInfo.regist_capital }
                      </td>
                      <td class="tit">ʵ���ʱ���</td>
                      <td style="text-align:left;">
                          ${baseInfo.real_capital }
                      </td>
                  </tr>
                  <tr style="margin-top:20px;">
                      <td class="tit">�������У�</td>
                      <td style="text-align:left;">
                          ${baseInfo.open_bank }
                      </td>
                      <td class="tit">�����˺ţ�</td>
                      <td style="text-align:left;">
                          ${baseInfo.bank_account }
                      </td>
                      <td class="tit">��Ӫ��Χ��</td>
                      <td style="text-align:left;">
                          ${baseInfo.main_range }
                      </td>
                  </tr>
                  <tr style="margin-top:20px;">
                      <td class="tit">��Ӫ��Χ��</td>
                      <td style="text-align:left;">
                          ${baseInfo.and_range }
                      </td>
                      <td class="tit">��ҵ�³̣�</td>
                      <td style="text-align:left;">
                          ${baseInfo.company_articles }
                      </td>
                      <td class="tit">��ҵ��飺</td>
                      <td style="text-align:left;">
                          ${baseInfo.company_introduction }
                      </td>
                  </tr>
                  <tr style="margin-top:20px;">
                      <td class="tit">��ϵ�ˣ�</td>
                      <td style="text-align:left;">
                          ${baseInfo.contacts_name }
                      </td>
                      <td class="tit">����/ְ��</td>
                      <td style="text-align:left;">
                          ${baseInfo.dept }
                      </td>
                      <td class="tit">�������䣺</td>
                      <td style="text-align:left;">
                          ${baseInfo.email }
                      </td>
                  </tr>
                  <tr style="margin-top:20px;">
                      <td class="tit">��ҵ��ַ��</td>
                      <td style="text-align:left;">
                          ${baseInfo.company_website }
                      </td>
                      <td class="tit">�ֻ����룺</td>
                      <td style="text-align:left;">
                          ${baseInfo.msisdn }
                      </td>
                      <td class="tit">��ϵ�绰��</td>
                      <td style="text-align:left;">
                          ${baseInfo.contacts_no }
                      </td>
                  </tr>
                  <tr style="margin-top:20px;">
                      <td class="tit">���棺</td>
                      <td style="text-align:left;">
                          ${baseInfo.fax }
                      </td>
                      <td class="tit">�ʱࣺ</td>
                      <td style="text-align:left;">
                          ${baseInfo.zip_code }
                      </td>
                      <td class="tit">�칫��ַ��</td>
                      <td style="text-align:left;">
                          ${baseInfo.office_addr }
                      </td>
                  </tr>
            </table>
			</div>
			<h1>���������д</h1>
			<div>
			<dl class="nowrap">
				<dd><textarea name="remark" cols="100" rows="4" style="margin: 0px; height: 138px; width: 662px;"></textarea></dd>
			</dl>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>����</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="doSubmit()">ͬ��</button></div></div></li>
				<li>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="doReturn()">�˻�</button></div></div>
				</li>
			</ul>
		</div>
	</form>

</div>

  </body>
</html>
<Script Language="JavaScript">
    function doSubmit()
    {
	    $("#actionType").val("2");
	    $("#dealForm").submit();
    } 

    function doReturn()
    {
	    $("#actionType").val("3");
	    $("#dealForm").submit();
    }
    
     
</Script>
