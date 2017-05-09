var sag_pagedialog;

function openFrame() {
	// 统一定义宽度等级，保证项目各类页面宽度的一致
	var levelValue = [ 900, 850, 750, 650, 500 ];
	if (window.screen.availWidth < 1280) {
		levelValue = [ 850, 750, 700, 600, 450 ];
	}
	var args = arguments;
	var pageTitle = args[0] == null ? ''
			: "<font style='font-family:宋体; font-weight:bold;color:#5C606C'>"
					+ args[0] + "</font>";// 标题
	var url = encodeURI(args[1]);// url
	var pageWidth = args[2] > 4 ? args[2] : levelValue[args[2]];// 宽度
	var pageHeight = args[3];// 高度
	var isCloseReload = args[4] == null ? true : args[4];// 点击右上角关闭按钮时，父页面是否刷新
	var isTop = args[5] == null ? false : args[5];
	
	sag_pagedialog = new J.dialog({
		title : pageTitle,
		page : url,
		width : pageWidth,
		height : pageHeight,
		cover : true,
		SetTopWindow : (isTop ? top : window),
		btns : false,
		iconTitle:false,
	    cancelBtn:false,
	    autoSize:false,
		rang : true
	});
	sag_pagedialog.ShowDialog();
	
	if (isCloseReload)
		J('#xbtn', sag_pagedialog.dlg).click( function() {
			sag_pagedialog.win.document.forms[0].submit();
		});
}


function closeFrameReload(flag) {
	if (flag == false)
		frameElement.lhgDG.cancel();
	else
		parent.document.forms[0].submit();
}

function closeParentFrameReload(flag) {
	//alert(flag);
	if (flag == false)
		frameElement.lhgDG.cancel();
	else{
		//alert(parent.document.forms[0].name);
		parent.document.forms[0].submit();
		frameElement.lhgDG.cancel();
	}
}