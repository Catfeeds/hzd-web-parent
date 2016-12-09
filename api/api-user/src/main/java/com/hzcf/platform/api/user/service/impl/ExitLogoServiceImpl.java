package com.hzcf.platform.api.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.hzcf.platform.api.user.common.BackResult;
import com.hzcf.platform.api.user.service.IExitLogoService;
import com.hzcf.platform.common.cache.ICache;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.core.ConstantsToken;
import com.hzcf.platform.core.MyfStatusCodeEnum;
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
public class ExitLogoServiceImpl implements IExitLogoService {
	private static final Log logger = Log.getLogger(ExitLogoServiceImpl.class);

	@Autowired
    private ICache cache;	
	@Override
	public BackResult exitLogo(UserVO user) {
		try {
			cache.delete(ConstantsToken.USER_CACHE_KEY+user.getToken());
			return new BackResult(MyfStatusCodeEnum.MEF_CODE_0000.getCode(),MyfStatusCodeEnum.MEF_CODE_0000.getMsg());
		} catch (Exception e) {
			logger.i("退出登录出现异常");
			return new BackResult(MyfStatusCodeEnum.MEF_CODE_9999.getCode(),MyfStatusCodeEnum.MEF_CODE_9999.getMsg());
		}
	}

}
