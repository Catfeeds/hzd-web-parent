package com.hzcf.platform.mgr.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.core.user.model.UserDictJson;
import com.hzcf.platform.core.user.model.UserImageVO;
import com.hzcf.platform.core.user.model.UserInfoVO;
import com.hzcf.platform.core.user.model.UserRelationVO;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.core.user.service.DictUtilService;
import com.hzcf.platform.core.user.service.UserApplyInfoSerivce;
import com.hzcf.platform.core.user.service.UserImageService;
import com.hzcf.platform.core.user.service.UserInfoService;
import com.hzcf.platform.core.user.service.UserRelationService;
import com.hzcf.platform.core.user.service.UserService;
import com.hzcf.platform.mgr.sys.service.IApplyDetailService;
import com.hzcf.platform.mgr.sys.util.DateUtil;

@Service
public class ApplyDetailServiceImpl implements IApplyDetailService {
	
	private static final Log logger = Log.getLogger(ApplyDetailServiceImpl.class);

	@Autowired
	public UserApplyInfoSerivce userApplyInfoService;
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public UserInfoService userInfoService;
	
	@Autowired
	public UserRelationService userRelationService;
	
	@Autowired
	public UserImageService userImageService;
	
	@Autowired
    DictUtilService dictUtilService;
	
	/**
	 * 通过applyId获取用户申请表中的信息，即借款需求信息（大类、小类）
	 */
	@Override
	public UserApplyInfoVO getUserApplyInfoDetail(String applyId) {
		
		Result<UserApplyInfoVO> userApplyInfoVO = userApplyInfoService.selectByApplyId(applyId);
		String loanPurposeOne = userApplyInfoVO.getItems().getLoanPurposeOne();
		String loanPurposeTwo = userApplyInfoVO.getItems().getLoanPurposeTwo();
		userApplyInfoVO.getItems().setLoanPurposeOne(dictUtilService.convertLoanPurposeOne(loanPurposeOne));
		userApplyInfoVO.getItems().setLoanPurposeTwo(dictUtilService.convertLoanPurposeTwo(loanPurposeOne,loanPurposeTwo));
		return userApplyInfoVO.getItems();
	}

	/**
	 * 通过applyId获取用户详情表中的信息，即个人详情资料信息、工作信息、其它
	 */
	@Override
	public UserVO getUserDetail(String mobile) {

		Result<UserVO> userVO = userService.getByMobile(mobile);
//		String idcard = userVO.getItems().getIdCard();
//		String birthday = idcard.substring(6,10)+"年"+idcard.substring(10,12)+"月"+idcard.substring(12,14)+"日";
//		userVO.getItems().setBirthday(birthday);
//		String gendercode = idcard.substring(16,17);
//		if(Integer.parseInt(gendercode) % 2 != 0){
//			userVO.getItems().setGender("男");
//		} else {
//			userVO.getItems().setGender("女");
//		}
//		return userVO.getItems();

		String idcard = userVO.getItems().getIdCard();
		//根据idcard获取出生日期
		String birthdate = idcard.substring(6,14);
		Date birthday = DateUtil.parseDate(birthdate);
		userVO.getItems().setBirthday(birthday);
	 	//根据idcard获取性别
		String gendercode = idcard.substring(16,17);
		if(Integer.parseInt(gendercode) % 2 != 0){
			userVO.getItems().setGender("男");
		} else {
			userVO.getItems().setGender("女");
		}
		return userVO.getItems();
		
	}
	
	@Override
	public UserInfoVO getUserInfoDetail(String applyId) {

		Result<UserInfoVO> userInfoVO = userInfoService.selectByApplyId(applyId);
		return userInfoVO.getItems();
	}

	/**
	 * 通过applyId获取联系人信息	?通过进件id（applyID）和证明人类型（Type）
	 * 0=工作证明人		 1=家庭证明人		2=紧急联络人
	 */
	@Override
	public List<UserRelationVO> getUserRelationDetail(String applyId) {
		
		UserDictJson userDictJson = new UserDictJson();
		
		
		Result<List<UserRelationVO>> userRelationList = userRelationService.selectByApplyId(applyId);
		List<UserRelationVO> userRelationVOList = userRelationList.getItems();
		
		
		return userRelationVOList;
	}

	/**
	 * 通过applyId获取图片	?通过进件id（applyID）和图片类型（Type）
	 * 0=身份证		 1=征信报告		2=个人住址证明		3=收入证明		4=实名认证图片		 5=工作证明		6=社保/公积金		7=其它
	 */
	@Override
	public List<UserImageVO> getUserImageDetail(String applyId) {

		Result<List<UserImageVO>> userImageList = userImageService.selectByApplyId(applyId);
		List<UserImageVO> userImageVOList = userImageList.getItems();
		return userImageVOList;
	}
	
}
