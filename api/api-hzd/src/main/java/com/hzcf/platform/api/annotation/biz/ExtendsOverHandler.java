package com.hzcf.platform.api.annotation.biz;

import java.lang.annotation.Annotation;

/**
 * Created by leijiaming on 2017/2/18
 */
public interface ExtendsOverHandler<T extends Annotation> {

    /**
     * 创建一个符合规则的注解
     *
     * @param old   旧的注解
     * @param clazz 原始所有类类型
     * @param vna   参数名称
     * @param vty   参数类型
     * @return
     */
    T createNew(T old, Class<?> clazz, String vna, Class<?> vty);

}
