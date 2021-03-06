package com.hzcf.platform.api.service.impl;

import com.hzcf.platform.api.annotation.LogAnnotation;
import com.hzcf.platform.api.config.ConstantsToken;
import com.hzcf.platform.common.util.status.StatusCodes;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.service.IUserpwdForService;
import com.hzcf.platform.common.cache.ICache;
import com.hzcf.platform.common.exception.CheckException;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.api.common.DataVerifcation;
import com.hzcf.platform.api.baseEnum.HzdStatusCodeEnum;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.core.user.service.UserService;
/**
 * 
 * @description:修改密码
 * @author 雷佳明
 * @version 1.0
 * 
 * <pre>
 * Modification History: 
 * Date              Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年12月8日                       雷佳明                           1.0       1.0 Version
 * </pre>
 */
@Component
public class UserpwdForServiceImpl implements IUserpwdForService{
	private static final Log logger = Log.getLogger(UserpwdForServiceImpl.class);
	@Autowired
	public UserService userSerivce;
	@Autowired
    private ICache cache;
	
	@Override
	@LogAnnotation
	public BackResult updatepwdForlogin(UserVO user,String smsnum) {
		if(StringUtils.isNotBlank(user.getMobile())&&StringUtils.isNotBlank(user.getPassword())){
			try {

				String registerType = cache.load(ConstantsToken.SMS_CACHE_UPDATEPWD_KEY + user.getMobile());

				if(!smsnum.equals(registerType)){
					logger.i("修改密码验证码输入有误");
					return new BackResult(HzdStatusCodeEnum.HZD_CODE_3000.getCode(), HzdStatusCodeEnum.HZD_CODE_3000.getMsg());

				}

				if(StringUtils.isBlank( user.getId())){
					return new BackResult(HzdStatusCodeEnum.HZD_CODE_9000.getCode(),"userId为空");
				}	
				DataVerifcation.datavVerification(user.getMobile(), null, null, null, smsnum, user.getPassword(), user.getId());



				Result<Boolean> updateMobile = userSerivce.updateMobile(user);
				if (StatusCodes.OK != (updateMobile.getStatus())) {
					logger.i("修改密码失败 。byMobile   >>>>500。。。。。。。。。。。 ");
					return new BackResult(HzdStatusCodeEnum.HZD_CODE_0001.getCode(),
							HzdStatusCodeEnum.HZD_CODE_0001.getMsg());
				}
					if(updateMobile.getItems()){
						logger.i("修改密码成功-手机号:"+user.getMobile());
						return new BackResult(HzdStatusCodeEnum.HZD_CODE_0000.getCode(), HzdStatusCodeEnum.HZD_CODE_0000.getMsg());

					}else{
						logger.i("修改密码失败-手机号:"+user.getMobile());
						return new BackResult(HzdStatusCodeEnum.HZD_CODE_0001.getCode(), HzdStatusCodeEnum.HZD_CODE_0001.getMsg());
					}
				
			}catch (CheckException e) {
				e.printStackTrace();
				logger.e("修改密码系统异常-手机号:"+user.getMobile());
				return new BackResult(HzdStatusCodeEnum.HZD_CODE_9000.getCode(),e.getMessage());

			} catch (Exception e) {
				e.printStackTrace();
				logger.i("修改密码系统异常-手机号:"+user.getMobile());
			}
		
			
		}else{
			logger.i("修改密码传入参数有误-手机号:"+user.getMobile());
			return new BackResult(HzdStatusCodeEnum.HZD_CODE_9000.getCode(), HzdStatusCodeEnum.HZD_CODE_9000.getMsg());

		}
		return new BackResult(HzdStatusCodeEnum.HZD_CODE_9999.getCode(), HzdStatusCodeEnum.HZD_CODE_9999.getMsg());
	}

	@Override
	@LogAnnotation
	public BackResult findpwdForlogin(UserVO user,String smsnum) {
		if(StringUtils.isBlank(user.getMobile())){
			return new BackResult(HzdStatusCodeEnum.HZD_CODE_9000.getCode(),
					"手机号码输入不能为空",null);
		}

		String registerType = cache.load(ConstantsToken.SMS_CACHE_FINDPWD_KEY + user.getMobile());

		if(!smsnum.equals(registerType)){
			logger.i("找回密码验证码输入有误");
			return new BackResult(HzdStatusCodeEnum.HZD_CODE_3000.getCode(), HzdStatusCodeEnum.HZD_CODE_3000.getMsg());

		}
		if(StringUtils.isNotBlank(user.getMobile())&&StringUtils.isNotBlank(user.getPassword())){
			try {
				Result<UserVO> byMobile = userSerivce.getByMobile(user.getMobile());
				if (StatusCodes.OK != (byMobile.getStatus())) {
					logger.i("查询数据失败 。byMobile   >>>>500。。。。。。。。。。。 ");
					return new BackResult(HzdStatusCodeEnum.HZD_CODE_0001.getCode(),
							HzdStatusCodeEnum.HZD_CODE_0001.getMsg());
				}
				UserVO items = byMobile.getItems();
				if(StringUtils.isBlank( items.getId())){
					return new BackResult(HzdStatusCodeEnum.HZD_CODE_9000.getCode(),"未查询到用户信息");
				}
				user.setId(items.getId());
				DataVerifcation.datavVerification(user.getMobile(), null, null, null, smsnum, user.getPassword(), user.getId());
					Result<Boolean> updateMobile = userSerivce.updateMobile(user);
					if(updateMobile.getItems()){
						logger.i("找回密码成功-手机号:"+user.getMobile());
						return new BackResult(HzdStatusCodeEnum.HZD_CODE_0000.getCode(), HzdStatusCodeEnum.HZD_CODE_0000.getMsg());

					}
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.e("找回密码系统异常-手机号:"+user.getMobile());
			}
		

		}else{
			logger.i("找回密码传入参数有误-手机号:"+user.getMobile());
			return new BackResult(HzdStatusCodeEnum.HZD_CODE_9000.getCode(), HzdStatusCodeEnum.HZD_CODE_9000.getMsg());

		}
		return new BackResult(HzdStatusCodeEnum.HZD_CODE_9999.getCode(), HzdStatusCodeEnum.HZD_CODE_9999.getMsg());
	}
	
}
