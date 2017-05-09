<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.nl.util.SessionData"%>
<%@page import="com.nl.util.SessionConst"%>
<%@page import="com.nl.repository.util.*"%>
<%@page import="com.nl.repository.dt.KnowledgeBaseDT"%>
<%
	String path = request.getContextPath();
	SessionData sessdata = (SessionData) request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
	List<KnowledgeBaseDT> manualList = sessdata.getManualList();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<link href="<%=path%>/css/welcome.css" rel="stylesheet" type="text/css" />
	<link href="<%=path%>/css/systemHead.css" type="text/css" rel="stylesheet" />
  </head>
  
  <body>
    <div class="manualttlbox">
	<div class="allttl manualttl" style="float:left">操作指南</div>
    <a href="#" class="more" style="float:right; margin-top:15px;" onclick="doRepository()"></a>
	</div>
	<div style="clear:both;"></div>
	<ul class="list">
		 <%
		  	if(manualList!=null&&manualList.size()>0){
		  		for(KnowledgeBaseDT dt:manualList){
		  %>
		  
	    <li> <a href="#" onclick="doDetail(<%=dt.getRecord_id() %>)"><%=dt.getKnowledge_title() %></a></li>
	    <%}
		  		}%>
	</ul>
  </body>
</html>
<script type="text/javascript" src="<%=path%>/plugins/jquery/plugins/dialog/Dialog.js"></script>
<script type="text/javascript">
	function doRepository()
    {
    	var url='<%=path%>/knowledgeBaseAction.do?method=queryKnowledgeBaseInfo&systemId=99&functionId=99201010&knowledgeType=2';
		parent.parent.parent.showD(url,1100,560,'信息查询',20);
    }  
    
    function doDetail(recordId)
    {
    	var url='<%=path%>/knowledgeBaseAction.do?method=detailKnowledgeBase&recordId='+recordId
	    		+'&systemId=99&functionId=<%=AppConst.FUNCTION_KNOWLEDGE_QUERY%>'
	    		+'&operatorId=<%=sessdata.getSysOperator().getSNo()%>';
	    		
		parent.parent.parent.showD(url,600,300,'信息详细',100);	    		
    }
</script>