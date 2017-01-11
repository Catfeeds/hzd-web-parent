package com.hzcf.platform.api.service.impl;

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
	@Override
	public void insertLoad() {
		try {
			String result=LoadService.insertLoad("APP20170110152844173");
			logger.i("result:"+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**借款人查询借款进度
	 * 
	 */
	@Override
	public BackResult selectLoadProgress(UserVO user) {
		String mobile=user.getMobile();
		if(StringUtils.isBlank(mobile)){
			logger.i("接口：借款人查询借款进度失败，手机号不存在，mobile参数值："+mobile);
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_6102.getCode(), HzdStatusCodeEnum.MEF_CODE_6102.getMsg());
		}
		Result<UserVO> byMobile=userSerivce.getByMobile(mobile);
		UserVO items=byMobile.getItems();
		if(items ==null){
			logger.i("接口：借款人查询借款进度失败，未查询到用户信息");
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_6103.getCode(),HzdStatusCodeEnum.MEF_CODE_6103.getMsg());
		}
		String idCard=items.getIdCard();//用户身份证信息
		String result=LoadService.selectLoadProgress(idCard);
		if (StringUtils.isNotBlank(result) && "0000".equals(JSONObject.fromObject(result).getString("retCode"))) {
			logger.i("接口：借款人查询借款进度成功，结果："+result);
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(), HzdStatusCodeEnum.MEF_CODE_0000.getMsg(),result);
		}else{
			logger.e("接口：借款人查询借款进度失败，结果："+result);
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_6101.getCode(), HzdStatusCodeEnum.MEF_CODE_6101.getMsg());
		}
	}
}