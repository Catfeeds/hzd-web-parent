package com.hzcf.platform.core.user.dao;


import com.hzcf.platform.core.user.data.UserRelation;

public interface userRelationDao {
    int deleteByPrimaryKey(String relationId);

    int insert(UserRelation record);

    int insertSelective(UserRelation record);

    UserRelation selectByPrimaryKey(String relationId);

    int updateByPrimaryKeySelective(UserRelation record);

    int updateByPrimaryKey(UserRelation record);
}