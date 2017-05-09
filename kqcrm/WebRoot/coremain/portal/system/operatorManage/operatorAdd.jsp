<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.nl.portal.util.*"%>
<%@page import="com.nl.portal.actionForm.OperatorForm"%>
<%@page import="com.nl.portal.dt.SysOperator"%>
<%@page import="com.nl.util.GlobalConst"%>
<%@page import="com.nl.util.SessionData"%>
<%@page import="com.nl.util.SessionConst"%>  
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.nl.base.utils.GlobalFunc"%>
<%
	String contextpath = request.getContextPath();
	SessionData sessdata = (SessionData) request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
	OperatorForm form = (OperatorForm)request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM); 
	List<SysOperator> deptList = (List)request.getAttribute("deptList");
	SysOperator list;
	
	String operatorId = sessdata.getSysOperator().getSNo();
	String user_id = sessdata.getSysOperator().getUser_id();
	String user_code = sessdata.getSysOperator().getCop_code();
	String operatorName = sessdata.getSysOperator().getUser_name();
	String operatorCellNumber = sessdata.getSysOperator().getMsisdn();
	
	Date dd = new Date();
	SimpleDateFormat timenow = new SimpleDateFormat("yyyy-MM-dd");
	String date = timenow.format(dd);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
    <title>新增员工工号</title>
    <link  href="<%=contextpath%>/css/systemCont.css" rel="stylesheet" type="text/css" />
    <link href="<%=contextpath%>/plugins/jquery/plugins/ztree/3.1/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css">
    <link href="<%=contextpath%>/plugins/jquery/plugins/tokeninput/css/main_token-input.css" rel="stylesheet" type="text/css"/>
</head>
<body class="Contbody">
<div class="contAllDiv borNO">
	<!--显示工作区 START-->
	    <div class="divF"></div>
	  	<div class="workCont">
			    <form action="<%=contextpath%>/operator.do?method=doUserAdd" method="post" enctype="multipart/form-data" name="addForm">
			  		<input type="hidden" name="functionId" value="<%=GlobalConst.FUNCTION_OPERATOR_ADD %>">
		  	    	<input type="hidden" name="systemId" value="<%=GlobalConst.SYSTEM_ID_SYSTEM%>">
		  	    	<input type="hidden" name="operatorId"  value="<%=operatorId%>">
		  	    	<input type="hidden" name="operatorName"  value="<%=operatorName%>">
		  	    	
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="Title01">
				  <tr>
					<td class="td01"></td>
					<td class="td02"><div class="F01">新增员工工号</div><div class="S01"><img src="<%=contextpath%>/images/portal/contTit12.gif"></div></td>
					<td class="td03"></td>
				  </tr>
				</table>
				<table id="table_info" width="100%"  border="0" cellspacing="1" cellpadding="0"  class="TabList">
					  <tr>			 
					    <td class="tit" width="15%">员工工号
						</td>
						<td width="35%">
							<input id="userId" name="userId" type="text" onblur="getOperatorById();" class="inputS" maxlength="8"><font class="R">*</font>
						</td>
						<td class="tit" width="15%">员工姓名
						</td>
						<td width="35%">
							    <input id="userName" name="userName" type="text" class="inputS" maxlength="20"><font class="R">*</font>
						</td>
					  </tr> 
					  <tr>									
						<td class="tit" width="15%">手机号码</td>
						<td width="35%">
							    <input id="msisdn" name="msisdn" type="text" class="inputS" maxlength="11">
					    </td>
					    <td class="tit" width="15%">电子邮箱
						</td>
						<td width="35%">
							    <input id="email" name="email" type="text" class="inputS" style="width:60%" maxlength="45">
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
							<select id="sno_dept" name="sno_dept" class="selOpNSize">
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
				</form>
				
	      	  	<div class="OpLine">
	      	  		<input id="btn_submit" name=""  type="button" value="确定"  class="bntSty" onclick="doSubmit(this)" />
	      	  		<input name=""  type="button" value="返回"  class="bntSty" onClick="goBack()"/>
	      	  	</div>
		  	
		</div>
	<!--显示工作区 END-->
</div>
</body>
</html>
<script type="text/javascript" src='<%=contextpath%>/dwr/engine.js'></script>
<script type="text/javascript" src='<%=contextpath%>/dwr/util.js'></script>
<script type="text/javascript" src='<%=contextpath%>/dwr/interface/VerifyDWR.js'></script>
<script type="text/javascript" src="<%=contextpath %>/plugins/jquery/jquery-1.4.4.js"></script>
<script type="text/javascript">
	var $j=jQuery.noConflict();
</script>
<script type="text/javascript" src="<%=contextpath %>/plugins/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="<%=contextpath %>/plugins/jquery/plugins/ztree/demoTools.js"></script>
<script type="text/javascript" src="<%=contextpath %>/plugins/jquery/plugins/ztree/3.1/js/jquery.ztree.core-3.1.js"></script>
<script type="text/javascript" src="<%=contextpath %>/js/json2.js"></script>
<script type="text/javascript" src="<%=contextpath %>/plugins/jquery/plugins/ztree/3.1/js/jquery.ztree.excheck-3.1.js"></script>
<script type="text/javascript" src="<%=contextpath%>/plugins/jquery/plugins/tokeninput/jquery.tokeninput.js"></script> 
<script type="text/javascript" src="<%=contextpath%>/plugins/calendar/my97/WdatePicker.js"></script>
<script type="text/javascript"> 
	jQuery(document).ready(function(){

	});
	
	function getOperatorById() 
	{
		jQuery.ajax({			
				type: "POST",
				url: '<%=contextpath%>/operator.do?method=getOperatorById&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_OPERATOR_ADD%>',						
				data: {userId:jQuery("#userId").val()},
				dataType: 'json',
				success: function(data){
				    if (data != '') {
				    	var login_id = data[0].user_id; 
				    	
				    	if(login_id!='')
				    	{
				    		alert('该员工已有登录账号，无需再次新增！账号：'+login_id);				    		
				    		jQuery("#btn_submit").attr("disabled","disabled");
				    		jQuery("#userId").focus();
				    	}

			        }else{
			        	jQuery("#btn_submit").attr("disabled","");
			        }
				}
			});
	}

    function doSubmit(obj)
    {
    	var user_name = jQuery("#userName").val();
		var user_id = jQuery("#userId").val();
		var sno_dept = jQuery("#sno_dept").val();		
		var beginDate = jQuery("#beginDate").val();
		var msisdn = jQuery("#msisdn").val();
		
		if(user_id==''){
			alert("请输入员工工号!");
			jQuery("#userId").focus();
			return;
		}
		
		if(isNaN(user_id))
		{
			alert("员工工号应为纯数字!");
			jQuery("#userId").focus();
			return;
		}
		
		if(user_name==''){
			alert("请输入员工姓名!");
			jQuery("#userName").focus();
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
		if(sno_dept=='-99'){
			alert("请选择归属部门!");
			jQuery("#sno_dept").focus();
			return;
		}
		if(beginDate==''){
			alert("请选择开始时间!");
			return;
		}	
        addForm.submit();
    }
    
    function goBack()
    {
        history.go(-1);
    }
    

</script>
