package com.nl.portal.sc;



import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.dom4j.Element;
 

import com.nl.base.bssp.BsspBaseMgr;
import com.nl.base.bssp.BsspXmlMgr;
import com.nl.portal.dt.AdmUserFc;
import com.nl.util.AppConfigPropertyUtil;
import com.nl.util.GlobalConst;


/**
 * @Description: 
 * @author CJ
 * @version 1.0
 * Oct 13, 2016
 * -------------------------------------------
 * @History:
 * 修订日期    修订人    版本    描述
 * 
 */
public class SystemXml extends BsspBaseMgr
{
	private static Logger logger = Logger.getLogger(SystemXml.class);
	String bossCodeStr = "";
//	public SystemXml(String bossCodeStr)
//	{
//		//构造方法实现
//		this.bossCodeStr = bossCodeStr;
//	}
	
	public SystemXml(AdmUserFc dtUserInfo)
    {
        super(dtUserInfo);
    }
	
	/**
	 * 
	 * 
	 * @param userid
	 * @param strtoken
	 * @return
	 * @author sanjing
	 * @createdate Oct 21, 2016
	 * @version v1.0
	 */
	public boolean checkUser(StringBuffer userid,String strtoken){
		AdmUserFc dtUserInfo = new AdmUserFc();
		BsspXmlMgr xml = new BsspXmlMgr(dtUserInfo, "checkuser",10000);
	      
	      try
	      {   
	    	  xml.addElement(xml.m_Content, "StrToken", strtoken);
	      }
	      catch(Exception e)
	      {
	    	  getLogger("bosscodestr",GlobalConst.ERROR).error("ERROR_ADD_ELEMENT:"+e.getMessage());
	    	  //com.nl.base.bssp.BsspXmlMgr.ERROR_ADD_ELEMENT;
	          return  false;
	      }
	      
	      logger.info("begin call " + xml.m_processCode);
	      logger.info("传入流程xml为："+xml.outputXMLStr());
	      
//	      StringBuffer strErr = new StringBuffer();
//	      int nRet = 0;
//
//	      StringBuffer[] aRet={new StringBuffer("")};
//	      aRet[0] = new StringBuffer("-1");
	      String reXml = "";
	      try{
				//IceAuth authser = new IceAuthImplServiceSoapBindingStub(new URL("http://218.3.124.236:9080/iceAuth/services/iceAuth"),new IceAuthImplServiceLocator());
//	    	  IceAuth authser = new IceAuthImplServiceSoapBindingStub(new URL(AppConfigPropertyUtil.getMsg("struts-config_res","kd_webservice_addr")),new IceAuthImplServiceLocator());
//				//String reXml = authser.authToken("<?xml version=\"1.0\" encoding=\"utf-8\"?><operation_in><service_name>checkUserLogin</service_name><sysfunc_id>10011909</sysfunc_id><request_type>1001</request_type><operator_id>test111</operator_id><request_time>201610221830</request_time><content><StrToken>j6DZdZH33D9WxpL4Uiv4Kuirjpcjfzy8qSXhxWVzsHrZQn6v1cO/IRsZjlXxLS6xK0qkRWsJTHgy%20%20mowSxcFXuh+IsotlwgBOvc6zeiEvSvE=</StrToken></content></operation_in>");
//	    	  reXml = authser.authToken(xml.outputXMLStr());
//	    	  logger.info("返回xml为："+reXml);
	    	  
	    	  //<?xml version="1.0" encoding="utf-8"?><operation_out><service_name>checkuser</service_name><sysfunc_id>10000</sysfunc_id><request_type>1</request_type><operator_id>null</operator_id><request_time>20163010103057</request_time><response><resp_type>1</resp_type><resp_code>1</resp_code><resp_desc>成功</resp_desc></response><content><Result><ResultCode>1</ResultCode><ResultMessage>用户正常登陆</ResultMessage></Result><userid>1</userid></content></operation_out>
//	    	  reXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><operation_out><service_name>checkuser</service_name><sysfunc_id>10000</sysfunc_id><request_type>1</request_type><operator_id>null</operator_id><request_time>20163010103057</request_time><response><resp_type>1</resp_type><resp_code>1</resp_code><resp_desc>成功</resp_desc></response><content><Result><ResultCode>1</ResultCode><ResultMessage>用户正常登陆</ResultMessage></Result><userid>1</userid></content></operation_out>";
	    	  //System.out.println("reXml="+reXml);
			}catch (Exception e){
				logger.error(e.getMessage());
				
			}
			
	      //BsspXmlMgr ret_xml = this.Post(xml, aRet, strErr);
	      BsspXmlMgr ret_xml = new BsspXmlMgr(reXml);
	      //int nRet = 0;
	      String resp_code = "-1";
	      try
	      {
	    	resp_code = ret_xml.getElementStringValue(ret_xml.m_Response,"resp_code");
	    	
//	        nRet = Integer.parseInt(aRet[0].toString());
	      }
	      catch(Exception e)
	      {
	         //
	      }
	      
	      if("1".equals(resp_code))
	      {
	          try
	          {

	        	  Element element = (Element)ret_xml.m_Content.element("Result");
	        	  Element ele = (Element)ret_xml.m_Content.element("userid");
//	        	  System.out.println(ret_xml.m_Content.getStringValue());
//	        	  System.out.println(element.getStringValue());
//	        	  System.out.println("userid="+ele.getStringValue());
	        	  userid.append(ele.getStringValue());
	              
	          }catch(Exception ee){
	        	  getLogger("bosscodestr",GlobalConst.ERROR).error("校验不通过:"+ee.getMessage());
	             return false;  //此地应该报错！！
	          }
	          return true;
	      }
	      return false;
	}
	/*
	 --科大应答报文
<?xml version="1.0" encoding="GBK"?>
<operation_out>
	<service_name>checkUserLogin</service_name>
	<sysfunc_id>10011909</sysfunc_id>
	<request_type>1001</request_type>
	<operator_id>test111</operator_id>
	<request_time>201610221830</request_time>
	--2016年10月23日增加开始
	<response>
  <resp_type>1</resp_type> 
  <resp_code>1</resp_code> 
  <resp_desc>成功</resp_desc> 
  </response>
  --2016年10月23日增加结束
	<content>
	  <Result>
		  <ResultCode>1</ResultCode> 
		  <ResultMessage>用户正常登陆</ErrorMessage> 
	 	</Result>
	 	<userid>
	 	1234566
	 	</userid>
	</content>
</operation_out>
	 */
	
