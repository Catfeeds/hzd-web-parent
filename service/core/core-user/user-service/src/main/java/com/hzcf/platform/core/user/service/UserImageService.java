package com.hzcf.platform.core.user.service;

import java.util.List;
import java.util.Map;

import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.user.model.UserImageVO;
import com.hzcf.platform.framework.core.service.IBaseService;

/**
 * Created by leijiaming on 2016/12/29 0029.
 */
public interface UserImageService extends IBaseService<UserImageVO> {
	
	//public Result<UserImageVO> getByMobile(String mobile);
	
	public Result<List<UserImageVO>> getUserId(String userId);
	
	//String applyId,String type
	public Result<List<UserImageVO>> selectUserImageByApplyIdAndType(Map<String, Object> parmMap);

	public Result<List<UserImageVO>> selectUserImageByUserIdAndType(Map<String, String> parmMap);
	public Result<Boolean> updateImage(UserImageVO userImageVO);
	public Result<Boolean> insertSelective(UserImageVO userImageVO);
	public Result<Boolean> deleteByPrimaryKey(UserImageVO userImageVO);
	
	public Result<Boolean> updateImageByUserIdAndTypeAndUrl(Map<String, String> parmMap);

	public Result<List<UserImageVO>> selectByApplyId(String applyId);
}
