<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.nl.integratedManage.sc.ApprovalFlowSC"%>
<%
String path = request.getContextPath();
String companyInfoId = request.getParameter("companyInfoId");
String applyOrderId = request.getParameter("applyOrderId");
String type = request.getParameter("type");
String auId = request.getParameter("auId");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=path%>">
    
    <title>�ļ��ϴ�</title>

  </head>
  <style type="text/css" media="screen">
	.my-uploadify-button {
		background:none;
		border: none;
		text-shadow: none;
		border-radius:0;
	}
	
	.uploadify:hover .my-uploadify-button {
		background:none;
		border: none;
	}
	
	.fileQueue {
		width: 400px;
		height: 150px;
		overflow: auto;
		border: 1px solid #E5E5E5;
		margin-bottom: 10px;
	}
	</style>
  <body>
    <div class="pageContent" style="margin: 0 10px" layoutH="60">

	<input id="testFileInput2" type="file" name="image2" 
		uploaderOption="{
			swf:'/htzj/dwz/uploadify/scripts/uploadify.swf',
			uploader:'<%=path%>/coremain/integratedManage/uploadAction.jsp?companyInfoId=<%=companyInfoId%>&applyOrderId=<%=applyOrderId %>&type=<%=type %>&auId=<%=auId %>',
			formData:{PHPSESSID:'xxx', ajax:1},
			buttonText:'��ѡ���ļ�',
			queueID:'fileQueue',
			width:102,
			auto:false,
			onUploadSuccess:function uploadifySuccess(file, data, response){},
			onQueueComplete:function uploadifyQueueComplete(queueData){ 
						      var span_value= '�ϴ��걨��ɨ���';				      
						      document.getElementById('span_upload').innerHTML=span_value+queueData.uploadsSuccessful;
						}
		}"
	/>
	
	<div id="fileQueue" class="fileQueue"></div>
	<input type="button" value="�ϴ�" onclick="$('#testFileInput2').uploadify('upload', '*');"/>
	<input type="button" value="ȡ��" onclick="$('#testFileInput2').uploadify('cancel', '*');"/>


</div>
  </body>
</html>
