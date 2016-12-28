package com.hzcf.platform.api.controller;

import com.hzcf.platform.api.annotation.RequestAttribute;
import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.config.BaseConfig;
import com.hzcf.platform.core.user.model.UserVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 线上申请进件
 * Created by leijiaming on 2016/12/28 0028.
 */
@RestController
public class OnlineApplyLoanController {

    @RequestMapping(value = "rest/api/100/onlineLoan/apply")
    public BackResult isApplyLoanQuery(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user){

        return null;
    }


}
