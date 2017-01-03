package com.hzcf.platform.core.user.dao;


import com.hzcf.platform.core.user.data.UserImage;
import com.hzcf.platform.framework.core.storage.IBaseDao;

public interface UserImageDao extends IBaseDao<UserImage> {
    int deleteByPrimaryKey(String imageId);

    int insert(UserImage record);

    int insertSelective(UserImage record);

    UserImage selectByPrimaryKey(String imageId);

    int updateByPrimaryKeySelective(UserImage record);

    int updateByPrimaryKey(UserImage record);
    
   // UserImage getByMobile(String mobile);
    
    UserImage getById(String id);
    
    boolean updateByUserId(String userId);
}