package com.hzcf.platform.api.service;

import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.core.user.model.MsgBoxVO;
import com.hzcf.platform.core.user.model.UserVO;

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
	public BackResult selectUnReadNum(UserVO user, MsgBoxVO msgBoxVO);
	/**
     * by zhangmx
     * 查询所有消息
     */
	public BackResult selectAllByUser(UserVO user, MsgBoxVO msgBoxVO);
	/**
     * by zhangmx
     * 修改成已读
     */
	public BackResult updateReadByUser( UserVO user,MsgBoxVO msgBoxVO);
	
}
