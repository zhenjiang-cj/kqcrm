package com.nl.base.bssp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.nl.base.components.AbstractComponent;
import com.nl.portal.dt.AdmUserFc;

/** BSSP信息类
 * <p>Title: BSSP信息类</p>
 * <p>Description: 以XML的形式生成BSSP信息</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: NEWLAND</p>
 * @author not attributable
 * @version 1.0
 */

public class BsspXmlMgr extends AbstractComponent
{

    private static Logger logger = Logger.getLogger(BsspXmlMgr.class);
    
    /**
     * 树
     */
    public Document m_Document;

    /**
     * 根元素
     */
    public Element m_rootElement;

    /**
     * 编码方式
     */
    public String m_sEncoding;

    /**
     * 业务元素区
     */
    public Element m_Content;

    /**
     * 业务元素区
     */
    public Element m_Response;

    /**
     * 流程名
     */
    public String m_processCode;


    public static int ERROR_BSSP_XML_INVALID = -1001;
    public static int ERROR_ADD_ELEMENT      = -1002;


    /**
     * 根据操作信息和流程代码构造BsspXmlMgr对象
     * @param operationInfo OperatingInfo_DT 操作信息
     * @param processCode String  流程代码
     */
    public BsspXmlMgr(AdmUserFc dtUserInfo, String processCode, int nFuncId)
    {
    	
    	m_sEncoding = "utf-8"; //"GBK";
    	m_Document = DocumentHelper.createDocument();
    	m_rootElement = m_Document.addElement("operation_in");
        m_processCode = processCode;

        try{
        	addElement(m_rootElement, "service_name", processCode); 			//1	string	V30	CGI服务名	参看接口定义中的服务名
        	addElement(m_rootElement, "request_type", "1");						//	1	int	V4	请求类型	参看“请求类型”
        	addElement(m_rootElement, "sysfunc_id", nFuncId); 					//1   int	V8	功能代码
        	if(dtUserInfo.getAuID()!=null)
        		addElement(m_rootElement, "operator_id", dtUserInfo.getAuID()); 	//?	string	V30	操作员工号
        	else{
        		addElement(m_rootElement, "operator_id", "null");
        	}
        	addElement(m_rootElement, "request_time", new SimpleDateFormat("yyyymmddHHmmss").format(new Date()).toString());						// 1	datetime	F14	请求时间

        	Element tmp = addElement(m_rootElement, "content");
        	m_Content = tmp;
        }
        catch(Exception e){}
    }



    /**
     * 根据字符串构造document（解析xml使用）
     * @param strXml
     */
    public BsspXmlMgr(String strXml)
    {
    	/* jdom的代码,rem by Lampard Lee,May 14th,2008 */
    	/*
        strXml = transGbk(strXml);
        SAXBuilder sb = null;

        try
        {
            sb = new SAXBuilder();
            byte[] tmp = new String(strXml).getBytes();
            ByteArrayInputStream ins = new ByteArrayInputStream(tmp);

          if(tmp == null)
             logger.info("is null");
            //int n = ins.read(tmp);
            m_Document = sb.build(ins);
            m_rootElement = m_Document.getRootElement();
            m_Response = m_rootElement.getChild("response");
        }
        catch(Exception e)
        {
            try
            {
                String strXml_new = filterIllegalChar(strXml);
                sb = new SAXBuilder();
                byte[] tmp = new String(strXml_new).getBytes();
                ByteArrayInputStream ins = new ByteArrayInputStream(tmp);
                if(tmp == null)
                     logger.info("is null");
                //int n = ins.read(tmp);
                m_Document = sb.build(ins);
                m_rootElement = m_Document.getRootElement();
                m_Response = m_rootElement.getChild("response");
            }
            catch(Exception e1)
            {
                logger.info("generate xml error1  " + e.getMessage());
                logger.info("generate xml error2  " + strXml);
            }
        }
        */
    	/* dom4j的代码,add by Lampard Lee,May 14th,2008 */
    	strXml = transGbk(strXml);
    	try{
        	m_Document = DocumentHelper.parseText(strXml);
            m_rootElement = m_Document.getRootElement();
            m_Response = m_rootElement.element("response");
            m_Content = m_rootElement.element("content");
        }
        catch(Exception e){
        	try{
                String strXml_new = filterIllegalChar(strXml);
                m_Document = DocumentHelper.parseText(strXml_new);
                m_rootElement = m_Document.getRootElement();
                m_Response = m_rootElement.element("response");
            }
            catch(Exception e1)
            {
            	logger.info("generate xml error1  " + e.getMessage());
                logger.info("generate xml error2  " + strXml);
            }
        }
    	
    }

