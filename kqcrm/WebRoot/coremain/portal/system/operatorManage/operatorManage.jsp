<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.nl.portal.dt.SysOperatorRole"%>
<%@page import="com.nl.base.utils.GlobalFunc"%>
<%@page import="com.nl.util.GlobalConst"%>
<%@page import="com.nl.portal.actionForm.RoleForm"%>
<%@page import="com.nl.portal.actionForm.OperatorForm"%>
<%@page import="com.nl.portal.dt.SysOperator"%>
<%@page import="com.nl.util.GlobalConst"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.util.SessionData"%>
<%@page import="com.nl.util.SessionConst"%>
<%@ taglib uri="/pageListTag.tld" prefix="pageTags"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String contextpath = request.getContextPath();
    OperatorForm operatorForm = (OperatorForm)request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
    SessionData sessdata = (SessionData) request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
    String sno = sessdata.getSysOperator().getSNo();
    Map map = sessdata.getSysPrivilgeMap().get(GlobalConst.SYSTEM_ID_SYSTEM);
    String[] functionId = {GlobalConst.FUNCTION_OPERATOR_DETAIL,GlobalConst.FUNCTION_OPERATOR_UPDATE,
    	GlobalConst.FUNCTION_OPERATOR_ROLE_UPDATE,GlobalConst.FUNCTION_OPERATOR_PASSWORD_RESET,GlobalConst.FUNCTION_OPERATOR_ADD};
    boolean checkResult[] = GlobalFunc.functionCheck(map,functionId);
    
    List operatorList = (List)request.getAttribute("operatorList");
    SysOperator operator;
    int resultSum = 0;
    String url = "./operator.do?method=queryList&systemId="+GlobalConst.SYSTEM_ID_SYSTEM+"&functionId="+GlobalConst.FUNCTION_OPERATOR_MANAGE
                 + "&userId=" + operatorForm.getUserId()
                 + "&userName=" + operatorForm.getUserName();
    String param = "&userId=" + operatorForm.getUserId()
                 + "&userName=" + operatorForm.getUserName();
%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>����Ա����</title>
    <link  href="<%=contextpath%>/css/systemCont.css" rel="stylesheet" type="text/css">
</head>
<body class="Contbody">
	<div class="contAllDiv borNO">
		<!--��ʾ������ START-->
		    <div class="divF"></div>
		  	<div class="workCont">
		  	  <form action="<%=contextpath%>/operator.do?method=queryList" method="post" name="operatorForm">
		  	    <input type="hidden" name="functionId" value="<%=GlobalConst.FUNCTION_OPERATOR_MANAGE%>">
		  	    <input type="hidden" name="systemId" value="<%=GlobalConst.SYSTEM_ID_SYSTEM%>">
				<!--������ START-->
				 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="Title01">
				  <tr>
					<td class="td01"></td>
					<td class="td02"><div class="F01">��ѯ��������</div><div class="S01"><img src="<%=contextpath%>/images/portal/contTit12.gif"></div></td>
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
						     <input id="userId" name="userId" type="text" class="inputS" value="">
						</td>
						<td class="tit" width="15%">����Ա����</td>
						<td width="35%">
						    <input id="userName" name="userName" type="text" class="inputS" value="">
					    </td>
					   </tr>
				   </table>
				<div class="OpLine">
		         <input name=""  type="button" value="�� ѯ"  class="bntSty" onClick="query()"/>
			     <input name=""  type="button" value="�� ��"  class="bntSty" onclick="add()"/> 
		        </div>
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="Title01">
				  <tr>
					<td class="td01"></td>
					<td class="td02"><div class="F01">����Ա��Ϣ�б�</div><div class="S01"><img src="<%=contextpath%>/images/portal/contTit12.gif"></div></td>
					<td class="td03"></td>
				  </tr>
				</table>
				<!--������ END-->
				<!--���1 START-->
				  <table  width="100%" border="0" cellspacing="1" cellpadding="0"  class="TabList">
					  <tr>
						<th width="10%">����</th>
						<th width="15%">����</th>
						<th width="30%">������֯����</th>
						<th width="20%">�ֻ���</th>
						<th width="10%">״̬</th>
						<th width="15%">����</th>
					  </tr>
					  <%
					      if (operatorList != null && operatorList.size() > 0)
					      {
					          for (int i = 0; i< operatorList.size(); i++)
					          {	
					        	 
					              	operator = (SysOperator)operatorList.get(i);
					              	
					              	if (i == 0)
					              	    resultSum = Integer.valueOf(operator.getTotalCount());		      
					  %>
					       <tr>
					          <td align="center"><%=operator.getUser_id() %></td>
					          <td align="center"><%=operator.getUser_name() %></td>
					          <td align="center"><%=GlobalFunc.initStr(operator.getSno_dept_name(),"-") %></td>
					          <td align="center"><%=GlobalFunc.initStr(operator.getMsisdn(),"-") %></td>
					          <td align="center"><%=DictMgmt.getValueDescs(DictMgmt.DICT_SYS_VALID_FLAG,operator.getEn_flag())%></td>
					          <td align="center">
					          	<%
					          		if(checkResult[0]){
					          	 %>
					              <img src="<%=contextpath%>/images/portal/file.gif" onClick="doDetail('<%=operator.getUser_seq()%>','<%=operator.getSno_dept() %>')" alt="��ϸ��Ϣ" style="cursor: pointer;">
					            <%
					            	}
					            	if(checkResult[1]){
					             %>
					              <img src="<%=contextpath%>/images/portal/modify_ico.gif" width="15" onClick="doUpdate('<%=operator.getUser_seq()%>','<%=operator.getSno_dept() %>')" alt="�����޸�" style="cursor: pointer;">
					            <%
					            	}
					            	if(checkResult[2]){
					             %>
					              <img src="<%=contextpath%>/images/portal/leftTit02.gif" width="15" onClick="updateRole('<%=operator.getUser_seq()%>','<%=operator.getSno_dept() %>')" alt="��ɫ����" style="cursor: pointer;">
					            <%
					            	}
					            	if(checkResult[3]){
					             %>
					              <img src="<%=contextpath%>/images/portal/key.gif" width="15" onClick="resetPassword('<%=operator.getUser_seq()%>')" alt="��������" style="cursor: pointer;"> 
					            <%
					            	}
					             %>					              
					             <% 
					            
				            	if(checkResult[1]){
				            		if("1".equals(String.valueOf(operator.getEn_flag()))){
				            			%> 
							              <img src="<%=contextpath%>/images/portal/img/config2.png" width="15" onClick="closeUser('<%=operator.getUser_seq()%>')" alt="�رչ���" style="cursor: pointer;">
							             <% 
				            		}else{
				            			%> 
							              <img src="<%=contextpath%>/images/portal/img/config2.png_gray.png" width="15" onClick="openUser('<%=operator.getUser_seq()%>')" alt="�򿪹���" style="cursor: pointer;">
							             <% 
				            		}
					             }
				            	
					             %>
					             
					             
					          </td>
					       </tr>
					  <%
					          }
					  %>
					       <tr>
							<td colspan="6">
							 <div class="TabPage" align="center">
								<pageTags:pageListTag linkUrl="<%=url%>" currentPage="<%=operatorForm.getCurrentPage()%>" resultSum="<%=resultSum%>" pageDisplayNum="10" aroundType="false">
				                </pageTags:pageListTag>
							</div>	
							</td>
						  </tr>

					  <%
					      }
					      else
					      {
					  %>
					      <tr>
					          <td align="center" colspan="6">û�з��������Ľ������������������²�ѯ��</td>
					      </tr>
					  <%
					      }
					  %>
				     
		      </table>
		      </form>  
			</div>
			
		<!--��ʾ������ END-->
	</div>
