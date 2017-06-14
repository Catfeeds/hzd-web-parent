package com.hzcf.platform.api.service.impl;

import com.hzcf.platform.api.annotation.LogAnnotation;
import com.hzcf.platform.api.baseEnum.HzdStatusCodeEnum;
import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.service.IMsgBoxService;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.core.user.model.MsgBoxVO;
import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.core.user.service.MsgBoxservice;
import com.hzcf.platform.core.user.service.UserApplyInfoSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @Description:站内信service
  * @author zhangmx
  */
@Service
public class MsgBoxServiceImpl implements IMsgBoxService {
	private static final Log logger = Log.getLogger(MsgBoxServiceImpl.class);
	
	@Autowired
	private MsgBoxservice msgBoxservice;

	@Autowired
	private UserApplyInfoSerivce userApplyInfoSerivce;

	/**
	 * 查询未读数量
	 */
	@Override
	@LogAnnotation
	public BackResult selectUnReadNum(UserVO user,MsgBoxVO msgBoxVO) {
		Map<String,Object> map = new HashMap<String,Object>();

		if(msgBoxVO != null){
			msgBoxVO.setUserId(user.getId());
			Result<Integer> result = this.msgBoxservice.selectUnReadNum(msgBoxVO);
			if (StatusCodes.OK != (result.getStatus())) {
				logger.i("数据查询失败 - 500,  失败 。。。。。。。。。。。。。 ");
				return new BackResult(HzdStatusCodeEnum.HZD_CODE_0001.getCode(),
						HzdStatusCodeEnum.HZD_CODE_0001.getMsg());
			}
			map.put("number",result.getItems());
			logger.i("用户  查询未读数量 。。。。。。 成功。。。。。 ");
			return new BackResult(HzdStatusCodeEnum.HZD_CODE_0000.getCode(), HzdStatusCodeEnum.HZD_CODE_0000.getMsg(), map);
		}else{
			logger.i("用户  查询未读数量 。。。。。。 失败。。。。。 ");
			return new BackResult(HzdStatusCodeEnum.HZD_CODE_2100.getCode(), HzdStatusCodeEnum.HZD_CODE_2100.getMsg(), null);
		}
	}

	/**
	 * 查询站内信列表
	 */

	@Override
	@LogAnnotation
	public BackResult selectAllByUser(UserVO user, MsgBoxVO msgBoxVO) {
		String applyId= "";
		String additionalStatus ="1";//默认已补充
		if(msgBoxVO != null){
			msgBoxVO.setUserId(user.getId());
			Result<List<MsgBoxVO>> listResult = msgBoxservice.selectAllByUser(msgBoxVO);
			Map<String,Object> map = new HashMap<String,Object>();
			Map<String,Object> resultMap = new HashMap<String,Object>();

			map.put("userId",user.getId());
			map.put("additionalStatus","0");//状态 0  为需要补充资料 (后台用,APP暂没有用)

			Result<List<UserApplyInfoVO>> listResult1 = userApplyInfoSerivce.selectByUserId(map);
			if(listResult1.getItems().size()>0){
				applyId = listResult1.getItems().get(0).getApplyId();
				additionalStatus =listResult1.getItems().get(0).getAdditionalStatus();
			}

			resultMap.put("msgBoxVO",listResult.getItems());
			resultMap.put("applyId",applyId);
			resultMap.put("additionalStatus",additionalStatus);//(后台用,APP暂没有用)
			if(StatusCodes.OK==listResult.getStatus() ){
				if(listResult.getItems().size()==0){
					logger.i("用户 查询站内信列表 。。。。。。 。。。。。查询成功， 但无数据。。。。。。。。 ");
					return new BackResult(HzdStatusCodeEnum.HZD_CODE_5100.getCode(), HzdStatusCodeEnum.HZD_CODE_5100.getMsg(),resultMap);
				}
				Result<Boolean> update_result = this.msgBoxservice.updateReadByUser(msgBoxVO);
				if(StatusCodes.OK==update_result.getStatus()){
					logger.i("用户 查询站内信列表 。。。。。。 。。。。。修改已读状态 成功。。。。。。。。 ");
					return new BackResult(HzdStatusCodeEnum.HZD_CODE_0000.getCode(), HzdStatusCodeEnum.HZD_CODE_0000.getMsg(), resultMap);
				}else{
					logger.i("用户 查询站内信列表 。。。。。。 。。。。。修改已读状态 失败。。。。。。。。 ");
					return new BackResult(HzdStatusCodeEnum.HZD_CODE_2200.getCode(), HzdStatusCodeEnum.HZD_CODE_2200.getMsg(), null);
				}
			}else{
				logger.i("用户 查询站内信列表 。。。。。。 。。。。。查询 失败 。读取数据异常 500。。。。。。。 ");
				return new BackResult(HzdStatusCodeEnum.HZD_CODE_2100.getCode(), HzdStatusCodeEnum.HZD_CODE_2100.getMsg(), null);
			}
		}else{
			logger.i("用户 查询站内信列表 。。。。。。 。。。。。查询 失败。。。传入参数为空。。。。。 ");
			return new BackResult(HzdStatusCodeEnum.HZD_CODE_2100.getCode(), HzdStatusCodeEnum.HZD_CODE_2100.getMsg(), null);
		}
		
	}
	
	
	/**
	 * 修改已读状态
	 */
	@Override
	@LogAnnotation
	public BackResult updateReadByUser( UserVO user,MsgBoxVO msgBoxVO) {
		if(msgBoxVO != null){
			msgBoxVO.setUserId(user.getId());
			Result<Boolean> result = this.msgBoxservice.updateReadByUser(msgBoxVO);
			return new BackResult(HzdStatusCodeEnum.HZD_CODE_0000.getCode(), HzdStatusCodeEnum.HZD_CODE_0000.getMsg(), result.getItems());
		}else{
			return new BackResult(HzdStatusCodeEnum.HZD_CODE_2100.getCode(), HzdStatusCodeEnum.HZD_CODE_2100.getMsg(), null);
		}
	}

}