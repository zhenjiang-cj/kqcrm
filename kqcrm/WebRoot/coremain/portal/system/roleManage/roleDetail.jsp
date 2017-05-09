<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.nl.util.GlobalConst"%>
<%@page import="com.nl.portal.actionForm.RoleForm"%>
<%@page import="com.nl.util.SessionData"%>
<%@page import="com.nl.util.SessionConst"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.portal.dt.SysPrivilege"%>
<%@page import="com.nl.portal.dt.SysOperatorRole"%>
<%@page import="com.nl.base.utils.GlobalFunc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String contextpath = request.getContextPath();
    SessionData sessdata = (SessionData) request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
    List privilegeList = (List)request.getAttribute("privilegeList");
    List allPrivilegeList = (List)request.getAttribute("allPrivilegeList");
    
    String jsonStr = GlobalFunc.getJosnStrForList(allPrivilegeList);
    SysOperatorRole operatorRole = (SysOperatorRole)request.getAttribute("operatorRole");
    SysPrivilege sysPrivilege = new SysPrivilege();
    RoleForm roleForm = (RoleForm)request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
    String param = "&sysId=" + roleForm.getSysId()
                 + "&roleName=" + roleForm.getRoleName();
%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>角色详细信息</title>
    <link  href="<%=contextpath%>/css/systemCont.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="<%=contextpath%>/plugins/jquery/plugins/ztree/3.0/css/zTreeStyle/zTreeStyle.css" type="text/css">
</head>
<body class="Contbody">
	<div class="contAllDiv borNO">
		<!--显示工作区 START-->
		    <div class="divF"></div>
		  	<div class="workCont">
		  	   <form action="<%=contextpath%>/role.do?method=addRole" method="post" name="roleForm">
		  	    <input type="hidden" name="functionId" value="<%=GlobalConst.FUNCTION_ROLE_ADD%>">
		  	    <input type="hidden" name="systemId" value="<%=GlobalConst.SYSTEM_ID_SYSTEM%>">
		  	    <input type="hidden" name="operatorId"  value="<%=sessdata.getSysOperator().getSNo()%>">
				<!--标题栏 START-->
				 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="Title01">
				  <tr>
					<td class="td01"></td>
					<td class="td02"><div class="F01">角色详细信息</div><div class="S01"><img src="<%=contextpath%>/images/portal/contTit12.gif"></div></td>
					<td class="td03"></td>
				  </tr>
				</table>
				<!--标题栏 END-->
				<!--表格1 START-->
				  <table width="100%"  border="0" cellspacing="1" cellpadding="0"  class="TabList">
					  <tr>			 
						<td class="tit" width="15%">角色名称</td>
						<td width="35%">
						    <%=operatorRole.getRole_name()%>
					    </td>
					    <td class="tit" width="15%">归属系统
						</td>
						<td width="35%">
						    <%=DictMgmt.getValueDescs(DictMgmt.DICT_SYS_SYSTEM_ID,operatorRole.getSysid())%>
						</td>
					   </tr>
					   <tr>			 
						<td class="tit" width="15%">角色描述</td>
						<td colspan="3" width="85%">
						    <textarea id="roleDesc" name="roleDesc" cols="70%" rows="4"><%=GlobalFunc.initStr(operatorRole.getRemark()) %></textarea>
					    </td>
					   </tr>
					   <tr>
					    <td class="tit" width="15%">权限列表</td>
						<td colspan="3" width="85%">
						    <div>
								<ul id="dropTree" class="ztree" 
								style="margin-top: 10px;border: 1px solid #617775;background: #ffffff;height:350px; width:100%;overflow-y:auto;overflow-x:auto;"></ul>
							</div>
					    </td>
					   </tr>
				   </table>		       
		      
				    <div class="OpLine">
			         <input name=""  type="button" value="返 回"  class="bntSty" onClick="history.go(-1)"/>
			        </div>

			 </form>  	      
			</div>
		<!--显示工作区 END-->
	</div>
</body>
</html>
<script type="text/javascript" src="<%=contextpath%>/plugins/jquery/jquery-1.4.4.js"></script>
<script type="text/javascript" src="<%=contextpath%>/plugins/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="<%=contextpath%>/plugins/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=contextpath%>/plugins/jquery/jquery-ui-1.7.js"></script> 
<script type="text/javascript" src="<%=contextpath%>/plugins/jquery/plugins/ztree/3.0/jquery.ztree.core-3.0.js"></script>
<script type="text/javascript" src="<%=contextpath%>/plugins/jquery/plugins/ztree/3.0/jquery.ztree.excheck-3.0.js"></script>
<script type="text/javascript">
    jQuery(document).ready(function(){
        jQuery("#sysId").val("<%=roleForm.getSysId()%>");
        reloadTree();
    })
    
    <!--加载和设置树方法开始!-->
    var zTree1;
	var setting;
	

	setting = {
		check: {
				enable: true
			   },
		data: {
			key: {
				name: "privilege_name"
			     },
			simpleData: {
				enable: true,
				idKey: "privilege_id",
		        pIdKey: "parent_id"	
			    }				
			},
		view: {
			fontCss: getFontCss
		}
	};

	var zNodes = <%=jsonStr%>;
	
	/*初始化时默认选中已经角色对应的权限*/
	function initSelNodes()
	{
	    <%
	        if (privilegeList.size()>0)
	        {
	            for (int i=0;i<privilegeList.size();i++)
	            {
	                sysPrivilege = (SysPrivilege)privilegeList.get(i);
	            
	    %>
	    var treeNodes = zTree1.getNodeByParam("privilege_id", <%=sysPrivilege.getPrivilege_id()%>);
	    treeNodes.checked = true;
	    zTree1.updateNode(treeNodes);
	    <%
	            }
	        }
	    %>
	}
	
	function setDisable()
	{
		var nodes = zTree1.getCheckedNodes();
		for (var i=0, l=nodes.length; i < l; i++) 
		{
			zTree1.setChkDisabled(nodes[i], true);
		}
	    
	}
	
	function getFontCss(treeId, treeNode) 
	{
		return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
	}
    
	function reloadTree() {
		jQuery.fn.zTree.init(jQuery("#dropTree"), setting, zNodes);
		//获得tree对象
		zTree1 = jQuery.fn.zTree.getZTreeObj("dropTree");
		//展开所有的节点
		zTree1.expandAll(true);
		//初始化选中值
		initSelNodes();
		//设置值不能修改
		setDisable();
	} 
    <!--加载和设置树方法结束!-->
    
    
    function goBack()
    {
        window.location.href="./role.do?method=queryList&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_ROLE_MANAGE%>" + "<%=param%>";
    }
</script>
