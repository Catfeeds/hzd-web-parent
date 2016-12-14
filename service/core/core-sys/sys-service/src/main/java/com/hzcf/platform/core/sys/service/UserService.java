package com.hzcf.platform.core.sys.service;


import java.util.List;
import java.util.Map;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.sys.model.ChangePwdVO;
import com.hzcf.platform.core.sys.model.UserVO;
import com.hzcf.platform.framework.core.service.CommonBaseService;


public interface UserService extends CommonBaseService<UserVO>{

	public Result<List<UserVO>> getCollecion();
	
	public Result<List<UserVO>> getUserListByIds(Long... ids);

	public Result getUserByID(String id);
	
	public Result<Boolean> updateUserByID(String id,boolean flag);


	public Result getUserByLoginName(String loginName);

	public Result<Boolean> changePsw(ChangePwdVO pswVO);
	/**
	 * 管理员修改密码
	 * @param pswVO
	 * @return
	 */
	public Result<Boolean> changeUserPsw(ChangePwdVO pswVO);


	public Result<Boolean> deleteById(Long id);

	/**
	 * 添加用户
	 */
	public Result<Long> create(UserVO userVo);

	public PaginatedResult<UserVO> flipPage(Map<String, Object> param, int pageSize, int pageNo);

}
