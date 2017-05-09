<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.company.dt.*"%>
<%@page import="java.util.List"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<UserInfo> userList = (List<UserInfo>) request.getAttribute("userlist");//人员列表


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
  <form onsubmit="return navTabSearch(this);" action="demo_page1.html" method="post">
  	<div class="pageContent sortDrag" selector="h1" layoutH="42">
		 <div class="tabs" currentIndex="0" eventType="click">
		<div class="tabsContent" style="height:750px;">
		<!-- 物业经营业绩 -->
			<div>
			<!-- 物业经营业绩基本信息begin -->
				<div id="navTab0" class="panel">
					<div class="navTab-panel tabsPageContent layoutBox">
						<div class="page unitBox">
							 <table   width="100%"    >
							 	<thead>
									<tr>
										<th style="text-align:center;font-size:30px;font-weight:bold;" colspan="2">企业资质信息</th>
									</tr>
								</thead>
							 </table>
						</div>
						
					</div>
				</div>
			<!-- 物业经营业绩基本信息end -->	
			<!-- 物业经营业绩报表begin -->	
			<table class="table" width="100%"  >
				<thead>
					<tr>
						<th width="20%">企业名称</th>
						<th width="15%">证书有效期始</th>
						<th width="15%">证书有效期止</th>
						<th width="15%">资质等级</th>
						<th width="20%">申请事项</th>
						<th width="15%">审批记录</th>
					</tr>
				</thead>
				<tbody>
					<tr target="sid_user" rel="1">
						<td>
							 xxxxxxxx
						</td>
						<td>2016-02-01</td>
						<td>2017-02-01</td>
						<td>贰级</td>
						<td>初次申请</td>
						<td>-</td>
					</tr>
					 
				</tbody>
			</table>
			<!-- 分页控制 -->
			<div class="panelBar">
				<div class="pages">
					<span>显示</span>
					<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
						<option value="20">20</option>
						<option value="50">50</option>
						<option value="100">100</option>
						<option value="200">200</option>
					</select>
					<span>条，共${totalCount}条</span>
				</div>
				
				<div class="pagination" targetType="navTab" totalCount="200" numPerPage="20" pageNumShown="10" currentPage="1"></div>
		
			</div>
					
			
			<!-- 物业经营业绩报表end -->
			</div>
			<!-- 物业管理项目 -->
			<div></div>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
		
		
	 </div>
	
	</form>
  </body>
</html>
