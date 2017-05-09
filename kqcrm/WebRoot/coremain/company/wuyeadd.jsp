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
	     // alert(msg);
	      imgs[type] = queueData.uploadsSuccessful;
	      document.getElementById("span_img_5").innerHTML='上传企业资质图片'+queueData.uploadsSuccessful;
	      
	    if (queueData.uploadsErrored) {  
	//      alertMsg.error(msg);  
	    } else {  
	//      alertMsg.correct(msg);  
	    }  
	}

	setInterval("check()",1000);
	function check(){
		var mang_flow_area= $('#mang_flow_area').val();
		var high_flow_area= $('#high_flow_area').val();
		var villa_area= $('#villa_area').val();
		var other_area= $('#other_area').val();
		var all_area = parseInt(mang_flow_area)+parseInt(high_flow_area)+parseInt(villa_area)+parseInt(other_area);
		if(!isNaN(all_area)){
			$('#all_area').html(all_area);
		}
	}
	</script>
  </head>
  
  <body>
  <h2 class="contentTitle">物业服务项目明细</h2>
  
<div class="pageContent">
  <form class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone)" action="<%=path%>/companyAction.do?method=doProSave" method="post" name="proForm">
  <input type="hidden" name="company_info_id" id="company_info_id" value="<%=comForm.getCompany_info_id() %>">
  <input type="hidden" name="pro_code" id="pro_code" value="<%=comForm.getPro_code() %>">
  <input type="hidden" name="operatorId" id="operatorId" value="<%=comForm.getOperatorId() %>">
  <input type="hidden" name="pro_type" id="pro_type" value="1">
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
				<dt>项目名称：</dt>
					<dd>
						<input type="text" name="pro_name"  maxlength="15"   class="required" />
					</dd>
				</dl>
				<dl>
				<dt>项目地点：</dt>
					<dd>
						<select   name="pro_region" id="pro_region">
							<%=DictMgmt.getSelectObj(DictMgmt.DICT_COMPANY_REGION,"",false,false,"-1", -1, null, null, null,-1,"") %>
						</select>
						<input type="text" name="pro_addr"  alt="请输入项目具体地址"  maxlength="30"  class="required" />
					</dd>
				</dl>
				<dl>
				<dt>物业类型：</dt>
					<dd>
						<select   name="property_type" id="property_type"  class="required">
							<%=DictMgmt.getSelectObj(DictMgmt.DICT_COMPANY_WUYE_TYPE,"",false,false,"-1", -1, null, null, null,-1,"") %>
						</select>
					</dd>
				</dl>
				<!-- 
				<dl>
				<dt>项目类型：</dt>
					<dd>
						<select   name="pro_type" id="pro_type"  class="required">
						</select>
					</dd>
				</dl>
				 -->
				<dl>
				<dt>多层建筑面积：</dt>
					<dd>
						<input type="text" name="mang_flow_area" id="mang_flow_area" maxlength="12"  class="required digits" />㎡
						<font color="silver" style="text-align:right;font-size:16px;">（若无填“0”）</font>
					</dd>
				</dl>
				<dl>
				<dt>高层建筑面积：</dt>
					<dd>
						<input type="text" name="high_flow_area" id="high_flow_area"  maxlength="12"   class="required digits" />㎡
						<font color="silver" style="text-align:right;font-size:16px;">（若无填“0”）</font>
					</dd>
				</dl>
				<dl>
				<dt>别墅建筑面积：</dt>
					<dd>
						<input type="text" name="villa_area" id="villa_area"   maxlength="12"   class="required digits" />㎡
						<font color="silver" style="text-align:right;font-size:16px;">（若无填“0”）</font>
					</dd>
				</dl>
				<dl>
				<dt>其他建筑面积：</dt>
					<dd>
						<input type="text" name="other_area" id="other_area"   maxlength="12"  class="required digits" />㎡
						<font color="silver" style="text-align:right;font-size:16px;">（若无填“0”）</font>
					</dd>
				</dl>
				<dl>
				<dt>总面积：</dt>
					<dd>
						<span id="all_area"></span> ㎡
					</dd>
				</dl>
				<dl>
				<dt>物业费用：</dt>
					<dd>
						<input type="text" name="property_fee"   maxlength="10"  class="required digits" /> 元
					</dd>
				</dl>
				<dl>
				<dt>委托方名称：</dt>
					<dd>
						<input type="text" name="client_name"   maxlength="30"  class="required" />
					</dd>
				</dl>
				<dl>
				<dt>委托方法定代表人：</dt>
					<dd>
						<input type="text" name="client_representative"    maxlength="20" class="required" />
					</dd>
				</dl>
				<dl>
				<dt>电话：</dt>
					<dd>
						<input type="text" name="client_phone" maxlength="20"  class="required alphanumeric" />
					</dd>
				</dl>
				<dl>
				<dt>合同号：</dt>
					<dd>
						<input type="text" name="pro_contract_no"  maxlength="20"  class="alphanumeric" />
						<a class="button" href="<%=path %>/coremain/company/upload_img.jsp?type=10&companyid=<%=comForm.getCompany_info_id() %>&orderid=<%=comForm.getPro_code() %>&operid=<%=comForm.getOperatorId() %>"
												 target="dialog" rel="dlg_page1"   title="上传合同材料图片" width="645" height="370">
												<span id="span_img_10">上传合同材料图片</span></a>
												<input type="hidden" name="contract_file_no" id="contract_file_no" class="required" />
					</dd>
				</dl>
				<dl>
				<dt>合同开始时间：</dt>
					<dd>
						<input type="text" name="contract_start_date"   class="date" readonly="true" /><a class="inputDateButton" href="javascript:;">选择</a>
					</dd>
				</dl>
				<dl>
				<dt>合同结束时间：</dt>
					<dd>
						<!-- <input type="text" name="contract_end_date"   class="date" readonly="true" /><a class="inputDateButton" href="javascript:;">选择</a> -->
						<input type="text" name="contract_end_date" maxlength="30"   />
					</dd>
				</dl>
				<dl>
				<dt>物业项目合同备案证明：</dt>
					<dd>
					<%
				if("".equals(comForm.getApply_order_id())){
					%>
			<a class="button" href="<%=path %>/coremain/company/upload_img.jsp?type=11&companyid=<%=comForm.getCompany_info_id() %>&orderid=<%=comForm.getPro_code() %>&operid=<%=comForm.getOperatorId() %>"
												 target="dialog" rel="dlg_page1"   title="上传物业项目合同备案证明图片" width="645" height="370">
												<span id="span_img_11">上传物业项目合同备案证明图片</span></a>
												<input type="hidden" name="pro_bak_contract_proof_file_no" id="pro_bak_contract_proof_file_no" value="0" class=""  />
						<%
				} 
				%>	
						
					</dd>
				</dl>
				<dl>
				<dt>获奖情况：</dt>
					<dd>
						<%
				if("".equals(comForm.getApply_order_id())){
					%>
			<a class="button" href="<%=path %>/coremain/company/upload_img.jsp?type=19&companyid=<%=comForm.getCompany_info_id() %>&orderid=<%=comForm.getPro_code() %>&operid=<%=comForm.getOperatorId() %>"
												 target="dialog" rel="dlg_page1"   title="上传获奖情况" width="645" height="370">
												<span id="span_img_19">上传获奖情况</span></a>
												<input type="hidden" name="winning_info" id="winning_info" class=""  />
						<%
				} 
				%>	
						
						 <!-- <textarea   name="winning_info" id="winning_info" rows="10" cols="100" ></textarea> -->
					</dd>
				</dl>
				 
		
		
		 
	 </div>
	
	</form>
</div>
  </body>
</html>
