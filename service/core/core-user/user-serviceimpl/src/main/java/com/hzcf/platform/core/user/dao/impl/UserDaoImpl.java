package com.hzcf.platform.core.user.dao.impl;


import com.hzcf.platform.core.user.dao.UserDao;
import com.hzcf.platform.core.user.data.User;
import com.hzcf.platform.framework.core.storage.mysql.AbstractMysqlBaseDaoImpl;
import org.springframework.stereotype.Repository;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
@Repository
public class UserDaoImpl  extends AbstractMysqlBaseDaoImpl<User> implements UserDao {

}
