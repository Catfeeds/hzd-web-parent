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
    /**
     * @Title: updateApplyId 
     * @Description:根据applyId有选择的修改UserApplyInfo中的用户申请信息
     * @time: 2017年1月12日 下午4:48:14  
     * @return:boolean
     */
    public boolean updateApplyId(UserApplyInfo userApplyInfo);
    
}