<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.nl.util.GlobalConst"%>
<%@page import="com.nl.util.SessionData"%>
<%@page import="com.nl.util.SessionConst"%>
<%@page import="com.nl.portal.dt.SysOperator"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="java.net.URLEncoder"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	SessionData sessdata = (SessionData) request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
	SysOperator operator = sessdata.getSysOperator();
	Map meauMap = sessdata.getTreeMap();
	//��ͬ�Ĳ���Ա��¼ϵͳ�˵���������һ�£����ݱ�������˵�����
	int i=0;
	
	//�ж��ܷ��½ԭҵ��֧��ƽ̨
	int oldBizj=sessdata.getOldBizj();
	
	List list = new ArrayList();
	list.addAll(meauMap.keySet());
	

	int m=0;
	int N=0;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>�й������򽭷ֹ�˾ͳһƽ̨ͷ��</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link href="./css/systemHead.css" type="text/css" rel="stylesheet"/>
    <link href="./css/loginCss.css" type="text/css" rel="stylesheet"/>
    <link href="./css/toptitle.css" type="text/css" rel="stylesheet" />
</head>
<style>*{margin:0;padding:0}
</style>
<body>
<input type="hidden" name="welcomFlag" value="1">
<div align="center">
<!-- һ���˵� END -->
</div><table border="0" cellspacing="0" cellpadding="0" class="topLine02">
    <tr>
        <td class="td01" >&nbsp;</td>
        <!-- �����˵� START -->
        <td class="td02"  >
            <div id="wrapper">
                <table border="0" cellspacing="0" cellpadding="0" class="ulList bgp" id="svc-toolbar"  >
                    <tr >
                        
                        <%
                        	for(int n=0;n<list.size();n++)
                        	{
                                int systemId = Integer.valueOf(list.get(n).toString());
                                if(systemId<99)
                                {
                        %>
                        	<td id="menu<%=m%>" <%=m>8?"style='display:none;'":""%> ><a onfocus="this.blur()" id="a3-i" href="#" title='<%=DictMgmt.getValueDescs(DictMgmt.DICT_SYS_SYSTEM_ID,systemId) %>' onclick="doCheck('<%=systemId %>');return false;"><div class="Hs<%=systemId %>"><span class="bgp-fr"></span></div><%=DictMgmt.getValueDescs(DictMgmt.DICT_SYS_SYSTEM_ID,systemId) %></a></td>
                        <%   
                        			N = n;                    		
                        		if(m>8)
                        			break;
                        		}
                        	}
                        %>
                        
						<%m=m+1;%>
                        <td id="menu<%=m%>" <%=m>8?"style='display:none;'":""%>><a onfocus="this.blur()" id="a1-i" href="#" title='���ʲ�ѯ' onclick="openNewWindow('2');return false;"><div class="Hs1"><span class="bgp-fr"></span></div>���ʲ�ѯ</a></td>
                        <%m=m+1;%>
                        <td id="menu<%=m%>" <%=m>8?"style='display:none;'":""%>><a onfocus="this.blur()" id="a2-i" href="#" title='�ó�����' onclick="openNewWindow('1');return false;"><div class="Hs2"><span class="bgp-fr"></span></div>�ó�����</a></td>
                    	
						<%
                        	for(int n=0;n<list.size();n++)
                        	{
                                int systemId = Integer.valueOf(list.get(n).toString());
                                if(systemId==99)
                                {
                        %>
                        	<td id="menu<%=m%>" <%=m>8?"style='display:none;'":""%> ><a onfocus="this.blur()" id="a3-i" href="#" title='<%=DictMgmt.getValueDescs(DictMgmt.DICT_SYS_SYSTEM_ID,systemId) %>' onclick="doCheck('<%=systemId %>');return false;"><div class="Hs<%=systemId %>"><span class="bgp-fr"></span></div><%=DictMgmt.getValueDescs(DictMgmt.DICT_SYS_SYSTEM_ID,systemId) %></a></td>
                        <%   
                        			N = n;                    		
                        		if(m>8)
                        			break;
                        		}
                        	}
                        %>
						
                    </tr>
                </table>
            </div>
        </td>
        <!-- �����˵� END -->
        <td class="td03">
        	<%if(m>8){ %>
            <div class="openbtn" style="margin-left:10px;" id="open" onclick="parent.window.showPop(); this.style.display='none'; document.getElementById('close').style.display='block';parent.document.getElementById('subsHead').src = 'popmenu.jsp?idNum=<%=N %>'; return false;">չ��</div>
            <div class="closebtn" style="margin-left:10px; display:none" id="close" onclick="parent.window.closePop();this.style.display='none'; document.getElementById('open').style.display='block';return false;">����</div>
            <%}else{ %>
            &nbsp;
            <%} %>
        </td>
        <!-- ��פ�˵� START -->
        <td class="td04">
            <ul class="ulList">
           		 
         		 <li class="li01"><b><a href="#" onclick="modifyPassword();return false;" >�޸�����</a></b></li>
                
                <li class="li02"><b><a href="" onclick="returnHome();return false;">������ҳ</a></b></li>
                <li class="li03"><b><a href="" onclick="closeWindow();return false;">�˳�ϵͳ</a></b></li>
            </ul>
        </td>
        <!-- ��פ�˵� END -->
        <td class="td05">&nbsp;</td>
    </tr>
