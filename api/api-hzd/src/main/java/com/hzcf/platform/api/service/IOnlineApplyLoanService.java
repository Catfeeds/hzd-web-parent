package com.hzcf.platform.api.service;


import com.hzcf.platform.api.common.BackResult;

import com.hzcf.platform.core.user.model.UserVO;

/**
 *
 * Created by leijiaming on 2016/12/28 0028.
 */
public interface IOnlineApplyLoanService {

    public BackResult isApplyLoanQuery( UserVO user);
}
