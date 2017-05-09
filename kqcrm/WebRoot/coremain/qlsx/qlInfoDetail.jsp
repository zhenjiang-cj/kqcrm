<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.List"%>
<%@page import="com.nl.ql.util.AppConst"%>
<%@page import="com.nl.ql.dt.KmQlInfo"%>
<%@page import="com.nl.ql.actionForm.QlForm"%>
<%@page import="com.nl.util.GlobalUtil"%>
<!--包含head页面-->

<%List<KmQlInfo> list = (List<KmQlInfo>)request.getAttribute("qlinfolist"); %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=AppConst.TITLE_INFO %></title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/category.css" />
<link rel="stylesheet" type="text/css" href="css/rfloat.css" />

<script language="javascript" type="text/javascript" src="plugins/jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="js/json2.js"></script>


<script type="text/javascript">
$(function(){
	var screenWidth=window.screen.width;
	var screenHeight=window.screen.height;
	
	if(document.body.clientWidth <=1024){
		window.resizeTo(screenWidth, screenHeight);
	}
});
<%
String isCharge = null;
if(list.get(0).getCHARGE_FLAG()!=null&&"1".equals(list.get(0).getCHARGE_FLAG()))
	isCharge = "是";
else if(list.get(0).getCHARGE_FLAG()!=null&&"2".equals(list.get(0).getCHARGE_FLAG()))
	isCharge = "否";
else
	isCharge = "";

//String aa= null;
try{
	GlobalUtil.xmlTransTable(list.get(0).getSERVICE_TABLE());
}catch(Exception e){
	
}
%>


</script>
</head>
<body>
<div id='barrierfree_container'>

<!--loginBar-->
<jsp:include page="../global/head.jsp" />

  
<!--包含head页面-->

