<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>�й������򽭷ֹ�˾ͳһ��¼ƽ̨</title>
<link href="css/systemHead.css"  type="text/css" rel="stylesheet" />
</head>
<body  scroll="no">
<iframe id="index_iframe" name="index_iframe" frameborder="0" width="100%" height="100%" src="./coremain/portal/content.html"   scrolling="auto"></iframe>
</body>
</html>
<script type="text/javascript" src="./plugins/jquery/jquery-1.4.4.js"></script>
<script type="text/javascript">var $j=jQuery.noConflict();</script>
<script type="text/javascript" src="./plugins/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="./plugins/jquery/plugins/dialog/Dialog.js"></script>
<script type="text/javascript">
    //������
	IMGFOLDERPATH = './plugins/jquery/plugins/dialog/images/';
	
	//begin changed by sanjing 2012/09/29
	var diag;
	function showD(url1,w,h,tit,top)
	{
		diag = new Dialog("Diag1");
		//alert('showD111........');
		diag.Width = w;
		diag.Height = h;
		diag.Title = tit;
		diag.URL = url1;
		diag.Top = top;
		diag.show();
    }
    //end changed by sanjing 2012/09/29
    //begin add by sanjing 2012/09/29
    //�����¼����رյ�������
    function doClose()
    {
    	diag.close();
    }
    //end add by sanjing 2012/09/29
    
    //�����ɹ�ʧ����ʾ
    function showM(name, flag)
    {
        index_iframe.showM(name, flag);
    }
    
</script>