package com.hzcf.platform.core.sys.service.impl;


import java.util.Map;

import com.hzcf.platform.core.sys.data.SysLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.core.sys.dao.SysLogDao;
import com.hzcf.platform.core.sys.dao.UserDao;
import com.hzcf.platform.core.sys.model.SysLogVO;
import com.hzcf.platform.core.sys.service.SysLogService;
import com.hzcf.platform.framework.core.service.impl.CommonBaseServiceImpl;
import com.hzcf.platform.framework.core.storage.StorageProvider;
@Service
public class SysLogServiceImpl extends CommonBaseServiceImpl<SysLogVO, SysLog> implements SysLogService{

	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SysLogDao sysLogDao;
	
	@Autowired
	private UserDao userDao;
	
	
	@Override
	protected SysLogVO getModel() {
		// TODO Auto-generated method stub
		return new SysLogVO();
	}

	@Override
	protected SysLog getEntity() {
		// TODO Auto-generated method stub
		return new SysLog();
	}

	@Override
	protected StorageProvider<SysLog> getGenericDAO() {
		// TODO Auto-generated method stub
		return sysLogDao;
	}

	@Override
	public Result getSysLogByID(String id) {
		// TODO Auto-generated method stub
		SysLogVO sysLogVO = new SysLogVO();
		int status = StatusCodes.OK;
		try {
			SysLog sysLog = sysLogDao.getSysLogByID(id);
			if (null == sysLog) {
				status = StatusCodes.NOT_FOUND;
			}
			sysLogVO = toVO(sysLog);
		} catch (Exception e) {
			logger.error("an error occur in getSysLogByID service : {}", e);
			status = StatusCodes.INTERNAL_SERVER_ERROR;
		}
		return new Result<SysLogVO>(status, sysLogVO);
	}

	@Override
	public PaginatedResult<SysLogVO> flipPage(Map<String, Object> param, int pageSize, int pageNo) {
		// TODO Auto-generated method stub
		try {
			PaginatedResult<SysLogVO> result = new PaginatedResult<SysLogVO>();
			
			PaginatedResult<SysLog> resultDO = this.sysLogDao.flipPage(param,pageSize, pageNo);
			if (null != resultDO && resultDO.getItems().size() > 0) {
				for (SysLog log : resultDO.getItems()) {
					
					result.addItem(toVO(log));
				}
			}
			result.setPaginate(resultDO.getPaginate());
			result.setStatus(StatusCodes.OK);
			return result;
		} catch (Exception e) {
			logger.error("an error occur in SysLog findPage service : {}", e);
			return new PaginatedResult<SysLogVO>(StatusCodes.INTERNAL_SERVER_ERROR, pageSize, pageNo, 0);
		}
	}

}