</table>
<!-- icon end --> <!-- tips start:don't changes these id-->
<div id="tt" class="ttm" style="display:none">
    <div class="ttl"></div>
    <div class="ttc">
        <div class="ttdc">
            <div class="ttdl"></div>
            <div class="ttdr"></div>
        </div>
        <div class="tt-text"></div>
        <div class="ttdc">
            <div class="ttdl"></div>
            <div class="ttdr"></div>
        </div>
    </div>
    <div class="ttl"></div>
    <div class="ttvc">
        <div class="ttv"></div>
    </div>
</div>
</body>
<script type="text/javascript" src="./plugins/jquery/jquery.js"></script>
<script type="text/javascript">var $j = jQuery.noConflict()</script>
<script type="text/javascript" src="./js/portal/topTitle.js"></script>
 
<script type="text/javascript"> 

    jQuery(document).ready(function(){
        //parent.frames['show'].change1();
        //parent.addTask('��ӭ��¼','./coremain/portal/welcome.html');
        //Ĭ����ת��ONUϵͳ
        //doCheck('3');
    });
    
    //�˵�ǰ��������ʱʹ��
    var menuNum = 1;
    //�˵���ʾ�ĸ���
    var showMenuNum = 8;
    //�˵�����
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
        //�������в˵�
        for (k=1;k<=totalMenuNum;k++)
        {
            trId = "menu"+k;
            jQuery("#"+trId).hide();
        }
        //�������в˵�
        for (i=1;i<=menuNum;i++)
        {
            hiddenTrId = "menu"+i;
            jQuery("#"+hiddenTrId).hide();
        }
        //�������в˵�
        for (j=menuNum;j<menuNum+showMenuNum;j++)
        {
            showTrId = "menu"+j;
            jQuery("#"+showTrId).show();
        }
    }
    
    function openNewWindow(sysId)
    {
        if (sysId == 2)
            window.open('http://10.43.128.30/gz','','');
        else if (sysId == 1)
            window.open('http://10.43.128.40:8001/web','','');
        else if (sysId == 3)
        	oldBizj.submit();
    }

    //���ϵͳ�˵�ʱ��idΪϵͳ��ţ�showFuncIdĬ����ʾ���ܣ�priϵͳ�˵��鿴Ȩ��
    function doCheck(sysId) 
    {
    	if (document.all.welcomFlag.value == 1 )
        {
        	parent.document.getElementById("leftContent").style.display = 'none' ;
            parent.frames['show'].changeFlag.value = 1;
            parent.frames['show'].change();
            document.all.welcomFlag.value = 0;
        }
        parent.document.getElementById("leftContent").src = 'menu/system_leftCont.jsp?sysId='+sysId;
    }
    function returnHome()
    {
        //parent.parent.window.location.href = "../../index.jsp";
        parent.addTaskNoDelWelcome('��ӭ��¼','./coremain/portal/welcome.html');
    }
    function modifyPassword()
    {
        parent.showD("<%=basePath%>/operator.do?method=preUpdateOperPassword&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_OPERATOR_PASSWORD_UPDATE%>",400,200,"�޸�����",100);
    }
    function closeWindow()
    {
        //parent.parent.parent.window.close();
        parent.parent.window.location.href = "../../index.jsp";
    }

function temp()
{
	parent.window.closePop();
	document.getElementById('close').style.display='none'; 
	document.getElementById('open').style.display='block';
	return false;
}

</script>
</html>
