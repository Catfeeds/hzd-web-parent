package com.hzcf.platform.api.common;

import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.core.user.model.UserInfoVO;
import com.hzcf.platform.core.user.model.UserRelationVO;
import com.hzcf.platform.core.user.model.UserVO;
import org.apache.commons.lang3.StringUtils;

import com.hzcf.platform.common.exception.CheckException;
import com.hzcf.platform.common.util.utils.JudgeNumberLegal;
import com.hzcf.platform.common.util.utils.ServiceUtil;

import java.util.List;

/**
 * 
 * @description:数据校验
 * @author 雷佳明
 * @version 1.0
 * 
 * <pre>
 * Modification History: 
 * Date              Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年12月13日                       雷佳明                           1.0       1.0 Version 
 * </pre>
 */
public class DataVerifcation {
	 public DataVerifcation() {
	    }

	    public static void datavVerification( String phone_num, String idCard, String area, String name, String sms, String psw, String id) throws CheckException {
	        if (StringUtils.isBlank(phone_num) || !JudgeNumberLegal.isMobileNum(phone_num)) {
	            throw new CheckException("手机号码输入不合法");
	        }  else if (StringUtils.isBlank(sms) || !JudgeNumberLegal.isCodeNum(sms)) {
	            throw new CheckException("验证码输入不合法");
	        } else if (StringUtils.isBlank(psw)) {
	            throw new CheckException("密码输入有误");
	        }
	    }
	    
	    public static void datavVerification( String phone_num, String sms) throws CheckException {
	        if (StringUtils.isBlank(phone_num) || !JudgeNumberLegal.isMobileNum(phone_num)) {
	            throw new CheckException("手机号码输入不合法");
	        }  else if (StringUtils.isBlank(sms)) {
	            throw new CheckException("验证码输入有误");
	        }
	    }

	public static void datavVerification( String phone_num) throws CheckException {
		if (StringUtils.isBlank(phone_num) || !JudgeNumberLegal.isMobileNum(phone_num)) {
			throw new CheckException("手机号码输入不合法");
		}
	}
	    
	    public static void datavVerification( String phone_num, String idCard, String area, String name) throws CheckException {
	        if (StringUtils.isBlank(phone_num) || !JudgeNumberLegal.isMobileNum(phone_num)) {
	            throw new CheckException("手机号码输入不合法");
	        } else if (StringUtils.isBlank(idCard) || !ServiceUtil.validateIdNo(idCard)) {
	            throw new CheckException("身份证号码输入不合法");
	        } else if (StringUtils.isBlank(name) || !JudgeNumberLegal.isNameString(name)) {
	            throw new CheckException("用户名输入不合法");
	        } 
	    }


	    public static void checkUserApplyInfoVO(UserApplyInfoVO userApplyInfoVO,UserVO userVO) throws CheckException  {

			if (StringUtils.isBlank(userApplyInfoVO.getLoanPurposeOne()) ) {
				throw new CheckException("借款用途大类输入为空");
			}else if (StringUtils.isBlank(userVO.getId())){
				throw new CheckException("用户ID不能为空");
			}else if (StringUtils.isBlank(userApplyInfoVO.getLoanPurposeTwo())){
				throw new CheckException("借款用途小类输入为空");
			}else if (userApplyInfoVO.getMinApplyAmount()==null){
				throw new CheckException("申请最低额度输入为空");
			}else if (userApplyInfoVO.getMaxApplyAmount()==null){
				throw new CheckException("申请最高额度输入为空");
			}else if (userApplyInfoVO.getMaxMonthlyPayment()==null ){
				throw new CheckException("可接受最高月还款额输入为空");
			}else if (StringUtils.isBlank(userApplyInfoVO.getPeriod()) ){
				throw new CheckException("期数输入为空");
			}


		}

