package com.hzcf.platform.core.user.service.impl;

import com.hzcf.platform.common.util.rpc.result.Result;

import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.core.user.dao.UserInfoDao;
import com.hzcf.platform.core.user.data.UserInfo;
import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.core.user.model.UserInfoVO;
import com.hzcf.platform.core.user.service.UserInfoService;
import com.hzcf.platform.framework.core.service.impl.AbstractBaseServiceImpl;
import com.hzcf.platform.framework.core.storage.IBaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by leijiaming on 2016/12/29 0029.
 */
public class UserInfoServiceImpl extends AbstractBaseServiceImpl<UserInfoVO,UserInfo> implements UserInfoService {

    private Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);
    @Autowired
    private UserInfoDao purchaseOrderDao;


    @Override
    public Result<UserInfoVO> selectByApplyId(String applyId) {
        try {
            UserInfo t = purchaseOrderDao.selectByApplyId(applyId);
            if (null == t) {
                logger.debug("data null.");
                return new Result<UserInfoVO>(StatusCodes.OK, null);
            }
            return new Result<UserInfoVO>(StatusCodes.OK, toVO(t));
        } catch (Exception e) {
            logger.error("an error occur in getByPK service : {}", e);
            return new Result<UserInfoVO>(StatusCodes.INTERNAL_SERVER_ERROR, getModel());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public Result<Boolean> updateUserInfo(UserInfoVO userInfoVO) {
        try {
            UserInfo t = toDO(userInfoVO);
            purchaseOrderDao.updateByApplyId(t);
            return new Result<Boolean>(StatusCodes.OK, true);
        } catch (Exception e) {
            logger.error("an error occur in update service : {}", e);
            return new Result<Boolean>(StatusCodes.INTERNAL_SERVER_ERROR, false);
        }
    }

    @Override
    protected UserInfoVO getModel() {
        return new UserInfoVO();
    }

    @Override
    protected UserInfo getEntity() {
        return new UserInfo();
    }

    @Override
    protected IBaseDao<UserInfo> getGenericDAO() {
        return purchaseOrderDao;
    }
}
