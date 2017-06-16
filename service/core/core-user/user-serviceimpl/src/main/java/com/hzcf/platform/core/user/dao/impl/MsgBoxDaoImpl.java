package com.hzcf.platform.core.user.dao.impl;

import com.hzcf.platform.core.user.dao.MsgBoxDao;
import com.hzcf.platform.core.user.data.MsgBox;
import com.hzcf.platform.framework.core.storage.mysql.AbstractMysqlBaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by leijiaming on 2016/12/29 0029.
 */
@Repository
public class MsgBoxDaoImpl  extends AbstractMysqlBaseDaoImpl<MsgBox> implements MsgBoxDao {

	
    /**
     * by zhangmx 
     * 未读个数
     */
	@Override
    public int selectUnReadNum(MsgBox msgBox){
    	return sqlSessionTemplate.selectOne(getSqlName("selectUnReadNum"), msgBox);
    }
    /**
     * by zhangmx
     * 查询所有消息
     */
	@Override
    public List<MsgBox> selectAllByUser(MsgBox msgBox){
    	return sqlSessionTemplate.selectList(getSqlName("selectAllByUser"), msgBox);
    }
    /**
     * by zhangmx
     * 修改成已读
     */
	@Override
    public boolean updateReadByUser(MsgBox msgBox){
    	if (msgBox != null) {
			sqlSessionTemplate.update(getSqlName("updateReadByUser"), msgBox);
		}
		return true;
    }

	/**
	 * 修改补件资料状态为已补充
	 * @param parmMap
	 * @return
	 */
	@Override
	public boolean updateReadByUserIdStatus(Map<String, Object> parmMap){
		if (parmMap != null) {
			sqlSessionTemplate.update(getSqlName("updateReadByUserIdStatus"), parmMap);
		}
		return true;
	}

	@Override
	public boolean insertSelective(MsgBox msgBox) {
		sqlSessionTemplate.insert(this.getSqlName("insertSelective"), msgBox);
		return true;
	}

	@Override
	public boolean updateCheckPassByUserId(MsgBox msgBox){
		sqlSessionTemplate.update(this.getSqlName("updateCheckPassByUserId"), msgBox);
		return true;
	}
    
}
