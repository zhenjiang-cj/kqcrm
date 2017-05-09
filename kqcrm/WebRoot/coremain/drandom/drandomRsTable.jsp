<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<%@page import="com.nl.ql.util.AppConst"%>
<!--包含head页面-->

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
	alert('服务器繁忙。')
	}
	}); 
}

//双随机外网的展示：
function show_table(data){
var _arr = eval(data);

var html = '';
var head = '<tr><th>序号</th><th>抽查事项</th><th>执法人员</th><th>抽查对象</th></tr>'
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

//写到div
var o = window.document.getElementById('div_ww_ssj_table');
o.innerHTML = html;
}



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
					双随机抽查结果
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
								字体调整：<a href="javascript:fontZoom('page_content',1);">大</a> 
								<a href="javascript:fontZoom('page_content',2);">中</a> 
								<a href="javascript:fontZoom('page_content',3);">小</a>
							</td>
						</tr>
					</table>
				</div>

				<div id="page_content" class="page_content">
					<table id="div_ww_ssj_table" width="100%" border="1" cellspacing="0" cellpadding="10">
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



</body>
</html>

  <!--foot--结束-->


<!--包含foot页面-->
