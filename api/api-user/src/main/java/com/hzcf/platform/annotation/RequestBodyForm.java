package com.hzcf.platform.annotation;


import java.lang.annotation.*;

/**
 * 
 * @description:同时支持json及from提交
 * @author lei
 * @version 1.0
 * 
 * <pre>
 * Modification History: 
 * Date              Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年12月6日                        lei          1.0       1.0 Version 
 * </pre>
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestBodyForm {

    boolean required() default true;

    String value() default "";

}
