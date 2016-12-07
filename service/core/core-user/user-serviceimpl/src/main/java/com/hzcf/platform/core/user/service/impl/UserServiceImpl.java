package com.hzcf.platform.core.user.service.impl;


import com.hzcf.platform.core.user.dao.UserDao;
import com.hzcf.platform.core.user.data.User;
import com.hzcf.platform.core.user.service.UserService;
import com.hzcf.platform.framework.core.service.impl.AbstractBaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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


	

}
