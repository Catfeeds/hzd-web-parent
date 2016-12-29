package com.hzcf.platform.core.user.dao;


import com.hzcf.platform.core.user.data.MsgBox;
import com.hzcf.platform.framework.core.storage.IBaseDao;

public interface MsgBoxDao extends IBaseDao<MsgBox> {
    int deleteByPrimaryKey(String msgId);

    int insert(MsgBox record);

    int insertSelective(MsgBox record);

    MsgBox selectByPrimaryKey(String msgId);

    int updateByPrimaryKeySelective(MsgBox record);

    int updateByPrimaryKey(MsgBox record);
}