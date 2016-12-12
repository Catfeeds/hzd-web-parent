package com.hzcf.platform.core.user.dao.impl;


import com.hzcf.platform.core.user.dao.UserDao;
import com.hzcf.platform.core.user.data.User;
import com.hzcf.platform.framework.core.storage.mysql.AbstractMysqlBaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl  extends AbstractMysqlBaseDaoImpl<User> implements UserDao {
	@Override
	public boolean updateMobile(User user) {
		if (user != null && user.getMobile().length()>0 ) {
			sqlSessionTemplate.update(getSqlName("updateMobile"), user);
		}
		return true;
	}
	
	@Override
	public User getByMobile(String mobile) {
		return sqlSessionTemplate.selectOne(getSqlName("selectByMobile"), mobile);
	}
	
}
