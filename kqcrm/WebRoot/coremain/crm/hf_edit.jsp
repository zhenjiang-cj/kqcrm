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
	List<CrmInfo> hflist =  (List<CrmInfo>) request.getAttribute("hflist");
	CrmInfo hf = hflist.get(0);
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
  
	<script type="text/javascript">
 


	</script>
	

  </head>
  
  <body>
  
   
  
  
    <div class="pageContent">
  <form class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)" action="<%=path%>/crmAction.do?method=doHfEdit" method="post" name="userForm">
    <input type="hidden" name="hf_id" id="hf_id" value="<%=hf.getHf_id() %>"> 
    <input type="hidden" name="operatorId" id="operatorId" value="<%=userform.getOperatorId() %>">
    
    <div class="pageFormContent nowrap" layoutH="56" style="height:100px">
    	<table class="TabList">                   	                  
               <tr style="margin-top:20px;">
                   <td class="tit">应回访日期：</td>
                   <td style="text-align:left;">
                      <%=hf.getHf_date_must()==null?"":hf.getHf_date_must() %>
                   </td>
                   <td class="tit">实际回访日期：</td>
                   <td style="text-align:left;">
                       <input name="hf_date_fact"  id="hf_date_fact" type="text"   class="required date" value="<%=hf.getHf_date_fact()==null?"":hf.getHf_date_fact() %>" />
                   </td>
               </tr>
	  		  <tr style="margin-top:20px;">
                   <td class="tit">回访人：</td>
                   <td style="text-align:left;" colspan="3">
                       <input name="hf_user_name" id="hf_user_name" type="text"  class="required"   value="<%=hf.getHf_user_name()==null?"":hf.getHf_user_name().trim() %>" />
                   </td>
               </tr>
               <tr style="margin-top:20px;">
   				   <td class="tit">耗材选择：</td>
                   <td style="text-align:left;" colspan="3">
                       <input type="checkbox" name="hf_material" value="1" <%if("1".equals(hf.getMaterial1())){%>checked="checked"<%} %>/>滤芯1
                       <input type="checkbox" name="hf_material" value="2" <%if("1".equals(hf.getMaterial2())){%>checked="checked"<%} %>/>滤芯2
                       <input type="checkbox" name="hf_material" value="3" <%if("1".equals(hf.getMaterial3())){%>checked="checked"<%} %>/>滤芯3
                       <input type="checkbox" name="hf_material" value="4" <%if("1".equals(hf.getMaterial4())){%>checked="checked"<%} %>/>滤芯4
                   </td>
               </tr>
               <tr style="margin-top:20px;">
                   <td class="tit">回访情况：</td>
                   <td style="text-align:left;" colspan="3">
                       <textarea   name="hf_remark" id="hf_remark" rows="10" cols="70"  ><%=hf.getHf_remark()==null?"":hf.getHf_remark().trim() %></textarea>
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
