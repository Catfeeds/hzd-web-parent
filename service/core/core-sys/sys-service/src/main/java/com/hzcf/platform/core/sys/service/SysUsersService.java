package com.hzcf.platform.core.sys.service;

import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.sys.model.SysUsersVO;
import com.hzcf.platform.framework.core.service.IBaseService;


/**
 * @description:用于获取后台登陆的用户名对应密码
 * @author 李强
 * @version 1.0
 * 
 * <pre>
 * Modification History: 
 * Date              Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年1月3日                            李强                          	 1.0      	 1.0 Version 
 * </pre>
 */
public interface SysUsersService extends IBaseService<SysUsersVO>{
	
	public Result<SysUsersVO> getBySysUsersName(String userName);
}
