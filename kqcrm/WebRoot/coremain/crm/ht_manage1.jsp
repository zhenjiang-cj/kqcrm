<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.portal.dt.*"%>
<%@ page import="com.nl.portal.actionForm.*"%>
<%@page import="java.util.List"%>
<%@ page import="com.nl.util.GlobalConst"%>
<%@page import="com.nl.util.SessionData"%>
<%@page import="com.nl.util.SessionConst"%>
<%@page import="com.nl.util.GlobalUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<CrmInfo> userlist = (List<CrmInfo>) request.getAttribute("userlist");//
CrmForm userform = (CrmForm) request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
SessionData sessdata = (SessionData) request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
List privlist = null;
if(sessdata!=null){
	privlist = sessdata.getPrivMap();
}
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
  
  <form id="pagerForm" method="post" action="<%=path%>/crmAction.do?method=toHtManage1">
	<input type="hidden" name="pageNum" value="${pager.pageNum}" />
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
</form>


<div class="pageHeader">
	<form rel="pagerForm"  onsubmit="return navTabSearch(this);" action="<%=path%>/crmAction.do?method=toHtManage1" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					客户名称： <input type="text" name="kh_name" id="kh_name"  value="<%=userform.getKh_name()==null?"":userform.getKh_name() %>" />
				</td>
				<td>
					合同类别：
					 <select name="ht_type" id="ht_type">
					<option value="">--请选择--</option>
					<option value="1" <%if("1".equals(userform.getHt_type())){%>selected<%} %>  >新增</option>
					<option value="2" <%if("2".equals(userform.getHt_type())){%>selected<%} %> >续约</option>
					</select>
				</td>
				<td>
					签约开始日期：<input type="text" name="ht_begin_date" id="ht_begin_date" class="date" value="<%=userform.getHt_begin_date()==null?"":userform.getHt_begin_date() %>" />
				</td>
				<td>
					签约结束日期：<input type="text" name="ht_end_date" id="ht_end_date" class="date"  value="<%=userform.getHt_end_date()==null?"":userform.getHt_end_date() %>"/>
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
			<%
			if(GlobalUtil.functionCheck(privlist,GlobalConst.FUNCTION_HT_USER_ADD)){
				%>
				<li><a class="add" href="<%=path%>/crmAction.do?method=toHtAdd2" target="navTab" rel="ht_add" ><span>新增</span></a></li>
				<%
			}
			%>
			<li class="line">line</li>
			<%
			if(GlobalUtil.functionCheck(privlist,GlobalConst.FUNCTION_HT_USER_EXP)){
				%>
				<li><a class="icon" href="<%=path%>/crmAction.do?method=toHtExp" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
				<%
			}
			%>
			
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
				<th width="5%">合同类别</th>
				<th width="10%">备注</th>
				<th width="10%">操作</th>
			</tr>
		</thead>
		<tbody>
		
			<% if(userlist!=null&&userlist.size()>0){
				for(int i=0;i<userlist.size();i++){
					CrmInfo user = userlist.get(i);
					
					%>
					<tr target="sid_user" rel="1">
						<td><%=user.getKh_name() %></td>
						<td  title="<%=user.getKh_addr() %>" style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;"><%=user.getKh_addr() %></td>
						<td><%=user.getHt_date_first() %></td>
						<td><%=user.getHt_date_current() %></td>
						<td><%=user.getHt_pledge() %></td>
						<td><%=user.getHt_rent() %></td>
						<td><%=user.getProd_name() %></td>
						<td><%=user.getHt_year() %></td>
						<td><%=user.getHt_type().equals("1")?"新增":"续约" %></td>
						<td  title="<%=user.getRemark() %>" style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;"><%=user.getRemark() %></td>
						<td>
							<%
							if(GlobalUtil.functionCheck(privlist,GlobalConst.FUNCTION_HT_USER_DEL)){
								%>
								<a title="删除" target="ajaxTodo" href="<%=path%>/crmAction.do?method=doHtDel1&ht_id=<%=user.getHt_id() %>" class="btnDel">删除</a>
								<%
							}
							%>
							<%
							if(GlobalUtil.functionCheck(privlist,GlobalConst.FUNCTION_HT_USER_EDIT)){
								%>
								<a title="编辑" target="navTab" href="<%=path%>/crmAction.do?method=toHtEdit1&ht_id=<%=user.getHt_id() %>" width="645" height="400"   class="btnEdit">编辑</a>
								<%
							}
							%>
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
