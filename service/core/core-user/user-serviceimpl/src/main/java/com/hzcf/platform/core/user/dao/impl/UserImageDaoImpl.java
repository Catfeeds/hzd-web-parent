package com.hzcf.platform.core.user.dao.impl;

import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.user.dao.UserImageDao;
import com.hzcf.platform.core.user.data.UserImage;
import com.hzcf.platform.core.user.model.UserImageVO;
import com.hzcf.platform.framework.core.storage.mysql.AbstractMysqlBaseDaoImpl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * Created by leijiaming on 2016/12/29 0029.
 */
@Repository
public class UserImageDaoImpl extends AbstractMysqlBaseDaoImpl<UserImage> implements UserImageDao {
    @Override
    public int deleteByPrimaryKey(String imageId) {
        return 0;
    }

    @Override
    public int insert(UserImage record) {
        return 0;
    }

    @Override
    public int insertSelective(UserImage record) {
        return 0;
    }

    @Override
    public UserImage selectByPrimaryKey(String imageId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(UserImage record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(UserImage record) {
        return 0;
    }

	/*@Override
	public UserImage getByMobile(String mobile) {
		return sqlSessionTemplate.selectOne(getSqlName("selectByMobile"), mobile);
	}*/

	@Override
	public UserImage getById(String id) {
		return sqlSessionTemplate.selectOne(getSqlName("selectByUserId"), id);
	}
	//String applyId, String type
	@Override
	public List<UserImageVO> selectUserImageByApplyIdAndType(Map<String,Object> paramsMap) {
		return sqlSessionTemplate.selectList("selectUserImageByApplyIdAndType",paramsMap);
	}

	@Override
	public boolean updateByUserId(UserImage userImage) {
		if (userImage.getUserId() != null && userImage.getUserId().length()>0 ) {
			sqlSessionTemplate.update(getSqlName("updateByUserId"), userImage);
		}
		return true;
	}
}
