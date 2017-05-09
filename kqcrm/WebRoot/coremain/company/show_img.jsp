<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.nl.company.sc.CompanySc"%>
<%@ page import="com.nl.company.dt.PicFileInfo"%>
<%@ page import="com.nl.company.actionForm.*"%>
<%@ page import="com.nl.util.GlobalConst"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String type = request.getParameter("type");
String companyid = request.getParameter("companyid");
CompanySc sc = new CompanySc();
List<PicFileInfo> picList = sc.getPicList(type,companyid);
int cnt_pic=1;
if(picList!=null&&picList.size()>0){
	cnt_pic = picList.size();
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'show_img.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
	/*base*/
	ul,
	li {
		margin: 0;
		padding: 0;
		list-style: none;
	}
	.clearfix:before,
	.clearfix:after {
		content: " ";
		display: table;
	}
	.clearfix:after {
		clear: both;
	}
	.clearfix {
	 *zoom: 1;
	}
	/*main*/
	.imgContainer {
		width: 645px;
		margin: 20px auto;
		padding: 10px;
		box-shadow: 1px 0 6px 0 rgba(180, 180, 180, 0.4);
	}
	.imgUl {
		position: relative;
		width:2500px;
	}
	.box {
		width: 500px;
		margin-left: 30px;
		overflow: hidden;
	}
	.detailImg,
	.smallImg {
		position: relative;
	}
	/*detailImg*/
	#detailImg-box {
		min-height: 200px;
	}
	.detailImg {
		text-align: center;
	}
	.detailImg img {
		max-width: 400px;
		max-height: 400px;
	}
	.detailImg p {
		text-align: left;
		color: #999;
	}
	#detailImg-pre,
	#detailImg-next {
		position: absolute;
		width: 30px;
		height: 46px;
		top: 50%;
		background: url(image/page_button.gif) 0 0 no-repeat;
		cursor: pointer;
		text-indent:-9999px;
	}
	#detailImg-pre {
		left: 0;
		background-position: -35px 0;
	}
	#detailImg-next {
		right: 0;
		background-position: -99px 0;
	}
	#detailImg-pre:hover {
		background-position: -35px -47px;
	}
	#detailImg-next:hover {
		background-position: -99px -47px;
	}
	/*smallImg*/
	#smallImg-box {
		height: 92px;
	}
	.smallImg li {
		float: left;
		width: 76px;
	}
	.smallImg li a {
		display: block;
		width: 70px;
		height: 70px;
		border: 3px solid #fff;
		overflow: hidden;
		vertical-align: middle;
		margin: 5px auto;
	}
	.smallImg img {
		width: 70px;
	}
	.smallImg li a:hover,
	.smallImg .cur a {
		border-color: #F45E5E;
	}
	#smallImg-pre,
	#smallImg-next {
		position: absolute;
		top: 0;
		height: 92px;
		width: 28px;
		margin: 0;
		background: url(image/album-arrow.png) 0 0 no-repeat;
		cursor: pointer;
	}
	#smallImg-pre {
		left: 0;
	}
	#smallImg-next {
		right: 0;
		background-position: 0 -94px;
	}
	#smallImg-pre:hover {
		background-position: -28px 0;
	}
	#smallImg-next:hover {
		background-position: -28px -94px;
	}
	</style>

  </head>
  
  <body>
  <%
  if(picList!=null&&picList.size()>0){
	  %>
	   
	   <div class="imgContainer">
		<!--大图-->
		<div class="detailImg" > 
			<a id="detailImg-pre">&lt;</a>
			<div id="detailImg-box" class="box"> </div>
			<a id="detailImg-next">&gt;</a> 
		</div>
		<!--小图-->
		<div class="smallImg"> 
			<a id="smallImg-pre"></a>
			<div id="smallImg-box" class="box">
				<ul id="smallImg-ul" class="imgUl">
				</ul>
			</div>
			<a id="smallImg-next"></a> 
		</div>
		<div>
		 <button onclick="del_img()">删除当前图片</button>
		 <a class="button" href="" id="all_img" target="_blank" rel="show_all_img" title="展示全图" onmousedown="show_img()"> <span >展示全图</span></a>
		</div>
	</div>
	
	<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
	  
	  <%
	  
  }else{
	  %> 
	  <div class="imgContainer">
	    	没有图片
	    </div>
	  
	  <%
	  
  }
  %>
    
  </body>
  
  
