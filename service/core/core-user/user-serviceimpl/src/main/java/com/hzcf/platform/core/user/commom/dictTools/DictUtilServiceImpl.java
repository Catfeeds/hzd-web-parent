package com.hzcf.platform.core.user.commom.dictTools;

import com.alibaba.dubbo.config.annotation.Service;
import com.hzcf.platform.common.cache.ICache;
import com.hzcf.platform.core.user.service.DictUtilService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by leijiaming on 2016/12/30 0030.
 */
@Service
public class DictUtilServiceImpl  implements DictUtilService {
    @Autowired
    private ICache cache;
    @Override
    public Object getDict(){
        return cache.load("userDictMap");
    }
}
