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
	var selectElIndex = 0;//���� ���������������˵�
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
				beforeSearch:function(val){//�����ַ���ѯ֮ǰ
				},
				endSearch:function(val){//�����ַ���ѯ��
				},
				height:200,
				displayId:'value' //��ʾ�ַ��������еļ�
			},config);
		if(this.length > 0){
			for(var $i=0;$i<this.length;$i++){
				var me = this[$i];
				me.settings = settings;
				var index;//����ָ�򱻱�ǵĶ���
				var curEl;//��ǰ������ʾ�Ľڵ�
				var initIndex = -1;
				me.datas;
				me.displayList;
				var showDiv = new Array();
				var searchValue = '';
				var pressTime = null;
				var ElTop = 0;
				var ElLeft = 0;
				var ElHeight = 20;//�ӽڵ�ĸ߶�
				var willHide = false;//�ж��Ƿ��ܹ����ؿؼ�
				var maxHeight = 200;//Ĭ�����߶� 200;
				/* �����µĿؼ� */
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
				me.selectElIndex = selectElIndex;//ΪDOM������е����� ���ں�����Ҷ�Ӧ�Ŀؼ�
				selectElIndex++;
				/* ���������µĿؼ� */
				function clickField(obj){
					//����ؼ����ú���
					choose(index);
					closeField();//ѡ�к�ر�
				}
				function showField(){
					//��ʾ�ؼ�
					inIndex();//��ʼ������
					changeSize();
					selectParent.show();
					if(value() != null){//��ʾʱ�����ֵ�Ļ�����һ��
						search();
					}
				}
				function closeField(){
					selectParent.hide();//���ؿؼ�
					notShowHighAll();//ȡ�����и���
					me.blur();
					inIndex();
					curEl = null;
					if(value() == ''){
						me.settings.whenEmptyFn&&me.settings.whenEmptyFn();
					}
				}
				function slipDown(){
					//ѡ����һ��ѡ��
					//������һ��
					slipChoose(1);
				}
				function value(){//��ȡֵ
					return $(me).val();
				}
				function slipUp(){
					//ѡ����һ��ѡ��
					//������һ��
					slipChoose(-1);
				}
				function slipChoose(step){
					getIndexByStep(step);
					showHighByIndex(index);//������ʾ
					showInMiddle();
					choose(index);
					//(showDiv.length - height/elHeight)
				}
				function getIndexByStep(step){
					//ͨ����������ѡ��ʱ��ȡ��ǰ��������
					if(index == initIndex){//��ʾ��ʼ��
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
				function getShowEl(index){//����������ȡ��ǰѡ��Ľڵ�
					return showDiv[index];//������ؿձ�ʾû��
				}
				function getIndexByShowEl(obj){//����������ȡ��ǰѡ��Ľڵ�
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
					//��������ѡ��
					//������ʾ ��ѡ��
					notShowHighAll();
					var obj = getShowEl(index);//��ȡ��ǰѡ��Ľڵ�
					if(!!obj){
						showHigh(obj);
					}
				}
				function choose(index){
					//ѡ�����ѡ��
					//��ֵ���������
					var obj = getShowEl(index);//��ȡ��ǰѡ��Ľڵ�
					if(!!obj){
						$(me).val($(obj).text());
						//���ú���
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
					//��ѯ�����ֵ
					//����ÿһ���ڵ㣬����ڵ��ֵ����������е�ֵ����ʾ��������
					
					//����ǰ��ʾ�Ľڵ����һ�� ������
					//��������ָ��ǰѡ�е�DOM��ֻ��$().length -1;
					//���Ƴߴ�
					var val = value();
					me.settings.beforeSearch(val);
					showDiv = new Array();//�����ʾ��DOM ��ʼ��
					var highlineIndex = -1;
					if(me.displayList != null && me.displayList.length>0){
						var objects = selectParent.children('.displayList');
						for(var i=0; i<me.displayList.length;i++){
							if(!!me.settings.searchCheckFn && !me.settings.searchCheckFn(val,i)){
								//���صĽڵ�
								$(objects[i]).removeClass('show').addClass('hide').remove('highline');
							}else{//��ʾ�Ľڵ�
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
					searchValue = val;//��¼��ǰ�Ĳ�ѯ�ַ�
					me.settings.endSearch(val);
				}
				function mouseover(obj){//��껮���ڵ�
					//������ʾ��ǰ�ڵ�
					notShowHighAll();
					showHigh(obj);
					return me;
				}
				function mouseout(obj){
					//��ǰ�ڵ�ȡ������
					notShowHigh(obj);
					inIndex();
					return me;
				}
				function showHigh(obj){
					//������ʾ
					$(obj).addClass('highline');
					index = getIndexByShowEl(obj);
					curEl = obj;
					return me;
				}
				function notShowHigh(obj){
					//��������ʾ
					$(obj).removeClass('highline');
					return me;
				}
				function notShowHighAll(){//ȫ��ȡ������
					if(showDiv != null && showLength() > 0){
						for(var i=0;i<showLength();i++){
							notShowHigh(showDiv[i]);
						}
					}
					return me;
				}
				function hideEl(obj){
					//����
					$(obj).removeClass('show').addClass('hide');
					return me;
				}
				function showEl(obj){
					//��ʾ
					$(obj).removeClass('hide').addClass('show');
					return me;
				}
				function changeSize(){
					//�߶ȴ��ڣ� Ĭ�����߶Ȼ��߿ؼ�����ҳ���¶�ʱ�������߶�
					var bodyHeight = document.body.scrollHeight;
					maxHeight = bodyHeight - ElTop + 20;//���div�ĸ߶ȳ�����ҳ��׶� �����Ƹ߶�
					//maxHeight ����������޶�  �����Ϊ��һ׼��
					if(maxHeight > me.settings.height){
						maxHeight = me.settings.height;
					}
					var height = 'auto';
					var divHeight = showLength()*ElHeight;//��ʾ�ĸ߶� �����ǰ����ӽڵ�ĸ߶��������
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
					showField();//��ʾ�ؼ�
				}).blur(function(){
					//�� willHide ��Ϊ true
					//�ӳ�500���� ���willHide=true ������ ���Ϊfalse ����
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
							closeField();//ѡ�к�ر�
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
						case 40 : //��
							slipDown();
							e.preventDefault();
							break;
						case 38 : //��
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
				//����ѡ���DOM�ڵ�
				me.render = function(){
					//����DOM
					//����������������Ҫ��ʾ���ֶΣ�����div��dom
					//����� click��mouseover��mouseout���¼�
					//���Ƴߴ�
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
					showDiv = selectParent.children('.displayList');//�����ʾ��DOM
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