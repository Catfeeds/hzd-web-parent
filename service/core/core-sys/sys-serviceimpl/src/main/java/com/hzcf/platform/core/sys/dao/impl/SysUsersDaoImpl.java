package com.hzcf.platform.core.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.sys.dao.SysUsersDao;
import com.hzcf.platform.core.sys.data.SysUsers;
import com.hzcf.platform.core.sys.model.SysUsersVO;
import com.hzcf.platform.framework.core.storage.mysql.AbstractMysqlBaseDaoImpl;

@Repository
public class SysUsersDaoImpl  extends AbstractMysqlBaseDaoImpl<SysUsers> implements SysUsersDao {

	
	/**
	 * 获取后台登陆的用户名所对应的密码
	 */
	@Override
	public SysUsers getBySysUsersName(String userName) {
		return this.sqlSessionTemplate.selectOne(this.getSqlName("selectBySysUsersName"),userName);
	}
	/**修改后台用户的登录密码 
	 * 
	 */
	@Override
	public boolean updateByPrimaryKeySelective(SysUsersVO sysUsersVO) {
		if(sysUsersVO!=null){
			this.sqlSessionTemplate.update(this.getSqlName("updateByPrimaryKeySelective"),sysUsersVO);			
		}else{
			return false;
		}
		return true;
	}
	@Override
	public int updateByUserNameSelective(SysUsersVO sysUsersVO) {
		if(sysUsersVO!=null){
			return this.sqlSessionTemplate.update(this.getSqlName("updateByUserNameSelective"),sysUsersVO);			
		}else{
			return -1;
		}
	}
	
}
