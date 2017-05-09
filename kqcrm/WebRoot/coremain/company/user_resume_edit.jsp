<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.company.dt.*"%>
<%@ page import="com.nl.company.actionForm.*"%>
<%@page import="java.util.List"%>
<%@ page import="com.nl.util.GlobalConst"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<UserResume> resumeList = (List<UserResume>) request.getAttribute("resumeList");//人员列表
CompanyForm comForm = (CompanyForm) request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
UserResume resume = resumeList.get(0);

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'user_resume.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="<%=path%>/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="<%=path%>/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="<%=path%>/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
	<link href="<%=path%>/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>

  </head>
  
  <body>
  <div class="pageContent">
  <form class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDoneFather)" action="<%=path%>/companyAction.do?method=doUserResumeEdit" method="post" name="userForm">
    <input type="hidden" name="company_info_id" id="company_info_id" value="<%=comForm.getCompany_info_id() %>">
    <input type="hidden" name="employee_id" id="employee_id" value="<%=comForm.getEmployee_id() %>">
    <input type="hidden" name="resume_srl" id="resume_srl" value="<%=resume.getResume_srl() %>">
    <div class="formBar">
			<ul>
			 <%
				if("".equals(comForm.getApply_order_id())){
					%>
								
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
					<%
				}
				%>
			</ul>
    </div>
    
    <div class="pageFormContent nowrap" layoutH="56" style="height:100px">
    <h1>个人简历</h1>
    	<p>
			<label>开始时间：</label>
			<input type="text" name="start_date" id="start_date" class="required date" readonly="true" value="<%=resume.getStart_date() %>"   />
			<a class="inputDateButton" href="javascript:;">选择</a>
		</p>
		<p>
			<label>任结束时间：</label>
			<input type="text" name="resume_end_date" id="resume_end_date" class="required date" readonly="true"  value="<%=resume.getResume_end_date() %>"  />
			<a class="inputDateButton" href="javascript:;">选择</a>
		</p>
		<p>
			<label>工作单位：</label>
			<input type="text" name="work_company" id="work_company" maxlength="128"  value="<%=resume.getWork_company() %>" />
		</p>
		<p>
			<label>职位：</label>
			<input type="text" name="work_station" id="work_station" maxlength="10"  value="<%=resume.getWork_station() %>" />
		</p>
		<p>
			<label>从事工作：</label>
			<input type="text" name="work_name" id="work_name" maxlength="128"  value="<%=resume.getWork_name() %>" />
		</p>
		 
	</div>
	</form>
	</div>
  </body>
</html>
