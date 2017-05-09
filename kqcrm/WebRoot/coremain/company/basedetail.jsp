<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@ page import="com.nl.company.actionForm.*"%>
<%@ page import="com.nl.company.dt.*"%>
<%@ page import="com.nl.util.GlobalConst"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
CompanyForm comForm = (CompanyForm) request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
List<CompanyInfo> companyList = (List<CompanyInfo>) request .getAttribute("companyList");//企业基本信息
CompanyInfo company = null;
if(companyList!=null&&companyList.size()>0){
	  company = companyList.get(0);
}
List<PicFileInfo> aptitude_picList = (List<PicFileInfo>) request .getAttribute("aptitude_picList");//企业基本信息
List<PicFileInfo> license_picList = (List<PicFileInfo>) request .getAttribute("license_picList");//企业基本信息
List<PicFileInfo> articles_picList = (List<PicFileInfo>) request .getAttribute("articles_picList");//企业基本信息
List<PicFileInfo> service_picList = (List<PicFileInfo>) request .getAttribute("service_picList");//企业基本信息
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
	
	// 1为企业资质图片，2为营业执照图片，3为企业章程图片，4为企业服务质量/服务收费制度图片，5为企业人员身份证图片，6为企业人员劳动合同图片，7为企业人员任职文件图片，8为企业人员社保证明图片，
    // 9为企业人员职称证书图片，10为物业服务项目合同材料图片，11为物业服务项目项目合同备案证明图片，12为企业上报申报表扫描件图片，13证书变更申请报告图片，14为工商变更核准通知书 
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
	      document.getElementById("span_img_1").innerHTML='上传企业资质图片'+queueData.uploadsSuccessful;
	      
	    if (queueData.uploadsErrored) {  
	//      alertMsg.error(msg);  
	    } else {  
	//      alertMsg.correct(msg);  
	    }  
	}
	function doSave(){
	     
		baseForm.submit();
	}
	jQuery(document).ready(function(){
     	$('#company_type').val(<%=company.getCompany_type()%>);
     	$('#company_region').val(<%=company.getCompany_region()%>);
     	$('#legal_person_type').val(<%=company.getLegal_person_type()%>);
     	$('#parent_department').val(<%=company.getParent_department()%>);
     	$('#approva_department').val(<%=company.getApprova_department()%>);
	});
	</script>
  </head>
  
  <body>
  <h2 class="contentTitle">企业基本情况</h2>
  
