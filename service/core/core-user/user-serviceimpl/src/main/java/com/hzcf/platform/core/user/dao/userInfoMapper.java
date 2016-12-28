package com.hzcf.platform.core.user.dao;


import com.hzcf.platform.core.user.data.userInfo;

public interface userInfoMapper {
    int deleteByPrimaryKey(String userInfoId);

    int insert(userInfo record);

    int insertSelective(userInfo record);

    userInfo selectByPrimaryKey(String userInfoId);

    int updateByPrimaryKeySelective(userInfo record);

    int updateByPrimaryKey(userInfo record);
}