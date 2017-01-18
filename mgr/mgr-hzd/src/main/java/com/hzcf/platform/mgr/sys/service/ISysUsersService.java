package com.hzcf.platform.mgr.sys.service;

import java.util.Map;

import com.hzcf.platform.core.sys.model.SysUsersVO;

/**
 *
 * @author 李强
 * 
 */
public interface ISysUsersService {
	
	public boolean CheckLogin(String username,String password);
	/**
	 * @Title: updatePassword 
	 * @Description:后台用户修改登录密码
	 * @time: 2017年1月17日 下午7:07:49  
	 * @return:Map<String,Object>
	 */
	public Map<String,Object> updatePassword(String username,String password,String passwordNew);
}