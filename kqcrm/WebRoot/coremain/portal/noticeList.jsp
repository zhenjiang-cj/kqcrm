<%@ page language="java" pageEncoding="GBK" isELIgnored="false"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv='pragma' content='no-cache' >
	<meta http-equiv='Cache-Control' content='no-cache, must-revalidate' >
	<meta http-equiv='expires' content='0' >
	<link href="<%=path%>/css/welcome.css" rel="stylesheet" type="text/css" />
	<link href="<%=path%>/css/systemHead.css" type="text/css" rel="stylesheet" />
  </head>
  
  <body>
	<div class="manualttlbox">
		<div class="allttl noticettl2" style="float:left">公告信息</div>
	    <a href="#" class="more" style="float:right; margin-top:15px;" onclick="doRepository()"></a>
	</div>
	
	<ul class="list">
	</ul>
  </body>
</html>
<script type="text/javascript" src="<%=path%>/plugins/jquery/jquery-1.4.4.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function(){
		var ran = Math.random()*1000000;
		jQuery.post(
			'<%=path%>/noticeManageAction.do?method=queryNoticeInfoForWelcome',
			{ran:ran},
			function(response){
				if(response.rtnCode == 0){
					var datas = response.rtnData.items;
					for(var ii=0; ii<datas.length; ii++){
						jQuery('.list').append(
							jQuery('<li></li>').append(
								jQuery('<a href="#" noticeId="'+datas[ii].noticeId+'" ></a>').html(datas[ii].noticeTitle)
									.bind('click', function(){
								    	var url='<%=path%>/noticeManageAction.do?method=detailNoticeInfo&noticeId='+this.noticeId;
										parent.parent.parent.showD(url,1000,400,'公告详细',100);	
									})
							)
						);
					}
				}else{
					
				}
			},
			'json'
		);
	});
	
	function doRepository(){
		var ran = Math.random()*1000000;
    	var pObj = parent;
    	while(!!pObj){
    		if(!!pObj.addTask){
    			pObj.addTask('历史公告', '<%=path%>/noticeManageAction.do?method=toNoticeList2&ran='+ran);
    			break;
    		}else{
    			pObj = pObj.parent;
    		}
    	}
    }
</script>