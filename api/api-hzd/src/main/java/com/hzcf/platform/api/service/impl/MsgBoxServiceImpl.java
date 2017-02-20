package com.hzcf.platform.api.service.impl;

import com.hzcf.platform.api.annotation.LogAnnotation;
import com.hzcf.platform.core.user.model.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.service.IMsgBoxService;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.api.baseEnum.HzdStatusCodeEnum;
import com.hzcf.platform.core.user.model.MsgBoxVO;
import com.hzcf.platform.core.user.service.MsgBoxservice;

import java.util.HashMap;
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
		if(msgBoxVO != null){
			msgBoxVO.setUserId(user.getId());
			PaginatedResult<MsgBoxVO> result = this.msgBoxservice.selectAllByUser(msgBoxVO);
			if(StatusCodes.OK==result.getStatus() ){
				if(result.getItems().size()==0){
					logger.i("用户 查询站内信列表 。。。。。。 。。。。。查询成功， 但无数据。。。。。。。。 ");
					return new BackResult(HzdStatusCodeEnum.HZD_CODE_5100.getCode(), HzdStatusCodeEnum.HZD_CODE_5100.getMsg(), result.getItems());
				}
				Result<Boolean> update_result = this.msgBoxservice.updateReadByUser(msgBoxVO);
				if(StatusCodes.OK==update_result.getStatus()){
					logger.i("用户 查询站内信列表 。。。。。。 。。。。。修改已读状态 成功。。。。。。。。 ");
					return new BackResult(HzdStatusCodeEnum.HZD_CODE_0000.getCode(), HzdStatusCodeEnum.HZD_CODE_0000.getMsg(), result.getItems());
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