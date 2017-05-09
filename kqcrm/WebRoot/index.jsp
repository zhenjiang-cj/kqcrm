<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>��ʵ�ù���jQuery UI��� - DWZ���ͻ��˿��(J-UI.com)</title>

<link href="<%=path%>/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=path%>/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=path%>/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="<%=path%>/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<!--[if IE]>
<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->
<style type="text/css">
	#header{height:85px}
	#leftside, #container, #splitBar, #splitBarProxy{top:90px}
</style>

<!--[if lte IE 9]>
<script src="js/speedup.js" type="text/javascript"></script>
<![endif]-->
<script src="<%=path%>/dwz/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/jquery.cookie.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/jquery.validate.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/uploadify/scripts/jquery.uploadify.min.js" type="text/javascript"></script>

<script src="<%=path%>/dwz/bin/dwz.min.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("/khcrm/dwz/dwz.frag.xml", {
		loginUrl:"login_dialog.html", loginTitle:"��¼",	// ������¼�Ի���
//		loginUrl:"login.html",	// ������¼ҳ��
		statusCode:{ok:200, error:300, timeout:301}, //����ѡ��
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //����ѡ��
		debug:false,	// ����ģʽ ��true|false��
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"themes"});
			//setTimeout(function() {$("#sidebar .toggleCollapse div").trigger("click");}, 10);
		}
	});
});
</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="http://j-ui.com">��־</a>
				<ul class="nav">
					<li id="switchEnvBox"><a href="javascript:">��<span>����</span>���л�����</a>
						<ul>
							<li><a href="sidebar_1.html">����</a></li>
							<li><a href="sidebar_2.html">�Ϻ�</a></li>
							<li><a href="sidebar_2.html">�Ͼ�</a></li>
							<li><a href="sidebar_2.html">����</a></li>
							<li><a href="sidebar_2.html">����</a></li>
							<li><a href="sidebar_2.html">���</a></li>
							<li><a href="sidebar_2.html">����</a></li>
						</ul>
					</li>
					<li><a href="https://me.alipay.com/dwzteam" target="_blank">����</a></li>
					<li><a href="changepwd.html" target="dialog" width="600">����</a></li>
					<li><a href="http://www.cnblogs.com/dwzjs" target="_blank">����</a></li>
					<li><a href="http://weibo.com/dwzui" target="_blank">΢��</a></li>
					<li><a href="http://bbs.dwzjs.com" target="_blank">��̳</a></li>
					<li><a href="login.html">�˳�</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">��ɫ</div></li>
					<li theme="green"><div>��ɫ</div></li>
					<!--<li theme="red"><div>��ɫ</div></li>-->
					<li theme="purple"><div>��ɫ</div></li>
					<li theme="silver"><div>��ɫ</div></li>
					<li theme="azure"><div>����</div></li>
				</ul>
			</div>
		
			<div id="navMenu">
				<ul>
					<li class="selected"><a href="<%=path%>/dwz/sidebar_2.html"><span>��������</span></a></li>
					<li ><a href="<%=path%>/coremain/portal/user/frame.jsp"><span>ϵͳ����</span></a></li>
					<li><a href="<%=path%>/dwz/sidebar_1.html"><span>��Ʒ����</span></a></li>
					<li><a href="<%=path%>/dwz/sidebar_2.html"><span>��Ա����</span></a></li>
					<li><a href="<%=path%>/dwz/sidebar_1.html"><span>�������</span></a></li>
					<li><a href="<%=path%>/dwz/sidebar_2.html"><span>ϵͳ����</span></a></li>
				</ul>
			</div>
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>���˵�</h2><div>����</div></div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2><span>Folder</span>�������</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="tabsPage.html" target="navTab">��������</a>
								<ul>
									<li><a href="main.html" target="navTab" rel="main">�ҵ���ҳ</a></li>
									<li><a href="http://www.baidu.com" target="navTab" rel="page1">ҳ��һ(�ⲿҳ��)</a></li>
									<li><a href="demo_page2.html" target="navTab" rel="external" external="true">iframe navTabҳ��</a></li>
									<li><a href="demo_page1.html" target="navTab" rel="page1" fresh="false">�滻ҳ��һ</a></li>
									<li><a href="demo_page2.html" target="navTab" rel="page2">ҳ���</a></li>
									<li><a href="demo_page4.html" target="navTab" rel="page3" title="ҳ�������Զ����ǩ����">ҳ����</a></li>
									<li><a href="demo_page4.html" target="navTab" rel="page4" fresh="false">����ҳ�棨fresh="false"��</a></li>
									<li><a href="w_editor.html" target="navTab">���ύ�Ự��ʱ</a></li>
									<li><a href="demo/common/ajaxTimeout.html" target="navTab">navTab�Ự��ʱ</a></li>
									<li><a href="demo/common/ajaxTimeout.html" target="dialog">dialog�Ự��ʱ</a></li>
								</ul>
							</li>
							
							<li><a>�������</a>
								<ul>
									<li><a href="w_panel.html" target="navTab" rel="w_panel">���</a></li>
									<li><a href="w_tabs.html" target="navTab" rel="w_tabs">ѡ����</a></li>
									<li><a href="w_dialog.html" target="navTab" rel="w_dialog">��������</a></li>
									<li><a href="w_alert.html" target="navTab" rel="w_alert">��ʾ����</a></li>
									<li><a href="w_list.html" target="navTab" rel="w_list">CSS�������</a></li>
									<li><a href="demo_page1.html" target="navTab" rel="w_table">�������</a></li>
									<li><a href="w_removeSelected.html" target="navTab" rel="w_table">������ݿ�����+����ɾ��</a></li>
									<li><a href="w_tree.html" target="navTab" rel="w_tree">���β˵�</a></li>
									<li><a href="w_accordion.html" target="navTab" rel="w_accordion">�����˵�</a></li>
									<li><a href="w_editor.html" target="navTab" rel="w_editor">�༭��</a></li>
									<li><a href="w_datepicker.html" target="navTab" rel="w_datepicker">���ڿؼ�</a></li>
									<li><a href="demo/database/db_widget.html" target="navTab" rel="db">suggest+lookup+���ӽṹ</a></li>
									<li><a href="demo/sortDrag/1.html" target="navTab" rel="sortDrag">����sortDragʾ��</a></li>
									<li><a href="demo/sortDrag/2.html" target="navTab" rel="sortDrag">���sortDragʾ��</a></li>
								</ul>
							</li>
							 
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>����ҳ��</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder treeCheck">
							<li><a href="demo_page1.html" target="navTab" rel="demo_page1">��ѯ�ҵĿͻ�</a></li>
							<li><a href="demo_page1.html" target="navTab" rel="demo_page2">����ѯҳ��</a></li>
							<li><a href="demo_page4.html" target="navTab" rel="demo_page4">��¼��ҳ��</a></li>
							<li><a href="demo_page5.html" target="navTab" rel="demo_page5">���ı�����ı�</a></li>
							<li><a href="javascript:;">����ʾ�ı�����ҳ��</a>
								<ul>
									<li><a href="javascript:;">ҳ��һ</a></li>
									<li><a href="javascript:;">ҳ���</a></li>
								</ul>
							</li>
							<li><a href="javascript:;">ѡ���ͼ�ε�ҳ��</a>
								<ul>
									<li><a href="javascript:;">ҳ��һ</a></li>
									<li><a href="javascript:;">ҳ���</a></li>
								</ul>
							</li>
							<li><a href="javascript:;">ѡ���ͼ���л���ҳ��</a></li>
							<li><a href="javascript:;">��������������ҳ��</a></li>
							<li><a href="javascript:;">�б������ҳ��</a></li>
							<li><a href="javascript:;">˫����Ŀ�б��ҳ��</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>������ʾ</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree">
							<li><a href="newPage1.html" target="dialog" rel="dlg_page">�б�</a></li>
							<li><a href="newPage1.html" target="dialog" rel="dlg_page">�б�</a></li>
							<li><a href="newPage1.html" target="dialog" rel="dlg_page2">�б�</a></li>
							<li><a href="newPage1.html" target="dialog" rel="dlg_page2">�б�</a></li>
							<li><a href="newPage1.html" target="dialog" rel="dlg_page2">�б�</a></li>
						</ul>
					</div>
				</div>

			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- ��ʾ���ҿ���ʱ��� class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">�ҵ���ҳ</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- ����ֻ��Ҫ���һ����ʽ class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- ����ֻ��Ҫ���һ����ʽ class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">�ҵ���ҳ</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							<div class="alertInfo">
								<h2><a href="doc/dwz-user-guide.pdf" target="_blank">DWZ���ʹ���ֲ�(PDF)</a></h2>
								<a href="doc/dwz-user-guide.swf" target="_blank">DWZ�����ʾ��Ƶ</a>
							</div>
							<div class="right">
								<p><a href="doc/dwz-user-guide.zip" target="_blank" style="line-height:19px">DWZ���ʹ���ֲ�(CHM)</a></p>
								<p><a href="doc/dwz-ajax-develop.swf" target="_blank" style="line-height:19px">DWZ���Ajax������Ƶ�̲�</a></p>
							</div>
							<p><span>DWZ���ͻ��˿��</span></p>
							<p>DWZ�ٷ�΢��:<a href="http://weibo.com/dwzui" target="_blank">http://weibo.com/dwzui</a></p>
						</div>
						<div class="pageFormContent" layoutH="80">
							<iframe width="100%" height="430" class="share_self"  frameborder="0" scrolling="no" src="http://widget.weibo.com/weiboshow/index.php?width=0&height=430&fansRow=2&ptype=1&speed=300&skin=1&isTitle=0&noborder=1&isWeibo=1&isFans=0&uid=1739071261&verifier=c683dfe7"></iframe>
						</div>
					</div>
					
				</div>
			</div>
		</div>

	</div>

	<div id="footer">Copyright &copy; 2010 <a href="demo_page2.html" target="dialog">DWZ�Ŷ�</a></div>

<!-- ע��˴�js��������googleվ��ͳ�ƣ���DWZ���룬��ɾ�� -->
<script type="text/javascript">
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-16716654-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? ' https://ssl' : ' http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
</script>

</body>
</html>