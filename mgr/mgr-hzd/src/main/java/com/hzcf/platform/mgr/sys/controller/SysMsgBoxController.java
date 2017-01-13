package com.hzcf.platform.mgr.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.platform.core.user.model.MsgBoxVO;
import com.hzcf.platform.mgr.sys.common.pageModel.DataGrid;
import com.hzcf.platform.mgr.sys.common.pageModel.PageHelper;
import com.hzcf.platform.mgr.sys.service.ISysMsgBoxService;

/**
 * @description:msgBox功能操作
 * @author 李强
 *
 */
public class SysMsgBoxController {

	@Autowired
	ISysMsgBoxService sysMsgBoxService;
	
	@RequestMapping(value="/msgBox/list",method=RequestMethod.POST)
    @ResponseBody
    public DataGrid orderPage(PageHelper page, MsgBoxVO msgBox){
		return sysMsgBoxService.getMsgBoxPage(page, msgBox);
    }
	
	
}
