package com.nl.base.bssp;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.nl.base.components.AbstractComponent;
import com.nl.portal.dt.AdmUserFc;
import com.nl.util.AppConfigPropertyUtil;


/**
 * BSSP�����Ļ���
 * <p>Title: BSSP�����Ļ���</p>
 * <p>Description: ʵ�ֶ�BSSP�Ĳ������������󣬲��Եȷ���</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: NL</p>
 * @author not attributable
 * @version 1.0
 */ 
public class BsspBaseMgr extends AbstractComponent
{
    
    private static Logger logger = Logger.getLogger(BsspBaseMgr.class);
    
    /**������Ϣ
     *
     */
    protected AdmUserFc m_dtUserInfo = null;   //�û���Ϣ
    

    /**
     * BSSP��ַ
     */
    private String m_strUrl = "";              //BSSP��ַ
    //"http://10.4.74.152/fcgi-bin/UIG_SFC"; http://10.6.90.241/fcgi-bin/UIG_SFC
    public static int ERROR_BSSP_TEST_URL = -1001;
    public static int ERROR_BSSP_POST     = -1002;
    public static int ERROR_BSSP_CONNECT  = -1003;
    public static int ERROR_GET_ELEMENT   = -1004;

    
    /**
     * ����BsspBaseMgr
     */
    public BsspBaseMgr()
    {
    	try	{
    		setUrl(AppConfigPropertyUtil.getMsg("struts-config_res","kd_webservice_addr"));
    	}
    	catch(Exception e)	{
    		e.printStackTrace();
    	}
    }

    /**
     * ����BsspBaseMgr
     * @param strUrl String BSSP��ַ
     */
    public BsspBaseMgr(String strUrl)
    {
        setUrl(strUrl);
    }

