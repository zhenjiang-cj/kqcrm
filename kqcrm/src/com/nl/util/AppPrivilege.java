package com.nl.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.sun.istack.internal.Nullable;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.ANNOTATION_TYPE})
/**
 * 该注释只可以作为类、方法、注释参数的注释类型
 */
public @interface AppPrivilege{
	
	@Nullable()
	public abstract AppParam[] limit() default {};
	
	public abstract AppSystem sysId();
	
	public abstract AppFunc funcId();
	
}
