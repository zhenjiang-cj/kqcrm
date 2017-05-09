<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.List"%>
<%@page import="com.nl.ql.util.AppConst"%>
<%@page import=" com.nl.base.utils.GlobalFunc"%>
<%@page import="com.nl.integratedManage.dt.CompanyBaseInfo"%>
<%@page import="com.nl.integratedManage.actionForm.ApprovalFlowForm"%>
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

<%
	String url=request.getContextPath()+"/frontManageAction.do?method=queryExpireCert";
	ApprovalFlowForm form = (ApprovalFlowForm)request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);

    

 %>
 
 
 
$(document).ready(function(){
	$('#queryCert').addClass('left_list_sub_select');
})


function addSelect(){
	$.ajax({
		
	  	url: "<%=request.getContextPath() %>/getCommon.do?method=queryDictById&dict_id=1026",
		dataType: 'json',
	  	cache: false,
	  	success: function(msg){
	  		var arraySelect = new Array();
	  		arraySelect = msg;
	  			  		
	  		var strSelect = null;
	  		strSelect = "<option value='' selected>全部</option>"

	  		for(var i=0;i<arraySelect.length;i++){
	  			
	  			strSelect = strSelect +"<option value='"+arraySelect[i]["value"]+"'>"+arraySelect[i]["value_name"]+"</option>";
				
			}
			$('#aptitudeType').html(strSelect);
			
			
			
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
  <a class="local_list" href="#">查询统计</a>
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
			<div class="left_list_sub" id="queryCert" onclick="location='<%=request.getContextPath() %>/frontManageAction.do?method=queryExpireCert';">
				资质证书过期查询 &gt;
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
                  <div class="right_r2"> <span class="r2_rdbs_tit"> &gt; 资质证书</span> 
                  	
                  
                  </div>
                  <div class="rdbs_list">
                  	<ul>
                  		<form name="form1" id="form1" method="post" action="<%=request.getContextPath() %>/frontManageAction.do?method=queryExpireCert">
                    	<span>资质类型：&nbsp;
                    		<select id="aptitudeType" name="aptitudeType" >
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
                    	<span>序号</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    	<span>企业名称</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    	<span>资质类型</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    	<!-- <span>资质等级</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--> 
                    	<!--<span>联系电话</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->
                    	<span>过期日期</span>&nbsp;&nbsp;&nbsp;&nbsp;                 	
                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>剩余天数</span>
                    
                    </ul>
                    <ul>
                    <% 	
                    	
                    	List<CompanyBaseInfo> list = (List<CompanyBaseInfo>)request.getAttribute("certList");
                    	int totalCount = 0;
                    	for(int i=0;i<list.size();i++){
                    	totalCount = list.get(i).getTotalCount();
                    %>
                    	<li>
	                      		<span><%=i+1 %></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					        	<span><%=list.get(i).getCompany_name() %></span>
					        	<span class="rdbs_list_date"><%=GlobalFunc.isNull(list.get(i).getAptitude_type_name()) %></span>
					        	<!--<span class="rdbs_list_date"><%=list.get(i).getAptitude_grade_name() %></span>-->
					        	<!--<span class="rdbs_list_date"><%=list.get(i).getContacts_no() %></span>-->
					        	<span class="rdbs_list_date"><%=GlobalFunc.isNull(list.get(i).getAptitude_end_date()) %></span>
					        	<span class="rdbs_list_date"><%=GlobalFunc.isNull(list.get(i).getLeave_days()) %></span>
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