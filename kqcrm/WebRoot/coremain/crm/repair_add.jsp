<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.portal.dt.*"%>
<%@ page import="com.nl.portal.actionForm.*"%>
<%@page import="java.util.List"%>
<%@ page import="com.nl.util.GlobalConst"%>
<%@page import="com.nl.util.SessionConst"%>
<%@page import="com.nl.util.SessionData"%>
<%
	String path = request.getContextPath();
	CrmForm userform = (CrmForm) request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
	
	SessionData sessionData = (SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>客户保修记录录入</title>
    
	<link type="text/css" rel= "stylesheet" href="<%=path%>/css/content.css" >
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	$(function(){
		
	});
	</script>
	
  </head>
  
  <body>
    <div class="pageContent">
    <form class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)" action="<%=path%>/crmAction.do?method=doRepairAdd" method="post" name="userForm">
    <input name="operatorId"   type="hidden" value="<%=sessionData.getSno() %>" />
    <input name="provinces" id="provinces"  type="hidden"  />
    <input name="city"  id="city" type="hidden"  />
    <input name="region" id="region"  type="hidden" />
    <div class="pageFormContent nowrap" layoutH="56" style="height:100px">
		<table class="TabList">                   	                  
	                  <tr style="margin-top:20px;">
	                      <td class="tit03">客户编号：</td>
	                      <td style="text-align:left;">
	                         <input name="kh_id"  id="kh_id" type="text"  maxlength="30"  />
	                         <div class="button"><div class="buttonContent"><button type="button" onclick="showInfo()">查询</button></div></div>
	                      </td>
	                      <td class="tit03">客户姓名：</td>
	                      <td style="text-align:left;">
	                          <input name="kh_name"  id="kh_name" type="text" readonly />
	                      </td>
	                  </tr>
	                  <tr style="margin-top:20px;">
	                      <td class="tit">手机号码：</td>
	                      <td style="text-align:left;">
	                          <input name="kh_phone1"  id="kh_phone1" type="text"  readonly />
	                      </td>
	                      
	                      <td class="tit">首签日期：</td>
	                      <td style="text-align:left;">
	                          <input name="ht_date_first" id="ht_date_first" type="text"  readonly/>
	                      </td>
	                  </tr>
	                  
	                  <tr style="margin-top:20px;">
	                      
	                      <td class="tit">客户状态：</td>
	                      <td style="text-align:left;">
	                          <input name="hf_type" id="hf_type" type="text"  readonly  />
	                      </td>
	                      <td class="tit">地址：</td>
	                      <td style="text-align:left;">
	                          <input name="kh_addr"  id="kh_addr" type="text" size="60" readonly />
	                      </td>
	                  </tr>
	                  <tr style="margin-top:20px;">
	                      <td class="tit">报修日期：</td>
	                      <td style="text-align:left;" colspan=3>
	                          <input name="warranty_date" id="warranty_date" type="text"  class="required date" />
	                      </td>
	                  </tr>
	                  <tr>
	                      <td class="tit">报修内容：</td>
	                      <td style="text-align:left;" colspan=3>
	                          <textarea name="warranty_content" cols="80" rows="2" class="required textInput" style="margin: 0px; height: 150px; width: 100%;"></textarea>
	                      </td>
	                  </tr>
	                  <tr style="margin-top:20px;">
	                      <td class="tit">维修人员：</td>
	                      <td style="text-align:left;">
	                          <input name="repair_person" id="repair_person" type="text"  maxlength="80" />
	                      </td>
	                      <td class="tit">维修时间：</td>
	                      <td style="text-align:left;">
							  <input name="repair_date" id="repair_date" type="text"  class="required date" />
	                      </td>
	                  </tr>
	                  <tr style="margin-top:20px;">
	                      <td class="tit">故障原因：</td>
	                      <td colspan=3 style="text-align:left;">
	                          <textarea name="repair_reason" cols="80" rows="2" class="required textInput" style="margin: 0px; height: 150px; width: 100%;"></textarea>
	                      </td>
	                  </tr>
	            </table>
	</div>
	
	<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
    </div>
    
	</form>
	</div>
  </body>
</html>
<script type="text/javascript">

	function showInfo()
	{
	 	jQuery.ajax({
		type: "POST",
		url: '<%=path%>/crmAction.do?method=queryKhById&kh_id='+jQuery("#kh_id").val(),
		data: {},
		dataType: 'json',
		success: function(data){
			//alert(JSON.stringify(data));	
			if(data!=null)
			{
				//alert(data[0].kh_name);
				jQuery("#provinces").val(data[0].provinces);
				jQuery("#city").val(data[0].city);
				jQuery("#region").val(data[0].region);
				jQuery("#kh_name").val(data[0].kh_name);
				jQuery("#kh_phone1").val(data[0].kh_phone1);
				jQuery("#kh_addr").val(data[0].kh_addr);
				jQuery("#ht_date_first").val(data[0].ht_date_first);
				jQuery("#hf_type").val(data[0].hf_type);
			}

			}
		});		
		
		
	} 
	
</script>