<script>

var img=[];
var cur_img=0;
var del_imga = $('#del_imga'); 

$(function(){
	var cnt_img = '<%= cnt_pic  %>';
	//循环构成图片数组
	for(var m=0;m<cnt_img;m++){		
		var pic = {
		        //'href':'#',
				'alt':'图片'+(m+1),
				//'src':'<%=path%>/images/homepage/adv01.jpg',
				'src':'<%=path%>/companyAction.do?method=showPhoto&type=<%=type %>&companyid=<%=companyid %>&file_num='+(m+1)+'&r='+Math.random(),
				'smallSrc':'<%=path%>/companyAction.do?method=showPhoto&type=<%=type %>&companyid=<%=companyid %>&file_num='+(m+1)+'&r='+Math.random(),
				'title':'标题'+(m+1)
			};
		img[m] = pic;
	}


	var i=0,//大图编号
		len=img.length,//img数组的长度
		cur=0;//当前图片编号
		j='<%= cnt_pic  %>',//默认显示小图个数
		page=0,//小图的页码
		$s_next=$('#smallImg-next'),//小图下一页
		$s_pre=$('#smallImg-pre'),//小图上一页
		box=$('#smallImg-box').width(),//显示的长度
		$ul=$('#smallImg-ul'),//小图外层
		$imgLi=$ul.find('li'),//小图li
		html=_html='';//存放载入的代码		
	//$('#detailImg-box').append('<a href=\"'+img[0].href+'\" class=\"detailImg_1\"><img alt=\"'+img[0].alt+'\" src=\"'+img[i].src+'\"></a><p>'+img[i].title+'</p>');
	$('#detailImg-box').append('<img alt=\"'+img[i].alt+'\" src=\"'+img[i].src+'\"><p>'+img[i].title+'</p>');
	//大图	
	$('#detailImg-next').click(function(){
		++i;
		detailImg_click($s_next,i,len);
	})
	$('#detailImg-pre').click(function(){
		--i;
		detailImg_click($s_pre,i,len);
	})
	//小图
	for(var k=0;k<j;k++){
		var _k=k%len;
		s_html(_k);
		html+=h;
	}
	$ul.append(html);
	$('.smallImg_1').addClass('cur');	
	//小图下一页
	$('#smallImg-next').click(function(){
		if(!$ul.is(':animated')){
			page++;
			var a=page*j,_a,c;
			for(var k=0;k<j;k++,a++){
				smallImg_click(a,_a,len,i);
				_html+=h;
			}
			$ul.append(_html);
			$ul.css({'left':0,'right':'auto'});
			$ul.animate({left:-box},1600,function(){
				$ul.find('li:lt('+j+')').detach();
				$ul.css('left',0);
				_html='';
			});//动画执行后,再删除前9个li,将left设回0
			$('#smallImg-ul li').click(function(){//三处一样，不知道这个要怎么优化？？？
				var _this=$(this);
				i=_this.attr('class').replace(/[^0-9]/ig,'')-1;
				img_info(i);
				s_a_r(_this,'cur');
				cur=i;
				cur_img = i;
				del_imga.href='<%=path%>/companyAction.do?method=delImg&type=<%=type %>&companyid=<%=companyid %>&file_num='+(cur_img+1)+'&r='+Math.random();
			})
		}
	})
	//小图上一页
	$('#smallImg-pre').click(function(){
		if(!$ul.is(':animated')){
			page--;
			var a=(page-1)*j,_a,c;
			for(var k=0;k<j;k++,a--){
				smallImg_click(a,_a,len,i);
				_html=h+_html;
			}
			$ul.prepend(_html).css({'right':box,'left':'auto'});
			$ul.animate({right:0},1600,function(){
				$ul.find('li:gt('+(j-1)+')').detach();//删除后9个li,从8开始
				_html='';
			});
			$('#smallImg-ul li').click(function(){
				var _this=$(this);
				i=_this.attr('class').replace(/[^0-9]/ig,'')-1;
				img_info(i);
				s_a_r(_this,'cur');
				cur=i;
				cur_img = i;
				del_imga.href='<%=path%>/companyAction.do?method=delImg&type=<%=type %>&companyid=<%=companyid %>&file_num='+(cur_img+1)+'&r='+Math.random();
			})
		}
			
	})
	//点击小图
	$('#smallImg-ul li').click(function(){
		var _this=$(this);
		i=_this.attr('class').replace(/[^0-9]/ig,'')-1;
		img_info(i);
		s_a_r(_this,'cur');
		cur=i;
		cur_img = i;
		del_imga.href='<%=path%>/companyAction.do?method=delImg&type=<%=type %>&companyid=<%=companyid %>&file_num='+(cur_img+1)+'&r='+Math.random();
		
	})
})

