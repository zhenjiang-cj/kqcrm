package com.nl.base.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.upload.FormFile;

import com.nl.portal.dt.AdmUserLog;
import com.nl.portal.sc.SystemSC;
import com.nl.util.FTPTools;
import com.nl.util.GlobalConst;
import com.nl.util.PortalzjException;
import com.nl.util.db.DBConnection;


/**
 * <p>
 * Copyright: Copyright (c) 2011 �´�½����������޹�˾All rights reserved
 * </p>.
 * <p>
 * FileName: GlobalFunc.java
 * </p>
 * <p>
 * Description: ���÷���
 * </p>
 * <p>
 * Date : 2011-01-10
 * </p>
 * <p>
 * �޸ļ�¼:
 * </p>
 * 
 * @author �ܿ�
 * @version 0.1
 */
public class GlobalFunc {
	
	/**
	 * dwz�����������
	 * 
	 * @param rsdt
	 * @return
	 * @author sanjing
	 * @createdate Oct 26, 2016
	 * @version v1.0
	 */
	public static String getRsJson(GlobalRsDt rsdt){
		rsdt.setMessage(cnToUnicode(rsdt.getMessage()));
		
		return getJosnStrForObject(rsdt);		
		
	}
	
	/**
	 * ����תΪunicode
	 * 
	 * @param cn
	 * @return
	 * @author sanjing
	 * @createdate Oct 26, 2016
	 * @version v1.0
	 */
	public static String cnToUnicode(String cn) { 
	    char[] chars = cn.toCharArray(); 
	    String returnStr = ""; 
	    for (int i = 0; i < chars.length; i++) { 
	      returnStr += "\\u" + Integer.toString(chars[i], 16); 
	    } 
	    return returnStr; 
	} 

	

    /**
     * ��ʽ�����ڶ���
     * 
     * @param date
     *            ����
     * @param model
     *            ��ʽ�ַ���(��:"yyyy-MM-dd HH:mm:ss")
     * @return ����ַ���
     */
    public static String dateFormat(Date date, String model) {
        SimpleDateFormat df = new SimpleDateFormat(model);
        return df.format(date);
    }

    /**
     * ��ʼ���ַ���
     * 
     * @param o
     *            ��ʽ������
     * @param sDef
     *            ����Ϊnullʱ�ķ��� sDef
     * @return ����ַ���
     */
    public static String initStr(Object o, String sDef) {
        if (o != null&&!"null".equals(o)&&!"".equals(o)) {
            return o.toString();
        } else {
            return sDef;
        }
    }

    /**
     * ��ʼ������
     * 
     * @param s
     *            ��ʽ������
     * @param nDef
     *            ����Ϊnullʱ�ķ��� nDef
     * @return ����ַ���
     */
    public static int initInt(String s, int nDef) {
        if (s != null && !"".equals(s)) {
            return Integer.parseInt(s);
        } else {
            return nDef;
        }
    }

    /**
     * ��ʼ������
     * 
     * @param s
     *            ��ʽ������
     * @param nDef
     *            ����Ϊnullʱ�ķ��� nDef
     * @return ����ַ���
     */
    public static float initFloat(String s, float nDef) {
        if (s != null && !"".equals(s)) {
            return Float.parseFloat(s);
        } else {
            return nDef;
        }
    }

    /**
     * ��ʼ������
     * 
     * @param s
     *            ��ʽ������
     * @param nDef
     *            ����Ϊnullʱ�ķ��� nDef
     * @return ����ַ���
     */
    public static long initLong(String s, long nDef) {
        if (s != null && !"".equals(s)) {
            return Long.parseLong(s);
        } else {
            return nDef;
        }
    }

    /**
     * ��ʽ���ַ���
     * 
     * @param o
     *            ��ʽ������
     * @return ����ַ��� ����Ϊnullʱ�ķ��ء�����
     */
    public static String initStr(Object o) {
        return initStr(o, "");
    }

    /**
     * ����DT���б����select�б�(HTML)���ɶ�������һ��Option������'ȫѡ'����'��ѡ��'�ȵȣ�
     * 
     * @param hashDtList
     *            Hashtable ����(DT)�б�(��HashTable�洢)
     * @param strValueField
     *            String select�е�value��Ӧ��Dt�ֶ���
     * @param strTextField
     *            String select����ʾ�ı���Ӧ��Dt�ֶ���
     * @param strListName
     *            String select����
     * @param strExtraText
     *            String �������ӵ�Option����
     * @param strExtraValue
     *            String �������ӵ�Option��Ӧ��Value
     * @param strDefault
     *            String select�е�Ĭ��ѡ���value(null��ʾ��Ĭ��)
     * @param b_HasOnChange
     *            boolean select�Ƿ���Change�¼�(�����,������ΪF_select����_OnChange())
     * @return String
     */
    @SuppressWarnings("unchecked")
    public static String getDtListSelect(Hashtable hashDtList, String strValueField, String strTextField, String strListName, String strExtraText,
            String strExtraValue, String strDefault, boolean b_HasOnChange) {
        ArrayList listDt = new ArrayList();

        if (hashDtList != null) {
            Enumeration enu = hashDtList.elements();
            Object obj;

            while (enu.hasMoreElements()) {
                obj = enu.nextElement();
                listDt.add(0, obj);
            }
        }

        return getDtListSelect(listDt, strValueField, strTextField, strListName, strExtraText, strExtraValue, strDefault, b_HasOnChange);
    }

