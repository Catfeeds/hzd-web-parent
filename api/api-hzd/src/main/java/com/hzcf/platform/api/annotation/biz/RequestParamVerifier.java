package com.hzcf.platform.api.annotation.biz;


import com.hzcf.platform.api.baseEnum.HzdStatusCodeEnum;
import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.common.util.log.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.annotation.Annotation;
import java.util.*;



/**
 * Created by leijiaming on 2017/2/18
 * 请求参数切面验证
 */
@Aspect
public class RequestParamVerifier {

    private static Log logger = Log.getLogger(RequestParamVerifier.class);
    @Autowired
    private Validator validator;

    private ExtendsOverProcessor processor;

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    public void setProcessor(ExtendsOverProcessor processor) {
        this.processor = processor;
    }

    /**
     * 切spring-mvc方法上的@RequestMapping注解, 后续放弃
     *
     * @param point
     * @return
     * @throws Throwable
     */
//    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object verifySpringMVCMethod(ProceedingJoinPoint point) throws Throwable {
        return verify(point);
    }

    /**
     * 切@RequestValidation
     *
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("@within(com.hzcf.platform.api.aop.RequestValidation)")
    public Object verifyType(ProceedingJoinPoint point) throws Throwable {
        return verify(point);
    }

    /**
     * 切请求
     *
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("@annotation(com.hzcf.platform.api.aop.RequestValidation)")
    public Object verifyMethod(ProceedingJoinPoint point) throws Throwable {
        return verify(point);
    }

    /**
     * 验证方法
     *
     * @param point
     * @return
     * @throws Throwable
     */
    private Object verify(ProceedingJoinPoint point) throws Throwable {
        logger.i("start verify parameter for " + String.valueOf(point.getStaticPart()) + "\n\r");

        Object[] os = point.getArgs();

        MethodSignature method = (MethodSignature) point.getSignature();
        String[] names = method.getParameterNames();
        Class<?>[] types = method.getParameterTypes();

        Annotation[][] p_ans = method.getMethod().getParameterAnnotations();

        for (int i = 0; i < os.length; i++) {
            if (os[i] == null)
                continue;
            if (Errors.class.isAssignableFrom(os[i].getClass())) {// 验证spring-mvc的验证结果
                Errors er = (Errors) os[i];
                if (er.hasErrors()) {
                    List<ObjectError> ers = er.getAllErrors();
                    for (ObjectError e : ers) {
                        if (e == null)
                            continue;
                        BackResult result = new BackResult();
                        result.setState(HzdStatusCodeEnum.HZD_CODE_9000.getCode());
                        result.setMessage(HzdStatusCodeEnum.HZD_CODE_9000.getMsg());
                        result.setShowMessage(e.getDefaultMessage());
                        // 打印验证日志
                        logger.w("parameter " + names[i] + "=" + os[i] + " was invalid the cause was " + e.getDefaultMessage() + "\n\r");
                        return result;
                    }
                }
            } else {// 自定义验证
                Annotation[] ans = p_ans[i];
                Annotation valid = null;
                for (Annotation an : ans) {
                    if (an == null)
                        continue;
                    if (an.annotationType().getSimpleName().startsWith("Check")) {
                        valid = an;
                        break;
                    }
                }
                if (valid == null)
                    continue;
                BackResult result = createBackResult(valid, os[i], types[i], true);
                if (result == null)
                    continue;
                logger.w("parameter " + names[i] + "=" + os[i] + " was invalid the cause was " + result.getShowMessage() + "\n\r");
                return result;
            }
        }

        logger.i("finish verify parameter for " + String.valueOf(point.getStaticPart()) + "\n\r");
        return point.proceed(os);
    }

    /**
     * 验证参数
     *
     * @param ann
     * @param value
     * @param out 是否外部调用
     * @return
     */
    private BackResult createBackResult(Annotation ann, Object value, Class<?> valType, boolean out) {
        if (ann == null)
            return null;
        BackResult result = null;
        if (out && Collection.class.isAssignableFrom(valType)) {// 若参数是集合
            Collection coll = (Collection) value;
            for (Object val : coll) {
                result = createConstraintViolation(val);
                if (result != null)
                    break;
            }
        } else if (out && Object[].class.isAssignableFrom(valType)) {// 若参数是数组
            Object[] arr = (Object[]) value;
            for (Object val : arr) {
                result = createConstraintViolation(val);
                if (result != null)
                    break;
            }
        } else {
            Object val = value;
            if (ann.annotationType().getSimpleName().equals("Check")) {
                try {
                    List<ExtendsOverProcessor.FieldTypeValue> ext = processor.getExtendsOverField(val);
                    if (ext == null || ext.isEmpty()) {
                        result = createConstraintViolation(val);
                    } else {
                        for (ExtendsOverProcessor.FieldTypeValue ef : ext) {
                            if (ef == null)
                                continue;
                            result = createBackResult(ef.getAnnotation(), ef.getValue(), ef.getClazz(), false);
                            if (result != null)
                                return result;
                        }
                    }
                } catch (IllegalAccessException e) {
                    logger.e("can not get the target field, target: " + value, e);
                }

            } else {
                val = CreateTemper.createTemper(val, valType, ann);
                result = createConstraintViolation(val);
            }
        }
        return result;
    }

    /**
     * 验证结果
     *
     * @param value
     * @return
     */
    private BackResult createConstraintViolation(Object value) {
        if (value == null)
            return null;
        Set<ConstraintViolation<Object>> set = validator.validate(value);
        for (ConstraintViolation<Object> cv : set) {
            if (cv == null)
                continue;
            BackResult result = new BackResult();
            result.setState(HzdStatusCodeEnum.HZD_CODE_9000.getCode());
            result.setMessage(HzdStatusCodeEnum.HZD_CODE_9000.getMsg());
            result.setShowMessage(cv.getMessage());
            return result;
        }
        return null;
    }

}
