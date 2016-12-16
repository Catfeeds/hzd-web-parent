package com.hzcf.platform.core.sys.dao.impl;

import java.util.List;
import java.util.Map;

import com.hzcf.platform.core.sys.data.SysLog;
import com.hzcf.platform.framework.core.storage.mysql.AbstractMysqlBaseDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hzcf.platform.common.util.rpc.result.Paginate;
import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.core.sys.dao.SysLogDao;

@Repository
public class SysLogDaoImpl extends AbstractMysqlBaseDaoImpl<SysLog> implements SysLogDao {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public SysLog getSysLogByID(String id) {
		// TODO Auto-generated method stub
		SysLog sysLog = (SysLog) sqlSessionTemplate.selectOne("SysLog.queryById",
				Integer.valueOf(id));
		return sysLog;
	}

	@Override
	public PaginatedResult<SysLog> flipPage(Map<String, Object> param, int pageSize,
                                            int pageNo) {
		// TODO Auto-generated method stub
		Paginate paginate = new Paginate();
		paginate.setPageSize(pageSize);
		paginate.setPageNumber(pageNo);
		PaginatedResult<SysLog> result = new PaginatedResult<SysLog>();
		result.setPaginate(paginate);
		param.put("loginDate", new Integer(0));
		param.put("pageSize", new Integer(pageSize));
		param.put("sorting", "id  desc");
		int tPageNum = pageNo - 1;
		if (tPageNum < 0) {
			tPageNum = 0;
		}
		param.put("pageNo", new Integer(tPageNum * pageSize));

		Object count = sqlSessionTemplate.selectOne(getSqlName("select") + "Count", param);

		int totalItemsCount = 0;

		if (count == null) {
			totalItemsCount = 0;
		} else {
			totalItemsCount = new Integer(count.toString());
			;
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
					result.addItem((SysLog) obj);
				}
			}
		}

		return result;
	}

}
