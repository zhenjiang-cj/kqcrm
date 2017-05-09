<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.List"%>
<%@page import="com.nl.ql.util.AppConst"%>
<%@page import="com.nl.ql.dt.KmQlInfo"%>
<%@page import="com.nl.ql.actionForm.QlForm"%>
<%@page import="com.nl.util.GlobalConst"%>
<%@ taglib uri="../../WEB-INF/tlds/pageListTag.tld" prefix="pageTags" %> 

<!--包含head页面-->


  <!--head--开始-->
	

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=AppConst.TITLE_INFO %></title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/category.css" />
<link rel="stylesheet" type="text/css" href="css/rfloat.css" />

<script language="javascript" type="text/javascript" src="plugins/jquery/jquery.js"></script>

<script type="text/javascript">
$(function(){
	var screenWidth=window.screen.width;
	var screenHeight=window.screen.height;
	
	if(document.body.clientWidth <=1024){
		window.resizeTo(screenWidth, screenHeight);
	}
});
var ql_type = 0
<%
	String url=request.getContextPath()+"/qlsx.do?method=queryQlInfoList";
    QlForm form = (QlForm)request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
    
    if(!"".equals(form.getQl_type())){
    	
    	if("1".equals(form.getQl_type()))
    		out.println("ql_type = 1;");
    	if("2".equals(form.getQl_type()))
    		out.println("ql_type = 2;");
    	if("3".equals(form.getQl_type()))
    		out.println("ql_type = 3;");
    	if("4".equals(form.getQl_type()))
    		out.println("ql_type = 4;");
    }else
    	out.println("ql_type = 0;");
    

 %>
 
 
 
$(document).ready(function(){
	if(ql_type==0)
		$('#qlsx').addClass('left_list_sub_select');
	else if(ql_type==1)
		$('#xk').addClass('left_list_sub_select');
	else if(ql_type==2)
		$('#cf').addClass('left_list_sub_select');
	else if(ql_type==3)
		$('#qz').addClass('left_list_sub_select');
	else if(ql_type==4)
	    $('#zs').addClass('left_list_sub_select');
	else if(ql_type==5)
	    $('#sp').addClass('left_list_sub_select');
	else if(ql_type==6)
	    $('#gf').addClass('left_list_sub_select');
	else if(ql_type==7)
	    $('#jl').addClass('left_list_sub_select');
	else if(ql_type==8)
	    $('#qr').addClass('left_list_sub_select');
	else if(ql_type==9)
	    $('#zy').addClass('left_list_sub_select');
	else if(ql_type==10)
	    $('#cj').addClass('left_list_sub_select');
	else if(ql_type==11)
	    $('#qt').addClass('left_list_sub_select');
})


function addSelect(){
	$.ajax({
		
	  	url: "<%=request.getContextPath() %>/getCommon.do?method=queryDictById&dict_id=1027",
		dataType: 'json',
	  	cache: false,
	  	success: function(msg){
	  		var arraySelect = new Array();
	  		arraySelect = msg;
	  			  		
	  		var strSelect = null;
	  		if(ql_type==0)
	  			strSelect = "<option value='' selected>全部</option>"
	  		else
	  			strSelect = "<option value='' >全部</option>"

	  		for(var i=0;i<arraySelect.length;i++){
	  			
	  			if(i+1 == ql_type)
					strSelect = strSelect +"<option value='"+arraySelect[i]["value"]+"' selected>"+arraySelect[i]["value_name"]+"</option>";
				else
					strSelect = strSelect +"<option value='"+arraySelect[i]["value"]+"'>"+arraySelect[i]["value_name"]+"</option>";
				
			}
			$('#ql_type').html(strSelect);
			
			
			
	  }
	});
	
}


</script>

</head>
<body>
<div id='barrierfree_container'>

<!--loginBar-->


  <!--head--开始-->
  <jsp:include page="../global/head.jsp" />

  
  
  

<!--包含head页面-->

  <!--当前位置--开始-->
  <div class="local"> 
  <span class="local_tag">当前位置：</span> 
  <a class="local_list" href="#">首页</a> &gt;  
  <a class="local_list" href="#">权力事项</a>
  </div>
  <!--当前位置--结束-->
  
  
<!--页面主体--开始-->
  <div class="body_main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tbody>
        <tr>
          <td width="236" valign="top">
          
          	<!--包含sidebar页面-->
			

