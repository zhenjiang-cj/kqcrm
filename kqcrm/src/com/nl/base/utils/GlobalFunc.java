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
 * Copyright: Copyright (c) 2011 新大陆软件工程有限公司All rights reserved
 * </p>.
 * <p>
 * FileName: GlobalFunc.java
 * </p>
 * <p>
 * Description: 公用方法
 * </p>
 * <p>
 * Date : 2011-01-10
 * </p>
 * <p>
 * 修改记录:
 * </p>
 * 
 * @author 周科
 * @version 0.1
 */
public class GlobalFunc {
	
	/**
	 * dwz操作结果返回
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
	 * 中文转为unicode
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
     * 格式化日期对象
     * 
     * @param date
     *            日期
     * @param model
     *            格式字符串(如:"yyyy-MM-dd HH:mm:ss")
     * @return 结果字符串
     */
    public static String dateFormat(Date date, String model) {
        SimpleDateFormat df = new SimpleDateFormat(model);
        return df.format(date);
    }

    /**
     * 初始化字符串
     * 
     * @param o
     *            格式化对象
     * @param sDef
     *            对象为null时的返回 sDef
     * @return 结果字符串
     */
    public static String initStr(Object o, String sDef) {
        if (o != null&&!"null".equals(o)&&!"".equals(o)) {
            return o.toString();
        } else {
            return sDef;
        }
    }

    /**
     * 初始化数字
     * 
     * @param s
     *            格式化对象
     * @param nDef
     *            对象为null时的返回 nDef
     * @return 结果字符串
     */
    public static int initInt(String s, int nDef) {
        if (s != null && !"".equals(s)) {
            return Integer.parseInt(s);
        } else {
            return nDef;
        }
    }

    /**
     * 初始化数字
     * 
     * @param s
     *            格式化对象
     * @param nDef
     *            对象为null时的返回 nDef
     * @return 结果字符串
     */
    public static float initFloat(String s, float nDef) {
        if (s != null && !"".equals(s)) {
            return Float.parseFloat(s);
        } else {
            return nDef;
        }
    }

    /**
     * 初始化数字
     * 
     * @param s
     *            格式化对象
     * @param nDef
     *            对象为null时的返回 nDef
     * @return 结果字符串
     */
    public static long initLong(String s, long nDef) {
        if (s != null && !"".equals(s)) {
            return Long.parseLong(s);
        } else {
            return nDef;
        }
    }

    /**
     * 格式化字符串
     * 
     * @param o
     *            格式化对象
     * @return 结果字符串 对象为null时的返回“”；
     */
    public static String initStr(Object o) {
        return initStr(o, "");
    }

    /**
     * 根据DT的列表产生select列表(HTML)，可额外增加一个Option（比如'全选'或者'请选择'等等）
     * 
     * @param hashDtList
     *            Hashtable 对象(DT)列表(用HashTable存储)
     * @param strValueField
     *            String select中的value对应的Dt字段名
     * @param strTextField
     *            String select中显示文本对应的Dt字段名
     * @param strListName
     *            String select名称
     * @param strExtraText
     *            String 额外增加的Option文字
     * @param strExtraValue
     *            String 额外增加的Option对应的Value
     * @param strDefault
     *            String select中的默认选择的value(null表示无默认)
     * @param b_HasOnChange
     *            boolean select是否有Change事件(如果有,则名称为F_select名称_OnChange())
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
     * 根据DT的列表产生select列表(HTML)，可额外增加一个Option（比如'全选'或者'请选择'等等）
     * 
     * @param listDt
     *            List 对象(DT)列表
     * @param strValueField
     *            String select中的value对应的Dt字段名
     * @param strTextField
     *            String select中显示文本对应的Dt字段名
     * @param strListName
     *            String select名称
     * @param strExtraText
     *            String 额外增加的Option文字
     * @param strExtraValue
     *            String 额外增加的Option对应的Value
     * @param strDefault
     *            String select中的默认选择的value(null表示无默认)
     * @param b_HasOnChange
     *            boolean select是否有Change事件(如果有,则名称为F_select名称_OnChange())
     * @return String
     */
    public static String getDtListSelect(List listDt, String strValueField, String strTextField, String strListName, String strExtraText,
            String strExtraValue, String strDefault, boolean b_HasOnChange) {
        return getDtListSelect(listDt, strValueField, strTextField, strListName, null, strExtraText, strExtraValue, strDefault, b_HasOnChange);
    }

