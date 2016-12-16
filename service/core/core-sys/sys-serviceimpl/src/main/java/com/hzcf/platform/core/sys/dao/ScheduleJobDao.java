package com.hzcf.platform.core.sys.dao;

import java.util.List;
import java.util.Map;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.core.sys.data.ScheduleJob;

import com.hzcf.platform.framework.core.storage.IBaseDao;

public interface ScheduleJobDao extends IBaseDao<ScheduleJob> {


	public List<ScheduleJob> getCollecion();

	/***
	 * 
	 * @param param
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public PaginatedResult<ScheduleJob> flipPage(Map<String, Object> param, int pageSize, int pageNo);

	
	/**
	 * 单条更新
	 * 
	 * @param id
	 * @return
	 */
	public boolean update(ScheduleJob scheduleJob);
	
	
	public ScheduleJob getScheduleJobById(String id);
}
