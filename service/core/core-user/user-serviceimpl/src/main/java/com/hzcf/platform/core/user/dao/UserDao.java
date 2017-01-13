package com.hzcf.platform.core.user.dao;


import java.util.List;
import java.util.Map;

import com.hzcf.platform.core.user.data.User;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.framework.core.storage.IBaseDao;

public interface UserDao extends IBaseDao<User> {
	/**
	 * 
		 * @Description: 根据手机号更新 
		 * @User: 雷佳明
		 * @FileName: WipeRecordMgr.java
		 * @param 参数  
		 * @return 返回类型 
		 * @date 2016年12月8日
		 * @throws
	 */
	boolean updateMobile(User user);
	
	/**
	 * 按手机号取得
	 * 
	 * @param mobile
	 * @return
	 */
	User getByMobile(String mobile);
	/**
	 * @Title: selectByPrimaryKey 
	 * @Description:根据id查询用户信息 
	 * @time: 2017年1月9日 下午9:26:28  
	 * @return:User
	 */
	public User selectByPrimaryKey(String id);

	String insertSelective(User user);
	
	/***
	 * @Title: updateByPrimaryKeySelective 
	 * @Description:根据id有选择的修改User中的字段 
	 * @time: 2017年1月12日 下午4:42:54  
	 * @return:boolean
	 */
	boolean updateByPrimaryKeySelective(User user);
	
	public List<User> getUserList(Map<String, Object> parmMap);
	public Long getUserTotal(Map<String, Object> parmMap);
	/*根据用户的真实姓名（name）和身份证号码（id_card），查询“真实姓名”和“身份证号码”重复的数量
	 *	请求数据：
			格式：Map<String,Object>
			参数：name，idCard
		返回数据：
			格式：Map<String,Object>
			参数：realnamerepeat（真实姓名重复的数量），idcardrepeat（身份证号码重复的数量）,allrepeat（真实姓名和身份证号码总共的重复数量）
	 */
	public Map selectNameAndIdCardRepeat(Map<String, Object> parmMap);
	
	public List<User> getCheckUserForSearch(Map<String, Object> parmMap);
}