    /**
     * 过滤导致jdom构造失败的非法字符
     * @param pi_strXml 传入的xml字符
     * @return  过滤了非法字符的xml字符（非法字符已空格替代）
     */
    public String filterIllegalChar(String pi_strXml)
    {
        byte[] tmp = new String(pi_strXml).getBytes();
        for(int i = tmp.length - 1; i >= 0 ; i --)
        {
            if(tmp[i] < 32 && tmp[i] >= 0)
                tmp[i] = ' ';
        }
        return new String(tmp);
    }

    /**
     * 设置编码
     * @param strEncoding 编码方式
     */
    public void setEncoding(String strEncoding)
    {
    	m_sEncoding = strEncoding;
    }

    /**
     * 设置根元素，并且初始化这棵树
     * @param strRoot  根节点
     */
    public void setRoot(String strRoot) 
    {
    	/* jdom,Rem By Lampard Lee,May 14th,2008 */
    	/*
    	m_rootElement = new Element(strRoot);
    	m_Document = new Document(m_rootElement);
    	*/
    	/* dom4j,Add By Lampard Lee,May 14th,2008 */
    	SAXReader sr = null;
        try{
        	sr = new SAXReader();
            m_Document = sr.read(strRoot);
            m_rootElement = m_Document.getRootElement();
        }
        catch(Exception e){
        	m_Document = null;
        	m_rootElement = null;
        }
    }

    /**
     * 增加根节点
     * @param strAttrName   根节点
     * @param strAttrValue  根节点value
     */
    public void setRoot(String strAttrName, String strAttrValue) 
    {
    	/* jdom,Rem By Lampard Lee,May 14th,2008 */
    	/*
    	m_rootElement.addAttribute(new Attribute(strAttrName, strAttrValue,
    		  				Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance")));
    	*/
    	/* dom4j,Add By Lampard Lee,May 14th,2008 */
    	//m_rootElement.add(new Attribute(strAttrName, strAttrValue,Namespace.get("xsi", "http://www.w3.org/2001/XMLSchema-instance")));
    	System.out.println("strAttrName="+strAttrName+",strAttrValue="+strAttrValue);
    }



    /**
     * 给父节点增加一个子节点
     * @param m_Father        父节点
     * @param strSonName      子节点名字
     * @param strSonValue     子节点的值
     * @param strAttrName[]   子节点的属性名
     * @param strAttrValue[]  子节点的属性值
     * @return 子节点
     */
    /* jdom,Rem By Lampard Lee,May 14th,2008 */
    /*
    public Element addElement(Element m_Father, String strSonName, String strSonValue,
                              String strAttrName[], String strAttrValue[]) throws IOException, JDOMException 
    {
      try {
        Element m_Temp;
        m_Temp = new Element(strSonName);
        if (strSonValue != null && !strSonValue.equals("")) {
          m_Temp.setText(transValue(strSonValue));
        }
        for (int i = 0;i < strAttrName.length;i ++) {
          if (strAttrName[i] != null && !strAttrName[i].equals("")) {
            m_Temp.addAttribute(strAttrName[i], strAttrValue[i]);
          }
        }

        m_Father.addContent(m_Temp);
        return m_Temp;
      }
      catch (Exception e) {
        String strMsg =
            "addElement error:sonName=" + strSonName + ",errMsg=" + e.getMessage();
        logger.info(strMsg);
        throw (new IOException(strMsg));
      }
    }
    */
    /* dom4j,Add By Lampard Lee,May 14th,2008 */
    public Element addElement(Element m_Father, String strSonName, String strSonValue,
            String strAttrName[], String strAttrValue[]) throws IOException{
    	try {
    		Element m_Temp = null ;
    		if (strSonValue != null && !strSonValue.equals("")) {
    			m_Temp = m_Father.addElement(strSonName).addText(transValue(strSonValue));
    		}
    		for (int i = 0;i < strAttrName.length;i ++) {
    			if (strAttrName[i] != null && !strAttrName[i].equals("")) {
    				m_Temp = m_Father.addElement(strSonName).addAttribute(strAttrName[i],strAttrValue[i]);
    			}
    		}
    		return m_Temp;
    	}
    	catch (Exception e) {
    		String strMsg =
    			"addElement error:sonName=" + strSonName + ",errMsg=" + e.getMessage();
    		logger.info(strMsg);
    		throw (new IOException(strMsg));
    	}
    }

