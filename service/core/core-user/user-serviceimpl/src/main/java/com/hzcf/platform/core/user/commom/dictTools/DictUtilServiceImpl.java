package com.hzcf.platform.core.user.commom.dictTools;

import com.alibaba.dubbo.config.annotation.Service;
import com.hzcf.platform.common.cache.ICache;
import com.hzcf.platform.core.user.service.DictUtilService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by leijiaming on 2016/12/30 0030.
 */
@Service
public class DictUtilServiceImpl  implements DictUtilService {
    @Autowired
    private ICache cache;
    @Override
    public Map<String, Object> applyDictionaryJkyt() {
        return  (Map<String, Object>) cache.load("applyDictionaryJkyt");
    }
    @Override
    public Map<String,Object> applyDictionaryinfo() {
        return  (Map<String, Object>)cache.load("applyDictionaryinfo");
    }
    @Override
    public Map<String,Object> applyDictionaryRegion() {
        return  (Map<String, Object>)cache.load("applyDictionaryRegion");
    }
}
