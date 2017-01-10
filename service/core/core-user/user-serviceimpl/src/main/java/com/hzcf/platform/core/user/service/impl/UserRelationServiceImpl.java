package com.hzcf.platform.core.user.service.impl;

import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.core.user.dao.UserInfoDao;
import com.hzcf.platform.core.user.dao.UserRelationDao;
import com.hzcf.platform.core.user.data.UserApplyInfo;
import com.hzcf.platform.core.user.data.UserRelation;
import com.hzcf.platform.core.user.model.UserInfoVO;
import com.hzcf.platform.core.user.model.UserRelationVO;
import com.hzcf.platform.core.user.service.UserRelationService;
import com.hzcf.platform.framework.core.service.impl.AbstractBaseServiceImpl;
import com.hzcf.platform.framework.core.storage.IBaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by leijiaming on 2016/12/29 0029.
 */
@Service
public class UserRelationServiceImpl  extends AbstractBaseServiceImpl<UserRelationVO,UserRelation> implements UserRelationService {

    private Logger logger = LoggerFactory.getLogger(UserRelationServiceImpl.class);
    @Autowired
    private UserRelationDao purchaseOrderDao;


    @Override
    public Result<List<UserRelationVO>> selectByApplyId(String applyId) {
        try {
            List<UserRelation> t = purchaseOrderDao.selectByApplyId(applyId);
            if (null == t) {
                logger.debug("data null.");
                return new Result<List<UserRelationVO>>(StatusCodes.OK, null);
            }
            return new Result<List<UserRelationVO>>(StatusCodes.OK, toVO(t));
        } catch (Exception e) {
            logger.error("an error occur in getByPK service : {}", e);
            return new Result<List<UserRelationVO>>(StatusCodes.INTERNAL_SERVER_ERROR, (List<UserRelationVO>) getModel());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public Result<Boolean> updateRelationId(UserRelationVO userRelationVO) {
        try {
            UserRelation t = toDO(userRelationVO);
            purchaseOrderDao.updateByPrimaryKeySelective(t);
            return new Result<Boolean>(StatusCodes.OK, true);
        } catch (Exception e) {
            logger.error("an error occur in update service : {}", e);
            return new Result<Boolean>(StatusCodes.INTERNAL_SERVER_ERROR, false);
        }
    }


    @Override
    protected UserRelationVO getModel() {
        return new UserRelationVO();
    }

    @Override
    protected UserRelation getEntity() {
        return new UserRelation();
    }

    @Override
    protected IBaseDao<UserRelation> getGenericDAO() {
        return purchaseOrderDao;
    }
}
