package com.hzcf.platform.mgr.sys.annotation.aop;


import com.hzcf.platform.common.util.log.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by leijiaming on 2017/2/17. 日志切面
 */
@Aspect
@Component
public class LogAspect {
    private static final String CRLF = "\r\n";
    private static final AtomicLong seqSeed = new AtomicLong(0);
    private static Log logger = Log.getLogger(LogAspect.class);


    private final ThreadLocal<Long> currentSeq = new ThreadLocal<>();

    @Before("@within(com.hzcf.platform.mgr.sys.annotation.biz.LogAnnotation)")
    public void beforeType(JoinPoint joinPoint) {
        before(joinPoint);
    }

    @Before("@annotation(com.hzcf.platform.mgr.sys.annotation.biz.LogAnnotation)")
    public void beforeMethod(JoinPoint joinPoint) {
        before(joinPoint);
    }

    /**
     * 之前打印日志
     *
     * @param joinPoint 切点
     */
    private void before(JoinPoint joinPoint) {
        currentSeq.set(seqSeed.incrementAndGet());
        Signature signature = joinPoint.getSignature();
        StringBuilder str = new StringBuilder(currentSeq.get() + " ->> ")
                .append(String.valueOf(joinPoint.getStaticPart())).append(CRLF).append("请求 : ")
                .append(signature.getDeclaringTypeName()).append(CRLF).append("方法 : ").append(signature.getName())
                .append(CRLF);
        Object[] os = joinPoint.getArgs();
        for (int i = 0; i < os.length; i++) {
            str.append("参数 [").append(i).append("]: ");
            str.append(os[i]);
            str.append(CRLF);
        }
        str.append("已开始").append(CRLF);
        logger.i(str.toString());
    }

    @AfterReturning(value = "@within(com.hzcf.platform.mgr.sys.annotation.biz.LogAnnotation)", returning = "result")
    public void afterReturningType(JoinPoint joinPoint, Object result) {
        afterReturning(joinPoint, result);
    }

    @AfterReturning(value = "@annotation(com.hzcf.platform.mgr.sys.annotation.biz.LogAnnotation)", returning = "result")
    public void afterReturningMethod(JoinPoint joinPoint, Object result) {
        afterReturning(joinPoint, result);
    }

    /**
     * 之后打印日志
     *
     * @param joinPoint 切点
     * @param result    返回结果
     */
    private void afterReturning(JoinPoint joinPoint, Object result) {

        Signature signature = joinPoint.getSignature();
        StringBuilder str = new StringBuilder(currentSeq.get() + " ->> ")
                .append(String.valueOf(joinPoint.getStaticPart())).append(CRLF).append("请求 : ")
                .append(signature.getDeclaringTypeName()).append(CRLF).append("方法 : ").append(signature.getName())
                .append(CRLF);
        str.append("已结束").append(CRLF).append("返回结果 : ")
                .append(CRLF).append(result == null ? "void or null" : result);
        logger.i(str.toString());
    }


    @AfterThrowing(value = "@within(com.hzcf.platform.mgr.sys.annotation.biz.LogAnnotation)", throwing = "ex")
    public void afterThrowingType(JoinPoint joinPoint, Throwable ex) {
        afterThrowing(joinPoint, ex);
    }

    @AfterThrowing(value = "@annotation(com.hzcf.platform.mgr.sys.annotation.biz.LogAnnotation)", throwing = "ex")
    public void afterThrowingMethod(JoinPoint joinPoint, Throwable ex) {
        afterThrowing(joinPoint, ex);
    }

    /**
     * 暂时不需要返回值
     *
     * @param joinPoint
     * @param ex
     * @return
     */
    private void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        Signature signature = joinPoint.getSignature();
        StringBuilder str = new StringBuilder(currentSeq.get() + " ->> ")
                .append(String.valueOf(joinPoint.getStaticPart())).append(CRLF).append("请求 : ")
                .append(signature.getDeclaringTypeName()).append(CRLF).append("方法 : ").append(signature.getName())
                .append(CRLF);
        str.append("出现异常").append(CRLF).append("异常信息 : ").append(ex);
        logger.e(str.toString());
    }
}
