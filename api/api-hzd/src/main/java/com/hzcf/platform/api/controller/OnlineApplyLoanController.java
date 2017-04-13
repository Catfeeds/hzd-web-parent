package com.hzcf.platform.api.controller;

import javax.servlet.http.HttpServletRequest;

import com.hzcf.platform.api.annotation.LogAnnotation;
import com.hzcf.platform.core.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hzcf.platform.api.annotation.RequestAttribute;
import com.hzcf.platform.api.annotation.RequestBodyForm;
import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.config.BaseConfig;
import com.hzcf.platform.api.form.UserRelationForm;
import com.hzcf.platform.api.service.IOnlineApplyLoanService;
import com.hzcf.platform.common.util.json.parser.JsonUtil;
import com.hzcf.platform.common.util.log.Log;


/**
 * 线上申请进件
 * Created by leijiaming on 2016/12/28 0028.
 */
@RestController
public class OnlineApplyLoanController {
    private static final Log logger = Log.getLogger(OnlineApplyLoanController.class);
    @Autowired
    private IOnlineApplyLoanService onlineApplyLoanService;

    /**
     * 查询是否可以进件
     *
     * @param user
     * @return
     */
    @RequestMapping(value = {"rest/api/100/isonlineLoan/apply", "api/100/onlineLoan/apply"}, method = RequestMethod.POST)
    public BackResult isApplyLoanQuery(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user) {
        logger.i("进入  -----查询是否可以进件");
        return onlineApplyLoanService.isApplyLoanQuery(user);
    }

    /**
     * 用户进件申请 第一步
     */
    @RequestMapping(value = {"rest/api/100/onlineLoanapply/one", "api/100/onlineLoanapply/one"}, method = RequestMethod.POST)
    public BackResult onlineLoanapplyOne(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user, @RequestBodyForm UserApplyInfoVO userApplyInfoVO) {
        logger.i("进入  -----用户进件申请第一步,录入借款用途信息 ");
        return onlineApplyLoanService.onlineLoanapplyOne(user, userApplyInfoVO);
    }

    /**
     * 用户进件申请  详情信息  第二步
     */
    @RequestMapping(value = {"rest/api/100/onlineLoanapply/info/two/{applyId}", "api/100/onlineLoanapply/info/two/{applyId}"}, method = RequestMethod.POST)
    public BackResult onlineLoanapplyInfoTwo(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user, @RequestBodyForm UserInfoVO userInfoVO,  @PathVariable  String applyId) {
        logger.i("进入  -----用户进件申请第二步,录入借款人详细信息 ");
        return onlineApplyLoanService.onlineLoanapplyInfoTwo(user, userInfoVO, applyId);
    }

    /**
     * 用户进件申请  单位信息  第三步
     */
    @RequestMapping(value = {"rest/api/100/onlineLoanapply/info/three/{applyId}", "api/100/onlineLoanapply/info/three/{applyId}"}, method = RequestMethod.POST)
    public BackResult onlineLoanapplyInfoThree(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user,
                                               @RequestBodyForm UserInfoVO userInfoVO,
                                               @PathVariable String applyId) {
        logger.i("进入  -----用户进件申请第三步,录入借款人详细信息 ");
        return onlineApplyLoanService.onlineLoanapplyInfoThree(user, userInfoVO, applyId);
    }

    /**
     * 用户进件申请  完善个人信息  第四步
     */
    @RequestMapping(value = {"rest/api/100/onlineLoanapply/info/perfect/{applyId}", "api/100/onlineLoanapply/info/perfect/{applyId}"}, method = RequestMethod.POST)
    public BackResult onlineLoanapplyInfoPerfect(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user, @RequestBodyForm UserRelationForm userRelationForm,
                                                 @PathVariable String applyId) {
        logger.i("进入  -----用户进件申请第四步,录入借款人详细信息 ");
        return onlineApplyLoanService.onlineLoanapplyInfoPerfect(user,userRelationForm, applyId);
    }


    @RequestMapping(value = {"rest/api/100/onlineLoanapply/ImgUpload/{applyId}", "api/100/onlineLoanapply/ImgUpload/{applyId}"}, method = RequestMethod.POST)
    public BackResult onlineLoanapplyImgUpload(HttpServletRequest request,
                                               @RequestAttribute(BaseConfig.USER_TYPE) UserVO user,
                                               @RequestBodyForm UserImageVO userImageVO,
                                               @PathVariable String applyId) {
        logger.i("线上进件申请上传图片 手机号:"+user.getMobile());
        return onlineApplyLoanService.onlineLoanapplyImgUpload(request, user, userImageVO, applyId);
    }


    /**
     * 用户进件申请  个人信息预览  第六步
     */
    @RequestMapping(value = {"rest/api/100/onlineLoanapply/info/Preview/{applyId}", "api/100/onlineLoanapply/info/Preview/{applyId}"}, method = RequestMethod.POST)
    public BackResult onlineLoanapplyInfoPreview(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user,
                                                 @PathVariable String applyId) {
        logger.i("进入  -----用户进件申请第六步,个人信息预览 ");
        return onlineApplyLoanService.onlineLoanapplyInfoPreview(user, applyId);
    }

    /**
     * 用户进件申请  提交进件信息  第七步
     */
    @RequestMapping(value = {"rest/api/100/onlineLoanapply/submit/{applyId}", "api/100/onlineLoanapply/info/Preview/{applyId}"}, method = RequestMethod.POST)
    public BackResult onlineLoanapplyInfoSubmit(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user,
                                                @PathVariable String applyId) {
        logger.i("进入  -----用户进件申请第七步,提交进件 ");
        return onlineApplyLoanService.onlineLoanapplyInfoSubmit(user, applyId);
    }

    /**
     * 用户进件申请  根据applyId删除图片信息
     */
    @RequestMapping(value = {"rest/api/100/deleteApplyIdImage/{applyId}", "api/100/onlineLoanapply/info/Preview/{applyId}"}, method = RequestMethod.POST)
    public BackResult deleteApplyIdImage(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user,
                                              @PathVariable String applyId) {
        logger.i("进入 根据applyId删除图片信息 ");
        return onlineApplyLoanService.deleteApplyIdImage(user, applyId);
    }


}
