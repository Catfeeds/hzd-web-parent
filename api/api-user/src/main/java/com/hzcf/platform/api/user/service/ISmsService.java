package com.hzcf.platform.api.user.service;

import com.hzcf.platform.api.user.common.BackResult;
import com.hzcf.platform.core.user.model.UserVO;

/**
 * 短信Server
 */
public interface ISmsService {
	/**
	 * 用户注册验证码
	 * @param user
	 * @return
	 */
	public BackResult registerSms(UserVO user);
	
	/**
	 * 用户找回密码验证码
	 * @param user
	 * @return
	 */
	public BackResult findPwdSms(UserVO user);
	
	/**
	 * 用户修改密码验证码
	 * @param user
	 * @return
	 */
	public BackResult updatePwdSms(UserVO user);
}