<div class="pageContent">
  <form class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone)" action="<%=path%>/companyAction.do?method=doBaseEdit" method="post" name="baseForm">
  <input type="hidden" name="operatorId" id="operatorId" value="<%=comForm.getOperatorId() %>">
  <input type="hidden" name="company_info_id" id="company_info_id" value="<%=comForm.getCompany_info_id() %>">
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
  	<div class="pageFormContent nowrap" layoutH="97">
				<dl>
				<dt>企业名称：</dt>
					<dd>
						<input type="text" name="company_name"  maxlength="128"  class="required"  value="<%=company.getCompany_name() %>" />
					</dd>
				</dl>
				<dl>
				<dt>公司类型：</dt>
					<dd>
						<select  name="company_type" id="company_type" >
							<%=DictMgmt.getSelectObj(DictMgmt.DICT_COMPANY_TYPE,"",false,false,"-1", -1, null, null, null,-1,"") %>
						</select>
					</dd>
				</dl>
				<dl>
				<dt>所属区域：</dt>
					<dd>
						<select   name="company_region" id="company_region" class="required">
							<%=DictMgmt.getSelectObj(DictMgmt.DICT_COMPANY_REGION,"",false,false,"-1", -1, null, null, null,-1,"") %>
						</select>
						&nbsp;&nbsp;&nbsp;
						<!-- 
						<select  name="company_county" id="company_county">
							<%=DictMgmt.getSelectObj(DictMgmt.DICT_COMPANY_COUNTY,"",false,false,"-1", -1, null, null, null,-1,"") %>
						</select>
						 -->
						<input type="hidden" name="company_county" id="company_county" value="0">
					</dd>
				</dl>
				<dl>
				<dt>法人类型：</dt>
					<dd>
						<select  name="legal_person_type" id="legal_person_type">
							<%=DictMgmt.getSelectObj(DictMgmt.DICT_COMPANY_LEGAL,"",false,false,"-1", -1, null, null, null,-1,"") %>
						</select>
					</dd>
				</dl>
				<dl>
				<dt>注册地址：</dt>
					<dd>
						<input type="text" name="regis_addr"   class="required" maxlength="128"  size="100"   value="<%=company.getRegis_addr() %>" />
						<font color="silver" style="text-align:right;font-size:16px;">（营业执照上的注册地址）</font>
					</dd>
				</dl>
				<dl>
				<dt>成立日期：</dt>
					<dd>
						<input type="text" name="establishment_date"   class="required date" readonly="true"   value="<%=company.getEstablishment_date() %>" /><a class="inputDateButton" href="javascript:;">选择</a>
						<font color="silver" style="text-align:right;font-size:16px;">（营业执照的签发日期）</font>
					</dd>
				</dl>
				<dl>
				<dt>主管部门：</dt>
					<dd>
						<select  name="parent_department" id="parent_department">
							<%=DictMgmt.getSelectObj(DictMgmt.DICT_COMPANY_PARENT_DEPT,"",false,false,"-1", -1, null, null, null,-1,"") %>
						</select>
					</dd>
				</dl>
				<dl>
				<dt>批准日期：</dt>
					<dd>
						<input type="text" name="approva_date" id="approva_date" class="date" readonly="true"   value="<%=company.getApprova_date() %>"/><a class="inputDateButton" href="javascript:;">选择</a>
						
					</dd>
				</dl>
				<dl>
				<dt>批准单位：</dt>
					<dd>
						<select name="approva_department" id="approva_department">
							<%=DictMgmt.getSelectObj(DictMgmt.DICT_COMPANY_APPROVE_DEPT,"",false,false,"-1", -1, null, null, null,-1,"") %>
						</select>
					</dd>
				</dl>
				<dl>
				<dt>企业资质图片：</dt>
					<dd>
					<%
				if("".equals(comForm.getApply_order_id())){
					%>
					<a class="button" href="<%=path %>/coremain/company/upload_img.jsp?type=1&companyid=<%=comForm.getCompany_info_id() %>&orderid=<%=comForm.getCompany_info_id() %>&operid=<%=comForm.getOperatorId() %>" 
									target="dialog" rel="dlg_page1" title="上传企业资质图片" width="745" height="570">
									<span id="span_img_1">上传企业资质图片</span></a>
						<%
				} 
				%>
						<a class="button" href="<%=path %>/coremain/company/show_img.jsp?type=1&companyid=<%=comForm.getCompany_info_id() %>&orderid=<%=comForm.getCompany_info_id() %>&operid=<%=comForm.getOperatorId() %>" 
									target="dialog" rel="dlg_page1" title="展示企业资质图片" width="745" height="570">
									<span id="show_img_1">展示企业资质图片</span></a>
									<input type="hidden" name="aptitude_file_no" id="aptitude_file_no" class="required" value="<%=company.getAptitude_file_no() %>" />
					</dd>
				</dl>
				<dl>
	<div class="divider"></div>
			
				<dl>
				<dt>营业执照注册号：</dt>
					<dd>
						<input type="text" name="busi_license_no" id="busi_license_no" class="required alphanumeric"  maxlength="18"   value="<%=company.getBusi_license_no() %>"/>
						
					<%
				if("".equals(comForm.getApply_order_id())){
					%>
					<a class="button" href="<%=path %>/coremain/company/upload_img.jsp?type=2&companyid=<%=comForm.getCompany_info_id() %>&orderid=<%=comForm.getCompany_info_id() %>&operid=<%=comForm.getOperatorId() %>"
									 target="dialog" rel="dlg_page1"   title="上传图片" width="745" height="570">
										<span id="span_img_2">上传图片</span></a>
						<%
				} 
				%>					
						<a class="button" href="<%=path %>/coremain/company/show_img.jsp?type=2&companyid=<%=comForm.getCompany_info_id() %>&orderid=<%=comForm.getCompany_info_id() %>&operid=<%=comForm.getOperatorId() %>"
									 target="dialog" rel="dlg_page1"   title="展示图片" width="745" height="570">
										<span id="show_img_2">展示图片</span></a>
									<input type="hidden" name="busi_license_file_no" id="busi_license_file_no" class="required" value="<%=company.getBusi_license_file_no() %>"/>
					</dd>
				</dl>
				<dl>
				<dt>组织机构代码号：</dt>
					<dd>
						 <input type="text" name="organization_no" id="organization_no"  maxlength="9"  class="alphanumeric"  value="<%=company.getOrganization_no() %>" />
					</dd>
				</dl>
				<dl>
				<dt>税务登记号：</dt>
					<dd>
						 <input type="text" name="tax_regist_no" id="tax_regist_no" maxlength="18" class="alphanumeric"  value="<%=company.getTax_regist_no() %>" />
					</dd>
				</dl>
				<dl>
				<dt>注册资本：</dt>
					<dd>
						 <input type="text" name="regist_capital" id="regist_capital" class="required digits" maxlength="16"  value="<%=company.getRegist_capital() %>" />(元)
						 <input type="hidden" name="real_capital" value="0"> 
					</dd>
				</dl> 
				<dl>
				<dt>开户银行：</dt>
					<dd>
						 <input type="text" name="open_bank" id="open_bank" maxlength="128"   value="<%=company.getOpen_bank() %>"   /> 
					</dd>
				</dl>
				<dl>
				<dt>银行帐号：</dt>
					<dd>
						 <input type="text" name="bank_account" id="bank_account"  maxlength="30" class="alphanumeric"  value="<%=company.getBank_account() %>"    /> 
					</dd>
				</dl>
				<dl>
				<dt>主营范围：</dt>
					<dd>
						 <textarea   name="main_range" id="main_range" rows="10" cols="100" class="required" ><%=company.getMain_range() %></textarea>
								<font color="silver" style="text-align:right;font-size:16px;">许可经营范围中必须含有房地产类经营范围，否则不予审批</font>
					</dd>
				</dl>
				<dl>
				<dt>兼营范围：</dt>
					<dd>
						 <textarea   name="and_range" id="and_range" rows="10" cols="100" ><%=company.getAnd_range() %></textarea>
					</dd>
				</dl>
				<dl>
				<dt>企业章程图片：</dt>
					<dd>
						
										<%
				if("".equals(comForm.getApply_order_id())){
					%>
					<a class="button" href="<%=path %>/coremain/company/upload_img.jsp?type=3&companyid=<%=comForm.getCompany_info_id() %>&orderid=<%=comForm.getCompany_info_id() %>&operid=<%=comForm.getOperatorId() %>" 
								target="dialog" rel="dlg_page1"  title="上传图片" width="745" height="570">
										<span id="span_img_3">上传图片</span></a>
						<%
				} 
				%>		
						 <a class="button" href="<%=path %>/coremain/company/show_img.jsp?type=3&companyid=<%=comForm.getCompany_info_id() %>&orderid=<%=comForm.getCompany_info_id() %>&operid=<%=comForm.getOperatorId() %>" 
								target="dialog" rel="dlg_page1"  title="展示图片" width="745" height="570">
										<span id="show_img_3">展示图片</span></a>
									<input type="hidden" name="company_articles_file_no" id="company_articles_file_no" class="required" value="<%=company.getCompany_articles_file_no() %>"/>
					</dd>
				</dl>
				<dl>
				<dt>企业章程：</dt>
					<dd>
						<textarea  name="company_articles" id="company_articles" rows="12" cols="100" ><%=company.getCompany_articles() %></textarea> 
					</dd>
				</dl>
				<dl>
				<dt>企业简介：</dt>
					<dd>
						<textarea  name="company_introduction" id="company_introduction" rows="12" cols="100" ><%=company.getCompany_introduction() %></textarea> 
					</dd>
				</dl>
				<dl>
				<dt>服务质量/服务收费制度：</dt>
					<dd>
										<%
				if("".equals(comForm.getApply_order_id())){
					%>
					<a class="button" href="<%=path %>/coremain/company/upload_img.jsp?type=4&companyid=<%=comForm.getCompany_info_id() %>&orderid=<%=comForm.getCompany_info_id() %>&operid=<%=comForm.getOperatorId() %>"
								 target="dialog" rel="dlg_page1"  title="上传服务质量/服务收费制度图片" width="745" height="570">
										<span id="span_img_4">上传服务质量/服务收费制度图片</span></a>
						<%
				} 
				%>	
						<a class="button" href="<%=path %>/coremain/company/show_img.jsp?type=4&companyid=<%=comForm.getCompany_info_id() %>&orderid=<%=comForm.getCompany_info_id() %>&operid=<%=comForm.getOperatorId() %>"
								 target="dialog" rel="dlg_page1"  title="展示服务质量/服务收费制度图片" width="745" height="570">
										<span id="show_img_4">展示服务质量/服务收费制度图片</span></a>
									<input type="hidden" name="service_info_file_no" id="service_info_file_no" class="required"   value="<%=company.getService_info_file_no() %>" />
					</dd>
				</dl>
