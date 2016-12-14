package com.hzcf.platform.core.sys.service;

import java.util.List;

import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.sys.model.DepartmentVO;
import com.hzcf.platform.framework.core.service.IBaseService;

public interface DepartmentService extends IBaseService<DepartmentVO>{

    public Result<List<DepartmentVO>> getCollecion();
	
	public Result<DepartmentVO>  getDepartmentByID(Long id);

    
}
