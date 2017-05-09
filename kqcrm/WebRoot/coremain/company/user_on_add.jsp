<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.company.dt.*"%>
<%@page import="java.util.List"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	List<UserInfo> userList = (List<UserInfo>) request
			.getAttribute("userlist");//人员列表
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

  </head>
  
  <body>
    <form onsubmit="return navTabSearch(this);" action="demo_page1.html" method="post">
    <div id="div1" style="width:99%; float:left;margin:0px;">
				<div class="pageContent sortDrag" selector="h1" layoutH="42">

					<div id="navTab0" class="panel">
						<div class="navTab-panel tabsPageContent layoutBox">
							<div class="page unitBox">
								<table width="100%">
									<tr>
										<td style="text-align: right; font-size: 24px;">
											<input type="button" name="save" class="but_s" value="保存"
												onclick="doSave();" />
											&nbsp; 
										</td>
									</tr>
								</table>

								<table width="100%">
									<thead>
										<tr>
											<th
												style="text-align: center; font-size: 30px; font-weight: bold;"
												colspan="2">
												专业人员
											</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td
												style="text-align: right; font-size: 18px; width: 25%; padding: 5px 5px;">
												<font color="red">*</font>姓名:
											</td>
											<td
												style="text-align: left; font-size: 18px; width: 75%; padding: 5px 5px;"
												colspan=3>
												<input type="text" name="company_name" id="company_name"
													class="input" />
											</td>
										</tr>
										<tr>
											<td
												style="text-align: right; font-size: 18px; width: 25%; padding: 5px 5px;">
												<font color="red">*</font>性别:
											</td>
											<td
												style="text-align: left; font-size: 18px; width: 75%; padding: 5px 5px;"
												colspan=3>
												<select class="combox" name="company_type" id="company_type">
													<%=DictMgmt.getSelectObj(DictMgmt.DICT_COMPANY_REGION, "",
							false, false, "-1", -1, null, null, null, -1, "")%>
												</select>
											</td>
										</tr>
										<tr>
											<td
												style="text-align: right; font-size: 18px; width: 25%; padding: 5px 5px;">
												<font color="red">*</font>职称/专业:
											</td>
											<td
												style="text-align: left; font-size: 18px; width: 75%; padding: 5px 5px;"
												colspan=3>
												<input type="text" name="company_name" id="company_name"
													class="input" />
											</td>
										</tr>
										<tr>
											<td
												style="text-align: right; font-size: 18px; width: 25%; padding: 5px 5px;">
												<font color="red">*</font>从业年限:
											</td>
											<td
												style="text-align: left; font-size: 18px; width: 75%; padding: 5px 5px;"
												colspan=3>
												<input type="text" name="company_name" id="company_name"
													class="input" />
											</td>
										</tr>
										<tr>
											<td
												style="text-align: right; font-size: 18px; width: 25%; padding: 5px 5px;">
												<font color="red">*</font>岗位:
											</td>
											<td
												style="text-align: left; font-size: 18px; width: 75%; padding: 5px 5px;"
												colspan=3>
												<input type="text" name="company_name" id="company_name"
													class="input" />
											</td>
										</tr> 
										<tr>
											<td
												style="text-align: right; font-size: 18px; width: 25%; padding: 5px 5px;">
												<font color="red">*</font>职务:
											</td>
											<td
												style="text-align: left; font-size: 18px; width: 75%; padding: 5px 5px;"
												colspan=3>
												<input type="text" name="company_name" id="company_name"
													class="input" />
											</td>
										</tr>
										<tr>
											<td style="text-align:right;font-size:18px;width:25%;padding:5px 5px;">
												证书图片:
											</td>
											<td style="text-align:left;font-size:18px;width:75%;padding:5px 5px;">
												<a class="button" href="<%=path %>/coremain/company/upload_img.jsp?type=5" target="dialog" rel="dlg_page1" 
												 title="上传证书图片" width="645" height="370">
												<span id="span_img_5">上传证书图片</span></a>
											</td>
										</tr>
									</tbody>
								</table>

							</div>

						</div>
					</div>



				</div>
	</div>
	
	</form>
  </body>
</html>
