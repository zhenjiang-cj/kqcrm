if (window.top != window) {
    parent.location = '/';
}
$(function() {
    //��׽�س����½
    $(document).keypress(function(e) {
        if (e.which == 13) {
            checkInputInfo();
        }
    });
    //��½��ʾ
    jQuery(".buttonBg").hover(function() {
        if (!jQuery(".buttonAni").is(":animated")) {
            jQuery(".buttonAni").animate({top:"-10px"}, 200).animate({top:"-4px"}, 200).animate({top:"-7px"}, 100).animate({top:"-4px"}, 100).animate({top:"-6px"}, 100).animate({top:"-4px"}, 100);
        }
    });

    //title��ʾ
    jQuery('[class^=homeTitle]').mouseover(function() {
        if (!jQuery(this).is(":animated")) {
            jQuery(this).hide('clip', {}, 800, function() {
                jQuery(this).show('slide', {}, 800);
            });
        }
    });
    //������ʾ
    jQuery("a.more").hover(function() {
        setTimes(jQuery(this).find('span'));
    }, function() {
        clearTimes(jQuery(this).find('span'));
    });

    jQuery('input:button').dropShadow({left: 3, top: 3});
    jQuery('.buttonAni,.buttonBg').dropShadow({left: 3, top: 3, blur: 2, opacity: 0.7});
    jQuery('.dropS').dropShadow({left: 3, top: 0,opacity:0.4 ,blur:2});
    jQuery('.more').dropShadow({left: 3, top: 2,opacity:0.3,blur:2});
    jQuery('#dy').dropShadow({left: 3, top: 3,opacity:0.9,blur:1});


    //����ie6
    if (Sys.ie != undefined && Sys.ie.substr(0, 1) < 7) {
        //        jQuery("a.more span").css('backgroundImage', 'url(../images/func/24/upload.gif)');
        jQuery(".buttonAni").attr('src', './images/homepage/button.gif');
//        jQuery(".handle,handleOver,handleSelected").pngFix();
    }
    //���ù������Զ��߶�

    jQuery("[name=btnExit]").each(function() {
        var class1,class2;
        if (jQuery(this).val().length < 6) {
            class1 = "inp_L1";
            class2 = "inp_L2";
        } else {
            class1 = "inp_L3";
            class2 = "inp_L4";
        }
        jQuery(this).addClass(class1);
        jQuery(this).hover(function() {
            jQuery(this).addClass(class2);
            jQuery(this).removeClass(class1);
        }, function() {

            jQuery(this).addClass(class1);
            jQuery(this).removeClass(class2);
        });

    });
    jQuery('div.DbsyDiv').css("marginLeft", "-1px");
    //Բ��
    //    alert(jQuery(".CompanyTit").length)
    //    jQuery(".title").corner("round 3px");
});

var timers ;
function setTimes(obj) {
    obj.animate({top:"-=2px"}, 40);
    timers = setTimeout(function() {
        setTimes(obj);
    }, 20);
}

function clearTimes(obj) {
    if (timers) {
        clearTimeout(timers);
    }
    obj.animate({top:'16px'}, 0)
}
//��½��֤
function checkInputInfo() {
    var name = jQuery('#operID');
    var pw = jQuery('#password');
    var rand = jQuery('#rand');
    if (name.val() == "") {
        alert("�û�������Ϊ��,����������!");
        pw.val('');
        name.focus();
        return;
    }
    if (pw.val() == "") {
        alert("���벻��Ϊ��,����������!");
        pw.focus();
        return;
    }
    if (rand.val() == "") {
        alert("��֤�벻��Ϊ��,����������!");
        rand.focus();
        return;
    }
    document.loginForm.submit();
}
//�˳���¼
function quit() {
    document.loginForm.nextStep.value = "logout";
    document.loginForm.submit();
}
function enter() {
    document.location.href = '/sysmgr/login.do?nextStep=main&subSystem=/xzcas&menuID=-9999'
}
function moreClick(sessdata, funcNode) {
    if ('null' == sessdata || '' == sessdata) {
        alert('��û�е�½ϵͳ���½��ʱ�����½��');
        return false;
    }
    if (-1 == funcNode) {
        alert('��û�в鿴Ȩ�ޣ�');
        return false;
    }
    return true;
}

