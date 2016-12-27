package com.hzcf.platform.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hzcf.platform.api.annotation.RequestBodyForm;
import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.service.IUserpwdForService;
import com.hzcf.platform.common.util.log.Log;
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
	@RequestMapping(value="api/100/user/updatepwd/{type}",method=RequestMethod.POST)
	@ResponseBody
	public BackResult updatepwdForlogin(@RequestBodyForm UserVO user,@PathVariable String type){
		logger.i("进入修改密码====UserVO:"+user.toString()+"num:"+type);
		
		return userpwdForService.updatepwdForlogin(user, type);
	}
	                                                                                                       
    /**
     * 找回密码
     * @param user
     * @return
     */                                                                                                                                                                                                                                                                                            
	@RequestMapping(value="api/100/user/findpwd/{type}",method=RequestMethod.POST)
	@ResponseBody
	public BackResult findpwdForlogin(@RequestBodyForm UserVO user,@PathVariable String type){
		logger.i("进入修改密码====UserVO:"+user.toString());
		
		return userpwdForService.findpwdForlogin(user, type);
	}
	
	
}
