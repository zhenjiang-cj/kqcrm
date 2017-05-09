/**
 * 获取字符串中文长度
 */
String.prototype.gblen = function() {
    var len = 0;
    for (var i = 0; i < this.length; i++) {
        if (this.charCodeAt(i) > 127 || this.charCodeAt(i) == 94) {
            len += 2;
        } else {
            len ++;
        }
    }
    return len;
};

/**
 * 对中文进行补位
 * @param len 补位长度
 * @param s 补位字符串
 */
String.prototype.gbtrim = function(len, s) {
    var str = '';
    var sp = s || '';
    var len2 = 0;
    for (var i = 0; i < this.length; i++) {
        if (this.charCodeAt(i) > 127 || this.charCodeAt(i) == 94) {
            len2 += 2;
        } else {
            len2 ++;
        }
    }
    if (len2 <= len) {
        return this;
    }
    len2 = 0;
    len = (len > sp.length) ? len - sp.length : len;
    for (var i = 0; i < this.length; i++) {
        if (this.charCodeAt(i) > 127 || this.charCodeAt(i) == 94) {
            len2 += 2;
        } else {
            len2 ++;
        }
        if (len2 > len) {
            str += sp;
            break;
        }
        str += this.charAt(i);
    }
    return str;
};
jQuery.fn.extend({
    createTitle:function() {
        jQuery(this).addClass("title_bar_left");
        jQuery(this).append("<div class='title_bar_right'><div class='title_bar_bj'><div class='title_bar_bj_left1'><div class='title_bar_bj_left2'></div><div class='title_bar_bj_left3'></div><div  class='title_bar_bj_bj'><div class='title_bar_bj_font'></div></div><div class='title_bar_bj_right'></div></div></div></div>");
        jQuery(this).find(".title_bar_bj_font").text(jQuery(this).attr("value"));
    }
});
var Sys = {};
var ua = navigator.userAgent.toLowerCase();
jQuery(document).ready(function() {
    //设置输入时高亮
    if (jQuery.browser.msie)
        jQuery('input').not("[name=totale],[name=usede],[name=remaine]").focus(function() {
            jQuery(this).addClass('iefocus');
        }).blur(function() {
            jQuery(this).removeClass('iefocus');
        });
    //为input控件新增id属性
    jQuery('input,select,textarea').each(function() {
        if (jQuery(this).attr('name') != '' && jQuery(this).attr('name') != undefined)
            jQuery(this).attr('id', jQuery(this).attr('name'));
    });

    if (window.ActiveXObject) {
        Sys.ie = ua.match(/msie ([\d.]+)/)[1]
    } else if (document.getBoxObjectFor) {
        Sys.firefox = ua.match(/firefox\/([\d.]+)/)[1]
    }
//    securetAll();
});
/**
 * 屏蔽右键和选择文本

function securetAll() {
    if (window.ActiveXObject) {
        document.oncontextmenu = function() {
            self.event.returnValue = false;
        };

    } else if (document.getBoxObjectFor) {
        document.oncontextmenu = function() {
            return   false;
        };
        jQuery('body').get(0).setAttribute("style","-moz-user-select: none")
    }
    document.onselectstart = function() {
        return false;
    };
} */



