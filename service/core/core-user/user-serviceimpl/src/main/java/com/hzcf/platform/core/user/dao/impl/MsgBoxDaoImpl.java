package com.hzcf.platform.core.user.dao.impl;

import com.hzcf.platform.core.user.dao.MsgBoxDao;
import com.hzcf.platform.core.user.data.MsgBox;
import com.hzcf.platform.core.user.data.User;
import com.hzcf.platform.framework.core.storage.mysql.AbstractMysqlBaseDaoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * Created by leijiaming on 2016/12/29 0029.
 */
@Repository
public class MsgBoxDaoImpl  extends AbstractMysqlBaseDaoImpl<MsgBox> implements MsgBoxDao {

	
    /**
     * by zhangmx 
     * 未读个数
     */
    public int selectUnReadNum(User user){
    	return sqlSessionTemplate.selectOne(getSqlName("selectUnReadNum"), user);
    }
    /**
     * by zhangmx
     * 查询所有消息
     */
    public List<User> selectAllByUser(User user){
    	return sqlSessionTemplate.selectList(getSqlName("selectAllByUser"), user);
    }
    /**
     * by zhangmx
     * 修改成已读
     */
    public boolean updateReadByUser(User user){
    	if (user != null && user.getMobile().length()>0 ) {
			sqlSessionTemplate.update(getSqlName("updateReadByUser"), user);
		}
		return true;
    }
	
	
    @Override
    public int deleteByPrimaryKey(String msgId) {
        return 0;
    }

    @Override
    public int insert(MsgBox record) {
        return 0;
    }

    @Override
    public int insertSelective(MsgBox record) {
        return 0;
    }

    @Override
    public MsgBox selectByPrimaryKey(String msgId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(MsgBox record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(MsgBox record) {
        return 0;
    }
}
