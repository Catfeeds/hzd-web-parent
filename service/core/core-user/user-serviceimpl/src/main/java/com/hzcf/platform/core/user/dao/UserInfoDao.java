package com.hzcf.platform.core.user.dao;


import com.hzcf.platform.core.user.data.UserInfo;
import com.hzcf.platform.framework.core.storage.IBaseDao;

public interface UserInfoDao extends IBaseDao<UserInfo> {
    int deleteByPrimaryKey(String userInfoId);



    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String userInfoId);

    int updateByPrimaryKeySelective(UserInfo record);

    Boolean updateByApplyId(UserInfo userInfo);

    UserInfo selectByApplyId(String ApplyId);
}