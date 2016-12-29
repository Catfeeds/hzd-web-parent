package com.hzcf.platform.core.user.service.impl;

import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.core.user.dao.UserApplyInfoDao;
import com.hzcf.platform.core.user.data.UserApplyInfo;
import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.core.user.service.UserApplyInfoSerivce;
import com.hzcf.platform.framework.core.service.impl.AbstractBaseServiceImpl;
import com.hzcf.platform.framework.core.storage.IBaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by leijiaming on 2016/12/29 0029.
 */
@Service
public class UserApplyInfoSerivceImpl  extends AbstractBaseServiceImpl<UserApplyInfoVO,UserApplyInfo> implements UserApplyInfoSerivce {

    private Logger logger = LoggerFactory.getLogger(UserApplyInfoSerivceImpl.class);
    @Autowired
    private UserApplyInfoDao purchaseOrderDao;

    @Override
    public Result<UserApplyInfoVO> selectByUserId(String userId) {
        try {
            UserApplyInfo t = purchaseOrderDao.selectByUserId(userId);
            if (null == t) {
                logger.debug("data null.");
                return new Result<UserApplyInfoVO>(StatusCodes.OK, null);
            }
            return new Result<UserApplyInfoVO>(StatusCodes.OK, toVO(t));
        } catch (Exception e) {
            logger.error("an error occur in getByPK service : {}", e);
            return new Result<UserApplyInfoVO>(StatusCodes.INTERNAL_SERVER_ERROR, getModel());
        }
    }


    @Override
    protected UserApplyInfoVO getModel() {
        return new UserApplyInfoVO();
    }

    @Override
    protected UserApplyInfo getEntity() {
        return new UserApplyInfo();
    }

    @Override
    protected IBaseDao<UserApplyInfo> getGenericDAO() {
        return  purchaseOrderDao;
    }
}
