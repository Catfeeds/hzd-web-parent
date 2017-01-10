package com.hzcf.platform.core.user.dao;


import com.hzcf.platform.core.user.data.UserRelation;
import com.hzcf.platform.framework.core.storage.IBaseDao;

import java.util.List;

public interface UserRelationDao extends IBaseDao<UserRelation> {
    int deleteByPrimaryKey(String relationId);



    int insertSelective(UserRelation record);

    UserRelation selectByPrimaryKey(String relationId);

    /**
     * 根据申请编号查询
     * @param applyId
     * @return
     */
    List<UserRelation> selectByApplyId(String applyId);

    boolean updateByPrimaryKeySelective(UserRelation record);

    int updateByPrimaryKey(UserRelation record);

}