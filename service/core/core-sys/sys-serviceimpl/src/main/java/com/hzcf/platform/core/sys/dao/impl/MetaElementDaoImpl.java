package com.hzcf.platform.core.sys.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.core.sys.dao.MetaElementDao;
import com.hzcf.platform.core.sys.data.MetaElement;
import com.hzcf.platform.framework.core.storage.mysql.MysqlGenericDAO;

@Repository
public class MetaElementDaoImpl extends MysqlGenericDAO<MetaElement> implements MetaElementDao{

	private Logger logger = LoggerFactory.getLogger(this.getClass());


	@Override
	public PaginatedResult<MetaElement> flipPage(Map<String, Object> param, int pageSize, int pageNo) {
		// TODO Auto-generated method stub
		
		PaginatedResult<MetaElement> result = this.flipPage(param, pageSize, pageNo, "FINDLIST");
		return result;
	}


	@Override
	public MetaElement getMetaElementByID(String id) {
		// TODO Auto-generated method stub
		MetaElement  element= (MetaElement) sqlSessionTemplate.selectOne("MetaElement.queryById", Integer.valueOf(id));
		return element;
	}


	@Override
	public List<MetaElement>  getMetaElementByParam(String dataName, String dataCode) {
		// TODO Auto-generated method stub
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("dataName", dataName);
		param.put("dataCode", dataCode);
		param.put("enable", 0);//可用的
		logger.info(" queryMetaElement dataCode:"+dataCode);
		List<MetaElement> list = sqlSessionTemplate.selectList("MetaElement.selectList",param);
		return list;
	}

    /***
     *逻辑删除
     */
	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.delete(getSqlName("logicdeleteById"), id);
		return true;
	}


	

}
