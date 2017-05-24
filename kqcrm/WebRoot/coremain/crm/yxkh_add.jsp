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
	CrmForm userform = (CrmForm) request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
	
	SessionData sessionData = (SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>意向客户录入</title>
    
	<link type="text/css" rel= "stylesheet" href="<%=path%>/css/content.css" >
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
 
	<script src="<%=path%>/dwz/js/jquery.validate.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(function(){
		jQuery("#provinces").val('<%=sessionData.getProvinces()  %>');
		jQuery("#city").val('<%=sessionData.getCity()  %>');
		jQuery("#region").val('<%=sessionData.getRegion()  %>');
		checkuser();
		//setTimeout(checkuser, 1000);
		
	});
	//根据用户归属，确定能新增客户的归属
	function checkuser(){
		var org_level = '<%=sessionData.getOrg_level()  %>';
		if(org_level=='1'){
			var p_name = $("#provinces").find("option:selected").text();
			var p_value = $("#provinces").val();
			$("#provinces").empty();
			$("#provinces").html('<option value="'+p_value+'">'+p_name+'</option>');
			provincesChange();
		}else if(org_level=='2'){
			var p_name = $("#provinces").find("option:selected").text();
			var p_value = $("#provinces").val();
			$("#provinces").empty();
			$("#provinces").html('<option value="'+p_value+'">'+p_name+'</option>');
			var c_name = $("#city").find("option:selected").text();
			var c_value = $("#city").val();
			$("#city").empty();
			$("#city").html('<option value="'+c_value+'">'+c_name+'</option>');
			cityChange();
		}else{
			var p_name = $("#provinces").find("option:selected").text();
			var p_value = $("#provinces").val();
			$("#provinces").empty();
			$("#provinces").html('<option value="'+p_value+'">'+p_name+'</option>');
			var c_name = $("#city").find("option:selected").text();
			var c_value = $("#city").val();
			$("#city").empty();
			$("#city").html('<option value="'+c_value+'">'+c_name+'</option>');
			var r_name = $("#region").find("option:selected").text();
			var r_value = $("#region").val();
			$("#region").empty();
			$("#region").html('<option value="'+r_value+'">'+r_name+'</option>');
		}
	}
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
  <form class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)" action="<%=path%>/crmAction.do?method=doYxkhAdd" method="post" name="userForm">
    <input type="hidden" name="kh_id" id="kh_id" value="<%=userform.getKh_id() %>">
    
    
    
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
	                      <td class="tit">渠道来源：</td>
	                      <td style="text-align:left;">
	                          <input name="channel_source" id="channel_source" type="text"  maxlength="80" />
	                      </td>
	                      <td class="tit">是否安装：</td>
	                      <td style="text-align:left;">
												<select  name="is_install" id="is_install" class="required"  >
																<option value="0">未安装</option>
																<option value="1">已安装</option>
															</select>
	                      </td>
	                  </tr>
					  <tr style="margin-top:20px;">
	                      <td class="tit">家庭住址：</td>
	                      <td style="text-align:left;" colspan="3">
	                          <input name="kh_addr" id ="kh_addr" type="text"  maxlength="100"  class="required" size="60"/>
	                      </td>
	      
	                  </tr>

	                  <tr style="margin-top:20px;">
	                      <td class="tit">备注：</td>
	                      <td colspan=3 style="text-align:left;">
	                          <textarea name="remark" cols="80" rows="2" class="required textInput" style="margin: 0px; height: 350px; width: 100%;"></textarea>
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
