package com.hzcf.platform.core.sys.dao;

import java.util.List;
import java.util.Map;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.core.sys.data.Permission;
import com.hzcf.platform.framework.core.storage.IBaseDao;

public interface PermissionDao extends IBaseDao<Permission> {
	
	public List<Permission> getCollecion();
	
	/**
	 * 根角色取得权限
	 * @param user_id
	 * @return
	 */
	public List<Permission> getCollecion(Integer role_id);	
	/**
	 * 根据用户取得权限
	 * @param user_id
	 * @return
	 */
	public List<Permission> getPermissionCollecion(Integer user_id);
	
	
	public List<String> getMenuByUserId(Integer user_id);
	
	public List<String> getPermissionByUserId(Integer user_id);
	
	public List<Permission> getPermissionListByUserCode(String userCode);
	
	public Permission getPermissionByID(String id);
	
	 /**
     * 分页查找描述
     * 
     * @param pageSize
     * @param pageNo
     * @return
     */
	public PaginatedResult<Permission> flipPage(Map<String,Object> param, int pageSize,int pageNo);

	/**
     * 分页查找描述
     * 
     * @param pageSize
     * @param pageNo
     * @return
     */
	public PaginatedResult<Permission> queryForRoleNotExistflipPage(Map<String,Object> param, int pageSize,int pageNo);

}
