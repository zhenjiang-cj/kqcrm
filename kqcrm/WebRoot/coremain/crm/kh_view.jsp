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
	CrmInfo kh = null;
	if(userlist!=null){
		kh = userlist.get(0);
	}
	List<CrmInfo> htlist = (List<CrmInfo>) request.getAttribute("htlist");//
	List<CrmInfo> hflist = (List<CrmInfo>) request.getAttribute("hflist");//
	//List<CrmInfo> introducelist = (List<CrmInfo>) request.getAttribute("introducelist");//
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
	
 
	<script src="<%=path%>/dwz/js/jquery.validate.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
	<script type="text/javascript">
 


	</script>
	

  </head>
  
  <body>
   
	<div class="tabsPage"  >
		<ul class="tabsPageHeader"><li><span>客户明细</span></li></ul>
	</div>
	<div class="pageFormContent nowrap"   style="height:160px">

		<table class="TabList">                   	                  
               <tr style="margin-top:20px;">
                   <td class="tit03">客户编号：</td>
                   <td style="text-align:left;">
                      <%=kh.getKh_id() %>
                   </td>
                   <td class="tit03">客户名称：</td>
                   <td style="text-align:left;">
                       <%=kh.getKh_name() %>
                   </td>
               </tr>
               <tr style="margin-top:20px;">
                   <td class="tit">客户身份证：</td>
                   <td style="text-align:left;">
                       <%=kh.getKh_card() %>
                   </td>
                   <td class="tit">客户归属：</td>
                   <td style="text-align:left;">
                       <%=DictMgmt.getValueDescs(DictMgmt.DICT_kq_REGION,kh.getRegion(),"" ) %>
                   </td>
               </tr>
               <tr style="margin-top:20px;">
                   <td class="tit">客户号码1：</td>
                   <td style="text-align:left;">
                       <%=kh.getKh_phone1() %>
                   </td>
                   <td class="tit">客户号码2：</td>
                   <td style="text-align:left;">
                       <%=kh.getKh_phone2() %>
                   </td>
               </tr>
               <tr style="margin-top:20px;">
                   <td class="tit">转介绍人(姓名+手机号码)：</td>
                   <td style="text-align:left;" colspan="3">
                       <%=kh.getIntroduce_name() %> 
                   </td>
   
               </tr>
	  		  <tr style="margin-top:20px;">
                   <td class="tit">家庭住址：</td>
                   <td style="text-align:left;" colspan="3">
                       <%=kh.getKh_addr() %>
                   </td>
   
               </tr>
         </table>
	</div>
 
    <div class="tabsPage"  >
		<ul class="tabsPageHeader"><li><span>客户合同</span></li></ul>
	</div>
	<div class="pageContent"  >
	<div class=tabsPage style="height:220px;">
		<table class="table" width="1200"   style="table-layout:fixed;height:220px">
			<thead>
				<tr>
					<th width="200" orderField="accountName">合同编号</th>
					<th width="150">首次签约日期</th>
					<th width="150">本次签约日期</th>
					<th width="100">押金(元)</th>
					<th width="100">租金(元)</th>
					<th width="150">产品名</th>
					<th width="100">签约年度</th>
					<th >备注</th>
				</tr>
			</thead>
			<tbody>
			
				<% if(htlist!=null&&htlist.size()>0){
					for(int i=0;i<htlist.size();i++){
						CrmInfo ht = htlist.get(i);
						
						%>
						<tr  style="margin-top:20px;">
							<td ><%=ht.getHt_id() %></td>
							<td ><%=ht.getHt_date_first() %></td>
							<td ><%=ht.getHt_date_current() %></td>
							<td ><%=ht.getHt_pledge() %></td>
							<td ><%=ht.getHt_rent() %></td>
							<td ><%=ht.getProd_name() %></td>
							<td ><%=ht.getHt_year() %></td>
							<td  title="<%=ht.getRemark() %>" style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;"><%=ht.getRemark() %></td>
						
						</tr>
						
						<%
					}
				}
				%>
				
				
			</tbody>
		</table> 
	</div>
	</div>
	
	 <div class="tabsPage"  >
		<ul class="tabsPageHeader"><li><span>回访记录</span></li></ul>
	</div>
	<div class="pageContent"  >
<<<<<<< .mine
	<div class=tabsPage style="height:320px;">
		<table class="table" width="1200"   style="table-layout:fixed;height:320px">
=======
	<div class=tabsPage style="height:160px;">
		<table class="table" width="1200"   style="table-layout:fixed;height:200px">
>>>>>>> .r172
			<thead>
				<tr>
					<th width="120" orderField="accountNo"  >合同编号</th>
					<th width="100">回访状态</th>
					<th width="100">回访次数</th>
					<th width="100">应访日期</th>
					<th width="100">实际日期</th>
					<th >访问情况</th>
					<th width="100">回访人</th>
				</tr>
			</thead>
			<tbody>
			
				<% if(hflist!=null&&hflist.size()>0){
					for(int i=0;i<hflist.size();i++){
						CrmInfo hf = hflist.get(i);
						
						%>
						<tr  style="margin-top:20px;">
							<td  ><%=hf.getHt_id() %></td>
							<td><%=hf.getHf_status().equals("1")?"已回访":"未回访" %></td>
						<td><%=hf.getHf_type()==null?"":hf.getHf_type() %></td>
							<td><%=hf.getHf_date_must()==null?"":hf.getHf_date_must() %></td>
							<td><%=hf.getHf_date_fact()==null?"":hf.getHf_date_fact() %></td>
							<td  title="<%=hf.getHf_remark()==null?"":hf.getHf_remark() %>" style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;"><%=hf.getHf_remark()==null?"":hf.getHf_remark() %></td>
							<td><%=hf.getHf_user_name()==null?"":hf.getHf_user_name() %></td>
						</tr>
						
						<%
					}
				}
				%>
				
				
			</tbody>
		</table> 
	</div>
	</div>
	 
   
	
    <div class="formBar" >
			<ul>
				<!-- <li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li> -->
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
			</ul>
    </div>
	</form>
	</div>
  </body>
</html>
