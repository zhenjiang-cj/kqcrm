<%@ page language="java"  pageEncoding="GBK"%>
<%@page import="com.nl.util.GlobalConst"%>
<%@page import="com.nl.portal.actionForm.OperatorForm"%>

<%
	String path = request.getContextPath();
	
	OperatorForm operatorForm = (OperatorForm)request.getAttribute(GlobalConst.GLOBAL_CURRENT_FORM);
	
	String param = "&userId=" + operatorForm.getUserId()
                 + "&userName=" + operatorForm.getUserName();
	
	String name="";	

	if(operatorForm.getFunctionId().equals(GlobalConst.FUNCTION_OPERATOR_UPDATE))//�޸Ĳ���Ա
	{    
		name="�޸Ĳ���Ա����";
	}
	else if(operatorForm.getFunctionId().equals(GlobalConst.FUNCTION_OPERATOR_ROLE_UPDATE))//�޸Ĳ���Ա��ɫ
	{    
		name="�޸Ĳ���Ա��ɫ";
	}
	else if(operatorForm.getFunctionId().equals(GlobalConst.FUNCTION_OPERATOR_PASSWORD_RESET))//������Ա��ɫ
	{    
		name="���ò���Ա����";
	}

	else if(operatorForm.getFunctionId().equals(GlobalConst.FUNCTION_OPERATOR_ADD))//��������
	{    
		name="��������";
	}

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
  </head>
  <body>
 
  </body>
<script type="text/javascript" src="<%=path%>/plugins/jquery/jquery-1.4.4.js"></script>
  <script type="text/javascript">
   	//���غ�ִ��
	jQuery(document).ready(function(){
		<%
		if(operatorForm.getFlag()==0){
		%>
			parent.parent.showM("<%=name%>�ɹ�!",'1');
  		<%
  		}else{
  			param = "";
		%>
			parent.parent.showM("<%=name%>ʧ��!",'2');
		<%
		}
		%>

  		<%
  		if(operatorForm.getFunctionId().equals(GlobalConst.FUNCTION_OPERATOR_UPDATE)){   //����Ա�����޸�
  		%>
  			 window.location.href="./operator.do?method=queryList&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_ROLE_MANAGE%>" + "<%=param%>";
  		<%
  		}else if(operatorForm.getFunctionId().equals(GlobalConst.FUNCTION_OPERATOR_ROLE_UPDATE))//�޸�
  		{
  		%>
	  		window.location.href="./operator.do?method=queryList&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_ROLE_MANAGE%>" + "<%=param%>";
  		<%
  		}else if(operatorForm.getFunctionId().equals(GlobalConst.FUNCTION_OPERATOR_PASSWORD_RESET))//��������
  		{
  		%>
	  		window.location.href="./operator.do?method=queryList&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_ROLE_MANAGE%>" + "<%=param%>";
  		
  		<%
		}else if(operatorForm.getFunctionId().equals(GlobalConst.FUNCTION_OPERATOR_ADD)){//��������
  		%>
	  		window.location.href="./operator.do?method=queryList&systemId=<%=GlobalConst.SYSTEM_ID_SYSTEM%>&functionId=<%=GlobalConst.FUNCTION_ROLE_MANAGE%>" ;
  		<%
		}
  		%>

	  		
  			
	});
  </script>
</html>
