package com.hzcf.platform.mgr.sys.controller;


import java.util.HashMap;
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
import com.hzcf.platform.core.sys.model.RoleVO;
import com.hzcf.platform.core.sys.service.RoleService;

@RestController
public class RoleController<Role> {

	
private Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private RoleService roleService;
	
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = "/role/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getRoleByID(HttpServletRequest request,@PathVariable String id) {
		
		Result<RoleVO> result= roleService.getRoleByID(id);
		
		return ResponseBuilder.instance().body(result).build();

	}
	
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = "/role/getRoleList", method=RequestMethod.GET)
	public ResponseEntity<Object> getList(HttpServletRequest request) {
		
		Result resultList = (Result) roleService.getCollecion();
		
		return ResponseBuilder.instance().body(resultList).build();
		
	}
	
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = "/role/page", method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	public ResponseEntity<Object> getPageList(HttpServletRequest request,@RequestBody SearchParam param) {
		
		
		System.out.println("============," + param.getPageSize()+ ", " + param.getPageNo());
		Map<String ,Object> querymap=new HashMap<String ,Object>();
		if(!StringUtils.isEmpty(param.getRoleName())){
		querymap.put("roleNameLike", param.getRoleName());
		}
		PaginatedResult<RoleVO> result =roleService.flipPage(querymap,param.getPageSize(), param.getPageNo());
		
		logger.info("================end= {}{}{}", param.getPageSize(), param.getPageNo(), result);
		return ResponseBuilder.instance().body(result).build();

	}
	
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value = "/role", method=RequestMethod.POST ,produces={"application/json;charset=UTF-8"})
	public ResponseEntity<Object> add(HttpServletRequest request,@RequestBody RoleVO roleVO) {
		
		roleVO.setUserType(new Integer(0));
		roleVO.setCreateTime(new java.util.Date());
		roleVO.setLogicalDel(new Integer(0));
		roleVO.setDataScope(new Integer(0));
		Subject subject = SecurityUtils.getSubject();
		BaseUser user = (BaseUser) subject.getPrincipal();
		roleVO.setCreateBy(user.getId().intValue());
		Result<Boolean> result = (Result) roleService.create(roleVO);
		
		return ResponseBuilder.instance().body(result).build();
		
	}
	
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value = "/role", method=RequestMethod.PUT)
	public ResponseEntity<Object> modify(HttpServletRequest request,@RequestBody RoleVO roleVO) {
		
		
		roleVO.setUpdateTime(new java.util.Date());
		Result<Boolean> result = (Result) roleService.update(roleVO);
		
		return ResponseBuilder.instance().body(result).build();
		
	}
	
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value = "/role/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable String id) {
		
		System.out.println("delete id is:"+id);
		
		Result<Boolean> result = roleService.deleteById(new Integer(id));
		
		return ResponseBuilder.instance().body(result).build();
	}
	
	public static class SearchParam{
		int pageSize;
		int pageNo;
		String roleName;
		
		public String getRoleName() {
			return roleName;
		}
		public void setRoleName(String roleName) {
			this.roleName = roleName;
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
