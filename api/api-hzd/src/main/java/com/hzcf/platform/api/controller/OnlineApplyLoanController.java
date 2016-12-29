package com.hzcf.platform.api.controller;

import com.hzcf.platform.api.annotation.RequestAttribute;
import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.config.BaseConfig;
import com.hzcf.platform.api.service.IOnlineApplyLoanService;
import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.core.user.model.UserInfoVO;
import com.hzcf.platform.core.user.model.UserRelationVO;
import com.hzcf.platform.core.user.model.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 线上申请进件
 * Created by leijiaming on 2016/12/28 0028.
 */
@RestController
public class OnlineApplyLoanController {
    @Autowired
    private IOnlineApplyLoanService onlineApplyLoanService;

    /**
     * 查询是否可以进件
     * @param user
     * @return
     */
    @RequestMapping({"rest/api/100/isonlineLoan/apply","api/100/onlineLoan/apply"})
    public BackResult isApplyLoanQuery(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user){

        return onlineApplyLoanService.isApplyLoanQuery(user);
    }

    /**
     * 用户进件申请 第一步
     */
    @RequestMapping({"rest/api/100/onlineLoanapply/one","api/100/onlineLoanapply/one"})
    public BackResult onlineLoanapplyOne(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user,UserApplyInfoVO userApplyInfoVO){

        return onlineApplyLoanService.onlineLoanapplyOne(user,userApplyInfoVO);
    }

    /**
     * 用户进件申请  详情信息  第二步
     */
    @RequestMapping({"rest/api/100/onlineLoanapply/info/two","api/100/onlineLoanapply/info/two"})
    public BackResult onlineLoanapplyInfoTwo(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user,UserInfoVO userInfoVO){

        return onlineApplyLoanService.onlineLoanapplyInfoTwo(user,userInfoVO);
    }

    /**
     * 用户进件申请  单位信息  第三步
     */
    @RequestMapping({"rest/api/100/onlineLoanapply/info/three","api/100/onlineLoanapply/info/three"})
    public BackResult onlineLoanapplyInfoThree(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user,UserInfoVO userInfoVO){

        return onlineApplyLoanService.onlineLoanapplyInfoThree(user,userInfoVO);
    }

    /**
     * 用户进件申请  完善个人信息  第四步
     */
    @RequestMapping({"rest/api/100/onlineLoanapply/info/perfect","api/100/onlineLoanapply/info/perfect"})
    public BackResult onlineLoanapplyInfoPerfect(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user,List<UserRelationVO> userRelationVO){

        return onlineApplyLoanService.onlineLoanapplyInfoPerfect(user,userRelationVO);
    }


}