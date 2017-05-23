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
  
  <form id="pagerForm" method="post" action="<%=path%>/crmAction.do?method=toHfManage">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="20" /> 
</form>


<div class="pageHeader">
	<form rel="pagerForm"  onsubmit="return navTabSearch(this);" action="<%=path%>/crmAction.do?method=toHfManage" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					客户名称： <input type="text" name="kh_name" id="kh_name" value="<%=userform.getKh_name()==null?"":userform.getKh_name() %>"  />
				</td>
				<td>
					回访状态： 
					<select name="hf_status" id="hf_status">
					<option value="">--请选择--</option>
					<option value="1" <%if("1".equals(userform.getHf_status())){%>selected<%} %>  >已回访</option>
					<option value="0" <%if("0".equals(userform.getHf_status())){%>selected<%} %> >未回访</option>
					</select>
				</td>
				<td>
					回访次数： <input type="text" name="hf_type" id="hf_type" value="<%=userform.getHf_type()==null?"":userform.getHf_type() %>"  />
				</td> 
				<td>
					工作统计开始日期：<input type="text" name="hf_begin_date" id="hf_begin_date" class="date" value="<%=userform.getHf_begin_date()==null?"":userform.getHf_begin_date() %>" />
				</td>
				<td>
					工作统计结束日期：<input type="text" name="hf_end_date" id="hf_end_date" class="date" value="<%=userform.getHf_end_date()==null?"":userform.getHf_end_date() %>" />
				</td>
				<td>
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
			if(GlobalUtil.functionCheck(privlist,GlobalConst.FUNCTION_HF_USER_EXP)){
				%>
				<li><a class="icon" href="<%=path%>/crmAction.do?method=toHfExp" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
				<%
			}
			%>
		</ul>
	</div>
	<table class="table" width="1400" layoutH="138"  style="table-layout:fixed">
		<thead>
			<tr>
				<th width="120" >客户编码</th>
				<th width="120" >客户姓名</th>
				<th width="100" >签约年度</th>  
				<th width="100" >押金</th>  
				<th width="120" >身份证号</th>
				<th width="100" >电话号码</th>
				<th >地址</th>  
				<th width="100">回访状态</th>
				<th width="100">回访次数</th>
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
						<td><%=user.getHf_type()==null?"":user.getHf_type() %></td>
						<td><%=user.getHf_date_must()==null?"":user.getHf_date_must() %></td>
						<td><%=user.getHf_date_fact()==null?"":user.getHf_date_fact() %></td>
						<td  title="<%=user.getHf_remark()==null?"":user.getHf_remark() %>" style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;"><%=user.getHf_remark()==null?"":user.getHf_remark() %></td>
						<td><%=user.getHf_user_name()==null?"":user.getHf_user_name() %></td>
						<td>
							<%
							if(GlobalUtil.functionCheck(privlist,GlobalConst.FUNCTION_HF_USER_EDIT)){
								%>
								<a title="回访" target="dialog" href="<%=path%>/crmAction.do?method=toHfEdit&hf_id=<%=user.getHf_id() %>" width="645" height="400" rel="ht"  class="btnEdit">回访</a>
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
