package com.xwl.sample.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionDesc {
	String todo() default "";
	String successStr() default "成功";
	String failStr() default "失败";
	String[] remarks() default {};
	boolean logResponse() default false; // 是否进行响应日志记录,默认不记录
	String[] includes() default {};
	String[] excludes() default {};
	boolean auth() default true; // 是否进行权限过滤,默认过滤!
}
