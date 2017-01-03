package com.hzcf.platform.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.service.IMsgBoxService;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.core.HzdStatusCodeEnum;
import com.hzcf.platform.core.user.model.MsgBoxVO;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.core.user.service.MsgBoxservice;

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
	public BackResult selectUnReadNum(MsgBoxVO msgBoxVO) {
		if(msgBoxVO != null){
			Result<Integer> result = this.msgBoxservice.selectUnReadNum(msgBoxVO);
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(), HzdStatusCodeEnum.MEF_CODE_0000.getMsg(), result.getItems().intValue());
		}else{
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_2100.getCode(), HzdStatusCodeEnum.MEF_CODE_2100.getMsg(), null);
		}
	}

	/**
	 * 查询站内信列表
	 */
	@Override
	public BackResult selectAllByUser(MsgBoxVO msgBoxVO) {
		if(msgBoxVO != null){
			PaginatedResult<MsgBoxVO> result = this.msgBoxservice.selectAllByUser(msgBoxVO);
			if(StatusCodes.OK==result.getStatus()){
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
	public BackResult updateReadByUser(MsgBoxVO msgBoxVO) {
		if(msgBoxVO != null){
			Result<Boolean> result = this.msgBoxservice.updateReadByUser(msgBoxVO);
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(), HzdStatusCodeEnum.MEF_CODE_0000.getMsg(), result.getItems());
		}else{
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_2100.getCode(), HzdStatusCodeEnum.MEF_CODE_2100.getMsg(), null);
		}
	}

}