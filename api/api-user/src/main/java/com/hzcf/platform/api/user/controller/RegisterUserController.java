package com.hzcf.platform.api.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hzcf.platform.annotation.RequestBodyForm;
import com.hzcf.platform.api.user.common.BackResult;
import com.hzcf.platform.api.user.service.IUserService;
import com.hzcf.platform.common.cache.ICache;
import com.hzcf.platform.common.cache.redis.IKVCacheService;
import com.hzcf.platform.core.user.model.UserVO;

/**
 * 
 * @description:用户注册
 * @author lei
 * @version 1.0
 * 
 * <pre>
 * Modification History: 
 * Date              Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年12月6日                  lei      1.0       1.0 Version 
 * </pre>
 */
@RestController
public class RegisterUserController {
	
    @Autowired
    private ICache cache;
	@Autowired
	IUserService registerUserService;
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public BackResult register(@RequestBodyForm UserVO user){
		cache.save("useraaa", user);
		System.out.println(user.toString());
		return registerUserService.Register(user);
	}
	
	@RequestMapping(value="/aaaa",method=RequestMethod.POST)
	public BackResult registeaar(@RequestBodyForm UserVO user){
		UserVO usera  = (UserVO)cache.load("useraaa");
		System.out.println(usera.toString());
		return null;
	}
}
