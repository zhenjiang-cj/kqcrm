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
	

	List<UserInfo> useronlist = (List<UserInfo>) request.getAttribute("useronlist");//人员列表
	List<UserResume> resumelist = (List<UserResume>) request.getAttribute("resumelist");//简历
	UserInfo user = new UserInfo();
	
	if(useronlist!=null&&useronlist.size()>0){
		user = useronlist.get(0);
	}
	
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
	jQuery(document).ready(function(){
     	$('#sex').val(<%=user.getSex()%>);
     	$('#card_type').val(<%=user.getNationality()%>);
     	$('#nationality').val(<%=user.getStation_grade()%>);
     	$('#specialty').val(<%=user.getEducation()%>);
     	$('#education').val(<%=user.getSpecialty()%>);
     	
     	$('#station').val(<%=user.getStation()%>);
     	$('#post').val(<%=user.getPost()%>);
     	$('#is_full_time').val(<%=user.getIs_full_time()%>);
     	$('#station_grade').val(<%=user.getStation_grade()%>);
     	$('#is_pay_insurance').val(<%=user.getIs_pay_insurance()%>);
     	$('#qualifications_type').val(<%=user.getQualifications_type()%>);
     	$('#qualifications_grade').val(<%=user.getQualifications_grade()%>);
	     	var url = '<%=path%>/companyAction.do?method=viewUserResume&employee_id=<%=comForm.getEmployee_id() %>';
	     	 $("#jbsxBox1").loadUrl(url,{ data: {}, dialogId: '', callback: null });
     	
     	
	});
	 
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
		jQuery(document).ready(function(){
	     	 
			var iContentH=$(window).height()-$("#header").height()-110;
			$("#div1").height(iContentH-110);
			$("#div_page").height(iContentH-110);
		});
	</script>
  </head>
  
  <body>
