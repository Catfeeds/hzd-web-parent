package com.hzcf.platform.core.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hzcf.platform.core.user.dao.DistrictDao;
import com.hzcf.platform.core.user.data.District;
import com.hzcf.platform.framework.core.storage.mysql.AbstractMysqlBaseDaoImpl;

/**
 */
@Repository
public class DistrictDaoImpl  extends AbstractMysqlBaseDaoImpl<District> implements DistrictDao {

	@Override
	public List<District> selectAllProvince() {
		return sqlSessionTemplate.selectList(getSqlName("selectAllProvince"));
	}

	@Override
	public List<District> selectAllCity() {
		return sqlSessionTemplate.selectList(getSqlName("selectAllCity"));
	}
	

	@Override
	public List<District> selectAllArea() {
		return sqlSessionTemplate.selectList(getSqlName("selectAllArea"));
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(District record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(District record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public District selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(District record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(District record) {
		// TODO Auto-generated method stub
		return 0;
	}


	
   
	
	
    
}
