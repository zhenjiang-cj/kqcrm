/**
 * @author zhouk for newlandcomputer inc
 *
 * Verision 1.0, improvements to be made.
 * Copyright (c) 2009 CompareNetworks Inc.
 *
 * Licensed under the MIT license:
 *
 */
(function($) {
    $.widget("newland.SuperArea", {
        version: '1.0',
        KEY: [
            8,//BACKSPACE
            27,//ESC
            35,//HOME
            36,//END
            37,//LEFT
            38,//UP
            39,//RIGHT
            40,//DOWN
            46//DEL
        ],
        idx:0,
        options  :{
            maxlength: 1024,//最大字符数
            alertMsg: "您输入的消息内容已经超过允许的最多1024字限制！",//到达最大字符数后的提示
            width: "80%",//控件宽度
            maxNumSize: 4,//提示最大字符宽度，默认为4，即支持：9999
            delayTime: 4000,//提示消失时间
            minWidth: 8,//提示区域最小宽度
            style:{},//样式
            alertColor:'#68CEFF',//背景色
            msgColor:['#FEC56C','#D9E8FF','#E8F5CB'],//提示信息颜色
            barCss:{margin:"-1 0 0 0",height: 30,overflow: "hidden",backgroundColor: '#FFDFB5','float':'left',zIndex:1},
            filterCss: "progid:DXImageTransform.Microsoft.Gradient(gradientType=1,startColorStr=#000000ff,endColorStr=#ff80CC17);"
        },
        /***********************
         * Initialise SuperArea *
         ***********************/
        _init:function() {
            this.idx = parseInt(Math.random() * 100);
            this.element.css({overflow:"auto"}).wrap($('<div/>', {id:"textAreaO" + this.idx,css:{width:this.options.width}}));
            //if(jQuery.cookie('mySkin')=='sea')
            jQuery.extend(this.options.barCss,{backgroundColor: '#DEEBEF'});
            //else
            //jQuery.extend(this.options.barCss,{backgroundColor: '#FFDFB5'});
            var $barDiv = $("<div/>", {id:"textAreaP" + this.idx,css:this.options.barCss}).width("100%");
            //                        $barDiv.get(0).style.filter = "alpha(opacity=1,finishopacity=100,style=2);";
            var $Tmp = $("<div/>", {id:"textAreaPbg" + this.idx,css:{'float':'left',zIndex:0,height:30}});
            $Tmp.get(0).runtimeStyle.filter = this.options.filterCss;
            $barDiv.append($Tmp);
            this.element.after($barDiv);
            $Tmp = $("<div/>", {id:"textAreaPr" + this.idx,css:{"float":"right",margin:"1 0 0 -120",fontSize: 12,color:'black'}}).text("已输:0;限制:" + this.options.maxlength);
            $barDiv.after($Tmp);
            $Tmp = $("<div/>", {id:"textAreaPleft" + this.idx,css:{padding:0,margin:"1 0 0 -100%","float":"left",fontSize: 12,color:'black'}});
            $barDiv.after($Tmp);


            this.element.bind('keyup', {type:'k'}, $.proxy(this._dosth, this));
            this.element.bind('paste', {type:'p'}, $.proxy(this._dosth, this));
            this.element.bind('resize', $.proxy(this._resize, this));
            this.element.trigger('keyup');
            this.element.width($("#textAreaO"+ this.idx).width());
            
        },
        _resize:function() {
            this.textCounter(this.element.get(0), this._gblen(this.element.val()))
        },
        _dosth:function(e) {
            var mess = this.element.val();
            if (e.data.type == 'p') {
                mess += window.clipboardData.getData('Text');
            }
            var len = this._gblen(mess);
            if (e.keyCode != undefined && !this._inKey(e.keyCode) && len > this.options.maxlength) {
                var rng = this.element.get(0).createTextRange();
                this._selectOver(rng, mess);
            }
            this.textCounter(this.element.get(0), len)

        }, textCounter:function(textSelf, len) {
            var percentage = parseInt(100 - (( this.options.maxlength - len) * 100) / this.options.maxlength);
            percentage = percentage <= 100 ? percentage : 100;
            var tmpText = "已输:" + len + ";限制:" + this.options.maxlength;
            var $prWidth=$('#textAreaPr' + this.idx).get(0).offsetWidth+20;
            $('#textAreaPr' + this.idx).text(tmpText).css({margin:"1 0 0 -"+$prWidth,color:len <= this.options.maxlength ? 'black' : 'red'});
            var fieldWidth = parseInt(textSelf.offsetWidth );
            var tmpWidth = parseInt((fieldWidth * percentage / 100));
            $('#textAreaPbg' + this.idx).animate({width:(tmpWidth <
                    this.options.minWidth ? this.options.minWidth : tmpWidth)}, { queue: false, duration: 1000 });
            $('#textAreaPleft' + this.idx).text("已输占比:" + percentage + "%");

            this.options.isOver = (this.options.maxlength - this._gblen(this.element.val())) < 0;
        },
        _selectOver:function(rng, mess) {
            //将开始点和结束点在容器起始位置重合
            rng.moveStart("character", -mess.length);
            rng.moveEnd("character", -mess.length);
            rng.collapse(true);
            //按参数值移动
            rng.moveStart("character", this._gbsubstr(mess, this.options.maxlength).length);
            rng.moveEnd("character", mess.length);
            rng.select();
        },

        _gblen:function(value) {
            var len = 0;
            for (var i = 0; i < value.length; i++) {
                if (value.charCodeAt(i) > 127 || value.charCodeAt(i) == 94) {
                    len += 2;
                } else {
                    len ++;
                }
            }
            return len;
        },
        isOver:function() {
            return  this.options.isOver;
        },
        _inKey:function(key) {
            return  $.inArray(key, this.KEY) >= 0;
        },
        _gbsubstr:function(value, len) {
            var str = '';
            var sp = '';
            var len2 = 0;
            len2 = this._gblen(value);
            if (len2 <= len) {
                return value;
            }
            len2 = 0;
            len = (len > sp.length) ? len - sp.length : len;
            for (var i = 0; i < value.length; i++) {
                if (value.charCodeAt(i) > 127 || value.charCodeAt(i) == 94) {
                    len2 += 2;
                } else {
                    len2 ++;
                }
                if (len2 > len) {
                    str += sp;
                    break;
                }
                str += value.charAt(i);
            }
            return str;
        }});
    // Public global variables
    $.extend($.newland.SuperArea, {
        version: '1.0',
        KEY: [
            8,//BACKSPACE
            27,//ESC
            35,//HOME
            36,//END
            37,//LEFT
            38,//UP
            39,//RIGHT
            40,//DOWN
            46//DEL
        ],
        isOver:false,
        defaults :{
            maxlength: 1024,//最大字符数
            alertMsg: "您输入的消息内容已经超过允许的最多1024字限制！",//到达最大字符数后的提示
            width: "80%",//控件宽度
            maxNumSize: 4,//提示最大字符宽度，默认为4，即支持：9999
            delayTime: 4000,//提示消失时间
            minWidth: 8,//提示区域最小宽度
            style:{},//样式
            alertColor:'#68CEFF',//背景色
            msgColor:['#FEC56C','#D9E8FF','#E8F5CB'],//提示信息颜色
            barCss:{margin:"-1 0 0 0",height: 30,overflow: "hidden",backgroundColor: '#FFDFB5','float':'left',zIndex:1},
            filterCss: "progid:DXImageTransform.Microsoft.Gradient(gradientType=1,startColorStr=#000000ff,endColorStr=#ff80CC17);"
        }});
})(jQuery);
