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
	List<CrmInfo> hflist =  (List<CrmInfo>) request.getAttribute("hflist");
	CrmInfo hf = hflist.get(0);
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
  
   
  
  
    <div class="pageContent">
  <form class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone)" action="<%=path%>/crmAction.do?method=doHfEdit" method="post" name="userForm">
    <input type="hidden" name="hf_id" id="hf_id" value="<%=hf.getHf_id() %>"> 
    <input type="hidden" name="operatorId" id="operatorId" value="<%=userform.getOperatorId() %>">
    
    <div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
    </div>
    
    <div class="pageFormContent nowrap" layoutH="56" style="height:100px">
    	<h1>回访工作</h1>
		<p>
			<label>应回访日期：</label>
			<input name="hf_date_must"  id="hf_date_must" type="text" class="date" value="<%=hf.getHf_date_must()==null?"":hf.getHf_date_must() %>" readonly />
		</p>
		<p>
			<label>实际回访日期：</label>
			<input name="hf_date_fact"  id="hf_date_fact" type="text"   class="date" value="<%=hf.getHf_date_fact()==null?"":hf.getHf_date_fact() %>" />
		</p>
		<p>
			<label>回访人：</label>
			<input name="hf_user_name" id="hf_user_name" type="text"     value="<%=hf.getHf_user_name()==null?"":hf.getHf_user_name() %>" />
		</p>
		<dl>
		<dt>回访情况：</dt>
			<dd>
			<textarea   name="hf_remark" id="hf_remark" rows="10" cols="100"  ><%=hf.getHf_remark()==null?"":hf.getHf_remark() %></textarea>
			</dd>
		</dl> 
		<div class="divider"></div>	
		<!-- 
		<h1>回访工作2</h1>
		<p>
			<label>应回访日期2：</label>
			<input name="hf_date_must2"  id="hf_date_must2" type="text" class="date" value="<%=hf2.getHf_date_must()==null?"":hf1.getHf_date_must() %>" readonly />
		</p>
		<p>
			<label>实际回访日期2：</label>
			<input name="hf_date_fact2"  id="hf_date_fact2" type="text"  class="date"  value="<%=hf2.getHf_date_fact()==null?"":hf1.getHf_date_fact() %>" />
		</p>
		<p>
			<label>回访情况2：</label>
			<input name="hf_remark2" id ="hf_remark2" type="text"   value="<%=hf2.getHf_remark()==null?"":hf1.getHf_remark() %>" />
		</p> 
		<p>
			<label>回访人2：</label>
			<input name="hf_user_name2" id="hf_user_name2" type="text"     value="<%=hf2.getHf_user_name()==null?"":hf1.getHf_user_name() %>" />
		</p>
		<div class="divider"></div>	
		<h1>回访工作3</h1>
		<p>
			<label>应回访日期3：</label>
			<input name="hf_date_must3"  id="hf_date_must3" type="text" class="date" value="<%=hf3.getHf_date_must()==null?"":hf1.getHf_date_must() %>"  readonly/>
		</p>
		<p>
			<label>实际回访日期3：</label>
			<input name="hf_date_fact3"  id="hf_date_fact3" type="text"  class="date"  value="<%=hf3.getHf_date_fact()==null?"":hf1.getHf_date_fact() %>" />
		</p>
		<p>
			<label>回访情况3：</label>
			<input name="hf_remark3" id ="hf_remark3" type="text"   value="<%=hf3.getHf_remark()==null?"":hf1.getHf_remark() %>" />
		</p> 
		<p>
			<label>回访人3：</label>
			<input name="hf_user_name3" id="hf_user_name3" type="text"     value="<%=hf3.getHf_user_name()==null?"":hf1.getHf_user_name() %>" />
		</p>
		<div class="divider"></div>	
		<p>
			<label>应回访日期4：</label>
			<input name="hf_date_must4"  id="hf_date_must4" type="text" class="date" value="<%=hf4.getHf_date_must()==null?"":hf1.getHf_date_must() %>"  readonly/>
		</p>
		<p>
			<label>实际回访日期4：</label>
			<input name="hf_date_fact4"  id="hf_date_fact4" type="text" class="date"   value="<%=hf4.getHf_date_fact()==null?"":hf1.getHf_date_fact() %>" />
		</p>
		<p>
			<label>回访情况4：</label>
			<input name="hf_remark4" id ="hf_remark4" type="text"   value="<%=hf4.getHf_remark()==null?"":hf1.getHf_remark() %>" />
		</p> 
		<p>
			<label>回访人4：</label>
			<input name="hf_user_name4" id="hf_user_name4" type="text"     value="<%=hf4.getHf_user_name()==null?"":hf1.getHf_user_name() %>" />
		</p>
		 -->
	</div>
	
	</form>
	</div>
  </body>
</html>
