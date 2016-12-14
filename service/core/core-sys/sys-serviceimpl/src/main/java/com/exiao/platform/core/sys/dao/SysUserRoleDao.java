package com.exiao.platform.core.sys.dao;

import com.exiao.platform.core.sys.data.SysUserRole;
import com.exiao.platform.framework.core.storage.StorageProvider;

public interface SysUserRoleDao extends StorageProvider<SysUserRole>{
	
	public  SysUserRole getByUserId(Long userId);

}
