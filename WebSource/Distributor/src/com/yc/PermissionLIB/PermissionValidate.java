package com.yc.PermissionLIB;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限验证注解接口
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermissionValidate {
	/**
	 * 功能模块id
	 * 
	 * @return
	 */
	int funID() default 0;

	/**
	 * 权限验证结果返回方式（1：json输出，2：重定向到登录页）
	 */
	int resultType() default 1;
}
