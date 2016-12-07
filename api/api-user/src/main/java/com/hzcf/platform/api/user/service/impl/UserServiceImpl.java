package com.hzcf.platform.api.user.service.impl;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.api.user.common.BackResult;
import com.hzcf.platform.api.user.controller.UserController;
import com.hzcf.platform.api.user.service.IUserService;
import com.hzcf.platform.common.cache.ICache;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.common.util.uuid.UUIDGenerator;
import com.hzcf.platform.core.MyfStatusCodeEnum;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.core.user.service.UserService;

@Service
public class UserServiceImpl implements IUserService {
	private static final Log logger = Log.getLogger(UserServiceImpl.class);
	@Autowired
	public UserService userSerivce;
	@Autowired
    private ICache cache;
	@Override
	public BackResult register(UserVO user) {
		Result<UserVO> byMobile = userSerivce.getByMobile(user.getMobile());
		UserVO items = byMobile.getItems();
		if(items!=null){
			logger.i("此用户已经注册 ---手机号:"+user.getMobile());
			return new BackResult(MyfStatusCodeEnum.MEF_CODE_1010.getCode(),MyfStatusCodeEnum.MEF_CODE_1010.getMsg());
		}
		user.setId(UUIDGenerator.getUUID());
		user.setCreateTime(new Date());
		Result<String> create = userSerivce.create(user);
		if(StatusCodes.OK==create.getStatus()){
			logger.i("注册成功 ---手机号:"+user.getMobile());
			return new BackResult(MyfStatusCodeEnum.MEF_CODE_0000.getCode(),MyfStatusCodeEnum.MEF_CODE_0000.getMsg());
		}
		
		return null;
	}

}
