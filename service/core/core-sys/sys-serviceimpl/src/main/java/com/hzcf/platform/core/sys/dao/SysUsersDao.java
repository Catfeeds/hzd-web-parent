package com.hzcf.platform.core.sys.dao;

import com.hzcf.platform.core.sys.data.SysUsers;
import com.hzcf.platform.framework.core.storage.IBaseDao;

public interface SysUsersDao extends IBaseDao<SysUsers> {
	/**
	 * @Description: 获取后台登陆的用户名所对应的密码 
	 * @User: 李强
	 * @FileName: WipeRecordMgr.java
	 * @param 参数  
	 * @return 返回类型 
	 * @date 2017年1月3日
	 * @throws
	 */
	public SysUsers getBySysUsersName(String userName);
}
