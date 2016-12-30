package com.hzcf.platform.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.config.BaseConfig;
import com.hzcf.platform.api.service.IRealNameService;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.core.HzdStatusCodeEnum;
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
		//根据借款人的手机号查询用户信息
		Result<UserVO> byMobile=userSerivce.getByMobile(user.getMobile());
        UserVO items=byMobile.getItems();
        //判断借款人的实名状态
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
		//根据借款人的手机号查询借款人信息
		Result<UserVO> byMobile = userSerivce.getByMobile(user.getMobile());
        UserVO items=byMobile.getItems();
        //更新借款人的实名状态
        Result<Boolean> updateResult=userSerivce.updateMobile(items);
        //判断更新操作结果，设置返回结果
        if(StatusCodes.OK==updateResult.getStatus()){//更新借款人实名认证信息成功
        	return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),HzdStatusCodeEnum.MEF_CODE_0000.getMsg(),items);//返回“保存成功”，用户的实名认证信息
        }else{
        	return new BackResult(HzdStatusCodeEnum.MEF_CODE_1031.getCode(),HzdStatusCodeEnum.MEF_CODE_1031.getMsg(),null);//返回“保存失败”，null
        }
	}
	/**保存借款人上传的图片信息
	 * 
	 */
	@Override
	public BackResult saveRealNamePic(UserVO user) {
		return null;
	}
}