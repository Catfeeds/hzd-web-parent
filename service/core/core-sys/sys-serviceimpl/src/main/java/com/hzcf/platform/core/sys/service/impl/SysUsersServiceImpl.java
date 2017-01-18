package com.hzcf.platform.core.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.core.sys.dao.SysUsersDao;
import com.hzcf.platform.core.sys.data.SysUsers;
import com.hzcf.platform.core.sys.model.SysUsersVO;
import com.hzcf.platform.core.sys.service.SysUsersService;
import com.hzcf.platform.framework.core.service.impl.AbstractBaseServiceImpl;
import com.hzcf.platform.framework.core.storage.IBaseDao;
@Service
public class SysUsersServiceImpl extends AbstractBaseServiceImpl<SysUsersVO,SysUsers> implements SysUsersService {

	@Autowired
	private SysUsersDao purchaseOrderDao;
	
	
	@Override
	protected SysUsersVO getModel() {
		return new SysUsersVO();
	}

	@Override
	protected SysUsers getEntity() {
		return new SysUsers();
	}

	@Override
	protected IBaseDao<SysUsers> getGenericDAO() {
		return purchaseOrderDao;
	}
	
	/**
	 * 获取后台登陆的用户名所对应的密码
	 */
	@Override
	public Result<SysUsersVO> getBySysUsersName(String userName) {
		try {
			SysUsers s = purchaseOrderDao.getBySysUsersName(userName);
			if (null == s) {
				logger.debug("data null.");
				return new Result<SysUsersVO>(StatusCodes.OK, null);
			}
			return new Result<SysUsersVO>(StatusCodes.OK, toVO(s));
		} catch (Exception e) {
			logger.error("an error occur in getByPK service : {}", e);
			return new Result<SysUsersVO>(StatusCodes.INTERNAL_SERVER_ERROR, getModel());
		}
	}
	@Override
	public Result<Boolean> updateByPrimaryKeySelective(SysUsersVO sysUsersVO){
		try {
			purchaseOrderDao.updateByPrimaryKeySelective(sysUsersVO);
			return new Result<Boolean>(StatusCodes.OK, true);
		} catch (Exception e) {
			logger.error("an error occur in updateByPrimaryKeySelective service : {}", e);
			return new Result<Boolean>(StatusCodes.INTERNAL_SERVER_ERROR,false);
		}
	}

	@Override
	public Result<Integer> updateByUserNameSelective(SysUsersVO sysUsersVO) {
		try {
			int result=purchaseOrderDao.updateByUserNameSelective(sysUsersVO);
			return new Result<Integer>(StatusCodes.OK, result);
		} catch (Exception e) {
			logger.error("an error occur in updateByUserNameSelective service : {}", e);
			return new Result<Integer>(StatusCodes.INTERNAL_SERVER_ERROR,0);
		}
	}
	
	
	
	
}
