package com.exiao.platform.core.sys.dao;

import java.util.List;
import java.util.Map;

import com.exiao.platform.common.util.rpc.result.PaginatedResult;
import com.exiao.platform.core.sys.data.ScheduleJob;

import com.exiao.platform.framework.core.storage.StorageProvider;

public interface ScheduleJobDao extends StorageProvider<ScheduleJob>{


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
