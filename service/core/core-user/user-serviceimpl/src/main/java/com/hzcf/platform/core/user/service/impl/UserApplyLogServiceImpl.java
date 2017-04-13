package com.hzcf.platform.core.user.service.impl;

import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.core.user.dao.UserApplyLogDao;
import com.hzcf.platform.core.user.data.UserApplyLog;
import com.hzcf.platform.core.user.model.UserApplyLogVO;
import com.hzcf.platform.core.user.service.UserApplyLogService;
import com.hzcf.platform.framework.core.service.impl.AbstractBaseServiceImpl;
import com.hzcf.platform.framework.core.storage.IBaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by lll on 2017-04-10.
 */
@Service
public class UserApplyLogServiceImpl extends AbstractBaseServiceImpl<UserApplyLogVO,UserApplyLog> implements UserApplyLogService {

    private Logger logger = LoggerFactory.getLogger(UserApplyLogServiceImpl.class);

    @Autowired
    private UserApplyLogDao purchaseOrderDao;

    @Override
    public Result<List<UserApplyLogVO>> queryUserApplyLog(Map<String, Object> parmMap) {
        try {
            List<UserApplyLog> userApplyLogs = purchaseOrderDao.queryUserApplyLog(parmMap);
            return new Result<List<UserApplyLogVO>>(StatusCodes.OK,toVO(userApplyLogs));
        } catch (Exception e) {
            logger.error("an error occur in update service : {}", e);
            return new Result<List<UserApplyLogVO>>(StatusCodes.INTERNAL_SERVER_ERROR, null);
        }
    }

    @Override
    public Result<Long> getUserApplyLogTotal(Map<String, Object> parmMap) {
        try {
            Long userApplyLogTotal = purchaseOrderDao.getUserApplyLogTotal(parmMap);
            return new Result<Long>(StatusCodes.OK,userApplyLogTotal);
        } catch (Exception e) {
            logger.error("an error occur in update service : {}", e);
            return new Result<Long>(StatusCodes.INTERNAL_SERVER_ERROR, null);
        }
    }

    @Override
    public Result<String> insertUserApplyLog(UserApplyLogVO userApplyLogVO) {
        try {
            UserApplyLog userApplyLog = toDO(userApplyLogVO);
            String id = purchaseOrderDao.insertUserApplyLog(userApplyLog);
            return new Result<String>(StatusCodes.OK,id);
        } catch (Exception e) {
            logger.error("an error occur in update service : {}", e);
            return new Result<String>(StatusCodes.INTERNAL_SERVER_ERROR, null);
        }
    }

    @Override
    protected UserApplyLogVO getModel() {
        return new UserApplyLogVO();
    }

    @Override
    protected UserApplyLog getEntity() {
        return new UserApplyLog();
    }

    @Override
    protected IBaseDao<UserApplyLog> getGenericDAO() {
        return purchaseOrderDao;
    }
}
