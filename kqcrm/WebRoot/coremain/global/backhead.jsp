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
<script language="javascript" type="text/javascript" src="/htzj/publish/js/jquery.js">
</script>
<script language="javascript" type="text/javascript" src="/htzj/publish/js/common.js">
</script>
<script language="javascript" type="text/javascript" src="/htzj/publish/js/cal.js">
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
          <td rowspan="2" valign="middle">
          <a href="/htzj/">
          <img name="index_r1_c2" src="<%=GlobalUtil.getPropertiesText("struts-config_res","htzj_uri") %>/htzj/publish/style/index_r1_c2.png" width="255" height="82" border="0" id="index_r1_c2" alt="" />          </a>          </td>
          <td width="230" align="center">&nbsp;
           <!--loginBar-->
			<br/>
            
            	<a href="/htzj/system/index.jsp">
            	<img name="index_r1_c20" src="<%=GlobalUtil.getPropertiesText("struts-config_res","htzj_uri") %>/htzj/publish/images/person.png" align="bottom"/>
            	<%=viewUser %></a>
              	<a href="/htzj/publish/loginOut.jsp"> 【退  出】 </a>            
            
          </td>
          <td nowrap></td>
          <td align="right" nowrap><img src="<%=GlobalUtil.getPropertiesText("struts-config_res","htzj_uri") %>/htzj/publish/style/index_r1_c22.png" alt="" name="index_r1_c22" width="30" height="26" border="0" align="absbottom" id="index_r1_c22" /> <span class="STYLE1">镇江市住建局公共服务信息平台</span> &nbsp;&nbsp;&nbsp;</td>
          <td rowspan="2" scope="col"><img src="<%=GlobalUtil.getPropertiesText("struts-config_res","htzj_uri") %>/htzj/publish/images/htzj_2code.png" width="90" height="90"/></td>
        </tr>
        
        
        <tr>
          <td align="center" rowspan="2">
          	<b style="font-size:14px">
            <label id="localTimeJs"></label>
			<script>
      		setInterval("localTimeJs.innerHTML=new Date().toLocaleString();",1000);
    		</script>
            </b> 
           </td>
          <td align="right" colspan="2"><img name="index_r3_c20" src="<%=GlobalUtil.getPropertiesText("struts-config_res","htzj_uri") %>/htzj/publish/style/index_r3_c20.png" width="345" height="50" border="0" id="index_r3_c20" alt="" /></td>
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
      <div class="nav_sub" id="index" onClick="location='<%=GlobalUtil.getPropertiesText("struts-config_res","htzj_uri") %>/htzj/publish/index.jsp';">首 页</div>
      <div class="nav_sub" id="wkk" onClick="location='<%=GlobalUtil.getPropertiesText("struts-config_res","htzj_uri") %>/htzj/publish/queryXwdtList.do';">我看看</div>
      <div class="nav_sub" id="wxw" onClick="location='<%=GlobalUtil.getPropertiesText("struts-config_res","htzj_uri") %>/htzj/publish/wxw.jsp?nav=wxw';">我询问</div>
      <div class="nav_sub" id="wcx" onClick="location='<%=GlobalUtil.getPropertiesText("struts-config_res","htzj_uri") %>/htzj/publish/queryZcfgList.do';">我查询</div>
      <div class="nav_sub" id="wbs" onClick="location='<%=GlobalUtil.getPropertiesText("struts-config_res","htzj_uri") %>/htzj/publish/wbs_bmfw_zfbz.jsp?nav=wbs';">我办事</div>
      <div class="nav_sub" id="wbg" onClick="location='<%=GlobalUtil.getPropertiesText("struts-config_res","htzj_uri") %>/htzj/publish/wbg.jsp?nav=wbg';">我办公</div>
    </div>
  </div>
 
  <!--nav--结束-->

