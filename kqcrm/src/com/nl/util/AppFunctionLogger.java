package com.nl.util;
 
/**
 * 该注释只可以作为类、方法、注释参数的注释类型
 */
public @interface AppFunctionLogger{
	public String name();

	public abstract String type() default "-1";

	public boolean needLog();

	public String objectParam() default "";
}
