<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.nl.util.GlobalConst"%>
<%@page import="com.nl.portal.dt.SysOperator"%>
<%@page import="com.nl.portal.actionForm.OperatorForm"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.portal.dt.SysOperatorRole"%>
<%@page import="com.nl.portal.dt.PriTreeDisplayCfgDt"%>
<%@page import="com.nl.util.SessionData"%>
<%@page import="com.nl.util.SessionConst"%>
<%@page import="com.nl.base.utils.GlobalFunc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String contextpath = request.getContextPath();
    OperatorForm operatorForm = (OperatorForm)request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
    SysOperatorRole operatorRole;
    SessionData sessiondata = (SessionData) request.getSession().getAttribute(SessionConst.LOGIN_SESSION);

    List useRoleList = (List)request.getAttribute("useRoleList");
    List chooseRoleList = (List)operatorForm.getOperRoleMap().get(operatorForm.getSysId());


    SysOperator operator = operatorForm.getOperator();
    String param = "&userId=" + operatorForm.getUserId()
                 + "&userName=" + operatorForm.getUserName();
    
    String chooseRoleStr = "";             
    if (chooseRoleList!=null&&chooseRoleList.size()>0){
       for (int i=0; i<chooseRoleList.size(); i++)
       {
           operatorRole = (SysOperatorRole)chooseRoleList.get(i);
           chooseRoleStr += operatorRole.getRole_id();
           if(i!=chooseRoleList.size()-1)
           		chooseRoleStr += ",";
       }
   }
    
%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>操作员角色配置</title>
    <link  href="<%=contextpath%>/css/systemCont.css" rel="stylesheet" type="text/css">
    <link href="<%=contextpath%>/plugins/jquery/plugins/ztree/3.1/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css">