		public static void checkUserInfoVOTwo(UserInfoVO userInfoVO, UserVO userVO) throws CheckException{

			if (StringUtils.isBlank(userVO.getId()) ) {
				throw new CheckException("用户UserID为空");
			}else if (StringUtils.isBlank(userInfoVO.getIdcardValidity())){
				throw new CheckException("证件有效期不能为空");
			}else if (StringUtils.isBlank(userInfoVO.getEducation())){
				throw new CheckException("最高学历不能为空");
			}else if (StringUtils.isBlank(userInfoVO.getDomicileProvince())){
				throw new CheckException("户籍所在省不能为空");
			}else if (StringUtils.isBlank(userInfoVO.getDomicileCity())){
				throw new CheckException("户籍所在市不能为空");
			}else if (StringUtils.isBlank(userInfoVO.getDomicileAddress())){
				throw new CheckException("户籍详细地址不能为空");
			}else if (StringUtils.isBlank(userInfoVO.getMarriageStatus())){
				throw new CheckException("婚姻状况不能为空");
			}else if (StringUtils.isBlank(userInfoVO.getChildrenStatus())){
				throw new CheckException("有无子女不能为空");
			}else if (StringUtils.isBlank(userInfoVO.getHouseStatus())){
				throw new CheckException("房产情况不能为空");
			}else if (StringUtils.isBlank(userInfoVO.getResidentProvince())){
				throw new CheckException("家庭所在省不能为空");
			}else if (StringUtils.isBlank(userInfoVO.getResidentCity())){
				throw new CheckException("家庭所在市不能为空");
			}else if (StringUtils.isBlank(userInfoVO.getResidentAddress())){
				throw new CheckException("家庭详细地址不能为空");
			}else if (StringUtils.isBlank(userInfoVO.getResidentTelCode())){
				throw new CheckException("家庭电话号码不能为空");
			}else if (userInfoVO.getResidentTelCode().length()!=8){
				throw new CheckException("家庭电话号码长度不符");
			}else if (StringUtils.isBlank(userInfoVO.getEmail())){
				throw new CheckException("电子邮件地址不能为空");
			}else if (userInfoVO.getAnnualIncome()==null){
				throw new CheckException("个人年收入不能为空");
			}else if (userInfoVO.getCreditCardLimit()==null){
				throw new CheckException("信用卡最高额度不能为空");
			}else if (StringUtils.isBlank(userInfoVO.getLiveTogether())){
				throw new CheckException("共同居住者不能为空");
			}/*else if (StringUtils.isBlank(userInfoVO.getStaffNo())){
				throw new CheckException("员工编号不能为空");
			}*/


		}

	public static void checkUserInfoVOThree(UserInfoVO userInfoVO, UserVO userVO) throws CheckException{

		if (StringUtils.isBlank(userVO.getId()) ) {
			throw new CheckException("用户UserID为空");
		}else if (StringUtils.isBlank(userInfoVO.getOrgName())){
			throw new CheckException("单位名称不能为空");
		}else if (StringUtils.isBlank(userInfoVO.getOrgType())){
			throw new CheckException("单位性质不能为空");
		}else if (StringUtils.isBlank(userInfoVO.getOrgProvince())){
			throw new CheckException("单位所在省不能为空");
		}else if (StringUtils.isBlank(userInfoVO.getOrgCity())){
			throw new CheckException("单位所在市不能为空");
		}else if (StringUtils.isBlank(userInfoVO.getOrgAddress())){
			throw new CheckException("单位详细地址不能为空");
		}else if (StringUtils.isBlank(userInfoVO.getOrgTelCode())){
			throw new CheckException("单位电话号码不能为空");
		}else if (userInfoVO.getOrgTelCode().length()!=8){
			throw new CheckException("单位电话号码长度不符");
		}



	}

	public static  void checkUserRelationVO(UserVO user,  List<UserRelationVO> userRelationVO) throws  CheckException{

		if (StringUtils.isBlank(user.getId()) ) {
			throw new CheckException("用户UserID为空");
		}else if (userRelationVO==null){
			throw new CheckException("请填写联系人信息");
		}

	}
}
