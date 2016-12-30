package com.hzcf.platform.mgr.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.mgr.sys.common.pageModel.DataGrid;
import com.hzcf.platform.mgr.sys.common.pageModel.PageHelper;
import com.hzcf.platform.mgr.sys.service.IUserService;
/**
 * @description:后台用户管理
 * @author zhangmx
 * 
 */
@Controller
public class UserController {

	private static final Log logger = Log.getLogger(UserController.class);
    
	@Autowired
	IUserService sysUserService;
	
	@RequestMapping(value = "/users/list",method = RequestMethod.GET)
	public String memberList() {
	    return "users/list";
	}
	
	@RequestMapping(value="/users/page",method=RequestMethod.POST)
    @ResponseBody
    public DataGrid userPage(PageHelper page, UserVO user){
		return sysUserService.getUserPage(page, user);
    }
}
