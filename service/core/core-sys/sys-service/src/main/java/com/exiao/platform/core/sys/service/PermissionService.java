package com.exiao.platform.core.sys.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.exiao.platform.common.util.rpc.result.PaginatedResult;
import com.exiao.platform.common.util.rpc.result.Result;
import com.exiao.platform.core.sys.model.PermissionVO;
import com.exiao.platform.core.sys.model.RoleVO;
import com.exiao.platform.framework.core.service.CommonBaseService;

public interface PermissionService extends CommonBaseService<PermissionVO>{
	
    public Result<List<PermissionVO>> getCollecion();
    /***
     * 根据用户查询权限列表
     * @param User_id
     * @return
     */
    /**
	 * 根角色取得权限
	 * @param user_id
	 * @return
	 */
	public Result<List<PermissionVO>> getCollecionByRoleId(Integer role_id);	
	/**
	 * 根据用户取得权限
	 * @param user_id
	 * @return
	 */
	public Result<List<PermissionVO>> getPermissionCollecionByUserId(Integer user_id);

	
	/**
	 * 根据用户取得权限
	 * @param user_id
	 * @return
	 */
	public List<String> getMenuListByUserId(Integer user_id);

	  
	/**
	 * 根据用户取得权限
	 * @param user_id
	 * @return
	 */
	public List<String> getPermissionListByUserId(Integer user_id);

	/**
	 * 查询所有元数据
	 * @param name
	 * @param pageSize  
	 * @param pageNo
	 * @return
	 */

	public PaginatedResult<PermissionVO> queryListByPaginate(Map<String, Object> param,int pageSize,int pageNo);

	
	public  Result<PermissionVO> getPermissionById(Long id);
	
	public PaginatedResult<PermissionVO> flipPage(Map<String, Object> param, int pageSize, int pageNo);

	public PaginatedResult<PermissionVO> queryForRoleNotExistflipPage(Map<String,Object> param, int pageSize,int pageNo);

	
	public Result<Boolean> deleteById(Long id);
}
