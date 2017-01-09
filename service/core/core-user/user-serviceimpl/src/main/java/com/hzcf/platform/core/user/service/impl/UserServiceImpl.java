package com.hzcf.platform.core.user.service.impl;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.core.user.dao.UserDao;
import com.hzcf.platform.core.user.data.User;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.core.user.service.UserService;
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
	public PaginatedResult<UserVO> getUserList(Map<String, Object> parmMap){
		try {
			List<User> result = purchaseOrderDao.getUserList(parmMap);
			if (null == result) {
				logger.debug("data null.");
				PaginatedResult<UserVO> resultVO = new PaginatedResult<UserVO>();
				resultVO.setStatus(StatusCodes.OK);
				return resultVO;
			}
			PaginatedResult<UserVO> resultVO = new PaginatedResult<UserVO>();
			resultVO.setItems(toVO(result));
			resultVO.setStatus(StatusCodes.OK);
			return resultVO;
		} catch (Exception e) {
			logger.error("an error occur in getByPK service : {}", e);
			PaginatedResult<UserVO> resultVO = new PaginatedResult<UserVO>();
			resultVO.setStatus(StatusCodes.INTERNAL_SERVER_ERROR);
			return resultVO;
		}
	}
	
	@Override
	public Long getUserTotal(Map<String, Object> parmMap){
		return purchaseOrderDao.getUserTotal(parmMap);
	}
	/*根据用户的真实姓名（name）和身份证号码（id_card），查询“真实姓名”和“身份证号码”重复的数量
	 *	请求数据：
			格式：Map<String,Object>
			参数：name，idCard
		返回数据：
			格式：Map<String,Object>
			参数：realnamerepeat（真实姓名重复的数量），idcardrepeat（身份证号码重复的数量）,allrepeat（真实姓名和身份证号码总共的重复数量）
	 */
	@Override
	public Map selectNameAndIdCardRepeat(Map<String, Object> parmMap) {
		return purchaseOrderDao.selectNameAndIdCardRepeat(parmMap);
	}
	
}
