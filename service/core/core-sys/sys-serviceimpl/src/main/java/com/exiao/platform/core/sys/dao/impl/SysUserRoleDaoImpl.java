package com.exiao.platform.core.sys.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.exiao.platform.core.sys.dao.SysUserRoleDao;

import com.exiao.platform.core.sys.data.SysUserRole;

import com.exiao.platform.framework.core.storage.mysql.MysqlGenericDAO;

@Repository
public class SysUserRoleDaoImpl extends MysqlGenericDAO<SysUserRole>implements SysUserRoleDao {
private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	final static String mapperStr = "com.exiao.platform.core.sys.data.SysUserRole";

	@Override
	public SysUserRole getByUserId(Long userId) {
		// TODO Auto-generated method stub
		SysUserRole  sr=sqlSessionTemplate.selectOne(mapperStr
				+ ".queryByUserId", userId);
		return sr;
	}

}
