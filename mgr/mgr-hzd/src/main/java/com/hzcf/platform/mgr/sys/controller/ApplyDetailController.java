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
		UserVO user = applyDetailService.getUserDetail(mobile);
		UserInfoVO userInfo = applyDetailService.getUserInfoDetail(applyId);
		List<UserRelationVO> userRelationVOList = applyDetailService.getUserRelationDetail(applyId);
		List<UserImageVO> userImageVOList = applyDetailService.getUserImageDetail(applyId);
		
		model.addAttribute("userApplyInfo", userApplyInfo);
		model.addAttribute("user", user);
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("userRelationVOList", userRelationVOList);
		model.addAttribute("userImageVOList",userImageVOList);
		
		
		return "apply/detail";
	}

}
