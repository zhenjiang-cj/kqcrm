<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
 <%@page import="com.nl.util.SessionData"%>
  <%@page import="com.nl.util.SessionConst"%>
<%@page import="com.nl.util.GlobalUtil"%> 

 <!--head--开始-->
<%
String viewUser = "";
//判断当前用户是否登录
SessionData sessionData = (SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
SessionData c_sessionData = (SessionData)request.getSession().getAttribute(SessionConst.COMPANY_LOGIN_SESSION);

try{
	if(sessionData!=null&&sessionData.getAdmUser()!=null)
		viewUser = sessionData.getAdmUser().getAuType();
	if(c_sessionData!=null&&c_sessionData.getCompanyUser()!=null)
		viewUser = c_sessionData.getCompanyUser().getCompany_user_name();
}catch(Exception e){
	
	
}

%>	

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>慧通住建 公共服务信息平台  — 镇江市住房和城乡建设局</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/category.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/rfloat.css" />
<style type="text/css">
<!--
.STYLE1 {
	font-family: "华文楷体";
	font-weight: bold;
}
-->
</style>
<script language="javascript" type="text/javascript" src="/khcrm/publish/js/jquery.js">
</script>
<script language="javascript" type="text/javascript" src="/khcrm/publish/js/common.js">
</script>
<script language="javascript" type="text/javascript" src="/khcrm/publish/js/cal.js">
</script>
</head>
<body>
<div id='barrierfree_container'>
<!--head--开始-->
<div class="head_all">
  <div class="head">
    <div class="head_main">
      <table border="0" cellspacing="0" cellpadding="0">
      
        
        <tr>
          <td align="center" rowspan="2">
          	<b style="font-size:14px">
            <label id="localTimeJs"></label>
			<script>
      		setInterval("localTimeJs.innerHTML=new Date().toLocaleString();",1000);
    		</script>
            </b> 
           </td>
          <td align="right" colspan="2"><img name="index_r3_c20" src="" width="345" height="50" border="0" id="index_r3_c20" alt="" /></td>
        </tr>
      </table>
    </div>
  </div>
  <div style="background-color:#E1E1E1; height:4px;"></div>
</div>

<!--head--结束-->

  <!--head--结束-->
  
  <!--nav--开始-->
  <div class="nav">
    <div class="nav_main">
    </div>
  </div>
 
  <!--nav--结束-->

