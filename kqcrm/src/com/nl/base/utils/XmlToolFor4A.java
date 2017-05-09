package com.nl.base.utils;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.nl.base.components.AbstractComponent;
import com.nl.base.reverseService.JsAppAcctReverseServiceServiceLocator;
import com.nl.base.reverseService.JsAppAcctReverseService_PortType;
import com.nl.base.tokenService.CommonTokenService;
import com.nl.base.tokenService.CommonTokenServiceServiceLocator;
import com.nl.util.AppConfigPropertyUtil;


public class XmlToolFor4A extends AbstractComponent{
	
	public String PostXMLFor4A(String appacctid,String tokenValue) { 
		
		String userId = "";
		
        try {  
        	CommonTokenServiceServiceLocator services = new CommonTokenServiceServiceLocator();
    		services.setCheckAiuapTokenSoapEndpointAddress(getAddress("CheckAiuapTokenSoap_address"));
    		CommonTokenService commonTokenService =  services.getCheckAiuapTokenSoap();
            String xmlInfo = get4AXmlInfo(appacctid,tokenValue);
            getLogger().debug("request>>>>>>>"+xmlInfo);
            
    		String xmlString = commonTokenService.checkAiuapTokenSoap(xmlInfo);
    		getLogger().debug("response>>>>>>"+xmlString);

            userId = analysisResponseInfo(xmlString);
            
            return userId;

        } catch (Exception e) {  
            e.printStackTrace(); 
            return userId;
        }  
    }
	
	private String get4AXmlInfo(String appacctid,String tokenValue) {  
        StringBuilder sb = new StringBuilder();  
        /*sb.append("<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">");  
        sb.append("<SOAP-ENV:Body>");  
        sb.append("<m:CheckAiuapTokenSoap xmlns:m=\"http://service.base.app.core.a4.asiainfo.com\" SOAP-ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">");  
        sb.append("<RequestInfo xsi:type=\"xsd:string\">");  */
        sb.append("<?xml version='1.0' encoding='UTF-8'?>");  
        sb.append("<USERREQ>");  
        sb.append("<HEAD>");  
        sb.append("<CODE></CODE>");  
        sb.append("<SID></SID>");  
        sb.append("<TIMESTAMP>"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"</TIMESTAMP>"); 
        sb.append("<SERVICEID>JSZJUL</SERVICEID>"); 
        sb.append("</HEAD>"); 
        sb.append("<BODY>"); 
        sb.append("<APPACCTID>"+appacctid+"</APPACCTID>"); 
        sb.append("<TOKEN>"+tokenValue+"</TOKEN>"); 
        sb.append("</BODY>"); 
        sb.append("</USERREQ>"); 
        /*sb.append("</RequestInfo>"); 
        sb.append("</m:CheckAiuapTokenSoap>"); 
        sb.append("</SOAP-ENV:Body>"); 
        sb.append("</SOAP-ENV:Envelope>");*/ 
        
        return sb.toString();  
    }  
	
	
	public String analysisResponseInfo(String responseInfo)
    {
        String retValue = "";
        if("".equals(responseInfo))
        {
            return retValue;
        }
        try
        {
            Document doc = DocumentHelper.parseText(responseInfo); // 将字符串转为XML
            Element rootElt = doc.getRootElement();    // 获取根节点
            retValue = rootElt.element("BODY").element("APPACCTID").getTextTrim();
        }
        catch (DocumentException e)
        {
            e.printStackTrace();
            getLogger().debug("解析调用统一运营平台单点登录接口返回的结果信息有异常！");
            getLogger().debug("应答报文："+responseInfo);
            getLogger().debug("获取登录名："+retValue);
        }
        return retValue;
    }
	
