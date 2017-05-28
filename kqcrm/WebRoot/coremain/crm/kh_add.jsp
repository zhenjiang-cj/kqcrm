<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.portal.dt.*"%>
<%@ page import="com.nl.portal.actionForm.*"%>
<%@page import="java.util.List"%>
<%@ page import="com.nl.util.GlobalConst"%>
<%@page import="com.nl.util.SessionConst"%>
<%@page import="com.nl.util.SessionData"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	CrmForm userform = (CrmForm) request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
	
	SessionData sessionData = (SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
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
	$(function(){
		getprovinces();
		
	});
	function getprovinces(){
		try{
			$.ajax({
			    type:"post",
				dataType:"json",
				contentType:"application/json;charset=UTF-8",
				url:'<%=path%>/coremain/portal/user/getproveinces.jsp',
			    success:function(data){
		            if(null!=data){
	                    jQuery("#provinces",navTab.getCurrentPanel()).html(data[0].cityMap);
	                    provincesChange();
		            }else{
		                alert("没有获取省信息");
		            }
			    },
			    error:function (data){
			        alert("获取省信息失败！");
			    }
			});
		}catch(e){
			alert(e);
		}
	}
function provincesChange() {
    jQuery("#city",navTab.getCurrentPanel()).html('');
    jQuery("#region",navTab.getCurrentPanel()).html('');
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
                    jQuery("#city",navTab.getCurrentPanel()).html(data[0].cityMap);
                    cityChange();
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
    jQuery("#region",navTab.getCurrentPanel()).html('');
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
                    jQuery("#region",navTab.getCurrentPanel()).html(data[0].cityMap);
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
  <form class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)" action="<%=path%>/crmAction.do?method=doKhAdd" method="post" name="userForm">
    <input type="hidden" name="kh_id" id="kh_id" value="<%=userform.getKh_id() %>">
    <input type="hidden" name="operatorId" id="operatorId" value="<%=userform.getOperatorId() %>">
    
    
    
    <div class="pageFormContent nowrap" layoutH="56" style="height:100px">

		<table class="TabList">                   	                  
	                  <tr style="margin-top:20px;">
	                      <td class="tit">用户名称：</td>
	                      <td style="text-align:left;">
	                         <input name="kh_name"  id="kh_name" type="text"  maxlength="30"  class="required" />
	                      </td>
	                      <td class="tit03">省份：</td>
	                      <td style="text-align:left;">
	                          <select  name="provinces" id="provinces" class="required" onchange="provincesChange();">
															<option value="">--请选择--</option>
															<%=DictMgmt.getSelectObj(DictMgmt.DICT_KQ_PROVINCES,"",false,false,"-1", -1, null, null, null,-1,"") %>
														</select>
	                      </td>
	                  </tr>
	                  <tr style="margin-top:20px;">
	                      <td class="tit">地市：</td>
	                      <td style="text-align:left;">
	                          <select  name="city" id="city" onchange="cityChange();" class="required" >
															<option value="">--请选择--</option>
															<%=DictMgmt.getSelectObj(DictMgmt.DICT_KQ_CITY,"",false,false,"-1", -1, null, null, null,-1,"") %>
														</select>
	                      </td>
	                      <td class="tit">县区：</td>
	                      <td style="text-align:left;">
	                          <select  name="region" id="region" class="required"  >
															<option value="">--请选择--</option>
															<%=DictMgmt.getSelectObj(DictMgmt.DICT_kq_REGION,"",false,false,"-1", -1, null, null, null,-1,"") %>
														</select>
	                      </td>
	                  </tr>
	                  <tr style="margin-top:20px;">
	                      <td class="tit">手机号码1：</td>
	                      <td style="text-align:left;">
	                          <input name="kh_phone1" id="kh_phone1" type="text"  maxlength="11"  class="required digits"  />
	                      </td>
	                      <td class="tit">手机号码2：</td>
	                      <td style="text-align:left;">
	                          <input name="kh_phone2" id="kh_phone2" type="text"  maxlength="11"  class="digits"  />
	                      </td>
	                  </tr>
	                  <tr style="margin-top:20px;">
	                      <td class="tit">身份证号：</td>
	                      <td style="text-align:left;">
	                          <input name="kh_card" id="kh_card" type="text"  maxlength="18"  class="alphanumeric"  />
	                      </td>
	                      <td class="tit">转介绍人(姓名+手机号码)：</td>
	                      <td style="text-align:left;">
	                          <input name="introduce_name" id="introduce_name" type="text"  maxlength="20"     />
	                      </td>
	                  </tr>
					  <tr style="margin-top:20px;">
	                      <td class="tit">家庭住址：</td>
	                      <td style="text-align:left;" colspan="3">
	                          <input name="kh_addr" id ="kh_addr" type="text"  maxlength="100"  class="required" size="60"/>
	                      </td>
	      
	                  </tr>
	            </table>
		 
		
	</div>
	
	<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
    </div>
    
	</form>
	</div>
  </body>
</html>
