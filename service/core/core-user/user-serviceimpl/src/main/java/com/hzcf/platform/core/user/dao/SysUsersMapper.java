package com.hzcf.platform.core.user.dao;

import com.hzcf.platform.core.user.data.SysUsers;

public interface SysUsersMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUsers record);

    int insertSelective(SysUsers record);

    SysUsers selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUsers record);

    int updateByPrimaryKey(SysUsers record);
}