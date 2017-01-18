package com.hzcf.platform.core.sys.service;

import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.sys.model.SysUsersVO;
import com.hzcf.platform.framework.core.service.IBaseService;


/**
 * @description:用于获取后台登陆的用户名对应密码
 * @author 李强
 * @version 1.0
 * 
 * <pre>
 * Modification History: 
 * Date              Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年1月3日                            李强                          	 1.0      	 1.0 Version 
 * </pre>
 */
public interface SysUsersService extends IBaseService<SysUsersVO>{
	
	public Result<SysUsersVO> getBySysUsersName(String userName);
	/**
	 * @Title: updateByPrimaryKeySelective 
	 * @Description:根据主键修改后台用户信息 
	 * @time: 2017年1月17日 下午7:24:21  
	 * @return:Result<SysUsersVO>
	 */
	public Result<Boolean> updateByPrimaryKeySelective(SysUsersVO sysUsersVO);
	/**
	 * @Title: updateByUserNameSelective 
	 * @Description: TODO 
	 * @time: 2017年1月17日 下午9:41:18  
	 * @return:Result<Integer>
	 */
	public Result<Integer> updateByUserNameSelective(SysUsersVO sysUsersVO);
	
	
	
}
