(function($) {
		document.write('<style type="text/css">' +
			'.displayDiv{' +
			'	position:absolute;display:none;border:1px solid #5E7D99;overflow-y:auto;top:100px;left:100px;' +
			'	background-color:white;color:#444444;z-index:999;}' +
			'.displayDiv .displayList{' +
			'	cursor:pointer;text-align:left;inline-height:20px;' +
			'	overflow:hidden;white-space:nowrap;text-overflow:ellipsis;' +
			'	-o-text-overflow:ellipsis;-icab-text-overflow: ellipsis;-khtml-text-overflow: ellipsis;' +
			'	-moz-text-overflow: ellipsis;-webkit-text-overflow: ellipsis;background-color:white;}' +
			'.displayDiv .highline{' +
			'	background-color:#8EaDa9;' +
			'}' +
			'.displayDiv .show{' +
			'	display:block;' +
			'}' +
			'.displayDiv .hide{' +
			'	display:none;' +
			'}' +
			'<\/style>');
	var selectElIndex = 0;//序列 区别多个搜索下拉菜单
	$.fn.searchSelect=function(config){
		var settings = $.extend({
				chooseFn:function(data,index){
				},
				whenErrorFn:function(){
				},
				whenEmptyFn:function(){
				},
				searchCheckFn:function(val,index){
					if(!!me.displayList && index != null){
						if(me.displayList[index].toLowerCase().indexOf(val.toLowerCase()) >= 0){
							return true;
						}
					}
					return false;
				},
				blurFn:function(data){
				},
				beforeSearch:function(val){//输入字符查询之前
				},
				endSearch:function(val){//输入字符查询后
				},
				height:200,
				displayId:'value' //显示字符在数据中的键
			},config);
		if(this.length > 0){
			for(var $i=0;$i<this.length;$i++){
				var me = this[$i];
				me.settings = settings;
				var index;//索引指向被标记的对象
				var curEl;//当前高亮显示的节点
				var initIndex = -1;
				me.datas;
				me.displayList;
				var showDiv = new Array();
				var searchValue = '';
				var pressTime = null;
				var ElTop = 0;
				var ElLeft = 0;
				var ElHeight = 20;//子节点的高度
				var willHide = false;//判断是否能够隐藏控件
				var maxHeight = 200;//默认最大高度 200;
				/* 创建新的控件 */
				var selectParent = $('<div id="displayDiv'+selectElIndex+'" class="displayDiv"></div>')
					.blur(function(){
						willHide = true;
						window.setTimeout(function(){
							if(willHide){
								closeField();
							}
						},200);
					}).focus(function(){
						willHide = false;
					})
					.appendTo(document.body);
				me.selectElIndex = selectElIndex;//为DOM添加序列的属性 用于后面查找对应的控件
				selectElIndex++;
				/* 结束创建新的控件 */
				function clickField(obj){
					//点击控件调用函数
					choose(index);
					closeField();//选中后关闭
				}
				function showField(){
					//显示控件
					inIndex();//初始化索引
					changeSize();
					selectParent.show();
					if(value() != null){//显示时如果有值的话搜索一次
						search();
					}
				}
				function closeField(){
					selectParent.hide();//隐藏控件
					notShowHighAll();//取消所有高亮
					me.blur();
					inIndex();
					curEl = null;
					if(value() == ''){
						me.settings.whenEmptyFn&&me.settings.whenEmptyFn();
					}
				}
				function slipDown(){
					//选择下一个选项
					//索引升一个
					slipChoose(1);
				}
				function value(){//获取值
					return $(me).val();
				}
				function slipUp(){
					//选择上一个选项
					//索引降一个
					slipChoose(-1);
				}
				function slipChoose(step){
					getIndexByStep(step);
					showHighByIndex(index);//高亮显示
					showInMiddle();
					choose(index);
					//(showDiv.length - height/elHeight)
				}
				function getIndexByStep(step){
					//通过按键上下选择时获取当前的索引；
					if(index == initIndex){//表示初始化
						if(step >= 0){
							index = - 1;
						}else{
							index = 0;
						}
					}
					index += step ;
					if(index < 0){
						index = showLength() + index;
					}else if(index >= showLength()){
						index = index - showLength();
					}
				}
				function inIndex(){
					index = initIndex;
				}
				function showInMiddle(){
					var scrollTop = ElHeight *(index+1)-selectParent[0].offsetHeight/2;
					selectParent[0].scrollTop = scrollTop;
				}
				function getShowEl(index){//根据索引获取当前选择的节点
					return showDiv[index];//如果返回空表示没有
				}
				function getIndexByShowEl(obj){//根据索引获取当前选择的节点
					if(!!showDiv && showLength() > 0){
						for(var i=0;i < showLength(); i++){
							if(obj == showDiv[i]){
								return i;
							}
						}
					}
					return -1;
				}
				function showLength(){
					return showDiv.length;
				}
				function showHighByIndex(index){
					//按键上下选择
					//高亮显示 不选择
					notShowHighAll();
					var obj = getShowEl(index);//获取当前选择的节点
					if(!!obj){
						showHigh(obj);
					}
				}
				function choose(index){
					//选择这个选项
					//将值赋给输入框
					var obj = getShowEl(index);//获取当前选择的节点
					if(!!obj){
						$(me).val($(obj).text());
						//调用函数
						var objects = selectParent.children('.displayList');
						for(var i=0;i<objects.length;i++){
							if(objects[i] == obj){
								var data = me.datas[i];
								me.settings.chooseFn&&me.settings.chooseFn(data,i);
								return;
							}
						} 
					}
					me.settings.whenErrorFn&&me.settings.whenErrorFn();
				}
				function search(){
					//查询输入的值
					//遍历每一个节点，如果节点的值包含输入框中的值就显示否则隐藏
					
					//将当前显示的节点放在一个 变量中
					//并将索引指向当前选中的DOM，只需$().length -1;
					//控制尺寸
					var val = value();
					me.settings.beforeSearch(val);
					showDiv = new Array();//存放显示的DOM 初始化
					var highlineIndex = -1;
					if(me.displayList != null && me.displayList.length>0){
						var objects = selectParent.children('.displayList');
						for(var i=0; i<me.displayList.length;i++){
							if(!!me.settings.searchCheckFn && !me.settings.searchCheckFn(val,i)){
								//隐藏的节点
								$(objects[i]).removeClass('show').addClass('hide').remove('highline');
							}else{//显示的节点
								showDiv.push(objects[i]);
							}
						}
					}
					changeSize();
					for(var i=0;i<showDiv.length;i++){
						$(showDiv[i]).removeClass('hide').addClass('show');
						if( !!curEl && curEl == showDiv[i]){
							highlineIndex = i;
						}
					}
					
					index = highlineIndex;
					searchValue = val;//记录当前的查询字符
					me.settings.endSearch(val);
				}
				function mouseover(obj){//鼠标划过节点
					//高亮显示当前节点
					notShowHighAll();
					showHigh(obj);
					return me;
				}
				function mouseout(obj){
					//当前节点取消高亮
					notShowHigh(obj);
					inIndex();
					return me;
				}
				function showHigh(obj){
					//高亮显示
					$(obj).addClass('highline');
					index = getIndexByShowEl(obj);
					curEl = obj;
					return me;
				}
				function notShowHigh(obj){
					//不高亮显示
					$(obj).removeClass('highline');
					return me;
				}
				function notShowHighAll(){//全部取出高亮
					if(showDiv != null && showLength() > 0){
						for(var i=0;i<showLength();i++){
							notShowHigh(showDiv[i]);
						}
					}
					return me;
				}
				function hideEl(obj){
					//隐藏
					$(obj).removeClass('show').addClass('hide');
					return me;
				}
				function showEl(obj){
					//显示
					$(obj).removeClass('hide').addClass('show');
					return me;
				}
				function changeSize(){
					//高度大于（ 默认最大高度或者控件超过页面下端时）调整高度
					var bodyHeight = document.body.scrollHeight;
					maxHeight = bodyHeight - ElTop + 20;//如果div的高度超过了页面底端 则限制高度
					//maxHeight 这个是最大的限度  以这个为第一准则
					if(maxHeight > me.settings.height){
						maxHeight = me.settings.height;
					}
					var height = 'auto';
					var divHeight = showLength()*ElHeight;//显示的高度 这里是按照子节点的高度算出来的
					if(divHeight > maxHeight){
						height = maxHeight+"px";
					}
					selectParent.css({
						height : height
					});
				}
				$(me).focus(function(){
					var left=this.offsetLeft;
					var top=this.offsetTop + this.offsetHeight;
					var parent = this;
					while(!!parent.offsetParent){
						parent = parent.offsetParent;
						left += parent.offsetLeft;
						top += parent.offsetTop;
					}
					ElLeft = left;
					ElTop = top;
					selectParent.css({
						left:left,top:top,
						width:this.offsetWidth,
						height : 'auto'
					});
					showField();//显示控件
				}).blur(function(){
					//将 willHide 置为 true
					//延迟500毫秒 如果willHide=true 则隐藏 如果为false 隐藏
					willHide = true;
					window.setTimeout(function(){
						if(willHide){
							closeField();
						}
					},200);
					me.settings.blurFn(me.value);
				}).keyup(function(e){
					var code = e.keyCode;
					if(code > 40 || code == 32 || code == 8 || code == 13){
						if($.trim(searchValue) != $.trim(value())){
							search();
						}
					}else{
						e.preventDefault();
					}
				}).keypress(function(e){
					var code = e.keyCode;
					switch(code){
						case 13 :
							choose(index);
							closeField();//选中后关闭
							e.preventDefault();
							break;
						default:;
					}
				}).keydown(function(e){
					var code = e.keyCode;
					switch(code){
						case 9 : 
							clear();
							break;
						case 27 : 
							clear();
							modifySearchValue('');
							e.preventDefault();
							break;
						case 40 : //下
							slipDown();
							e.preventDefault();
							break;
						case 38 : //上
							slipUp();
							e.preventDefault();
							break;
						default:;
					}
				});
				function getShowIndex(obj){
					for(var i=0;i<showLength();i++){
						if(showDiv[i] == obj){
							return i;
						}
					}
				}
				//创建选项的DOM节点
				me.render = function(){
					//生成DOM
					//遍历操作对象中需要显示的字段，生成div的dom
					//并添加 click、mouseover、mouseout的事件
					//控制尺寸
					selectParent.children('.displayList').remove();
					if(!!me.displayList && me.displayList.length>0){
						for(var i=0; i<me.displayList.length; i++){
							selectParent.append(
								$('<div class="displayList show" ></div>')
									.css({height:ElHeight})
									.html(me.displayList[i])
									.click(function(){
										clickField(this);
									}).mouseover(function(){
										mouseover(this);
									}).mouseout(function(){
										mouseout(this);
									})
							);
						}
					}
					showDiv = selectParent.children('.displayList');//存放显示的DOM
				}
				return this;
			}
		}
	}
	$.fn.fillData=function(data){
		if(this.length>0){
			for(var $i=0;$i<this.length;$i++){
				var me = this[$i];
				var selectParent = $('#displayDiv'+me.selectElIndex);
				selectParent.children('.displayList').remove();
				me.datas = data;
				me.displayList = new Array();
				if(!!data && data.length>0){
					for(var ii in data){
						me.displayList.push(data[ii][me.settings.displayId]);
					}
				}
				me.render();
			}
		}
	}
	$.fn.clearData=function(){
		if(this.length>0){
			for(var $i=0;$i<this.length;$i++){
				var me = this[$i];
				$('#displayDiv'+me.selectElIndex)
				.children('.displayList')
				.remove();
			}
		}
	}
})(jQuery);