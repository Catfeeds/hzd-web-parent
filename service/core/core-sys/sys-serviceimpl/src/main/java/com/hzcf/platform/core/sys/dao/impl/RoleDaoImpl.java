package com.hzcf.platform.core.sys.dao.impl;

import java.util.List;
import java.util.Map;

import com.hzcf.platform.core.sys.dao.RoleDao;
import com.hzcf.platform.core.sys.data.Role;
import com.hzcf.platform.framework.core.storage.mysql.AbstractMysqlBaseDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hzcf.platform.common.util.rpc.result.Paginate;
import com.hzcf.platform.common.util.rpc.result.PaginatedResult;


@Repository
public class RoleDaoImpl extends AbstractMysqlBaseDaoImpl<Role> implements RoleDao {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public List<Role> getCollecion() {
		// TODO Auto-generated method stub
		List<Role> list = sqlSessionTemplate.selectList("Role.getAll");
		return list;
	}

	


	@Override
	public Role getRoleByID(String id) {
		// TODO Auto-generated method stub
		Role role = (Role) sqlSessionTemplate.selectOne("Role.queryById", Integer.valueOf(id));
		return role;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.delete(getSqlName("delete"), id);
		return true;
	}




	@Override
	public PaginatedResult<Role> flipPage(Map<String, Object> param, int pageSize, int pageNo) {
		// TODO Auto-generated method stub
		Paginate paginate = new Paginate();
		paginate.setPageSize(pageSize);
		paginate.setPageNumber(pageNo);
		PaginatedResult<Role> result = new PaginatedResult<Role>();
		result.setPaginate(paginate);
		param.put("loginDate", new Integer(0));
		param.put("pageSize", new Integer(pageSize));

		int tPageNum = pageNo - 1;
		if (tPageNum < 0) {
			tPageNum = 0;
		}
		param.put("pageNo", new Integer(tPageNum * pageSize));
		
		param.put("sorting", "id  desc");

		Object count = sqlSessionTemplate.selectOne(getSqlName("select") + "Count", param);

		int totalItemsCount = 0;

		if (count == null) {
			totalItemsCount = 0;
		} else {
			totalItemsCount = new Integer(count.toString());;
		}
		paginate.setTotalItemsCount(totalItemsCount);

		if ((totalItemsCount > 0) && (totalItemsCount <= tPageNum * pageSize)) {
			int pages = paginate.getPagesCount();
			tPageNum = pages - 1;
			param.put("pageNo", new Integer(tPageNum * pageSize));
		}

		if (totalItemsCount > 0) {
			List<Object> resultList = sqlSessionTemplate.selectList(getSqlName("select"), param);
			if (null != resultList && resultList.size() > 0) {
				for (Object obj : resultList) {
					result.addItem((Role) obj);
				}
			}
		}

		return result;
	}




	@Override
	public List<Role> getCollecionByUserId(Long userId) {
		// TODO Auto-generated method stub
		List<Role> list = sqlSessionTemplate.selectList("Role.getListByUserId");
		return list; 
	}




	@Override
	public List<Role> getListByName(String roleName) {
		// TODO Auto-generated method stub
		List<Role> list = sqlSessionTemplate.selectList("Role.selectByName",roleName);
		return list; 
	}
}