    /**
     * ����DT���б����select�б�(HTML)���ɶ�������һ��Option������'ȫѡ'����'��ѡ��'�ȵȣ�
     * 
     * @param listDt
     *            List ����(DT)�б�
     * @param strValueField
     *            String select�е�value��Ӧ��Dt�ֶ���
     * @param strTextField
     *            String select����ʾ�ı���Ӧ��Dt�ֶ���
     * @param strListName
     *            String select����
     * @param strExtraText
     *            String �������ӵ�Option����
     * @param strExtraValue
     *            String �������ӵ�Option��Ӧ��Value
     * @param strDefault
     *            String select�е�Ĭ��ѡ���value(null��ʾ��Ĭ��)
     * @param b_HasOnChange
     *            boolean select�Ƿ���Change�¼�(�����,������ΪF_select����_OnChange())
     * @return String
     */
    public static String getDtListSelect(List listDt, String strValueField, String strTextField, String strListName, String strExtraText,
            String strExtraValue, String strDefault, boolean b_HasOnChange) {
        return getDtListSelect(listDt, strValueField, strTextField, strListName, null, strExtraText, strExtraValue, strDefault, b_HasOnChange);
    }

    /**
     * ����DT���б����select�б�(HTML)���ɶ�������һ��Option������'ȫѡ'����'��ѡ��'�ȵȣ�
     * 
     * @param listDt
     *            List ����(DT)�б�
     * @param strValueField
     *            String select�е�value��Ӧ��Dt�ֶ���
     * @param strTextField
     *            String select����ʾ�ı���Ӧ��Dt�ֶ���
     * @param strListName
     *            String select����
     * @param strListType
     *            String select���� ��ͨ��ѡSelect:null;չ��������:multiple
     * @param strExtraText
     *            String �������ӵ�Option����
     * @param strExtraValue
     *            String �������ӵ�Option��Ӧ��Value
     * @param strDefault
     *            String select�е�Ĭ��ѡ���value(null��ʾ��Ĭ��)
     * @param b_HasOnChange
     *            boolean select�Ƿ���Change�¼�(�����,������ΪF_select����_OnChange())
     * @return String
     */
    public static String getDtListSelect(List listDt, String strValueField, String strTextField, String strListName, String strListType,
            String strExtraText, String strExtraValue, String strDefault, boolean b_HasOnChange) {
        Object dtObj; // ����dt��Ϣ
        String strList = "<select name='" + strListName + "' ";

        // �ж��Ƿ�Ҫ�趨OnChange�¼�
        if (b_HasOnChange) {
            strList += (" onChange='F_" + strListName + "OnChange(this);'");
        }

        if (null != strListType) {
            strList += (" " + strListType);
        }

        strList += ">";

        // �����Ƿ����Ӷ���ѡ��
        if (strExtraText != null) {
            if (strExtraValue != null) {
                if (listDt.size() > 0) { // �Ƿ����һ����¼

                    // �Ƿ�Ĭ��ѡ��
                    if ((strDefault != null) && strDefault.equals(strExtraValue)) {
                        strList += ("\n\t<option value='" + strExtraValue + "' selected>" + strExtraText + "</option>");
                    } else {
                        strList += ("\n\t<option value='" + strExtraValue + "' >" + strExtraText + "</option>\n");
                    }
                }
            }
        }

        // ���ȡ������DT���д���
        // for (Object aListDt : listDt) {
        for (int h = 0; h < listDt.size(); h++) {
            dtObj = listDt.get(h);

            Field[] flds = dtObj.getClass().getFields();
            String strValue = null;
            String strText = null;

            // for (Field fld : flds) {
            for (int j = 0; j < flds.length; j++) {
                Field fld = flds[j];
                try {
                    // �������Ӧ���ֶ�����
                    if (fld.getName().equals(strValueField)) {
                        strValue = fld.get(dtObj).toString();
                    }

                    // �ı����Ӧ���ֶ�����
                    if (fld.getName().equals(strTextField)) {
                        strText = fld.get(dtObj).toString();
                    }
                } catch (Exception e) {
                    return null;
                }
            }

            if (strValue != null) {
                // �Ƿ�Ĭ��ѡ��
                if ((strDefault != null) && strDefault.equals(strValue)) {
                    strList += ("\n\t<option value='" + strValue + "' selected>" + strText + "</option>");
                } else {
                    strList += ("\n\t<option value='" + strValue + "' >" + strText + "</option>\n");
                }
            }
        }

        strList += "\t</select>";

        return strList;
    }

