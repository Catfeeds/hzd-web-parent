package com.hzcf.platform.core.sys.service;


import java.util.List;
import java.util.Map;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.sys.model.RoleVO;
import com.hzcf.platform.framework.core.service.IBaseService;

public interface RoleService extends IBaseService<RoleVO> {

	
    public Result<List<RoleVO>> getCollecion();
	
	public Result<RoleVO>  getRoleByID(String id);
	
	public Result<List<RoleVO>> getCollecionByUserId(Long userId);

	/**  
	 * 添加角色
	 */
	public Result create(RoleVO roleVo);
	

	public Result<Boolean> deleteById(Integer id);
	
	public PaginatedResult<RoleVO> flipPage(Map<String, Object> param, int pageSize, int pageNo);
	
}
