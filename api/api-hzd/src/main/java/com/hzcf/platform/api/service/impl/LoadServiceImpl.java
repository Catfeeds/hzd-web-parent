package com.hzcf.platform.api.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.api.baseEnum.HzdStatusCodeEnum;
import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.service.ILoadService;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.core.user.model.UserVO;
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
	public LoadService LoadService;
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
		String result=LoadService.selectLoadProgress(user);
		if (StringUtils.isNotBlank(result) && "0000".equals(JSONObject.fromObject(result).getString("retCode"))) {
			logger.i("接口：借款人查询借款进度成功，结果："+result);
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(), HzdStatusCodeEnum.MEF_CODE_0000.getMsg());
		}else{
			logger.e("接口：借款人查询借款进度失败，结果："+result);
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_6101.getCode(), HzdStatusCodeEnum.MEF_CODE_6101.getMsg());
		}
	}
}