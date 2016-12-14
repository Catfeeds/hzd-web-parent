package com.exiao.platform.core.sys.dao;

import java.util.List;
import java.util.Map;

import com.exiao.platform.common.util.rpc.result.PaginatedResult;
import com.exiao.platform.core.sys.data.SysRolePermission;

import com.exiao.platform.framework.core.storage.StorageProvider;

public interface SysRolePermissionDao extends StorageProvider<SysRolePermission> {

	public List<SysRolePermission> getSysRolePermissionByRoleId(Long roleId);
	
	public PaginatedResult<SysRolePermission> flipPage(Map<String, Object> param, int pageSize, int pageNo);
	
	public boolean delete(Long id);
}
