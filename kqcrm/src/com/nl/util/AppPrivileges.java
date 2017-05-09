package com.nl.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.METHOD})
/**
 * ��ע��ֻ������Ϊ�ࡢ����
 */
public @interface AppPrivileges {
	public abstract AppPrivilege[] limits() default {};
}
