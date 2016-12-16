/**
 * 
 */
package com.hzcf.platform.core.sys.dao;

import java.util.List;

import com.hzcf.platform.core.sys.data.Area;
import com.hzcf.platform.framework.core.storage.IBaseDao;

/**
 * @author allen.shen
 * @Date 2015年10月16日
 * 
 * description: 
 */
public interface AreaDao extends IBaseDao<Area> {
	
	public List<Area> getAreaInfoByProvince();
	
	public List<Area> getAreaInfoByCity();
	
	public List<Area> getAreaInfoByCounty();

}
