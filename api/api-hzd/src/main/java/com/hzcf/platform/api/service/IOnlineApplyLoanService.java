package com.hzcf.platform.api.service;


import com.hzcf.platform.api.annotation.RequestAttribute;
import com.hzcf.platform.api.annotation.RequestBodyForm;
import com.hzcf.platform.api.common.BackResult;

import com.hzcf.platform.api.config.BaseConfig;
import com.hzcf.platform.core.user.model.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 * Created by leijiaming on 2016/12/28 0028.
 */
public interface IOnlineApplyLoanService {
    /**
     * 查询是否可以进件
     * @param user
     * @return
     */
    public BackResult isApplyLoanQuery( UserVO user);

    /**
     * 进件申请第一步 基本信息
     * @param user
     * @param userApplyInfoVO
     * @return
     */
    public BackResult onlineLoanapplyOne (UserVO user,UserApplyInfoVO userApplyInfoVO);

    /**
     * 进件申请第二步 详细信息
     * @param user
     * @param userInfoVO
     * @return
     */
    public BackResult  onlineLoanapplyInfoTwo(UserVO user, UserInfoVO userInfoVO,String applyId);
    /**
     * 进件申请第三步 详细信息
     * @param user
     * @param userInfoVO
     * @return
     */
    public BackResult onlineLoanapplyInfoThree(UserVO user, UserInfoVO userInfoVO,String applyId);



    /**
     * 进件申请第四步 完善个人信息
     * @param user
     * @param userRelationVO
     * @return
     */
    public BackResult onlineLoanapplyInfoPerfect(UserVO user, List<UserRelationVO> userRelationVO,String applyId);

    public BackResult onlineLoanapplyImgUpload(HttpServletRequest request,UserVO user, UserImageVO userImageVO) ;


        /**
         * 进件申请第刘步 个人信息预览
         * @param user
         * @param
         * @return
         */
    public BackResult onlineLoanapplyInfoPreview(UserVO user, String applyId);
}
