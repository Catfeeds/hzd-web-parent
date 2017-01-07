package com.hzcf.platform.api.service;

import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.core.user.model.UserVO;

/**
  * @Description:借款信息的service
  * @author 作者:裴高祥 E-mail:pgx19890112@163.com Tel:13241706779
  * @date 创建时间：2016年12月29日 下午5:41:29 
  * @version 1.0 
  * @since  JDK1.7
  */
public interface ILoadService {
	//查询实名认证状态，信息
	public BackResult selectLoadProgress(UserVO user);
}