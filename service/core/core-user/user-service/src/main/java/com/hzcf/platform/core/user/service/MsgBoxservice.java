package com.hzcf.platform.core.user.service;

import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.user.model.MsgBoxVO;
import com.hzcf.platform.framework.core.service.IBaseService;

import java.util.List;


/**
 * Created by leijiaming on 2016/12/30 0030.
 */
public interface MsgBoxservice extends IBaseService<MsgBoxVO> {
	/**
     * by zhangmx 
     * 未读个数
     */
	public Result<Integer> selectUnReadNum(MsgBoxVO msgBoxVO);
	/**
     * by zhangmx
     * 查询所有消息
     */
	public Result<List<MsgBoxVO>> selectAllByUser(MsgBoxVO msgBoxVO);
	/**
     * by zhangmx
     * 修改成已读
     */
	public Result<Boolean> updateReadByUser(MsgBoxVO msgBoxVO);

	/**
	 * 修改站内信补件状态为已补充
	 * @param UserId
	 * @return
	 */
	public Result<Boolean> updateReadByUserIdStatus(String UserId, String checkSource);
	/**
	 * 插入信息
	 * @param msgBoxVO
	 * @return
	 */
	public Result<Boolean> insertSelective(MsgBoxVO msgBoxVO);
}


