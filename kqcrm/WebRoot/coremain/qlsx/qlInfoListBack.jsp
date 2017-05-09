<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="../../WEB-INF/tlds/c-rt.tld" prefix="c"%>
<%@ taglib uri="../../WEB-INF/tlds/fn.tld" prefix="fn"%>
<%@ page import="com.nl.util.GlobalConst"%>
<%@ page import="com.nl.ql.actionForm.QlForm"%>

<%
	String path = request.getContextPath();
	QlForm afForm = (QlForm)request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
	List qllist = (List)request.getAttribute("qlinfolist"); 
	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=path%>">
    
    <title>��̨Ȩ�������б�</title>
<script type="text/javascript">
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
	  			strSelect = '�������ͣ�<select name="ql_type"><option value="" selected>--��ѡ��--</option>';
	  		else
	  			strSelect = '�������ͣ�<select name="ql_type"><option value="" >--��ѡ��--</option>';

	  		for(var i=0;i<arraySelect.length;i++){
	  			if(i+1 == ql_type)
	  				strSelect = strSelect +'<option value="'+arraySelect[i]['value']+'" selected>'+arraySelect[i]['value_name']+'</option>';
	  			else
					strSelect = strSelect +'<option value="'+arraySelect[i]['value']+'">'+arraySelect[i]['value_name']+'</option>';
				
			}
			strSelect = strSelect+'</select>';
			$('#init_ql_type').html(strSelect);
			
			
			
	  }
	});
	
}
</script>
  </head>
  
  <body>
    <form id="pagerForm" method="post" action="<%=path%>/qlsxBack.do?method=queryQlInfoList">
		<input type="hidden" name="pageNum" value="1" />
		<input type="hidden" name="numPerPage" value="20" />
	</form>


<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="<%=path%>/qlsxBack.do?method=queryQlInfoList" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					�������ƣ�<input type="text" name="companyName" size="30" />
				</td>
				<td id="init_ql_type">
					<script type="text/javascript">
               			addSelect();
               		</script>
					�������ͣ�
					<select name="ql_type">
						<option value="">--��ѡ��--</option>
					</select>
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">��&nbsp;ѯ</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="edit" href="<%=path %>/qlsxBack.do?method=queryQlInfoList&ql_reg_id={ql_reg_id}&modifyflag=1" target="navTab"><span>�޸�</span></a></li>
			<li><a class="delete" href="<%=path %>/qlsxBack.do?method=delQlInfo&ql_reg_id={ql_reg_id}&del_flag=1" target="ajaxTodo" title="ȷ��Ҫɾ����?"><span>ɾ��</span></a></li>
			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="5%" align="center">���</th>
				<th width="35%" align="center">Ȩ����������</th>
				<th width="15%" align="center">Ȩ����������</th>
				<th width="15%" align="center">ʵʩ��������</th>
				<th width="15%" align="center">��Чʱ��</th>
				<th width="15%" align="center">��ֹʱ��</th>
			</tr>
		</thead>
		<tbody>
			
			<%if(qllist.size()>0){ %>
			<c:forEach items="${qlinfolist}" var="list" varStatus="index">
			<tr target="ql_reg_id" rel="${list.ql_reg_id}">
			    <td>${index.index+1}</td>
			    <td>${list.ql_name}</td>
				<td>${list.ql_type}</td>
				<td>${list.ql_dep_name}</td>
				<td>${list.start_time}</td>
				<td>${list.end_time}</td>
			</tr>
			</c:forEach>
			<%}else{ %>
			  <tr>
		          <td align="center" colspan="5">�����ݣ�</td>
		      </tr>
			<%} %>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>��ʾ</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>
			<span>������${pager.totalCount}��</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${pager.totalCount}" numPerPage="${pager.numPerPage}" pageNumShown="10" currentPage="${pager.pageNum}"></div>

	</div>
</div>

  </body>
</html>
