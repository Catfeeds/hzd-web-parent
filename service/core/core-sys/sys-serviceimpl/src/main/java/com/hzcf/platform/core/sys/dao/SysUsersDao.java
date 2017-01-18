package com.hzcf.platform.core.sys.dao;

import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.sys.data.SysUsers;
import com.hzcf.platform.core.sys.model.SysUsersVO;
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
	/**
	 * @Title: updateByPrimaryKeySelective 
	 * @Description:修改后台用户的登录密码 
	 * @time: 2017年1月17日 下午7:28:29  
	 * @return:boolean
	 */
	public boolean updateByPrimaryKeySelective(SysUsersVO sysUsersVO);
	
	/**
	 * @Title: updateByUserNameSelective 
	 * @Description: TODO 
	 * @time: 2017年1月17日 下午9:41:18  
	 * @return:Result<Integer>
	 */
	public int updateByUserNameSelective(SysUsersVO sysUsersVO);
	
}