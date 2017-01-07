package com.hzcf.platform.core.user.commom.dictTools;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.hzcf.platform.common.cache.ICache;
import com.hzcf.platform.core.user.model.UserDictJson;
import com.hzcf.platform.core.user.service.DictUtilService;

/**
 * Created by leijiaming on 2016/12/30 0030.
 */
@Service
public class DictUtilServiceImpl  implements DictUtilService {
    @Autowired
    private ICache cache;

    @Override
    public List<UserDictJson> applyDictionaryJkyt() {
        return (List<UserDictJson>) cache.load("applyDictionaryJkyt");
    }

    @Override
    public Map<String, Object> applyDictionaryinfo() {
        return (Map<String, Object>) cache.load("applyDictionaryinfo");
    }

    @Override
    public List<UserDictJson> applyDictionaryRegionsheng() {
        return (List<UserDictJson>) cache.load("applyDictionaryRegionsheng");    }

    @Override
    public Map<String, Object> applyDictionaryRegionshi() {
        return (Map<String, Object>) cache.load("applyDictionaryRegionshi");    }

    @Override
    public Map<String, Object> applyDictionaryRegionqu() {
        return (Map<String, Object>) cache.load("applyDictionaryRegionqu");    }
}