    /**
     * 给父节点增加一个子节点
     * @param m_Father     父节点
     * @param strSonName   子节点名字
     * @param strSonValue  子节点的值
     * @return 子节点
     */
    public Element addElement(Element m_Father,String strSonName, int nSonValue) throws Exception
    {
        return addElement(m_Father, strSonName, String.valueOf(nSonValue));
    }


    /**
     * 给父节点增加一个子节点
     * @param m_Father     父节点
     * @param strSonName   子节点名字
     * @param strSonValue  子节点的值
     * @return 子节点
     */
    public Element addElement(Element m_Father,String strSonName,String strSonValue)throws Exception
    {
    	/* jdom,Rem By Lampard Lee,May 14th,2008 */
    	/*
    	try 
    	{
    		Element m_Temp;
	        m_Temp = new Element(strSonName);
	        if (strSonValue != null && !strSonValue.equals(""))
	        	m_Temp.setText(transValue(strSonValue));
	        m_Father.addContent(m_Temp);
	        return m_Temp;
	    }catch (Exception e) {
	    	String strMsg = "addElement error:sonName=" + strSonName + ",errMsg=" + e.getMessage();
	    	logger.info(strMsg);
	    	throw new Exception();
	    }
	    */
	    /* dom4j,Add By Lampard Lee,May 14th,2008 */
    	try 
    	{
    		Element m_Temp;
    		
	        if (strSonValue != null && !strSonValue.equals(""))
	        	m_Temp = m_Father.addElement(strSonName).addText(transValue(strSonValue));
	        else
	        	m_Temp = m_Father.addElement(strSonName);
	        return m_Temp;
	    }catch (Exception e) {
	    	String strMsg = "addElement error:sonName=" + strSonName + ",errMsg=" + e.getMessage();
	    	logger.info(strMsg);
	    	throw new Exception();
	    }
    }

    /**
     * 给父节点增加一个子节点
     * @param m_Father     父节点
     * @param strSonName   子节点名字
     * @return 子节点
     */
    public Element addElement(Element m_Father, String strSonName) throws Exception
    {
    	/* jdom,Rem By Lampard Lee,May 14th 2008 */
    	/*
        try{
        	Element m_Temp;
        	m_Temp = new Element(strSonName);
        	m_Father.addContent(m_Temp);
        	return m_Temp;
        }
        catch (Exception e) {
        	String strMsg = "addElement error:sonName=" + strSonName + ",errMsg=" + e.getMessage();
        	logger.info(strMsg);
        	throw (new Exception());
        }
        */
    	/* dom4j,Add By Lampard Lee,May 14th,2008 */
    	try{
        	Element m_Temp;
        	m_Temp = m_Father.addElement(strSonName);
        	return m_Temp;
        }
        catch (Exception e) {
        	String strMsg = "addElement error:sonName=" + strSonName + ",errMsg=" + e.getMessage();
        	logger.info(strMsg);
        	throw (new Exception());
        }
    }

    /**
     * 取子节点
     * @param m_Father    父节点
     * @param strSonName  子节点名称
     * @return
     */
    public Element getElement(Element m_Father,
                              String strSonName)
    {
        /* jdom,Rem By Lampard Lee,May 14th,2008 */
    	/*
        return m_Father.getChild(strSonName);
        */
    	/* dom4j Add By Lampard Lee,May 14th,2008 */
    	return m_Father.element(strSonName);
    }

