package com.hzcf.platform.core.user.dao.impl;

import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.user.dao.UserImageDao;
import com.hzcf.platform.core.user.data.UserImage;
import com.hzcf.platform.core.user.model.UserImageVO;
import com.hzcf.platform.framework.core.storage.mysql.AbstractMysqlBaseDaoImpl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

/**
 * Created by leijiaming on 2016/12/29 0029.
 */
@Repository
public class UserImageDaoImpl extends AbstractMysqlBaseDaoImpl<UserImage> implements UserImageDao {
    @Override
    public boolean deleteByPrimaryKey(UserImage record) {
        if (record.getUserId().length() > 0 )  {
            sqlSessionTemplate.delete(getSqlName("deleteByPrimaryKey"), record);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByImageId(String imageId) {
        if (imageId.length() > 0 )  {
            sqlSessionTemplate.delete(getSqlName("deleteByImageId"), imageId);
            return true;
        }
        return false;
    }
    @Override
    public int insert(UserImage record) {
        return 0;
    }

    @Override
    public boolean insertSelective(UserImage userImage) {
        if (userImage.getImageId() != null && userImage.getImageId().length()>0 ) {
            sqlSessionTemplate.insert(getSqlName("insertSelective"), userImage);
            return true;
        }
        return false;
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
	public List<UserImage> getUserId(String UserId) {
		return sqlSessionTemplate.selectList(getSqlName("selectByUserId"), UserId);
	}
	//String applyId, String type
	@Override
	public List<UserImageVO> selectUserImageByApplyIdAndType(Map<String,Object> paramsMap) {
		return sqlSessionTemplate.selectList("selectUserImageByApplyIdAndType",paramsMap);
	}

	@Override
	public boolean updateByImageId(UserImage userImage) {
		if (userImage.getUserId() != null && userImage.getImageId().length()>0 ) {
			sqlSessionTemplate.update(getSqlName("updateByImageId"), userImage);
            return true;
		}

        return false;
	}

	@Override
	public List<UserImageVO> selectUserImageByUserIdAndType(Map<String, String> paramsMap) {
		return sqlSessionTemplate.selectList("selectUserImageByUserIdAndType",paramsMap);
	}
	
	@Override
	public boolean updateImageByUserIdAndTypeAndUrl(Map<String, String> parmMap) {
		sqlSessionTemplate.update(getSqlName("updateByUserIdAndTypeAndUrl"), parmMap);
		return true;
	}

	@Override
	public List<UserImageVO> selectByApplyId(String applyId) {
		return this.sqlSessionTemplate.selectList(this.getSqlName("selectByApplyId"),applyId);
	}

	/**
	 * @Title: deleteByApplyIdList 
	 * @Description:根据applyId集合删除图片信息 
	 * @time: 2017年1月18日 下午5:12:12  
	 * @return:boolean
	 */
	@Override
	public boolean deleteImageByApplyIdList(List<String> applyIdList){
        if (applyIdList!=null){
            sqlSessionTemplate.delete(getSqlName("deleteImageByApplyIdList"), applyIdList);
            return true;
        }
        return false;
	}

}