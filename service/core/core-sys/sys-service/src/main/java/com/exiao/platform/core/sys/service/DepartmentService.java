package com.exiao.platform.core.sys.service;

import java.util.List;

import com.exiao.platform.common.util.rpc.result.Result;
import com.exiao.platform.core.sys.model.DepartmentVO;
import com.exiao.platform.framework.core.service.CommonBaseService;

public interface DepartmentService extends CommonBaseService<DepartmentVO>{

    public Result<List<DepartmentVO>> getCollecion();
	
	public Result<DepartmentVO>  getDepartmentByID(Long id);

    
}
