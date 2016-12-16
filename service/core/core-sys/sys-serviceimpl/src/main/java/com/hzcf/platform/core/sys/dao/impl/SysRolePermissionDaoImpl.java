package com.hzcf.platform.core.sys.dao.impl;

import java.util.List;
import java.util.Map;

import com.hzcf.platform.core.sys.dao.SysRolePermissionDao;
import com.hzcf.platform.core.sys.data.SysRolePermission;
import com.hzcf.platform.framework.core.storage.mysql.AbstractMysqlBaseDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hzcf.platform.common.util.rpc.result.Paginate;
import com.hzcf.platform.common.util.rpc.result.PaginatedResult;

@Repository
public class SysRolePermissionDaoImpl extends AbstractMysqlBaseDaoImpl<SysRolePermission> implements SysRolePermissionDao {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	String mapperStr = "SysRolePermission.";

	@Override
	public List<SysRolePermission> getSysRolePermissionByRoleId(Long roleId) {
		// TODO Auto-generated method stub
		List<SysRolePermission> list = sqlSessionTemplate
				.selectList("SysRolePermission.selectByRoleIdList",roleId);
		return list;
	}

	@Override
	public PaginatedResult<SysRolePermission> flipPage(Map<String, Object> param, int pageSize, int pageNo) {
		// TODO Auto-generated method stub
		Paginate paginate = new Paginate();
		paginate.setPageSize(pageSize);
		paginate.setPageNumber(pageNo);
		PaginatedResult<SysRolePermission> result = new PaginatedResult<SysRolePermission>();
		result.setPaginate(paginate);
		
		param.put("pageSize", new Integer(pageSize));

		int tPageNum = pageNo - 1;
		if (tPageNum < 0) {
			tPageNum = 0;
		}
		param.put("pageNo", new Integer(tPageNum * pageSize));
		

		Object count = sqlSessionTemplate.selectOne(getSqlName("FINDLIST") + "_COUNT", param);

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
			List<Object> resultList = sqlSessionTemplate.selectList(getSqlName("FINDLIST"), param);
			if (null != resultList && resultList.size() > 0) {
				for (Object obj : resultList) {
					result.addItem((SysRolePermission) obj);
				}
			}
		} 

		return result;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		int result = sqlSessionTemplate.delete("SysRolePermission.deleteById", id);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

}
