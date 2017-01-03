package com.hzcf.platform.api.controller;

import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.core.user.service.DictUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by leijiaming on 2016/12/30 0030.
 */
@RestController
public class DictUtilController {
    @Autowired
    DictUtilService dictUtilService;

    @RequestMapping(value="test/test")
    public BackResult selectload(){
        Object dict = dictUtilService.getDict();
        return new BackResult(0,"123",dict);
    }
}
