package com.hzcf.platform.api.service;


import com.hzcf.platform.api.annotation.RequestAttribute;
import com.hzcf.platform.api.annotation.RequestBodyForm;
import com.hzcf.platform.api.common.BackResult;

import com.hzcf.platform.api.config.BaseConfig;
import com.hzcf.platform.api.form.UserRelationForm;
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
     * @param UserRelationForm
     * @return
     */
    public BackResult onlineLoanapplyInfoPerfect(UserVO user, UserRelationForm userRelationForm, String applyId);

    /**
     * 进件申请第五步 上传图片
     * @param request
     * @param user
     * @param userImageVO
     * @return
     */
    public BackResult onlineLoanapplyImgUpload(HttpServletRequest request,UserVO user, UserImageVO userImageVO,String applyId) ;



    /**
         * 进件申请第六步 个人信息预览
         * @param user
         * @param
         * @return
         */
    public BackResult onlineLoanapplyInfoPreview(UserVO user, String applyId);

    /**
     * 进件申请第七步 提交进件
     * @param user
     * @param
     * @return
     */
    public BackResult onlineLoanapplyInfoSubmit(UserVO user, String applyId);

    /**
     * 根据applyId删除图片信息
     * @param user
     * @param applyId
     * @return
     */
    public BackResult deleteApplyIdImage(UserVO user, String applyId);
}
