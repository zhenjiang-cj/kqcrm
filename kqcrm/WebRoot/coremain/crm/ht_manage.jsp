<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.portal.dt.*"%>
<%@ page import="com.nl.portal.actionForm.*"%>
<%@page import="java.util.List"%>
<%@ page import="com.nl.util.GlobalConst"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<CrmInfo> userlist = (List<CrmInfo>) request.getAttribute("userlist");//
CrmForm userform = (CrmForm) request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
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
  
  <form id="pagerForm" method="post" action="<%=path%>/crmAction.do?method=toHtManage">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="20" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=path%>/crmAction.do?method=toHtManage" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					客户编号：<%=userform.getKh_id()==null?"":userform.getKh_id() %>
					<input type="hidden" name="ht_id" id="ht_id" value="<%=userform.getKh_id()==null?"":userform.getKh_id() %>" />
				</td>
				<td>
					签约开始日期：<input type="text" name="ht_begin_date" id="ht_begin_date" class="date" value="<%=userform.getHt_begin_date()==null?"":userform.getHt_begin_date() %>"  />
				</td>
				<td>
					签约结束日期：<input type="text" name="ht_end_date" id="ht_end_date" class="date" value="<%=userform.getHt_end_date()==null?"":userform.getHt_end_date() %>" />
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
			<li><a class="add" href="<%=path%>/crmAction.do?method=toHtAdd&kh_id=<%=userform.getKh_id()==null?"":userform.getKh_id() %>" target="navTab" rel="ht_add"  ><span>新增</span></a></li>
			<!-- <li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="demo/common/ajaxDone.html" class="delete"><span>批量删除默认方式</span></a></li> -->
			<!--<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="demo/common/ajaxDone.html" class="delete"><span>批量删除逗号分隔</span></a></li>-->
			<!--<li><a class="edit" href="demo_page4.html?uid={sid_user}" target="navTab" warn="请选择一个用户"><span>修改</span></a></li>-->
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138" style="table-layout:fixed">
		<thead>
			<tr>
				<th width="10%" >客户名称</th>
				<th width="15%" >地址</th>
				<th width="10%" >首次签约日期</th>  
				<th width="10%" >本次签约日期</th>  
				<th width="5%">押金(元)</th>
				<th width="5%">租金(元)</th>
				<th width="10%">产品名</th>
				<th width="10%">签约年度</th>
				<th width="15%">备注</th>
				<th width="10%">操作</th>
			</tr>
		</thead>
		<tbody>
		
			<% if(userlist!=null&&userlist.size()>0){
				for(int i=0;i<userlist.size();i++){
					CrmInfo user = userlist.get(i);
					
					%>
					<tr  >
						<td><%=user.getKh_name() %></td>
						<td  title="<%=user.getKh_addr() %>" style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;"><%=user.getKh_addr() %></td>
						<td><%=user.getHt_date_first() %></td>
						<td><%=user.getHt_date_current() %></td>
						<td><%=user.getHt_pledge() %></td>
						<td><%=user.getHt_rent() %></td>
						<td><%=user.getProd_name() %></td>
						<td><%=user.getHt_year() %></td>
						<td  title="<%=user.getRemark() %>" style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;"><%=user.getRemark() %></td>
						<td>
							<a title="删除" target="ajaxTodo" href="<%=path%>/crmAction.do?method=doHtDel&ht_id=<%=user.getHt_id() %>" class="btnDel">删除</a>
							<a title="编辑" target="navTab" href="<%=path%>/crmAction.do?method=toHtEdit&ht_id=<%=user.getHt_id() %>" rel="ht_add"   class="btnEdit">编辑</a>
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
				<span>20条，共${pager.totalCount}条</span>
			</div>
			
			<div class="pagination" targetType="navTab" totalCount="${pager.totalCount}" numPerPage="${pager.numPerPage}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
		
		</div>
  
</div>
  </body>
 
</html>
