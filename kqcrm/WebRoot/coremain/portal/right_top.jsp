<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.nl.util.SessionData"%>
<%@page import="com.nl.util.SessionConst"%>
<%@page import="com.nl.portal.dt.SysOperator"%>
<%@page import="com.nl.portal.dt.SysDept"%>
<%@page import="com.nl.util.DateTime"%>
<%@page import="com.nl.portal.vo.PortalDeptInfoVo"%>
<%@page import="com.nl.sysflow.sc.FlowSc"%>
<%@page import="com.nl.sysflow.actionForm.*"%>
<%@page import="com.nl.sysflow.dt.*"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	SessionData sessionData = (SessionData)session.getAttribute(SessionConst.LOGIN_SESSION);
	SysOperator operator =  sessionData.getSysOperator();
	SysDept sysOrg = new SysDept();
	
	int remindCnt = sessionData.getRemindCnt();
	int maxRemindCnt = sessionData.getMaxRemindCnt();

	StringBuffer logintimeStr = new StringBuffer();

	String logintime[] = DateTime.getTime(6,0);
	if (Integer.parseInt(logintime[3]) <= 12)
	    logintimeStr.append("����");
	if (Integer.parseInt(logintime[3]) >12 && Integer.parseInt(logintime[3]) <= 18)
	    logintimeStr.append("����");
	if (Integer.parseInt(logintime[3]) >18)
	    logintimeStr.append("����");
	logintimeStr.append(logintime[3]+":"+logintime[4]+":"+logintime[5]);  
	
	FlowSc fsc = new FlowSc();
	List<FlowTaskDt>  list =   fsc.queryTrackFlowBySno(operator.getSNo());
	int listsize =0;
	if(list!=null&&list.size()>0){
		listsize=list.size();
	}
	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>������ͳһƽ̨�ұ�ͷ��</title>
    <link href="./css/systemCont.css" rel="stylesheet" type="text/css">
	<link href="<%=path%>/plugins/jquery/plugins/tokeninput/css/main_token-input.css" rel="stylesheet" type="text/css"/>
    <link href='./plugins/jquery/plugins/bartip/css/bartip.css'  rel="stylesheet" type="text/css"/>
	<link type="text/css" rel="stylesheet" href="<%=path%>/plugins/lhgdialog/lhgdialog.css" />
  </head>
