package com.hzcf.platform.core.user.dao.impl;

import com.hzcf.platform.core.user.dao.MsgBoxDao;
import com.hzcf.platform.core.user.data.MsgBox;
import com.hzcf.platform.framework.core.storage.mysql.AbstractMysqlBaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by leijiaming on 2016/12/29 0029.
 */
@Repository
public class MsgBoxDaoImpl  extends AbstractMysqlBaseDaoImpl<MsgBox> implements MsgBoxDao {

    @Override
    public int deleteByPrimaryKey(String msgId) {
        return 0;
    }

    @Override
    public int insert(MsgBox record) {
        return 0;
    }

    @Override
    public int insertSelective(MsgBox record) {
        return 0;
    }

    @Override
    public MsgBox selectByPrimaryKey(String msgId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(MsgBox record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(MsgBox record) {
        return 0;
    }
}
