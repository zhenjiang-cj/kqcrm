package com.nl.util;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class FormulaPrase {
	private int cursor = 0;
	private StringBuffer pattern = null;
	
	public double regex(String pattern){
		this.pattern = new StringBuffer(pattern);
		return regexCount();
	}
	public double regexs(StringBuffer pattern, String... collections){
		int index = 0;
		int leijia = 0;
		while ((index = pattern.indexOf("?")) >= 0) {
			pattern.replace(index, index+1, collections[leijia]);
			leijia++;
		}
		this.pattern = new StringBuffer(pattern);
		return regexCount();
	}
	/**
	 * 
	 * @Title: regexCount 
	 * @Description: ���չ��� ����
	 * @author dq   
	 * @date 2014-4-2 ����09:55:50 
	 * @version V1.0  
	 * @param pattern ����
	 * @param collections ����
	 * @return double
	 */
	public double regexCount(){
		
		/**
		 * ѭ��������ţ�����ʹ�õ�������
		 */
		for (int i = 0; i < pattern.length(); i++) {
			char c = pattern.charAt(i);
			int dex=0;
			if (c == '(') {
				dex++;//���������Եı�־λ
				for (int j = i+1; j < pattern.length(); j++) {
					if (pattern.charAt(j) == '(' || pattern.charAt(j) == '��') {
						dex++;
					} else if (pattern.charAt(j) == ')' || pattern.charAt(j) == '��') {
						dex--;
					}
					if (dex == 0) {
						double rst = new FormulaPrase().regex(pattern.substring(i+1, j));
						if (String.valueOf(rst).indexOf("E") >= 0) {
							BigDecimal b = new BigDecimal(rst);
							pattern.replace(i, j+1, b.toString());
						} else {
							pattern.replace(i, j+1, String.valueOf(rst));
						}
						break;
					}
				}
			}
		}
		if(!this.isNumber(pattern.toString())) {
			/**
			 * �ȼ���˳���
			 */
			for (cursor = 0; cursor < pattern.length(); cursor++) {
				char c = pattern.charAt(cursor);
				if (c == '/' || c == '%' || c == '*' || c == '/') {
					getNum();
				}
			}
		}
		if(!this.isNumber(pattern.toString())) {
			/**
			 * ����Ӽ�
			 */
			for (cursor = 0; cursor < pattern.length(); cursor++) {
				char c = pattern.charAt(cursor);
				if (cursor != 0 && (c == '+' || c == '-')) {
					getNum();
				}
			}
		}
		return Double.parseDouble(pattern.toString());
	}

	private boolean isNumber(String pattern){
		Pattern p = Pattern.compile("[-]?[0-9]*[.]?[0-9]*");
		return p.matcher(pattern).matches();
	}

	public void getNum(){
		double beNum = 0;
		double num = 0;
		int startIndex = 0;
		int endIndex = 0;
		for (int j = cursor-1; j >= 0; j--) {
			if (this.isNumber(pattern.substring(j, cursor))) {
				if (j != 0) {
					continue;
				}else {
					startIndex = 0;
				}
			} else {
				startIndex = j+1;
			}
			beNum = Double.parseDouble(pattern.substring(startIndex, cursor));
			break;
		}
		for (int j = cursor+1; j < pattern.length(); j++) {
			if (this.isNumber(pattern.substring(cursor+1, j+1))) {
				if (j != pattern.length()-1) {
					continue;
				} else {
					endIndex = pattern.length();
				}
			} else {
				endIndex = j;
			}
			num = Double.parseDouble(pattern.substring(cursor+1, endIndex));
			break;
		}
		double res = 0;
		String resText = "0";
		switch (pattern.charAt(cursor)) {
			case '/':
				res = beNum/num;
				break;
			case '%':
				res = beNum%num;
				break;
			case '*':
				res = beNum*num;
				break;
			case '+':
				res = beNum+num;
				break;
			case '-':
				res = beNum-num;
				break;
			default:
				break;
		}
		if (String.valueOf(res).indexOf("E") >= 0) {
			BigDecimal b = new BigDecimal(res);
			resText = b.toString();
		} else {
			resText = String.valueOf(res);
		}
		pattern.replace(startIndex, endIndex, resText);
		/*
		 * ���α� ����ԭλ��
		 * ����  1+1*3*5  ����  1*3=3��õ�1+3*5  �α�����3��λ��
		 */
		cursor = startIndex + resText.length()-1;
	}
}
