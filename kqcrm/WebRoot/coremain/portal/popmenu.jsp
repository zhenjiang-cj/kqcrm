<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.nl.util.GlobalConst"%>
<%@page import="com.nl.util.SessionData"%>
<%@page import="com.nl.util.SessionConst"%>
<%@page import="com.nl.portal.dt.SysOperator"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	SessionData sessdata = (SessionData) request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
	SysOperator operator = null;;
	List list = new ArrayList();
	int i=0;
	int idNum = Integer.valueOf(request.getParameter("idNum"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>无标题文档</title>
<link href="<%=basePath %>css/systemHead.css"  type="text/css" rel="stylesheet"/>
<link href="<%=basePath %>css/toptitle.css" type="text/css" rel="stylesheet" />
</head>

<body>
<!--20130401modify START-->   

<input type="hidden" name="welcomFlag" value="1">
<%
	if(sessdata==null)
	{
%>
<script>
    parent.parent.parent.window.location.href = "../../index.jsp";
</script> 
<%
	}else {
		operator = sessdata.getSysOperator();
		Map meauMap = sessdata.getTreeMap();
		
		int oldBizj=sessdata.getOldBizj();
		
		list.addAll(meauMap.keySet());
		for(int a=0;a<list.size();a++)
		{
			int aId = Integer.valueOf(list.get(a).toString());
			int cId = 0;
			for(int b=a+1;b<list.size();b++)
			{
				int bId = Integer.valueOf(list.get(b).toString());
				if(bId<aId)
				{
					cId = bId;
					list.set(b,aId);
					list.set(a,cId);
					aId = cId;
				}
			}
		}
%>
	<table id="popmenu_tb" border="0" cellspacing="0" cellpadding="0" class=" popmenu">
    <tr class=" popmenubg">
    	<td  class="border_l" ></td>
        <td>
        	<table border="0" cellpadding="0" cellspacing="0" class="topLine02">
            	<tr>
                    <!-- 二级菜单 START -->
                    <td class="td02"  >
                        <div id="wrapper">
                        <form id="oldBizj" name="oldBizj" action="<%=GlobalConst.URL_OLD_BIZJ%>" method="post" target="_blank">
		            	<input type="hidden" name="user_id" value="<%=operator.getUser_id()%>" />
		            	<input type="hidden" name="user_pswd" value="<%=operator.getUserPassword1()%>" />
                            <table border="0" cellspacing="0" cellpadding="0" class="ulList bgp" id="svc-toolbar"  >
                                <tr >
                    	<%
                    		if(list!=null&&list.size()>1){
	                    		for(int k=idNum;k<list.size();k+=8)
	                    		{
	                    		%>
		                        	<tr>
		                        <%
	                    			for(int n=k;n<k+8;n++)
		                        	{
		                        		if(n<list.size())
		                        		{
			                                int systemId = Integer.valueOf(list.get(n).toString());
			                                if(systemId!=2&&systemId!=1)
			                                {
				                        		i=i+1;
			                        %>
			                        			<td id="menu<%=i%>" ><a onfocus="this.blur()" id="a3-i" href="#" title='<%=DictMgmt.getValueDescs(DictMgmt.DICT_SYS_SYSTEM_ID,systemId) %>' onclick="doCheck('<%=systemId %>');return false;"><div class="Hs<%=systemId %>"><span class="bgp-fr"></span></div><%=DictMgmt.getValueDescs(DictMgmt.DICT_SYS_SYSTEM_ID,systemId) %></a></td>
			                        <%
			                        		}
			                        	}else
			                        	{
			                        	%>
				                        	<td>&nbsp;</td>
				                        <%
			                        	}
		                        	}
		                        %>
		                        	</tr>
		                        <%
	                    		}	                        	
	                        }else{
                        %>
                        		&nbsp;
                        <%
                        	}
                        %>
                            </table>
                        </div>
                    </td>
                    <!-- 二级菜单 END -->
                </tr>
                
            </table>
        </td>
        <td  class="border_r" ></td>
        </tr>
        <tr style="overflow:hidden; line-height:0">
            <td class="popmenu_btm_l" width="4"></td>
            <td class="popmenu_btm_c"></td>
            <td class="popmenu_btm_r" width="4"></td>
        </tr>
    </table>
</body>
<script type="text/javascript" src="./plugins/jquery/jquery.js"></script>
<script type="text/javascript" src="./js/portal/topTitle.js"></script>
    <!--20130401modify END-->   
    <script type="text/javascript">
		function getHeight(){
			var popmenuHeight = document.getElementById('popmenu_tb').offsetHeight + "px";
			parent.document.getElementById("subsHead").style.height=popmenuHeight; 			
		}
		 window.onload = function() {
	          getHeight();
	      }
	      
    
    //菜单前进、后退时使用
    var menuNum = 1;
    //菜单显示的个数
    var showMenuNum = 8;
    //菜单总数
    var totalMenuNum = <%=i%>;

    function menuGo()
    {
        if (menuNum+showMenuNum <= totalMenuNum)
        {
            menuNum = menuNum+1;
            showMenu();
        }
    }
    
    function menuBack()
    {
        if (menuNum > 1)
        {
            menuNum = menuNum-1;
            showMenu();
        }
    }
    
    function showMenu()
    {
        //隐藏所有菜单
        for (k=1;k<=totalMenuNum;k++)
        {
            trId = "menu"+k;
            jQuery("#"+trId).hide();
        }
        //隐藏所有菜单
        for (i=1;i<=menuNum;i++)
        {
            hiddenTrId = "menu"+i;
            jQuery("#"+hiddenTrId).hide();
        }
        //隐藏所有菜单
        for (j=menuNum;j<menuNum+showMenuNum;j++)
        {
            showTrId = "menu"+j;
            jQuery("#"+showTrId).show();
        }
    }
    
    function openNewWindow(sysId)
    {
        if (sysId == 2)
            window.open('<%=GlobalConst.URL_BIZJ%>?portal=1&userNo=<%=operator.getUser_id() %>&password=<%=operator.getUserPassword1()%>&channelFlag=1','','');
        else if (sysId == 1)
            window.open('<%=GlobalConst.URL_BXZJ%>?portal=1&userNo=<%=operator.getUser_id()%>&password=<%=operator.getUserPassword1()%>','','');
        else if (sysId == 3)
        	oldBizj.submit();
    }

    //点击系统菜单时，id为系统编号，showFuncId默认显示功能，pri系统菜单查看权限
    function doCheck(sysId) 
    {
    	if (document.all.welcomFlag.value == 1 )
        {
        	parent.document.getElementById("leftContent").style.display = 'none' ;
            parent.frames['show'].changeFlag.value = 1;
            parent.frames['show'].change();
            document.all.welcomFlag.value = 0;
        }
        //var f = parent.frames['leftContent'].location.href = "<%=basePath%>/menu/system_leftCont.jsp?sysId="+sysId;
        parent.document.getElementById("leftContent").src = 'menu/system_leftCont.jsp?sysId='+sysId;
        parent.frames["top_head"].temp();
    	/*
        if (document.all.welcomFlag.value == 1 )
        {
        	//alert('cccccccc==='+parent.document.all.show.changeFlag.value);
            alert('cccccccc==='+parent.frames['show'].changeFlag.value);
            parent.document.all.show.changeFlag.value = 1;
            
            
            parent.document.all.show.change();
            document.all.welcomFlag.value = 0;
        }
        parent.document.all.content.style.display = 'block';
        var f = parent.document.all.leftContent.location.href = "./menu/system_leftCont.jsp?sysId="+sysId;
        */
    }
    function returnHome()
    {
        parent.parent.window.location.href = "../../index.jsp";
    }
    function modifyPassword()
    {
        parent.showD("<%=basePath%>/operator.do?method=preUpdateOperPassword&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_OPERATOR_PASSWORD_UPDATE%>",400,200,"修改密码",100);
    }
    function closeWindow()
    {
        parent.parent.parent.window.close();
    }
	   
	</script>
	
<%
}
%>
</html>
