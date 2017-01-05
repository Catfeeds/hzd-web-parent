package com.hzcf.platform.core.user.service.impl;

import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.core.user.dao.UserImageDao;
import com.hzcf.platform.core.user.data.User;
import com.hzcf.platform.core.user.data.UserImage;
import com.hzcf.platform.core.user.model.UserImageVO;
import com.hzcf.platform.core.user.service.UserImageService;
import com.hzcf.platform.framework.core.service.impl.AbstractBaseServiceImpl;
import com.hzcf.platform.framework.core.storage.IBaseDao;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by leijiaming on 2016/12/29 0029.
 */
@Service
public class UserImageServiceImpl extends AbstractBaseServiceImpl<UserImageVO,UserImage>  implements UserImageService {
    private Logger logger = LoggerFactory.getLogger(UserImageServiceImpl.class);
    @Autowired
    private UserImageDao purchaseOrderDao;

    @Override
    protected UserImageVO getModel() {
        return new UserImageVO();
    }

    @Override
    protected UserImage getEntity() {
        return new UserImage();
    }

    @Override
    protected IBaseDao<UserImage> getGenericDAO() {
        return purchaseOrderDao;
    }

	/*@Override
	public Result<UserImageVO> getById(String userId) {
		try {
			UserImage t = purchaseOrderDao.getById(userId);
			if (null == t) {
				logger.debug("data null.");
				return new Result<UserImageVO>(StatusCodes.OK, null);
			}
			return new Result<UserImageVO>(StatusCodes.OK, toVO(t));
		} catch (Exception e) {
			logger.error("an error occur in getByPK service : {}", e);
			return new Result<UserImageVO>(StatusCodes.INTERNAL_SERVER_ERROR, getModel());
		}
	}*/

	public Result<Boolean> updateImage(UserImageVO userImageVO){
		try {
			UserImage t = toDO(userImageVO);
			purchaseOrderDao.updateByUserId(t);
			return new Result<Boolean>(StatusCodes.OK, true);
		} catch (Exception e) {
			logger.error("an error occur in update service : {}", e);
			return new Result<Boolean>(StatusCodes.INTERNAL_SERVER_ERROR, false);
		}
	}

	@Override
	public Result<Boolean> insertSelective(UserImageVO userImageVO) {
		try {
			UserImage t = toDO(userImageVO);
			purchaseOrderDao.insertSelective(t);
			return new Result<Boolean>(StatusCodes.OK, true);
		} catch (Exception e) {
			logger.error("an error occur in update service : {}", e);
			return new Result<Boolean>(StatusCodes.INTERNAL_SERVER_ERROR, false);
		}
	}

	@Override
	public Result<UserImageVO> getById(String userId) {
		System.out.println(userId);
		try {
			UserImage t = purchaseOrderDao.getById(userId);
			if (null == t) {
				logger.debug("data null.");
				return new Result<UserImageVO>(StatusCodes.OK, null);
			}
			return new Result<UserImageVO>(StatusCodes.OK, toVO(t));
		} catch (Exception e) {
			logger.error("an error occur in getByPK service : {}", e);
			return new Result<UserImageVO>(StatusCodes.INTERNAL_SERVER_ERROR, getModel());
		}
	}
	//String applyId, String type
	@Override
	public Result<List<UserImageVO>> selectUserImageByApplyIdAndType(Map<String, Object> parmMap) {
		List<UserImageVO> result = null;
		try {
			result = purchaseOrderDao.selectUserImageByApplyIdAndType(parmMap);
			if (null == result) {
				logger.debug("data null.");
				return new Result<List<UserImageVO>>(StatusCodes.OK, null);
			}
			return new Result<List<UserImageVO>>(StatusCodes.OK,result);
		} catch (Exception e) {
			logger.error("an error occur in getByPK service : {}", e);
			return new Result<List<UserImageVO>>(StatusCodes.INTERNAL_SERVER_ERROR,result);
		}
	}
}
