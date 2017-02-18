package com.hzcf.platform.api.annotation.biz;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by leijiaming on 2017/2/18
 * 校验注解, 用于代替{@link javax.validation.Valid}
 */
@Target({ METHOD, FIELD, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface Check {

}