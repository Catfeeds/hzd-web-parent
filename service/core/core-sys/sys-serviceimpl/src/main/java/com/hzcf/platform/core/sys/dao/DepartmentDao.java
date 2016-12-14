package com.hzcf.platform.core.sys.dao;

import java.util.List;

import com.hzcf.platform.core.sys.data.Department;

import com.hzcf.platform.framework.core.storage.StorageProvider;

public interface DepartmentDao extends StorageProvider<Department>{

	  public List<Department> getCollecion();
		
	  public Department getDepartmentByID(Long id);
		
}
