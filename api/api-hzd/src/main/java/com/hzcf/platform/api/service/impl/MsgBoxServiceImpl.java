package com.hzcf.platform.api.service.impl;

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
	public BackResult selectUnReadNum(UserVO user,MsgBoxVO msgBoxVO) {
		Map<String,Object> map = new HashMap<String,Object>();

		if(msgBoxVO != null){
			msgBoxVO.setUserId(user.getId());
			Result<Integer> result = this.msgBoxservice.selectUnReadNum(msgBoxVO);
			map.put("number",result.getItems());
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(), HzdStatusCodeEnum.MEF_CODE_0000.getMsg(), map);
		}else{
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_2100.getCode(), HzdStatusCodeEnum.MEF_CODE_2100.getMsg(), null);
		}
	}

	/**
	 * 查询站内信列表
	 */
	@Override
	public BackResult selectAllByUser(UserVO user, MsgBoxVO msgBoxVO) {
		if(msgBoxVO != null){
			msgBoxVO.setUserId(user.getId());
			PaginatedResult<MsgBoxVO> result = this.msgBoxservice.selectAllByUser(msgBoxVO);
			if(StatusCodes.OK==result.getStatus() ){
				if(result.getItems().size()==0){
					return new BackResult(HzdStatusCodeEnum.MEF_CODE_5100.getCode(), HzdStatusCodeEnum.MEF_CODE_5100.getMsg(), result.getItems());
				}
				Result<Boolean> update_result = this.msgBoxservice.updateReadByUser(msgBoxVO);
				if(StatusCodes.OK==update_result.getStatus()){
					return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(), HzdStatusCodeEnum.MEF_CODE_0000.getMsg(), result.getItems());
				}else{
					return new BackResult(HzdStatusCodeEnum.MEF_CODE_2200.getCode(), HzdStatusCodeEnum.MEF_CODE_2200.getMsg(), null);
				}
			}else{
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_2100.getCode(), HzdStatusCodeEnum.MEF_CODE_2100.getMsg(), null);
			}
		}else{
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_2100.getCode(), HzdStatusCodeEnum.MEF_CODE_2100.getMsg(), null);
		}
		
	}
	
	
	/**
	 * 修改已读状态
	 */
	@Override
	public BackResult updateReadByUser( UserVO user,MsgBoxVO msgBoxVO) {
		if(msgBoxVO != null){
			msgBoxVO.setUserId(user.getId());
			Result<Boolean> result = this.msgBoxservice.updateReadByUser(msgBoxVO);
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(), HzdStatusCodeEnum.MEF_CODE_0000.getMsg(), result.getItems());
		}else{
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_2100.getCode(), HzdStatusCodeEnum.MEF_CODE_2100.getMsg(), null);
		}
	}

}