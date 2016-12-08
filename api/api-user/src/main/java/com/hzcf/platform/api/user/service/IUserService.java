package com.hzcf.platform.api.user.service;

import com.hzcf.platform.api.user.common.BackResult;
import com.hzcf.platform.core.user.model.UserVO;

/**
 * 
 * @description:在线进件申请和查询
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
	
	public BackResult register(UserVO user,String type);
	
	public BackResult logonUser(UserVO user);
}
