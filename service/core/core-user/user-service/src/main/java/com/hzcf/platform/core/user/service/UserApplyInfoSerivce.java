package com.hzcf.platform.core.user.service;

import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.framework.core.service.IBaseService;

/**
 * Created by leijiaming on 2016/12/29 0029.
 */
public interface UserApplyInfoSerivce extends IBaseService<UserApplyInfoVO> {

    Result<UserApplyInfoVO> selectByUserId(String userId);
    Result<UserApplyInfoVO> selectByApplyId(String applyId);
}