    /**
     * ����BsspBaseMgr
     * @param dtUserInfo UserInfo_DT �û���Ϣ
     * @param dtMacInfo MacInfo_DT �ն���Ϣ
     */
    public BsspBaseMgr(AdmUserFc dtUserInfo) 
    {
        m_dtUserInfo = dtUserInfo;
        
        try{
        	setUrl(AppConfigPropertyUtil.getMsg("struts-config_res","kd_webservice_addr"));
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
    }

    /**
     * ����BsspBaseMgr
     * @param dtUserInfo UserInfo_DT �û���Ϣ
     * @param dtMacInfo MacInfo_DT �ն���Ϣ
     * @param strUrl String BSSP��ַ
     */
    public BsspBaseMgr(AdmUserFc dtUserInfo, String strUrl)
    {
        m_dtUserInfo = dtUserInfo;
        setUrl(strUrl);
    }

  /**
   * HTTP POST����������
   */
  public static final String POST_METHOD="POST";

  /**
   * HTTP GET����������
   */
  public static final String GET_METHOD="GET";

  /**
   * ���ջ�������С
   */
  public static int MAX_REC_BUFFER=1024;

  /**
   * ���ӳ�ʱʱ�䣬Ĭ��1��
   */
  private int iTimeout = 1000;

  public void setUrl(String strUrl)
  {
      this.m_strUrl = new String();
      this.m_strUrl = strUrl;
  }

  /**
   * ����WEB��������ַ�Ƿ�Ϸ�,iTimeout>0�����web���������������Ƿ���Ч��
   * @param sUrl String
   * @param iTimeout int
   * @return URL ����null��ʾ��Ч����null��ʾ��Ч
   */
  public URL testWebServer(String sUrl,int iTimeout)
  {
      try
      {
          URL url=new URL(sUrl);
          if(iTimeout>0)
          {
              try
              {
            	  HttpURLConnection urlCon = (HttpURLConnection)url.openConnection();
            	  System.setProperty("sun.net.client.defaultConnectTimeout", String.valueOf(iTimeout));
            	  System.setProperty("sun.net.client.defaultReadTimeout", String.valueOf(iTimeout));
            	  urlCon.disconnect();
              }
              catch (UnknownHostException ue)
              {
                  logger.info("UnknownHostException"+ue.getMessage());
                  url = null;
              }
              catch (IOException ioe)
              {
                  logger.info("IOException"+ioe.getMessage());
                  url = null;
              }
              catch (SecurityException se)
              {
                  logger.info("SecurityException"+se.getMessage());
                  url = null;
              }
              catch (NullPointerException ne)
              {
                  logger.info("NullPointerException"+ne.getMessage());
                  url = null;
              }
          }
          return url;
      }
      catch (Exception ex)
      {
          logger.info("Exception"+ex.getMessage());
          return null;
      }
  }

  /**
   * ���Է������Ƿ���Ч
   * @param sUrl String
   * @return boolean
   */
  public boolean testWebServer(String sUrl)
  {
	  return (testWebServer(sUrl, iTimeout) == null) ? false : true;
  }


  /**
   * �������ӳ�ʱʱ�䣬��ʱ���޷�ʹ���ϣ�Ԥ���ӿ�
   * @param iTimeout int
   */
  public void setConnectTimeout(int iTimeout)
  {
      this.iTimeout=iTimeout;
  }


  /**
   * �ϲ��������,���ɷ���http���������
   * @param aInputName  Ҫ�ύ��web server�Ĳ���������
   * @param aInputValue Ҫ�ύ��web server�Ĳ���ֵ����
   * @return ����http���������
   */
  public static byte[] encodeRequest(String strXml)
  {
    try {
        return URLEncoder.encode(strXml,"GBK").getBytes();
    }
    catch (Exception ex) {
      return null;
    }
  }

  /**
   * ��WEB�������ύ����
   * @param aRequestContent byte[] �ύ���������
   * @param aRet int[] ���صĴ������
   * @param sbError StringBuffer ��������
   * @return byte[]
   */
  public byte[] doAction(byte[] aRequestContent,StringBuffer[] aRet,StringBuffer sbError)
  {
      OutputStream out = null;
      InputStream ins = null;
      URL url = null;
      URLConnection urlcon = null;

      logger.info("do action m_strUrl="+m_strUrl);
      try
      {
          url = testWebServer(this.m_strUrl, 1000); //����WEB��ַ�Ƿ���Ч
          logger.info("this.m_strUrl="+this.m_strUrl);
          if (url == null)
          {
                if (sbError != null)
                      sbError.append("URL��ַ[" + this.m_strUrl + "]��Ч");
                aRet[0] =new StringBuffer(String.valueOf(ERROR_BSSP_TEST_URL));
                return null;
          }
          urlcon = url.openConnection(); //����WEB SERVER
          urlcon.setRequestProperty("method", POST_METHOD); //�����ύ��ʽ
          urlcon.setRequestProperty("Connection","close");
          urlcon.setDoInput(true);
      }
      catch (Exception ex)
      {
          logger.info(ex.getMessage());
          if (sbError != null)
              sbError.append(ex.toString());
          aRet[0] = new StringBuffer(String.valueOf(ERROR_BSSP_CONNECT));
		  try {
			   if (out != null) out.close();
		  } catch (IOException e) 
		  {}
          try  {
              if (ins != null)  ins.close();
          }
          catch (Exception ex2)
          {}
          return null;
      }
      try
      {
          if (aRequestContent != null && aRequestContent.length > 0)
          {
              urlcon.setDoOutput(true);
              out = urlcon.getOutputStream();
              out.write(aRequestContent);
              out.flush();
          }
          out.close();
          out = null;
          //----------------------------��ȡ��������������----------------------------
          ins = urlcon.getInputStream();
          byte[] aResult = null;
          byte[] bContent = new byte[MAX_REC_BUFFER];
          while (true)
          {
              int iReadNum = ins.read(bContent);
              if (iReadNum == -1)   break;
              if (aResult == null)
              {
                  aResult = new byte[iReadNum];
                  System.arraycopy(bContent, 0, aResult, 0, iReadNum);
              }
              else{
                  byte[] aTmp = new byte[aResult.length + iReadNum];
                  System.arraycopy(aResult, 0, aTmp, 0, aResult.length);
                  System.arraycopy(bContent, 0, aTmp, aResult.length,iReadNum);
                  aResult = aTmp;
              }
          }
          ins.close();
          ins = null;
          return aResult;
      }
      catch (Exception ex)
      {
          logger.info(ex.getMessage());
          try {
			   if (out != null) out.close();
		  } catch (IOException e) 
		  {  }
         try  {
             if (ins != null)  ins.close();
         }
         catch (Exception ex2)
         {  }
          if (sbError != null) sbError.append(ex.toString());
          aRet[0] = new StringBuffer(String.valueOf(ERROR_BSSP_POST));
          return null;
      }
  }

  /**
   * ��BSSP�������ύ����
   * @param xml BsspXmlMgr ���������(xml��ʽ)
   * @param aRet int[] ���صĴ������
   * @param strErr StringBuffer ��������
   * @return BsspXmlMgr
   */
  public BsspXmlMgr Post(BsspXmlMgr xml, StringBuffer[] aRet, StringBuffer strErr)
  {
	  String sRet="";
      String strProcess = xml.m_processCode;  		//������
      //���Կ�ʼ
      URL url= testWebServer(this.m_strUrl,0);		//����WEB��ַ�Ƿ���Ч
      if(url==null || url.equals(""))  				//URL��ַ��Ч
      {
          aRet[0] =new StringBuffer(String.valueOf(ERROR_BSSP_TEST_URL));
          return null;
      }
      //���Խ���
      StringBuffer sbResult = new StringBuffer();
      logger.info("begin call " + xml.m_processCode);
      logger.info("��������xmlΪ��"+xml.outputXMLStr());
      
      //byte[] aRecv = doAction(encodeRequest(xml.outputXMLStr()),aRet,sbResult);
      String test_xml = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><SOAP-ENV:Body><m:authToken xmlns:m=\"http://webservice.just.com.cn/\"><arg0>MbMvLaU7s4YqDVQiyj36jgz9jXuqdPLJ3GXFFwWfcjes5EVqYzXrrm+d4Y323PY8PI08U+dpE42y%20%20k2H2K1yfcQ+BWWsT0eGb4iG/eF6FCMY=</arg0></m:authToken></SOAP-ENV:Body></SOAP-ENV:Envelope>";
      byte[] aRecv = doAction(encodeRequest(test_xml),aRet,sbResult);

      logger.info("after call " + xml.m_processCode + ", nRet = " + aRet[0]);
      if(aRet[0].toString().equals("0"))  return null;
      //���Կ�ʼ
//      if(aRecv == null)
//      {
//          aRet[0] =  new StringBuffer(String.valueOf(ERROR_BSSP_POST));
//          return null;
//      }
    //���Խ���
      try
      {
    	  //���Կ�ʼ
          //String result = new String(aRecv);
    	  String result = "<?xml version=\"1.0\" encoding=\"utf-8\"?><operation_out><service_name>12312312</service_name><sysfunc_id>312312</sysfunc_id><request_type>312312</request_type><operator_id>null</operator_id><request_time>20160505</request_time><response><resp_type>1</resp_type><resp_code>1</resp_code><resp_desc>�ɹ�</resp_desc></response><content><Result><ResultCode>1</ResultCode><ResultMessage>�û�������½</ResultMessage><userid>123456</userid></Result></content></operation_out>";
          int nValid = xml.checkXmlString(result);

          if(nValid != 0)
          {
              aRet[0] =new StringBuffer(String.valueOf(nValid));
              return null;
          }
          logger.info("���xml��" + result.trim());
          xml = new BsspXmlMgr(result);
          xml.m_processCode = strProcess;

          xml.getElementStringValue(xml.m_Response,"resp_type");
          String str2 = xml.getElementStringValue(xml.m_Response,"resp_code");
          String str3 = xml.getElementStringValue(xml.m_Response,"resp_desc");
         
          sRet=str2.trim();						//�������ŵ�RESP_CODE��
          strErr.append(str3);					//�������ŵ���ʾ��Ϣ       
          if(sRet.equals("1")) 				//�������ŵ�RESP_CODE��,0000��ʾ��ѯ�ɹ�������content������
        	  xml.m_Content = xml.getElement(xml.m_rootElement, "content");
          else
          {
        	  try
        	  {
                logger.info("set content from resp_result");
                xml.m_Content = xml.getElement(xml.m_rootElement, "content");
        	  }
        	  catch(Exception ee)
        	  {}
          }
      }
      catch(Exception e)  {
          sRet = String.valueOf(ERROR_GET_ELEMENT);
      }
      aRet[0] = new StringBuffer(sRet);
      return xml;
  }
}
