package com.hzcf.platform.core.user.dao;

import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.user.data.UserApplyLog;
import com.hzcf.platform.core.user.model.UserApplyLogVO;
import com.hzcf.platform.framework.core.storage.IBaseDao;

import java.util.List;
import java.util.Map;

/**
 * Created by lll on 2017-04-10.
 */
public interface UserApplyLogDao extends IBaseDao<UserApplyLog> {
    /**
     * 查询日志信息
     * @param parmMap
     * @return
     */
     List<UserApplyLog> queryUserApplyLog(Map<String, Object> parmMap);

     Long getUserApplyLogTotal(Map<String, Object> parmMap);

     String insertUserApplyLog(UserApplyLog userApplyLog);

}
