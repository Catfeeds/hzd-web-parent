package com.hzcf.platform.api.user.service;

import com.hzcf.platform.annotation.RequestAttribute;
import com.hzcf.platform.api.user.common.BackResult;
import com.hzcf.platform.config.BaseConfig;
import com.hzcf.platform.core.user.model.UserVO;

/**
 * 
 * @description:退出登录
 * @author 雷佳明
 * @version 1.0
 * 
 * <pre>
 * Modification History: 
 * Date              Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年12月9日                       雷佳明                           1.0       1.0 Version 
 * </pre>
 */
public interface IExitLogoService {
	public BackResult  exitLogo( UserVO user);
}
