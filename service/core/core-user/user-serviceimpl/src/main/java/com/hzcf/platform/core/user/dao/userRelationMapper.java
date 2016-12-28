package com.hzcf.platform.core.user.dao;


import com.hzcf.platform.core.user.data.userRelation;

public interface userRelationMapper {
    int deleteByPrimaryKey(String relationId);

    int insert(userRelation record);

    int insertSelective(userRelation record);

    userRelation selectByPrimaryKey(String relationId);

    int updateByPrimaryKeySelective(userRelation record);

    int updateByPrimaryKey(userRelation record);
}