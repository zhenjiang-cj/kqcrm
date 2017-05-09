
var IMGFOLDERPATH = "images/", CONTEXTPATH = "", isIE = navigator.userAgent.toLowerCase().indexOf("msie") != -1, isIE6 = navigator.userAgent.toLowerCase().indexOf("msie 6.0") != -1, isGecko = navigator.userAgent.toLowerCase().indexOf("gecko") != -1, isQuirks = document.compatMode == "BackCompat";
function $(a) {
	if (typeof a == "string") {
		a = document.getElementById(a);
		if (!a) {
			return null;
		}
	}
	a && Core.attachMethod(a);
	return a;
}
function $T(a, b) {
	b = (b = $(b)) || document;
	a = b.getElementsByTagName(a);
	b = [];
	for (var d = a.length, c = 0; c < d; c++) {
		b.push($(a[c]));
	}
	return b;
}
function stopEvent(a) {
	if (a = window.event || a) {
		if (isGecko) {
			a.preventDefault();
			a.stopPropagation();
		}
		a.cancelBubble = true;
		a.returnValue = false;
	}
}
Array.prototype.remove = function (a) {
	for (var b = 0; b < this.length; b++) {
		a == this[b] && this.splice(b, 1);
	}
};
if (window.HTMLElement) {
	HTMLElement.prototype.__defineGetter__("parentElement", function () {
		if (this.parentNode == this.ownerDocument) {
			return null;
		}
		return this.parentNode;
	});
	HTMLElement.prototype.__defineSetter__("outerHTML", function (a) {
		var b = this.ownerDocument.createRange();
		b.setStartBefore(this);
		this.parentNode.replaceChild(b.createContextualFragment(a), this);
		return a;
	});
	HTMLElement.prototype.__defineGetter__("outerHTML", function () {
		for (var a, b = this.attributes, d = "<" + this.tagName, c = 0; c < b.length; c++) {
			a = b[c];
			if (a.specified) {
				d += " " + a.name + "=\"" + a.value + "\"";
			}
		}
		if (!this.canHaveChildren) {
			return d + ">";
		}
		return d + ">" + this.innerHTML + "</" + this.tagName + ">";
	});
	HTMLElement.prototype.__defineSetter__("innerText", function (a) {
		return this.innerHTML = a = document.createTextNode(a);
	});
	HTMLElement.prototype.__defineGetter__("innerText", function () {
		var a = this.ownerDocument.createRange();
		a.selectNodeContents(this);
		return a.toString();
	});
}
var $E = {};
$E.getTopLevelWindow = function () {
	return window;
};
$E.hide = function (a) {
	a = a || this;
	a = $(a);
	a.style.display = "none";
};
$E.show = function (a) {
	a = a || this;
	a = $(a);
	a.style.display = "";
};
$E.visible = function (a) {
	a = a || this;
	a = $(a);
	if (a.style.display == "none") {
		return false;
	}
	return true;
};
var Core = {};
Core.attachMethod = function (a) {
	if (!(!a || a.$A)) {
		if (a.nodeType != 9) {
			var b;
			try {
				b = isGecko ? a.ownerDocument.defaultView : a.ownerDocument.parentWindow;
				for (var d in $E) {
					a[d] = b.$E[d];
				}
			}
			catch (c) {
			}
		}
	}
};
function Dialog(a) {
	if (a) {
		this.ID = a;
		this.isModal = true;
		this.Width = 400;
		this.Height = 300;
		this.Left = this.Top = 0;
		this.Window = this.onLoad = this.ParentWindow = null;
		this.Title = "";
		this.URL = null;
		this.DialogArguments = {};
		this.WindowFlag = false;
		this.MessageTitle = this.Message = null;
		this.ShowMessageRow = false;
		this.ShowButtonRow = true;
		this.bgdivID = this.Icon = null;
	} else {
		alert("\u9519\u8bef\u7684Dialog ID\uff01");
	}
}
Dialog._Array = [];
Dialog.prototype.showWindow = function () {
	isIE && this.ParentWindow.showModalessDialog(this.URL, this.DialogArguments, "dialogWidth:" + this.Width + ";dialogHeight:" + this.Height + ";help:no;scroll:no;status:no");
	if (isGecko) {
		var a = this.Window = this.ParentWindow.open("", this.URL, "location=no,menubar=no,status=no;toolbar=no,dependent=yes,dialog=yes,minimizable=no,modal=yes,alwaysRaised=yes,resizable=no", true);
		if (a) {
			a.moveTo(this.Left, this.Top);
			a.resizeTo(this.Width, this.Height + 30);
			a.focus();
			a.location.href = this.URL;
			a.Parent = this.ParentWindow;
			a.dialogArguments = this.DialogArguments;
		} else {
			alert("\u53d1\u73b0\u5f39\u51fa\u7a97\u53e3\u88ab\u963b\u6b62\uff0c\u8bf7\u66f4\u6539\u6d4f\u89c8\u5668\u8bbe\u7f6e\uff0c\u4ee5\u4fbf\u6b63\u5e38\u4f7f\u7528\u672c\u529f\u80fd!");
		}
	}
};
Dialog.prototype.show = function () {
	var a = $E.getTopLevelWindow(), b = a.document, d = b.compatMode == "BackCompat" ? b.body.clientWidth : b.documentElement.clientWidth, c = b.compatMode == "BackCompat" ? b.body.clientHeight : b.documentElement.clientHeight, e = Math.max(b.documentElement.scrollLeft, b.body.scrollLeft), f = Math.max(b.documentElement.scrollTop, b.body.scrollTop), g = Math.max(b.documentElement.scrollWidth, b.body.scrollWidth), h = Math.max(b.documentElement.scrollHeight, b.body.scrollHeight);
	g = Math.max(g, d);
	h = Math.max(h, c);
	if (!this.ParentWindow) {
		this.ParentWindow = window;
	}
	this.DialogArguments._DialogInstance = this;
	this.DialogArguments.ID = this.ID;
	if (!this.Height) {
		this.Height = this.Width / 2;
	}
	if (this.Top == 0) {
		this.Top = (c - this.Height - 30) / 2 + f - 32;
	}
	if (this.Left == 0) {
		this.Left = (d - this.Width - 12) / 2 + e;
	}
	if (this.ShowButtonRow) {
		this.Top -= 18;
	}
	if (this.WindowFlag) {
		this.showWindow();
	} else {
		c = [];
		c.push("<table id='diagTableCss' style='-moz-user-select:none;' ' onselectstart='stopEvent(event);' border='0' cellpadding='0' cellspacing='0' width='" + (this.Width + 26) + "'>");
		c.push("  <tr style='cursor:move;' id='_draghandle_" + this.ID + "'>");
		c.push("    <td width='13' height='33' style=\"background-image:url(" + IMGFOLDERPATH + "dialog_lt.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + IMGFOLDERPATH + "dialog_lt.png', sizingMethod='crop');\"><div style='width:13px;'></div></td>");
		c.push("    <td height='33' style=\"background-image:url(" + IMGFOLDERPATH + "dialog_ct.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + IMGFOLDERPATH + "dialog_ct.png', sizingMethod='crop');\"><div style=\"float:left;font-weight:bold; color:#FFFFFF; padding:9px 0 0 4px;\"><img src=\"" + IMGFOLDERPATH + "icon_dialog.gif\" align=\"absmiddle\">&nbsp;" + this.Title + "</div>");
		c.push("      <div style=\"position: relative;cursor:pointer; float:right; margin:5px 0 0; _margin:4px 0 0;height:17px; width:28px; background-image:url(" + IMGFOLDERPATH + "dialog_closebtn.gif)\" onMouseOver=\"this.style.backgroundImage='url(" + IMGFOLDERPATH + "dialog_closebtn_over.gif)'\" onMouseOut=\"this.style.backgroundImage='url(" + IMGFOLDERPATH + "dialog_closebtn.gif)'\" drag='false' onClick=\"Dialog.getInstance('" + this.ID + "').CancelButton.onclick.apply(Dialog.getInstance('" + this.ID + "').CancelButton,[]);\"></div></td>");
		c.push("    <td width='13' height='33' style=\"background-image:url(" + IMGFOLDERPATH + "dialog_rt.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + IMGFOLDERPATH + "dialog_rt.png', sizingMethod='crop');\"><div style=\"width:13px;\"></div></td>");
		c.push("  </tr>");
		c.push("  <tr drag='false'><td width='13' style=\"background-image:url(" + IMGFOLDERPATH + "dialog_mlm.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + IMGFOLDERPATH + "dialog_mlm.png', sizingMethod='crop');\"></td>");
		c.push("    <td align='center' valign='top'>");
		c.push("    <table width='100%' border='0' cellpadding='0' cellspacing='0' bgcolor='#FFFFFF'>");
		c.push("        <tr id='_MessageRow_" + this.ID + "' style='display:none'>");
		c.push("          <td height='50' valign='top'><table id='_MessageTable_" + this.ID + "' width='100%' border='0' cellspacing='0' cellpadding='8' style=\" background:#EAECE9 url(" + IMGFOLDERPATH + "dialog_bg.jpg) no-repeat right top;\">");
		c.push("              <tr><td width='25' height='50' align='right'><img id='_MessageIcon_" + this.ID + "' src='" + IMGFOLDERPATH + "window.gif' width='32' height='32'></td>");
		c.push("                <td align='left' style='line-height:16px;'>");
		c.push("                <h5 class='fb' id='_MessageTitle_" + this.ID + "'>&nbsp;</h5>");
		c.push("                <div id='_Message_" + this.ID + "'>&nbsp;</div></td>");
		c.push("              </tr></table></td></tr>");
		c.push("        <tr><td align='center' valign='top'><div style='position:relative;width:" + this.Width + "px;height:" + this.Height + "px;'>");
		c.push("         <div  id='_Covering_" + this.ID + "' style='position:absolute; height:100%; width:100%;display:none;'>&nbsp;</div>");
		c.push("          <iframe src='");
		this.URL.indexOf(":") == -1 ? c.push(CONTEXTPATH + this.URL) : c.push(this.URL);
		c.push("' id='_DialogFrame_" + this.ID + "' allowTransparency='true'  width='100%' height='100%' frameborder='0' style=\"background-color: #transparent; border:none;\"></iframe></div></td></tr>");
		c.push("      </table><a href='#;' onfocus='$(\"_DialogFrame_" + this.ID + "\").focus();'></a></td>");
		c.push("    <td width='13' style=\"background-image:url(" + IMGFOLDERPATH + "dialog_mrm.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + IMGFOLDERPATH + "dialog_mrm.png', sizingMethod='crop');\"></td></tr>");
		c.push("  <tr><td width='13' height='13' style=\"background-image:url(" + IMGFOLDERPATH + "dialog_lb.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + IMGFOLDERPATH + "dialog_lb.png', sizingMethod='crop');\"></td>");
		c.push("    <td style=\"background-image:url(" + IMGFOLDERPATH + "dialog_cb.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + IMGFOLDERPATH + "dialog_cb.png', sizingMethod='crop');\"></td>");
		c.push("    <td width='13' height='13' style=\"background-image:url(" + IMGFOLDERPATH + "dialog_rb.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + IMGFOLDERPATH + "dialog_rb.png', sizingMethod='crop');\">");
		c.push("  <input id='_ButtonCancel_" + this.ID + "'  type='hidden' onclick=\"Dialog.getInstance('" + this.ID + "').close();\"    ></td></tr></table>");
		this.TopWindow = a;
		d = a.$("_DialogBGDiv");
		if (!d) {
			d = a.document.createElement("div");
			d.id = "_DialogBGDiv";
			$E.hide(d);
			a.$T("body")[0].appendChild(d);
			if (isIE6) {
				e = a.document.createElement("<div style=\"position:relative;width:100%;height:100%;\"></div>");
				f = a.document.createElement("<iframe src=\"about:blank\" style=\"filter:alpha(opacity=1);\" width=\"100%\" height=\"100%\"></iframe>");
				g = a.document.createElement("<div src=\"about:blank\" style=\"position:absolute;background-color:#333;filter:alpha(opacity=1);width:100%;height:100%;\"></div>");
				e.appendChild(g);
				e.appendChild(f);
				d.appendChild(e);
				e = f.contentWindow.document;
				e.open();
				e.write("<body style='background-color:#333' oncontextmenu='return false;'></body>");
				e.close();
			}
		}
		e = a.$("_DialogDiv_" + this.ID);
		if (!e) {
			e = a.document.createElement("div");
			$E.hide(e);
			e.id = "_DialogDiv_" + this.ID;
			a.$T("body")[0].appendChild(e);
		}
		this.DialogDiv = e;
		e.innerHTML = c.join("\n");
		a.$("_DialogFrame_" + this.ID).DialogInstance = this;
		a.Drag.init(a.$("_draghandle_" + this.ID), a.$("_DialogDiv_" + this.ID));
		if (!isIE) {
			a.$("_DialogDiv_" + this.ID).dialogId = this.ID;
			a.$("_DialogDiv_" + this.ID).onDragStart = function () {
				$("_Covering_" + this.dialogId).style.display = "";
			};
			a.$("_DialogDiv_" + this.ID).onDragEnd = function () {
				$("_Covering_" + this.dialogId).style.display = "none";
			};
		}
		this.CancelButton = a.$("_ButtonCancel_" + this.ID);
		if (this.ShowMessageRow) {
			$E.show(a.$("_MessageRow_" + this.ID));
			if (this.MessageTitle) {
				a.$("_MessageTitle_" + this.ID).innerHTML = this.MessageTitle;
			}
			if (this.Message) {
				a.$("_Message_" + this.ID).innerHTML = this.Message;
			}
		}
		this.ShowButtonRow || a.$("_ButtonRow_" + this.ID).hide();
		if (this.CancelEvent) {
			this.CancelButton.onclick = this.CancelEvent;
		}
		if (this.AlertFlag) {
			d = a.$("_AlertBGDiv");
			if (!d) {
				d = a.document.createElement("div");
				d.id = "_AlertBGDiv";
				$E.hide(d);
				a.$T("body")[0].appendChild(d);
				if (isIE6) {
					e = a.document.createElement("<div style=\"position:relative;width:100%;height:100%;\"></div>");
					f = a.document.createElement("<iframe src=\"about:blank\" style=\"filter:alpha(opacity=1);\" width=\"100%\" height=\"100%\"></iframe>");
					g = a.document.createElement("<div src=\"about:blank\" style=\"position:absolute;background-color:#333;filter:alpha(opacity=1);width:100%;height:100%;\"></div>");
					e.appendChild(g);
					e.appendChild(f);
					d.appendChild(e);
					e = f.contentWindow.document;
					e.open();
					e.write("<body style='background-color:#333' oncontextmenu='return false;'></body>");
					e.close();
				}
				d.style.cssText = "background-color:#333;position:absolute;left:0px;top:0px;opacity:0.4;filter:alpha(opacity=40);width:100%;height:" + h + "px;z-index:991";
			}
			$E.show(d);
			this.bgdivID = "_AlertBGDiv";
		} else {
			$E.show(d);
			this.bgdivID = "_DialogBGDiv";
		}
		this.DialogDiv.style.cssText = "position:absolute; display:block;z-index:" + (this.AlertFlag ? 992 : 990) + ";left:" + this.Left + "px;top:" + this.Top + "px";
		if (!this.AlertFlag) {
			c = window;
			for (e = false; c != c.parent; ) {
				if (c._DialogInstance) {
					c._DialogInstance.DialogDiv.style.zIndex = 959;
					e = true;
					break;
				}
				c = c.parent;
			}
			if (!e) {
				d.style.cssText = "background-color:#333;position:absolute;left:0px;top:0px;opacity:0.4;filter:alpha(opacity=40);width:100%;height:" + h + "px;z-index:960";
			}
		}
		b = b.getElementsByTagName(isQuirks ? "BODY" : "HTML")[0];
		pwbodyOverFow = b.style.overflow;
		b.style.overflow = "hidden";
		a.Dialog._Array.push(this.ID);
	}
};
Dialog.prototype.addParam = function (a, b) {
	this.DialogArguments[a] = b;
};
var pwbodyOverFow;
Dialog.prototype.close = function () {
	if (this.WindowFlag) {
		this.ParentWindow.$D = null;
		this.ParentWindow.$DW = null;
		this.Window.opener = null;
		this.Window.close();
		this.Window = null;
	} else {
		for (var a = $E.getTopLevelWindow(), b = a.document, d = window, c = false; d != d.parent; ) {
			if (d._DialogInstance) {
				c = true;
				d._DialogInstance.DialogDiv.style.zIndex = 960;
				break;
			}
			d = d.parent;
		}
		this.AlertFlag && $E.hide(a.$("_AlertBGDiv"));
		if (!c && !this.AlertFlag) {
			a.eval("window._OpacityFunc = function(){var w = $E.getTopLevelWindow();$E.hide(w.$(\"_DialogBGDiv\"));}");
			a._OpacityFunc();
		}
		this.DialogDiv.outerHTML = "";
		b.getElementsByTagName(isQuirks ? "BODY" : "HTML")[0].style.overflow = pwbodyOverFow;
		a.Dialog._Array.remove(this.ID);
	}
};
Dialog.prototype.addButton = function (a, b, d) {
	b = "<input id='_Button_" + this.ID + "_" + a + "' type='button' value='" + b + "'> ";
	var c = $E.getTopLevelWindow();
	c.$("_DialogButtons_" + this.ID).$T("input")[0].getParent("a").insertAdjacentHTML("beforeBegin", b);
	c.$("_Button_" + this.ID + "_" + a).onclick = d;
};
Dialog.close = function () {
	window.Args._DialogInstance.close();
};
Dialog.getInstance = function (a) {
	a = $E.getTopLevelWindow().$("_DialogFrame_" + a);
	if (!a) {
		return null;
	}
	return a.DialogInstance;
};
Dialog.AlertNo = 0;
Dialog.alert = function (a, b, d, c) {
	var e = $E.getTopLevelWindow(), f = new Dialog("_DialogAlert" + Dialog.AlertNo++);
	f.ParentWindow = e;
	f.Width = d ? d : 300;
	f.Height = c ? c : 120;
	f.Title = "\u7cfb\u7edf\u63d0\u793a";
	f.URL = "javascript:void(0);";
	f.AlertFlag = true;
	f.CancelEvent = function () {
		f.close();
		b && b();
	};
	f.show();
	e.$("_AlertBGDiv").style.display = "";
	$E.hide(e.$("_ButtonOK_" + f.ID));
	var g = e.$("_DialogFrame_" + f.ID).contentWindow;
	d = g.document;
	d.open();
	d.write("<body oncontextmenu='return false;'></body>");
	c = [];
	c.push("<table height='100%' border='0' align='center' cellpadding='10' cellspacing='0'>");
	c.push("<tr><td align='right'><img id='Icon' src='" + IMGFOLDERPATH + "icon_alert.gif' width='34' height='34' align='absmiddle'></td>");
	c.push("<td align='left' id='Message' style='font-size:9pt'>" + a + "</td></tr></table>");
	a = d.createElement("div");
	a.innerHTML = c.join("");
	d.body.appendChild(a);
	d.close();
	c = Math.max(d.documentElement.scrollHeight, d.body.scrollHeight);
	d = Math.max(d.documentElement.scrollWidth, d.body.scrollWidth);
	if (d > 300) {
		g.frameElement.width = d;
	}
	if (c > 120) {
		g.frameElement.height = c;
	}
	f.CancelButton.value = "\u786e \u5b9a";
	f.CancelButton.focus();
	e.$("_DialogButtons_" + f.ID).style.textAlign = "center";
};
Dialog.confirm = function (a, b, d, c, e) {
	var f = $E.getTopLevelWindow(), g = new Dialog("_DialogAlert" + Dialog.AlertNo++);
	g.Width = c ? c : 300;
	g.Height = e ? e : 120;
	g.Title = "\u4fe1\u606f\u786e\u8ba4";
	g.URL = "javascript:void(0);";
	g.AlertFlag = true;
	g.CancelEvent = function () {
		g.close();
		d && d();
	};
	g.OKEvent = function () {
		g.close();
		b && b();
	};
	g.show();
	f.$("_AlertBGDiv").style.dispaly = "";
	c = f.$("_DialogFrame_" + g.ID).contentWindow.document;
	c.open();
	c.write("<body oncontextmenu='return false;'></body>");
	e = [];
	e.push("<table height='100%' border='0' align='center' cellpadding='10' cellspacing='0'>");
	e.push("<tr><td align='right'><img id='Icon' src='" + IMGFOLDERPATH + "icon_query.gif' width='34' height='34' align='absmiddle'></td>");
	e.push("<td align='left' id='Message' style='font-size:9pt'>" + a + "</td></tr></table>");
	a = c.createElement("div");
	a.innerHTML = e.join("");
	c.body.appendChild(a);
	c.close();
	g.OKButton.focus();
	f.$("_DialogButtons_" + g.ID).style.textAlign = "center";
};
var _DialogInstance = window.frameElement ? window.frameElement.DialogInstance : null, Page = {};
Page.onDialogLoad = function () {
	if (_DialogInstance) {
		if (_DialogInstance.Title) {
			document.title = _DialogInstance.Title;
		}
		window.Args = _DialogInstance.DialogArguments;
		_DialogInstance.Window = window;
		window.Parent = _DialogInstance.ParentWindow;
	}
};
Page.onDialogLoad();
PageOnLoad = function () {
	var a = _DialogInstance;
	if (a) {
		try {
			a.ParentWindow.$D = a;
			a.ParentWindow.$DW = a.Window;
			var b = false;
			if (!this.AlertFlag) {
				for (var d = a.ParentWindow; d != d.parent; ) {
					if (d._DialogInstance) {
						b = true;
						break;
					}
					d = d.parent;
				}
				if (!b) {
					$E.getTopLevelWindow().$("_DialogBGDiv").style.opacity = "0.4";
					$E.getTopLevelWindow().$("_DialogBGDiv").style.filter = "alpha(opacity=40)";
				}
			}
			a.AlertFlag && $E.show($E.getTopLevelWindow().$("_AlertBGDiv"));
			a.ShowButtonRow && $E.visible(a.CancelButton) && a.CancelButton.focus();
			a.onLoad && a.onLoad();
		}
		catch (c) {
			alert("DialogOnLoad:" + c.message + "\t(" + c.fileName + " " + c.lineNumber + ")");
		}
	}
};
Dialog.onKeyDown = function (a) {
	if (a.shiftKey && a.keyCode == 9) {
		var b = $E.getTopLevelWindow();
		if (b.Dialog._Array.length > 0) {
			stopEvent(a);
			return false;
		}
	}
	if (a.keyCode == 27) {
		b = $E.getTopLevelWindow();
		if (b.Dialog._Array.length > 0) {
			a = b.Dialog.getInstance(b.Dialog._Array[b.Dialog._Array.length - 1]);
			a.CancelButton.onclick.apply(a.CancelButton, []);
		}
	}
};
Dialog.dragStart = function () {
};
Dialog.setPosition = function () {
	if (window.parent == window) {
		var a = $E.getTopLevelWindow(), b = a.Dialog._Array;
		if (!(b == null || b.length == 0)) {
			for (i = 0; i < b.length; i++) {
				a.$("_DialogFrame_" + b[i]).DialogInstance.setPosition();
			}
		}
	}
};
Dialog.prototype.setPosition = function () {
	var a = $E.getTopLevelWindow(), b = a.document, d = b.compatMode == "BackCompat" ? b.body.clientWidth : b.documentElement.clientWidth, c = b.compatMode == "BackCompat" ? b.body.clientHeight : b.documentElement.clientHeight, e = Math.max(b.documentElement.scrollLeft, b.body.scrollLeft), f = Math.max(b.documentElement.scrollTop, b.body.scrollTop), g = Math.max(b.documentElement.scrollWidth, b.body.scrollWidth);
	b = Math.max(b.documentElement.scrollHeight, b.body.scrollHeight);
	Math.max(g, d);
	b = Math.max(b, c);
	this.Top = (c - this.Height - 30) / 2 + f - 32;
	this.Left = (d - this.Width - 12) / 2 + e;
	if (this.ShowButtonRow) {
		this.Top -= 18;
	}
	this.DialogDiv.style.top = this.Top + "px";
	this.DialogDiv.style.left = this.Left + "px";
	a.$(this.bgdivID).style.height = b + "px";
};
var Drag = {obj:null, init:function (a, b, d) {
	if (d == null) {
		a.onmousedown = Drag.start;
	}
	a.root = b;
	if (isNaN(parseInt(a.root.style.left))) {
		a.root.style.left = "0px";
	}
	if (isNaN(parseInt(a.root.style.top))) {
		a.root.style.top = "0px";
	}
	a.root.onDragStart = new Function;
	a.root.onDragEnd = new Function;
	a.root.onDrag = new Function;
	if (d != null) {
		a = Drag.obj = a;
		d = Drag.fixe(d);
		b = parseInt(a.root.style.top);
		var c = parseInt(a.root.style.left);
		a.root.onDragStart(c, b, d.pageX, d.pageY);
		a.lastMouseX = d.pageX;
		a.lastMouseY = d.pageY;
		document.onmousemove = Drag.drag;
		document.onmouseup = Drag.end;
	}
}, start:function (a) {
	var b = Drag.obj = this;
	a = Drag.fixEvent(a);
	var d = parseInt(b.root.style.top), c = parseInt(b.root.style.left);
	b.root.onDragStart(c, d, a.pageX, a.pageY);
	b.lastMouseX = a.pageX;
	b.lastMouseY = a.pageY;
	document.onmousemove = Drag.drag;
	document.onmouseup = Drag.end;
	return false;
}, drag:function (a) {
	a = Drag.fixEvent(a);
	var b = Drag.obj, d = a.pageY, c = a.pageX, e = parseInt(b.root.style.top), f = parseInt(b.root.style.left);
	isIE ? Drag.obj.setCapture() : a.preventDefault();
	f = f + c - b.lastMouseX;
	e = e + (d - b.lastMouseY);
	b.root.style.left = f + "px";
	b.root.style.top = e + "px";
	b.lastMouseX = c;
	b.lastMouseY = d;
	b.root.onDrag(f, e, a.pageX, a.pageY);
	return false;
}, end:function () {
	isIE && Drag.obj.releaseCapture();
	document.onmousemove = null;
	document.onmouseup = null;
	Drag.obj.root.onDragEnd(parseInt(Drag.obj.root.style.left), parseInt(Drag.obj.root.style.top));
	Drag.obj = null;
}, fixEvent:function (a) {
	var b = Math.max(document.documentElement.scrollLeft, document.body.scrollLeft), d = Math.max(document.documentElement.scrollTop, document.body.scrollTop);
	if (typeof a == "undefined") {
		a = window.event;
	}
	if (typeof a.layerX == "undefined") {
		a.layerX = a.offsetX;
	}
	if (typeof a.layerY == "undefined") {
		a.layerY = a.offsetY;
	}
	if (typeof a.pageX == "undefined") {
		a.pageX = a.clientX + b - document.body.clientLeft;
	}
	if (typeof a.pageY == "undefined") {
		a.pageY = a.clientY + d - document.body.clientTop;
	}
	return a;
}};
if (isIE) {
	document.attachEvent("onkeydown", Dialog.onKeyDown);
	window.attachEvent("onresize", Dialog.setPosition);
} else {
	document.addEventListener("keydown", Dialog.onKeyDown, false);
	window.addEventListener("load", PageOnLoad, false);
	window.addEventListener("resize", Dialog.setPosition, false);
}

