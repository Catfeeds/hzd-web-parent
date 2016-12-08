package com.hzcf.platform.api.user.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hzcf.platform.annotation.RequestAttribute;
import com.hzcf.platform.annotation.RequestBodyForm;
import com.hzcf.platform.api.model.OnlineLoanInfo;
import com.hzcf.platform.api.user.common.BackResult;
import com.hzcf.platform.api.user.property.RockProperty;
import com.hzcf.platform.api.user.service.IOnlineLoanService;
import com.hzcf.platform.common.cache.ICache;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.config.BaseConfig;
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
	IOnlineLoanService onlineLoanService;
	
    /**
     * 修改密码
     * @param user
     * @return
     */
	@RequestMapping(value="rest/api/jinjian/updatepwd",method=RequestMethod.POST)
	public BackResult findpwdForlogin(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user){
		logger.i("进入修改密码登录状态====UserVO:"+user.toString());
		return null;
		//return onlineLoanService.OnlineLoanApply(user, onlineLoanInfo);
	}
	
	/**
	 * 找回密码
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/api/user/findpwd/{type}",method=RequestMethod.POST)
	public BackResult findpwdForlogon(String type){
		logger.i("进入找回密码未登录状态====mobile:"+type);
		return null;
	}
	
	
}
