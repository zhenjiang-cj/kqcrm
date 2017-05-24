<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.portal.dt.*"%>
<%@ page import="com.nl.portal.actionForm.*"%>
<%@page import="java.util.List"%>
<%@ page import="com.nl.util.GlobalConst"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<UserInfo> userlist = (List<UserInfo>) request.getAttribute("userlist");//
UserForm userform = (UserForm) request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
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

  </head>
  
  <body>
  
  <form id="pagerForm" method="post" action="<%=path%>/userAction.do?method=toUserManage">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="20" />
	<input type="hidden" name="user_name"  />
</form>


<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="<%=path%>/userAction.do?method=toUserManage" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					姓名：<input type="text" name="user_name" id="user_name"  />
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="<%=path%>/userAction.do?method=toUserAdd" target="dialog" rel="user_add" width="700" height="600"><span>新增</span></a></li>
			
			<!-- <li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="demo/common/ajaxDone.html" class="delete"><span>批量删除默认方式</span></a></li> -->
			<!--<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="demo/common/ajaxDone.html" class="delete"><span>批量删除逗号分隔</span></a></li>-->
			<!--<li><a class="edit" href="demo_page4.html?uid={sid_user}" target="navTab" warn="请选择一个用户"><span>修改</span></a></li>-->
			<li class="line">line</li>
			<!--<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>-->
		</ul>
	</div>
	<table class="table" width="1200" layoutH="138">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="120" orderField="accountNo" class="asc">用户号</th>
				<th orderField="accountName">用户名称</th>
				<th width="80" orderField="accountType">用户号码</th>  
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
		
			<% if(userlist!=null&&userlist.size()>0){
				for(int i=0;i<userlist.size();i++){
					UserInfo user = userlist.get(i);
					
					%>
					<tr target="sid_user" rel="1">
						<td><input name="ids" value="xxx" type="checkbox"></td>
						<td><%=user.getUser_id() %></td>
						<td><%=user.getUser_name() %></td>
						<td><%=user.getMsisdn() %></td>
						<td>
							<a title="删除" target="ajaxTodo" href="<%=path%>/userAction.do?method=doUserDel&sno=<%=user.getSno() %>" class="btnDel">删除</a>
							<a title="编辑"   class="btnEdit"  href="<%=path%>/userAction.do?method=toUserEdit&sno=<%=user.getSno() %>" width="700" height="600" target="dialog" rel="user_edit" ><span>编辑</span></a>
							<a title="角色" target="navTab" href="<%=path%>/userAction.do?method=toUserRole&sno=<%=user.getSno() %>" width="645" height="600" class="btnAssign">角色</a>
						</td>
					</tr>
					
					<%
				}
			}
			%>
			
			
		</tbody>
	</table>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span>
				<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
					<option value="20">20</option>
					<option value="50">50</option>
				</select>
				<span>条，共${pager.totalCount}条</span>
			</div>
			
			<div class="pagination" targetType="navTab" totalCount="${pager.totalCount}" numPerPage="${pager.numPerPage}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
		
		</div>
  </div>
  
  </body>
</html>
