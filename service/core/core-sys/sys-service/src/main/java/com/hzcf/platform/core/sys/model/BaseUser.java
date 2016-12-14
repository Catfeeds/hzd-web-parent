package com.hzcf.platform.core.sys.model;

import java.util.List;

import com.hzcf.platform.common.model.Entity;

/**
 * 用户序列化对象
 * 
 * @author lx828
 *
 */
public class BaseUser extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String loginName;

	private String userName;

	private List<RoleVO> roles;// 角色

	//private List<PermissionVO> menus;// 菜单

	public List<RoleVO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleVO> roles) {
		this.roles = roles;
	}

	/*public List<PermissionVO> getMenus() {
		return menus;
	}

	public void setMenus(List<PermissionVO> menus) {
		this.menus = menus;
	}*/

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isAdmin() {
		return isAdmin(this.getId());
	}

	public static boolean isAdmin(Long id) {
		return id != null && id.equals(1L);
	}

}
