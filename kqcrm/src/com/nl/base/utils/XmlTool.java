package com.nl.base.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


import com.nl.base.HttpURLConnHelper;
import com.nl.util.AppConfigPropertyUtil;



public class XmlTool {
	
	public static String PostXML(String tokenValue) { 
		
		String operator_subsystem = "";
		
        try {  
        	String ssoIp = getSSOIP("sso_ip");
        	//System.out.println("SSO_Ip:"+ssoIp);
        	if("".equals(ssoIp)||ssoIp == null)
        	{
        		return "";
        	}        	
        	String serverUrl = "http://"+ssoIp+"/main/sso/validate.do";  //请求URL   
            String requestStr = getXmlInfo(tokenValue); //请求报文
            String responseStr = HttpURLConnHelper.execute(serverUrl, requestStr);//获取应答报文
            operator_subsystem = analysisResponseInfo(responseStr);  
            
            return operator_subsystem;

        } catch (Exception e) {  
            e.printStackTrace(); 
            return operator_subsystem;
        }  
    }
	
	private static String getXmlInfo(String tokenValue) {  
        StringBuilder sb = new StringBuilder();  
        sb.append("<?xml version='1.0' encoding='UTF-8'?>");  
        sb.append("<request>");  
        sb.append("<token>"+tokenValue+"</token>");  
        sb.append("<system_id>10042</system_id>");  
        sb.append("</request>");  
          
        return sb.toString();  
    }  
	
	private static String getSSOIP(String param)throws Exception{
		String ssoIp = AppConfigPropertyUtil.getMsg("struts-config_res",param);

		if (ssoIp == null || "".equals(ssoIp)) {
			return "";
		}

		return ssoIp;
	}
	
	private static String analysisResponseInfo(String responseStr)
    {
        String retValue = "";
        if("".equals(responseStr))
        {
            return retValue;
        }
        try
        {
            Document doc = DocumentHelper.parseText(responseStr); // 将字符串转为XML
            Element rootElt = doc.getRootElement();    // 获取根节点
            retValue = rootElt.element("operator_subsystem").getTextTrim();
            //System.out.println("status>>>>>>"+rootElt.element("status").getTextTrim());
            //System.out.println("operator_subsystem>>>>>>"+retValue);
        }
        catch (DocumentException e)
        {
            e.printStackTrace();
        }
        return retValue;
    }
	
}
