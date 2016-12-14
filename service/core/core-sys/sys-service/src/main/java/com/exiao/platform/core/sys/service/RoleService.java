package com.exiao.platform.core.sys.service;


import java.util.List;
import java.util.Map;

import com.exiao.platform.common.util.rpc.result.PaginatedResult;
import com.exiao.platform.common.util.rpc.result.Result;
import com.exiao.platform.core.sys.model.RoleVO;

import com.exiao.platform.framework.core.service.CommonBaseService;

public interface RoleService extends CommonBaseService<RoleVO> {

	
    public Result<List<RoleVO>> getCollecion();
	
	public Result<RoleVO>  getRoleByID(String id);
	
	public Result<List<RoleVO>> getCollecionByUserId(Long userId);

	/**  
	 * 添加角色
	 */
	public Result<Long> create(RoleVO roleVo);
	

	public Result<Boolean> deleteById(Integer id);
	
	public PaginatedResult<RoleVO> flipPage(Map<String, Object> param, int pageSize, int pageNo);
	
}
