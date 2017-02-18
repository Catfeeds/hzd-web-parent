package com.hzcf.platform.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by leijiaming on 2017/2/18
 * 请求参数验证, 若请求需要验证则加入该注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface RequestValidation {

    // 处理参数注解

}
