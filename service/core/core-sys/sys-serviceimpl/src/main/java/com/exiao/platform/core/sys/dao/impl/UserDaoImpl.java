package com.exiao.platform.core.sys.dao.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.exiao.platform.common.util.rpc.result.Paginate;
import com.exiao.platform.common.util.rpc.result.PaginatedResult;
import com.exiao.platform.core.sys.dao.UserDao;
import com.exiao.platform.core.sys.data.User;
import com.exiao.platform.framework.core.storage.mysql.MysqlGenericDAO;

@Repository
public class UserDaoImpl extends MysqlGenericDAO<User> implements UserDao{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	final static String mapperStr = "com.exiao.platform.core.sys.data.User";

	@Override
	public List<User> getCollecion() {
		// TODO Auto-generated method stub
		List<User> list = sqlSessionTemplate.selectList("com.exiao.platform.core.sys.data.User.getAll");
		return list;
	}

	@Override
	public User getUserByID(String id) {
		// TODO Auto-generated method stub
		User user = (User) sqlSessionTemplate.selectOne("com.exiao.platform.core.sys.data.User.queryById", Integer.valueOf(id));
		return user;
	}

	@Override
	public PaginatedResult<User> flipPage(Map<String, Object> param, int pageSize, int pageNo) {
		// TODO Auto-generated method stub
		Paginate paginate = new Paginate();
		paginate.setPageSize(pageSize);
		paginate.setPageNumber(pageNo);
		PaginatedResult<User> result = new PaginatedResult<User>();
		result.setPaginate(paginate);
		param.put("logicalDel","0");
		//param.put("loginDate", new Integer(0));
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
			totalItemsCount = new Integer(count.toString());
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
					result.addItem((User) obj);
				}
			}
		}

		return result;
	}

	@Override
	public User getUserByLoginName(String loginName) {
		// TODO Auto-generated method stub
		  User record = (User) sqlSessionTemplate.selectOne(mapperStr
					+ ".getUserByloginName", loginName);
			return record;
		
	}

	@Override
	public boolean updateUserPsw(User user) {
		// TODO Auto-generated method stub
		if (user != null && user.getId() > 0) {
			sqlSessionTemplate.update(mapperStr + ".updateByIdSelective", user);
		}
		return true;

	}

	@Override
	public List<User> getUserListByIds(Long... ids) {
		// TODO Auto-generated method stub
		List<User> list = sqlSessionTemplate.selectList("com.exiao.platform.core.sys.data.User.getUserListByIds", ids);
		return list;
	}

}
