package com.hzcf.platform.core.sys.dao;

import com.hzcf.platform.core.sys.data.SysUserRole;
import com.hzcf.platform.framework.core.storage.StorageProvider;

public interface SysUserRoleDao extends StorageProvider<SysUserRole>{
	
	public  SysUserRole getByUserId(Long userId);

}