<div id="div_page" class="pageContent">
  <form class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone)" action="<%=path%>/companyAction.do?method=doUserOnEdit" method="post" name="userForm">
    <input type="hidden" name="company_info_id" id="company_info_id" value="<%=comForm.getCompany_info_id() %>">
    <input type="hidden" name="employee_id" id="employee_id" value="<%=comForm.getEmployee_id() %>">
	<input type="hidden" name="operatorId" id="operatorId" value="<%=comForm.getOperatorId() %>">
    
    
    <div id="div1" class="pageFormContent nowrap"  >
    <h1>企业在职人员</h1>
    	<p>
			<label>姓名：</label>
			<input name="employee_name" type="text" maxlength="20"  class="required" value="<%=user.getEmployee_name()==null?"":user.getEmployee_name() %>"/>
			
		</p>
		<p>
			<label>性别：</label>
			<select  name="sex" id="sex" class="required">
				<%=DictMgmt.getSelectObj(DictMgmt.DICT_COMPANY_USER_SEX, "", false, false, "-1", -1, null, null, null, -1, "")%>
			</select>
		</p>
		<p>
			<label>证件类型：</label>
			<select  name="card_type" id="card_type" class="required">
				<%=DictMgmt.getSelectObj(DictMgmt.DICT_COMPANY_USER_CARD, "", false, false, "-1", -1, null, null, null, -1, "")%>
			</select>
		</p>
    	<p>
			<label>证件号：</label>
			<input name="card_no" type="card_no"  maxlength="30"  class="required alphanumeric" value="<%=user.getCard_no()==null?"":user.getCard_no() %>"/>
		</p>
		<p>
			<label>出生日期：</label>
			<input type="text" name="both_no" class="date" readonly="true" value="<%=user.getBoth_no()==null?"":user.getBoth_no() %>"/>
			<a class="inputDateButton" href="javascript:;">选择</a>
		</p>
		<p>
			<label>国籍：</label>
			<select  name="nationality" id="nationality" class="required" >
				<%=DictMgmt.getSelectObj(DictMgmt.DICT_COMPANY_USER_NATION, "", false, false, "-1", -1, null, null, null, -1, "")%>
			</select>
		</p>
		<p>
			<label>专业：</label>
			<select  name="specialty" id="specialty" class="required">
				<%=DictMgmt.getSelectObj(DictMgmt.DICT_COMPANY_USER_SPECIALTY, "", false, false, "-1", -1, null, null, null, -1, "")%>
			</select>
		</p>
		<p>
			<label>学历：</label>
			<select  name="education" id="education" class="required">
				<%=DictMgmt.getSelectObj(DictMgmt.DICT_COMPANY_USER_EDU, "", false, false, "-1", -1, null, null, null, -1, "")%>
			</select>
		</p>
		<p>
			<label>从业年限：</label>
			<input type="text" name="work_years" id="work_years"  max="99" class="required digits" value="<%=user.getWork_years()==null?"":user.getWork_years() %>"/>
		</p>
		<p>
			<label>个人电话：</label>
			<input type="text" name="phone_no" id="phone_no" maxlength="20"  class="alphanumeric"  value="<%=user.getPhone_no()==null?"":user.getPhone_no() %>"/>
		</p>
		<p>
			<label>移动电话：</label>
			<input type="text" name="mobile_phone" id="mobile_phone"  maxlength="11"  value="<%=user.getMobile_phone()==null?"":user.getMobile_phone() %>" class="required digits"  />
		</p>
		<p>
			<label>电子邮箱：</label>
			<input type="text" name="email" id="email"   class="email" maxlength="40"  value="<%=user.getEmail()==null?"":user.getEmail() %>" />
		</p>
		<p>
			<label>身份证图片：</label>
			<a class="button" href="<%=path %>/coremain/company/show_imgview.jsp?type=5&companyid=<%=comForm.getEmployee_id() %>&orderid=<%=comForm.getEmployee_id() %>&operid=<%=comForm.getOperatorId() %>"
									 target="dialog" rel="dlg_page1"   title="展示图片" width="745" height="570">
										<span id="show_img_5">展示</span></a>
		</p>
		<p>
			<label>劳动合同图片：</label>
			<a class="button" href="<%=path %>/coremain/company/show_imgview.jsp?type=6&companyid=<%=comForm.getEmployee_id() %>&orderid=<%=comForm.getEmployee_id() %>&operid=<%=comForm.getOperatorId() %>"
									 target="dialog" rel="dlg_page1"   title="展示图片" width="745" height="570">
										<span id="show_img_6">展示</span></a>
		</p>
		<p>
			<label>任职文件图片：</label>
			<a class="button" href="<%=path %>/coremain/company/show_imgview.jsp?type=7&companyid=<%=comForm.getEmployee_id() %>&orderid=<%=comForm.getEmployee_id() %>&operid=<%=comForm.getOperatorId() %>"
									 target="dialog" rel="dlg_page1"   title="展示图片" width="745" height="570">
										<span id="show_img_7">展示</span></a>
		</p>
		<p>
			<label>社保证明图片：</label>
			<a class="button" href="<%=path %>/coremain/company/show_imgview.jsp?type=8&companyid=<%=comForm.getEmployee_id() %>&orderid=<%=comForm.getEmployee_id() %>&operid=<%=comForm.getOperatorId() %>"
									 target="dialog" rel="dlg_page1"   title="展示图片" width="745" height="570">
										<span id="show_img_8">展示</span></a>
		</p>
		<p>
			<label>职称证书图片：</label>
			<a class="button" href="<%=path %>/coremain/company/show_imgview.jsp?type=9&companyid=<%=comForm.getEmployee_id() %>&orderid=<%=comForm.getEmployee_id() %>&operid=<%=comForm.getOperatorId() %>"
									 target="dialog" rel="dlg_page1"   title="展示图片" width="745" height="570">
										<span id="show_img_9">展示</span></a>
		</p>
		
	<div class="divider"></div>
		<p>
			<label>职务：</label>
			<select   name="post" id="post" class="required">
				<%=DictMgmt.getSelectObj(DictMgmt.DICT_COMPANY_USER_POST_CHILD, "", false, false, "-1", -1, null, null, null, -1, "")%>
			</select>
		</p>
		<p>
			<label>岗位：</label>
			<select   name="station" id="station" class="required" >
				<%=DictMgmt.getSelectObj(DictMgmt.DICT_COMPANY_USER_STATION, "", false, false, "-1", -1, null, null, null, -1, "")%>
			</select>
		</p>
		<p>
			<label>是否专职：</label>
			<select   name="is_full_time" id="is_full_time" class="required">
				<option value="1">专职</option>
				<option value="0">兼职</option>
			</select>
		</p>
		<p>
			<label>劳动保险号：</label> 
			<input type="text" name="labour_insurance_no" id="labour_insurance_no"  maxlength="30"  class="alphanumeric"    value="<%=user.getLabour_insurance_no()==null?"":user.getLabour_insurance_no() %>"    >
		</p>
		<p>
			<label>是否缴纳社保：</label> 
			<select   name="is_pay_insurance" id="is_pay_insurance" >
				<option value="1">是</option>
				<option value="0">否</option>
			</select>
		</p>
		<p>
			<label>劳动合同号：</label> 
			<input type="text" name="labor_contract" id="labor_contract"   maxlength="30"  class="alphanumeric"   value="<%=user.getLabor_contract()==null?"":user.getLabor_contract() %>"    >
		</p>
		<p>
			<label>合同开始时间：</label> 
			<input type="text" name="contract_start_date" id="contract_start_date" class="date" readonly="true"    value="<%=user.getContract_start_date()==null?"":user.getContract_start_date() %>"     >
			<a class="inputDateButton" href="javascript:;">选择</a>
		</p>
		<p>
			<label>合同结束时间：</label> 
			<input type="text" name="contract_end_date" id="contract_end_date"   class="date" readonly="true"    value="<%=user.getContract_end_date()==null?"":user.getContract_end_date() %>"    >
			<a class="inputDateButton" href="javascript:;">选择</a>
		</p>
		<div class="divider"></div>
		<label>备注：</label><textarea   name="remark" id=remark rows="10" cols="100" style="width:60%;height:100px"><%=user.getRemark()==null?"":user.getRemark() %></textarea>
		<div class="divider"></div>
		<h2>职称信息</h2>
		<p>
			<label>职称名称：</label> 
			<input type="text" name="station_name" id="station_name"   maxlength="30"  value="<%=user.getStation_name()==null?"":user.getStation_name() %>"   />
		</p>
		<p>
			<label>职称等级：</label>
			<select   name="station_grade" id="station_grade" class="required">
				<%=DictMgmt.getSelectObj(DictMgmt.DICT_COMPANY_USER_STA_GRADE, "", false, false, "-1", -1, null, null, null, -1, "")%>
			</select>
		</p>
		<p>
			<label>职称证号：</label> 
			<input type="text" name="station_card_no" id="station_card_no"   value="<%=user.getStation_card_no()==null?"":user.getStation_card_no() %>"   maxlength="30"  class="required alphanumeric"    />
		</p>
		<p>
			<label>发证机关：</label>
			<input type="text" name="post_Issuing_authority" id="post_Issuing_authority"   maxlength="30"  value="<%=user.getPost_Issuing_authority()==null?"":user.getPost_Issuing_authority() %>" />
		</p>
		<p>
			<label>发证日期：</label>
			<input type="text" name="issue_date" id="issue_date"   class="date" readonly="true"   value="<%=user.getIssue_date()==null?"":user.getIssue_date() %>"  />
			<a class="inputDateButton" href="javascript:;">选择</a>
		</p>
		<div class="divider"></div>
		<h2>执业资格信息</h2>
		<p>
			<label>证书编号：</label>
			<input type="text" name="qualifications_certificate_no" id="qualifications_certificate_no" maxlength="30"  class="alphanumeric" value="<%=user.getQualifications_certificate_no()==null?"":user.getQualifications_certificate_no() %>"   />
		</p>
		<p>
			<label>资格类别：</label>
			<select   name="qualifications_type" id="qualifications_type" >
				<%=DictMgmt.getSelectObj(DictMgmt.DICT_COMPANY_QUALIFICATIONS_TYPE, "", false, false, "-1", -1, null, null, null, -1, "")%>
			</select>
		</p>
		<p>
			<label>资格级别：</label>
			<input type="text" name="qualifications_grade" id="qualifications_grade"  maxlength="10"   value="<%=user.getQualifications_grade()==null?"":user.getQualifications_grade() %>"  />
		</p>
		<p>
			<label>发证机关：</label>
			<input type="text" name="quali_Issuing_authority" id="quali_Issuing_authority"  maxlength="30"   value="<%=user.getQuali_Issuing_authority()==null?"":user.getQuali_Issuing_authority() %>" />
		</p>
		<p>
			<label>发证日期：</label>
			<input type="text" name="quali_Issuing_date" id="quali_Issuing_date"   class="date" readonly="true"   value="<%=user.getQuali_Issuing_date()==null?"":user.getQuali_Issuing_date() %>"  />
			<a class="inputDateButton" href="javascript:;">选择</a>
		</p>
		<div class="divider"></div>
	</div>
	</form>
				<div id="jbsxBox1" class="unitBox" style="margin-left:10px;">
				<!--#include virtual="<%=path%>/companyAction.do?method=toResumeAdd&employee_id=<%=comForm.getEmployee_id() %>" -->
				</div>
	</div>
				
	
  </body>
</html>