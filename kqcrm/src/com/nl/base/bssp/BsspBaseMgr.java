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
 * BSSP操作的基类
 * <p>Title: BSSP操作的基类</p>
 * <p>Description: 实现对BSSP的操作，包括请求，测试等方法</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: NL</p>
 * @author not attributable
 * @version 1.0
 */ 
public class BsspBaseMgr extends AbstractComponent
{
    
    private static Logger logger = Logger.getLogger(BsspBaseMgr.class);
    
    /**操作信息
     *
     */
    protected AdmUserFc m_dtUserInfo = null;   //用户信息
    

    /**
     * BSSP地址
     */
    private String m_strUrl = "";              //BSSP地址
    //"http://10.4.74.152/fcgi-bin/UIG_SFC"; http://10.6.90.241/fcgi-bin/UIG_SFC
    public static int ERROR_BSSP_TEST_URL = -1001;
    public static int ERROR_BSSP_POST     = -1002;
    public static int ERROR_BSSP_CONNECT  = -1003;
    public static int ERROR_GET_ELEMENT   = -1004;

    
    /**
     * 构造BsspBaseMgr
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
     * 构造BsspBaseMgr
     * @param strUrl String BSSP地址
     */
    public BsspBaseMgr(String strUrl)
    {
        setUrl(strUrl);
    }

    /**
     * 构造BsspBaseMgr
     * @param dtUserInfo UserInfo_DT 用户信息
     * @param dtMacInfo MacInfo_DT 终端信息
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
     * 构造BsspBaseMgr
     * @param dtUserInfo UserInfo_DT 用户信息
     * @param dtMacInfo MacInfo_DT 终端信息
     * @param strUrl String BSSP地址
     */
    public BsspBaseMgr(AdmUserFc dtUserInfo, String strUrl)
    {
        m_dtUserInfo = dtUserInfo;
        setUrl(strUrl);
    }

  /**
   * HTTP POST方法常量名
   */
  public static final String POST_METHOD="POST";

  /**
   * HTTP GET方法常量名
   */
  public static final String GET_METHOD="GET";

  /**
   * 接收缓冲区大小
   */
  public static int MAX_REC_BUFFER=1024;

  /**
   * 连接超时时间，默认1秒
   */
  private int iTimeout = 1000;

  public void setUrl(String strUrl)
  {
      this.m_strUrl = new String();
      this.m_strUrl = strUrl;
  }

  /**
   * 测试WEB服务器地址是否合法,iTimeout>0则测试web服务器网络连接是否有效。
   * @param sUrl String
   * @param iTimeout int
   * @return URL 返回null表示无效，非null表示有效
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
   * 测试服务器是否还有效
   * @param sUrl String
   * @return boolean
   */
  public boolean testWebServer(String sUrl)
  {
	  return (testWebServer(sUrl, iTimeout) == null) ? false : true;
  }


  /**
   * 设置连接超时时间，暂时还无法使用上，预留接口
   * @param iTimeout int
   */
  public void setConnectTimeout(int iTimeout)
  {
      this.iTimeout=iTimeout;
  }


  /**
   * 合并输入参数,生成符合http的请求参数
   * @param aInputName  要提交到web server的参数名数组
   * @param aInputValue 要提交到web server的参数值数组
   * @return 符合http的请求参数
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
   * 向WEB服务器提交请求
   * @param aRequestContent byte[] 提交请求的内容
   * @param aRet int[] 返回的错误代码
   * @param sbError StringBuffer 错误内容
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
          url = testWebServer(this.m_strUrl, 1000); //分析WEB地址是否有效
          logger.info("this.m_strUrl="+this.m_strUrl);
          if (url == null)
          {
                if (sbError != null)
                      sbError.append("URL地址[" + this.m_strUrl + "]无效");
                aRet[0] =new StringBuffer(String.valueOf(ERROR_BSSP_TEST_URL));
                return null;
          }
          urlcon = url.openConnection(); //连接WEB SERVER
          urlcon.setRequestProperty("method", POST_METHOD); //设置提交方式
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
          //----------------------------读取服务器返回内容----------------------------
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
   * 向BSSP服务器提交请求
   * @param xml BsspXmlMgr 请求的内容(xml形式)
   * @param aRet int[] 返回的错误代码
   * @param strErr StringBuffer 错误内容
   * @return BsspXmlMgr
   */
  public BsspXmlMgr Post(BsspXmlMgr xml, StringBuffer[] aRet, StringBuffer strErr)
  {
	  String sRet="";
      String strProcess = xml.m_processCode;  		//流程名
      //测试开始
      URL url= testWebServer(this.m_strUrl,0);		//分析WEB地址是否有效
      if(url==null || url.equals(""))  				//URL地址无效
      {
          aRet[0] =new StringBuffer(String.valueOf(ERROR_BSSP_TEST_URL));
          return null;
      }
      //测试结束
      StringBuffer sbResult = new StringBuffer();
      logger.info("begin call " + xml.m_processCode);
      logger.info("传入流程xml为："+xml.outputXMLStr());
      
      //byte[] aRecv = doAction(encodeRequest(xml.outputXMLStr()),aRet,sbResult);
      String test_xml = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><SOAP-ENV:Body><m:authToken xmlns:m=\"http://webservice.just.com.cn/\"><arg0>MbMvLaU7s4YqDVQiyj36jgz9jXuqdPLJ3GXFFwWfcjes5EVqYzXrrm+d4Y323PY8PI08U+dpE42y%20%20k2H2K1yfcQ+BWWsT0eGb4iG/eF6FCMY=</arg0></m:authToken></SOAP-ENV:Body></SOAP-ENV:Envelope>";
      byte[] aRecv = doAction(encodeRequest(test_xml),aRet,sbResult);

      logger.info("after call " + xml.m_processCode + ", nRet = " + aRet[0]);
      if(aRet[0].toString().equals("0"))  return null;
      //测试开始
//      if(aRecv == null)
//      {
//          aRet[0] =  new StringBuffer(String.valueOf(ERROR_BSSP_POST));
//          return null;
//      }
    //测试结束
      try
      {
    	  //测试开始
          //String result = new String(aRecv);
    	  String result = "<?xml version=\"1.0\" encoding=\"utf-8\"?><operation_out><service_name>12312312</service_name><sysfunc_id>312312</sysfunc_id><request_type>312312</request_type><operator_id>null</operator_id><request_time>20160505</request_time><response><resp_type>1</resp_type><resp_code>1</resp_code><resp_desc>成功</resp_desc></response><content><Result><ResultCode>1</ResultCode><ResultMessage>用户正常登陆</ResultMessage><userid>123456</userid></Result></content></operation_out>";
          int nValid = xml.checkXmlString(result);

          if(nValid != 0)
          {
              aRet[0] =new StringBuffer(String.valueOf(nValid));
              return null;
          }
          logger.info("结果xml：" + result.trim());
          xml = new BsspXmlMgr(result);
          xml.m_processCode = strProcess;

          xml.getElementStringValue(xml.m_Response,"resp_type");
          String str2 = xml.getElementStringValue(xml.m_Response,"resp_code");
          String str3 = xml.getElementStringValue(xml.m_Response,"resp_desc");
         
          sRet=str2.trim();						//返回亚信的RESP_CODE码
          strErr.append(str3);					//返回亚信的提示信息       
          if(sRet.equals("1")) 				//返回亚信的RESP_CODE码,0000表示查询成功，解析content的内容
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
