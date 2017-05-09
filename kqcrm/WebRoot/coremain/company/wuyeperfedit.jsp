<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.company.dt.*"%>
<%@ page import="com.nl.company.actionForm.*"%>
<%@page import="java.util.List"%>
<%@ page import="com.nl.util.GlobalConst"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
CompanyForm comForm = (CompanyForm) request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
List<ProPerfInfo> prolist = (List<ProPerfInfo>) request.getAttribute("prolist");
ProPerfInfo proper = prolist.get(0);

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'desktop.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=path%>/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="<%=path%>/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="<%=path%>/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
	<link href="<%=path%>/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
	
	<style>
		 .input{width:250px;}
		 .but_s{width:60px;height:30px;}
		 .table{border:solid #add9c0; border-width:2px 1px 1px 2px;}
		 .tr{height:60px;}
	</style>


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
	      alert(msg);
	      imgs[type] = queueData.uploadsSuccessful;
	      document.getElementById("span_img_1").innerHTML='上传企业资质图片'+queueData.uploadsSuccessful;
	      
	    if (queueData.uploadsErrored) {  
	//      alertMsg.error(msg);  
	    } else {  
	//      alertMsg.correct(msg);  
	    }  
	}  
	
	</script>
  </head>
  
  <body>
  <h2 class="contentTitle">物业经营业绩</h2>
  
<div class="pageContent">
  <form class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)" action="<%=path%>/companyAction.do?method=doProPerfEdit" method="post" name="proForm">
  <input type="hidden" name="operating_performance_srl" id="operating_performance_srl" value="<%=proper.getOperating_performance_srl() %>">
    <input type="hidden" name="operatorId" id="operatorId" value="<%=comForm.getOperatorId() %>">
  
		
  	<div class="pageFormContent nowrap" layoutH="97">
  	<div class="formBar">
			<ul>
				<%
				if("1".equals(comForm.getApply_order_id())){
					
				}else{
					%>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
					<%
				}
				%>
			</ul>
		</div>
				<dl>
				<dt>年度：</dt>
					<dd>
						<input type="text" name="year" class="date" dateFmt="yyyy" readonly="true" value="<%=proper.getYear() %>" />
										<a class="inputDateButton" href="javascript:;">选择</a>
										<span class="info">yyyy</span>
					</dd>
				</dl>
				<dl>
				<dt>万米以上小区：</dt>
					<dd>
						<input type="text" name="wpm_district" maxlength="4"   class="required digits"  value="<%=proper.getWpm_district() %>" />个
					</dd>
				</dl>
				<dl>
				<dt>整改小区：</dt>
					<dd>
						<input type="text" name="rectification_district" maxlength="4"  class="required digits"  value="<%=proper.getRectification_district() %>" />个
					</dd>
				</dl>
				<dl>
				<dt>总建筑面积：</dt>
					<dd>
						<input type="text" name="full_built_area"  maxlength="16"   class="required digits"  value="<%=proper.getFull_built_area() %>" />㎡
					</dd>
				</dl>
				 
				<dl>
				<dt>年度企业利润总额：</dt>
					<dd>
						<input type="text" name="full_year_profit" maxlength="10"  class="required digits"  value="<%=proper.getFull_year_profit() %>" /> 元 
					</dd>
				</dl>
				 
	 </div>
	
	</form>
</div>
  </body>
</html>
