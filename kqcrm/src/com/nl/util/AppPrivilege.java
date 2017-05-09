package com.nl.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.sun.istack.internal.Nullable;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.ANNOTATION_TYPE})
/**
 * ��ע��ֻ������Ϊ�ࡢ������ע�Ͳ�����ע������
 */
public @interface AppPrivilege{
	
	@Nullable()
	public abstract AppParam[] limit() default {};
	
	public abstract AppSystem sysId();
	
	public abstract AppFunc funcId();
	
}
