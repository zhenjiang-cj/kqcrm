<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.company.sc.CompanySc"%>
<%
//要上传图片的类型（企业资质、）
String type = request.getParameter("type");
//企业信息编号
String companyid = request.getParameter("companyid");
//对象编号
String orderid = request.getParameter("orderid");

//登录工号（企业用户）
String operid =  request.getParameter("operid");

CompanySc sc = new CompanySc();
//文件编号
//String fileid = sc.getFileId();

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'upload_img.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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

  </head>
  
  <body>
    <div class="pageContent" style="margin: 0 10px" layoutH="50">
	
											
		<input id="testFileInput2" type="file" name="image2" 
			uploaderOption="{
				swf:'/htzj/dwz/uploadify/scripts/uploadify.swf',
				uploader:'<%=path%>/coremain/company/uploadAction.jsp?up=<%=type %>&companyid=<%=companyid %>&orderid=<%=orderid %>&operid=<%=operid %>',
				formData:{PHPSESSID:'xxx', ajax:1},
				queueID:'fileQueue',
				buttonText:'请选择文件',
				width:102,
				auto:false,  
            	onUploadSuccess:uploadifySuccess,
            	onQueueComplete:function uploadifyQueueComplete(queueData,type,companyid,orderid){ 
            					 type='<%=type %>';
            					 companyid = '<%=companyid %>';
            					 orderid = '<%=orderid %>';
            					 operid = '<%=operid %>';
						      imgs[type] = queueData.uploadsSuccessful;
						      var span_value= '';
						      if(type=='1'){
						      	span_value='上传';
						      	document.getElementById('aptitude_file_no').value='<%=companyid %>';
						      }else if(type=='2'){
						      	span_value='上传';
						      	document.getElementById('busi_license_file_no').value='<%=companyid %>';
						      }else if(type=='3'){
						      	span_value='上传';
						      	document.getElementById('company_articles_file_no').value='<%=companyid %>';
						      }else if(type=='4'){
						      	span_value='上传';
						      	document.getElementById('service_info_file_no').value='<%=companyid %>';
						      }else if(type=='10'){
						      	span_value='上传合同材料图片';
						      	document.getElementById('contract_file_no').value='<%=orderid %>';
						      }else if(type=='11'){
						      	span_value='上传物业项目合同备案证明图片';
						      	document.getElementById('pro_bak_contract_proof_file_no').value='<%=orderid %>';
						      }else if(type=='5'){
						      	span_value='上传';
						      	document.getElementById('is_photo').value='1';
						      }else if(type=='6'){
						      	span_value='上传';
						      	document.getElementById('is_photo1').value='1';
						      }else if(type=='7'){
						      	span_value='上传';
						      	document.getElementById('is_photo2').value='1';
						      }else if(type=='8'){
						      	span_value='上传';
						      	document.getElementById('is_photo3').value='1';
						      }else if(type=='9'){
						      	span_value='上传';
						      	document.getElementById('is_photo4').value='1';
						      }else if(type=='19'){
						      	span_value='上传获奖情况';
						      }else if(type=='17'){
						      	span_value='上传验资报告';
						      	document.getElementById('winning_info').value='1';
						      }else if(type=='18'){
						      	span_value='上传诚信证明';
						      	document.getElementById('prove_file_id').value='<%=companyid %>';
						      }
						      document.getElementById('span_img_'+type).innerHTML=span_value+queueData.uploadsSuccessful;
						}
			}"
		/>
		<div id="fileQueue"   class="fileQueue"></div>
		
		<input type="button" value="上传" onclick="$('#testFileInput2').uploadify('upload', '*');"/>
		<input type="button" value="取消" onclick="$('#testFileInput2').uploadify('cancel', '*');"/>
	
	
		<div class="divider"></div>

	</div>
  </body>
</html>
