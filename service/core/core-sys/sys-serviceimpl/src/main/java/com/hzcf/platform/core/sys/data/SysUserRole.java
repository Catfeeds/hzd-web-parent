package com.hzcf.platform.core.sys.data;

import com.hzcf.platform.common.model.AbstractEntity;

public class SysUserRole extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;

    private Long userId;
    
    private Long roleId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
    
	
}
