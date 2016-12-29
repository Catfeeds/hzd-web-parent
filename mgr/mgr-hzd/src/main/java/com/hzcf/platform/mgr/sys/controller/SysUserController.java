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
import com.hzcf.platform.mgr.sys.service.ISysUserService;
/**
 * 
 * @author zhangmx
 * 
 */
@Controller
public class SysUserController {

	private static final Log logger = Log.getLogger(SysUserController.class);
    
	@Autowired
	ISysUserService sysUserService;
	
	@RequestMapping(value="/user/list",method=RequestMethod.POST)
    @ResponseBody
    public DataGrid orderPage(PageHelper page, UserVO user){
		return sysUserService.getUserPage(page, user);
    }

	   
}
