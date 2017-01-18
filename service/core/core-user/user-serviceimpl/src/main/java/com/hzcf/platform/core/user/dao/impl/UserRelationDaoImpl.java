package com.hzcf.platform.core.user.dao.impl;

import com.hzcf.platform.core.user.dao.UserRelationDao;
import com.hzcf.platform.core.user.data.UserRelation;
import com.hzcf.platform.framework.core.storage.mysql.AbstractMysqlBaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by leijiaming on 2016/12/29 0029.
 */
@Repository
public class UserRelationDaoImpl extends AbstractMysqlBaseDaoImpl<UserRelation> implements UserRelationDao {
    @Override
    public int deleteByPrimaryKey(String relationId) {
        return 0;
    }



    @Override
    public int insertSelective(UserRelation record) {
        return 0;
    }

    @Override
    public UserRelation selectByPrimaryKey(String relationId) {
        return null;
    }

    @Override
    public List<UserRelation>selectByApplyId(String applyId) {
        return this.sqlSessionTemplate.selectList(this.getSqlName("selectByApplyId"), applyId);

    }

    @Override
    public boolean updateByPrimaryKeySelective(UserRelation record) {
        this.sqlSessionTemplate.update(this.getSqlName("updateByPrimaryKeySelective"), record);
        return true;
    }

    @Override
    public int updateByPrimaryKey(UserRelation record) {
        return 0;
    }



	@Override
	public int deleteByApplyId(String applyId) {
		return this.sqlSessionTemplate.delete("deleteByApplyId",applyId);
	}
    /**
     * @Title: deleteByApplyIdList
     * @Description:根据applyId集合删除借款人关系信息 
     * @time: 2017年1月18日 下午8:02:19  
     * @return:Result<Boolean>
     */
	@Override
	public boolean deleteByApplyIdList(List<String> applyIdList) {
        if (applyIdList!=null)  {
            sqlSessionTemplate.delete(getSqlName("deleteByApplyIdList"), applyIdList);
            return true;
        }
        return false;
	}
}
