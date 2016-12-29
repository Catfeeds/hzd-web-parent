package com.hzcf.platform.core.user.service.impl;

import com.hzcf.platform.core.user.dao.UserDictDao;
import com.hzcf.platform.core.user.data.UserDict;
import com.hzcf.platform.core.user.model.UserDictVO;
import com.hzcf.platform.core.user.service.UserDictService;
import com.hzcf.platform.framework.core.service.impl.AbstractBaseServiceImpl;
import com.hzcf.platform.framework.core.storage.IBaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by leijiaming on 2016/12/29 0029.
 */
@Service
public class UserDictServiceImpl extends AbstractBaseServiceImpl<UserDictVO,UserDict> implements UserDictService{
    private Logger logger = LoggerFactory.getLogger(UserDictServiceImpl.class);

    @Autowired
    private UserDictDao purchaseOrderDao;


    @Override
    protected UserDictVO getModel() {
        return new UserDictVO();
    }

    @Override
    protected UserDict getEntity() {
        return new UserDict();
    }

    @Override
    protected IBaseDao<UserDict> getGenericDAO() {
        return purchaseOrderDao;
    }
}
