package com.hzcf.platform.api.service.impl;


import com.hzcf.platform.common.util.status.StatusCodes;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.service.ISmsService;
import com.hzcf.platform.common.cache.ICache;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.utils.Serialnumber;
import com.hzcf.platform.api.common.ConstantsToken;
import com.hzcf.platform.api.config.ConstantsDictionary;
import com.hzcf.platform.api.baseEnum.HzdStatusCodeEnum;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.core.user.service.UserService;
import com.hzcf.platform.webService.SmsObtainService;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
	public BackResult registerSms(String mobile) {
		if (StringUtils.isNotBlank(mobile)) {
			try {
				Result<UserVO> byMobile = userSerivce.getByMobile(mobile);
				if (StatusCodes.OK != (byMobile.getStatus())) {
					logger.i("数据查询失败 。byMobile   >>>>500。。。。。。。。。。。 ");
					return new BackResult(HzdStatusCodeEnum.MEF_CODE_0001.getCode(),
							HzdStatusCodeEnum.MEF_CODE_0001.getMsg());
				}
				UserVO items = byMobile.getItems();
				if (items != null ) {
					logger.i("获取短信码失败，用户已注册"+mobile);
					return new BackResult(HzdStatusCodeEnum.MEF_CODE_1010.getCode(), HzdStatusCodeEnum.MEF_CODE_1010.getMsg());
				} else {
					String six = Serialnumber.getSix();
					String dataInfo = SmsObtainService.smsObtain(six, mobile);
					if (StringUtils.isNotBlank(dataInfo) &&"0000".equals(JSONObject.fromObject(dataInfo.toString()).getString("retCode"))) {
						//缓存验证码
						cache.save(ConstantsToken.SMS_CACHE_REG_KEY+mobile, six ,ConstantsToken.SMS_EXPIRES_MIN);
						
						logger.i("-------------获取短信验证码成功" + six + "phoneNum:"+ mobile);
						Map map = new HashMap<>();
						map.put("sms",six);
						return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(), HzdStatusCodeEnum.MEF_CODE_0000.getMsg(), map);
					}else{
						logger.i("调用接口失败"+ mobile);
						return new BackResult(HzdStatusCodeEnum.MEF_CODE_9999.getCode(), HzdStatusCodeEnum.MEF_CODE_9999.getMsg());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.i("数据处理异常"+ e.toString());
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_9999.getCode(), HzdStatusCodeEnum.MEF_CODE_9999.getMsg());

			}
		}else{
			logger.i("-------------手机号码为空");
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_3010.getCode(), HzdStatusCodeEnum.MEF_CODE_3010.getMsg());
		}
	}

	/**
	 * 找回密码
	 * @param mobile
	 * @return
	 */
	@Override
	public BackResult findPwdSms(String mobile) {
		if (StringUtils.isNotBlank(mobile)) {
			try {
				Result<UserVO> byMobile = userSerivce.getByMobile(mobile);
				if (StatusCodes.OK != (byMobile.getStatus())) {
					logger.i("数据查询失败 。byMobile   >>>>500。。。。。。。。。。。 ");
					return new BackResult(HzdStatusCodeEnum.MEF_CODE_0001.getCode(),
							HzdStatusCodeEnum.MEF_CODE_0001.getMsg());
				}
				UserVO items = byMobile.getItems();
				if (items != null) {
					String six = Serialnumber.getSix();
					String dataInfo = SmsObtainService.smsObtain(six, mobile);
					if (StringUtils.isNotBlank(dataInfo) &&"0000".equals(JSONObject.fromObject(dataInfo.toString()).getString("retCode"))) {
						//缓存验证码
						cache.save(ConstantsToken.SMS_CACHE_FINDPWD_KEY+mobile, six ,ConstantsToken.SMS_EXPIRES_MIN);
						Map map = new HashMap<>();
						map.put("sms",six);
						logger.i("-------------获取短信验证码成功" + six + "phoneNum:"+ mobile);
						return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(), HzdStatusCodeEnum.MEF_CODE_0000.getMsg(), map);
					}else{
						logger.i("获取验证码失败"+ mobile);
						return new BackResult(HzdStatusCodeEnum.MEF_CODE_9999.getCode(), HzdStatusCodeEnum.MEF_CODE_9999.getMsg());
					}
				} else {
					logger.i("获取短信码失败，用户未注册"+mobile);
					return new BackResult(HzdStatusCodeEnum.MEF_CODE_1011.getCode(), HzdStatusCodeEnum.MEF_CODE_1011.getMsg());
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.i("数据处理异常"+ e.toString());
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_9999.getCode(), HzdStatusCodeEnum.MEF_CODE_9999.getMsg());
			}
		}else{
			logger.i("-------------手机号码为空");
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_3010.getCode(), HzdStatusCodeEnum.MEF_CODE_3010.getMsg());
		}
	}

	/**
	 * 修改密码
	 */
	@Override
	public BackResult updatePwdSms(UserVO user) {
		if (user != null && StringUtils.isNotBlank(user.getMobile())) {
			try {
				String six = Serialnumber.getSix();
				String dataInfo = SmsObtainService.smsObtain(six, user.getMobile());
				if (StringUtils.isNotBlank(dataInfo) &&"0000".equals(JSONObject.fromObject(dataInfo.toString()).getString("retCode"))) {
					//缓存验证码
					cache.save(ConstantsToken.SMS_CACHE_UPDATEPWD_KEY+user.getMobile(), six ,ConstantsToken.SMS_EXPIRES_MIN);
					
					logger.i("-------------获取短信验证码成功" + six + "phoneNum:"+ user.getMobile());
					 Map map = new HashMap<>();
					map.put("sms",six);
					return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(), HzdStatusCodeEnum.MEF_CODE_0000.getMsg(), map);
				}else{
					logger.i("获取验证码失败"+ user.getMobile());
					return new BackResult(HzdStatusCodeEnum.MEF_CODE_9999.getCode(), HzdStatusCodeEnum.MEF_CODE_9999.getMsg());
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.i("数据处理异常"+ e.toString());
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_9999.getCode(), HzdStatusCodeEnum.MEF_CODE_9999.getMsg());
			}
		}else{
			logger.i("-------------手机号码为空");
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_3010.getCode(), HzdStatusCodeEnum.MEF_CODE_3010.getMsg());
		}
	}
	
	public BackResult smsCheck(String key,String mobile,String sms){
		try {

			if(sms.equals(ConstantsDictionary.SMSNUM) && "TRUE".equals(ConstantsDictionary.SMSNUMSWITCH)){

	    		logger.i("---------用户使用超级验证码:mobile:"+mobile);
	    		System.out.println(cache.getClass());
					cache.save("SMS_CACHE_REG_"+mobile, "666666" ,ConstantsToken.SMS_EXPIRES_MIN);
					cache.save("SMS_CACHE_FINDPWD_"+mobile, "666666" ,ConstantsToken.SMS_EXPIRES_MIN);
					cache.save("SMS_CACHE_UPDATEPWD_"+mobile, "666666" ,ConstantsToken.SMS_EXPIRES_MIN);

				//缓存验证码


				logger.i("-------------获取超级短信验证码成功" + ConstantsDictionary.SMSNUM + "mobile:"+ mobile);
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(), HzdStatusCodeEnum.MEF_CODE_0000.getMsg());
			}
			
			Result<UserVO> byMobile = userSerivce.getByMobile(mobile);
			UserVO items = byMobile.getItems();
			String cacheSmsnum = cache.load(key+mobile);
			if(sms.equals(cacheSmsnum)){
				logger.i("验证短信成功-手机号:"+mobile+"验证码:"+sms);
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(), HzdStatusCodeEnum.MEF_CODE_0000.getMsg(),items);
			}else{
				logger.i("用户注册输入验证码有误--手机号:"+mobile+"验证码:"+sms);
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_3000.getCode(), HzdStatusCodeEnum.MEF_CODE_3000.getMsg());
			}
			
		} catch (Exception e) {
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_9999.getCode(), HzdStatusCodeEnum.MEF_CODE_9999.getMsg());

		}
	}

	
}
