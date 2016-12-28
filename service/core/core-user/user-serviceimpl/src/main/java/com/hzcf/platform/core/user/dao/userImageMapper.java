package com.hzcf.platform.core.user.dao;


import com.hzcf.platform.core.user.data.userImage;

public interface userImageMapper {
    int deleteByPrimaryKey(String imageId);

    int insert(userImage record);

    int insertSelective(userImage record);

    userImage selectByPrimaryKey(String imageId);

    int updateByPrimaryKeySelective(userImage record);

    int updateByPrimaryKey(userImage record);
}