<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=path%>">
    <link type="text/css" rel= "stylesheet" href="<%=path%>/css/content.css" >
    <title>工单处理</title>
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
			<h1>企业基本情况</h1>
			<div>
			<table class="TabList">                   
                  <tr style="margin-top:20px;">
                      <td class="tit">企业信息编号:</td>
                      <td style="text-align:left;">
                          ${baseInfo.company_info_id }
                      </td>
                      <td class="tit02">企业名称：</td>
                      <td style="text-align:left;">
                          ${baseInfo.company_name }
                      </td>
                      <td class="tit">公司类型：</td>
                      <td style="text-align:left;">
                          ${baseInfo.company_type }
                      </td>
                  </tr>
                  <tr style="margin-top:20px;">
                      <td class="tit">所属区域：</td>
                      <td style="text-align:left;">
                          ${baseInfo.company_region }
                      </td>
                      <td class="tit">所属单元：</td>
                      <td style="text-align:left;">
                          ${baseInfo.company_county }
                      </td>
                      <td class="tit">法人类型：</td>
                      <td style="text-align:left;">
                          ${baseInfo.legal_person_type }
                      </td>
                  </tr>
                  <tr style="margin-top:20px;">
                      <td class="tit">注册地址：</td>
                      <td style="text-align:left;">
                          ${baseInfo.regis_addr }
                      </td>
                      <td class="tit">成立日期：</td>
                      <td style="text-align:left;">
                          ${baseInfo.establishment_date }
                      </td>
                      <td class="tit">主管部门：</td>
                      <td style="text-align:left;">
                          ${baseInfo.parent_department }
                      </td>
                  </tr>
                  <tr style="margin-top:20px;">
                      <td class="tit">批准日期：</td>
                      <td style="text-align:left;">
                          ${baseInfo.approva_date }
                      </td>
                      <td class="tit">批准单位：</td>
                      <td style="text-align:left;">
                          ${baseInfo.approva_department }
                      </td>
                      <td class="tit">企业资质：</td>
                      <td style="text-align:left;">
                          ${baseInfo.aptitude_file_no }
                      </td>
                  </tr>
                  <tr style="margin-top:20px;">
                      <td class="tit">营业执照注册号：</td>
                      <td style="text-align:left;">
                          ${baseInfo.busi_license_no }
                      </td>
                      <td class="tit">营业执照文件编号：</td>
                      <td style="text-align:left;">
                          ${baseInfo.busi_license_file_no }
                      </td>
                      <td class="tit">税务登记号：</td>
                      <td style="text-align:left;">
                          ${baseInfo.tax_regist_no }
                      </td>
                  </tr>
                  <tr style="margin-top:20px;">
                      <td class="tit">组织机构代码号：</td>
                      <td style="text-align:left;">
                          ${baseInfo.organization_no }
                      </td>
                      <td class="tit">注册资本：</td>
                      <td style="text-align:left;">
                          ${baseInfo.regist_capital }
                      </td>
                      <td class="tit">实收资本：</td>
                      <td style="text-align:left;">
                          ${baseInfo.real_capital }
                      </td>
                  </tr>
                  <tr style="margin-top:20px;">
                      <td class="tit">开户银行：</td>
                      <td style="text-align:left;">
                          ${baseInfo.open_bank }
                      </td>
                      <td class="tit">银行账号：</td>
                      <td style="text-align:left;">
                          ${baseInfo.bank_account }
                      </td>
                      <td class="tit">主营范围：</td>
                      <td style="text-align:left;">
                          ${baseInfo.main_range }
                      </td>
                  </tr>
                  <tr style="margin-top:20px;">
                      <td class="tit">兼营范围：</td>
                      <td style="text-align:left;">
                          ${baseInfo.and_range }
                      </td>
                      <td class="tit">企业章程：</td>
                      <td style="text-align:left;">
                          ${baseInfo.company_articles }
                      </td>
                      <td class="tit">企业简介：</td>
                      <td style="text-align:left;">
                          ${baseInfo.company_introduction }
                      </td>
                  </tr>
                  <tr style="margin-top:20px;">
                      <td class="tit">联系人：</td>
                      <td style="text-align:left;">
                          ${baseInfo.contacts_name }
                      </td>
                      <td class="tit">部门/职务：</td>
                      <td style="text-align:left;">
                          ${baseInfo.dept }
                      </td>
                      <td class="tit">电子邮箱：</td>
                      <td style="text-align:left;">
                          ${baseInfo.email }
                      </td>
                  </tr>
                  <tr style="margin-top:20px;">
                      <td class="tit">企业网址：</td>
                      <td style="text-align:left;">
                          ${baseInfo.company_website }
                      </td>
                      <td class="tit">手机号码：</td>
                      <td style="text-align:left;">
                          ${baseInfo.msisdn }
                      </td>
                      <td class="tit">联系电话：</td>
                      <td style="text-align:left;">
                          ${baseInfo.contacts_no }
                      </td>
                  </tr>
                  <tr style="margin-top:20px;">
                      <td class="tit">传真：</td>
                      <td style="text-align:left;">
                          ${baseInfo.fax }
                      </td>
                      <td class="tit">邮编：</td>
                      <td style="text-align:left;">
                          ${baseInfo.zip_code }
                      </td>
                      <td class="tit">办公地址：</td>
                      <td style="text-align:left;">
                          ${baseInfo.office_addr }
                      </td>
                  </tr>
            </table>
			</div>
			<h1>处理意见填写</h1>
			<div>
			<dl class="nowrap">
				<dd><textarea name="remark" cols="100" rows="4" style="margin: 0px; height: 138px; width: 662px;"></textarea></dd>
			</dl>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="doSubmit()">同意</button></div></div></li>
				<li>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="doReturn()">退回</button></div></div>
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
