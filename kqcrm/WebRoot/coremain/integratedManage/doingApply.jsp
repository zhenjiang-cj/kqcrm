<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=path%>">
    <link type="text/css" rel= "stylesheet" href="<%=path%>/css/content.css" >
    <title>�����걨������</title>
  </head>
  <style>
	.div_container{width:0;height:0;position:fixed;left:50%;rigth:50%;top:50%;bottom:50%;}
	.div_position{width:600px;height:220px;margin-left:-300px;margin-top:-110px}
  </style>
<body>

<div class="pageFormContent" layoutH="56">
	<form id="dealForm" name="dealForm" method="post" action="<%=path%>/approvalFlowAction.do?method=dealOrder" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	<input type="hidden" name="taskId"  value="${companyApply.task_id}">
	<input type="hidden" name="taskInstanceId"  value="${companyApply.task_instance_id}">
	<input type="hidden" name="flowInstanceId"  value="${companyApply.flow_instance_id}">
	<input type="hidden" name="companyInfoId"  value="${companyApply.company_info_id}">
	<input type="hidden" name="applyOrderId"  value="${companyApply.apply_order_id}">
	<input type="hidden" id="actionType" name="actionType" >
	
	<div class="div_container">
	<div class="div_position">
	<table class="TabList"> 
		<tr style="margin-top:20px;">
            <td colspan=4 style="text-align:center;font-size:30px;font-weight:bold;">���ĵ�ǰ�������ڴ����У�</td>
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
                ${companyApply.contacts_name}
            </td>
            <td class="tit">��ϵ�绰��</td>
            <td style="text-align:left;">
                ${companyApply.contacts_mobile_phone}
            </td>
        </tr> 
        <tr style="margin-top:20px;">
            <td class="tit">�걨���</td>
            <td style="text-align:left;">
                ${companyApply.apply_type}
            </td>
            <td class="tit">�걨�ȼ���</td>
            <td style="text-align:left;">
                ${companyApply.apply_grade}
            </td>
        </tr> 
        <tr style="margin-top:20px;">
            <td class="tit">��ҵ�걨������</td>
            <td colspan=3 style="text-align:left;">
                ${companyApply.company_declare}
            </td>
        </tr>
        <tr style="margin-top:20px;">
            <td class="tit">��ǰ���״̬��</td>
            <td style="text-align:left;">
                ${companyApply.task_instance_name}
            </td>
            <td class="tit">�걨��ɨ�����</td>
            <td style="text-align:left;">
                <!-- <a href="demo_page2.html" target="navTab" rel="lookPic">�鿴ɨ���(${companyApply.cnt_files})</a> -->
                <a class="button" href="<%=path %>/coremain/company/show_img.jsp?type=12&companyid=${companyApply.company_info_id}" 
				   max="true" target="dialog" rel="queryAppPic" title="�鿴ɨ���" width="645" height="370">
				<span id="span_img_1">�鿴ɨ���(${companyApply.cnt_files})</span></a>
            </td>
        </tr> 
    </table>
    </div>
    </div>
    
    <div class="formBar">
		<ul>
			<li><div id="btnClass" class="buttonActive"><div class="buttonContent"><button id="cancel" type="button" onclick="doCancel();">����</button></div></div></li>
		</ul>
	</div>
	</form>
</div>
  </body>
</html>
<script type="text/javascript">
    jQuery(document).ready(function(){
    	if(${companyApply.task_id}>1011)
    	{
    		$("#btnClass").removeClass("buttonActive");
    		$("#btnClass").addClass("buttonDisabled");
    	}		
    })
    
    function doCancel()
    {
	    $("#actionType").val("4");
	    $("#dealForm").submit();
    } 
    
     
</script>
