<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<%@page import="com.nl.ql.util.AppConst"%>
<%@page import="com.nl.util.GlobalUtil"%>

<!--����headҳ��-->


  <!--head--��ʼ-->
	



  <!--head--��ʼ-->
  <div class="head_all">
    <div class="head">
      <div class="head_main">
        <table border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td rowspan="2" valign="middle"><img name="index_r1_c2" src="images/htzj/index_r1_c2.png" width="255" height="82" border="0" id="index_r1_c2" alt="" /></td>
            <td width="330">&nbsp;</td>
            <td align="center"><p><a href="<%=GlobalUtil.getPropertiesText("struts-config_res","htzj_uri") %>/htzj/login.jsp"><img name="index_r1_c20" src="images/htzj/index_r1_c20.png" width="84" height="24" border="0" id="index_r1_c20" alt="" /></a></p>
            </td>
            <td nowrap><img src="images/htzj/index_r1_c22.png" alt="" name="index_r1_c22" width="30" height="26" border="0" align="absbottom" id="index_r1_c22" /> <b><%=AppConst.SYS_NAME %></b> </td>
          </tr>
          <tr>
            <td align="center" valign="middle">
            <b style="font-size:14px"> 
            <script language="javascript" type="text/javascript">
            	RunGLNL();
            </script>
            </b>
            </td>
            <td colspan="2" align="right"><img name="index_r3_c20" src="images/htzj/index_r3_c20.png" width="345" height="50" border="0" id="index_r3_c20" alt="" /></td>
          </tr>
        </table>
      </div>
    </div>
    <div style="background-color:#E1E1E1; height:4px;"></div>
  </div>
  <!--head--����-->
  <!--head--����-->
  
  <!--nav--��ʼ-->
  <div class="nav">
    <div class="nav_main">
      <div class="nav_sub" id="index" onClick="location='<%=GlobalUtil.getPropertiesText("struts-config_res","htzj_uri") %>/index.jsp';">�� ҳ</div>
      <!-- <div class="nav_sub" id="qlsx" onClick="location='#';">Ȩ������</div> -->
      <div class="nav_sub" id="wkk" onClick="location='<%=GlobalUtil.getPropertiesText("struts-config_res","htzj_uri") %>/htzj/publish/queryXwdtList.do';">�ҿ���</div>
      <div class="nav_sub" id="wxw" onClick="location='<%=GlobalUtil.getPropertiesText("struts-config_res","htzj_uri") %>/htzj/publish/wxw.jsp?nav=wxw';">��ѯ��</div>
      <div class="nav_sub" id="wcx" onClick="location='<%=GlobalUtil.getPropertiesText("struts-config_res","htzj_uri") %>/htzj/publish/queryZcfgList.do';">�Ҳ�ѯ</div>
      <div class="nav_sub" id="wbs" onClick="location='<%=GlobalUtil.getPropertiesText("struts-config_res","htzj_uri") %>/htzj/publish/wbs_bmfw_zfbz.jsp?nav=wbs';">�Ұ���</div>
      <div class="nav_sub" id="wbg" onClick="location='<%=GlobalUtil.getPropertiesText("struts-config_res","htzj_uri") %>/htzj/publish/wbg.jsp?nav=wbg';">�Ұ칫</div>
    </div>
  </div>
  <script type="text/javascript">
  	if($("#qlsx")){
  		$("#qlsx").addClass("nav_sub_fouce");
  	}
  </script>
  <!--nav--����-->

<!--����headҳ��-->
