package com.hzcf.platform.api.service.impl;

import com.hzcf.platform.api.annotation.LogAnnotation;
import com.hzcf.platform.api.config.BaseConfig;
import com.hzcf.platform.api.model.WxjinjianQueryRsp;
import com.hzcf.platform.api.util.DateUtil;
import com.hzcf.platform.common.util.json.parser.JsonUtil;
import com.hzcf.platform.common.util.status.StatusCodes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.core.user.service.UserApplyInfoSerivce;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.api.baseEnum.HzdStatusCodeEnum;
import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.service.ILoadService;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.core.user.service.UserService;
import com.hzcf.platform.webService.LoadService;

import net.sf.json.JSONObject;

/**
  * @Description:借款信息的service
  * @author 作者:裴高祥 
  * @date 创建时间：2016年12月29日 下午5:57:19 
  * @version 1.0 
  * @since  JDK1.7
  */
@Service
public class LoadServiceImpl implements ILoadService {
	private static final Log logger = Log.getLogger(LoadServiceImpl.class);
	@Autowired
	public LoadService LoadService;//借款组件service（线下和调度的对接类）
    @Autowired
    public UserService userSerivce;//借款人service
	@Autowired
	public UserApplyInfoSerivce userApplyInfoSerivce;
	/**进件接口，进件成功之后不会修改数据库
	 * 
	 */
	@Override
	@LogAnnotation
	public BackResult insertLoad(String params) {
		JSONObject json=JSONObject.fromObject(params);
		String applyId=json.getString("applyId");
		String result="";
			result=LoadService.insertLoad(applyId);
		
		return new BackResult(HzdStatusCodeEnum.HZD_CODE_0000.getCode(),result);
	}
	/**进件接口，进件成功之后会修改数据库
	 * 
	 */
	@Override
	@LogAnnotation
	public BackResult operateLoad(String params) throws Exception {
		JSONObject json=JSONObject.fromObject(params);
		String applyId=json.getString("applyId");
		boolean result=LoadService.operateLoad(applyId);
		return new BackResult(HzdStatusCodeEnum.HZD_CODE_0000.getCode(),HzdStatusCodeEnum.HZD_CODE_0000.getMsg(),result);
	}
	/**根据userId删除借款人的进件信息
	 * 
	 */
	@Override
	@LogAnnotation
	public BackResult deleteLoad(String params) {
		JSONObject json=JSONObject.fromObject(params);
		String userId=json.getString("userId");
		boolean result=LoadService.deleteLoad(userId);
		return new BackResult(HzdStatusCodeEnum.HZD_CODE_0000.getCode(),HzdStatusCodeEnum.HZD_CODE_0000.getMsg(),result);
	}
	/**借款人查询借款进度
	 * 
	 */
	@Override
	@LogAnnotation
	public BackResult selectLoadProgress(UserVO user) {
		String mobile=user.getMobile();
		if(StringUtils.isBlank(mobile)){
			logger.i("接口：借款人查询借款进度失败，手机号不存在，mobile参数值："+mobile);
			return new BackResult(HzdStatusCodeEnum.HZD_CODE_6102.getCode(), HzdStatusCodeEnum.HZD_CODE_6102.getMsg());
		}
		try {
			Result<UserVO> byMobile=userSerivce.getByMobile(mobile);
			UserVO items=byMobile.getItems();
				if (StatusCodes.OK != (byMobile.getStatus())) {
					logger.i("数据查询失败 - 500,  失败 。。。。。。。。。。。。。 ");
					return new BackResult(HzdStatusCodeEnum.HZD_CODE_0001.getCode(),
							HzdStatusCodeEnum.HZD_CODE_0001.getMsg());
				}
			if(items ==null){
				logger.i("接口：借款人查询借款进度失败，未查询到用户信息");
				return new BackResult(HzdStatusCodeEnum.HZD_CODE_6103.getCode(),HzdStatusCodeEnum.HZD_CODE_6103.getMsg());
			}

			//String idCard=items.getIdCard();//用户身份证信息
			String result=LoadService.selectLoadProgress(items.getMobile());
			JSONObject  json = JSONObject.fromObject(result);
			//WxjinjianQueryRsp wxrsp =JsonUtil.string2Object(json.toString(),WxjinjianQueryRsp.class);
			String retCode = json.getString("retCode");
			String retInfo = json.getString("retInfo");
			if(retCode.equals("0000")){
				Map map = new HashedMap();
				logger.i("接口：借款人查询借款进度成功，结果："+result);
				WxjinjianQueryRsp wr= JsonUtil.jsonNote2Object(result, WxjinjianQueryRsp.class);

			/*	if("0".equals(wr.getStatusCodeApplyOnLine()) && "0".equals(wr.getStatusCodeWFXZ()) && wr.getWeiXinApplyList()==null){
					Map mapstatus = new HashedMap();
					mapstatus.put("userId",items.getId());
					mapstatus.put("status","2");
					Result<UserApplyInfoVO> userApplyInfoVOResult = userApplyInfoSerivce.selectByUserIdAndStatus(mapstatus);
					if(userApplyInfoVOResult.getItems()!=null){
						List<WxjinjianQueryRsp.WeiXinApplyList>  wlList = new ArrayList<>();
						WxjinjianQueryRsp.WeiXinApplyList  wl = new WxjinjianQueryRsp.WeiXinApplyList();
						Date createTime = userApplyInfoVOResult.getItems().getApplySubmitTime();
						wl.setCreateTime(DateUtil.formatDate3(createTime));
						wl.setIsWFXZFlag("1");
						wl.setWeiXinApplicationStatus("正在等待审核资料");
						wlList.add(wl);
						return new BackResult(HzdStatusCodeEnum.HZD_CODE_0000.getCode(),
								HzdStatusCodeEnum.HZD_CODE_0000.getMsg(),wlList);
					}
				}*/
				return new BackResult(HzdStatusCodeEnum.HZD_CODE_0000.getCode(),retInfo,wr.getWeiXinApplyList()!=null?wr.getWeiXinApplyList():null);
			}else{
				logger.e("接口：借款人查询借款进度失败，结果："+result);
				return new BackResult(HzdStatusCodeEnum.HZD_CODE_6101.getCode(), HzdStatusCodeEnum.HZD_CODE_6101.getMsg());
			}

		}catch (Exception e){
			e.printStackTrace();
			logger.e("接口：借款人查询借款进度异常");
			return new BackResult(HzdStatusCodeEnum.HZD_CODE_9999.getCode(), HzdStatusCodeEnum.HZD_CODE_9999.getMsg());
		}
	}
}