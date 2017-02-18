package com.hzcf.platform.api.annotation.biz;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by leijiaming on 2017/2/18
 * 要验证的类有子父类关系, 自定义校验
 */
@Documented
@Target({TYPE, FIELD})
@Retention(RUNTIME)
public @interface ExtendsOver {

    /**
     * 处理器, 与注解一对一
     * @return
     */
    Class<? extends ExtendsOverHandler>[] handler() default {};

    /**
     * 处理器, 与处理器一对一
     * @return
     */
    Class<? extends Annotation>[] annotation() default {};

}
