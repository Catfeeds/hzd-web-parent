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
	
    public boolean insertSelective(MsgBox msgBox);

}