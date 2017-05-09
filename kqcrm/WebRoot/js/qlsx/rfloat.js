function getLocalurl(){
	return window.location.href;
}
var htzj_uri="http://218.3.124.236:9080";

document.writeln("<script>");
document.writeln("$(document).ready(function(){");
document.writeln("	//电梯导航");
document.writeln("	var win_H = $(window).height();");
document.writeln("	$(\".sy_rfloat\").stop().fadeIn(1500);");
document.writeln("	$(window).scroll(function(){");
document.writeln("		var scroll_H = $(window).scrollTop();");
document.writeln("		if(scroll_H>=250){");
document.writeln("			$(\".sy_rfloat\").stop().animate({top:(win_H-462)/2},500);");
document.writeln("		}else if(scroll_H<250){");
document.writeln("			$(\".sy_rfloat\").stop().animate({top:117},500);");
document.writeln("		}");
document.writeln("	});");
document.writeln("	$(\".rfloat_sub.rfloat_sub6\").click(function(){");
document.writeln("		$(\'body,html\').animate({scrollTop:0},500); ");
document.writeln("	});");
document.writeln("	//收回、展开效果");
document.writeln("	$(\".rfloat_sub7\").click(function(){");
document.writeln("		$(\".rfloat_sub1,.rfloat_sub2,.rfloat_sub3,.rfloat_sub4,.rfloat_sub5,.rfloat_sub6,.rfloat_sub7\").animate({top:318},500);");
document.writeln("		$(\".rfloat_sub8_bg\").animate({top:0},500);");
document.writeln("		$(\".rfloat_sub8\").animate({top:318},500);");
document.writeln("	});");
document.writeln("	$(\".rfloat_sub8\").click(function(){");
document.writeln("		$(\".rfloat_sub1\").animate({top:0},500);");
document.writeln("		$(\".rfloat_sub2\").animate({top:71},500);");
document.writeln("		$(\".rfloat_sub3\").animate({top:142},500);");
document.writeln("		$(\".rfloat_sub5\").animate({top:213},500);");
document.writeln("		$(\".rfloat_sub6\").animate({top:284},500);");
document.writeln("		$(\".rfloat_sub7\").animate({top:355},500);");
document.writeln("		$(\".rfloat_sub8\").animate({top:427},500);");
document.writeln("		$(\".rfloat_sub8_bg\").animate({top:-73},500);");
//document.writeln("		$(\".rfloat_sub4\").animate({top:462},500);");
document.writeln("	});");
document.writeln("});");
document.writeln("");
document.writeln("//其他调用");
document.writeln("function openWin(new_bmfw_url){");
document.writeln("	var webid=$(\'#ss_webid\').length==0?\'0\':+$(\'#ss_webid\').text();");
document.writeln("	new_bmfw_url=new_bmfw_url+\'?webId=\'+webid;");
document.writeln("	window.location.href=new_bmfw_url;");
document.writeln("}	");
document.writeln("function openNewWin(new_bmfw_url){");
document.writeln("	new_bmfw_url=new_bmfw_url;");
document.writeln("	window.open(new_bmfw_url);");
document.writeln("}");
document.writeln("</script>");
document.writeln("");
document.writeln("<div class=\"sy_rfloat\">");


document.writeln("	<div class=\"rfloat_sub rfloat_sub1\"  style=\"display:none;\"></div>");
document.writeln("    <div class=\"rfloat_sub rfloat_sub2\" onClick=\"location='"+htzj_uri+"/htzj/publish/wxw.jsp?nav=wxw';\" >");
document.writeln("    	<div class=\"rfloat_sub_tit\"> 我要咨询</div>");
/*
document.writeln("        <div class=\"rfloat_sub_zxts\">");
document.writeln("        	<div class=\"rfloat_sub_zx\" onClick=\"location='/htzj/publish/wxw.jsp?nav=wxw';\">");
document.writeln("            	<div class=\"rfloat_sub_zxtit\">我要咨询</div>");
document.writeln("            </div>");
document.writeln("            <div class=\"rfloat_sub_ts\" onClick=\"location='/htzj/publish/wxw_wyts.jsp?nav=wxw&sidebar=wyts';\">");
document.writeln("            	<div class=\"rfloat_sub_tstit\">我要投诉</div>");
document.writeln("            </div>");
document.writeln("            <div class=\"rfloat_sub_zxxx\" onClick=\"location='#';\">");
document.writeln("            	<div class=\"rfloat_sub_zxtitxx\">信件回复</div>");
document.writeln("            </div>");
document.writeln("        </div>");
*/
document.writeln("    </div>");


document.writeln("    <div class=\"rfloat_sub rfloat_sub3\"  onClick=\"location='"+htzj_uri+"/htzj/publish/wxw_wyts.jsp?nav=wxw&sidebar=wyts';\" >");
document.writeln("    	<div class=\"rfloat_sub_tit\">我要投诉</div>");
/*
document.writeln("        <div class=\"rfloat_sub_jiucuo\">");
document.writeln("        	<div class=\"rfloat_sub_zxtp\" style=\"width:120px; height:108px;\" onClick=\"window.open(\'http://www.zjzwfw.gov.cn/zjzwfw_xtwhz.html\')\">");
document.writeln("            	<div class=\"rfloat_sub_zxtittp\">我要纠错</div>");
document.writeln("            </div>");
document.writeln("            <div class=\"rfloat_sub_zxxxbm\" onClick=\"window.open(\'http://www.zjzwfw.gov.cn/zjzwfw_xtwhz.html\')\">");
document.writeln("            	<div class=\"rfloat_sub_zxtitxxbm\">纠错回复</div>");
document.writeln("            </div>");
document.writeln("        </div>");
*/
document.writeln("    </div>");


document.writeln("    <div class=\"rfloat_sub rfloat_sub5\" onClick=\"openNewWin('"+htzj_uri+"/htzj/publish/wyjc.jsp?url="+getLocalurl()+"');\" >");
document.writeln("    	<div class=\"rfloat_sub_tit\">我要纠错</div>");
document.writeln("    </div>");

/*
document.writeln("    <div class=\"rfloat_sub rfloat_sub_mobile\">");
document.writeln("    	<div class=\"rfloat_sub_tit\">移动端</div>");
document.writeln("        <div class=\"rfloat_sub_2code\"></div>");
document.writeln("    </div>");
*/

document.writeln("    <div class=\"rfloat_sub rfloat_sub6\"><div class=\"rfloat_sub_tit\">返回顶部</div></div>");
document.writeln("    <div class=\"rfloat_sub rfloat_sub7\"><div class=\"rfloat_sub_tit\">收起</div></div>");
document.writeln("    <div class=\"rfloat_sub rfloat_sub8\">");
document.writeln("    	<div class=\"rfloat_sub8_bg\">");
document.writeln("        	<div class=\"rfloat_sub_tit\">展开</div>");
document.writeln("        </div>        ");
document.writeln("    </div>");
document.writeln("</div>");