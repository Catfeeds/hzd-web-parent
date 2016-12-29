package com.hzcf.platform.api.service;

import javax.servlet.http.HttpServletRequest;

import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.config.RequestAgent;
import com.hzcf.platform.core.user.model.UserVO;

/**
 * 
 * @description:登录和注册
 * @author 雷佳明
 * @version 1.0
 * 
 * <pre>
 * Modification History: 
 * Date              Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年12月7日                       雷佳明                           1.0       1.0 Version 
 * </pre>
 */
public interface IUserService {
	
	public BackResult register(UserVO user);
	
	public BackResult logonUser(UserVO user,HttpServletRequest request,RequestAgent agent);
	
	public BackResult exitLogon(UserVO user);
	public BackResult isLogon(UserVO user);
}
