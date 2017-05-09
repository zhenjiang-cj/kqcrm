<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.nl.util.SessionData"%>
<%@page import="com.nl.util.SessionConst"%>
<%@page import="com.nl.portal.dt.*"%>
<%
	String path = request.getContextPath();
	SessionData sessdata = (SessionData) request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
	List<SysMenu> myMenuList = sessdata.getUsualMenu();
	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<link href="<%=path%>/css/welcome.css" rel="stylesheet" type="text/css" />
	<link href="<%=path%>/css/systemHead.css" type="text/css" rel="stylesheet" />
	<link href="<%=path%>/css/toptitle.css" type="text/css" rel="stylesheet" />
  </head>
  
  <body>
    <table cellspacing="0" cellpadding="0" border="0" class="ulList bgp usualtable" id="svc-toolbar" style="margin:0;">
          <tr>
          	  <%if(myMenuList!=null&&myMenuList.size()>0){
  		  			for(SysMenu dt:myMenuList){ %>
              <td id=""><a onfocus="this.blur()" href="#" title='<%=dt.getMenu_path() %>' onclick="doWork('<%=dt.getName()%>','<%=dt.getWebaddr()%>')"><div class="Hs9"><span class="bgp-fr"></span></div><%=dt.getAlias() %></td>
              <td id="">&nbsp;</td>
              <%}
  		  			}%>
          </tr>
      </table>
  </body>
</html>
<script type="text/javascript">
	function doWork(taskName,taskUrl)
	{
		parent.parent.parent.addTask(taskName,taskUrl);
	}
</script>