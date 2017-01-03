package com.hzcf.platform.api.service;

import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.core.user.model.MsgBoxVO;

/**
 * 
 * @description: 站内信
 * @author zhangmx
 */
public interface IMsgBoxService {
	
	/**
     * by zhangmx 
     * 未读个数
     */
	public BackResult selectUnReadNum(MsgBoxVO msgBoxVO);
	/**
     * by zhangmx
     * 查询所有消息
     */
	public BackResult selectAllByUser(MsgBoxVO msgBoxVO);
	/**
     * by zhangmx
     * 修改成已读
     */
	public BackResult updateReadByUser(MsgBoxVO msgBoxVO);
	
}
