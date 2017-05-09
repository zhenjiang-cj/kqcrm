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
	List<CompanyInfo> comlist =  (List<CompanyInfo>) request.getAttribute("comlist");
	CompanyInfo com = comlist.get(0);
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
	jQuery(document).ready(function(){
	     	$('#prove_dept').val(<%=com.getProve_dept()%>);
		});
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
  <form class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)" action="<%=path%>/companyAction.do?method=doCxEdit" method="post" name="companyForm">
    <input type="hidden" name="company_info_id" id="company_info_id" value="<%=com.getCompany_info_id() %>">
    <input type="hidden" name="operatorId" id="operatorId" value="<%=comForm.getOperatorId() %>">
    <input type="hidden" name="prove_srl" id="prove_srl" value="<%=com.getProve_srl() %>">
    
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
    <h1>诚信证明</h1>
		<p>
			<label>出具单位：</label>
			<select  name="prove_dept" id="prove_dept" class="required">
				<option value="1">京口住建局</option>
				<option value="2">润州住建局</option>
				<option value="3">丹徒住建局</option>
				<option value="4">新区建设局</option>
				<option value="5">丹阳住建局</option>
				<option value="6">句容住建局</option>
				<option value="7">扬中住建局</option>
				<option value="8">其他</option>
			</select>
		</p> 
		 
		<p>
			<label>诚信证明：</label>
			<%
				if("".equals(comForm.getApply_order_id())){
					%>
						<a class="button" href="<%=path %>/coremain/company/upload_img.jsp?type=18&companyid=<%=com.getCompany_info_id() %>&orderid=<%=com.getProve_srl() %>&operid=<%=comForm.getOperatorId() %>"
												 target="dialog" rel="dlg_cx"   title="上传诚信证明" width="645" height="370">
												<span id="span_img_18">上传诚信证明</span></a>
												<input type="hidden" name="prove_file_id" id="prove_file_id"  value="0"  class="required"  />
						<%
				} 
				%>	
				<a class="button" href="<%=path %>/coremain/company/show_img.jsp?type=18&companyid=<%=com.getProve_srl() %>&orderid=<%=com.getProve_srl() %>&operid=<%=comForm.getOperatorId() %>" 
									target="dialog" rel="dlg_page17" title="展示诚信证明" width="745" height="570">
									<span id="show_img_18">展示诚信证明</span></a>
			
		</p>
		 
		
	</div>
	</form>
	</div>
  </body>
</html>
