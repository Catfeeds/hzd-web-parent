package com.exiao.platform.core.sys.service;

import java.util.Map;

import com.exiao.platform.common.util.rpc.result.PaginatedResult;
import com.exiao.platform.common.util.rpc.result.Result;
import com.exiao.platform.core.sys.model.SysLogVO;
import com.exiao.platform.framework.core.service.CommonBaseService;


public interface SysLogService extends CommonBaseService<SysLogVO>{
	
	public Result getSysLogByID(String id);

	public Result<Long> create(SysLogVO sysLogVo);
	
	public PaginatedResult<SysLogVO> flipPage(Map<String, Object> param, int pageSize, int pageNo);

}
