package com.hzcf.platform.core.sys.dao.impl;

import java.util.List;

import com.hzcf.platform.core.sys.data.Department;
import com.hzcf.platform.framework.core.storage.mysql.AbstractMysqlBaseDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hzcf.platform.core.sys.dao.DepartmentDao;

@Repository
public class DepartmentDaoImpl extends AbstractMysqlBaseDaoImpl<Department> implements DepartmentDao{

	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<Department> getCollecion() {
		// TODO Auto-generated method stub
		List<Department> list = sqlSessionTemplate.selectList("Department.getAll");
		return list;
	}

	@Override
	public Department getDepartmentByID(Long id) {
		// TODO Auto-generated method stub
		Department department = (Department) sqlSessionTemplate.selectOne("Department.queryById", id);
		return department;
	}

}
