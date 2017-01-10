package com.hzcf.platform.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hzcf.platform.api.annotation.RequestAttribute;
import com.hzcf.platform.api.annotation.RequestBodyForm;
import com.hzcf.platform.api.model.OnlineLoanInfo;
import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.service.IOfflineLoanService;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.api.config.BaseConfig;
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
public class OfflineApplyLoanController {
	private static final Log logger = Log.getLogger(OfflineApplyLoanController.class);
    @Autowired
	IOfflineLoanService onlineLoanService;
	
	@RequestMapping(value="rest/api/100/offlineLoan/apply",method=RequestMethod.POST)
	@ResponseBody
	public BackResult onlineLoanApply(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user ,@RequestBodyForm OnlineLoanInfo onlineLoanInfo ){
		logger.i("进入外访协助接口 ====入参====UserVO:"+user.toString());
		logger.i("进入外访协助接口====入参====onlineLoanInfo:"+onlineLoanInfo.toString());
		return onlineLoanService.offlineLoanApply(user, onlineLoanInfo);
	}
	
	@RequestMapping(value="rest/api/100/offlineLoan/query/{mobile}",method=RequestMethod.GET)
	@ResponseBody
	public BackResult onlineLoanQuery(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user,@PathVariable String mobile){
		 logger.i("进入用户查询进件功能 ====入参====UserVO:"+user.toString());
		 return onlineLoanService.offlineLoanQuery(mobile);
	}
	
}