<style>*{margin:0;padding:0}
</style>
<body class="Contbody">
    <input type="hidden" id="hidden1" value="��ӭ��¼"/>
	<input type="hidden" id="hidden2" value=""/>
	<input type="hidden" id="hidden3" value=""/>
	<input type="hidden" id="hidden4" value=""/>
	<input type="hidden" id="hidden5" value=""/>
	<div class="contAllDiv">
       <div class="workCont">
			<!--���� START-->
				<div class="borNO workContBG">
							<!--��¼��Ϣ START-->
							<div class="contInfo">
								 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tab">
								  <tr>
									<td class="L">
									    ����Ա:&nbsp;<%=operator.getUser_name()%>
									    (&nbsp;�û���:<%=operator.getUser_id()%>)
									    <%
									    	if(listsize>0){
									    		%>
									    		&nbsp;���칤����:<a onclick="tomywait();" style="cursor:pointer;" ><font color="red"><%=listsize %></font></a>
									    		<%
									    	}
									     %>
									</td>
									<td>
                                        <div id="bartip"></div>
                                    </td>
									<td class="R">��ǰ����:
									<%if(sessionData.getSysDeptList().size()>=2){ 
									%>
										<select id="deptId" name="deptId" class="selOpNSize" onchange="freshOperSession()">
									<%	for (int i=0; i<sessionData.getSysDeptList().size(); i++)
			                   			{
											sysOrg = (SysDept)sessionData.getSysDeptList().get(i);
									 %>
										
									        <option value="<%=sysOrg.getSno_dept() %>">
							                   <%=sysOrg.getSno_dept_name()%>
							               </option>  
								        
									<% }%>
										</select>
									<% }else{ %>
									<%=StringUtils.isNotEmpty(operator.getSno_dept_name())?operator.getSno_dept_name():"���ա�"%> 
									<%} %>
									��¼ʱ��:<%=logintimeStr.toString()%></td>
								  </tr>
								</table>
							</div>
							<!--��¼��Ϣ END-->
							<!--������ѡ�� START-->
			 <div  class="workMenu" style="text-align:left">
			 <table border="0" cellpadding="0" cellspacing="0">
				 <tr>
				 <!-- ��һҳ START-->
				 
				 <td>
				 <table border="0" id="table1" cellspacing="0" cellpadding="0" class="unitTab02" style="display:block">
					  <tr>
						<td class="colTd01"></td>
						<td id="td1" class="colTd02"  onClick="showTitleAndFrame(1)">��ӭ��¼</td>
						<td class="colTd03" onClick="deleteTaskById(1)" id="pic1" onMouseOver="changeToRed(1)" onMouseOut="changeToBack(1)"></td>
					  </tr>
				</table>
				</td>
				 <!-- ��һҳ END-->
				 <!-- �ڶ�ҳ START-->
				 <td>
				 <table border="0" id="table2" cellspacing="0" cellpadding="0" class="unitTab" style="display:none">
					  <tr>
						<td class="colTd01"></td>
						<td id="td2" class="colTd02"  onClick="showTitleAndFrame(2)"></td>
						<td class="colTd03" onClick="deleteTaskById(2)" id="pic2" onMouseOver="changeToRed(2)" onMouseOut="changeToBack(2)"></td>
					  </tr>
				</table>
				</td>
				 <!-- �ڶ�ҳ END-->
				 <!-- ��ǰҳ START-->
				 <td>
				 <table border="0" id="table3" cellspacing="0" cellpadding="0" class="unitTab" style="display:none">
					  <tr>
						<td class="colTd01"></td>
						<td id="td3" class="colTd02" onClick="showTitleAndFrame(3)"></td>
						<td class="colTd03" onClick="deleteTaskById(3)" id="pic3" onMouseOver="changeToRed(3)" onMouseOut="changeToBack(3)"></td>
					  </tr>
				</table>
				</td>
				 <!-- ��ǰҳ END-->
				 
				  <!-- ��ǰҳ START-->
				  <td>
				 <table border="0" id="table4" cellspacing="0" cellpadding="0" class="unitTab" style="display:none">
					  <tr>
						<td class="colTd01"></td>
						<td id="td4" class="colTd02" onClick="showTitleAndFrame(4)"></td>
						<td class="colTd03" onClick="deleteTaskById(4)" id="pic4" onMouseOver="changeToRed(4)" onMouseOut="changeToBack(4)"></td>
					  </tr>
				</table>
				</td>
				 <!-- ��ǰҳ END-->
				 
				  <!-- ��ǰҳ START-->
				  <td>
				 <table border="0" id="table5" cellspacing="0" cellpadding="0" class="unitTab" style="display:none">
					  <tr>
						<td class="colTd01"></td>
						<td id="td5" class="colTd02" onClick="showTitleAndFrame(5)"></td>
						<td class="colTd03" onClick="deleteTaskById(5)" id="pic5" onMouseOver="changeToRed(5)" onMouseOut="changeToBack(5)"></td>
					  </tr>
				</table>
				</td>
				 <!-- ��ǰҳ END-->
				 </tr>
				 </table>
			 </div>	
			<!--������ѡ�� END-->	
					</div>
			<!--���� END-->

	     </div>
	     <div class="menuLine">&nbsp;</div>
	</div>
</body>
</html>
<script type="text/javascript" src="./plugins/jquery/jquery-1.4.4.js"></script>
<script type="text/javascript" src="./plugins/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="./plugins/jquery/plugins/bartip/jquery.bartip.js"></script>	
<script language="javascript" type="text/javascript">
jQuery(document).ready(function(){
jQuery('#bartip').bartip({
                    postionTop: '2px',//����λ��TOP
                    postionLeft: undefined,//����λ��left Ϊundefinedʱ��Ч
                    postionRight:'540px' ,//����λ��right ����λ��TOP
                    titleColor:"#000",//������ɫ
                    limitLen:70,//��������
                    title: "���ݴ�����,..."//Ĭ����ʾ����
                });
                if(<%=remindCnt%><<%=maxRemindCnt%>){
					parent.showD('<%=path%>/coremain/portal/remind.jsp',600,300,'��֯������Ϣȷ��',70);
					}
jQuery("#deptId").val('<%=operator.getSno_dept() %>');
}); 
function myrefresh(){
   //window.location.reload();
}
//setTimeout('myrefresh()',10000); //ָ��5��ˢ��һ��

