package com.hzcf.platform.core.user.service;

import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.user.model.UserApplyLogVO;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.framework.core.service.IBaseService;

import java.util.List;
import java.util.Map;

/**
 * Created by lll on 2017-04-10.
 */
public interface UserApplyLogService  extends IBaseService<UserApplyLogVO> {
    /**
     * 查询日志信息
     * @param parmMap
     * @return
     */
     Result<List<UserApplyLogVO>> queryUserApplyLog(Map<String, Object> parmMap);

     Result<Long> getUserApplyLogTotal(Map<String, Object> parmMap);

     Result<String> insertUserApplyLog(UserApplyLogVO userApplyLogVO);
}
