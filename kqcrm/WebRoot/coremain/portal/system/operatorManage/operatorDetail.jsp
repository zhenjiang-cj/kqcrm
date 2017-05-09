<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.nl.util.GlobalConst"%>
<%@page import="com.nl.portal.dt.SysOperator"%>
<%@page import="com.nl.portal.actionForm.OperatorForm"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.portal.dt.SysOperatorRole"%>
<%@page import="com.nl.base.utils.GlobalFunc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String contextpath = request.getContextPath();
    OperatorForm operatorForm = (OperatorForm)request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
    SysOperator operator = operatorForm.getOperator();
    SysOperatorRole operatorRole;
    Map operRoleMap = operatorForm.getOperRoleMap();
    List roleList = new ArrayList();
    int systemId[] = GlobalConst.Global_SYSTEM_ID;
    
    String param = "&userId=" + operatorForm.getUserId()
                 + "&userName=" + operatorForm.getUserName();
    
%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>����Ա��ϸ��Ϣ</title>
    <link  href="<%=contextpath%>/css/systemCont.css" rel="stylesheet" type="text/css">
</head>
<body class="Contbody">
	<div class="contAllDiv borNO">
		<!--��ʾ������ START-->
		    <div class="divF"></div>
		  	<div class="workCont">
		  	    <input type="hidden" name="functionId" value="<%=GlobalConst.FUNCTION_OPERATOR_DETAIL %>">
		  	    <input type="hidden" name="systemId" value="<%=GlobalConst.SYSTEM_ID_SYSTEM%>">
				<!--������ START-->
				 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="Title01">
				  <tr>
					<td class="td01"></td>
					<td class="td02"><div class="F01">����Ա��ϸ��Ϣ</div><div class="S01"><img src="<%=contextpath%>/images/portal/contTit12.gif"></div></td>
					<td class="td03"></td>
				  </tr>
				</table>
				<!--������ END-->
				<!--���1 START-->
				  <table width="100%"  border="0" cellspacing="1" cellpadding="0"  class="TabList">
					  <tr>			 
					    <td class="tit" width="15%">����Ա����
						</td>
						<td width="35%">
						    <%=operator.getUser_id()%>
						</td>
						<td class="tit" width="15%">����</td>
						<td width="35%">
						    <%=operator.getUser_name()%>
					    </td>
					  </tr>
					  <tr>			 
					    <td class="tit" width="15%">��������
						</td>
						<td width="35%">
						    <%=operator.getSno_dept_name() %>
						</td>
						<td class="tit" width="15%">�ֻ���</td>
						<td width="35%">
						    <%=GlobalFunc.initStr(operator.getMsisdn(),"-") %>
					    </td>
					  </tr>
					  <tr>			 
					    <td class="tit" width="15%">�����ַ
						</td>
						<td width="35%">
						    <%=GlobalFunc.initStr(operator.getEmail(),"-") %>
						</td>
						<td class="tit" width="15%">״̬</td>
						<td width="35%">
						    <%=DictMgmt.getValueDescs(DictMgmt.DICT_SYS_VALID_FLAG,operator.getEn_flag())%>
					    </td>
					  </tr>  
				   </table>
				   <table width="100%" border="0" cellspacing="0" cellpadding="0" class="Title01">
					  <tr>
						<td class="td01"></td>
						<td class="td02"><div class="F01">��ɫ��Ϣ</div><div class="S01"><img src="<%=contextpath%>/images/portal/contTit12.gif"></div></td>
						<td class="td03"></td>
					  </tr>
					</table>
					<!--������ END-->
					<!--���1 START-->
					  <table  width="100%" border="0" cellspacing="1" cellpadding="0"  class="TabList">
						  <tr>
							<th width="15%">ϵͳ����</th>
							<th width="85%">�����ɫ</th>
						  </tr>
						  <%
						      for (int i=0;i<systemId.length;i++)
						      {
						          roleList = (List)operRoleMap.get(String.valueOf(systemId[i]));
						  %>
						   <tr>
						     <td align="center">
						         <%=DictMgmt.getValueDescs(DictMgmt.DICT_SYS_SYSTEM_ID,systemId[i])%>
						     </td>
						     <td>
						         <%
						             if (roleList != null && roleList.size() > 0)
						             {
						                 for (int j=0;j<roleList.size();j++)
						                 {
						                     operatorRole = (SysOperatorRole)roleList.get(j);
						         %>
						         <%=operatorRole.getRole_name()%>&nbsp;
						         <%
						                 }
						             }
						             else
						             {
						         %>
						             -
						         <%
						             }
						         %>  
						     </td>
						   </tr>
						  <%
						      }
						  %>
					  </table>

				<div class="OpLine">
		         <input name=""  type="button" value="�� ��"  class="bntSty" onClick="goBack()"/>
		       </div>		      
			</div>
		<!--��ʾ������ END-->
	</div>
</body>
</html>
<script type="text/javascript">
    function goBack()
    {
        window.location.href="./operator.do?method=queryList&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_OPERATOR_MANAGE %>" + "<%=param%>";
    }
</script>
