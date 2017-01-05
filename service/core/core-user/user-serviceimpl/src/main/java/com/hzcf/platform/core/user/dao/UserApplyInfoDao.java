package com.hzcf.platform.core.user.dao;


import java.util.List;
import java.util.Map;

import com.hzcf.platform.core.user.data.UserApplyInfo;
import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.framework.core.storage.IBaseDao;

public interface UserApplyInfoDao  extends IBaseDao<UserApplyInfo> {



    UserApplyInfo selectByUserId(String userId);

	public List<UserApplyInfoVO> getUserApplyInfoList(Map<String, Object> parmMap);
	public Long getUserApplyInfoTotal(Map<String, Object> parmMap);
    UserApplyInfo selectByApplyId(String applyId);

    public List<UserApplyInfoVO> getUserApplyForSearch(Map<String, Object> parmMap);
    
}