    /**
     * 根据DT的列表产生select列表(HTML)，可额外增加一个Option（比如'全选'或者'请选择'等等）
     * 
     * @param listDt
     *            List 对象(DT)列表
     * @param strValueField
     *            String select中的value对应的Dt字段名
     * @param strTextField
     *            String select中显示文本对应的Dt字段名
     * @param strListName
     *            String select名称
     * @param strListType
     *            String select类型 普通单选Select:null;展开下拉框:multiple
     * @param strExtraText
     *            String 额外增加的Option文字
     * @param strExtraValue
     *            String 额外增加的Option对应的Value
     * @param strDefault
     *            String select中的默认选择的value(null表示无默认)
     * @param b_HasOnChange
     *            boolean select是否有Change事件(如果有,则名称为F_select名称_OnChange())
     * @return String
     */
    public static String getDtListSelect(List listDt, String strValueField, String strTextField, String strListName, String strListType,
            String strExtraText, String strExtraValue, String strDefault, boolean b_HasOnChange) {
        Object dtObj; // 定义dt信息
        String strList = "<select name='" + strListName + "' ";

        // 判断是否要设定OnChange事件
        if (b_HasOnChange) {
            strList += (" onChange='F_" + strListName + "OnChange(this);'");
        }

        if (null != strListType) {
            strList += (" " + strListType);
        }

        strList += ">";

        // 考虑是否增加额外选项
        if (strExtraText != null) {
            if (strExtraValue != null) {
                if (listDt.size() > 0) { // 是否仅有一条记录

                    // 是否默认选择
                    if ((strDefault != null) && strDefault.equals(strExtraValue)) {
                        strList += ("\n\t<option value='" + strExtraValue + "' selected>" + strExtraText + "</option>");
                    } else {
                        strList += ("\n\t<option value='" + strExtraValue + "' >" + strExtraText + "</option>\n");
                    }
                }
            }
        }

        // 逐个取出数据DT进行处理
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
                    // 数据域对应的字段名称
                    if (fld.getName().equals(strValueField)) {
                        strValue = fld.get(dtObj).toString();
                    }

                    // 文本域对应的字段名称
                    if (fld.getName().equals(strTextField)) {
                        strText = fld.get(dtObj).toString();
                    }
                } catch (Exception e) {
                    return null;
                }
            }

            if (strValue != null) {
                // 是否默认选择
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
     * 格式化金额(厘)数值为两位小数(元)
     * 
     * @param long
     *            num 金额(厘)
     * @return 金额(元)
     */
    public static String numberFormat(long num) {
        return numberFormatDouble(num);
    }

    /**
     * 格式化金额(厘)数值为两位小数(元)
     * 
     * @param String
     *            num 金额(厘)
     * @return 金额(元)
     */
    public static String numberFormat(String num) {
        return numberFormatDouble(Double.parseDouble(num));
    }

    /**
     * 格式化金额(厘)数值为两位小数(元)
     * 
     * @param double
     *            num 金额(厘)
     * @return 金额(元)
     */
    public static String numberFormatDouble(double num) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);

        double b = (num / 1000);

        return nf.format(b);
    }

    /**
     * 格式化单位元的小数为单位为厘
     * 
     * @param String
     *            sNum 金额(元)
     * @return 金额(厘)
     */
    public static long unDonumberFormat(String sNum) {
        return unDonumberFormat(sNum, "1000", 3);
    }

    /**
     * 格式化单位元的小数为单位为厘
     * 
     * @param sNum
     *            金额(元)
     * @param multiplicator
     *            乘数(取值仅可以为10,100,1000...的字符)
     * @return 金额(厘)
     */
    public static long unDonumberFormat(String sNum, String multiplicator) {
        return unDonumberFormat(sNum, multiplicator, 3);
    }

    /**
     * 格式化单位元的小数为单位为厘
     * 
     * @param sNum
     *            金额(元)
     * @param multiplicator
     *            乘数(取值仅可以为10,100,1000...的字符)
     * @param flag
     *            操作标记(3 乘)
     * @return 金额(厘)
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
     * 加减乘除计算
     * 
     * @param num1
     *            运算数1
     * @param num2
     *            运算数2
     * @param scale
     *            保留小数位数
     * @param roundingMode
     *            舍入标度
     * @param flag
     *            (1 加;2 减;3 乘;4 除;)
     * @return BigDecimal 结果
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
     * 乘法计算
     * 
     * @param num
     *            乘数
     * @param multiplicator
     *            乘积
     * @param scale
     *            保留小数位数
     * @param roundingMode
     *            舍入标度
     * @return BigDecimal 结果
     */
    public static double multiplyNumber(String num, String multiplicator, int scale, int roundingMode) {
        BigDecimal outDecimal = null;
        outDecimal = operNumber(num, multiplicator, scale, roundingMode, 3);
        return outDecimal.doubleValue();
    }

    /**
     * 乘法计算(默认四舍五入)
     * 
     * @param num
     *            乘数
     * @param multiplicator
     *            乘积
     * @param scale
     *            保留小数位数
     * @return BigDecimal 结果
     */
    public static double multiplyNumber(String num, String multiplicator, int scale) {
        BigDecimal outDecimal = null;
        outDecimal = operNumber(num, multiplicator, scale, 5, 3);
        return outDecimal.doubleValue();
    }

    /**
     * 乘法计算(默认保留两位小数点,四舍五入)
     * 
     * @param num
     *            乘数
     * @param multiplicator
     *            乘积
     * @return double 结果
     */
    public static double multiplyNumber(String num, String multiplicator) {
        BigDecimal outDecimal = null;
        outDecimal = operNumber(num, multiplicator, 2, 5, 3);
        return outDecimal.doubleValue();
    }

    /**
     * 除法计算
     * 
     * @param num
     *            除数
     * @param dividend
     *            被除数
     * @param scale
     *            保留小数位数
     * @param roundingMode
     *            舍入标度
     * @return double 结果
     */
    public static double divideNumber(String num, String dividend, int scale, int roundingMode) {
        BigDecimal outDecimal = null;
        outDecimal = operNumber(num, dividend, scale, roundingMode, 4);
        return outDecimal.doubleValue();
    }

    /**
     * 除法计算(默认四舍五入)
     * 
     * @param num
     *            除数
     * @param dividend
     *            被除数
     * @param scale
     *            保留小数位数
     * @return double 结果
     */
    public static double divideNumber(String num, String dividend, int scale) {
        BigDecimal outDecimal = null;
        outDecimal = operNumber(num, dividend, scale, 5, 4);
        return outDecimal.doubleValue();
    }

    /**
     * 除法计算(默认保留两位小数点,四舍五入)
     * 
     * @param num
     *            除数
     * @param dividend
     *            被除数
     * @param roundingMode
     *            保留小数位数
     * @return double 结果
     */
    public static double divideNumber(String num, String dividend) {
        BigDecimal outDecimal = null;
        outDecimal = operNumber(num, dividend, 2, 5, 4);
        return outDecimal.doubleValue();
    }

    /**
     * 截取字符串
     * 
     * @param inStr
     *            字符串
     * @param length
     *            期望输出长度
     * @param ctemp
     *            填充字符
     * @return 字符串
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
     * 按格式补齐字符串
     * 
     * @param inStr
     *            字符串
     * @param length
     *            期望输出长度
     * @param ctemp
     *            填充字符
     * @param lrFlag
     *            补位方向标记 (0 从左边补位;1 从右边补位)
     * @return 字符串
     */
    public static String formatStr(String inStr, int length, char ctemp, int lrFlag) {
        String temp = null;
        if (inStr != null && inStr.trim().length() > 0) {
            temp = inStr.trim();
            int strLen = temp.getBytes().length;
            if (strLen < length) {
                for (int i = 0; i < length - strLen; i++) {
                    if (lrFlag == 0) { // 左补齐
                        temp = ctemp + temp;
                    } else { // 右补齐
                        temp += ctemp;
                    }
                }
            }
        }
        return temp;
    }

    /**
     * 按格式补齐字符串
     * 
     * @param inStr
     *            字符串
     * @param length
     *            期望输出长度
     * @param ctemp
     *            填充字符
     * @param lrFlag
     *            补位方向标记 (0 从左边补位;1 从右边补位)
     * @param remLrFlag
     *            取指定长度方向标记 (0 从左边取;1 从右边取)
     * @return 字符串
     */
    public static String formatStr(String inStr, int length, char ctemp, int lrFlag, int remLrFlag) {
        String temp = inStr.trim();
        int strLen = temp.getBytes().length;
        if (strLen < length) {
            for (int i = 0; i < length - strLen; i++) {
                if (lrFlag == 0) { // 左补齐
                    temp = ctemp + temp;
                } else { // 右补齐
                    temp += ctemp;
                }
            }
        } else {
            if (remLrFlag == 0) { // 从左取值
                temp = getBytes(temp.getBytes(), 0, length);
            } else { // 从右取值
                temp = getBytes(temp.getBytes(), strLen - length, strLen);
            }
        }

        return temp;
    }

    /**
     * 获取byte[]的其中一段值
     * 
     * @param pBytes
     *            字节数组
     * @param startNum
     *            取值开始位置
     * @param endNum
     *            取值结束位置
     * @return 返回字符串(已执行过trim)
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
     * 判断传入字符串是否为NULL ,如果为NULL,返回 defaultValue
     * 
     * @param str
     *            要判断的字符串
     * @param defaultValue
     *            默认值
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
     * 判断传入字符串是否为空，若为空则返回""
     * 
     * @author yangsy
     * @param str
     *            判断字符串
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
     * 替换字符串
     * 
     * @param mainString
     *            String 替换的字符串
     * @param oldString
     *            String 被替换的值
     * @param newString
     *            String 替换后的新值
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
     * 获取utf8格式字符串
     * 
     * @param s
     *            String 原字符串
     * @return String 转换后字符串
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
     * 返回java.sql.Date
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
     * 获取当前月的一号是第几周、周几
     * 
     * @param date
     *            当前时间
     * @param format
     *            Calendar.WEEK_OF_MONTH Calendar.DAY_OF_WEEK
     * @return Calendar.WEEK_OF_MONTH 返回的是当月第几周 Calendar.DAY_OF_WEEK 返回的是周几
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
     * 获取当前是的是当月第几周,
     * 
     * @param date
     *            当前时间
     * @param format
     *            Calendar.WEEK_OF_MONTH Calendar.DAY_OF_WEEK
     * @return Calendar.WEEK_OF_MONTH 返回的是当月第几周 Calendar.DAY_OF_WEEK 返回的是周几
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
     * 检查输入的字符串是否是全数字（整数）
     * 
     * @param str
     *            输入字符串
     * @return true：是；false：否
     */
    public static boolean isInteger(String str) {
        Matcher m = INTEGER.matcher(str);
        return m.matches();
    }

    /**
     * 取模板生效时间和当前时间的比较(add by gej)
     * 
     * @param dateA
     *            模板的生效时间
     * @param dateB
     *            当前数据库的时间
     * @return 标志 1 模板生效时间小于当前时间 2 模板生效时间大于当前时间
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
     * 将传递的字符串分割成数组，去重
     * 
     * @param str
     *            传入字符串
     * @param aff
     *            分割符
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
        // 打印 验证是否去重
        for (int i = 0; i < arrayList.size(); i++) {
            single2[i] = (String) arrayList.get(i);
            // 遍历新数组,打印其中所有元素
        }
        return single2;
    }

    /**
     * 判断传入的key在集合中是否存在
     * 
     * @param strKey
     *            String key
     * @param strKeySet
     *            String set由;分割
     * @return true要被过滤，false不要被过滤
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
     * 获取本机ip
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
     * 转化为double型
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
     * 判断字符串是否是整数
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
     * 判断字符串是否是浮点数
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
     * 把字符串中的英文双引号转换为页面显示的双引号
     * 
     * @param str
     * @return
     */
    public static String convertHtml(String str) {
        return (null != str ? str.replaceAll("\"", "&quot;") : "");
    }
    
    /**
     * 把double类型的数字保留对应的小数位
     * @param number 需要保留小数位的数
     * @param formatStr 格式化参数，'#0.0'表示保留一位小数，'#0.00'表示保留两位小数，
     * @return String
     */
    public static String formatDouble(double number,String formatStr) 
    {
    	DecimalFormat df = new DecimalFormat(formatStr);
        return df.format(number);
    }
    
    //将LIST转换为JSON对象
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
     * 权限校验，functionMap为权限的MAP,functionId为需要校验的权限数组
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
		String regEx="[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";   
     	Pattern   p   =   Pattern.compile(regEx);      
     	Matcher   m   =   p.matcher(str);  
     	return   m.replaceAll("").trim();      
    }  
    
    /**
     * 
     * @Title: sendMsg 
     * @Description: 发送短信
     * @author dq   
     * @date Jan 2, 2014 3:13:32 PM 
     * @version V1.0  
     * @return boolean   
     * @throws Exception 
     * @throws
     */
    public static boolean sendMsg(String msg, String msisdn) throws Exception {
    	if (StringUtils.isEmpty(msg)) {
			throw new Exception("参数 msg 为空");
		}
    	if (StringUtils.isEmpty(msisdn)) {
			throw new Exception("参数 msg 为空");
		}
    	/* 正式	
    	 **/
    	String sql = 
    			" insert into SMS_SEND_RECORD (SEQUENCENO," +
    			" ORGADDR, DESTADDR, SENDTIME, VALIDTIME, SERVICEID, " +
    			" FEECODE, FEETYPE, DELIVERY, MSGTEXT, MSGFMT, MSGLENGTH, SENDSTATUS, BATCH_ID)" +
    			" values (SEQ_SEND_RECORD.nextval, '1065831897', ?, to_char(sysdate, 'YYYYMMDDHH24MISS'), " +
    			" to_char(sysdate + 1 / 24, 'YYYYMMDDHH24MISS'), 'MF', '0', '01', 0, ?, 8, lengthb(?), 1, 0) ";
    			
    	/* 
    	 * 测试 
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
	 * 上传附件
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
	 * 上传附件
	 * @param saveName
	 * @param file
	 * @throws Exception 
	 * @returnintNov 4, 2011Administrator
	 */
	public static void uploadFile(String saveName, String filePath, InputStream is) throws Exception{
		try {
			SystemTool.getLoggerForWebApp().info(GlobalConst.ENTER+"上传附件开始。");

			int port;
			if("".equals(GlobalConst.FTP_SERVER_PORT))
				port = 21;
			else
				port = Integer.valueOf(GlobalConst.FTP_SERVER_PORT);
			
			FTPTools ftpServer = new FTPTools(GlobalConst.FTP_SERVER_IP, port, GlobalConst.FTP_SERVER_USERNAME, GlobalConst.FTP_SERVER_PASSWORD);
			ftpServer.login();//登录FTP服务器
			filePath = GlobalConst.FILE_PATH +File.separator + filePath;//上传到指定的upload包中
			ftpServer.buildList(filePath);
			ftpServer.upFile(is, saveName);
			ftpServer.logout();

			SystemTool.getLoggerForWebApp().info(GlobalConst.EXIT+"上传附件结束。");
		}catch(Exception e){
			throw new PortalzjException("上传附件有错！  "+e.toString());
		}
	}
	
	/**
	 * 下载附件
	 * @param saveName
	 * @param file
	 * @throws Exception 
	 * @returnintNov 4, 2011Administrator
	 */
	public static void downloadFileFromFtp(String newName, String fileName, String filePath, HttpServletResponse response) throws Exception{
		InputStream is = null;
		OutputStream os = null;
		try {
			SystemTool.getLoggerForWebApp().info(GlobalConst.ENTER+"下载附件开始。");
			int port;
			if("".equals(GlobalConst.FTP_SERVER_PORT))
				port = 21;
			else
				port = Integer.valueOf(GlobalConst.FTP_SERVER_PORT);
			
			FTPTools ftpServer = new FTPTools(GlobalConst.FTP_SERVER_IP, port, GlobalConst.FTP_SERVER_USERNAME, GlobalConst.FTP_SERVER_PASSWORD);
			//System.out.println(ip+":"+p+":"+username+":"+password);
			ftpServer.login();//登录FTP服务器
			filePath = GlobalConst.FILE_PATH +File.separator + filePath;//上传到指定的upload包中
			is = ftpServer.readFile(filePath + File.separator + fileName);
			if (is == null) {
				throw new Exception("文件不存在！");
			}
			response.setContentType("application/octet-stream;charset=iso8859_1");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ new String(newName.getBytes("gb2312"), "ISO8859_1"));
			os = response.getOutputStream();//等文件确定可以到处时才 获取输出流
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ( (bytesRead = is.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);//将文件复制到输出流
			}
			is.close();
			ftpServer.logout();//退出
			SystemTool.getLoggerForWebApp().info(GlobalConst.EXIT+"下载附件结束。");
		}catch(Exception e){
			SystemTool.getLoggerForWebApp().error("::下载附件出错！::" + "error："+e.getMessage());
			throw new Exception("下载附件出错！");
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
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @author dq   
     * @date 2014-5-19 下午04:38:53 
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
	 * 创建系统操作流水日志
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
			throw new PortalzjException("创建系统操作流水日志");
		}
	}


    
    
    /**
     * 
     * @Title: getWatermark 
     * @Description: 从Thread中获取sessionData 然后对sno进行编码 获取不可见字符
     * @author dq   
     * @date 2014-4-18 上午09:06:42 
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
     * @Description: 解析水印
     *               0() 1(29) 2(30) 3(31) 4(31 29) 5(31 30) 6(31 31) 7(31 31 29) 8(31 31 30) 9(31 31 31) 类似于3进制
     * @author dq   
     * @date 2014-4-28 上午09:57:24 
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
