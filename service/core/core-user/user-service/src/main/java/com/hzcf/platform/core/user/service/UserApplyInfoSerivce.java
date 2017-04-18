package com.hzcf.platform.core.user.service;

import java.util.List;
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

    Result<UserApplyInfoVO> selectByUserId(Map<String, Object> parmMap);
    Result<UserApplyInfoVO> selectByApplyId(String applyId);
	Result<UserApplyInfoVO> selectByBorrowerApplyId(String borrowerApplyId);

	/**
     * @Title: selectByUserIdAndStatusAll 
     * @Description:根据userId，status查询借款人的申请信息 
     * @time: 2017年1月18日 下午6:47:57  
     * @return:Result<UserApplyInfoVO>
     */
  	public Result<List<UserApplyInfoVO>> selectByUserIdAndStatusAll(Map<String, Object> parmMap);
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
	 * @Title: deleteByApplyIdListAndStatus 
	 * @Description:根据applyIdList集合，status删除借款人的进件信息 
	 * @time: 2017年1月18日 下午8:34:40  
	 * @return:Result<Boolean>
	 */
	public Result<Boolean> deleteByApplyIdListAndStatus(Map<String,Object> paramsMap);
}