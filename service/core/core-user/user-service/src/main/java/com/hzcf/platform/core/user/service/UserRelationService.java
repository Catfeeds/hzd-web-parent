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
    
    public Result<Boolean> deleteByApplyId(String applyId);
}
