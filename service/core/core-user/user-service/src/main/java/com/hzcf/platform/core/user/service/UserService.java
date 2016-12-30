package com.hzcf.platform.core.user.service;

import java.util.Map;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.framework.core.service.IBaseService;


/**
 * 
 * @description:user功能操作
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
public interface UserService extends IBaseService<UserVO>{
	/**
	 * 按手机号更新
	 * 
	 * @param m
	 * @return
	 */
	public Result<Boolean> updateMobile(UserVO user);
	
	/**
	 * 按手机号取得
	 * 
	 * @param mobile
	 * @return
	 */
	public Result<UserVO> getByMobile(String mobile);

	public Result<String> insertSelective(UserVO user);

	public Result<Boolean> updateByPrimaryKeySelective(UserVO user);
	
	public PaginatedResult<UserVO> getUserList(Map<String, Object> parmMap);
	public Long getUserTotal(Map<String, Object> parmMap);
}
