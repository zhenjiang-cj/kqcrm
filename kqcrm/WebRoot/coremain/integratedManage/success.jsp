<%@ page language="java"  pageEncoding="GBK"%>
<%@page import="com.nl.complexWork.flowMarketing.actionForm.FlowMarketingForm"%>
<%@page import="com.nl.util.GlobalConst"%>
<%@page import="com.nl.complexWork.util.*"%>
<%@page import="com.nl.util.SessionData"%>
<%@page import="com.nl.util.SessionConst"%>

<%
	String path = request.getContextPath();
	
	SessionData sessdata = (SessionData) request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
	FlowMarketingForm form = (FlowMarketingForm)request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM); 
	String name="";	
	String param="";
	if(form.getFunctionId().equals(AppConst.FUNCTION_FLOW_MARKETING_ADD))//流动市场信息新增
	{ 
		name="流动市场信息新增";
		param = "&countyCode="+form.getCountyCode()
			  + "&partitionCode="+form.getPartitionCode()
			  + "&unitType="+form.getUnitCode()
			  + "&busiType="+form.getBusiType()
			  + "&unitName="+form.getUnitName()
			  + "&unitAddress="+form.getUnitAddress()
			  + "&unitExpireDate="+form.getUnitExpireDate()
			  + "&keyPerson="+form.getKeyPerson()
			  + "&keyPersonPhone="+form.getKeyPersonPhone()
			  + "&staffCmcc="+form.getStaffCmcc()
			  + "&staffUnicom="+form.getStaffUnicom()
			  + "&staffTelecom="+form.getStaffTelecom()
			  + "&broadBand="+form.getBroadBand()
			  + "&broadBandExpire="+form.getBroadBandExpire()
			  + "&serverName="+form.getServerName()
			  + "&serverSno="+form.getServerSno()
			  + "&serviceYytName="+form.getServiceYytName()
			  + "&serviceYytCode="+form.getServiceYytCode();
	}
	if(form.getFunctionId().equals(AppConst.FUNCTION_FLOW_MARKETING_MODIFY))//流动市场信息修改
	{ 
		name="流动市场信息修改";
	}
	if(form.getFunctionId().equals(AppConst.FUNCTION_FLOW_MARKETING_DEL))//流动市场信息删除
	{ 
		name="流动市场信息删除";
	}
	if(form.getFunctionId().equals(AppConst.FUNCTION_FLOW_MARKETING_SERVICE))//流动市场服务
	{ 
		name="流动市场服务";
		param = "&countyCode="+form.getCountyCode()
		  + "&partitionCode="+form.getPartitionCode()
		  + "&unitType="+form.getUnitType()
		  + "&busiType="+form.getBusiType()
		  + "&unitName="+form.getUnitName()		  
		  + "&serverName="+form.getServerName()
		  + "&isService="+form.getIsService()
		  + "&beginDate="+form.getBeginDate()
		  + "&endDate="+form.getEndDate();
	}
	if(form.getFunctionId().equals(AppConst.FUNCTION_FLOW_MARKETING_STAFF))//新增员工信息
	{ 
		name="新增员工信息";
	}
	if(form.getFunctionId().equals(AppConst.FUNCTION_FLOW_MARKETING_RESULT))//新增策反信息
	{ 
		name="新增策反信息";
	}
	

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
  </head>
  <body>
 
  </body>
<script type="text/javascript" src="<%=path%>/plugins/jquery/jquery-1.4.4.js"></script>
  <script type="text/javascript">
   	//加载后执行
	jQuery(document).ready(function(){
	
		<%
		if(form.getFlag()==0){
		%>
			parent.parent.showM("<%=name%>成功!",'1');
  		<%
  		}else{
		%>
			parent.parent.showM("<%=name%>失败!",'2');
		<%
		}
		%>
		<%
  		if(form.getFunctionId().equals(AppConst.FUNCTION_FLOW_MARKETING_ADD)){//流动市场信息新增
  		%>
  			window.location.href='<%=path%>/flowMarketingManage.do?method=doAddFlowMarketingInfo'+
    		'&systemId=<%=GlobalConst.SYSTEM_ID_BID%>&functionId=<%=AppConst.FUNCTION_FLOW_MARKETING_ADD%>'+
    		'&operatorId=<%=sessdata.getSysOperator().getSNo()%><%=param%>';
  		<%
  		}else if(form.getFunctionId().equals(AppConst.FUNCTION_FLOW_MARKETING_MODIFY)){//流动市场信息修改
  		%>
  			window.location.href='<%=path%>/flowMarketingManage.do?method=doModifyFlowMarketingInfo'+
    		'&systemId=<%=GlobalConst.SYSTEM_ID_BID%>&functionId=<%=AppConst.FUNCTION_FLOW_MARKETING_MODIFY%>'+
    		'&operatorId=<%=sessdata.getSysOperator().getSNo()%>&unitCode=<%=form.getUnitCode()%>';
    	<%
  		}else if(form.getFunctionId().equals(AppConst.FUNCTION_FLOW_MARKETING_DEL)){//流动市场信息删除
		%>
			window.location.href='<%=path%>/flowMarketingManage.do?method=queryFlowMarketingInfo'+
    		'&systemId=<%=GlobalConst.SYSTEM_ID_BID%>&functionId=<%=AppConst.FUNCTION_FLOW_MARKETING_QUERY%>'+
    		'&operatorId=<%=sessdata.getSysOperator().getSNo()%>';
    	<%
  		}else if(form.getFunctionId().equals(AppConst.FUNCTION_FLOW_MARKETING_SERVICE)){//流动市场服务
		%>		
			window.location.href='<%=path%>/flowMarketingManage.do?method=queryFlowMarketingInfo'+
    		'&systemId=<%=GlobalConst.SYSTEM_ID_BID%>&functionId=<%=AppConst.FUNCTION_FLOW_MARKETING_QUERY%>'+
    		'&operatorId=<%=sessdata.getSysOperator().getSNo()%><%=param%>';
    	<%
  		}else if(form.getFunctionId().equals(AppConst.FUNCTION_FLOW_MARKETING_STAFF)){//新增员工信息
		%>
			window.location.href='<%=path%>/flowMarketingManage.do?method=queryStaffInfo'+
	    		'&systemId=<%=GlobalConst.SYSTEM_ID_BID%>&functionId=<%=AppConst.FUNCTION_FLOW_MARKETING_STAFF%>'+
	    		'&operatorId=<%=sessdata.getSysOperator().getSNo()%>&unitCode=<%=form.getUnitCode()%>';
    	<%
  		}else if(form.getFunctionId().equals(AppConst.FUNCTION_FLOW_MARKETING_RESULT)){//新增策反信息
		%>		
			window.location.href='<%=path%>/flowMarketingManage.do?method=queryResultInfo'+
	    		'&systemId=<%=GlobalConst.SYSTEM_ID_BID%>&functionId=<%=AppConst.FUNCTION_FLOW_MARKETING_RESULT%>'+
	    		'&operatorId=<%=sessdata.getSysOperator().getSNo()%>&unitCode=<%=form.getUnitCode()%>';
    	<%
  		}
		%>
	});
  </script>
</html>
