package com.exiao.platform.core.sys.service;

import java.util.List;
import java.util.Map;

import com.exiao.platform.common.util.rpc.result.PaginatedResult;
import com.exiao.platform.common.util.rpc.result.Result;
import com.exiao.platform.core.sys.model.ScheduleJobVO;
import com.exiao.platform.framework.core.service.CommonBaseService;

public interface ScheduleJobService extends CommonBaseService<ScheduleJobVO>{

	public Result<List<ScheduleJobVO>> getCollecion();

	/***
	 * 
	 * @param param
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public PaginatedResult<ScheduleJobVO> flipPage(Map<String, Object> param, int pageSize, int pageNo);


	public Result getScheduleJobById(String id);
	
	public Result<Boolean> updateSheduleJobById(String id);

}
