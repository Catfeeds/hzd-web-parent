package com.hzcf.platform.api.user.service.impl;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.api.user.common.BackResult;
import com.hzcf.platform.api.user.service.ISmsService;
import com.hzcf.platform.common.cache.ICache;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.utils.Serialnumber;
import com.hzcf.platform.core.ConstantsToken;
import com.hzcf.platform.core.MyfStatusCodeEnum;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.core.user.service.UserService;
import com.hzcf.platform.webService.SmsObtainService;

import net.sf.json.JSONObject;

@Service
public class SmsServiceImpl implements ISmsService {
	private static final Log logger = Log.getLogger(SmsServiceImpl.class);
	@Autowired
	public UserService userSerivce;
	@Autowired
    private ICache cache;
	
	/**
	 * 用户注册 短信验证码
	 */
	@Override
	public BackResult registerSms(UserVO user) {
		if (user != null && StringUtils.isNotBlank(user.getMobile())) {
			try {
				Result<UserVO> byMobile = userSerivce.getByMobile(user.getMobile());
				UserVO items = byMobile.getItems();
				if (items != null) {
					logger.i("获取短信码失败，用户已注册"+user.getMobile());
					return new BackResult(MyfStatusCodeEnum.MEF_CODE_3021.getCode(),MyfStatusCodeEnum.MEF_CODE_3021.getMsg());
				} else {
					String six = Serialnumber.getSix();
					String dataInfo = SmsObtainService.smsObtain(six, user.getMobile());
					if (StringUtils.isNotBlank(dataInfo) &&"0000".equals(JSONObject.fromObject(dataInfo.toString()).getString("retCode"))) {
						//缓存验证码
						cache.save(ConstantsToken.SMS_CACHE_REG_KEY+user.getMobile(), six ,ConstantsToken.SMS_EXPIRES_MIN);
						
						logger.i("-------------获取短信验证码成功" + six + "phoneNum:"+ user.getMobile());
						return new BackResult(MyfStatusCodeEnum.MEF_CODE_0000.getCode(),MyfStatusCodeEnum.MEF_CODE_0000.getMsg(), six);
					}else{
						logger.i("获取验证码失败"+ user.getMobile());
						return new BackResult(MyfStatusCodeEnum.MEF_CODE_9999.getCode(),MyfStatusCodeEnum.MEF_CODE_9999.getMsg());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.i("数据处理异常"+ e.toString());
				return new BackResult(MyfStatusCodeEnum.MEF_CODE_9999.getCode(),MyfStatusCodeEnum.MEF_CODE_9999.getMsg());

			}
		}else{
			logger.i("-------------手机号码为空");
			return new BackResult(MyfStatusCodeEnum.MEF_CODE_3010.getCode(),MyfStatusCodeEnum.MEF_CODE_3010.getMsg());
		}
	}

	@Override
	public BackResult findPwdSms(UserVO user) {
		if (user != null && StringUtils.isNotBlank(user.getMobile())) {
			try {
				Result<UserVO> byMobile = userSerivce.getByMobile(user.getMobile());
				UserVO items = byMobile.getItems();
				if (items != null) {
					logger.i("获取短信码失败，用户已注册"+user.getMobile());
					return new BackResult(MyfStatusCodeEnum.MEF_CODE_3021.getCode(),MyfStatusCodeEnum.MEF_CODE_3021.getMsg());
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.i("数据处理异常"+ e.toString());
				return new BackResult(MyfStatusCodeEnum.MEF_CODE_9999.getCode(),MyfStatusCodeEnum.MEF_CODE_9999.getMsg());
			}
		}
		return null;
	}

	@Override
	public BackResult updatePwdSms(UserVO user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
