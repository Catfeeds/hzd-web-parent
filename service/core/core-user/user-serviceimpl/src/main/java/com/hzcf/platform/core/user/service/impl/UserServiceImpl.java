package com.hzcf.platform.core.user.service.impl;


import com.hzcf.platform.core.user.dao.UserDao;
import com.hzcf.platform.core.user.data.User;
import com.hzcf.platform.core.user.service.UserService;
import com.hzcf.platform.framework.core.service.impl.AbstractBaseServiceImpl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.framework.core.service.impl.AbstractBaseServiceImpl;
import com.hzcf.platform.framework.core.storage.IBaseDao;
@Service
public class UserServiceImpl extends AbstractBaseServiceImpl<UserVO,User> implements UserService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserDao purchaseOrderDao;
	
	
	@Override
	protected UserVO getModel() {
		return new UserVO();
	}

	@Override
	protected User getEntity() {
		return new User();
	}

	@Override
	protected IBaseDao<User> getGenericDAO() {
		return purchaseOrderDao;
	}
	
	@Override
	@Transactional(propagation = Propagation.NESTED)
	public Result<Boolean> updateMobile(UserVO user) {
		try {
			User t = toDO(user);
			purchaseOrderDao.updateMobile(t);
			return new Result<Boolean>(StatusCodes.OK, true);
		} catch (Exception e) {
			logger.error("an error occur in update service : {}", e);
			return new Result<Boolean>(StatusCodes.INTERNAL_SERVER_ERROR, false);
		}
	}

	
	/**
	 * 按手机号取得
	 * 
	 * @param mobile
	 * @return
	 */
	@Override
	public Result<UserVO> getByMobile(String mobile){
		try {
			User t = purchaseOrderDao.getByMobile(mobile);
			if (null == t) {
				logger.debug("data null.");
				return new Result<UserVO>(StatusCodes.OK, null);
			}
			return new Result<UserVO>(StatusCodes.OK, toVO(t));
		} catch (Exception e) {
			logger.error("an error occur in getByPK service : {}", e);
			return new Result<UserVO>(StatusCodes.INTERNAL_SERVER_ERROR, getModel());
		}
	}

	@Override
	public Result<String> insertSelective(UserVO userVo){
		try {
			User user = this.toDO(userVo);
			String id =purchaseOrderDao.insertSelective(user);

			return new Result<String>(StatusCodes.OK, id);
		} catch (Exception e) {
			logger.error("an error occur in getByPK service : {}", e);
			return new Result<String>(StatusCodes.INTERNAL_SERVER_ERROR, null);
		}
	}

    @Override
    public Result<Boolean> updateByPrimaryKeySelective(UserVO userVo){
        try {
            User user = this.toDO(userVo);
            purchaseOrderDao.updateByPrimaryKeySelective(user);

            return new Result<Boolean>(StatusCodes.OK, true);
        } catch (Exception e) {
            logger.error("an error occur in getByPK service : {}", e);
            return new Result<Boolean>(StatusCodes.INTERNAL_SERVER_ERROR, false);
        }
    }

	@Override
	public PaginatedResult<UserVO> getUserPage(Map<String, Object> parmMap){
		try {
			PaginatedResult<User> result = purchaseOrderDao.getUserPage(parmMap);
			if (null == result || result.getItems() == null) {
				logger.debug("data null.");
				PaginatedResult<UserVO> resultVO = new PaginatedResult<UserVO>();
				resultVO.setStatus(StatusCodes.OK);
				return resultVO;
			}
			PaginatedResult<UserVO> resultVO = new PaginatedResult<UserVO>();
			resultVO.setItems(toVO(result.getItems()));
			resultVO.setStatus(StatusCodes.OK);
			return resultVO;
		} catch (Exception e) {
			logger.error("an error occur in getByPK service : {}", e);
			PaginatedResult<UserVO> resultVO = new PaginatedResult<UserVO>();
			resultVO.setStatus(StatusCodes.INTERNAL_SERVER_ERROR);
			return resultVO;
		}
	}

}
