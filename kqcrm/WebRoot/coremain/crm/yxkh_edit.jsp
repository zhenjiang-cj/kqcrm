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
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	CrmForm userform = (CrmForm) request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
	SessionData sessionData = (SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
	List<CrmInfo> userlist =  (List<CrmInfo>) request.getAttribute("userlist");
	CrmInfo user = new CrmInfo();
	if(userlist!=null)
	{
		user = userlist.get(0);
	}

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
 
	<script src="<%=path%>/dwz/js/jquery.validate.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(function(){
		jQuery("#is_install").val('<%=user.getIs_install()  %>');
		
	});




	</script>
	

  </head>
  
  <body>
    <div class="pageContent">
  <form class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone)" action="<%=path%>/crmAction.do?method=doYxkhEdit" method="post" name="userForm">
    <input type="hidden" name="kh_id" id="kh_id" value="<%=user.getKh_id() %>">
    <input type="hidden" name="operatorId" id="operatorId" value="<%=sessionData.getSno() %>">   

    <div class="pageFormContent nowrap" layoutH="56" style="height:100px">
    <table class="TabList">                   	                  
	                  <tr style="margin-top:20px;">
	                      <td class="tit">用户名称：</td>
	                      <td style="text-align:left;">
	                         <%=user.getKh_name() %>
	                      </td>
	                      <td class="tit03">省份：</td>
	                      <td style="text-align:left;">
	                          <%=user.getProvinces() %>
	                      </td>
	                  </tr>
	                  <tr style="margin-top:20px;">
	                      <td class="tit">地市：</td>
	                      <td style="text-align:left;">
	                          <%=user.getCity() %>
	                      </td>
	                      <td class="tit">县区：</td>
	                      <td style="text-align:left;">
	                          <%=user.getRegion() %>
	                      </td>
	                  </tr>
	                  <tr style="margin-top:20px;">
	                      <td class="tit">手机号码1：</td>
	                      <td style="text-align:left;">
	                          <input name="kh_phone1" id="kh_phone1" type="text"  maxlength="11"  class="required digits"  value="<%=user.getKh_phone1() %>" />
	                      </td>
	                      <td class="tit">手机号码2：</td>
	                      <td style="text-align:left;">
	                          <input name="kh_phone2" id="kh_phone2" type="text"  maxlength="11"  class="digits"  value="<%=user.getKh_phone2()==null?"":user.getKh_phone2() %>" />
	                      </td>
	                  </tr>
	                  <tr style="margin-top:20px;">
	                      <td class="tit">身份证号：</td>
	                      <td style="text-align:left;">
	                          <input name="kh_card" id="kh_card" type="text"  maxlength="18"  class="alphanumeric" value="<%=user.getKh_card() %>" />
	                      </td>
	                      <td class="tit">转介绍人(姓名+手机号码)：</td>
	                      <td style="text-align:left;">
	                          <input name="introduce_name" id="introduce_name" type="text"  maxlength="20"   value="<%=user.getIntroduce_name()==null?"":user.getIntroduce_name() %>"   />
	                      </td>
	                  </tr>
	                  <tr style="margin-top:20px;">
	                      <td class="tit">渠道来源：</td>
	                      <td style="text-align:left;">
	                          <input name="channel_source" id="channel_source" type="text"  maxlength="80" value="<%=user.getChannel_source()==null?"":user.getChannel_source() %>"/>
	                      </td>
	                      <td class="tit">是否安装：</td>
	                      <td style="text-align:left;">
												<select  name="is_install" id="is_install" class="required"  >
																<option value="0">未安装</option>
																<option value="1">已安装</option>
															</select>
	                      </td>
	                  </tr>
					  <tr style="margin-top:20px;">
	                      <td class="tit">家庭住址：</td>
	                      <td style="text-align:left;" colspan="3">
	                          <input name="kh_addr" id ="kh_addr" type="text"  maxlength="100"  class="required" size="60" value="<%=user.getKh_addr() %>"/>
	                      </td>
	      
	                  </tr>

	                  <tr style="margin-top:20px;">
	                      <td class="tit">备注：</td>
	                      <td colspan=3 style="text-align:left;">
	                          <textarea name="remark" cols="80" rows="2" class="required textInput" style="margin: 0px; height: 150px; width: 100%;"><%=user.getRemark()==null?"":user.getRemark() %></textarea>
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
