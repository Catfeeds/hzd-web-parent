package com.hzcf.platform.core.sys.dao;

import com.hzcf.platform.core.sys.data.SysUserRole;
import com.hzcf.platform.framework.core.storage.IBaseDao;

public interface SysUserRoleDao extends IBaseDao<SysUserRole> {
	
	public  SysUserRole getByUserId(Long userId);

}
