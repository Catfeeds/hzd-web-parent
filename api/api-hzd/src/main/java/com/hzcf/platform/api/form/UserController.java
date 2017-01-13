package com.hzcf.platform.api.form;
/*package com.hzcf.platform.api.user.controller;

import FormBeanHelper;
import UserResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.core.user.service.UserService;

@RestController
public class UserController {
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/users",method=RequestMethod.GET)
	public ResponseEntity<Object> getPageList(
			@RequestParam(defaultValue="10") Integer pageSize,
			@RequestParam(defaultValue="1") Integer pageNo
			){
		//SupplierBasicVO supplierUser = (SupplierBasicVO)WebRuntimeContextHolder.getRuntimeContext().getUser();
		if(null == supplierUser){
			Result<String> result = new Result<String>(StatusCodes.UNAUTHORIZED,null);
			result.setMsg("supplierBasicVO is null,do you login?");
			return ResponseBuilder.instance().body(result).build();
		}
		//获得cookie中的supplier信息
		//String supplierId = supplierUser.getSupplierId();

		PaginatedResult<UserVO> result = userService.findList(pageSize,pageNo);
		return UserResponseBuilder.instance().body(
				FormBeanHelper.convertVOPagingResult2FormResult(UserForm.class, result)).build();
	}
	
	@RequestMapping(value="/users/{id}",method=RequestMethod.GET)
	public ResponseEntity<Object>  getPurchaseOrder(@PathVariable Long id){
		Result<UserVO> result = userService.getByPK(id);
		Result<UserForm> formResult =
				FormBeanHelper.convertVOResult2FormResult(UserForm.class, result);
		return UserResponseBuilder.instance().body(formResult).build();
	}
	

	}
}
*/