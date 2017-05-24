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
	<link href="<%=path%>/css/content.css" rel="stylesheet" type="text/css"  />

  </head>
  
  <body>
  
  <form id="pagerForm" method="post" action="<%=path%>/crmAction.do?method=toKhManage">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="20" />
</form>


<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="<%=path%>/crmAction.do?method=toKhManage" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<!--<td>
					客户编号：<input type="text" name="kh_id" id="kh_id" value="<%=userform.getKh_id()==null?"":userform.getKh_id() %>" />
				</td>  -->
				<td>
					客户名称：<input type="text" name="kh_name" id="kh_name" value="<%=userform.getKh_name()==null?"":userform.getKh_name() %>" />
				</td>
				<td>
					资料是否完善：
					<select name="is_ws" id="is_ws">
					<option value="">--请选择--</option>
					<option value="1" <%if("1".equals(userform.getIs_ws())){%>selected<%} %>  >是</option>
					<option value="0" <%if("0".equals(userform.getIs_ws())){%>selected<%} %> >否</option>
					</select>
				</td>
				<td>
					转介绍人(姓名+手机号码)：<input type="text" name="introduce_name" id="introduce_name" value="<%=userform.getIntroduce_name()==null?"":userform.getIntroduce_name() %>"  />
				</td> 
				</tr>
				<tr> 
				<td>
					手机号码1：<input type="text" name="kh_phone1" id="kh_phone1" value="<%=userform.getKh_phone1()==null?"":userform.getKh_phone1() %>" />
				</td>
				<td>
					手机号码2：<input type="text" name="kh_phone2" id="kh_phone2" value="<%=userform.getKh_phone2()==null?"":userform.getKh_phone2() %>" />
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
		<%
		if(GlobalUtil.functionCheck(privlist,GlobalConst.FUNCTION_CRM_USER_ADD)){
			%>
			<li><a class="add" href="<%=path%>/crmAction.do?method=toKhAdd" target="dialog" rel="kh_add" width="800" height="400"><span>新增</span></a></li>
			<%
		}
		%>
			<!-- <li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="demo/common/ajaxDone.html" class="delete"><span>批量删除默认方式</span></a></li> -->
			<!--<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="demo/common/ajaxDone.html" class="delete"><span>批量删除逗号分隔</span></a></li>-->
			<!--<li><a class="edit" href="demo_page4.html?uid={sid_user}" target="navTab" warn="请选择一个用户"><span>修改</span></a></li>-->
			<li class="line">line</li>
		<%
		if(GlobalUtil.functionCheck(privlist,GlobalConst.FUNCTION_CRM_USER_EXP)){
			%>
			<li><a class="icon" href="<%=path%>/crmAction.do?method=toKhExp" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			<%
		}
		%>
			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="160">
		<thead>
			<tr>
				<th width="120"  >编号</th>
				<th width="120" >名称</th>
				<th >地址</th>
				<th width="100"  >号码1</th>  
				<th width="100"  >号码2</th>  
				<th width="100"  >转介绍人</th>  
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
						<td>
							<a title="详细" target="navTab" href="<%=path%>/crmAction.do?method=toKhView&kh_id=<%=user.getKh_id() %>"    class="btnView">详细</a>
							<%
							if(GlobalUtil.functionCheck(privlist,GlobalConst.FUNCTION_CRM_USER_DEL)){
								%>
								<a title="删除" target="ajaxTodo" href="<%=path%>/crmAction.do?method=doKhDel&kh_id=<%=user.getKh_id() %>" class="btnDel">删除</a>
								<%
							}
							%>
							<%
							if(GlobalUtil.functionCheck(privlist,GlobalConst.FUNCTION_CRM_USER_EDIT)){
								%>
								<a title="编辑" target="dialog" href="<%=path%>/crmAction.do?method=toKhEdit&kh_id=<%=user.getKh_id() %>" width="800" height="400"  class="btnEdit">编辑</a>
								<%
							}
							%>
							<%
							if(GlobalUtil.functionCheck(privlist,GlobalConst.FUNCTION_HT_USER_ADD)){
								%>
								<a title="合同新增" target="navTab" href="<%=path%>/crmAction.do?method=toHtAdd&kh_id=<%=user.getKh_id() %>"  rel="ht_add"  class="btnAttach">客户合同</a>
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
  
  
  </body>
</html>
