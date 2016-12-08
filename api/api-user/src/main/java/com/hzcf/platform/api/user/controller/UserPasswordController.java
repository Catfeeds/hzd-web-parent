package com.hzcf.platform.api.user.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hzcf.platform.annotation.RequestBodyForm;
import com.hzcf.platform.api.user.common.BackResult;
import com.hzcf.platform.api.user.property.RockProperty;
import com.hzcf.platform.api.user.service.IOnlineLoanService;
import com.hzcf.platform.api.user.service.IUserpwdForService;
import com.hzcf.platform.common.cache.ICache;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.config.BaseConfig;
import com.hzcf.platform.config.ConfigSmsUtil;
import com.hzcf.platform.core.ConstantsToken;
import com.hzcf.platform.core.MyfStatusCodeEnum;
import com.hzcf.platform.core.user.model.UserVO;

/**
 * 
 * @description:短信Controller
 * @author zhang
 * @version 1.0
 */
@RestController
public class UserPasswordController {
	private static final Log logger = Log.getLogger(UserPasswordController.class);
    @Autowired
	public IUserpwdForService userpwdForService;
	
    /**
     * 修改密码
     * @param user
     * @return
     */
	@RequestMapping(value="api/user/updatepwd/{type}",method=RequestMethod.POST)
	public BackResult updatepwdForlogin(@RequestBodyForm UserVO user,@PathVariable String type){
		logger.i("进入修改密码====UserVO:"+user.toString()+"num:"+type);
		if(ConfigSmsUtil.superSmsNum(user.getMobile(), type, ConstantsToken.SMS_CACHE_UPDATEPWD_KEY)){
			return new BackResult(MyfStatusCodeEnum.MEF_CODE_0000.getCode(),MyfStatusCodeEnum.MEF_CODE_0000.getMsg());
		}
		return userpwdForService.updatepwdForlogin(user, type);
	}
	
    /**
     * 找回密码
     * @param user
     * @return
     */
	@RequestMapping(value="api/user/findpwd/{type}",method=RequestMethod.POST)
	public BackResult findpwdForlogin(@RequestBodyForm UserVO user,@PathVariable String type){
		logger.i("进入修改密码====UserVO:"+user.toString());
		if(ConfigSmsUtil.superSmsNum(user.getMobile(), type, ConstantsToken.SMS_CACHE_FINDPWD_KEY)){
			return new BackResult(MyfStatusCodeEnum.MEF_CODE_0000.getCode(),MyfStatusCodeEnum.MEF_CODE_0000.getMsg());
		}
		return userpwdForService.findpwdForlogin(user, type);
	}
	
	
}
