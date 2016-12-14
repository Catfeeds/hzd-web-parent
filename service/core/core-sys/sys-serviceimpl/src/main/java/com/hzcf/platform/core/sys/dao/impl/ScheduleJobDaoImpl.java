package com.hzcf.platform.core.sys.dao.impl;

import java.util.List;
import java.util.Map;

import com.hzcf.platform.core.sys.dao.ScheduleJobDao;
import com.hzcf.platform.core.sys.data.ScheduleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hzcf.platform.common.util.rpc.result.Paginate;
import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.framework.core.storage.mysql.MysqlGenericDAO;

@Repository
public class ScheduleJobDaoImpl extends MysqlGenericDAO<ScheduleJob>implements ScheduleJobDao {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<ScheduleJob> getCollecion() {
		// TODO Auto-generated method stub
		List<ScheduleJob> list = sqlSessionTemplate.selectList("ScheduleJob.getAll");
		return list; 
	}

	@Override
	public PaginatedResult<ScheduleJob> flipPage(Map<String, Object> param, int pageSize, int pageNo) {
		// TODO Auto-generated method stub
		Paginate paginate = new Paginate();
		paginate.setPageSize(pageSize);
		paginate.setPageNumber(pageNo);
		PaginatedResult<ScheduleJob> result = new PaginatedResult<ScheduleJob>();
		result.setPaginate(paginate);
		param.put("loginDate", new Integer(0));
		param.put("pageSize", new Integer(pageSize));

		int tPageNum = pageNo - 1;
		if (tPageNum < 0) {
			tPageNum = 0;
		}
		param.put("pageNo", new Integer(tPageNum * pageSize));

		param.put("sorting", "id  desc");

		Object count = sqlSessionTemplate.selectOne(getSqlName("FINDLIST") + "_COUNT", param);

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
			List<Object> resultList = sqlSessionTemplate.selectList(getSqlName("FINDLIST"), param);
			if (null != resultList && resultList.size() > 0) {
				for (Object obj : resultList) {
					result.addItem((ScheduleJob) obj);
				}
			}
		}

		return result;
	}

	@Override
	public boolean update(ScheduleJob scheduleJob) {
		// TODO Auto-generated method stub
		if (scheduleJob != null && scheduleJob.getId() > 0) {
			sqlSessionTemplate.update("ScheduleJob.updateByIdSelective", scheduleJob);
		}
		return true;
	}

	@Override
	public ScheduleJob getScheduleJobById(String id) {
		// TODO Auto-generated method stub
		ScheduleJob job = (ScheduleJob) sqlSessionTemplate.selectOne("ScheduleJob.selectById", id);
		return job;
	}

	
	
}
