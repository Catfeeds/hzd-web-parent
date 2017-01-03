package com.hzcf.platform.core.user.service.impl;

import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.core.user.dao.UserImageDao;
import com.hzcf.platform.core.user.data.UserImage;
import com.hzcf.platform.core.user.model.UserImageVO;
import com.hzcf.platform.core.user.service.UserImageService;
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

	@Override
	public Result<UserImageVO> getByMobile(String mobile) {
		try {
			UserImage t = purchaseOrderDao.getByMobile(mobile);
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
}
