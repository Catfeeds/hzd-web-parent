package com.hzcf.platform.core.sys.dao;

import java.util.List;
import java.util.Map;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.core.sys.data.SysRolePermission;

import com.hzcf.platform.framework.core.storage.IBaseDao;

public interface SysRolePermissionDao extends IBaseDao<SysRolePermission> {

	public List<SysRolePermission> getSysRolePermissionByRoleId(Long roleId);
	
	public PaginatedResult<SysRolePermission> flipPage(Map<String, Object> param, int pageSize, int pageNo);
	
	public boolean delete(Long id);
}
