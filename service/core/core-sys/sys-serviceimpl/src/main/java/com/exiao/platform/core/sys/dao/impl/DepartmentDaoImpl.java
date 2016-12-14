package com.exiao.platform.core.sys.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.exiao.platform.core.sys.dao.DepartmentDao;
import com.exiao.platform.core.sys.data.Department;
import com.exiao.platform.framework.core.storage.mysql.MysqlGenericDAO;

@Repository
public class DepartmentDaoImpl extends MysqlGenericDAO<Department> implements DepartmentDao{

	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<Department> getCollecion() {
		// TODO Auto-generated method stub
		List<Department> list = sqlSessionTemplate.selectList("com.exiao.platform.core.sys.data.Department.getAll");
		return list;
	}

	@Override
	public Department getDepartmentByID(Long id) {
		// TODO Auto-generated method stub
		Department department = (Department) sqlSessionTemplate.selectOne("com.exiao.platform.core.sys.data.Department.queryById", id);
		return department;
	}

}
