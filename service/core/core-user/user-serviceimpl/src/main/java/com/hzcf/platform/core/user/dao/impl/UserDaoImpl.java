package com.hzcf.platform.core.user.dao.impl;


import com.hzcf.platform.core.user.dao.UserDao;
import com.hzcf.platform.core.user.data.User;
import org.springframework.stereotype.Repository;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.framework.core.storage.mysql.MysqlGenericDAO;
@Repository
public class UserDaoImpl  extends MysqlGenericDAO<User> implements UserDao {

}
