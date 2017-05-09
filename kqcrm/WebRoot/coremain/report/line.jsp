<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.nl.util.config.DictMgmt"%>
<%@page import="com.nl.report.dt.*"%>
<%@ page import="com.nl.report.actionForm.*"%>
<%@page import="java.util.List"%>
<%@page import="com.nl.util.DateTime"%>

<%@ page import="com.nl.util.GlobalConst"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ReportForm repForm = (ReportForm) request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
List<ReportInfo> org_list = (List<ReportInfo>) request.getAttribute("org_list");//
List<ReportInfo> line_list = (List<ReportInfo>) request.getAttribute("line_list");//
List x_point =new ArrayList();
List y_point =new ArrayList();
List x_day =new ArrayList();
String max_date = "0";
String min_date = "0";
if(line_list!=null&&line_list.size()>0){
	for(int i=0;i<line_list.size();i++){
		ReportInfo point = line_list.get(i);
		x_point.add(i,point.getDate_tj());
		y_point.add(i,point.getCnt_tj());
		String date_tj = point.getDate_tj();
		if(Integer.parseInt(date_tj)>Integer.parseInt(max_date)){
			max_date = date_tj;
		}
		if(i==0){min_date = date_tj;}
		if(Integer.parseInt(date_tj)<Integer.parseInt(min_date)){
			min_date = date_tj;
		}
		
	}

	java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");
	Date max_dt =  df.parse(max_date);
	Date min_dt =  df.parse(min_date);
	int dif_day = (int)DateTime.diffDate(max_dt , min_dt,1)/24;
	for(int j=0;j<=dif_day;j++){
		Date tmp_dt = DateTime.addDate(min_dt,j*24);
		x_day.add(j,df.format(tmp_dt));
	}
}

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>折线图</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=path%>/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="<%=path%>/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="<%=path%>/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
	<link href="<%=path%>/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
	
	<script type="text/javascript">
		//var options = {
		//	axis: "0 0 1 1", // Where to put the labels (trbl)
		//	axisxstep: '<%=x_point.size()-1  %>', // How many x interval labels to render (axisystep does the same for the y axis)
		//	shade:false, // true, false
		//	smooth:false, //曲线
		//	symbol:"circle",
		//	colors:["#F44"]
		//};
		
		//x、y ,java to js
				var data_x = []; 
				var data_y = []; 
				var x_data = []; 
				
		var options = {
			axis: "0 0 1 1", // Where to put the labels (trbl)
			axisxstep: '<%=x_day.size()-1  %>', // How many x interval labels to render (axisystep does the same for the y axis)
			//axisxlables: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
			axisxlables: x_data,
			shade:false, // true, false
			smooth:false, //曲线
			symbol:"circle",
			colors:["#F44"]
		};
		
		
		
		
		$(function () {
			var report_type = '<%=repForm.getReport_type()%>';
			if(report_type!=''){
				if(report_type=='1'){
				 	document.getElementById("span_msg").style.display='block';
				}else{
					document.getElementById("span_msg").style.display='none';
				}
			}
			
			<%
				if(x_point!=null&&x_point.size()>0){
					for(int j=0;j<x_point.size();j++)
					{%>
					    data_x.push('<%=x_point.get(j)%>');
					<%}
					for(int k=0;k<y_point.size();k++)
					{%>
					    data_y.push('<%=y_point.get(k)%>');
					<%}
					for(int m=0;m<x_day.size();m++)
					{%>
					    x_data.push('<%=x_day.get(m)%>');
					<%}
				}else{
					%>
				    return;	
				<%
				}
				%>
		
			
			// Make the raphael object
			var r = Raphael("chartHolder"); 
			
			var lines = r.linechart(
				20, // X start in pixels
				20, // Y start in pixels
				800, // Width of chart in pixels
				400, // Height of chart in pixels
				data_x,
				data_y,
				options // opts object
			).hoverColumn(function () {
		        this.tags = r.set();

		        for (var i = 0, ii = this.y.length; i < ii; i++) {
		            this.tags.push(r.tag(this.x, this.y[i], this.values[i], 160, 10).insertBefore(this).attr([{ fill: "#fff" }, { fill: this.symbols[i].attr("fill") }]));
		        }
		    }, function () {
		        this.tags && this.tags.remove();
		    });
		
		    lines.symbols.attr({ r: 6 });
	});




		function changeType(){
			var report_type = $('#report_type').val();
			if(report_type=='1'){
				 document.getElementById("span_msg").style.display='block';
			}else{
				document.getElementById("span_msg").style.display='none';
			}
		
		}
		
	</script>
	
	
  </head>
  
  <body>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=path%>/reportAction.do?method=toGetLine" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					统计类型：
					<select  name="report_type" id="report_type" onChange="changeType();">
						<option value="1" <%if("1".equals(repForm.getReport_type())){%>selected<%} %>>发布量统计</option>
						<option value="2" <%if("2".equals(repForm.getReport_type())){%>selected<%} %>>出错量统计</option>
					</select>
					<span id="span_msg" style="display:block;">
						信息类型：
						<select  name="message_type" id="message_type" >
							<option value="1" <%if("1".equals(repForm.getMessage_type())){%>selected<%} %>>住建动态</option>
							<option value="2" <%if("2".equals(repForm.getMessage_type())){%>selected<%} %>>通知公告</option>
							<option value="3" <%if("3".equals(repForm.getMessage_type())){%>selected<%} %>>政策法规</option>
							<option value="4" <%if("4".equals(repForm.getMessage_type())){%>selected<%} %>>办事指南</option>
							<option value="5" <%if("5".equals(repForm.getMessage_type())){%>selected<%} %>>资料档案</option>
							<option value="6" <%if("6".equals(repForm.getMessage_type())){%>selected<%} %>>阳光政务</option>
						</select>
						发布处室：
						<select  name="org_id" id="org_id" >
							<%
							if(org_list!=null&&org_list.size()>0){
								for(int i=0;i<org_list.size();i++){
									ReportInfo org = org_list.get(i);
									%>
									<option value="<%=org.getOrg_id() %>" <%if(org.getOrg_id().equals(repForm.getOrg_id())){%>selected<%} %>><%=org.getOrg_name() %></option>
									<%
								}
							}
							%>
						</select>
					</span>
					选择时间：<input type="text" name="begin_date" class="date" readonly="true" value="<%=repForm.getBegin_date() %>"/> ~ <input type="text" name="end_date" class="date" readonly="true"  value="<%=repForm.getEnd_date() %>"/> 
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">统计</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
 
 
<div id="chartHolder" style="width:80%;height:450px"></div>
  
  
  </body>
</html>
