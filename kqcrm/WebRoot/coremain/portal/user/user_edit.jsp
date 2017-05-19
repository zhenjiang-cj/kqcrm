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
	
	<script src="<%=path%>/dwz/js/jquery.validate.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(function(){
		jQuery("#provinces").val('<%=user.getProvinces()  %>');
		jQuery("#city").val('<%=user.getCity()  %>');
		jQuery("#region").val('<%=user.getRegion()  %>');	
		
	});


function provincesChange() {
    jQuery("#city").html('');
    jQuery("#region").html('');
    var provinces = "";
    provinces=document.getElementById("provinces").value;
    if(provinces==''){
        provinces='';
    }
    try{
		$.ajax({
		    type:"post",
			dataType:"json",
			contentType:"application/json;charset=UTF-8",
			url:'<%=path%>/coremain/portal/user/provincesChange.jsp?provinces='+provinces,
		    success:function(data){
	            if(null!=data){
                    jQuery("#city").html(data[0].cityMap);
	            }else{
	                alert("没有获取地市信息");
	            }
		    },
		    error:function (data){
		        alert("获取地市信息信息失败！");
		    }
		});
	}catch(e){
		alert(e);
	}
}

function cityChange() {
    jQuery("#region").html('');
    var provinces =  document.getElementById("provinces").value;
    if(provinces==''){
        provinces='';
    }
    var city =document.getElementById("city").value;
    if(city==''){
        city='';
    }
    try{
		$.ajax({
		    type:"post",
			dataType:"json",
			contentType:"application/json;charset=UTF-8",
			url:'<%=path%>/coremain/portal/user/cityChange.jsp?provinces='+provinces+'&city='+city,
		    success:function(data){
	             if(null!=data){
                    jQuery("#region").html(data[0].cityMap);
	            }else{
	                alert("没有获取区域信息");
	            }
		    },
		    error:function (data){
		        alert("获取区域信息失败！");
		    }
		});
	}catch(e){
		alert(e);
	}
}
	</script>
	
  </head>
  
  <body>
    <div class="pageContent">
  <form class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)" action="<%=path%>/userAction.do?method=doUserEdit" method="post" name="userForm">
    <input type="hidden" name="sno" id="sno" value="<%=user.getSno() %>">
    <input type="hidden" name="operatorId" id="operatorId" value="<%=userform.getOperatorId() %>">
    
    <div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
    </div>
    
    <div class="pageFormContent nowrap" layoutH="56" style="height:100px">
    <h1>用户修改</h1>
		 
		<p>
			<label>用户帐号：</label>
			<input name="user_id" type="text"   maxlength="10"  class="required" value="<%=user.getUser_id() %>" readonly/>
		</p>
		<p>
			<label>用户名称：</label>
			<input name="user_name" type="text"  maxlength="20"  class="required"  value="<%=user.getUser_name() %>" />
		</p> 
		<p>
			<label>省份：</label>
			<select  name="provinces" id="provinces" class="required" onchange="provincesChange();">
				<option value="">--请选择--</option>
				<%=DictMgmt.getSelectObj(DictMgmt.DICT_KQ_PROVINCES,"",false,false,"-1", -1, null, null, null,-1,"") %>
			</select>
		</p>
		<p>
			<label>地市：</label>
			<select  name="city" id="city" onchange="cityChange();">
				<option value="">--请选择--</option>
				<%=DictMgmt.getSelectObj(DictMgmt.DICT_KQ_CITY,"",false,false,"-1", -1, null, null, null,-1,"") %>
			</select>
		</p>
		<p>
			<label>县区：</label>
			<select  name="region" id="region" >
				<option value="">--请选择--</option>
				<%=DictMgmt.getSelectObj(DictMgmt.DICT_kq_REGION,"",false,false,"-1", -1, null, null, null,-1,"") %>
			</select>
		</p>
		<p>
			<label>手机号码：</label>
			<input name="msisdn" type="text"  maxlength="11"  class="digits"  value="<%=user.getMsisdn() %>"  />
		</p>
		<p>
			<label>email：</label>
			<input name="email" type="text"  maxlength="40"  class="email"  value="<%=user.getEmail() %>" />
		</p>
		 
		
	</div>
	</form>
	</div>
  </body>
</html>
