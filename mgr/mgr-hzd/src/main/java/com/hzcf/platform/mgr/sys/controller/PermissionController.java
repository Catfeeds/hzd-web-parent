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
import com.hzcf.platform.core.sys.model.PermissionVO;
import com.hzcf.platform.core.sys.service.PermissionService;


@RestController
public class PermissionController {

	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PermissionService  permissionService;
	
	@RequiresPermissions("sys:permission:view")
	@RequestMapping(value = "/permission/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getPermissionByID(HttpServletRequest request,@PathVariable Long id) {
		
		Result<PermissionVO> result= permissionService.getPermissionById(id);

		return ResponseBuilder.instance().body(result).build();
	}
	
	@RequiresPermissions("sys:permission:view")
	@RequestMapping(value = "/permission/list/page", method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	public ResponseEntity<Object> getPageList(HttpServletRequest request,@RequestBody SearchParam param) {
		
		System.out.println("========permission====," + param.getPageSize()+ ", " + param.getPageNo());
		Map<String ,Object> querymap=new HashMap<String ,Object>();
		if (!StringUtils.isEmpty(param.getName())) {
			querymap.put("nameLike", param.getName());
		}
		PaginatedResult<PermissionVO> result =permissionService.flipPage(querymap,param.getPageSize(), param.getPageNo());
		
		logger.info("================end= {}{}{}", param.getPageSize(), param.getPageNo(), result);
		return ResponseBuilder.instance().body(result).build();

	}
	
	@RequiresPermissions("sys:permission:edit")
	@RequestMapping(value = "/permission", method=RequestMethod.POST ,produces={"application/json;charset=UTF-8"})
	public ResponseEntity<Object> add(HttpServletRequest request,@RequestBody PermissionVO permissionVO) {
		
		permissionVO.setCreateTime(new java.util.Date());
		permissionVO.setLogicalDel(new Integer(0));
		Subject subject = SecurityUtils.getSubject();
		BaseUser user = (BaseUser) subject.getPrincipal();
		permissionVO.setCreateBy(user.getId().intValue());
		Result<Boolean> result = (Result) permissionService.create(permissionVO);
		
		return ResponseBuilder.instance().body(result).build();
		
	}
	
	@RequiresPermissions("sys:permission:edit")
	@RequestMapping(value = "/permission", method=RequestMethod.PUT)
	public ResponseEntity<Object> modify(HttpServletRequest request,@RequestBody PermissionVO permissionVO) {
		
		
		permissionVO.setUpdateTime(new java.util.Date());
		Subject subject = SecurityUtils.getSubject();
		BaseUser user = (BaseUser) subject.getPrincipal();
		permissionVO.setUpdateBy(user.getId().intValue());
	
		Result<Boolean> result = (Result) permissionService.update(permissionVO);
		
		return ResponseBuilder.instance().body(result).build();
		
	}
	
    @RequiresPermissions("sys:permission:del")
	@RequestMapping(value = "/permission/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable String id) {
		
		System.out.println("delete id is:"+id);
		
		Result<Boolean> result = permissionService.deleteById(new Long(id));
		
		return ResponseBuilder.instance().body(result).build();
	}
	
	
	public static class SearchParam{
		int pageSize;
		int pageNo;
		
		String name;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
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