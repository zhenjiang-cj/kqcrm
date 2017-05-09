<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.portal.dt.*"%>
<%@ page import="com.nl.portal.actionForm.*"%>
<%@page import="java.util.List"%>
<%@ page import="com.nl.util.GlobalConst"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	UserForm userform = (UserForm) request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
	List<UserInfo> userlist =  (List<UserInfo>) request.getAttribute("userlist");
	UserInfo user = userlist.get(0);
	List<UserInfo> rolelist =  (List<UserInfo>) request.getAttribute("rolelist");
	List<UserInfo> userrolelist =  (List<UserInfo>) request.getAttribute("userrolelist");
	
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
	<link href="<%=path%>/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="<%=path%>/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="<%=path%>/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
	<link href="<%=path%>/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
	
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
	     // document.getElementById("span_img_5").innerHTML='上传企业资质图片'+queueData.uploadsSuccessful;
	      
	    if (queueData.uploadsErrored) {  
	//      alertMsg.error(msg);  
	    } else {  
	//      alertMsg.correct(msg);  
	    }  
	}
	
	
    function changeObject(s,t){ 
		var objs="#"+s;  
		var objt="#"+t;  
		jQuery("option:selected",objs).clone().appendTo(objt);  
		jQuery("option:selected",objs).remove(); 
		var str = "";
		jQuery("#chooseRole").children().each(function(){
			str += jQuery(this).val()+",";
		});
		str = str.substring(0,str.length-1);
    }
    
    //将java查询到的
    	var roles = [];
    	var user_roles = [];
    	<% if(rolelist!=null&&rolelist.size()>0){
    		for(int i=0;i<rolelist.size();i++){
    			UserInfo role = rolelist.get(i);
    			String role_id = role.getRole_id();
    			String role_name = role.getRole_name();
    			String sysid = role.getSysid();
    			%>
    			var role= {
								'role_id':'<%=role_id %>',
								'role_name':'<%=role_name %>',
								'sysid':'<%=sysid %>' 
							};
				roles[<%=i %>] = role;
				
    		<%
    		}
    	}%>
    	
    	<% if(userrolelist!=null&&userrolelist.size()>0){
    		for(int i=0;i<userrolelist.size();i++){
    			UserInfo userrole = rolelist.get(i);
    			String role_id = userrole.getRole_id();
    			String role_name = userrole.getRole_name();
    			String sysid = userrole.getSysid();
    			%>
    			var userrole= {
								'role_id':'<%=role_id %>',
								'role_name':'<%=role_name %>',
								'sysid':'<%=sysid %>' 
							};
				user_roles[<%=i %>] = userrole;
				
    		<%
    		}
    	}%>
    	
    	
    function changeSys()
    {
         jQuery("option:selected","#allRole").remove(); 
         jQuery("option:selected","#chooseRole").remove();
         var sysid = jQuery("#sysid").val();
         //加载select
         var option ='';
         for(var m=0;m<roles.length;m++){
         	var role = roles[m];
         	var role_id = role.role_id;
         	var role_name = role.role_name;
         	var sys_id = role.sysid;
         	if(sysid==sys_id){
         		option = option + "<option value='"+role_id+"'>"+role_name+"</option>";
         	}
         	
         }
         jQuery("#allRole").html(option);
         
         var user_option ='';
         for(var m=0;m<user_roles.length;m++){
         	var userrole = user_roles[m];
         	var role_id1 = userrole.role_id;
         	var role_name1 = userrole.role_name;
         	var sys_id1 = userrole.sysid;
         	if(sysid==sys_id1){
         		user_option = user_option + "<option value='"+role_id1+"'>"+role_name1+"</option>";
         	}
         	
         }
         jQuery("#chooseRole").html(user_option);
         
    }

	</script>
	
  </head>
  
  <body>
    <div class="pageContent">
  <form class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)" action="<%=path%>/userAction.do?method=doUserRole" method="post" name="userForm">
    <input type="hidden" name="sno" id="sno" value="<%=user.getSno() %>">
    <input type="hidden" name="operatorId" id="operatorId" value="<%=userform.getOperatorId() %>">
    
    <div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
    </div>
    <div class="panel collapse" defH="150">
		<h1>用户信息</h1>
		<div>
			<p>用户帐号:<%=user.getUser_id() %></p>
			<p>用户名称:<%=user.getUser_name() %></p>
			<p>手机号码:<%=user.getMsisdn() %></p>
			<p>email:<%=user.getEmail() %></p>
		</div>
	</div>
	
	<div class="panel collapse" defH="150">
		<h1>角色信息</h1>
		<div>
			<table class="list" width="98%">
				<tbody>
					<tr>
						<td>归属功能</td>
						<td>
							<select id="sysid" name="sysid" class="required" onchange="changeSys()">
							        <%=DictMgmt.getSelectObj(DictMgmt.DICT_SYS_SYSTEM_ID,"",false,false,"-1", -1, null, null, null,-1,"") %>
						    </select><font class="Red">*</font>
					    </td>
					</tr>
				</tbody>
			</table>
			
			<table class="list" width="98%">
				<tbody> 
					<tr>
						<td >分配角色</td>
						<td >设置</td>
						<td >可用角色</td>
					</tr>
					<tr>
					<td>
					     <select multiple="multiple" name="allRole" id="allRole" class="selOp" style="width:300px;height:150px"   
				               ondblclick="changeObject(this.id,'chooseRole');">  
				               <% 
				                   for (int i=0; i<rolelist.size(); i++)
				                   {
				                       UserInfo role = (UserInfo)rolelist.get(i);
				               %>
				               <option value="<%=role.getRole_id() %>">
				                   <%=role.getRole_name()%>
				               </option>  
				               <%
				                   }
				               %>       
				         </select> 
					</td>
					<td>				
						<div class="Divtr"><input name=""  type="button" value="增加 >>"  class="bntSty" onclick="changeObject('allRole','chooseRole')"/></div>
					    <div class="Divtr"><input name=""  type="button" value="<< 删除"  class="bntSty" onclick="changeObject('chooseRole','allRole')"/></div>
				    </td>
					<td>
					   <select multiple="multiple" name="chooseRole" id="chooseRole"  class="selOp" style="width:300px;height:150px" 
                          ondblclick="changeObject(this.id,'allRole');">  
                          <% 
                          	if (userrolelist!=null&&userrolelist.size()>0){
			                   for (int i=0; i<userrolelist.size(); i++)
			                   {
			                       UserInfo userrole = (UserInfo)userrolelist.get(i);
			               %>
			               <option value="<%=userrole.getRole_id() %>">
			                   <%=userrole.getRole_name()%>
			               </option>  
			               <%
			                   }
                          	}
			               %>  
                       </select>
					</td>
				  </tr>
				</tbody>
			</table>
		</div>
	</div>
     
	</form>
	</div>
  </body>
</html>
