package com.hzcf.platform.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hzcf.platform.api.baseEnum.HzdStatusCodeEnum;
import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.common.ConstantsToken;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.core.user.model.UserDictJson;
import com.hzcf.platform.core.user.service.DictUtilService;

/**
 * Created by leijiaming on 2016/12/30 0030.
 */
@RestController
public class DictUtilController {
    private static final Log logger = Log.getLogger(DictUtilController.class);

    @Autowired
    DictUtilService dictUtilService;

    @RequestMapping(value={"rest/api/100/apply/dictionary/jkyt","api/100/apply/dictionary/jkyt"},method= RequestMethod.POST)
    public BackResult applyDictionaryJkyt(){
        try {
        	List<UserDictJson>  map =dictUtilService.applyDictionaryJkyt();
            logger.i("map"+map);
            return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),HzdStatusCodeEnum.MEF_CODE_0000.getMsg(),map);
        }catch (Exception e){
            logger.i("-----------系统异常,请检查数据源-------");
            e.printStackTrace();
            return new BackResult(HzdStatusCodeEnum.MEF_CODE_9999.getCode(), HzdStatusCodeEnum.MEF_CODE_9999.getMsg(),
                    null);
        }
    }
    
    @RequestMapping(value={"rest/api/100/apply/dictionary/relation","api/100/apply/dictionary/relation"},method= RequestMethod.POST)
    public BackResult applyDictionaryRelation(){
        try {
        	List<UserDictJson>  list =dictUtilService.applyDictionaryRelation();
        	Map<String, UserDictJson> map = new HashMap();
        	for(UserDictJson userDice : list){
        		if(ConstantsToken.RELATION_TO_HOME.equals(userDice.getDict_value())){
        			map.put("RELATION_TO_HOME", userDice);
        		}else if(ConstantsToken.RELATION_TO_URGE.equals(userDice.getDict_value())){
        			map.put("RELATION_TO_URGE", userDice);
        		}else if(ConstantsToken.RELATION_TO_WORK.equals(userDice.getDict_value())){
        			map.put("RELATION_TO_WORK", userDice);
        		}
        	}
        	
            logger.i("map"+map);
            return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),HzdStatusCodeEnum.MEF_CODE_0000.getMsg(),map);
        }catch (Exception e){
            logger.i("-----------系统异常,请检查数据源-------");
            e.printStackTrace();
            return new BackResult(HzdStatusCodeEnum.MEF_CODE_9999.getCode(), HzdStatusCodeEnum.MEF_CODE_9999.getMsg(),
                    null);
        }
    }
    

    @RequestMapping(value={"rest/api/100/apply/dictionary/info","api/100/apply/dictionary/info"},method= RequestMethod.POST)
    public BackResult applyDictionaryinfo(){
        try {
            Map<String, Object> stringObjectMap = dictUtilService.applyDictionaryinfo();
            return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),HzdStatusCodeEnum.MEF_CODE_0000.getMsg(),stringObjectMap);
        }catch (Exception e){
            logger.i("-----------系统异常,请检查数据源-------");
            e.printStackTrace();
            return new BackResult(HzdStatusCodeEnum.MEF_CODE_9999.getCode(), HzdStatusCodeEnum.MEF_CODE_9999.getMsg(),
                    null);
        }
    }





    @RequestMapping(value={"rest/api/100/apply/dictionary/sheng","api/100/apply/dictionary/sheng"},method= RequestMethod.POST)
    public BackResult applyDictionarySheng(){
        try {
            List<UserDictJson> userDictJsons = dictUtilService.applyDictionaryRegionsheng();
            return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),HzdStatusCodeEnum.MEF_CODE_0000.getMsg(),userDictJsons);
        }catch (Exception e){
            logger.i("-----------系统异常,请检查数据源-------");
            e.printStackTrace();
            return new BackResult(HzdStatusCodeEnum.MEF_CODE_9999.getCode(), HzdStatusCodeEnum.MEF_CODE_9999.getMsg(),
                    null);
        }
    }

    @RequestMapping(value={"rest/api/100/apply/dictionary/shi/{code}","api/100/apply/dictionary/shi/{code}"},method= RequestMethod.POST)
    public BackResult applyDictionaryShi(@PathVariable String code){
        try {
            Map<String, Object> stringObjectMap = dictUtilService.applyDictionaryRegionshi();
            return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),HzdStatusCodeEnum.MEF_CODE_0000.getMsg(),stringObjectMap.get(code));
        }catch (Exception e){
            logger.i("-----------系统异常,请检查数据源-------");
            e.printStackTrace();
            return new BackResult(HzdStatusCodeEnum.MEF_CODE_9999.getCode(), HzdStatusCodeEnum.MEF_CODE_9999.getMsg(),
                    null);
        }
    }

    @RequestMapping(value={"rest/api/100/apply/dictionary/qu/{code}","api/100/apply/dictionary/qu/{code}"},method= RequestMethod.POST)
    public BackResult applyDictionaryQu(@PathVariable String code){
        try {
            Map<String, Object> stringObjectMap = dictUtilService.applyDictionaryRegionqu();
            return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),HzdStatusCodeEnum.MEF_CODE_0000.getMsg(),stringObjectMap.get(code));
        }catch (Exception e){
            logger.i("-----------系统异常,请检查数据源-------");
            e.printStackTrace();
            return new BackResult(HzdStatusCodeEnum.MEF_CODE_9999.getCode(), HzdStatusCodeEnum.MEF_CODE_9999.getMsg(),
                    null);
        }
    }
}
