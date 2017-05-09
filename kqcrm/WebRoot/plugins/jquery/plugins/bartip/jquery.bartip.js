/**
 *
 * @author zhouk for newlandcomputer inc
 *
 * Verision 0.1, improvements to be made.
 * Copyright (c) 2009 CompareNetworks Inc.
 *
 * Licensed under the MIT license:
 * Depends:
 *   query.ui.js
 */
(function($) {
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
        len2 = this.gblen();
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
    $.widget("newland.bartip", {

        /***********************
         * Initialise progressModal *
         ***********************/
        _init : function() {
            this.element.css({position:"absolute",top:this.options.postionTop,zIndex:99});
            if (this.options.postionRight != undefined)
                this.element.css({right:this.options.postionRight});
            if (this.options.postionLeft != undefined)
                this.element.css({left:this.options.postionLeft});
            this.$area = $('<button id="mArea" onfocus="this.blur();"></button>').appendTo(this.element);


        },

        /**
         * 显示帮助信息
         * @param content 提示的内容
         * @param animateType 先
         * @param animateType 动画方式
         * @param animateOption 动画参数
         * @param stayTime 信息停留时间
         * @param color 背景色
         */
        showMessageDetail:function (content, animateType, animateOption, stayTime, color, type) {
            this.$area.stop(true);
            if (color)
                this.$area.css('color', this.options.titleColor);
            this.$area.html("<div id='showMessage'>" + this.options.title.gbtrim(this.options.limitLen) + "<b></b></div>");

            var bgClass;
            if (type == 2) {
                bgClass = 'btn_red';
            } else {
                bgClass = 'btn_green';
            }
            this.$area.addClass(bgClass);
            this.$area.find('b').addClass(bgClass);
            this.$area.show(animateType, animateOption, 'normal', function() {
                $(this).effect('bounce', {}, 500, function() {
                    setTimeout(function() {
                        $('#mArea').hide('explode', {pieces:16}, 500);
                    }, stayTime);
                });

            });
            this.$area.width(15+this.options.title.gbtrim(this.options.limitLen).gblen()*8)
        },
        /**
         * 显示帮助信息
         * @param content 提示的内容
         * @param type 提示类型 1 正确 2 错误 3 提示信息（驻留）
         * @param time 显示时长（单位：秒）
         */
        _showMDetail: function (content, type, time) {
            content += "";
            this.options.title = content;
            if (type == 1) {
                this.showMessageDetail(content, 'slide', {}, time * 1000, '#4A4F55', type);
            } else if (type == 2) {
                this.showMessageDetail(content, 'slide', {}, time * 1000, '#4A4F55', type);
            } else if (type == 3) {
                this.showMessageDetail(content, 'slide', {}, time * 1000, '#333', type);
            }
        },

        /**
         * 显示帮助信息
         * @param content 提示的内容
         * @param type 提示类型 1 正确 2 错误 3 提示信息（驻留）
         */
        showMessage: function (content, type) {
            var time;
            if (type == 1) {
                time = 3;
            } else if (type == 2) {
                time = 3;
            } else if (type == 3) {
                time = 6;
            }

            this._showMDetail(content, type, time);
        },
        /**
         * 关闭帮助信息
         */
        hideMessage: function () {
            this.$area.fadeOut('slow');
        }
    });

    // Public global variables
    $.extend($.newland.bartip, {
        version: '0.9',
        progressValue:0,
        defaults :{
            postionTop: '5px',//绝对位置TOP
            postionLeft: undefined,//绝对位置left 为undefined时无效
            postionRight:'100px' ,//绝对位置right 绝对位置TOP
            titleColor:"#000",//字体颜色
            limitLen:60,//限制字数
            title: "数据处理中,请等待..."//默认提示数据
        }});

})(jQuery);