<!--页面主体--开始-->
<div class="page_body_main">
	<div class="page_main">
		
				<div class="page_title">
					<%=list.get(0).getQl_name() %>
				</div>
				<div class="page_info">
					<table width="100%" border="0" cellspacing="0" cellpadding="10">
						<tr>
							<td>
								&nbsp;
							</td>
							<td>
								创建时间：
								<%=list.get(0).getCreate_date() %>
							</td>
							<td>
								
							</td>
							<td>
								
							</td>
							<td>
								字体调整：<a href="javascript:fontZoom('page_content',1);">大</a> 
								<a href="javascript:fontZoom('page_content',2);">中</a> 
								<a href="javascript:fontZoom('page_content',3);">小</a>
							</td>
						</tr>
					</table>
				</div>

				<div id="page_content" class="page_content">
					<table width="100%" border="1" cellspacing="0" cellpadding="10">
						<tr>
							<td width="15%">
								权力类型
							</td>
							<td width="35%">
								<%=list.get(0).getQl_type_name() %>
							</td >
							<td width="15%">
								实施主体名称
							</td>
							<td width="35%">
								<%=list.get(0).getQl_dep_name() %>
							</td>
						</tr>
						<tr>
							<td width="10%">
								生效时间
							</td>
							<td width="40%">
								<%=list.get(0).getStart_time() %>
							</td >
							<td>
								废止时间
							</td>
							<td>
								<%=list.get(0).getEnd_time() %>
							</td>
						</tr>
						<tr>
							<td width="10%">
								权力更新类型
							</td>
							<td width="40%">
								<%=list.get(0).getUpdate_type_name() %>
							</td >
							<td>
								实施主体性质
							</td>
							<td>
								<%=list.get(0).getQL_DEPSTATE_NAME() %>
							</td>
						</tr>
						<tr>
							<td width="10%">
								委托机关
							</td>
							<td width="40%">
								<%=list.get(0).getENTRUST_NAME() %>
							</td >
							<td>
								权力状态
							</td>
							<td>
								<%=list.get(0).getQL_DEPSTATE_NAME() %>
							</td>
						</tr>
						<tr>
							<td width="10%">
								权力法律依据
							</td>
							<td colspan="3">
								<%=list.get(0).getBY_LAW() %>
							</td >
						</tr>
						<tr>
							<td width="10%">
								承诺时间
							</td>
							<td width="40%">
								<%=list.get(0).getPROMISE_DAY() %>
							</td >
							<td>
								承诺时间单位
							</td>
							<td>
								<%=list.get(0).getPROMISE_TYPE_NAME() %>
							</td>
						</tr>
						<tr>
							<td width="10%">
								法定期限
							</td>
							<td width="40%">
								<%=list.get(0).getANTICIPATE_DAY() %>
							</td >
							<td>
								法定时间单位
							</td>
							<td>
								<%=list.get(0).getANTICIPATE_TYPE_NAME() %>
							</td>
						</tr>
						<tr>
							<td width="10%">
								外部流程图
							</td>
							<td colspan="3">
								<%if(list.get(0).getOUT_FLOW_IMG().equals("1")){
								%>
								<img  src="<%=request.getContextPath() %>/qlsx.do?method=showPhoto&type=16&type_obj_no=<%=list.get(0).getQl_reg_id() %>&file_order=1&file_num=1" width="100%" height="100%" /><br>
								<%}else{} %>
							</td >
						</tr>
						<tr>
							<td width="10%">
								内部流程图
							</td>
							<td colspan="3">
							<%if(list.get(0).getIN_FLOW_IMG().equals("1")){
								%>
								<img  src="<%=request.getContextPath() %>/qlsx.do?method=showPhoto&type=16&type_obj_no=<%=list.get(0).getQl_reg_id() %>&file_order=2&file_num=1" width="100%" height="100%" /><br>
								<%}else{} %>
							</td >
						</tr>
						<tr>
							<td width="10%">
								内部流程信息
							</td>
							<td colspan="3">
								<%=list.get(0).getIN_FLOW_INFO() %>
							</td >
						</tr>
						<tr>
							<td width="10%">
								服务指南
							</td>
							<td colspan="3">
								<%=list.get(0).getSERVICE_GUIDE() %>
							</td >
						</tr>
						<tr>
							<td width="10%">
								服务表格
							</td>
							<td colspan="3">
							<%=GlobalUtil.xmlTransTable(list.get(0).getSERVICE_TABLE()) %>							
							</td>
						</tr>
						<tr>
							<td width="10%">
								常见问题及回答
							</td>
							<td colspan="3">
								<%=list.get(0).getQUESTION_BY_ANSWER() %>
							</td >
						</tr>
						<tr>
							<td width="10%">
								收费依据
							</td>
							<td colspan="3">
								<%=list.get(0).getCHARGE_BASIS() %>
							</td >
						</tr>
						<tr>
							<td width="10%">
								是否收费
							</td>
							<td colspan="3">
								<%=isCharge %>
							</td >
						</tr>
						<tr>
							<td width="10%">
								收费标准
							</td>
							<td colspan="3">
								<%=list.get(0).getCHARGE_STANDARD() %>
							</td >
						</tr>
						<tr>
							<td width="10%">
								自由裁量
							</td>
							<td colspan="3">
								<%=list.get(0).getSTANDARD() %>
							</td >
						</tr>
						<tr>
							<td width="10%">
								处罚种类
							</td>
							<td colspan="3">
								<%=list.get(0).getPUNISH_CLASS() %>
							</td >
						</tr>
						<tr>
							<td width="10%">
								外网在线办理链接
							</td>
							<td colspan="3">
								<%=list.get(0).getTRANSACT_URL() %>
							</td >
						</tr>
						<tr>
							<td width="10%">
								监督电话
							</td>
							<td width="40%">
								<%=list.get(0).getSUPERVISE_TEL() %>
							</td >
							<td>
								联系电话
							</td>
							<td>
								<%=list.get(0).getLINK_TEL() %>
							</td>
						</tr>
						<tr>
							<td width="10%">
								办理地点
							</td>
							<td width="40%">
								<%=list.get(0).getSUPERVISE_TEL() %>
							</td >
							<td>
								办理部门
							</td>
							<td>
								<%=list.get(0).getLINK_TEL() %>
							</td>
						</tr>
						<tr>
							<td width="10%">
								受理条件
							</td>
							<td colspan="3">
								<%=list.get(0).getCONDITION() %>
							</td >
						</tr>
					</table>
				</div>

	
	<!--包含page_tools页面-->
	

<br/>

<div align="right">
<hr/>

<a style="CURSOR: pointer" onClick="window.external.addFavorite(location.href,document.title)">
<strong>[加入收藏 ]</strong>
</a>

<a style="CURSOR: pointer" href="javascript:window.print();">
<strong>[打印此文 ] </strong>
</a>


<a href="javascript:window.opener=null;window.close()">
<strong>[关 闭 ]</strong>
</a>

</div>
	<!--包含page_tools页面-->
	
	</div>
</div>

<!--页面主体--结束-->
<!--包含foot页面-->

<jsp:include page="../global/foot.jsp" />
  
 
</div>

<script type="text/javascript">
$.ajax({
  url: "/htzj/publish/fwlSet.do",
  cache: false,
  success: function(html){
  }
});
</script>


</body>
</html>

  <!--foot--结束-->


<!--包含foot页面-->
