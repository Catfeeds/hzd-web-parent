package com.hzcf.platform.core.sys.dao;

import java.util.Map;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.core.sys.data.SysLog;
import com.hzcf.platform.framework.core.storage.StorageProvider;

public interface SysLogDao extends StorageProvider<SysLog>{

	public SysLog getSysLogByID(String id);
	
	public PaginatedResult<SysLog> flipPage(Map<String, Object> param, int pageSize, int pageNo);

}
