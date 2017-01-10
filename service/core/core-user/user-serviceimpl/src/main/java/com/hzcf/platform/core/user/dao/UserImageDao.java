package com.hzcf.platform.core.user.dao;


import java.util.List;
import java.util.Map;

import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.user.data.UserImage;
import com.hzcf.platform.core.user.model.UserImageVO;
import com.hzcf.platform.framework.core.storage.IBaseDao;

public interface UserImageDao extends IBaseDao<UserImage> {
    boolean deleteByPrimaryKey(UserImage record);

    int insert(UserImage record);

    boolean insertSelective(UserImage record);

    UserImage selectByPrimaryKey(String imageId);

    int updateByPrimaryKeySelective(UserImage record);

    int updateByPrimaryKey(UserImage record);
    
   // UserImage getByMobile(String mobile);

    List<UserImage> getUserId(String id);
    
    //String applyId,String type
    public List<UserImageVO> selectUserImageByApplyIdAndType(Map<String,Object> paramsMap);
    public List<UserImageVO> selectUserImageByUserIdAndType(Map<String,String> paramsMap);
    boolean updateByImageId(UserImage userImage);
    
    public boolean updateImageByUserIdAndTypeAndUrl(Map<String, String> parmMap);

}