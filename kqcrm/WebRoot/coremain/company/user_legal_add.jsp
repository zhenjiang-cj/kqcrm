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
	List<UserInfo> userList = (List<UserInfo>) request .getAttribute("userlist");//人员列表
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
<script type="text/javascript">
	// 1为企业资质图片，2为营业执照图片，3为企业章程图片，4为企业服务质量/服务收费制度图片，5为企业人员身份证图片，6为企业人员劳动合同图片，7为企业人员任职文件图片，8为企业人员社保证明图片，
    // 9为企业人员职称证书图片，10为物业服务项目合同材料图片，11为物业服务项目项目合同备案证明图片，12为企业上报申报表扫描件图片，13证书变更申请报告图片，14为工商变更核准通知书 
	var imgs=[];//不同类型上传的图片类型数量数组
	imgs[0]=0;
	
	function uploadifySuccess(file, data, response){  
	      
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
	function doSave(){
		userForm.submit();
	}
	
	function dialogAjaxDoneFather(json) {
	    DWZ.ajaxDone(json);
	    if (json.statusCode == DWZ.statusCode.ok) {
	        if (json.navTabId) {
	            //var dialog = $("body").data(json.navTabId);
	            $("#jbsxBox").loadUrl(json.forwardUrl,{ data: {}, dialogId: json.navTabId, callback: null });
	            //navTab.reload(json.forwardUrl,{navTabId: json.navTabId});
	            //$.pdialog.reload(dialog.data("url"), { data: {}, dialogId: json.navTabId, callback: null })
	        }
	        if ("closeCurrent" == json.callbackType) {
	            $.pdialog.closeCurrent();
	        }
	    }
	}
	function checkStation(){
		var station = $("#station").val();
		if(station<=3){
			$("#is_photo2").attr("class","required");
		}else{
			$("#is_photo2").attr("class","");
		}
	
	}
		jQuery(document).ready(function(){
	     	 
			var iContentH=$(window).height()-$("#header").height()-110;
			$("#div1").height(iContentH-300);
		});
	
	</script>
  </head>
  
  <body>
<div class="pageContent">
  <form class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone)" action="<%=path%>/companyAction.do?method=doUserLegalSave" method="post" name="userForm">
    <input type="hidden" name="company_info_id" id="company_info_id" value="<%=comForm.getCompany_info_id() %>">
    <input type="hidden" name="employee_id" id="employee_id" value="<%=comForm.getEmployee_id() %>">
	<input type="hidden" name="operatorId" id="operatorId" value="<%=comForm.getOperatorId() %>">
	<input type="hidden" name="is_full_time" id="is_full_time" value="1">
	<input type="hidden" name="station_card_no" id="station_card_no" value="0">
    
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
    
    <div id="div1" class="pageFormContent nowrap"   >
    <h1>法定代表人简况</h1>
    	<p>
			<label>姓名：</label>
			<input name="employee_name" type="text" maxlength="10"  class="required"/>
		</p>
		<p>
			<label>性别：</label>
			<select  name="sex" id="sex" class="required">
				<%=DictMgmt.getSelectObj(DictMgmt.DICT_COMPANY_USER_SEX, "", false, false, "-1", -1, null, null, null, -1, "")%>
			</select>
		</p>
		<p>
			<label>国籍：</label>
			<select  name="nationality" id="nationality" class="required">
				<%=DictMgmt.getSelectObj(DictMgmt.DICT_COMPANY_USER_NATION, "", false, false, "-1", -1, null, null, null, -1, "")%>
			</select>
		</p>
		<p>
			<label>出生日期：</label>
			<input type="text" name="both_no" class="date" readonly="true" />
			<a class="inputDateButton" href="javascript:;">选择</a>
		</p>
		<p>
			<label>身份证：</label>
			<input type="hidden" name="card_type" id="card_type" value="1">
			<input type="text" name="card_no" id="card_no" maxlength="18" class="required alphanumeric"  />
		</p>
		<p>
			<label>任职时间(开始)：</label>
			<input type="text" name="contract_start_date" id="contract_start_date" class="required date" readonly="true" />
			<a class="inputDateButton" href="javascript:;">选择</a>
		</p>
		<p>
			<label>任职时间(结束)：</label>
			<input type="text" name="contract_end_date" id="contract_end_date" class="required date" readonly="true" />
			<a class="inputDateButton" href="javascript:;">选择</a>
		</p>
		<p>
			<label>职务：</label>
			董事长<input type="hidden" name="station" id="station"  value="5" readonly="true" />
			 <input type="hidden" name="post" id="post"  value="11" readonly="true" />
		</p>
		<p>
			<label>职称等级：</label>
			<select   name="station_grade" id="station_grade" class="required">
				<%=DictMgmt.getSelectObj(DictMgmt.DICT_COMPANY_USER_STA_GRADE, "", false, false, "-1", -1, null, null, null, -1, "")%>
			</select>
		</p>
		<p>
			<label>从业年限：</label>
			<input type="text" name="work_years" id="work_years"  maxlength="2" max="99" class="required digits" />
		</p>
		<p>
			<label>电子邮箱：</label>
			<input type="text" name="email" id="email"   class="email" maxlength="40" />
		</p>
		<p>
			<label>学历情况：</label>
			<select   name="education" id="education" class="required">
				<%=DictMgmt.getSelectObj(DictMgmt.DICT_COMPANY_USER_EDU, "", false, false, "-1", -1, null, null, null, -1, "")%>
			</select>
		</p>
		<p>
			<label>个人电话：</label>
			<input type="text" name="phone_no" id="phone_no" maxlength="20"  class="alphanumeric"  />
		</p>
		<p>
			<label>专业情况：</label>
			<select   name="specialty" id="specialty" class="required">
													<%=DictMgmt.getSelectObj(DictMgmt.DICT_COMPANY_USER_SPECIALTY, "", false, false, "-1", -1, null, null, null, -1, "")%>
												</select>
		</p>
		<p>
			<label>移动电话：</label>
			<input type="text" name="mobile_phone" id="mobile_phone"  maxlength="11"  class="required digits"  />
		</p> 
		<p>
			<label>身份证图片：</label>
			<%
				if("".equals(comForm.getApply_order_id())){
					%>
			<a class="button" href="<%=path %>/coremain/company/upload_img.jsp?type=5&companyid=<%=comForm.getCompany_info_id() %>&orderid=<%=comForm.getEmployee_id() %>&operid=<%=comForm.getOperatorId() %>"
								 target="dialog" rel="dlg_page1"   title="身份证图片" width="645" height="370">
								<span id="span_img_5">身份证图片</span></a>
					<input type="hidden" name="is_photo" id="is_photo" class="required" />
						<%
				} 
				%>	
		</p>
		<p>
			<label>劳动合同图片：</label>
			<%
				if("".equals(comForm.getApply_order_id())){
					%>
			<a class="button" href="<%=path %>/coremain/company/upload_img.jsp?type=6&companyid=<%=comForm.getCompany_info_id() %>&orderid=<%=comForm.getEmployee_id() %>&operid=<%=comForm.getOperatorId() %>"
								 target="dialog" rel="dlg_page1"   title="劳动合同图片" width="645" height="370">
								<span id="span_img_6">劳动合同图片</span></a>
					<input type="hidden" name="is_photo1" id="is_photo1" class="required" />
						<%
				} 
				%>	
		</p>
		<p>
			<label>任职文件图片：</label>
			<%
				if("".equals(comForm.getApply_order_id())){
					%>
			<a class="button" href="<%=path %>/coremain/company/upload_img.jsp?type=7&companyid=<%=comForm.getCompany_info_id() %>&orderid=<%=comForm.getEmployee_id() %>&operid=<%=comForm.getOperatorId() %>"
								 target="dialog" rel="dlg_page1"   title="任职文件图片" width="645" height="370">
								<span id="span_img_7">任职文件图片</span></a>
					<input type="hidden" name="is_photo2" id="is_photo2" class="" />
						<%
				} 
				%>	
		</p>
		<p>
			<label>社保证明图片：</label>
			<%
				if("".equals(comForm.getApply_order_id())){
					%>
			<a class="button" href="<%=path %>/coremain/company/upload_img.jsp?type=8&companyid=<%=comForm.getCompany_info_id() %>&orderid=<%=comForm.getEmployee_id() %>&operid=<%=comForm.getOperatorId() %>"
								 target="dialog" rel="dlg_page1"   title="社保证明图片" width="645" height="370">
								<span id="span_img_8">社保证明图片</span></a>
					<input type="hidden" name="is_photo3" id="is_photo3" class="required" />
						<%
				} 
				%>	
		</p>
		<p>
			<label>职称证书图片：</label>
			<%
				if("".equals(comForm.getApply_order_id())){
					%>
			<a class="button" href="<%=path %>/coremain/company/upload_img.jsp?type=9&companyid=<%=comForm.getCompany_info_id() %>&orderid=<%=comForm.getEmployee_id() %>&operid=<%=comForm.getOperatorId() %>"
								 target="dialog" rel="dlg_page1"   title="职称证书图片" width="645" height="370">
								<span id="span_img_9">职称证书图片</span></a>
					<input type="hidden" name="is_photo4" id="is_photo4" class="required" />
						<%
				} 
				%>	
		</p>
		
		<div class="divider"></div>
		<label>备注：</label><textarea   name="remark" id=remark rows="10" cols="100" style="width:60%;height:100px"></textarea>
		<div class="divider"></div>
		
				 
	</div>
	</form>
	</div>
			<div class="panelBar">
					<ul class="toolBar">
					<%
				if("".equals(comForm.getApply_order_id())){
					%>
										<li><a class="add" href="<%=path%>/companyAction.do?method=toResumeAdd&employee_id=<%=comForm.getEmployee_id() %>" 
													width="645" height="470" target="dialog" rel="jbsxBox" ><span>添加简历</span></a></li>
						<%
				} 
				%>	
					</ul>
				</div>
				<div id="jbsxBox" class="unitBox" style="margin-left:10px;">
				<!--#include virtual="<%=path%>/companyAction.do?method=toResumeAdd&employee_id=<%=comForm.getEmployee_id() %>" -->
				</div>	
  </body>
</html>
