/**
 * 
 */
package com.nl.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @Description: ����ת��Ϊ����������ҵĴ�д
 * @author CJ
 * @version 1.0
 * Nov 4, 2016
 * -------------------------------------------
 * @History:
 * �޶�����    �޶���    �汾    ����
 * 
 */
public class NumberToCN {
    /**
     * ���������ִ�д
     */
    private static final String[] CN_UPPER_NUMBER = { "��", "Ҽ", "��", "��", "��",
            "��", "½", "��", "��", "��" };
    /**
     * �����л��ҵ�λ��д�����������������ռλ��
     */
    private static final String[] CN_UPPER_MONETRAY_UNIT = { "��", "��", "Ԫ",
            "ʰ", "��", "Ǫ", "��", "ʰ", "��", "Ǫ", "��", "ʰ", "��", "Ǫ", "��", "ʰ",
            "��", "Ǫ" };
    /**
     * �����ַ�����
     */
    private static final String CN_FULL = "��";
    /**
     * �����ַ�����
     */
    private static final String CN_NEGATIVE = "��";
    /**
     * ���ľ��ȣ�Ĭ��ֵΪ2
     */
    private static final int MONEY_PRECISION = 2;
    /**
     * �����ַ�����Ԫ��
     */
    private static final String CN_ZEOR_FULL = "��Ԫ" + CN_FULL;

    static String[] units = { "", "ʮ", "��", "ǧ", "��", "ʮ��", "����", "ǧ��", "��", 	"ʮ��", "����", "ǧ��", "����" };
    static char[] numArray = { '��', 'һ', '��', '��', '��', '��', '��', '��', '��', '��' };
    
    /**
     * ������Ľ��ת��Ϊ����������ҵĴ�д
     * 
     * @param numberOfMoney
     *            ����Ľ��
     * @return ��Ӧ�ĺ����д
     */
    public static String number2CNMontrayUnit(BigDecimal numberOfMoney) {
        StringBuffer sb = new StringBuffer();
        // -1, 0, or 1 as the value of this BigDecimal is negative, zero, or
        // positive.
        int signum = numberOfMoney.signum();
        // ��Ԫ�������
        if (signum == 0) {
            return CN_ZEOR_FULL;
        }
        //�������н�����������
        long number = numberOfMoney.movePointRight(MONEY_PRECISION)
                .setScale(0, 4).abs().longValue();
        // �õ�С�������λֵ
        long scale = number % 100;
        int numUnit = 0;
        int numIndex = 0;
        boolean getZero = false;
        // �ж������λ����һ�������������00 = 0, 01 = 1, 10, 11
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
            // ÿ�λ�ȡ�����һ����
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
            // ��numberÿ�ζ�ȥ�����һ����
            number = number / 10;
            ++numIndex;
        }
        // ���signum == -1����˵�����������Ϊ������������ǰ��׷�������ַ�����
        if (signum == -1) {
            sb.insert(0, CN_NEGATIVE);
        }
        // ���������С�������λΪ"00"���������Ҫ�����׷�������ַ�����
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
    		
    		//��������
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
	    		result+= "��";
	    		break;
	    	}
	    	case 1: {
	    		result+= "һ";
	    		break;
	    	}
	
	    	case 2: {
	    		result+= "��";
	    		break;
	    	}
	    	
	    	case 3: {
	    		result+= "��";
	    		break;
	    	}
	
	    	case 4: {
	    		result+= "��";
	    		break;
	    	}
	    	
	    	case 5: {
	    		result+= "��";
	    		break;
	    	}
	    	
	    	case 6: {
	    		result+= "��";
	    		break;
	    	}
	    	
	    	case 7: {
	    		result+= "��";
	    		break;
	    	}
	    	
	    	case 8: {
	    		result+= "��";
	    		break;
	    	}
	    	
	    	case 9: {
	    		result+= "��";
	    		break;
	    	}
	    	
	    	case 10: {
	    		result+= "ʮ";
	    		break;
	    	}
	    	
	    	case 11: {
	    		result+= "ʮһ";
	    		break;
	    	}
	    	
	    	case 12: {
	    		result+= "ʮ��";
	    		break;
	    	}
	    	
			case 13: {
	    		result+= "ʮ��";
	    		break;
	    	}
	    	
	    	case 14: {
	    		result+= "ʮ��";
	    		break;
	    	}
	    	
	    	case 15: {
	    		result+= "ʮ��";
	    		break;
	    	}
	    	
	    	case 16: {
	    		result+= "ʮ��";
	    		break;
	    	}
	    	
	    	case 17: {
	    		result+= "ʮ��";
	    		break;
	    	}
	    	
	    	case 18: {
	    		result+= "ʮ��";
	    		break;
	    	}
	    	
	    	case 19: {
	    		result+= "ʮ��";
	    		break;
	    	}
	    	
	    	case 20: {
	    		result+= "��ʮ";
	    		break;
	    	}
	    	
	    	case 21: {
	    		result+= "��ʮһ";
	    		break;
	    	}
	    	
	    	case 22: {
	    		result+= "��ʮ��";
	    		break;
	    	}
	    	
	    	case 23: {
	    		result+= "��ʮ��";
	    		break;
	    	}
	    	
	    	case 24: {
	    		result+= "��ʮ��";
	    		break;
	    	}
	    	
	    	case 25: {
	    		result+= "��ʮ��";
	    		break;
	    	}
	    	
	    	case 26: {
	    		result+= "��ʮ��";
	    		break;
	    	}
	    	
	    	case 27: {
	    		result+= "��ʮ��";
	    		break;
	    	}
	    	
	    	case 28: {
	    		result+= "��ʮ��";
	    		break;
	    	}
	    	
	    	case 29: {
	    		result+= "��ʮ��";
	    		break;
	    	}
	    	
	    	case 30: {
	    		result+= "��ʮ";
	    		break;
	    	}
	    	
	    	case 31: {
	    		result+= "��ʮһ";
	    		break;
	    	}
	
		}
	
		return result;
    }
    
    /*public static void main(String[] args) {
        double money = 100;
        BigDecimal numberOfMoney = new BigDecimal(money);
        String s = NumberToCN.number2CNMontrayUnit(numberOfMoney);
        System.out.println("������Ľ��Ϊ����"+ money +"��   #--# [" +s.toString()+"]");
        
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
