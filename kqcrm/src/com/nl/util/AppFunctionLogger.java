package com.nl.util;
 
/**
 * ��ע��ֻ������Ϊ�ࡢ������ע�Ͳ�����ע������
 */
public @interface AppFunctionLogger{
	public String name();

	public abstract String type() default "-1";

	public boolean needLog();

	public String objectParam() default "";
}
