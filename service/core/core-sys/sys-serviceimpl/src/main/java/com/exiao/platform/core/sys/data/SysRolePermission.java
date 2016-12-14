package com.exiao.platform.core.sys.data;

import com.exiao.platform.common.model.DataEntity;

public class SysRolePermission extends DataEntity{
	
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
