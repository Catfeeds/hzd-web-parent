package com.exiao.platform.core.sys.dao;


import java.util.List;
import java.util.Map;

import com.exiao.platform.common.util.rpc.result.PaginatedResult;
import com.exiao.platform.core.sys.data.Role;
import com.exiao.platform.framework.core.storage.StorageProvider;
public interface RoleDao extends StorageProvider<Role>{
	
    public List<Role> getCollecion();
	
	public Role getRoleByID(String id);
	
	public boolean delete(Integer id);	
	
	public List<Role> getCollecionByUserId(Long userId);
	
	
	public List<Role> getListByName(String roleName);

	public PaginatedResult<Role> flipPage(Map<String, Object> param, int pageSize, int pageNo);
}