<div class="body_left">
	<div class="left_tit_main">
		<div class="left_tit_sub">
			所有分类
		</div>
	</div>
	<div class="left_list_main">
		<!--分类列表-->
		<div class="left_listsub_main">
			<div class="left_list_sub" id="qlsx" onclick="location='<%=request.getContextPath() %>/qlsx.do?method=queryQlInfoList';">
				行政权力事项 &gt;
			</div>
            <div class="left_list_sub" id="xk" onclick="location='<%=request.getContextPath() %>/qlsx.do?method=queryQlInfoList&ql_type=1';">
				行政许可 &gt;
			</div>
			<div class="left_list_sub" id="cf" onclick="location='<%=request.getContextPath() %>/qlsx.do?method=queryQlInfoList&ql_type=2';">
				行政处罚 &gt;
			</div>
			<div class="left_list_sub" id="qz" onclick="location='<%=request.getContextPath() %>/qlsx.do?method=queryQlInfoList&ql_type=3';">
				行政强制 &gt;
			</div>
			<div class="left_list_sub" id="zs" onclick="location='<%=request.getContextPath() %>/qlsx.do?method=queryQlInfoList&ql_type=4';">
				行政征收 &gt;
			</div>
			<div class="left_list_sub" id="sp" onclick="location='<%=request.getContextPath() %>/qlsx.do?method=queryQlInfoList&ql_type=5';">
				非许可审批 &gt;
			</div>
			<div class="left_list_sub" id="gf" onclick="location='<%=request.getContextPath() %>/qlsx.do?method=queryQlInfoList&ql_type=6';">
				行政给付 &gt;
			</div>
			<div class="left_list_sub" id="jl" onclick="location='<%=request.getContextPath() %>/qlsx.do?method=queryQlInfoList&ql_type=7';">
				行政奖励 &gt;
			</div>
			<div class="left_list_sub" id="qr" onclick="location='<%=request.getContextPath() %>/qlsx.do?method=queryQlInfoList&ql_type=8';">
				行政确认 &gt;
			</div>
			<div class="left_list_sub" id="zy" onclick="location='<%=request.getContextPath() %>/qlsx.do?method=queryQlInfoList&ql_type=9';">
				行政征用 &gt;
			</div>
			<div class="left_list_sub" id="cj" onclick="location='<%=request.getContextPath() %>/qlsx.do?method=queryQlInfoList&ql_type=10';">
				行政裁决 &gt;
			</div>
			<div class="left_list_sub" id="qt" onclick="location='<%=request.getContextPath() %>/qlsx.do?method=queryQlInfoList&ql_type=11';">
				其他 &gt;
			</div>
		</div>
	</div>
</div>


<!--包含sidebar-->


<script type="text/javascript">
//隐藏子菜单
$(".left_list_sublist").hide();

if("null"!="null" && $("#null").size()>0){
	//点击菜单
	$("#null").addClass("left_list_sub_select");
	//子菜单联动
	$("#null").find(".left_list_sublist").addClass("left_list_sub_select");
	$("#null").find(".left_list_sublist").fadeIn(300);
}else{
	$(".left_list_sub").first().addClass("left_list_sub_select");
	//
	$(".left_list_sub").first().find(".left_list_sublist").fadeIn(300);
}

	
</script>
<!--包含sidebar-->
			<!--包含sidebar页面-->
            
            
          </td>
          <td width="19"></td>
          <td width="745" valign="top" id="listtd">
         	 <div class="body_right">
                  <div class="right_r2"> <span class="r2_rdbs_tit"> &gt; 行政权力事项</span> 
                  	
                  
                  </div>
                  <div class="rdbs_list">
                  	<ul>
                  		<form name="form1" id="form1" method="post" action="<%=request.getContextPath() %>/qlsx.do?method=queryQlInfoList">
                    	<span>事项名称：&nbsp;<input id="ql_name" name="ql_name" type = "text" size = 15/></span>&nbsp;&nbsp;
                    	<span>实施主体：&nbsp;<input id="ql_dep_name" name="ql_dep_name" type = "text" size = 15/></span>&nbsp;&nbsp;
                    	<span>权力类型：&nbsp;
                    		<select id="ql_type" name="ql_type" >
                    			<script type="text/javascript">
                    			   addSelect();
                    			</script>
						    </select>
                    	</span>&nbsp;&nbsp;
                    	<span class="right_tit_sub" onclick="doQuery()">搜索</span>
                    	</form>
                    </ul>
                    <ul>
                    	&nbsp;&nbsp;&nbsp;
                    	<span>权力事项名称</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    	<span>实施主体</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>生效时间</span>
                    
                    </ul>
                    <ul>
                    <% 	
                    	
                    	List<KmQlInfo> list = (List<KmQlInfo>)request.getAttribute("qlinfolist");
                    	int totalCount = 0;
                    	for(int i=0;i<list.size();i++){
                    	totalCount = list.get(i).getTotalCount();
                    %>
                    	<li>
	                      		<a href="<%=request.getContextPath() %>/qlsx.do?method=queryQlInfoList&ql_reg_id=<%=list.get(i).getQl_reg_id() %>" target="_blank"><%=list.get(i).getQl_name() %></a>
					        	
					        	<span class="rdbs_list_date"><%=list.get(i).getStart_time() %></span>
                      		</li>
                    <% } %>
                    	                   	
                    
                    </ul>
                    
                    <div class="rdbs_fenye" align="center">
	                    
							 <form id='commonPageForm' name='commonPageForm' action='http://218.3.124.236:9080/htzj/publish/queryXwdtList.do' method='post'> <input type='hidden' id='currentPage' name='currentPage' value='null'></form> 
<script language="Javascript">
function gotoPage(pageNo){  
document.getElementById("currentPage").value = pageNo;  
document.getElementById("commonPageForm").submit(); 
return true; }</script>  
<div class='pages_btns' align='center' style='padding-left: 25px'> 
	<pageTags:pageListTag linkUrl="<%= url %>" currentPage="<%=form.getCurrentPage() %>" resultSum="<%=totalCount %>" pageDisplayNum="10" aroundType="false">
				                </pageTags:pageListTag>
						
					</div>
                  </div>
            </div>
            </td>
        </tr>
    </table>
  </div>
<!--页面主体--结束-->


<jsp:include page="../global/foot.jsp" />	





 
</div>

<script type="text/javascript">

function doQuery(){

	document.getElementById("form1").submit();
}
</script>


</body>
</html>

  <!--foot--结束-->

<!--包含foot页面-->