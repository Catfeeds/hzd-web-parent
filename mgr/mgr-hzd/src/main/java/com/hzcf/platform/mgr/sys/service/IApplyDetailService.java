package com.hzcf.platform.mgr.sys.service;

import java.util.List;

import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.core.user.model.UserImageVO;
import com.hzcf.platform.core.user.model.UserInfoVO;
import com.hzcf.platform.core.user.model.UserRelationVO;
import com.hzcf.platform.core.user.model.UserVO;

/**
 * 
 * @author 李强
 * 
 */
public interface IApplyDetailService {
	
	/**
	 * 
	 * @param applyId
	 * @return 借款需求信息
	 */
	public UserApplyInfoVO getUserApplyInfoDetail(String applyId);
	
	/**
	 * 
	 * @param mobile
	 * @param applyId
	 * @return 个人资料信息
	 */
	public UserVO getUserDetail(String mobile);
	
	public UserInfoVO getUserInfoDetail(String applyId);
	
	/**
	 * 
	 * @param applyId
	 * @return 联系人信息
	 */
	public List<UserRelationVO> getUserRelationDetail(String applyId);
	
	/**
	 * 
	 * @param applyId
	 * @return 上传图片展示
	 */
	public List<UserImageVO> getUserImageDetail(UserApplyInfoVO userApplyInfo);

	/**
	 *
	 * @param applyId
	 * @return 补充资料上传图片展示
	 */
	public List<UserImageVO> getUserImageDetailForAdd(UserApplyInfoVO userApplyInfo) ;
}
