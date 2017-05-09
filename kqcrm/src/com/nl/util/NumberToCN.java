/**
 * 
 */
package com.nl.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @Description: 数字转换为汉语中人民币的大写
 * @author CJ
 * @version 1.0
 * Nov 4, 2016
 * -------------------------------------------
 * @History:
 * 修订日期    修订人    版本    描述
 * 
 */
public class NumberToCN {
    /**
     * 汉语中数字大写
     */
    private static final String[] CN_UPPER_NUMBER = { "零", "壹", "贰", "叁", "肆",
            "伍", "陆", "柒", "捌", "玖" };
    /**
     * 汉语中货币单位大写，这样的设计类似于占位符
     */
    private static final String[] CN_UPPER_MONETRAY_UNIT = { "分", "角", "元",
            "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "兆", "拾",
            "佰", "仟" };
    /**
     * 特殊字符：整
     */
    private static final String CN_FULL = "整";
    /**
     * 特殊字符：负
     */
    private static final String CN_NEGATIVE = "负";
    /**
     * 金额的精度，默认值为2
     */
    private static final int MONEY_PRECISION = 2;
    /**
     * 特殊字符：零元整
     */
    private static final String CN_ZEOR_FULL = "零元" + CN_FULL;

    static String[] units = { "", "十", "百", "千", "万", "十万", "百万", "千万", "亿", 	"十亿", "百亿", "千亿", "万亿" };
    static char[] numArray = { '零', '一', '二', '三', '四', '五', '六', '七', '八', '九' };
    
