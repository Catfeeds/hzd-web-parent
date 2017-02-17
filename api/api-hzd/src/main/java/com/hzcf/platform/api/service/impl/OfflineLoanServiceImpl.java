package com.hzcf.platform.api.service.impl;


import com.hzcf.platform.api.annotation.LogAnnotation;
import com.hzcf.platform.api.service.IOfflineLoanService;
import com.hzcf.platform.common.exception.CheckException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.api.model.OnlineLoanInfo;
import com.hzcf.platform.api.model.WxjinjianQueryRsp;
import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.common.util.json.parser.JsonUtil;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.api.common.DataVerifcation;
import com.hzcf.platform.api.baseEnum.HzdStatusCodeEnum;
import com.hzcf.platform.core.user.model.UserVO;
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
public class OfflineLoanServiceImpl implements IOfflineLoanService {
	private static final Log logger = Log.getLogger(OfflineLoanServiceImpl.class);
	@Autowired
	OnlineLoanWebService onlineLoanWebServic;
	
	@Override
	@LogAnnotation
	public BackResult offlineLoanApply(UserVO user, OnlineLoanInfo onlineLoanInfo) {
		try {
			DataVerifcation.datavVerification(user.getMobile(), onlineLoanInfo.getIdCard().toUpperCase(), onlineLoanInfo.getArea(), onlineLoanInfo.getName());

			onlineLoanInfo.setMobile(user.getMobile());
			onlineLoanInfo.setOpenId(user.getId());
			String sendRsp = onlineLoanWebServic.OnlineLoanApply(onlineLoanInfo);
			JSONObject json = JSONObject.fromObject(sendRsp);
			String retCode = json.getString("retCode");
			String retInfo = json.getString("retInfo");
			logger.i("外访协助申请返回参数：" + sendRsp);
			
			if (retCode.equals("0000")) {
				logger.i("外访协助申请成功"+retInfo+"手机号:"+onlineLoanInfo.getMobile());
				return	new BackResult(
						HzdStatusCodeEnum.MEF_CODE_0000.getCode(),
						HzdStatusCodeEnum.MEF_CODE_0000.getMsg(),retInfo);
			} else if(retCode.equals("4000")) {
		    	return new BackResult(
		    			HzdStatusCodeEnum.MEF_CODE_2333.getCode(), retInfo,null);
		    }else{
				logger.i("进入微信进件提交方法:提交失败:"+retInfo+"手机号:"+onlineLoanInfo.getMobile());
				return	new BackResult(
						HzdStatusCodeEnum.MEF_CODE_2200.getCode(), retInfo);

			}
		}catch (CheckException e){
			e.printStackTrace();
			return	new BackResult(
					HzdStatusCodeEnum.MEF_CODE_9000.getCode(),
					e.getMessage(),null);
		}
		catch (Exception e) {
			e.printStackTrace();
			return	new BackResult(
					HzdStatusCodeEnum.MEF_CODE_2211.getCode(),
					e.getMessage(),null);
		}
	}
	//作废  LoadService
	@Override
	@LogAnnotation
	public BackResult offlineLoanQuery(String mobile) {
		try {
			String sendRsp = onlineLoanWebServic.OnlineLoanQuery(mobile);
			JSONObject  json = JSONObject.fromObject(sendRsp);
			//WxjinjianQueryRsp wxrsp =JsonUtil.string2Object(json.toString(),WxjinjianQueryRsp.class);
		    String retCode = json.getString("retCode");
		    String retInfo = json.getString("retInfo");
		    if(retCode.equals("0000")){
		    	WxjinjianQueryRsp wr=JsonUtil.jsonNote2Object(sendRsp, WxjinjianQueryRsp.class);
		    	logger.i("查询微信进件信息成功：mobile"+mobile);
		    	return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),retInfo,wr!=null?wr.getWeiXinApplyList():null);
			 	
		    }else if(retCode.equals("4000")) {
		    	return new BackResult(
		    			HzdStatusCodeEnum.MEF_CODE_2333.getCode(), retInfo,null);
		    }else{
		    	logger.i("查询微信进件信息失败：mobile"+mobile);
		    	return 	new BackResult(HzdStatusCodeEnum.MEF_CODE_2100.getCode(), HzdStatusCodeEnum.MEF_CODE_2100.getMsg());
			 	
		    }
			
		} catch (Exception e) {
			e.printStackTrace();
			new BackResult(
					HzdStatusCodeEnum.MEF_CODE_2111.getCode(),
					e.getMessage());
		}
		return null;
	}
	
}