package com.hzcf.platform.core.sys.dao;


import java.util.List;
import java.util.Map;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.sys.data.User;
import com.hzcf.platform.core.sys.model.UserVO;
import com.hzcf.platform.framework.core.storage.StorageProvider;

public interface UserDao extends StorageProvider<User> {

    public List<User> getCollecion();
    
	public List<User> getUserListByIds(Long... ids);

	public User getUserByID(String id);	
	
	public boolean updateUserPsw(User user);
	
	public User getUserByLoginName(String loginName);	
	
	public PaginatedResult<User> flipPage(Map<String, Object> param, int pageSize, int pageNo);
 
}
