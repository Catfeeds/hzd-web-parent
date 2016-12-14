package com.exiao.platform.core.sys.dao;

import java.util.Map;

import com.exiao.platform.common.util.rpc.result.PaginatedResult;
import com.exiao.platform.core.sys.data.SysLog;
import com.exiao.platform.framework.core.storage.StorageProvider;

public interface SysLogDao extends StorageProvider<SysLog>{

	public SysLog getSysLogByID(String id);
	
	public PaginatedResult<SysLog> flipPage(Map<String, Object> param, int pageSize, int pageNo);

}
