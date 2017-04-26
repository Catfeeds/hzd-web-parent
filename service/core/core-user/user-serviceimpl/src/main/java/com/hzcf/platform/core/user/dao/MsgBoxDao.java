package com.hzcf.platform.core.user.dao;


import java.util.List;

import com.hzcf.platform.core.user.data.MsgBox;
import com.hzcf.platform.framework.core.storage.IBaseDao;

public interface MsgBoxDao extends IBaseDao<MsgBox> {
	
    //未读个数
    public int selectUnReadNum(MsgBox msgBox);
    //查询所有消息
    public List<MsgBox> selectAllByUser(MsgBox msgBox);
    //修改成已读
    public boolean updateReadByUser(MsgBox msgBox);

    /**
     * 修改站内信补件状态为已补充
     * @param userId
     * @return
     */
    public boolean updateReadByUserIdStatus(String userId);
    public boolean insertSelective(MsgBox msgBox);

}