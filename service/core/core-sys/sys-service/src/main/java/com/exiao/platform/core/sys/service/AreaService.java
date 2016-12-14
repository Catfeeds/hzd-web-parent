/**
 * 
 */
package com.exiao.platform.core.sys.service;

import java.util.List;

import com.exiao.platform.common.util.rpc.result.Result;
import com.exiao.platform.core.sys.model.AreaVO;
import com.exiao.platform.framework.core.service.CommonBaseService;

/**
 * @author allen.shen
 * @Date 2015年10月10日
 * 
 * description: 
 */
public interface AreaService extends CommonBaseService<AreaVO> {
	
	/**
	 * 根据参数查询省/市/区县列表
	 * @param params
	 * @return
	 */
	public Result<List<AreaVO>> getAreaInfoByParams(String params);
	
	/**
	 * 根据父级编码查询子集列表
	 * @param parentCode
	 * @return
	 */
	public Result<List<AreaVO>> getAreaInfoByParentCode(String parentCode);
	

}
