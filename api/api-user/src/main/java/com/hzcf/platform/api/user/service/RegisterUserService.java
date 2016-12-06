package com.hzcf.platform.api.user.service;

import com.hzcf.platform.api.user.common.BackResult;
import com.hzcf.platform.core.user.model.UserVO;

/**
 * 
 * @description:用户注册
 * @author lei
 * @version 1.0
 * 
 * <pre>
 * Modification History: 
 * Date              Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年12月6日                         lei         1.0       1.0 Version 
 * </pre>
 */
public interface RegisterUserService {
	
	public BackResult Register(UserVO user);
}
