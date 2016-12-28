package com.hzcf.platform.api.service.impl;


import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.service.IOnlineApplyLoanService;
import com.hzcf.platform.core.HzdStatusCodeEnum;
import com.hzcf.platform.core.user.model.UserVO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by leijiaming on 2016/12/28 0028.
 */
@Service
public class OnlineApplyLoanServiceSerivce implements IOnlineApplyLoanService {


    @Override
    public BackResult isApplyLoanQuery(UserVO user) {
        Map<String,Object> map = new HashMap<String,Object>();
        //TODO
        map.put("id","1234566789");
        map.put("name","雷佳明");
        map.put("num","13102519890913131");
        map.put("model","321312");
        return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),HzdStatusCodeEnum.MEF_CODE_0000.getMsg(),map);
    }
}
