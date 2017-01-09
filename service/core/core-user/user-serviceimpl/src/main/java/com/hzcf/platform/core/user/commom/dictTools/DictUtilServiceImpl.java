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
    
    
    /**
     * 借款用途 大类
     * @param value
     * @return
     */
    @Override
    public String convertLoanPurposeOne(String value) {
    	List<UserDictJson> result = (List<UserDictJson>) cache.load("applyDictionaryJkyt");
    	for(UserDictJson dict : result){
    		if(value.equals(dict.getDict_value())){
    			return dict.getDict_text();
    		}
    	}
    	return "";
    }
    
    /**
     * 借款用途 小类
     * @param parentVale
     * @param value
     * @return
     */
    @Override
    public String convertLoanPurposeTwo(String parentVale, String value) {
    	List<UserDictJson> result = (List<UserDictJson>) cache.load("applyDictionaryJkyt");
    	List<UserDictJson> tempList = null;
    	for(UserDictJson dict : result){
    		if(parentVale.equals(dict.getDict_value())){
    			tempList = dict.getList();
    			for(UserDictJson temp : tempList){
    				if(value.equals(temp.getDict_value()))
    				return temp.getDict_text();
    			}
    		}
    	}
    	return "";
    }
    
    /**
     * 其他字典   - 房产状况等等
     * @param type    类型  “HOUSE_STATUS/LIVE_TOGETHER/CHILDRE_STATUS” 
     * @param value   值
     * @return
     */
    @Override
    public String convertDict(String type, String value) {
    	Map<String, Object> result = (Map<String, Object>) cache.load("applyDictionaryinfo");
        List<UserDictJson> tempList = (List<UserDictJson>)result.get(type);
        for(UserDictJson dict : tempList){
    		if(value.equals(dict.getDict_value())){
    			return dict.getDict_text();
    		}
    	}
    	return "";
    }
    
    /**
     * 省份
     * @param value
     * @return
     */
    @Override
    public String convertProvince(String value) {
    	List<UserDictJson> result = (List<UserDictJson>) cache.load("applyDictionaryRegionsheng");
    	for(UserDictJson dict : result){
    		if(value.equals(dict.getDict_value())){
    			return dict.getDict_text();
    		}
    	}
    	return "";
    }
    
    /**
     * 市
     * @param provinceValue 省份value
     * @param value
     * @return
     */
    public String convertCity(String provinceValue, String value) {
    	Map<String, Object> result = (Map<String, Object>) cache.load("applyDictionaryRegionshi");
        List<UserDictJson> tempList = (List<UserDictJson>)result.get(provinceValue);
        for(UserDictJson dict : tempList){
    		if(value.equals(dict.getDict_value())){
    			return dict.getDict_text();
    		}
    	}
    	return "";
    }
    
    /**
     * 区、县
     * @param cityValue 市value
     * @param value
     * @return
     */
    public String convertArea(String cityValue, String value){
    	Map<String, Object> result = (Map<String, Object>) cache.load("applyDictionaryRegionqu");
        List<UserDictJson> tempList = (List<UserDictJson>)result.get(cityValue);
        for(UserDictJson dict : tempList){
    		if(value.equals(dict.getDict_value())){
    			return dict.getDict_text();
    		}
    	}
    	return "";    	
    }
}
