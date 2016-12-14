package com.hzcf.platform.mgr.sys.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hzcf.platform.mgr.sys.security.util.Md5HashUtil;
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
import com.hzcf.platform.core.sys.model.ChangePwdVO;
import com.hzcf.platform.core.sys.model.UserVO;
import com.hzcf.platform.core.sys.service.UserService;


@RestController
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "/user/del/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(HttpServletRequest request,@PathVariable String id) {

		Result<Boolean> result = userService.deleteById(Long.parseLong(id));

		return ResponseBuilder.instance().body(result).build();
	}
	
	
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "/user/active/{id}", method = RequestMethod.POST)
	public ResponseEntity<Object> active(HttpServletRequest request,@PathVariable String id) {

		Result<Boolean> result = userService.updateUserByID(id,false);

		return ResponseBuilder.instance().body(result).build();
	}
	
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "/user/forbid/{id}", method = RequestMethod.POST)
	public ResponseEntity<Object> forbid(HttpServletRequest request,@PathVariable String id) {

		Result<Boolean> result = userService.updateUserByID(id,true);

		return ResponseBuilder.instance().body(result).build();
	}
	
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getUserByID(HttpServletRequest request,@PathVariable String id) {

		Result<UserVO> result = userService.getUserByID(id);
	

		return ResponseBuilder.instance().body(result).build();
		}

	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<Object> getAll(HttpServletRequest request) {

		Result<List<UserVO>> resultList = userService.getCollecion();

		return ResponseBuilder.instance().body(resultList).build();

	}

	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "/user/page", method = RequestMethod.POST)
	public ResponseEntity<Object> getPageList(HttpServletRequest request,@RequestBody SearchParam param) {


		logger.info("============," + param.getPageSize()+ ", " + param.getPageNo()+","+param.getUserName());
		Map<String ,Object> querymap=new HashMap<String ,Object>();
		if(!StringUtils.isEmpty(param.getUserName())){
		querymap.put("userNameLike", param.getUserName());
		}
		if (!StringUtils.isEmpty(param.getDepartmentId())) {
			querymap.put("departmentId", param.getDepartmentId());
		}
		if (!StringUtils.isEmpty(param.getRoleID())) {
			querymap.put("roleID", param.getRoleID());
		}
		if (!StringUtils.isEmpty(param.getActivestatus())) {
			querymap.put("locked", param.getActivestatus());
		} 
		PaginatedResult<UserVO> result = userService.flipPage(querymap,param.getPageSize(), param.getPageNo());
		logger.info("================end= {}{}{}", param.getPageSize(), param.getPageNo(), result);
		
	
		return ResponseBuilder.instance().body(result).build();
		}

	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<Object> add(HttpServletRequest request,@RequestBody UserVO userVO) {

		userVO.setCreateTime(new java.util.Date());
		userVO.setLogicalDel(new Integer(0));
		String salt= Md5HashUtil.getSalt();
        String entrypt_password=Md5HashUtil.entryptPasswordByMd5(userVO.getPassword(), salt);
        userVO.setPassword(entrypt_password);
        userVO.setSalt(salt); 
        Subject subject = SecurityUtils.getSubject();
		BaseUser user = (BaseUser) subject.getPrincipal();
		userVO.setCreateBy(user.getId().intValue());
		Result<Long> result  = (Result) userService.create(userVO);
 
		return ResponseBuilder.instance().body(result).build();

	}

	
	@RequestMapping(value = "/user/changePsw", method = RequestMethod.POST)
	public ResponseEntity<Object> changePsw(HttpServletRequest request,@RequestBody ChangePwdVO pswVO) {
	
		Result<Long> result  = (Result) userService.changePsw(pswVO);
 
		return ResponseBuilder.instance().body(result).build();

	} 
	
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "/user/changeUserPsw", method = RequestMethod.POST)
	public ResponseEntity<Object> changeUserPsw(HttpServletRequest request,@RequestBody ChangePwdVO pswVO) {
	
		Result<Long> result  = (Result) userService.changeUserPsw(pswVO);
 
		return ResponseBuilder.instance().body(result).build();

	} 
	
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public ResponseEntity<Object> modify(HttpServletRequest request,@RequestBody UserVO userVO) {
		
		/*String salt=Md5HashUtil.getSalt();
		userVO.setSalt(salt);
		String entrypt_password=Md5HashUtil.entryptPasswordByMd5(userVO.getPassword(), userVO.getSalt());
	    userVO.setPassword(entrypt_password);
	    */   
		userVO.setUpdateTime(new java.util.Date());
		Result<Boolean> result = (Result) userService.update(userVO);
		 
		return ResponseBuilder.instance().body(result).build();

	}
	
	

	
	public static class SearchParam{
		int pageSize;
		int pageNo;
		String userName;
		String loginName;
	    Integer departmentId;
		Integer roleID;
		Byte  activestatus;
		
		
		
		public Byte getActivestatus() {
			return activestatus;
		}
		public void setActivestatus(Byte activestatus) {
			this.activestatus = activestatus;
		}
		public Integer getDepartmentId() {
			return departmentId;
		}
		public void setDepartmentId(Integer departmentId) {
			this.departmentId = departmentId;
		}
		public Integer getRoleID() {
			return roleID;
		}
		public void setRoleID(Integer roleID) {
			this.roleID = roleID;
		}
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
	
	