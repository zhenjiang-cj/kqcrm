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

/** BSSP��Ϣ��
 * <p>Title: BSSP��Ϣ��</p>
 * <p>Description: ��XML����ʽ����BSSP��Ϣ</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: NEWLAND</p>
 * @author not attributable
 * @version 1.0
 */

public class BsspXmlMgr extends AbstractComponent
{

    private static Logger logger = Logger.getLogger(BsspXmlMgr.class);
    
    /**
     * ��
     */
    public Document m_Document;

    /**
     * ��Ԫ��
     */
    public Element m_rootElement;

    /**
     * ���뷽ʽ
     */
    public String m_sEncoding;

    /**
     * ҵ��Ԫ����
     */
    public Element m_Content;

    /**
     * ҵ��Ԫ����
     */
    public Element m_Response;

    /**
     * ������
     */
    public String m_processCode;


    public static int ERROR_BSSP_XML_INVALID = -1001;
    public static int ERROR_ADD_ELEMENT      = -1002;


    /**
     * ���ݲ�����Ϣ�����̴��빹��BsspXmlMgr����
     * @param operationInfo OperatingInfo_DT ������Ϣ
     * @param processCode String  ���̴���
     */
    public BsspXmlMgr(AdmUserFc dtUserInfo, String processCode, int nFuncId)
    {
    	
    	m_sEncoding = "utf-8"; //"GBK";
    	m_Document = DocumentHelper.createDocument();
    	m_rootElement = m_Document.addElement("operation_in");
        m_processCode = processCode;

        try{
        	addElement(m_rootElement, "service_name", processCode); 			//1	string	V30	CGI������	�ο��ӿڶ����еķ�����
        	addElement(m_rootElement, "request_type", "1");						//	1	int	V4	��������	�ο����������͡�
        	addElement(m_rootElement, "sysfunc_id", nFuncId); 					//1   int	V8	���ܴ���
        	if(dtUserInfo.getAuID()!=null)
        		addElement(m_rootElement, "operator_id", dtUserInfo.getAuID()); 	//?	string	V30	����Ա����
        	else{
        		addElement(m_rootElement, "operator_id", "null");
        	}
        	addElement(m_rootElement, "request_time", new SimpleDateFormat("yyyymmddHHmmss").format(new Date()).toString());						// 1	datetime	F14	����ʱ��

        	Element tmp = addElement(m_rootElement, "content");
        	m_Content = tmp;
        }
        catch(Exception e){}
    }



    /**
     * �����ַ�������document������xmlʹ�ã�
     * @param strXml
     */
    public BsspXmlMgr(String strXml)
    {
    	/* jdom�Ĵ���,rem by Lampard Lee,May 14th,2008 */
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
    	/* dom4j�Ĵ���,add by Lampard Lee,May 14th,2008 */
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
     * ���˵���jdom����ʧ�ܵķǷ��ַ�
     * @param pi_strXml �����xml�ַ�
     * @return  �����˷Ƿ��ַ���xml�ַ����Ƿ��ַ��ѿո������
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
     * ���ñ���
     * @param strEncoding ���뷽ʽ
     */
    public void setEncoding(String strEncoding)
    {
    	m_sEncoding = strEncoding;
    }

    /**
     * ���ø�Ԫ�أ����ҳ�ʼ�������
     * @param strRoot  ���ڵ�
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
     * ���Ӹ��ڵ�
     * @param strAttrName   ���ڵ�
     * @param strAttrValue  ���ڵ�value
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
     * �����ڵ�����һ���ӽڵ�
     * @param m_Father        ���ڵ�
     * @param strSonName      �ӽڵ�����
     * @param strSonValue     �ӽڵ��ֵ
     * @param strAttrName[]   �ӽڵ��������
     * @param strAttrValue[]  �ӽڵ������ֵ
     * @return �ӽڵ�
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
     * �����ڵ�����һ���ӽڵ�
     * @param m_Father     ���ڵ�
     * @param strSonName   �ӽڵ�����
     * @param strSonValue  �ӽڵ��ֵ
     * @return �ӽڵ�
     */
    public Element addElement(Element m_Father,String strSonName, int nSonValue) throws Exception
    {
        return addElement(m_Father, strSonName, String.valueOf(nSonValue));
    }


    /**
     * �����ڵ�����һ���ӽڵ�
     * @param m_Father     ���ڵ�
     * @param strSonName   �ӽڵ�����
     * @param strSonValue  �ӽڵ��ֵ
     * @return �ӽڵ�
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
     * �����ڵ�����һ���ӽڵ�
     * @param m_Father     ���ڵ�
     * @param strSonName   �ӽڵ�����
     * @return �ӽڵ�
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
     * ȡ�ӽڵ�
     * @param m_Father    ���ڵ�
     * @param strSonName  �ӽڵ�����
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
     * ȡ�ӽڵ�ֵ��String��
     * @param m_Father    ���ڵ�
     * @param strSonName  �ӽڵ�����
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
     * ȡ�ӽڵ�ֵ��int��
     * @param m_Father    ���ڵ�
     * @param strSonName  �ӽڵ�����
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
     * ȡ���ڵ��µ��ӽڵ�ֵ��int��
     * @param m_Father    ���ڵ�
     * @param elementName  �ӽڵ�����
     * @param nDefaultValue �ڵ�ֵΪnullʱ������Ĭ��ֵ
     * @return �ڵ�ֵΪnullʱ��Ĭ��ֵ
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
     * ȡ�ڵ�ֵ��String��
     * @param elementName  �ڵ�����
     * @return
     */
    public String getElementStringValue(Element elementName)
        throws Exception
    {
        return elementName.getText();
    }

    /**
     * ȡ�ڵ�ֵ��int��
     * @param elementName  �ڵ�����
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
     * ȡ�ڵ�ֵ��int��
     * @param elementName  �ڵ�����
     * @param nDefaultValue �ڵ�ֵΪnullʱ������Ĭ��ֵ
     * @return �ڵ�ֵΪnullʱ��Ĭ��ֵ
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
     * ȡ�ӽڵ��б�
     * @param elementName   ���ڵ�
     * @param aChild        �ӽڵ�
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
     * ������xml����ʽ���(String)
     * @return xml��
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
     * ������xml�ļ���ʽ���
     * @param strFile ������ļ���
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
     * У��xml�Ƿ��ԡ�<xml����ͷ
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
     * ��'gbk'ת��Ϊ'GBK'
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
     * �����unix�����������뷽ʽת��Ϊ8859
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
     * ת�������ַ�ΪHTML��ǩ.
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
        //String tmp = "<?xml version='1.0' encoding='GBK' ?><operation_out><content><aaa>����ʡ���д��������´�7��20��</aaa><ret_msg>1234</ret_msg><ret_code>0</ret_code></content><response><resp_result>0</resp_result><resp_code>0000</resp_code><resp_desc></resp_desc></response></operation_out>";
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
//        tmp = "20��=====����";
//        String aa = xml.filterIllegalChar(tmp);
//        logger.info(aa);
    }
}
