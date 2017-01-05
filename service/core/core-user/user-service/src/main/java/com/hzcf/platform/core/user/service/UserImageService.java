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
	
	public Result<UserImageVO> getById(String userId);
	
	//String applyId,String type
	public Result<List<UserImageVO>> selectUserImageByApplyIdAndType(Map<String, Object> parmMap);
	
	public Result<Boolean> updateImage(UserImageVO userImageVO);
	
}
