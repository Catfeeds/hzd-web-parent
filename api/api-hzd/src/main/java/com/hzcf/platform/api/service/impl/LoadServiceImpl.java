package com.hzcf.platform.api.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.config.ConstantsDictionary;
import com.hzcf.platform.api.service.ILoadService;
import com.hzcf.platform.api.util.HttpTool;
import com.hzcf.platform.core.user.model.UserVO;

/**
  * @Description:借款信息的service
  * @author 作者:裴高祥 
  * @date 创建时间：2016年12月29日 下午5:57:19 
  * @version 1.0 
  * @since  JDK1.7
  */
@Service
public class LoadServiceImpl implements ILoadService {
	/**借款人查询借款信息，状态，进度
	 * 
	 */
	@Override
	public BackResult selectLoadProgress(UserVO user) {
		/**封装参数：*/
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("idCard","");//身份证号
		map.put("systemSourceId","");//系统标识
		map.put("signature","");//签名信息
		String result=HttpTool.doGet(ConstantsDictionary.dispatchSelectLoadProgress,map);
		
		
		return null;
	}
}