package com.exiao.platform.core.sys.dao.impl;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.exiao.platform.common.util.rpc.result.Paginate;
import com.exiao.platform.common.util.rpc.result.PaginatedResult;
import com.exiao.platform.core.sys.dao.PermissionDao;
import com.exiao.platform.core.sys.data.Permission;
import com.exiao.platform.core.sys.data.Role;
import com.exiao.platform.framework.core.storage.mysql.MysqlGenericDAO;

@Repository
public class PermissionDaoImpl extends MysqlGenericDAO<Permission> implements PermissionDao{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	String mapperStr = "com.exiao.platform.core.sys.data.Permission.";

	@Override
	public List<Permission> getCollecion() {
		// TODO Auto-generated method stub
		List<Permission> list = sqlSessionTemplate.selectList("com.exiao.platform.core.sys.data.Permission.getAll");
		return list;
	}

	@Override
	public Permission getPermissionByID(String id) {
		// TODO Auto-generated method stub
		Permission permission = (Permission) sqlSessionTemplate.selectOne("com.exiao.platform.core.sys.data.Permission.queryById", Integer.valueOf(id));
		return permission;
	}

	@Override
	public PaginatedResult<Permission> flipPage(Map<String, Object> param, int pageSize, int pageNo) {
		// TODO Auto-generated method stub
		param.put("logicalDel", new Integer(0));
		PaginatedResult<Permission> result = this.flipPage(param, pageSize, pageNo, "FINDLIST");
		return result;
	}


	

	@Override
	public List<Permission> getPermissionCollecion(Integer user_id) {
		// TODO Auto-generated method stub
		List<Permission> list = sqlSessionTemplate.selectList("com.exiao.platform.core.sys.data.Permission.getListByUserId",user_id);
		return list;
	}

	@Override
	public List<Permission> getCollecion(Integer role_id) {
		// TODO Auto-generated method stub
		List<Permission> list = sqlSessionTemplate.selectList("com.exiao.platform.core.sys.data.Permission.getListByRoleId",role_id);
		return list; 
	}

	@Override
	public List<Permission> getPermissionListByUserCode(String userCode) {
		// TODO Auto-generated method stub
		List<Permission> list = sqlSessionTemplate.selectList("com.exiao.platform.core.sys.data.Permission.getListByUserloginName",userCode);
		return list; 
	}

	@Override
	public List<String> getMenuByUserId(Integer user_id) {
		// TODO Auto-generated method stub
		List<String> list = sqlSessionTemplate
				.selectList(mapperStr+"getMenuListByUserId", user_id);
		return list;
	}

	@Override
	public List<String> getPermissionByUserId(Integer user_id) {
		// TODO Auto-generated method stub
		List<String> list = sqlSessionTemplate
				.selectList(mapperStr+"getPermissionListByUserId", user_id);
		return list;

	}

	@Override
	public PaginatedResult<Permission> queryForRoleNotExistflipPage(Map<String, Object> param, int pageSize,
			int pageNo) {
		// TODO Auto-generated method stub
		Paginate paginate = new Paginate();
		paginate.setPageSize(pageSize);
		paginate.setPageNumber(pageNo);
		PaginatedResult<Permission> result = new PaginatedResult<Permission>();
		result.setPaginate(paginate);
		param.put("pageSize", new Integer(pageSize));

		int tPageNum = pageNo - 1;
		if (tPageNum < 0) {
			tPageNum = 0;
		}
		param.put("pageNo", new Integer(tPageNum * pageSize));
		
		
		Object count = sqlSessionTemplate.selectOne(getSqlName("ROLE_NOTEXIST_FINDLIST_COUNT") , param);

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
			List<Object> resultList = sqlSessionTemplate.selectList(getSqlName("ROLE_NOTEXIST_FINDLIST"), param);
			if (null != resultList && resultList.size() > 0) {
				for (Object obj : resultList) {
					result.addItem((Permission) obj);
				}
			}
		}

		return result;
	}

}
