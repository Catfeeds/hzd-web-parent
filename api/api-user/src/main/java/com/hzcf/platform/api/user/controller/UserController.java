package com.hzcf.platform.api.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hzcf.platform.annotation.RequestBodyForm;
import com.hzcf.platform.api.user.common.BackResult;
import com.hzcf.platform.api.user.service.IUserService;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.config.ConfigSmsUtil;
import com.hzcf.platform.core.ConstantsToken;
import com.hzcf.platform.core.MyfStatusCodeEnum;
import com.hzcf.platform.core.user.model.UserVO;

/**
 * 
 * @description:用户注册
 * @author 雷佳明
 * @version 1.0
 * 
 * <pre>
 * Modification History: 
 * Date              Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年12月6日                       雷佳明                           1.0       1.0 Version 
 * </pre>
 */
@RestController
public class UserController {
	private static final Log logger = Log.getLogger(UserController.class);
    
	@Autowired
	IUserService registerUserService;
	
	@RequestMapping(value="api/user/register/{type}",method=RequestMethod.POST)
	public BackResult register(@RequestBodyForm UserVO user,@PathVariable String type){
		logger.i("进入用户注册功能 ====入参====UserVO:"+user.toString());
		if(ConfigSmsUtil.superSmsNum(user.getMobile(), type, ConstantsToken.SMS_CACHE_REG_KEY)){
			return new BackResult(MyfStatusCodeEnum.MEF_CODE_0000.getCode(),MyfStatusCodeEnum.MEF_CODE_0000.getMsg());
		}
		return registerUserService.register(user,type);
	}
	
	@RequestMapping(value="api/user/logon",method=RequestMethod.POST)
	public BackResult logonUser(@RequestBodyForm UserVO user){
		logger.i("进入用户登录功能 ====入参====UserVO:"+user.toString());
		return registerUserService.logonUser(user);
	}
	
}
