package com.hzcf.platform.api.annotation.biz;



import java.lang.annotation.Annotation;

/**
 * Created by leijiaming on 2017/2/18
 */
public class SimpleExtendsHandler implements ExtendsOverHandler<Annotation> {

    @Override
    public Annotation createNew(Annotation old, Class<?> clazz, String vna, Class<?> vty) {
        return old;
    }
}
