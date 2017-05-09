
(function (a) {
	a.widget("newland.scrollToText", {_init:function () {
		var c = a("#" + this.options.inputId);
		var f = a("<input id='nextB' type=button value='\u4e0b\u4e00\u4e2a'>");
		var d = a("<input id='prevB' type=button value='\u524d\u4e00\u4e2a'>");
		var b = a("<span id='totalT'><span>");
		this.element.css("overflow-y", "auto").width(this.options.width).height(this.options.height);
		var e = this;
		c.keyup(function () {
			var g = c.val();
			if (e.pre) {
				e.pre.removeClass(e.options.selectClass);
			}
			if (g.gblen() >= e.options.startMinLen) {
				e.pre = e.element.find(e.options.queryDom + ":contains('" + a(this).val() + "')" + (e.options.maxFilter != -1 ? ":lt(" + e.options.maxFilter + ")" : ""));
				e.pre.addClass(e.options.selectClass);
				a("#contains").stop().scrollTo(e.pre.eq(0), e.options.delayTime);
			}
		});
	}});
	a.extend(a.newland.scrollToText, {version:"0.9", pre:null, defaults:{inputId:"", queryDom:"td", width:"100%", height:"100px", delayTime:1000, startMinLen:4, maxFilter:10, selectClass:"selectText"}});
})(jQuery);

