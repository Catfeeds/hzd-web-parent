package com.hzcf.platform.mgr.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.user.model.UserApplyInfoVO;
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
import com.hzcf.platform.mgr.sys.util.ConstantsDictionary;
import com.hzcf.platform.mgr.sys.util.CustomerUtils;
import com.hzcf.platform.mgr.sys.common.util.DictBase;

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
	 * 通过applyId查询user-apply-info表，获取用户信息（借款需求信息）
	 * 转码：需求信息（大类loanPurposeOne；小类loanPurposeTwo;期数period）
	 */
	@Override
	public UserApplyInfoVO getUserApplyInfoDetail(String applyId) {
		
		Result<UserApplyInfoVO> userApplyInfoVO = userApplyInfoService.selectByApplyId(applyId);
		//需求大类
		String loanPurposeOne = userApplyInfoVO.getItems().getLoanPurposeOne();
		String loanPurposeOneValue = dictUtilService.convertLoanPurposeOne(loanPurposeOne);
		userApplyInfoVO.getItems().setLoanPurposeOne(loanPurposeOneValue);
		
		//需求小类
		String loanPurposeTwo = userApplyInfoVO.getItems().getLoanPurposeTwo();
		String loanPurposeTwoValue = dictUtilService.convertLoanPurposeTwo(loanPurposeOne,loanPurposeTwo);
		userApplyInfoVO.getItems().setLoanPurposeTwo(loanPurposeTwoValue);
		
		//期数
		String period = userApplyInfoVO.getItems().getPeriod();
		String periodValue = dictUtilService.convertDict(DictBase.PERIOD_NUM,period);
		userApplyInfoVO.getItems().setPeriod(periodValue);
		
		return userApplyInfoVO.getItems();
	}

	/**
	 * 通过applyId查询users表，获取用户信息（出生日期birthday、性别gender）
	 * 根据idcard获取出生日期(Date birthday)
	 * 根据idcard获取性别(String gender)
	 */
	@Override
	public UserVO getUserDetail(String mobile) {

		Result<UserVO> userVO = userService.getByMobile(mobile);
		
		String idcard = userVO.getItems().getIdCard();
		if(CustomerUtils.isValidIdentityNo(idcard) != -1) {
			//生日
			Date birthday = CustomerUtils.calculateBirthDate(idcard);
			userVO.getItems().setBirthday(birthday);
			//性别
			String gender = CustomerUtils.calculateGender(idcard);
			if (gender == "0") {
				userVO.getItems().setGender("女");
			}
			if (gender == "1") {
				userVO.getItems().setGender("男");
			}
		}
		
		return userVO.getItems();
		
	}
	
	/**
	 * 通过applyId查询user-info表，获取用户信息（个人资料信息、工作信息、其它）
	 * 转码：
	 * 个人信息（学历；婚姻状况；有无子女；房产情况；户籍所在省、市、邮编；家庭所在省、市、邮编、电话区号；共同居住者）、
	 * 工作信息（单位性质；单位所在省、市、邮编、电话区号）、
	 * 其他（贷款类型；网内/网外）
	 */
	@Override
	public UserInfoVO getUserInfoDetail(String applyId) {

		Result<UserInfoVO> userInfoVO = userInfoService.selectByApplyId(applyId);
		if (userInfoVO.getItems() == null) {
			return userInfoVO.getItems();
		} else {
			
			//学历education
			String education = userInfoVO.getItems().getEducation();
			String educationValue = dictUtilService.convertDict(DictBase.EDUCATION, education);
			userInfoVO.getItems().setEducation(educationValue);
			
			//婚姻状况marriageStatus
			String marriageStatus = userInfoVO.getItems().getMarriageStatus();
			String marriageStatusValue = dictUtilService.convertDict(DictBase.MARRIAGE_STATUS, marriageStatus);
			userInfoVO.getItems().setMarriageStatus(marriageStatusValue);
			
			//有无子女childrenStatus
			String childrenStatus = userInfoVO.getItems().getChildrenStatus();
			String childrenStatusValue = dictUtilService.convertDict(DictBase.CHILDRE_STATUS, childrenStatus);
			userInfoVO.getItems().setChildrenStatus(childrenStatusValue);
			
			//房产情况houseStatus
			String houseStatus = userInfoVO.getItems().getHouseStatus();
			String houseStatusValue = dictUtilService.convertDict(DictBase.HOUSE_STATUS, houseStatus);
			userInfoVO.getItems().setHouseStatus(houseStatusValue);

			//共同居住者liveTogether
			String liveTogether = userInfoVO.getItems().getLiveTogether();
			String liveTogetherValue = dictUtilService.convertDict(DictBase.LIVE_TOGETHER, liveTogether);
			userInfoVO.getItems().setLiveTogether(liveTogetherValue);

			//户籍所在省domicileProvince
			String domicileProvince = userInfoVO.getItems().getDomicileProvince();
			String domicileProvinceValue = dictUtilService.convertProvince(domicileProvince);
			userInfoVO.getItems().setDomicileProvince(domicileProvinceValue);
			
			//户籍所在市
			String domicileCity = userInfoVO.getItems().getDomicileCity();
			String domicileCityValue = dictUtilService.convertCity(domicileProvince, domicileCity);
			userInfoVO.getItems().setDomicileCity(domicileCityValue);
			
			//户籍邮政编码domicilePostCode
			String domicilePostCodeValue = dictUtilService.convertCityBean(domicileProvince, domicileCity).getPostcode();
			userInfoVO.getItems().setDomicilePostCode(domicilePostCodeValue);
			
			//家庭所在省residentProvince
			String residentProvince = userInfoVO.getItems().getResidentProvince();
			String residentProvinceValue = dictUtilService.convertProvince(residentProvince);
			userInfoVO.getItems().setResidentProvince(residentProvinceValue);
			
			//家庭所在市residentCity
			String residentCity = userInfoVO.getItems().getResidentCity();
			String residentCityValue = dictUtilService.convertCity(residentProvince, residentCity);
			userInfoVO.getItems().setResidentCity(residentCityValue);

			//家庭邮政编码residentPostCode
			String residentPostValue = dictUtilService.convertCityBean(residentProvince, residentCity).getPostcode();
			userInfoVO.getItems().setResidentPostCode(residentPostValue);

			//家庭电话区号residentTelAreaCode
			String residentTelAreaValue = dictUtilService.convertCityBean(residentProvince, residentCity).getAreacode();
			userInfoVO.getItems().setResidentTelAreaCode(residentTelAreaValue);

			//单位性质orgType
			String orgType = userInfoVO.getItems().getOrgType();
			String orgTypeValue = dictUtilService.convertDict(DictBase.ORG_TYPE, orgType);
			userInfoVO.getItems().setOrgType(orgTypeValue);
			
			//单位所在省orgProvince
			String orgProvince = userInfoVO.getItems().getOrgProvince();
			String orgProvinceValue = dictUtilService.convertProvince(orgProvince);
			userInfoVO.getItems().setOrgProvince(orgProvinceValue);

			//单位所在市orgCity
			String orgCity = userInfoVO.getItems().getOrgCity();
			String orgCityValue = dictUtilService.convertCity(orgProvince, orgCity);
			userInfoVO.getItems().setOrgCity(orgCityValue);

			//单位邮政编码orgPostCode
			String orgPostCodeValue = dictUtilService.convertCityBean(orgProvince, orgCity).getPostcode();
			userInfoVO.getItems().setOrgPostCode(orgPostCodeValue);

			//单位电话区号orgTelAreaCode
			String orgTelAreaValue = dictUtilService.convertCityBean(orgProvince, orgCity).getAreacode();
			userInfoVO.getItems().setOrgTelAreaCode(orgTelAreaValue);
		}

		return userInfoVO.getItems();
	}

	/**
	 * 通过applyId查询user-relation表，获取用户信息（联系人信息）
	 * Type：0=工作证明人		 1=家庭证明人		2=紧急联络人
	 * 转码：联系人与本人关系（raletionType）
	 */
	@Override
	public List<UserRelationVO> getUserRelationDetail(String applyId) {
		
		Result<List<UserRelationVO>> userRelationList = userRelationService.selectByApplyId(applyId);
		
		List<UserRelationVO> userRelationVOList = userRelationList.getItems();
		if (userRelationVOList == null){
			return userRelationVOList;
		} else {
			for(UserRelationVO userRelationVO:userRelationVOList){
				userRelationVO.setRelationType(dictUtilService.convertRelationTwo(userRelationVO.getType(), userRelationVO.getRelationType()));
				//userRelationVO.setType(dictUtilService.convertRelationOne(userRelationVO.getType()));
		}
	}

		return userRelationVOList;
	}

	/**
	 * 通过applyId查询user-image表，获取用户信息（上传图片展示）
	 * Type：0=身份证		 1=征信报告		2=个人住址证明		3=收入证明		4=实名认证图片		 5=工作证明		6=社保/公积金		7=其它
	 */
	@Override
	public List<UserImageVO> getUserImageDetail(String applyId) {
		
		Result<List<UserImageVO>> userImageList = userImageService.selectByApplyId(applyId);
		List<UserImageVO> userImageVOList = userImageList.getItems();
		if (userImageVOList == null){
			return userImageVOList;
		} else {
			for(UserImageVO vo : userImageVOList){
				vo.setArtWork(this.geturl(vo.getArtWork()));
			}
		}
		
		return userImageVOList;
	}
	
	public String geturl(String url){
		return ConstantsDictionary.imgUpload+"/"+url;
	}
}
