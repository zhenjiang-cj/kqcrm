$(function() {


    //兼容ie6
    if (Sys.ie != undefined && Sys.ie.substr(0, 1) < 7) {
        //        jQuery("a.more span").css('backgroundImage', 'url(../images/func/24/upload.gif)');
        jQuery(".handle,handleOver,handleSelected").pngFix();
    }
    //公告lava
    jQuery("#menu .LavaLamp").lavaLamp();
    jQuery("#menu .LavaLamp li.current").removeClass("current");
    //设置公告栏自动高度
    var ggHeight = 0;
    jQuery(".SlideTab").each(function() {
        var tmp = jQuery(this).height();
        ggHeight = ggHeight < tmp ? tmp : ggHeight;
    });
    var minHeight = ggHeight > 155 ? ggHeight : 155;
    jQuery("#content").height(minHeight);
    jQuery("#notice", parent.document).height(minHeight);
    jQuery("div.SlideTab").height(minHeight);
    //公告选择
    jQuery("#NoiceCont").localScroll({
        target: '#content', // could be a selector or a jQuery object too.
        axis:'xy',
        queue:true,
        duration:1000,
        hash:true,
        onBefore:function(e, anchor, $target) {
            // The 'this' is the settings object, can be modified
        },
        onAfter:function(anchor, settings) {
            // The 'this' contains the scrolled element (#content)
        }
    });
    jQuery("div.SlideTab").css({marginLeft:"-2px"});
});
