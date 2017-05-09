/**
 * 等待执行的类：
 *   loadingImg ： 图片的路径
 *   imgPath ： 根路径
 *   delay : 延时隐藏
 */
function LoadClass(config){
	config = config == null ? {} : config;
	var loadingImg = config.loadingImg == null ? '/images/portal/img/loader3.gif' : config.loadingImg;
	var loadingImgPath = config.imgPath == null ? '/portalzj' : config.imgPath;
	var filterPer = config.filterPer == null ? '60' : config.filterPer;
	var backgroundColor = config.backgroundColor == null ? '#FFF' : config.backgroundColor;
	var parentEL = config.parentEL == null ? document.body : config.parentEL;
	var delay = config.parentEL == null ? 500 : config.delay;
	var time = new Date().getTime();
	var id = 'loaddingDiv' + time;
	var loadO = jQuery('<div></div>').attr('id', id)
				.css({
					'z-index' : 1000,
					'position' : 'absolute',
					'filter' : 'alpha(opacity='+filterPer+')',
					'background-color' : backgroundColor,
					left : '0px',
					top : '0px'
				}).hide();
	var img = jQuery('<img/>').attr({
						src : loadingImgPath + loadingImg,
						width : '120',
						height : '120',
						'z-index' : 1001
					}).css('position', 'absolute');
	loadO.append(img);
	jQuery(parentEL).append(loadO);
	this.start=function(){
		var width = document.body.scrollWidth;
		var height = document.body.scrollHeight;
		img.css({
			left : (width - img.width())/2,
			top : (height - img.height())/2
		})
		loadO.css({
			width : width,
			height : height
		});
		loadO.show();
	}
	this.stop=function(){
		window.setTimeout(function(){
			loadO.hide();
		}, delay);
	}
	return this;
}