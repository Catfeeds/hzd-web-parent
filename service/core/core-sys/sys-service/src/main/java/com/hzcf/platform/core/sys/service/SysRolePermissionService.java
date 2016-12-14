package com.hzcf.platform.core.sys.service;

import java.util.List;
import java.util.Map;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.common.util.rpc.result.Result;

import com.hzcf.platform.core.sys.model.SysRolePermissionVO;
import com.hzcf.platform.framework.core.service.IBaseService;

public interface SysRolePermissionService extends IBaseService<SysRolePermissionVO> {

	/**
	 * 根据角色名获取权限列表
	 * 
	 * @param roleId
	 * @return
	 */
	public Result<List<SysRolePermissionVO>> getSysRolePermissionByRoleId(Long roleId);
	
	/***
	 * 
	 * @param param
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public PaginatedResult<SysRolePermissionVO> flipPage(Map<String, Object> param, int pageSize, int pageNo);

	
	
	
	public Result<Boolean>  add(String ids,String roleId);
	/**
	 * 单条删除
	 * @param id
	 * @return
	 */
	public Result<Boolean> delete(Long id) ;
	
	
}
