package com.hzcf.platform.core.user.dao.impl;


import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hzcf.platform.core.user.dao.UserApplyInfoDao;
import com.hzcf.platform.core.user.data.UserApplyInfo;
import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.framework.core.storage.mysql.AbstractMysqlBaseDaoImpl;

/**
 * Created by leijiaming on 2016/12/29 0029.
 */
@Repository
public class UserApplyInfoDaoImpl  extends AbstractMysqlBaseDaoImpl<UserApplyInfo> implements UserApplyInfoDao{


    @Override
    public UserApplyInfo selectByUserId(String userId) {
        return (UserApplyInfo)this.sqlSessionTemplate.selectOne(this.getSqlName("selectByUserId"), userId);

    }

	@Override
	public List<UserApplyInfoVO> getUserApplyInfoList(Map<String, Object> parmMap) {
		return sqlSessionTemplate.selectList(getSqlName("FINDLIST"), parmMap);
	}

	@Override
	public Long getUserApplyInfoTotal(Map<String, Object> parmMap) {
		return sqlSessionTemplate.selectOne(getSqlName("FINDLIST_COUNT"), parmMap);
	}
    @Override
    public UserApplyInfo selectByApplyId(String applyId) {
        return (UserApplyInfo)this.sqlSessionTemplate.selectOne(this.getSqlName("selectByApplyId"), applyId);
    }
    
    @Override
    public List<UserApplyInfoVO> getUserApplyForSearch(Map<String, Object> parmMap){
    	return sqlSessionTemplate.selectList(getSqlName("FINDLIST_SEARCH"), parmMap);
    }

    @Override
    public boolean updateApplyId(UserApplyInfo userApplyInfo) {
        this.sqlSessionTemplate.update(this.getSqlName("updateByApplyId"), userApplyInfo);
        return true;
    }

	@Override
	public UserApplyInfoVO selectByUserIdAndStatus(Map<String, Object> parmMap) {
		return (UserApplyInfoVO)this.sqlSessionTemplate.selectOne(this.getSqlName("selectByUserIdAndStatus"), parmMap);
	}
	/**
	 * @Title: deleteByApplyId 
	 * @Description:根据applyId删除图片信息 
	 * @time: 2017年1月18日 下午5:12:12  
	 * @return:Result<Boolean>
	 */
	public boolean deleteByApplyId(String applyId){
        if (StringUtils.isNotBlank(applyId))  {
            sqlSessionTemplate.delete(getSqlName("deleteByApplyId"), applyId);
            return true;
        }
        return false;
	}
}