    /**
     * 取子节点值（String）
     * @param m_Father    父节点
     * @param strSonName  子节点名称
     * @return
     */
    public String getElementStringValue(Element m_Father,
                                        String strSonName)
    {
        
        /* jdom,Rem By Lampard Lee,May 14th,2008 */
    	/*
        return m_Father.getChild(strSonName).getText();
        */
    	/* dom4j Add By Lampard Lee,May 14th,2008 */
    	return m_Father.element(strSonName).getText();
    }

    /**
     * 取子节点值（int）
     * @param m_Father    父节点
     * @param strSonName  子节点名称
     * @return
     */
    public int getElementIntValue(Element m_Father,
                                  String strSonName)
    {
    	/* jdom,Rem By Lampard Lee,May 14th,2008 */
    	/*
        return Integer.parseInt(m_Father.getChild(strSonName).getText().trim());
        */
    	/* dom4j Add By Lampard Lee,May 14th,2008 */
    	return Integer.parseInt(m_Father.element(strSonName).getText().trim());
    }

    /**
     * 取父节点下的子节点值（int）
     * @param m_Father    父节点
     * @param elementName  子节点名称
     * @param nDefaultValue 节点值为null时，传回默认值
     * @return 节点值为null时的默认值
     * @throws Exception
     */
    public int getElementIntValue(Element m_Father,
                                  String strSonName,
                                  int nDefaultValue)
    {
    	/*jdom,Rem By Lampard Lee,May 14th,2008 */
    	/*
        Element child = m_Father.getChild(strSonName);
        if(child == null)
            return nDefaultValue;

        String tmp = child.getText();
        if(tmp == null || tmp.trim().equals(""))
            return  nDefaultValue;

        return Integer.parseInt(tmp.trim());
        */
    	/* dom4j,Add By Lampard Lee,May 14th,2008 */
    	Element child = m_Father.element(strSonName);
        if(child == null)
            return nDefaultValue;

        String tmp = child.getText();
        if(tmp == null || tmp.trim().equals(""))
            return  nDefaultValue;

        return Integer.parseInt(tmp.trim());
        
    }

    /**
     * 取节点值（String）
     * @param elementName  节点名称
     * @return
     */
    public String getElementStringValue(Element elementName)
        throws Exception
    {
        return elementName.getText();
    }

    /**
     * 取节点值（int）
     * @param elementName  节点名称
     * @return
     */
    public int getElementIntValue(Element elementName)
        throws Exception
    {
        try
        {
            return Integer.parseInt(elementName.getText().trim());
        }
        catch(Exception e)
        {
            String strMsg = "addElement error:Element Name=" + elementName.getName() 
            				+ ",errMsg=" + e.getMessage();
            logger.info(strMsg);
            throw (new Exception());
        }
    }

    /**
     * 取节点值（int）
     * @param elementName  节点名称
     * @param nDefaultValue 节点值为null时，传回默认值
     * @return 节点值为null时的默认值
     * @throws Exception
     */
    public int getElementIntValue(Element elementName, int nDefaultValue)
        throws Exception
    {
        try
        {
            String tmp = elementName.getText();
            if(tmp == null || tmp.trim().equals(""))
                return  nDefaultValue;

            return Integer.parseInt(tmp.trim());
        }
        catch(Exception e)
        {
            String strMsg =
                "addElement error:Element Name=" + elementName.getName() + ",errMsg=" + e.getMessage();
            logger.info(strMsg);
            throw (new Exception());
        }
    }

    /**
     * 取子节点列表
     * @param elementName   父节点
     * @param aChild        子节点
     * @return
     */
    public int getSubElement(Element m_Father, String elementName, List aChild)
    {
    	/* jdom,Rem By Lampard Lee,May 14th,2008 */
    	/*
        aChild = m_Father.getChildren(elementName);
        */
    	/* dom4j,Add By Lampard Lee,May 14th,2008 */
    	aChild = m_Father.elements(elementName);
        return 0;
    }

