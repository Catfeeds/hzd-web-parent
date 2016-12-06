package com.hzcf.platform.api.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.api.user.common.BackResult;
import com.hzcf.platform.api.user.service.RegisterUserService;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.core.user.service.UserService;
@Service
public class RegisterUserServiceImpl implements RegisterUserService {
	@Autowired
	public UserService userSerivce;
	
	@Override
	public BackResult Register(UserVO user) {
		
		
		
		return null;
	}

}
