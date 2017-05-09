<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.nl.util.SessionData"%>
<%@page import="com.nl.util.SessionConst"%>
<%@page import="com.nl.base.utils.TreeBuilder"%>
<%@page import="com.nl.base.utils.BASTree"%>
<%@page import="com.nl.portal.dt.SysLoginLog" %>
<%@page import="com.nl.portal.sc.SystemSC" %>
<%@page import="com.nl.util.GlobalConst"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String sysid = request.getParameter("sysId") == null?"3":request.getParameter("sysId");
    String page_id = request.getParameter("page_id") == null?"":request.getParameter("page_id");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>镇江人寿统一平台左侧</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="./css/portal.css" type="text/css" rel="stylesheet"/>
    <link  href="./css/systemLeft.css" rel="stylesheet" type="text/css">
    <script language="javascript" src="./js/portal/tree_builder.js"></script>
</head>
<style>*{margin:0;padding:0}
</style>
<body scroll='auto'>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="leftTab">
  <tr>
    <td class="R">
	<!-- 菜单 START -->

		<table border="0" cellspacing="0" cellpadding="0" class="menuList">
		  <tr>
			<td>
			    <%
			        SessionData sessdata = (SessionData) request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
			    
			        //如果登录超时
				    if (sessdata == null)
				    {	
			    %>
			       <script>
				       parent.parent.parent.window.location.href = "../../../index.jsp";
				   </script>  
			    <%
				    }
	                try {
		                    BASTree basTree = null;
		                    basTree = sessdata.getTreeMap().get(sysid);
		                    if (basTree != null && basTree.getNext() != null)
		                    {		                    	
		                        TreeBuilder treeBuilder = new TreeBuilder();
			                    treeBuilder.setPicPath("./images/tree/");
			                    treeBuilder.setSelectType(0);
			                    long firstID = -1;
			                    int i = 0 ;
			                    boolean isDefault = true;
			
			                    if (basTree != null) 
			                    {			                    	
			                        treeBuilder.addTreeNode(true, basTree.getName(), basTree.getId(), basTree.getLevel(), true,"" , true, false,basTree.pageDesc);
			                        while (basTree.getNext() != null) 
			                        {
			                            basTree = basTree.getNext();
			                            if (basTree.isFather())
			                            {
			                                treeBuilder.addTreeNode(true, basTree.getName(), basTree.getId(), basTree.getLevel(), basTree.isLast(), "", true, false,basTree.pageDesc);
			                            } 
			                            else 
			                            {
			                                if (isDefault) {
			                                    isDefault = false;
			                                    //if(basTree.getId()==48010)
			                                    //	treeBuilder.addTreeNode(false, basTree.getName(), basTree.getId(), basTree.getLevel(), basTree.isLast(), null, null, "parent.addTask('" + basTree.getName() + "','" + basTree.getDescription()+"?user="+sessdata.getSysOperator().getSNo()+"');return false;", false, true, true);
			                                    //else
			                                    	treeBuilder.addTreeNode(false, basTree.getName(), basTree.getId(), basTree.getLevel(), basTree.isLast(), null, null, "parent.addTask('" + basTree.getName() + "','" + basTree.getDescription()+"');return false;", false, true, true,basTree.pageDesc);
			                                   if(!"".equals(page_id)){
			                                  	 firstID = Long.parseLong(page_id);
			                                   }else{
			                                  	 firstID = basTree.getId() ;
			                                   }
			                                    
			                                } else {
			                                    treeBuilder.addTreeNode(false, basTree.getName(), basTree.getId(), basTree.getLevel(), basTree.isLast(), "parent.addTask('" + basTree.getName() + "','" + basTree.getDescription()+"');return false;", false, true,basTree.pageDesc);
			                                }
			                            }
			                        }
		                        }
		                        out.println(treeBuilder.getTreeString("funcTree"));
		                        out.println("<script>oElement2=document.all(\"_" + firstID + "\");oElement2.click();</script>");
		                        
					        
	                    }
	                    else
	                    {
	                        out.println("您没有对应菜单权限！");
	                    }
	                     
	                    
	                }
	                catch (Exception e)
	                {
	                    out.println("系统异常!");
	                }
            %>
			</td>
		  </tr>
		</table>

	<!-- 菜单 END -->

	  </td>
  </tr>
</table>
</body>
</html>
