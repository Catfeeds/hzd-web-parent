package com.hzcf.platform.mgr.sys.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.mgr.sys.common.pageModel.DataGrid;
import com.hzcf.platform.mgr.sys.common.pageModel.PageHelper;
import com.hzcf.platform.mgr.sys.common.pageModel.SmsUserInfo;
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
	
	/**
	 * 用户列表页面
	 * @return
	 */
	@RequestMapping(value = "/users/list",method = RequestMethod.GET)
	public String userList() {
	    return "users/list";
	}
	
	/**
	 * 用户分页
	 * @param page
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/users/page",method=RequestMethod.POST)
    @ResponseBody
    public DataGrid userPage(PageHelper page, UserVO user){
		return sysUserService.getUserPage(page, user);
    }
	
	/**
	 * 实名认证列表页面
	 * @return
	 */
	@RequestMapping(value = "/users/check/list",method = RequestMethod.GET)
	public String checkUserList() {
	    return "users/checklist";
	}
	
	/**
	 * 实名认证列表分页
	 * @param page
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/users/check/page",method=RequestMethod.POST)
    @ResponseBody
    public DataGrid checkUserPage(PageHelper page, UserVO user){
		return sysUserService.getUserPage(page, user);
    }	
	/**
	 * 实名认证详情页面
	 * @return
	 */
	@RequestMapping(value="/users/check/detail",method=RequestMethod.GET)
	public String detail(String mobile,Model m) {
		
		//System.out.println(mobile);
		SmsUserInfo smsUserInfo = sysUserService.getSmsUserDetail(mobile);
		m.addAttribute("smsUserInfo",smsUserInfo);
		return "users/detail";
	}
	
	@RequestMapping(value="/users/check/edit",method=RequestMethod.GET)
	public String edit(String mobile,Model m) {
		
		//System.out.println(mobile);
		SmsUserInfo smsUserInfo = sysUserService.getSmsUserDetail(mobile);
		m.addAttribute("smsUserInfo",smsUserInfo);
		return "users/edit";
	}
	
	@RequestMapping(value="/users/check/update",method=RequestMethod.POST)
    public String update(HttpServletRequest request){
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String idCard = request.getParameter("idCard");
		String card1 = request.getParameter("card1");
		String card2 = request.getParameter("card2");
		String card3 = request.getParameter("card3");
		sysUserService.update(mobile, name, idCard, card1, card2, card3);
		return "redirect:/users/check/list";
    }	
	
	@RequestMapping(value="/users/check/updateStatus",method=RequestMethod.GET)
	public String updateStatus(String mobile,String checkStatus,String nopassCause) {
		
		Result<Boolean> st= sysUserService.updateStatus(mobile, checkStatus,nopassCause);
		return "redirect:/users/check/list";
	}
}
