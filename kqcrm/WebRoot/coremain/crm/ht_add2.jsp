<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.portal.dt.*"%>
<%@ page import="com.nl.portal.actionForm.*"%>
<%@page import="java.util.List"%>
<%@ page import="com.nl.util.GlobalConst"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	CrmForm userform = (CrmForm) request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
 
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'user_legal.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="<%=path%>/css/content.css" rel="stylesheet" type="text/css"  />
	<style type="">
	
	</style>
  
	<script type="text/javascript">
 


	</script>
	

  </head>
  
  <body>
  <div class="tabsPage"  >
		<ul class="tabsPageHeader"><li><span>合同新增</span></li></ul>
	</div>
	<div class="pageFormContent">
  <form class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone)" action="<%=path%>/crmAction.do?method=doHtAdd2" method="post" name="userForm">
    <input type="hidden" name="operatorId" id="operatorId" value="<%=userform.getOperatorId() %>">
    
    
    <div class="pageFormContent nowrap"   style="height:350px">
		
		<table class="TabList">               
		 		<tr style="margin-top:20px;">
                   <td class="tit03">客户编号：</td>
                   <td style="text-align:left;">
                      <input name="kh_id"  id="kh_id" type="text"  maxlength="16"   class="required digits" />
                   </td>
                   <td class="tit03"></td>
                   <td style="text-align:left;">
                   </td>
               </tr>        	                  
               <tr style="margin-top:20px;">
                   <td class="tit03">合同编号：</td>
                   <td style="text-align:left;">
                      <input name="ht_code"  id="ht_code" type="text"  maxlength="16" />
                   </td>
                   <td class="tit03">首次签约日期：</td>
                   <td style="text-align:left;">
                       <input name="ht_date_first"  id="ht_date_first" type="text"   class="required date"  />
                   </td>
               </tr>
               <tr style="margin-top:20px;">
                   <td class="tit">本次签约日期：</td>
                   <td style="text-align:left;">
                       <input name="ht_date_current" id ="ht_date_current" type="text"   class="required date" />
                   </td>
                   <td class="tit">签约年度：</td>
                   <td style="text-align:left;">
                       <input name="ht_year" id="ht_year" type="text"  maxlength="4"    class="required digits"   />
                   </td>
               </tr>
               <tr style="margin-top:20px;">
                   <td class="tit">押金：</td>
                   <td style="text-align:left;">
                       <input name="ht_pledge" id="ht_pledge" type="text"  maxlength="9"  class="required digits"  />
                   </td>
                   <td class="tit">租金：</td>
                   <td style="text-align:left;">
                       <input name="ht_rent" id="ht_rent" type="text"  maxlength="9"  class="required digits"  />
                   </td>
               </tr>
               <tr style="margin-top:20px;">
                   <td class="tit">产品名称：</td>
                   <td style="text-align:left;">
                       <input name="prod_name" id="prod_name" type="text"  maxlength="18"  class="required"  />
                   </td>
                   <td class="tit">产品代码：</td>
                   <td style="text-align:left;">
                       <input name="prod_code" id="prod_code" type="text"  maxlength="11"     />
                   </td>
               </tr>
               <tr style="margin-top:20px;">
                   <td class="tit">备注：</td>
                   <td style="text-align:left;" colspan="3">
                       <textarea   name="remark" id="remark" rows="5" cols="100"  ></textarea>
                   </td>
   
               </tr>
         </table>
		
	</div>
	
    <div class="formBar" >
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
    </div>
	</form>
	</div>
  </body>
</html>
