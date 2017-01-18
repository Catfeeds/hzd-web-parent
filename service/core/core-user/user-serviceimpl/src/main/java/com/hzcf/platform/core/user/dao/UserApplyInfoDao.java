package com.hzcf.platform.core.user.dao;


import java.util.List;
import java.util.Map;

import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
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
    //后台实名认证通过，查询进件数据
    public UserApplyInfoVO selectByUserIdAndStatus(Map<String, Object> parmMap);
    
	/**
	 * @Title: deleteByApplyId 
	 * @Description:根据applyId删除图片信息 
	 * @time: 2017年1月18日 下午5:12:12  
	 * @return:Result<Boolean>
	 */
	public boolean deleteByApplyId(String applyId);
}