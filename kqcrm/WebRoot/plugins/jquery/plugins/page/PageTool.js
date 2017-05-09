//����pageTags taglib����ʽ������ ��Ҫ����jquery����ļ�
/**
 * callBack ��ʾ�����ҳ�������¼�  
 *      ������� toPageNumParam(��Ҫ������ҳ��), startNum(��ʼ������), endNum(��������)
 *      �ص�������Ҫһ������ֵ  return true��ʾ�ɹ�  Ȼ��ҳ�����Ż������ת   
 */
function PageTool(pageSize, className, parentEl, callBack){
		var pageObj = this;
		var firstPage = new Page('��ҳ',
				function(curPageParam){
					return curPageParam > 1;
				},
				function(curPageParam){
					return 1;
				},
				function(curPageParam){
					init(curPageParam);
				}
			);
			var prePage = new Page('��һҳ', 
				function(curPageParam){
					return curPageParam > 1;
				},
				function(curPageParam){
					return curPageParam - 1;
				},
				function(curPageParam){
					init(curPageParam);
				}
			);
			var nextPage = new Page('��һҳ', 
				function(curPageParam){
					return curPageParam < pageObj.totalPage;
				},
				function(curPageParam){
					return curPageParam + 1;
				},
				function(curPageParam){
					init(curPageParam);
				}
			);
			var lastPage = new Page('βҳ', 
				function(curPageParam){
					return curPageParam < pageObj.totalPage;
				},
				function(curPageParam){
					return pageObj.totalPage;
				},
				function(curPageParam){
					init(curPageParam);
				}
			);
			//����ѯ�������б仯���õĺ���
			pageObj.initPage = function(curPageParam, totalNumParam){
				if(!totalNumParam){
					curPageParam = 0;
					totalNumParam = 0;
				}
				pageObj.curPage = curPageParam;
				pageObj.totalNum = totalNumParam;
				pageObj.totalPage = Math.ceil(totalNumParam/pageSize);
				totalNumSpan.html(totalNumParam);
				totalNumPageSpan.html(pageObj.totalPage);
				init(pageObj.curPage);
			}
			function init(curPageParam){
				firstPage.init(curPageParam);
				prePage.init(curPageParam);
				nextPage.init(curPageParam);
				lastPage.init(curPageParam);
				selectPage.init(curPageParam);
				selectPageInput.init(curPageParam);
			}
			var selectPage = jQuery('<span></span>');
			selectPage.init = function(curPageParam){
				jQuery(this).html(curPageParam)
			}
			var totalNumSpan = jQuery('<span></span>');
			var totalNumPageSpan = jQuery('<span></span>');
			var reg = /^[0-9]+$/;
			var selectPageInput = jQuery('<input name="selectPage"  class="in01" value="" id="fy" type="text"'+
						 'size="2" maxlength="4">')
							.bind('blur', function(){
								if(!reg.test(this.value)){
									alert('����������!');
									this.focus();
								};
							});
			selectPageInput.init = function(curPageParam){
				jQuery(this).val(curPageParam)
			}
			var selectPageButton = 
						jQuery('<input name=""  type="button" value="�� ת"  class="bntSty"/>')
							.bind('click', function(){
								var toPageNum = !selectPageInput.val() ? '1' : selectPageInput.val();
								if(toPageNum < 1 || toPageNum > pageObj.totalPage){
									alert('ҳ����������!');
									selectPageInput.focus();
									return;
								}
								doPage(parseInt(toPageNum), function(){
									init(pageObj.curPage);
								});
							});
							
			//�����ҳʱ�����ĺ���  ����toPageNumParam ����������ҳ��
			function doPage(toPageNumParam, resultCallback){
				var startNum = (toPageNumParam - 1) * pageSize + 1;
				var endNum = toPageNumParam * pageSize;
				var result = callBack.call(this, toPageNumParam, startNum, endNum);
				//�����dwr �������� �첽����Ļ� һ�´����ʧЧ  ��δ�����Ҫд�� callBack �ĺ�����
				if(result == true){
					pageObj.curPage = toPageNumParam;
					resultCallback.call(this, toPageNumParam);
				}
			}
			
			//��������ָ����Ҫǰ����ҳ��
			pageObj.doPage = doPage;
			
			//��ҳ����
			function Page(html,initCallBack , toPageCallback, clickListener){
				var page = new Object();
				page.init = function(curPageParam){
					var num = initCallBack.call(this, curPageParam);
					if(num == false){
						page.disable();
					}else{
						page.able();
					}
				}
				page.click = function(){
					var toPageNum = toPageCallback.call(this, pageObj.curPage);
					doPage(toPageNum, clickListener);
				}
				page.disable = function(){
					page.EL.css('disabled');
					page.EL.removeClass(className);
					page.EL.removeAttr('href');
					page.EL.unbind().bind('click',function(){});
				}
				page.able = function(){
					page.EL.css(className);
					page.EL.removeClass('disabled');
					page.EL.attr('href', 'javascript:void(0);');
					page.EL.unbind().bind('click', page.click);
				}
				page.EL = jQuery('<a></a>').html(html)
							.bind('click', page.click);
				page.init();
				return page;
			}
			$(parentEl).append(firstPage.EL).append('&nbsp;&nbsp;')
				.append(prePage.EL).append('&nbsp;&nbsp;')
				.append(nextPage.EL).append('&nbsp;&nbsp;')
				.append(lastPage.EL).append('&nbsp;&nbsp;')
				.append('�ܹ�').append(totalNumSpan).append('��>��')
				.append(selectPage).append('/').append(totalNumPageSpan).append('ҳ&nbsp;&nbsp;')
				.append('��ת��').append(selectPageInput).append(selectPageButton);
				
			pageObj.initPage(0,0);
			return pageObj;		
}