function tomywait()
{	
	
	parent.parent.document.getElementById("leftContent").style.display = '' ; 
	/*var maxWidth = Math.max(
		parent.parent.document.documentElement["clientWidth"],
		parent.parent.document.body["scrollWidth"], parent.document.documentElement["scrollWidth"],
		parent.parent.document.body["offsetWidth"], parent.document.documentElement["offsetWidth"]
	);
	parent.parent.document.getElementById("contents").style.width = ( maxWidth - 185 ) +'px';
	parent.parent.document.getElementById("leftContent").width = '176px' ;
	*/
	var clientWidth = parent.parent.document.documentElement["clientWidth"];
	parent.parent.document.getElementById("subformset").cols = "179,9,*"
	parent.parent.document.getElementById("leftContent").style.width = '179px';
	parent.parent.document.getElementById("show").style.width = '9px';
	var leftContentw = parent.parent.document.getElementById("leftContent").style.width;
	var showw = parent.parent.document.getElementById("show").style.width;
	parent.parent.document.getElementById("contents").style.width = ( clientWidth - leftContentw.substring(0,leftContentw.indexOf('px')) -  showw.substring(0,showw.indexOf('px')) -1) +'px';
	
	parent.parent.document.getElementById("leftContent").src = 'menu/system_leftCont.jsp?sysId=98&page_id=9840';
	
}

//������ʾ
function showM(name, flag) {
    jQuery('#bartip').bartip('showMessage', name, flag);
}

function addTask(taskname,taskurl)
{
	//alert(taskurl);
	//�����Ƿ�򿪿���
	var flag = true;
	//�����Ƿ񳬹�5������
	var flag1=true;
	
	//�������������Ƿ��Ѿ�����
	for(i=1;i<=5;i++)
	{
		//�����Ѿ��򿪣���ʾ������
		if(taskname == this.document.getElementById("hidden"+i).value)
		{
			showTitleAndFrameById(i,taskurl);
			flag = false;
			break;
		}
	}	
	
	if(flag)
	{
		//��Ϊ�´򿪵�����
		for(j=1;j<=5;j++)
		{
			if(this.document.getElementById("hidden"+j).value =="")
			{
				this.document.getElementById("hidden"+j).value = taskname;
				this.document.getElementById("td"+j).innerHTML = taskname;
				//this.document.getElementById("iframe"+j).src = taskurl;
				showTitleAndFrameById(j,taskurl);
				flag1=false;
				break;
			}
		}
		
		//���������Ѿ��ﵽ5���Ĵ���
		if(flag1)
		{
			for(i=3;i<=5;i++)
			{
				this.document.getElementById("hidden"+(i-1)).value=this.document.getElementById("hidden"+i).value;
				//this.document.getElementById("hidden"+(i-1)).src=this.document.getElementById("hidden"+i).src;
				this.document.getElementById("td"+(i-1)).innerHTML = this.document.getElementById("td"+i).innerHTML;
			}
			this.document.getElementById("hidden5").value = taskname;
			
			this.document.getElementById("td5").innerHTML=taskname;
			for(i=1;i<=5;i++)
			{
				this.document.getElementById("table"+i).style.display="block";
				this.document.getElementById("table"+i).className="unitTab";
				//this.document.getElementById("tr"+i).style.display="none";
			}
			this.document.getElementById("table5").className="unitTab02";
			//this.document.getElementById("tr5").style.display="block";
			//this.document.getElementById("iframe5").src = taskurl;
			parent.moveAndShowFrame(5,taskurl);
			
		}
	}
	
	//ɾ������ӭ��¼���Ĵ���
	for(i=1;i<=5;i++)
	{
		if ('��ӭ��¼' == this.document.getElementById("hidden"+i).value)
	    {
	        deleteTaskById(i);
	    }
	}
}