    /**
     * 把树按xml流方式输出(String)
     * @return xml流
     */
    public String outputXMLStr()
    {
    	/* jdom,Rem By Lampard Lee,May 14th,2008 */
    	/*
    	XMLOutputter xmlOut = new XMLOutputter();
    	try{
    		xmlOut.setEncoding(m_sEncoding);
    		return xmlOut.outputString(m_Document);
    	}
    	catch(Exception e){
    		return null;
    	}
    	*/
    	/* dom4j,Add By Lampard Lee,May 14th,2008 */
    	try{
    		m_Document.setXMLEncoding(m_sEncoding);
    		return m_Document.asXML();
    	}
    	catch(Exception e){
    		return null;
    	}
    	
    }

    /**
     * 把树按xml文件方式输出
     * @param strFile 输出的文件名
     */
    /* jdom,Rem By Lampard Lee,May 14th,2008 */
    /*
    public void outputXMLFile(String strFile) throws IOException, JDOMException {
    	
    	XMLOutputter xmlOut = new XMLOutputter();
    	xmlOut.setEncoding(m_sEncoding);
    	File file = new File(strFile);
    	FileWriter fWriter = new FileWriter(file);
    	xmlOut.output(m_Document, fWriter);
    	fWriter.close();
    	
    }
    */
    /* dom4j,Add By Lampard Lee,May 14th,2008 */
    public void outputXMLFile(String strFile) throws IOException {
    	
    	OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding(m_sEncoding);
    	XMLWriter Writer = new XMLWriter(new FileWriter(new File(strFile)),format);
    	Writer.write(m_Document);
    	Writer.close();
    	
    }

    /**
     * 校验xml是否以“<xml”开头
     * @param strXml
     * @return
     */
    public int checkXmlString(String strXml)
    {
        if(strXml.indexOf("<?xml") != 0)
            return ERROR_BSSP_XML_INVALID;

        return 0;
    }

    /**
     * 把'gbk'转化为'GBK'
     * @param strXml
     * @return
     */
    public String transGbk(String strXml)
    {
        int nIndex = strXml.indexOf("gbk");
        if(nIndex == -1) return strXml;
        return strXml.substring(0,nIndex) + "GBK" + strXml.substring(nIndex+3,strXml.length());
    }

    /**
     * 如果是unix环境，将编码方式转化为8859
     * @param strValue
     * @return
     */
    public String transValue(String strValue)
    {
        //if( global.GlobalConst.CHANGE_STRING)
        //    return global.GlobalFunc.get8859ByGBK(strValue);
            //return strValue;
        //else
            return strValue;
    }


    /**
     * 转换特殊字符为HTML标签.
     * @param input String
     * @return string
     */
    public String escapeHTMLTags(String input) {

    	return input;
/*
        if (input == null || input.length() == 0)
            return input;
        StringBuffer buf = new StringBuffer();
        char ch = ' ';
        for (int i = 0; i < input.length(); i++) {
            ch = input.charAt(i);
            if (ch == '<')
                buf.append("&lt;");
            else if (ch == '>')
                buf.append("&gt;");
            else if (ch == '&')
                buf.append("&amp;");
            else if (ch == '"')
                buf.append("&quot;");
            else if (ch == '\'')
                buf.append("&apos;");
            else
                buf.append(ch);
        }
        return buf.toString();
*/
    }

    public static void main(String[] args)
    {
        //String tmp = "<?xml version='1.0' encoding='GBK' ?><operation_out><content><aaa>江苏省镇江市大港镇港中新村7区20幢</aaa><ret_msg>1234</ret_msg><ret_code>0</ret_code></content><response><resp_result>0</resp_result><resp_code>0000</resp_code><resp_desc></resp_desc></response></operation_out>";
    	String tmp = "<?xml version=\"1.0\" encoding=\"utf-8\"?><operation_out><service_name></service_name><sysfunc_id></sysfunc_id><request_type></request_type><operator_id></operator_id><request_time></request_time><response><resp_type></resp_type><resp_code>0</resp_code><resp_desc>error</resp_desc></response><content><Result><ResultCode></ResultCode><ResultMessage></ResultMessage><userid>123</userid></Result></content></operation_out>";
    	BsspXmlMgr xml = null;
        try
        {
            xml = new BsspXmlMgr(tmp);
            
            System.out.println(xml);
        }
        catch(Exception e)  {
        	e.printStackTrace();
        }
//        tmp = "20幢=====？？";
//        String aa = xml.filterIllegalChar(tmp);
//        logger.info(aa);
    }
}
