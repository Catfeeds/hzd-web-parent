package com.hzcf.platform.api.user.service.impl;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.api.user.common.BackResult;
import com.hzcf.platform.api.user.service.IUserService;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.uuid.UUIDGenerator;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.core.user.service.UserService;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	public UserService userSerivce;
	
	@Override
	public BackResult Register(UserVO user) {
		
		
		
		user.setId(UUIDGenerator.getUUID());
		user.setCreateTime(new Date());
		Result<Long> create = userSerivce.create(user);
		return null;
	}

}
