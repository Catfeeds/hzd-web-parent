package com.hzcf.platform.mgr.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.core.user.model.UserImageVO;
import com.hzcf.platform.core.user.model.UserInfoVO;
import com.hzcf.platform.core.user.model.UserRelationVO;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.mgr.sys.service.IApplyDetailService;
/**
 * @description:后台进件详情Controller
 * @author 李强
 * 
 */
@Controller
public class ApplyDetailController {

	private static final Log logger = Log.getLogger(ApplyDetailController.class);
    
	@Autowired
	IApplyDetailService applyDetailService;
	
	/**
	 * 后台进件详情页面
	 * @return
	 */
	@RequestMapping(value="/apply/check/detail",method=RequestMethod.GET)
	public String Detail(String applyId,String mobile,Model model) {
		
		UserApplyInfoVO userApplyInfo = applyDetailService.getUserApplyInfoDetail(applyId);
		if (userApplyInfo != null) {
			model.addAttribute("userApplyInfo", userApplyInfo);
		}
		
		UserVO user = applyDetailService.getUserDetail(mobile);
		if (user != null) {
			model.addAttribute("user", user);
		}
		
		UserInfoVO userInfo = applyDetailService.getUserInfoDetail(applyId);
		if (userInfo != null) {
			model.addAttribute("userInfo", userInfo);
		}
		
		List<UserRelationVO> userRelationVOList = applyDetailService.getUserRelationDetail(applyId);
		if (userRelationVOList != null) {
			model.addAttribute("userRelationVOList", userRelationVOList);
		}
		
		List<UserImageVO> userImageVOList = applyDetailService.getUserImageDetail(applyId);
		if (userImageVOList != null) {
			model.addAttribute("userImageVOList",userImageVOList);
		}
		
		return "apply/detail";
	}

}
