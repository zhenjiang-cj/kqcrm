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
  
  <form id="pagerForm" method="post" action="<%=path%>/companyAction.do?method=queryUserResume">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="20" />
</form>


<div class="pageContent">
	<table class="table"  style="width:100%;height:300px;table-layout:fixed;" >
		<thead>
			<tr>
				<th width="20%">时间</th>
				<th width="20%">工作单位</th>
				<th width="10%">职位</th>
				<th width="20%">从事工作</th>
				<th width="20%">操作</th>
			</tr>
		</thead>
		<tbody>
		<% if(resumeList!=null&&resumeList.size()>0){
			for(int i =0; i<resumeList.size();i++){
				UserResume  resume =  resumeList.get(i);
			%>
			<tr target="sid_user" rel="1">
				<td >
					<%=resume.getStart_date() %>至<%=resume.getResume_end_date() %>
				</td>
				<td title="<%= resume.getWork_company()%>" style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">
					<%=resume.getWork_company() %>
				</td>
				<td title="<%= resume.getWork_station()%>" style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">
					<%=resume.getWork_station() %>
				</td>
				<td title="<%= resume.getWork_name()%>" style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">
					<%=resume.getWork_name() %>
				</td>
				<td   class="panelBar">
					<ul class="toolBar">
						<li><a class="add" href="<%=path%>/companyAction.do?method=toResumeEdit&resume_srl=<%=resume.getResume_srl() %>" 
						width="645" height="470" target="dialog" rel="jbsxBox" ><span>修改</span></a></li>
						<li><a class="delete" href="<%=path%>/companyAction.do?method=delResume&employee_id=<%=resume.getEmployee_id() %>&resume_srl=<%=resume.getResume_srl() %>" target="ajaxTodo" rel="jbsxBox" callback="dialogAjaxDoneFather" title="确定要删除吗?"><span>删除</span></a></li>
					</ul>
				</td>
			</tr> 
			
			<%
			}
		}
			
		 %>
			
		</tbody>
	</table>
	 
</div>
  
  
  </body>
</html>
