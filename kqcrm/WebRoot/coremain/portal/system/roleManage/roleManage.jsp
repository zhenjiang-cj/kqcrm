<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.nl.portal.dt.SysOperatorRole"%>
<%@page import="com.nl.base.utils.GlobalFunc"%>
<%@page import="com.nl.util.GlobalConst"%>
<%@page import="com.nl.portal.actionForm.RoleForm"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.util.SessionData"%>
<%@page import="com.nl.util.SessionConst"%>
<%@ taglib uri="../../../../WEB-INF/tlds/pageListTag.tld" prefix="pageTags"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String contextpath = request.getContextPath();
    
    SessionData sessionData = (SessionData)session.getAttribute(SessionConst.LOGIN_SESSION);
    Map map = sessionData.getSysPrivilgeMap().get(GlobalConst.SYSTEM_ID_SYSTEM);
    String []functionId = {GlobalConst.FUNCTION_ROLE_ADD,GlobalConst.FUNCTION_ROLE_MANAGE,GlobalConst.FUNCTION_ROLE_UPDATE,GlobalConst.FUNCTION_ROLE_DELETE};
    boolean checkResult[] = GlobalFunc.functionCheck(map,functionId);
    
    RoleForm roleForm = (RoleForm)request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
    List roleList = (List)request.getAttribute("roleList");
    SysOperatorRole operatorRole;
    int resultSum = 0;
    
    String param = "&sysId=" + roleForm.getSysId()
                 + "&roleName=" + roleForm.getRoleName()
                 + "&menuName=" + roleForm.getMenuName()
                 + "&roleType=" + roleForm.getRoleType();
    
    String url = "./role.do?method=queryList&systemId="+GlobalConst.SYSTEM_ID_SYSTEM+"&functionId="+GlobalConst.FUNCTION_ROLE_MANAGE+param;

%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>��ɫ����</title>
    <link  href="<%=contextpath%>/css/systemCont.css" rel="stylesheet" type="text/css">
