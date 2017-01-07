package com.hzcf.platform.api.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.api.baseEnum.HzdStatusCodeEnum;
import com.hzcf.platform.core.user.service.DictUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
            Map<String, Object>  map =dictUtilService.applyDictionaryJkyt();
            logger.i("map"+map);

            return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),HzdStatusCodeEnum.MEF_CODE_0000.getMsg(),map);
        }catch (Exception e){
            logger.i("-----------系统异常,请检查数据源-------");
            e.printStackTrace();
            return new BackResult(HzdStatusCodeEnum.MEF_CODE_9999.getCode(), HzdStatusCodeEnum.MEF_CODE_9999.getMsg(),
                    null);
        }
    }

    @RequestMapping(value="rest/api/100/apply/dictionary/info",method= RequestMethod.POST)
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

    @RequestMapping(value="rest/api/100/apply/dictionary/region",method= RequestMethod.POST)
    public BackResult applyDictionaryRegion(){
        try {
            Map<String, Object> stringObjectMap = dictUtilService.applyDictionaryRegion();
            return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),HzdStatusCodeEnum.MEF_CODE_0000.getMsg(),stringObjectMap);
        }catch (Exception e){
            logger.i("-----------系统异常,请检查数据源-------");
            e.printStackTrace();
            return new BackResult(HzdStatusCodeEnum.MEF_CODE_9999.getCode(), HzdStatusCodeEnum.MEF_CODE_9999.getMsg(),
                    null);
        }
    }
}
