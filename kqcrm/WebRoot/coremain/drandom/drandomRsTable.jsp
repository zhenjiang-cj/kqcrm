<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<%@page import="com.nl.ql.util.AppConst"%>
<!--����headҳ��-->

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=AppConst.TITLE_INFO %></title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/category.css" />
<link rel="stylesheet" type="text/css" href="css/rfloat.css" />


<script type="text/javascript">
$(function(){
	var screenWidth=window.screen.width;
	var screenHeight=window.screen.height;
	
	if(document.body.clientWidth <=1024){
		window.resizeTo(screenWidth, screenHeight);
	}
});

function initTable(){
	$.ajax({
	type: 'POST',
	url: '<%=request.getContextPath()%>/drandom c=km&m=show_pool_res' + '&r=' + Math.random(),
	data: 'data=' + '',
	dataType: 'text',
	timeout: 5000,
	cache:false,
	contentType: 'application/x-www-form-urlencoded;charset=utf-8',
	success: function(data){
	show_table(data); 
	},
	error: function(xhr, type){
	alert('��������æ��')
	}
	}); 
}

//˫���������չʾ��
function show_table(data){
var _arr = eval(data);

var html = '';
var head = '<tr><th>���</th><th>�������</th><th>ִ����Ա</th><th>������</th></tr>'
for(var i=0;i<_arr.length;++i){
var _line = _arr[i];
var _lineHTML = '';
_lineHTML = _lineHTML + '<td>' + (i+1) + '</td>';
for(var j=1;j<_line.length;++j){
_lineHTML = _lineHTML + '<td>' + _line[j] + '</td>';
}
html = html + '<tr>' + _lineHTML + '</tr>';
}
html = '<table>' + head + html + '</table>';

//д��div
var o = window.document.getElementById('div_ww_ssj_table');
o.innerHTML = html;
}



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
					˫��������
				</div>
				<div class="page_info">
					<table width="100%" border="0" cellspacing="0" cellpadding="10">
						<tr>
							<td>
								&nbsp;
							</td>
							<td>
								&nbsp;
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
					<table id="div_ww_ssj_table" width="100%" border="1" cellspacing="0" cellpadding="10">
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



</body>
</html>

  <!--foot--����-->


<!--����footҳ��-->
