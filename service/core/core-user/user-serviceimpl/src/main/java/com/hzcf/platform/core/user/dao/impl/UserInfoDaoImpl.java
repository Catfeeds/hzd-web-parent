package com.hzcf.platform.core.user.dao.impl;

import com.hzcf.platform.core.user.dao.UserInfoDao;
import com.hzcf.platform.core.user.data.UserApplyInfo;
import com.hzcf.platform.core.user.data.UserInfo;
import com.hzcf.platform.framework.core.storage.mysql.AbstractMysqlBaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by leijiaming on 2016/12/29 0029.
 */
@Repository
public class UserInfoDaoImpl extends AbstractMysqlBaseDaoImpl<UserInfo> implements UserInfoDao{


    @Override
    public int deleteByPrimaryKey(String userInfoId) {
        return 0;
    }



    @Override
    public int insertSelective(UserInfo record) {
        return 0;
    }

    @Override
    public UserInfo selectByPrimaryKey(String userInfoId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(UserInfo record) {
        return 0;
    }

    @Override
    public Boolean updateByApplyId(UserInfo userInfo) {
        this.sqlSessionTemplate.update(this.getSqlName("updateByApplyId"), userInfo);
        return true;
    }

    @Override
    public UserInfo selectByApplyId(String ApplyId) {
        return (UserInfo)this.sqlSessionTemplate.selectOne(this.getSqlName("selectByApplyId"), ApplyId);

    }
}
