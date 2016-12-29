package com.hzcf.platform.core.user.dao;


import java.util.Map;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.core.user.data.User;
import com.hzcf.platform.framework.core.storage.IBaseDao;

public interface UserDao extends IBaseDao<User> {
	/**
	 * 
		 * @Description: 根据手机号更新 
		 * @User: 雷佳明
		 * @FileName: WipeRecordMgr.java
		 * @param 参数  
		 * @return 返回类型 
		 * @date 2016年12月8日
		 * @throws
	 */
	boolean updateMobile(User user);
	
	/**
	 * 按手机号取得
	 * 
	 * @param mobile
	 * @return
	 */
	User getByMobile(String mobile);


	String insertSelective(User user);

	boolean updateByPrimaryKeySelective(User user);
	
	public PaginatedResult<User> getUserPage(Map<String, Object> parmMap);
}
