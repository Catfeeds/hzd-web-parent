package com.hzcf.platform.api.service;

import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.core.user.model.UserVO;

/**
 * 短信Server
 */
public interface ISmsService {
	/**
	 * 用户注册验证码
	 * @param mobile
	 * @return
	 */
	public BackResult registerSms(String mobile);
	
	/**
	 * 用户找回密码验证码
	 * @param mobile
	 * @return
	 */
	public BackResult findPwdSms(String mobile);
	
	/**
	 * 用户修改密码验证码
	 * @param user
	 * @return
	 */
	public BackResult updatePwdSms(UserVO user);
	
	/**
	 * 
		 * @Description: 短信验证 
		 * @User: 雷佳明
		 * @FileName: WipeRecordMgr.java
		 * @param 参数  
		 * @return 返回类型 
		 * @date 2016年12月9日
		 * @throws
	 */
	public BackResult smsCheck(String key,String mobile,String sms);
}
