$(function() {
    //–¬Œ≈ª¨∂Ø

    jQuery(".news").hrzAccordion({openOnLoad:1,cycle: true,cycleInterval:3000});
//    jQuery(".news").hrzAccordion({eventTrigger:"mouseover",openOnLoad:3,cycle: true,cycleInterval:8000});
//    jQuery(".news").hrzAccordion({eventTrigger:"mouseover",openOnLoad:2,cycle: true,cycleInterval:8000});
//    jQuery(".news").hrzAccordion({eventTrigger:"mouseover",openOnLoad:1,cycle: true,cycleInterval:8000});

    //ºÊ»›ie6
    if (Sys.ie != undefined && Sys.ie.substr(0, 1) < 7) {
        jQuery(".handle,handleOver,handleSelected").pngFix();
//        jQuery(".news").removeClass('new').addClass('ie6');
        jQuery(".handle img").hide();
    }
//jQuery('h3').dropShadow({left: 2, top: 2,opacity:0.3,blur:0,swap:false});
    /* alert(jQuery("p.content").length)
     alert(jQuery(jQuery("p.content").find('*')).length)
     jQuery("p.content").find('*').click(function(){
     alert(1)
     })*/
});
function newsClick(info_id) {
    window.open("./lookMainIFrame.jsp?type=613&info_id=" + info_id, '_blank', 'width=800px,height=650px,resizable=yes');
}
