package com.hzcf.platform.core.user.dao;


import com.hzcf.platform.core.user.data.UserImage;

public interface UserImageDao {
    int deleteByPrimaryKey(String imageId);

    int insert(UserImage record);

    int insertSelective(UserImage record);

    UserImage selectByPrimaryKey(String imageId);

    int updateByPrimaryKeySelective(UserImage record);

    int updateByPrimaryKey(UserImage record);
}