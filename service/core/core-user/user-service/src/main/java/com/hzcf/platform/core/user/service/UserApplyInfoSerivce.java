package com.hzcf.platform.core.user.service;

import java.util.Map;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.framework.core.service.IBaseService;

/**
 * Created by leijiaming on 2016/12/29 0029.
 */
public interface UserApplyInfoSerivce extends IBaseService<UserApplyInfoVO> {

    Result<UserApplyInfoVO> selectByUserId(String userId);
    Result<UserApplyInfoVO> selectByApplyId(String applyId);
    
	public PaginatedResult<UserApplyInfoVO> getUserApplyInfoList(Map<String, Object> parmMap);
	public Long getUserApplyInfoTotal(Map<String, Object> parmMap);
	
	//不分页
	public PaginatedResult<UserApplyInfoVO> getUserApplyForSearch(Map<String, Object> parmMap);
	
}
