package com.hzcf.platform.api.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hzcf.platform.annotation.RequestAttribute;
import com.hzcf.platform.api.user.common.BackResult;
import com.hzcf.platform.api.user.service.ISmsService;
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
public class SmsController {
	private static final Log logger = Log.getLogger(SmsController.class);
	
    @Autowired
    ISmsService smsService;
    
    /**
	 * 用户注册
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/api/sms/register/{mobile}",method=RequestMethod.POST)
	public BackResult register(@PathVariable String mobile){
		logger.i("进入用户注册SmsController====mobile:"+mobile);
		return smsService.registerSms(mobile);
	}
	
	/**
	 * 找回密码
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/api/sms/findpwd/{mobile}",method=RequestMethod.POST)
	public BackResult findpwd(@PathVariable String mobile){
		logger.i("进入找回密码SmsController====mobile:"+mobile);
		return smsService.findPwdSms(mobile);
	}
    
    /**
     * 修改密码
     * @param user
     * @return
     */
	@RequestMapping(value="rest/api/sms/updatepwd",method=RequestMethod.POST)
	public BackResult updatepwd(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user){
		logger.i("进入修改密码SmsController====UserVO:"+user.toString());
		return smsService.updatePwdSms(user);
	}
	

    private void allPass(){
    	
    }
	
	
}
