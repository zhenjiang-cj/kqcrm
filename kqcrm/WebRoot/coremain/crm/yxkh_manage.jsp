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
    
    <title>意向客户维护</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page"> 
	<link href="<%=path%>/css/content.css" rel="stylesheet" type="text/css"  />

  </head>
  
  <body>
  
  <form id="pagerForm" method="post" action="<%=path%>/crmAction.do?method=toKhManage">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="20" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=path%>/crmAction.do?method=toYxkhManage" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					客户名称：<input type="text" name="kh_name" id="kh_name" value="<%=userform.getKh_name()==null?"":userform.getKh_name() %>" />
				</td>
				<td>
					渠道来源：<input type="text" name="channel_source" id="channel_source" value="<%=userform.getChannel_source()==null?"":userform.getChannel_source() %>" />
				</td>
				<td>
					是否已安装： 
					<select name="is_install" id="is_install">
					<option value="-1">--请选择--</option>
					<option value="1" <%if("1".equals(userform.getIs_install())){%>selected<%} %>  >已安装</option>
					<option value="0" <%if("0".equals(userform.getIs_install())){%>selected<%} %> >未安装</option>
					</select>
				</td>
				<td>
					家庭地址：<input type="text" name="kh_addr" id="kh_addr" value="<%=userform.getKh_addr()==null?"":userform.getKh_addr() %>"  />
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
			<!-- <li><a class="add" href="<%=path%>/crmAction.do?method=toKhAdd" target="dialog" rel="kh_add" width="800" height="400"><span>新增</span></a></li>
			<li class="line">line</li> -->
			<%
			if(GlobalUtil.functionCheck(privlist,GlobalConst.FUNCTION_CRM_YXUSER_EXP)){
				%>
			<li><a class="icon" href="<%=path%>/crmAction.do?method=toYxkhExp" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			<%
				}
				%>
		</ul>
	</div>
	<table class="table" width="1200" layoutH="138"  style="table-layout:fixed">
		<thead>
			<tr>
				<th width="120"  >编号</th>
				<th width="120" >名称</th>
				<th >地址</th>
				<th width="100"  >号码1</th>  
				<th width="100"  >号码2</th>  
				<th width="100"  >转介绍人</th> 
				<th width="100"  >是否已安装</th> 
				<th width="100"  >渠道来源</th> 
				<th width="100"  >备注</th>  
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
		
			<% if(userlist!=null&&userlist.size()>0){
				for(int i=0;i<userlist.size();i++){
					CrmInfo user = userlist.get(i);
					
					%>
					<tr target="sid_user" rel="1">
						<td ><%=user.getKh_id() %></td>
						<td><%=user.getKh_name() %></td>
						<td title="<%=user.getKh_addr() %>" style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;"><%=user.getKh_addr() %></td>
						<td><%=user.getKh_phone1() %></td>
						<td><%=user.getKh_phone2() %></td>
						<td><%=user.getIntroduce_name() %></td>
						<td><%="1".equals(user.getIs_install())?"已安装":"未安装" %></td>
						<td><%=user.getChannel_source() %></td>
						<td title="<%=user.getRemark() %>" style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;"><%=user.getRemark() %></td>
						<td>
							<%
							if(GlobalUtil.functionCheck(privlist,GlobalConst.FUNCTION_CRM_YXUSER_DEL)){
								%>								
							<a title="删除" target="ajaxTodo" href="<%=path%>/crmAction.do?method=doYxkhDel&kh_id=<%=user.getKh_id() %>" class="btnDel">删除</a>
							<%} %>
							<%if(GlobalUtil.functionCheck(privlist,GlobalConst.FUNCTION_CRM_YXUSER_EDIT)&&!"1".equals(user.getIs_install())){ %>
							<a title="编辑" target="dialog" href="<%=path%>/crmAction.do?method=toYxkhEdit&kh_id=<%=user.getKh_id() %>" width="800" height="500"  class="btnEdit">编辑</a>
							<%} %>
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
  
  
  </body>
</html>
