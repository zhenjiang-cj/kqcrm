<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.company.dt.*"%>
<%@page import="java.util.List"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	List<UserInfo> userdeptlist = (List<UserInfo>) request.getAttribute("userdeptlist");//人员列表
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
			$("#div1").height(iContentH);
			$("#navTab2").height(iContentH-80);
			$("#navTab0").height(iContentH-80);
		});
		
	 
	 
	</script>
  </head>
  
  <body>
    <form onsubmit="return navTabSearch(this);" action="demo_page1.html" method="post">
    <div id="div1" style="width:99%; float:left;margin:0px;">
				<div class="pageContent sortDrag" selector="h1"  >

					<div id="navTab2" class="">
						<h1>
							部门负责人
						</h1>
						<div id="navTab0" class="navTab-panel tabsPageContent layoutBox">
							<table class="table" width="100%">
							
								<thead>
									<tr>
										<th width="10%">
											姓名
										</th>
										<th width="5%">
											性别
										</th>
										<th width="15%">
											出生日期
										</th>
										<th width="15%">
											职称/专业
										</th>
										<th width="10%">
											从业年限
										</th>
										<th width="20%">
											人员类别
										</th>
										<th width="10%">
											职务
										</th>
										<th width="10%">
											操作
										</th>
									</tr>
								</thead>
								<tbody>
									<%
										if (userdeptlist != null && userdeptlist.size() > 0) {
											for (int i = 0;i<userdeptlist.size(); i++) {
												UserInfo  user = userdeptlist.get(i);
									%>
									<tr target="sid_user" rel="1">
										<td>
											<%=user.getEmployee_name() %>
										</td>
										<td>
											<%=DictMgmt.getValueDescs(DictMgmt.DICT_COMPANY_USER_SEX,user.getSex(),"" )%>
										</td>
										<td>
											<%=user.getBoth_no() %>
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
											<ul class="toolBar">
												<li><a class="view" href="<%=path%>/companyAction.do?method=toUserView&employee_id=<%=user.getEmployee_id() %>" target="navTab"><span>详细</span></a></li>
											</ul>
										</td>
									</tr>
									<%
										}
										}
									%>

								</tbody>
							</table>



							<!-- 人员汇总报表end -->

						</div>
					</div>


				</div>
	</div>
	
	</form>
  </body>
</html>
