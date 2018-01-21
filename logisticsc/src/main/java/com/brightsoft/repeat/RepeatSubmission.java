package com.brightsoft.repeat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 防止表单重复提交注解，用户方法上
 * 在新建页面方法上，设置needSaveToken为true，此时拦截器会在session中保存一个token
 * 同时需要在新建页面中添加
 * <input type="hidden" name="token" value="${token}">
 * 保存方法需要验证重复提交的，设置needUpdateToken为true
 * 此时会在拦截器中验证是否重复提交
 * @author yangshoubao
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatSubmission {
 
	boolean needSaveToken() default false;
	
	boolean needUpdateToken() default false;
}
