package com.hzcf.platform.core.user.dao;


import com.hzcf.platform.core.user.data.UserInfo;

public interface userInfoDao {
    int deleteByPrimaryKey(String userInfoId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String userInfoId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}