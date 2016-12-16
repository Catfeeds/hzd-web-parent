package com.hzcf.platform.core.sys.service.impl;


import java.util.ArrayList;
import java.util.List;

import com.hzcf.platform.framework.core.service.impl.AbstractBaseServiceImpl;
import com.hzcf.platform.framework.core.storage.IBaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.core.sys.dao.DepartmentDao;
import com.hzcf.platform.core.sys.data.Department;
import com.hzcf.platform.core.sys.model.DepartmentVO;
import com.hzcf.platform.core.sys.service.DepartmentService;


@Service
public class DepartmentServiceImpl extends AbstractBaseServiceImpl<DepartmentVO, Department> implements DepartmentService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DepartmentDao departmentDao;
	
	@Override
	public Result<List<DepartmentVO>> getCollecion() {
		// TODO Auto-generated method stub
		List<DepartmentVO> resultList = new ArrayList<DepartmentVO>();
		int status = StatusCodes.OK;
		try {
			List<Department> list = departmentDao.getCollecion();
			if (list != null && list.size() > 0) {
				for (Department s : list) {
					resultList.add(toVO(s));
				}
			} else {
				status = StatusCodes.NOT_FOUND;
			}
		} catch (Exception e) {
			logger.error("an error occur in department getCollection service, {} ", e);
			status = StatusCodes.INTERNAL_SERVER_ERROR;
		}
		return new Result<List<DepartmentVO>>(status, resultList);
	}

	@Override
	protected DepartmentVO getModel() {
		// TODO Auto-generated method stub
		return new DepartmentVO();
	}

	@Override
	protected Department getEntity() {
		// TODO Auto-generated method stub
		return new Department();
	}

	@Override
	protected IBaseDao<Department> getGenericDAO() {
		// TODO Auto-generated method stub
		return departmentDao;
	}

	@Override
	public Result<DepartmentVO> getDepartmentByID(Long id) {
		// TODO Auto-generated method stub
		DepartmentVO deptVO = new DepartmentVO();
		int status = StatusCodes.OK;
		try {
			Department dept = departmentDao.getDepartmentByID(id);
			if (null == dept) {
				status = StatusCodes.NOT_FOUND;
			}
			deptVO = toVO(dept);
		} catch (Exception e) {
			logger.error("an error occur in getDepartmentByID service : {}", e);
			status = StatusCodes.INTERNAL_SERVER_ERROR;
		}
		return new Result<DepartmentVO>(status, deptVO);
	}

}
