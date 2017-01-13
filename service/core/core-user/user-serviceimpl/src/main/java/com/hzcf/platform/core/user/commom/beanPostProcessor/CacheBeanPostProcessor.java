package com.hzcf.platform.core.user.commom.beanPostProcessor;

import com.hzcf.platform.core.user.commom.dictTools.DictUtilInitService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by leijiaming on 2016/12/30 0030.
 */
public class CacheBeanPostProcessor implements BeanPostProcessor {
    private static final Logger logger = Logger.getLogger(CacheBeanPostProcessor.class);
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            if( bean instanceof DictUtilInitService){
                ((DictUtilInitService)bean).loadData();
            }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
