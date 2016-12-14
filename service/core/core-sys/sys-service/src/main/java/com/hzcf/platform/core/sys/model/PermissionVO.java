package com.hzcf.platform.core.sys.model;

import java.util.List;

import com.hzcf.platform.common.model.BaseVO;
import com.google.common.collect.Lists;

public class PermissionVO extends BaseVO {

	private static final long serialVersionUID = 1L;
	
	private Integer parentId;
	private PermissionVO parent; // 父级菜单
	private String parentIds; // 所有父级编号
	private String name; // 名称
	private String href_url; // 链接
	private String target; // 目标（ mainFrame、_blank、_self、_parent、_top）
	private String icon; // 图标
	private Integer sort; // 排序
	private String isShow; // 是否在菜单中显示（1：显示；0：不显示）
	private String permission; // 权限标识
	private Integer logicalDel; // 删除标识
	private String remarks; // 备注
	private Integer createBy;
	private Integer updateBy;

	private List<PermissionVO> childList = Lists.newArrayList();// 拥有子菜单列表
	private List<RoleVO> roleList = Lists.newArrayList(); // 拥有角色列表

	public PermissionVO getParent() {
		return parent;
	}

	public void setParent(PermissionVO parent) {
		this.parent = parent;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHref_url() {
		return href_url;
	}

	public void setHref_url(String href_url) {
		this.href_url = href_url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Integer getLogicalDel() {
		return logicalDel;
	}

	public void setLogicalDel(Integer logicalDel) {
		this.logicalDel = logicalDel;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public List<PermissionVO> getChildList() {
		return childList;
	}

	public void setChildList(List<PermissionVO> childList) {
		this.childList = childList;
	}

	public List<RoleVO> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleVO> roleList) {
		this.roleList = roleList;
	}

}