function showTitleAndFrameById(id,taskurl)
{
	showTitle(id);
	showFrame(id,taskurl);
}

//��ʾ����
function showTitle(id)
{
	for(i=1;i<=5;i++)
	{
		if(this.document.getElementById("hidden"+i).value != "")
		{
			this.document.getElementById("table"+i).style.display="block";
			this.document.getElementById("table"+i).className="unitTab";
		}
		else
		{
			break;
		}
	}
	this.document.getElementById("table"+id).style.display="block";
	this.document.getElementById("table"+id).className="unitTab02";
}

//��ʾ�������
function showFrame(id,taskurl)
{
	parent.showFrame(id,taskurl);
}

//
function showTitleById(id)
{
	for(i=1;i<=5;i++)
	{
		if(this.document.getElementById("hidden"+i).value != "")
		{
			this.document.getElementById("table"+i).style.display="block";
			this.document.getElementById("table"+i).className="unitTab";
		}
		else
		{
			break;
		}
			this.document.getElementById("table"+id).className="unitTab02";
	}
}

function showTitleAndFrame(id)
{
	showTitleById(id);
	parent.showFrameById(id);
}





//ɾ������
function deleteTaskById(id)
{
	var count =0;
	//�鿴�м������񱻴�
	var flag = true;
	for(i=1;i<=5;i++)
	{
		if(this.document.getElementById("hidden"+i).value != "")
		{
			count =count + 1;
		}
	}
	
	//ֻ��һ�������ʱ
	if(count == 1)
	{
		/*this.document.getElementById("hidden1").value="�û�������Ϣ";
		this.document.getElementById("td1").innerHTML="�û�������Ϣ";
		showTitleAndFrameById(1,"�û�������Ϣ.html");*/
		
		/*for(i=1;i<=5;i++)
		{
			this.document.getElementById("hidden"+i).value="";
			this.document.getElementById("table"+i).style.display="none";
			this.document.getElementById("table"+i).className="unitTab";
			this.document.getElementById("tr"+i).style.display="none";
			this.document.getElementById("iframe"+i).src="";
			this.document.getElementById("td"+i).innerHTML="";
		}
		    this.document.getElementById("hidden1").value="�û�������Ϣ";
			this.document.getElementById("td1").innerHTML="�û�������Ϣ";
			this.document.getElementById("table1").style.display="block";
			this.document.getElementById("table1").className="unitTab02";
			this.document.getElementById("tr1").style.display="block";
			this.document.getElementById("iframe1").src="�û�������Ϣ.html";*/
		//parent.showFrame1(1);
		//alert(1);
	}
	
	//�ж�������ʱ
	else
	{	
		//Ҫɾ��������Ϊ��5��ʱ
		if(id ==5)
		{
			this.document.getElementById("table5").style.display="none";
			this.document.getElementById("hidden5").value="";
			this.document.getElementById("td5").innerHTML="";
			//this.document.getElementById("tr5").style.display="none";
			//this.document.getElementById("iframe5").src="";
			parent.deleteFrameByIdAndShowNextById(5);
			showTitleById(4);
		}
		else
		{
			//Ҫɾ��������Ϊ����������һ��ʱ
			if(this.document.getElementById("hidden"+(id+1)).value == "")
			{
				this.document.getElementById("table"+id).style.display="none";
				this.document.getElementById("hidden"+id).value="";
				this.document.getElementById("td"+id).innerHTML="";
				//this.document.getElementById("tr"+id).style.display="none";
				
				//this.document.getElementById("iframe"+id).src="";
				showTitleAndFrame(id-1);
			} 
			else
			{
				//Ҫɾ���������ڴ�������м�ʱ
				for(i=id+1;(i<=5)&&flag;i++)
				{
					if(this.document.getElementById("hidden"+i).value=="")
					{
						this.document.getElementById("hidden"+(i-1)).value="";
						this.document.getElementById("table"+(i-1)).style.display="none";
						this.document.getElementById("td"+(i-1)).innerHTML="";
						this.document.getElementById("table"+(i-1)).className="unitTab";
						
						//this.document.getElementById("tr"+(i-1)).style.display="none";
						//this.document.getElementById("iframe"+(i-1)).src="";
						parent.deleteFrame(i-1);
						flag = false;
					}
					this.document.getElementById("hidden"+(i-1)).value = this.document.getElementById("hidden"+i).value;
					this.document.getElementById("td"+(i-1)).innerHTML=this.document.getElementById("td"+i).innerHTML��
					parent.moveFrame(i-1);
				}
				
				
				
				this.document.getElementById("table5").style.display="none";
				this.document.getElementById("hidden5").value="";
				parent.deleteFrame(5);
				
				
				for(i=1;i<=5;i++)
				{
					this.document.getElementById("td"+i).innerHTML="";
					this.document.getElementById("table"+i).style.display="none";
					if(this.document.getElementById("hidden"+i).value != "")
					{
						this.document.getElementById("table"+i).style.display="block";
						this.document.getElementById("td"+i).innerHTML=this.document.getElementById("hidden"+i).value;
						if(i == id)
						{
							this.document.getElementById("table"+id).className="unitTab02";
							parent.showFrame1(i);
						}
						else
						{
							this.document.getElementById("table"+i).className="unitTab";
							parent.hideFrame(i);
						}
					}
				}
			}
		}
	}

}

