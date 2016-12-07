package com.hzcf.platform.api.user.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.api.model.OnlineLoanInfo;
import com.hzcf.platform.api.user.common.BackResult;
import com.hzcf.platform.api.user.controller.UserController;
import com.hzcf.platform.api.user.service.IOnlineLoanService;
import com.hzcf.platform.api.user.service.IUserService;
import com.hzcf.platform.common.cache.ICache;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.common.util.uuid.UUIDGenerator;
import com.hzcf.platform.core.ConstantsToken;
import com.hzcf.platform.core.MyfStatusCodeEnum;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.core.user.service.UserService;

@Service
public class OnlineLoanServiceImpl implements IOnlineLoanService {
	private static final Log logger = Log.getLogger(OnlineLoanServiceImpl.class);

	@Override
	public BackResult OnlineLoanApply(UserVO user, OnlineLoanInfo onlineLoanInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BackResult OnlineLoanQuery(UserVO user) {
		// TODO Auto-generated method stub
		return null;
	}
	
}