package com.hzcf.platform.core.user.service.impl;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.core.user.dao.UserApplyInfoDao;
import com.hzcf.platform.core.user.data.User;
import com.hzcf.platform.core.user.data.UserApplyInfo;
import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.core.user.service.UserApplyInfoSerivce;
import com.hzcf.platform.framework.core.service.impl.AbstractBaseServiceImpl;
import com.hzcf.platform.framework.core.storage.IBaseDao;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    public Result<UserApplyInfoVO> selectByApplyId(String applyId) {
        try {
            UserApplyInfo t = purchaseOrderDao.selectByApplyId(applyId);
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


	@Override
	public PaginatedResult<UserApplyInfoVO> getUserApplyInfoList(Map<String, Object> parmMap) {
		try {
			List<UserApplyInfoVO> result = purchaseOrderDao.getUserApplyInfoList(parmMap);
			if (null == result) {
				logger.debug("data null.");
				PaginatedResult<UserApplyInfoVO> resultVO = new PaginatedResult<UserApplyInfoVO>();
				resultVO.setStatus(StatusCodes.OK);
				return resultVO;
			}
			PaginatedResult<UserApplyInfoVO> resultVO = new PaginatedResult<UserApplyInfoVO>();
			resultVO.setItems(result);
			resultVO.setStatus(StatusCodes.OK);
			return resultVO;
		} catch (Exception e) {
			logger.error("an error occur in UserApplyInfoSerivceImpl service : {getUserApplyInfoList}", e);
			PaginatedResult<UserApplyInfoVO> resultVO = new PaginatedResult<UserApplyInfoVO>();
			resultVO.setStatus(StatusCodes.INTERNAL_SERVER_ERROR);
			return resultVO;
		}
	}


	@Override
	public Long getUserApplyInfoTotal(Map<String, Object> parmMap) {
		return purchaseOrderDao.getUserApplyInfoTotal(parmMap);
	}

	public PaginatedResult<UserApplyInfoVO> getUserApplyForSearch(Map<String, Object> parmMap){
		try {
			List<UserApplyInfoVO> result = purchaseOrderDao.getUserApplyForSearch(parmMap);
			if (null == result) {
				logger.debug("data null.");
				PaginatedResult<UserApplyInfoVO> resultVO = new PaginatedResult<UserApplyInfoVO>();
				resultVO.setStatus(StatusCodes.OK);
				return resultVO;
			}
			PaginatedResult<UserApplyInfoVO> resultVO = new PaginatedResult<UserApplyInfoVO>();
			resultVO.setItems(result);
			resultVO.setStatus(StatusCodes.OK);
			return resultVO;
		} catch (Exception e) {
			logger.error("an error occur in UserApplyInfoSerivceImpl service : {getUserApplyForSearch}", e);
			PaginatedResult<UserApplyInfoVO> resultVO = new PaginatedResult<UserApplyInfoVO>();
			resultVO.setStatus(StatusCodes.INTERNAL_SERVER_ERROR);
			return resultVO;
		}
	}

	@Override
	@Transactional(propagation = Propagation.NESTED)
	public Result<Boolean> updateApplyId(UserApplyInfoVO userApplyInfoVO) {
		try {
			UserApplyInfo t = toDO(userApplyInfoVO);
			purchaseOrderDao.updateApplyId(t);
			return new Result<Boolean>(StatusCodes.OK, true);
		} catch (Exception e) {
			logger.error("an error occur in update service : {}", e);
			return new Result<Boolean>(StatusCodes.INTERNAL_SERVER_ERROR, false);
		}
	}

	@Override
	public Result<UserApplyInfoVO> selectByUserIdAndStatus(Map<String, Object> parmMap) {
		try {
			UserApplyInfoVO t = purchaseOrderDao.selectByUserIdAndStatus(parmMap);
            if (null == t) {
                logger.debug("data null.");
                return new Result<UserApplyInfoVO>(StatusCodes.OK, null);
            }
            return new Result<UserApplyInfoVO>(StatusCodes.OK, t);
        } catch (Exception e) {
            logger.error("an error occur in selectByUserIdAndStatus service : {}", e);
            return new Result<UserApplyInfoVO>(StatusCodes.INTERNAL_SERVER_ERROR, getModel());
        }
	}
	/**
	 * @Title: deleteByApplyId 
	 * @Description:根据applyId删除借款人的申请信息 
	 * @time: 2017年1月18日 下午5:22:18  
	 * @return:Result<Boolean>
	 */
	@Override
	public Result<Boolean> deleteByApplyId(String applyId){
		try {
			purchaseOrderDao.deleteByApplyId(applyId);
			return new Result<Boolean>(StatusCodes.OK, true);
		} catch (Exception e) {
			logger.error("an error occur in deleteByApplyId service : {}", e);
			return new Result<Boolean>(StatusCodes.INTERNAL_SERVER_ERROR, false);
		}
	}
}