    /**
     * 把输入的金额转换为汉语中人民币的大写
     * 
     * @param numberOfMoney
     *            输入的金额
     * @return 对应的汉语大写
     */
    public static String number2CNMontrayUnit(BigDecimal numberOfMoney) {
        StringBuffer sb = new StringBuffer();
        // -1, 0, or 1 as the value of this BigDecimal is negative, zero, or
        // positive.
        int signum = numberOfMoney.signum();
        // 零元整的情况
        if (signum == 0) {
            return CN_ZEOR_FULL;
        }
        //这里会进行金额的四舍五入
        long number = numberOfMoney.movePointRight(MONEY_PRECISION)
                .setScale(0, 4).abs().longValue();
        // 得到小数点后两位值
        long scale = number % 100;
        int numUnit = 0;
        int numIndex = 0;
        boolean getZero = false;
        // 判断最后两位数，一共有四中情况：00 = 0, 01 = 1, 10, 11
        if (!(scale > 0)) {
            numIndex = 2;
            number = number / 100;
            getZero = true;
        }
        if ((scale > 0) && (!(scale % 10 > 0))) {
            numIndex = 1;
            number = number / 10;
            getZero = true;
        }
        int zeroSize = 0;
        while (true) {
            if (number <= 0) {
                break;
            }
            // 每次获取到最后一个数
            numUnit = (int) (number % 10);
            if (numUnit > 0) {
                if ((numIndex == 9) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[6]);
                }
                if ((numIndex == 13) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[10]);
                }
                sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                getZero = false;
                zeroSize = 0;
            } else {
                ++zeroSize;
                if (!(getZero)) {
                    sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                }
                if (numIndex == 2) {
                    if (number > 0) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                    }
                } else if (((numIndex - 2) % 4 == 0) && (number % 1000 > 0)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                }
                getZero = true;
            }
            // 让number每次都去掉最后一个数
            number = number / 10;
            ++numIndex;
        }
        // 如果signum == -1，则说明输入的数字为负数，就在最前面追加特殊字符：负
        if (signum == -1) {
            sb.insert(0, CN_NEGATIVE);
        }
        // 输入的数字小数点后两位为"00"的情况，则要在最后追加特殊字符：整
        if (!(scale > 0)) {
            sb.append(CN_FULL);
        }
        return sb.toString();
    }

    public static String formatYearCN(int num) {
    	char[] val = String.valueOf(num).toCharArray();
    	int len = val.length;
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < len; i++) {
    	String m = val[i] + "";
    	int n = Integer.valueOf(m);
    	boolean isZero = n == 0;
    	String unit = units[(len - 1) - i];
    	if (isZero) {
    	if ('0' == val[i - 1]) {
    	// not need process if the last digital bits is 0
    	continue;
    	} else {
    	// no unit for 0
    	sb.append(numArray[n]);
    	}
    	} else {
    	sb.append(numArray[n]);
    	sb.append(unit);
    	}
    	}
    	return sb.toString();
    }
    
    public static String formatDateCN(String param)
    {
    	int num;
    	String result = "";
    	
    	if(GlobalUtil.isNumeric(param))
    	{
    		char [] stringArr = param.toCharArray();
    		
    		//如果是年份
    		if(stringArr.length==4)
    		{
	    		for(int i=0;i<stringArr.length;i++)
	    		{    			    	
		    		num = Integer.parseInt(String.valueOf(stringArr[i]));
		    		result += toCN(num);
	    		}
    		}else{
    			num = Integer.parseInt(String.valueOf(param));
    			result += toCN(num);
    		}
    	}
    	
    	return result;
    }
    
    private static String toCN(int num)
    {
    	String result="";
    	
    	switch (num) {
		
	    	case 0: {
	    		result+= "零";
	    		break;
	    	}
	    	case 1: {
	    		result+= "一";
	    		break;
	    	}
	
	    	case 2: {
	    		result+= "二";
	    		break;
	    	}
	    	
	    	case 3: {
	    		result+= "三";
	    		break;
	    	}
	
	    	case 4: {
	    		result+= "四";
	    		break;
	    	}
	    	
	    	case 5: {
	    		result+= "五";
	    		break;
	    	}
	    	
	    	case 6: {
	    		result+= "六";
	    		break;
	    	}
	    	
	    	case 7: {
	    		result+= "七";
	    		break;
	    	}
	    	
	    	case 8: {
	    		result+= "八";
	    		break;
	    	}
	    	
	    	case 9: {
	    		result+= "九";
	    		break;
	    	}
	    	
	    	case 10: {
	    		result+= "十";
	    		break;
	    	}
	    	
	    	case 11: {
	    		result+= "十一";
	    		break;
	    	}
	    	
	    	case 12: {
	    		result+= "十二";
	    		break;
	    	}
	    	
			case 13: {
	    		result+= "十三";
	    		break;
	    	}
	    	
	    	case 14: {
	    		result+= "十四";
	    		break;
	    	}
	    	
	    	case 15: {
	    		result+= "十五";
	    		break;
	    	}
	    	
	    	case 16: {
	    		result+= "十六";
	    		break;
	    	}
	    	
	    	case 17: {
	    		result+= "十七";
	    		break;
	    	}
	    	
	    	case 18: {
	    		result+= "十八";
	    		break;
	    	}
	    	
	    	case 19: {
	    		result+= "十九";
	    		break;
	    	}
	    	
	    	case 20: {
	    		result+= "二十";
	    		break;
	    	}
	    	
	    	case 21: {
	    		result+= "二十一";
	    		break;
	    	}
	    	
	    	case 22: {
	    		result+= "二十二";
	    		break;
	    	}
	    	
	    	case 23: {
	    		result+= "二十三";
	    		break;
	    	}
	    	
	    	case 24: {
	    		result+= "二十四";
	    		break;
	    	}
	    	
	    	case 25: {
	    		result+= "二十五";
	    		break;
	    	}
	    	
	    	case 26: {
	    		result+= "二十六";
	    		break;
	    	}
	    	
	    	case 27: {
	    		result+= "二十七";
	    		break;
	    	}
	    	
	    	case 28: {
	    		result+= "二十八";
	    		break;
	    	}
	    	
	    	case 29: {
	    		result+= "二十九";
	    		break;
	    	}
	    	
	    	case 30: {
	    		result+= "三十";
	    		break;
	    	}
	    	
	    	case 31: {
	    		result+= "三十一";
	    		break;
	    	}
	
		}
	
		return result;
    }
    
    /*public static void main(String[] args) {
        double money = 100;
        BigDecimal numberOfMoney = new BigDecimal(money);
        String s = NumberToCN.number2CNMontrayUnit(numberOfMoney);
        System.out.println("你输入的金额为：【"+ money +"】   #--# [" +s.toString()+"]");
        
        String num = "2016";
        String numStr = formatDateCN(num);
        System.out.println("num= " + num + ", convert result: " + numStr);
        
        int num1 = 8;
        String numStr1 = formatYearCN(num1);
        System.out.println("num1= " + num1 + ", convert result: " + numStr1);
        
        System.out.println(Math.ceil(money)/100000);
        
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println(df.format(Math.round(money)/10000));
    }*/
}
