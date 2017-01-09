package com.hzcf.platform.api.service.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.hzcf.platform.api.baseEnum.HzdStatusCodeEnum;
import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.service.ILoadService;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.webService.LoadService;

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
	/**进件接口，就是保存借款申请
	 * 
	 */
	@Override
	public BackResult insertLoad(UserVO user, Map map) {
		
		
		return null;
	}
	/**借款人查询借款进度
	 * 
	 */
	@Override
	public BackResult selectLoadProgress(UserVO user) {
		String result=LoadService.selectLoadProgress(user);
		if (StringUtils.isNotBlank(result)) {
			logger.i("接口：借款人查询借款进度成功，结果："+result);
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(), HzdStatusCodeEnum.MEF_CODE_0000.getMsg());
		}else{
			logger.e("接口：借款人查询借款进度成功，结果："+result);
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_6100.getCode(), HzdStatusCodeEnum.MEF_CODE_6100.getMsg());
		}
	}
}