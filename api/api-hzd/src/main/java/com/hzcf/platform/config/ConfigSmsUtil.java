package com.hzcf.platform.config;

import org.springframework.beans.factory.annotation.Autowired;

import com.hzcf.platform.api.user.common.BackResult;
import com.hzcf.platform.api.user.controller.OnlineLoanController;
import com.hzcf.platform.common.cache.ICache;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.core.ConstantsToken;
import com.hzcf.platform.core.MyfStatusCodeEnum;

public class ConfigSmsUtil {
	private static final Log logger = Log.getLogger(OnlineLoanController.class);
	@Autowired
    private static ICache cache;
	/**
	 * 超级验证码
	 */
    public static boolean superSmsNum(String mobile,  String smsNum){
    	if(smsNum.equals(ConstantsDictionary.SMSNUM) && "TRUE".equals(ConstantsDictionary.SMSNUMSWITCH)){
    		logger.i("---------用户使用超级验证码:mobile:"+mobile);
			//缓存验证码
			logger.i("-------------获取短信验证码成功" + ConstantsDictionary.SMSNUM + "mobile:"+ mobile);
			return true;
		}
    	return false;
    }
}
