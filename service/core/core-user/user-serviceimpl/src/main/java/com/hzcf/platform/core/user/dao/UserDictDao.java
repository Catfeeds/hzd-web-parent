package com.hzcf.platform.core.user.dao;


import com.hzcf.platform.core.user.data.UserDict;
import com.hzcf.platform.framework.core.storage.IBaseDao;

import java.util.List;

public interface UserDictDao extends IBaseDao<UserDict> {
    int deleteByPrimaryKey(String dictId);

    int insert(UserDict record);

    int insertSelective(UserDict record);

    UserDict selectByPrimaryKey(String dictId);

    int updateByPrimaryKeySelective(UserDict record);

    int updateByPrimaryKey(UserDict record);

    List<UserDict> selectList();
    List<UserDict> selectJkytList();
}