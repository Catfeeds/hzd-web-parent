package com.hzcf.platform.api.annotation.biz;


import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 验证字符串长短及正则表示
 */
@Documented
@Constraint(validatedBy = {CheckStringValidator.class})
@Target({PARAMETER, FIELD})
@Retention(RUNTIME)
@Inherited
public @interface CheckString {

    /**
     * 最小长度
     * @return
     */
    int min() default 0;

    /**
     * 最大长度
     * @return
     */
    int max() default 2;

    String regexp() default "[\\S\\s]+";

    String message() default "{}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
