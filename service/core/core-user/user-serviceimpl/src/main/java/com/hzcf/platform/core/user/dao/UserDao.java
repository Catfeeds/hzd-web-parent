package com.hzcf.platform.core.user.dao;

import com.hzcf.platform.core.user.data.User;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.framework.core.storage.IBaseDao;

public interface UserDao extends IBaseDao<User> {
	boolean updateMobile(User user);
}
