package com.hzcf.platform.core.user.dao.impl;

import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.user.dao.UserApplyLogDao;
import com.hzcf.platform.core.user.data.UserApplyLog;
import com.hzcf.platform.core.user.model.UserApplyLogVO;
import com.hzcf.platform.framework.core.storage.mysql.AbstractMysqlBaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by lll on 2017-04-10.
 */
@Repository
public class UserApplyLogDaoImpl extends AbstractMysqlBaseDaoImpl<UserApplyLog> implements UserApplyLogDao {


    @Override
    public List<UserApplyLog> queryUserApplyLog(Map<String, Object> parmMap) {
        return sqlSessionTemplate.selectList(getSqlName("queryUserApplyLog"), parmMap);

    }

    @Override
    public Long getUserApplyLogTotal(Map<String, Object> parmMap) {
        return sqlSessionTemplate.selectOne(getSqlName("getUserApplyLogTotal"), parmMap);
    }

    @Override
    public String insertUserApplyLog(UserApplyLog userApplyLog) {
        this.sqlSessionTemplate.insert(this.getSqlName("insertUserApplyLog"), userApplyLog);
        return userApplyLog.getApplyId();
    }
}
