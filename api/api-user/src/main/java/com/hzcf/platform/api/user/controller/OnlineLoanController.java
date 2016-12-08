package com.hzcf.platform.api.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hzcf.platform.annotation.RequestAttribute;
import com.hzcf.platform.annotation.RequestBodyForm;
import com.hzcf.platform.api.model.OnlineLoanInfo;
import com.hzcf.platform.api.user.common.BackResult;
import com.hzcf.platform.api.user.service.IOnlineLoanService;
import com.hzcf.platform.api.user.service.IUserService;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.config.BaseConfig;
import com.hzcf.platform.core.user.model.UserVO;

/**
 * 
 * @description:在线进件申请和查询功能
 * @author 雷佳明
 * @version 1.0
 * 
 * <pre>
 * Modification History: 
 * Date              Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年12月7日                       雷佳明                           1.0       1.0 Version 
 * </pre>
 */
@RestController
public class OnlineLoanController {
	private static final Log logger = Log.getLogger(OnlineLoanController.class);
    @Autowired
	IOnlineLoanService onlineLoanService;
	
	@RequestMapping(value="rest/api/jinjian/apply",method=RequestMethod.POST)
	public BackResult apply(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user ,@RequestBodyForm OnlineLoanInfo onlineLoanInfo ){
		logger.i("进入用户借贷申请 ====入参====UserVO:"+user.toString());
		logger.i("进入用户借贷申请====入参====onlineLoanInfo:"+onlineLoanInfo.toString());
		return onlineLoanService.OnlineLoanApply(user, onlineLoanInfo);
	}
	
	@RequestMapping(value="rest/api/jinjian/query/{type}",method=RequestMethod.GET)
	public BackResult logonUser(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user,@PathVariable String type){
		 logger.i("进入用户查询进件功能 ====入参====UserVO:"+user.toString());
		 return onlineLoanService.OnlineLoanQuery(type);
	}
	
}
