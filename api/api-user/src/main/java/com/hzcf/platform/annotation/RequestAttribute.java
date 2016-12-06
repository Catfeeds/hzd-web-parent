package com.hzcf.platform.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * 在Controller的方法参数中使用此注解，该方法在映射时会注入当前登录的User对象
 * @see com.RequestAttributeAnnotation.authorization.resolvers.CurrentUserMethodArgumentResolver
 * @author ScienJus
 * @date 2015/7/31.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestAttribute {

	String value();
}