//�ı�ͼ��
function changeToRed(id)
{
	this.document.getElementById("pic"+id).className="colTd04";
}
//��ͼ������
function changeToBack(id)
{
	this.document.getElementById("pic"+id).className="colTd03";
}
//��õ�ǰѡ�е�iframe��Id
function getChooseFram()
{
    var iframeId = "";
    for (i=1;i<=5;i++)
    {
        if (this.document.getElementById("table"+i).className ==  "unitTab02")
        {
           iframeId = "iframe"+i;
           break;
        }
    }
    return iframeId;
}

function addTaskNoDelWelcome(taskname,taskurl)
{
	//alert(taskurl);
	//�����Ƿ�򿪿���
	var flag = true;
	//�����Ƿ񳬹�5������
	var flag1=true;
	
	//�������������Ƿ��Ѿ�����
	for(i=1;i<=5;i++)
	{
		//�����Ѿ��򿪣���ʾ������
		if(taskname == this.document.getElementById("hidden"+i).value)
		{
			showTitleAndFrameById(i,taskurl);
			flag = false;
			break;
		}
	}	
	
	if(flag)
	{
		//��Ϊ�´򿪵�����
		for(j=1;j<=5;j++)
		{
			if(this.document.getElementById("hidden"+j).value =="")
			{
				this.document.getElementById("hidden"+j).value = taskname;
				this.document.getElementById("td"+j).innerHTML = taskname;
				//this.document.getElementById("iframe"+j).src = taskurl;
				showTitleAndFrameById(j,taskurl);
				flag1=false;
				break;
			}
		}
		
		//���������Ѿ��ﵽ5���Ĵ���
		if(flag1)
		{
			for(i=3;i<=5;i++)
			{
				this.document.getElementById("hidden"+(i-1)).value=this.document.getElementById("hidden"+i).value;
				//this.document.getElementById("hidden"+(i-1)).src=this.document.getElementById("hidden"+i).src;
				this.document.getElementById("td"+(i-1)).innerHTML = this.document.getElementById("td"+i).innerHTML;
			}
			this.document.getElementById("hidden5").value = taskname;
			
			this.document.getElementById("td5").innerHTML=taskname;
			for(i=1;i<=5;i++)
			{
				this.document.getElementById("table"+i).style.display="block";
				this.document.getElementById("table"+i).className="unitTab";
				//this.document.getElementById("tr"+i).style.display="none";
			}
			this.document.getElementById("table5").className="unitTab02";
			//this.document.getElementById("tr5").style.display="block";
			//this.document.getElementById("iframe5").src = taskurl;
			parent.moveAndShowFrame(5,taskurl);
			
		}
	}

}

function freshOperSession()
{
	var deptId = jQuery("#deptId").val();
			
	top.location.href = "<%=path%>/login.do?method=freshOperSession&operatorId=<%=operator.getUser_id()%>&deptId="+deptId;
}
</script>