/*----自定义函数-----------*/
//大图图片信息
function img_info(i){
	var href=img[i].href,
		alt=img[i].alt,
		src=img[i].src,
		title=img[i].title,
		$main=$('#detailImg-box');
	$main.find('a').attr({'href':href,'class':'detailImg_'+(i+1)});
	$main.find('img').attr({'alt':alt,'src':src});
	$main.find('p').text(title);
}
function s_a_r(o,c){
	o.addClass(c).siblings().removeClass(c);	
}
//大图左右点击
function i_cur(i,len){
	i=i%len;
	if(i<0){
		i=len+i;
	}
	return i;	
}
function detailImg_click($pn,i,len){
	i_cur(i,len);
	img_info(i);
	var imgCur=$('.smallImg_'+(i+1));
	if(!imgCur.html()){
		$pn.click();
	} 
	s_a_r($('.smallImg_'+(i+1)),'cur');//小图选中
}
//小图左右点击
function smallImg_click(a,_a,len,i){
	_a=a;
	_a=a%len;
	if(_a<0){
		_a+=len;
	}
	c=_a==i?'cur':'';
	s_html(_a,c);
}
function s_html(_a,c){
	return h='<li class=\"smallImg_'+(_a+1)+' '+c+'\"><a><img alt=\"'+img[_a].alt+'\" src=\"'+img[_a].smallSrc+'\"></a></li>';
}
function del_img()
{
	var img_i = cur_img+1;
	var $thp = $(this).parent();
	try{
		$.ajax({
			    type:"post",
				dataType:"json",
				contentType:"application/json;charset=UTF-8",
				url:'<%=path%>/companyAction.do?method=delImg&type=<%=type %>&companyid=<%=companyid %>&file_num='+(img_i),
				//url:path+"/index/go4gDataPopularity.do?in_stat_date="+iframe_sum_date+"&grid_id="+grid_id+"&partition_id="+partition_id+"&county_id="+county_id
			    success:function(data){
			    	alert("删除图片成功!");
			    	//delpic(img_i);
			    	//$thp.reload();
			    	$.pdialog.reload("<%=path %>/coremain/company/show_img.jsp?type=<%=type %>&companyid=<%=companyid %>",'dlg_page1');
			    },
			    error:function (data){
			    	alert("删除图片失败!");
			    }
			});
	}catch(e){
		alert(e);
	}
}
function show_img()
{
	var img_i = cur_img+1;
	var $thp = $(this).parent();
	$('#all_img').attr("href","<%=path%>/coremain/company/showimg.jsp?type=<%=type %>&companyid=<%=companyid %>&file_num="+img_i+"&r="+Math.random());
}

function delpic(img_i)
{
//删除图片数组   //img.splice(img_i-1,delCount);//从start的位置开始向后删除delCount个元素
	img.splice(img_i-1,1);//删除当前图片


	var i=0,//大图编号
		len=img.length,//img数组的长度
		cur=0;//当前图片编号
		j=img.length,//默认显示小图个数
		page=0,//小图的页码
		$s_next=$('#smallImg-next'),//小图下一页
		$s_pre=$('#smallImg-pre'),//小图上一页
		box=$('#smallImg-box').width(),//显示的长度
		$ul=$('#smallImg-ul'),//小图外层
		$imgLi=$ul.find('li'),//小图li
		html=_html='';//存放载入的代码		
	//点击小图
	$('#smallImg-ul li').click(function(){
		var _this=$(this);
		i=_this.attr('class').replace(/[^0-9]/ig,'')-1;
		img_info(i);
		s_a_r(_this,'cur');
		cur=i;
		cur_img = i;
	})


}
</script>
</html>
