package com.hzcf.platform.core;

import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.core.user.model.UserVO;
import org.apache.commons.lang3.StringUtils;

import com.hzcf.platform.common.exception.CheckException;
import com.hzcf.platform.common.util.utils.JudgeNumberLegal;
import com.hzcf.platform.common.util.utils.ServiceUtil;

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
			}


		}
}
