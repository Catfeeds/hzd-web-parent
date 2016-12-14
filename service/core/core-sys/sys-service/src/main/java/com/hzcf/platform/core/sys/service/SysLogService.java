package com.hzcf.platform.core.sys.service;

import java.util.Map;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.sys.model.SysLogVO;
import com.hzcf.platform.framework.core.service.IBaseService;


public interface SysLogService extends IBaseService<SysLogVO> {
	
	public Result getSysLogByID(String id);

	public Result create(SysLogVO sysLogVo);
	
	public PaginatedResult<SysLogVO> flipPage(Map<String, Object> param, int pageSize, int pageNo);

}