</head>
<body class="Contbody">
	<div class="contAllDiv borNO">
		<!--��ʾ������ START-->
		    <div class="divF"></div>
		  	<div class="workCont">
		  	   <form action="<%=contextpath%>/role.do?method=queryList" method="post" name="roleForm">
		  	    <input type="hidden" name="functionId" value="<%=GlobalConst.FUNCTION_ROLE_MANAGE%>">
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
						<td class="tit" width="15%">ϵͳ����
						</td>
						<td width="35%">
						     <select id="sysId" name="sysId" class="selOp">
							    <option value="<%=GlobalConst.GLOBLA_SELECT_VALUE %>"><%=GlobalConst.GLOBAL_SELECT_NAME %></option>
						        <%=DictMgmt.getSelectObj(DictMgmt.DICT_SYS_SYSTEM_ID,"",false,false,"-1", -1, null, null, null,-1,"") %>
					        </select>
						</td>
						<td class="tit" width="15%">��ɫ����</td>
						<td width="35%">
						    <input id="roleName" name="roleName" type="text" class="inputS" value="">
					    </td>
					   </tr>
					   <tr>			 
					    <td class="tit" width="15%">�˵�����</td>
						<td colspan="3">
						    <input id="menuName" name="menuName" type="text" class="inputS" value="">
					    </td>
					   </tr>
				   </table>
				<div class="OpLine">
		         <input name=""  type="button" value="�� ѯ"  class="bntSty" onClick="query()"/>
		         <%
		           if (checkResult[0])
		           {
		         %>
		         <input name=""  type="button" value="�� ��"  class="bntSty" onClick="add()"/>
		         <%
		           }
		         %>
		       </div>

				
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="Title01">
				  <tr>
					<td class="td01"></td>
					<td class="td02"><div class="F01">��ɫ��Ϣ�б�</div><div class="S01"><img src="<%=contextpath%>/images/portal/contTit12.gif"></div></td>
					<td class="td03"></td>
				  </tr>
				</table>
				<!--������ END-->
				<!--���1 START-->
				  <table  width="100%" border="0" cellspacing="1" cellpadding="0"  class="TabList">
					  <tr>
						<th width="15%">��ɫ���</th>
						<th width="25%">��ɫ����</th>
						<th width="15%">����ϵͳ</th>
						<th width="20%">��������</th>
						<th width="25%">����</th>
					  </tr>
					  <%
					      if (roleList != null &&roleList.size() > 0)
					      {
					          for (int i = 0; i< roleList.size(); i++)
					          {	
					              	operatorRole = (SysOperatorRole)roleList.get(i);
					              	if (i == 0)
					              	    resultSum = Integer.valueOf(operatorRole.getTotalCount());		      
					  %>
					       <tr>
					          <td align="center"><%=operatorRole.getRole_id() %></td>
					          <td align="center"><%=operatorRole.getRole_name() %></td>
					          <td align="center"><%=DictMgmt.getValueDescs(DictMgmt.DICT_SYS_SYSTEM_ID,operatorRole.getSysid()) %></td>
					          <td align="center"><%=GlobalFunc.initStr(operatorRole.getCreatetime(),"-") %></td>
					          <td align="center">
					               <%
							           if (checkResult[1])
							           {
						          %>
					              <img src="<%=contextpath%>/images/portal/img/file.gif" onClick="doDetail('<%=operatorRole.getRole_id()%>')" alt="��ϸ��Ϣ" style="cursor: pointer;">
					              <%
					                   }
							           if (checkResult[2])
							           {
						          %>
					              <img src="<%=contextpath%>/images/portal/img/modify_ico.gif" width="15" onClick="doUpdate('<%=operatorRole.getRole_id()%>')" alt="�޸�" style="cursor: pointer;">
					              <%
					                   }
							           if (checkResult[3])
							           {
						          %>
					              <img src="<%=contextpath%>/images/portal/img/false_ico.gif" width="15" onClick="doDelete('<%=operatorRole.getRole_id()%>','<%=operatorRole.getRole_name() %>')" alt="ɾ��" style="cursor: pointer;"> 
					              <%
					                   }
					              %>
					          </td>
					       </tr>
					  <%
					          }
					  %>
					       <tr>
							<td colspan="5">
							 <div class="TabPage" align="center">
								<pageTags:pageListTag linkUrl="<%=url%>" currentPage="<%=roleForm.getCurrentPage()%>" resultSum="<%=resultSum%>" pageDisplayNum="10" aroundType="false">
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
					          <td align="center" colspan="5">û�з��������Ľ������������������²�ѯ��</td>
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
        $("#sysId").val("<%=roleForm.getSysId() %>");
        $("#roleName").val("<%=roleForm.getRoleName()%>");
        $("#roleType").val("<%=roleForm.getRoleType()%>");
        $("#menuName").val("<%=roleForm.getMenuName()%>");
    })
    function query()
    {
        roleForm.submit();
    }
    function add()
    {
        window.location.href = "<%=contextpath%>/role.do?method=preAddRole&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_ROLE_ADD%>" + "<%=param%>";
    }
    function doDetail(roleId)
    {
        window.location.href = "<%=contextpath%>/role.do?method=roleDetail&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_ROLE_MANAGE%>"
                               + "&roleId=" +roleId
                               + "<%=param%>";
    }
    function doUpdate(roleId)
    {
        window.location.href = "<%=contextpath%>/role.do?method=preUpdateRole&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_ROLE_UPDATE%>"
                               + "&roleId=" +roleId
                               + "<%=param%>";
    }
    function doDelete(roleId,roleName)
    {
        if (confirm("��ȷ��ɾ���ý�ɫ��"))
            window.location.href = "<%=contextpath%>/role.do?method=deleteRole&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_ROLE_DELETE%>" 
                                   + "&roleId=" +roleId + "&roleName" + roleName 
                                   + "<%=param%>";
        else
            return;
    }
</script>
