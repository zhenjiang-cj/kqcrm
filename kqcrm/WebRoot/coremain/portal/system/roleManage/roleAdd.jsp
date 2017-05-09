<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.nl.util.GlobalConst"%>
<%@page import="com.nl.portal.actionForm.RoleForm"%>
<%@page import="com.nl.util.SessionData"%>
<%@page import="com.nl.util.SessionConst"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.portal.dt.SysPrivilege"%>
<%@page import="com.nl.base.utils.GlobalFunc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String contextpath = request.getContextPath();
    SessionData sessdata = (SessionData) request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
    List privilegeList = (List)request.getAttribute("privilegeList");
    String jsonStr = GlobalFunc.getJosnStrForList(privilegeList);
    SysPrivilege sysPrivilege = new SysPrivilege();
    RoleForm roleForm = (RoleForm)request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
    String param = "&sysId=" + roleForm.getSysId()
                 + "&roleName=" + roleForm.getRoleName();
%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>��ɫ����</title>
    <link  href="<%=contextpath%>/css/systemCont.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="<%=contextpath%>/plugins/jquery/plugins/ztree/3.0/css/zTreeStyle/zTreeStyle.css" type="text/css">
</head>
<body class="Contbody">
	<div class="contAllDiv borNO">
		<!--��ʾ������ START-->
		    <div class="divF"></div>
		  	<div class="workCont">
		  	   <form action="<%=contextpath%>/role.do?method=addRole" method="post" name="roleForm">
		  	    <input type="hidden" name="functionId" value="<%=GlobalConst.FUNCTION_ROLE_ADD%>">
		  	    <input type="hidden" name="systemId" value="<%=GlobalConst.SYSTEM_ID_SYSTEM%>">
		  	    <input type="hidden" name="operatorId"  value="<%=sessdata.getSysOperator().getSNo()%>">
		  	    <input type="hidden" id="checkPrivId" name="checkPrivId"  value="">
				<!--������ START-->
				 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="Title01">
				  <tr>
					<td class="td01"></td>
					<td class="td02"><div class="F01">��ɫ����</div><div class="S01"><img src="<%=contextpath%>/images/portal/contTit12.gif"></div></td>
					<td class="td03"></td>
				  </tr>
				</table>
				<!--������ END-->
				<!--���1 START-->
				  <table width="100%"  border="0" cellspacing="1" cellpadding="0"  class="TabList">
					  <tr>			 
						<td class="tit" width="15%">��ɫ����</td>
						<td width="35%">
						    <input id="roleName" name="roleName" type="text" class="inputS" value="" style="width:180px"><font class="Red">*</font>
					    </td>
					    <td class="tit" width="15%">����ϵͳ
						</td>
						<td width="35%">
						    <select id="sysId" name="sysId" class="selOp" onchange="changeSys()">
							    <option value="<%=GlobalConst.GLOBLA_SELECT_VALUE %>"><%=GlobalConst.GLOBAL_SELECT_NAME %></option>
						        <%=DictMgmt.getSelectObj(DictMgmt.DICT_SYS_SYSTEM_ID,"",false,false,"-1", -1, null, null, null,-1,"") %>
					        </select><font class="Red">*</font>
						</td>
					   </tr>
					   <tr>			 
						<td class="tit" width="15%">��ɫ����</td>
						<td colspan="3" width="85%">
						    <textarea id="roleDesc" name="roleDesc" cols="70%" rows="4"></textarea>
					    </td>
					   </tr>
					   <tr>
					    <td class="tit" width="15%">Ȩ���б�</td>
						<td colspan="3" width="85%">
						    <div>
						       <input  id="searchKey" name="searchKey"  type="text" class="inputS" value=""></input>
					           <button class="searchBntSty" onfocus="this.blur();" title="����" onclick="getNodeByParam();"></button>���������ģ��������
					        </div>
						    
						    <div>
								<ul id="dropTree" class="ztree" 
								style="margin-top: 2px;border: 1px solid #617775;background: #ffffff;height:350px; width:100%;overflow-y:auto;overflow-x:auto;"></ul>
							</div>
					    </td>
					   </tr>
				   </table>
				<div class="OpLine">
		         <input id="button1" name="button1"  type="button" value="ȷ ��"  class="bntSty" onClick="doSubmit()"/>
		         <input name=""  type="button" value="�� ��"  class="bntSty" onClick="goBack()"/>
		       </div>
				
			  </form>  	      
			</div>
		  
		<!--��ʾ������ END-->
	</div>
