package com.hzcf.platform.core.user.dao;


import java.util.List;
import java.util.Map;

import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.user.data.UserImage;
import com.hzcf.platform.core.user.model.UserImageVO;
import com.hzcf.platform.framework.core.storage.IBaseDao;

public interface UserImageDao extends IBaseDao<UserImage> {
    int deleteByPrimaryKey(String imageId);

    int insert(UserImage record);

    boolean insertSelective(UserImage record);

    UserImage selectByPrimaryKey(String imageId);

    int updateByPrimaryKeySelective(UserImage record);

    int updateByPrimaryKey(UserImage record);
    
   // UserImage getByMobile(String mobile);
    
    UserImage getById(String id);
    
    //String applyId,String type
    public List<UserImageVO> selectUserImageByApplyIdAndType(Map<String,Object> paramsMap);
    public List<UserImageVO> selectUserImageByUserIdAndType(Map<String,String> paramsMap);
    boolean updateByUserId(UserImage userImage);
}