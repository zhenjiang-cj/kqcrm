package com.nl.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.METHOD})
/**
 * 该注释只可以作为类、方法
 */
public @interface AppPrivileges {
	public abstract AppPrivilege[] limits() default {};
}
