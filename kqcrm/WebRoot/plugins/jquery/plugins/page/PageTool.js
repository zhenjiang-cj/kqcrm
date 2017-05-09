//基于pageTags taglib的样式制作， 需要引入jquery类的文件
/**
 * callBack 表示点击翻页触发的事件  
 *      传入参数 toPageNumParam(需要跳往的页数), startNum(开始的数字), endNum(结束数字)
 *      回调函数需要一个返回值  return true表示成功  然后翻页函数才会完成跳转   
 */
function PageTool(pageSize, className, parentEl, callBack){
		var pageObj = this;
		var firstPage = new Page('首页',
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
			var prePage = new Page('上一页', 
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
			var nextPage = new Page('下一页', 
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
			var lastPage = new Page('尾页', 
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
			//当查询后总数有变化调用的函数
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
									alert('请输入数字!');
									this.focus();
								};
							});
			selectPageInput.init = function(curPageParam){
				jQuery(this).val(curPageParam)
			}
			var selectPageButton = 
						jQuery('<input name=""  type="button" value="跳 转"  class="bntSty"/>')
							.bind('click', function(){
								var toPageNum = !selectPageInput.val() ? '1' : selectPageInput.val();
								if(toPageNum < 1 || toPageNum > pageObj.totalPage){
									alert('页码输入有误!');
									selectPageInput.focus();
									return;
								}
								doPage(parseInt(toPageNum), function(){
									init(pageObj.curPage);
								});
							});
							
			//点击翻页时触发的函数  参数toPageNumParam 即将跳往的页码
			function doPage(toPageNumParam, resultCallback){
				var startNum = (toPageNumParam - 1) * pageSize + 1;
				var endNum = toPageNumParam * pageSize;
				var result = callBack.call(this, toPageNumParam, startNum, endNum);
				//如果是dwr 或者其他 异步处理的话 一下代码会失效  这段代码需要写在 callBack 的函数中
				if(result == true){
					pageObj.curPage = toPageNumParam;
					resultCallback.call(this, toPageNumParam);
				}
			}
			
			//可以任意指定想要前往的页码
			pageObj.doPage = doPage;
			
			//翻页对象
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
				.append('总共').append(totalNumSpan).append('条>现')
				.append(selectPage).append('/').append(totalNumPageSpan).append('页&nbsp;&nbsp;')
				.append('跳转到').append(selectPageInput).append(selectPageButton);
				
			pageObj.initPage(0,0);
			return pageObj;		
}