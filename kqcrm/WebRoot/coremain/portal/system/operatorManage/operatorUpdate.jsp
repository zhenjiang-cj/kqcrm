<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.nl.util.GlobalConst"%>
<%@page import="com.nl.portal.dt.SysOperator"%>
<%@page import="com.nl.portal.actionForm.OperatorForm"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.base.utils.GlobalFunc"%>
<%@page import="java.text.SimpleDateFormat"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String contextpath = request.getContextPath();
    OperatorForm operatorForm = (OperatorForm)request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
    List<SysOperator> deptList = (List)request.getAttribute("deptList");
    SysOperator list;
    
    SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    String beginDate = day.format(date);
    
    SysOperator operator = operatorForm.getOperator();
    String param = "&userId=" + operatorForm.getUserId()
                 + "&userName=" + operatorForm.getUserName();
    
%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>操作员信息修改</title>
    <link  href="<%=contextpath%>/css/systemCont.css" rel="stylesheet" type="text/css">
</head>
<body class="Contbody">
	<div class="contAllDiv borNO">
		<!--显示工作区 START-->
		    <div class="divF"></div>
		  	<div class="workCont">  
		  	  <form action="<%=contextpath%>/operator.do?method=updateOperator" method="post" name="operatorForm">
		  	    <input type="hidden" name="functionId" value="<%=GlobalConst.FUNCTION_OPERATOR_UPDATE %>">
		  	    <input type="hidden" name="systemId" value="<%=GlobalConst.SYSTEM_ID_SYSTEM%>">
		  	    <input type="hidden" name="userSeq" value="<%=operator.getUser_seq()%>">
		  	    <input type="hidden" name="userId" value="<%=operator.getUser_id()%>">
				<input type="hidden" name="hr_sno" value="<%=operator.getHr_sno()%>">
				<input type="hidden" name="sno_dept_old" value="<%=operatorForm.getSno_dept_old()%>">
				<!--标题栏 START-->
				 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="Title01">
				  <tr>
					<td class="td01"></td>
					<td class="td02"><div class="F01">操作员信息修改</div><div class="S01"><img src="<%=contextpath%>/images/portal/contTit12.gif"></div></td>
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
						    <input id="userName" name="userName" type="text" class="inputS" value="<%=operator.getUser_name()%>" maxlength="20"><font class="R">*</font>
					    </td>
					  </tr>
					  <tr>			 
						<td class="tit" width="15%">手机号</td>
						<td width="35%">
						    <input id="msisdn" name="msisdn" type="text" class="inputS" value="<%=GlobalFunc.initStr(operator.getMsisdn(),"")%>" maxlength="11">
					    </td>
					    <td class="tit" width="15%">邮箱地址
						</td>
						<td width="35%">
						    <input id="email" name="email" type="text" class="inputS" value="<%=GlobalFunc.initStr(operator.getEmail(),"")%>" style="width:60%" maxlength="45">
						</td>
					  </tr>  
				   </table>
				   <table width="100%" border="0" cellspacing="0" cellpadding="0" class="Title01">
					  <tr>
						<td class="td01"></td>
						<td class="td02"><div class="F01">归属部门</div><div class="S01"><img src="<%=contextpath%>/images/portal/contTit12.gif"></div></td>
						<td class="td03"></td>
					  </tr>
					</table>
					
					<table id="table_info" width="100%"  border="0" cellspacing="1" cellpadding="0"  class="TabList">
					  <tr>			 
					    <td class="tit" width="15%">部门列表
						</td>
						<td colspan="3">
							<select id="sno_dept" name="sno_dept" class="selOpNSize" onchange="initDate()">
						       <option value="-99">--请选择--</option>
							   <%
							   	if (deptList!=null&&deptList.size()>0)
							   		{
							   			for(int i=0;i<deptList.size();i++)
							   				{
							   				list = (SysOperator)deptList.get(i);
							   %>
							   		<option value="<%=list.getSno_dept() %>"><%=list.getSno_dept_name() %></option>
							   <%
							   				}
							   		}
							   %>
						    </select><font class="R">*</font>
						</td>
					  </tr> 
					  <tr>									
						<td class="tit" width="15%">开始时间</td>
						<td width="35%">
							<input id="beginDate" type="text" name="beginDate" onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'});" class="Wdate" style="width:100px;"/><font class="R">*</font>
					    </td>
					    <td class="tit" width="15%">结束时间</td>
						<td width="35%">
							<input id="endDate" type="text" name="endDate" onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginDate\')}'});" class="Wdate" style="width:100px;"/>
						</td>
					  </tr> 
				</table>
				<div class="OpLine">
				 <input name="button1"  type="button" value="修 改"  class="bntSty" onClick="doSubmit()"/>
		         <input name=""  type="button" value="返 回"  class="bntSty" onClick="history.go(-1);"/>
		       </div>
		      </form>		      
			</div>	
		<!--显示工作区 END-->
	</div>
</body>
</html>
<script type="text/javascript" src="<%=contextpath%>/plugins/jquery/jquery-1.4.4.js"></script>
<script type="text/javascript" src="<%=contextpath%>/plugins/calendar/my97/WdatePicker.js"></script>
<script type="text/javascript">
	var $j=jQuery.noConflict();
</script>
<script type="text/javascript">
	jQuery(document).ready(function(){
		jQuery("#sno_dept").val("<%=operatorForm.getSno_dept_old()%>");
		jQuery("#beginDate").val("<%=GlobalFunc.initStr(operator.getBegin_date(),"")%>");
		jQuery("#endDate").val("<%=GlobalFunc.initStr(operator.getEnd_date(),"")%>");
	});
	
	function initDate()
	{
		jQuery("#beginDate").val('<%=beginDate%>');
		jQuery("#endDate").val('');
	}
	
    function doSubmit()
    {
        jQuery("#button1").attr("disabled","disabled");
        
        var user_name = jQuery("#userName").val();
		var sno_dept = jQuery("#sno_dept").val();		
		var beginDate = jQuery("#beginDate").val();
		var msisdn = jQuery("#msisdn").val();
		
        if(user_name==''){
			alert("请输入员工姓名!");
			jQuery("#user_name").focus();
			return;
		}
		if(sno_dept==''){
			alert("请选择归属部门!");
			jQuery("#sno_dept").focus();
			return;
		}
		if(msisdn.length>0){
			if(isNaN(msisdn))
			{
				alert("手机号码应为纯数字!");
				jQuery("#msisdn").focus();
				return;
			}
		}
		if(beginDate==''){
			alert("请选择开始时间!");
			jQuery("#beginDate").focus();
			return;
		}
		
        operatorForm.submit();
    }
    function goBack()
    {
        window.location.href="./operator.do?method=queryList&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_OPERATOR_MANAGE%>" + "<%=param%>";
    }
    

</script>
