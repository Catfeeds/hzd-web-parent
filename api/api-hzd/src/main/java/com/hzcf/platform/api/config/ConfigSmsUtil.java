package com.hzcf.platform.api.config;

import com.hzcf.platform.api.common.ConstantsToken;
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
			cache.save(ConstantsToken.SMS_CACHE_REG_KEY+mobile, smsNum ,ConstantsToken.SMS_EXPIRES_MIN);
			cache.save(ConstantsToken.SMS_CACHE_FINDPWD_KEY+mobile, smsNum ,ConstantsToken.SMS_EXPIRES_MIN);
			cache.save(ConstantsToken.SMS_CACHE_UPDATEPWD_KEY+mobile, smsNum ,ConstantsToken.SMS_EXPIRES_MIN);

			logger.i("-------------获取短信验证码成功" + ConstantsDictionary.SMSNUM + "mobile:"+ mobile);
			return true;
		}
    	return false;
    }
}
