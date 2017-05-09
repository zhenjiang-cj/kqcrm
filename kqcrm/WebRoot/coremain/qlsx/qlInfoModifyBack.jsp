<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.ql.dt.KmQlInfo"%>
<%@ page import="com.nl.util.GlobalConst"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

List<KmQlInfo> list = (List<KmQlInfo>)request.getAttribute("qlinfolist");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>权力事项修改</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=path%>/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="<%=path%>/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="<%=path%>/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
	<link href="<%=path%>/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
	
	<style>
		 .input{width:250px;}
		 .but_s{width:60px;height:30px;}
		 .table{border:solid #add9c0; border-width:2px 1px 1px 2px;}
		 .tr{height:60px;}
	</style>
	


<script type="text/javascript">

function setImgOut(){
	document.getElementById("OUT_FLOW_IMG").value=1;
	//alert("out="+document.getElementById("OUT_FLOW_IMG").value);
}

function setImgIn(){
	
	document.getElementById("IN_FLOW_IMG").value=1;
	//alert("in");
}

function delIMG(type){
	if(type==1)
		document.getElementById("OUT_FLOW_IMG").value=0;
	else if(type==2)
		document.getElementById("IN_FLOW_IMG").value=0;
		
	var ql_req_id = document.getElementById("ql_reg_id").value;
	
	var d_out_img = '<a class="button" id="a_out_flow_upload" onclick="addQlId(1);" href="<%=path%>/coremain/qlsx/toUploadFile.jsp?imgType=1&ql_reg_id=" target="dialog" rel="uploadOutImg" width="645" height="370">';
		//d_out_img = d_out_img+'<input type="text" name="OUT_FLOW_IMG" class="required"/>';
		d_out_img = d_out_img+'<span id="out_flow_upload">上传外部流程图</span>';
		d_out_img = d_out_img+'</a>';
		d_out_img = d_out_img+'<input type="hidden" id="OUT_FLOW_IMG" name="OUT_FLOW_IMG" value="0"/>';
	var d_in_img = '<a class="button" id="a_in_flow_upload" onclick="addQlId(2);" href="<%=path%>/coremain/qlsx/toUploadFile.jsp?imgType=2&ql_reg_id=" target="dialog" rel="uploadInImg" width="645" height="370">';
		//d_in_img = '<input type="text" name="IN_FLOW_IMG" class="required"/>';
		d_in_img = '<span id="in_flow_upload">上传内部流程图</span>';
		d_in_img = '</a>';
		d_in_img = '<input type="hidden" id="IN_FLOW_IMG" name="IN_FLOW_IMG" value="0"/>';

	$.ajax({
	  	url: "<%=request.getContextPath() %>/qlsxBack.do?method=updateQlImgFlag&ql_reg_id="+ql_req_id+"&imgFlag="+type+"&imgValue=0&deal_type=2",
		dataType: 'json',
	  	cache: false,
	  	success: function(msg){
	  	
	  	if(type == 1)
	  		document.getElementById("span_del_out_img").style.display="none";
	  	else
	  		document.getElementById("span_del_in_img").style.display="none";
	  	/*
	  	if(type == 1)
	  		$('#d_OUT_FLOW_IMG').html(d_out_img);
	  	else
	  		$('#d_IN_FLOW_IMG').html(d_in_img);
		*/
	  }
	});
		
}
 


//获取权力事项字典
function addSelect(){
	var sel_ql_type = <%=list.get(0).getQl_type() %>;
	$.ajax({
	  	url: "<%=request.getContextPath() %>/getCommon.do?method=queryDictById&dict_id=1027",
		dataType: 'json',
	  	cache: false,
	  	success: function(msg){
	  		var arraySelect = new Array();
	  		arraySelect = msg;
	  			  		
	  		var strSelect = '';
	  		
			strSelect = strSelect+'<select class="combox" id="ql_type" name="ql_type">';
	  		//strSelect = strSelect+ '<option value="0" selected>--请选择--</option>';

			//alert('sel_ql_type='+sel_ql_type);
	  		for(var i=0;i<arraySelect.length;i++){
	  			if(sel_ql_type == arraySelect[i]['value'])
					strSelect = strSelect +'<option value="'+arraySelect[i]['value']+'" selected>'+arraySelect[i]['value_name']+'</option>';
				else
					strSelect = strSelect +'<option value="'+arraySelect[i]['value']+'">'+arraySelect[i]['value_name']+'</option>';
				
			}
			strSelect = strSelect+'</select>';
			//document.getElementById("ql_type").innerhtml=strSelect;
			$('#ql_type').html(strSelect);

	  }
	});
	
}

//获取权力更新类型字典
function addUpdateTypeSelect(){
	var sel_update_type = <%=list.get(0).getUpdate_type() %>;
	$.ajax({
		
	  	url: "<%=request.getContextPath() %>/getCommon.do?method=queryDictById&dict_id=1028",
		dataType: 'json',
	  	cache: false,
	  	success: function(msg){
	  		var arraySelect = new Array();
	  		arraySelect = msg;
	  			  		
	  		var strSelect = '';
	  		
			strSelect = strSelect+'<select class="combox" id="update_type" name="update_type">';
	  		strSelect = strSelect+ '<option value="0" selected>--请选择--</option>';

	  		for(var i=0;i<arraySelect.length;i++){
	  			if(sel_update_type == arraySelect[i]['value'])
					strSelect = strSelect +'<option value="'+arraySelect[i]['value']+'" selected >'+arraySelect[i]['value_name']+'</option>';
				else
					strSelect = strSelect +'<option value="'+arraySelect[i]['value']+'">'+arraySelect[i]['value_name']+'</option>';
				
			}
			strSelect = strSelect+'</select>';
			$('#update_type').html(strSelect);

	  }
	});
	
}

//获取实施主体性质字典
function addQlDepstateSelect(){
	var sel_QL_DEPSTATE = <%=list.get(0).getQL_DEPSTATE() %>;
	$.ajax({
		
	  	url: "<%=request.getContextPath() %>/getCommon.do?method=queryDictById&dict_id=1029",
		dataType: 'json',
	  	cache: false,
	  	success: function(msg){
	  		var arraySelect = new Array();
	  		arraySelect = msg;
	  			  		
	  		var strSelect = '';
	  		
			strSelect = strSelect+'<select class="combox" id="QL_DEPSTATE" name="QL_DEPSTATE">';
	  		strSelect = strSelect+ '<option value="0" selected>--请选择--</option>';


	  		for(var i=0;i<arraySelect.length;i++){
	  			if(sel_QL_DEPSTATE == arraySelect[i]['value'])
					strSelect = strSelect +'<option value="'+arraySelect[i]['value']+'" selected >'+arraySelect[i]['value_name']+'</option>';
				else
					strSelect = strSelect +'<option value="'+arraySelect[i]['value']+'">'+arraySelect[i]['value_name']+'</option>';
				
			}
			strSelect = strSelect+'</select>';
			$('#QL_DEPSTATE').html(strSelect);

	  }
	});
	
}

//获取权力状态字典
function addQlStateSelect(){
	var sel_QL_STATE = <%=list.get(0).getQL_STATE() %>;
	$.ajax({
		
	  	url: "<%=request.getContextPath() %>/getCommon.do?method=queryDictById&dict_id=1030",
		dataType: 'json',
	  	cache: false,
	  	success: function(msg){
	  		var arraySelect = new Array();
	  		arraySelect = msg;
	  			  		
	  		var strSelect = '';
	  		
			strSelect = strSelect+'<select class="combox" id="QL_STATE" name="QL_STATE">';
	  		//strSelect = strSelect+ '<option value="0" selected>--请选择--</option>';


	  		for(var i=0;i<arraySelect.length;i++){
	  			if(sel_QL_STATE == arraySelect[i]['value'])
					strSelect = strSelect +'<option value="'+arraySelect[i]['value']+'" selected >'+arraySelect[i]['value_name']+'</option>';
				else
					strSelect = strSelect +'<option value="'+arraySelect[i]['value']+'">'+arraySelect[i]['value_name']+'</option>';
				
			}
			strSelect = strSelect+'</select>';
			$('#QL_STATE').html(strSelect);

	  }
	});
	
}

//承诺时间单位，法定时间单位字典
function addPromiseTypeSelect(){
	var sel_PROMISE_TYPE = <%=list.get(0).getPROMISE_TYPE() %>;
	var sel_ANTICIPATE_TYPE = <%=list.get(0).getANTICIPATE_TYPE() %>;
	$.ajax({
		
	  	url: "<%=request.getContextPath() %>/getCommon.do?method=queryDictById&dict_id=1031",
		dataType: 'json',
	  	cache: false,
	  	success: function(msg){
	  		var arraySelect = new Array();
	  		arraySelect = msg;
	  			  		
	  		var strSelect = '';
	  		
			strSelect = strSelect+'<select class="combox" id="PROMISE_TYPE" name="PROMISE_TYPE">';
	  		strSelect = strSelect+ '<option value="-1" selected>--请选择--</option>';
	  		
	  		var strSelect2 = '';
	  		strSelect2 = strSelect2+'<select name="ANTICIPATE_TYPE">';
	  		strSelect2 = strSelect2+ '<option value="-1" selected>--请选择--</option>';


	  		for(var i=0;i<arraySelect.length;i++){
	  			if(sel_PROMISE_TYPE == arraySelect[i]['value'])
					strSelect = strSelect +'<option value="'+arraySelect[i]['value']+'" selected >'+arraySelect[i]['value_name']+'</option>';
				else
					strSelect = strSelect +'<option value="'+arraySelect[i]['value']+'">'+arraySelect[i]['value_name']+'</option>';
				if(sel_ANTICIPATE_TYPE == arraySelect[i]['value'])
					strSelect2 = strSelect2 +'<option value="'+arraySelect[i]['value']+'" selected >'+arraySelect[i]['value_name']+'</option>';
				else	
					strSelect2 = strSelect2 +'<option value="'+arraySelect[i]['value']+'">'+arraySelect[i]['value_name']+'</option>';
				
			}
			strSelect = strSelect+'</select>';
			$('#PROMISE_TYPE').html(strSelect);
			
			strSelect2 = strSelect2+'</select>';
			$('#ANTICIPATE_TYPE').html(strSelect2);

	  }
	});

}
//不用实现
function addAnticipateTypeSelect(){

}

function addIsChargeSelect(){
	var sel_CHARGE_FLAG = <%=list.get(0).getCHARGE_FLAG() %>;
	<%
	/*
	if(!"".equals(list.get(0).getCHARGE_FLAG())){
		out.println("sel_CHARGE_FLAG="+list.get(0).getCHARGE_FLAG()+";");
		System.out.println("list.get(0).getCHARGE_FLAG()="+list.get(0).getCHARGE_FLAG());
	}
	*/
	%>
	
	var strSelect = '<select class="" id="CHARGE_FLAG" name="CHARGE_FLAG">';
	if(sel_CHARGE_FLAG == 0)
		strSelect = strSelect+'<option value="0" selected>--请选择--</option>';
	else 
		strSelect = strSelect+'<option value="0">--请选择--</option>';
	if(sel_CHARGE_FLAG == 1)
		strSelect = strSelect+'<option value="1" selected >是</option>';
	else
		strSelect = strSelect+'<option value="1" >是</option>';
	if(sel_CHARGE_FLAG == 2)
		strSelect = strSelect+'<option value="2" selected >否</option>';
	else
		strSelect = strSelect+'<option value="2" >否</option>';
	strSelect = strSelect+'</select>';
	$('#CHARGE_FLAG').html(strSelect);
}

function addQlId(imgType){
	var ql_req_id = document.getElementById("ql_reg_id").value;
	alert(document.getElementById("ql_reg_id").value);
	if(ql_req_id == null||ql_req_id=="")
		alert("请先输入权力事项编码");
	else{
		document.getElementById("ql_reg_id").readonly = true;
		
		if(imgType == 1){
			var hrefstr ="<%=path%>/coremain/qlsx/toUploadFile.jsp?imgType=1&ql_reg_id=";
			hrefstr = hrefstr+ql_req_id;
			document.getElementById("a_out_flow_upload").href = hrefstr;
		}else{
			var hrefstr ="<%=path%>/coremain/qlsx/toUploadFile.jsp?imgType=2&ql_reg_id=";
			hrefstr = hrefstr+ql_req_id;
			document.getElementById("a_in_flow_upload").href = hrefstr;
		}

	}
}

function submitcheck(){
	if(document.getElementById("PROMISE_DAY").value == '')
		document.getElementById("PROMISE_DAY").value = 0;
	if(document.getElementById("ANTICIPATE_DAY").value == '')
		document.getElementById("ANTICIPATE_DAY").value = 0;
	
}  					
	  				

</script>
  </head>
  
  <body>
  <h2 class="contentTitle">修改信息</h2>
  
  <div class="pageContent">
	
	<form method="post" action="<%=request.getContextPath() %>/qlsxBack.do?method=modifyQlInfo" class="pageForm required-validate" onsubmit="submitcheck(); return validateCallback(this, navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">

			<dl>
				<dt>权力事项编码</dt>
				<dd>
					<input type="text" id="ql_reg_id" name="ql_reg_id" value="<%=list.get(0).getQl_reg_id() %>" maxlength="50" class="required" />
				</dd>
			</dl>
			<dl>
				<dt>事项名称</dt>
				<dd>
					<input type="text" onfocus="" name="ql_name" value="<%=list.get(0).getQl_name() %>" maxlength="1000" class="required" />
				</dd>
			</dl>
			<dl>
				<dt>权力类型</dt>
				<dd id="ql_type">
					<script type="text/javascript">
               			addSelect();
               		</script>
					
				</dd>
			</dl>
			<dl>
				<dt>实施主体名称</dt>
				<dd>
					<input type="text" name="ql_dep_name" value="<%=list.get(0).getQl_dep_name() %>" maxlength="20" class="required" />
				</dd>
			</dl>
			<!-- 
			<dl>
				<dt>办事流程图片</dt>
				<dd>
					<input type="text" name="ql_pic_file_id" class="required"/>
				</dd>
			</dl>
			 -->
			
			<dl>
				<dt>生效时间</dt>
				<dd>
					<input type="text" name="start_time" value="<%=list.get(0).getStart_time() %>" class="required date" readonly="true"/>
				<a class="inputDateButton" href="javascript:;"></a>
				</dd>
			</dl>
			<dl>
				<dt>废止时间</dt>
				<dd>
					<input type="text" name="end_time" value="<%=list.get(0).getEnd_time() %>" class="required date" readonly="true"/>
				<a class="inputDateButton" href="javascript:;"></a>
				</dd>
			</dl>
			<dl>
				<dt>权力更新类型</dt>
				<dd id="update_type">
					<script type="text/javascript">
               			addUpdateTypeSelect();
               		</script>
					
				</dd>
			</dl>
			<dl>
				<dt>实施主体性质</dt>
				<dd id="QL_DEPSTATE">
					<script type="text/javascript">
               			addQlDepstateSelect();
               		</script>
					
				</dd>
			</dl>
			<dl>
				<dt>委托机关</dt>
				<dd>
					<input type="text" name="ENTRUST_NAME" value="<%=list.get(0).getENTRUST_NAME() %>" maxlength="200" class="" />
				</dd>
			</dl>
			<dl>
				<dt>权力状态</dt>
				<dd id="QL_STATE">
					<script type="text/javascript">
               			addQlStateSelect();
               		</script>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>法律依据</dt>
				<dd><textarea name="BY_LAW" cols="80" rows="6"><%=list.get(0).getBY_LAW() %></textarea></dd>
			</dl>
			<dl>
				<dt>承诺时间</dt>
				<dd>
					<input type="text" id="PROMISE_DAY" name="PROMISE_DAY" value="<%=list.get(0).getPROMISE_DAY() %>" maxlength="8" class="digits" />
				</dd>
			</dl>
			<dl>
				<dt>承诺时间单位</dt>
				<dd id="PROMISE_TYPE">
					<script type="text/javascript">
               			addPromiseTypeSelect();
               		</script>
				</dd>
			</dl>
			<dl>
				<dt>法定期限</dt>
				<dd>
					<input type="text" id="ANTICIPATE_DAY" name="ANTICIPATE_DAY" value="<%=list.get(0).getANTICIPATE_DAY() %>" maxlength="8" class="digits" />
				</dd>
			</dl>
			<dl>
				<dt>法定时间单位</dt>
				<dd id="ANTICIPATE_TYPE">
					<script type="text/javascript">
               			addAnticipateTypeSelect();
               		</script>
				</dd>
			</dl>
			<dl>
				<dt>外部流程图</dt>
				<dd id="d_OUT_FLOW_IMG">
					<%
					System.out.println("list.get(0).getOUT_FLOW_IMG()="+list.get(0).getOUT_FLOW_IMG()); 
					System.out.println("list.get(0).getIN_FLOW_IMG()="+list.get(0).getIN_FLOW_IMG()); 
					%>
					<%if(list.get(0).getOUT_FLOW_IMG().equals("1")){
					%>
					<span id="span_del_out_img" style="display:">
					<img  src="<%=request.getContextPath() %>/qlsx.do?method=showPhoto&type=16&type_obj_no=<%=list.get(0).getQl_reg_id() %>&file_order=1&file_num=1" width="100%" height="100%" /><br>
					<a class="button" onclick="delIMG(1);">
					<span id="del_out_flow_upload">删除外部流程图</span>
					</a>
					<input type="hidden" id="OUT_FLOW_IMG" name="OUT_FLOW_IMG" value="1"/>
					</span>
					<a class="button" id="a_out_flow_upload" onclick="addQlId(1);" href="<%=path%>/coremain/qlsx/toUploadFile.jsp?imgType=1&ql_reg_id=" target="dialog" rel="uploadOutImg" width="645" height="370">
					<!--<input type="text" name="OUT_FLOW_IMG" class="required"/>-->
					<span id="out_flow_upload">重新上传外部流程图</span> 
					</a>
					<input type="hidden" id="OUT_FLOW_IMG" name="OUT_FLOW_IMG" value="0"/>
					
					<%}else{
					%>	
						 
					<a class="button" id="a_out_flow_upload" onclick="addQlId(1);" href="<%=path%>/coremain/qlsx/toUploadFile.jsp?imgType=1&ql_reg_id=" target="dialog" rel="uploadOutImg" width="645" height="370">
					<!--<input type="text" name="OUT_FLOW_IMG" class="required"/>-->
					<span id="out_flow_upload">上传外部流程图</span> 
					</a>
					<input type="hidden" id="OUT_FLOW_IMG" name="OUT_FLOW_IMG" value="0"/>
					<%	
					} %>
					
				</dd>
			</dl>
			<dl>
				<dt>内部流程图</dt>
				<dd id="d_IN_FLOW_IMG">
					
					 <%if(list.get(0).getIN_FLOW_IMG().equals("1")){
						%>
						<span id="span_del_in_img" style="display:">
						<img  src="<%=request.getContextPath() %>/qlsx.do?method=showPhoto&type=16&type_obj_no=<%=list.get(0).getQl_reg_id() %>&file_order=2&file_num=1" width="100%" height="100%" /><br>
						<a class="button" onclick="delIMG(2);">
						<span id="del_in_flow_upload">删除内部流程图</span>
						</a>
						<input type="hidden" id="IN_FLOW_IMG" name="IN_FLOW_IMG" value="1"/>
						</span>
						
						<a class="button" id="a_in_flow_upload" onclick="addQlId(2);" href="<%=path%>/coremain/qlsx/toUploadFile.jsp?imgType=2&ql_reg_id=" target="dialog" rel="uploadInImg" width="645" height="370">
						<!--<input type="text" name="IN_FLOW_IMG" class="required"/>-->
						<span id="in_flow_upload">重新上传内部流程图</span>
						</a>
						<input type="hidden" id="IN_FLOW_IMG" name="IN_FLOW_IMG" value="0"/>
						
						<%}else{
						%>
						
						<a class="button" id="a_in_flow_upload" onclick="addQlId(2);" href="<%=path%>/coremain/qlsx/toUploadFile.jsp?imgType=2&ql_reg_id=" target="dialog" rel="uploadInImg" width="645" height="370">
						<!--<input type="text" name="IN_FLOW_IMG" class="required"/>-->
						<span id="in_flow_upload">上传内部流程图</span>
						</a>
						<input type="hidden" id="IN_FLOW_IMG" name="IN_FLOW_IMG" value="0"/>
						 
						<%	
						} %>
					
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>内部流程信息</dt>
				<dd><textarea name="IN_FLOW_INFO" cols="80" rows="6"><%=list.get(0).getIN_FLOW_INFO() %></textarea></dd>
			</dl>
			<dl class="nowrap">
				<dt>服务指南</dt>
				<dd><textarea name="SERVICE_GUIDE"  cols="80" rows="6"><%=list.get(0).getSERVICE_GUIDE() %></textarea></dd>
			</dl>
			<dl class="nowrap">
				<dt>服务表格</dt>
				<dd><textarea name="SERVICE_TABLE" cols="80" rows="6"><%=list.get(0).getSERVICE_TABLE() %></textarea></dd>
			</dl>
			<dl class="nowrap">
				<dt>常见问题及回答</dt>
				<dd><textarea name="QUESTION_BY_ANSWER" cols="80" rows="6"><%=list.get(0).getQUESTION_BY_ANSWER() %></textarea></dd>
			</dl>
			<dl class="nowrap">
				<dt>收费依据</dt>
				<dd><textarea name="CHARGE_BASIS" cols="80" rows="6"><%=list.get(0).getCHARGE_BASIS() %></textarea></dd>
			</dl>
			
			<dl>
				<dt>是否收费</dt>
				<dd id="CHARGE_FLAG">
					<script type="text/javascript">
               			addIsChargeSelect();
               		</script>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>收费标准</dt>
				<dd><textarea name="CHARGE_STANDARD" cols="80" rows="4"><%=list.get(0).getCHARGE_STANDARD() %></textarea></dd>
			</dl>
			<dl class="nowrap">
				<dt>自由裁量</dt>
				<dd><textarea name="STANDARD" cols="80" rows="6"><%=list.get(0).getSTANDARD() %></textarea></dd>
			</dl>
			<dl class="nowrap">
				<dt>处罚种类</dt>
				<dd><textarea name="PUNISH_CLASS" cols="80" rows="4"><%=list.get(0).getPUNISH_CLASS() %></textarea></dd>
			</dl>
			<dl class="nowrap">
				<dt>外网在线办理链接</dt>
				<dd><textarea name="TRANSACT_URL" cols="80" rows="4"><%=list.get(0).getTRANSACT_URL() %></textarea></dd>
			</dl>
			<dl>
				<dt>监督电话</dt>
				<dd>
					<input type="text" name="SUPERVISE_TEL" value="<%=list.get(0).getSUPERVISE_TEL() %>" maxlength="80" class="" />
				</dd>
			</dl>
			<dl>
				<dt>联系电话</dt>
				<dd>
					<input type="text" name="LINK_TEL" value="<%=list.get(0).getLINK_TEL() %>" maxlength="80" class="" />
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>办理地点</dt>
				<dd><textarea name="TRANSACT_ADDR" cols="80" rows="2"><%=list.get(0).getTRANSACT_ADDR() %></textarea></dd>
			</dl>
			<dl class="nowrap">
				<dt>办理部门</dt>
				<dd><textarea name="TRANSACT_DEPNAME" cols="80" rows="2"><%=list.get(0).getTRANSACT_DEPNAME() %></textarea></dd>
			</dl>
			<dl class="nowrap">
				<dt>受理条件</dt>
				<dd><textarea name="CONDITION" cols="80" rows="6"><%=list.get(0).getCONDITION() %></textarea></dd>
			</dl>
			<!--
			<dl class="nowrap">
				<dt>申办材料</dt>
				<dd><textarea name="bid_data" cols="80" rows="4"></textarea></dd>
			</dl>
			 -->
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>
  
  </body>
</html>