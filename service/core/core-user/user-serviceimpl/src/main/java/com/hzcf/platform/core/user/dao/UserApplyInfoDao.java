package com.hzcf.platform.core.user.dao;


import com.hzcf.platform.core.user.data.UserApplyInfo;
import com.hzcf.platform.framework.core.storage.IBaseDao;

public interface UserApplyInfoDao  extends IBaseDao<UserApplyInfo> {



    UserApplyInfo selectByUserId(String userId);
    UserApplyInfo selectByApplyId(String applyId);

}