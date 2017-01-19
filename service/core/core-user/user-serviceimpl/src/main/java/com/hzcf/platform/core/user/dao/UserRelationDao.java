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

    
    public boolean deleteByApplyId(String applyId);
    /**
     * @Title: deleteByApplyIdList
     * @Description:根据applyId集合删除借款人关系信息 
     * @time: 2017年1月18日 下午8:02:19  
     * @return:Result<Boolean>
     */
    public boolean deleteByApplyIdList(List<String> applyIdList);
}