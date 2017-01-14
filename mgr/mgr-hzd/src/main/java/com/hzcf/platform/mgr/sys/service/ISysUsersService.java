package com.hzcf.platform.mgr.sys.service;

import com.hzcf.platform.core.sys.model.SysUsersVO;
/**
 *
 * @author 李强
 * 
 */
public interface ISysUsersService {
	
	public boolean CheckLogin(String username,String password);
	
}
