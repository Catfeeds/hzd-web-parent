
package com.hzcf.platform.mgr.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hzcf.platform.common.util.response.ResponseBuilder;
import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.sys.model.BaseUser;
import com.hzcf.platform.core.sys.model.Element;
import com.hzcf.platform.core.sys.model.MetaElementVO;
import com.hzcf.platform.core.sys.model.PermissionVO;
import com.hzcf.platform.core.sys.model.SysRolePermissionVO;
import com.hzcf.platform.core.sys.model.UserVO;
import com.hzcf.platform.core.sys.service.PermissionService;
import com.hzcf.platform.core.sys.service.SysRolePermissionService;

@RestController
public class SysRolePermissionController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SysRolePermissionService sysRolePermissionService;

	
	@Autowired
	private PermissionService permissionService;

	@RequiresPermissions("sys:authorize:view")
	@RequestMapping(value = "/authorize/page", method = RequestMethod.POST)
	public ResponseEntity<Object> getAuthorizePageList(HttpServletRequest request,@RequestBody SearchParam param) {


		logger.info("============," + param.getPageSize()+ ", " + param.getPageNo());
		Map<String ,Object> querymap=new HashMap<String ,Object>();
		
		if (!StringUtils.isEmpty(param.getRoleId())) {
			querymap.put("roleId", param.getRoleId());
		}
		PaginatedResult<SysRolePermissionVO> result = sysRolePermissionService.flipPage(querymap,param.getPageSize(), param.getPageNo());
		logger.info("================end= {}{}{}", param.getPageSize(), param.getPageNo(), result);
		return ResponseBuilder.instance().body(result).build();
		}
   
	@RequiresPermissions("sys:authorize:view")
	@RequestMapping(value = "/authorize/queryByRoleNotExistpage", method = RequestMethod.POST)
	public ResponseEntity<Object> queryByRoleNotExistpage(HttpServletRequest request,@RequestBody SearchParam param) {


		logger.info("============," + param.getPageSize()+ ", " + param.getPageNo());
		Map<String ,Object> querymap=new HashMap<String ,Object>();
		
		if (!StringUtils.isEmpty(param.getRoleId())) {
			querymap.put("roleId", param.getRoleId());
		}
		PaginatedResult<PermissionVO> result = permissionService.queryForRoleNotExistflipPage(querymap,param.getPageSize(), param.getPageNo());
		logger.info("================end= {}{}{}", param.getPageSize(), param.getPageNo(), result);
		return ResponseBuilder.instance().body(result).build();
		}
	/**
	 * 查询岗位权限
	 * 
	 * @param roleId
	 * @return
	 */
	@RequiresPermissions("sys:authorize:view")
	@RequestMapping(value = "/rolePermIdslist/{roleId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getRolePermissionIdsListByRoleId(@PathVariable Long roleId) {

		System.out.println("query rolePermIdslist  roleId is:" + roleId);
		Result<List<SysRolePermissionVO>> result = sysRolePermissionService.getSysRolePermissionByRoleId(roleId);
		List<SysRolePermissionVO> list = result.getItems();
		List<Long> permids = new ArrayList<Long>();
		permids.clear();
		for (SysRolePermissionVO v : list) {
			permids.add(v.getPermissionId());
		}
		Result<List> resultlist = new Result<List>();

		resultlist.setItems(permids);
		resultlist.setStatus(200);
		System.out.println(" rolePermIdslist  resultlist is:" + resultlist);

		return ResponseBuilder.instance().body(resultlist).build();
	}

	
	/**
	 * 添加权限 ，权限配置
	 * @param ids
	 * @param roleId
	 * @return
	 */
	@RequiresPermissions("sys:authorize:edit")
	@RequestMapping(value = "/authorize/{ids}/{roleId}", method = RequestMethod.POST)
	public ResponseEntity<Object> add(@PathVariable String ids,@PathVariable String roleId) {

		System.out.println("add ids is:" + ids +"-----roleId:"+roleId);

		Result<Boolean> result = sysRolePermissionService.add(ids,roleId);

		return ResponseBuilder.instance().body(result).build();
	}
     
	@RequiresPermissions("sys:authorize:edit")
	@RequestMapping(value = "/authorize/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable String id) {

		System.out.println("delete ids is:" + id);

		Result<Boolean> result = sysRolePermissionService.delete(new Long(id));

		return ResponseBuilder.instance().body(result).build();
	}

	public static class SearchParam {
		int pageSize;
		int pageNo;

		Integer roleId;

		

		public Integer getRoleId() {
			return roleId;
		}

		public void setRoleId(Integer roleId) {
			this.roleId = roleId;
		}

		public int getPageSize() {
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}

		public int getPageNo() {
			return pageNo;
		}

		public void setPageNo(int pageNo) {
			this.pageNo = pageNo;
		}
	}

}
