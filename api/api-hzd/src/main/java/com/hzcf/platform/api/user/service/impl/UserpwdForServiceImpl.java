package com.hzcf.platform.api.user.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.hzcf.platform.api.user.common.BackResult;
import com.hzcf.platform.api.user.service.IUserpwdForService;
import com.hzcf.platform.common.cache.ICache;
import com.hzcf.platform.common.exception.CheckException;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.ConstantsToken;
import com.hzcf.platform.core.DataVerifcation;
import com.hzcf.platform.core.MyfStatusCodeEnum;
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
	public BackResult updatepwdForlogin(UserVO user,String smsnum) {
		if(StringUtils.isNotBlank(user.getMobile())&&StringUtils.isNotBlank(user.getPassword())){
			try {
				if(StringUtils.isBlank( user.getId())){
					return new BackResult(MyfStatusCodeEnum.MEF_CODE_9000.getCode(),"userId为空");
				}	
				DataVerifcation.datavVerification(user.getMobile(), null, null, null, smsnum, user.getPassword(), user.getId());
				Result<Boolean> updateMobile = userSerivce.updateMobile(user);
					if(updateMobile.getItems()){
						logger.i("修改密码成功-手机号:"+user.getMobile());
						return new BackResult(MyfStatusCodeEnum.MEF_CODE_0000.getCode(),MyfStatusCodeEnum.MEF_CODE_0000.getMsg());

					}else{
						logger.i("修改密码失败-手机号:"+user.getMobile());
						return new BackResult(MyfStatusCodeEnum.MEF_CODE_0001.getCode(),MyfStatusCodeEnum.MEF_CODE_0001.getMsg());
					}
				
			}catch (CheckException e) {
				e.printStackTrace();
				logger.i("修改密码系统异常-手机号:"+user.getMobile());
				return new BackResult(MyfStatusCodeEnum.MEF_CODE_9000.getCode(),e.getMessage());

			} catch (Exception e) {
				e.printStackTrace();
				logger.i("修改密码系统异常-手机号:"+user.getMobile());
			}
		
			
		}else{
			logger.i("修改密码传入参数有误-手机号:"+user.getMobile());
			return new BackResult(MyfStatusCodeEnum.MEF_CODE_9000.getCode(),MyfStatusCodeEnum.MEF_CODE_9000.getMsg());

		}
		return new BackResult(MyfStatusCodeEnum.MEF_CODE_9999.getCode(),MyfStatusCodeEnum.MEF_CODE_9999.getMsg());
	}

	@Override
	public BackResult findpwdForlogin(UserVO user,String smsnum) {
		if(StringUtils.isNotBlank(user.getMobile())&&StringUtils.isNotBlank(user.getPassword())){
			try {
				if(StringUtils.isBlank( user.getId())){
					return new BackResult(MyfStatusCodeEnum.MEF_CODE_9000.getCode(),"userId为空");
				}
				DataVerifcation.datavVerification(user.getMobile(), null, null, null, smsnum, user.getPassword(), user.getId());
					Result<Boolean> updateMobile = userSerivce.updateMobile(user);
					if(updateMobile.getItems()){
						logger.i("找回密码成功-手机号:"+user.getMobile());
						return new BackResult(MyfStatusCodeEnum.MEF_CODE_0000.getCode(),MyfStatusCodeEnum.MEF_CODE_0000.getMsg());

					}
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.i("找回密码系统异常-手机号:"+user.getMobile());
			}
		
			
		}else{
			logger.i("找回密码传入参数有误-手机号:"+user.getMobile());
			return new BackResult(MyfStatusCodeEnum.MEF_CODE_9000.getCode(),MyfStatusCodeEnum.MEF_CODE_9000.getMsg());

		}
		return new BackResult(MyfStatusCodeEnum.MEF_CODE_9999.getCode(),MyfStatusCodeEnum.MEF_CODE_9999.getMsg());
	}
	
}
