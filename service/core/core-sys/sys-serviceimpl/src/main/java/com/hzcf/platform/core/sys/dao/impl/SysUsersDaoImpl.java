package com.hzcf.platform.core.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.hzcf.platform.core.sys.dao.SysUsersDao;
import com.hzcf.platform.core.sys.data.SysUsers;
import com.hzcf.platform.framework.core.storage.mysql.AbstractMysqlBaseDaoImpl;

@Repository
public class SysUsersDaoImpl  extends AbstractMysqlBaseDaoImpl<SysUsers> implements SysUsersDao {

	
	/**
	 * 获取后台登陆的用户名所对应的密码
	 */
	@Override
	public SysUsers getBySysUsersName(String userName) {
		return sqlSessionTemplate.selectOne(getSqlName("selectBySysUsersName"),userName);
	}
	
}
