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
	<style>
		*{margin:0;padding:0}
	</style>
  </head>
  
  <body>
	<div style="padding:5px 5px 5px 10px;position:absolute;" id="noticeList">
		<ul id="notice_tb">
		</ul>
	</div>
  </body>
</html>
	<script type="text/javascript" src="<%=path%>/plugins/jquery/jquery-1.4.4.js"></script>
	<script type="text/javascript">
		jQuery(document).ready(function(){
			var ran = Math.random()*1000000;
			jQuery.post(
				'<%=path%>/noticeManageAction.do?method=queryNoticeInfoForWelcome2',
				{ran:ran},
				function(response){
					if(response.rtnCode == 0){
						if(response.rtnData == null || response.rtnData.length == 0){
							return;
						}
						var stopFlag=false;//是否滚动的标识  true表示停止滚动
						var datas = response.rtnData;
						var top = 0;
						var width = jQuery(document.body).width();
						var paddingBottom = 10;
						jQuery('#noticeList').bind('mouseover',function(){
									stopFlag = true;
								}).bind('mouseout',function(){
									stopFlag = false;
								});
						for(var ii=0; ii<datas.length; ii++){
							var liEl = jQuery('<li></li>').css({'cursor':'point', 'position':'absolute', 'top':top, width:width})
									.append(
										jQuery('<table></table>').append(
											jQuery('<tr height="25px"></tr>').append(
												jQuery('<td></td>')
													.css({'font-size':'12px','line-height':'22px','color':'#FF0000', 'font-weight':'600'})
													.html(datas[ii].noticeTitle+":")
											)
										).append(
											jQuery('<tr></tr>').append(
												jQuery('<td></td>')
													.css({'font-size':'12px','line-height':'22px'})
													.html(datas[ii].noticeText.replace('\n', '<br/>').replace('\r\n', '<br/>').replace('\r', '<br/>').replace(' ', '&nbsp;'))
											)
										)
									);
							jQuery('#notice_tb').append(
								liEl
							);
							top += liEl.height() + paddingBottom;
						}
						
						/**********调整iframe框架的高度**********/
						var noticemarque = jQuery(parent.document.getElementById('noticemarque'));
						jQuery(parent.document.getElementById('noticecon')).css('height', noticemarque.height()+"px");
						
						var liEls = jQuery('#noticeList li');
						var maxHeight = jQuery(liEls[liEls.length-1]).offset().top + jQuery(liEls[liEls.length-1]).height();
						if(maxHeight > noticemarque.height()){
							var moveSpace=1;//人寿距离
							window.setInterval(function(){
								//jQuery('#noticeList li:first-child').height() 为0 表示界面未显示  不显示的话则判断用户没有看，没看的话就不需要滚动
								var firstLi = jQuery('#noticeList li:first-child');
								if(!stopFlag && firstLi.height() != 0 && firstLi.length > 0){
									var liEls = jQuery('#noticeList li');
									liEls.each(function(){
										var top = jQuery(this).offset().top;
										jQuery(this).css({top:(top-moveSpace)});
									});
									
									/********如果公共标签集合底部进入 框架范围内 则复制顶部的标签至底部  *********/
									var nextLiTop = jQuery(liEls[liEls.length-1]).offset().top + jQuery(liEls[liEls.length-1]).height();
									if(nextLiTop < noticemarque.height()){
										//循环将第一个未复制的标签 复制到最下面
										while(nextLiTop < noticemarque.height()){
											var liEls = jQuery('#noticeList li');
											
											/****查询第一个未复制的标签****/
											var liEl = null;
											for(var ii=0; ii<liEls.length; ii++){
												liEl = liEls[ii];
												if(liEl.isClone != 'true'){
													break;
												}
											}
											/********/
											
											if(liEl != null){
												var cloneEL = jQuery(liEl).clone();
												cloneEL.attr('isClone', 'false');
												
												cloneEL.css({top:(nextLiTop + cloneEL.height() + paddingBottom)});
												jQuery('#notice_tb').append(cloneEL);
												liEl.isClone = 'true';
												var nextLiTop = jQuery(cloneEL).offset().top + jQuery(cloneEL).height();
											}else{
												stopFlag = true;//如果为空的话 说明有问题 停止滚动
												break;
											}
										}
									}
									
									/********如果公共标签集合顶部超过 框架范围 删除超出的标签 *********/
									var liEl = jQuery('#noticeList li:first-child');
									while((liEl.offset().top + liEl.height()) < 0 && liEl.attr('isClone') == 'true'){
										liEl.remove();
										liEl = jQuery('#noticeList li:first-child');
									}
								}
							}, 200);
						}
					}else{
						alert('公告历史出错，请重新尝试或者联系管理人员！\n原因：'+response.rtnInfo);
					}
				},
				'json'
			);
		});
	</script>
