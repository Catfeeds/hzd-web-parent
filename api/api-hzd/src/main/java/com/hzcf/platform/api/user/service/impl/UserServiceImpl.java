package com.hzcf.platform.api.user.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.api.user.common.BackResult;
import com.hzcf.platform.api.user.controller.UserController;
import com.hzcf.platform.api.user.service.IUserService;
import com.hzcf.platform.common.cache.ICache;
import com.hzcf.platform.common.exception.CheckException;
import com.hzcf.platform.common.util.json.parser.JsonUtil;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.common.util.uuid.UUIDGenerator;
import com.hzcf.platform.core.ConstantsToken;
import com.hzcf.platform.core.DataVerifcation;
import com.hzcf.platform.core.MyfStatusCodeEnum;
import com.hzcf.platform.core.user.model.RequestAgent;
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
	public BackResult register(UserVO user,String num) {
		try {
			DataVerifcation.datavVerification(user.getMobile(),num);
			Result<UserVO> byMobile = userSerivce.getByMobile(user.getMobile());
			UserVO items = byMobile.getItems();
			
			if(items!=null){
				logger.i("此用户已经注册 ---手机号:"+user.getMobile());
				return new BackResult(MyfStatusCodeEnum.MEF_CODE_1010.getCode(),MyfStatusCodeEnum.MEF_CODE_1010.getMsg());
			}
			
			user.setId(UUIDGenerator.getUUID());
			user.setCreateTime(new Date());
			Result<String> create = userSerivce.create(user);
			if(StatusCodes.OK==create.getStatus()){
				logger.i("注册成功 ---手机号:"+user.getMobile());
				return new BackResult(MyfStatusCodeEnum.MEF_CODE_0000.getCode(),MyfStatusCodeEnum.MEF_CODE_0000.getMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.i("注册出现异常---手机号:"+user.getMobile());
		}
	
		 return new BackResult(MyfStatusCodeEnum.MEF_CODE_9999.getCode(),MyfStatusCodeEnum.MEF_CODE_9999.getMsg());
	}
	
	/**
	 * 
		 * @Description: 登录
		 * @User: 雷佳明
		 * @FileName: WipeRecordMgr.java
		 * @param 参数  
		 * @return 返回类型 
		 * @date 2016年12月7日
		 * @throws
	 */
	public BackResult logonUser(UserVO user,HttpServletRequest request,RequestAgent agent){
		
		try {
			
			Result<UserVO> byMobile = userSerivce.getByMobile(user.getMobile());
			UserVO items = byMobile.getItems();
			Map<String,Object> map = new HashMap<String,Object>();
			if(items!=null){
				if(user.getPassword().equals(items.getPassword())){
					String token =UUIDGenerator.getUUID();
					items.setToken(token);
					items.setIp(agent.getIp());
					items.setTerminal(agent.getTerminal());
					cache.save(ConstantsToken.USER_CACHE_KEY+token, items,ConstantsToken.TOKEN_EXPIRES_HOUR);
					logger.i("用户登录成功.手机号:"+user.getMobile());
					map.put("mobile", user.getMobile());
					map.put("token", token);
					return new BackResult(MyfStatusCodeEnum.MEF_CODE_0000.getCode(),MyfStatusCodeEnum.MEF_CODE_0000.getMsg(),map);
				}
				logger.i("用户帐号密码错误.手机号:"+user.getMobile());
				return new BackResult(MyfStatusCodeEnum.MEF_CODE_1022.getCode(),MyfStatusCodeEnum.MEF_CODE_1022.getMsg());

			}else{
				logger.i("用户未注册,请先注册.手机号:)"+user.getMobile());
				return new BackResult(MyfStatusCodeEnum.MEF_CODE_1011.getCode(),MyfStatusCodeEnum.MEF_CODE_1011.getMsg());
			}
		}  catch (Exception e) {
			e.printStackTrace();
			logger.i("登录出现异常---手机号:"+user.getMobile());
			return new BackResult(MyfStatusCodeEnum.MEF_CODE_9999.getCode(),MyfStatusCodeEnum.MEF_CODE_9999.getMsg());
		}

	}
	@Override
	public BackResult exitLogon(UserVO user) {
		try {
			cache.delete(ConstantsToken.USER_CACHE_KEY+user.getToken());
			return new BackResult(MyfStatusCodeEnum.MEF_CODE_0000.getCode(),MyfStatusCodeEnum.MEF_CODE_0000.getMsg());
		} catch (Exception e) {
			logger.i("退出登录出现异常");
			return new BackResult(MyfStatusCodeEnum.MEF_CODE_9999.getCode(),MyfStatusCodeEnum.MEF_CODE_9999.getMsg());
		}
	}
	
	@Override
	public BackResult isLogon(UserVO user) {
			if(user!=null){
				
				return new BackResult(MyfStatusCodeEnum.MEF_CODE_0000.getCode(),MyfStatusCodeEnum.MEF_CODE_0000.getMsg());
			}
			return new BackResult(MyfStatusCodeEnum.MEF_CODE_1012.getCode(),MyfStatusCodeEnum.MEF_CODE_1012.getMsg()); 
	}
}