</head>
<style>
.searchBntSty{background:url(<%=contextpath%>/images/portal/searchNode.png) no-repeat; width:26px; height:20px; text-align:center; color:#000000; font-size:12px; list-style:none; border:0px; line-height:20px; cursor:pointer; margin:0px 2px;border:0px; }
</style>
<body class="Contbody">
	<div class="contAllDiv borNO">
		<!--显示工作区 START-->
		    
		  	<div class="workCont">  
		  	  <form action="<%=contextpath%>/operator.do?method=updateOperRole" method="post" name="operatorForm">
		  	    <input type="hidden" name="functionId" value="<%=GlobalConst.FUNCTION_OPERATOR_ROLE_UPDATE %>">
		  	    <input type="hidden" name="systemId" value="<%=GlobalConst.SYSTEM_ID_SYSTEM%>">
		  	    <input type="hidden" id="userSeq" name="userSeq" value="<%=operator.getUser_seq()%>">
		  	    <input type="hidden" name="userId" value="<%=operatorForm.getUserId()%>">
		  	    <input type="hidden" name="userName" value="<%=operatorForm.getUserName()%>">
		  	    <input type="hidden" name="operatorId"  value="<%=sessiondata.getSysOperator().getSNo()%>">
				<!--标题栏 START-->
				 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="Title01">
				  <tr>
					<td class="td01"></td>
					<td class="td02"><div class="F01">操作员角色配置</div><div class="S01"><img src="<%=contextpath%>/images/portal/contTit12.gif"></div></td>
					<td class="td03"></td>
				  </tr>
				</table>
				<!--标题栏 END-->
				<!--表格1 START-->
				  <table width="100%"  border="0" cellspacing="1" cellpadding="0"  class="TabList">
					  <tr>			 
					    <td class="tit" width="15%">操作员工号
						</td>
						<td width="35%">
						    <%=operator.getUser_id()%>
						</td>
						<td class="tit" width="15%">姓名</td>
						<td width="35%">
						    <%=operator.getUser_name()%>
					    </td>
					  </tr>
					  <tr>			 
					    <td class="tit" width="15%">归属部门
						</td>
						<td width="35%">
						    <%=GlobalFunc.initStr(operator.getSno_dept_name(),"-") %>
						</td>
						<td class="tit" width="15%">手机号</td>
						<td width="35%">
						    <%=GlobalFunc.initStr(operator.getMsisdn(),"-") %>
					    </td>
					  </tr>
					  <tr>			 
					    <td class="tit" width="15%">邮箱地址
						</td>
						<td width="35%">
						    <%=GlobalFunc.initStr(operator.getEmail(),"-") %>
						</td>
						<td class="tit" width="15%">状态</td>
						<td width="35%">
						    <%=DictMgmt.getValueDescs(DictMgmt.DICT_SYS_VALID_FLAG,operator.getEn_flag())%>
					    </td>
					  </tr>
				   </table>
				   <table width="100%" border="0" cellspacing="0" cellpadding="0" class="Title01">
					  <tr>
						<td class="td01"></td>
						<td class="td02"><div class="F01">系统数据范围设置</div><div class="S01"><img src="<%=contextpath%>/images/portal/contTit12.gif"></div></td>
						<td class="td03"></td>
					  </tr>
					</table>
					<table id="table_info" width="100%"  border="0" cellspacing="1" cellpadding="0"  class="TabList">					  
					   <tr>			 
					    <td class="tit" width="15%">归属系统
						</td>
						<td colspan="3">
						    <select id="sysId" name="sysId" class="selOp" onchange="changeSys()">
						        <%=DictMgmt.getSelectObj(DictMgmt.DICT_SYS_SYSTEM_ID,"",false,false,"-1", -1, null, null, null,-1,"") %>
					        </select><font class="Red">*</font>
						</td>
					   </tr>
				   </table>
				   <table width="100%" border="0" cellspacing="0" cellpadding="0" class="Title01">
					  <tr>
						<td class="td01"></td>
						<td class="td02"><div class="F01">角色设置</div><div class="S01"><img src="<%=contextpath%>/images/portal/contTit12.gif"></div></td>
						<td class="td03"></td>
					  </tr>
					</table>
				 <table width="99%" border="0" cellspacing="1" cellpadding="0" class="tabLiat03">
				  <tr>
					<td class="titTD Cent">可分配角色</td>
					<td class="titTD Cent">设置</td>
					<td class="titTD Cent">已分配角色</td>
				  </tr>
				  <tr>
					<td>
					     <select multiple="multiple" name="allRole" id="allRole" class="selOp" style="width:300px;height:150px"   
				               ondblclick="changeObject(this.id,'chooseRole');">  
				               <% 
				                   for (int i=0; i<useRoleList.size(); i++)
				                   {
				                       operatorRole = (SysOperatorRole)useRoleList.get(i);
				               %>
				               <option value="<%=operatorRole.getRole_id() %>">
				                   <%=operatorRole.getRole_name()%>
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
                          	if (chooseRoleList!=null&&chooseRoleList.size()>0){
			                   for (int i=0; i<chooseRoleList.size(); i++)
			                   {
			                       operatorRole = (SysOperatorRole)chooseRoleList.get(i);
			               %>
			               <option value="<%=operatorRole.getRole_id() %>">
			                   <%=operatorRole.getRole_name()%>
			               </option>  
			               <%
			                   }
                          	}
			               %>  
                       </select>
					</td>
				  </tr>
				</table>
					

				<div class="OpLine">
				 <input name="button1"  type="button" value="修 改"  class="bntSty" onClick="doSubmit()"/>
		         <input name=""  type="button" value="返 回"  class="bntSty" onClick="goBack()"/>
		       </div>
		       </form>		      
			</div>
		<!--显示工作区 END-->
	</div>
	<div id="orgtreeContent"  style="display:none; position:absolute;z-index:5555;height:300px; background-color:white;border:1px solid;overflow-y:auto;overflow-x:auto;">
		<div id="searchContent"  style="z-index:5555;background-color:white;border:0px solid;overflow-y:auto;overflow-x:auto;">
		</div>
		<ul id="orgtree" class="ztree" style="margin-top:0; width:160px;"></ul>
	</div>
	<!-- 
	<div id="orgtreeContent_2"  style="display:none; position:absolute;height:300px; background-color:white;border:1px solid;overflow-y:auto;overflow-x:auto;">
		<ul id="orgtree_2" class="ztree" style="margin-top:0; width:160px;"></ul>
	</div>
	 -->
</body>
</html>
<script type="text/javascript" src="<%=contextpath%>/plugins/jquery/jquery-1.4.4.js"></script>
<script type="text/javascript">
	var $j=jQuery.noConflict();
</script>
<script type="text/javascript" src="<%=contextpath%>/plugins/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="<%=contextpath%>/plugins/jquery/plugins/ztree/demoTools.js"></script>
<script type="text/javascript" src="<%=contextpath%>/plugins/jquery/plugins/ztree/3.1/js/jquery.ztree.core-3.1.js"></script>
<script type="text/javascript" src="<%=contextpath%>/js/json2.js"></script>
<script type="text/javascript" src="<%=contextpath%>/plugins/jquery/plugins/ztree/3.1/js/jquery.ztree.excheck-3.1.js"></script>
<script type="text/javascript">

/*
 *页面初始化
 * @author sanjing
 * @createdate 2012/08/8
 */
jQuery(document).ready(function(){
	jQuery("#sysId").val('<%=operatorForm.getSysId() %>');

});
   
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
     
    function changeSys()
    {
        window.location.href="./operator.do?method=preUpdateOperRole&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_OPERATOR_ROLE_UPDATE%>" 
                             + "&sysId=" + jQuery("#sysId").val()
                             + "&userSeq=" + jQuery("#userSeq").val()
                             + "<%=param%>";
    }
    
    function doSubmit()
    {

        jQuery("#chooseRole").children().each(function(){jQuery(this).attr("selected","selected")});
        jQuery("#button1").attr("disabled","disabled");
        operatorForm.submit();
    }

    function goBack()
    {
        window.location.href="./operator.do?method=queryList&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_OPERATOR_MANAGE%>" + "<%=param%>";
    }
    
    
</script>
