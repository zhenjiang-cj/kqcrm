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
     * ��ȡ�ַ������ĳ���
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
     * �����Ľ��в�λ
     * @param len ��λ����
     * @param s ��λ�ַ���
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
         * ��ʾ������Ϣ
         * @param content ��ʾ������
         * @param animateType ��
         * @param animateType ������ʽ
         * @param animateOption ��������
         * @param stayTime ��Ϣͣ��ʱ��
         * @param color ����ɫ
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
         * ��ʾ������Ϣ
         * @param content ��ʾ������
         * @param type ��ʾ���� 1 ��ȷ 2 ���� 3 ��ʾ��Ϣ��פ����
         * @param time ��ʾʱ������λ���룩
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
         * ��ʾ������Ϣ
         * @param content ��ʾ������
         * @param type ��ʾ���� 1 ��ȷ 2 ���� 3 ��ʾ��Ϣ��פ����
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
         * �رհ�����Ϣ
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
            postionTop: '5px',//����λ��TOP
            postionLeft: undefined,//����λ��left Ϊundefinedʱ��Ч
            postionRight:'100px' ,//����λ��right ����λ��TOP
            titleColor:"#000",//������ɫ
            limitLen:60,//��������
            title: "���ݴ�����,��ȴ�..."//Ĭ����ʾ����
        }});

})(jQuery);
