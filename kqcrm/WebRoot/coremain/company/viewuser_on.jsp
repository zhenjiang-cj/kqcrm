<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@ page import="com.nl.company.actionForm.*"%>
<%@page import="com.nl.company.dt.*"%>
<%@page import="java.util.List"%>
<%@ page import="com.nl.util.GlobalConst"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	List<UserInfo> useronlist = (List<UserInfo>) request.getAttribute("useronlist");//人员列表
	List<UserInfo> userfblist = (List<UserInfo>) request.getAttribute("userfblist");//分布情况
	CompanyForm comForm = (CompanyForm) request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
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
		jQuery(document).ready(function(){
			var iContentH=$(window).height()-$("#header").height()-110;
			//$("#div1").height(iContentH-100);
			//$("#navTab0").height(iContentH-100);
			$("#div_fb").height((iContentH-100)/3);
			$("#div_on").height((iContentH-100)/2);
			$("#div_table").height((iContentH-100)/3);
			$("#navTab0").height($("#div_fb").height()+$("#div_table").height()+100);
			$("#div1").height($("#div_fb").height()+$("#div_table").height()+100);
		});
	</script>
  </head>
  
  <body>
  <form id="pagerForm" method="post" action="<%=path%>/companyAction.do?method=viewUseron">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="20" />
  </form>
    <div id="div1" style="width:100%; float:left;margin:0px;">
				

					<div id="navTab0" class="panel" style="width:99%;">
						<h2>
							企业在册有职称专业人员分布情况
						</h2>
						<div  id="div_fb">
								<table class="table"  Style="height:300;width:99%;">
									<thead>
										<tr>
											<th width="10%">
												专业/职称等级
											</th>
											<th width="10%">
												高级
											</th>
											<th width="15%">
												中级
											</th>
											<th width="15%">
												初级
											</th>
											<th width="20%">
												合计
											</th>
										</tr>
									</thead>
									<tbody>
										<%
											if (userfblist != null && userfblist.size() > 0) {
												for (int i = 0;i<userfblist.size(); i++) {
													UserInfo userfb = userfblist.get(i);
													
										%>
										<tr target="sid_user" rel="1">
											<td>
												<%=DictMgmt.getValueDescs(DictMgmt.DICT_COMPANY_USER_SPECIALTY,userfb.getSpecialty(),"" )%> 
											</td>
											<td>
												<%=userfb.getCnt_high() %>
											</td>
											<td>
												<%=userfb.getCnt_mid() %>
											</td>
											<td>
												<%=userfb.getCnt_low() %>
											</td>
											<td>
												<%=userfb.getCnt_all() %>
											</td>
										</tr>
										<%
											}
											}
										%>
	
									</tbody>
								</table>
						</div>
	
						<div   id="div_on" >
							<h1>
								企业在册有职称专业人员名册
							</h1>
							 
							<div id="div_table" class="">
								<table class="table" width="100%" >
									<thead>
										<tr>
											<th width="15%">
												姓名
											</th>
											<th width="5%">
												性别
											</th>
											<th width="15%">
												职称/专业
											</th>
											<th width="10%">
												从业年限
											</th>
											<th width="15%">
												岗位
											</th>
											<th width="15%">
												职务
											</th>
											<th width="10%">
												证书
											</th>
											<th width="15%">
												操作
											</th>
										</tr>
									</thead>
									<tbody>
										<%
											if (useronlist != null && useronlist.size() > 0) {
												for (int i = 0;i<useronlist.size(); i++) {
													UserInfo user = useronlist.get(i);
										%>
										<tr target="sid_user" rel="1">
											<td>
												<%=user.getEmployee_name() %>
											</td>
											<td>
												<%=DictMgmt.getValueDescs(DictMgmt.DICT_COMPANY_USER_SEX,user.getSex(),"" )%> 
											</td>
											<td>
												<%=DictMgmt.getValueDescs(DictMgmt.DICT_COMPANY_USER_SPECIALTY,user.getSpecialty(),"" )%> 
											</td>
											<td>
												<%=user.getWork_years() %>
											</td>
											<td>
												<%=DictMgmt.getValueDescs(DictMgmt.DICT_COMPANY_USER_STATION,user.getStation(),"" )%> 
											</td>
											<td>
												<%=DictMgmt.getValueDescs(DictMgmt.DICT_COMPANY_USER_POST,user.getPost(),"" )%> 
											</td>
											<td>
												--
											</td>
											<td>
											<div class="panelBar">
												<ul class="toolBar">
													<li><a class="view" href="<%=path%>/companyAction.do?method=toUserView&employee_id=<%=user.getEmployee_id() %>" target="navTab"><span>详细</span></a></li>
													<li class="line">line</li>
												</ul>
											</div>
											</td>
										</tr>
										<%
											}
											}
										%>
	
									</tbody>
								</table>
								</div>
						<div class="panelBar">
							<div class="pages">
								<span>显示</span>
								<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
									<option value="20">20</option>
									<option value="50">50</option>
									<option value="100">100</option>
									<option value="200">200</option>
								</select>
								<span>条，共${pager.totalCount}条</span>
							</div>
							
							<div class="pagination" targetType="navTab" totalCount="${pager.totalCount}" numPerPage="${pager.numPerPage}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
	
	 					</div>
								<!-- 人员汇总报表end -->
	
							</div>
						</div>

					</div>
			
	
    
	
  </body>
</html>
