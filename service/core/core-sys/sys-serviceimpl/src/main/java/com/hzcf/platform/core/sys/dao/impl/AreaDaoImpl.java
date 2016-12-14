/**
 * 
 */
package com.hzcf.platform.core.sys.dao.impl;

import java.util.List;

import com.hzcf.platform.core.sys.dao.AreaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hzcf.platform.core.sys.data.Area;
import com.hzcf.platform.framework.core.storage.mysql.MysqlGenericDAO;

/**
 * @author allen.shen
 * @Date 2015年10月16日
 * 
 * description: 
 */
@Repository
public class AreaDaoImpl extends MysqlGenericDAO<Area> implements AreaDao {
	
	private Logger logger = LoggerFactory.getLogger(AreaDaoImpl.class);
	
	String mapperStr = "Area.";

	@Override
	public List<Area> getAreaInfoByProvince() {
		return sqlSessionTemplate.selectList(mapperStr + "getAreaInfoByProvince");
	}

	@Override
	public List<Area> getAreaInfoByCity() {
		return sqlSessionTemplate.selectList(mapperStr + "getAreaInfoByCity");
	}

	@Override
	public List<Area> getAreaInfoByCounty() {
		return sqlSessionTemplate.selectList(mapperStr + "");
	}

	
}
