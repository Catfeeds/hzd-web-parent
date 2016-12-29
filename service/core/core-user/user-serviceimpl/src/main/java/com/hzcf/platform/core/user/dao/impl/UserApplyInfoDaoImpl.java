package com.hzcf.platform.core.user.dao.impl;


import com.hzcf.platform.core.user.dao.UserApplyInfoDao;
import com.hzcf.platform.core.user.data.UserApplyInfo;
import com.hzcf.platform.framework.core.storage.mysql.AbstractMysqlBaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by leijiaming on 2016/12/29 0029.
 */
@Repository
public class UserApplyInfoDaoImpl  extends AbstractMysqlBaseDaoImpl<UserApplyInfo> implements UserApplyInfoDao{


    @Override
    public UserApplyInfo selectByUserId(String userId) {
        return (UserApplyInfo)this.sqlSessionTemplate.selectOne(this.getSqlName("selectByUserId"), userId);

    }
}
