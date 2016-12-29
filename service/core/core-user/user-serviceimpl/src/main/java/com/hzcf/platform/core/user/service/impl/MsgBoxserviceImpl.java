package com.hzcf.platform.core.user.service.impl;

import com.hzcf.platform.core.user.dao.MsgBoxDao;
import com.hzcf.platform.core.user.data.MsgBox;
import com.hzcf.platform.core.user.data.UserApplyInfo;
import com.hzcf.platform.core.user.model.MsgBoxVO;
import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.core.user.service.MsgBoxservice;
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
public class MsgBoxserviceImpl   extends AbstractBaseServiceImpl<MsgBoxVO,MsgBox> implements MsgBoxservice {
    private Logger logger = LoggerFactory.getLogger(MsgBoxserviceImpl.class);

    @Autowired
    private MsgBoxDao purchaseOrderDao;

    @Override
    protected MsgBoxVO getModel() {
        return new MsgBoxVO();
    }

    @Override
    protected MsgBox getEntity() {
        return new MsgBox();
    }

    @Override
    protected IBaseDao<MsgBox> getGenericDAO() {
        return purchaseOrderDao;
    }
}
