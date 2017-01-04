package com.hzcf.platform.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hzcf.platform.api.annotation.RequestAttribute;
import com.hzcf.platform.api.annotation.RequestBodyForm;
import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.service.ISmsService;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.api.config.BaseConfig;
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
	@RequestMapping(value="api/100/sms/register",method=RequestMethod.POST)
	@ResponseBody
	public BackResult register(@RequestBodyForm UserVO user){
		logger.i("进入用户注册SmsController====user:"+user.toString());
		return smsService.registerSms(user.getMobile());
	}
	
	/**
	 * 找回密码
	 * @param type
	 * @return
	 */
	@RequestMapping(value="api/100/sms/findpwd",method=RequestMethod.POST)
	@ResponseBody
	public BackResult findpwd(@RequestBodyForm UserVO user){
		logger.i("进入找回密码SmsController====user:"+user.toString());
		return smsService.findPwdSms(user.getMobile());
	}
    
    /**
     * 修改密码
     * @param user
     * @return
     */
	@RequestMapping(value="rest/api/100/sms/updatepwd",method=RequestMethod.POST)
	@ResponseBody
	public BackResult updatepwd(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user){
		logger.i("进入修改密码SmsController====UserVO:"+user.toString());
		return smsService.updatePwdSms(user);
	}
	
	   /**
     * 
     * @param user
     * @return
     */
	@RequestMapping(value="api/100/sms/smsCheck/{type}",method=RequestMethod.POST)
	@ResponseBody
	public BackResult smsCheck(@RequestBodyForm UserVO user,@PathVariable String type){
		logger.i("进入SmsController====UserVO:"+user.toString());
		return smsService.smsCheck(user.getSmsCacheType(),user.getMobile(),type);
	}
	
}
