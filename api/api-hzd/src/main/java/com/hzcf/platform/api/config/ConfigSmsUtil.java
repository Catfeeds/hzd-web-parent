package com.hzcf.platform.api.config;

import org.springframework.beans.factory.annotation.Autowired;

import com.hzcf.platform.api.controller.OfflineApplyLoanController;
import com.hzcf.platform.common.cache.ICache;
import com.hzcf.platform.common.util.log.Log;

public class ConfigSmsUtil {
	private static final Log logger = Log.getLogger(OfflineApplyLoanController.class);
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
