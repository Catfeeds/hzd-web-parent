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
	/**
	 * @Title: deleteByApplyId 
	 * @Description:根据applyId删除图片信息 
	 * @time: 2017年1月18日 下午5:12:12  
	 * @return:Result<Boolean>
	 */
	public Result<Boolean> deleteByApplyId(String applyId);
	/**
	 * @Title: deleteByApplyIdList 
	 * @Description:根据applyId集合删除借款人的图片信息
	 * @time: 2017年1月18日 下午8:18:03  
	 * @return:Result<Boolean>
	 */
	public Result<Boolean> deleteByApplyIdList(List<String> applyIdList);
}
