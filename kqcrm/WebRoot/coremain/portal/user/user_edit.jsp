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
	List<UserInfo> userlist =  (List<UserInfo>) request.getAttribute("userlist");
	UserInfo user = userlist.get(0);
    List regionList = (List)request.getAttribute("regionList");
    String jsonStr = GlobalFunc.getJosnStrForList(regionList);
    
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
	
	<script src="<%=path%>/dwz/js/jquery.validate.js" type="text/javascript"></script>
	<script src="<%=path%>/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(function(){
		jQuery("#region").val('<%=user.getRegion()  %>');	
		
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
				name: "org_name"
			     },
			simpleData: {
				enable: true,
				idKey: "org_id",
		        pIdKey: "parent_org_id"	
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
	
	    var treeNode = zTree1.getNodeByParam("org_id", 1);
	    
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
	       checkPrivId +=  tmp[i].org_id + ",";
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
			nodeList = zTree1.getNodesByParamFuzzy("org_name", key);
			
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
	        if (regionList.size()>0)
	        {
	            for (int i=0;i<regionList.size();i++)
	            {
	                UserInfo priv = (UserInfo)regionList.get(i);
	            
	    %>
	    var treeNodes = zTree1.getNodeByParam("org_id", <%=priv.getOrg_id()%>);
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
 
	</script>
	
  </head>
  
  <body>
    <div class="pageContent">
  <form class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)" action="<%=path%>/userAction.do?method=doUserEdit" method="post" name="userForm">
    <input type="hidden" name="sno" id="sno" value="<%=user.getSno() %>">
    <input type="hidden" name="operatorId" id="operatorId" value="<%=userform.getOperatorId() %>">
	<input type="hidden" id="checkPrivId" name="checkPrivId"  value="<%=user.getRegion() %>" class="required" >
    
    <div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
    </div>
    
    <div class="pageFormContent nowrap" layoutH="56" style="height:100px">
    <h1>用户修改</h1>
		 
		<p>
			<label>用户帐号：</label>
			<input name="user_id" type="text"   maxlength="10"  class="required" value="<%=user.getUser_id() %>" readonly/>
		</p>
		<p>
			<label>用户名称：</label>
			<input name="user_name" type="text"  maxlength="20"  class="required"  value="<%=user.getUser_name() %>"  />
		</p> 
		 
		<p>
			<label>手机号码：</label>
			<input name="msisdn" type="text"  maxlength="11"  class="digits"  value="<%=user.getMsisdn() %>"  />
		</p>
		<p>
			<label>email：</label>
			<input name="email" type="text"  maxlength="40"  class="email"  value="<%=user.getEmail() %>" />
		</p>
		 
		<p>
			<label>归属区域：</label>
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
