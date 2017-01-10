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
	//根据id查询用户信息
	public Result<UserVO> selectByPrimaryKey(String id);
	
	public Result<String> insertSelective(UserVO user);

	public Result<Boolean> updateByPrimaryKeySelective(UserVO user);
	
	public PaginatedResult<UserVO> getUserList(Map<String, Object> parmMap);
	public Long getUserTotal(Map<String, Object> parmMap);
	/**根据用户的真实姓名（name）和身份证号码（id_card），查询“真实姓名”和“身份证号码”重复的数量
	 *	请求数据：
			格式：Map<String,Object>
			参数：name，idCard
		返回数据：
			格式：Map<String,Object>
			参数：realnamerepeat（真实姓名重复的数量），idcardrepeat（身份证号码重复的数量）,allrepeat（真实姓名和身份证号码总共的重复数量）
	 */
	public Map selectNameAndIdCardRepeat(Map<String, Object> parmMap);
}