</body>
</html>
<script type="text/javascript" src="<%=contextpath%>/plugins/jquery/jquery-1.4.4.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $("#userId").val("<%=operatorForm.getUserId() %>");
        $("#userName").val("<%=operatorForm.getUserName() %>");

    })
    function query()
    {
        operatorForm.submit();
    }
    function doDetail(userSeq,snoDept)
    {
        window.location.href = "<%=contextpath%>/operator.do?method=operatorDetail&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_OPERATOR_DETAIL%>" 
                               + "&userSeq=" + userSeq + "&sno_dept_old=" + snoDept
                               + "<%=param%>";
    }
    function doUpdate(userSeq,snoDeptOld)
    {
        window.location.href = "<%=contextpath%>/operator.do?method=preUpdateOperator&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_OPERATOR_UPDATE%>" 
                               + "&userSeq=" + userSeq + "&sno_dept_old=" + snoDeptOld + "<%=param%>";
    }
    function updateRole(userSeq,snoDept)
    {
        window.location.href = "<%=contextpath%>/operator.do?method=preUpdateOperRole&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_OPERATOR_ROLE_UPDATE%>" 
                               + "&userSeq=" + userSeq + "&sno_dept=" + snoDept
                               + "<%=param%>";
    }
    function resetPassword(userSeq)
    {
    	if(confirm('��ȷ��Ҫ���ò���Ա������?')){
	    	window.location.href = "<%=contextpath%>/operator.do?method=resetPassword&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_OPERATOR_PASSWORD_RESET%>" 
	                               + "&userSeq=" + userSeq
	                               + "<%=param%>";
        }
    }

    
 	function add(){
 		window.location.href="<%=contextpath%>/operator.do?method=toUserAdd"
						    +"&operatorCode=<%=sessdata.getSysOperator().getSNo()%>"
						    +"&operatorName=<%=sessdata.getSysOperator().getUser_name()%>"
						    +"&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_OPERATOR_ADD %>"
						    ; 
 	}
 	function closeUser(userSeq)
 	{
 		if(confirm('��ȷ��Ҫ�رչ�����?')){
	    	window.location.href = "<%=contextpath%>/operator.do?method=closeUser&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_OPERATOR_UPDATE%>" 
	                               + "&userSeq=" + userSeq
	                               + "<%=param%>";
        }
 	}
 	function openUser(userSeq)
 	{
 		if(confirm('��ȷ��Ҫ�򿪹�����?')){
	    	window.location.href = "<%=contextpath%>/operator.do?method=openUser&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_OPERATOR_UPDATE%>" 
	                               + "&userSeq=" + userSeq
	                               + "<%=param%>";
        }
 	}

</script>
