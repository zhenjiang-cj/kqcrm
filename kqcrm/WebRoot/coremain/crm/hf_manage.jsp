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
  
  <form id="pagerForm" method="post" action="<%=path%>/crmAction.do?method=toHfManage">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="20" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=path%>/crmAction.do?method=toHfManage" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					客户名称： <input type="text" name="kh_name" id="kh_name"  />
				</td>
				<td>
					回访状态： 
					<select name="hf_status" id="hf_status">
					<option value="">--请选择--</option>
					<option value="1">已回访</option>
					<option value="0">未回访</option>
					</select>
				</td>
				<td>
					工作统计开始日期：<input type="text" name="hf_begin_date" id="hf_begin_date" class="date"  />
				</td>
				<td>
					工作统计结束日期：<input type="text" name="hf_end_date" id="hf_end_date" class="date"  />
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
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="1200" layoutH="138"  style="table-layout:fixed">
		<thead>
			<tr>
				<th width="120" orderField="accountNo" class="asc">客户编码</th>
				<th width="120" orderField="accountName">客户姓名</th>
				<th width="100" orderField="accountType">签约年度</th>  
				<th width="120" orderField="accountName">身份证号</th>
				<th width="100" >电话号码</th>
				<th orderField="accountName">地址</th>  
				<th width="100">回访状态</th>
				<th width="100">应访日期</th>
				<th width="100">实际日期</th>
				<th width="100">访问情况</th>
				<th width="100">回访人</th>
				<th width="50">操作</th>
			</tr>
		</thead>
		<tbody>
		
			<% if(userlist!=null&&userlist.size()>0){
				for(int i=0;i<userlist.size();i++){
					CrmInfo user = userlist.get(i);
					
					%>
					<tr target="sid_user" rel="1">
						<td><%=user.getKh_id() %></td>
						<td><%=user.getKh_name() %></td>
						<td><%=user.getHt_year() %></td>
						<td><%=user.getKh_card() %></td>
						<td><%=user.getKh_phone1() %></td>
						<td  title="<%=user.getKh_addr() %>" style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;"><%=user.getKh_addr() %></td>
						<td><%=user.getHf_status().equals("1")?"已回访":"未回访" %></td>
						<td><%=user.getHf_date_must() %></td>
						<td><%=user.getHf_date_fact() %></td>
						<td  title="<%=user.getHf_remark() %>" style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;"><%=user.getHf_remark() %></td>
						<td><%=user.getHf_user_name() %></td>
						<td>
							<a title="回访" target="dialog" href="<%=path%>/crmAction.do?method=toHfEdit&ht_id=<%=user.getHt_id() %>" width="645" height="400"   class="btnEdit">回访</a>
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
