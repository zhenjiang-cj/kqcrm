<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.company.dt.*"%>
<%@page import="java.util.List"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	List<UserInfo> userList = (List<UserInfo>) request.getAttribute("userlist");//人员列表
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
	<link href="<%=path%>/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="<%=path%>/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="<%=path%>/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
	<link href="<%=path%>/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
	
	
	
	<script type="text/javascript">
		function doreflash(){
			var sx = document.getElementById("reflash");
			sx.click();
		}
	
	</script>

  </head>
  
  <body>
    <form onsubmit="return navTabSearch(this,navTabAjaxDone);" action="demo_page1.html" method="post">
    <div id="div1" style="width:99%; float:left;margin:0px;">
			<div class="pageContent sortDrag" selector="h1" layoutH="42">

				<div id="navTab0" class="panel">
					<div class="navTab-panel tabsPageContent layoutBox">
						<div class="page unitBox">
							<table width="100%">
								<tbody>
								
									<tr>
										<td style="text-align: right; font-size: 18px; width: 25%; padding: 5px 5px;">
											<font color="red">*</font>总资产:
										</td>
										<td style="text-align: left; font-size: 18px; width: 75%; padding: 5px 5px;" colspan=3>
											<input type="text" name="company_name" id="company_name" class="input" />元
										</td>
									</tr>
									<tr>
										<td style="text-align: right; font-size: 18px; width: 25%; padding: 5px 5px;">
											净资产:
										</td>
										<td style="text-align: left; font-size: 18px; width: 75%; padding: 5px 5px;" colspan=3>
											<input type="text" name="company_name" id="company_name" class="input" />元
										</td>
									</tr>
									<tr>
										<td style="text-align: right; font-size: 18px; width: 25%; padding: 5px 5px;">
											其中货币资金:
										</td>
										<td style="text-align: left; font-size: 18px; width: 75%; padding: 5px 5px;" colspan=3>
											<input type="text" name="company_name" id="company_name" class="input" />%
										</td>
									</tr>
									<tr>
										<td style="text-align: right; font-size: 18px; width: 25%; padding: 5px 5px;">
											验资机构:
										</td>
										<td style="text-align: left; font-size: 18px; width: 75%; padding: 5px 5px;" colspan=3>
											<input type="text" name="company_name" id="company_name" class="input" />%
										</td>
									</tr>
									<tr>
										<td style="text-align: right; font-size: 18px; width: 25%; padding: 5px 5px;">
											<font color="red">*</font>验资报告日期:
										</td>
										<td style="text-align: left; font-size: 18px; width: 75%; padding: 5px 5px;" colspan=3>
											<input type="text" name="company_name" id="company_name" class="input" />%
										</td>
									</tr>
									
								</tbody>
							</table>

						</div>
						<div class="formBar">
							<ul>
								<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
								<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
								<li>
									<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
								</li>
							</ul>
						</div>
					</div>
				</div>
				
				 
			  </div>
					
	</div>
	
	</form>
  </body>
</html>
