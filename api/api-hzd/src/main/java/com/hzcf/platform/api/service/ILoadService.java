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
	//进件,仅仅保存借款信息
	public BackResult insertLoad(String params);
/*	//进件,保存借款信息，同时修改数据库中的“借款状态”
	public BackResult operateLoad(String params) throws Exception;*/
	//删除进件,
	public BackResult deleteLoad(String params);
	//查询借款进度
	public BackResult selectLoadProgress(UserVO user);
}