    /**
     * ��ʽ�����(��)��ֵΪ��λС��(Ԫ)
     * 
     * @param long
     *            num ���(��)
     * @return ���(Ԫ)
     */
    public static String numberFormat(long num) {
        return numberFormatDouble(num);
    }

    /**
     * ��ʽ�����(��)��ֵΪ��λС��(Ԫ)
     * 
     * @param String
     *            num ���(��)
     * @return ���(Ԫ)
     */
    public static String numberFormat(String num) {
        return numberFormatDouble(Double.parseDouble(num));
    }

    /**
     * ��ʽ�����(��)��ֵΪ��λС��(Ԫ)
     * 
     * @param double
     *            num ���(��)
     * @return ���(Ԫ)
     */
    public static String numberFormatDouble(double num) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);

        double b = (num / 1000);

        return nf.format(b);
    }

    /**
     * ��ʽ����λԪ��С��Ϊ��λΪ��
     * 
     * @param String
     *            sNum ���(Ԫ)
     * @return ���(��)
     */
    public static long unDonumberFormat(String sNum) {
        return unDonumberFormat(sNum, "1000", 3);
    }

    /**
     * ��ʽ����λԪ��С��Ϊ��λΪ��
     * 
     * @param sNum
     *            ���(Ԫ)
     * @param multiplicator
     *            ����(ȡֵ������Ϊ10,100,1000...���ַ�)
     * @return ���(��)
     */
    public static long unDonumberFormat(String sNum, String multiplicator) {
        return unDonumberFormat(sNum, multiplicator, 3);
    }

    /**
     * ��ʽ����λԪ��С��Ϊ��λΪ��
     * 
     * @param sNum
     *            ���(Ԫ)
     * @param multiplicator
     *            ����(ȡֵ������Ϊ10,100,1000...���ַ�)
     * @param flag
     *            �������(3 ��)
     * @return ���(��)
     */
    public static long unDonumberFormat(String sNum, String multiplicator, int flag) {
        if (sNum == null || "".equals(sNum.trim())) {
            return 0;
        } else {
            long tempValue = 0L;
            BigDecimal a = new BigDecimal(sNum.trim());
            BigDecimal b = new BigDecimal(multiplicator);

            if (flag == 3) {
                tempValue = a.multiply(b).longValue();
            } else if (flag == 4) {
                tempValue = a.divide(b, 2).longValue();
            }

            return tempValue;
        }
    }

    /**
     * �Ӽ��˳�����
     * 
     * @param num1
     *            ������1
     * @param num2
     *            ������2
     * @param scale
     *            ����С��λ��
     * @param roundingMode
     *            ������
     * @param flag
     *            (1 ��;2 ��;3 ��;4 ��;)
     * @return BigDecimal ���
     */
    public static BigDecimal operNumber(String num1, String num2, int scale, int roundingMode, int flag) {
        BigDecimal outDecimal = null;

        if (num1 == null || num1.trim().length() <= 0 || num2 == null || num2.trim().length() <= 0) {
            return null;
        } else {
            BigDecimal decm1 = new BigDecimal(num1.trim());
            BigDecimal decm2 = new BigDecimal(num2.trim());

            if (flag == 1) {
                outDecimal = decm1.add(decm2);
                outDecimal = outDecimal.setScale(scale, roundingMode);
            } else if (flag == 2) {
                outDecimal = decm1.subtract(decm2);
                outDecimal = outDecimal.setScale(scale, roundingMode);
            } else if (flag == 3) {
                outDecimal = decm1.multiply(decm2);
                outDecimal = outDecimal.setScale(scale, roundingMode);
            }
            if (flag == 4) {
                outDecimal = decm1.divide(decm2, scale, roundingMode);
            }

            return outDecimal;
        }
    }

    /**
     * �˷�����
     * 
     * @param num
     *            ����
     * @param multiplicator
     *            �˻�
     * @param scale
     *            ����С��λ��
     * @param roundingMode
     *            ������
     * @return BigDecimal ���
     */
    public static double multiplyNumber(String num, String multiplicator, int scale, int roundingMode) {
        BigDecimal outDecimal = null;
        outDecimal = operNumber(num, multiplicator, scale, roundingMode, 3);
        return outDecimal.doubleValue();
    }

    /**
     * �˷�����(Ĭ����������)
     * 
     * @param num
     *            ����
     * @param multiplicator
     *            �˻�
     * @param scale
     *            ����С��λ��
     * @return BigDecimal ���
     */
    public static double multiplyNumber(String num, String multiplicator, int scale) {
        BigDecimal outDecimal = null;
        outDecimal = operNumber(num, multiplicator, scale, 5, 3);
        return outDecimal.doubleValue();
    }

    /**
     * �˷�����(Ĭ�ϱ�����λС����,��������)
     * 
     * @param num
     *            ����
     * @param multiplicator
     *            �˻�
     * @return double ���
     */
    public static double multiplyNumber(String num, String multiplicator) {
        BigDecimal outDecimal = null;
        outDecimal = operNumber(num, multiplicator, 2, 5, 3);
        return outDecimal.doubleValue();
    }

    /**
     * ��������
     * 
     * @param num
     *            ����
     * @param dividend
     *            ������
     * @param scale
     *            ����С��λ��
     * @param roundingMode
     *            ������
     * @return double ���
     */
    public static double divideNumber(String num, String dividend, int scale, int roundingMode) {
        BigDecimal outDecimal = null;
        outDecimal = operNumber(num, dividend, scale, roundingMode, 4);
        return outDecimal.doubleValue();
    }

    /**
     * ��������(Ĭ����������)
     * 
     * @param num
     *            ����
     * @param dividend
     *            ������
     * @param scale
     *            ����С��λ��
     * @return double ���
     */
    public static double divideNumber(String num, String dividend, int scale) {
        BigDecimal outDecimal = null;
        outDecimal = operNumber(num, dividend, scale, 5, 4);
        return outDecimal.doubleValue();
    }

    /**
     * ��������(Ĭ�ϱ�����λС����,��������)
     * 
     * @param num
     *            ����
     * @param dividend
     *            ������
     * @param roundingMode
     *            ����С��λ��
     * @return double ���
     */
    public static double divideNumber(String num, String dividend) {
        BigDecimal outDecimal = null;
        outDecimal = operNumber(num, dividend, 2, 5, 4);
        return outDecimal.doubleValue();
    }

    /**
     * ��ȡ�ַ���
     * 
     * @param inStr
     *            �ַ���
     * @param length
     *            �����������
     * @param ctemp
     *            ����ַ�
     * @return �ַ���
     */
    public static String formatStr(String inStr, int length, char ctemp) {
        String temp = null;
        if (inStr != null && inStr.trim().length() > 0) {
            int strLen = inStr.length();
            if (strLen > length) {
                temp = inStr.substring(0, length);
                for (int i = 0; i < 3; i++) {
                    temp = temp + ctemp;
                }
            } else {
                temp = inStr;
            }
        } else {
            temp = "";
        }
        return temp;
    }

    /**
     * ����ʽ�����ַ���
     * 
     * @param inStr
     *            �ַ���
     * @param length
     *            �����������
     * @param ctemp
     *            ����ַ�
     * @param lrFlag
     *            ��λ������ (0 ����߲�λ;1 ���ұ߲�λ)
     * @return �ַ���
     */
    public static String formatStr(String inStr, int length, char ctemp, int lrFlag) {
        String temp = null;
        if (inStr != null && inStr.trim().length() > 0) {
            temp = inStr.trim();
            int strLen = temp.getBytes().length;
            if (strLen < length) {
                for (int i = 0; i < length - strLen; i++) {
                    if (lrFlag == 0) { // ����
                        temp = ctemp + temp;
                    } else { // �Ҳ���
                        temp += ctemp;
                    }
                }
            }
        }
        return temp;
    }

    /**
     * ����ʽ�����ַ���
     * 
     * @param inStr
     *            �ַ���
     * @param length
     *            �����������
     * @param ctemp
     *            ����ַ�
     * @param lrFlag
     *            ��λ������ (0 ����߲�λ;1 ���ұ߲�λ)
     * @param remLrFlag
     *            ȡָ�����ȷ����� (0 �����ȡ;1 ���ұ�ȡ)
     * @return �ַ���
     */
    public static String formatStr(String inStr, int length, char ctemp, int lrFlag, int remLrFlag) {
        String temp = inStr.trim();
        int strLen = temp.getBytes().length;
        if (strLen < length) {
            for (int i = 0; i < length - strLen; i++) {
                if (lrFlag == 0) { // ����
                    temp = ctemp + temp;
                } else { // �Ҳ���
                    temp += ctemp;
                }
            }
        } else {
            if (remLrFlag == 0) { // ����ȡֵ
                temp = getBytes(temp.getBytes(), 0, length);
            } else { // ����ȡֵ
                temp = getBytes(temp.getBytes(), strLen - length, strLen);
            }
        }

        return temp;
    }

    /**
     * ��ȡbyte[]������һ��ֵ
     * 
     * @param pBytes
     *            �ֽ�����
     * @param startNum
     *            ȡֵ��ʼλ��
     * @param endNum
     *            ȡֵ����λ��
     * @return �����ַ���(��ִ�й�trim)
     */
    public static String getBytes(byte[] pBytes, int startNum, int endNum) {
        String returnStr = null;

        int count = 0;
        int len = pBytes.length;
        byte[] tempBytes = null;

        if (pBytes == null || len < endNum) {
            return null;
        } else {
            tempBytes = new byte[endNum - startNum + 1];
        }
        for (int i = startNum; i < endNum; ++i) {
            tempBytes[count] = pBytes[i];
            ++count;
        }
        returnStr = new String(tempBytes);
        returnStr = returnStr.length() > 0 ? returnStr.trim() : null;
        return returnStr;
    }

    /**
     * �жϴ����ַ����Ƿ�ΪNULL ,���ΪNULL,���� defaultValue
     * 
     * @param str
     *            Ҫ�жϵ��ַ���
     * @param defaultValue
     *            Ĭ��ֵ
     * @return
     */
    public static String notNull(String str, String defaultValue) {
        if (str == null) {
            return defaultValue;
        } else {
            return str.trim();
        }
    }

    public static String notNull(String str) {
        if (str == null) {
            return "";
        } else {
            return str.trim();
        }
    }

    /**
     * �жϴ����ַ����Ƿ�Ϊ�գ���Ϊ���򷵻�""
     * 
     * @author yangsy
     * @param str
     *            �ж��ַ���
     * @return String
     */
    public static String isNull(String str) {
        if (str == null) {
            return "";
        } else {
            return str.trim();
        }
    }

    /**
     * �滻�ַ���
     * 
     * @param mainString
     *            String �滻���ַ���
     * @param oldString
     *            String ���滻��ֵ
     * @param newString
     *            String �滻�����ֵ
     * @return String
     */
    public static String replace(String mainString, String oldString, String newString) {
        if (mainString == null) {
            return null;
        }
        int i = mainString.lastIndexOf(oldString);
        if (i < 0) {
            return mainString;
        }
        StringBuffer mainSb = new StringBuffer(mainString);
        while (i >= 0) {
            mainSb.replace(i, i + oldString.length(), newString);
            i = mainString.lastIndexOf(oldString, i - 1);
        }
        return mainSb.toString();
    }

    /**
     * ��ȡutf8��ʽ�ַ���
     * 
     * @param s
     *            String ԭ�ַ���
     * @return String ת�����ַ���
     */
    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c >= 0) && (c <= 255)) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = String.valueOf(c).getBytes("utf-8");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0) {
                        k += 256;
                    }
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    /**
     * escape
     * 
     * @param srcStr
     * @return
     */
    public static String escape(String srcStr) {
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(srcStr.length() * 6);
        for (i = 0; i < srcStr.length(); i++) {
            j = srcStr.charAt(i);
            if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
                tmp.append(j);
            else if (j < 256) {
                tmp.append("%");
                if (j < 16)
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }

    /**
     * unescape
     * 
     * @param srcStr
     * @return
     */
    public static String unescape(String srcStr) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(srcStr.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < srcStr.length()) {
            pos = srcStr.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (srcStr.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(srcStr.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(srcStr.substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(srcStr.substring(lastPos));
                    lastPos = srcStr.length();
                } else {
                    tmp.append(srcStr.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    /**
     * ����java.sql.Date
     * 
     * @param date
     * @return
     */
    public static java.sql.Date getSqlDate(String date) {
        if (null == date)
            return null;
        if (date.equalsIgnoreCase(""))
            return null;
        try {
            return java.sql.Date.valueOf(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * ��ȡ��ǰ�µ�һ���ǵڼ��ܡ��ܼ�
     * 
     * @param date
     *            ��ǰʱ��
     * @param format
     *            Calendar.WEEK_OF_MONTH Calendar.DAY_OF_WEEK
     * @return Calendar.WEEK_OF_MONTH ���ص��ǵ��µڼ��� Calendar.DAY_OF_WEEK ���ص����ܼ�
     */
    public int getSystemdateWeek(String date, int format) {
        int week = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        try {
            calendar.setTime(new SimpleDateFormat("yyyyMMdd").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        week = calendar.get(format) - 1;
        if (week == 0) {
            week = 7;
        }
        return week;
    }

    /**
     * ��ȡ��ǰ�ǵ��ǵ��µڼ���,
     * 
     * @param date
     *            ��ǰʱ��
     * @param format
     *            Calendar.WEEK_OF_MONTH Calendar.DAY_OF_WEEK
     * @return Calendar.WEEK_OF_MONTH ���ص��ǵ��µڼ��� Calendar.DAY_OF_WEEK ���ص����ܼ�
     */
    public int getWeekOfMonth(String date, int format) {
        int week = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        try {
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        week = calendar.get(format);
        return week;
    }

    public static Pattern INTEGER = Pattern.compile("\\d*");

    /**
     * ���������ַ����Ƿ���ȫ���֣�������
     * 
     * @param str
     *            �����ַ���
     * @return true���ǣ�false����
     */
    public static boolean isInteger(String str) {
        Matcher m = INTEGER.matcher(str);
        return m.matches();
    }

    /**
     * ȡģ����Чʱ��͵�ǰʱ��ıȽ�(add by gej)
     * 
     * @param dateA
     *            ģ�����Чʱ��
     * @param dateB
     *            ��ǰ���ݿ��ʱ��
     * @return ��־ 1 ģ����Чʱ��С�ڵ�ǰʱ�� 2 ģ����Чʱ����ڵ�ǰʱ��
     */
    public static int compareDate(String dateA, String dateB) {
        String date[] = dateB.split(" ");
        dateB = date[0];
        int flag = 0;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = sdf1.parse(dateA);
            date2 = sdf1.parse(dateB);
            if (date1.before(date2) || date1.equals(date2)) {
                flag = 1;
            } else {
                flag = 2;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * �����ݵ��ַ����ָ�����飬ȥ��
     * 
     * @param str
     *            �����ַ���
     * @param aff
     *            �ָ��
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String[] StrToArrayList(String str, String aff) {
        List arrayList = new ArrayList();
        String[] single = str.split(aff);
        for (int i = 0; i < single.length; i++) {
            if (!arrayList.contains(single[i])) {
                arrayList.add(single[i]);
            }
        }
        String[] single2 = new String[arrayList.size()];
        // ��ӡ ��֤�Ƿ�ȥ��
        for (int i = 0; i < arrayList.size(); i++) {
            single2[i] = (String) arrayList.get(i);
            // ����������,��ӡ��������Ԫ��
        }
        return single2;
    }

    /**
     * �жϴ����key�ڼ������Ƿ����
     * 
     * @param strKey
     *            String key
     * @param strKeySet
     *            String set��;�ָ�
     * @return trueҪ�����ˣ�false��Ҫ������
     */
    private static boolean isExistKey(String strKey, String strKeySet) {
        boolean flag = false;
        String[] filterKeyArray = strKeySet.split(";");
        for (String aFilterKeyArray : filterKeyArray) {
            if (aFilterKeyArray.equals(strKey)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * ��ȡ����ip
     * 
     * @return
     */
    public static String getIp() {
        String ip = "";
        InetAddress addr;
        try {
            addr = InetAddress.getLocalHost();
            ip = addr.getHostAddress().toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }

    /**
     * ת��Ϊdouble��
     * 
     * @param s
     * @param nDef
     * @return
     */
    public static double initDouble(String s, int nDef) {
        if (s != null && !"".equals(s)) {
            return Double.parseDouble(s);
        } else {
            return nDef;
        }
    }

    public static void main(String[] args) {
    	System.out.println(GlobalFunc.parseWatermark(""));
    }

    public static boolean isNumber(String str) {
        String numberChars = "1234567890.";
        for (int i = 0; i < str.length(); i++) {
            if (i == 0) {
                if (str.charAt(i) == '.') {
                    return false;
                }
            }
            if (numberChars.indexOf(str.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * �ж��ַ����Ƿ�������
     */
    public static boolean isinteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * �ж��ַ����Ƿ��Ǹ�����
     */
    public static boolean isdouble(String value) {
        try {
            Double.parseDouble(value);
            if (value.contains("."))
                return true;
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * ���ַ����е�Ӣ��˫����ת��Ϊҳ����ʾ��˫����
     * 
     * @param str
     * @return
     */
    public static String convertHtml(String str) {
        return (null != str ? str.replaceAll("\"", "&quot;") : "");
    }
    
    /**
     * ��double���͵����ֱ�����Ӧ��С��λ
     * @param number ��Ҫ����С��λ����
     * @param formatStr ��ʽ��������'#0.0'��ʾ����һλС����'#0.00'��ʾ������λС����
     * @return String
     */
    public static String formatDouble(double number,String formatStr) 
    {
    	DecimalFormat df = new DecimalFormat(formatStr);
        return df.format(number);
    }
    
    //��LISTת��ΪJSON����
    public static String getJosnStrForList(List list)
    {
    	JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray.toString();
    }
    
    public static String getJosnStrForObject(Object obj) {  
    	HashMap<String, String> map = new HashMap<String, String>(); 
    	Class c = obj.getClass();  
    	Field[] fields = c.getDeclaredFields(); 
    	for (int i = 0; i < fields.length; i++) 
    	{ 
    		String name = fields[i].getName(); 
    		try 
    		{  
    			fields[i].setAccessible(true); 
    			Object o = fields[i].get(obj); 
    			if (o instanceof Number) 
    			{  
    				map.put("\"" + name + "\"", o.toString()); 
    			} else if (o instanceof String) {  
    				map.put("\"" + name + "\"", "\"" + o.toString() + "\""); 
    				}  
    			} 
    		catch (IllegalArgumentException e) { } 
    		catch (IllegalAccessException e) { } 
    	}  
    	String s = map.toString();  
    	String str = s.replaceAll("\"=", "\":"); 
    	return str; 
    } 
    
    /**
     * Ȩ��У�飬functionMapΪȨ�޵�MAP,functionIdΪ��ҪУ���Ȩ������
     * @param functionMap
     * @param functionId
     * @return
     */
    public static boolean[] functionCheck(Map functionMap,String[] functionId)
    {
    	boolean checkrResult[] = new boolean[functionId.length];
    	boolean functionIdFlag = false;
    	for(int i=0; i < functionId.length; i++)
    	{
    		functionIdFlag = false;
    		if (functionMap.get(functionId[i]) != null)
    		{
    			functionIdFlag = true;
    		}
    		checkrResult[i] = functionIdFlag;
    	}
    	return checkrResult;
    }

    public static boolean contains(String[] params ,String param){
    	for(int i =0;i<=params.length-1;i++){
    		if(param.equals(params[i])){
    			return true;
    		}
    	}
    	return false;
    }

    public static String StringFilter(String  str) { 
		String regEx="[`~!@#$%^&*()+=|{}':;',//[//].<>/?~��@#��%����&*��������+|{}������������������������]";   
     	Pattern   p   =   Pattern.compile(regEx);      
     	Matcher   m   =   p.matcher(str);  
     	return   m.replaceAll("").trim();      
    }  
    
    /**
     * 
     * @Title: sendMsg 
     * @Description: ���Ͷ���
     * @author dq   
     * @date Jan 2, 2014 3:13:32 PM 
     * @version V1.0  
     * @return boolean   
     * @throws Exception 
     * @throws
     */
    public static boolean sendMsg(String msg, String msisdn) throws Exception {
    	if (StringUtils.isEmpty(msg)) {
			throw new Exception("���� msg Ϊ��");
		}
    	if (StringUtils.isEmpty(msisdn)) {
			throw new Exception("���� msg Ϊ��");
		}
    	/* ��ʽ	
    	 **/
    	String sql = 
    			" insert into SMS_SEND_RECORD (SEQUENCENO," +
    			" ORGADDR, DESTADDR, SENDTIME, VALIDTIME, SERVICEID, " +
    			" FEECODE, FEETYPE, DELIVERY, MSGTEXT, MSGFMT, MSGLENGTH, SENDSTATUS, BATCH_ID)" +
    			" values (SEQ_SEND_RECORD.nextval, '1065831897', ?, to_char(sysdate, 'YYYYMMDDHH24MISS'), " +
    			" to_char(sysdate + 1 / 24, 'YYYYMMDDHH24MISS'), 'MF', '0', '01', 0, ?, 8, lengthb(?), 1, 0) ";
    			
    	/* 
    	 * ���� 
    	String sql = 
			" insert into SMS_SEND_RECORD (SEQUENCENO," +
			" ORGADDR, DESTADDR, SENDTIME, VALIDTIME, SERVICEID, " +
			" FEECODE, FEETYPE, DELIVERY, MSGTEXT, MSGFMT, MSGLENGTH, SENDSTATUS, BATCH_ID)" +
			" values (SEQ_SEND_RECORD.nextval, '1065831897', ?, to_char(sysdate, 'YYYYMMDDHH24MISS'), " +
			" to_char(sysdate + 1 / 24, 'YYYYMMDDHH24MISS'), 'MF', '0', '01', 0, ?, 8, lengthb(?), 1, 0) ";
    	 
    	 */
    	
    	Connection connection = DBConnection.Open();
    	PreparedStatement ps = connection.prepareStatement(sql);
    	ps.setString(1, msisdn);
    	ps.setString(2, msg);
    	ps.setString(3, msg);
    	int num = ps.executeUpdate();
		DBConnection.CloseConnection(connection);
		return  num> 0;
    }
    

	/**
	 * �ϴ�����
	 * @param saveName
	 * @param file
	 * @throws Exception 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @returnintNov 4, 2011Administrator
	 */
	public static void uploadFile(String saveName, String filePath, FormFile file) throws Exception{
		uploadFile(saveName, filePath, file.getInputStream());
	}


	/**
	 * �ϴ�����
	 * @param saveName
	 * @param file
	 * @throws Exception 
	 * @returnintNov 4, 2011Administrator
	 */
	public static void uploadFile(String saveName, String filePath, InputStream is) throws Exception{
		try {
			SystemTool.getLoggerForWebApp().info(GlobalConst.ENTER+"�ϴ�������ʼ��");

			int port;
			if("".equals(GlobalConst.FTP_SERVER_PORT))
				port = 21;
			else
				port = Integer.valueOf(GlobalConst.FTP_SERVER_PORT);
			
			FTPTools ftpServer = new FTPTools(GlobalConst.FTP_SERVER_IP, port, GlobalConst.FTP_SERVER_USERNAME, GlobalConst.FTP_SERVER_PASSWORD);
			ftpServer.login();//��¼FTP������
			filePath = GlobalConst.FILE_PATH +File.separator + filePath;//�ϴ���ָ����upload����
			ftpServer.buildList(filePath);
			ftpServer.upFile(is, saveName);
			ftpServer.logout();

			SystemTool.getLoggerForWebApp().info(GlobalConst.EXIT+"�ϴ�����������");
		}catch(Exception e){
			throw new PortalzjException("�ϴ������д�  "+e.toString());
		}
	}
	
	/**
	 * ���ظ���
	 * @param saveName
	 * @param file
	 * @throws Exception 
	 * @returnintNov 4, 2011Administrator
	 */
	public static void downloadFileFromFtp(String newName, String fileName, String filePath, HttpServletResponse response) throws Exception{
		InputStream is = null;
		OutputStream os = null;
		try {
			SystemTool.getLoggerForWebApp().info(GlobalConst.ENTER+"���ظ�����ʼ��");
			int port;
			if("".equals(GlobalConst.FTP_SERVER_PORT))
				port = 21;
			else
				port = Integer.valueOf(GlobalConst.FTP_SERVER_PORT);
			
			FTPTools ftpServer = new FTPTools(GlobalConst.FTP_SERVER_IP, port, GlobalConst.FTP_SERVER_USERNAME, GlobalConst.FTP_SERVER_PASSWORD);
			//System.out.println(ip+":"+p+":"+username+":"+password);
			ftpServer.login();//��¼FTP������
			filePath = GlobalConst.FILE_PATH +File.separator + filePath;//�ϴ���ָ����upload����
			is = ftpServer.readFile(filePath + File.separator + fileName);
			if (is == null) {
				throw new Exception("�ļ������ڣ�");
			}
			response.setContentType("application/octet-stream;charset=iso8859_1");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ new String(newName.getBytes("gb2312"), "ISO8859_1"));
			os = response.getOutputStream();//���ļ�ȷ�����Ե���ʱ�� ��ȡ�����
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ( (bytesRead = is.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);//���ļ����Ƶ������
			}
			is.close();
			ftpServer.logout();//�˳�
			SystemTool.getLoggerForWebApp().info(GlobalConst.EXIT+"���ظ���������");
		}catch(Exception e){
			SystemTool.getLoggerForWebApp().error("::���ظ�������::" + "error��"+e.getMessage());
			throw new Exception("���ظ�������");
		} finally {
			if (is != null) {
				is.close();
			}
			if (os != null) {
				os.close();
			}
		}
	}
	

    /**
     * 
     * @Title: getFunctionName 
     * @Description: TODO(������һ�仰�����������������) 
     * @author dq   
     * @date 2014-5-19 ����04:38:53 
     * @version V1.0  
     * @param @param functionid
     * @param @return    
     * @return String   
     * @throws SQLException 
     * @throws
     */
    public static String getFunctionName(String functionid) throws SQLException{
    	Connection connection = DBConnection.Open();
    	String sql ="select a.name from portal_menu a where a.page_id=?";
    	PreparedStatement ps = connection.prepareStatement(sql);
    	ps.setObject(1, functionid);
    	ResultSet rs = ps.executeQuery();
    	String funcName = "";
    	if (rs != null && rs.next()) {
    		funcName = rs.getString(1);
		}
    	DBConnection.CloseConnection(connection);
    	return funcName;
    }
    
    
	/**
	 * ����ϵͳ������ˮ��־
	 * 
	 * @param operating_srl
	 * @param operating_oper_id
	 * @param operating_type
	 * @param function_id
	 * @param operating_object
	 * @param sys_id
	 * @return void
	 * @author daihb
	 * @param operating_result
	 * @param remark
	 * @throws PortalzjException 
	 */
	@SuppressWarnings("unused")
	public static void createSysOperatingLog(String lgUserID,
			  String lgOptrType,
			  String lgObjType,
			  String lgNotes,
			  String lgStatus,
			  String lgUserType,
			  String lgpcIP,
			  String lgpcName) throws PortalzjException {
		SystemSC sc = new SystemSC();
		AdmUserLog dt = new AdmUserLog();
		try {
  			dt.setLgUserID(lgUserID);
			dt.setLgOptrType(lgOptrType);
			dt.setLgObjType(lgObjType);
			dt.setLgNotes(lgNotes);
			dt.setLgStatus(lgStatus);
			dt.setLgUserType(lgUserType);
			dt.setLgpcIP(lgpcIP);
			dt.setLgpcName(lgpcName);
			sc.insertSysOperatingLog(dt);
		} catch (Exception e) {
			throw new PortalzjException("����ϵͳ������ˮ��־");
		}
	}


    
    
    /**
     * 
     * @Title: getWatermark 
     * @Description: ��Thread�л�ȡsessionData Ȼ���sno���б��� ��ȡ���ɼ��ַ�
     * @author dq   
     * @date 2014-4-18 ����09:06:42 
     * @version V1.0  
     * @param @return    
     * @return String   
     * @throws
     */
    public static String getWatermark(String input){
    	return input ;
    }
    
    /**
     * 
     * @Title: parseWatermark 
     * @Description: ����ˮӡ
     *               0() 1(29) 2(30) 3(31) 4(31 29) 5(31 30) 6(31 31) 7(31 31 29) 8(31 31 30) 9(31 31 31) ������3����
     * @author dq   
     * @date 2014-4-28 ����09:57:24 
     * @version V1.0  
     * @param @return    
     * @return String   
     * @throws
     */
    public static String parseWatermark(String value){
    	if (StringUtils.isEmpty(value)) {
			return "";
		} else {
			String sno="";
			String[] marks = value.split(String.valueOf((char)28));
			for (int i = 0; i < marks.length; i++) {
				if (StringUtils.isEmpty(marks[i])) {
					sno += "0";
				}else {
					int snoPoint = 0;
					for (int j = 0; j < marks[i].length(); j++) {
						int code = marks[i].codePointAt(j);
						switch (code) {
						case 29:
							snoPoint += 1;
							break;
						case 30:
							snoPoint += 2;
							break;
						case 31:
							snoPoint += 3;
							break;
						default:
							break;
						}
					}
					sno += snoPoint;
				}
			}
			return sno;
		}
    }
}
