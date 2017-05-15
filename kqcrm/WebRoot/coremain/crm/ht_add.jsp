<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.portal.dt.*"%>
<%@ page import="com.nl.portal.actionForm.*"%>
<%@page import="java.util.List"%>
<%@ page import="com.nl.util.GlobalConst"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	CrmForm userform = (CrmForm) request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
	List<CrmInfo> userlist = (List<CrmInfo>) request.getAttribute("userlist");//
	List<CrmInfo> khlist = (List<CrmInfo>) request.getAttribute("khlist");//
	CrmInfo kh = null;
	if(khlist!=null){
		kh = khlist.get(0);
	}
	List<CrmInfo> introducelist = (List<CrmInfo>) request.getAttribute("introducelist");//
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
 
	<script src="<%=path%>/dwz/js/jquery.validate.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
	<script type="text/javascript">
 


	</script>
	

  </head>
  
  <body>
  
   <h1>客户明细</h1>
	<div class="pageFormContent"  style="height:80px">
		  <p> <label>客户编号：</label> <%=kh.getKh_id() %> </p>
		  <p> <label>客户名称：</label> <%=kh.getKh_name() %> </p>
		  <p> <label>客户身份证：</label> <%=kh.getKh_card() %> </p>
		  <p> <label>客户归属：</label> <%=DictMgmt.getValueDescs(DictMgmt.DICT_kq_REGION,kh.getRegion(),"" )%>  </p>
		  <p> <label>客户地址：</label> <%=kh.getKh_addr() %> </p>
		  <p> <label>客户号码1：</label> <%=kh.getKh_phone1() %> </p>
		  <p> <label>客户号码2：</label> <%=kh.getKh_phone2() %> </p>
	</div>
  
  
    <div class="pageContent">
  <form class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone)" action="<%=path%>/crmAction.do?method=doHtAdd" method="post" name="userForm">
    <input type="hidden" name="kh_id" id="kh_id" value="<%=userform.getKh_id() %>">
    <input type="hidden" name="operatorId" id="operatorId" value="<%=userform.getOperatorId() %>">
    
    <div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
    </div>
    
    <div class="pageFormContent nowrap"   style="height:200px">
    <h1>合同新增</h1>
		<p>
			<label>合同编号：</label>
			<input name="ht_code"  id="ht_code" type="text"   />
		</p>
		<p>
			<label>首次签约日期：</label>
			<input name="ht_date_first"  id="ht_date_first" type="text"   class="required date" />
		</p>
		<p>
			<label>本次签约日期：</label>
			<input name="ht_date_current" id ="ht_date_current" type="text"   class="required date" />
		</p>
		<p>
			<label>押金：</label>
			<input name="ht_pledge" id="ht_pledge" type="text"  maxlength="9"  class="required digits"  />
		</p>
		<p>
			<label>租金：</label>
			<input name="ht_rent" id="ht_rent" type="text"  maxlength="9"  class="required digits"  />
		</p>
		<p>
			<label>产品名称：</label>
			<input name="prod_name" id="prod_name" type="text"  maxlength="18"  class="required"  />
		</p>
		<p>
			<label>产品代码：</label>
			<input name="prod_code" id="prod_code" type="text"  maxlength="11"     />
		</p>
		<p>
			<label>签约年度：</label>
			<input name="ht_year" id="ht_year" type="text"  maxlength="4"    class="required digits"   />
		</p>
		 
		<dl>
		<dt>备注：</dt>
			<dd>
				<textarea   name="remark" id="remark" rows="5" cols="100"  ></textarea>
			</dd>
		</dl>
		
	</div>
	</form>
	</div>
	
	 <h1>介绍人情况</h1>
	<div class="pageContent"  style="height:200px;">
		 
		<table class="table" width="1200"  style="table-layout:fixed;height:200px;">
			<thead>
				<tr>
					<th width="120" orderField="accountNo"  >客户编号</th>
					<th width="220" orderField="accountName">客户名称</th>
					<th orderField="accountType">地址</th>  
					<th width="150"  >号码1</th>  
					<th width="150">号码2</th>
				</tr>
			</thead>
			<tbody>
			
				<% if(introducelist!=null&&introducelist.size()>0){
					for(int i=0;i<introducelist.size();i++){
						CrmInfo user = introducelist.get(i);
						
						%>
						<tr target="sid_user" rel="1">
							<td><%=user.getKh_id() %></td>
							<td><%=user.getKh_name() %></td>
							<td  title="<%=user.getKh_addr() %>" style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;"><%=user.getKh_addr() %></td>
							<td><%=user.getKh_phone1() %></td>
							<td><%=user.getKh_phone2()==null?"":user.getKh_phone2() %></td>
						</tr>
						
						<%
					}
				}
				%>
				
				
			</tbody>
		</table>
		 
  
	</div>
	
	<h1>历史合同</h1>
	<div class="pageContent"  style="height:300px;">
		 
		<table class="table" width="1200" layoutH="138"  style="table-layout:fixed">
			<thead>
				<tr>
					<th width="120" orderField="accountNo"  >客户编号</th>
					<th width="220" orderField="accountName">客户名称</th>
					<th orderField="accountType">地址</th>  
					<th width="150"  >号码</th>  
					<th width="150">首次签约日期</th>
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
							<td  title="<%=user.getKh_addr() %>" style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;"><%=user.getKh_addr() %></td>
							<td><%=user.getKh_phone1() %></td>
							<td><%=user.getHt_date_first() %></td>
						</tr>
						
						<%
					}
				}
				%>
				
				
			</tbody>
		</table>
		 
  
	</div>
  </body>
</html>