</body>
</html>
<script type="text/javascript" src="<%=contextpath%>/plugins/jquery/jquery-1.4.4.js"></script>
<script type="text/javascript" src="<%=contextpath%>/plugins/jquery/plugins/ztree/3.0/jquery.ztree.core-3.0.js"></script>
<script type="text/javascript" src="<%=contextpath%>/plugins/jquery/plugins/ztree/3.0/jquery.ztree.excheck-3.0.js"></script>
<script type="text/javascript">
    jQuery(document).ready(function(){
        jQuery("#sysId").val("<%=roleForm.getSysId()%>");
        reloadTree();  
    })
    
    <!--���غ�������������ʼ!-->
    var zTree1;
	var setting;
	var nodeList = [];

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
    
    //չ��ĳһ��
	function expandNode() 
	{
	    var treeNode = zTree1.getNodeByParam("priv_level", 1);
	    
		if (treeNode) {
		    //��һ������treeNodeΪҪչ���Ľڵ㣬�ڶ���������true����ʾ�ü�չ����������������false����ʾ�¼���չ��
			zTree1.expandNode(treeNode, true, true);
			//node����Ϊ��ѡ��״̬
			//zTree1.selectNode(treeNode);
		}
	}
	
	function zTreeOnChange()
	{
	    //�����ŵ��ύ��ʱ��ִ��
	    //getCheckedNodesId();
	}
	
	function getCheckedNodesId()
	{
	    var checkPrivId = "";
	    
	    var tmp = zTree1.getCheckedNodes();
	    for (var i=0;i<tmp.length;i++)
	    {
	       checkPrivId +=  tmp[i].privilege_id + ",";
	    }
	    if (checkPrivId != "")
	    {
	        checkPrivId = checkPrivId.substr(0,checkPrivId.length-1);
	    }

	    $("#checkPrivId").val(checkPrivId);
	    
	}
	
	function getNodeByParam() {
		var key = $("#searchKey").val();
		if (key.length > 0)
		{
		    updateNodes(false);
			nodeList = zTree1.getNodesByParamFuzzy("privilege_name", key);
			
			if (nodeList && nodeList.length>0) 
			{
				updateNodes(true);
			} 
			else 
			{
				alert("û���ҵ�ƥ��Ľڵ㣬�������������");
			}
	    }
	}
	
	function updateNodes(highlight)
	{
		for( var i=0, l=nodeList.length; i<l; i++) 
		{
			nodeList[i].highlight = highlight;
			zTree1.updateNode(nodeList[i]);
		}
	}
	
	function getFontCss(treeId, treeNode) 
	{
		return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
	}
    
	function reloadTree() {
	    /**���ӹ�����ϵ�趨��"Y"��ʾ����ѡʱ��"N"��ʾȡ����ѡʱ��"p"��ʾ������,"s"��ʾ������	
	       �磺ps��ʾִ�ж�Ӧ������ʱ��������������ӣ�"s"��ʾִ�ж�Ӧ�Ĳ�����ʱ��ֻ������
	    **/   
	    setting.check.chkboxType = { "Y" : "ps", "N" : "s" };
		$.fn.zTree.init($("#dropTree"), setting, zNodes);
		zTree1 = $.fn.zTree.getZTreeObj("dropTree");
		expandNode();
	} 
    <!--���غ���������������!-->
    
    
    function changeSys()
    {
        window.location.href="./role.do?method=preAddRole&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_ROLE_ADD%>" 
                             + "&sysId=" + $("#sysId").val()
                             + "<%=param%>";
    }
    function doSubmit()
    {
        $("#button1").attr("disabled","disabled");
        getCheckedNodesId();
        
        if($("#roleName").val()==''||$("#roleName").val()=='null'){
			alert('����д��ɫ���ƣ�');
			$("#button1").attr("disabled",""); //ȡ���һ�
			$("#roleName").focus();
			return false;
   		} 
        
        if($("#sysId").val()==''||$("#sysId").val()=='null'){
			alert('��ѡ�����ϵͳ��');
			$("#button1").attr("disabled",""); //ȡ���һ�
			$("#sysId").focus();
			return false;
   		} 
   		
   		if($("#roleType").val()==''||$("#roleType").val()=='-99'){
			alert('��ѡ���ɫ���ͣ�');
			$("#button1").attr("disabled",""); //ȡ���һ�
			$("#roleType").focus();
			return false;
   		} 
   		
        roleForm.submit();
    }
    function goBack()
    {
        window.location.href="./role.do?method=queryList&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_ROLE_MANAGE%>" + "<%=param%>";
    }
</script>
