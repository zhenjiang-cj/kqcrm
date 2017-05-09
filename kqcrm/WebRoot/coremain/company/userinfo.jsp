<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@ page import="com.nl.company.actionForm.*"%>
<%@ page import="com.nl.company.dt.*"%>
<%@ page import="com.nl.util.GlobalConst"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
CompanyForm comForm = (CompanyForm) request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
String company_info_id = comForm.getCompany_info_id();

List<UserInfo> userList = (List<UserInfo>) request.getAttribute("userlist");//人员列表

UserInfo userlegal = null;//法人代表
UserInfo usermanage = null;//总经理
UserInfo user_pro = null;//工程
UserInfo user_cw = null;//财务
UserInfo user_jy = null;//经营
UserInfo user_tj = null;//统计
int cnt_all=0;
int cnt_high=0;
int cnt_middle=0;
int cnt_low=0;

if(userList!=null&&userList.size()>0){
	for(int i =0;i<userList.size();i++){
		UserInfo user = userList.get(i);
		if(user.getEmployee_type().equals("1")){
			userlegal = user;
		}
		if(user.getEmployee_type().equals("2")){
			usermanage = user;
		}
		if(user.getEmployee_type().equals("3")&&user.getStation().equals("1")){
			user_pro = user;
		}
		if(user.getEmployee_type().equals("3")&&user.getStation().equals("2")){
			user_cw = user;
		}
		if(user.getEmployee_type().equals("3")&&user.getStation().equals("3")){
			user_jy = user;
		}
		if(user.getEmployee_type().equals("3")&&user.getStation().equals("4")){
			user_tj = user;
		}
		if(user.getEmployee_type().equals("3")){
			cnt_all = cnt_all+1;
		}
		if(user.getEmployee_type().equals("3")&&user.getStation_grade().equals("3")){
			cnt_low = cnt_low+1;
		}
		if(user.getEmployee_type().equals("3")&&user.getStation_grade().equals("2")){
			cnt_middle = cnt_middle+1;
		}
		if(user.getEmployee_type().equals("3")&&user.getStation_grade().equals("1")){
			cnt_high = cnt_high+1;
		}
	}
}




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
	jQuery(document).ready(function(){
			var iContentH=$(window).height()-$("#header").height()-110;
			//$("#div1").height(iContentH-100);
			//$("#navTab0").height(iContentH-100);
			$("#div_pages").height(iContentH-100);
		});
	</script>
  </head>
  
  <body>
  
  
  <form id="pagerForm" method="post" action="<%=path%>/companyAction.do?method=toUserinfo">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="20" />
</form>

  
  	<div class="pageContent" selector="h1" id="div_pages" >
		 <div class="tabs" currentIndex="0" eventType="click">
			<div class="tabsHeader">
				<div class="tabsHeaderContent">
					<ul>
						<li><a href="javascript:;"><span>人员汇总</span></a></li>
						<li><a href="<%=path%>/companyAction.do?method=viewUserlegal&company_info_id=<%=company_info_id %>" class="j-ajax"><span>法定代表人</span></a></li>
						<li><a href="<%=path%>/companyAction.do?method=viewUsermanage&company_info_id=<%=company_info_id %>" class="j-ajax"><span>总经理</span></a></li>
						<li><a href="<%=path%>/companyAction.do?method=viewUserdept&company_info_id=<%=company_info_id %>" class="j-ajax"><span>部门负责人</span></a></li>
						<li><a href="<%=path%>/companyAction.do?method=viewUseron&company_info_id=<%=company_info_id %>" class="j-ajax"><span>专业人员</span></a></li>
					</ul>
				</div>
			</div>
			<div class="tabsContent"  >
			<!-- 人员汇总 -->
				<div class="searchBar">
					<!-- 人员汇总基本信息begin -->
					<table class="searchContent">
						<tr>
							<td>
								法定代表人： <%= userlegal==null?"":userlegal.getEmployee_name() %>
							</td>
						</tr>
						<tr>
							<td>
								总经理： <%= usermanage==null?"":usermanage.getEmployee_name() %>
							</td>
						</tr>
						<tr>
							<td>
								部门负责人： 
								<font color="gray">工程</font>&nbsp;&nbsp;<%= user_pro==null?"无":user_pro.getEmployee_name() %>
								<font color="gray">财务</font>&nbsp;&nbsp;<%= user_cw==null?"无":user_cw.getEmployee_name() %>
								<font color="gray">经营</font>&nbsp;&nbsp;<%= user_jy==null?"无":user_jy.getEmployee_name() %>
								<font color="gray">统计</font>&nbsp;&nbsp;<%= user_tj==null?"无":user_tj.getEmployee_name() %>
							</td>
						</tr>
						<tr>
							<td>
								有职称人员： 共<%= cnt_all %>人
								<font color="gray">高级职称</font>&nbsp;&nbsp; <%= cnt_high %>人 
								<font color="gray">中级职称</font>&nbsp;&nbsp; <%= cnt_middle %>人 
								<font color="gray">初级职称</font>&nbsp;&nbsp; <%= cnt_low %>人 
							</td>
						</tr>
					</table>
					<!-- 人员汇总基本信息end -->	
					<!-- 人员汇总报表begin -->	
					<table class="table" width="100%"  >
						<thead>
							<tr>
								<th width="5%">序号</th>
								<th width="20%">姓名</th>
								<th width="20%">职务</th>
								<th width="20%">学历</th>
								<th width="20%">职称等级/专业</th>
								<th width="15%" align="center">岗位</th>
							</tr>
						</thead>
						<tbody>
						<%
							if( userList!=null && userList.size()>0  ){
								for(int i=0;i<userList.size();i++){
									UserInfo user = userList.get(i);
								%>
									<tr target="sid_user" rel="1">
										<td><%=i %></td>
										<td><%=user.getEmployee_name() %></td>
										<td><%=DictMgmt.getValueDescs(DictMgmt.DICT_COMPANY_USER_POST,user.getPost(),"" )%>  </td>
										<td><%=DictMgmt.getValueDescs(DictMgmt.DICT_COMPANY_USER_EDU,user.getEducation(),"" )%>  </td>
										<td><%=DictMgmt.getValueDescs(DictMgmt.DICT_COMPANY_USER_STA_GRADE,user.getStation_grade(),"" )%>  </td>
										<td><%=DictMgmt.getValueDescs(DictMgmt.DICT_COMPANY_USER_STATION,user.getStation(),"" )%>  </td>
									</tr>
								<% 
								}
							}
						
						%>
							 
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
							<span>条，共${pager.totalCount}条</span>
						</div>
						<div class="pagination" targetType="navTab" totalCount="${pager.totalCount}" numPerPage="${pager.numPerPage}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
					</div>
							
					
					<!-- 人员汇总报表end -->
				</div>
				<!-- 法定代表人 -->
				<div></div>
				<!-- 总经理 -->
				<div></div>
				<!-- 部门负责人 -->
				<div></div>
				<!-- 专业人员 -->
				<div></div>
			</div>
			<div class="tabsFooter">
				<div class="tabsFooterContent"></div>
			</div>
		</div>
	 </div>
	
  </body>
</html>
