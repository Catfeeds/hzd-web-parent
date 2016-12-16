package com.hzcf.platform.core.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hzcf.platform.core.sys.dao.ScheduleJobDao;
import com.hzcf.platform.core.sys.data.ScheduleJob;
import com.hzcf.platform.framework.core.service.impl.AbstractBaseServiceImpl;
import com.hzcf.platform.framework.core.storage.IBaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.core.sys.model.ScheduleJobVO;
import com.hzcf.platform.core.sys.service.ScheduleJobService;


@Service
public class ScheduleJobServiceImpl extends AbstractBaseServiceImpl<ScheduleJobVO, ScheduleJob>
		implements ScheduleJobService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ScheduleJobDao scheduleJobDao;

	@Override
	protected ScheduleJobVO getModel() {
		// TODO Auto-generated method stub
		return new ScheduleJobVO();
	}

	@Override
	protected ScheduleJob getEntity() {
		// TODO Auto-generated method stub
		return new ScheduleJob();
	}

	@Override
	protected IBaseDao<ScheduleJob> getGenericDAO() {
		// TODO Auto-generated method stub
		return scheduleJobDao;
	}

	@Override
	public Result<List<ScheduleJobVO>> getCollecion() {
		// TODO Auto-generated method stub
		List<ScheduleJobVO> resultList = new ArrayList<ScheduleJobVO>();
		int status = StatusCodes.OK;
		try {
			List<ScheduleJob> list = scheduleJobDao.getCollecion();
			if (list != null && list.size() > 0) {
				for (ScheduleJob s : list) {
					resultList.add(toVO(s));
				}
			} else {
				status = StatusCodes.NOT_FOUND;
			}
		} catch (Exception e) {
			logger.error("an error occur in role getCollection service, {} ", e);
			status = StatusCodes.INTERNAL_SERVER_ERROR;
		}
		return new Result<List<ScheduleJobVO>>(status, resultList);
	}

	@Override
	public PaginatedResult<ScheduleJobVO> flipPage(Map<String, Object> param, int pageSize, int pageNo) {
		// TODO Auto-generated method stub
		try {
			PaginatedResult<ScheduleJobVO> result = new PaginatedResult<ScheduleJobVO>();
			param.put("logicalDel", "0");
			PaginatedResult<ScheduleJob> resultDO = this.scheduleJobDao.flipPage(param, pageSize, pageNo);
			if (null != resultDO && resultDO.getItems().size() > 0) {
				for (ScheduleJob scheduleJob : resultDO.getItems()) {
					ScheduleJobVO vo = toVO(scheduleJob);
					result.addItem(vo);
				}
			}
			result.setPaginate(resultDO.getPaginate());
			result.setStatus(StatusCodes.OK);
			return result;
		} catch (Exception e) {
			logger.error("an error occur in Role findPage service : {}", e);
			return new PaginatedResult<ScheduleJobVO>(StatusCodes.INTERNAL_SERVER_ERROR, pageSize, pageNo, 0);
		}
	}

	@Override
	public Result getScheduleJobById(String id) {
		// TODO Auto-generated method stub
		ScheduleJobVO scheduleJobVO = new ScheduleJobVO();
		int status = StatusCodes.OK;
		try {
			ScheduleJob job = scheduleJobDao.getScheduleJobById(id);
			if (null == job) {
				status = StatusCodes.NOT_FOUND;
			}
			scheduleJobVO = toVO(job);
		} catch (Exception e) {
			logger.error("an error occur in getRoleByID service : {}", e);
			status = StatusCodes.INTERNAL_SERVER_ERROR;
		}
		return new Result<ScheduleJobVO>(status, scheduleJobVO);
	}

	@Override
	public Result<Boolean> updateSheduleJobById(String id) {
		// TODO Auto-generated method stub
		int status = StatusCodes.OK;
		try {
			ScheduleJob job = scheduleJobDao.getScheduleJobById(id);
			if (null == job) {
				status = StatusCodes.NOT_FOUND;
			}
			job.setJobStatus(1);
			scheduleJobDao.update(job);
		} catch (Exception e) {
			logger.error("an error occur in getRoleByID service : {}", e);
			status = StatusCodes.INTERNAL_SERVER_ERROR;
		}
		return new Result<Boolean>(StatusCodes.OK, true);
	}
 
}
