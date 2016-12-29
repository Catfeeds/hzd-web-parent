package com.hzcf.platform.core.user.service.impl;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.core.user.dao.MsgBoxDao;
import com.hzcf.platform.core.user.data.MsgBox;
import com.hzcf.platform.core.user.model.MsgBoxVO;
import com.hzcf.platform.core.user.service.MsgBoxService;
import com.hzcf.platform.framework.core.service.impl.AbstractBaseServiceImpl;
import com.hzcf.platform.framework.core.storage.IBaseDao;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by leijiaming on 2016/12/29 0029.
 */
@Service
public class MsgBoxServiceImpl   extends AbstractBaseServiceImpl<MsgBoxVO,MsgBox> implements MsgBoxService {
    private Logger logger = LoggerFactory.getLogger(MsgBoxServiceImpl.class);

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

    /**
     * 获取站内消息
     */
	@Override
	public PaginatedResult<MsgBoxVO> getMsgBoxPage(Map<String, Object> parmMap) {
		try {
			PaginatedResult<MsgBox> result = purchaseOrderDao.getMsgBoxPage(parmMap);
			if (null == result || result.getItems() == null) {
				logger.debug("data null.");
				PaginatedResult<MsgBoxVO> resultVO = new PaginatedResult<MsgBoxVO>();
				resultVO.setStatus(StatusCodes.OK);
				return resultVO;
			}
			PaginatedResult<MsgBoxVO> resultVO = new PaginatedResult<MsgBoxVO>();
			resultVO.setItems(toVO(result.getItems()));
			resultVO.setStatus(StatusCodes.OK);
			return resultVO;
		} catch (Exception e) {
			logger.error("an error occur in getByPK service : {}", e);
			PaginatedResult<MsgBoxVO> resultVO = new PaginatedResult<MsgBoxVO>();
			resultVO.setStatus(StatusCodes.INTERNAL_SERVER_ERROR);
			return resultVO;
		}
	}
}
