<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.portal.dt.*"%>
<%@ page import="com.nl.portal.actionForm.*"%>
<%@page import="java.util.List"%>
<%@ page import="com.nl.util.GlobalConst"%>
<%@page import="com.nl.base.utils.GlobalFunc"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	UserForm userform = (UserForm) request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
    List privilegeList = (List)request.getAttribute("privilegeList");
    List privrolelist = (List)request.getAttribute("privrolelist");
    String jsonStr = GlobalFunc.getJosnStrForList(privilegeList);
	List<UserInfo> rolelist =  (List<UserInfo>) request.getAttribute("rolelist");
	UserInfo user = rolelist.get(0);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'user_legal.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	--> 
	
	<script type="text/javascript" src="<%=path%>/plugins/jquery/plugins/ztree/3.0/jquery.ztree.core-3.0.js"></script>
	<script type="text/javascript" src="<%=path%>/plugins/jquery/plugins/ztree/3.0/jquery.ztree.excheck-3.0.js"></script>
	<script type="text/javascript">
	$(function(){
		jQuery("#sysid").val("<%=userform.getSysid()%>");
        //reloadTree();  
        changeSys();
	});

	 
<!--加载和设置树方法开始!-->
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
    
    //展开某一级
	function expandNode() 
	{
	    var treeNode = zTree1.getNodeByParam("priv_level", 1);
	    
		if (treeNode) {
		    //第一个参数treeNode为要展开的节点，第二个参数“true”表示该级展开，第三个参数“false”表示下级不展开
			zTree1.expandNode(treeNode, true, true);
			//node设置为被选中状态
			//zTree1.selectNode(treeNode);
		}
	}
	
	function zTreeOnChange()
	{
	    //方法放到提交的时候执行
	    getCheckedNodesId();
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
				alert("没有找到匹配的节点，请更换搜索条件");
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
	    /**父子关联关系设定："Y"表示被勾选时，"N"表示取消勾选时，"p"表示关联父,"s"表示关联子	
	       如：ps表示执行对应操作的时候关联父、关联子，"s"表示执行对应的操作的时候只关联子
	    **/   
	    setting.check.chkboxType = { "Y" : "ps", "N" : "s" };
		$.fn.zTree.init($("#dropTree"), setting, zNodes);
		zTree1 = $.fn.zTree.getZTreeObj("dropTree");
		expandNode();
		initSelNodes();
	} 
	
	/*初始化时默认选中已经角色对应的权限*/
	function initSelNodes()
	{
	    <%
	        if (privrolelist.size()>0)
	        {
	            for (int i=0;i<privrolelist.size();i++)
	            {
	                UserInfo priv = (UserInfo)privrolelist.get(i);
	            
	    %>
	    var treeNodes = zTree1.getNodeByParam("privilege_id", <%=priv.getPrivilege_id()%>);
	    if(treeNodes){
		     treeNodes.checked = true;
		    zTree1.updateNode(treeNodes);
	    }
	   
	    <%
	            }
	        }
	    %>
	}
	
    <!--加载和设置树方法结束!-->
    
    
    function changeSys()
    {
    	var sysid =  document.getElementById("sysid").value;
        //根据功能，选择对应的权限
        try{
		$.ajax({
			    type:"post",
				dataType:"json",
				contentType:"application/json;charset=UTF-8",
				url:'<%=path%>/coremain/portal/user/getPrivilegeBySysid.jsp?sysid='+sysid,
			    success:function(data){
		           zNodes = data;
		           reloadTree();
			    },
			    error:function (data){
			        alert("获取权限失败！");
			    }
			});
		}catch(e){
			alert(e);
		}
        
    }
    
    function validateCallback(form, callback, confirmMsg) {
    	getCheckedNodesId();
    	
		var $form = $(form);
	
		if (!$form.valid()) {
			return false;
		}
		
		var _submitFn = function(){
			$.ajax({
				type: form.method || 'POST',
				url:$form.attr("action"),
				data:$form.serializeArray(),
				dataType:"json",
				cache: false,
				success: callback || DWZ.ajaxDone,
				error: DWZ.ajaxError
			});
		}
		
		if (confirmMsg) {
			alertMsg.confirm(confirmMsg, {okCall: _submitFn});
		} else {
			_submitFn();
		}
		
		return false;
	}
    

	</script>
	
  </head>
  
  <body>
    <div class="pageContent">
  <form class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)" action="<%=path%>/userAction.do?method=doRoleEdit" method="post" name="userForm">
    <input type="hidden" name="role_id" id="role_id" value="<%=user.getRole_id() %>">
    <input type="hidden" name="operatorId" id="operatorId" value="<%=userform.getOperatorId() %>">
	<input type="hidden" id="checkPrivId" name="checkPrivId"  value="">
    
    <div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">修改</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
    </div>
    
    <div class="pageFormContent nowrap" layoutH="56" style="height:100px">
    <h1>角色新增</h1>
		 
		<p>
			<label>角色名称：</label>
			<input name="role_name" type="text"   maxlength="10"  class="required" value="<%=user.getRole_name() %>" />
		</p>
		<p>
			<label>角色描述：</label>
			<input name="role_remark" type="text"  maxlength="20"  value="<%=user.getRole_remark() %>"  />
		</p> 
		<p>
			<label>归属功能：</label>
			<select id="sysid" name="sysid" class="required" onchange="changeSys()">
			        <%=DictMgmt.getSelectObj(DictMgmt.DICT_SYS_SYSTEM_ID,"",false,false,"-1", -1, null, null, null,-1,"") %>
		    </select> 
		</p>
		<p>
			<label>对应权限：</label>
			<!-- 
			<div>
		       <input  id="searchKey" name="searchKey"  type="text" class="inputS" value=""></input>
	           <button class="searchBntSty" onfocus="this.blur();" title="搜索" onclick="getNodeByParam();"></button>（点击进行模糊搜索）
	        </div>
		     -->
		    <div>
				<ul id="dropTree" class="ztree" 
				style="margin-top: 2px;border: 1px solid #617775;background: #ffffff;height:350px; width:100%;overflow-y:auto;overflow-x:auto;"></ul>
			</div>
		</p>
		 
		
	</div>
	</form>
	</div>
  </body>
</html>
