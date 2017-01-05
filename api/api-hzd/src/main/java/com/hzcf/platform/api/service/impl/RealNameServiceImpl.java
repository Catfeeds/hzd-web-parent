package com.hzcf.platform.api.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.config.BaseConfig;
import com.hzcf.platform.api.service.IRealNameService;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.common.util.utils.JudgeNumberLegal;
import com.hzcf.platform.common.util.utils.ServiceUtil;
import com.hzcf.platform.api.baseEnum.HzdStatusCodeEnum;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.core.user.service.UserService;

/**
  * @Description:实名认证的操作
  * @author 作者:裴高祥 E-mail:lettger@163.com Tel:13241706779
  * @date 创建时间：2016年12月29日 下午3:50:16 
  * @version 1.0 
  * @since  JDK1.7
  */
@Service
public class RealNameServiceImpl implements IRealNameService {
    @Autowired
    public UserService userSerivce;//借款人service
    /**查询借款人的实名认证信息，状态
     * 
     * 
     */
	@Override
	public BackResult selectRealName(UserVO user) {
		/**初始化参数：根据借款人的手机号查询用户信息*/
		Result<UserVO> byMobile=userSerivce.getByMobile(user.getMobile());
        UserVO items=byMobile.getItems();
        /**判断借款人的实名状态，设置返回结果*/
        if(BaseConfig.card_status_0.equals(items.getCheckStatus())){//身份证有效，借款人已经实名认证
        	return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),HzdStatusCodeEnum.MEF_CODE_0000.getMsg(),items);//返回“查询成功”，借款人的实名认证信息
        }else{//身份证无效，借款人未实名认证，对应的状态：BaseConfig.card_status_1.equals(items.getCheckStatus())
        	return new BackResult(HzdStatusCodeEnum.MEF_CODE_1030.getCode(),HzdStatusCodeEnum.MEF_CODE_1030.getMsg(),null);//返回“查询失败”，null
        }
	}
	/**保存借款人的实名认证信息
	 * 
	 */
	@Override
	public BackResult saveRealName(UserVO user) {
		/**初始化参数：根据借款人的手机号查询借款人信息,该信息包含“实名认证信息”*/
		Result<UserVO> byMobile = userSerivce.getByMobile(user.getMobile());
        UserVO items=byMobile.getItems();
        /**验证实名认证信息是否符合要求*/
        String realName=items.getName();//借款人的姓名
        String idCard=items.getIdCard();//借款人的身份证号码
        /*第一步验证：验证实名认证信息是否符合要求
         *1、“姓名”“身份证号”是否符合正则表达式的要求
         *2、“姓名”“身份证号”是否真实存在，是否对应（第2点暂时不做）
         */
        if(StringUtils.isBlank(realName) || !JudgeNumberLegal.isNameString(realName)){
        	//返回“保存失败”，用户的“真实姓名”不符合要求，null
        	return new BackResult(HzdStatusCodeEnum.MEF_CODE_1031.getCode(),HzdStatusCodeEnum.MEF_CODE_1031.getMsg(),null);
        }
        if(StringUtils.isBlank(idCard) || !ServiceUtil.validateIdNo(idCard)){
        	//返回“保存失败”，用户的“身份证号码”不符合要求，null
        	return new BackResult(HzdStatusCodeEnum.MEF_CODE_1032.getCode(),HzdStatusCodeEnum.MEF_CODE_1032.getMsg(),null);
        }
        /*第二步验证：验证实名认证信息是否已经存在，
         *存在：该身份信息已经使用，实名认证失败
         *不存在：该身份信息没有使用，实名认证符合要求
         *请求数据：
			格式：Map<String,Object>
			参数：name，idCard
		返回数据：
			格式：Map<String,Object>
			参数：realnamerepeat（真实姓名重复的数量），idcardrepeat（身份证号码重复的数量）,allrepeat（真实姓名和身份证号码总共的重复数量）
         */
        Map<String,Object> paramsMap=new HashMap<String,Object>();//初始化Map
        paramsMap.put("name", realName);//存储“真实姓名”
        paramsMap.put("idCard", idCard);//存储“身份证号”
        Map<String,Object> resultMap=userSerivce.selectNameAndIdCardRepeat(paramsMap);//查询“真实姓名”，“身份证号”重复的数量
        int realnamerepeat=(Integer) resultMap.get("realnamerepeat");//“真实姓名”重复的数量
        int idcardrepeat=(Integer) resultMap.get("idcardrepeat");//“身份证号”重复的数量
        if(realnamerepeat>0){
        	//返回“保存失败”，“真实姓名”重复，null
        	return new BackResult(HzdStatusCodeEnum.MEF_CODE_1033.getCode(),HzdStatusCodeEnum.MEF_CODE_1033.getMsg(),null);
        }
        if(idcardrepeat>0){
        	//返回“保存失败”，“身份证号码”重复，null
        	return new BackResult(HzdStatusCodeEnum.MEF_CODE_1034.getCode(),HzdStatusCodeEnum.MEF_CODE_1034.getMsg(),null);
        }
        /**更新借款人的实名状态*/
        Result<Boolean> updateResult=userSerivce.updateMobile(items);
        /**判断更新操作结果，设置返回结果*/
        if(StatusCodes.OK==updateResult.getStatus()){//更新借款人实名认证信息成功
        	return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),HzdStatusCodeEnum.MEF_CODE_0000.getMsg(),items);//返回“保存成功”，用户的实名认证信息
        }else{
        	return new BackResult(HzdStatusCodeEnum.MEF_CODE_1035.getCode(),HzdStatusCodeEnum.MEF_CODE_1035.getMsg(),null);//返回“保存失败”，null
        }
	}
	/**保存借款人上传的图片信息
	 * 需要2个参数：借款人信息，实名认证的图片信息
	 */
	@Override
	public BackResult saveRealNamePic(UserVO user) {
		
		
		return null;
	}
}