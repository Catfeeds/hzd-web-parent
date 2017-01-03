package com.hzcf.platform.core.user.dao.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hzcf.platform.core.user.dao.UserDao;
import com.hzcf.platform.core.user.data.User;
import com.hzcf.platform.framework.core.storage.mysql.AbstractMysqlBaseDaoImpl;

@Repository
public class UserDaoImpl  extends AbstractMysqlBaseDaoImpl<User> implements UserDao {
	@Override
	public boolean updateMobile(User user) {
		if (user != null && user.getMobile().length()>0 ) {
						sqlSessionTemplate.update(getSqlName("updateMobile"), user);
		}
		return true;
	}
	

	public User getByMobile(String mobile) {
		return sqlSessionTemplate.selectOne(getSqlName("selectByMobile"), mobile);
	}
	@Override
	public String insertSelective(User user) {

		this.sqlSessionTemplate.insert(this.getSqlName("insertSelective"), user);

		return user.getId();
	}


	@Override
	public boolean updateByPrimaryKeySelective(User user) {

		if (user != null && user.getMobile().length()>0 ) {
			sqlSessionTemplate.update(getSqlName("updateByPrimaryKeySelective"), user);
		}
		return true;
	}

	@Override
	public List<User> getUserList(Map<String, Object> parmMap){
		//return this.flipPage(parmMap, 1, 10, "FINDLIST");
//		return this.flipPage(parmMap, Integer.parseInt(parmMap.get("pageNo").toString()), 
//				Integer.parseInt(parmMap.get("pageSize").toString()), "FINDLIST");
		
		return sqlSessionTemplate.selectList(getSqlName("FINDLIST"), parmMap);
	}
	
	@Override
	public Long getUserTotal(Map<String, Object> parmMap){
		return sqlSessionTemplate.selectOne(getSqlName("FINDLIST_COUNT"), parmMap);
	}
	/*根据用户的真实姓名（name）和身份证号码（id_card），查询“真实姓名”和“身份证号码”重复的数量
	 *	请求数据：
			格式：Map<String,Object>
			参数：name，idCard
		返回数据：
			格式：Map<String,Object>
			参数：realnamerepeat（真实姓名重复的数量），idcardrepeat（身份证号码重复的数量）,allrepeat（真实姓名和身份证号码总共的重复数量）
	 */
	@Override
	public Map selectNameAndIdCardRepeat(Map<String, Object> paramMap) {
		return sqlSessionTemplate.selectOne(getSqlName("selectNameAndIdCardRepeat"), paramMap);
	}
}
