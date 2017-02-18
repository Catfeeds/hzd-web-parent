package com.hzcf.platform.api.annotation.biz;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by leijiaming on 2017/2/18
 * 验证字符串是否是数字
 */
@Documented
@Constraint(validatedBy = {CheckStringNumberValidator.class})
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Inherited
public @interface CheckStringNumber {

    int min() default 0;

    int max() default Integer.MAX_VALUE;

    String message() default "{}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
