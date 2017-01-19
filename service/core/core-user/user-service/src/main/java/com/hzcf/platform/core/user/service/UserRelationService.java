package com.hzcf.platform.core.user.service;


import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.user.model.UserRelationVO;
import com.hzcf.platform.framework.core.service.IBaseService;

import java.util.List;

/**
 * Created by leijiaming on 2016/12/29 0029.
 */
public interface UserRelationService extends IBaseService<UserRelationVO> {

    Result<List<UserRelationVO>> selectByApplyId(String applyId);
    public Result<Boolean> updateRelationId(UserRelationVO userRelationVO);
    /**
     * @Title: deleteByApplyId 
     * @Description:根据applyId删除借款人关系信息 
     * @time: 2017年1月18日 下午8:02:19  
     * @return:Result<Boolean>
     */
    public Result<Boolean> deleteByRelationApplyId(String applyId);
    /**
     * @Title: deleteByApplyIdList
     * @Description:根据applyId集合删除借款人关系信息 
     * @time: 2017年1月18日 下午8:02:19  
     * @return:Result<Boolean>
     */
    public Result<Boolean> deleteRelationByApplyIdList(List<String> applyIdList);
}