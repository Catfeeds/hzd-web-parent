package com.hzcf.platform.mgr.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.mgr.sys.common.pageModel.DataGrid;
import com.hzcf.platform.mgr.sys.common.pageModel.PageHelper;
import com.hzcf.platform.mgr.sys.service.IApplyService;
import com.hzcf.platform.mgr.sys.service.IUserService;
/**
 * @description:后台进件管理Controller
 * @author zhangmx
 * 
 */
@Controller
public class ApplyController {

	private static final Log logger = Log.getLogger(ApplyController.class);
    
	@Autowired
	IApplyService applyService;
	
	@RequestMapping(value = "/apply/list",method = RequestMethod.GET)
	public String memberList() {
	    return "apply/list";
	}
	
	@RequestMapping(value="/apply/page",method=RequestMethod.POST)
    @ResponseBody
    public DataGrid userPage(PageHelper page, UserApplyInfoVO apply){
		return applyService.getApplyPage(page, apply);
    }
}
