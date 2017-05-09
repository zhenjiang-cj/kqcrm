//锋.David
//MSN:jianfeng.ye@msn.com
//Q Q:54698263
//E-mail:jianfeng.ye@msn.com
var webserver="";
var newphotoimg1_1=new Image();
newphotoimg1_1.src=webserver+"1_r1_c1.png";
var newphotoimg1_2=new Image();
newphotoimg1_2.src=webserver+"1_r1_c2.png";
var newphotoimg1_3=new Image();
newphotoimg1_3.src=webserver+"1_r1_c4.png";
var newphotoimg1_4=new Image();
newphotoimg1_4.src=webserver+"1_r2_c11.png";
var newphotoimg1_5=new Image();
newphotoimg1_5.src=webserver+"1_r2_c2.png";
var newphotoimg1_6=new Image();
newphotoimg1_6.src=webserver+"1_r2_c5.png";

var newphotoimg1_7=new Image();
newphotoimg1_7.src=webserver+"1_r5_c1.png";
var newphotoimg1_8=new Image();
newphotoimg1_8.src=webserver+"1_r5_c2.png";
var newphotoimg1_9=new Image();
newphotoimg1_9.src=webserver+"1_r5_c5.png";
var newphotoimg2_1=new Image();
newphotoimg2_1.src=webserver+"2_r1_c1.png";
var newphotoimg2_2=new Image();
newphotoimg2_2.src=webserver+"2_r1_c2.png";
var newphotoimg2_3=new Image();
newphotoimg2_3.src=webserver+"2_r1_c4.png";
var newphotoimg2_4=new Image();
newphotoimg2_4.src=webserver+"2_r2_c11.png";
var newphotoimg2_5=new Image();
newphotoimg2_5.src=webserver+"2_r2_c2.png";
var newphotoimg2_6=new Image();
newphotoimg2_6.src=webserver+"2_r2_c5.png";
var newphotoimg2_7=new Image();
newphotoimg2_7.src=webserver+"2_r5_c1.png";
var newphotoimg2_8=new Image();
newphotoimg2_8.src=webserver+"2_r5_c2.png";
var newphotoimg2_9=new Image();
newphotoimg2_9.src=webserver+"2_r5_c5.png";
var t=new Object();
var obj_moing=false;
var obj_resize="";
var obj_Ofx=0;
var obj_Vfx=0;
var obj_lastx=0;
var obj_Ofy=0;
var obj_Vfy=0;
var obj_lasty=0;
var alldivnum=2;
var allwidth=0;
var allheight=0;
var closeleft=0;
var closetop=0;
var moveallwidth=0;
var moveallheight=0;
var moveleft=0;
var movetop=0;
var curX=0;
var curY=0;
var resizeobjnum=0;
var pagewidth=0;
var pageheight=0;
var istrmoveing=0;
//alert(pagewidth+"|"+pageheight);
function istrmove(yesorno)
{
	if (yesorno=="yes")
	{
		istrmoveing=1;
	}
	else
	{
		istrmoveing=0;
	}
}
function down_obj(obj)
{
	if(obj_moing) return; //如果正在拖动,那么返回
	var objnum=obj.replace("photodiv","");
	if (document.getElementById("ismax"+objnum).value=="yes")
	{
		return false;
	}
	//alert(objnum);
	document.getElementById(obj).style.zIndex=100+alldivnum;
	document.getElementById("topleft"+objnum).style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+newphotoimg2_1.src+"')";
	document.getElementById("topcenter"+objnum).style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+newphotoimg2_2.src+"',sizingMethod='scale')";
	document.getElementById("topright"+objnum).style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+newphotoimg2_3.src+"')";
	document.getElementById("middleleft"+objnum).style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+newphotoimg2_4.src+"',sizingMethod='scale')";
	document.getElementById("middlecenter"+objnum).style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+newphotoimg2_5.src+"',sizingMethod='scale')";
	document.getElementById("middleright"+objnum).style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+newphotoimg2_6.src+"',sizingMethod='scale')";
	document.getElementById("bottomleft"+objnum).style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+newphotoimg2_7.src+"')";
	document.getElementById("bottomcenter"+objnum).style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+newphotoimg2_8.src+"',sizingMethod='scale')";
	document.getElementById("bottomright"+objnum).style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+newphotoimg2_9.src+"')";

	for(i=1;i<=alldivnum;i++)
	{
		if (obj!="photodiv"+i)
		{
			if (document.getElementById("photodiv"+i))
			{
				document.getElementById("photodiv"+i).style.zIndex=parseInt(document.getElementById("photodiv"+i).style.zIndex)-1;
				document.getElementById("topleft"+i).style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+newphotoimg1_1.src+"')";
				document.getElementById("topcenter"+i).style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+newphotoimg1_2.src+"',sizingMethod='scale')";
				document.getElementById("topright"+i).style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+newphotoimg1_3.src+"')";
				document.getElementById("middleleft"+i).style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+newphotoimg1_4.src+"',sizingMethod='scale')";
				document.getElementById("middlecenter"+i).style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+newphotoimg1_5.src+"',sizingMethod='scale')";
				document.getElementById("middleright"+i).style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+newphotoimg1_6.src+"',sizingMethod='scale')";
				document.getElementById("bottomleft"+i).style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+newphotoimg1_7.src+"')";
				document.getElementById("bottomcenter"+i).style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+newphotoimg1_8.src+"',sizingMethod='scale')";
				document.getElementById("bottomright"+i).style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+newphotoimg1_9.src+"')";
			}
		}
	}
	curX=event.clientX+document.documentElement.scrollLeft;
	curY=event.clientY+document.documentElement.scrollTop;
	moveallwidth=parseInt(document.getElementById(obj).offsetLeft)+parseInt(document.getElementById(obj).offsetWidth);
	moveallheight=parseInt(document.getElementById(obj).offsetTop)+parseInt(document.getElementById(obj).offsetHeight);
	moveleft=parseInt(moveallwidth)-parseInt(curX);
	movetop=parseInt(moveallheight)-parseInt(curY);
	if (moveleft<=7 && moveleft>=5)
	{
		if (moveleft<=7 && moveleft>=5 && movetop<=13 && movetop>=6)
		{
			//document.getElementById(obj).style.cursor="ne-resize";
			obj_resize="right_bottom";
		}
		else
		{
			obj_resize="right_right";
		}
		document.getElementById(obj).setCapture();	//锁住图片
	}
	else if (movetop<=10 && movetop>=8)
	{
		//document.getElementById(obj).style.cursor="n-resize";
		obj_resize="bottom_bottom";
		document.getElementById(obj).setCapture();	//锁住图片
	}
	else
	{
		//document.getElementById(obj).style.cursor="";
		obj_resize="";
		obj_Ofx = event.clientX;	//获取鼠标在网页中的X坐标
		obj_Vfx = document.getElementById(obj).offsetLeft;	//获取图片的左位置
		obj_Ofy = event.clientY;	//获取鼠标在网页中的X坐标
		obj_Vfy = document.getElementById(obj).offsetTop;	//获取图片的左位置
		document.getElementById(obj).setCapture();	//锁住图片
		obj_moing = true;	//设正在拖动
	}
}
function up_obj(obj)	//当鼠标弹起时触发
{
	var objnum=obj.replace("photodiv","");
	if(obj_moing==true && obj_resize=="") //如果不是正在拖动,那么
	{
		document.getElementById("lefttop"+objnum).value=parseInt(document.getElementById(obj).style.left)+","+parseInt(document.getElementById(obj).style.top);
		document.getElementById(obj).releaseCapture();	//释放锁住的图片
		obj_moing = false;	//设不是正在拖动
	}
	else if (obj_moing==false && obj_resize!="")
	{
		document.getElementById("widthheight"+objnum).value=parseInt(document.getElementById(obj).offsetWidth)+","+parseInt(document.getElementById(obj).offsetHeight);
		document.getElementById("lefttop"+objnum).value=parseInt(document.getElementById(obj).style.left)+","+parseInt(document.getElementById(obj).style.top);
		document.getElementById(obj).releaseCapture();	//释放锁住的图片
		obj_resize="";
	}
}
function move_obj(obj)	//当鼠标移动时触发
{
	if(obj_moing==false && obj_resize=="") //如果不是正在拖动,那么返回
	{
		curX=event.clientX+document.documentElement.scrollLeft;
		curY=event.clientY+document.documentElement.scrollTop;
		moveallwidth=parseInt(document.getElementById(obj).offsetLeft)+parseInt(document.getElementById(obj).offsetWidth);
		moveallheight=parseInt(document.getElementById(obj).offsetTop)+parseInt(document.getElementById(obj).offsetHeight);
		moveleft=parseInt(moveallwidth)-parseInt(curX);
		movetop=parseInt(moveallheight)-parseInt(curY);
		if (moveleft<=7 && moveleft>=5)
		{
			if (moveleft<=7 && moveleft>=5 && movetop<=13 && movetop>=6)
			{
				document.getElementById(obj).style.cursor="se-resize";
			}
			else
			{
				document.getElementById(obj).style.cursor="w-resize";
			}
		}
		else if (movetop<=10 && movetop>=8)
		{
			document.getElementById(obj).style.cursor="n-resize";
		}
		else
		{
			document.getElementById(obj).style.cursor="";
		}
		//var objnum=obj.replace("photodiv","");
		//document.getElementById("middlecenter"+objnum).innerHTML=document.getElementById(obj).offsetLeft+","+document.getElementById(obj).offsetWidth+"|"+document.getElementById(obj).offsetTop+"<br>"+curX+"|"+curY;
		//document.getElementById("middlecenter"+objnum).innerHTML=event.x;
	}
	else
	{
		if (obj_moing==true)
		{
			curX=event.clientX;
			curY=event.clientY;
			obj_lastx=obj_Vfx+curX-obj_Ofx;	//取得移动后的左位置
			obj_lasty=obj_Vfy+curY-obj_Ofy;	//取得移动后的上位置
			//if (photo_lastx>184) photo_lastx=184;	//如果左位置大于184,那么等于184.....防止超出指定的范围
			//if (photo_lastx<164) photo_lastx=164;	//如果左位置小于164,那么等于164.....防止超出指定的范围
			document.getElementById(obj).style.left = obj_lastx;	//设定移动后的位置
			document.getElementById(obj).style.top = obj_lasty;	//设定移动后的位置
		}
		else
		{
			resizeobjnum=obj.replace("photodiv","");
			switch(obj_resize)
			{
				case "right_right":
					if (event.clientX-parseInt(document.getElementById(obj).offsetLeft)<290)
					{
						document.getElementById(obj).style.width=290;
						document.getElementById("topcenter"+resizeobjnum).width=parseInt(document.getElementById(obj).style.width)-120;
						document.getElementById("middlecenter"+resizeobjnum).width=parseInt(document.getElementById(obj).style.width)-30;
						document.getElementById("bottomcenter"+resizeobjnum).width=parseInt(document.getElementById(obj).style.width)-30;
					}
					else
					{
						if (event.clientX-parseInt(document.getElementById(obj).offsetLeft)<pagewidth)
						{
							document.getElementById(obj).style.width=event.clientX-parseInt(document.getElementById(obj).offsetLeft);
							document.getElementById("topcenter"+resizeobjnum).width=parseInt(document.getElementById(obj).style.width)-120;
							document.getElementById("middlecenter"+resizeobjnum).width=parseInt(document.getElementById(obj).style.width)-30;
							document.getElementById("bottomcenter"+resizeobjnum).width=parseInt(document.getElementById(obj).style.width)-30;
						}
					}
					break;
				case "bottom_bottom":
					if (event.clientY-parseInt(document.getElementById(obj).offsetTop)<70)
					{
						document.getElementById(obj).style.height=70;
						document.getElementById("middlecenter"+resizeobjnum).height=parseInt(document.getElementById(obj).style.height)-40;
					}
					else
					{
						if (event.clientY-parseInt(document.getElementById(obj).offsetTop)<pageheight)
						{
							document.getElementById(obj).style.height=event.clientY-parseInt(document.getElementById(obj).offsetTop);
							document.getElementById("middlecenter"+resizeobjnum).height=parseInt(document.getElementById(obj).style.height)-40;
						}
					}
					break;
				case "right_bottom":
					if ((event.clientY-parseInt(document.getElementById(obj).offsetTop)<70) && (event.clientX-parseInt(document.getElementById(obj).offsetLeft)<290))
					{
						document.getElementById(obj).style.width=290;
						document.getElementById("topcenter"+resizeobjnum).width=parseInt(document.getElementById(obj).style.width)-120;
						document.getElementById("middlecenter"+resizeobjnum).width=parseInt(document.getElementById(obj).style.width)-30;
						document.getElementById("bottomcenter"+resizeobjnum).width=parseInt(document.getElementById(obj).style.width)-30;
						document.getElementById(obj).style.height=70;
						document.getElementById("middlecenter"+resizeobjnum).height=parseInt(document.getElementById(obj).style.height)-40;
					}
					else if ((event.clientY-parseInt(document.getElementById(obj).offsetTop)>=70) && (event.clientX-parseInt(document.getElementById(obj).offsetLeft)<290))
					{
						document.getElementById(obj).style.width=290;
						document.getElementById("topcenter"+resizeobjnum).width=parseInt(document.getElementById(obj).style.width)-120;
						document.getElementById("middlecenter"+resizeobjnum).width=parseInt(document.getElementById(obj).style.width)-30;
						document.getElementById("bottomcenter"+resizeobjnum).width=parseInt(document.getElementById(obj).style.width)-30;
						if (event.clientY-parseInt(document.getElementById(obj).offsetTop)<pageheight)
						{
							document.getElementById(obj).style.height=event.clientY-parseInt(document.getElementById(obj).offsetTop);
							document.getElementById("middlecenter"+resizeobjnum).height=parseInt(document.getElementById(obj).style.height)-40;
						}
					}
					else if ((event.clientY-parseInt(document.getElementById(obj).offsetTop)<70) && (event.clientX-parseInt(document.getElementById(obj).offsetLeft)>=290))
					{
						if (event.clientX-parseInt(document.getElementById(obj).offsetLeft)<pagewidth)
						{
							document.getElementById(obj).style.width=event.clientX-parseInt(document.getElementById(obj).offsetLeft);
							document.getElementById("topcenter"+resizeobjnum).width=parseInt(document.getElementById(obj).style.width)-120;
							document.getElementById("middlecenter"+resizeobjnum).width=parseInt(document.getElementById(obj).style.width)-30;
							document.getElementById("bottomcenter"+resizeobjnum).width=parseInt(document.getElementById(obj).style.width)-30;
						}
						
						document.getElementById(obj).style.height=70;
						document.getElementById("middlecenter"+resizeobjnum).height=parseInt(document.getElementById(obj).style.height)-40;
					}
					else
					{
						if (event.clientX-parseInt(document.getElementById(obj).offsetLeft)<pagewidth && event.clientY-parseInt(document.getElementById(obj).offsetTop)<pageheight)
						{
							document.getElementById(obj).style.width=event.clientX-parseInt(document.getElementById(obj).offsetLeft);
							document.getElementById("topcenter"+resizeobjnum).width=parseInt(document.getElementById(obj).style.width)-120;
							document.getElementById("middlecenter"+resizeobjnum).width=parseInt(document.getElementById(obj).style.width)-30;
							document.getElementById("bottomcenter"+resizeobjnum).width=parseInt(document.getElementById(obj).style.width)-30;
							document.getElementById(obj).style.height=event.clientY-parseInt(document.getElementById(obj).offsetTop);
							document.getElementById("middlecenter"+resizeobjnum).height=parseInt(document.getElementById(obj).style.height)-40;
						}
					}
					break;
				default:

					break;
			}
		}
	}
}
function showadddiv()
{
	document.getElementById("addtable").style.display="";
}
function isnumber(str) /*是否为数字(匹配整数,包括小数点)*/
{
	var renr=true;
	for (var i=0;i<str.length;i++)
	{
		var substr = str.charAt(i);
		//alert(substr);
		var reg = /[^(-?\d+)(\.\d+)?$]/;
		if (reg.test(substr)==true)
		{
			renr=false;
			break;
		}
	}
	return renr;
}
function adddiv()
{
	var innercode=document.getElementById("newcode").value;
	var innerwidth=document.getElementById("newdivwidth").value;
	var innerheight=document.getElementById("newdivheight").value;
	var innertitle=document.getElementById("newdivtitle").value;
	if (isnumber(innerwidth)==false || innerwidth=="")
	{
		alert("初始化窗口的宽不正确");
		document.getElementById("newdivwidth").focus();
		document.getElementById("newdivwidth").select();
		return false;
	}
	if (isnumber(innerheight)==false || innerheight=="")
	{
		alert("初始化窗口的高不正确");
		document.getElementById("newdivheight").focus();
		document.getElementById("newdivheight").select();
		return false;
	}
	if (parseInt(innerwidth)>parseInt(pagewidth))
	{
		alert("初始化窗口的宽超过屏幕显示的范围");
		document.getElementById("newdivwidth").focus();
		document.getElementById("newdivwidth").select();
		return false;
	}
	if (parseInt(innerheight)>parseInt(pageheight))
	{
		alert("初始化窗口的高超过屏幕显示的范围");
		document.getElementById("newdivheight").focus();
		document.getElementById("newdivheight").select();
		return false;
	}
	if (innertitle.indexOf("<")!=-1)
	{
		alert("初始化窗口的标题不能包括特殊符号");
		document.getElementById("newdivtitle").focus();
		document.getElementById("newdivtitle").select();
		return false;
	}
	if (parseInt(innerwidth)<290)
	{
		alert("初始化窗口的宽不能小于290");
		document.getElementById("newdivwidth").focus();
		document.getElementById("newdivwidth").select();
		return false;
	}
	if (parseInt(innerheight)<70)
	{
		alert("初始化窗口的高不能小于70");
		document.getElementById("newdivheight").focus();
		document.getElementById("newdivheight").select();
		return false;
	}
	innerwidth=parseInt(innerwidth);
	innerheight=parseInt(innerheight);
	var tdwidthtop=innerwidth-120;
	var tdwidth=innerwidth-30;
	var tdheight=innerheight-40;
	alldivnum=alldivnum+1;
	var ff="		<table border=\"0\" style=\"table-layout:fixed;\" id=\"phototable"+alldivnum+"\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">";
	ff=ff+"			<tr>";
	ff=ff+"				<td height=\"25\" width=\"15\" id=\"topleft"+alldivnum+"\" style=\"filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+newphotoimg1_1.src+"');\"><input type=\"hidden\" value=\"20,7\" id=\"lefttop"+alldivnum+"\" /><input type=\"hidden\" value=\""+innerwidth+","+innerheight+"\" id=\"widthheight"+alldivnum+"\" /><input type=\"hidden\" value=\"no\" id=\"ismax"+alldivnum+"\" /></td>";
	ff=ff+"				<td height=\"25\" valign=\"bottom\" id=\"topcenter"+alldivnum+"\" width=\""+tdwidthtop+"\" style=\"filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+newphotoimg1_2.src+"',sizingMethod='scale');overflow:hidden;white-space:nowrap;word-break:keep-all;text-overflow:ellipsis;\">"+innertitle+"</td>";
	ff=ff+"				<td height=\"25\" id=\"topright"+alldivnum+"\" width=\"105\" style=\"filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+newphotoimg1_3.src+"');\"></td>";
	ff=ff+"			</tr>";
	ff=ff+"			<tr>";
	ff=ff+"				<td colspan=\"3\">";
	ff=ff+"					<table border=\"0\" width=\"100%\" style=\"table-layout:fixed;\" cellspacing=\"0\" cellpadding=\"0\">";
	ff=ff+"						<tr>";
	ff=ff+"							<td width=\"15\" id=\"middleleft"+alldivnum+"\" style=\"filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+newphotoimg1_4.src+"',sizingMethod='scale');\"></td>";
	ff=ff+"							<td id=\"middlecenter"+alldivnum+"\" height=\""+tdheight+"\" width=\""+tdwidth+"\" style=\"filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+newphotoimg1_5.src+"',sizingMethod='scale');font-size:12px;color:#000000;overflow:hidden;white-space:nowrap;word-break:keep-all;text-overflow:ellipsis;\">"+innercode+"</td>";
	ff=ff+"							<td width=\"15\" id=\"middleright"+alldivnum+"\" style=\"filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+newphotoimg1_6.src+"',sizingMethod='scale');\"></td>";
	ff=ff+"						</tr>";
	ff=ff+"					</table>";
	ff=ff+"				</td>";
	ff=ff+"			</tr>";
	ff=ff+"			<tr>";
	ff=ff+"				<td colspan=\"3\">"
	ff=ff+"					<table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">";
	ff=ff+"						<tr>";
	ff=ff+"							<td id=\"bottomleft"+alldivnum+"\" height=\"15\" width=\"15\" style=\"filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+newphotoimg1_7.src+"');\"></td>";
	ff=ff+"							<td id=\"bottomcenter"+alldivnum+"\" height=\"15\" width=\""+tdwidth+"\" style=\"filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+newphotoimg1_8.src+"',sizingMethod='scale');\"></td>";
	ff=ff+"							<td id=\"bottomright"+alldivnum+"\" height=\"15\" width=\"15\" style=\"filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+newphotoimg1_9.src+"');\"></td>";
	ff=ff+"						</tr>";
	ff=ff+"					</table>";
	ff=ff+"				</td>";
	ff=ff+"			</tr>";
	ff=ff+"		</table>";
	var photodiv=document.createElement("div");
	photodiv.id="photodiv"+alldivnum;

	photodiv.style.width=innerwidth+"px";
	photodiv.style.height=innerheight+"px";
	photodiv.style.top="7px";
	photodiv.style.left="20px";
	photodiv.style.zIndex=alldivnum+100;
	photodiv.style.position="absolute";

	photodiv.onclick=new Function("closediv(this.id)");
	photodiv.onmousedown=new Function("down_obj(this.id)");
	photodiv.onmousemove=new Function("move_obj(this.id)");
	photodiv.onmouseup=new Function("up_obj(this.id)");

	photodiv.innerHTML=ff;
	document.body.appendChild(photodiv);
	document.getElementById("addtable").style.display="none";
}
function closediv(obj)
{
	//if(obj_moing==true || obj_resize!="") return false;
	curX=event.clientX+document.documentElement.scrollLeft;
	curY=event.clientY+document.documentElement.scrollTop;
	allwidth=parseInt(document.getElementById(obj).offsetLeft)+parseInt(document.getElementById(obj).offsetWidth);
	allheight=parseInt(document.getElementById(obj).offsetTop);
	closeleft=parseInt(allwidth)-parseInt(curX);
	closetop=parseInt(curY)-allheight;
	if (closeleft<=50 && closeleft>=12  && closetop<=25 && closetop>=11)
	{
		document.body.removeChild(document.getElementById(obj));
	}
	if (closeleft<=76 && closeleft>=54  && closetop<=25 && closetop>=11)
	{	
		var objnum=obj.replace("photodiv","");
		if (document.getElementById("ismax"+objnum).value=="yes")
		{
			huanyuandiv(obj);
		}
		else
		{
			maxdiv(obj);
		}
	}
	if (closeleft<=101 && closeleft>=79  && closetop<=25 && closetop>=11)
	{

	}
}
function huanyuandiv(obj)
{
	var bbobj=obj.replace("photodiv","");
	document.getElementById("ismax"+bbobj).value="no";
	var lefttop_xiang=document.getElementById("lefttop"+bbobj).value.split(",");
	var widthheight_xiang=document.getElementById("widthheight"+bbobj).value.split(",");
	if (parseInt(document.getElementById(obj).style.width)<=parseInt(widthheight_xiang[0]) || parseInt(document.getElementById(obj).style.height)<=parseInt(widthheight_xiang[1]))
	{
		document.getElementById("photodiv"+bbobj).style.left=lefttop_xiang[0];
		document.getElementById("photodiv"+bbobj).style.top=lefttop_xiang[1];
		document.getElementById("photodiv"+bbobj).style.width=widthheight_xiang[0];
		document.getElementById("photodiv"+bbobj).style.height=widthheight_xiang[1];
		document.getElementById("topcenter"+bbobj).width=parseInt(widthheight_xiang[0])-120;
		document.getElementById("middlecenter"+bbobj).width=parseInt(widthheight_xiang[0])-30;
		document.getElementById("bottomcenter"+bbobj).width=parseInt(widthheight_xiang[0])-30;
		document.getElementById("middlecenter"+bbobj).height=parseInt(widthheight_xiang[1])-40;
		clearTimeout(t.timer1);
	}
	else
	{
		document.getElementById("photodiv"+bbobj).style.width=parseInt(document.getElementById("photodiv"+bbobj).style.width)-pagewidth/20;
		document.getElementById("photodiv"+bbobj).style.height=parseInt(document.getElementById("photodiv"+bbobj).style.height)-pageheight/20;
		document.getElementById("photodiv"+bbobj).style.left=(pagewidth-parseInt(document.getElementById("photodiv"+bbobj).style.width))/2;
		document.getElementById("photodiv"+bbobj).style.top=(pageheight-parseInt(document.getElementById("photodiv"+bbobj).style.height))/2;
		document.getElementById("topcenter"+bbobj).width=parseInt(document.getElementById("photodiv"+bbobj).style.width)-120;
		document.getElementById("middlecenter"+bbobj).width=parseInt(document.getElementById("photodiv"+bbobj).style.width)-30;
		document.getElementById("bottomcenter"+bbobj).width=parseInt(document.getElementById("photodiv"+bbobj).style.width)-30;
		document.getElementById("middlecenter"+bbobj).height=parseInt(document.getElementById("photodiv"+bbobj).style.height)-40;
		t.timer1=setTimeout("huanyuandiv('"+obj+"')",10);
	}

}
function maxdiv(obj)
{
	var bbobj=obj.replace("photodiv","");
	if (parseInt(document.getElementById(obj).style.width)>=pagewidth || parseInt(document.getElementById(obj).style.height)>=pageheight)
	{
		document.getElementById("ismax"+bbobj).value="yes";
		document.getElementById("photodiv"+bbobj).style.width=pagewidth;
		document.getElementById("photodiv"+bbobj).style.height=pageheight;
		document.getElementById("photodiv"+bbobj).style.left=0;
		document.getElementById("photodiv"+bbobj).style.top=0;
		document.getElementById("topcenter"+bbobj).width=parseInt(pagewidth)-120;
		document.getElementById("middlecenter"+bbobj).width=parseInt(pagewidth)-30;
		document.getElementById("bottomcenter"+bbobj).width=parseInt(pagewidth)-30;
		document.getElementById("middlecenter"+bbobj).height=parseInt(pageheight)-40;
		clearTimeout(t.timer1);
	}
	else
	{
		document.getElementById("photodiv"+bbobj).style.width=parseInt(document.getElementById("photodiv"+bbobj).style.width)+pagewidth/20;
		document.getElementById("photodiv"+bbobj).style.height=parseInt(document.getElementById("photodiv"+bbobj).style.height)+pageheight/20;
		document.getElementById("photodiv"+bbobj).style.left=(pagewidth-parseInt(document.getElementById("photodiv"+bbobj).style.width))/2;
		document.getElementById("photodiv"+bbobj).style.top=(pageheight-parseInt(document.getElementById("photodiv"+bbobj).style.height))/2;
		document.getElementById("topcenter"+bbobj).width=parseInt(document.getElementById("photodiv"+bbobj).style.width)-120;
		document.getElementById("middlecenter"+bbobj).width=parseInt(document.getElementById("photodiv"+bbobj).style.width)-30;
		document.getElementById("bottomcenter"+bbobj).width=parseInt(document.getElementById("photodiv"+bbobj).style.width)-30;
		document.getElementById("middlecenter"+bbobj).height=parseInt(document.getElementById("photodiv"+bbobj).style.height)-40;
		t.timer1=setTimeout("maxdiv('"+obj+"')",10);
	}
}