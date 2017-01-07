package com.hzcf.platform.core.user.service;

import com.hzcf.platform.core.user.model.UserDictJson;

import java.util.List;
import java.util.Map;

/**
 * Created by leijiaming on 2016/12/30 0030.
 */
public interface DictUtilService {
    /**
     * 借款用途
     * @return
     */
    public Map<String, Object> applyDictionaryJkyt();


    public Map<String,Object> applyDictionaryinfo();

    /**
     *
     * @return
     */
    public List<UserDictJson> applyDictionaryRegionsheng();
    public Map<String,Object> applyDictionaryRegionshi();
    public Map<String,Object> applyDictionaryRegionqu();
}
