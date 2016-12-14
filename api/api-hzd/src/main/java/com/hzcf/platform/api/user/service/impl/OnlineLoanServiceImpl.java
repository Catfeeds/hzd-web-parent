package com.hzcf.platform.api.user.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.api.model.OnlineLoanInfo;
import com.hzcf.platform.api.model.WxjinjianQueryRsp;
import com.hzcf.platform.api.user.common.BackResult;
import com.hzcf.platform.api.user.controller.UserController;
import com.hzcf.platform.api.user.service.IOnlineLoanService;
import com.hzcf.platform.api.user.service.IUserService;
import com.hzcf.platform.common.cache.ICache;
import com.hzcf.platform.common.util.json.parser.JsonUtil;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.common.util.uuid.UUIDGenerator;
import com.hzcf.platform.core.ConstantsToken;
import com.hzcf.platform.core.DataVerifcation;
import com.hzcf.platform.core.MyfStatusCodeEnum;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.core.user.service.UserService;
import com.hzcf.platform.webService.OnlineLoanWebService;

import net.sf.json.JSONObject;
/**
 * 
 * @description:进件提交和查询
 * @author 雷佳明
 * @version 1.0
 * 
 * <pre>
 * Modification History: 
 * Date              Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年12月8日                       雷佳明                           1.0       1.0 Version 
 * </pre>
 */
@Service
public class OnlineLoanServiceImpl implements IOnlineLoanService {
	private static final Log logger = Log.getLogger(OnlineLoanServiceImpl.class);
	@Autowired
	OnlineLoanWebService onlineLoanWebServic;
	
	@Override
	public BackResult OnlineLoanApply(UserVO user, OnlineLoanInfo onlineLoanInfo) {
		try {
			DataVerifcation.datavVerification(onlineLoanInfo.getMobile(), onlineLoanInfo.getIdCard().toUpperCase(), onlineLoanInfo.getArea(), onlineLoanInfo.getName(), null, user.getPassword(),null);
			

			String sendRsp = onlineLoanWebServic.OnlineLoanApply(onlineLoanInfo);
			JSONObject json = JSONObject.fromObject(sendRsp);
			String retCode = json.getString("retCode");
			String retInfo = json.getString("retInfo");
			logger.i("返回参数：" + sendRsp);
			
			if (retCode.equals("0000")) {
				logger.i("进入微信进件提交方法:提交成功"+retInfo+"手机号:"+onlineLoanInfo.getMobile());
				new BackResult(
						MyfStatusCodeEnum.MEF_CODE_0000.getCode(),
						MyfStatusCodeEnum.MEF_CODE_0000.getMsg(),retInfo);
			} else if(retCode.equals("4000")) {
				new BackResult(
						MyfStatusCodeEnum.MEF_CODE_2200.getCode(), retInfo);
			}else{
				logger.i("进入微信进件提交方法:提交失败:"+retInfo+"手机号:"+onlineLoanInfo.getMobile());
				new BackResult(
						MyfStatusCodeEnum.MEF_CODE_2200.getCode(), retInfo);

			}
		} catch (Exception e) {
			e.printStackTrace();
			new BackResult(
					MyfStatusCodeEnum.MEF_CODE_2211.getCode(),
					e.getMessage());
		}
		return null;
	}

	@Override
	public BackResult OnlineLoanQuery(String mobile) {
		try {
			String sendRsp = onlineLoanWebServic.OnlineLoanQuery(mobile);
			JSONObject  json = JSONObject.fromObject(sendRsp);
			//WxjinjianQueryRsp wxrsp =JsonUtil.string2Object(json.toString(),WxjinjianQueryRsp.class);
		    String retCode = json.getString("retCode");
		    String retInfo = json.getString("retInfo");
		    if(retCode.equals("0000")){
		    	WxjinjianQueryRsp wr=JsonUtil.jsonNote2Object(sendRsp, WxjinjianQueryRsp.class);
		    	logger.i("查询微信进件信息成功：mobile"+mobile);
		    	return new BackResult(MyfStatusCodeEnum.MEF_CODE_0000.getCode(),retInfo,wr!=null?wr.getWeiXinApplyList():null);
			 	
		    }else{
		    	logger.i("查询微信进件信息失败：mobile"+mobile);
		    	return 	new BackResult(MyfStatusCodeEnum.MEF_CODE_2100.getCode(),MyfStatusCodeEnum.MEF_CODE_2100.getMsg());
			 	
		    }
			
		} catch (Exception e) {
			e.printStackTrace();
			new BackResult(
					MyfStatusCodeEnum.MEF_CODE_2111.getCode(),
					e.getMessage());
		}
		return null;
	}
	
}