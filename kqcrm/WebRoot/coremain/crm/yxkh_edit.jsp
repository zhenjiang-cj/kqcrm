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
	List<CrmInfo> userlist =  (List<CrmInfo>) request.getAttribute("userlist");
	CrmInfo user = userlist.get(0);
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
		getprovinces();
		setTimeout(function(){
				jQuery("#provinces").val('<%=user.getProvinces()  %>');
				jQuery("#city").val('<%=user.getCity()  %>');
				jQuery("#region").val('<%=user.getRegion()  %>');
				jQuery("#is_install").val('<%=user.getIs_install()  %>');
			},1000);
		
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
	                    jQuery("#provinces").html(data[0].cityMap);
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
  <form class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)" action="<%=path%>/crmAction.do?method=doYxkhEdit" method="post" name="userForm">
    <input type="hidden" name="kh_id" id="kh_id" value="<%=user.getKh_id() %>">
    <input type="hidden" name="operatorId" id="operatorId" value="<%=userform.getOperatorId() %>">
    
    <div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
    </div>
    
    <div class="pageFormContent nowrap" layoutH="56" style="height:100px">
    <h1>客户新增</h1>
		<p>
			<label>用户名称：</label>
			<input name="kh_name"  id="kh_name" type="text"  maxlength="30"  class="required" value="<%=user.getKh_name() %>"/>
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
			<select  name="city" id="city" onchange="cityChange();" class="required" >
				<option value="">--请选择--</option>
				<%=DictMgmt.getSelectObj(DictMgmt.DICT_KQ_CITY,"",false,false,"-1", -1, null, null, null,-1,"") %>
			</select>
		</p>
		<p>
			<label>县区：</label>
			<select  name="region" id="region" class="required"  >
				<option value="">--请选择--</option>
				<%=DictMgmt.getSelectObj(DictMgmt.DICT_kq_REGION,"",false,false,"-1", -1, null, null, null,-1,"") %>
			</select>
		</p>
		<dl>
		<dt>家庭住址：</dt>
			<dd>
			<input name="kh_addr" id ="kh_addr" type="text"  maxlength="100"  class="required" size="60"  value="<%=user.getKh_addr() %>"/>
			</dd>
		</dl> 
		<p>
			<label>手机号码1：</label>
			<input name="kh_phone1" id="kh_phone1" type="text"  maxlength="11"  class="required digits"   value="<%=user.getKh_phone1() %>"/>
		</p>
		<p>
			<label>手机号码2：</label>
			<input name="kh_phone2" id="kh_phone2" type="text"  maxlength="11"  class="digits"   value="<%=user.getKh_phone2()==null?"":user.getKh_phone2() %>"/>
		</p>
		<p>
			<label>身份证号：</label>
			<input name="kh_card" id="kh_card" type="text"  maxlength="18"  class="alphanumeric"   value="<%=user.getKh_card() %>"/>
		</p>
		<p>
			<label>转介绍人（编号）：</label>
			<input name="introduce_name" id="introduce_name" type="text"  maxlength="11"   value="<%=user.getIntroduce_name()==null?"":user.getIntroduce_name() %>"   />
		</p>
		 
		<p>
			<label>渠道来源：</label>
			<input name="channel_source" id="channel_source" type="text"  maxlength="80" value="<%=user.getChannel_source()==null?"":user.getChannel_source() %>"/>
		</p>
		<p>
			<label>是否安装：</label>
			<select  name="is_install" id="is_install" class="required"  >
				<option value="0">未安装</option>
				<option value="1">已安装</option>
			</select>
		</p>
		
		<dl>
		<dt>备注：</dt>
			<dd>
				<textarea name="remark" id="remark" rows="5" cols="100"  ><%=user.getRemark()==null?"":user.getRemark() %></textarea>
			</dd>
		</dl>
		
	</div>
	</form>
	</div>
  </body>
</html>
