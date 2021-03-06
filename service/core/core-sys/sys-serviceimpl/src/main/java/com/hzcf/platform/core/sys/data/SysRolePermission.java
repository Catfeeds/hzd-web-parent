package com.hzcf.platform.core.sys.data;

import com.hzcf.platform.common.model.AbstractEntity;

public class SysRolePermission extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	private Long roleId;
	
	private Long permissionId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
	

}
