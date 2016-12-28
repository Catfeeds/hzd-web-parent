package com.hzcf.platform.core.user.dao;


import com.hzcf.platform.core.user.data.UserApplyInfo;

public interface UserApplyInfoMapper {
    int deleteByPrimaryKey(String applyId);

    int insert(UserApplyInfo record);

    int insertSelective(UserApplyInfo record);

    UserApplyInfo selectByPrimaryKey(String applyId);

    int updateByPrimaryKeySelective(UserApplyInfo record);

    int updateByPrimaryKey(UserApplyInfo record);
}