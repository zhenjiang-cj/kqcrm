<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.nl.integratedManage.sc.ApprovalFlowSC"%>
<%
String path = request.getContextPath();
String imgType = request.getParameter("imgType");


String buttonName = "";
String span_name = "";
if("1".equals(imgType))
{
	buttonName = "上传外部流程图";
	span_name = "out_flow_upload";
}else{
	buttonName = "上传内部流程图";
	span_name = "in_flow_upload";
}

String ql_reg_id = request.getParameter("ql_reg_id");

%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=path%>">
    
    <title>文件上传</title>
<script type="text/javascript">
	//获取到图片类型
	var imgType = <%=imgType %>
	function updateImg(){
		
		//alert("alert="+top.img_flag);
		if(imgType==1)
			setImgOut();
		else
			setImgIn();
		
		
		
		$.ajax({
		  	url: "<%=request.getContextPath() %>//qlsxBack.do?method=updateQlImgFlag&ql_reg_id=<%=ql_reg_id%>&imgFlag="+imgType+"&imgValue=1&deal_type=1",
			dataType: 'json',
		  	cache: false,
		  	success: function(msg){
		  	
		  	/*
		  	if(type == 1)
		  		$('#d_OUT_FLOW_IMG').html(d_out_img);
		  	else
		  		$('#d_IN_FLOW_IMG').html(d_in_img);
			*/
		  }
		});
	}
</script>
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
			swf:'<%=path%>/dwz/uploadify/scripts/uploadify.swf',
			uploader:'<%=path%>/coremain/qlsx/uploadAction.jsp?imgType=<%=imgType %>&ql_reg_id=<%=ql_reg_id %>',
			formData:{PHPSESSID:'xxx', ajax:1},
			buttonText:'请选择文件',
			queueID:'fileQueue',
			width:102,
			auto:false,
			onUploadSuccess:function uploadifySuccess(file, data, response){},
			onQueueComplete:function uploadifyQueueComplete(queueData){ 
						      var span_value= '<%=buttonName %>';			      
						      document.getElementById('<%=span_name %>').innerHTML=span_value+queueData.uploadsSuccessful;
						      updateImg();
						      
						}
		}"
	/>
	
	<div id="fileQueue" class="fileQueue"></div>
	<%if(ql_reg_id.equals("")){
		out.println("<script type=\"text/javascript\">");
		out.println("$('#pic_up').html('')");
		out.println("$('#pic_qx').html('')");
		out.println("</script>");
		
	} %>
	<span id = "pic_up"><input type="button" value="上传" onclick="$('#testFileInput2').uploadify('upload', '*');"/></span>
	<span id = "pic_qx"><input type="button" value="取消" onclick="$('#testFileInput2').uploadify('cancel', '*');"/></span>


</div>
  </body>
</html>
