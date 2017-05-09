<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.company.dt.*"%>
<%@ page import="com.nl.company.actionForm.*"%>
<%@page import="java.util.List"%>
<%@ page import="com.nl.util.GlobalConst"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	CompanyForm comForm = (CompanyForm) request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
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
	<link href="<%=path%>/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="<%=path%>/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="<%=path%>/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
	<link href="<%=path%>/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
	
	<script type="text/javascript">
	var imgs=[];//不同类型上传的图片类型数量数组
	imgs[0]=0;
	function uploadifySuccess(file, data, response){  
    //获取后台返回到前台的文件名，添加到隐藏域,多文件用";"号隔开
	    //var files = $("#files").attr("value");  
	//  alert(files=="");  
	    //第一个文件  
	    //if(files=="")  
	    //    files = data;  
	    //else  
	   //     files+=";"+data  
	//  alert(files);  
	   // $("#files").attr("value",files);  
	      
	}
	function uploadifyQueueComplete(queueData,type){  
  
	    var msg = "The total number of files uploaded: "+queueData.uploadsSuccessful+"<br/>"  
	        + "The total number of errors while uploading: "+queueData.uploadsErrored+"<br/>"  
	        + "The total number of bytes uploaded: "+queueData.queueBytesUploaded+"<br/>"  
	        + "The average speed of all uploaded files: "+queueData.averageSpeed;  
	     // alert(msg);
	      imgs[type] = queueData.uploadsSuccessful;
	      document.getElementById("span_img_5").innerHTML='上传企业资质图片'+queueData.uploadsSuccessful;
	      
	    if (queueData.uploadsErrored) {  
	//      alertMsg.error(msg);  
	    } else {  
	//      alertMsg.correct(msg);  
	    }  
	}

	</script>
	
  </head>
  
  <body>
    <div class="pageContent">
  <form class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)" action="<%=path%>/companyAction.do?method=doCompanyInfoAdd" method="post" name="companyForm">
    <input type="hidden" name="company_info_id" id="company_info_id" value="<%=comForm.getCompany_info_id() %>">
    <input type="hidden" name="operatorId" id="operatorId" value="<%=comForm.getOperatorId() %>">
    <input type="hidden" name="investment_info_srl" id="investment_info_srl" value="<%=comForm.getInvestment_info_srl() %>">
    		<input type="hidden" name="real_capital" id="real_capital" value="0">
    
    <div class="formBar">
			<ul>
				<%
				if("1".equals(comForm.getApply_order_id())){
					
				}else{
					%>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
					<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
					<%
				}
				%>
			</ul>
    </div>
    
    <div class="pageFormContent nowrap" layoutH="56" style="height:100px">
    <h1>企业投资者信息</h1>
    	<p>
			<label>名称：</label>
			<input name="investor_name" type="text"   maxlength="10"  class="required"/>
		</p>
		<p>
			<label>机构代码或身份证号：</label>
			<input name="org_or_card_no" type="text"  maxlength="20"  class="alphanumeric" />
		</p>
		<p>
			<label>投资者类型：</label>
			<select  name="investor_type" id="investor_type" class="required">
				<%=DictMgmt.getSelectObj(DictMgmt.DICT_COMPANY_INVESTOR_TYPE, "", false, false, "-1", -1, null, null, null, -1, "")%>
			</select>
		</p> 
		<p>
			<label>投资者类型2：</label>
			<select  name="investor_typec_child" id="investor_typec_child" class="required">
				<%=DictMgmt.getSelectObj(DictMgmt.DICT_COMPANY_INVESTOR_TYPE_OTHER, "", false, false, "-1", -1, null, null, null, -1, "")%>
			</select>
		</p> 
		<p>
			<label>出资方式：</label>
			<select  name="investment_way" id="investment_way" class="required">
				<%=DictMgmt.getSelectObj(DictMgmt.DICT_COMPANY_INVESTMENT_WAY, "", false, false, "-1", -1, null, null, null, -1, "")%>
			</select>
		</p>
		<p>
			<label>注册资本：</label>
			<input name="regist_capital" type="text"  maxlength="16" class="digits"/> （元）
		</p>
		<p>
			<label>验资报告：</label>
			<%
				if("".equals(comForm.getApply_order_id())){
					%>
						<a class="button" href="<%=path %>/coremain/company/upload_img.jsp?type=17&companyid=<%=comForm.getCompany_info_id() %>&orderid=<%=comForm.getInvestment_info_srl() %>&operid=<%=comForm.getOperatorId() %>"
												 target="dialog" rel="dlg_yz"   title="上传验资报告" width="645" height="370">
												<span id="span_img_17">上传验资报告</span></a>
												<input type="hidden" name="winning_info" id="winning_info" class=""  />
						<%
				} 
				%>	
			
		</p>
		 
		
	</div>
	</form>
	</div>
  </body>
</html>
