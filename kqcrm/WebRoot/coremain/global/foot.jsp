<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.nl.ql.sc.QlManageMgmt"%>
<%@page import="com.nl.ql.dt.WebsiteVisit"%>
<%@page import="com.nl.util.GlobalUtil"%>

<%
//查询网站访问量信息
QlManageMgmt qlsc = new QlManageMgmt("guest");
WebsiteVisit visit = qlsc.queryVisitCount();

 %>

<!--包含foot页面-->


  <!--右侧浮动--开始-->
  <div>
  	<script language="javascript" type="text/javascript" src="js/qlsx/rfloat.js"></script>
  </div>
  <!--右侧浮动--结束-->
  
  
  <!--foot--开始-->

<script type="text/javascript">

 function writeNum(num){
	 var url="<%=GlobalUtil.getPropertiesText("struts-config_res","htzj_uri") %>/htzj/publish/images/fwl/";
	 var carray=num.split("");
	 if(carray.length>0){
		 for(var i=0;i<carray.length;i++){
			 document.write("<img src='"+url+carray[i]+".gif' align='absmiddle'>");
		 }
	 }
 }
 
 
</script>

  <!--foot--开始-->
  <div style="background-color:#D8D8D8; height:4px;"></div>
  <div class="foot" align="center">
    <table border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td rowspan="2">&nbsp;</td>
        <td rowspan="2" align="center">镇江市住建局主办<br>
          @Copyright 2016 Zhenjiang China All Rights Reserved<br>
          苏ICP备10205253号 - 1</td>
        <td width="120" rowspan="2" align="center">&nbsp;
        <a href="http://bszs.conac.cn/sitename?method=show&id=0973512168DB66BCE053012819AC08CC" target="_blank"><img name="index_r11_c15" src="images/htzj/index_r11_c15.png" width="55" height="70" border="0" /></a>        </td>
        <td bgcolor="#EFEFF0"><h5>今日访问：</h5>                </td>
        <td id="daycount" bgcolor="#EFEFF0">
        	<script type="text/javascript">
    			writeNum("<%=visit.getDaycount()%>");
        </script>
        </td>
      </tr>
      <tr>
        <td bgcolor="#EFEFF0"><h5>总访问量：</h5> </td>
        <td id="yearcount" bgcolor="#EFEFF0">
        <script type="text/javascript">
    		writeNum("<%=visit.getYearcount()%>");
        </script>
        </td>
      </tr>
    </table>
  </div>
  <!--foot--结束-->

  <!--foot--结束-->

<!--包含foot页面-->