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
	/**
	 * @Title: updateApplyId 
	 * @Description:根据applyId有选择的修改UserApplyInfo中的用户申请信息
	 * @time: 2017年1月12日 下午4:50:57  
	 * @return:Result<Boolean>
	 */
	public Result<Boolean> updateApplyId(UserApplyInfoVO userApplyInfoVO);
	//后台实名认证通过，查询进件数据
	public Result<UserApplyInfoVO> selectByUserIdAndStatus(Map<String, Object> parmMap);
	/**
	 * @Title: deleteByApplyId 
	 * @Description:根据applyId删除借款人的申请信息 
	 * @time: 2017年1月18日 下午5:22:18  
	 * @return:Result<Boolean>
	 */
	public Result<Boolean> deleteByApplyId(String applyId);
}
