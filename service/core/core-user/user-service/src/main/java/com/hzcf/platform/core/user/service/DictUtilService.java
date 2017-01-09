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
    public List<UserDictJson> applyDictionaryJkyt();


    public Map<String,Object> applyDictionaryinfo();

    /**
     *
     * @return
     */
    public List<UserDictJson> applyDictionaryRegionsheng();
    public Map<String,Object> applyDictionaryRegionshi();
    public Map<String,Object> applyDictionaryRegionqu();
    
    
    /**
     * 借款用途 大类
     * @param value
     * @return
     */
    public String convertLoanPurposeOne(String value);
    /**
     * 借款用途 小类
     * @param value
     * @return
     */
    public String convertLoanPurposeTwo(String parentVale, String value);
    /**
     * 其他字典   - 房产状况等等
     * @param type    类型  “HOUSE_STATUS/LIVE_TOGETHER/CHILDRE_STATUS” 
     * @param value   值
     * @return
     */
    public String convertDict(String type, String value) ;
    /**
     * 省份
     * @param value
     * @return
     */
    public String convertProvince(String value) ;
    /**
     * 市
     * @param provinceValue 省份value
     * @param value
     * @return
     */
    public String convertCity(String provinceValue, String value) ;
    /**
     * 区、县
     * @param cityValue 市value
     * @param value
     * @return
     */
    public String convertArea(String cityValue, String value) ;
}
