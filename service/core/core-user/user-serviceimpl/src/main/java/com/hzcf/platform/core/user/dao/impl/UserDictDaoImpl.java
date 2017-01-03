package com.hzcf.platform.core.user.dao.impl;

import com.hzcf.platform.core.user.dao.UserDictDao;
import com.hzcf.platform.core.user.data.UserDict;
import com.hzcf.platform.framework.core.storage.mysql.AbstractMysqlBaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by leijiaming on 2016/12/29 0029.
 */
@Repository
public class UserDictDaoImpl extends AbstractMysqlBaseDaoImpl<UserDict> implements UserDictDao {
    @Override
    public int deleteByPrimaryKey(String dictId) {
        return 0;
    }

    @Override
    public int insert(UserDict record) {
        return 0;
    }

    @Override
    public int insertSelective(UserDict record) {
        return 0;
    }

    @Override
    public UserDict selectByPrimaryKey(String dictId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(UserDict record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(UserDict record) {
        return 0;
    }

    @Override
    public List<UserDict> selectList() {
        System.out.print("ss");
        return sqlSessionTemplate.selectList("selectList");

    }
}