	/**
	 * 
	 *@Title: acctReverse 
	 *@Description: TODO
	 *@author CJ
	 *@date Jun 25, 2014 4:06:06 PM 
	 *@param param
	 *@return
	 *@return int
	 */
	public Map<String,String> acctReverse(HashMap param)
	{
		Map<String,String> retValue = new HashMap<String,String>();
		
		try {
			if("".equals(param.get("modifyMode"))||param==null)
			{	
				retValue.put("rsp", "-1");
	            retValue.put("errdesc", "modifyMode is null");
	            	            
			}else if("ADD".equals(param.get("modifyMode").toString().toUpperCase())){
				JsAppAcctReverseServiceServiceLocator services = new JsAppAcctReverseServiceServiceLocator();
				
				services.setJsAppAcctReverseServiceEndpointAddress(getAddress("JsAppAcctReverseService_address"));
				JsAppAcctReverseService_PortType JAARservices = services.getJsAppAcctReverseService();
				
				//System.out.println("##########ADD#############"+services.getJsAppAcctReverseServiceAddress());
				String req = getAcctBindingXml(param);
				String resp = JAARservices.appAcctService(req);
				retValue = analysisRespInfo(resp);
				
			}else if("CHANGE".equals(param.get("modifyMode").toString().toUpperCase())){
				JsAppAcctReverseServiceServiceLocator services = new JsAppAcctReverseServiceServiceLocator();
				
				services.setJsAppAcctReverseServiceEndpointAddress(getAddress("JsAppAcctReverseService_address"));
				JsAppAcctReverseService_PortType JAARservices = services.getJsAppAcctReverseService();
				
				//System.out.println("##########CHANGE#############"+services.getJsAppAcctReverseServiceAddress());
				String req = getAcctBindingXml(param);
				String resp = JAARservices.updateOAAcctServices(req);
				retValue = analysisRespInfo(resp);
				
			}else if("DELETE".equals(param.get("modifyMode").toString().toUpperCase())){
				JsAppAcctReverseServiceServiceLocator services = new JsAppAcctReverseServiceServiceLocator();
				
				services.setJsAppAcctReverseServiceEndpointAddress(getAddress("JsAppAcctReverseService_address"));
				JsAppAcctReverseService_PortType JAARservices = services.getJsAppAcctReverseService();
				
				//System.out.println("##########DELETE#############"+services.getJsAppAcctReverseServiceAddress());
				String req = getAcctDelXml(param);
				String resp = JAARservices.updateOAAcctServices(req);
				retValue = analysisRespInfo(resp);
			}
			
			
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
		return retValue;
	}
	
	private String getAcctBindingXml(HashMap param) {  
        StringBuilder sb = new StringBuilder();  
        sb.append("<?xml version='1.0' encoding='UTF-8'?>");  
        sb.append("<USERMODIFYREQ>");  
        sb.append("<HEAD>");  
        sb.append("<CODE></CODE>");  
        sb.append("<SID></SID>");  
        sb.append("<TIMESTAMP>"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"</TIMESTAMP>"); 
        sb.append("<SERVICEID>JSZJUL</SERVICEID>"); 
        sb.append("</HEAD>"); 
        sb.append("<BODY>"); 
        sb.append("<OPERATORID>"+param.get("operatorId").toString()+"</OPERATORID>"); 
        sb.append("<OPERATORPWD>"+param.get("operatorPswd").toString()+"</OPERATORPWD>"); 
        sb.append("<MODIFYMODE>"+param.get("modifyMode").toString().toLowerCase()+"</MODIFYMODE>");
        sb.append("<USERINFO>"); 
        sb.append("<USERID>"+param.get("sno").toString()+"</USERID>"); 
        sb.append("<LOGINNO>"+param.get("userId").toString()+"</LOGINNO>"); 
        sb.append("<USERNAME>"+param.get("userName").toString()+"</USERNAME>"); 
        sb.append("<ORGID>"+param.get("orgId").toString()+"</ORGID>"); 
        sb.append("<EMAIL>"+param.get("email")+"</EMAIL>"); 
        sb.append("<MOBILE>"+param.get("msisdn").toString()+"</MOBILE>"); 
        sb.append("<PASSWORD>"+param.get("passWord").toString()+"</PASSWORD>"); 
        sb.append("<STATUS>1</STATUS>"); 
        sb.append("<EFFECTDATE>"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"</EFFECTDATE>"); 
        sb.append("<EXPIREDATE>"+new SimpleDateFormat("2099MMddHHmmss").format(new Date())+"</EXPIREDATE>"); 
        sb.append("<REMARK>"+param.get("modifyMode").toString().toUpperCase()+"</REMARK>"); 
        sb.append("<STAFFID></STAFFID>"); 
        sb.append("<MAINLOGINACCT>"+param.get("account4A").toString()+"</MAINLOGINACCT>"); 
        sb.append("</USERINFO>"); 
        sb.append("</BODY>"); 
        sb.append("</USERMODIFYREQ>"); 
        
        return sb.toString();  
    } 
	
	
	private String getAcctDelXml(HashMap param) {  
        StringBuilder sb = new StringBuilder();  
        sb.append("<?xml version='1.0' encoding='UTF-8'?>");  
        sb.append("<USERMODIFYREQ>");  
        sb.append("<HEAD>");  
        sb.append("<CODE></CODE>");  
        sb.append("<SID></SID>");  
        sb.append("<TIMESTAMP>"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"</TIMESTAMP>"); 
        sb.append("<SERVICEID>JSZJUL</SERVICEID>"); 
        sb.append("</HEAD>"); 
        sb.append("<BODY>"); 
        sb.append("<OPERATORID>"+param.get("operatorId").toString()+"</OPERATORID>"); 
        sb.append("<OPERATORPWD>"+param.get("operatorPswd").toString()+"</OPERATORPWD>"); 
        sb.append("<MODIFYMODE>"+param.get("modifyMode").toString().toLowerCase()+"</MODIFYMODE>"); 
        sb.append("<USERINFO>"); 
        sb.append("<USERID>"+param.get("userId").toString()+"</USERID>");       
        sb.append("<MAINLOGINACCT>"+param.get("account4A").toString()+"</MAINLOGINACCT>"); 
        sb.append("</USERINFO>"); 
        sb.append("</BODY>"); 
        sb.append("</USERMODIFYREQ>"); 
        
        return sb.toString();  
    }
	
	public Map<String,String> analysisRespInfo(String responseInfo)
    {
		Map<String,String> retValue = new HashMap<String,String>();
        if("".equals(responseInfo))
        {
            return retValue;
        }
        try
        {
            Document doc = DocumentHelper.parseText(responseInfo); // 将字符串转为XML
            Element rootElt = doc.getRootElement();    // 获取根节点
            retValue.put("rsp", rootElt.element("BODY").element("RSP").getTextTrim());
            retValue.put("errdesc", rootElt.element("BODY").element("ERRDESC").getTextTrim());
        }
        catch (DocumentException e)
        {
            e.printStackTrace();
            getLogger().debug("解析调用统一运营平台单点登录接口返回的结果信息有异常！");
            getLogger().debug("应答报文："+responseInfo);
            getLogger().debug("获取登录名："+retValue);
        }
        return retValue;
    }
	
	private static String getAddress(String param)throws Exception{
		String address = AppConfigPropertyUtil.getMsg("struts-config_res",param);

		if (address == null || "".equals(address)) {
			return "";
		}

		return address;
	}
	
}
