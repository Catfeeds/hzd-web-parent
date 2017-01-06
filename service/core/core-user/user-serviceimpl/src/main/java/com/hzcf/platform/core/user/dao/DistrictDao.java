package com.hzcf.platform.core.user.dao;

import java.util.List;

import com.hzcf.platform.core.user.data.District;
import com.hzcf.platform.framework.core.storage.IBaseDao;

public interface DistrictDao  extends IBaseDao<District> {
    int deleteByPrimaryKey(String id);

    int insert(District record);

    int insertSelective(District record);

    District selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);
    
    List<District> selectAllProvince();
    
    List<District> selectAllCity();
}