<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.List"%>
<%@page import="com.nl.ql.util.AppConst"%>
<%@page import=" com.nl.base.utils.GlobalFunc"%>
<%@page import="com.nl.integratedManage.dt.CompanyBaseInfo"%>
<%@page import="com.nl.integratedManage.actionForm.ApprovalFlowForm"%>
<%@page import="com.nl.util.GlobalConst"%>
<%@ taglib uri="../../WEB-INF/tlds/pageListTag.tld" prefix="pageTags" %> 

<!--����headҳ��-->


  <!--head--��ʼ-->
	

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
	  		strSelect = "<option value='' selected>ȫ��</option>"

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


  <!--head--��ʼ-->
  <jsp:include page="../global/head.jsp" />

  
  
  

<!--����headҳ��-->

  <!--��ǰλ��--��ʼ-->
  <div class="local"> 
  <span class="local_tag">��ǰλ�ã�</span> 
  <a class="local_list" href="#">��ҳ</a> &gt;  
  <a class="local_list" href="#">��ѯͳ��</a>
  </div>
  <!--��ǰλ��--����-->
  
  
<!--ҳ������--��ʼ-->
  <div class="body_main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tbody>
        <tr>
          <td width="236" valign="top">
          
          	<!--����sidebarҳ��-->
			

<div class="body_left">
	<div class="left_tit_main">
		<div class="left_tit_sub">
			���з���
		</div>
	</div>
	<div class="left_list_main">
		<!--�����б�-->
		<div class="left_listsub_main">
			<div class="left_list_sub" id="queryCert" onclick="location='<%=request.getContextPath() %>/frontManageAction.do?method=queryExpireCert';">
				����֤����ڲ�ѯ &gt;
			</div>
		</div>
	</div>
</div>


<!--����sidebar-->


<script type="text/javascript">
//�����Ӳ˵�
$(".left_list_sublist").hide();

if("null"!="null" && $("#null").size()>0){
	//����˵�
	$("#null").addClass("left_list_sub_select");
	//�Ӳ˵�����
	$("#null").find(".left_list_sublist").addClass("left_list_sub_select");
	$("#null").find(".left_list_sublist").fadeIn(300);
}else{
	$(".left_list_sub").first().addClass("left_list_sub_select");
	//
	$(".left_list_sub").first().find(".left_list_sublist").fadeIn(300);
}

	
</script>
<!--����sidebar-->
			<!--����sidebarҳ��-->
            
            
          </td>
          <td width="19"></td>
          <td width="745" valign="top" id="listtd">
         	 <div class="body_right">
                  <div class="right_r2"> <span class="r2_rdbs_tit"> &gt; ����֤��</span> 
                  	
                  
                  </div>
                  <div class="rdbs_list">
                  	<ul>
                  		<form name="form1" id="form1" method="post" action="<%=request.getContextPath() %>/frontManageAction.do?method=queryExpireCert">
                    	<span>�������ͣ�&nbsp;
                    		<select id="aptitudeType" name="aptitudeType" >
                    			<script type="text/javascript">
                    			   addSelect();
                    			</script>
						    </select>
                    	</span>&nbsp;&nbsp;
                    	<span class="right_tit_sub" onclick="doQuery()">����</span>
                    	</form>
                    </ul>
                    <ul>
                    	&nbsp;&nbsp;&nbsp;
                    	<span>���</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    	<span>��ҵ����</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    	<span>��������</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    	<!-- <span>���ʵȼ�</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--> 
                    	<!--<span>��ϵ�绰</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->
                    	<span>��������</span>&nbsp;&nbsp;&nbsp;&nbsp;                 	
                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>ʣ������</span>
                    
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
<!--ҳ������--����-->


<jsp:include page="../global/foot.jsp" />	





 
</div>

<script type="text/javascript">

function doQuery(){

	document.getElementById("form1").submit();
}
</script>


</body>
</html>

  <!--foot--����-->

<!--����footҳ��-->