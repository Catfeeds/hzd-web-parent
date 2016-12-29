package com.hzcf.platform.api.service;


import com.hzcf.platform.api.common.BackResult;

import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.core.user.model.UserInfoVO;
import com.hzcf.platform.core.user.model.UserRelationVO;
import com.hzcf.platform.core.user.model.UserVO;

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
    public BackResult onlineLoanapplyInfoTwo(UserVO user, UserInfoVO userInfoVO);
    /**
     * 进件申请第三步 详细信息
     * @param user
     * @param userInfoVO
     * @return
     */
    public BackResult onlineLoanapplyInfoThree(UserVO user, UserInfoVO userInfoVO);



    /**
     * 进件申请第四步 完善个人信息
     * @param user
     * @param userRelationVO
     * @return
     */
    public BackResult onlineLoanapplyInfoPerfect(UserVO user, List<UserRelationVO> userRelationVO);
}
