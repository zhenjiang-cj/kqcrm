<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/taglibs-log.tld" prefix="log"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<%@ page import="org.apache.log4j.Logger"%>
<%String contextpath = request.getContextPath(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>��������ҳ��</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link  href="<%=contextpath %>/css/systemCont.css" rel="stylesheet" type="text/css">
</head>
<body class="Contbody">
	<div class="contAllDiv borNO">
		<!--��ʾ������ START-->
		    <div class="divF"></div>
		  	<div class="workCont">
		  	   <div class="failD">
		         <div class="Divs">
		           <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                 <tr>
	                   <td><div class="picS">&nbsp;</div></td>
	                   <td>
		                   <div class="fonts" style="text-align:left;">
		                      <logic:present name="Global_ERROR_ID">
						          <bean:define id="err" name="Global_ERROR_ID" toScope="request" />
						          <!-- Console��־������� -->
						          <log:debug>
							        <bean:write name="err" property="message" />aaa
						          </log:debug>
						          <!-- ҳ����� -->
						          <bean:write name="err" property="message" />
						          
					          </logic:present>
					          <logic:notPresent name="Global_ERROR_ID">
						          û���ҵ���Ӧ�Ĵ�����Ϣ
					          </logic:notPresent>
		                   </div>
	                   </td>
	                 </tr>
	               </table>	
	            </div>
			 </div>
		</div>
		<!--��ʾ������ END-->
	</div>
</body>
</html>