<div class="divider"></div>		
		<h1>企业联系方式</h1>
				<dl>
				<dt>联系人：</dt>
					<dd>
						 <input type="text" name="contacts_name" id="contacts_name" class="required"  maxlength="15" value="<%=company.getContacts_name() %>"  />
					</dd>
				</dl>
				<dl>
				<dt>部门/职务：</dt>
					<dd>
						 <input type="text" name="dept" id="dept"   value="<%=company.getDept() %>" maxlength="15"  />
					</dd>
				</dl>
				<dl>
				<dt>电子邮箱：</dt>
					<dd>
						 <input type="text" name="email" id="email" class="email" value="<%=company.getEmail() %>"  maxlength="40" />
					</dd>
				</dl>
				<dl>
				<dt>企业网址：</dt>
					<dd>
						  <input type="text" name="company_website" id="company_website" class="url" maxlength="50" value="<%=company.getCompany_website() %>"  />
					</dd>
				</dl>
				<dl>
				<dt>手机：</dt>
					<dd>
						   <input type="text" name="msisdn" id="msisdn" class="digits" maxlength="11"  value="<%=company.getMsisdn() %>"   />
					</dd>
				</dl>
				<dl>
				<dt>联系电话：</dt>
					<dd>
						   <input type="text" name="contacts_no" id="contacts_no" maxlength="20"  class="required" value="<%=company.getContacts_no() %>"   />
					</dd>
				</dl>
				<dl>
				<dt>传真：</dt>
					<dd>
						   <input type="text" name="fax" id="fax"   value="<%=company.getFax() %>"  maxlength="50"   />
					</dd>
				</dl>
				<dl>
				<dt>邮编：</dt>
					<dd>
						   <input type="text" name="zip_code" id="zip_code" class="digits" maxlength="20"   value="<%=company.getZip_code() %>"  />
					</dd>
				</dl>
				<dl>
				<dt>办公地址：</dt>
					<dd>
						   <input type="text" name="office_addr" id="office_addr" class="required" maxlength="128"   value="<%=company.getOffice_addr() %>"  />
					</dd>
				</dl>
		
		
		 
	 </div>
	
	</form>
</div>
  </body>
</html>
