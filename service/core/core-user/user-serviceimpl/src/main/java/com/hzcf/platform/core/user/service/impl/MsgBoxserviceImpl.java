package com.hzcf.platform.core.user.service.impl;

import java.util.List;

import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.core.user.dao.MsgBoxDao;
import com.hzcf.platform.core.user.data.MsgBox;
import com.hzcf.platform.core.user.model.MsgBoxVO;
import com.hzcf.platform.core.user.service.MsgBoxservice;
import com.hzcf.platform.framework.core.service.impl.AbstractBaseServiceImpl;
import com.hzcf.platform.framework.core.storage.IBaseDao;

/**
 * Created by leijiaming on 2016/12/30 0030.
 */
@Service
//@Transactional
public class MsgBoxserviceImpl  extends AbstractBaseServiceImpl<MsgBoxVO,MsgBox> implements MsgBoxservice {
    private Logger logger = LoggerFactory.getLogger(MsgBoxserviceImpl.class);

    @Autowired
    private MsgBoxDao msgBoxDao;

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
        return msgBoxDao;
    }
    
    
    /**
     * 未读个数
     */
	public Result<Integer> selectUnReadNum(MsgBoxVO msgBoxVO){
		try {
			MsgBox t = this.toDO(msgBoxVO);
			int num =msgBoxDao.selectUnReadNum(t);

			return new Result<Integer>(StatusCodes.OK, num);
		} catch (Exception e) {
			logger.error("an error occur in selectUnReadNum service : {}", e);
			return new Result<Integer>(StatusCodes.INTERNAL_SERVER_ERROR, null);
		}
	}
	/**
     * 查询所有消息
     */
	public Result<List<MsgBoxVO>> selectAllByUser(MsgBoxVO msgBoxVO){
		try {
			MsgBox t = this.toDO(msgBoxVO);
			List<MsgBox> result = msgBoxDao.selectAllByUser(t);
			if (null == result) {
				logger.debug("selectByUserIdAndStatusAll data null.");
				return new Result<List<MsgBoxVO>>(StatusCodes.OK, null);
			}
			return new Result<List<MsgBoxVO>>(StatusCodes.OK, toVO(result));

		} catch (Exception e) {
			logger.error("an error occur in selectByUserIdAndStatusAll service : {}", e);
			return new Result<List<MsgBoxVO>>(StatusCodes.INTERNAL_SERVER_ERROR,null);
		}
	}
	/**
     * 修改成已读
     */
	public Result<Boolean> updateReadByUser(MsgBoxVO msgBoxVO){
		try {
			MsgBox t = toDO(msgBoxVO);
			msgBoxDao.updateReadByUser(t);
			return new Result<Boolean>(StatusCodes.OK, true);
		} catch (Exception e) {
			logger.error("an error occur in update updateReadByUser : {}", e);
			return new Result<Boolean>(StatusCodes.INTERNAL_SERVER_ERROR, false);
		}
	}

	@Override
	public Result<Boolean> insertSelective(MsgBoxVO msgBoxVO) {
		try {
			MsgBox msgBox = this.toDO(msgBoxVO);
			msgBoxDao.insertSelective(msgBox);

			return new Result<Boolean>(StatusCodes.OK, true);
		} catch (Exception e) {
			logger.error("an error occur in getByPK service : {}", e);
			return new Result<Boolean>(StatusCodes.INTERNAL_SERVER_ERROR, false);
		}
	}
}
