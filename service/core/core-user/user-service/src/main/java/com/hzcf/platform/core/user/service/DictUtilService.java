package com.hzcf.platform.core.user.service;

import java.util.List;
import java.util.Map;

import com.hzcf.platform.core.user.model.UserDictJson;

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
    public List<UserDictJson> applyDictionaryRelation() ;

    
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
     * 与借款人关系 大类
     * @param value
     * @return
     */
    public String convertRelationOne(String value);
    
    /**
     * 与借款人关系 小类
     * @param parentVale
     * @param value
     * @return
     */
    public String convertRelationTwo(String parentVale, String value) ;    
    
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
     * 市 /区号、邮编
     * @param provinceValue 省份value
     * @param value
     * @return
     */
    public UserDictJson convertCityBean(String provinceValue, String value) ;
    /**
     * 区、县
     * @param cityValue 市value
     * @param value
     * @return
     */
    public String convertArea(String cityValue, String value) ;
}