	public static void main(String args[]){
		StringBuffer userid = new StringBuffer();
		//String strtoken = "MbMvLaU7s4YqDVQiyj36jgz9jXuqdPLJ3GXFFwWfcjes5EVqYzXrrm+d4Y323PY8PI08U+dpE42y%20%20k2H2K1yfcQ+BWWsT0eGb4iG/eF6FCMY=";
		String strtoken = "YKJvGUGaG5yIj7thZn5MfKWNih1fhV6vLsRGbjYcQ3+40sFb6uXFRgW5WXMJsO53RVwtZAZiBBtTlxHRpvDrgpKudGYPvxdaqB1R1QhBu2Q=";
		AdmUserFc dtUserInfo = new AdmUserFc();
		SystemXml xml = new SystemXml(dtUserInfo);
		
		xml.checkUser(userid,strtoken);
		System.out.println(userid.toString());
//		
		//add test by sanjing
//		try{
//			String urlname = "http://218.3.124.236:9080/iceAuth/services/iceAuth?wsdl" ;
//
//	        urlname = "http://218.3.124.236:9080/iceAuth/services/iceAuth";
//
//	        
//
//	        Service s = new  Service();
//
//	        //Call call = (Call) s.createCall();
//	        Call call = (Call)s.createCall();
//	        
//
//	        call.setTimeout(new Integer(5000));
//
//	        call.setOperation( "m:authToken" );
//
//	        call.setTargetEndpointAddress(urlname);
//
//	            
//
//	        Object[] fn01 = { "MbMvLaU7s4YqDVQiyj36jgz9jXuqdPLJ3GXFFwWfcjes5EVqYzXrrm+d4Y323PY8PI08U+dpE42y%20%20k2H2K1yfcQ+BWWsT0eGb4iG/eF6FCMY="};
//	        //String reqXml="<?xml version=\"1.0\" encoding=\"utf-8\"?><operation_in><service_name>checkUserLogin</service_name><sysfunc_id>10011909</sysfunc_id><request_type>1001</request_type><operator_id>test111</operator_id><request_time>201610221830</request_time><content><StrToken>MbMvLaU7s4YqDVQiyj36jgz9jXuqdPLJ3GXFFwWfcjes5EVqYzXrrm+d4Y323PY8PI08U+dpE42y%20%20k2H2K1yfcQ+BWWsT0eGb4iG/eF6FCMY=</StrToken></content></operation_in>";
//
//	        String val = (String)call.invoke(fn01);
//	        //String val = (String)call.invoke(reqXml);
//
//	        System.out .println( "authToken(correct):"  + val);
			
//		}catch(Exception e){
//			
//			
//		}
		
		//add test by sanjing 20161107
//		try{
//			IceAuth authser = new IceAuthImplServiceSoapBindingStub(new URL("http://218.3.124.236:9080/iceAuth/services/iceAuth"),new IceAuthImplServiceLocator());
//			String reXml = authser.authToken("<?xml version=\"1.0\" encoding=\"utf-8\"?><operation_in><service_name>checkUserLogin</service_name><sysfunc_id>10011909</sysfunc_id><request_type>1001</request_type><operator_id>test111</operator_id><request_time>201610221830</request_time><content><StrToken>j6DZdZH33D9WxpL4Uiv4Kuirjpcjfzy8qSXhxWVzsHrZQn6v1cO/IRsZjlXxLS6xK0qkRWsJTHgy%20%20mowSxcFXuh+IsotlwgBOvc6zeiEvSvE=</StrToken></content></operation_in>");
//			System.out.println("reXml="+reXml);
//		}catch (Exception e){
//			
//			
//		}
		

		
//		URL url = new URL("http://218.3.124.236:9080/iceAuth/services/iceAuth");   
//		IceAuthImplServiceSoapBindingStub stub = new IceAuthImplServiceSoapBindingStub(url,new Service());   
//		IceAuth acct = new IceAuth();   
//		acct.setName("username");   
//		acct.setPassword("password");   
//		boolean result = stub.checkAccount(acct);   
//		System.out.println(result);   
		
		
//		String soapaction = "http://webservice.just.com.cn";
//        //你的webservice地址
//        String endpoint = "http://218.3.124.236:9080/iceAuth/services/iceAuth";
//        Service service = new Service();
//        try {
//            Call call = (Call) service.createCall();
//            call.setTimeout(new Integer(60000));
//            call.setTargetEndpointAddress(new URL(endpoint));
//            //你需要远程调用的方法
//            call.setOperationName(new QName(soapaction,"authToken"));
//            //方法参数，如果没有参数请无视
//            String strToken = "MbMvLaU7s4YqDVQiyj36jgz9jXuqdPLJ3GXFFwWfcjes5EVqYzXrrm+d4Y323PY8PI08U+dpE42y%20%20k2H2K1yfcQ+BWWsT0eGb4iG/eF6FCMY=";
//            call.addParameter(new QName(soapaction,strToken), XMLType.XSD_STRING, ParameterMode.IN);
//            //call.addParameter(new QName(soapaction,"xxxx"), XMLType.XSD_STRING, ParameterMode.IN);
//            //设置返回类型，对方接口返回的json，我就用string接收了,自定义类型另贴一个代码
//            call.setReturnType(XMLType.XSD_STRING);
//            //调用方法并传递参数，没有参数的话： call.invoke(new Object[] { null});
//            String result = (String) call.invoke(new Object[]{"xxxxx","xx,xx"});
//            System.out.println(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//		Date a = new Date();
//		SimpleDateFormat aa  =  new SimpleDateFormat("yyyymmddHHmmss");
//		aa.format(a);
//		System.out.println(new SimpleDateFormat("yyyymmddHHmmss").format(new Date()).toString());

		
		
	}
	

}
