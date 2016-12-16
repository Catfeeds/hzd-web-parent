package com.hzcf.platform.core.sys.dao.impl;

import com.hzcf.platform.core.sys.data.SysUserRole;
import com.hzcf.platform.framework.core.storage.mysql.AbstractMysqlBaseDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hzcf.platform.core.sys.dao.SysUserRoleDao;


@Repository
public class SysUserRoleDaoImpl extends AbstractMysqlBaseDaoImpl<SysUserRole> implements SysUserRoleDao {
private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	final static String mapperStr = "SysUserRole";

	@Override
	public SysUserRole getByUserId(Long userId) {
		// TODO Auto-generated method stub
		SysUserRole  sr=sqlSessionTemplate.selectOne(mapperStr
				+ ".queryByUserId", userId);
		return sr;
	}

}
