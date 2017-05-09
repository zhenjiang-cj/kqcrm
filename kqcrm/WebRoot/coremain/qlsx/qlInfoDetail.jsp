<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.List"%>
<%@page import="com.nl.ql.util.AppConst"%>
<%@page import="com.nl.ql.dt.KmQlInfo"%>
<%@page import="com.nl.ql.actionForm.QlForm"%>
<%@page import="com.nl.util.GlobalUtil"%>
<!--����headҳ��-->

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
	isCharge = "��";
else if(list.get(0).getCHARGE_FLAG()!=null&&"2".equals(list.get(0).getCHARGE_FLAG()))
	isCharge = "��";
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

  
<!--����headҳ��-->

<!--ҳ������--��ʼ-->
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
								����ʱ�䣺
								<%=list.get(0).getCreate_date() %>
							</td>
							<td>
								
							</td>
							<td>
								
							</td>
							<td>
								���������<a href="javascript:fontZoom('page_content',1);">��</a> 
								<a href="javascript:fontZoom('page_content',2);">��</a> 
								<a href="javascript:fontZoom('page_content',3);">С</a>
							</td>
						</tr>
					</table>
				</div>

				<div id="page_content" class="page_content">
					<table width="100%" border="1" cellspacing="0" cellpadding="10">
						<tr>
							<td width="15%">
								Ȩ������
							</td>
							<td width="35%">
								<%=list.get(0).getQl_type_name() %>
							</td >
							<td width="15%">
								ʵʩ��������
							</td>
							<td width="35%">
								<%=list.get(0).getQl_dep_name() %>
							</td>
						</tr>
						<tr>
							<td width="10%">
								��Чʱ��
							</td>
							<td width="40%">
								<%=list.get(0).getStart_time() %>
							</td >
							<td>
								��ֹʱ��
							</td>
							<td>
								<%=list.get(0).getEnd_time() %>
							</td>
						</tr>
						<tr>
							<td width="10%">
								Ȩ����������
							</td>
							<td width="40%">
								<%=list.get(0).getUpdate_type_name() %>
							</td >
							<td>
								ʵʩ��������
							</td>
							<td>
								<%=list.get(0).getQL_DEPSTATE_NAME() %>
							</td>
						</tr>
						<tr>
							<td width="10%">
								ί�л���
							</td>
							<td width="40%">
								<%=list.get(0).getENTRUST_NAME() %>
							</td >
							<td>
								Ȩ��״̬
							</td>
							<td>
								<%=list.get(0).getQL_DEPSTATE_NAME() %>
							</td>
						</tr>
						<tr>
							<td width="10%">
								Ȩ����������
							</td>
							<td colspan="3">
								<%=list.get(0).getBY_LAW() %>
							</td >
						</tr>
						<tr>
							<td width="10%">
								��ŵʱ��
							</td>
							<td width="40%">
								<%=list.get(0).getPROMISE_DAY() %>
							</td >
							<td>
								��ŵʱ�䵥λ
							</td>
							<td>
								<%=list.get(0).getPROMISE_TYPE_NAME() %>
							</td>
						</tr>
						<tr>
							<td width="10%">
								��������
							</td>
							<td width="40%">
								<%=list.get(0).getANTICIPATE_DAY() %>
							</td >
							<td>
								����ʱ�䵥λ
							</td>
							<td>
								<%=list.get(0).getANTICIPATE_TYPE_NAME() %>
							</td>
						</tr>
						<tr>
							<td width="10%">
								�ⲿ����ͼ
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
								�ڲ�����ͼ
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
								�ڲ�������Ϣ
							</td>
							<td colspan="3">
								<%=list.get(0).getIN_FLOW_INFO() %>
							</td >
						</tr>
						<tr>
							<td width="10%">
								����ָ��
							</td>
							<td colspan="3">
								<%=list.get(0).getSERVICE_GUIDE() %>
							</td >
						</tr>
						<tr>
							<td width="10%">
								������
							</td>
							<td colspan="3">
							<%=GlobalUtil.xmlTransTable(list.get(0).getSERVICE_TABLE()) %>							
							</td>
						</tr>
						<tr>
							<td width="10%">
								�������⼰�ش�
							</td>
							<td colspan="3">
								<%=list.get(0).getQUESTION_BY_ANSWER() %>
							</td >
						</tr>
						<tr>
							<td width="10%">
								�շ�����
							</td>
							<td colspan="3">
								<%=list.get(0).getCHARGE_BASIS() %>
							</td >
						</tr>
						<tr>
							<td width="10%">
								�Ƿ��շ�
							</td>
							<td colspan="3">
								<%=isCharge %>
							</td >
						</tr>
						<tr>
							<td width="10%">
								�շѱ�׼
							</td>
							<td colspan="3">
								<%=list.get(0).getCHARGE_STANDARD() %>
							</td >
						</tr>
						<tr>
							<td width="10%">
								���ɲ���
							</td>
							<td colspan="3">
								<%=list.get(0).getSTANDARD() %>
							</td >
						</tr>
						<tr>
							<td width="10%">
								��������
							</td>
							<td colspan="3">
								<%=list.get(0).getPUNISH_CLASS() %>
							</td >
						</tr>
						<tr>
							<td width="10%">
								�������߰�������
							</td>
							<td colspan="3">
								<%=list.get(0).getTRANSACT_URL() %>
							</td >
						</tr>
						<tr>
							<td width="10%">
								�ල�绰
							</td>
							<td width="40%">
								<%=list.get(0).getSUPERVISE_TEL() %>
							</td >
							<td>
								��ϵ�绰
							</td>
							<td>
								<%=list.get(0).getLINK_TEL() %>
							</td>
						</tr>
						<tr>
							<td width="10%">
								����ص�
							</td>
							<td width="40%">
								<%=list.get(0).getSUPERVISE_TEL() %>
							</td >
							<td>
								������
							</td>
							<td>
								<%=list.get(0).getLINK_TEL() %>
							</td>
						</tr>
						<tr>
							<td width="10%">
								��������
							</td>
							<td colspan="3">
								<%=list.get(0).getCONDITION() %>
							</td >
						</tr>
					</table>
				</div>

	
	<!--����page_toolsҳ��-->
	

<br/>

<div align="right">
<hr/>

<a style="CURSOR: pointer" onClick="window.external.addFavorite(location.href,document.title)">
<strong>[�����ղ� ]</strong>
</a>

<a style="CURSOR: pointer" href="javascript:window.print();">
<strong>[��ӡ���� ] </strong>
</a>


<a href="javascript:window.opener=null;window.close()">
<strong>[�� �� ]</strong>
</a>

</div>
	<!--����page_toolsҳ��-->
	
	</div>
</div>

<!--ҳ������--����-->
<!--����footҳ��-->

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

  <!--foot--����-->


<!--����footҳ��-->
