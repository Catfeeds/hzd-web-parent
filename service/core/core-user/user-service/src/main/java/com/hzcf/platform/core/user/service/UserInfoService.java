package com.hzcf.platform.core.user.service;

import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.user.model.UserInfoVO;
import com.hzcf.platform.framework.core.service.IBaseService;


/**
 * Created by leijiaming on 2016/12/29 0029.
 */
public interface UserInfoService extends IBaseService<UserInfoVO> {
    Result<UserInfoVO> selectByApplyId(String applyId);
    Result<Boolean> updateUserInfo(UserInfoVO userInfoVO);
    Result<Boolean> deleteUserInfoByApplyId(String applyId);
}
