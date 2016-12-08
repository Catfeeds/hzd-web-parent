package com.hzcf.platform.core.user.service;

import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.framework.core.service.IBaseService;
/**
 * 用户服务
 * @author gavin.miao
 *
 */
public interface UserService extends IBaseService<UserVO>{
	/**
	 * 按手机号更新
	 * 
	 * @param m
	 * @return
	 */
	public Result<Boolean> updateMobile(UserVO user);
}
