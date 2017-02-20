package com.hzcf.platform.api.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hzcf.platform.api.annotation.LogAnnotation;
import com.hzcf.platform.api.config.BaseConfig;
import com.hzcf.platform.api.config.RequestAgent;
import com.hzcf.platform.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.common.cache.ICache;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.common.util.uuid.UUIDGenerator;
import com.hzcf.platform.api.config.ConstantsToken;
import com.hzcf.platform.api.common.DataVerifcation;
import com.hzcf.platform.api.baseEnum.HzdStatusCodeEnum;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.core.user.service.UserService;

@Service
public class UserServiceImpl implements IUserService {
	private static final Log logger = Log.getLogger(UserServiceImpl.class);
	@Autowired
	public UserService userSerivce;
	@Autowired
	private ICache cache;
	@Override

	/**
	 *
	 * @Description: 注册
	 * @User: 雷佳明
	 * @FileName: WipeRecordMgr.java
	 * @param 参数
	 * @return 返回类型
	 * @date 2016年12月7日
	 * @throws
	 */
	@LogAnnotation
	public BackResult register(UserVO user,String type) {
		try {

			DataVerifcation.datavVerification(user.getMobile(),type);
			String registerType = cache.load(ConstantsToken.SMS_CACHE_REG_KEY + user.getMobile());

			if(!type.equals(registerType)){
				logger.i("");
				return new BackResult(HzdStatusCodeEnum.HZD_CODE_3000.getCode(), HzdStatusCodeEnum.HZD_CODE_3000.getMsg());

			}

			Result<UserVO> byMobile = userSerivce.getByMobile(user.getMobile());
			if (StatusCodes.OK != (byMobile.getStatus())) {
				logger.i("查询数据失败 。byMobile   >>>>500。。。。。。。。。。。 ");
				return new BackResult(HzdStatusCodeEnum.HZD_CODE_0001.getCode(),
						HzdStatusCodeEnum.HZD_CODE_0001.getMsg());
			}
			UserVO items = byMobile.getItems();

			if(items!=null){
				logger.i("此用户已经注册 ---手机号:"+user.getMobile());
				return new BackResult(HzdStatusCodeEnum.HZD_CODE_1010.getCode(), HzdStatusCodeEnum.HZD_CODE_1010.getMsg());
			}

			user.setId(UUIDGenerator.getUUID());
			user.setUserName(user.getMobile());
			user.setStatus(BaseConfig.status_0);
			user.setCheckStatus(BaseConfig.card_status_1);
			user.setApplyStatus(BaseConfig.apply_loan_0);
			user.setCreateTime(new Date());
			Result<String> create = userSerivce.insertSelective(user);
			if(StatusCodes.OK==create.getStatus()){
				logger.i("注册成功 ---手机号:"+user.getMobile());
				return new BackResult(HzdStatusCodeEnum.HZD_CODE_0000.getCode(), HzdStatusCodeEnum.HZD_CODE_0000.getMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.e("注册出现异常---手机号:"+user.getMobile());
			return new BackResult(HzdStatusCodeEnum.HZD_CODE_9999.getCode(), HzdStatusCodeEnum.HZD_CODE_9999.getMsg());
		}
		return new BackResult(HzdStatusCodeEnum.HZD_CODE_0001.getCode(), HzdStatusCodeEnum.HZD_CODE_0001.getMsg());

	}

	/**
	 *
	 * @Description: 登录
	 * @User: 雷佳明
	 * @FileName: WipeRecordMgr.java
	 * @param
	 * @return 返回类型
	 * @date 2016年12月7日
	 * @throws
	 */
	@LogAnnotation
	public BackResult logonUser(UserVO user,HttpServletRequest request,RequestAgent agent){

		try {

			Result<UserVO> byMobile = userSerivce.getByMobile(user.getMobile());
			if (StatusCodes.OK != (byMobile.getStatus())) {
				logger.i("查询数据失败 。byMobile   >>>>500。。。。。。。。。。。 ");
				return new BackResult(HzdStatusCodeEnum.HZD_CODE_0001.getCode(),
						HzdStatusCodeEnum.HZD_CODE_0001.getMsg());
			}
			UserVO items = byMobile.getItems();
			Map<String,Object> map = new HashMap<String,Object>();
			if(items!=null){
				if(user.getPassword().equals(items.getPassword())){
					if(BaseConfig.status_1.equals(items.getStatus())){
						logger.i("用户被禁用,禁止登录,手机号:"+items.getMobile());
						return new BackResult(HzdStatusCodeEnum.HZD_CODE_1099.getCode(), HzdStatusCodeEnum.HZD_CODE_1099.getMsg(),null);
					}
					String token =UUIDGenerator.getUUID();
					items.setToken(token);
					items.setIp(agent.getIp());
					items.setTerminal(agent.getTerminal());
					cache.save(ConstantsToken.USER_CACHE_KEY+token, items,ConstantsToken.TOKEN_EXPIRES_HOUR);
					logger.i("用户登录成功.手机号:"+user.getMobile());
					map.put("mobile", items.getMobile());
					map.put("token", token);
					map.put("id",items.getId());
					return new BackResult(HzdStatusCodeEnum.HZD_CODE_0000.getCode(), HzdStatusCodeEnum.HZD_CODE_0000.getMsg(),map);
				}
				logger.i("用户帐号密码错误.手机号:"+user.getMobile());
				return new BackResult(HzdStatusCodeEnum.HZD_CODE_1022.getCode(), HzdStatusCodeEnum.HZD_CODE_1022.getMsg());

			}else{
				logger.i("用户未注册,请先注册.手机号:"+user.getMobile());
				return new BackResult(HzdStatusCodeEnum.HZD_CODE_1011.getCode(), HzdStatusCodeEnum.HZD_CODE_1011.getMsg());
			}
		}  catch (Exception e) {
			e.printStackTrace();
			logger.e("登录出现异常---手机号:"+user.getMobile());
			return new BackResult(HzdStatusCodeEnum.HZD_CODE_9999.getCode(), HzdStatusCodeEnum.HZD_CODE_9999.getMsg());
		}

	}
	@Override
	@LogAnnotation
	public BackResult exitLogon(UserVO user) {
		try {
			cache.delete(ConstantsToken.USER_CACHE_KEY+user.getToken());
			return new BackResult(HzdStatusCodeEnum.HZD_CODE_0000.getCode(), HzdStatusCodeEnum.HZD_CODE_0000.getMsg());
		} catch (Exception e) {
			logger.e("退出登录出现异常");
			return new BackResult(HzdStatusCodeEnum.HZD_CODE_9999.getCode(), HzdStatusCodeEnum.HZD_CODE_9999.getMsg());
		}
	}

	@Override
	@LogAnnotation
	public BackResult isLogon(UserVO user) {
		if(user!=null){

			return new BackResult(HzdStatusCodeEnum.HZD_CODE_0000.getCode(), HzdStatusCodeEnum.HZD_CODE_0000.getMsg());
		}
		return new BackResult(HzdStatusCodeEnum.HZD_CODE_1012.getCode(), HzdStatusCodeEnum.HZD_CODE_1012.getMsg());
	}
}
