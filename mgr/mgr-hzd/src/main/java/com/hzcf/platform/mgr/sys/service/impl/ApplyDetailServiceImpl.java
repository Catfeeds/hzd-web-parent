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
		if (loanPurposeOne == null) {
			userApplyInfoVO.getItems().setLoanPurposeOne("数据为空");
		} else if (loanPurposeOneValue == "") {
			userApplyInfoVO.getItems().setLoanPurposeOne("数据非法");
		} else {
			userApplyInfoVO.getItems().setLoanPurposeOne(loanPurposeOneValue);
		}
		//需求小类
		String loanPurposeTwo = userApplyInfoVO.getItems().getLoanPurposeTwo();
		String loanPurposeTwoValue = dictUtilService.convertLoanPurposeTwo(loanPurposeOne,loanPurposeTwo);
		if (loanPurposeTwo == null) {
			userApplyInfoVO.getItems().setLoanPurposeTwo("数据为空");
		} else if (loanPurposeTwoValue == "") {
			userApplyInfoVO.getItems().setLoanPurposeTwo("数据非法");
		} else {
			userApplyInfoVO.getItems().setLoanPurposeTwo(loanPurposeTwoValue);
		}
		//期数
		String period = userApplyInfoVO.getItems().getPeriod();
		String periodValue = dictUtilService.convertDict(DictBase.PERIOD_NUM,period);
		if (period == null) {
			userApplyInfoVO.getItems().setPeriod("数据为空");
		} else if (periodValue == "") {
			userApplyInfoVO.getItems().setPeriod("数据非法");
		} else {
			userApplyInfoVO.getItems().setPeriod(periodValue);
		}
		
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
		
		//学历education
		String education = userInfoVO.getItems().getEducation();
		String educationValue = dictUtilService.convertDict(DictBase.EDUCATION, education);
		if (education == null) {
			userInfoVO.getItems().setEducation("数据为空");
		} else if (educationValue == "") {
			userInfoVO.getItems().setEducation("数据非法");
		} else {
			userInfoVO.getItems().setEducation(educationValue);
		}
		//婚姻状况marriageStatus
		String marriageStatus = userInfoVO.getItems().getMarriageStatus();
		String marriageStatusValue = dictUtilService.convertDict(DictBase.MARRIAGE_STATUS, marriageStatus);
		if (marriageStatus == null) {
			userInfoVO.getItems().setMarriageStatus("数据为空");
		} else if (marriageStatusValue == null) {
			userInfoVO.getItems().setMarriageStatus("数据非法");
		} else {
			userInfoVO.getItems().setMarriageStatus(marriageStatusValue);
		}
		//有无子女childrenStatus
		String childrenStatus = userInfoVO.getItems().getChildrenStatus();
		String childrenStatusValue = dictUtilService.convertDict(DictBase.CHILDRE_STATUS, childrenStatus);
		if (childrenStatus == null) {
			userInfoVO.getItems().setChildrenStatus("数据为空");
		} else if (childrenStatusValue == "") {
			userInfoVO.getItems().setChildrenStatus("数据非法");
		} else {
			userInfoVO.getItems().setChildrenStatus(childrenStatusValue);
		}
		//房产情况houseStatus
		String houseStatus = userInfoVO.getItems().getHouseStatus();
		String houseStatusValue = dictUtilService.convertDict(DictBase.HOUSE_STATUS, houseStatus);
		if (houseStatus == null) {
			userInfoVO.getItems().setHouseStatus("数据为空");
		} else if (houseStatusValue == "") {
			userInfoVO.getItems().setHouseStatus("数据非法");
		} else {
			userInfoVO.getItems().setHouseStatus(houseStatusValue);
		}
		//户籍所在省domicileProvince
		String domicileProvince = userInfoVO.getItems().getDomicileProvince();
		String domicileProvinceValue = dictUtilService.convertProvince(domicileProvince);
		if (domicileProvince == null) {
			userInfoVO.getItems().setDomicileProvince("数据为空");
		} else if (domicileProvinceValue == "") {
			userInfoVO.getItems().setDomicileProvince("数据非法");
		} else {
			userInfoVO.getItems().setDomicileProvince(domicileProvinceValue);
		}
		//户籍所在市
		String domicileCity = userInfoVO.getItems().getDomicileCity();
		String domicileCityValue = dictUtilService.convertCity(domicileProvince, domicileCity);
		if (domicileCity == null) {
			userInfoVO.getItems().setDomicileCity("数据为空");
		} else if (domicileCityValue == "") {
			userInfoVO.getItems().setDomicileCity("数据非法");
		} else {
			userInfoVO.getItems().setDomicileCity(domicileCityValue);
		}
		//户籍邮政编码domicilePostCode
		String domicilePostCode = userInfoVO.getItems().getDomicilePostCode();
		String domicilePostCodeValue = dictUtilService.convertCityBean(domicileProvince, domicileCity).getPostcode();
		if (domicilePostCode == null){
			userInfoVO.getItems().setDomicilePostCode("数据为空");
		} else if (domicilePostCodeValue == ""){
			userInfoVO.getItems().setDomicilePostCode("数据非法");
		} else {
			userInfoVO.getItems().setDomicilePostCode(domicilePostCodeValue);
		}
		//家庭所在省residentProvince
		String residentProvince = userInfoVO.getItems().getResidentProvince();
		String residentProvinceValue = dictUtilService.convertProvince(residentProvince);
		if (residentProvince == null) {
			userInfoVO.getItems().setResidentProvince("数据为空");
		} else if (residentProvinceValue == "") {
			userInfoVO.getItems().setResidentProvince("数据非法");
		} else {
			userInfoVO.getItems().setResidentProvince(residentProvinceValue);
		}
		//家庭所在市residentCity
		String residentCity = userInfoVO.getItems().getResidentCity();
		String residentCityValue = dictUtilService.convertCity(residentProvince, residentCity);
		if (residentCity == null) {
			userInfoVO.getItems().setResidentCity("数据为空");
		} else if (residentCityValue == "") {
			userInfoVO.getItems().setResidentCity("数据非法");
		} else {
			userInfoVO.getItems().setResidentCity(residentCityValue);
		}
		//家庭邮政编码residentPostCode
		String residentPostCode = userInfoVO.getItems().getResidentPostCode();
		String residentPostValue = dictUtilService.convertCityBean(residentProvince, residentCity).getPostcode();
		if (residentPostCode == null) {
			userInfoVO.getItems().setResidentPostCode("数据为空");
		} else if (residentPostValue == "") {
			userInfoVO.getItems().setResidentPostCode("数据非法");
		} else {
			userInfoVO.getItems().setResidentPostCode(residentPostValue);
		}
		//家庭电话区号residentTelAreaCode
		String residentTelAreaCode = userInfoVO.getItems().getResidentTelAreaCode();
		String residentTelAreaValue = dictUtilService.convertCityBean(residentProvince, residentCity).getAreacode();
		if (residentTelAreaCode == null) {
			userInfoVO.getItems().setResidentTelAreaCode("数据为空");
		} else if (residentTelAreaValue == "") {
			userInfoVO.getItems().setResidentTelAreaCode("数据非法");
		} else {
			userInfoVO.getItems().setResidentTelAreaCode(residentTelAreaValue);
		}
		//共同居住者liveTogether
		String liveTogether = userInfoVO.getItems().getLiveTogether();
		String liveTogetherValue = dictUtilService.convertDict(DictBase.LIVE_TOGETHER, liveTogether);
		if (liveTogether == null) {
			userInfoVO.getItems().setLiveTogether("数据为空");
		} else if (liveTogetherValue == "") {
			userInfoVO.getItems().setLiveTogether("数据非法");
		} else {
			userInfoVO.getItems().setLiveTogether(liveTogetherValue);
		}
		//单位性质orgType
		String orgType = userInfoVO.getItems().getOrgType();
		String orgTypeValue = dictUtilService.convertDict(DictBase.ORG_TYPE, orgType);
		if (orgType == null) {
			userInfoVO.getItems().setOrgType("数据为空");
		} else if (orgTypeValue == "") {
			userInfoVO.getItems().setOrgType("数据非法");
		} else {
			userInfoVO.getItems().setOrgType(orgTypeValue);
		}
		//单位所在省orgProvince
		String orgProvince = userInfoVO.getItems().getOrgProvince();
		String orgProvinceValue = dictUtilService.convertProvince(orgProvince);
		if (orgProvince == null) {
			userInfoVO.getItems().setOrgProvince("数据为空");
		} else if (orgProvinceValue == "") {
			userInfoVO.getItems().setOrgProvince("数据非法");
		} else {
			userInfoVO.getItems().setOrgProvince(orgProvinceValue);
		}
		//单位所在市orgCity
		String orgCity = userInfoVO.getItems().getOrgCity();
		String orgCityValue = dictUtilService.convertCity(orgProvince, orgCity);
		if (orgCity == null) {
			userInfoVO.getItems().setOrgCity("数据为空");
		} else if (orgCityValue == "") {
			userInfoVO.getItems().setOrgCity("数据非法");
		} else {
			userInfoVO.getItems().setOrgCity(orgCityValue);
		}
		//单位邮政编码orgPostCode
		String orgPostCode = userInfoVO.getItems().getOrgPostCode();
		String orgPostCodeValue = dictUtilService.convertCityBean(orgProvince, orgCity).getPostcode();
		if (orgPostCode == null) {
			userInfoVO.getItems().setOrgPostCode("数据为空");
		} else if (orgPostCodeValue == "") {
			userInfoVO.getItems().setOrgPostCode("数据非法");
		} else {
			userInfoVO.getItems().setOrgPostCode(orgPostCodeValue);
		}
		//单位电话区号orgTelAreaCode
		String orgTelAreaCode = userInfoVO.getItems().getOrgTelAreaCode();
		String orgTelAreaValue = dictUtilService.convertCityBean(orgProvince, orgCity).getAreacode();
		if (orgTelAreaCode == null) {
			userInfoVO.getItems().setOrgTelAreaCode("数据为空");
		} else if (orgTelAreaValue == "") {
			userInfoVO.getItems().setOrgTelAreaCode("数据非法");
		} else {
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
		
		for(UserRelationVO userRelationVO:userRelationVOList){
			userRelationVO.setRelationType(dictUtilService.convertRelationTwo(userRelationVO.getType(), userRelationVO.getRelationType()));
			//userRelationVO.setType(dictUtilService.convertRelationOne(userRelationVO.getType()));
			
			/*String relationTypeCode = userRelationVO.getRelationType();
			String relationTypeValue = dictUtilService.convertDict(DictBase.RELATION_TO_APPLYER, relationTypeCode);
			if (relationTypeCode == null) {
				userRelationVO.setRelationType("数据为空");
			} else if (relationTypeValue == "") {
				userRelationVO.setRelationType("数据非法");
			} else {
				userRelationVO.setRelationType(relationTypeValue);
			}*/
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
		
		for(UserImageVO vo : userImageVOList){
			vo.setArtWork(this.geturl(vo.getArtWork()));
		}
		
		
		/*for (UserImageVO userImageVO : userImageVOList) {
			String imageType = userImageVO.getType();
			Result<List<ArtWorkVO>> artWorkList = userImageService.selectByImageType(imageType);
			List<ArtWorkVO> artWorkVOList = artWorkList.getItems();
			for (ArtWorkVO artWorkVO : artWorkVOList) {
				if(artWorkVOList.size()==1){
					artWorkVO.setArtWorkA(this.geturl(artWorkVOList.get(0).getArtWork()));
					artWorkVO.setImgIdA(artWorkVOList.get(0).getImageId());
				}
				if(artWorkVOList.size()==2){
					artWorkVO.setArtWorkA(this.geturl(artWorkVOList.get(0).getArtWork()));
					artWorkVO.setArtWorkB(this.geturl(artWorkVOList.get(1).getArtWork()));
					artWorkVO.setImgIdA(artWorkVOList.get(0).getImageId());
					artWorkVO.setImgIdA(artWorkVOList.get(1).getImageId());
				}
				if(artWorkVOList.size()>=3){
					artWorkVO.setArtWorkA(this.geturl(artWorkVOList.get(0).getArtWork()));
					artWorkVO.setArtWorkB(this.geturl(artWorkVOList.get(1).getArtWork()));
					artWorkVO.setArtWorkC(this.geturl(artWorkVOList.get(2).getArtWork()));
					artWorkVO.setImgIdA(artWorkVOList.get(0).getImageId());
					artWorkVO.setImgIdA(artWorkVOList.get(1).getImageId());
					artWorkVO.setImgIdA(artWorkVOList.get(2).getImageId());
				}
			}
			
		}*/
		
		
		return userImageVOList;
	}
	
	public String geturl(String url){
		return ConstantsDictionary.imgUpload+"/"+url;
	}
}
