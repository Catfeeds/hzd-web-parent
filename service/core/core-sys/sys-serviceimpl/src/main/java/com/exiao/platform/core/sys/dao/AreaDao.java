/**
 * 
 */
package com.exiao.platform.core.sys.dao;

import java.util.List;

import com.exiao.platform.core.sys.data.Area;
import com.exiao.platform.framework.core.storage.StorageProvider;

/**
 * @author allen.shen
 * @Date 2015年10月16日
 * 
 * description: 
 */
public interface AreaDao extends StorageProvider<Area> {
	
	public List<Area> getAreaInfoByProvince();
	
	public List<Area> getAreaInfoByCity();
	
	public List<Area> getAreaInfoByCounty();

}
