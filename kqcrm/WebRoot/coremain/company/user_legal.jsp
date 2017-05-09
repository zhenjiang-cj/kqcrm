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
	UserInfo user = new UserInfo();
	List<UserInfo> userList = (List<UserInfo>) request.getAttribute("userlist");//人员列表
	List<PicFileInfo> picsflist = (List<PicFileInfo>) request.getAttribute("picsflist");//图片列表
	List<PicFileInfo> picldlist = (List<PicFileInfo>) request.getAttribute("picldlist");//图片列表
	List<PicFileInfo> picrzlist = (List<PicFileInfo>) request.getAttribute("picrzlist");//图片列表
	List<PicFileInfo> picsblist = (List<PicFileInfo>) request.getAttribute("picsblist");//图片列表
	List<PicFileInfo> piczclist = (List<PicFileInfo>) request.getAttribute("piczclist");//图片列表
	List<UserResume> resumelist = (List<UserResume>) request.getAttribute("resumelist");//简历
	
	
	for(int i=0;i<userList.size();i++){
		UserInfo userinfo = userList.get(i);
		if(userinfo.getEmployee_type().equals("1")){
			//法人（董事长）
			user = userinfo;
		}
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
	var imgs=[];//不同类型上传的图片类型数量数组
	imgs[0]=0;
		jQuery(document).ready(function(){
	     	$('#sex').val(<%=user.getSex()%>);
	     	$('#nationality').val(<%=user.getNationality()%>);
	     	$('#station_grade').val(<%=user.getStation_grade()%>);
	     	$('#education').val(<%=user.getEducation()%>);
	     	$('#specialty').val(<%=user.getSpecialty()%>);
	     	var url = '<%=path%>/companyAction.do?method=toUserResume&employee_id=<%=comForm.getEmployee_id() %>';
	     	 $("#jbsxBox").loadUrl(url,{ data: {}, dialogId: '', callback: null });
	     	 
			var iContentH=$(window).height()-$("#header").height()-110;
			$("#div1").height(iContentH);
			$("#div2").height(iContentH);
			$("#navTab0").height(iContentH-100);
			$("#navTab2").height(iContentH-100);
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
	 
	</script>
  </head>
  
  <body>
    <div id="div1" style="width:80%; float:left;margin:0px;">
				<div class="pageContent sortDrag" selector="h1"  >

					<div id="navTab0" class="panel">
						<div class="pageContent">
							  <form class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone)" action="<%=path%>/companyAction.do?method=doUserLegalEdit" method="post" name="userForm">
							    <input type="hidden" name="company_info_id" id="company_info_id" value="<%=user.getCompany_info_id() %>">
							    <input type="hidden" name="employee_id" id="employee_id" value="<%=user.getEmployee_id() %>">
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
							    
							    <div class="pageFormContent nowrap"   >
							    <h1>法定代表人简况</h1>
							    	<p>
										<label>姓名：</label>
										<input name="employee_name" type="text" maxlength="10"  class="required" value="<%=user.getEmployee_name()==null?"":user.getEmployee_name() %>"/>
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
										<input type="text" name="both_no" class="date" readonly="true" value="<%=user.getBoth_no()==null?"":user.getBoth_no() %>" />
										<a class="inputDateButton" href="javascript:;">选择</a>
									</p>
									<p>
										<label>身份证：</label>
										<input type="hidden" name="card_type" id="card_type" value="1">
										<input type="text" name="card_no" id="card_no" maxlength="18" value="<%=user.getCard_no()==null?"":user.getCard_no() %>" class="required alphanumeric"  />
									</p>
									<p>
										<label>任职时间(开始)：</label>
										<input type="text" name="contract_start_date" id="contract_start_date" class="date" readonly="true"  value="<%=user.getContract_start_date()==null?"":user.getContract_start_date() %>" />
										<a class="inputDateButton" href="javascript:;">选择</a>
									</p>
									<p>
										<label>任职时间(结束)：</label>
										<input type="text" name="contract_end_date" id="contract_end_date" class="date" readonly="true" value="<%=user.getContract_end_date()==null?"":user.getContract_end_date() %>"/>
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
										<input type="text" name="work_years" id="work_years"  maxlength="2" max="99" class="required digits"  value="<%=user.getWork_years()==null?"":user.getWork_years() %>" />
									</p>
									<p>
										<label>电子邮箱：</label>
										<input type="text" name="email" id="email" maxlength="40"   class="email" />
									</p>
									<p>
										<label>学历情况：</label>
										<select   name="education" id="education" class="required">
											<%=DictMgmt.getSelectObj(DictMgmt.DICT_COMPANY_USER_EDU, "", false, false, "-1", -1, null, null, null, -1, "")%>
										</select>
									</p>
									<p>
										<label>个人电话：</label>
										<input type="text" name="phone_no" id="phone_no"  maxlength="20"  class="alphanumeric" value="<%=user.getPhone_no()==null?"":user.getPhone_no() %>"/>
									</p>
									<p>
										<label>专业情况：</label>
										<select   name="specialty" id="specialty" class="required">
											<%=DictMgmt.getSelectObj(DictMgmt.DICT_COMPANY_USER_SPECIALTY, "", false, false, "-1", -1, null, null, null, -1, "")%>
										</select>
									</p>
									<p>
										<label>移动电话：</label>
										<input type="text" name="mobile_phone" id="mobile_phone"  maxlength="11"  class="required digits"  value="<%=user.getMobile_phone()==null?"":user.getMobile_phone() %>" />
									</p>
									
									<p>
										<label>身份证图片：</label>
			<%
				if("".equals(comForm.getApply_order_id())){
					%>
										<a class="button" href="<%=path %>/coremain/company/upload_img.jsp?type=5&companyid=<%=comForm.getCompany_info_id() %>&orderid=<%=comForm.getEmployee_id() %>&operid=<%=comForm.getOperatorId() %>"
															 target="dialog" rel="dlg_page1"   title="身份证图片" width="745" height="570">
															<span id="span_img_5">上传</span></a>
						<%
				} 
				%>	
										<a class="button" href="<%=path %>/coremain/company/show_img.jsp?type=5&companyid=<%=comForm.getEmployee_id() %>&orderid=<%=comForm.getEmployee_id() %>&operid=<%=comForm.getOperatorId() %>" 
											target="dialog" rel="dlg_page1" title="展示企业资质图片" width="745" height="570">
											<span id="show_img_5">展示</span></a>
									</p>
									<p>
										<label>劳动合同图片：</label>
			<%
				if("".equals(comForm.getApply_order_id())){
					%>
										<a class="button" href="<%=path %>/coremain/company/upload_img.jsp?type=6&companyid=<%=comForm.getCompany_info_id() %>&orderid=<%=comForm.getEmployee_id() %>&operid=<%=comForm.getOperatorId() %>"
															 target="dialog" rel="dlg_page1"   title="劳动合同图片" width="745" height="570">
															<span id="span_img_6">上传</span></a>
						<%
				} 
				%>	
										<a class="button" href="<%=path %>/coremain/company/show_img.jsp?type=6&companyid=<%=comForm.getEmployee_id() %>&orderid=<%=comForm.getEmployee_id() %>&operid=<%=comForm.getOperatorId() %>" 
											target="dialog" rel="dlg_page1" title="展示劳动合同图片" width="745" height="570">
											<span id="show_img_6">展示</span></a>
									</p>
									<p>
										<label>任职文件图片：</label>
			<%
				if("".equals(comForm.getApply_order_id())){
					%>
										<a class="button" href="<%=path %>/coremain/company/upload_img.jsp?type=7&companyid=<%=comForm.getCompany_info_id() %>&orderid=<%=comForm.getEmployee_id() %>&operid=<%=comForm.getOperatorId() %>"
															 target="dialog" rel="dlg_page1"   title="任职文件图片" width="745" height="570">
															<span id="span_img_7">上传</span></a>
						<%
				} 
				%>	
										<a class="button" href="<%=path %>/coremain/company/show_img.jsp?type=7&companyid=<%=comForm.getEmployee_id() %>&orderid=<%=comForm.getEmployee_id() %>&operid=<%=comForm.getOperatorId() %>" 
											target="dialog" rel="dlg_page1" title="展示任职文件图片" width="745" height="570">
											<span id="show_img_7">展示</span></a>
									</p>
									<p>
										<label>社保证明图片：</label>
			<%
				if("".equals(comForm.getApply_order_id())){
					%>
										<a class="button" href="<%=path %>/coremain/company/upload_img.jsp?type=8&companyid=<%=comForm.getCompany_info_id() %>&orderid=<%=comForm.getEmployee_id() %>&operid=<%=comForm.getOperatorId() %>"
															 target="dialog" rel="dlg_page1"   title="社保证明图片" width="745" height="570">
															<span id="span_img_8">上传</span></a>
						<%
				} 
				%>	
										<a class="button" href="<%=path %>/coremain/company/show_img.jsp?type=8&companyid=<%=comForm.getEmployee_id() %>&orderid=<%=comForm.getEmployee_id() %>&operid=<%=comForm.getOperatorId() %>" 
											target="dialog" rel="dlg_page1" title="展示社保证明图片" width="745" height="570">
											<span id="show_img_8">展示</span></a>
									</p>
									<p>
										<label>职称证书图片：</label>
			<%
				if("".equals(comForm.getApply_order_id())){
					%>
										<a class="button" href="<%=path %>/coremain/company/upload_img.jsp?type=9&companyid=<%=comForm.getCompany_info_id() %>&orderid=<%=comForm.getEmployee_id() %>&operid=<%=comForm.getOperatorId() %>"
															 target="dialog" rel="dlg_page1"   title="职称证书图片" width="745" height="570">
															<span id="span_img_9">上传</span></a>
						<%
				} 
				%>	
										<a class="button" href="<%=path %>/coremain/company/show_img.jsp?type=9&companyid=<%=comForm.getEmployee_id() %>&orderid=<%=comForm.getEmployee_id() %>&operid=<%=comForm.getOperatorId() %>" 
											target="dialog" rel="dlg_page1" title="展示职称证书图片" width="745" height="570">
											<span id="show_img_9">展示</span></a>
									</p>
									
									
									<div class="divider"></div>
									<label>备注：</label><textarea   name="remark" id=remark rows="10" cols="100" style="width:60%;height:100px"><%=user.getRemark()==null?"":user.getRemark() %></textarea>
									<div class="divider"></div>
											
								</div>
							</form>
							<!-- 简历管理 -->
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
											</div>
						</div>
					</div>

				</div>
	</div>
    <div id="div2" style="width:19%; float:left;margin-left:10px;margin-top:0px" >
    	<div id="navTab2" class="panel"  style="overflow-y:auto;overflow-x:hidden;position:relative;">
						<h1>
							相关证件
						</h1>
						<div  >
						
						<%
							if(picsflist!=null&&picsflist.size()>0){
								for(int i=0;i<picsflist.size();i++){
									PicFileInfo pics = picsflist.get(i);
									%>
									<img  src="<%=path%>/companyAction.do?method=showPhotoById&file_id=<%=pics.getFile_id() %>&r=<%=Math.random() %>" width="200" height="200" /><br>
									<a id="del_img" onclick="del_img('<%=pics.getFile_id() %>')">删除图片</a> 
									<br>
									<%
								}
							}
						if(picldlist!=null&&picldlist.size()>0){
							for(int i=0;i<picldlist.size();i++){
								PicFileInfo picld = picldlist.get(i);
								%>
								<img  src="<%=path%>/companyAction.do?method=showPhotoById&file_id=<%=picld.getFile_id() %>&r=<%=Math.random() %>" width="200" height="200" /><br>
								<a id="del_img" onclick="del_img('<%=picld.getFile_id() %>')">删除图片</a> 
								<br>
								<%
							}
						}
						if(picrzlist!=null&&picrzlist.size()>0){
							for(int i=0;i<picrzlist.size();i++){
								PicFileInfo picrz = picrzlist.get(i);
								%>
								<img  src="<%=path%>/companyAction.do?method=showPhotoById&file_id=<%=picrz.getFile_id() %>&r=<%=Math.random() %>" width="200" height="200" /><br>
								<a id="del_img" onclick="del_img('<%=picrz.getFile_id() %>')">删除图片</a> 
								<br>
								<%
							}
						}
						if(picsblist!=null&&picsblist.size()>0){
							for(int i=0;i<picsblist.size();i++){
								PicFileInfo picsb = picsblist.get(i);
								%>
								<img  src="<%=path%>/companyAction.do?method=showPhotoById&file_id=<%=picsb.getFile_id() %>&r=<%=Math.random() %>" width="200" height="200" /><br>
								<a id="del_img" onclick="del_img('<%=picsb.getFile_id() %>')">删除图片</a> 
								<br>
								<%
							}
						}
						if(piczclist!=null&&piczclist.size()>0){
							for(int i=0;i<piczclist.size();i++){
								PicFileInfo piczc = piczclist.get(i);
								%>
								<img  src="<%=path%>/companyAction.do?method=showPhotoById&file_id=<%=piczc.getFile_id() %>&r=<%=Math.random() %>" width="200" height="200" /><br>
								<a id="del_img" onclick="del_img('<%=piczc.getFile_id() %>')">删除图片</a> 
								<br>
								<%
							}
						}
						
						
						%>
							
						</div>
					</div>
    </div>
	
  </body>
  <script type="text/javascript">
  
	
function del_img(file_id)
{
	try{
		$.ajax({
			    type:"post",
				dataType:"json",
				contentType:"application/json;charset=UTF-8",
				url:'<%=path%>/companyAction.do?method=delImgById&file_id='+file_id,
				//url:path+"/index/go4gDataPopularity.do?in_stat_date="+iframe_sum_date+"&grid_id="+grid_id+"&partition_id="+partition_id+"&county_id="+county_id
			    success:function(data){
			    	alert("删除图片成功!");
			    	navTab.reload("<%=path%>/companyAction.do?method=toUsermanage", null);
			    },
			    error:function (data){
			    	alert("删除图片失败!");
			    }
			});
	}catch(e){
		alert(e);
	}

}
  </script>
</html>
